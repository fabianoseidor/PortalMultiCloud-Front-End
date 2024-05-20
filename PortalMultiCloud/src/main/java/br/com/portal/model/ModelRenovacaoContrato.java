package br.com.portal.model;

public class ModelRenovacaoContrato {
	// Informacoes sobre o contrato
    private Long   id_contrato;
    private Long   id_fase_contrato;
    private Long   id_ciclo_update;
    private Long   id_servico_contratado;
	private String pep;
	private Long   id_nuvem_contr;             // idNuvemMAR
	private Long   id_site_contr;              // idSiteMAR
	private String id_hub_spot;
	private int    termo_admin;
	private int    termo_download; 
    private Long   id_cliente;
    private String nomeCliente;

    // Informacoes sobre os Recursos
	private Long   id_retencao_backup;   // idRetencaoBackupMAR
	private String desc_retencao_backup;
	private String tag_vcenter;          // tagVcenterMAR
	private Long   id_tipo_disco;        // idTipoDiscoMAR
	private String desc_tipo_disco;
	private Long   id_so;                // idSoMAR
	private String desc_so;
	private Long   id_ambiente;          // idAmbienteMAR
	private String desc_ambiente;
	private Long   id_tipo_servico;      // idTipoServicoMAR
	private String desc_tipo_servico;
	private String hostname;             // hostnameModalRecursoMAR
	private String tamanho_disco;        // tamanhoDiscoModalRecursoMAR 
	private String primary_ip;           // primaryIPModalRecursoMAR
	private Long   id_nuvem;             // idNuvemMAR
	private Long   id_site;              // idSiteMAR
	private Long   id_familia_flavors;   // idFamiliaFlavorsMAR
	private String desc_familia;
    private String eip_Vcenter; 
    private String host_Vcenter;
    
	public Long getId_nuvem_contr() {
		return id_nuvem_contr;
	}
	public void setId_nuvem_contr(Long id_nuvem_contr) {
		this.id_nuvem_contr = id_nuvem_contr;
	}
	public Long getId_site_contr() {
		return id_site_contr;
	}
	public void setId_site_contr(Long id_site_contr) {
		this.id_site_contr = id_site_contr;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
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
	public Long getId_servico_contratado() {
		return id_servico_contratado;
	}
	public void setId_servico_contratado(Long id_servico_contratado) {
		this.id_servico_contratado = id_servico_contratado;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
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
	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}
	public void setId_retencao_backup(Long id_retencao_backup) {
		this.id_retencao_backup = id_retencao_backup;
	}
	public String getDesc_retencao_backup() {
		return desc_retencao_backup;
	}
	public void setDesc_retencao_backup(String desc_retencao_backup) {
		this.desc_retencao_backup = desc_retencao_backup;
	}
	public String getTag_vcenter() {
		return tag_vcenter;
	}
	public void setTag_vcenter(String tag_vcenter) {
		this.tag_vcenter = tag_vcenter;
	}
	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public String getDesc_tipo_disco() {
		return desc_tipo_disco;
	}
	public void setDesc_tipo_disco(String desc_tipo_disco) {
		this.desc_tipo_disco = desc_tipo_disco;
	}
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public String getDesc_so() {
		return desc_so;
	}
	public void setDesc_so(String desc_so) {
		this.desc_so = desc_so;
	}
	public Long getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public String getDesc_ambiente() {
		return desc_ambiente;
	}
	public void setDesc_ambiente(String desc_ambiente) {
		this.desc_ambiente = desc_ambiente;
	}
	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public String getDesc_tipo_servico() {
		return desc_tipo_servico;
	}
	public void setDesc_tipo_servico(String desc_tipo_servico) {
		this.desc_tipo_servico = desc_tipo_servico;
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
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public Long getId_site() {
		return id_site;
	}
	public void setId_site(Long id_site) {
		this.id_site = id_site;
	}
	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}
	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}
	public String getDesc_familia() {
		return desc_familia;
	}
	public void setDesc_familia(String desc_familia) {
		this.desc_familia = desc_familia;
	}
	public String getEip_Vcenter() {
		return eip_Vcenter;
	}
	public void setEip_Vcenter(String eip_Vcenter) {
		this.eip_Vcenter = eip_Vcenter;
	}
	public String getHost_Vcenter() {
		return host_Vcenter;
	}
	public void setHost_Vcenter(String host_Vcenter) {
		this.host_Vcenter = host_Vcenter;
	}

}
