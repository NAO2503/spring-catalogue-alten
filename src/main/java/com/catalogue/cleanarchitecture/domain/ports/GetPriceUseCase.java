package com.catalogue.cleanarchitecture.domain.ports;

import com.catalogue.cleanarchitecture.domain.model.Price;

import java.util.List;

public interface GetPriceUseCase {

   Price findByBrandProductBetweenDate(String brandId, String productId, String dateBetween);

}
