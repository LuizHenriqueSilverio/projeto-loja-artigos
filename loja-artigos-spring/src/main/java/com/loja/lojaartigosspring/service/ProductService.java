package com.loja.lojaartigosspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loja.lojaartigosspring.dto.ProductDto;
import com.loja.lojaartigosspring.dto.mapper.CategoryMapper;
import com.loja.lojaartigosspring.dto.mapper.ProductMapper;
import com.loja.lojaartigosspring.exception.RecordNotFoundException;
import com.loja.lojaartigosspring.repository.ProductRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::toDto).toList();
    }

    public ProductDto getProductById(@NotNull @Positive Long id) {
        return productRepository.findById(id)
            .map(productMapper::toDto)
            .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id + "."));
    }

    public ProductDto createProduct(@Valid @NotNull ProductDto productDTO) {
        return productMapper.toDto(productRepository.save(productMapper.toModel(productDTO)));
    }

    public ProductDto updateProduct(@NotNull @Positive Long id, @Valid @NotNull ProductDto productDTO) {
        return productRepository.findById(id)
            .map(productFound -> {
                productFound.setName(productDTO.name());
                productFound.setCategory(categoryMapper.toModel(productDTO.category()));
                productFound.setDescription(productDTO.description());
                if (productDTO.imageUrl() != null && !productDTO.imageUrl().isBlank()){
                    productFound.setImageUrl(productDTO.imageUrl());
                }else{
                    productFound.setImageUrl("https://www.thermaxglobal.com/wp-content/uploads/2020/05/image-not-found.jpg");
                }
                productFound.setPrice(productDTO.price());
                return productMapper.toDto(productRepository.save(productFound));
            })
            .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id + "."));
    }

    public void deleteProduct(@NotNull @Positive Long id) {
        productRepository.delete(productRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id + ".")));
    }
}
