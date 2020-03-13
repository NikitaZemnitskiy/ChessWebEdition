package com.zemnitskiy.chess.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {



    public void addViewControllers(ViewControllerRegistry registry) {

     //   registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("PersonalCabinet");
        registry.addViewController("/PersonalCabinet").setViewName("PersonalCabinet");
    //    registry.addViewController("/hello").setViewName("PersonalCabinet");
        registry.addViewController("/login").setViewName("login");
    }

}
