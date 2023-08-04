package com.pragma.powerup.infrastructure.out.mongodb.mapper;

import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.infrastructure.out.mongodb.entity.Traceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITraceabilityEntityMapper {

    Traceability toEntity(TraceabilityModel traceabilityModel);

    TraceabilityModel toTraceabilityModel(Traceability traceability);

    List<TraceabilityModel> toTraceabilityModelList(List<Traceability> traceabilityList);
}