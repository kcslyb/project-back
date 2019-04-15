package cn.kcs.service.impl;

import cn.kcs.business.inter.FileManager;
import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.dao.inter.pojo.TFile;
import cn.kcs.service.inter.FileService;
import cn.kcs.service.inter.dto.FileDto;
import cn.kcs.service.inter.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-25 14:57
 **/
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileManager fileManager;

    @Override
    public FileDto add(FileDto t) {
        return encapsulation(fileManager.add(conversion(t)));
    }

    private FileDto encapsulation(TFile tFile) {
        FileDto fileDto = new FileDto();
        fileDto.setFileId(tFile.getFileId());
        fileDto.setFileName(tFile.getFileName());
        fileDto.setFilePath(tFile.getFilePath());
        fileDto.setFileDescription(tFile.getFileDescription());
        fileDto.setFileTime(tFile.getFileTime());
        return fileDto;
    }

    private TFile conversion(FileDto fileDto) {
        TFile tFile = new TFile();
        tFile.setFileId(fileDto.getFileId());
        tFile.setFileName(fileDto.getFileName());
        tFile.setFilePath(fileDto.getFilePath());
        tFile.setFileDescription(fileDto.getFileDescription());
        tFile.setFileTime(fileDto.getFileTime());
        return tFile;
    }

    @Override
    public int edit(FileDto t) {
        return fileManager.edit(conversion(t));
    }

    @Override
    public int deleteByKey(String pk) {
        return fileManager.deleteByKey(pk);
    }

    @Override
    public FileDto getByKey(String pk) {
        return encapsulation(fileManager.getByKey(pk));
    }

    @Override
    public int deleteByKeys(String pks) {
        return fileManager.deleteByKeys(pks);
    }

    @Override
    public List<FileDto> query(FileDto t, OrderBy... orderArgs) {
        List<FileDto> list = new ArrayList<>();
        OrderBy orderBy = new OrderBy("file_time", false);
        List<TFile> tFiles = fileManager.query(conversion(t), orderBy);
        for (TFile tfile : tFiles) {
            list.add(encapsulation(tfile));
        }
        return list;
    }

    @Override
    public PageResponse<FileDto> queryPager(int start, int limit, FileDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("file_time", false);
        Pager<TFile> list = fileManager.queryPager(start, limit, conversion(t), orderBy);
        List<FileDto> fileDtos = new ArrayList<>();
        for (TFile tFile : list.getRecords()) {
            fileDtos.add(encapsulation(tFile));
        }
        PageResponse<FileDto> pageResponse = new PageResponse(list.getCurrentPage(), list.getLimit(), list.getTotalCount(), fileDtos);
        return pageResponse;
    }

    @Override
    public PageResponse<FileDto> queryCurrentPager(int start, int limit, FileDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("file_time", false);
        t.setFileDescription("上传者：" + LoginInfo.getUserName());
        List<TFile> list = fileManager.query(conversion(t), orderBy);
        List<FileDto> fileDtos = new ArrayList<>();
        for (TFile tFile : list) {
            fileDtos.add(encapsulation(tFile));
        }
        PageResponse<FileDto> pageResponse = new PageResponse(1, list.size(), list.size(), fileDtos);
        return pageResponse;
    }
}
