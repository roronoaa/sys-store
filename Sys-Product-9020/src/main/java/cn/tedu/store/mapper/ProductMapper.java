package cn.tedu.store.mapper;

import cn.tedu.store.entity.Product;
import cn.tedu.store.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品模块的持久层接口
 */
public interface ProductMapper {

    /**
     * 基于商品id更新商品库存数量
     * @param id
     * @param num 新的库存数量
     * @return
     */
    Integer updateNumById(@Param("id") Long id, @Param("num") Integer num);

    /**
     * 基于商品名称模糊查询总数据条数
     * @param name 商品名称
     * @return
     */
    Integer getCountByName(String name);

    /**
     * 按商品名称模糊查询并排序
     * @param name 商品名称
     * @param tag 排序关键字
     * @param order 正序逆序 1表示正序 0表示逆序
     * @param recordIndex
     * @param pageSize
     * @return
     */
    List<Product> listAllByName(@Param("name") String name,
                                @Param("tag")Integer tag,
                                @Param("order")Integer order,
                                @Param("recordIndex") Integer recordIndex,
                                @Param("pageSize") Integer pageSize);

//    List<Product> listAllByNameOrdered(@Param("name") String name,
//                                @Param("order")Integer order,
//                                @Param("recordIndex") Integer recordIndex,
//                                @Param("pageSize") Integer pageSize);
    /**
     * 基于商品种类id查询总数据条数
     * @param cid 商品种类id
     * @return
     */
    Integer getCountByCid(Integer cid);


    /**
     * 基于商品种类id分页查询商品
     * @param cid 商品种类id
     * @param recordIndex 跳过的数据条数
     * @param pageSize 查询的数据条数
     * @return
     */
    List<Product> listAllByCid(@Param("cid") Integer cid,
                               @Param("recordIndex") Integer recordIndex,
                               @Param("pageSize") Integer pageSize);


    /**
     * 查询所有商品种类
     * @return
     */
    List<ProductCategory> listAllCategory();

    /**
     * 基于商品种类id随机查询4种商品信息
     * @param cid 商品种类id
     * @return
     */
    List<Product> listByCid(Integer cid);

    /**
     * 基于商品id查询商品信息
     * 查询添加排他锁
     * @param id
     * @return
     */
    Product getByIdForUpdate(Long id);

    /**
     * 基于商品id查询商品信息findAllCategory
     * @param id
     * @return
     */
    Product getById(Long id);

    /**
     * 显示热销排行商品
     * 显示优先级最高的前4个商品
     * 显示的是上架的库存大于0的商品
     * @return
     */
    List<Product> listHotProduct();

}
