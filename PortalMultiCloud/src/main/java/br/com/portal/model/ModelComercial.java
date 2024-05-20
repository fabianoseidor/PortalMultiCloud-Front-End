package br.com.portal.model;

import java.util.Objects;

public class ModelComercial {

	private Long   id_comercial;  
	private String nome_comercial;
	private String telefone;
	private String email;
	private String obs;
	private String dt_cadastro;
	
	public boolean isNovo() {
    	
    if(this.id_comercial == null) {
    		return true;
    	}else if( this.id_comercial != null && this.id_comercial > 0 ) {
    		return false;
    	}
    	return this.id_comercial == null;
    }

	
	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public String getDt_cadastro() {
		return dt_cadastro;
	}


	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}


	public Long getId_comercial() {
		return id_comercial;
	}
	public void setId_comercial(Long id_comercial) {
		this.id_comercial = id_comercial;
	}
	public String getNome_comercial() {
		return nome_comercial;
	}
	public void setNome_comercial(String nome_comercial) {
		this.nome_comercial = nome_comercial;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_comercial);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelComercial other = (ModelComercial) obj;
		return Objects.equals(id_comercial, other.id_comercial);
	}

	@Override
	public String toString() {
		return "ModelComercial [id_comercial=" + id_comercial + ", nome_comercial=" + nome_comercial + ", telefone="
				+ telefone + ", email=" + email + "]";
	}

	
}
