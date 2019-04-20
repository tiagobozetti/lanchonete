package com.finch.burguer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finch.burguer.models.PromocaoIngrediente;

@Repository
public interface PromocaoIngredienteRepository extends JpaRepository<PromocaoIngrediente, Long>{

}
