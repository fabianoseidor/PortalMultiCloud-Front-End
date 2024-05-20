package br.com.portal.model.consolidacao;

import java.util.Objects;

public class ModelVlrFaturado {
	
	private String pepFaturado;
	private Double vlrFaturado;
	private String vlrFaturadoView;
	
	private String pepAFaturar;
	private Double vlrAFaturar;
	private String vlrAFaturarView;
	
	private Double vlrDesvio;
	private Double vlrDesvioDiff;
	private String vlrDesvioDiffView;
	private String status;
	
	
	public String getVlrDesvioDiffView() {
		return vlrDesvioDiffView;
	}
	public void setVlrDesvioDiffView(String vlrDesvioDiffView) {
		this.vlrDesvioDiffView = vlrDesvioDiffView;
	}
	public Double getVlrDesvioDiff() {
		return vlrDesvioDiff;
	}
	public void setVlrDesvioDiff(Double vlrDesvioDiff) {
		this.vlrDesvioDiff = vlrDesvioDiff;
	}
	public String getVlrFaturadoView() {
		return vlrFaturadoView;
	}
	public void setVlrFaturadoView(String vlrFaturadoView) {
		this.vlrFaturadoView = vlrFaturadoView;
	}
	public String getVlrAFaturarView() {
		return vlrAFaturarView;
	}
	public void setVlrAFaturarView(String vlrAFaturarView) {
		this.vlrAFaturarView = vlrAFaturarView;
	}
	public Double getVlrDesvio() {
		return vlrDesvio;
	}
	public void setVlrDesvio(Double vlrDesvio) {
		this.vlrDesvio = vlrDesvio;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPepFaturado() {
		return pepFaturado;
	}
	public void setPepFaturado(String pepFaturado) {
		this.pepFaturado = pepFaturado;
	}
	public Double getVlrFaturado() {
		return vlrFaturado;
	}
	public void setVlrFaturado(Double vlrFaturado) {
		this.vlrFaturado = vlrFaturado;
	}
	public String getPepAFaturar() {
		return pepAFaturar;
	}
	public void setPepAFaturar(String pepAFaturar) {
		this.pepAFaturar = pepAFaturar;
	}
	public Double getVlrAFaturar() {
		return vlrAFaturar;
	}
	public void setVlrAFaturar(Double vlrAFaturar) {
		this.vlrAFaturar = vlrAFaturar;
	}


	

	@Override
	public int hashCode() {
		return Objects.hash(pepAFaturar, pepFaturado, status, vlrAFaturar, vlrAFaturarView, vlrDesvio, vlrFaturado,
				vlrFaturadoView);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelVlrFaturado other = (ModelVlrFaturado) obj;
		return Objects.equals(pepAFaturar, other.pepAFaturar) && Objects.equals(pepFaturado, other.pepFaturado)
				&& Objects.equals(status, other.status) && Objects.equals(vlrAFaturar, other.vlrAFaturar)
				&& Objects.equals(vlrAFaturarView, other.vlrAFaturarView) && Objects.equals(vlrDesvio, other.vlrDesvio)
				&& Objects.equals(vlrFaturado, other.vlrFaturado)
				&& Objects.equals(vlrFaturadoView, other.vlrFaturadoView);
	}
	@Override
	public String toString() {
		return "ModelVlrFaturado [pepFaturado=" + pepFaturado + ", vlrFaturado=" + vlrFaturado + ", vlrFaturadoView="
				+ vlrFaturadoView + ", pepAFaturar=" + pepAFaturar + ", vlrAFaturar=" + vlrAFaturar
				+ ", vlrAFaturarView=" + vlrAFaturarView + ", vlrDesvio=" + vlrDesvio + ", vlrDesvioDiff="
				+ vlrDesvioDiff + ", vlrDesvioDiffView=" + vlrDesvioDiffView + ", status=" + status + "]";
	}


}
