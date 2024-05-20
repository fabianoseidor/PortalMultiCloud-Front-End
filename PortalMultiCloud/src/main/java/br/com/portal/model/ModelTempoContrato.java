package br.com.portal.model;

public class ModelTempoContrato {

	private Long id_tempo_contrato;
	private String desc_tempo;
	private int tempo_dia;
	private int tempo_semana;
	private int tempo_meses;
	private int tempo_ano;
	
	public Long getId_tempo_contrato() {
		return id_tempo_contrato;
	}
	public void setId_tempo_contrato(Long id_tempo_contrato) {
		this.id_tempo_contrato = id_tempo_contrato;
	}
	public String getDesc_tempo() {
		return desc_tempo;
	}
	public void setDesc_tempo(String desc_tempo) {
		this.desc_tempo = desc_tempo;
	}
	public int getTempo_dia() {
		return tempo_dia;
	}
	public void setTempo_dia(int tempo_dia) {
		this.tempo_dia = tempo_dia;
	}
	public int getTempo_semana() {
		return tempo_semana;
	}
	public void setTempo_semana(int tempo_semana) {
		this.tempo_semana = tempo_semana;
	}
	public int getTempo_meses() {
		return tempo_meses;
	}
	public void setTempo_meses(int tempo_meses) {
		this.tempo_meses = tempo_meses;
	}
	public int getTempo_ano() {
		return tempo_ano;
	}
	public void setTempo_ano(int tempo_ano) {
		this.tempo_ano = tempo_ano;
	}

	public String toString() {
		return "ModelTempoContrato [id_tempo_contrato=" + id_tempo_contrato + ", desc_tempo=" + desc_tempo
				+ ", tempo_dia=" + tempo_dia + ", tempo_semana=" + tempo_semana + ", tempo_meses=" + tempo_meses
				+ ", tempo_ano=" + tempo_ano + "]";
	}
	
	
}
