package cn.kvmial.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/res/");
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/res/layui/");
    }
}