package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;


import com.catalogue.cleanarchitecture.domain.model.Price;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper.PriceMapper;
import com.catalogue.cleanarchitecture.application.service.GetPriceService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
class CatalogueRestControllerTest {

    @Mock
    private GetPriceService getPriceUseCase;

    @Mock
    PriceMapper priceMapper;

    @InjectMocks
    private CatalogueRestController catalogueRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.catalogueRestController = new CatalogueRestController(this.getPriceUseCase, this.priceMapper);
    }

    @Test
    void returnPriceDataOK() {
        Mockito.doReturn(Price.builder().startDate(LocalDateTime.now()).build())
                .when(this.getPriceUseCase).findByBrandProductBetweenDate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assertions.assertNotNull(this.catalogueRestController.findByBrandProductBetweenDate("2", "2", "2020-06-14 10:00:00"));
    }

    @Test
    void returnNumberFormatException() {
        Mockito.doThrow(new NumberFormatException(""))
                .when(this.getPriceUseCase).findByBrandProductBetweenDate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assertions.assertThrows(NumberFormatException.class, () -> {
            this.catalogueRestController.findByBrandProductBetweenDate("3A", "1", "2020-06-14 10:00:00");
        });
    }

    @Test
    void returnNull() {
        Mockito.doReturn(null)
                .when(this.getPriceUseCase).findByBrandProductBetweenDate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Assertions.assertNotNull(this.catalogueRestController.findByBrandProductBetweenDate("1", "1", ""));
    }

}
