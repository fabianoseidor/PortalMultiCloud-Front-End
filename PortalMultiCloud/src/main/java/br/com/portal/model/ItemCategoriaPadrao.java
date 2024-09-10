package br.com.portal.model;


public class ItemCategoriaPadrao {
	
	
	private String titulo_atividade_mudanca;
	private String atividade_mudanca; 
	private String dt_tarefa;
	private String duracao;
	private String prioridade;
	private Boolean enviar_email; 
	ResponsavelAtividadeGMUD responsavelAtividade;
	StatusAtividadeGmud statusAtividade;
	
	
	public Boolean getEnviar_email() {
		return enviar_email;
	}
	public void setEnviar_email(Boolean enviar_email) {
		this.enviar_email = enviar_email;
	}
	public StatusAtividadeGmud getStatusAtividade() {
		return statusAtividade;
	}
	public void setStatusAtividade(StatusAtividadeGmud statusAtividade) {
		this.statusAtividade = statusAtividade;
	}
	public String getTitulo_atividade_mudanca() {
		return titulo_atividade_mudanca;
	}
	public void setTitulo_atividade_mudanca(String titulo_atividade_mudanca) {
		this.titulo_atividade_mudanca = titulo_atividade_mudanca;
	}
	public String getAtividade_mudanca() {
		return atividade_mudanca;
	}
	public void setAtividade_mudanca(String atividade_mudanca) {
		this.atividade_mudanca = atividade_mudanca;
	}
	public String getDt_tarefa() {
		return dt_tarefa;
	}
	public void setDt_tarefa(String dt_tarefa) {
		this.dt_tarefa = dt_tarefa;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public ResponsavelAtividadeGMUD getResponsavelAtividade() {
		return responsavelAtividade;
	}
	public void setResponsavelAtividade(ResponsavelAtividadeGMUD responsavelAtividade) {
		this.responsavelAtividade = responsavelAtividade;
	}
	
	
   
}
