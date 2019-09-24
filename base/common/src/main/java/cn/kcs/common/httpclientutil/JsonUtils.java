package cn.kcs.common.httpclientutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @description: JSON装换工具
 * @author: kcs
 * @create: 2019-04-09 12:41
 **/
public class JsonUtils {

    /**
     * 对象转化为json字符串
     *
     * @param obj 待转化对象
     * @return 代表该对象的Json字符串
     */
    public static final String toJson(final Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * simpleMapToJsonStr
     *
     * @param map
     * @return
     */
    public static String simpleMapToJsonString(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = map.entrySet().iterator();
        stringBuffer.append("{");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            stringBuffer.append("\"");
            stringBuffer.append(entry.getKey());
            stringBuffer.append("\"");
            stringBuffer.append(":");
            stringBuffer.append("\"");
            stringBuffer.append(entry.getValue());
            stringBuffer.append("\"");
            stringBuffer.append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    /**
     * jsonStringToMap
     *
     * @param str
     * @return
     */
    public static Map jsonStringToMap(String str) {
        if (StringUtils.isEmpty(str)) {
            return new HashMap();
        }
        String sb = str.substring(1, str.length() - 1);
        String[] name = sb.split(",");
        String[] nn = null;
        Map<String, String> map = new HashMap<>();
        for (String aName : name) {
            nn = aName.split(":");
            map.put(nn[0].substring(1, nn[0].length() - 1), nn[1].substring(1, nn[1].length() - 1));
        }
        return map;
    }

    public static <T> List<T> jsonToObjectList(String json, Class<T> tClass) {
        JSONObject temp = JSON.parseObject(json);
        JSONArray array = temp.getJSONArray("message");
        List<T> list = new ArrayList<>();
        for (Object o : array) {
            JSONObject record = (JSONObject) o;
            T code = JSONObject.toJavaObject(record, tClass);
            list.add(code);
        }
        return list;
    }
}
