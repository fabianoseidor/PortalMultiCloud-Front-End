package br.com.portal.model;

import java.util.Objects;

public class ModelPepSap {
	private String pep;
	private String nome_cliente;
	private String cnpj;
	private Long idContrato;
	
	
	public Long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	@Override
	public String toString() {
		return "ModelPepSap [pep=" + pep + ", nome_cliente=" + nome_cliente + ", cnpj=" + cnpj + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(pep);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelPepSap other = (ModelPepSap) obj;
		return Objects.equals(pep, other.pep);
	}

	
}
