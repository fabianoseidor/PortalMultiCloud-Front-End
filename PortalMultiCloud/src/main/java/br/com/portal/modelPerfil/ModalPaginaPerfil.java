package br.com.portal.modelPerfil;

public class ModalPaginaPerfil {
	private Long   id_pag_secao;
	private Long   id_secao;
	private String dec_secao;
	private String dt_criacao; 
	private String user_cadastro;
	private String desc_pagina;      
	private String nome_pag_extensao;
	private String extensao;         
	private String obs;

	public boolean isNovo() {
    	
    	if(this.id_pag_secao == null) {
    		return true;
    	}else if( this.id_pag_secao != null && this.id_pag_secao > 0 ) {
    		return false;
    	}
    	return this.id_pag_secao == null;
    }
	
	public String getDec_secao() {
		return dec_secao;
	}

	public void setDec_secao(String dec_secao) {
		this.dec_secao = dec_secao;
	}

	public Long getId_pag_secao() {
		return id_pag_secao;
	}
	public void setId_pag_secao(Long id_pag_secao) {
		this.id_pag_secao = id_pag_secao;
	}
	public Long getId_secao() {
		return id_secao;
	}
	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
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
	public String getDesc_pagina() {
		return desc_pagina;
	}
	public void setDesc_pagina(String desc_pagina) {
		this.desc_pagina = desc_pagina;
	}
	public String getNome_pag_extensao() {
		return nome_pag_extensao;
	}
	public void setNome_pag_extensao(String nome_pag_extensao) {
		this.nome_pag_extensao = nome_pag_extensao;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	

}
