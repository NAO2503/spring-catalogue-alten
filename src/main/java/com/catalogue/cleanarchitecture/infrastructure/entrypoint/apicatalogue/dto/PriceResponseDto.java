package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PriceResponseDto {

    private Long id;

    private Long brandId;

    private Double price;

    private String startDate;

    private String endDate;
}
