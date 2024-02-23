package com.loja.lojaartigosspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.lojaartigosspring.model.SaleItem;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long>{
    
    public List<SaleItem> findBySaleId(Long saleId);
}
