package com.codebyoli.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/*

*/

@Configuration
public class WebClientConfig
{
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
      return WebClient.builder();
    }
    //create a bean of type WebClient and name is webClient
}
/*
*  @LoadBalanced - add client side load balancing in webClientBuilder
* when we create bean of webClient.Builder it will automatically create webClient
* instances , Now order service will have instances of inventory service available
* Order service will not get confused , it will call instances one after another
 * */