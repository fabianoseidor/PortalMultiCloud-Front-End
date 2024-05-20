package br.com.portal.model;

public class ModelSite {
	private Long  id_site;
	private String  nome;
	private Long id_nuvem;
	private int selecionado;
	private String  dt_criacao;
	private String  observacao;
		
	public boolean isNovo() {
    	
    	if(this.id_site == null) {
    		return true;
    	}else if( this.id_site != null && this.id_site > 0 ) {
    		return false;
    	}
    	return this.id_site == null;
    }
		
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Long getId_site() {
		return id_site;
	}
	public void setId_site(Long id_site) {
		this.id_site = id_site;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public int getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(int selecionado) {
		this.selecionado = selecionado;
	}
	
	
}
