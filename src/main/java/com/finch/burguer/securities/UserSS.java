package com.finch.burguer.securities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long codigoUsuario;
	private String usuario;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
	}
	
	public UserSS(Long codigoUsuario, String usuario, String senha) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.usuario = usuario;
		this.senha = senha;
	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
