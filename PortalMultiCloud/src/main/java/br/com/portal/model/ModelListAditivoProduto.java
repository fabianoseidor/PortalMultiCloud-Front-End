package br.com.portal.model;

public class ModelListAditivoProduto {

	 private Long   id_aditivado;
	 private String dt_criacao;    
	 private Long   id_status_aditivo;
	 private String dsc_status_aditivo;
	 private Long   id_contrato;
	 private String vlr_total_adit;
	 private Long   id_tipo_aditivo;
	 private String nome_tipo_aditivo_contratado;
	 private Long   id_produto_contratado;
	 private String nome_prod_contratado;
	 private String vlr_prod_cad_contratado;
	 private String qty_produto_contratado;
	 private String valor_unitario_contratado;
	 private String vlr_produto_contratado;
	 private String desc_serv_prod_contratado;
	 private String data_produto_contratado;
	 private Long   id_tipo_protudo_contratado;
	 private String desc_tipo_produto_contratado;
     private String valor_convertido;
     private String custo_total;
     private String cotacao_moeda;
     private Long   id_moeda;    
	 private String observacao;
	 private String login_cadastro;
	 private String hubspot_aditivo;
	 private Long   id_rascunho;
	 private String motivoRascunho;
	 
	 // Referencia a tabela Vigencia
	 private Long   id_vigencia;
	 private Long   id_aditivo_recurso;
	 private Long   id_tempo_contrato;
	 private String dt_inicio;                    // dtInicioMAR
	 private String dt_final;                     // dtFinalMAR
	 private String dt_criacao_vigencia;         
	 private String observacao_vigencia;

	// Informacoes da comissicao de venda sobre o contrato equivalente a equipe de vendas
	private Boolean comissao_adit;
	private String  valor_setup_adit;
	private String  valor_parcela_setup_adit;
	private int     qty_parcela_setup_adit;
	private int     qty_mese_setup_adit;


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
	public Long getId_vigencia() {
		return id_vigencia;
	}
	public void setId_vigencia(Long id_vigencia) {
		this.id_vigencia = id_vigencia;
	}
	public Long getId_aditivo_recurso() {
		return id_aditivo_recurso;
	}
	public void setId_aditivo_recurso(Long id_aditivo_recurso) {
		this.id_aditivo_recurso = id_aditivo_recurso;
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
	public String getDsc_status_aditivo() {
		return dsc_status_aditivo;
	}
	public void setDsc_status_aditivo(String dsc_status_aditivo) {
		this.dsc_status_aditivo = dsc_status_aditivo;
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
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDesc_serv_prod_contratado() {
		return desc_serv_prod_contratado;
	}
	public void setDesc_serv_prod_contratado(String desc_serv_prod_contratado) {
		this.desc_serv_prod_contratado = desc_serv_prod_contratado;
	}
	public String getNome_tipo_aditivo_contratado() {
		return nome_tipo_aditivo_contratado;
	}
	public void setNome_tipo_aditivo_contratado(String nome_tipo_aditivo_contratado) {
		this.nome_tipo_aditivo_contratado = nome_tipo_aditivo_contratado;
	}
	public Long getId_aditivado() {
		return id_aditivado;
	}
	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public Long getId_status_aditivo() {
		return id_status_aditivo;
	}
	public void setId_status_aditivo(Long id_status_aditivo) {
		this.id_status_aditivo = id_status_aditivo;
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
	public String getVlr_total_adit() {
		return vlr_total_adit;
	}
	public void setVlr_total_adit(String vlr_total_adit) {
		this.vlr_total_adit = vlr_total_adit;
	}
	public Long getId_produto_contratado() {
		return id_produto_contratado;
	}
	public void setId_produto_contratado(Long id_produto_contratado) {
		this.id_produto_contratado = id_produto_contratado;
	}
	public String getNome_prod_contratado() {
		return nome_prod_contratado;
	}
	public void setNome_prod_contratado(String nome_prod_contratado) {
		this.nome_prod_contratado = nome_prod_contratado;
	}
	public String getVlr_prod_cad_contratado() {
		return vlr_prod_cad_contratado;
	}
	public void setVlr_prod_cad_contratado(String vlr_prod_cad_contratado) {
		this.vlr_prod_cad_contratado = vlr_prod_cad_contratado;
	}
	public String getQty_produto_contratado() {
		return qty_produto_contratado;
	}
	public void setQty_produto_contratado(String qty_produto_contratado) {
		this.qty_produto_contratado = qty_produto_contratado;
	}
	public String getValor_unitario_contratado() {
		return valor_unitario_contratado;
	}
	public void setValor_unitario_contratado(String valor_unitario_contratado) {
		this.valor_unitario_contratado = valor_unitario_contratado;
	}
	public String getVlr_produto_contratado() {
		return vlr_produto_contratado;
	}
	public void setVlr_produto_contratado(String vlr_produto_contratado) {
		this.vlr_produto_contratado = vlr_produto_contratado;
	}
	public String getData_produto_contratado() {
		return data_produto_contratado;
	}
	public void setData_produto_contratado(String data_produto_contratado) {
		this.data_produto_contratado = data_produto_contratado;
	}
	public Long getId_tipo_protudo_contratado() {
		return id_tipo_protudo_contratado;
	}
	public void setId_tipo_protudo_contratado(Long id_tipo_protudo_contratado) {
		this.id_tipo_protudo_contratado = id_tipo_protudo_contratado;
	}
	public String getDesc_tipo_produto_contratado() {
		return desc_tipo_produto_contratado;
	}
	public void setDesc_tipo_produto_contratado(String desc_tipo_produto_contratado) {
		this.desc_tipo_produto_contratado = desc_tipo_produto_contratado;
	}


	public String toString() {
		return "ModelListAditivoProduto [id_aditivado=" + id_aditivado + ", dt_criacao=" + dt_criacao
				+ ", id_status_aditivo=" + id_status_aditivo + ", id_contrato=" + id_contrato + ", vlr_total_adit="
				+ vlr_total_adit + ", id_tipo_aditivo=" + id_tipo_aditivo + ", nome_tipo_aditivo_contratado="
				+ nome_tipo_aditivo_contratado + ", id_produto_contratado=" + id_produto_contratado
				+ ", nome_prod_contratado=" + nome_prod_contratado + ", vlr_prod_cad_contratado="
				+ vlr_prod_cad_contratado + ", qty_produto_contratado=" + qty_produto_contratado
				+ ", valor_unitario_contratado=" + valor_unitario_contratado + ", vlr_produto_contratado="
				+ vlr_produto_contratado + ", data_produto_contratado=" + data_produto_contratado
				+ ", id_tipo_protudo_contratado=" + id_tipo_protudo_contratado + ", desc_tipo_produto_contratado="
				+ desc_tipo_produto_contratado + "]";
	}


}
