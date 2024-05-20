package br.com.portal.model;

public class ModelTempoLigado {
	
    private Long   id_tempo_ligado;
    private String dt_criacao;
    private String desc_tempo_ligado;
    private String obs;

    public boolean isNovo() {
    	
    	if(this.id_tempo_ligado == null) {
    		return true;
    	}else if( this.id_tempo_ligado != null && this.id_tempo_ligado > 0) {
    		return false;
    	}
    	
    	return this.id_tempo_ligado == null;
    }

	public Long getId_tempo_ligado() {
		return id_tempo_ligado;
	}

	public void setId_tempo_ligado(Long id_tempo_ligado) {
		this.id_tempo_ligado = id_tempo_ligado;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDesc_tempo_ligado() {
		return desc_tempo_ligado;
	}

	public void setDesc_tempo_ligado(String desc_tempo_ligado) {
		this.desc_tempo_ligado = desc_tempo_ligado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
