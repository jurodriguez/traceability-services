package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

import java.util.List;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;

    public TraceabilityUseCase(ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.traceabilityPersistencePort = traceabilityPersistencePort;
    }

    @Override
    public void saveTraceability(TraceabilityModel traceabilityModel) {
        traceabilityPersistencePort.saveTraceability(traceabilityModel);
    }

    @Override
    public List<TraceabilityModel> getAllTraceability(Long orderId) {
        List<TraceabilityModel> traceabilityModelList = traceabilityPersistencePort.getAllTraceability(orderId);
        if (traceabilityModelList.isEmpty()) throw new NoDataFoundException();
        return traceabilityModelList;
    }
}