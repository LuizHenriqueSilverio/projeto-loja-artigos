package com.loja.lojaartigosspring.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record SaleDto(
    Long id,
    List<SaleItemDto> items,
    @NotNull @PositiveOrZero Double total
) {
    
}
