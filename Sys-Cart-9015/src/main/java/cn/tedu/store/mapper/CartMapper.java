package cn.tedu.store.mapper;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 购物车模块的持久层接口
 */
public interface CartMapper {


    /**
     * 基于购物车id数组查询购物车记录
     * @param cids
     * @return
     */
    List<CartVO> listByCids(@Param("cids") Integer[] cids);

    /**
     * 基于购物车id查询购物车记录，查询时添加排他锁
     * @param cid
     * @return
     */
    Cart getByCidForUpdate(Integer cid);

    /**
     * 基于购物车id数组查询实际存在的数据条数
     * @param cids
     * @return
     */
    Integer getCount(@Param("cids") Integer[] cids);

    /**
     * 基于购物车id数组批量删除购物车记录
     * @param cids
     * @return
     */
    Integer deleteCartList(@Param("cids") Integer[] cids);


    /**
     * 基于购物车id查询购物车记录
     * @param cid
     * @return
     */
    Cart getByCid(Integer cid);

    /**
     * 基于购物车id删除购物车记录
     * @param cid
     * @return
     */
    Integer deleteCartByCid(Integer cid);

    /**
     * 基于用户id查询所有购物车记录
     * @param uid
     * @return
     */
    List<CartVO> listCart(Integer uid);

    /**
     * 添加一条购物车记录
     * @param cart
     * @return
     */
    Integer insertCart(Cart cart);

    /**
     * 基于id更新购物车记录的数量
     * @param cid
     * @param num
     * @param username
     * @param modifiedTime
     * @return
     */
    Integer updateNum(@Param("cid") Integer cid,
                      @Param("num") Integer num,
                      @Param("username") String username,
                      @Param("modifiedTime") Date modifiedTime);

    /**
     * 基于用户id和商品id查询购物车记录
     * @param uid
     * @param pid
     * @return
     */
    Cart getByUidAndPid(@Param("uid") Integer uid,
                        @Param("pid") Integer pid);
    Integer setCheck(@Param("uid") Integer uid,@Param("cids") Integer[] cids,@Param("check") Integer check);
    List<CartVO> listCartChecked(Integer uid);
}
