package com.example.conversion_service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="API-GATEWAY", path = "/exchange-api")
public interface ExchangeServiceApiClient {


    @GetMapping("/from/{from}/to/{to}/{amount}")
    public CurrencyConversionBean exchange(@PathVariable String from, @PathVariable String to, @PathVariable double amount);


}
