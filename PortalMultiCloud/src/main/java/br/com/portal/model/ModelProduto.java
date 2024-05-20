package br.com.portal.model;

public class ModelProduto {
	 
	private Long id_produto;
	private String produto;
	private String valor;
	private String obs;
	private String dt_criacao;

	public boolean isNovo() {
    	
    	if(this.id_produto == null) {
    		return true;
    	}else if( this.id_produto != null && this.id_produto > 0 ) {
    		return false;
    	}
    	return this.id_produto == null;
    }

	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	

	public String toString() {
		return "ModelProduto [id_produto=" + id_produto + ", produto=" + produto + ", valor=" + valor + ", obs=" + obs
				+ "]";
	}

	
}
