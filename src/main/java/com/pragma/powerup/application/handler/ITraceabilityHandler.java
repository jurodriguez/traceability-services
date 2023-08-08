package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.TraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;

import java.util.List;

public interface ITraceabilityHandler {

    void saveTraceability(TraceabilityRequestDto traceabilityRequestDto);

    List<TraceabilityResponseDto> getAllTraceability(Long orderId);

    String timeDifferenceForOrders(Long orderId);
}