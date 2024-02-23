package com.loja.lojaartigosspring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleItemDto(
    Long id,
    @NotNull ProductDto product,
    @Positive @NotNull Double quantity,
    SaleDto sale
) {
    
}
