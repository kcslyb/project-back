package cn.kcs.websocket;

import cn.kcs.common.util.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @description: CheckCenterController
 * @author: kcs
 * @date: 2019-05-10 10:03
 **/
@Api(value = "web socket", description = "WEB SOCKET API")
@Controller
@RequestMapping("/socket")
public class CheckCenterController {

    //推送数据接口
    @ResponseBody
    @RequestMapping("/push/{cid}")
    @ApiOperation(value = "推送数据接口")
    public JSONObject pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return CommonUtil.errorJson(cid + "#" + e.getMessage());
        }
        return CommonUtil.successJson(cid + message);
    }
}
