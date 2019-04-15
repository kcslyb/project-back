package cn.kcs.common.exception;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.constants.ErrorEnum;
import com.alibaba.fastjson.JSONObject;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * 调用时可以在任何代码处直接throws这个Exception,
     * 都会统一被拦截,并封装好json返回给前台
     *
     * @param errorEnum 以错误的ErrorEnum做参数
     */
    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}
