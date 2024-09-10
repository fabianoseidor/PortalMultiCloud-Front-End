package br.com.portal.dao.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.model.dashboard.ModalClientesInfo;

public class DAOClientesInfo {
	
	private Connection connection;
	
	public DAOClientesInfo() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModalClientesInfo> getListaClientesInfo( String dtInicio, String dtFim ) throws SQLException{
		List<ModalClientesInfo> mdClientesInfos = new ArrayList<ModalClientesInfo>();
		String sql = "SELECT                                                                                              "
				+ "      CON.ID_CONTRATO                                                                                  "
				+ "    , CLI.ID_CLIENTE                                                                                   "
				+ "    , CLI.RAZAO_SOCIAL                                                                                 "
				+ "    , CLI.NOME_FANTASIA                                                                                "
				+ "    , CLI.ALIAS                                                                                        "
				+ "    , CLI.CNPJ                                                                                         "
				+ "    , CON.DT_CRIACAO                                                                                   "
				+ "    , CON.PEP                                                                                          "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CON.ID_HUB_SPOT                                                                                  "
				+ "    , (SELECT COUNT(ID_CONTRATO) FROM ADITIVADO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTA_ADITIVOS  "
				+ "    , (SELECT  COUNT(ID_CONTRATO) FROM RECURSO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTAL_INSTANCIAS"
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO_CONTRATO_ORIGEM IS NOT NULL THEN 'RENOVAÇÃO'                                "
				+ "        ELSE '1º CONTRATO'                                                                             "
				+ "      END AS 'HISTORICO_CONTRATO'                                                                      "
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO IS NULL THEN ' - '                                                          "
				+ "       ELSE CON.RENOVACAO_CONTRATO_ORIGEM                                                              "
				+ "      END AS 'CONTRATO_ANTIGO'                                                                         "
				+ "    , STC.STATUS_CONTRATO                                                                              "
				+ "    , ( SELECT SUM(QTY_USUARIOS.QYT_USER ) AS QYT_USER                                                 "
				+ "         FROM                                                                                          "
				+ "        ( SELECT SUM( CPR.QTY_SERVICO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO              AS CON1                                                         "
				+ "       LEFT JOIN CONTRATO_PRODUTO AS CPR                                                               "
				+ "              ON CON1.ID_CONTRATO = CPR.ID_CONTRATO                                                    "
				+ "       LEFT JOIN PRODUTO          AS PRO                                                               "
				+ "              ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                       "
				+ "           WHERE CON1.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "             AND PRO.ID_PRODUTO  = 8                                                                   "
				+ "           GROUP BY CON1.ID_CONTRATO, CON1.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO      "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( CON2.QTY_USUARIO_CONTRATADA ) AS QYT_USER                                        "
				+ "            FROM CONTRATO AS CON2                                                                      "
				+ "           WHERE CON2.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( APR.QTY_PRODUTO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO AS CON3                                                                      "
				+ "           INNER JOIN  ADITIVADO        AS ADT                                                         "
				+ "                   ON ADT.ID_CONTRATO  = CON3.ID_CONTRATO                                              "
				+ "            LEFT JOIN ADITIVADO_PRODUTO AS APR                                                         "
				+ "                   ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                                              "
				+ "           WHERE APR.ID_PRODUTO  = 8                                                                   "
				+ "             AND CON3.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           GROUP BY CON3.ID_CONTRATO, CON3.PEP                                                         "
				+ "        )  AS QTY_USUARIOS                                                                             "
				+ "    )  AS QTY_USUARIO_CONTRATADA                                                                       "
				+ "    , NUV.MOME_PARCEIRO AS 'NUVEM'                                                                     "
				+ "    , SIT.NOME AS 'SITE'                                                                               "
				+ "    , SEC.DESC_SERVICO AS 'SERVICO_CONTRATADO'                                                         "
				+ "    , MOE.MOEDA                                                                                        "
				+ "    , FAC.FASE_CONTRATO                                                                                "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CIU.DESCRICAO AS 'CICLO_UPDATE'                                                                  "
				+ "    , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                         "
				+ "    , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                          "
				+ "    , VIG.DT_DESATIVACAO                                                                               "
				+ "    , DATEDIFF( DAY  , VIG.DT_INICIO , DT_FINAL)  AS QTY_DIAS_VIGENCIA                                 "
				+ "    , DATEDIFF( MONTH, VIG.DT_INICIO , DT_FINAL)  AS QTY_MESES_VIGENCIA                                "
				+ "    , TEC.DESC_TEMPO AS VIGENCIA                                                                       "
				+ "  FROM CLIENTE CLI                                                                                     "
				+ "    LEFT JOIN CONTRATO         AS CON                                                                  "
				+ "           ON CLI.ID_CLIENTE = CON.ID_CLIENTE                                                          "
				+ "   LEFT JOIN NUVEM               AS NUV                                                                "
				+ "          ON NUV.ID_NUVEM              = CON.ID_NUVEM                                                  "
				+ "  LEFT JOIN SITE                AS SIT                                                                 "
				+ "          ON SIT.ID_SITE               = CON.ID_SITE                                                   "
				+ "   LEFT JOIN SERVICO_CONTRATADO  AS SEC                                                                "
				+ "          ON SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO                                     "
				+ "   LEFT JOIN MOEDA               AS MOE                                                                "
				+ "          ON MOE.ID_MOEDA              = CON.ID_MOEDA                                                  "
				+ "   LEFT JOIN FASE_CONTRATO       AS FAC                                                                "
				+ "          ON FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO                                          "
				+ "   LEFT JOIN CICLO_UPDATE        AS CIU                                                                "
				+ "        ON CIU.ID_CICLO_UPDATE         = CON.ID_CICLO_UPDATE                                           "
				+ "   LEFT JOIN VIGENCIA            AS VIG                                                                "
				+ "          ON VIG.ID_CONTRATO           = CON.ID_CONTRATO                                               "
				+ "  LEFT JOIN TEMPO_CONTRATO       AS TEC                                                                "
				+ "          ON TEC.ID_TEMPO_CONTRATO = VIG.ID_TEMPO_CONTRATO                                             "
				+ "  LEFT JOIN STATUS_CONTRATO     AS STC                                                                 "
				+ "        ON STC.ID_STATUS_CONTRATO         = CON.ID_STATUS_CONTRATO                                     "
				+ "    WHERE CON.ID_STATUS_CONTRATO IN ( 1, 4 )                                                           "
				+ "      AND CON.DT_CRIACAO BETWEEN '" + dtInicio + "' AND '" + dtFim + "'                               	 ";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet set = ps.executeQuery();
		DAOUtil daoUtil = new DAOUtil();
		
		while(set.next()) {
			ModalClientesInfo mdClientesInfo = new ModalClientesInfo();			
			mdClientesInfo.setId_contrato           ( set.getString( "ID_CONTRATO"                                           ));
			mdClientesInfo.setId_cliente            ( set.getString( "ID_CLIENTE"                                            ));
			mdClientesInfo.setRazao_social          ( set.getString( "RAZAO_SOCIAL"                                          ));
			mdClientesInfo.setNome_fantasia         ( set.getString( "NOME_FANTASIA"                                         ));
			mdClientesInfo.setAlias                 ( set.getString( "ALIAS"                                                 ));
			mdClientesInfo.setCnpj                  ( set.getString( "CNPJ"                                                  ));
			mdClientesInfo.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")     ));
			mdClientesInfo.setPep                   ( set.getString( "PEP"                                                   ));
			mdClientesInfo.setLogin_cadastro        ( set.getString( "LOGIN_CADASTRO"                                        ));
			mdClientesInfo.setId_hub_spot           ( set.getString( "ID_HUB_SPOT"                                           ));
			mdClientesInfo.setTota_aditivos         ( set.getString( "TOTA_ADITIVOS"                                         ));
			mdClientesInfo.setTotal_instancias      ( set.getString( "TOTAL_INSTANCIAS"                                      ));
			mdClientesInfo.setHistorico_contrato    ( set.getString( "HISTORICO_CONTRATO"                                    ));
			mdClientesInfo.setContrato_antigo       ( set.getString( "CONTRATO_ANTIGO"                                       ));
			mdClientesInfo.setQty_usuario_contratada( set.getString( "QTY_USUARIO_CONTRATADA"                                ));
			mdClientesInfo.setNuvem                 ( set.getString( "NUVEM"                                                 ));
			mdClientesInfo.setSite                  ( set.getString( "SITE"                                                  ));
			mdClientesInfo.setServico_contratado    ( set.getString( "SERVICO_CONTRATADO"                                    ));
			mdClientesInfo.setMoeda                 ( set.getString( "MOEDA"                                                 ));
			mdClientesInfo.setFase_contrato         ( set.getString( "FASE_CONTRATO"                                         ));
			mdClientesInfo.setCiclo_update          ( set.getString( "CICLO_UPDATE"                                          ));
			mdClientesInfo.setDt_inicio_vigencia    ( set.getString( "DT_INICIO_VIGENCIA"                                    ));
			mdClientesInfo.setDt_final_vigencia     ( set.getString( "DT_FINAL_VIGENCIA"                                     ));
			mdClientesInfo.setQty_dias_vigencia     ( set.getString( "QTY_DIAS_VIGENCIA"                                     ));
			mdClientesInfo.setQty_meses_vigencia    ( set.getString( "QTY_MESES_VIGENCIA"                                    ));
			mdClientesInfo.setVigencia              ( set.getString( "VIGENCIA"                                              ));
			mdClientesInfo.setStatus                ( set.getString( "STATUS_CONTRATO"                                       ));
			mdClientesInfo.setDt_desativacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_DESATIVACAO") ));
			mdClientesInfos.add(mdClientesInfo);
		}
		// 
		return mdClientesInfos;		
	}
	
