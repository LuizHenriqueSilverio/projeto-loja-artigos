package com.loja.lojaartigosspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductDto(
    Long id,
    @NotBlank @NotNull @Length(min = 3, max = 100) String name,
    @NotNull CategoryDto category,
    @NotBlank @NotNull @Length(min = 3, max = 1000) String description,
    String imageUrl,
    @NotNull @Positive Double price
) {
    
}
