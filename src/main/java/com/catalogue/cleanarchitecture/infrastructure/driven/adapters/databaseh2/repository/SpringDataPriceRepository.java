package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.repository;


import com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.springdata.dbo.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.priceList = ?1 ")
    Optional<PriceEntity> findByPriceList(Long priceList);

    @Query("SELECT p FROM PriceEntity p WHERE p.brandId = ?1 AND p.productId = ?2 AND p.startDate <= ?3 AND p.endDate >= ?3 ORDER BY p.priority DESC, p.priceList DESC")
    Optional<List<PriceEntity>> findAllByBrandIdAndProductIdBetweenDates(Long brandId, Long productId, LocalDateTime dateBetween);
}
