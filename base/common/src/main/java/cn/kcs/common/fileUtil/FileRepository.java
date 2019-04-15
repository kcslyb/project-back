package cn.kcs.common.fileUtil;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 本地文件存储
 */
public interface FileRepository {

    /**
     * 删除对象
     *
     * @param key
     * @param bucket
     */
    void deleteObject(String key, String bucket);

    InputStream getObject(String key, String bucketName);

    UploadFilePath store(MultipartFile file);

    UploadFilePath store(MultipartFile file, String destPath);

    /**
     * 保存比特数据
     *
     * @param data     对象内容
     * @param path     保存文件的相对路径
     * @param fileName 文件名包括后缀
     * @return
     */
    UploadFilePath store(byte[] data, String path, String fileName);


    /**
     * @param input    对象流
     * @param path     保存文件的相对路径
     * @param fileName 文件名包括后缀
     * @return
     */
    UploadFilePath store(InputStream input, Long size, String path, String fileName);
}
