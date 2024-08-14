package com.example.conversion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/conversion-service")
public class ConversionController {

    @Autowired
    CurrrencyExchangeServiceProxy currrencyExchangeServiceProxy;

    @GetMapping("/from/{from}/to/{to}/{amount}")
    public CurrencyConversionBean conversion(@PathVariable String from, @PathVariable String to, @PathVariable double amount) {
        CurrencyConversionBean conversionBean = currrencyExchangeServiceProxy.exchange(from,to);
        BigDecimal finalValue = BigDecimal.valueOf(amount).multiply(conversionBean.getExchangeValue());
        conversionBean.setAmount(amount);
        conversionBean.setTargetValue(finalValue);
        return conversionBean;
    }


}
