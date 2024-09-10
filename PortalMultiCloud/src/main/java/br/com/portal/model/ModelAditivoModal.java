package br.com.portal.model;

public class ModelAditivoModal {
	// Referencia a tabela Aditivo
	private Long   id_aditivado;              
	private Long   id_status_aditivo;         
	private Long   id_fase_contrato;          
	private Long   id_ciclo_update;           
	private Long   id_tipo_aditivo;           
	private Long   id_contrato;               
	private int    recurso_temporario;        
	private int    adti_sem_receita;          
	private int    aprovacao_adit_sem_receita;
	private String dt_criacao;                
	private String vlr_total_adit;            
	private String observacao; 
	private String login_cadastro;
	private String hubspot_aditivo;
	 private Long   id_rascunho;
	 private String motivoRascunho;
    
	// Referencia a tabela Aditivado Produto
	private String dt_cadastro;    
	private String qty_servico;    
	private String valor_unitario; 
	private String vlr_adt_produto;          
	private Long   id_tipo_protudo;

	// Referencia a tabela Produto
	private Long   id_produto;
	private String qty_produto;   
	private String valor_produto;
	private String vlr_total_produto;
	
	// Referencia a tabela Tipo Produto
	private Long   id_tipo_produto;        
	private String desc_tipo_produto;  

	// Referencia a tabela Vigencia
	private Long   id_vigencia;
	private Long   id_tempo_contrato;
	private String dt_inicio;
	private String dt_final;
	private String dt_criacao_vigencia;
	private String observacao_vigencia;
	
	// Referencia a servico_aditivado
	private Long   id_servico_contratado;      
	private String qty_serv_contratado;           
	private String valor_unit_serv_cont;
	private String valor_serv_contratado; 
	private String desc_serv_contratado; 
	
	// Informacao do tipo de Moeda Contratada para o Atidivo.
    private String valor_convertido;
    private String custo_total;
    private String cotacao_moeda;
    private Long   id_moeda;    

	// Informacoes da comissicao de venda sobre o contrato equivalente a equipe de vendas
	private Boolean comissao_adit;
	private String  valor_setup_adit;
	private String  valor_parcela_setup_adit;
	private int     qty_parcela_setup_adit;
	private int     qty_mese_setup_adit;

	public ModelAditivoModal() {}	
	
    public boolean isNovo() {
    	
    	if(this.id_aditivado == null) {
    		return true;
    	}else if( this.id_aditivado != null && this.id_aditivado > 0) {
    		return false;
    	}
    	
    	return this.id_aditivado == null;
    }
 
	public Boolean getComissao_adit() {
		return comissao_adit;
	}

	public void setComissao_adit(Boolean comissao_adit) {
		this.comissao_adit = comissao_adit;
	}

	public String getValor_setup_adit() {
		return valor_setup_adit;
	}

	public void setValor_setup_adit(String valor_setup_adit) {
		this.valor_setup_adit = valor_setup_adit;
	}

	public String getValor_parcela_setup_adit() {
		return valor_parcela_setup_adit;
	}

	public void setValor_parcela_setup_adit(String valor_parcela_setup_adit) {
		this.valor_parcela_setup_adit = valor_parcela_setup_adit;
	}

	public int getQty_parcela_setup_adit() {
		return qty_parcela_setup_adit;
	}

	public void setQty_parcela_setup_adit(int qty_parcela_setup_adit) {
		this.qty_parcela_setup_adit = qty_parcela_setup_adit;
	}

	public int getQty_mese_setup_adit() {
		return qty_mese_setup_adit;
	}

	public void setQty_mese_setup_adit(int qty_mese_setup_adit) {
		this.qty_mese_setup_adit = qty_mese_setup_adit;
	}

	public Long getId_rascunho() {
		return id_rascunho;
	}

	public void setId_rascunho(Long id_rascunho) {
		this.id_rascunho = id_rascunho;
	}

