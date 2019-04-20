package com.finch.burguer.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finch.burguer.models.Promocao;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long>{

	List<Promocao> findAllByOrderByDesconto();
}
