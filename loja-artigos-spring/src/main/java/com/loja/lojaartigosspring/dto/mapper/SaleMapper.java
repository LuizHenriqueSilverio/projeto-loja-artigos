package com.loja.lojaartigosspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.loja.lojaartigosspring.dto.SaleDto;
import com.loja.lojaartigosspring.model.Sale;

@Component
public class SaleMapper {

    public SaleMapper() {
    }
    
    public Sale toModel(SaleDto saleDto) {
        if(saleDto == null) {
            return null;
        }
        Sale sale = new Sale();
        if(saleDto.id() != null) {
            sale.setId(saleDto.id());
        }
        sale.setTotal(saleDto.total());
        return sale;
    }

    public SaleDto toDto(Sale sale) {
        if(sale == null) {
            return null;
        }
        return new SaleDto(
            sale.getId(),
            null,
            sale.getTotal()
        );
    }
}
