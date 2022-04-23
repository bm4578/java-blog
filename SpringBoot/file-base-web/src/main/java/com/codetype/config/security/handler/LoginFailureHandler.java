package com.codetype.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codetype.config.security.exception.CustomerAuthenionException;
import com.codetype.config.security.exception.ImageException;
import com.codetype.config.security.exception.UserException;
import com.codetype.utils.ResultVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 */

@Component("loginFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //设置响应状态码
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        String str = null;
        int code = 500;
        if(e instanceof AccountExpiredException){
            str = "账户过期，登录失败!";
        }else if(e instanceof BadCredentialsException){
            str = "用户名或密码错误，登录失败!";
        } else if (e instanceof UsernameNotFoundException) {
            str = "用户名不存在";
        }else if(e instanceof CredentialsExpiredException){
            str = "密码过期，登录失败!";
        }else if(e instanceof DisabledException){
            str = "账户被禁用，登录失败!";
        }else if(e instanceof LockedException){
            str = "账户被锁，登录失败!";
        }else if(e instanceof InternalAuthenticationServiceException){
            str = "账户不存在，登录失败!";
        }else if(e instanceof CustomerAuthenionException){
            //token验证失败
            code = 600;
            str = e.getMessage();
        } else if (e instanceof UserException){
            //验证码失败
            code = 601;
            str = e.getMessage();
        }else if (e instanceof ImageException){
            //验证码失败
            code = 602;
            str = e.getMessage();
        }
        else{
            str = "登录失败!";
        }

        String res = JSONObject.toJSONString(new ResultVo<>(str, code, null), SerializerFeature.DisableCircularReferenceDetect);
        out.write(res.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
