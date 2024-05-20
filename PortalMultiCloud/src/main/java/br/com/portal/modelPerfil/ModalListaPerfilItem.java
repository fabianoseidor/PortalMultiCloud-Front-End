package br.com.portal.modelPerfil;

public class ModalListaPerfilItem {
	private Long   id_secao;
	private String nome_secao;
	private Long   id_perfil;
	private String nome_perfil;
	private Long   id_pag;
	private String desc_pagina;
	private int    bt_novo;
	private int    bt_editar;
	private int    bt_escluir;
	private int    bt_pesquisar;
	private String dt_criacao;
	private String user_cadastro;
	private String obs;
	
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
	public Long getId_pag() {
		return id_pag;
	}
	public void setId_pag(Long id_pag) {
		this.id_pag = id_pag;
	}
	public String getDesc_pagina() {
		return desc_pagina;
	}
	public void setDesc_pagina(String desc_pagina) {
		this.desc_pagina = desc_pagina;
	}
	public int getBt_novo() {
		return bt_novo;
	}
	public void setBt_novo(int bt_novo) {
		this.bt_novo = bt_novo;
	}
	public int getBt_editar() {
		return bt_editar;
	}
	public void setBt_editar(int bt_editar) {
		this.bt_editar = bt_editar;
	}
	public int getBt_escluir() {
		return bt_escluir;
	}
	public void setBt_escluir(int bt_escluir) {
		this.bt_escluir = bt_escluir;
	}
	public int getBt_pesquisar() {
		return bt_pesquisar;
	}
	public void setBt_pesquisar(int bt_pesquisar) {
		this.bt_pesquisar = bt_pesquisar;
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
