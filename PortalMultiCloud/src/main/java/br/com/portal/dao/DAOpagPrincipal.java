package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.portal.Beandto.BeanDtoGraficoVlrMes;
import br.com.portal.connection.SingleConnectionBanco;

public class DAOpagPrincipal {
	private Connection connection;
	public DAOpagPrincipal() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public String getVlrTotalContratos( ) throws SQLException {
		String sql = "SELECT SUM(VALOR_TOTAL) AS VALOR_TOTAL "
				   + "  FROM CONTRATO CON                    "
				   +  "WHERE CON.ID_STATUS_CONTRATO = 1      ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		ResultSet resutado = prepareSql.executeQuery();
		Locale localeBR = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

		while (resutado.next()) {
			return dinheiro.format(resutado.getDouble("VALOR_TOTAL") );
			
		}
		return "";
	}

	public String getQtyContratoAtivos( ) throws SQLException {
		String sql = "SELECT COUNT(1) AS CONT_ATIVOS                 "
				   + "  FROM CONTRATO CON                    "
				   +  "WHERE CON.ID_STATUS_CONTRATO = 1      ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) return resutado.getString("CONT_ATIVOS");

		return "";
	}
	
	public Integer getQtyContrato( ) throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL_CONT                 "
				   + "  FROM CONTRATO CON                    ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) return resutado.getInt("TOTAL_CONT");

		return 0;
	}	
	
	public String getQtyContratoTemporario( ) throws SQLException {
		String sql = "SELECT COUNT(RECURSO_TEMPORARIO) AS TOTAL_TEMP "
				+ "  FROM ADITIVADO ADT                              "
				+ "  WHERE ADT.RECURSO_TEMPORARIO = 1                "
				+ "    AND ADT.ID_STATUS_ADITIVO  = 1                ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) return resutado.getString("TOTAL_TEMP");

		return "";
	}
	
	public Double getQtyAditivosAtivos( ) throws SQLException {
		String sql = "SELECT COUNT(1) AS TOTAL_ADIT "
				+ "  FROM ADITIVADO ADT                              "
				+ "  WHERE ADT.ID_STATUS_ADITIVO  = 1                ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) return resutado.getDouble("TOTAL_ADIT");

		return 0.0;
	}	

	public String VlrContratosAditivoMesAtual( ) throws SQLException {
		String sql = "SELECT SUM(ADT.VLR_TOTAL_ADIT) + SUM(CONT.VALOR_TOTAL) AS TORAL_CONT_ADIT "
				+ "  FROM                                                                       "
				+ "     ADITIVADO AS ADT                                                        "
				+ "   , CONTRATO  AS CONT                                                       "
				+ "WHERE ADT.ID_STATUS_ADITIVO   = 1                                            "
				+ "  AND ADT.ID_CONTRATO         =  CONT.ID_CONTRATO                            "
				+ "  AND CONT.ID_STATUS_CONTRATO = 1                                            "
				+ "  AND CONT.DT_CRIACAO   BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -1))   "
				+ "                            AND EOMONTH (GETDATE() )                         ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		ResultSet resutado = prepareSql.executeQuery();
		Locale localeBR = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

		while (resutado.next()) return dinheiro.format(resutado.getDouble("TORAL_CONT_ADIT") );

		return "";
	}

	public Double VlrAditivoMesAnterior( ) throws SQLException {
		String sql = " SELECT SUM(ADT.VLR_TOTAL_ADIT)  VLR_TOTAL_ADIT                           "
				   + "   FROM ADITIVADO AS ADT                                                  "
				   + "  WHERE ADT.ID_STATUS_ADITIVO = 1                                         "
				   + "    AND ADT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -2)) "
				   + "				             AND EOMONTH (GETDATE (), -1)                   ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getDouble("VLR_TOTAL_ADIT") ;

		return 0.0;
	}

	public Double VlrContratosMesAnterior( ) throws SQLException {
		String sql = " SELECT SUM(CONT.VALOR_TOTAL) VLR_TOTAL_CONT                               "
				   + "   FROM CONTRATO AS CONT                                                   "
				   + "  WHERE CONT.ID_STATUS_CONTRATO = 1                                        "
				   + "    AND CONT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -2)) "
				   + "				              AND EOMONTH (GETDATE (), -1)                   ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) return resutado.getDouble("VLR_TOTAL_CONT") ;

		return 0.0;
	}

	
	public Double VlrContratoTrimestri( ) throws SQLException {
		String sql = "SELECT                                                                "
				+ "    CASE WHEN                                                            "
				+ "        SUM(CONT.VALOR_TOTAL) IS NULL THEN 0                             "
				+ "	 ELSE  SUM(CONT.VALOR_TOTAL) END AS TOTAL_CONT                          "
				+ "  FROM CONTRATO  AS CONT                                                 "
				+ "where  CONT.ID_STATUS_CONTRATO = 1                                       "
				+ "  AND CONT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -4)) "
				+ "                          AND EOMONTH (GETDATE (), -1)                   ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getDouble("TOTAL_CONT");

		return 0.0;
	}
	
	public Double VlrAditivoTrimestri( ) throws SQLException {
		String sql = "SELECT                                                               "
				+ "     CASE WHEN                                                          "
				+ "        SUM(ADT.VLR_TOTAL_ADIT) IS NULL THEN 0                          "
				+ "	 ELSE  SUM(ADT.VLR_TOTAL_ADIT) END AS TOTAL_ADIT                       "
				+ "  FROM ADITIVADO AS ADT                                                 "
				+ " where ADT.ID_STATUS_ADITIVO = 1                                        "
				+ "  AND ADT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -4)) "
				+ "                          AND EOMONTH (GETDATE (), -1)                  ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getDouble("TOTAL_ADIT");

		return 0.0;
	}
