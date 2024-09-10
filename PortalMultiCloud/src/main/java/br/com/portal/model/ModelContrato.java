package br.com.portal.model;

public class ModelContrato {
    private Long   id_contrato;
    private Long   id_nuvem;
    private Long   id_fase_contrato;
    private Long   id_status_contrato;
    private Long   id_rascunho;
    private Long   id_comercial;
    private Long   id_suporte_b1;   
	private String motivoRascunho;
    
    private Long   id_ciclo_update;
    private Long   id_servico_contratado;
    private Long   id_site;
    private String dt_criacao;
	private String pep;
	private String id_hub_spot;
	private int    termo_admin;
	private int    termo_download; 
    private String qty_usuario_contratada;
    private String valor_total;
    private String valor_convertido;
    private String custo_total;
    private String cotacao_moeda;
    private Long   id_moeda;
	private String login_cadastro;
	private String observacao;
	private String contratopdf;
	private String extensaocontratopdf;
	private String nomeaqrpdf;

    private Long   id_cliente;
    private String nomeCliente;
    
	private Long   id_vigencia;
	private Long   id_tempo_contrato;
	private String dt_inicio;
	private String dt_final;
	private String dt_criacao_vigencia;
	private String observacao_vigencia;
	private boolean isRenovacao;
	private Long   id_contrato_origem;
	// Informacoes da comissicao de venda sobre o contrato equivalente a equipe de vendas
	private String  comissao;
	private String  valor_setup;
	private String  valor_parcela_setup;
	private int     qty_parcela_setup;
	private int     qty_mese_setup;
	
	
	public String getComissao() {
		return comissao;
	}

	public void setComissao(String comissao) {
		this.comissao = comissao;
	}

	public int getQty_mese_setup() {
		return qty_mese_setup;
	}

	public void setQty_mese_setup(int qty_mese_setup) {
		this.qty_mese_setup = qty_mese_setup;
	}
	
	public String getValor_parcela_setup() {
		return valor_parcela_setup;
	}

	public void setValor_parcela_setup(String valor_parcela_setup) {
		this.valor_parcela_setup = valor_parcela_setup;
	}

	public int getQty_parcela_setup() {
		return qty_parcela_setup;
	}

	public void setQty_parcela_setup(int qty_parcela_setup) {
		this.qty_parcela_setup = qty_parcela_setup;
	}

	public String getValor_setup() {
		return valor_setup;
	}

	public void setValor_setup(String valor_setup) {
		this.valor_setup = valor_setup;
	}

	public Long getId_comercial() {
		return id_comercial;
	}

	public void setId_comercial(Long id_comercial) {
		this.id_comercial = id_comercial;
	}

	public Long getId_suporte_b1() {
		return id_suporte_b1;
	}

	public void setId_suporte_b1(Long id_suporte_b1) {
		this.id_suporte_b1 = id_suporte_b1;
	}

	public Long getId_contrato_origem() {
		return id_contrato_origem;
	}

	public void setId_contrato_origem(Long id_contrato_origem) {
		this.id_contrato_origem = id_contrato_origem;
	}

	public boolean isRenovacao() {
		return isRenovacao;
	}

	public void setRenovacao(boolean isRenovacao) {
		this.isRenovacao = isRenovacao;
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

	public String getNomeaqrpdf() {
		return nomeaqrpdf;
	}

	public void setNomeaqrpdf(String nomeaqrpdf) {
		this.nomeaqrpdf = nomeaqrpdf;
	}

	public String getContratopdf() {
		return contratopdf;
	}

	public void setContratopdf(String contratopdf) {
		this.contratopdf = contratopdf;
	}

	public String getExtensaocontratopdf() {
		return extensaocontratopdf;
	}

	public void setExtensaocontratopdf(String extensaocontratopdf) {
		this.extensaocontratopdf = extensaocontratopdf;
	}

	public void setId_moeda(Long id_moeda) {
		this.id_moeda = id_moeda;
	}

	public String getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}

    public boolean isNovo() {
    	
    	if(this.id_contrato == null) {
    		return true;
    	}else if( this.id_contrato != null && this.id_contrato > 0) {
    		return false;
    	}
    	
    	return this.id_contrato == null;
    }
    public Long getId_moeda() {
		return id_moeda;
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

	public String getId_hub_spot() {
		return id_hub_spot;
	}

	public void setId_hub_spot(String id_hub_spot) {
		this.id_hub_spot = id_hub_spot;
	}

	public int getTermo_admin() {
		return termo_admin;
	}
	public void setTermo_admin(int termo_admin) {
		this.termo_admin = termo_admin;
	}
	public int getTermo_download() {
		return termo_download;
	}
	public void setTermo_download(int termo_download) {
		this.termo_download = termo_download;
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

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}
   	 
    public void setId_site(Long id_site) {
		this.id_site = id_site;
	}
    public Long getId_site() {
		return id_site;
	}
    
    public String getNomeCliente() {
		return nomeCliente;
	}
    public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public Long getId_fase_contrato() {
		return id_fase_contrato;
	}
	public void setId_fase_contrato(Long id_fase_contrato) {
		this.id_fase_contrato = id_fase_contrato;
	}
	public Long getId_status_contrato() {
		return id_status_contrato;
	}
	public void setId_status_contrato(Long id_status_contrato) {
		this.id_status_contrato = id_status_contrato;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_ciclo_update() {
		return id_ciclo_update;
	}
	public void setId_ciclo_update(Long id_ciclo_update) {
		this.id_ciclo_update = id_ciclo_update;
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
	public String getQty_usuario_contratada() {
		return qty_usuario_contratada;
	}
	public void setQty_usuario_contratada(String qty_usuario_contratada) {
		this.qty_usuario_contratada = qty_usuario_contratada;
	}

	public String getValor_total() {
		return valor_total;
	}
	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String toString() {
		return "ModelContrato [id_contrato=" + id_contrato + ", id_nuvem=" + id_nuvem + ", id_fase_contrato="
				+ id_fase_contrato + ", id_status_contrato=" + id_status_contrato + ", id_cliente=" + id_cliente
				+ ", nomeCliente=" + nomeCliente + ", id_ciclo_update=" + id_ciclo_update + ", id_servico_contratado="
				+ id_servico_contratado + ", id_site=" + id_site + ", dt_criacao=" + dt_criacao + ", pep=" + pep
				+ ", qty_usuario_contratada=" + qty_usuario_contratada + ", valor_total=" + valor_total
				+ ", observacao=" + observacao + ", id_vigencia=" + id_vigencia + ", id_tempo_contrato="
				+ id_tempo_contrato + ", dt_inicio=" + dt_inicio + ", dt_final=" + dt_final + ", dt_criacao_vigencia="
				+ dt_criacao_vigencia + ", observacao_vigencia=" + observacao_vigencia + "]";
	}
	
	
	
    
}
