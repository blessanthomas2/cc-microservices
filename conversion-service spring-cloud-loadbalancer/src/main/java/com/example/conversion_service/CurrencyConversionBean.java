package com.example.conversion_service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor
@Data
public class CurrencyConversionBean {
    int id;
    String from;
    String to;
    double amount;
    BigDecimal exchangeValue;
    BigDecimal targetValue;
    String port;
}
