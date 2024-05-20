package br.com.portal.model;

public class ModelStatusCliente {

	private Long id_status;
	private String status;
    private String observacao;
	private String dt_criacao;

	public boolean isNovo() {
    	
    	if(this.id_status == null) {
    		return true;
    	}else if( this.id_status != null && this.id_status > 0 ) {
    		return false;
    	}
    	return this.id_status == null;
    }

	public Long getId_status() {
		return id_status;
	}

	public void setId_status(Long id_status) {
		this.id_status = id_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	
	
}
