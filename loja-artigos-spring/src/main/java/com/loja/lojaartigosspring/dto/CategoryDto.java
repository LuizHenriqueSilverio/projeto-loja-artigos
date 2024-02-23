package com.loja.lojaartigosspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(
    Long id,
    @NotBlank @NotNull @Length(min = 3, max = 100) String name,
    List<ProductDto> products
) {
    
}
