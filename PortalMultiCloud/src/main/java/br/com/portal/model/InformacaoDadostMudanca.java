package br.com.portal.model;

import java.util.List;

public class InformacaoDadostMudanca {

	private String             titulo_mudanca;
	private String             login_user;
	private String             email_solicitante;
	
	CriticidadeGMUD            criticidade;
	ImpactoMudancaGMUD         impactoMudanca;
	TipoMudancaGMUD            tipoMudanca;
	CategoriaPadraoGMUD        categoriaPadrao;
	List<ListClientesAfetados> mudancaClientesAfetados;
	List<String>               arquivosMudanca;
	List<ItemCategoriaPadrao>  atividadesMudanca;
	DadosMudancaGMUD           dadosMudanca;
	AcaoPosAtividadeGMUD       acaoPosAtividade;
	
	public String getTitulo_mudanca() {
		return titulo_mudanca;
	}
	public void setTitulo_mudanca(String titulo_mudanca) {
		this.titulo_mudanca = titulo_mudanca;
	}
	public String getLogin_user() {
		return login_user;
	}
	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}
	public String getEmail_solicitante() {
		return email_solicitante;
	}
	public void setEmail_solicitante(String email_solicitante) {
		this.email_solicitante = email_solicitante;
	}
	public CriticidadeGMUD getCriticidade() {
		return criticidade;
	}
	public void setCriticidade(CriticidadeGMUD criticidade) {
		this.criticidade = criticidade;
	}
	public ImpactoMudancaGMUD getImpactoMudanca() {
		return impactoMudanca;
	}
	public void setImpactoMudanca(ImpactoMudancaGMUD impactoMudanca) {
		this.impactoMudanca = impactoMudanca;
	}
	public TipoMudancaGMUD getTipoMudanca() {
		return tipoMudanca;
	}
	public void setTipoMudanca(TipoMudancaGMUD tipoMudanca) {
		this.tipoMudanca = tipoMudanca;
	}
	public CategoriaPadraoGMUD getCategoriaPadrao() {
		return categoriaPadrao;
	}
	public void setCategoriaPadrao(CategoriaPadraoGMUD categoriaPadrao) {
		this.categoriaPadrao = categoriaPadrao;
	}
	public List<ListClientesAfetados> getMudancaClientesAfetados() {
		return mudancaClientesAfetados;
	}
	public void setMudancaClientesAfetados(List<ListClientesAfetados> mudancaClientesAfetados) {
		this.mudancaClientesAfetados = mudancaClientesAfetados;
	}
	public List<String> getArquivosMudanca() {
		return arquivosMudanca;
	}
	public void setArquivosMudanca(List<String> arquivosMudanca) {
		this.arquivosMudanca = arquivosMudanca;
	}
	public List<ItemCategoriaPadrao> getAtividadesMudanca() {
		return atividadesMudanca;
	}
	public void setAtividadesMudanca(List<ItemCategoriaPadrao> atividadesMudanca) {
		this.atividadesMudanca = atividadesMudanca;
	}
	public DadosMudancaGMUD getDadosMudanca() {
		return dadosMudanca;
	}
	public void setDadosMudanca(DadosMudancaGMUD dadosMudanca) {
		this.dadosMudanca = dadosMudanca;
	}
	public AcaoPosAtividadeGMUD getAcaoPosAtividade() {
		return acaoPosAtividade;
	}
	public void setAcaoPosAtividade(AcaoPosAtividadeGMUD acaoPosAtividade) {
		this.acaoPosAtividade = acaoPosAtividade;
	}
	
	
}
