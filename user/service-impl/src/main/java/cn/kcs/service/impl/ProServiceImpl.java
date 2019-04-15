package cn.kcs.service.impl;

import cn.kcs.business.inter.ProManager;
import cn.kcs.common.util.DataUtil;
import cn.kcs.dao.inter.pojo.TProduct;
import cn.kcs.service.inter.ProService;
import cn.kcs.service.inter.dto.ProDto;
import cn.kcs.service.inter.util.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 20:58
 **/
@Service
public class ProServiceImpl implements ProService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProManager proManager;

    @Override
    public ProDto add(ProDto proDto) {
        proDto.setProCreattime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        proDto.setProModifytime(proDto.getProCreattime());
        logger.info("-add product: current time is" + proDto.getProCreattime());
        return assemblyDto(proManager.add(assembly(proDto)));
    }

    private ProDto assemblyDto(TProduct tProduct) {
        ProDto proDto = new ProDto();
        proDto.setProId(tProduct.getProId());
        proDto.setProLable(tProduct.getProLable());
        proDto.setProPrise(tProduct.getProPrise());
        proDto.setProPath(tProduct.getProPath());
        proDto.setProSrc(tProduct.getProSrc());
        proDto.setProType(tProduct.getProType());
        proDto.setProCreattime(tProduct.getProCreattime());
        proDto.setProModifytime(tProduct.getProModifytime());
        return proDto;
    }

    private TProduct assembly(ProDto proDto) {
        TProduct tProduct = new TProduct();
        tProduct.setProId(proDto.getProId());
        tProduct.setProLable(proDto.getProLable());
        tProduct.setProPrise(proDto.getProPrise());
        tProduct.setProPath(proDto.getProPath());
        tProduct.setProSrc(proDto.getProSrc());
        tProduct.setProType(proDto.getProType());
        tProduct.setProCreattime(proDto.getProCreattime());
        tProduct.setProModifytime(proDto.getProModifytime());
        return tProduct;
    }

    @Override
    public int edit(ProDto t) {
        t.setProModifytime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        return proManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        return proManager.deleteByKey(pk);
    }

    @Override
    public ProDto getByKey(String pk) {
        return assemblyDto(proManager.getByKey(pk));
    }

    @Override
    public int deleteByKeys(String pks) {
        return proManager.deleteByKeys(pks);
    }

    @Override
    public List<ProDto> query(ProDto t, OrderBy... orderArgs) {
        List<TProduct> tProducts = proManager.query(assembly(t));
        List<ProDto> proDtos = new ArrayList<>();
        for (TProduct tProduct : tProducts) {
            proDtos.add(assemblyDto(tProduct));
        }
        return proDtos;
    }

    @Override
    public PageResponse<ProDto> queryPager(int start, int limit, ProDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("pro_creattime", false);
        Pager<TProduct> list = proManager.queryPager(start, limit, assembly(t), orderBy);
        List<ProDto> listdto = new ArrayList<>();
        for (TProduct tProduct : list.getRecords()) {
            listdto.add(assemblyDto(tProduct));
        }
        PageResponse<ProDto> pageResponse = new PageResponse(list.getCurrentPage(), list.getLimit(), list.getTotalCount(), listdto);
        return pageResponse;
    }

    @Override
    public ProDto getByUserName(String username) {
        return null;
    }

    @Override
    public ProDto getByUserId(String key) {
        return null;
    }

    @Override
    public ProDto getUserByIdAndName(String loginName, String password) {
        return null;
    }

    @Override
    public void editUserRolePerm(String roleId, String userId) {

    }
}
