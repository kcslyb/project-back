package cn.kcs.websocket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * 推送数据接口
     *
     * @param cid     推送唯一标示
     * @param message 推送信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/push/{cid}")
    @ApiOperation(value = "推送数据接口")
    public ResponseEntity pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("信息推送异常,请稍后再试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
