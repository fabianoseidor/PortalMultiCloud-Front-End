package br.com.portal.Beandto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeanDtoGraficoVlrMes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	List<String> meses = new ArrayList<String>();
	List<Double> valores = new ArrayList<Double>();
	
	public List<String> getMeses() {
		return meses;
	}
	public void setMeses(List<String> meses) {
		this.meses = meses;
	}
	public List<Double> getValores() {
		return valores;
	}
	public void setValores(List<Double> valores) {
		this.valores = valores;
	}

	
	
}
