package br.com.portal.model;

public class ModelTipoServico {

	private Long   id_tipo_servico;
	private String dt_criacao;
	private String tipo_servico;
	private String obs;
	
	public boolean isNovo() {
    	
    	if(this.id_tipo_servico == null) {
    		return true;
    	}else if( this.id_tipo_servico != null && this.id_tipo_servico > 0 ) {
    		return false;
    	}
    	return this.id_tipo_servico == null;
    }
	
	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getTipo_servico() {
		return tipo_servico;
	}
	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public String toString() {
		return "ModelTipoServico [id_tipo_servico=" + id_tipo_servico + ", dt_criacao=" + dt_criacao + ", tipo_servico="
				+ tipo_servico + ", obs=" + obs + "]";
	}
}
