package com.user.service.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
// import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // public FeignClient feignClient() {
    //     return new FeignClient();
    // }

}
