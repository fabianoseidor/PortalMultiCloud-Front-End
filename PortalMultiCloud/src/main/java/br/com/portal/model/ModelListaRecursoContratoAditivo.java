package br.com.portal.model;

public class ModelListaRecursoContratoAditivo {
	
	private Long   id_recurso;
	private String status_recurso;
	private Long   id_retencao_backup;
	private String retencao_backup;
	private Long   id_tipo_disco;
	private String tipo_disco;
	private Long   id_so;
	private String sistema_operacional;
	private Long   id_ambiente;
	private String ambiente;
	private Long   id_familia;
	private String familia;
	private Long   id_tipo_servico;
	private String tipo_servico;
	private String dt_cadastro;
	private String hostname;
	private String tamanho_disco;
	private String primary_ip;
	private String tag_vcenter;
	private String obs;
	private Long   id_contrato;
	private Long   id_aditivado;
	private String isaditivo;
    private String valor_recurso;
    private String login_cadastro;

	private String razao_social;
    private String status_contrato;
    private Long   id_nuvem;
    private String mome_parceiro;
    private Long   id_site;
    private String nome_site;
    private String eip_Vcenter;   
    private String host_Vcenter;  
    
	public String getHost_Vcenter() {
		return host_Vcenter;
	}
	public void setHost_Vcenter(String host_Vcenter) {
		this.host_Vcenter = host_Vcenter;
	}
	public String getEip_Vcenter() {
		return eip_Vcenter;
	}
	public void setEip_Vcenter(String eip_Vcenter) {
		this.eip_Vcenter = eip_Vcenter;
	}
	public Long getId_familia() {
		return id_familia;
	}
	public void setId_familia(Long id_familia) {
		this.id_familia = id_familia;
	}
	public Long getId_site() {
		return id_site;
	}
	public void setId_site(Long id_site) {
		this.id_site = id_site;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public Long getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}
	public void setId_retencao_backup(Long id_retencao_backup) {
		this.id_retencao_backup = id_retencao_backup;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public String getMome_parceiro() {
		return mome_parceiro;
	}
	public void setMome_parceiro(String mome_parceiro) {
		this.mome_parceiro = mome_parceiro;
	}
	public String getNome_site() {
		return nome_site;
	}
	public void setNome_site(String nome_site) {
		this.nome_site = nome_site;
	}
	public String getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}
	public String getValor_recurso() {
		return valor_recurso;
	}
	public void setValor_recurso(String valor_recurso) {
		this.valor_recurso = valor_recurso;
	}
	public String getIsaditivo() {
		return isaditivo;
	}
	public void setIsaditivo(String isaditivo) {
		this.isaditivo = isaditivo;
	}
	public Long getId_aditivado() {
		return id_aditivado;
	}
	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}
	public Long getId_recurso() {
		return id_recurso;
	}
	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}
	public String getStatus_recurso() {
		return status_recurso;
	}
	public void setStatus_recurso(String status_recurso) {
		this.status_recurso = status_recurso;
	}
	public String getRetencao_backup() {
		return retencao_backup;
	}
	public void setRetencao_backup(String retencao_backup) {
		this.retencao_backup = retencao_backup;
	}
	public String getTipo_disco() {
		return tipo_disco;
	}
	public void setTipo_disco(String tipo_disco) {
		this.tipo_disco = tipo_disco;
	}
	public String getSistema_operacional() {
		return sistema_operacional;
	}
	public void setSistema_operacional(String sistema_operacional) {
		this.sistema_operacional = sistema_operacional;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getTipo_servico() {
		return tipo_servico;
	}
	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getTamanho_disco() {
		return tamanho_disco;
	}
	public void setTamanho_disco(String tamanho_disco) {
		this.tamanho_disco = tamanho_disco;
	}
	public String getPrimary_ip() {
		return primary_ip;
	}
	public void setPrimary_ip(String primary_ip) {
		this.primary_ip = primary_ip;
	}
	public String getTag_vcenter() {
		return tag_vcenter;
	}
	public void setTag_vcenter(String tag_vcenter) {
		this.tag_vcenter = tag_vcenter;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}


	
	
}
