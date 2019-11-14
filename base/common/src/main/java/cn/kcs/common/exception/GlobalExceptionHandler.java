package cn.kcs.common.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kcs
 * @date 2018-10-26 11:20
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    /**
     * 异常拦截器
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultErrorHandler(Exception e) {
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(element.getClassName()).append(":")
                    .append(element.getFileName())
                    .append(element.getLineNumber());
            logger.error("服务器异常:{}:{};错误位置:{}", e.getCause(), e.getMessage(), stringBuffer);
        }
        logger.error("", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     *
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity httpRequestMethodHandler() {
        return new ResponseEntity<>("请求方式错误", HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 本系统自定义错误的拦截器
     * 拦截到此错误之后,就返回这个类里面的json给前端
     * 常见使用场景是参数校验失败,抛出此错,返回错误信息给前端
     *
     * @param customException Custom Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity customExceptionHandler(CustomException customException) {
        return customException.getResponseEntity();
    }

    /**
     * 权限不足报错拦截
     *
     * @return ResponseEntity
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity unauthorizedExceptionHandler() {
        return new ResponseEntity<>("权限不足", HttpStatus.FORBIDDEN);
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     *
     * @param e Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity unauthenticatedException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
