package br.com.portal.model;

public class ModelCicloUpdate {

	private Long   id_ciclo_update;
	private String dt_criacao;
    private String descricao;
    private String observacao;
       
	public boolean isNovo() {
    	
    	if(this.id_ciclo_update == null) {
    		return true;
    	}else if( this.id_ciclo_update != null && this.id_ciclo_update > 0 ) {
    		return false;
    	}
    	return this.id_ciclo_update == null;
    }

	public Long getId_ciclo_update() {
		return id_ciclo_update;
	}

	public void setId_ciclo_update(Long id_ciclo_update) {
		this.id_ciclo_update = id_ciclo_update;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String toString() {
		return "ModelCicloUpdate [id_ciclo_update=" + id_ciclo_update + ", dt_criacao=" + dt_criacao + ", descricao="
				+ descricao + ", observacao=" + observacao + "]";
	}
	
    
    
}
