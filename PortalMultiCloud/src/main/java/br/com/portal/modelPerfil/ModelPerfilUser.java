package br.com.portal.modelPerfil;

public class ModelPerfilUser {

	private Long   id_colaboradores;
	private Long   id_perfil;
	private String nome_perfil;
	private String nome;
	private String login;
	private String useradmin;
	private String dt_criacao;
	private String login_cadastro;
	
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
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
	public String getNome_perfil() {
		return nome_perfil;
	}
	public void setNome_perfil(String nome_perfil) {
		this.nome_perfil = nome_perfil;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getUseradmin() {
		return useradmin;
	}
	public void setUseradmin(String useradmin) {
		this.useradmin = useradmin;
	}
	
	

}
