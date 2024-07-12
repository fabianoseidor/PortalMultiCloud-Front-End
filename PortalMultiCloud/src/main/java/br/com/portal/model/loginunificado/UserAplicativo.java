package br.com.portal.model.loginunificado;

import java.io.Serializable;



public class UserAplicativo implements Serializable{

	private static final long serialVersionUID = -3136664057017268896L;

	private Long id_user_aplicativo;
	private Boolean admin = Boolean.FALSE;
	private Boolean aprovador = Boolean.FALSE;;
	private Boolean executor = Boolean.FALSE;;
	private Boolean owner = Boolean.FALSE;;
    private String dt_criacao;
	private Long users;
	private Aplicacao aplicacao;
	

	public Long getUsers() {
		return users;
	}

	public void setUsers(Long users) {
		this.users = users;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Long getId_user_aplicativo() {
		return id_user_aplicativo;
	}

	public void setId_user_aplicativo(Long id_user_aplicativo) {
		this.id_user_aplicativo = id_user_aplicativo;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getAprovador() {
		return aprovador;
	}

	public void setAprovador(Boolean aprovador) {
		this.aprovador = aprovador;
	}

	public Boolean getExecutor() {
		return executor;
	}

	public void setExecutor(Boolean executor) {
		this.executor = executor;
	}

	public Boolean getOwner() {
		return owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
}
