package br.com.portal.model;



public class ModelRecursoAditivo {
	
    private Long   id_recurso;
    private Long   id_status_recurso;
    private Long   id_retencao_backup;
    private Long   id_tipo_disco;
    private Long   id_so;
    private Long   id_ambiente;
    private Long   id_familia_flavors;
    private Long   id_tipo_servico;
    private String dt_cadastro;
    private String tag_vcenter;
    private String obs;
    private Long   id_contrato;
    private Long   id_aditivado;

	private Long id_cliente;
	private String nomeCliente;

	private Long id_nuvem;
	private String nuvem;
	
	private String cpu;               
	private String ram;               
	private String valor;  
	

	public boolean isNovo() {
    	
    	if(this.id_recurso == null) {
    		return true;
    	}else if( this.id_recurso != null && this.id_recurso > 0 ) {
    		return false;
    	}
    	
    	return this.id_recurso == null;
    }

   public Long getId_nuvem() {
		return id_nuvem;
	}

	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}

	public String getNuvem() {
		return nuvem;
	}

	public void setNuvem(String nuvem) {
		this.nuvem = nuvem;
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

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(String dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public Long getId_recurso() {
		return id_recurso;
	}

	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}

	public Long getId_status_recurso() {
		return id_status_recurso;
	}

	public void setId_status_recurso(Long id_status_recurso) {
		this.id_status_recurso = id_status_recurso;
	}

	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}

	public void setId_retencao_backup(Long id_retencao_backup) {
		this.id_retencao_backup = id_retencao_backup;
	}

	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}

	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}

	public Long getId_so() {
		return id_so;
	}

	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}

	public Long getId_ambiente() {
		return id_ambiente;
	}

	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}

	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}

	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}

	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}

	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}


	public String getTag_vcenter() {
		return tag_vcenter;
	}


	public void setTag_vcenter(String tag_vcenter) {
		this.tag_vcenter = tag_vcenter;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public Long getId_contrato() {
		return id_contrato;
	}


	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}


	public Long getId_aditivado() {
		return id_aditivado;
	}


	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}


	public String toString() {
		return "ModelRecursoContrato [id_recurso=" + id_recurso + ", id_status_recurso=" + id_status_recurso
				+ ", id_retencao_backup=" + id_retencao_backup + ", id_tipo_disco=" + id_tipo_disco + ", id_so=" + id_so
				+ ", id_ambiente=" + id_ambiente + ", id_familia_flavors=" + id_familia_flavors + ", id_tipo_servico="
				+ id_tipo_servico + ", dt_cadastro=" + dt_cadastro + ", tag_vcenter=" + tag_vcenter + ", obs=" + obs
				+ ", id_contrato=" + id_contrato + ", id_aditivado=" + id_aditivado + ", id_cliente=" + id_cliente
				+ ", nomeCliente=" + nomeCliente + ", id_nuvem=" + id_nuvem + ", nuvem=" + nuvem + ", cpu=" + cpu
				+ ", ram=" + ram + ", valor=" + valor + "]";
	}


    
}
