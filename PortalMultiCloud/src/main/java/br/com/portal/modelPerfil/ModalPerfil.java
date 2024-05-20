package br.com.portal.modelPerfil;

public class ModalPerfil {
	private Long   id_perfil;          
	private String nome_perfil;  
	private String dt_criacao;   
	private String user_cadastro;
	private String obs;
		
	public boolean isNovo() {
    	
    	if(this.id_perfil == null) {
    		return true;
    	}else if( this.id_perfil != null && this.id_perfil > 0 ) {
    		return false;
    	}
    	return this.id_perfil == null;
    }

	public Long getId_perfil() {
		return id_perfil;
	}
	
	public void setId_perfil(Long id_perfil) {
		this.id_perfil = id_perfil;
	}
	
	public String getNome_perfil() {
		return nome_perfil;
	}
	
	public void setNome_perfil(String nome_perfil) {
		this.nome_perfil = nome_perfil;
	}
	
	public String getDt_criacao() {
		return dt_criacao;
	}
	
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
	public String getUser_cadastro() {
		return user_cadastro;
	}
	
	public void setUser_cadastro(String user_cadastro) {
		this.user_cadastro = user_cadastro;
	}
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
}
