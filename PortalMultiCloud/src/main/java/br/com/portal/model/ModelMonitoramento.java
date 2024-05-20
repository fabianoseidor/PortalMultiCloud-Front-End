package br.com.portal.model;

public class ModelMonitoramento {
	
    private Long   id_monitoramento;
    private String dt_criacao;
    private String desc_monitoramento;
    private String obs;

    public boolean isNovo() {
    	
    	if(this.id_monitoramento == null) {
    		return true;
    	}else if( this.id_monitoramento != null && this.id_monitoramento > 0) {
    		return false;
    	}
    	
    	return this.id_monitoramento == null;
    }

	public Long getId_monitoramento() {
		return id_monitoramento;
	}

	public void setId_monitoramento(Long id_monitoramento) {
		this.id_monitoramento = id_monitoramento;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDesc_monitoramento() {
		return desc_monitoramento;
	}

	public void setDesc_monitoramento(String desc_monitoramento) {
		this.desc_monitoramento = desc_monitoramento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
