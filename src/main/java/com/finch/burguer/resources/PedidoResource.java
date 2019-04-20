package com.finch.burguer.resources;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.Pedido;
import com.finch.burguer.resources.dto.IngredienteDTO;
import com.finch.burguer.resources.dto.PedidoDTO;
import com.finch.burguer.service.PedidoService;
import com.finch.burguer.services.exceptions.ObjectNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Pedidos")
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@ApiOperation(value="Inserir pedido")
	@PostMapping(value = "")
	public ResponseEntity<Void> inserir(@RequestBody @Valid PedidoDTO pedidoDTO) {
		
		Pedido pedido = pedidoDTO.build();
		pedidoService.save(pedido);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoPedido}").buildAndExpand(pedido.getCodigoPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Retornar pedido através do código")
	@GetMapping(value = "/{codigoPedido}")
	public ResponseEntity<PedidoDTO> find(@PathVariable Long codigoPedido) {
		
		Optional<Pedido> optPedido = pedidoService.findById(codigoPedido);
		optPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigoPedido + ", Tipo: " + Pedido.class.getName()));
		
		PedidoDTO ped = new PedidoDTO(optPedido.get());
		
		return ResponseEntity.ok().body(ped);
	}
	
	@ApiOperation(value="Retornar pedidos paginado")
	@GetMapping(value = "")
	public ResponseEntity<Page> find(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="codigoPedido") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		
		Page<Pedido> pedidos = pedidoService.search(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> lstPedidoDTO = pedidos.map(obj -> new PedidoDTO(obj));
		return ResponseEntity.ok().body(lstPedidoDTO);
	}

}
