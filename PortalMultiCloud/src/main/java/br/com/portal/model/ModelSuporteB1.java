package br.com.portal.model;

public class ModelSuporteB1 {
	
	private Long   id_suporte_b1;  
	private String telefone;
	private String nome_suporte;
	private String email;
	private String obs;
	private String dt_cadastro;
	
	public boolean isNovo() {
    	
	    if(this.id_suporte_b1 == null) {
	    		return true;
	    	}else if( this.id_suporte_b1 != null && this.id_suporte_b1 > 0 ) {
	    		return false;
	    	}
	    	return this.id_suporte_b1 == null;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId_suporte_b1() {
		return id_suporte_b1;
	}
	public void setId_suporte_b1(Long id_suporte_b1) {
		this.id_suporte_b1 = id_suporte_b1;
	}
	public String getNome_suporte() {
		return nome_suporte;
	}
	public void setNome_suporte(String nome_suporte) {
		this.nome_suporte = nome_suporte;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getDt_cadastro() {
		return dt_cadastro;
	}
	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	
}
