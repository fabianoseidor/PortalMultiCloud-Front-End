package br.com.portal.model;

public class ModelServicoContratado {

	private Long id_servico_contratado;
	private String dt_criacao;
	private String desc_servico;
	private String obs;
	
	public boolean isNovo() {
    	
    	if(this.id_servico_contratado == null) {
    		return true;
    	}else if( this.id_servico_contratado != null && this.id_servico_contratado > 0 ) {
    		return false;
    	}
    	return this.id_servico_contratado == null;
    }
	
	
	public Long getId_servico_contratado() {
		return id_servico_contratado;
	}
	public void setId_servico_contratado(Long id_servico_contratado) {
		this.id_servico_contratado = id_servico_contratado;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getDesc_servico() {
		return desc_servico;
	}
	public void setDesc_servico(String desc_servico) {
		this.desc_servico = desc_servico;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public String toString() {
		return "ModelServicoContratado [id_servico_contratado=" + id_servico_contratado + ", dt_criacao=" + dt_criacao
				+ ", desc_servico=" + desc_servico + ", obs=" + obs + "]";
	}
	
	
}
