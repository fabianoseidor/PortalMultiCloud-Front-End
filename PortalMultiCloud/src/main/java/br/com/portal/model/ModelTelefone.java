package br.com.portal.model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable{


	private static final long serialVersionUID = 1L;
	                             // CAMPOS TABELA
	private Long   id_telefone;  // ID_TELEFONE
	private String nome_contato; // NOME_CONTATO 
	private String telefone;     // TELEFONE
	private String email;        // EMAIL
	private Long   id_funcao;    // ID_FUNCAO
	private String nomeFuncao;
	private String obs;
	
	private ModelCliente cliente;   // ID_CLIENTE

	public boolean isNovo() {
    	
    	if(this.id_telefone == null) {
    		return true;
    	}else if( this.id_telefone != null && this.id_telefone > 0) {
    		return false;
    	}
    	return this.id_telefone == null;
    }
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNomeFuncao() {
		return nomeFuncao;
	}

	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}

	public ModelCliente getCliente() {
		return cliente;
	}

	public void setCliente(ModelCliente cliente) {
		this.cliente = cliente;
	}

	public Long getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(Long id_telefone) {
		this.id_telefone = id_telefone;
	}

	public String getNome_contato() {
		return nome_contato;
	}

	public void setNome_contato(String nome_contato) {
		this.nome_contato = nome_contato;
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

	public Long getId_funcao() {
		return id_funcao;
	}

	public void setId_funcao(Long id_funcao) {
		this.id_funcao = id_funcao;
	}




	public int hashCode() {
		return Objects.hash(id_telefone);
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id_telefone, other.id_telefone);
	}

	
	
}
