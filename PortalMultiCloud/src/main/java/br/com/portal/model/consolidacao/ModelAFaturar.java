package br.com.portal.model.consolidacao;

public class ModelAFaturar {
	
	private String pep;
	private String nome_emissor;
	private String dt_faturamento;
	private String mes;
	private String ano;
	private String valor;  
	private String moeda;
	private String vl_faturamento;

	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getNome_emissor() {
		return nome_emissor;
	}
	public void setNome_emissor(String nome_emissor) {
		this.nome_emissor = nome_emissor;
	}
	public String getDt_faturamento() {
		return dt_faturamento;
	}
	public void setDt_faturamento(String dt_faturamento) {
		this.dt_faturamento = dt_faturamento;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getVl_faturamento() {
		return vl_faturamento;
	}
	public void setVl_faturamento(String vl_faturamento) {
		this.vl_faturamento = vl_faturamento;
	}
}
