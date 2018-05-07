package com.hackdog.springbootlearn.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.hackdog.springbootlearn.dto.ResponseResult;
import com.hackdog.springbootlearn.enums.ResponseInfo;
import com.hackdog.springbootlearn.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 登录接口，由于UserService中是模拟返回用户信息的，
     * 所以用户名随意，密码123456
     *
     * @param body
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<User> login(@RequestBody String body) {
        ResponseResult responseResult = new ResponseResult();
        JSONObject userJson = JSONObject.parseObject(body);
        String username = userJson.getString("username");
        String pwd = userJson.getString("pwd");
        if (StringUtils.isEmpty(username)) {
            responseResult.setMsg("用户名不能为空！");
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            return responseResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            responseResult.setMsg("密码不能为空！");
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            return responseResult;
        }
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, pwd));
            User user = (User) subject.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }
            responseResult.setData(user);
            responseResult.setCode(ResponseInfo.SUCCESS.getCode());
            responseResult.setMsg(ResponseInfo.SUCCESS.getMsg());
            return responseResult;
        } catch (UnknownAccountException uae) {
            responseResult.setMsg("用户帐号不正确");
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            log.warn("用户帐号不正确");
            return responseResult;
        } catch (IncorrectCredentialsException ice) {
            responseResult.setMsg("用户密码不正确");
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            log.warn("用户密码不正确");
            return responseResult;
        } catch (LockedAccountException lae) {
            responseResult.setMsg("用户帐号被锁定");
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            log.warn("用户账号被锁定");
            return responseResult;
        } catch (AuthenticationException ae) {
            responseResult.setMsg(ae.getMessage());
            responseResult.setCode(ResponseInfo.FAIL.getCode());
            log.warn("登陆出错");
            return responseResult;
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return 注册成功返回标识1
     */
    @PostMapping("/register")
    public ResponseResult<User> register(User user) {
        return null;
    }
}
