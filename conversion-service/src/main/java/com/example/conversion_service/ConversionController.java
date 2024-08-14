package com.example.conversion_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/conversion-api")
public class ConversionController {

    @Autowired
    ExchangeServiceApiClient exchangeServiceApiClient;

    Logger logger = LoggerFactory.getLogger(ConversionController.class);

    @GetMapping("/from/{from}/to/{to}/{amount}")
    public CurrencyConversionBean conversion(@PathVariable String from, @PathVariable String to, @PathVariable double amount) throws JsonProcessingException {
        logger.info("From conversion-service. Got request for {}, {}, {}", from, to, amount);
        CurrencyConversionBean conversionBean = exchangeServiceApiClient.exchange(from,to, amount);
        BigDecimal finalValue = BigDecimal.valueOf(amount).multiply(conversionBean.getExchangeValue());
        conversionBean.setAmount(amount);
        conversionBean.setTargetValue(finalValue);
        logger.info("From conversion-service. Sending response {}", new ObjectMapper().writeValueAsString(conversionBean));
        return conversionBean;
    }


}
