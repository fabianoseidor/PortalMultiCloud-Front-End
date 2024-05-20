package br.com.portal.model;

public class ModelTipoRascunho {
     
	private Long   id_tipo_rascunho;
	private String rascunho;
	private String dt_criacao;
	private String obs;

	public boolean isNovo() {
    	
	    if(this.id_tipo_rascunho == null) {
	    		return true;
	    	}else if( this.id_tipo_rascunho != null && this.id_tipo_rascunho > 0 ) {
	    		return false;
	    	}
	    	return this.id_tipo_rascunho == null;
	}

	public Long getId_tipo_rascunho() {
		return id_tipo_rascunho;
	}
	public void setId_tipo_rascunho(Long id_tipo_rascunho) {
		this.id_tipo_rascunho = id_tipo_rascunho;
	}
	public String getRascunho() {
		return rascunho;
	}
	public void setRascunho(String rascunho) {
		this.rascunho = rascunho;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	} 
	
	
	
	
}
