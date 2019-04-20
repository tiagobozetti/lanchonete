package com.finch.burguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.repositories.PedidoItemRepository;

@Service
public class PedidoItemService extends AbstractService<PedidoItem,Long>{

	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Override
    protected JpaRepository<PedidoItem, Long> getRepository() {
        return pedidoItemRepository;
    }

}
