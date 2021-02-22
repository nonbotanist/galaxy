package com.wayne.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication
@EnableEurekaClient
public class MyGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGatewayApplication.class, args);
	}
	
	@Bean
	public CorsWebFilter corsFilter() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedMethod("*");//支持所有方法
	    config.addAllowedOrigin("*");//跨域处理 允许所有的域
	    config.addAllowedHeader("*");//支持所有请求头

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
	    source.registerCorsConfiguration("/**", config);//匹配所有请求

	    return new CorsWebFilter(source);
	}

}
