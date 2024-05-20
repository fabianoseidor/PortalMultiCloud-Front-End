package br.com.portal.modelRelatorio;

import java.util.List;
import java.util.Objects;

import br.com.portal.model.ModelAitivoRecursoModal;
import br.com.portal.model.ModelListAditivoProduto;
import br.com.portal.model.ModelListaRecursoContratoAditivo;

public class ModelRelClienteContrato {

	private String id_contrato;
	private String id_nuvem;
	private String mome_parceiro;
	private String id_fase_contrato;
	private String fase_contrato;
	private String id_status_contrato;
	private String status_contrato;
	private String id_cliente;
	private String nome_cliente;
	private String id_servico_contratado;
	private String desc_servico;
	private String id_site;
	private String nome_site;
	private String id_moeda;
	private String moeda;
	private String pep;
	private String login_cadastro;
	private String valor_total;
	private String valor_convertido;
	private String custo_total;
	private String cotacao_moeda;
	List<ModelListaRecursoContratoAditivo> listaRecursoContratos;
	List<ModelListAditivoProduto> listaAditivoProdutos;
	List<ModelAitivoRecursoModal> listaAitivoRecursos;

	public List<ModelListAditivoProduto> getListaAditivoProdutos() {
		return listaAditivoProdutos;
	}
	public void setListaAditivoProdutos(List<ModelListAditivoProduto> listaAditivoProdutos) {
		this.listaAditivoProdutos = listaAditivoProdutos;
	}
	public List<ModelAitivoRecursoModal> getListaAitivoRecursos() {
		return listaAitivoRecursos;
	}
	public void setListaAitivoRecursos(List<ModelAitivoRecursoModal> listaAitivoRecursos) {
		this.listaAitivoRecursos = listaAitivoRecursos;
	}
	public String getNome_site() {
		return nome_site;
	}
	public void setNome_site(String nome_site) {
		this.nome_site = nome_site;
	}
	public String getNome_cliente() {
		return nome_cliente;
	}
	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
	public List<ModelListaRecursoContratoAditivo> getListaRecursoContratos() {
		return listaRecursoContratos;
	}
	public void setListaRecursoContratos(List<ModelListaRecursoContratoAditivo> listaRecursoContratos) {
		this.listaRecursoContratos = listaRecursoContratos;
	}
	
	public String getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(String id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public String getMome_parceiro() {
		return mome_parceiro;
	}
	public void setMome_parceiro(String mome_parceiro) {
		this.mome_parceiro = mome_parceiro;
	}
	public String getId_fase_contrato() {
		return id_fase_contrato;
	}
	public void setId_fase_contrato(String id_fase_contrato) {
		this.id_fase_contrato = id_fase_contrato;
	}
	public String getFase_contrato() {
		return fase_contrato;
	}
	public void setFase_contrato(String fase_contrato) {
		this.fase_contrato = fase_contrato;
	}
	public String getId_status_contrato() {
		return id_status_contrato;
	}
	public void setId_status_contrato(String id_status_contrato) {
		this.id_status_contrato = id_status_contrato;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getId_servico_contratado() {
		return id_servico_contratado;
	}
	public void setId_servico_contratado(String id_servico_contratado) {
		this.id_servico_contratado = id_servico_contratado;
	}
	public String getDesc_servico() {
		return desc_servico;
	}
	public void setDesc_servico(String desc_servico) {
		this.desc_servico = desc_servico;
	}
	public String getId_site() {
		return id_site;
	}
	public void setId_site(String id_site) {
		this.id_site = id_site;
	}
	public String getId_moeda() {
		return id_moeda;
	}
	public void setId_moeda(String id_moeda) {
		this.id_moeda = id_moeda;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public String getLogin_cadastro() {
		return login_cadastro;
	}
	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}
	public String getValor_total() {
		return valor_total;
	}
	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}
	public String getValor_convertido() {
		return valor_convertido;
	}
	public void setValor_convertido(String valor_convertido) {
		this.valor_convertido = valor_convertido;
	}
	public String getCusto_total() {
		return custo_total;
	}
	public void setCusto_total(String custo_total) {
		this.custo_total = custo_total;
	}
	public String getCotacao_moeda() {
		return cotacao_moeda;
	}
	public void setCotacao_moeda(String cotacao_moeda) {
		this.cotacao_moeda = cotacao_moeda;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id_cliente, id_contrato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelRelClienteContrato other = (ModelRelClienteContrato) obj;
		return Objects.equals(id_cliente, other.id_cliente) && Objects.equals(id_contrato, other.id_contrato);
	}
	@Override
	public String toString() {
		return "ModelRelClienteContrato [id_contrato=" + id_contrato + ", id_nuvem=" + id_nuvem + ", mome_parceiro="
				+ mome_parceiro + ", id_fase_contrato=" + id_fase_contrato + ", fase_contrato=" + fase_contrato
				+ ", id_status_contrato=" + id_status_contrato + ", status_contrato=" + status_contrato
				+ ", id_cliente=" + id_cliente + ", nome_cliente=" + nome_cliente + ", id_servico_contratado="
				+ id_servico_contratado + ", desc_servico=" + desc_servico + ", id_site=" + id_site + ", nome_site="
				+ nome_site + ", id_moeda=" + id_moeda + ", moeda=" + moeda + ", pep=" + pep + ", login_cadastro="
				+ login_cadastro + ", valor_total=" + valor_total + ", valor_convertido=" + valor_convertido
				+ ", custo_total=" + custo_total + ", cotacao_moeda=" + cotacao_moeda + ", listaRecursoContratos="
				+ listaRecursoContratos + ", listaAditivoProdutos=" + listaAditivoProdutos + ", listaAitivoRecursos="
				+ listaAitivoRecursos + "]";
	}

}
