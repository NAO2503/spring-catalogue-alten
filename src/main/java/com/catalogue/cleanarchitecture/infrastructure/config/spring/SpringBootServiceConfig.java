package com.catalogue.cleanarchitecture.infrastructure.config.spring;


import org.springframework.context.annotation.Bean;

import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.application.service.GetPriceService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootServiceConfig {

    @Bean
    public GetPriceService getUserUseCase(PriceRepository priceRepository) {
        return new GetPriceService(priceRepository);
    }
}