package br.com.portal.model.loginunificado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users implements Serializable{

	private static final long serialVersionUID = -4395798630208505815L;
	private Long id_users;
	private String login;
	private String passoword;
	private String role;
    private String dt_criacao;
	private Pessoa pessoa = new Pessoa();	
    private List<UserAplicativo> userAplicativos = new ArrayList<UserAplicativo>();
 
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<UserAplicativo> getUserAplicativos() {
		return userAplicativos;
	}


	public void setUserAplicativos(List<UserAplicativo> userAplicativos) {
		this.userAplicativos = userAplicativos;
	}


	public Long getId_users() {
		return id_users;
	}


	public void setId_users(Long id_users) {
		this.id_users = id_users;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassoword() {
		return passoword;
	}

	public void setPassoword(String passoword) {
		this.passoword = passoword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id_users);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(id_users, other.id_users);
	}


	@Override
	public String toString() {
		return "Users [id_users=" + id_users + ", login=" + login + ", passoword=" + passoword + ", role=" + role
				+ ", dt_criacao=" + dt_criacao + ", pessoa=" + pessoa + ", userAplicativos=" + userAplicativos + "]";
	}
	
	
	

} 
