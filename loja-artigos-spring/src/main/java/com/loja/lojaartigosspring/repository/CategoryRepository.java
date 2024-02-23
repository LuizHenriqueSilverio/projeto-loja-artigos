package com.loja.lojaartigosspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojaartigosspring.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
