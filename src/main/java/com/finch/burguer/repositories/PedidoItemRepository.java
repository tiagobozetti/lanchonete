package com.finch.burguer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finch.burguer.models.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>{

}
