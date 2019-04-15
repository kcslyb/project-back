package cn.kcs.service.impl;

import cn.kcs.business.inter.OrderManager;
import cn.kcs.common.util.DataUtil;
import cn.kcs.dao.inter.TOrderProductDao;
import cn.kcs.dao.inter.TScheduleDao;
import cn.kcs.dao.inter.pojo.TOrder;
import cn.kcs.dao.inter.pojo.TOrderProduct;
import cn.kcs.dao.inter.pojo.TSchedule;
import cn.kcs.service.inter.DeskService;
import cn.kcs.service.inter.OrderService;
import cn.kcs.service.inter.ProService;
import cn.kcs.service.inter.dto.DeskDto;
import cn.kcs.service.inter.dto.OrderDto;
import cn.kcs.service.inter.dto.ProDto;
import cn.kcs.service.inter.util.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-27 16:15
 **/
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrderManager orderManager;
    @Autowired
    private DeskService deskService;
    @Autowired
    private TOrderProductDao tOrderProductDao;
    @Autowired
    private ProService proService;
    @Autowired
    private TScheduleDao tScheduleDao;
    @Override
    @Transactional
    public OrderDto add(OrderDto orderDto) {
        orderDto.setOrderCreatetime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        logger.info("-add product: current time is" + orderDto.getOrderCreatetime());
        String orderIndex = Long.toString(DataUtil.currentTimeStamp());
        orderDto.setOrderIndex("S" + orderIndex);
        DeskDto deskDto = orderDto.getDeskDto();
        deskDto.setDeskStatus("1");
        deskService.edit(deskDto);
        OrderDto dto = assemblyDto(orderManager.add(assembly(orderDto)));
        TOrderProduct tOrderProduct = new TOrderProduct();
        tOrderProduct.setOrderId(dto.getOrderId());
        for (ProDto p : orderDto.getProDtos()) {
            tOrderProduct.setProId(p.getProId());
            tOrderProductDao.add(tOrderProduct);
        }
        TSchedule tSchedule = new TSchedule();
        tSchedule.setScheduleId(dto.getOrderId());
        tSchedule.setScheduleStatus("0");
        tSchedule.setScheduleConfirmed(dto.getOrderCreatetime().toString());
        tScheduleDao.add(tSchedule);
        return dto;
    }

    private OrderDto assemblyDto(TOrder tOrder) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(tOrder.getOrderId());
        orderDto.setOrderIndex(tOrder.getOrderIndex());
        orderDto.setOrderCreated(tOrder.getOrderCreated());
        orderDto.setOrderCreatetime(tOrder.getOrderCreatetime());
        orderDto.setOrderPrise(asOrderPrise(tOrder.getOrderId()));
        if (tOrder.getOrderDesk() != null && !("".equals(tOrder.getOrderDesk()))) {
            orderDto.setDeskDto(deskService.getByKey(tOrder.getOrderDesk()));
        }
        if (tOrder.getOrderId() != null && !("".equals(tOrder.getOrderId()))) {
            orderDto.setProDtos(asProDtos(tOrder.getOrderId()));
        }
        return orderDto;
    }

    private String asOrderPrise(String orderId) {
        float prise = 0;
        TOrderProduct tOrderProduct = new TOrderProduct();
        tOrderProduct.setOrderId(orderId);
        List<TOrderProduct> tOrderProducts = tOrderProductDao.query(tOrderProduct);
        for (TOrderProduct t : tOrderProducts) {
            prise += Float.parseFloat(proService.getByKey(t.getProId()).getProPrise().toString());
        }
        return prise + "";
    }

    private List<ProDto> asProDtos(String orderId) {
        List<ProDto> list = new ArrayList<>();
        TOrderProduct tOrderProduct = new TOrderProduct();
        tOrderProduct.setOrderId(orderId);
        List<TOrderProduct> tOrderProducts = tOrderProductDao.query(tOrderProduct);
        for (TOrderProduct t : tOrderProducts) {
            list.add(proService.getByKey(t.getProId()));
        }
        return list;
    }

    private TOrder assembly(OrderDto orderDto) {
        TOrder tOrder = new TOrder();
        tOrder.setOrderId(orderDto.getOrderId());
        tOrder.setOrderIndex(orderDto.getOrderIndex());
        tOrder.setOrderCreated(orderDto.getOrderCreated());
        tOrder.setOrderCreatetime(orderDto.getOrderCreatetime());
        if (orderDto.getDeskDto() != null) {
            tOrder.setOrderDesk(orderDto.getDeskDto().getDeskId());
        }
        return tOrder;
    }

    @Override
    public int edit(OrderDto t) {
        return orderManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        TOrderProduct tOrderProduct = new TOrderProduct();
        tOrderProduct.setOrderId(pk);
        for (ProDto p : assemblyDto(orderManager.getByKey(pk)).getProDtos()) {
            tOrderProduct.setProId(p.getProId());
            List<TOrderProduct> lists = tOrderProductDao.query(tOrderProduct);
            for (TOrderProduct t : lists) {
                tOrderProductDao.deleteByKey(t.getOrderId());
            }
        }
        return orderManager.deleteByKey(pk);
    }

    @Override
    public OrderDto getByKey(String pk) {
        OrderBy orderBy = new OrderBy("order_createtime", false);
        TOrder tOrder = new TOrder();
        tOrder.setOrderDesk(pk);
        List<TOrder> list = orderManager.query(tOrder, orderBy);
        OrderDto orderDto = assemblyDto(list.get(0));
        orderDto.setOrderStatus(tScheduleDao.getByKey(orderDto.getOrderId()).getScheduleStatus());
//        return assemblyDto(orderManager.getByKey(orderDto.getOrderId()));
        return orderDto;
    }

    @Override
    public int deleteByKeys(String pks) {
        return orderManager.deleteByKeys(pks);
    }

    @Override
    public List<OrderDto> query(OrderDto t, OrderBy... orderArgs) {
        List<TOrder> tProducts = orderManager.query(assembly(t));
        List<OrderDto> proDtos = new ArrayList<>();
        for (TOrder tProduct : tProducts) {
            proDtos.add(assemblyDto(tProduct));
        }
        return proDtos;
    }

    @Override
    public PageResponse<OrderDto> queryPager(int start, int limit, OrderDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("schedule_confirmed", false);
        List<OrderDto> listdto = new ArrayList<>();
        TSchedule tSchedule = new TSchedule();
        Pager<TSchedule> tSchedules = null;
        if (t.getOrderStatus() != null && !("".equals(t.getOrderStatus()))) {
            tSchedule.setScheduleStatus(t.getOrderStatus());
        }
        tSchedules = tScheduleDao.queryPager(start, limit, tSchedule, orderBy);
        if (tSchedules.getRecords().size() > 0) {
            for (TSchedule ts : tSchedules.getRecords()) {
                TOrder tOrder = new TOrder();
                tOrder.setOrderId(ts.getScheduleId());
                List<TOrder> list = orderManager.query(tOrder);
                OrderDto orderDto = assemblyDto(list.get(0));
                orderDto.setOrderStatus(tScheduleDao.getByKey(orderDto.getOrderId()).getScheduleStatus());
                listdto.add(orderDto);
            }
        }
        PageResponse<OrderDto> pageResponse = new PageResponse(tSchedules.getCurrentPage(), tSchedules.getLimit(), tSchedules.getTotalCount(), listdto);
        return pageResponse;
    }

    @Override
    public void account(String key) {
        OrderBy orderBy = new OrderBy("order_createtime", false);
        TOrder tOrder = new TOrder();
        tOrder.setOrderDesk(key);
        List<TOrder> tOrders = orderManager.query(tOrder, orderBy);
        if (tOrders.size() > 0) {
            TSchedule tSchedule = new TSchedule();
            tSchedule.setScheduleStatus("1");
            tSchedule.setScheduleId(tOrders.get(0).getOrderId());
            DeskDto deskDto = deskService.getByKey(tOrders.get(0).getOrderDesk());
            deskDto.setDeskStatus("0");
            deskService.edit(deskDto);
            tScheduleDao.edit(tSchedule);
        }
    }

}
