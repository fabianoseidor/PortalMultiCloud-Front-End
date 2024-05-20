package br.com.portal.model;

public class ModelParticao {

	private Long   id_particao;
	private Long   id_recurso;
	private String quantidade;
	private String tamanho;
	private String descricao;
	
	public Long getId_particao() {
		return id_particao;
	}
	public void setId_particao(Long id_particao) {
		this.id_particao = id_particao;
	}
	public Long getId_recurso() {
		return id_recurso;
	}
	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return "ModelParticao [id_particao=" + id_particao + ", id_recurso=" + id_recurso + ", quantidade=" + quantidade
				+ ", tamanho=" + tamanho + ", descricao=" + descricao + "]";
	}

	

}
