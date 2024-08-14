package com.example.conversion_service;


import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadBalancerConfig {
    @Bean
    public ServiceInstanceListSupplier getServiceInstanceListSupplier(ConfigurableApplicationContext context) {
        ServiceInstanceListSupplier silSupplier = new ServiceInstanceListSupplier() {
            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.just(Arrays.asList
                        (new DefaultServiceInstance("1", "1", "localhost", 8100, false),
                                new DefaultServiceInstance("2", "2", "localhost", 8102, false)));
            }
            @Override
            public String getServiceId() {
                return "service-instance-list";
            }
        };
        return silSupplier;
    }

}
