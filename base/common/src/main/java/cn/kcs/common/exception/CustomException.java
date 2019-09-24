package cn.kcs.common.exception;

import org.springframework.http.ResponseEntity;

/**
 * @author kcs
 * @date 2018-10-26 11:25
 **/
public class CustomException extends RuntimeException {
    private ResponseEntity responseEntity;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     *
     * @param responseEntity 以错误的  做参数
     */

    public CustomException(ResponseEntity responseEntity) {
        this.responseEntity = responseEntity;
    }

    public ResponseEntity getResponseEntity() {
        return responseEntity;
    }
}
