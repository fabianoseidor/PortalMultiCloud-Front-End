package br.com.portal.model;

public class ModelRecursoContratoCliente {

	private Long id_contrato;  
	private Long id_cliente;
	private Long id_nuvem;
	private String nuvem;
	private String razao_social;
	private String nome_fantasia;

	public boolean isNovo() {
    	
    	if(this.id_contrato == null) {
    		return true;
    	}else if( this.id_contrato != null && this.id_contrato > 0) {
    		return false;
    	}
    	return this.id_contrato == null;
    }

	public Long getId_nuvem() {
		return id_nuvem;
	}

	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}

	public String getNuvem() {
		return nuvem;
	}

	public void setNuvem(String nuvem) {
		this.nuvem = nuvem;
	}

	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String toString() {
		return "ModelRecursoContratoCliente [id_contrato=" + id_contrato + ", id_cliente=" + id_cliente + ", id_nuvem="
				+ id_nuvem + ", nuvem=" + nuvem + ", razao_social=" + razao_social + ", nome_fantasia=" + nome_fantasia
				+ "]";
	}
	

}
