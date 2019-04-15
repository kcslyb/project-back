package cn.kcs.service.impl;

import cn.kcs.business.inter.DeskManager;
import cn.kcs.dao.inter.pojo.TDesk;
import cn.kcs.service.inter.DeskService;
import cn.kcs.service.inter.dto.DeskDto;
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
 * @create: 2018-11-30 14:05
 **/
@Service
public class DeskServiceImpl implements DeskService {
    @Autowired
    private DeskManager deskManager;

    @Override
    public DeskDto add(DeskDto t) {
        return assemblyDto(deskManager.add(assembly(t)));
    }

    private DeskDto assemblyDto(TDesk t) {
        DeskDto dto = new DeskDto();
        dto.setDeskId(t.getDeskId());
        dto.setDeskNumber(t.getDeskNumber());
        dto.setDeskSize(t.getDeskSize());
        dto.setDeskStatus(t.getDeskStatus());
        return dto;
    }

    private TDesk assembly(DeskDto dto) {
        TDesk t = new TDesk();
        t.setDeskId(dto.getDeskId());
        t.setDeskNumber(dto.getDeskNumber());
        t.setDeskSize(dto.getDeskSize());
        t.setDeskStatus(dto.getDeskStatus());
        return t;
    }

    @Override
    public int edit(DeskDto t) {
        return deskManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        return deskManager.deleteByKey(pk);
    }

    @Override
    public DeskDto getByKey(String pk) {
        return assemblyDto(deskManager.getByKey(pk));
    }

    @Override
    public int deleteByKeys(String pks) {
        return deskManager.deleteByKeys(pks);
    }

    @Override
    public List<DeskDto> query(DeskDto dto, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("desk_size", false);
        List<TDesk> ts = deskManager.query(assembly(dto), orderBy);
        List<DeskDto> dtos = new ArrayList<>();
        for (TDesk t : ts) {
            dtos.add(assemblyDto(t));
        }
        return dtos;
    }

    @Override
    public PageResponse<DeskDto> queryPager(int start, int limit, DeskDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("desk_size", false);
        Pager<TDesk> list = deskManager.queryPager(start, limit, assembly(t), orderBy);
        List<DeskDto> listdto = new ArrayList<>();
        if (list.getRecords().size() > 0) {
            for (TDesk tDesk : list.getRecords()) {
                listdto.add(assemblyDto(tDesk));
            }
        }
        PageResponse<DeskDto> pageResponse = new PageResponse(list.getCurrentPage(), list.getLimit(), list.getTotalCount(), listdto);
        return pageResponse;
    }
}
