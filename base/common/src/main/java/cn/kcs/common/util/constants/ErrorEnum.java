package cn.kcs.common.util.constants;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_200("200", "请求成功"),
    E_201("201", "登陆已过期,请重新登陆"),
    E_400("400", "请求异常,请稍后再试"),
    E_401("501", "请求方式有误,请检查GET/POST"),
    E_403("203", "缺少必填参数,请检查参数"),
    E_404("404", "请求资源不存在"),
    E_500("500", "服务器异常,请稍后再试"),
    E_502("502", "权限不足,请联系管理员"),
    E_501("501", "运行时异常,服务器正在更新,请稍后再试"),
    E_504("504", "服务器响应超时,请稍后再试");

    private String status;

    private String message;

    ErrorEnum() {
    }

    ErrorEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
