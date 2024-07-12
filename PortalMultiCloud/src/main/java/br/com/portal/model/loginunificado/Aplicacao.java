package br.com.portal.model.loginunificado;

import java.io.Serializable;

public class Aplicacao implements Serializable{

	private static final long serialVersionUID = -7771435642889660801L; 

	private Long id_aplicacao;
	private String aplicacao;
	private String url;
	private String departamento;
	private String desenvolvedor;
    private String dt_criacao;
	private String descricao;

	public Long getId_aplicacao() {
		return id_aplicacao;
	}

	public void setId_aplicacao(Long id_aplicacao) {
		this.id_aplicacao = id_aplicacao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
