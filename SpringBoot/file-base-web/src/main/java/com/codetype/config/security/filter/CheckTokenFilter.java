package com.codetype.config.security.filter;

import org.apache.commons.lang3.StringUtils;
import com.codetype.config.jwt.JwtUtils;
import com.codetype.config.security.detailservice.CustomerUserDetailsService;
import com.codetype.config.security.exception.CustomerAuthenionException;
import com.codetype.config.security.exception.ImageException;
import com.codetype.config.security.handler.LoginFailureHandler;
import com.codetype.redis.RedisService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 配置token拦截器
 */

@Data
@Component("checkTokenFilter")
public class CheckTokenFilter extends OncePerRequestFilter {
    @Value("${itmk.loginUrl}")
    private String loginUrl;
    @Value("${itmk.imgUrl}")
    private String imgUrl;
   @Value("${itmk.register}")
   private String register;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private RedisService redisService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //不需要token验证
        if (StringUtils.contains(httpServletRequest.getServletPath(), "swagger")
                || StringUtils.contains(httpServletRequest.getServletPath(), "webjars")
                || StringUtils.contains(httpServletRequest.getServletPath(), "/api/sysImg")
                || StringUtils.contains(httpServletRequest.getServletPath(), "/api/article/list")
                || StringUtils.contains(httpServletRequest.getServletPath(), "/files")
                || StringUtils.contains(httpServletRequest.getServletPath(), "doc")
                || StringUtils.contains(httpServletRequest.getServletPath(), "v3")
                || StringUtils.contains(httpServletRequest.getServletPath(), "profile")
                || StringUtils.contains(httpServletRequest.getServletPath(), "swagger-ui")
                || StringUtils.contains(httpServletRequest.getServletPath(), "swagger-resources")
                || StringUtils.contains(httpServletRequest.getServletPath(), "csrf")
                || StringUtils.contains(httpServletRequest.getServletPath(), "favicon")
                || StringUtils.contains(httpServletRequest.getServletPath(), "v2")
        ) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            try {
                //获取请求的url
                String url = httpServletRequest.getRequestURI();
                //登录请求时才做验证码的验证
                if (url.equals(loginUrl)){
                    validateImage(httpServletRequest);
                }if (url.equals(register)){
                    validateImage(httpServletRequest);
                }
                //token验证
                if (!url.equals(loginUrl) && !url.equals(imgUrl) && !url.equals(register)) {
                    validateToken(httpServletRequest);
                }
            } catch (AuthenticationException e) {
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    //验证码验证
    private void validateImage(HttpServletRequest request){

        //1.获取登录请求的验证码
        String inputCode = request.getParameter("code");
        //判断验证码是否为空
        if(StringUtils.isEmpty(inputCode)) {
            throw new ImageException("验证码不能为空!");
        }
        //获取验证码
        String key = request.getRemoteAddr()+inputCode;

        //获取redis中的验证码
        String imgCode = redisService.get(key);
        //判断验证码是否相等
        if(!inputCode.equalsIgnoreCase(imgCode)){
            throw new ImageException("验证码输入错误!");
        }
    }
    //token验证
    private void validateToken(HttpServletRequest request){
        //从请求的头部获取token
        String token = request.getHeader("token");
        //如果请求头部没有获取到token，则从请求参数中获取token
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(StringUtils.isEmpty(token)){
            throw new CustomerAuthenionException("token不存在！");
        }
        //redis中是否含有token
        String token_key = "token_"+token;
        String redisToken = redisService.get(token_key);
        if (!token.equals(redisToken)){
            throw new CustomerAuthenionException("token验证失败！");
        }
        if(StringUtils.isEmpty(redisToken)){
            throw new CustomerAuthenionException("token过期");
        }
        //解析token
        String username = jwtUtils.getUsernameFromToken(token);
        if(StringUtils.isEmpty(username)){
            throw new CustomerAuthenionException("token解析失败!");
        }
        //获取用户信息
        UserDetails user = customerUserDetailsService.loadUserByUsername(username);
        if(user == null){
            throw new CustomerAuthenionException("用户不存在");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //设置到spring security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
