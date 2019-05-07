package cn.kcs.user;

import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.note.entity.TLogger;
import cn.kcs.note.service.TLoggerService;
import org.apache.commons.lang.StringUtils;
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
    private TLoggerService tLoggerService;

    @Pointcut("execution(public * cn.kcs.order.controller.*.*(..)) || execution(public * cn.kcs.note.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        TLogger logDto = new TLogger();
        logDto.setLogAccessType(request.getRequestURI());
        logDto.setLogAccessUserId(StringUtils.isEmpty(LoginInfo.getUserId()) ? "登录" : LoginInfo.getUserId());
        logDto.setLogAccessUserName(StringUtils.isEmpty(LoginInfo.getUserName()) ? "登录" : LoginInfo.getUserName());
        logDto.setLogAccessUrl(request.getMethod() + ": " + request.getRequestURI());
        logDto.setLogAccessIp(request.getRemoteAddr());
        tLoggerService.insert(logDto);
        // 记录下请求内容
        logger.info("操作者 :" + logDto.getLogAccessUserName());
        logger.info("请求IP :" + request.getRemoteAddr());
        logger.info("请求时间 : " + logDto.getLogAccessTime());
        logger.info("请求类型 : " + request.getMethod());
        logger.info("请求路径 : " + request.getRequestURL().toString());
    }

//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
//    }
}