	public List<ModalClientesInfo> getListaClientesInfoDesativos( String dtInicio, String dtFim ) throws SQLException{
		List<ModalClientesInfo> mdClientesInfos = new ArrayList<ModalClientesInfo>();
		String sql = "SELECT                                                                                              "
				+ "      CON.ID_CONTRATO                                                                                  "
				+ "    , CLI.ID_CLIENTE                                                                                   "
				+ "    , CLI.RAZAO_SOCIAL                                                                                 "
				+ "    , CLI.NOME_FANTASIA                                                                                "
				+ "    , CLI.ALIAS                                                                                        "
				+ "    , CLI.CNPJ                                                                                         "
				+ "    , CON.DT_CRIACAO                                                                                   "
				+ "    , CON.PEP                                                                                          "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CON.ID_HUB_SPOT                                                                                  "
				+ "    , (SELECT COUNT(ID_CONTRATO) FROM ADITIVADO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTA_ADITIVOS  "
				+ "    , (SELECT  COUNT(ID_CONTRATO) FROM RECURSO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTAL_INSTANCIAS"
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO_CONTRATO_ORIGEM IS NOT NULL THEN 'RENOVAÇÃO'                                "
				+ "        ELSE '1º CONTRATO'                                                                             "
				+ "      END AS 'HISTORICO_CONTRATO'                                                                      "
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO IS NULL THEN ' - '                                                          "
				+ "       ELSE CON.RENOVACAO_CONTRATO_ORIGEM                                                              "
				+ "      END AS 'CONTRATO_ANTIGO'                                                                         "
				+ "    , STC.STATUS_CONTRATO                                                                              "
				+ "    , ( SELECT SUM(QTY_USUARIOS.QYT_USER ) AS QYT_USER                                                 "
				+ "         FROM                                                                                          "
				+ "        ( SELECT SUM( CPR.QTY_SERVICO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO              AS CON1                                                         "
				+ "       LEFT JOIN CONTRATO_PRODUTO AS CPR                                                               "
				+ "              ON CON1.ID_CONTRATO = CPR.ID_CONTRATO                                                    "
				+ "       LEFT JOIN PRODUTO          AS PRO                                                               "
				+ "              ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                       "
				+ "           WHERE CON1.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "             AND PRO.ID_PRODUTO  = 8                                                                   "
				+ "           GROUP BY CON1.ID_CONTRATO, CON1.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO      "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( CON2.QTY_USUARIO_CONTRATADA ) AS QYT_USER                                        "
				+ "            FROM CONTRATO AS CON2                                                                      "
				+ "           WHERE CON2.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( APR.QTY_PRODUTO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO AS CON3                                                                      "
				+ "           INNER JOIN  ADITIVADO        AS ADT                                                         "
				+ "                   ON ADT.ID_CONTRATO  = CON3.ID_CONTRATO                                              "
				+ "            LEFT JOIN ADITIVADO_PRODUTO AS APR                                                         "
				+ "                   ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                                              "
				+ "           WHERE APR.ID_PRODUTO  = 8                                                                   "
				+ "             AND CON3.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           GROUP BY CON3.ID_CONTRATO, CON3.PEP                                                         "
				+ "        )  AS QTY_USUARIOS                                                                             "
				+ "    )  AS QTY_USUARIO_CONTRATADA                                                                       "
				+ "    , NUV.MOME_PARCEIRO AS 'NUVEM'                                                                     "
				+ "    , SIT.NOME AS 'SITE'                                                                               "
				+ "    , SEC.DESC_SERVICO AS 'SERVICO_CONTRATADO'                                                         "
				+ "    , MOE.MOEDA                                                                                        "
				+ "    , FAC.FASE_CONTRATO                                                                                "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CIU.DESCRICAO AS 'CICLO_UPDATE'                                                                  "
				+ "    , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                         "
				+ "    , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                          "
				+ "    , VIG.DT_DESATIVACAO                                                                               "
				+ "    , DATEDIFF( DAY  , VIG.DT_INICIO , DT_FINAL)  AS QTY_DIAS_VIGENCIA                                 "
				+ "    , DATEDIFF( MONTH, VIG.DT_INICIO , DT_FINAL)  AS QTY_MESES_VIGENCIA                                "
				+ "    , TEC.DESC_TEMPO AS VIGENCIA                                                                       "
				+ "  FROM CLIENTE CLI                                                                                     "
				+ "    LEFT JOIN CONTRATO         AS CON                                                                  "
				+ "           ON CLI.ID_CLIENTE = CON.ID_CLIENTE                                                          "
				+ "   LEFT JOIN NUVEM               AS NUV                                                                "
				+ "          ON NUV.ID_NUVEM              = CON.ID_NUVEM                                                  "
				+ "  LEFT JOIN SITE                AS SIT                                                                 "
				+ "          ON SIT.ID_SITE               = CON.ID_SITE                                                   "
				+ "   LEFT JOIN SERVICO_CONTRATADO  AS SEC                                                                "
				+ "          ON SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO                                     "
				+ "   LEFT JOIN MOEDA               AS MOE                                                                "
				+ "          ON MOE.ID_MOEDA              = CON.ID_MOEDA                                                  "
				+ "   LEFT JOIN FASE_CONTRATO       AS FAC                                                                "
				+ "          ON FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO                                          "
				+ "   LEFT JOIN CICLO_UPDATE        AS CIU                                                                "
				+ "        ON CIU.ID_CICLO_UPDATE         = CON.ID_CICLO_UPDATE                                           "
				+ "   LEFT JOIN VIGENCIA            AS VIG                                                                "
				+ "          ON VIG.ID_CONTRATO           = CON.ID_CONTRATO                                               "
				+ "  LEFT JOIN TEMPO_CONTRATO       AS TEC                                                                "
				+ "          ON TEC.ID_TEMPO_CONTRATO = VIG.ID_TEMPO_CONTRATO                                             "
				+ "  LEFT JOIN STATUS_CONTRATO     AS STC                                                                 "
				+ "        ON STC.ID_STATUS_CONTRATO         = CON.ID_STATUS_CONTRATO                                     "
				+ "    WHERE CON.ID_STATUS_CONTRATO NOT IN ( 1, 4 )                                                       "
				+ "      AND VIG.RENOVACAO = 0                                                                            "
				+ "      AND VIG.DT_DESATIVACAO BETWEEN '" + dtInicio + "' AND '" + dtFim + "'                            ";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet set = ps.executeQuery();
		DAOUtil daoUtil = new DAOUtil();
		
		while(set.next()) {
			ModalClientesInfo mdClientesInfo = new ModalClientesInfo();			
			mdClientesInfo.setId_contrato           ( set.getString( "ID_CONTRATO"                                           ));
			mdClientesInfo.setId_cliente            ( set.getString( "ID_CLIENTE"                                            ));
			mdClientesInfo.setRazao_social          ( set.getString( "RAZAO_SOCIAL"                                          ));
			mdClientesInfo.setNome_fantasia         ( set.getString( "NOME_FANTASIA"                                         ));
			mdClientesInfo.setAlias                 ( set.getString( "ALIAS"                                                 ));
			mdClientesInfo.setCnpj                  ( set.getString( "CNPJ"                                                  ));
			mdClientesInfo.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")     ));
			mdClientesInfo.setPep                   ( set.getString( "PEP"                                                   ));
			mdClientesInfo.setLogin_cadastro        ( set.getString( "LOGIN_CADASTRO"                                        ));
			mdClientesInfo.setId_hub_spot           ( set.getString( "ID_HUB_SPOT"                                           ));
			mdClientesInfo.setTota_aditivos         ( set.getString( "TOTA_ADITIVOS"                                         ));
			mdClientesInfo.setTotal_instancias      ( set.getString( "TOTAL_INSTANCIAS"                                      ));
			mdClientesInfo.setHistorico_contrato    ( set.getString( "HISTORICO_CONTRATO"                                    ));
			mdClientesInfo.setContrato_antigo       ( set.getString( "CONTRATO_ANTIGO"                                       ));
			mdClientesInfo.setQty_usuario_contratada( set.getString( "QTY_USUARIO_CONTRATADA"                                ));
			mdClientesInfo.setNuvem                 ( set.getString( "NUVEM"                                                 ));
			mdClientesInfo.setSite                  ( set.getString( "SITE"                                                  ));
			mdClientesInfo.setServico_contratado    ( set.getString( "SERVICO_CONTRATADO"                                    ));
			mdClientesInfo.setMoeda                 ( set.getString( "MOEDA"                                                 ));
			mdClientesInfo.setFase_contrato         ( set.getString( "FASE_CONTRATO"                                         ));
			mdClientesInfo.setCiclo_update          ( set.getString( "CICLO_UPDATE"                                          ));
			mdClientesInfo.setDt_inicio_vigencia    ( set.getString( "DT_INICIO_VIGENCIA"                                    ));
			mdClientesInfo.setDt_final_vigencia     ( set.getString( "DT_FINAL_VIGENCIA"                                     ));
			mdClientesInfo.setQty_dias_vigencia     ( set.getString( "QTY_DIAS_VIGENCIA"                                     ));
			mdClientesInfo.setQty_meses_vigencia    ( set.getString( "QTY_MESES_VIGENCIA"                                    ));
			mdClientesInfo.setVigencia              ( set.getString( "VIGENCIA"                                              ));
			mdClientesInfo.setStatus                ( set.getString( "STATUS_CONTRATO"                                       ));
			mdClientesInfo.setDt_desativacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_DESATIVACAO") ));
			mdClientesInfos.add(mdClientesInfo);
		}
		// 
		return mdClientesInfos;		
	}
	public List<ModalClientesInfo> getListaClientesInfoBoth( String dtInicio, String dtFim ) throws SQLException{
		List<ModalClientesInfo> mdClientesInfos = new ArrayList<ModalClientesInfo>();
		String sql = "SELECT                                                                                              "
				+ "      CON.ID_CONTRATO                                                                                  "
				+ "    , CLI.ID_CLIENTE                                                                                   "
				+ "    , CLI.RAZAO_SOCIAL                                                                                 "
				+ "    , CLI.NOME_FANTASIA                                                                                "
				+ "    , CLI.ALIAS                                                                                        "
				+ "    , CLI.CNPJ                                                                                         "
				+ "    , CON.DT_CRIACAO                                                                                   "
				+ "    , CON.PEP                                                                                          "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CON.ID_HUB_SPOT                                                                                  "
				+ "    , (SELECT COUNT(ID_CONTRATO) FROM ADITIVADO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTA_ADITIVOS  "
				+ "    , (SELECT  COUNT(ID_CONTRATO) FROM RECURSO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTAL_INSTANCIAS"
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO_CONTRATO_ORIGEM IS NOT NULL THEN 'RENOVAÇÃO'                                "
				+ "        ELSE '1º CONTRATO'                                                                             "
				+ "      END AS 'HISTORICO_CONTRATO'                                                                      "
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO IS NULL THEN ' - '                                                          "
				+ "       ELSE CON.RENOVACAO_CONTRATO_ORIGEM                                                              "
				+ "      END AS 'CONTRATO_ANTIGO'                                                                         "
				+ "    , STC.STATUS_CONTRATO                                                                              "
				+ "    , ( SELECT SUM(QTY_USUARIOS.QYT_USER ) AS QYT_USER                                                 "
				+ "         FROM                                                                                          "
				+ "        ( SELECT SUM( CPR.QTY_SERVICO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO              AS CON1                                                         "
				+ "       LEFT JOIN CONTRATO_PRODUTO AS CPR                                                               "
				+ "              ON CON1.ID_CONTRATO = CPR.ID_CONTRATO                                                    "
				+ "       LEFT JOIN PRODUTO          AS PRO                                                               "
				+ "              ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                       "
				+ "           WHERE CON1.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "             AND PRO.ID_PRODUTO  = 8                                                                   "
				+ "           GROUP BY CON1.ID_CONTRATO, CON1.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO      "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( CON2.QTY_USUARIO_CONTRATADA ) AS QYT_USER                                        "
				+ "            FROM CONTRATO AS CON2                                                                      "
				+ "           WHERE CON2.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( APR.QTY_PRODUTO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO AS CON3                                                                      "
				+ "           INNER JOIN  ADITIVADO        AS ADT                                                         "
				+ "                   ON ADT.ID_CONTRATO  = CON3.ID_CONTRATO                                              "
				+ "            LEFT JOIN ADITIVADO_PRODUTO AS APR                                                         "
				+ "                   ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                                              "
				+ "           WHERE APR.ID_PRODUTO  = 8                                                                   "
				+ "             AND CON3.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           GROUP BY CON3.ID_CONTRATO, CON3.PEP                                                         "
				+ "        )  AS QTY_USUARIOS                                                                             "
				+ "    )  AS QTY_USUARIO_CONTRATADA                                                                       "
				+ "    , NUV.MOME_PARCEIRO AS 'NUVEM'                                                                     "
				+ "    , SIT.NOME AS 'SITE'                                                                               "
				+ "    , SEC.DESC_SERVICO AS 'SERVICO_CONTRATADO'                                                         "
				+ "    , MOE.MOEDA                                                                                        "
				+ "    , FAC.FASE_CONTRATO                                                                                "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CIU.DESCRICAO AS 'CICLO_UPDATE'                                                                  "
				+ "    , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                         "
				+ "    , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                          "
				+ "    , VIG.DT_DESATIVACAO                                                                               "
				+ "    , DATEDIFF( DAY  , VIG.DT_INICIO , DT_FINAL)  AS QTY_DIAS_VIGENCIA                                 "
				+ "    , DATEDIFF( MONTH, VIG.DT_INICIO , DT_FINAL)  AS QTY_MESES_VIGENCIA                                "
				+ "    , TEC.DESC_TEMPO AS VIGENCIA                                                                       "
				+ "  FROM CLIENTE CLI                                                                                     "
				+ "    LEFT JOIN CONTRATO         AS CON                                                                  "
				+ "           ON CLI.ID_CLIENTE = CON.ID_CLIENTE                                                          "
				+ "   LEFT JOIN NUVEM               AS NUV                                                                "
				+ "          ON NUV.ID_NUVEM              = CON.ID_NUVEM                                                  "
				+ "  LEFT JOIN SITE                AS SIT                                                                 "
				+ "          ON SIT.ID_SITE               = CON.ID_SITE                                                   "
				+ "   LEFT JOIN SERVICO_CONTRATADO  AS SEC                                                                "
				+ "          ON SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO                                     "
				+ "   LEFT JOIN MOEDA               AS MOE                                                                "
				+ "          ON MOE.ID_MOEDA              = CON.ID_MOEDA                                                  "
				+ "   LEFT JOIN FASE_CONTRATO       AS FAC                                                                "
				+ "          ON FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO                                          "
				+ "   LEFT JOIN CICLO_UPDATE        AS CIU                                                                "
				+ "        ON CIU.ID_CICLO_UPDATE         = CON.ID_CICLO_UPDATE                                           "
				+ "   LEFT JOIN VIGENCIA            AS VIG                                                                "
				+ "          ON VIG.ID_CONTRATO           = CON.ID_CONTRATO                                               "
				+ "  LEFT JOIN TEMPO_CONTRATO       AS TEC                                                                "
				+ "          ON TEC.ID_TEMPO_CONTRATO = VIG.ID_TEMPO_CONTRATO                                             "
				+ "  LEFT JOIN STATUS_CONTRATO     AS STC                                                                 "
				+ "        ON STC.ID_STATUS_CONTRATO         = CON.ID_STATUS_CONTRATO                                     "
				+ "    WHERE CON.DT_CRIACAO BETWEEN '" + dtInicio + "' AND '" + dtFim + "'                                "
				+ "    UNION ALL                                                                                          "
	   	        + "   SELECT                                                                                              "
				+ "      CON.ID_CONTRATO                                                                                  "
				+ "    , CLI.ID_CLIENTE                                                                                   "
				+ "    , CLI.RAZAO_SOCIAL                                                                                 "
				+ "    , CLI.NOME_FANTASIA                                                                                "
				+ "    , CLI.ALIAS                                                                                        "
				+ "    , CLI.CNPJ                                                                                         "
				+ "    , CON.DT_CRIACAO                                                                                   "
				+ "    , CON.PEP                                                                                          "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CON.ID_HUB_SPOT                                                                                  "
				+ "    , (SELECT COUNT(ID_CONTRATO) FROM ADITIVADO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTA_ADITIVOS  "
				+ "    , (SELECT  COUNT(ID_CONTRATO) FROM RECURSO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTAL_INSTANCIAS"
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO_CONTRATO_ORIGEM IS NOT NULL THEN 'RENOVAÇÃO'                                "
				+ "        ELSE '1º CONTRATO'                                                                             "
				+ "      END AS 'HISTORICO_CONTRATO'                                                                      "
				+ "    , CASE                                                                                             "
				+ "        WHEN CON.RENOVACAO IS NULL THEN ' - '                                                          "
				+ "       ELSE CON.RENOVACAO_CONTRATO_ORIGEM                                                              "
				+ "      END AS 'CONTRATO_ANTIGO'                                                                         "
				+ "    , STC.STATUS_CONTRATO                                                                              "
				+ "    , ( SELECT SUM(QTY_USUARIOS.QYT_USER ) AS QYT_USER                                                 "
				+ "         FROM                                                                                          "
				+ "        ( SELECT SUM( CPR.QTY_SERVICO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO              AS CON1                                                         "
				+ "       LEFT JOIN CONTRATO_PRODUTO AS CPR                                                               "
				+ "              ON CON1.ID_CONTRATO = CPR.ID_CONTRATO                                                    "
				+ "       LEFT JOIN PRODUTO          AS PRO                                                               "
				+ "              ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                       "
				+ "           WHERE CON1.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "             AND PRO.ID_PRODUTO  = 8                                                                   "
				+ "           GROUP BY CON1.ID_CONTRATO, CON1.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO      "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( CON2.QTY_USUARIO_CONTRATADA ) AS QYT_USER                                        "
				+ "            FROM CONTRATO AS CON2                                                                      "
				+ "           WHERE CON2.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           UNION ALL                                                                                   "
				+ "          SELECT SUM( APR.QTY_PRODUTO    ) AS QYT_USER                                                 "
				+ "            FROM CONTRATO AS CON3                                                                      "
				+ "           INNER JOIN  ADITIVADO        AS ADT                                                         "
				+ "                   ON ADT.ID_CONTRATO  = CON3.ID_CONTRATO                                              "
				+ "            LEFT JOIN ADITIVADO_PRODUTO AS APR                                                         "
				+ "                   ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                                              "
				+ "           WHERE APR.ID_PRODUTO  = 8                                                                   "
				+ "             AND CON3.ID_CONTRATO = CON.ID_CONTRATO                                                    "
				+ "           GROUP BY CON3.ID_CONTRATO, CON3.PEP                                                         "
				+ "        )  AS QTY_USUARIOS                                                                             "
				+ "    )  AS QTY_USUARIO_CONTRATADA                                                                       "
				+ "    , NUV.MOME_PARCEIRO AS 'NUVEM'                                                                     "
				+ "    , SIT.NOME AS 'SITE'                                                                               "
				+ "    , SEC.DESC_SERVICO AS 'SERVICO_CONTRATADO'                                                         "
				+ "    , MOE.MOEDA                                                                                        "
				+ "    , FAC.FASE_CONTRATO                                                                                "
				+ "    , CON.LOGIN_CADASTRO                                                                               "
				+ "    , CIU.DESCRICAO AS 'CICLO_UPDATE'                                                                  "
				+ "    , CONVERT(VARCHAR, VIG.DT_INICIO, 103)  DT_INICIO_VIGENCIA                                         "
				+ "    , CONVERT(VARCHAR, VIG.DT_FINAL , 103)  DT_FINAL_VIGENCIA                                          "
				+ "    , VIG.DT_DESATIVACAO                                                                               "
				+ "    , DATEDIFF( DAY  , VIG.DT_INICIO , DT_FINAL)  AS QTY_DIAS_VIGENCIA                                 "
				+ "    , DATEDIFF( MONTH, VIG.DT_INICIO , DT_FINAL)  AS QTY_MESES_VIGENCIA                                "
				+ "    , TEC.DESC_TEMPO AS VIGENCIA                                                                       "
				+ "  FROM CLIENTE CLI                                                                                     "
				+ "    LEFT JOIN CONTRATO         AS CON                                                                  "
				+ "           ON CLI.ID_CLIENTE = CON.ID_CLIENTE                                                          "
				+ "   LEFT JOIN NUVEM               AS NUV                                                                "
				+ "          ON NUV.ID_NUVEM              = CON.ID_NUVEM                                                  "
				+ "  LEFT JOIN SITE                AS SIT                                                                 "
				+ "          ON SIT.ID_SITE               = CON.ID_SITE                                                   "
				+ "   LEFT JOIN SERVICO_CONTRATADO  AS SEC                                                                "
				+ "          ON SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO                                     "
				+ "   LEFT JOIN MOEDA               AS MOE                                                                "
				+ "          ON MOE.ID_MOEDA              = CON.ID_MOEDA                                                  "
				+ "   LEFT JOIN FASE_CONTRATO       AS FAC                                                                "
				+ "          ON FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO                                          "
				+ "   LEFT JOIN CICLO_UPDATE        AS CIU                                                                "
				+ "        ON CIU.ID_CICLO_UPDATE         = CON.ID_CICLO_UPDATE                                           "
				+ "   LEFT JOIN VIGENCIA            AS VIG                                                                "
				+ "          ON VIG.ID_CONTRATO           = CON.ID_CONTRATO                                               "
				+ "  LEFT JOIN TEMPO_CONTRATO       AS TEC                                                                "
				+ "          ON TEC.ID_TEMPO_CONTRATO = VIG.ID_TEMPO_CONTRATO                                             "
				+ "  LEFT JOIN STATUS_CONTRATO     AS STC                                                                 "
				+ "        ON STC.ID_STATUS_CONTRATO         = CON.ID_STATUS_CONTRATO                                     "
				+ "    WHERE CON.ID_STATUS_CONTRATO NOT IN ( 1, 4 )                                                       "
				+ "      AND VIG.RENOVACAO = 0                                                                            "
				+ "      AND VIG.DT_DESATIVACAO BETWEEN '" + dtInicio + "' AND '" + dtFim + "'                            ";
				
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet set = ps.executeQuery();
		DAOUtil daoUtil = new DAOUtil();
		
		while(set.next()) {
			ModalClientesInfo mdClientesInfo = new ModalClientesInfo();			
			mdClientesInfo.setId_contrato           ( set.getString( "ID_CONTRATO"                                           ));
			mdClientesInfo.setId_cliente            ( set.getString( "ID_CLIENTE"                                            ));
			mdClientesInfo.setRazao_social          ( set.getString( "RAZAO_SOCIAL"                                          ));
			mdClientesInfo.setNome_fantasia         ( set.getString( "NOME_FANTASIA"                                         ));
			mdClientesInfo.setAlias                 ( set.getString( "ALIAS"                                                 ));
			mdClientesInfo.setCnpj                  ( set.getString( "CNPJ"                                                  ));
			mdClientesInfo.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")     ));
			mdClientesInfo.setPep                   ( set.getString( "PEP"                                                   ));
			mdClientesInfo.setLogin_cadastro        ( set.getString( "LOGIN_CADASTRO"                                        ));
			mdClientesInfo.setId_hub_spot           ( set.getString( "ID_HUB_SPOT"                                           ));
			mdClientesInfo.setTota_aditivos         ( set.getString( "TOTA_ADITIVOS"                                         ));
			mdClientesInfo.setTotal_instancias      ( set.getString( "TOTAL_INSTANCIAS"                                      ));
			mdClientesInfo.setHistorico_contrato    ( set.getString( "HISTORICO_CONTRATO"                                    ));
			mdClientesInfo.setContrato_antigo       ( set.getString( "CONTRATO_ANTIGO"                                       ));
			mdClientesInfo.setQty_usuario_contratada( set.getString( "QTY_USUARIO_CONTRATADA"                                ));
			mdClientesInfo.setNuvem                 ( set.getString( "NUVEM"                                                 ));
			mdClientesInfo.setSite                  ( set.getString( "SITE"                                                  ));
			mdClientesInfo.setServico_contratado    ( set.getString( "SERVICO_CONTRATADO"                                    ));
			mdClientesInfo.setMoeda                 ( set.getString( "MOEDA"                                                 ));
			mdClientesInfo.setFase_contrato         ( set.getString( "FASE_CONTRATO"                                         ));
			mdClientesInfo.setCiclo_update          ( set.getString( "CICLO_UPDATE"                                          ));
			mdClientesInfo.setDt_inicio_vigencia    ( set.getString( "DT_INICIO_VIGENCIA"                                    ));
			mdClientesInfo.setDt_final_vigencia     ( set.getString( "DT_FINAL_VIGENCIA"                                     ));
			mdClientesInfo.setQty_dias_vigencia     ( set.getString( "QTY_DIAS_VIGENCIA"                                     ));
			mdClientesInfo.setQty_meses_vigencia    ( set.getString( "QTY_MESES_VIGENCIA"                                    ));
			mdClientesInfo.setVigencia              ( set.getString( "VIGENCIA"                                              ));
			mdClientesInfo.setStatus                ( set.getString( "STATUS_CONTRATO"                                       ));
			mdClientesInfo.setDt_desativacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_DESATIVACAO") ));
			mdClientesInfos.add(mdClientesInfo);
		}
		// 
		return mdClientesInfos;		
	}

}
