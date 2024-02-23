package com.loja.lojaartigosspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loja.lojaartigosspring.dto.CategoryDto;
import com.loja.lojaartigosspring.dto.mapper.CategoryMapper;
import com.loja.lojaartigosspring.exception.RecordNotFoundException;
import com.loja.lojaartigosspring.repository.CategoryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(categoryMapper::toDto).toList();
    }

    public CategoryDto getCategoryById(@NotNull Long id) {
        return categoryRepository.findById(id)
            .map(categoryMapper::toDto)
            .orElseThrow(() -> new RecordNotFoundException("Category not found with id: " + id + "."));
    }

    public CategoryDto createCategory(@Valid @NotNull CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toModel(categoryDto)));
    }

    public CategoryDto updateCategory(@NotNull Long id, @Valid @NotNull CategoryDto categoryDto) {
        return categoryRepository.findById(id)
            .map(categoryFound -> {
                categoryFound.setName(categoryDto.name());
                return categoryMapper.toDto(categoryRepository.save(categoryFound));
            })
            .orElseThrow(() -> new RecordNotFoundException("Category not found with id: " + id + "."));
    }

    public void deleteCategory(@NotNull @Positive Long id) {
        categoryRepository.delete(categoryRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Category not found with id: " + id + ".")));
    }

}
