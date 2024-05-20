package br.com.portal.model;

public class ModelAmbiente {
	private Long   id_ambiente; 
	private String ambiente;
	private String dt_criacao;
	private String obs;
	
	public boolean isNovo() {
    	
    	if(this.id_ambiente == null) {
    		return true;
    	}else if( this.id_ambiente != null && this.id_ambiente > 0 ) {
    		return false;
    	}
    	
    	return this.id_ambiente == null;
    }

	public Long getId_ambiente() {
		return id_ambiente;
	}

	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
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


	public String toString() {
		return "ModelAmbiente [id_ambiente=" + id_ambiente + ", ambiente=" + ambiente + ", dt_criacao=" + dt_criacao
				+ ", obs=" + obs + "]";
	}	
	
	

}
