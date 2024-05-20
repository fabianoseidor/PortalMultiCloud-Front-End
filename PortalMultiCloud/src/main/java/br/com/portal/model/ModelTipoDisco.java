package br.com.portal.model;

public class ModelTipoDisco {
	
	private Long   id_tipo_disco;
	private String tipo_disco;
	private String dt_criacao;
	private String obs;

	public boolean isNovo() {
    	
    	if(this.id_tipo_disco == null) {
    		return true;
    	}else if( this.id_tipo_disco != null && this.id_tipo_disco > 0 ) {
    		return false;
    	}
    	
    	return this.id_tipo_disco == null;
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
	public long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public String getTipo_disco() {
		return tipo_disco;
	}
	public void setTipo_disco(String tipo_disco) {
		this.tipo_disco = tipo_disco;
	}

	public String toString() {
		return "ModelTipoDisco [id_tipo_disco=" + id_tipo_disco + ", tipo_disco=" + tipo_disco + ", dt_criacao="
				+ dt_criacao + ", obs=" + obs + "]";
	}
	
	

}
