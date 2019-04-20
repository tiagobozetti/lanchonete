package com.finch.burguer.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finch.burguer.models.Usuario;
import com.finch.burguer.repositories.UsuarioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Usuário")
@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder bCryp;
	
    @PostMapping
    @ApiOperation(value = "Criar novo usuário")
    public ResponseEntity<Void> create(@RequestBody @Valid Usuario usuario) {
    	
    	usuario.setSenha(bCryp.encode(usuario.getSenha()));
    	usuarioRepository.save(usuario);
    	
    	return ResponseEntity.ok().build();
    }
}
