package cn.kcs.user.controller;

import cn.kcs.common.util.Md5Utils;
import cn.kcs.common.util.RandomUtil;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.mail.MailDto;
import cn.kcs.mail.MailServiceUtil;
import cn.kcs.user.entity.dto.LoginDto;
import cn.kcs.user.entity.dto.UserInfo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kcs
 */
@Api(value = "用户", description = "用户登录")
@RestController
public class BaseController {

    @Autowired
    private MailServiceUtil mailServiceUtil;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        Subject currentUser = SecurityUtils.getSubject();
        String userPassword = Md5Utils.GetMD5Code(Md5Utils.GetMD5Code(loginDto.getPassWord()));
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUserName(), userPassword, loginDto.isRemember());
        try {
            currentUser.login(token);
            return getActiveUser();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "获取以登录的用户信息", response = UserInfo.class)
    @PostMapping(value = "/getInfo")
    public ResponseEntity getActiveUser() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "退出登录")
    @PostMapping(value = "/logout")
    public ResponseEntity logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "api")
    @GetMapping(value = "/api")
    public ModelAndView api() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @PostMapping("/mail")
    public ResponseEntity sendMail(String mail) {
        String s = RandomUtil.generateRandomNumber(4);
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute(mail);
        session.setAttribute(mail, s);
        MailDto mailDto = new MailDto(new String[]{mail}, "sign in code", s);
        mailServiceUtil.sendSimpleMail(mailDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
