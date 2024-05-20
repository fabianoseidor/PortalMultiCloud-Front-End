package br.com.portal.model;

public class ModelRscunho {
	
	private Long    id_contrato;
	private Long    id_aditivo;
	private Long    id_cliente;
	private String  status_contrato;
	private String  qty_tempo_rascunho;
	private String  dt_update_status;
	private String  motivo_rascunho;
	private String  tipo_rascunho;
	private String  razao_social;
	private String  qty_usuario_contratada;
	private String  vl_total;
	private String  vigencia;
	
	
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_aditivo() {
		return id_aditivo;
	}
	public void setId_aditivo(Long id_aditivo) {
		this.id_aditivo = id_aditivo;
	}
	public String getQty_tempo_rascunho() {
		return qty_tempo_rascunho;
	}
	public void setQty_tempo_rascunho(String qty_tempo_rascunho) {
		this.qty_tempo_rascunho = qty_tempo_rascunho;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getTipo_rascunho() {
		return tipo_rascunho;
	}
	public void setTipo_rascunho(String tipo_rascunho) {
		this.tipo_rascunho = tipo_rascunho;
	}
	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getStatus_contrato() {
		return status_contrato;
	}
	public void setStatus_contrato(String status_contrato) {
		this.status_contrato = status_contrato;
	}
	public String getDt_update_status() {
		return dt_update_status;
	}
	public void setDt_update_status(String dt_update_status) {
		this.dt_update_status = dt_update_status;
	}
	public String getMotivo_rascunho() {
		return motivo_rascunho;
	}
	public void setMotivo_rascunho(String motivo_rascunho) {
		this.motivo_rascunho = motivo_rascunho;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getQty_usuario_contratada() {
		return qty_usuario_contratada;
	}
	public void setQty_usuario_contratada(String qty_usuario_contratada) {
		this.qty_usuario_contratada = qty_usuario_contratada;
	}
	public String getVl_total() {
		return vl_total;
	}
	public void setVl_total(String vl_total) {
		this.vl_total = vl_total;
	}
	@Override
	public String toString() {
		return "ModelRscunho [id_contrato=" + id_contrato + ", status_contrato=" + status_contrato
				+ ", qty_tempo_rascunho=" + qty_tempo_rascunho + ", dt_update_status=" + dt_update_status
				+ ", motivo_rascunho=" + motivo_rascunho + ", tipo_rascunho=" + tipo_rascunho + ", razao_social="
				+ razao_social + ", qty_usuario_contratada=" + qty_usuario_contratada + ", vl_total=" + vl_total
				+ ", vigencia=" + vigencia + "]";
	}
	
	
}
