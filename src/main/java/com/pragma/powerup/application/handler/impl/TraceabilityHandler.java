package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.TraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import com.pragma.powerup.application.mapper.ITraceabilityRequestMapper;
import com.pragma.powerup.application.mapper.ITraceabilityResponseMapper;
import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.model.TraceabilityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TraceabilityHandler implements ITraceabilityHandler {

    private final ITraceabilityServicePort traceabilityServicePort;
    private final ITraceabilityRequestMapper traceabilityRequestMapper;
    private final ITraceabilityResponseMapper traceabilityResponseMapper;

    @Override
    public void saveTraceability(TraceabilityRequestDto traceabilityRequestDto) {
        TraceabilityModel traceabilityModel = traceabilityRequestMapper.toTraceability(traceabilityRequestDto);
        traceabilityServicePort.saveTraceability(traceabilityModel);
    }

    @Override
    public List<TraceabilityResponseDto> getAllTraceability(Long orderId) {
        return traceabilityResponseMapper.toResponseList(traceabilityServicePort.getAllTraceability(orderId));
    }
}