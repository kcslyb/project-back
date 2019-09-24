package cn.kcs.user.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.Md5Utils;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.dto.UserInfo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kcs
 */
@Api(value = "用户", description = "用户登录")
@RestController
public class BaseController {

    @Decrypt
    @Encrypt
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "rememberMe,userName,userPassword");
        String rememberMe = requestJson.getString("rememberMe");
        String userName = requestJson.getString("userName");
        String userPassword = requestJson.getString("userPassword");
        Subject currentUser = SecurityUtils.getSubject();
        userPassword = Md5Utils.GetMD5Code(Md5Utils.GetMD5Code(userPassword));
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword, rememberMe);
        ResponseEntity responseEntity;
        try {
            currentUser.login(token);
            return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "获取以登录的用户信息", response = UserInfo.class)
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

    @Decrypt
    @Encrypt
    @ApiOperation(value = "api")
    @PostMapping(value = "/api")
    public ModelAndView api(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("/swagger-ui.html");
    }
}
