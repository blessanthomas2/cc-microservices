package com.cc.limits_service.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "limits-service")
@Component
public class ConfigValues {
    int min;
    int max;
}
