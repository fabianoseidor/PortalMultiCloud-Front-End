package br.com.portal.model;

public class ModelSuporte {
    private Long   id_suporte;
    private String dt_criacao;
    private String desc_suporte;
    private String obs;

    public boolean isNovo() {
    	
    	if(this.id_suporte == null) {
    		return true;
    	}else if( this.id_suporte != null && this.id_suporte > 0) {
    		return false;
    	}
    	
    	return this.id_suporte == null;
    }

	public Long getId_suporte() {
		return id_suporte;
	}

	public void setId_suporte(Long id_suporte) {
		this.id_suporte = id_suporte;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDesc_suporte() {
		return desc_suporte;
	}

	public void setDesc_suporte(String desc_suporte) {
		this.desc_suporte = desc_suporte;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}


    
}
