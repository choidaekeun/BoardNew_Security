package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.study.interceptor.LoggerInterceptor;
import com.study.interceptor.LoginCheckInterceptor;

//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoggerInterceptor()) //Interceptor 파일이 여러개인 경우 가장 상위 HandlerInterceptor 명시, mwork 참고
//                .excludePathPatterns("/css/**", "/images/**", "/js/**"); // 반대로 addPathPatterns( )
//        
//        registry.addInterceptor(new LoginCheckInterceptor())
//        		.addPathPatterns("/**/*.do")
//        		.excludePathPatterns("/log*");
//    }
//
//}
