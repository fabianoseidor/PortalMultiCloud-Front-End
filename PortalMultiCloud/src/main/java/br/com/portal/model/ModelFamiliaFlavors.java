package br.com.portal.model;

public class ModelFamiliaFlavors {
	
	private Long id_familia_flavors;
	private Long id_nuvem;
	private String familia;
    private String cpu;
	private String ram;
    private String valor;
	private String  dt_criacao;
	private String  observacao;

	public boolean isNovo() {
    	
    	if(this.id_familia_flavors == null) {
    		return true;
    	}else if( this.id_familia_flavors != null && this.id_familia_flavors > 0 ) {
    		return false;
    	}
    	return this.id_familia_flavors == null;
    }
	
	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}
	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String toString() {
		return "ModelFamiliaFlavors [id_familia_flavors=" + id_familia_flavors + ", id_nuvem=" + id_nuvem + ", familia="
				+ familia + ", cpu=" + cpu + ", ram=" + ram + ", valor=" + valor + "]";
	}

	
}
