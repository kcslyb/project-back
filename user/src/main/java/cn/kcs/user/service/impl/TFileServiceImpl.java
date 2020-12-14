package cn.kcs.user.service.impl;

import cn.kcs.common.fileutil.FileToBase64;
import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.TFileDao;
import cn.kcs.user.entity.TFile;
import cn.kcs.user.entity.dto.FileDto;
import cn.kcs.user.service.TFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TFile)表服务实现类
 *
 * @author kcs
 * @since 2019-04-19 15:17:08
 */
@Service("tFileService")
public class TFileServiceImpl implements TFileService {
    @Resource
    private TFileDao tFileDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public TFile queryById(String fileId) {
        return this.tFileDao.queryById(fileId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TFile> queryAllByLimit(TFile tFile, int offset, int limit) {
        return this.tFileDao.queryAllByLimit(tFile, offset, limit);
    }

    @Override
    public List<TFile> queryAll(TFile tFile) {
        return this.tFileDao.queryAll(tFile);
    }

    /**
     * 新增数据
     *
     * @param tFile 实例对象
     * @return 实例对象
     */
    @Override
    public TFile insert(TFile tFile) {
        tFile.setFileId(ShortUUID.generate());
        tFile.setFileOwner(LoginInfo.getUserId());
        tFile.setFileOwnerName(LoginInfo.getUserName());
        tFile.setFileCreateTime(CustomDateUtil.currentFormatDate());
        tFile.setFileDownloadNumber("0");
        tFile.setFileDeleteFlag("0");
        if (StringUtils.isEmpty(tFile.getFileDescription())) {
            tFile.setFileDescription("");
        }
        tFile.setFileDescription("由" + tFile.getFileOwnerName() + "上传:" + tFile.getFileDescription());
        this.tFileDao.insert(tFile);
        return tFileDao.queryById(tFile.getFileId());
    }

    /**
     * 修改数据
     *
     * @param tFile 实例对象
     * @return 实例对象
     */
    @Override
    public TFile update(TFile tFile) {
        this.tFileDao.update(tFile);
        return this.queryById(tFile.getFileId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String fileId) {
        return this.tFileDao.deleteById(fileId) > 0;
    }

    @Override
    public List<FileDto> queryFileBase64ByIds(List<String> fileIds) {
        List<TFile> files = this.tFileDao.queryByIds(fileIds);
        List<FileDto> fileDtoList = new ArrayList<>();
        files.forEach(tFile -> {
            try {
                FileDto fileDto = new FileDto();
                fileDto.setFileOwner(tFile.getFileOwner());
                fileDto.setFileOwnerName(tFile.getFileOwnerName());
                fileDto.setFileId(tFile.getFileId());
                fileDto.setFileName(tFile.getFileName());
                fileDto.setFileDescription(tFile.getFileDescription());
                fileDto.setFileCreateTime(tFile.getFileCreateTime());
                fileDto.setFileSize(tFile.getFileSize());
                fileDto.setFileType(tFile.getFileType());
                String base64 = FileToBase64.encodeBase64File(tFile.getFilePath());
                fileDto.setBase64(base64);
                fileDtoList.add(fileDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return fileDtoList;
    }
}