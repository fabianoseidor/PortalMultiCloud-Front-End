package br.com.portal.model;

public class ModelPorteCliente {
	private Long id_porte;
	private String porte_empresa;
	private String dt_criacao;
    private String observacao;
    
	public boolean isNovo() {
    	
    	if(this.id_porte == null) {
    		return true;
    	}else if( this.id_porte != null && this.id_porte > 0 ) {
    		return false;
    	}
    	return this.id_porte == null;
    }

	public Long getId_porte() {
		return id_porte;
	}

	public void setId_porte(Long id_porte) {
		this.id_porte = id_porte;
	}

	public String getPorte_empresa() {
		return porte_empresa;
	}

	public void setPorte_empresa(String porte_empresa) {
		this.porte_empresa = porte_empresa;
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
		return "ModelPorteCliente [id_porte=" + id_porte + ", porte_empresa=" + porte_empresa + ", dt_criacao="
				+ dt_criacao + ", observacao=" + observacao + "]";
	}

	
}
