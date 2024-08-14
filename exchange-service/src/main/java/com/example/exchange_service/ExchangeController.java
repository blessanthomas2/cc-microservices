package com.example.exchange_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/exchange-api")
public class ExchangeController {
    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    Environment environment;

    @Autowired
    LimitsServiceApiClient limitsServiceApiClient;

    Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @GetMapping("/from/{from}/to/{to}/{amount}")
    @CircuitBreaker(name="limits-service", fallbackMethod = "getDefaultLimits")
    public Exchange exchange(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) throws JsonProcessingException {
        logger.info("From exchange-service. Got request for {}, {}, {}", from, to, amount);
        LimitsConfiguration limitsConfiguration  = getLimitsConfiguration();
        if (amount.compareTo(BigDecimal.valueOf(limitsConfiguration.getMin()))==-1
                || amount.compareTo(BigDecimal.valueOf(limitsConfiguration.getMax()))==1) {
            throw new IllegalStateException("Amount is not within limits");
        }
        Exchange exchange = exchangeRepository.findByFromAndTo(from, to);
        exchange.setPort(environment.getProperty("local.server.port"));
        logger.info("From exchange-service. Sending response {}", new ObjectMapper().writeValueAsString(exchange));
        return exchange;
    }

    //@CircuitBreaker(name="limits-service", fallbackMethod = "getDefaultLimits")
    private LimitsConfiguration getLimitsConfiguration() {
        return limitsServiceApiClient.getLimits();
    }

    private Exchange getDefaultLimits(String from, String to, BigDecimal amount, Throwable throwable) throws JsonProcessingException {
        Exchange exchange = exchangeRepository.findByFromAndTo(from, to);
        exchange.setPort(environment.getProperty("local.server.port"));
        logger.info("From exchange-service. Limits service down" +
                "Sending fallback response {}", new ObjectMapper().writeValueAsString(exchange));
        return exchange;
    }


}
