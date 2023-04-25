package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.repository;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.domain.ports.PriceRepository;
import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PriceDboRepository implements PriceRepository {

    private final SpringDataPriceRepository priceRepository;

    private final PriceEntityMapper priceEntityMapper;

    @Override
    public List<Price> findAllByBrandIdAndProductIdBetweenDates(Long brandId, Long productId, LocalDateTime dateBetween) {
        return priceRepository.findAllByBrandIdAndProductIdBetweenDates(brandId, productId, dateBetween)
                .orElseGet(ArrayList::new)
                .stream()
                .map(priceEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
