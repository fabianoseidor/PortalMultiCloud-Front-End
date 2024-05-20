package br.com.portal.model;

import java.util.ArrayList;
import java.util.List;

public class ModelCliente {
	
	private Long   id_cliente;
	private Long   id_porte_emp;
	private Long   id_status_emp;
	private String razao_social;
	private String nome_fantasia;
	private String site;
	private String cep;
	private String endereco;
	private String bairro;
	private String numero;
	private String complemento;
	private String cidade;
	private String estado;
	private String pais;
	private String cnpj;
	private String inscricao_estadual;
	private String inscricao_municipal;
	private String nicho_mercado;
	private String status_emp;
	private String dt_criacao;
	private String observacao;
	private String login_cadastro;
	private String alias;
	private String pep_pesquisado;
	private Integer totalPagCli;
	
	

	public Integer getTotalPagCli() {
		return totalPagCli;
	}

	public void setTotalPagCli(Integer totalPagCli) {
		this.totalPagCli = totalPagCli;
	}

	public String getPep_pesquisado() {
		return pep_pesquisado;
	}

	public void setPep_pesquisado(String pep_pesquisado) {
		this.pep_pesquisado = pep_pesquisado;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	private List<ModelTelefone>  listaTelefones = new ArrayList<ModelTelefone>();
	
    public List<ModelTelefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<ModelTelefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}

	public String getLogin_cadastro() {
		return login_cadastro;
	}

	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}

	public String getStatus_emp() {
		return status_emp;
	}

	public void setStatus_emp(String status_emp) {
		this.status_emp = status_emp;
	}

	public boolean isNovo() {
    	
    	if(this.id_cliente == null) {
    		return true;
    	}else if( this.id_cliente != null && this.id_cliente > 0) {
    		return false;
    	}
    	
    	return this.id_cliente == null;
    }
	

    
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_porte_emp() {
		return id_porte_emp;
	}
	public void setId_porte_emp(Long id_porte_emp) {
		this.id_porte_emp = id_porte_emp;
	}
	public Long getId_status_emp() {
		return id_status_emp;
	}
	public void setId_status_emp(Long id_status_emp) {
		this.id_status_emp = id_status_emp;
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
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	public String getInscricao_municipal() {
		return inscricao_municipal;
	}
	public void setInscricao_municipal(String inscricao_municipal) {
		this.inscricao_municipal = inscricao_municipal;
	}
	public String getNicho_mercado() {
		return nicho_mercado;
	}
	public void setNicho_mercado(String nicho_mercado) {
		this.nicho_mercado = nicho_mercado;
	}
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao( String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



}
