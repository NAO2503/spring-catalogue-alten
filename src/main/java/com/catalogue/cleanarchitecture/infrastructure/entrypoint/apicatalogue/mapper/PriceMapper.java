package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "id", source = "priceList")
    @Mapping(target = "startDate", expression = "java(mapDateToString(price.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(mapDateToString(price.getEndDate()))")
    PriceResponseDto toResponseDto(Price price);

    List<PriceResponseDto> listToResponseDtoList(List<Price> prices);

    default String mapDateToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

}
