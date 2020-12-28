package cn.tedu.store.service;

import cn.tedu.store.entity.UserVisitLog;

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
}
