package com.loja.lojaartigosspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja.lojaartigosspring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
