package br.com.portal.modelPerfil;

public class ModelSecao {

	private Long   id_secao;    
	private String nome_secao;  
	private String dt_criacao; 
	private String user_cadastro;
	private String obs;

	public boolean isNovo() {
    	
    	if(this.id_secao == null) {
    		return true;
    	}else if( this.id_secao != null && this.id_secao > 0 ) {
    		return false;
    	}
    	return this.id_secao == null;
    }

	public Long getId_secao() {
		return id_secao;
	}
	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
	}
	public String getNome_secao() {
		return nome_secao;
	}
	public void setNome_secao(String nome_secao) {
		this.nome_secao = nome_secao;
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
