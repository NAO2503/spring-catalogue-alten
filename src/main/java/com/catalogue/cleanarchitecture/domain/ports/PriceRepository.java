package com.catalogue.cleanarchitecture.domain.ports;

import com.catalogue.cleanarchitecture.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
   
   List<Price> findAllByBrandIdAndProductIdBetweenDates(Long brandId, Long productId, LocalDateTime dateBetween);

}
