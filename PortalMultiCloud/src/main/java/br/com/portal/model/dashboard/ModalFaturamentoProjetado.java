package br.com.portal.model.dashboard;

import java.util.ArrayList;
import java.util.List;

public class ModalFaturamentoProjetado {

	private String id_contrato;
	private String id_cliente;
	private String razao_social;
	private String nome_fantasia;
	private String alias;
	private String pep;
	private String cnpj;
	private String dt_criacao;
	private String login_cadastro;
	private String id_hub_spot;
	private String tota_aditivos;
	private String total_instancias;
	private String qty_usuario_contratada;
	private String renovacao;
	private String contrato_antigo;
	private String nuvem;
	private String site;
	private String servico_contratado;
	private String moeda;
	private String fase_contrato;
	private String ciclo_update;
	private List<ModalProdutosContrato> mdProdutosContratos  = new ArrayList<ModalProdutosContrato>();
	private List<ModalRecursosContrato> mdRecursosContrato   = new ArrayList<ModalRecursosContrato>();
    private ModalVlrsContrato mdVlrsContrato                 = new ModalVlrsContrato();
    private List<ModalHistoricoContrato> mdHistoricoContrato = new ArrayList<ModalHistoricoContrato>();
    private List<ModalPepCnpj> mdPepCnpjs                    = new ArrayList<ModalPepCnpj>();
         
	public String getQty_usuario_contratada() {
		return qty_usuario_contratada;
	}
	public void setQty_usuario_contratada(String qty_usuario_contratada) {
		this.qty_usuario_contratada = qty_usuario_contratada;
	}
	public List<ModalPepCnpj> getMdPepCnpjs() {
		return mdPepCnpjs;
	}
	public void setMdPepCnpjs(List<ModalPepCnpj> mdPepCnpjs) {
		this.mdPepCnpjs = mdPepCnpjs;
	}
	public List<ModalHistoricoContrato> getMdHistoricoContrato() {
		return mdHistoricoContrato;
	}
	public void setMdHistoricoContrato(List<ModalHistoricoContrato> mdHistoricoContrato) {
		this.mdHistoricoContrato = mdHistoricoContrato;
	}
	public List<ModalRecursosContrato> getMdRecursosContrato() {
		return mdRecursosContrato;
	}
	public void setMdRecursosContrato(List<ModalRecursosContrato> mdRecursosContrato) {
		this.mdRecursosContrato = mdRecursosContrato;
	}
	public ModalVlrsContrato getMdVlrsContrato() {
		return mdVlrsContrato;
	}
	public void setMdVlrsContrato(ModalVlrsContrato mdVlrsContrato) {
		this.mdVlrsContrato = mdVlrsContrato;
	}
	public List<ModalProdutosContrato> getMdProdutosContratos() {
		return mdProdutosContratos;
	}
	public void setMdProdutosContratos(List<ModalProdutosContrato> mdProdutosContratos) {
		this.mdProdutosContratos = mdProdutosContratos;
	}
	public String getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
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
	public String getId_hub_spot() {
		return id_hub_spot;
	}
	public void setId_hub_spot(String id_hub_spot) {
		this.id_hub_spot = id_hub_spot;
	}
	public String getTota_aditivos() {
		return tota_aditivos;
	}
	public void setTota_aditivos(String tota_aditivos) {
		this.tota_aditivos = tota_aditivos;
	}
	public String getTotal_instancias() {
		return total_instancias;
	}
	public void setTotal_instancias(String total_instancias) {
		this.total_instancias = total_instancias;
	}
	public String getRenovacao() {
		return renovacao;
	}
	public void setRenovacao(String renovacao) {
		this.renovacao = renovacao;
	}
	public String getContrato_antigo() {
		return contrato_antigo;
	}
	public void setContrato_antigo(String contrato_antigo) {
		this.contrato_antigo = contrato_antigo;
	}
	public String getNuvem() {
		return nuvem;
	}
	public void setNuvem(String nuvem) {
		this.nuvem = nuvem;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getServico_contratado() {
		return servico_contratado;
	}
	public void setServico_contratado(String servico_contratado) {
		this.servico_contratado = servico_contratado;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getFase_contrato() {
		return fase_contrato;
	}
	public void setFase_contrato(String fase_contrato) {
		this.fase_contrato = fase_contrato;
	}
	public String getCiclo_update() {
		return ciclo_update;
	}
	public void setCiclo_update(String ciclo_update) {
		this.ciclo_update = ciclo_update;
	}

}
