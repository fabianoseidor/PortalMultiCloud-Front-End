package br.com.portal.model;

public class ModelNuvem {

	private Long id_nuvem;
	private String dt_criacao;
	private String mome_parceiro;
	private String observacao;
	
	public boolean isNovo() {
    	
    	if(this.id_nuvem == null) {
    		return true;
    	}else if( this.id_nuvem != null && this.id_nuvem > 0 ) {
    		return false;
    	}
    	return this.id_nuvem == null;
    }

	public Long getId_nuvem() {
		return id_nuvem;
	}

	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	public String getMome_parceiro() {
		return mome_parceiro;
	}

	public void setMome_parceiro(String mome_parceiro) {
		this.mome_parceiro = mome_parceiro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	


}
