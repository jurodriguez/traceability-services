package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.enums.EOrderStatuses;
import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public String timeDifferenceForOrders(Long orderId) {
        LocalDateTime startDate = getDateByOrderIdAndStatus(orderId, EOrderStatuses.PENDING.getName());
        LocalDateTime endDate = getDateByOrderIdAndStatus(orderId, EOrderStatuses.READY.getName());
        Duration duration = Duration.between(startDate, endDate);
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return hours + ":" + minutes + ":" + seconds;
    }

    private LocalDateTime getDateByOrderIdAndStatus(Long orderId, String status) {
        String patron = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
        TraceabilityModel traceabilityModel = traceabilityPersistencePort.getTraceabilityByOrderIdAndNewStatus(orderId, status);
        if (traceabilityModel == null || traceabilityModel.getDate() == null) throw new NoDataFoundException();
        return LocalDateTime.parse(traceabilityModel.getDate(), formatter);
    }
}