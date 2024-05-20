package br.com.portal.model;

public class ModeliSistemaOperacional {
	private Long   id_so;
	private String dt_criacao;
	private String sistema_operacional; 
	private String obs;
	
	public boolean isNovo() {
    	
    	if(this.id_so == null) {
    		return true;
    	}else if( this.id_so != null && this.id_so > 0 ) {
    		return false;
    	}
    	return this.id_so == null;
    }
	
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getSistema_operacional() {
		return sistema_operacional;
	}
	public void setSistema_operacional(String sistema_operacional) {
		this.sistema_operacional = sistema_operacional;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	

	

}
