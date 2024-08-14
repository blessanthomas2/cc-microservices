package com.example.exchange_service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LimitsConfiguration {
    int min;
    int max;
}
