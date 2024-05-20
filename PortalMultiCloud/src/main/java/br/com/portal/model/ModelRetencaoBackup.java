package br.com.portal.model;

public class ModelRetencaoBackup {
  
	private Long id_retencao_backup;
	private String retencao_backup;
	private String dt_criacao;
    private String observacao;
    
	public boolean isNovo() {
    	
    	if(this.id_retencao_backup == null) {
    		return true;
    	}else if( this.id_retencao_backup != null && this.id_retencao_backup > 0 ) {
    		return false;
    	}
    	return this.id_retencao_backup == null;
    }

	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}

	public void setId_retencao_backup(Long id_retencao_backup) {
		this.id_retencao_backup = id_retencao_backup;
	}

	public String getRetencao_backup() {
		return retencao_backup;
	}

	public void setRetencao_backup(String retencao_backup) {
		this.retencao_backup = retencao_backup;
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
		return "ModelRetencaoBackup [id_retencao_backup=" + id_retencao_backup + ", retencao_backup=" + retencao_backup
				+ ", dt_criacao=" + dt_criacao + ", observacao=" + observacao + "]";
	}
 
	

}
