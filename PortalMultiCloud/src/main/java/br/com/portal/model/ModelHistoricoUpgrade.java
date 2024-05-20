package br.com.portal.model;

import java.util.Objects;

public class ModelHistoricoUpgrade {

	private Long   id_recurso              ;    
	private Long   id_cliente              ;    
	private Long   id_contrato             ;    
	private Long   id_aditivo              ;    
	private String dt_cadastro             ;    
	private String login                   ;    
	private Long   id_familia_flavors_novo ;    
	private String familia_novo            ;    
	private Long   id_familia_flavors_velho;    
	private String familia_antiga          ;    
	private String tamanho_disco_velho     ;    
	private String tamanho_disco_novo      ;    
	private String cpu_novo                ;    
	private String cpu_velho               ;    
	private String ram_novo                ;    
	private String ram_velho               ;    
	private String valor_novo              ;    
	private String valor_velho             ;    
	private Long   id_status_recurso       ;
	private String dt_criacao_recurso      ;   
	private String hostname                ;    
	private Long   id_tipo_disco           ;    
	private Long   id_so                   ;
	private String ip                      ;
	private Long   id_ambiente             ;    
	private Long   id_familia_flavors      ;    
	private String familia_atual           ;    
	private Long   id_nuvem                ;
	private String nuvem                   ;
	private Long   site                    ;    
	private Long   id_status_contrato      ;    
	private String dt_criacao              ;    
	private String pep                     ;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNuvem() {
		return nuvem;
	}
	public void setNuvem(String nuvem) {
		this.nuvem = nuvem;
	}
	public String getDt_criacao_recurso() {
		return dt_criacao_recurso;
	}
	public void setDt_criacao_recurso(String dt_criacao_recurso) {
		this.dt_criacao_recurso = dt_criacao_recurso;
	}
	public Long getId_recurso() {
		return id_recurso;
	}
	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_aditivo() {
		return id_aditivo;
	}
	public void setId_aditivo(Long id_aditivo) {
		this.id_aditivo = id_aditivo;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Long getId_familia_flavors_novo() {
		return id_familia_flavors_novo;
	}
	public void setId_familia_flavors_novo(Long id_familia_flavors_novo) {
		this.id_familia_flavors_novo = id_familia_flavors_novo;
	}
	public String getFamilia_novo() {
		return familia_novo;
	}
	public void setFamilia_novo(String familia_novo) {
		this.familia_novo = familia_novo;
	}
	public Long getId_familia_flavors_velho() {
		return id_familia_flavors_velho;
	}
	public void setId_familia_flavors_velho(Long id_familia_flavors_velho) {
		this.id_familia_flavors_velho = id_familia_flavors_velho;
	}
	public String getFamilia_antiga() {
		return familia_antiga;
	}
	public void setFamilia_antiga(String familia_antiga) {
		this.familia_antiga = familia_antiga;
	}
	public String getTamanho_disco_velho() {
		return tamanho_disco_velho;
	}
	public void setTamanho_disco_velho(String tamanho_disco_velho) {
		this.tamanho_disco_velho = tamanho_disco_velho;
	}
	public String getTamanho_disco_novo() {
		return tamanho_disco_novo;
	}
	public void setTamanho_disco_novo(String tamanho_disco_novo) {
		this.tamanho_disco_novo = tamanho_disco_novo;
	}
	public String getCpu_novo() {
		return cpu_novo;
	}
	public void setCpu_novo(String cpu_novo) {
		this.cpu_novo = cpu_novo;
	}
	public String getCpu_velho() {
		return cpu_velho;
	}
	public void setCpu_velho(String cpu_velho) {
		this.cpu_velho = cpu_velho;
	}
	public String getRam_novo() {
		return ram_novo;
	}
	public void setRam_novo(String ram_novo) {
		this.ram_novo = ram_novo;
	}
	public String getRam_velho() {
		return ram_velho;
	}
	public void setRam_velho(String ram_velho) {
		this.ram_velho = ram_velho;
	}
	public String getValor_novo() {
		return valor_novo;
	}
	public void setValor_novo(String valor_novo) {
		this.valor_novo = valor_novo;
	}
	public String getValor_velho() {
		return valor_velho;
	}
	public void setValor_velho(String valor_velho) {
		this.valor_velho = valor_velho;
	}
	public Long getId_status_recurso() {
		return id_status_recurso;
	}
	public void setId_status_recurso(Long id_status_recurso) {
		this.id_status_recurso = id_status_recurso;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public Long getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}
	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}
	public String getFamilia_atual() {
		return familia_atual;
	}
	public void setFamilia_atual(String familia_atual) {
		this.familia_atual = familia_atual;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public Long getSite() {
		return site;
	}
	public void setSite(Long site) {
		this.site = site;
	}
	public Long getId_status_contrato() {
		return id_status_contrato;
	}
	public void setId_status_contrato(Long id_status_contrato) {
		this.id_status_contrato = id_status_contrato;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id_cliente, id_contrato, id_recurso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelHistoricoUpgrade other = (ModelHistoricoUpgrade) obj;
		return Objects.equals(id_cliente, other.id_cliente) && Objects.equals(id_contrato, other.id_contrato)
				&& Objects.equals(id_recurso, other.id_recurso);
	}
	@Override
	public String toString() {
		return "ModelHistoricoUpgrade [id_recurso=" + id_recurso + ", id_cliente=" + id_cliente + ", id_contrato="
				+ id_contrato + ", id_aditivo=" + id_aditivo + ", dt_cadastro=" + dt_cadastro + ", login=" + login
				+ ", id_familia_flavors_novo=" + id_familia_flavors_novo + ", familia_novo=" + familia_novo
				+ ", id_familia_flavors_velho=" + id_familia_flavors_velho + ", familia_antiga=" + familia_antiga
				+ ", tamanho_disco_velho=" + tamanho_disco_velho + ", tamanho_disco_novo=" + tamanho_disco_novo
				+ ", cpu_novo=" + cpu_novo + ", cpu_velho=" + cpu_velho + ", ram_novo=" + ram_novo + ", ram_velho="
				+ ram_velho + ", valor_novo=" + valor_novo + ", valor_velho=" + valor_velho + ", id_status_recurso="
				+ id_status_recurso + ", hostname=" + hostname + ", id_tipo_disco=" + id_tipo_disco + ", id_so=" + id_so
				+ ", id_ambiente=" + id_ambiente + ", id_familia_flavors=" + id_familia_flavors + ", familia_atual="
				+ familia_atual + ", id_nuvem=" + id_nuvem + ", site=" + site + ", id_status_contrato="
				+ id_status_contrato + ", dt_criacao=" + dt_criacao + ", pep=" + pep + "]";
	}
	
	
	
}
