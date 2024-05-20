package br.com.portal.model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L;

    private Long   id_colaboradores;
    private String nome;
    private Long   id_cargo;
    private String cep;
    private String endereco;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String pais;
    private String cpf;
    private String telefone;
    private String celular;
    private String email;
    private String login;
    private String senha;
    private String useradmin;
    private String fotouser;
    private int    primeiro_acesso;

    public int getPrimeiro_acesso() {
		return primeiro_acesso;
	}

	public void setPrimeiro_acesso(int primeiro_acesso) {
		this.primeiro_acesso = primeiro_acesso;
	}

	public String getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(String useradmin) {
		this.useradmin = useradmin;
	}

	private String extensaofotouser;
   
    
    public boolean isNovo() {
    	
    	if(this.id_colaboradores == null) {
    		return true;
    	}else if(this.id_colaboradores != null && this.id_colaboradores > 0) {
    		return false;
    	}
    	
    	return this.id_colaboradores == null;
    }
    
	public Long getId_colaboradores() {
		return id_colaboradores;
	}
	public void setId_colaboradores(Long id_colaboradores) {
		this.id_colaboradores = id_colaboradores;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(Long id_cargo) {
		this.id_cargo = id_cargo;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotouser() {
		return fotouser;
	}

	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}

	public String getExtensaofotouser() {
		return extensaofotouser;
	}

	public void setExtensaofotouser(String extensaofotouser) {
		this.extensaofotouser = extensaofotouser;
	}
    
}
