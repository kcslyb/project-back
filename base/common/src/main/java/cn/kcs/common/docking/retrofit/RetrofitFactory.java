package cn.kcs.common.docking.retrofit;

import cn.kcs.common.docking.converter.FastJsonConverterFactory;
import cn.kcs.common.docking.util.Constant;
import cn.kcs.common.docking.util.OkHttpUtil;
import retrofit2.Retrofit;

/**
 * @author kcs
 * @date 2019-09-20 22:37
 **/
public class RetrofitFactory {
    public static <T> T getRetrofitHelper(Class T) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.baseUrl)
                .client(OkHttpUtil.getInstance())
                // 自定义的fastJsonConverter
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        return (T) retrofit.create(T);
    }
}
