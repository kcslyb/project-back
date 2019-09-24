package cn.kcs.common;

/**
 * @description: response message
 * @author: kcs
 * @date: 2019/08/13 15:57
 **/
public class ErrorResponse {

    private String msg;

    private String code;

    public ErrorResponse() {
    }

    public ErrorResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
