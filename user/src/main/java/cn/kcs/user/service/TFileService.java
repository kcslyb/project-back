package cn.kcs.user.service;

import cn.kcs.user.entity.TFile;
import cn.kcs.user.entity.dto.FileDto;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * (TFile)表服务接口
 *
 * @author kcs
 * @since 2019-04-19 15:17:08
 */
public interface TFileService {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    TFile queryById(String fileId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TFile> queryAllByLimit(TFile tFile, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<TFile> queryAll(TFile tFile);

    /**
     * 新增数据
     *
     * @param tFile 实例对象
     * @return 实例对象
     */
    TFile insert(TFile tFile);

    /**
     * 修改数据
     *
     * @param tFile 实例对象
     * @return 实例对象
     */
    TFile update(TFile tFile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    boolean deleteById(String fileId);

    List<FileDto> queryFileBase64ByIds(List<String> fileIds);
}