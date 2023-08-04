package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.domain.usecase.TraceabilityUseCase;
import com.pragma.powerup.infrastructure.out.mongodb.adapter.TraceabilityJpaAdapter;
import com.pragma.powerup.infrastructure.out.mongodb.mapper.ITraceabilityEntityMapper;
import com.pragma.powerup.infrastructure.out.mongodb.repository.ITraceabilityMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITraceabilityMongoRepository traceabilityRepository;
    private final ITraceabilityEntityMapper traceabilityEntityMapper;

    @Bean
    public ITraceabilityPersistencePort traceabilityPersistencePort() {
        return new TraceabilityJpaAdapter(traceabilityRepository, traceabilityEntityMapper);
    }

    @Bean
    public ITraceabilityServicePort traceabilityServicePort() {
        return new TraceabilityUseCase(traceabilityPersistencePort());
    }
}