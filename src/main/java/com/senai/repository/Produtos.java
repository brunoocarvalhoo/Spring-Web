package com.senai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.model.Produto;  

public interface Produtos extends JpaRepository<Produto, Long> {

}
