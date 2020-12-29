package cn.tedu.store.controller;

import cn.tedu.store.entity.*;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.IUserVisitLogService;
import cn.tedu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService service;

    @Autowired
    IUserVisitLogService logService;

    @GetMapping("/findProductByName")
    public JsonResult<PageRecord> findProductByName(String name,Integer order,
                                                    Integer currentPage, Integer pageSize){
        return JsonResult.getSuccessJR(service.findProductByName(name,order,currentPage,pageSize));
    }

    @GetMapping("/findProductByCid")
    public JsonResult<PageRecord> findProductByCid(Integer cid,
                                                   Integer currentPage, Integer pageSize){
        return JsonResult.getSuccessJR(service.findProductByCid(cid,currentPage,pageSize));
    }


    @GetMapping("/findAllCategory")
    public JsonResult<List<Category>> findAllCategory(HttpServletRequest httpServletRequest){
        System.out.println("::"+httpServletRequest.getHeader("Origin"));
        return JsonResult.getSuccessJR(service.findAllCategory());
    }

    @GetMapping("/favourite")
    public JsonResult<List<Product>> findFavourite(HttpSession session){
        UserEntity user=(UserEntity)session.getAttribute("user");
        List<Product> data = service.findFavourite(user.getUid());
        return JsonResult.getSuccessJR(data);
    }

    @GetMapping("/{id}/get")
    public JsonResult<Product> findById(@PathVariable("id") Integer id, HttpSession session){
        Product product=service.findById(id);
        // 生成访问记录
        UserEntity user=(UserEntity)session.getAttribute("user");
        // 如果用户已登录，记录访问信息
        if(user!=null){
            UserVisitLog log=new UserVisitLog(null,
                    user.getUid(),
                    product.getId(),
                    product.getCategory().getId(),null);
            // 将访问记录添加到数据库中
            logService.saveVisitLog(log);
        }
        return JsonResult.getSuccessJR(product);
    }

    @GetMapping("/hot")
    public JsonResult<List<Product>> getHotList(){
        // 查询
        List<Product> data = service.findHotList();
        // 返回
        return JsonResult.getSuccessJR(data);
    }

    @PostMapping("/increaseProductNum")
    public void increaseProductNum(Integer pid,Integer num) {
        service.increaseProductNum(pid, num);
    }

    @PostMapping("/reduceProductNum")
    public void reduceProductNum(Integer pid,Integer num) {
        service.reduceProductNum(pid, num);
    }

    @GetMapping("/findProductById")
    public Product findProductById(Integer pid) {
        return service.findById(pid);
    }

}
