package com.loja.lojaartigosspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.loja.lojaartigosspring.dto.SaleItemDto;
import com.loja.lojaartigosspring.model.SaleItem;

@Component
public class SaleItemMapper {

    private final ProductMapper productMapper;

    public SaleItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    
    public SaleItem toModel(SaleItemDto saleItemDto) {
        if(saleItemDto == null) {
            return null;
        }
        SaleMapper saleMapper = new SaleMapper();
        SaleItem saleItem = new SaleItem();
        if(saleItemDto.id() != null) {
            saleItem.setId(saleItemDto.id());
        }
        saleItem.setProduct(productMapper.toModel(saleItemDto.product()));
        saleItem.setQuantity(saleItemDto.quantity());
        saleItem.setSale(saleMapper.toModel(saleItemDto.sale()));
        return saleItem;
    }

    public SaleItemDto toDto(SaleItem saleItem) {
        if(saleItem == null) {
            return null;
        }
        SaleMapper saleMapper = new SaleMapper();
        return new SaleItemDto(
            saleItem.getId(),
            productMapper.toDto(saleItem.getProduct()),
            saleItem.getQuantity(),
            saleMapper.toDto(saleItem.getSale())
        );
    }
}
