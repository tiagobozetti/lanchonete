package com.finch.burguer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finch.burguer.models.PedidoItemIngrediente;

@Repository
public interface PedidoItemIngredienteRepository extends JpaRepository<PedidoItemIngrediente, Long>{

}
