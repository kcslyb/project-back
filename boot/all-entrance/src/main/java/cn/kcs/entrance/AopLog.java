package cn.kcs.entrance;

import cn.kcs.user.entity.LoggerDto;
import cn.kcs.user.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop log
 * @author kcs
 * @date 2018-11-06 13:41
 **/
@Aspect
@Component
public class AopLog {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoggerService loggerService;

    @Value(value = "${log_aop_switch}")
    private boolean logAopSwitch;

    @Pointcut("execution(public * cn.kcs.order.controller.*.*(..))" +
            "|| execution(public * cn.kcs.user.controller.*.*(..))" +
            "|| execution(public * cn.kcs.schedule.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();
        LoggerDto loggerDto = new LoggerDto();
        loggerDto.setLogRequestUrl(attributes.getSessionId().concat("-").concat(request.getRequestURI()));
        loggerDto.setLogRequestRemoteIp(request.getRemoteAddr());
        loggerDto.setLogRequestParameter(String.valueOf(args.length));
        loggerDto.setLogRequestMethodAndInterface(request.getMethod().concat("-").concat(joinPoint.getStaticPart().getSignature().getName()));
        loggerDto.setLogRequestPageName(request.getLocalName());
        String s = request.getRequestURL().toString();
        boolean flag = (s.length() > 50);
        if (flag) {
            s = s.substring(0, 50);
        }
        loggerDto.setLogRequestDescribe(s);
        loggerDto.setLogType("1");
        if (logAopSwitch) {
            loggerService.insert(loggerDto);
        }
        logger.info("操作者 :" + loggerDto.getLogUserId());
        logger.info("请求IP :" + loggerDto.getLogRequestRemoteIp());
        logger.info("请求时间 : " + loggerDto.getLogRequestTime());
        logger.info("请求类型和接口 : " + loggerDto.getLogRequestMethodAndInterface());
        logger.info("请求路径 : " + loggerDto.getLogRequestDescribe());
    }

}
