package com.finch.burguer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finch.burguer.models.Usuario;
import com.finch.burguer.repositories.UsuarioRepository;
import com.finch.burguer.securities.UserSS;
import com.finch.burguer.services.exceptions.RegraNegocioException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usu) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUsuario(usu);
		
		if (usuario == null) {
			throw new RegraNegocioException(usu);
		}
		
		return new UserSS(usuario.getCodigoUsuario(), usuario.getUsuario(), usuario.getSenha());
	}

}
