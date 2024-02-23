package com.loja.lojaartigosspring.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.loja.lojaartigosspring.dto.ProductDto;
import com.loja.lojaartigosspring.model.Category;
import com.loja.lojaartigosspring.model.Product;
import com.loja.lojaartigosspring.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(10.0);
        product1.setCategory(new Category());
        product1.setDescription("Description 1");
        product1.setImageUrl("Image 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(20.0);
        product2.setCategory(new Category());
        product2.setDescription("Description 2");
        product2.setImageUrl("Image 2");

        List<Product> productsList = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(productsList);

        List<ProductDto> result = productService.getAllProducts();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Product 1", result.get(0).name());
        Assertions.assertEquals("Product 2", result.get(1).name());
        Assertions.assertEquals(10.0, result.get(0).price());
        Assertions.assertEquals(20.0, result.get(1).price());
        Assertions.assertEquals("Description 1", result.get(0).description());
        Assertions.assertEquals("Description 2", result.get(1).description());
        Assertions.assertEquals("Image 1", result.get(0).imageUrl());
        Assertions.assertEquals("Image 2", result.get(1).imageUrl());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setCategory(new Category());
        product.setDescription("Description 1");
        product.setImageUrl("Image 1");

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        ProductDto result = productService.getProductById(1L);

        Assertions.assertEquals("Product 1", result.name());
        Assertions.assertEquals(10.0, result.price());
        Assertions.assertEquals("Description 1", result.description());
        Assertions.assertEquals("Image 1", result.imageUrl());
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(com.loja.lojaartigosspring.exception.RecordNotFoundException.class, () -> {
            productService.getProductById(1L);
        });
    }
}
