package com.finch.burguer.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finch.burguer.models.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

	@Transactional
	@Modifying
	@Query("update Ingrediente ing set ing.valor = valor + (valor*?1)")
	int setValorForIngrediente(BigDecimal valor);
	
	@Query(value = "select distinct ing.* from ingrediente ing join PEDIDO_ITEM_INGREDIENTE pii on pii.codigoingrediente = ing.codigo_ingrediente where pii.codigopedidoitem = ?1", nativeQuery=true)
	List<Ingrediente> findByPedidoItem(Long codigoPedidoItem);
		
	@Query(value = "select ing.* from ingrediente ing join promocao_ingrediente pie on pie.codigoingrediente = ing.codigo_ingrediente where pie.codigopromocao = ?1", nativeQuery=true)
	List<Ingrediente> findByPromocao(Long codigoPromocao);
}
