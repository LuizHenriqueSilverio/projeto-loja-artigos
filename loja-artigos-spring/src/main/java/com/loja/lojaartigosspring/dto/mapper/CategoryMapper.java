package com.loja.lojaartigosspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.loja.lojaartigosspring.dto.CategoryDto;
import com.loja.lojaartigosspring.model.Category;

@Component
public class CategoryMapper {

    public Category toModel(CategoryDto categoryDto) {
        if(categoryDto == null) {
            return null;
        }
        Category category = new Category();
        if(categoryDto.id() != null) {
            category.setId(categoryDto.id());
        }
        category.setName(categoryDto.name());
        return category;
    }

    public CategoryDto toDto(Category category) {
        if(category == null) {
            return null;
        }
        return new CategoryDto(
            category.getId(),
            category.getName(),
            null
        );
    }
}
