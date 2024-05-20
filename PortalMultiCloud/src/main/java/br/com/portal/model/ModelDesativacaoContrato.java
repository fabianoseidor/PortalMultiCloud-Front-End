package br.com.portal.model;

public class ModelDesativacaoContrato {

	private String user_desativacao;
	private Long   id_contrato;
	private Long   id_aditivado;
	private Long   id_cliente;
	private Long   id_status;
	
	public String getUser_desativacao() {
		return user_desativacao;
	}
	public void setUser_desativacao(String user_desativacao) {
		this.user_desativacao = user_desativacao;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_aditivado() {
		return id_aditivado;
	}
	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_status() {
		return id_status;
	}
	public void setId_status(Long id_status) {
		this.id_status = id_status;
	}
	
	
}
