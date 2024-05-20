package br.com.portal.dao.relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.modelRelatorio.ModelClientesPorPeiodoEntrada;

public class DAOClientesPorPeiodoEntrada {
	private Connection connection;
	
	public DAOClientesPorPeiodoEntrada() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelClientesPorPeiodoEntrada> getListaClientesPorPeiodoEntrada( String dtInicio, String dtFinal ) throws Exception {

		List<ModelClientesPorPeiodoEntrada> listaClientesPorPeiodoEntrada = null;
		
		String sql = "SELECT                                                                                                      "
				 + "  TIPO_CONTRATO                                                                                               "
				 + ", PEP                                                                                                         "
				 + ", ID_CONTRATO                                                                                                 "
				 + ", RAZAO_SOCIAL                                                                                                "
				 + ", LOGIN_CADASTRO                                                                                              "
				 + ", CASE                                                                                                        "
				 + "        WHEN SUPORTE_B1 IS NULL THEN ' - '                                                                    "
				 + "        ELSE SUPORTE_B1                                                                                       "
				 + "    END AS SUPORTE_B1                                                                                         "
				 + ", CASE                                                                                                        "
				 + "        WHEN COMERCIAL IS NULL THEN ' - '                                                                     "
				 + "        ELSE COMERCIAL                                                                                        "
				 + "    END AS COMERCIAL                                                                                          "
				 + ", DT_CRIACAO_CONTRATO                                                                                         "
				 + ", MOEDA                                                                                                       "
				 + ", CASE                                                                                                        "
				 + "        WHEN VALOR_CONVERTIDO IS NULL THEN FORMAT(0, 'C', 'pt-br')                                            "
				 + "        WHEN ID_MOEDA = 1 THEN FORMAT(VALOR_CONVERTIDO, 'C', 'pt-br')                                         "
				 + "        WHEN ID_MOEDA = 2 THEN FORMAT(VALOR_CONVERTIDO, 'C', 'en-us')                                         "
				 + "        WHEN ID_MOEDA = 3 THEN FORMAT(VALOR_CONVERTIDO, 'C', 'de-de')                                         "
				 + "    END AS VLR_TOTAL                                                                                          "
				 + ", CASE                                                                                                        "
				 + "        WHEN VALOR_CONVERTIDO IS NULL THEN ' - '                                                              "
				 + "        ELSE FORMAT(VALOR_CONVERTIDO, 'C', 'pt-br')                                                           "
				 + "    END AS VALOR_CONVERTIDO                                                                                   "
				 + ", CASE                                                                                                        "
				 + "        WHEN CUSTO_TOTAL IS NULL THEN ' - '                                                                   "
				 + "        ELSE FORMAT(CUSTO_TOTAL, 'C', 'pt-br')                                                                "
				 + "    END AS CUSTO_TOTAL                                                                                        "
				 + ", CASE                                                                                                        "
				 + "        WHEN COTACAO_MOEDA IS NULL THEN ' - '                                                                 "
				 + "        ELSE FORMAT(COTACAO_MOEDA, 'C', 'pt-br')                                                              "
				 + "    END AS COTACAO_MOEDA                                                                                      "
				 + ", DT_CRIACAO_VIGENCIA                                                                                         "
				 + ", DT_INICIO_VIGENCIA                                                                                          "
				 + ", DT_FINAL_VIGENCIA                                                                                           "
				 + ", QTY_DIAS_VIGENCIA                                                                                           "
		         + "FROM  (                                                                                                       "
		         + "        SELECT                                                                                                "
		         + "              'ADITIVO'               TIPO_CONTRATO                                                           "
		         + "            , CON.PEP                 PEP                                                                     "
		         + "            , CON.ID_CONTRATO         ID_CONTRATO                                                             "
		         + "            , CLI.RAZAO_SOCIAL        RAZAO_SOCIAL                                                            "
		         + "            , CON.LOGIN_CADASTRO      LOGIN_CADASTRO                                                          "
		         + "            , SUB.NOME_SUPORTE        SUPORTE_B1                                                              "
		         + "            , COM.NOME_COMERCIAL      COMERCIAL                                                               "
		         + "            , CONVERT(VARCHAR, ADI.DT_CRIACAO, 22)  DT_CRIACAO_CONTRATO                                       "
		         + "            , ADI.ID_MOEDA            ID_MOEDA                                                                "
		         + "            , MOE.MOEDA               MOEDA                                                                   "
		         + "            , ADI.VLR_TOTAL_ADIT      VLR_TOTAL                                                               "
		         + "            , ADI.VALOR_CONVERTIDO    VALOR_CONVERTIDO                                                        "
		         + "            , ADI.CUSTO_TOTAL         CUSTO_TOTAL                                                             "
		         + "            , ADI.COTACAO_MOEDA       COTACAO_MOEDA                                                           "
		         + "            , CONVERT(VARCHAR, VIG.DT_CRIACAO, 131)  DT_CRIACAO_VIGENCIA                                      "
		         + "            , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                        "
		         + "            , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                         "
		         + "            , DATEDIFF( DAY , VIG.DT_INICIO , VIG.DT_FINAL)  AS QTY_DIAS_VIGENCIA                             "
		         + "        FROM CONTRATO               AS CON                                                                    "
		         + "        LEFT JOIN CLIENTE           AS CLI                                                                    "
		         + "               ON CLI.ID_CLIENTE    = CON.ID_CLIENTE                                                          "
		         + "        LEFT JOIN SUPORTE_B1        AS SUB                                                                    "
		         + "               ON SUB.ID_SUPORTE_B1    = CON.ID_SUPORTE_B1                                                    "
		         + "        LEFT JOIN COMERCIAL        AS COM                                                                     "
		         + "               ON COM.ID_COMERCIAL    = CON.ID_COMERCIAL                                                      "
		         + "        LEFT JOIN ADITIVADO         AS ADI                                                                    "
		         + "               ON ADI.ID_CONTRATO   = CON.ID_CONTRATO                                                         "
		         + "        LEFT JOIN MOEDA             AS MOE                                                                    "
		         + "               ON MOE.ID_MOEDA      = ADI.ID_MOEDA                                                            "
		         + "        LEFT JOIN VIGENCIA_ADITIVO  AS VIG                                                                    "
		         + "               ON  VIG.ID_ADITIVADO = ADI.ID_ADITIVADO                                                        "
		         + "       WHERE CON.ID_STATUS_CONTRATO IN ( 1, 4 )                                                               "
		         + "         AND ADI.ID_STATUS_ADITIVO  IN ( 1, 4 )                                                               "
		         + "UNION ALL                                                                                                     "
		         + "        SELECT                                                                                                "
		         + "            'CONTRATO PRINCIPAL'    TIPO_CONTRATO                                                             "
		         + "            , CON.PEP                 PEP                                                                     "
		         + "            , CON.ID_CONTRATO         ID_CONTRATO                                                             "
		         + "            , CLI.RAZAO_SOCIAL        RAZAO_SOCIAL                                                            "
		         + "            , CON.LOGIN_CADASTRO      LOGIN_CADASTRO                                                          "
		         + "            , SUB.NOME_SUPORTE        SUPORTE_B1                                                              "
		         + "            , COM.NOME_COMERCIAL      COMERCIAL                                                               "
		         + "            , CONVERT(VARCHAR, CON.DT_CRIACAO, 22)  DT_CRIACAO_CONTRATO                                       "
		         + "            , CON.ID_MOEDA            ID_MOEDA                                                                "
		         + "            , MOE.MOEDA               MOEDA                                                                   "
		         + "            , CON.VALOR_TOTAL         VLR_TOTAL                                                               "
		         + "            , CON.VALOR_CONVERTIDO    VALOR_CONVERTIDO                                                        "
		         + "            , CON.CUSTO_TOTAL         CUSTO_TOTAL                                                             "
		         + "            , CON.COTACAO_MOEDA       COTACAO_MOEDA                                                           "
		         + "            , CONVERT(VARCHAR, VIG.DT_CRIACAO, 131)  DT_CRIACAO_VIGENCIA                                      "
		         + "            , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                        "
		         + "            , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                         "
		         + "            , DATEDIFF( DAY , VIG.DT_INICIO , VIG.DT_FINAL)  AS QTY_DIAS_VIGENCIA                             "
		         + "        FROM CONTRATO               AS CON                                                                    "
		         + "            LEFT JOIN CLIENTE       AS CLI                                                                    "
		         + "                ON CLI.ID_CLIENTE   = CON.ID_CLIENTE                                                          "
		         + "        LEFT JOIN SUPORTE_B1        AS SUB                                                                    "
		         + "               ON SUB.ID_SUPORTE_B1 = CON.ID_SUPORTE_B1                                                       "
		         + "        LEFT JOIN COMERCIAL         AS COM                                                                    "
		         + "               ON COM.ID_COMERCIAL  = CON.ID_COMERCIAL                                                        "
		         + "            LEFT JOIN MOEDA         AS MOE                                                                    "
		         + "                ON MOE.ID_MOEDA     = CON.ID_MOEDA                                                            "
		         + "        LEFT JOIN VIGENCIA          AS VIG                                                                    "
		         + "                ON  VIG.ID_CONTRATO = CON.ID_CONTRATO                                                         "
		         + "        WHERE CON.ID_STATUS_CONTRATO IN ( 1, 4 )                                                              "
		         + ") AS SELECT_PRINCIPAL                                                                                         "
//		         + " WHERE DT_INICIO_VIGENCIA BETWEEN CONVERT(DATE, " + dtInicio + ", 103) AND CONVERT(DATE, " + dtFinal + ", 103)"
		         + " WHERE DT_INICIO_VIGENCIA BETWEEN CONVERT(DATE, ?, 103) AND CONVERT(DATE, ?, 103)                             "
		         + " ORDER BY                                                                                                     "
		         + "   ID_CONTRATO                                                                                                "
		         + " , TIPO_CONTRATO                                                                                              ";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setString(1, dtInicio);   
		prepareSql.setString(2, dtFinal );   
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  {

			ModelClientesPorPeiodoEntrada relClientesPorPeiodoEntrada = new ModelClientesPorPeiodoEntrada();
			
			relClientesPorPeiodoEntrada.setTipo_contrato      ( resutado.getString ("TIPO_CONTRATO")       );
			relClientesPorPeiodoEntrada.setPep                ( resutado.getString ("PEP")                 );
			relClientesPorPeiodoEntrada.setId_contrato        ( resutado.getString ("ID_CONTRATO")         );
			relClientesPorPeiodoEntrada.setRazao_social       ( resutado.getString ("RAZAO_SOCIAL")        );
			relClientesPorPeiodoEntrada.setLogin_cadastro     ( resutado.getString ("LOGIN_CADASTRO")      );
			relClientesPorPeiodoEntrada.setSuporte_b1         ( resutado.getString ("SUPORTE_B1")          );
			relClientesPorPeiodoEntrada.setComercial          ( resutado.getString ("COMERCIAL")           );
			relClientesPorPeiodoEntrada.setDt_criacao_contrato( resutado.getString ("DT_CRIACAO_CONTRATO") );
			relClientesPorPeiodoEntrada.setMoeda              ( resutado.getString ("MOEDA")               );
			relClientesPorPeiodoEntrada.setVlr_total          ( resutado.getString ("VLR_TOTAL")           );
			relClientesPorPeiodoEntrada.setValor_convertido   ( resutado.getString ("VALOR_CONVERTIDO")    );
			relClientesPorPeiodoEntrada.setCusto_total        ( resutado.getString ("CUSTO_TOTAL")         );
			relClientesPorPeiodoEntrada.setCotacao_moeda      ( resutado.getString ("COTACAO_MOEDA")       );
			relClientesPorPeiodoEntrada.setDt_criacao_vigencia( resutado.getString ("DT_CRIACAO_VIGENCIA") );
			relClientesPorPeiodoEntrada.setDt_inicio_vigencia ( resutado.getString ("DT_INICIO_VIGENCIA")  );
			relClientesPorPeiodoEntrada.setDt_final_vigencia  ( resutado.getString ("DT_FINAL_VIGENCIA")   );
			relClientesPorPeiodoEntrada.setQty_dias_vigencia  ( resutado.getString ("QTY_DIAS_VIGENCIA")   );
			
            if( listaClientesPorPeiodoEntrada == null ) listaClientesPorPeiodoEntrada = new ArrayList<ModelClientesPorPeiodoEntrada>();
            listaClientesPorPeiodoEntrada.add ( relClientesPorPeiodoEntrada );
		}

		return listaClientesPorPeiodoEntrada;
   }
	
	
}
