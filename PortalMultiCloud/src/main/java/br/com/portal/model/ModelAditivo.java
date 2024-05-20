package br.com.portal.model;

public class ModelAditivo {
    private Long   id_aditivado;
    private Long   id_status_aditivo;
    private Long   id_fase_contrato;
    private Long   id_ciclo_update;
    private Long   id_tipo_aditivo;
    private Long   id_contrato;
    private String desc_serv_contratado;
    private int    recurso_temporario;
    private int    adti_sem_receita;
    private int    aprovacao_adit_sem_receita;
    private String dt_criacao;
    private String qty_usuario_contratada;
    private String vlr_toral_adit;
    private String observacao;

    private Long   id_cliente;
    private String nomeCliente;

	private Long   id_vigencia;
	private Long   id_tempo_contrato;
	private String dt_inicio;
	private String dt_final;
	private String dt_criacao_vigencia;
	private String observacao_vigencia;
	// Aditivo --> Incremento de Servico
	private Long   idServContratado;
    private String qty_servicos;
    // Aditivo --> Incremento de Produto
	private Long   id_Produto;
    private String qty_Produto;    
	private Long   id_tipo_produto;
    private String vlr_Produto_unitario;
	// Aditivo --> Contratação Recurso ( QAS )
	private Long   id_monitoramento;
	private String desc_id_monitoramento;
	      
	private Long   id_suporte;
	private String desc_suporte;
	      
	private Long   id_tempo_ligado;
	private String desc_tempo_ligado;
	
    public boolean isNovo() {
    	
    	if(this.id_aditivado == null) {
    		return true;
    	}else if( this.id_aditivado != null && this.id_aditivado > 0) {
    		return false;
    	}
    	
    	return this.id_aditivado == null;
    }

	public String getVlr_Produto_unitario() {
		return vlr_Produto_unitario;
	}

	public void setVlr_Produto_unitario(String vlr_Produto_unitario) {
		this.vlr_Produto_unitario = vlr_Produto_unitario;
	}

	public Long getId_monitoramento() {
		return id_monitoramento;
	}

	public void setId_monitoramento(Long id_monitoramento) {
		this.id_monitoramento = id_monitoramento;
	}

	public String getDesc_id_monitoramento() {
		return desc_id_monitoramento;
	}

	public void setDesc_id_monitoramento(String desc_id_monitoramento) {
		this.desc_id_monitoramento = desc_id_monitoramento;
	}

	public Long getId_suporte() {
		return id_suporte;
	}

	public void setId_suporte(Long id_suporte) {
		this.id_suporte = id_suporte;
	}

	public String getDesc_suporte() {
		return desc_suporte;
	}

	public void setDesc_suporte(String desc_suporte) {
		this.desc_suporte = desc_suporte;
	}

	public Long getId_tempo_ligado() {
		return id_tempo_ligado;
	}

	public void setId_tempo_ligado(Long id_tempo_ligado) {
		this.id_tempo_ligado = id_tempo_ligado;
	}

	public String getDesc_tempo_ligado() {
		return desc_tempo_ligado;
	}

	public void setDesc_tempo_ligado(String desc_tempo_ligado) {
		this.desc_tempo_ligado = desc_tempo_ligado;
	}

	public Long getId_Produto() {
		return id_Produto;
	}

	public void setId_Produto(Long id_Produto) {
		this.id_Produto = id_Produto;
	}

	public String getQty_Produto() {
		return qty_Produto;
	}

	public void setQty_Produto(String qty_Produto) {
		this.qty_Produto = qty_Produto;
	}

	public Long getId_tipo_produto() {
		return id_tipo_produto;
	}

	public void setId_tipo_produto(Long id_tipo_produto) {
		this.id_tipo_produto = id_tipo_produto;
	}

	public String getQty_servicos() {
		return qty_servicos;
	}

	public void setQty_servicos(String qty_servicos) {
		this.qty_servicos = qty_servicos;
	}

	public Long getIdServContratado() {
		return idServContratado;
	}

	public void setIdServContratado(Long idServContratado) {
		this.idServContratado = idServContratado;
	}

	public Long getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
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
	public Long getId_fase_contrato() {
		return id_fase_contrato;
	}
	public void setId_fase_contrato(Long id_fase_contrato) {
		this.id_fase_contrato = id_fase_contrato;
	}
	public Long getId_ciclo_update() {
		return id_ciclo_update;
	}
	public void setId_ciclo_update(Long id_ciclo_update) {
		this.id_ciclo_update = id_ciclo_update;
	}
	public Long getId_tipo_aditivo() {
		return id_tipo_aditivo;
	}
	public void setId_tipo_aditivo(Long id_tipo_aditivo) {
		this.id_tipo_aditivo = id_tipo_aditivo;
	}
	public String getDesc_serv_contratado() {
		return desc_serv_contratado;
	}
	public void setDesc_serv_contratado(String desc_serv_contratado) {
		this.desc_serv_contratado = desc_serv_contratado;
	}
	public int getRecurso_temporario() {
		return recurso_temporario;
	}
	public void setRecurso_temporario(int recurso_temporario) {
		this.recurso_temporario = recurso_temporario;
	}
	public int getAdti_sem_receita() {
		return adti_sem_receita;
	}
	public void setAdti_sem_receita(int adti_sem_receita) {
		this.adti_sem_receita = adti_sem_receita;
	}
	public int getAprovacao_adit_sem_receita() {
		return aprovacao_adit_sem_receita;
	}
	public void setAprovacao_adit_sem_receita(int aprovacao_adit_sem_receita) {
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
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String toString() {
		return "ModelAditivo [id_aditivado=" + id_aditivado + ", id_status_aditivo=" + id_status_aditivo
				+ ", id_fase_contrato=" + id_fase_contrato + ", id_ciclo_update=" + id_ciclo_update
				+ ", id_tipo_aditivo=" + id_tipo_aditivo + ", desc_serv_contratado=" + desc_serv_contratado
				+ ", recurso_temporario=" + recurso_temporario + ", adti_sem_receita=" + adti_sem_receita
				+ ", aprovacao_adit_sem_receita=" + aprovacao_adit_sem_receita + ", dt_criacao=" + dt_criacao
				+ ", qty_usuario_contratada=" + qty_usuario_contratada + ", vlr_toral_adit=" + vlr_toral_adit
				+ ", observacao=" + observacao + ", id_cliente=" + id_cliente + ", nomeCliente=" + nomeCliente
				+ ", id_vigencia=" + id_vigencia + ", id_tempo_contrato=" + id_tempo_contrato + ", dt_inicio="
				+ dt_inicio + ", dt_final=" + dt_final + ", dt_criacao_vigencia=" + dt_criacao_vigencia
				+ ", observacao_vigencia=" + observacao_vigencia + "]";
	}

	
	
	
}
