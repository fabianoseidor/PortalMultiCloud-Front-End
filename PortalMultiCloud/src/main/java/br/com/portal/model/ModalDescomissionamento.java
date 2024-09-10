package br.com.portal.model;

public class ModalDescomissionamento {

	private Long    id_descomissionamento;
	private Long    id_contrato;
	private Long    id_cliente;
	private String  cliente;
	private String  alias;
	private Long    id_status_contrato;
	private String  status_contrato;
	private String  dt_criacao;
	private String  dt_alteracao;
	private String  motivo_descomissionamento;
	private String  motivo_reversao_descomissionamento;
	private Boolean solicitar_desligamento;
	private String  dt_solicitacao_desligamento;
	private Boolean desligamento_ambiente;
	private String  dt_desligamento_ambiente;
	private String  user_desligamento;
	private String  obsSolictcaoDesligue;

	public String getObsSolictcaoDesligue() {
		return obsSolictcaoDesligue;
	}
	public void setObsSolictcaoDesligue(String obsSolictcaoDesligue) {
		this.obsSolictcaoDesligue = obsSolictcaoDesligue;
	}
	public String getUser_desligamento() {
		return user_desligamento;
	}
	public void setUser_desligamento(String user_desligamento) {
		this.user_desligamento = user_desligamento;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public Long getId_status_contrato() {
		return id_status_contrato;
	}
	public void setId_status_contrato(Long id_status_contrato) {
		this.id_status_contrato = id_status_contrato;
	}
	public Long getId_descomissionamento() {
		return id_descomissionamento;
	}
	public void setId_descomissionamento(Long id_descomissionamento) {
		this.id_descomissionamento = id_descomissionamento;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getDt_alteracao() {
		return dt_alteracao;
	}
	public void setDt_alteracao(String dt_alteracao) {
		this.dt_alteracao = dt_alteracao;
	}
	public String getMotivo_descomissionamento() {
		return motivo_descomissionamento;
	}
	public void setMotivo_descomissionamento(String motivo_descomissionamento) {
		this.motivo_descomissionamento = motivo_descomissionamento;
	}
	public String getMotivo_reversao_descomissionamento() {
		return motivo_reversao_descomissionamento;
	}
	public void setMotivo_reversao_descomissionamento(String motivo_reversao_descomissionamento) {
		this.motivo_reversao_descomissionamento = motivo_reversao_descomissionamento;
	}
	public Boolean getSolicitar_desligamento() {
		return solicitar_desligamento;
	}
	public void setSolicitar_desligamento(Boolean solicitar_desligamento) {
		this.solicitar_desligamento = solicitar_desligamento;
	}
	public String getDt_solicitacao_desligamento() {
		return dt_solicitacao_desligamento;
	}
	public void setDt_solicitacao_desligamento(String dt_solicitacao_desligamento) {
		this.dt_solicitacao_desligamento = dt_solicitacao_desligamento;
	}
	public Boolean getDesligamento_ambiente() {
		return desligamento_ambiente;
	}
	public void setDesligamento_ambiente(Boolean desligamento_ambiente) {
		this.desligamento_ambiente = desligamento_ambiente;
	}
	public String getDt_desligamento_ambiente() {
		return dt_desligamento_ambiente;
	}
	public void setDt_desligamento_ambiente(String dt_desligamento_ambiente) {
		this.dt_desligamento_ambiente = dt_desligamento_ambiente;
	}

	
}
