package br.com.portal.model;

public class ModelStatusRecurso {
	private Long id_status_recurso;
	private String status_recurso;
	private String dt_criacao;
    private String observacao;
    
	public boolean isNovo() {
    	
    	if(this.id_status_recurso == null) {
    		return true;
    	}else if( this.id_status_recurso != null && this.id_status_recurso > 0 ) {
    		return false;
    	}
    	return this.id_status_recurso == null;
    }
    
    
	public Long getId_status_recurso() {
		return id_status_recurso;
	}
	public void setId_status_recurso(Long id_status_recurso) {
		this.id_status_recurso = id_status_recurso;
	}
	public String getStatus_recurso() {
		return status_recurso;
	}
	public void setStatus_recurso(String status_recurso) {
		this.status_recurso = status_recurso;
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


	public String toString() {
		return "ModelStatusRecurso [id_status_recurso=" + id_status_recurso + ", status_recurso=" + status_recurso
				+ ", dt_criacao=" + dt_criacao + ", observacao=" + observacao + "]";
	}
}
