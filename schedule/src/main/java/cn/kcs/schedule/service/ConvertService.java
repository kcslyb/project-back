package cn.kcs.schedule.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.schedule.entity.Convert;

import java.util.List;

/**
 * (Convert)表服务接口
 *
 * @author kcs
 * @since 2020-09-17 20:11:32
 */
public interface ConvertService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Convert queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Convert> queryAllByLimit(Convert convert, int offset, int limit);
    List<Convert> queryAll(Convert convert);

    /**
     * 新增数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    boolean insert(Convert convert);

    /**
     * 修改数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    boolean update(Convert convert);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    ResponseDto<Convert> queryPager(Convert convert, PageRequest pageRequest);

    List<Convert> queryRelevance(String relevance);
}