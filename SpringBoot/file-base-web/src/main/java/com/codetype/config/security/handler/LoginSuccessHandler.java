package com.codetype.config.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codetype.config.jwt.JwtUtils;
import com.codetype.redis.RedisService;
import com.codetype.web.blog.entity.User;
import com.codetype.status.StatusCode;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 */
@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisService redisService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        //生成token
        String token = jwtUtils.generateToken(user);
        //该token过期的时间，返回给前端
        long jwtTime = Jwts.parser() //得到DefaultJwtParser
                .setSigningKey(jwtUtils.getSecret()) //设置秘钥
                .parseClaimsJws(token.replace("jwt", ""))
                .getBody().getExpiration().getTime();
        //设置返回VO
        LoginResult loginResult = new LoginResult();
        loginResult.setId(user.getId());
        loginResult.setCode(StatusCode.SUCCESS_CODE);
        loginResult.setToken(token);
        loginResult.setExpireTime(jwtTime);
        /**
         * SerializerFeature.DisableCircularReferenceDetect:
         * 循环引用==>简单说，重复引用就是一个集合/对象中的多个元素/属性同时引用同一对象，
         *          循环引用就是集合/对象中的多个元素/属性存在相互引用导致循环。
         */
        String res = JSONObject.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(res.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        //把生成的token存入Redis
        String token_key = "token_"+token;
        redisService.set(token_key,token,jwtUtils.getExpiration()/1000);

        //清除验证码
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getRemoteAddr()+code;
        redisService.del(key);
    }
}
