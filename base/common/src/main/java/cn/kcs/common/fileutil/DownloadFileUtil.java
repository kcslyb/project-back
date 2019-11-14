package cn.kcs.common.fileutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * download file util
 *
 * @author kcs
 * @date 2019/08/14 11:00
 **/
public class DownloadFileUtil {
    private static Logger logger = LoggerFactory.getLogger("DownloadFileUtil");

    public static void downloadFile(HttpServletResponse response, String fileName, String path) {
        if (fileName != null) {
            //设置文件路径
            File file = new File(path);
            if (file.exists()) {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "multipart/form-data");
                try {
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            }
        }
    }
}
