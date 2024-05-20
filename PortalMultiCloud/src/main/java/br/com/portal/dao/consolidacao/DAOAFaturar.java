package br.com.portal.dao.consolidacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.List;


import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.consolidacao.ModelAFaturar;

public class DAOAFaturar {
	private Connection connection;
	
	public DAOAFaturar() {
		connection = SingleConnectionBanco.getConnection();
	}
	
    //******************************************************************************************//
    //                                                                                          //
    //                                                                                          //
    //                                                                                          //
    //******************************************************************************************//
	public int gravarAFaturar( List<ModelAFaturar> listaAFatura ) throws Exception {
	    
	    if(istruncateTable()) truncateTable( );
		int i = 0;
		for (ModelAFaturar listaAFaturar : listaAFatura) {
		    i++;
			String sql = "INSERT INTO AFATURA (PEP, NOME_EMISSOR, DT_FATURAMENTO, ANO, MES, VALOR, MOEDA, VL_FATURAMENTO)"
					+ "                VALUES (?, ?, ?, ?, ?, ?, ?, ?)                                                   ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			String valor          = null;
			String vl_faturamento = null;
	
			prepareSql.setString( 1, listaAFaturar.getPep()            ); // PEP
			prepareSql.setString( 2, listaAFaturar.getNome_emissor()   ); // NOME_EMISSOR
			prepareSql.setString( 3, listaAFaturar.getDt_faturamento() ); // DT_FATURAMENTO
			prepareSql.setString( 4, listaAFaturar.getAno()            ); // ANO
			prepareSql.setString( 5, listaAFaturar.getMes()            ); // MES
	
			valor = listaAFaturar.getValor();
			if(valor != null && !valor.isEmpty()) {
				valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			    if(valor.indexOf(" ") >= 0 )
			    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
				else
					valor = valor.replaceAll("\\.", "").replaceAll("\\,", ".");
			}
	
			prepareSql.setString( 6, valor                    ); // VALOR
			prepareSql.setString( 7, listaAFaturar.getMoeda() ); // MOEDA
			
			vl_faturamento = listaAFaturar.getVl_faturamento();		
			if(vl_faturamento != null && !vl_faturamento.isEmpty()) {
				vl_faturamento = Normalizer.normalize(vl_faturamento, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			    if(vl_faturamento.indexOf(" ") >= 0 )
			    	vl_faturamento = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
				else
					vl_faturamento = vl_faturamento.replaceAll("\\.", "").replaceAll("\\,", ".");
			}
			prepareSql.setString ( 8, vl_faturamento ); // VL_FATURAMENTO
			
			prepareSql.execute();
			connection.commit();
//			if (i % 1000 == 0){ System.out.println("Quantidade de linhas inseridos: " + i); }
			
			// verifica se exite PEP para ser atualizado, vindo do SAP.
			atualizaPepSap();
		}	
//		System.out.println("Quantidade de linhas inseridos: " + i);
		return i;
    }
	
	public void truncateTable( ) {
		String sql = "TRUNCATE TABLE AFATURA";
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean istruncateTable() throws SQLException {
		String sql = "SELECT count(1) as existe FROM AFATURA";
		PreparedStatement statemet = connection.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		while (resutado.next()) return resutado.getBoolean("existe");
		return false;
	}

	public String[] vlrsFaturamento() throws SQLException {
		String resutadoReturns[] = null;
		String sql = "SELECT                                                                "
				+ "    MOEDA                                                                "
				+ "  , CASE                                                                 "
				+ "       WHEN MOEDA = 'BRL' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'pt-br') "
				+ "       WHEN MOEDA = 'USD' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'en-us') "
				+ "       WHEN MOEDA = 'EUR' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'fr-FR') "
				+ "    END AS VL_FATURAMENTO                                                "
				+ "  FROM AFATURA                                                           "
				+ "  group by MOEDA  ";
		PreparedStatement statemet = connection.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		int rows = getTotalLinhas( );		
		int i = 0;
		resutadoReturns = new String[rows];
		while (resutado.next())  {
			resutadoReturns[i] = resutado.getString("MOEDA") + ";" + resutado.getString("VL_FATURAMENTO");
		    i++;
		}
		return resutadoReturns;
	}
	
	public int getTotalLinhas( ) throws SQLException {
		String sql = "select count(MOEDA) AS TOTAL from ( SELECT MOEDA,sum(VL_FATURAMENTO)AS VL_FATURAMENTO FROM AFATURA  group by MOEDA )as t";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getInt("TOTAL");
		
		return 0;
	}
	
	public String getVlrTotal( ) throws SQLException {
		String sql = "SELECT FORMAT(sum(VL_FATURAMENTO), 'C', 'pt-br') AS VL_FATURAMENTO FROM AFATURA";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getString("VL_FATURAMENTO");
		
		return "R$ 0,00";
	}
	
    public void atualizaPepSap() {
    	
		String sql = "INSERT INTO PEP_SAP ( PEP, NOME_CLIENTE )                                                       "
				   + "             SELECT DISTINCT PEP, NOME_EMISSOR                                                  "
				   + "               FROM AFATURA AS AF                                                               "
			 	   + "              WHERE NOT EXISTS ( SELECT *                                                       "
				   + "                                  FROM PEP_SAP PS                                               "
				   + "                                 WHERE PS.PEP = AF.PEP AND PS.NOME_CLIENTE = AF.NOME_EMISSOR )  ";
		
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
	 	
	

}
