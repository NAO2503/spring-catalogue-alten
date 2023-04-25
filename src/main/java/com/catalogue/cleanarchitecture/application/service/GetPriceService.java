package com.catalogue.cleanarchitecture.application.service;

import com.catalogue.cleanarchitecture.application.util.DateUtil;
import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.domain.ports.GetPriceUseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetPriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price findByBrandProductBetweenDate(String brandId, String productId, String dateBetween) {
        List<Price> prices = listAllByBrandProductBetweenDate(Long.parseLong(brandId), Long.parseLong(productId), dateBetween);

        return prices.isEmpty() ? null : prices.stream()
                .filter(p -> p.getPriceList()//Se dejan los elementos de mayor prioridad
                        >= Collections.max(prices, Comparator.comparing(Price::getPriority)).getPriceList())
                .collect(Collectors.toList())
                .stream()
                .filter(p -> p.getPriceList()//Si existe mas de uno aún, se dejara al elemento con mayor priceList
                        >= Collections.max(prices, Comparator.comparing(Price::getPriceList)).getPriceList())
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElseGet(Price::new);
    }

    private List<Price> listAllByBrandProductBetweenDate(Long brandId, Long productId, String dateBetween) {
        LocalDateTime dateToDateObj = DateUtil.getDateFromString(dateBetween);
        if (dateToDateObj == null) {
            return new ArrayList<>();
        }
        return priceRepository.findAllByBrandIdAndProductIdBetweenDates(brandId, productId, dateToDateObj);
    }
}
