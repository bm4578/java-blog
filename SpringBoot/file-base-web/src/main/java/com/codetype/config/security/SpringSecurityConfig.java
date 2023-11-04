package com.codetype.config.security;

import com.codetype.config.security.detailservice.CustomerUserDetailsService;
import com.codetype.config.security.filter.CheckTokenFilter;
import com.codetype.config.security.handler.CustomAccessDeineHandler;
import com.codetype.config.security.handler.CustomizeAuthenticationEntryPoint;
import com.codetype.config.security.handler.LoginFailureHandler;
import com.codetype.config.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  //启用Spring Security
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDeineHandler customAccessDeineHandler;
    @Autowired
    private CheckTokenFilter checkTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //官方推荐这种加密方式
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置权限资源
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加token拦截器
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();//支持跨域
        //表单登录
        http.formLogin()
                .loginProcessingUrl("/api/user/login")
                //　自定义的登录验证成功或失败后的去向
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                // 禁用csrf防御机制(跨域请求伪造)，这么做在测试和开发会比较方便。
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(  "/v3/**","/profile/**","/swagger-ui.html","/doc.html",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**","/swagger-ui/**","/v2/**","/favicon.ico","/webjars/springfox-swagger-ui/**","/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()

                /*
                   设置不需要token验证权限的页面
                   还需要在filter中设置放开token拦截的路径
                 */
                .antMatchers("/api/user/login","/api/user/imgCode","/api/user/register").permitAll()
                .antMatchers("/api/article/list/**","/api/sysImg").permitAll()
                .antMatchers("/files/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customizeAuthenticationEntryPoint)//匿名用户
                .accessDeniedHandler(customAccessDeineHandler);//认证无权限
    }
    /**
     * 配置认证处理器
     * 自定义的UserDetailsService
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService);
    }
}
