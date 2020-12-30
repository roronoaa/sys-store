package cn.tedu.store.service;

import cn.tedu.store.entity.Category;
import cn.tedu.store.entity.PageRecord;
import cn.tedu.store.entity.Product;
import cn.tedu.store.ex.EmptyArgumentException;
import cn.tedu.store.ex.RecordNotFoundException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.ProductOutOfStockException;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商品模块的业务层接口
 */
public interface IProductService {

    /**
     * 基于商品id增加商品库存
     * @param pid 商品id
     * @param num 要增加的库存数量
     * @throws RecordNotFoundException
     * @throws UpdateException
     */
    void increaseProductNum(Integer pid,Integer num)
            throws RecordNotFoundException, UpdateException;

    /**
     * 基于商品id减少商品库存
     * @param pid 商品id
     * @param num 要减少的库存数量
     */
    void reduceProductNum(Integer pid,Integer num)
        throws RecordNotFoundException, ProductOutOfStockException, UpdateException;



    /**
     * 基于商品名称模糊、分页查询商品记录并排序
     * @param name 商品名称
     * @param currentPage 当前页页码
     * @param pageSize 每页数据条数
     * @throws RecordNotFoundException
     * @return
     */
    PageRecord<List<Product>> findProductByName(String name,Integer tag,Integer order,
                                                Integer currentPage, Integer pageSize)
            throws RecordNotFoundException;
    /**
     * 基于商品种类id分页查询商品记录
     * @param cid 商品种类id
     * @param currentPage 当前页页码
     * @param pageSize 每页数据条数
     * @throws RecordNotFoundException
     * @return
     */
    PageRecord<List<Product>> findProductByCid(Integer cid,
                                               Integer currentPage, Integer pageSize)
                            throws RecordNotFoundException;

    /**
     * 查询所有商品种类信息
     * @return
     */
    List<Category> findAllCategory();


    /**
     * 基于uid为用户推荐4件商品
     * 如果用户id为null 或者
     * 用户近期没有访问记录
     * 则使用默认商品种类进行查询
     * @param uid
     * @return
     */
    List<Product> findFavourite(Integer uid);

    /**
     * 基于商品id查询商品数据
     * 查询添加排他锁
     * @param id
     * @return
     */
    Product findByIdForUpdate(Integer id)
            throws EmptyArgumentException;

    /**
     * 基于商品id查询商品数据
     * @param id
     * @return
     */
    Product findById(Integer id)
        throws EmptyArgumentException;

    /**
     * 查询热销排行商品
     * @return
     */
    List<Product> findHotList();

}
