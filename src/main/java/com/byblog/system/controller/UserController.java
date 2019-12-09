package com.byblog.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.byblog.common.BaseController;
import com.byblog.common.JsonResult;
import com.byblog.common.exception.BusinessException;
import com.byblog.common.utils.StringUtil;
import com.byblog.system.model.Token;
import com.byblog.system.model.User;
import com.byblog.system.service.TokenService;
import com.byblog.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Api(value = "用户管理", tags = "user")
@RestController
@RequestMapping("v1/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger("UserController");

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    private static final String slat = "!@#$%^&*()9876543";


    @ApiOperation(value = "注册用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "register")
    public JsonResult register(String username, String password) {
        if (StringUtil.isBlank(username)) {
            return JsonResult.error("用户名不能为空");
        }
        if (StringUtil.isBlank(password)) {
            return JsonResult.error("用不存不能为空");
        }

        int status = userService.getUserByName(username);
        if (status == 3) {
            String base = password + "/" + slat;
            User user = new User();
            user.setNickname(username);
            user.setUsername(username);
            user.setPassword(DigestUtils.md5DigestAsHex(base.getBytes()));
            user.setSex("女");
            user.setBirthday(new Date());
            int savetype = userService.setSaveUser(user);
            if (savetype == 0)
                return JsonResult.ok("注册成功");
            else
                return JsonResult.error("注册失败");
        } else {
            return JsonResult.error("用户已存在");
        }
    }


    @ApiOperation(value = "注册用户", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "login")
    public JsonResult login(String username, String password) {

        if (StringUtil.isBlank(username)) {
            return JsonResult.error("用户名不能为空");
        }
        if (StringUtil.isBlank(password)) {
            return JsonResult.error("用密码不能为空");
        }

        int status = userService.getUserByName(username);
        if (status == 1) {
            String token = makeToken();
            User user = userService.getUser(username);
            String base = password + "/" + slat;
            logger.info("1、user："+user);
            logger.info("密码："+user.getPassword()+"/---------------------/"+DigestUtils.md5DigestAsHex(base.getBytes()));
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(base.getBytes()))) {

                Token mToken = tokenService.selectTokenByID(user.getUserid() + "");
                logger.info("1、我的token："+mToken);
                if (mToken != null) {
                    mToken.setToken(token);
                    mToken.setUpdatetime(new Date());
                } else {
                    mToken = new Token();
                    mToken.setToken(token);
                    mToken.setUserid(user.getUserid());
                    Date date = new Date();
                    mToken.setCreatetime(date);
                    mToken.setUpdatetime(date);
                }
                logger.info("2、我的token："+mToken);
                tokenService.saveOrUpdateToken(mToken);
                return JsonResult.ok("登录成功").put("access_token", token).put("data", user);
            } else {
                return JsonResult.error("密码错误");
            }
        } else if (status == 2) {
            return JsonResult.error("账号异常，账号存在多个");
        } else if (status == 3) {
            return JsonResult.error("账号不存在");
        } else {
            return JsonResult.error("账号不存在");
        }

    }


    /**
     * 生成Token
     *
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)+System.currentTimeMillis()+new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            logger.error("token生成失败！");
            return null;
        }
    }
}
