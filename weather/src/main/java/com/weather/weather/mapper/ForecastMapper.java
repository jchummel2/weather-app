package com.weather.weather.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.weather.weather.provider.nws.NwsModels;
import com.weather.weather.schema.ForecastDto;

@Mapper(componentModel = "spring")
public interface ForecastMapper {
    
    @Mapping(target = "periodName", source = "name")
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "fetchedAt", ignore = true)
    ForecastDto toDto(NwsModels.Period period);

    List<ForecastDto> toList(List<NwsModels.Period> periods);
}
