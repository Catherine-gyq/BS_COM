package com.frankzhu.ems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//前端图片路径配置文件
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Value("${web.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("ActivityRoom/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:"+uploadPath);
    }

}
