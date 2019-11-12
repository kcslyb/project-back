package cn.kcs.common.fileutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * delete file util
 *
 * @author kcs
 * @date 2019/08/14 10:16
 **/
public class DeleteFileUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(DeleteFileUtil.class);

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return 是否成功
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            deleteFile(path);
        }
        String[] tempList = file.list();
        File temp;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                // 先删除文件夹里面的文件
                LOGGER.info("先删除文件夹" + path + "/" + tempList[i] + "的内容");
                delAllFile(path + "/" + tempList[i]);
                // 再删除空文件夹
                LOGGER.info("再删除文件夹" + path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            // 删除完里面所有内容
            LOGGER.info("正在删除文件夹" + folderPath + "中的内容");
            delAllFile(folderPath);
            File myFilePath = new File(folderPath);
            // 删除空文件夹
            myFilePath.delete();
            LOGGER.info("删除文件夹" + folderPath + "成功！");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            LOGGER.info("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            LOGGER.info("删除单个文件" + fileName + "失败！");
            return false;
        }
    }
}
