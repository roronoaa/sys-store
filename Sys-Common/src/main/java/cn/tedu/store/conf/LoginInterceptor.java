//package cn.tedu.store.conf;
//
//import cn.tedu.store.entity.UserEntity;
//import cn.tedu.store.util.JsonResult;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class LoginInterceptor extends HandlerInterceptorAdapter {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        JsonResult result = new JsonResult();
//        UserEntity user = (UserEntity) session.getAttribute("user");
//        if (user == null) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(new ObjectMapper().writeValueAsString(result));
//            return false;
//        } else {
//            return true;
//        }
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//    }
//}