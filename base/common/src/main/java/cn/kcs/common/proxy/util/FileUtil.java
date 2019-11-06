package cn.kcs.common.proxy.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kcs
 * @date 2019-09-20 23:03
 **/
public class FileUtil {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String filePath = System.getProperty("user.dir") + "token.text";

    public String readToken() {
        String token = "";
        String encoding = "UTF-8";
        File file = new File(filePath);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tokenStr = bufferedReader.readLine();
            token = tokenStr.split("=")[1];
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    public void writerToken(String token) {
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("");
            String now = simpleDateFormat.format(new Date());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("token=").append(token).append("\n");
            stringBuffer.append("time=").append(now);
            fileWriter.write(stringBuffer.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOverTime() {
        String time = "";
        boolean isOver = false;
        String encoding = "UTF-8";
        File file = new File(filePath);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String token = bufferedReader.readLine();
            String timeStr = bufferedReader.readLine();
            if (StringUtils.isBlank(token) || StringUtils.isBlank(timeStr)) {
                return true;
            }
            time = timeStr.split("=")[1];
            Date date = simpleDateFormat.parse(time);
            long temp = System.currentTimeMillis() - date.getTime();
            isOver = temp / 1000 > 7200;
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isOver;
    }


}
