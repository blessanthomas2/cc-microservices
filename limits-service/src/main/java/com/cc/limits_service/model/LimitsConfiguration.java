package com.cc.limits_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
public class LimitsConfiguration {
    int min;
    int max;
}
