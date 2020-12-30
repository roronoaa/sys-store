package cn.tedu.store.service.Impl;

import cn.tedu.store.entity.*;
import cn.tedu.store.ex.EmptyArgumentException;
import cn.tedu.store.ex.RecordNotFoundException;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.IUserVisitLogService;
import cn.tedu.store.ex.ProductOutOfStockException;
import cn.tedu.store.util.JsonResult;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired(required = false)
    ProductMapper mapper;

    @Autowired(required = false)
    IUserVisitLogService userVisitLogService;

    private static final Integer DEFAULT_CID=163;
    private static final Integer MAX_PAGE_SIZE=20;

    @Override
    @Transactional
    public void increaseProductNum(Integer pid, Integer num) throws RecordNotFoundException, UpdateException {
        Product product=findByIdForUpdate(pid);
        if(product==null){
            throw new RecordNotFoundException("增加商品库存异常：商品不存在");
        }
        int newNum=product.getNum()+num;
        Integer row=mapper.updateNumById(pid,newNum);
        if(row!=1){
            throw new UpdateException("增加商品库存异常：库存更新失败");
        }
    }

    @Override
    @Transactional
    public void reduceProductNum(Integer pid, Integer num)
            throws RecordNotFoundException, ProductOutOfStockException, UpdateException {
        Product product=findByIdForUpdate(pid);
        if(product==null){
            throw new RecordNotFoundException("减少商品库存异常：商品不存在");
        }
        int newNum=product.getNum()-num;
        if(newNum<0){
            throw new ProductOutOfStockException("减少商品库存异常：商品库存不足");
        }
        Integer row=mapper.updateNumById(pid,newNum);
        if(row!=1){
            throw new UpdateException("减少商品库存异常：库存更新失败");
        }
    }

    @Override
    public PageRecord<List<Product>> findProductByName(String name,Integer tag,Integer order, Integer currentPage, Integer pageSize) throws RecordNotFoundException {
        // 非空验证 TODO
        // 查询总数据条数
        Integer count=mapper.getCountByName(name);
        // 判断结果是否为0
        if(count==0){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("分页查询商品异常：未查到相关记录");
        }
        // 超过上限则使用上限
        if(pageSize>MAX_PAGE_SIZE){
            pageSize=MAX_PAGE_SIZE;
        }
        // 计算recordIndex=(currentPage-1)*pageSize
        Integer recordIndex=(currentPage-1)*pageSize;
        // 查询当前页记录
        List<Product> data=mapper.listAllByName(name,tag,order,recordIndex,pageSize);
        // 判断结果是否为null 或 长度是否为0
        if(data==null || data.size()==0){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("分页查询商品异常：未查到当前页记录");
        }

        // 计算pageCount=(count-1)/pageSize+1
        Integer pageCount=(count-1)/pageSize+1;
        // 创建PageRecord，封装数据
        PageRecord<List<Product>> pageRecord =
                new PageRecord<>(pageSize,count,pageCount,currentPage,data);
        // 返回PageRecord
        return pageRecord;
    }


    @Override
    public PageRecord<List<Product>> findProductByCid(
            Integer cid, Integer currentPage, Integer pageSize)
            throws RecordNotFoundException {
        // 非空验证 TODO
        // 查询总数据条数
        Integer count=mapper.getCountByCid(cid);
        // 判断结果是否为0
        if(count==0){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("分页查询商品异常：未查到相关记录");
        }
        // 超过上限则使用上限
        if(pageSize>MAX_PAGE_SIZE){
            pageSize=MAX_PAGE_SIZE;
        }
        // 计算recordIndex=(currentPage-1)*pageSize
        Integer recordIndex=(currentPage-1)*pageSize;
        // 查询当前页记录
        List<Product> data=mapper.listAllByCid(cid,recordIndex,pageSize);
        // 判断结果是否为null 或 长度是否为0
        if(data==null || data.size()==0){
            // 是：RecordNotFoundException
            throw new RecordNotFoundException("分页查询商品异常：未查到当前页记录");
        }

        // 计算pageCount=(count-1)/pageSize+1
        Integer pageCount=(count-1)/pageSize+1;
        // 创建PageRecord，封装数据
        PageRecord<List<Product>> pageRecord =
                new PageRecord<>(pageSize,count,pageCount,currentPage,data);
        // 返回PageRecord
        return pageRecord;
    }

    @Override
    public List<Category> findAllCategory() {
        List<ProductCategory> list=mapper.listAllCategory();
        ArrayList<Category> mylist=new ArrayList<>();
        for(ProductCategory p:list){
            if(p.getParentId().equals(0)){
                System.out.println(p.getSortOrder());
                mylist.add(new Category(p.getId(),p.getSortOrder(),0,p.getName(),p.getParentId(),0.0,"",""));
            }else{
                for(Category mc:mylist){
                    if(mc.getId().equals(p.getParentId())){
                        mc.addMyCategory(new Category(p.getId(),p.getSortOrder(),0,p.getName(),p.getParentId(),0.0,"",""));
                        break;
                    }
                    List<Category> temp=mc.getChildren();
                    for(Category t:temp){
                        if(t.getId().equals(p.getParentId())){
                            t.addMyCategory(new Category(p.getId(),p.getSortOrder(),0,p.getName(),p.getParentId(),0.0,"",""));
                            mc.setChildren(temp);
                            break;
                        }
                    }
                }
            }
        }
        return mylist;
    }

    @Override
    public List<Product> findFavourite(Integer uid) {
        // 基于uid查询用户近7天访问最多的商品种类
        JsonResult<Integer> result = userVisitLogService.getCidByUid(uid);
        Integer cid = result.getData();
        if (result == null || cid == null) {
            cid = DEFAULT_CID;
        }
        // 基于cid随机查询4种商品信息
        List<Product> list=mapper.listByCid(cid);
        return list;
    }

    @Override
    public Product findByIdForUpdate(Integer id) throws EmptyArgumentException {
        if(id==null){
            throw new EmptyArgumentException("查询商品数据异常：id不能为空");
        }
        return mapper.getByIdForUpdate(id);
    }

    @Override
    public Product findById(Integer id) throws EmptyArgumentException {

        Product product=mapper.getById(id);
        return product;
    }

    @Override
    public List<Product> findHotList() {
        return mapper.listHotProduct();
    }

}
