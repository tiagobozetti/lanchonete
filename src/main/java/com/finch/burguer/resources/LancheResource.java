package com.finch.burguer.resources;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.LancheIngrediente;
import com.finch.burguer.resources.dto.LancheDTO;
import com.finch.burguer.resources.dto.LancheIngredienteDTO;
import com.finch.burguer.service.LancheIngredienteService;
import com.finch.burguer.service.LancheService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Lanches")
@RestController
@RequestMapping(value="/lanches")
public class LancheResource {

	@Autowired
	private LancheService lancheService;
	@Autowired
	private LancheIngredienteService lancheIngredienteService;
	
	@ApiOperation(value="Retorna lanche por código")
	@GetMapping(value="/{codigoLanche}")
	public ResponseEntity<LancheDTO> find(@PathVariable Long codigoLanche) {
		return ResponseEntity.ok().body(new LancheDTO(lancheService.find(codigoLanche)));
	}
	
	@ApiOperation(value="Retorna todos lanches paginado")
	@GetMapping(value="")
	public ResponseEntity<Page> find(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="lanche") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Lanche> lanches = lancheService.search(page, linesPerPage, orderBy, direction);
		Page<LancheDTO> lstLancheDTO = lanches.map(obj -> new LancheDTO(obj));
		return ResponseEntity.ok().body(lstLancheDTO);
	}
	
    @PostMapping
    @ApiOperation(value = "Criar novo lanche")
    public ResponseEntity<Void> create(@RequestBody @Valid LancheDTO lancheDTO) {
    	
    	Lanche lanche = lancheDTO.build();
    	lanche = lancheService.save(lanche);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoLanche}").buildAndExpand(lanche.getCodigoLanche()).toUri();
		return ResponseEntity.created(uri).build();
    }

    @PutMapping("{codigoLanche}")
    @ApiOperation(value = "Alterar lanche.")
    public ResponseEntity<Void> update(@RequestBody @Valid LancheDTO lancheDTO, @PathVariable("codigoLanche") Long codigoLanche) {

    	Lanche lanche = lancheDTO.build();
    	lanche.setCodigoLanche(codigoLanche);
    	lancheService.update(lanche);
    	
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{codigoLanche}")
    @ApiOperation(value = "Remover lanche.")
    public ResponseEntity<Void> delete(@PathVariable("codigoLanche") Long codigoLanche) {
    	lancheService.deleteById(codigoLanche);
        return ResponseEntity.accepted().build();
    }
    
    @PostMapping("{codigoLanche}/ingredientes")
    @ApiOperation(value = "Inserir ingrediente em um lanche.")
    public ResponseEntity<Void> insert(@RequestBody @Valid LancheIngredienteDTO lancheIngredienteDTO, @PathVariable("codigoLanche") Long codigoLanche) {

    	LancheIngrediente lancheIngrediente = lancheIngredienteDTO.build();
    	lancheIngrediente.setLanche(new Lanche(codigoLanche));
    	lancheIngredienteService.save(lancheIngrediente);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoLanche}/ingredientes/{codigoLancheIngrediente}").buildAndExpand(codigoLanche, lancheIngrediente.getCodigoLancheIngrediente()).toUri();
		return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("{codigoLanche}/ingredientes/{codigoLancheIngrediente}")
    @ApiOperation(value = "Alterar a quantidade de um ingrediente de um lanche.")
    public ResponseEntity<Void> update(@RequestBody @Valid LancheIngredienteDTO lancheIngredienteDTO, @PathVariable("codigoLanche") Long codigoLanche, @PathVariable("codigoLancheIngrediente") Long codigoLancheIngrediente) {

    	LancheIngrediente lancheIngrediente = lancheIngredienteDTO.build();
    	lancheIngrediente.setCodigoLancheIngrediente(codigoLancheIngrediente);
    	lancheIngrediente.setLanche(new Lanche(codigoLanche));
    	lancheIngredienteService.update(lancheIngrediente);
    	
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("{codigoLanche}/ingredientes/{codigoLancheIngrediente}")
    @ApiOperation(value = "Remover ingrediente por código de um lanche.")
    public ResponseEntity<Void> delete(@PathVariable("codigoLanche") Long codigoLanche,
    		@PathVariable("codigoLancheIngrediente") Long codigoLancheIngrediente) {
    	lancheIngredienteService.deleteById(codigoLancheIngrediente);
        return ResponseEntity.accepted().build();
    }
}
