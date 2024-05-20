package br.com.portal.model;

public class ModelVigenciaAditivo {
		
		private Long   id_vigencia;
		private Long   id_aditivado;
		private Long   id_tempo_contrato;
		private String dt_inicio;
		private String dt_final;
		private String dt_criacao;
		private String dt_desativacao;
		private String observacao;

	    public boolean isNovo() {
	    	
	    	if(this.id_vigencia == null) {
	    		return true;
	    	}else if( this.id_vigencia != null && this.id_vigencia > 0) {
	    		return false;
	    	}
	    	
	    	return this.id_vigencia == null;
	    }

		public Long getId_aditivado() {
			return id_aditivado;
		}
		public void setId_aditivado(Long id_aditivado) {
			this.id_aditivado = id_aditivado;
		}
		public String getDt_desativacao() {
			return dt_desativacao;
		}
		public void setDt_desativacao(String dt_desativacao) {
			this.dt_desativacao = dt_desativacao;
		}
		public Long getId_vigencia() {
			return id_vigencia;
		}
		public void setId_vigencia(Long id_vigencia) {
			this.id_vigencia = id_vigencia;
		}
		public Long getId_tempo_contrato() {
			return id_tempo_contrato;
		}
		public void setId_tempo_contrato(Long id_tempo_contrato) {
			this.id_tempo_contrato = id_tempo_contrato;
		}
		public String getDt_inicio() {
			return dt_inicio;
		}
		public void setDt_inicio(String dt_inicio) {
			this.dt_inicio = dt_inicio;
		}
		public String getDt_final() {
			return dt_final;
		}
		public void setDt_final(String dt_final) {
			this.dt_final = dt_final;
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
			return "ModelVigenciaContrato [id_vigencia=" + id_vigencia + ", id_aditivado=" + id_aditivado
					+ ", id_tempo_contrato=" + id_tempo_contrato + ", dt_inicio=" + dt_inicio + ", dt_final=" + dt_final
					+ ", dt_criacao=" + dt_criacao + ", dt_desativacao=" + dt_desativacao + ", observacao=" + observacao
					+ "]";
		}
	
}
