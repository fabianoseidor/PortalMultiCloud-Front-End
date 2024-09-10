package br.com.portal.model.dashboard;

public class ModalProdutosContrato {
	
	private String id_contrato;
	private String id_produto; 
	private String produto;  
	private String tipo_contrato;  
	private String qty_servico; 
	private String valor_unitario;
	private String valor;
	public String getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getId_produto() {
		return id_produto;
	}
	public void setId_produto(String id_produto) {
		this.id_produto = id_produto;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getTipo_contrato() {
		return tipo_contrato;
	}
	public void setTipo_contrato(String tipo_contrato) {
		this.tipo_contrato = tipo_contrato;
	}
	public String getQty_servico() {
		return qty_servico;
	}
	public void setQty_servico(String qty_servico) {
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

	
}
