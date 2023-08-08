package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.TraceabilityRequestDto;
import com.pragma.powerup.domain.model.TraceabilityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceabilityRequestMapper {
    TraceabilityModel toTraceability(TraceabilityRequestDto traceabilityRequestDto);
}
