package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserVisitLog;

import java.util.List;

/**
 * 用户访问商品详情记录的持久层接口
 */
public interface UserVisitLogMapper {

    /**
     * 基于用户id查询
     * 近7天访问最多的商品种类id
     * @param uid
     * @return
     */
    Integer getCidByUid(Integer uid);

    /**
     * 添加一条访问商品详情记录
     * @param log
     * @return
     */
    Integer insertVisitLog(UserVisitLog log);

    /**
     * 查询浏览商品记录
     * @param uid
     * @return
     */
    List<Product> listProductsVisited(Integer uid);
}
