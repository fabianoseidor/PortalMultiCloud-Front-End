package br.com.portal.model;

public class ModelTipoAditivo {

	private Long id_tipo_aditivo;
	private String desc_tipo_ditivo;
	private String dt_criacao;
    private String observacao;
    
	public boolean isNovo() {
    	
    	if(this.id_tipo_aditivo == null) {
    		return true;
    	}else if( this.id_tipo_aditivo != null && this.id_tipo_aditivo > 0 ) {
    		return false;
    	}
    	return this.id_tipo_aditivo == null;
    }

	public Long getId_tipo_aditivo() {
		return id_tipo_aditivo;
	}
	public void setId_tipo_aditivo(Long id_tipo_aditivo) {
		this.id_tipo_aditivo = id_tipo_aditivo;
	}
	public String getDesc_tipo_ditivo() {
		return desc_tipo_ditivo;
	}
	public void setDesc_tipo_ditivo(String desc_tipo_ditivo) {
		this.desc_tipo_ditivo = desc_tipo_ditivo;
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
		return "ModelTipoAditivo [id_tipo_aditivo=" + id_tipo_aditivo + ", desc_tipo_ditivo=" + desc_tipo_ditivo
				+ ", dt_criacao=" + dt_criacao + ", observacao=" + observacao + "]";
	}	
}
