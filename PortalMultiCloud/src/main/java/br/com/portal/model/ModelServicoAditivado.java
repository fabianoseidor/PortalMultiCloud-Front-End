package br.com.portal.model;

public class ModelServicoAditivado {
	
	private Long id_servico_contratado;
	private Long id_aditivado;
	private int  quantidade;
	private String desc_serv_contratado;
	private String dt_criacao;
	
	public Long getId_servico_contratado() {
		return id_servico_contratado;
	}
	public void setId_servico_contratado(Long id_servico_contratado) {
		this.id_servico_contratado = id_servico_contratado;
	}
	public Long getId_aditivado() {
		return id_aditivado;
	}
	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDesc_serv_contratado() {
		return desc_serv_contratado;
	}
	public void setDesc_serv_contratado(String desc_serv_contratado) {
		this.desc_serv_contratado = desc_serv_contratado;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	

	
}
