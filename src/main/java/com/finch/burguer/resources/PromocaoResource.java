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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.finch.burguer.models.Promocao;
import com.finch.burguer.resources.dto.PromocaoDTO;
import com.finch.burguer.service.PromocaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Promoções")
@RestController
@RequestMapping(value="/promocoes")
public class PromocaoResource {

	@Autowired
	private PromocaoService promocaoService;
	
	@ApiOperation(value="Retorna promoção por código")
	@RequestMapping(value="/{codigoPromocao}", method=RequestMethod.GET)
	public ResponseEntity<Promocao> find(@PathVariable Long codigoPromocao) {
		Promocao promocao = promocaoService.find(codigoPromocao);
		
		return ResponseEntity.ok().body(promocao);
	}
	
	@ApiOperation(value="Retorna todas promoções paginada")
	@GetMapping(value="")
	public ResponseEntity<Page> find(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="promocao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		Page<Promocao> promocoes = promocaoService.search(page, linesPerPage, orderBy, direction);
		Page<PromocaoDTO> lstPromocaoDTO = promocoes.map(obj -> new PromocaoDTO(obj));
		return ResponseEntity.ok().body(lstPromocaoDTO);
	}
	
    @PostMapping
    @ApiOperation(value = "Criar nova promoção")
    public ResponseEntity<Void> create(@RequestBody @Valid PromocaoDTO promocaoDTO) {
    	
    	Promocao promocao = promocaoDTO.build();
    	promocao = promocaoService.save(promocao);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigoPromocao}").buildAndExpand(promocao.getCodigoPromocao()).toUri();
		return ResponseEntity.created(uri).build();
    }

    @PutMapping("{codigoPromocao}")
    @ApiOperation(value = "Alterar promoção.")
    public ResponseEntity<Void> update(@RequestBody @Valid PromocaoDTO promocaoDTO, @PathVariable("codigoPromocao") Long codigoPromocao) {

    	Promocao promocao = promocaoDTO.build();
    	promocao.setCodigoPromocao(codigoPromocao);
    	promocaoService.save(promocao);
    	
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{codigoPromocao}")
    @ApiOperation(value = "Remover promocao.")
    public ResponseEntity<Void> delete(@PathVariable("codigoPromocao") Long codigoPromocao) {
    	promocaoService.deleteById(codigoPromocao);
        return ResponseEntity.accepted().build();
    }
}
