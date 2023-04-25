package com.catalogue.cleanarchitecture.infrastructure.driven.adapters.databaseh2.springdata.dbo;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
public class PriceEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "PRICE_LIST", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long priceList;

  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "BRAND_ID", nullable = false)
  private Long brandId;

  @Column(name = "PRODUCT_ID", nullable = false)
  private Long productId;

  @Column(name = "PRIORITY", nullable = false)
  private Integer priority;

  @Column(name = "PRICE", nullable = false)
  private Double price;

  @Column(name = "CURR", nullable = false)
  private String curr;

}
