package com.euvsvirus.euvsvirus.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class DisableCORSConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
                .allowedMethods("GET", "POST", "OPTIONS", "HEAD", "PUT", "DELETE")
                .allowedOrigins("*")
                .allowedHeaders("*") //.allowCredentials(true)
    }
}