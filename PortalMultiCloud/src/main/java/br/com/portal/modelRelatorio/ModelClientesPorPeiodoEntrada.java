package br.com.portal.modelRelatorio;

public class ModelClientesPorPeiodoEntrada {
	 
	private String tipo_contrato      ;
	private String pep                ;
	private String id_contrato        ;
	private String razao_social       ;
	private String login_cadastro     ;
	private String suporte_b1         ;
	private String comercial          ;
	private String dt_criacao_contrato;
	private String moeda              ;
	private String vlr_total          ;
	private String valor_convertido   ;
	private String custo_total        ;
	private String cotacao_moeda      ;
	private String dt_criacao_vigencia;
	private String dt_inicio_vigencia ;
	private String dt_final_vigencia  ;
	private String qty_dias_vigencia  ;
	
	public String getTipo_contrato() {
		return tipo_contrato;
	}
	public void setTipo_contrato(String tipo_contrato) {
		this.tipo_contrato = tipo_contrato;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}
	public String getSuporte_b1() {
		return suporte_b1;
	}
	public void setSuporte_b1(String suporte_b1) {
		this.suporte_b1 = suporte_b1;
	}
	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
	public String getDt_criacao_contrato() {
		return dt_criacao_contrato;
	}
	public void setDt_criacao_contrato(String dt_criacao_contrato) {
		this.dt_criacao_contrato = dt_criacao_contrato;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getVlr_total() {
		return vlr_total;
	}
	public void setVlr_total(String vlr_total) {
		this.vlr_total = vlr_total;
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
	public String getDt_criacao_vigencia() {
		return dt_criacao_vigencia;
	}
	public void setDt_criacao_vigencia(String dt_criacao_vigencia) {
		this.dt_criacao_vigencia = dt_criacao_vigencia;
	}
	public String getDt_inicio_vigencia() {
		return dt_inicio_vigencia;
	}
	public void setDt_inicio_vigencia(String dt_inicio_vigencia) {
		this.dt_inicio_vigencia = dt_inicio_vigencia;
	}
	public String getDt_final_vigencia() {
		return dt_final_vigencia;
	}
	public void setDt_final_vigencia(String dt_final_vigencia) {
		this.dt_final_vigencia = dt_final_vigencia;
	}
	public String getQty_dias_vigencia() {
		return qty_dias_vigencia;
	}
	public void setQty_dias_vigencia(String qty_dias__vigencia) {
		this.qty_dias_vigencia = qty_dias__vigencia;
	}
	@Override
	public String toString() {
		return "ModelClientesPorPeiodoEntrada [tipo_contrato=" + tipo_contrato + ", pep=" + pep + ", id_contrato="
				+ id_contrato + ", razao_social=" + razao_social + ", login_cadastro=" + login_cadastro
				+ ", suporte_b1=" + suporte_b1 + ", comercial=" + comercial + ", dt_criacao_contrato="
				+ dt_criacao_contrato + ", moeda=" + moeda + ", vlr_total=" + vlr_total + ", valor_convertido="
				+ valor_convertido + ", custo_total=" + custo_total + ", cotacao_moeda=" + cotacao_moeda
				+ ", dt_criacao_vigencia=" + dt_criacao_vigencia + ", dt_inicio_vigencia=" + dt_inicio_vigencia
				+ ", dt_final_vigencia=" + dt_final_vigencia + ", qty_dias__vigencia=" + qty_dias_vigencia + "]";
	}
}
