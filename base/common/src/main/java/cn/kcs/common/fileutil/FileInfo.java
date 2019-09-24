package cn.kcs.common.fileutil;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * file info
 *
 * @author kcs
 * @date 2019/08/14 16:05
 **/
public class FileInfo {
    /**
     * 读取文件创建时间
     */
    public static String getFileCreateTime(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.endsWith(".txt")) {
                    strTime = line.substring(0, 17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 读取文件修改时间的方法1
     */
    @SuppressWarnings("deprecation")
    public static void getModifiedTime_1() {
        File f = new File("C:\\test.txt");
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        cal.setTimeInMillis(time);
        //此处toLocalString()方法是不推荐的，但是仍可输出
        System.out.println("修改时间[1] " + cal.getTime().toLocaleString());
        //输出：修改时间[1]    2009-8-17 10:32:38
    }

    /**
     * 读取修改时间的方法2
     */
    public static void getModifiedTime_2() {
        File f = new File("C:\\test.txt");
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        System.out.println("修改时间[2] " + formatter.format(cal.getTime()));
    }
}
