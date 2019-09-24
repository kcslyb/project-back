package cn.kcs.user.service;

import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.dto.ContactDto;
import cn.kcs.user.entity.dto.MsgDto;

import java.util.List;

/**
 * (TMsg)表服务接口
 *
 * @author makejava
 * @since 2018-12-30 19:47:03
 */
public interface TMsgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TMsg queryById(String id);

    /**
     * @param msgDto
     * @return
     */
    MsgDto queryAllByLimit(MsgDto msgDto);

    /**
     * 新增数据
     *
     * @param tMsg 实例对象
     * @return 实例对象
     */
    TMsg insert(TMsg tMsg);

    /**
     * 修改数据
     *
     * @param str 实例对象
     * @return 实例对象
     */
    int update(String str);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<ContactDto> findContact();
}