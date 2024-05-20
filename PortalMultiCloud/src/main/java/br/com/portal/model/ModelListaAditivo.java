package br.com.portal.model;

public class ModelListaAditivo {

	private Long   id_aditivado;
	private Long   id_status_aditivo;
	private String status_contrato;
	private Long   id_fase_contrato;
	private String fase_contrato;
	private Long   id_ciclo_update;
	private String descricao;
	private Long   id_tipo_aditivo;
	private String desc_tipo_ditivo;
	private Long   id_contrato;
	private String desc_serv_contratado;
	private String recurso_temporario;
	private String adti_sem_receita;
	private String aprovacao_adit_sem_receita;
	private String dt_criacao;
	private String qty_usuario_contratada;
	private String vlr_toral_adit;
	private String observacao;

	private Long   id_vigencia;
	private Long   id_tempo_contrato;
	private String dt_inicio;
	private String dt_final;
	private String dt_criacao_vigencia;
	private String observacao_vigencia;

	private Long   idServContratado;
	private Long   id_Produto;
	
	
	
	public Long getIdServContratado() {
		return idServContratado;
	}
	public void setIdServContratado(Long idServContratado) {
		this.idServContratado = idServContratado;
	}
	public Long getId_Produto() {
		return id_Produto;
	}
	public void setId_Produto(Long id_Produto) {
		this.id_Produto = id_Produto;
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
	public String getDt_criacao_vigencia() {
		return dt_criacao_vigencia;
	}
	public void setDt_criacao_vigencia(String dt_criacao_vigencia) {
		this.dt_criacao_vigencia = dt_criacao_vigencia;
	}
	public String getObservacao_vigencia() {
		return observacao_vigencia;
	}
	public void setObservacao_vigencia(String observacao_vigencia) {
		this.observacao_vigencia = observacao_vigencia;
	}
	public Long getId_aditivado() {
		return id_aditivado;
	}
	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}
	public Long getId_status_aditivo() {
		return id_status_aditivo;
	}
	public void setId_status_aditivo(Long id_status_aditivo) {
		this.id_status_aditivo = id_status_aditivo;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public Long getId_fase_contrato() {
		return id_fase_contrato;
	}
	public void setId_fase_contrato(Long id_fase_contrato) {
		this.id_fase_contrato = id_fase_contrato;
	}
	public String getFase_contrato() {
		return fase_contrato;
	}
	public void setFase_contrato(String fase_contrato) {
		this.fase_contrato = fase_contrato;
	}
	public Long getId_ciclo_update() {
		return id_ciclo_update;
	}
	public void setId_ciclo_update(Long id_ciclo_update) {
		this.id_ciclo_update = id_ciclo_update;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getId_tipo_aditivo() {
		return id_tipo_aditivo;
	}
	public void setId_tipo_aditivo(Long id_tipo_aditivo) {
		this.id_tipo_aditivo = id_tipo_aditivo;
	}
	public String getDesc_tipo_ditivo() {
		return desc_tipo_ditivo;
	}
	public void setDesc_tipo_ditivo(String desc_tipo_ditivo) {
		this.desc_tipo_ditivo = desc_tipo_ditivo;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getDesc_serv_contratado() {
		return desc_serv_contratado;
	}
	public void setDesc_serv_contratado(String desc_serv_contratado) {
		this.desc_serv_contratado = desc_serv_contratado;
	}
	public String getRecurso_temporario() {
		return recurso_temporario;
	}
	public void setRecurso_temporario(String recurso_temporario) {
		this.recurso_temporario = recurso_temporario;
	}
	public String getAdti_sem_receita() {
		return adti_sem_receita;
	}
	public void setAdti_sem_receita(String adti_sem_receita) {
		this.adti_sem_receita = adti_sem_receita;
	}
	public String getAprovacao_adit_sem_receita() {
		return aprovacao_adit_sem_receita;
	}
	public void setAprovacao_adit_sem_receita(String aprovacao_adit_sem_receita) {
		this.aprovacao_adit_sem_receita = aprovacao_adit_sem_receita;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getQty_usuario_contratada() {
		return qty_usuario_contratada;
	}
	public void setQty_usuario_contratada(String qty_usuario_contratada) {
		this.qty_usuario_contratada = qty_usuario_contratada;
	}
	public String getVlr_toral_adit() {
		return vlr_toral_adit;
	}
	public void setVlr_toral_adit(String vlr_toral_adit) {
		this.vlr_toral_adit = vlr_toral_adit;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String toString() {
		return "ModelListaAditivo [id_aditivado=" + id_aditivado + ", id_status_aditivo=" + id_status_aditivo
				+ ", status_contrato=" + status_contrato + ", id_fase_contrato=" + id_fase_contrato + ", fase_contrato="
				+ fase_contrato + ", id_ciclo_update=" + id_ciclo_update + ", descricao=" + descricao
				+ ", id_tipo_aditivo=" + id_tipo_aditivo + ", desc_tipo_ditivo=" + desc_tipo_ditivo + ", id_contrato="
				+ id_contrato + ", desc_serv_contratado=" + desc_serv_contratado + ", recurso_temporario="
				+ recurso_temporario + ", adti_sem_receita=" + adti_sem_receita + ", aprovacao_adit_sem_receita="
				+ aprovacao_adit_sem_receita + ", dt_criacao=" + dt_criacao + ", qty_usuario_contratada="
				+ qty_usuario_contratada + ", vlr_toral_adit=" + vlr_toral_adit + ", observacao=" + observacao
				+ ", id_vigencia=" + id_vigencia + ", id_tempo_contrato=" + id_tempo_contrato + ", dt_inicio="
				+ dt_inicio + ", dt_final=" + dt_final + ", dt_criacao_vigencia=" + dt_criacao_vigencia
				+ ", observacao_vigencia=" + observacao_vigencia + "]";
	}
	
}
