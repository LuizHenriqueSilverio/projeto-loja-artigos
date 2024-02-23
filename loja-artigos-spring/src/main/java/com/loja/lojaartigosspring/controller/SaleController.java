package com.loja.lojaartigosspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loja.lojaartigosspring.dto.SaleDto;
import com.loja.lojaartigosspring.service.SaleService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SaleDto> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleDto getSaleById(@PathVariable @NotNull @Positive Long id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDto createSale(@RequestBody @Valid SaleDto saleDto) {
        return saleService.createSale(saleDto);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleDto updateSale(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid SaleDto saleDto) {
        return saleService.updateSale(id, saleDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable @NotNull @Positive Long id) {
        saleService.deleteSale(id);
    }
}
