package com.loja.lojaartigosspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.loja.lojaartigosspring.dto.ProductDto;
import com.loja.lojaartigosspring.model.Product;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Product toModel(ProductDto productDTO) {
        if(productDTO == null) {
            return null;
        }
        Product product = new Product();
        if(productDTO.id() != null){ 
            product.setId(productDTO.id());
        }
        if(productDTO.imageUrl() != null && !productDTO.imageUrl().isBlank()) {
            product.setImageUrl(productDTO.imageUrl());
        }else{
            product.setImageUrl("https://www.thermaxglobal.com/wp-content/uploads/2020/05/image-not-found.jpg");
        }
        product.setName(productDTO.name());
        product.setCategory(categoryMapper.toModel(productDTO.category()));
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        return product;
    }

    public ProductDto toDto(Product product) {
        if(product == null) {
            return null;
        }
        return new ProductDto(
            product.getId(),
            product.getName(),
            categoryMapper.toDto(product.getCategory()),
            product.getDescription(),
            product.getImageUrl(),
            product.getPrice()
        );
    }
    
}
