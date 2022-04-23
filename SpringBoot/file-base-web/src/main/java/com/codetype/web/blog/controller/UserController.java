package com.codetype.web.blog.controller;

import com.codetype.config.jwt.JwtUtils;
import com.codetype.config.security.detailservice.CustomerUserDetailsService;
import com.codetype.config.security.exception.CustomerAuthenionException;
import com.codetype.redis.RedisService;
import com.codetype.utils.ResultUtils;
import com.codetype.web.blog.Vo.TokenVo;
import com.codetype.web.blog.dto.UserVO;
import com.codetype.web.blog.entity.User;
import com.codetype.web.blog.service.UserService;
import com.codetype.utils.ResultVo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Api(tags = "用户中心")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CustomerUserDetailsService  customerUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtUtils jwtUtils;


    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(dataType = "String",name = "token", value = "token(默认30min过期)",required = true)
    @GetMapping("/getAllUser")
    public ResultVo getAllUser(String token){
        List<User> list = userService.selectAll();
        return new ResultVo("success",200,list);
    }
    @ApiOperation(value = "获取用户基本信息")
    @ApiImplicitParam(dataType = "String",name = "token", value = "token(默认30min过期)",required = true)
    @GetMapping
    public ResultVo getalluser(){
        List<UserVO> selectalluser = userService.selectalluser();
        return new ResultVo("success",200,selectalluser);
    }

    @ApiOperation(value = "获取用户基本信息")
    @ApiImplicitParam(dataType = "String",name = "token", value = "token(默认30min过期)",required = true)
    @GetMapping({"{username}"})
    public ResultVo getUserName(@PathVariable("username") String username){
        List<UserVO> userVOS = userService.selectUsername(username);
        return new ResultVo("success",200,userVOS);
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "username", value = "用户名",required = true),
            @ApiImplicitParam(dataType = "String",name = "password", value = "密码",required = true),
            @ApiImplicitParam(dataType = "String",name = "code", value = "验证码",required = true)
    })
    @PostMapping(value = "/login")
    public void login(String username){
        customerUserDetailsService.loadUserByUsername(username);
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "username", value = "用户名",required = true),
            @ApiImplicitParam(dataType = "String",name = "password", value = "密码",required = true),
            @ApiImplicitParam(dataType = "String",name = "code", value = "验证码",required = true)
    })
    @PostMapping(value = "/register")
    public ResultVo register(String username , String password){
        try {
            customerUserDetailsService.register(username,password);
        }catch (Exception e){
            return new ResultVo(e.getMessage(),500);
        }
        return new ResultVo(200);
    }

    @CrossOrigin
    @ApiOperation(value = "获取验证码(60S过期)")
    @GetMapping ("/imgCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取验证码字符
        String yzm = defaultKaptcha.createText();
        log.debug("验证码:" + yzm);
        //2.获取ip
        String ip =request.getRemoteAddr();
        //3.存储到redis中
        //redis的key
        String key = ip+yzm;
        //设置验证码60S过期
        redisService.set(key,yzm,60L);
        //3.生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(yzm);
        //4.输出给前端
        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);

        if(out != null){
            out.close();
        }
    }
    @ApiOperation(value = "退出登录")
    @ApiImplicitParam(dataType = "String",name = "token", value = "token(默认30min过期)",required = true)
    @PostMapping("/loginOut")
    public ResultVo loginOut(String token,HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        //获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication != null){
            //清空用户信息
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            //清除token里面的Redis
            String token_key = "token_"+token;
            redisService.del(token_key);
        }
        return ResultUtils.success("退出登录成功");
    }

    @ApiOperation(value = "刷新token")
    @ApiImplicitParam(dataType = "String",name = "token",value = "刷新token",required = true)
    @PostMapping("/refreshToken")
    public ResultVo refreshToken(String token,HttpServletRequest request){
        //获取前端传来的token  首先从header里面取，如果没有，从参数里面取
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(StringUtils.isBlank(token)){
            throw new CustomerAuthenionException("token不能为空");
        }

        String reToken = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        //验证原来的token是否合法
        Boolean aBoolean = jwtUtils.validateToken(token, details);
        if(aBoolean){
            //生成新的token
            reToken = jwtUtils.refreshToken(token);
        }

        //取出本次token到期的时间,返回前端做判断
        long expireTime = Jwts.parser()   //得到DefaultJwtParser
                .setSigningKey(jwtUtils.getSecret())  //设置签名的秘钥
                .parseClaimsJws(reToken.replace("jwt_", ""))
                .getBody().getExpiration().getTime();
        //清除原来的token
        String oldTokenKey = "token_"+token;
        redisService.del(oldTokenKey);
        //存储新的token
        String newTokenKey = "token_"+reToken;
        redisService.set(newTokenKey,reToken,jwtUtils.getExpiration()/1000);
        TokenVo vo = new TokenVo();
        vo.setToken(reToken);
        vo.setExpireTime(expireTime);
        return ResultUtils.success("生成token成功!",vo);
    }

    /**
     * 查询用户详情
     * @param username
     * @return
     */
    @ApiOperation(value = "用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "token", value = "token",required = true),
            @ApiImplicitParam(dataType = "String",name = "username", value = "用户名",required = true),
    }
    )
    @PostMapping("/selectName")
    private ResultVo selectName(String username){
        User usersName = userService.getUsersName(username);
        return new ResultVo("",200,usersName);
    }

    @PutMapping
    private ResultVo updateUser(User user){
        Boolean aBoolean = userService.updateUserVo(user);
        return new ResultVo("",200,aBoolean);
    }
}

