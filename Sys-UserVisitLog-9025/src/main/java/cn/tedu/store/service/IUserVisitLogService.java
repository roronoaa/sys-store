package cn.tedu.store.service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.UserVisitLog;

import java.util.List;

/**
 * 用户访问商品详情记录的业务层接口
 */
public interface IUserVisitLogService {

    /**
     * 添加一条访问商品详情记录
     * @param log
     */
    void saveVisitLog(UserVisitLog log);

    Integer getCidByUid(Integer uid);

    List<Product> getProductsVisitedList(Integer uid);
}
