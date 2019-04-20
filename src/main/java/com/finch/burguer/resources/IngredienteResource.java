package com.finch.burguer.resources;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.resources.dto.IngredienteDTO;
import com.finch.burguer.service.IngredienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Ingredientes")
@RestController
@RequestMapping(value="/ingredientes")
public class IngredienteResource {

	@Autowired
	private IngredienteService ingredienteService;
	
	@ApiOperation(value="Retorna ingrediente por código")
	@RequestMapping(value="/{codigoIngrediente}", method=RequestMethod.GET)
	public ResponseEntity<Ingrediente> find(@PathVariable Long codigoIngrediente) {
		Ingrediente ingrediente = ingredienteService.find(codigoIngrediente);
		
		return ResponseEntity.ok().body(ingrediente);
	}
	
	@ApiOperation(value="Retorna todos ingredientes paginado")
	@GetMapping(value="")
	public ResponseEntity<Page> find(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="ingrediente") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Ingrediente> ingredientes = ingredienteService.search(page, linesPerPage, orderBy, direction);
		Page<IngredienteDTO> lstIngredienteDTO = ingredientes.map(obj -> new IngredienteDTO(obj, 0));
		return ResponseEntity.ok().body(lstIngredienteDTO);
	}
	
    @PostMapping
    @ApiOperation(value = "Criar novo ingrediente")
    public ResponseEntity<Void> create(@RequestBody @Valid IngredienteDTO ingredienteDTO) {
    	
    	Ingrediente ingrediente = ingredienteDTO.build();
    	ingrediente = ingredienteService.save(ingrediente);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoIngrediente}").buildAndExpand(ingrediente.getCodigoIngrediente()).toUri();
		return ResponseEntity.created(uri).build();
    }

    @PutMapping("{codigoIngrediente}")
    @ApiOperation(value = "Alterar ingrediente.")
    public ResponseEntity<Void> update(@RequestBody @Valid IngredienteDTO ingredienteDTO, @PathVariable("codigoIngrediente") Long codigoIngrediente) {

    	Ingrediente ingrediente = ingredienteDTO.build();
    	ingrediente.setCodigoIngrediente(codigoIngrediente);
    	ingredienteService.save(ingrediente);
    	
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{codigoIngrediente}")
    @ApiOperation(value = "Remover ingrediente.")
    public ResponseEntity<Void> delete(@PathVariable("codigoIngrediente") Long codigoIngrediente) {
    	ingredienteService.deleteById(codigoIngrediente);
        return ResponseEntity.accepted().build();
    }
	
	@ApiOperation(value="Atualizar preço de todos ingredientes")
	@RequestMapping(value="/atualizarPreco/{inflacao}", method=RequestMethod.PATCH)
	public ResponseEntity<Void> atualizarPreco(@PathVariable Double inflacao) {
		
		ingredienteService.atualizarPreco(BigDecimal.valueOf(inflacao));
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Atualizar preço de um único ingredientes")	
	@RequestMapping(value="{codigoIngrediente}/atualizarPreco/{inflacao}", method=RequestMethod.PATCH)
	public ResponseEntity<Void> atualizarPreco(@PathVariable Long codigoIngrediente, @PathVariable Double inflacao) {
		
		ingredienteService.atualizarPreco(codigoIngrediente, BigDecimal.valueOf(inflacao));
		
		return ResponseEntity.noContent().build();
	}
}