	public String getMotivoRascunho() {
		return motivoRascunho;
	}

	public void setMotivoRascunho(String motivoRascunho) {
		this.motivoRascunho = motivoRascunho;
	}

	public String getHubspot_aditivo() {
		return hubspot_aditivo;
	}

	public void setHubspot_aditivo(String hubspot_aditivo) {
		this.hubspot_aditivo = hubspot_aditivo;
	}

	public String getLogin_cadastro() {
		return login_cadastro;
	}

	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}

	public String getValor_convertido() {
		return valor_convertido;
	}

	public void setValor_convertido(String valor_convertido) {
		this.valor_convertido = valor_convertido;
	}

	public String getCusto_total() {
		return custo_total;
	}

	public void setCusto_total(String custo_total) {
		this.custo_total = custo_total;
	}

	public String getCotacao_moeda() {
		return cotacao_moeda;
	}

	public void setCotacao_moeda(String cotacao_moeda) {
		this.cotacao_moeda = cotacao_moeda;
	}

	public Long getId_moeda() {
		return id_moeda;
	}

	public void setId_moeda(Long id_moeda) {
		this.id_moeda = id_moeda;
	}

	public String getValor_unit_serv_cont() {
		return valor_unit_serv_cont;
	}

	public void setValor_unit_serv_cont(String valor_unit_serv_cont) {
		this.valor_unit_serv_cont = valor_unit_serv_cont;
	}

	public String getVlr_total_produto() {
		return vlr_total_produto;
	}

	public void setVlr_total_produto(String vlr_total_produto) {
		this.vlr_total_produto = vlr_total_produto;
	}

	public String getValor_produto() {
		return valor_produto;
	}

	public void setValor_produto(String valor_produto) {
		this.valor_produto = valor_produto;
	}

	public String getQty_produto() {
		return qty_produto;
	}

	public void setQty_produto(String qty_produto) {
		this.qty_produto = qty_produto;
	}

	public String getQty_serv_contratado() {
		return qty_serv_contratado;
	}

	public void setQty_serv_contratado(String qty_serv_contratado) {
		this.qty_serv_contratado = qty_serv_contratado;
	}

	public String getValor_serv_contratado() {
		return valor_serv_contratado;
	}

	public void setValor_serv_contratado(String valor_serv_contratado) {
		this.valor_serv_contratado = valor_serv_contratado;
	}

	public Long getId_servico_contratado() {
		return id_servico_contratado;
	}

	public void setId_servico_contratado(Long id_servico_contratado) {
		this.id_servico_contratado = id_servico_contratado;
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

	public String getVlr_total_adit() {
		return vlr_total_adit;
	}

	public void setVlr_total_adit(String vlr_total_adit) {
		this.vlr_total_adit = vlr_total_adit;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public String getQty_servico() {
		return qty_servico;
	}

	public void setQty_servico(String qty_servico) {
		this.qty_servico = qty_servico;
	}

	public String getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(String valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public String getVlr_adt_produto() {
		return vlr_adt_produto;
	}

	public void setVlr_adt_produto(String vlr_adt_produto) {
		this.vlr_adt_produto = vlr_adt_produto;
	}

	public Long getId_tipo_protudo() {
		return id_tipo_protudo;
	}

	public void setId_tipo_protudo(Long id_tipo_protudo) {
		this.id_tipo_protudo = id_tipo_protudo;
	}

	public Long getId_produto() {
		return id_produto;
	}

	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}

	public Long getId_tipo_produto() {
		return id_tipo_produto;
	}

	public void setId_tipo_produto(Long id_tipo_produto) {
		this.id_tipo_produto = id_tipo_produto;
	}

	public String getDesc_tipo_produto() {
		return desc_tipo_produto;
	}

	public void setDesc_tipo_produto(String desc_tipo_produto) {
		this.desc_tipo_produto = desc_tipo_produto;
	}
	

}
