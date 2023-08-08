package com.pragma.powerup.infrastructure.out.mongodb.adapter;

import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.infrastructure.out.mongodb.entity.Traceability;
import com.pragma.powerup.infrastructure.out.mongodb.mapper.ITraceabilityEntityMapper;
import com.pragma.powerup.infrastructure.out.mongodb.repository.ITraceabilityMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TraceabilityJpaAdapter implements ITraceabilityPersistencePort {

    private final ITraceabilityMongoRepository traceabilityRepository;
    private final ITraceabilityEntityMapper traceabilityEntityMapper;


    @Override
    public TraceabilityModel saveTraceability(TraceabilityModel traceabilityModel) {
        Traceability traceability = traceabilityRepository.save(traceabilityEntityMapper.toEntity(traceabilityModel));
        return traceabilityEntityMapper.toTraceabilityModel(traceability);
    }

    @Override
    public List<TraceabilityModel> getAllTraceability(Long orderId) {
        return traceabilityEntityMapper.toTraceabilityModelList(traceabilityRepository.findByOrderId(String.valueOf(orderId)));
    }

    @Override
    public TraceabilityModel getTraceabilityByOrderIdAndNewStatus(Long orderId, String status) {
        Optional<Traceability> traceability = traceabilityRepository.findByOrderIdAndNewStatus(String.valueOf(orderId), status);
        return traceabilityEntityMapper.toTraceabilityModel(traceability.orElse(null));
    }
}