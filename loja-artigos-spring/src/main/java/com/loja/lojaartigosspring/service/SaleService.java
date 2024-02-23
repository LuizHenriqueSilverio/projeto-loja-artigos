package com.loja.lojaartigosspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loja.lojaartigosspring.dto.SaleDto;
import com.loja.lojaartigosspring.dto.mapper.SaleItemMapper;
import com.loja.lojaartigosspring.dto.mapper.SaleMapper;
import com.loja.lojaartigosspring.exception.RecordNotFoundException;
import com.loja.lojaartigosspring.repository.SaleRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final SaleItemMapper saleItemMapper;

    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper, SaleItemMapper saleItemMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.saleItemMapper = saleItemMapper;
    }

    public List<SaleDto> getAllSales() {
        return saleRepository.findAll()
            .stream()
            .map(saleMapper::toDto).toList();
    }

    public SaleDto getSaleById(@NotNull @Positive Long id) {
        return saleRepository.findById(id)
            .map(saleMapper::toDto)
            .orElseThrow(() -> new RecordNotFoundException("Sale not found with id: " + id + "."));
    }

    public SaleDto createSale(@Valid @NotNull SaleDto saleDto) {
        return saleMapper.toDto(saleRepository.save(saleMapper.toModel(saleDto)));
    }

    public SaleDto createSaleWithSession(HttpSession session, @Valid @NotNull SaleDto saleDto) {
        SaleDto sessionSaleDto = this.createSale(saleDto);
        session.setAttribute("saleId", sessionSaleDto.id());
        return sessionSaleDto;
    }

    public void endSaleWithSession(HttpSession session) {
        Long saleId = (Long) session.getAttribute("saleId");
        this.deleteSale(saleId);
        session.invalidate();
    }

    public SaleDto updateSale(@NotNull @Positive Long id, @Valid @NotNull SaleDto saleDto) {
        return saleRepository.findById(id)
            .map(saleFound -> {
                saleFound.setItems(saleDto.items()
                    .stream()
                    .map(saleItemMapper::toModel)
                    .toList());
                saleFound.setTotal(saleDto.total());
                return saleMapper.toDto(saleRepository.save(saleFound));
            })
            .orElseThrow(() -> new RecordNotFoundException("Sale not found with id: " + id + "."));
    }

    public void deleteSale(@NotNull @Positive Long id) {
        saleRepository.delete(saleRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Sale not found with id: " + id + ".")));
    }
}
