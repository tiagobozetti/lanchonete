package com.finch.burguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.PedidoItemIngrediente;
import com.finch.burguer.repositories.PedidoItemIngredienteRepository;

@Service
public class PedidoItemIngredienteService extends AbstractService<PedidoItemIngrediente,Long>{

	@Autowired
	private PedidoItemIngredienteRepository pedidoItemIngredienteRepository;
	
	@Override
    protected JpaRepository<PedidoItemIngrediente, Long> getRepository() {
        return pedidoItemIngredienteRepository;
    }
}
