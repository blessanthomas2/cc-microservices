package com.example.conversion_service;


import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="exchange-client", path = "/exchange-service")
@LoadBalancerClient(name = "say-hello", configuration = LoadBalancerConfig.class)
public interface CurrrencyExchangeServiceProxy {


    @GetMapping("/from/{from}/to/{to}")
    public CurrencyConversionBean exchange(@PathVariable String from, @PathVariable String to);


}
