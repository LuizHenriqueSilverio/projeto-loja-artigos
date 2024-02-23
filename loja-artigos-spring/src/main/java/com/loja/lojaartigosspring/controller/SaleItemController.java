package com.loja.lojaartigosspring.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.loja.lojaartigosspring.dto.SaleItemDto;
import com.loja.lojaartigosspring.service.SaleItemService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/sale-items")
public class SaleItemController {

    private final SaleItemService saleItemService;

    public SaleItemController(SaleItemService saleItemService) {
        this.saleItemService = saleItemService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleItemDto getSaleItemById(@PathVariable @NotNull @Positive Long id) {
        return saleItemService.getSaleItemById(id);
    }

    @GetMapping("/sale/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleItemDto> getSaleItemBySaleId(@PathVariable @NotNull @Positive Long id) {
        return saleItemService.getSaleItemBySaleId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleItemDto createSaleItem(@RequestBody @Valid SaleItemDto saleItemDto) {
        return saleItemService.createSaleItem(saleItemDto);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleItemDto updateSaleItem(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid SaleItemDto saleItemDto) {
        return saleItemService.updateSaleItem(id, saleItemDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSaleItem(@PathVariable @NotNull @Positive Long id) {
        saleItemService.deleteSaleItem(id);
    }
}
