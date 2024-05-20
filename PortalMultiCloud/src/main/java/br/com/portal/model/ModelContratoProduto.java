package br.com.portal.model;

public class ModelContratoProduto {
	
	private Long id_contrato;
	private Long id_produto;
	private String produto;
	private String dt_cadastro;
	private Integer qty_servico;
	private String valor_unitario;
	private String valor;
	private String observacao;

	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_produto() {
		return id_produto;
	}
	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
	public Integer getQty_servico() {
		return qty_servico;
	}
	public void setQty_servico(Integer qty_servico) {
		this.qty_servico = qty_servico;
	}
	public String getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(String valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String toString() {
		return "ModelContratoProduto [id_contrato=" + id_contrato + ", id_produto=" + id_produto + ", produto="
				+ produto + ", dt_cadastro=" + dt_cadastro + ", qty_servico=" + qty_servico + ", valor_unitario="
				+ valor_unitario + ", valor=" + valor + "]";
	}
}
