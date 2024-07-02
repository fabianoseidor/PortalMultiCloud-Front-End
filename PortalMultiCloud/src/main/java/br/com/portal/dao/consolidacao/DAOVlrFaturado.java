package br.com.portal.dao.consolidacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.consolidacao.ModelVlrFaturado;

public class DAOVlrFaturado {
	
	private Connection connection;
	
	public DAOVlrFaturado() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelVlrFaturado> getvlrFaturado(Double desvioPadrao ){
		
		List<ModelVlrFaturado> listVlrFaturados = new ArrayList<ModelVlrFaturado>();
		
		// String sql = "SELECT RTRIM( PEP ) AS PEP, VLR_TOTAL FROM VIEW_VALOR_CONTRATO_ADITIVO ORDER BY PEP";
		String sql = "SELECT RTRIM( PEP ) AS PEP,  SUM(VLR_TOTAL) AS VLR_TOTAL FROM VIEW_VALOR_CONTRATO_ADITIVO GROUP BY PEP ORDER BY PEP";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelVlrFaturado listVlrFaturado = new ModelVlrFaturado();
				listVlrFaturado.setPepFaturado(set.getString("PEP"));
				listVlrFaturado.setVlrFaturado(set.getDouble("VLR_TOTAL"));
				listVlrFaturado.setVlrDesvio( desvioPadrao  );

				listVlrFaturados.add(listVlrFaturado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listVlrFaturados;
		
	}
	
	
	public List<ModelVlrFaturado> getvlrAFaturar( Integer mes, Integer ano, Double desvioPadrao ){
		
		List<ModelVlrFaturado> listVlrFaturados = this.getvlrFaturado( desvioPadrao );
		List<ModelVlrFaturado> listVlrAFaturar = new ArrayList<ModelVlrFaturado>();
		String sql = "SELECT RTRIM( PEP ) AS PEP, SUM(VALOR) VLR FROM AFATURA AS AF WHERE ANO = ? AND MES = ? GROUP BY PEP ORDER BY PEP";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt( 1,  ano );
			ps.setInt( 2,  mes );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				boolean isfind = false;
				String pepAFaturar = set.getString("PEP").trim();
				for(int i = 0; i < listVlrFaturados.size(); i++ ) {	
				    String pepFaturado = listVlrFaturados.get(i).getPepFaturado().trim();
				    
					if( !pepFaturado.isEmpty() && pepFaturado != null ) {
/*						
						if( pepAFaturar.equals( "BR-MC-SUPLEY_PUB-HOST" ) ) 
							System.out.println("pepFaturado: " + pepFaturado + " - pepAFaturar: " + pepAFaturar);
												
						if( pepFaturado.equals( "BR-MC-SUPLEY_PUB-HOST" ) ) 
							System.out.println("pepFaturado: " + pepFaturado + " - pepAFaturar: " + pepAFaturar);
*/												
						if( pepFaturado.equals( pepAFaturar ) ) {
							
							listVlrFaturados.get(i).setPepAFaturar(set.getString("PEP"));
							listVlrFaturados.get(i).setVlrAFaturar(set.getDouble("VLR"));
							
							Double calucuVlrDesvioPadrao = set.getDouble("VLR") - listVlrFaturados.get(i).getVlrFaturado();
							Double vlrAbsoluto = Math.abs(calucuVlrDesvioPadrao);
//							if( vlrDesvioPadrao >=0 && vlrDesvioPadrao <= listVlrFaturados.get(i).getVlrDesvio()  ) {
							
						    if( vlrAbsoluto <= desvioPadrao ) {
								listVlrFaturados.get(i).setStatus("OK");
							}else {
								listVlrFaturados.get(i).setStatus("DIFF");
								listVlrFaturados.get(i).setVlrDesvioDiff(calucuVlrDesvioPadrao);
							}
							isfind = true;
							break;
						}
					}
				}

				if( !isfind ) {
				    ModelVlrFaturado listVlrFaturado = new ModelVlrFaturado();
				    listVlrFaturado.setPepAFaturar(set.getString("PEP"));
				    listVlrFaturado.setVlrAFaturar(set.getDouble("VLR"));
				    listVlrFaturado.setStatus("NOT_EXIST_AFATURA_NAO_TEM_NO_FATURADO");
				    listVlrAFaturar.add(listVlrFaturado);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if( listVlrAFaturar.size() > 0 )listVlrFaturados.addAll(listVlrAFaturar);
		
		for( ModelVlrFaturado lista : listVlrFaturados ) 
			if( lista.getStatus() == null ) lista.setStatus("NOT_EXIST_FATURADO_NAO_TEM_NO_AFATURA");

		return listVlrFaturados;
		
	}
	
	
	
	
	
}
