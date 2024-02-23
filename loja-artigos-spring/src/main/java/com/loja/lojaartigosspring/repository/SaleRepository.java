package com.loja.lojaartigosspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojaartigosspring.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
