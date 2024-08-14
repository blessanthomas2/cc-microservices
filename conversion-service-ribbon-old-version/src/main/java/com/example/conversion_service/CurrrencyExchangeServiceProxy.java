package com.example.conversion_service;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="exchange-client", url="http://localhost:8100")
@FeignClient(name="exchange-client", path = "/exchange-service")
//@RibbonClient(name = "curr-exch-client", configuration = RibbonConfiguration.class)
@RibbonClient(name = "exchange-client")
public interface CurrrencyExchangeServiceProxy {


    @GetMapping("/from/{from}/to/{to}")
    public CurrencyConversionBean exchange(@PathVariable String from, @PathVariable String to);


}