/*******************************************************************************************/
	
	
	public Integer QtyContratoTrimestri( ) throws SQLException {
		String sql = "SELECT                                                                "
				+ "    CASE WHEN                                                            "
				+ "        COUNT(1) IS NULL THEN 0                                          "
				+ "	 ELSE  COUNT(1) END AS TOTAL_CONT                                       "
				+ "  FROM CONTRATO  AS CONT                                                 "
				+ "where  CONT.ID_STATUS_CONTRATO = 1                                       "
				+ "  AND CONT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -4)) "
				+ "                          AND EOMONTH (GETDATE (), -1)                   "
				+ "";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getInt("TOTAL_CONT");

		return 0;
	}
	
	public Integer QtyAditivoTrimestri( ) throws SQLException {
		String sql = "SELECT                                                               "
				+ "     CASE WHEN                                                          "
				+ "        COUNT(1) IS NULL THEN 0                                         "
				+ "	 ELSE  COUNT(1) END AS TOTAL_ADIT                                      "
				+ "  FROM ADITIVADO AS ADT                                                 "
				+ " where ADT.ID_STATUS_ADITIVO = 1                                        "
				+ "  AND ADT.DT_CRIACAO BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -4)) "
				+ "                          AND EOMONTH (GETDATE (), -1)                  ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getInt("TOTAL_ADIT");

		return 0;
	}
	
	
	public BeanDtoGraficoVlrMes montaGrafico( ) throws SQLException {
		String sql = " SELECT  DATENAME(MONTH, DT_CRIACAO) AS MES                           "
				   + "      ,SUM(VALOR_TOTAL) AS VLR                                        "
				   + "  FROM CONTRATO                                                       "
			       + "  where DT_CRIACAO > convert(date, DATEADD(day, -365, GETDATE()),105) "
				   + "  AND ID_STATUS_CONTRATO = 1                                          "
				   + "  GROUP BY  DATENAME(MONTH, DT_CRIACAO)                               ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		List<String> meses = new ArrayList<String>();
		List<Double> valores = new ArrayList<Double>();
		BeanDtoGraficoVlrMes beanDtoGraficoVlrMes = new BeanDtoGraficoVlrMes();		
		while (resutado.next()) { 
			String mes   = resutado.getString("MES");
			Double valor = resutado.getDouble("VLR");
			meses.add(mes);
			valores.add(valor);
		}

		beanDtoGraficoVlrMes.setMeses(meses);
		beanDtoGraficoVlrMes.setValores(valores);
		
		 return beanDtoGraficoVlrMes;
	}
	
	public Integer QtyContratosVencidosUltimos3Meses( ) throws SQLException {
		String sql = "SELECT COUNT(1) AS QTY_DT                                              "
			       + "  FROM VIGENCIA AS VIG                                                 "
			       + " WHERE VIG.DT_FINAL BETWEEN DATEADD (DAY, 1, EOMONTH (GETDATE (), -1)) "
			       + "                        AND EOMONTH (GETDATE (), 2)                    ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getInt("QTY_DT");

		return 0;
	}
	
	public Integer QtyContratosCadastrados( ) throws SQLException {
		String sql = "SELECT TOP (10) COUNT (ID_CONTRATO) AS QTY_CONTRATOS FROM CONTRATO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		while (resutado.next()) return resutado.getInt("QTY_CONTRATOS");

		return 0;
	}
	
}
