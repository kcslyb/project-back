package cn.kcs.common.docking.converter;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * @author kcs
 * @date 2019-09-23 10:05
 **/
public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T t) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(t));
    }
}
