package com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue;

import com.catalogue.cleanarchitecture.domain.ports.GetPriceUseCase;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.dto.PriceResponseDto;
import com.catalogue.cleanarchitecture.infrastructure.entrypoint.apicatalogue.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalogue")
public class CatalogueRestController {

    private final GetPriceUseCase getPriceUseCase;

    private final PriceMapper priceMapper;

    @Operation(summary = "Get a Price by its brandId, productId and offer date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the price",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PriceResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid params supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found",
                    content = @Content)})
    @GetMapping("/price/findByBrandProductBetweenDate")
    public ResponseEntity<PriceResponseDto> findByBrandProductBetweenDate(
            @NotNull @RequestParam String brandId,
            @NotNull @RequestParam String productId,
            @NotNull @RequestParam String dateQuery) {

        PriceResponseDto response = priceMapper.toResponseDto(getPriceUseCase.findByBrandProductBetweenDate(brandId,
                productId, dateQuery));
        return (response != null) ? new ResponseEntity<>(response, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
