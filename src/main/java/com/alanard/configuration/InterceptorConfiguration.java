package com.alanard.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alanard.interceptors.AuthInterceptor;

@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new AuthInterceptor());
     
        ir.addPathPatterns("/admin/**");
        
        ir.excludePathPatterns("/admin/login","/admin/login.do","/admin/signup.do","/admin/reset.do","/admin/sendmail.do");

        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
}

