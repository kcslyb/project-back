package cn.kcs.common.httpclientutil;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @description: http request util
 * @author: kcs
 * @create: 2019-04-09 11:33
 **/
public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static CookieStore cookieStore = new BasicCookieStore();
    private static CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    private static String token = new String();
    private static String access_token = new String();
    private static String token_type = new String();


    /**
     * 模拟登录
     */
    public static void simulateLogin() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "");
        jsonObject.put("userPassword", "");
        jsonObject.put("rememberMe", "");
        doPostJson("url", jsonObject.toJSONString());
    }

    /**
     * 获取toke
     */
    public static void getToken() {
        logger.info("::toke认证...");
        Map<String, String> map = new HashMap<>();
        map.put("client_id", Constant.CLIENT_ID);
        map.put("client_secret", Constant.CLIENT_SECRET);
        map.put("grant_type", Constant.GRANT_TYPE);
        if (StringUtils.isEmpty(token)) {
            token = doPost(Constant.TOKEN_URL, map);
            Map tokenMap = JsonUtils.jsonStringToMap(token);
            Iterator it = tokenMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                if ("access_token".equals(key)) {
                    access_token = (String) entry.getValue();
                    logger.info("::access_token:" + access_token);
                }
                if ("token_type".equals(key)) {
                    token_type = (String) entry.getValue();
                    logger.info("::token_type:" + token_type);
                }
            }
        }
        logger.info("::toke认证完成...");
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param param
     * @return String
     */
    public static String doGet(String url, Map<String, String> param) {
        if (param != null) {
            logger.info("::开始发送带map类型参数的get请求...");
            logger.info("::请求参数为: " + param.toString());
        }
        logger.info("::请求rul为:" + url);
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            if (!StringUtils.isEmpty(token)) {
                //添加http头信息
                httpGet.addHeader("Authorization", token_type + " " + access_token);
            }
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            logger.info("::返回状态码为:" + Integer.toString(response.getStatusLine().getStatusCode()));
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.info("::请求结束-->response为:" + resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     *
     * @param url
     * @return String
     */
    public static String doGet(String url) {
        logger.info("::开始发送不带参数的get请求...");
        return doGet(url, null);
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param param
     * @return String
     */
    public static String doPost(String url, Map<String, String> param) {
        if (param != null) {
            logger.info("::开始发送带map类型参数的post请求...");
            logger.info("::请求参数为: " + param.toString());
        }
        logger.info("::请求rul为:" + url);
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (!StringUtils.isEmpty(token)) {
                //添加http头信息
                httpPost.setHeader("Authorization", token_type + " " + access_token);
            }
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("::请求结束-->response为:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     * @return String
     */
    public static String doPost(String url) {
        logger.info("::开始发送不带参数的post请求...");
        return doPost(url, null);
    }

    /**
     * 传送json类型的post请求
     *
     * @param url
     * @param json
     * @return String
     */
    public static String doPostJson(String url, String json) {
        if (json != null) {
            logger.info("::开始发送带json类型参数的post请求...");
            logger.info("::请求参数为: " + json);
        }
        logger.info("::请求rul为:" + url);
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            if (!StringUtils.isEmpty(token)) {
                //添加http头信息
                httpPost.setHeader("Authorization", token_type + " " + access_token); //认证token
            }
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("::请求结束-->response为:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     * @return String
     */
    public static String doPutJson(String url) {
        logger.info("::开始发送不带参数的put请求...");
        return doPutJson(url, null);
    }

    /**
     * 传送json类型的put请求
     *
     * @param url
     * @param json
     * @return String
     */
    public static String doPutJson(String url, String json) {
        if (json != null) {
            logger.info("::开始发送带json类型参数的put请求...");
            logger.info("::请求参数为: " + json);
        }
        logger.info("::请求rul为:" + url);
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPut httpPut = new HttpPut(url);
            if (!StringUtils.isEmpty(token)) {
                //添加http头信息
                httpPut.setHeader("Authorization", token_type + " " + access_token); //认证token
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPut.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPut);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("::请求结束-->response为:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 发送delete请求
     *
     * @param url
     * @return String
     */
    public static String doDeleteJson(String url) {
        logger.info("::开始发送不带参数的delete请求...");
        logger.info("::请求url为: " + url);
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpDelete httpDelete = new HttpDelete(url);
            if (!StringUtils.isEmpty(token)) {
                //添加http头信息
                httpDelete.setHeader("Authorization", token_type + " " + access_token);
            }
            // 执行http请求
            response = httpClient.execute(httpDelete);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("::请求结束-->response为:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}