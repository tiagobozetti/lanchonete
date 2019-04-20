# FinchBurguer

Projeto para controle de vendas de lanches.

## Características

* Controle de lanches e promoções,
* Desconto progressivo por ingrediente(pague 3 leve 2, pague 5 leve 4),
* Ativação/desativação de desconto para determinados ingredientes,
* Atualização de preço de um ou todos ingredientes informando o percentual(exemplo: 10%),
* Quando houver atualização de preço dos ingredientes os valores dos lanches dos pedidos antigos não são atualizados,
* Registro do valor de desconto quando o lanche possuir promoção,
* As promoções podem ser acumulativas,
* Autenticação através de JWT,
* Documentação das API´s com Swagger,
* Desenvolvido com Spring BOOT 2.1.4.RELEASE
  
## Informações gerais

* Rodando o projeto no profile de test o banco de dados h2 será utilizado. Serão gerados cadastros de lanches, ingredientes, promoções e pedidos.
* No profile de dev e produção o banco de dados PostgreSQL será utilizado. Não haverá pré-cadastro nestes profiles.
* Para iniciar a utilização das apis será necessário primeiramente criar usuário através da URL http://localhost:8080/usuarios, usar HTTP Verb POST. O body deverá conter o seguinte Json: {"usuario" : "string","senha" : "string"}.
* O token de autenticação será retornado no header da chamada do http:localhost:8080/login, o HTTP Verb e Json do body são os mesmo do método de cadastro de usuário.
* O token deverá ser passado no header das chamadas.
* Documentação da API está disponível em http://<ip>:<porta>/swagger-ui.html
* Para utilizar as chamadas direto no swagger desabilitar a autenticação(comentar a linha 62 e descomentar a 61 do com.finch.burguer.config.SecurityConfig). Necessário implementar a autenticação no swagger.

## URL´s dos dados do profile test

- GET http://localhost:8080/pedidos/1
	* Pedido com lanches com ingredientes padrões.
	
- GET http://localhost:8080/pedidos/2
	* Pedido para teste da promoção ligth
	
- GET http://localhost:8080/pedidos/3
	* Pedido para teste da desativação da promoção ligth(lanche possui alface mas possui bacon)

- GET http://localhost:8080/pedidos/4
	* Pedido para teste da promoção muita carne
	
- GET http://localhost:8080/pedidos/5
	* Pedido para teste da promoção muito queijo	
	
- GET http://localhost:8080/pedidos/6
	* Pedido para teste das promoções muita carne e muito queijo
	
- GET http://localhost:8080/pedidos/7
	* Pedido para teste das promoções muita carne, muito queijo e ligth	
