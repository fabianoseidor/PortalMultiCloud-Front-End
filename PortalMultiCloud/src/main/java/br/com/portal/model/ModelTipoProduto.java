package br.com.portal.model;

public class ModelTipoProduto {
	
	private Long id_tipo_produto;
	private Long id_produto;
	private String dt_criacao;
	private String desc_tipo_produto;
	private String observacao;
	
	public Long getId_tipo_produto() {
		return id_tipo_produto;
	}
	public void setId_tipo_produto(Long id_tipo_produto) {
		this.id_tipo_produto = id_tipo_produto;
	}
	public Long getId_produto() {
		return id_produto;
	}
	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getDesc_tipo_produto() {
		return desc_tipo_produto;
	}
	public void setDesc_tipo_produto(String desc_tipo_produto) {
		this.desc_tipo_produto = desc_tipo_produto;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
}
