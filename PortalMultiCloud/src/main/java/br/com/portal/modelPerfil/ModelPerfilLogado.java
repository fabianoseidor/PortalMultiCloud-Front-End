package br.com.portal.modelPerfil;

public class ModelPerfilLogado {
	
	private Long    id_colaboradores;
	private Long    id_perfil;
	private String  nome_secao;
	private String  desc_pagina;
	private int     useradmin;
	private int     primeiro_acesso;
	private String  nome_perfil;
	private Boolean isVisivel;
	private String  obs;
	
	
	public String getNome_secao() {
		return nome_secao;
	}
	public void setNome_secao(String nome_secao) {
		this.nome_secao = nome_secao;
	}
	public Long getId_colaboradores() {
		return id_colaboradores;
	}
	public void setId_colaboradores(Long id_colaboradores) {
		this.id_colaboradores = id_colaboradores;
	}
	public Long getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(Long id_perfil) {
		this.id_perfil = id_perfil;
	}
	public String getDesc_pagina() {
		return desc_pagina;
	}
	public void setDesc_pagina(String desc_pagina) {
		this.desc_pagina = desc_pagina;
	}
	public int getUseradmin() {
		return useradmin;
	}
	public void setUseradmin(int useradmin) {
		this.useradmin = useradmin;
	}
	public int getPrimeiro_acesso() {
		return primeiro_acesso;
	}
	public void setPrimeiro_acesso(int primeiro_acesso) {
		this.primeiro_acesso = primeiro_acesso;
	}
	public String getNome_perfil() {
		return nome_perfil;
	}
	public void setNome_perfil(String nome_perfil) {
		this.nome_perfil = nome_perfil;
	}
	public Boolean getIsVisivel() {
		return isVisivel;
	}
	public void setIsVisivel(Boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
		
}
