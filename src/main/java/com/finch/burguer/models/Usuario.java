package com.finch.burguer.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.AUTO)
    private Long codigoUsuario;

    @NotEmpty
    @Basic(optional = false)
    private String usuario;

    @Basic(optional = false)
    private String senha;
    
    public Usuario() {
    }

    public Usuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	@Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.codigoUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.codigoUsuario, other.codigoUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoUsuario + " - " + usuario;
    }
}
