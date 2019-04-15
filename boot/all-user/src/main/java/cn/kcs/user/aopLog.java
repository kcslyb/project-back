package cn.kcs.user;

import cn.kcs.common.util.DataUtil;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.service.inter.LogService;
import cn.kcs.service.inter.dto.LogDto;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: aop log
 * @author: kcs
 * @create: 2018-11-06 13:41
 **/
@Aspect
@Component
public class aopLog {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LogService logService;

    @Pointcut("execution(public * cn.kcs.controller.action.*.*(..)) || execution(public * cn.kcs.note.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Session session = SecurityUtils.getSubject().getSession();
        LogDto logDto = new LogDto();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        if (session != null && jsonUser != null) {
            if ("/logout".equals(request.getRequestURI())) {
                logDto.setLogUsed(jsonUser.getString("userName") + "退出登录");
            } else {
                logDto.setLogUsed(jsonUser.getString("userName"));
            }
        } else {
            logDto.setLogUsed("登录");
        }
        logDto.setLogTime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        logDto.setLogOperate(request.getRequestURI());
        logDto.setLogAccessurl(request.getRequestURL().toString() + " IP:" + request.getRemoteAddr());
        logService.add(logDto);
        // 记录下请求内容
        logger.info("操作者 :" + logDto.getLogUsed());
        logger.info("请求IP :" + request.getRemoteAddr());
        logger.info("请求时间 : " + logDto.getLogTime());
        logger.info("请求类型 : " + request.getMethod());
        logger.info("请求路径 : " + request.getRequestURL().toString());
    }

//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
//    }
}
