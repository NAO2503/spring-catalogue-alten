package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper;

import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {PriceMapperImpl.class})
class PriceMapperTest {

    @Autowired
    private PriceMapper priceMapper;

    @Test
    void toResponseDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.of(2022, 01, 01, 1, 30, 59);
        LocalDateTime end = LocalDateTime.of(2022,01,31,1,30,59);
        Long priceList= 2L;
        Long brandId = 1L;
        Long productId = 35455L;
        Double priceEur = 25.45;
        Price price = Price.builder()
                .priceList(priceList)
                .brandId(brandId)
                .productId(productId)
                .price(priceEur)
                .startDate(start)
                .endDate(end)
                .build();
        PriceResponseDto priceResponseDto = priceMapper.toResponseDto(price);
        Assertions.assertNotNull(priceResponseDto);
        Assertions.assertEquals(brandId, priceResponseDto.getBrandId());
        Assertions.assertEquals(priceEur, priceResponseDto.getPrice());
        Assertions.assertEquals(priceList, priceResponseDto.getId());
        Assertions.assertEquals(start, LocalDateTime.parse(priceResponseDto.getStartDate(), formatter));
        Assertions.assertEquals(end, LocalDateTime.parse(priceResponseDto.getEndDate(), formatter));
    }

}
