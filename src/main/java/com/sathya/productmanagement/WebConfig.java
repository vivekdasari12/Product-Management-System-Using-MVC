package com.sathya.productmanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // ✅ This works both in IDE and jar deployment
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");  // ← important fix
    }
}
