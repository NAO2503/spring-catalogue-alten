package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.springdata.dbo.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

  Price toDomain(PriceEntity entity);

}
