package br.com.portal.model.dashboard;

public class ModalPepCnpj {
	
	private String id_contrato;
	private String cnpj;
	private String pep;

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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
