package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.domain.model.TraceabilityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceabilityResponseMapper {
    TraceabilityResponseDto toResponse(TraceabilityModel traceabilityModel);

    List<TraceabilityResponseDto> toResponseList(List<TraceabilityModel> traceabilityModelList);
}
