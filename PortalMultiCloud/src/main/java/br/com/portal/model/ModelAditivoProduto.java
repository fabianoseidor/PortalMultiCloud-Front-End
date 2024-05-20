package br.com.portal.model;

public class ModelAditivoProduto {
	private Long    id_aditivo;
	private Long    id_produto;
	private Long    id_tipo_protudo;
	private Long    id_tipo_aditivo;
	private String  produto;
	private String  dt_cadastro;
	private Integer qty_servico;
	private String  valor_unitario;
	private String  valor;

	
	public Long getId_tipo_aditivo() {
		return id_tipo_aditivo;
	}
	public void setId_tipo_aditivo(Long id_tipo_aditivo) {
		this.id_tipo_aditivo = id_tipo_aditivo;
	}
	public Long getId_tipo_protudo() {
		return id_tipo_protudo;
	}
	public void setId_tipo_protudo(Long id_tipo_protudo) {
		this.id_tipo_protudo = id_tipo_protudo;
	}
	public Long getId_aditivo() {
		return id_aditivo;
	}
	public void setId_aditivo(Long id_aditivo) {
		this.id_aditivo = id_aditivo;
	}
	public Long getId_produto() {
		return id_produto;
	}
	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
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
		return "ModelAditivoProduto [id_aditivo=" + id_aditivo + ", id_produto=" + id_produto + ", produto=" + produto
				+ ", dt_cadastro=" + dt_cadastro + ", qty_servico=" + qty_servico + ", valor_unitario=" + valor_unitario
				+ ", valor=" + valor + "]";
	}
	
	
}
