package com.unicartagena.actividad2_desarrollosoftwareweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Registra el interceptor en la aplicacion
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Aplica el interceptor a todas las rutas
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**");
    }
}