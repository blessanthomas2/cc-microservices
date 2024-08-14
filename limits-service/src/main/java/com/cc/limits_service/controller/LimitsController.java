package com.cc.limits_service.controller;

import com.cc.limits_service.model.ConfigValues;
import com.cc.limits_service.model.LimitsConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/limits-api")
public class LimitsController {

    @Autowired
    ConfigValues configValues;

    Logger logger = LoggerFactory.getLogger(LimitsController.class);

    @GetMapping("/limits")
    public LimitsConfiguration getLimits() throws JsonProcessingException {
        LimitsConfiguration limitsConfiguration = new LimitsConfiguration(configValues.getMin(), configValues.getMax());
        logger.info("From limits-service " + new ObjectMapper().writeValueAsString(limitsConfiguration));
        return limitsConfiguration;
    }

    @GetMapping("/test")
    public String getValue(@Value("${thisThing}") String value){
        return value;
    }

    

}
