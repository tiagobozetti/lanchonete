package com.finch.burguer.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.Promocao;
import com.finch.burguer.repositories.LancheRepository;
import com.finch.burguer.repositories.PromocaoRepository;
import com.finch.burguer.resources.dto.CardapioDTO;
import com.finch.burguer.resources.dto.LancheDTO;
import com.finch.burguer.resources.dto.PromocaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Cardápio")
@RestController
@RequestMapping(value="/cardapio")
public class CardapioResource {
	
	@Autowired
	private LancheRepository lancheRepository;
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@ApiOperation(value="Retorna lista de lanches e promoções")
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<CardapioDTO> find() {
		List<Lanche> lanches = lancheRepository.findAll();
		List<LancheDTO> lstCardapioDTO = lanches.stream().map(obj -> new LancheDTO(obj)).collect(Collectors.toList());
		
		List<Promocao> promocoes = promocaoRepository.findAll();
		List<PromocaoDTO> lstPromocaoDTO = promocoes.stream().map(obj -> new PromocaoDTO(obj)).collect(Collectors.toList());
		
		CardapioDTO cardapioDTO = new CardapioDTO();
		cardapioDTO.setLanches(lstCardapioDTO);
		cardapioDTO.setPromocoes(lstPromocaoDTO);
		
		return ResponseEntity.ok().body(cardapioDTO);
	}
}
