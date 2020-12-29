package cn.tedu.store.conf;

import cn.tedu.store.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
//        registration.addPathPatterns("/**");
//        List<String> list = new ArrayList<>();
//        list.add("/users/regist");
//        list.add("/users/login");
//        list.add("/users/checkUsername");
//        list.add("/products/**");
//        list.add("/districts/**");
//        registration.excludePathPatterns(list);
    }
}
