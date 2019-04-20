package com.finch.burguer.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.Ingrediente;
import com.finch.burguer.models.Lanche;
import com.finch.burguer.models.LancheIngrediente;
import com.finch.burguer.models.Pedido;
import com.finch.burguer.models.PedidoItem;
import com.finch.burguer.models.PedidoItemIngrediente;
import com.finch.burguer.models.Promocao;
import com.finch.burguer.models.PromocaoIngrediente;
import com.finch.burguer.models.enums.TipoPromocaoEnum;
import com.finch.burguer.repositories.IngredienteRepository;
import com.finch.burguer.repositories.LancheRepository;
import com.finch.burguer.repositories.PromocaoIngredienteRepository;

@Service
public class BancoTestService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	@Autowired
	private LancheRepository lancheRepository;
	@Autowired
	private PromocaoService promocaoService;
	@Autowired
	private PromocaoIngredienteRepository promocaoIngredienteRepository;
	@Autowired
	private PedidoService pedidoService;
	
	public void Iniciar() {
		Ingrediente ingAlface = new Ingrediente("Alface", BigDecimal.valueOf(0.40));
		Ingrediente ingBacon = new Ingrediente("Bacon", BigDecimal.valueOf(2.0));
		Ingrediente ingHamburguer = new Ingrediente("Hamburguer", BigDecimal.valueOf(3.0));
		Ingrediente ingOvo = new Ingrediente("Ovo", BigDecimal.valueOf(0.80));
		Ingrediente ingQueijo = new Ingrediente("Queijo", BigDecimal.valueOf(1.5));
		
		ingredienteRepository.saveAll(Arrays.asList(ingAlface, ingBacon, ingHamburguer, ingOvo, ingQueijo));
		
		Lanche lancheXbacon = new Lanche("X-Bacon");
		LancheIngrediente ingXbaconBacon = new LancheIngrediente(lancheXbacon, ingBacon, 1);
		LancheIngrediente ingXbaconHamburguer = new LancheIngrediente(lancheXbacon, ingHamburguer, 1);
		LancheIngrediente ingXbaconOvo = new LancheIngrediente(lancheXbacon, ingOvo, 1);
		lancheXbacon.getLancheIngredientes().addAll(Arrays.asList(ingXbaconBacon, ingXbaconHamburguer, ingXbaconOvo));
		
		Lanche lancheXburguer = new Lanche("X-Burguer");
		LancheIngrediente ingXburguerBacon = new LancheIngrediente(lancheXburguer, ingHamburguer, 1);
		LancheIngrediente ingXburguerQueijo = new LancheIngrediente(lancheXburguer, ingQueijo, 1);
		lancheXburguer.getLancheIngredientes().addAll(Arrays.asList(ingXburguerBacon, ingXburguerQueijo));
		
		Lanche lancheXegg = new Lanche("X-Egg");
		LancheIngrediente ingXeggOvo = new LancheIngrediente(lancheXegg, ingOvo, 1);
		LancheIngrediente ingXeggHamburguer = new LancheIngrediente(lancheXegg, ingHamburguer, 1);
		LancheIngrediente ingXeggQueijo = new LancheIngrediente(lancheXegg, ingQueijo, 1);
		lancheXegg.getLancheIngredientes().addAll(Arrays.asList(ingXeggOvo, ingXeggHamburguer, ingXeggQueijo));
		
		Lanche lancheXeggBacon = new Lanche("X-Egg Bacon");
		LancheIngrediente ingXeggBancoOvo = new LancheIngrediente(lancheXeggBacon, ingOvo, 1);
		LancheIngrediente ingXeggBancoBacon = new LancheIngrediente(lancheXeggBacon, ingBacon, 1);
		LancheIngrediente ingXeggBancoHamburguer = new LancheIngrediente(lancheXeggBacon, ingHamburguer, 1);
		LancheIngrediente ingXeggBancoQueijo = new LancheIngrediente(lancheXeggBacon, ingQueijo, 1);
		lancheXeggBacon.getLancheIngredientes().addAll(Arrays.asList(ingXeggBancoOvo, ingXeggBancoBacon, ingXeggBancoHamburguer, ingXeggBancoQueijo));
		
		lancheRepository.saveAll(Arrays.asList(lancheXbacon, lancheXburguer, lancheXegg, lancheXeggBacon));
		
		Promocao promocaoLigth = new Promocao("Ligth", "Se o lanche tem alface e não tem bacon, ganha 10% de desconto", TipoPromocaoEnum.PORCENTAGEM, BigDecimal.valueOf(10.00));
		//promocaoLigth.getPromocaoIngredientes().add(new PromocaoIngrediente(1l, promocaoLigth, ingAlface, 0, 0));
		promocaoLigth.getIngredientesExclusao().add(ingBacon);
		
		Promocao promocaoMuitaCarne = new Promocao("Muita Carne", "A cada 3 porções de hambúrguer o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente.", TipoPromocaoEnum.QUANTIDADE, BigDecimal.ZERO);
		
		Promocao promocaoMuitoQueijo = new Promocao("Muito Queijo", "A cada 3 porções de queijo o cliente só paga 2, a cada 6 porções, o cliente pagará 4 e assim sucessivamente.", TipoPromocaoEnum.QUANTIDADE, BigDecimal.ZERO);
		
		promocaoService.saveAll(Arrays.asList(promocaoLigth, promocaoMuitaCarne, promocaoMuitoQueijo));
		
		PromocaoIngrediente promocaoIngLigth = new PromocaoIngrediente(promocaoLigth, ingAlface, 0, 0);
		PromocaoIngrediente promocaoIngMuitaCarne = new PromocaoIngrediente(promocaoMuitaCarne, ingHamburguer, 3, 2);
		PromocaoIngrediente promocaoIngMuitoQueijo = new PromocaoIngrediente(promocaoMuitoQueijo, ingQueijo, 3, 2);
		
		promocaoIngredienteRepository.saveAll(Arrays.asList(promocaoIngLigth, promocaoIngMuitaCarne, promocaoIngMuitoQueijo));
		
		//Inserir lanche com os ingredientes padrões. Informar apenas os ingredientes adicionais
		Pedido pedido = new Pedido(new Date());		
		PedidoItem itemXbacon = new PedidoItem(pedido, lancheXbacon, 1);
		pedido.getItens().add(itemXbacon);		
		PedidoItem itemXburguer = new PedidoItem(pedido, lancheXburguer, 1);
	    pedido.getItens().add(itemXburguer);		
	    PedidoItem itemXegg = new PedidoItem(pedido, lancheXegg, 1);
	    pedido.getItens().add(itemXegg);	    
	    PedidoItem itemXeggBacon = new PedidoItem(pedido, lancheXeggBacon, 1);
	    pedido.getItens().add(itemXeggBacon);
		pedidoService.save(pedido);
			    
	    //Insere lanche X-Burguer com alface para teste da promoção Light
	    Pedido pedidoLigth = new Pedido(new Date());	    
	    PedidoItem itemXburguerLigth = new PedidoItem(pedidoLigth, lancheXburguer, 2);	  
	    itemXburguerLigth.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigth, ingAlface, 1));
	    pedidoLigth.getItens().add(itemXburguerLigth);
	    pedidoService.save(pedidoLigth);
	    
	    //Insere lanche X-Burguer com alface e bacon para teste de não entrar na promoção Light
	    Pedido pedidoLigthBacon = new Pedido(new Date());	    
	    PedidoItem itemXburguerLigthBacon = new PedidoItem(pedidoLigthBacon, lancheXburguer, 1);	  
	    itemXburguerLigthBacon.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigthBacon, ingAlface, 1));
	    itemXburguerLigthBacon.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigthBacon, ingBacon, 1));
	    pedidoLigthBacon.getItens().add(itemXburguerLigthBacon);
	    pedidoService.save(pedidoLigthBacon);

	    //Insere lanche X-egg com quatro carnes para teste da promoção muita carne
	    Pedido pedidoMuitaCarne = new Pedido(new Date());	    
	    PedidoItem itemXeggMuitaCarne = new PedidoItem(pedidoMuitaCarne, lancheXegg, 1);	  
	    itemXeggMuitaCarne.getIngredientes().add(new PedidoItemIngrediente(itemXeggMuitaCarne, ingHamburguer, 4));
	    pedidoMuitaCarne.getItens().add(itemXeggMuitaCarne);
	    pedidoService.save(pedidoMuitaCarne);

	    //Insere lanche X-egg com seis queijo para teste da promoção muito queijo
	    Pedido pedidoMuitoQueijo = new Pedido(new Date());	    
	    PedidoItem itemXeggMuitoQueijo = new PedidoItem(pedidoMuitoQueijo, lancheXegg, 1);	  
	    itemXeggMuitoQueijo.getIngredientes().add(new PedidoItemIngrediente(itemXeggMuitoQueijo, ingQueijo, 6));
	    pedidoMuitoQueijo.getItens().add(itemXeggMuitoQueijo);
	    pedidoService.save(pedidoMuitoQueijo);
	    
	    //Insere lanche X-egg com três carnes e três queijos para teste da promoção muita carne com muito queijo
	    Pedido pedidoMuitaCarneQueijo = new Pedido(new Date());	    
	    PedidoItem itemXeggMuitaCarneQueijo = new PedidoItem(pedidoMuitaCarneQueijo, lancheXegg, 1);	  
	    itemXeggMuitaCarneQueijo.getIngredientes().add(new PedidoItemIngrediente(itemXeggMuitaCarneQueijo, ingHamburguer, 3));
	    itemXeggMuitaCarneQueijo.getIngredientes().add(new PedidoItemIngrediente(itemXeggMuitaCarneQueijo, ingQueijo, 3));
	    pedidoMuitaCarneQueijo.getItens().add(itemXeggMuitaCarneQueijo);
	    pedidoService.save(pedidoMuitaCarneQueijo);
	    
	    //Insere lanche X-Burguer com alface para teste de todas as promoções
	    Pedido pedidoLigthAll = new Pedido(new Date());	    
	    PedidoItem itemXburguerLigthAll = new PedidoItem(pedidoLigthAll, lancheXburguer, 1);	  
	    itemXburguerLigthAll.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigthAll, ingAlface, 10));
	    itemXburguerLigthAll.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigthAll, ingHamburguer, 3));
	    itemXburguerLigthAll.getIngredientes().add(new PedidoItemIngrediente(itemXburguerLigthAll, ingQueijo, 3));
	    pedidoLigthAll.getItens().add(itemXburguerLigthAll);
	    pedidoService.save(pedidoLigthAll);
	}
}
