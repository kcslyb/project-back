package cn.kcs.note.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.MD5Utils;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.dto.UserDto;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "用户", description = "用户登录")
@RestController
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Decrypt
    @Encrypt
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "rememberMe,userName,userPassword");
        String rememberMe = requestJson.getString("rememberMe");
        String userName = requestJson.getString("userName");
        String userPassword = requestJson.getString("userPassword");
        JSONObject returnData = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        userPassword = MD5Utils.GetMD5Code(MD5Utils.GetMD5Code(userPassword));
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword, rememberMe);
        try {
            currentUser.login(token);
            returnData.put("status", HttpStatus.OK.value() + "");
            returnData.put("message", HttpStatus.OK.getReasonPhrase());
        } catch (AuthenticationException e) {
            returnData.put(HttpStatus.BAD_REQUEST.value() + "", HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return returnData;
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "获取以登录的用户信息", response = UserDto.class)
    @PostMapping(value = "/getInfo")
    public JSONObject getActiveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return CommonUtil.successJson(userInfo);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "退出登录")
    @PostMapping(value = "/logout")
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return CommonUtil.successJson();
    }
}
