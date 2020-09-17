//package cn.kcs.websocket;
//
//import io.swagger.annotations.ApiOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.IOException;
//
///**
// * Check Center Controller
// *
// * @author kcs
// * @date 2019-05-10 10:03
// **/
//@Controller
//@RequestMapping("/socket")
//public class CheckCenterController {
//    private Logger LOGGER = LoggerFactory.getLogger("CheckCenterController");
//
//    /**
//     * 推送数据接口
//     *
//     * @param cid     推送唯一标示
//     * @param message 推送信息
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/push/{cid}")
//    @ApiOperation(value = "推送数据接口")
//    public ResponseEntity pushToWeb(@PathVariable String cid, String message) {
//        try {
//            WebSocketServer.sendInfo(message, cid);
//        } catch (IOException e) {
//            LOGGER.info("SOCKET推送信息异常:", e);
//            return new ResponseEntity<>("信息推送异常,请稍后再试", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity(HttpStatus.OK);
//    }
//}
