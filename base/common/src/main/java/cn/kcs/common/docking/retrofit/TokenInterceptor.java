package cn.kcs.common.docking.retrofit;

import cn.kcs.common.docking.util.FileUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author kcs
 * @date 2019-09-20 14:06
 **/
public class TokenInterceptor implements Interceptor {
    static int NEED_AUTH = 401;
    static String token = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        FileUtil fileUtil = new FileUtil();
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        //当发起请求发现服务器返回token值过期的信息，这个时候就重新获取最新的Token值然后重新发起请求
        if (originalResponse.code() == NEED_AUTH) {
            if (fileUtil.isOverTime()) {
                //获取token逻辑 保存到token文件里或session里
            }
            // 然后保存token值
            Request newRequest = request.newBuilder()
                    //为请求重新添加Token值
                    .header("Authorization", fileUtil.readToken())
                    .build();
            originalResponse.body().close();
            return chain.proceed(newRequest);
        }
        return originalResponse;
    }
}
