package com.loja.lojaartigosspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loja.lojaartigosspring.dto.SaleItemDto;
import com.loja.lojaartigosspring.dto.mapper.ProductMapper;
import com.loja.lojaartigosspring.dto.mapper.SaleItemMapper;
import com.loja.lojaartigosspring.dto.mapper.SaleMapper;
import com.loja.lojaartigosspring.exception.RecordNotFoundException;
import com.loja.lojaartigosspring.repository.SaleItemRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SaleItemService {
    
    private final SaleItemRepository saleItemRepository;
    private final SaleItemMapper saleItemMapper;
    private final ProductMapper productMapper;
    private final SaleMapper saleMapper;

    public SaleItemService(
    SaleItemRepository saleItemRepository, 
    SaleItemMapper saleItemMapper, 
    ProductMapper productMapper,
    SaleMapper saleMapper) {
        this.saleItemRepository = saleItemRepository;
        this.saleItemMapper = saleItemMapper;
        this.productMapper = productMapper;
        this.saleMapper = saleMapper;
    }

    public SaleItemDto getSaleItemById(@Positive @NotNull Long id) {
        return saleItemRepository.findById(id)
            .map(saleItemMapper::toDto)
            .orElseThrow(() -> new RecordNotFoundException("Sale item not found with id: " + id + "."));
    }

    public List<SaleItemDto> getSaleItemBySaleId(@Positive @NotNull Long id) {
        List<SaleItemDto> saleItemDtos = saleItemRepository.findBySaleId(id)
            .stream()
            .map(saleItemMapper::toDto)
            .toList();

        if(saleItemDtos.isEmpty()) {
            throw new RecordNotFoundException("No sale items found with sale id: " + id + ".");
        }

        return saleItemDtos;
    }

    public SaleItemDto createSaleItem(@Valid @NotNull SaleItemDto saleItemDto) {
        return saleItemMapper.toDto(saleItemRepository.save(saleItemMapper.toModel(saleItemDto)));
    }

    public SaleItemDto updateSaleItem(@Positive @NotNull Long id, @Valid @NotNull SaleItemDto saleItemDto) {
        return saleItemRepository.findById(id)
            .map(saleItemFound -> {
                saleItemFound.setProduct(productMapper.toModel(saleItemDto.product()));
                saleItemFound.setQuantity(saleItemDto.quantity());
                saleItemFound.setSale(saleMapper.toModel(saleItemDto.sale()));
                return saleItemMapper.toDto(saleItemRepository.save(saleItemFound));
            })
            .orElseThrow(() -> new RecordNotFoundException("Sale item not found with id: " + id + "."));
    }

    public void deleteSaleItem(@Positive @NotNull Long id) {
        saleItemRepository.delete(saleItemRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Sale item not found with id: " + id + ".")));
    }
}
