package br.com.portal.model;

public class ModelFaseContrato {
    private Long   id_fase_contrato;
    private String fase_contrato;
	private String dt_criacao;
    private String observacao;
    
	public boolean isNovo() {
    	
    	if(this.id_fase_contrato == null) {
    		return true;
    	}else if( this.id_fase_contrato != null && this.id_fase_contrato > 0 ) {
    		return false;
    	}
    	return this.id_fase_contrato == null;
    }
   
	public Long getId_fase_contrato() {
		return id_fase_contrato;
	}
	public void setId_fase_contrato(Long id_fase_contrato) {
		this.id_fase_contrato = id_fase_contrato;
	}
	public String getFase_contrato() {
		return fase_contrato;
	}
	public void setFase_contrato(String fase_contrato) {
		this.fase_contrato = fase_contrato;
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
		return "ModelFaseContrato [id_fase_contrato=" + id_fase_contrato + ", fase_contrato=" + fase_contrato
				+ ", dt_criacao=" + dt_criacao + ", observacao=" + observacao + "]";
	}
    
}
