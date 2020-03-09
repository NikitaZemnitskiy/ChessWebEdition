package com.zemnitskiy.chess.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {



    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("PersonalCabinet");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/WaitingPage").setViewName("WaitingPage");
        registry.addViewController("/ConnectToGame").setViewName("ConnectToGame");
    }

}
