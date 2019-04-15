package cn.kcs.service.inter;

import cn.kcs.service.inter.dto.FileDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

public interface FileService {
    FileDto add(FileDto t);

    int edit(FileDto t);

    int deleteByKey(String pk);

    FileDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<FileDto> query(FileDto t, OrderBy... orderArgs);

    PageResponse<FileDto> queryPager(int start, int limit, FileDto t, OrderBy... orderArgs);

    PageResponse<FileDto> queryCurrentPager(int start, int limit, FileDto t, OrderBy... orderArgs);
}
