package br.com.portal.model;

public class ModelRecursoContratoAditivoRel {
	
	 private Long    id_recurso;
	 private Long    id_status_recurso;
	 private String  status_recurso;
	 private Long    id_retencao_backup;
	 private String  retencao_backup;
	 private Long    id_tipo_disco;
	 private String  tipo_disco;
	 private Long    id_so;
	 private String  sistema_operacional;
	 private Long    id_ambiente;
	 private String  ambiente;
	 private Long    id_familia_flavors;
	 private String  familia;
	 private Long    id_tipo_servico;
	 private String  tipo_servico; 
	 private String  hostname;
	 private String  primary_ip;
	 private String  tag_vcenter;
	 private Long    id_contrato;
	 private Long    id_aditivado;
	 private String  razao_social;
	 private String  dt_cadastro;
	 private String  nuvem;
	 private String  site;
	 
	 
	public String getNuvem() {
		return nuvem;
	}
	public void setNuvem(String nuvem) {
		this.nuvem = nuvem;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
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
	public Long getId_status_recurso() {
		return id_status_recurso;
	}
	public void setId_status_recurso(Long id_status_recurso) {
		this.id_status_recurso = id_status_recurso;
	}
	public String getStatus_recurso() {
		return status_recurso;
	}
	public void setStatus_recurso(String status_recurso) {
		this.status_recurso = status_recurso;
	}
	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}
	public void setId_retencao_backup(Long id_retencao_backup) {
		this.id_retencao_backup = id_retencao_backup;
	}
	public String getRetencao_backup() {
		return retencao_backup;
	}
	public void setRetencao_backup(String retencao_backup) {
		this.retencao_backup = retencao_backup;
	}
	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public String getTipo_disco() {
		return tipo_disco;
	}
	public void setTipo_disco(String tipo_disco) {
		this.tipo_disco = tipo_disco;
	}
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public String getSistema_operacional() {
		return sistema_operacional;
	}
	public void setSistema_operacional(String sistema_operacional) {
		this.sistema_operacional = sistema_operacional;
	}
	public Long getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public String getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}
	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public String getTipo_servico() {
		return tipo_servico;
	}
	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
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
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String toString() {
		return "ModelRecursoContratoRel [id_recurso=" + id_recurso + ", id_status_recurso=" + id_status_recurso
				+ ", status_recurso=" + status_recurso + ", id_retencao_backup=" + id_retencao_backup
				+ ", retencao_backup=" + retencao_backup + ", id_tipo_disco=" + id_tipo_disco + ", tipo_disco="
				+ tipo_disco + ", id_so=" + id_so + ", sistema_operacional=" + sistema_operacional + ", id_ambiente="
				+ id_ambiente + ", ambiente=" + ambiente + ", id_familia_flavors=" + id_familia_flavors + ", familia="
				+ familia + ", id_tipo_servico=" + id_tipo_servico + ", tipo_servico=" + tipo_servico + ", hostname="
				+ hostname + ", primary_ip=" + primary_ip + ", tag_vcenter=" + tag_vcenter + ", id_contrato="
				+ id_contrato + ", id_aditivado=" + id_aditivado + ", razao_social=" + razao_social + "]";
	}
	
	
}
