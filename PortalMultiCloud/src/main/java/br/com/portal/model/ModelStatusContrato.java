package br.com.portal.model;

public class ModelStatusContrato {

	private Long   id_status_contrato;
    private String status_contrato;
    private String observacao;
	private String dt_criacao;

	public boolean isNovo() {
    	
    	if(this.id_status_contrato == null) {
    		return true;
    	}else if( this.id_status_contrato != null && this.id_status_contrato > 0 ) {
    		return false;
    	}
    	return this.id_status_contrato == null;
    }
 	public Long getId_status_contrato() {
		return id_status_contrato;
	}
	public void setId_status_contrato(Long id_status_contrato) {
		this.id_status_contrato = id_status_contrato;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String toString() {
		return "ModelStatusContrato [id_status_contrato=" + id_status_contrato + ", status_contrato=" + status_contrato
				+ ", observacao=" + observacao + ", dt_criacao=" + dt_criacao + "]";
	}
}
