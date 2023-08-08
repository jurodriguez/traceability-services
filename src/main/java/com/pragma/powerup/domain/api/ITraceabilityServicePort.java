package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityServicePort {

    void saveTraceability(TraceabilityModel traceabilityModel);

    List<TraceabilityModel> getAllTraceability(Long orderId);

    String timeDifferenceForOrders(Long orderId);
}