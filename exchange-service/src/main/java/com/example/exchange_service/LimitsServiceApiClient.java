package com.example.exchange_service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "API-GATEWAY", path = "/limits-api")
public interface LimitsServiceApiClient {

    @GetMapping("/limits")
    public LimitsConfiguration getLimits();

}


