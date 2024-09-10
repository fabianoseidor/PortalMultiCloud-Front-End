package br.com.portal.dao.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.model.dashboard.ModalFaturamentoProjetado;
import br.com.portal.model.dashboard.ModalHistoricoContrato;
import br.com.portal.model.dashboard.ModalPepCnpj;
import br.com.portal.model.dashboard.ModalPesquisa;
import br.com.portal.model.dashboard.ModalProdutosContrato;
import br.com.portal.model.dashboard.ModalRecursosContrato;
import br.com.portal.model.dashboard.ModalVlrsContrato;

public class DAOFaturamentoProjetado {
	
	private Connection connection;
	
	public DAOFaturamentoProjetado() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModalPesquisa> getListaPesquisaPep( String pep ) throws SQLException {
		List<ModalPesquisa> mdPesquisas = new ArrayList<ModalPesquisa>();
		
		String sql = " SELECT                                          "
				+ "      CLI.ID_CLIENTE                                "
				+ "    , CON.ID_CONTRATO                               "
				+ "    , CON.PEP                                       "
				+ "  FROM                                              "
				+ "     CLIENTE  AS CLI                                "
				+ "   , CONTRATO AS CON                                "
				+ " WHERE UPPER( CON.PEP ) LIKE UPPER('%" + pep + "%') "
				+ "    AND CON.ID_CLIENTE = CLI.ID_CLIENTE             "
				+ "    AND CON.ID_STATUS_CONTRATO IN ( 1, 4 )          ";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet set = ps.executeQuery();
		
		while(set.next()) {
		   ModalPesquisa mdPesquisa = new ModalPesquisa();
		   mdPesquisa.setId_cliente  ( set.getString( "ID_CLIENTE"  ) );
		   mdPesquisa.setId_contrato ( set.getString( "ID_CONTRATO" ) );
		   mdPesquisa.setPep         ( set.getString( "PEP"         ) );
		   mdPesquisas.add(mdPesquisa);
		}
		
		return mdPesquisas;
	}

	public List<ModalPesquisa> getListaPesquisaAlias( String alias ) throws SQLException {
		List<ModalPesquisa> mdPesquisas = new ArrayList<ModalPesquisa>();
		
		String sql = "SELECT                                               "
				+ "      CLI.ID_CLIENTE                                    "
				+ "    , CON.ID_CONTRATO                                   "
				+ "    , CLI.ALIAS                                         "
				+ "  FROM                                                  "
				+ "     CLIENTE  AS CLI                                    "
				+ "   , CONTRATO AS CON                                    "
				+ " WHERE UPPER( CLI.ALIAS ) LIKE UPPER('%" + alias + "%') "
				+ "   AND CON.ID_CLIENTE = CLI.ID_CLIENTE                  "
				+ "   AND CON.ID_STATUS_CONTRATO IN ( 1, 4 )               ";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet set = ps.executeQuery();
		
		while(set.next()) {
		   ModalPesquisa mdPesquisa = new ModalPesquisa();
		   mdPesquisa.setId_cliente  ( set.getString( "ID_CLIENTE"  ) );
		   mdPesquisa.setId_contrato ( set.getString( "ID_CONTRATO" ) );
		   mdPesquisa.setAlias       ( set.getString( "ALIAS"       ) );
		   mdPesquisas.add(mdPesquisa);
		}
		
		return mdPesquisas;
	}

	public List<ModalPesquisa> getListaPesquisaRazaSocial( String razaSocial ) throws SQLException {
		List<ModalPesquisa> mdPesquisas = new ArrayList<ModalPesquisa>();
		
		String sql = "SELECT                                                           "
				+ "      CLI.ID_CLIENTE                                                "
				+ "    , CON.ID_CONTRATO                                               "
				+ "    , CLI.RAZAO_SOCIAL                                              "
				+ "  FROM                                                              "
				+ "     CLIENTE  AS CLI                                                "
				+ "   , CONTRATO AS CON                                                "
				+ " WHERE UPPER( CLI.RAZAO_SOCIAL ) LIKE UPPER('%" + razaSocial + "%') "
				+ "   AND CON.ID_CLIENTE = CLI.ID_CLIENTE                              "
				+ "   AND CON.ID_STATUS_CONTRATO IN ( 1, 4 )                           ";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet set = ps.executeQuery();
		
		while(set.next()) {
		   ModalPesquisa mdPesquisa = new ModalPesquisa();
		   mdPesquisa.setId_cliente  ( set.getString( "ID_CLIENTE"  ) );
		   mdPesquisa.setId_contrato ( set.getString( "ID_CONTRATO" ) );
		   mdPesquisa.setRazao_social( set.getString( "RAZAO_SOCIAL") );
		   mdPesquisas.add(mdPesquisa);
		}
		
		return mdPesquisas;
	}
	
	public ModalFaturamentoProjetado montaContrato( Long idContrato ) throws SQLException {
		ModalFaturamentoProjetado mdFaturamentoProjetado = new ModalFaturamentoProjetado();
		
		mdFaturamentoProjetado = consultaContrato( idContrato );
		
		List<ModalProdutosContrato> mdProdutosContratos = consultaProdutoContrato( idContrato );
		
		for( int i = 0; i < mdProdutosContratos.size(); i++)
		  mdFaturamentoProjetado.getMdProdutosContratos().add( mdProdutosContratos.get(i) );
		
		List<ModalRecursosContrato> mdRecursosContrato  = consultaRecursosContrato( idContrato );
		
		for( int i = 0; i < mdRecursosContrato.size(); i++)
			mdFaturamentoProjetado.getMdRecursosContrato().add(mdRecursosContrato.get(i));
		
		ModalVlrsContrato mdVlrsContrato = consultaVlrsContrato( idContrato );
		
		mdFaturamentoProjetado.setMdVlrsContrato(mdVlrsContrato);
		
		List<ModalPepCnpj> mdPepCnpjs = consultaPepCnpj(  mdFaturamentoProjetado.getPep() );
		
		for( int i = 0; i < mdPepCnpjs.size(); i++)
			mdFaturamentoProjetado.getMdPepCnpjs().add(mdPepCnpjs.get(i));
		
		ModalHistoricoContrato mdHistoricoContrato = new ModalHistoricoContrato();
		
		mdHistoricoContrato = consultaHistoricoContrato( idContrato );
		
		mdFaturamentoProjetado.getMdHistoricoContrato().add(mdHistoricoContrato);
		
		while( mdHistoricoContrato != null  ) {
			Long idContratoOrigem = 0L;
			
			if( mdHistoricoContrato.getRenovacao_contrato_origem() != null) {
			  idContratoOrigem = Long.parseLong( mdHistoricoContrato.getRenovacao_contrato_origem() );
			  
			  mdHistoricoContrato = consultaHistoricoContrato( idContratoOrigem );
			  mdFaturamentoProjetado.getMdHistoricoContrato().add(mdHistoricoContrato);
			  
			}else mdHistoricoContrato = null;
		}
		
		return mdFaturamentoProjetado;
	}
	
	public List<ModalPepCnpj> consultaPepCnpj( String pep ) throws SQLException {
		
		String sql = "SELECT                                   "
				+ "     CON.ID_CONTRATO                        "
				+ "   , CLI.CNPJ                               "
				+ "  FROM                                      "
				+ "     CONTRATO AS CON                        "
				+ "   , CLIENTE  AS CLI                        "
				+ "    WHERE CON.PEP = ?                       "
				+ "      AND CLI.ID_CLIENTE = CON.ID_CLIENTE   "
				+ "      AND CON.ID_STATUS_CONTRATO IN ( 1, 4 )";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setString( 1,  pep );
		ResultSet resultado = statemet.executeQuery();
		
		List<ModalPepCnpj> mdPepCnpjs = new ArrayList<ModalPepCnpj>();
		
		while (resultado.next()) {
			ModalPepCnpj mdPepCnpj = new ModalPepCnpj();
			mdPepCnpj.setId_contrato( resultado.getString ( "ID_CONTRATO" ) );
			mdPepCnpj.setCnpj       ( resultado.getString ( "CNPJ"        ) );
			mdPepCnpjs.add(mdPepCnpj);
		}
		
		return mdPepCnpjs;
		
	}
	
	public ModalHistoricoContrato consultaHistoricoContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT                                                               "
				+ "     CON.ID_CONTRATO                                                    "
				+ "   , CASE                                                               "
				+ "        WHEN CON.RENOVACAO_CONTRATO_ORIGEM IS NOT NULL THEN 'RENOVAÇÃO' "
				+ "        ELSE '1º CONTRATO'                                              "
				+ "      END AS 'HISTORICO_CONTRATO'                                       "
				+ "   , CON.RENOVACAO_CONTRATO_NOVO                                        "
				+ "   , CON.RENOVACAO_CONTRATO_ORIGEM                                      "
				+ "   , CONVERT(VARCHAR, DT_INICIO, 103)  DT_INICIO_VIGENCIA               "
				+ "   , CONVERT(VARCHAR, DT_FINAL , 103)  DT_FINAL_VIGENCIA                "
				+ "   , DATEDIFF( month , DT_INICIO , DT_FINAL)  AS QTY_MESES_VIGENCIA     "
				+ "  FROM CONTRATO CON                                                     "
				+ "  INNER JOIN VIGENCIA    AS VIG                                         "
				+ "         ON VIG.ID_CONTRATO = CON.ID_CONTRATO                           "
				+ " WHERE CON.ID_CONTRATO = ?                                              ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		ResultSet resultado = statemet.executeQuery();
		
		ModalHistoricoContrato mdHistoricoContratos = new ModalHistoricoContrato();
		
		while (resultado.next()) {
			mdHistoricoContratos.setId_contrato              ( resultado.getString ( "ID_CONTRATO"              ) );
			mdHistoricoContratos.setHistorico_contrato       ( resultado.getString ( "HISTORICO_CONTRATO"       ) );
			mdHistoricoContratos.setRenovacao_contrato_novo  ( resultado.getString ( "RENOVACAO_CONTRATO_NOVO"  ) );
			mdHistoricoContratos.setRenovacao_contrato_origem( resultado.getString ( "RENOVACAO_CONTRATO_ORIGEM") );
			mdHistoricoContratos.setDt_inicio_vigencia       ( resultado.getString ( "DT_INICIO_VIGENCIA"       ) );
			mdHistoricoContratos.setDt_final_vigencia        ( resultado.getString ( "DT_FINAL_VIGENCIA"        ) );
			mdHistoricoContratos.setQty_meses_vigencia       ( resultado.getString ( "QTY_MESES_VIGENCIA"       ) );
		}
		
		return mdHistoricoContratos;
		
	}
	
	public ModalVlrsContrato consultaVlrsContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT                                                                                       "
				+ "     CASE                                                                                       "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 1 THEN FORMAT( SUM( VLR_TOTAL_CONV  ), 'c', 'pt-br')      "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 2 THEN FORMAT( SUM( VLR_TOTAL_CONV  ), 'C', 'en-us')      "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 3 THEN FORMAT( SUM( VLR_TOTAL_CONV  ), 'C', 'de-de')      "
				+ "     END AS VLR_TOTAL_CONV                                                                      "
				+ "   , CASE                                                                                       "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 1 THEN FORMAT( SUM( VLR_TOTAL_PRINCIPAL  ), 'c', 'pt-br') "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 2 THEN FORMAT( SUM( VLR_TOTAL_PRINCIPAL  ), 'C', 'en-us') "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 3 THEN FORMAT( SUM( VLR_TOTAL_PRINCIPAL  ), 'C', 'de-de') "
				+ "     END AS VLR_TOTAL_PRINCIPAL                                                                 "
				+ "   , CASE                                                                                       "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 1 THEN FORMAT( SUM( VLR_TOTAL_ADITIVO  ), 'c', 'pt-br')   "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 2 THEN FORMAT( SUM( VLR_TOTAL_ADITIVO  ), 'C', 'en-us')   "
				+ "        WHEN VLRS_CONTRATO.ID_MOEDA = 3 THEN FORMAT( SUM( VLR_TOTAL_ADITIVO  ), 'C', 'de-de')   "
				+ "     END AS VLR_TOTAL_ADITIVO                                                                   "
				+ "FROM(                                                                                           "
				+ "SELECT                                                                                          "
				+ "      SUM( VLR_TOTAL ) AS VLR_TOTAL_CONV                                                        "
				+ "    , 0 AS VLR_TOTAL_PRINCIPAL                                                                  "
				+ "    , 0 AS VLR_TOTAL_ADITIVO                                                                    "
				+ "    , ID_MOEDA                                                                                  "
				+ "    FROM (                                                                                      "
				+ "            SELECT                                                                              "
				+ "                CON1.VALOR_TOTAL AS VLR_TOTAL                                                   "
				+ "              , CON1.ID_MOEDA  AS ID_MOEDA                                                      "
				+ "              FROM CONTRATO           AS CON1                                                   "
				+ "            WHERE CON1.ID_CONTRATO  = ?                                                         "
				+ "            UNION ALL                                                                           "
				+ "            SELECT                                                                              "
				+ "                SUM( ADT1.VLR_TOTAL_ADIT) AS VLR_TOTAL                                          "
				+ "              , CON2.ID_MOEDA  AS ID_MOEDA                                                      "
				+ "              FROM CONTRATO          AS CON2                                                    "
				+ "            INNER JOIN ADITIVADO    AS ADT1                                                     "
				+ "                ON ADT1.ID_CONTRATO  = CON2.ID_CONTRATO                                         "
				+ "            WHERE CON2.ID_CONTRATO  = ?                                                         "
				+ "            GROUP BY CON2.ID_MOEDA                                                              "
				+ "    ) AS VLR_TOTAL                                                                              "
				+ "GROUP BY ID_MOEDA                                                                               "
				+ "UNION ALL                                                                                       "
				+ "SELECT                                                                                          "
				+ "       0 AS VLR_TOTAL_CONV                                                                      "
				+ "     , CON1.VALOR_TOTAL AS VLR_TOTAL_PRINCIPAL                                                  "
				+ "     , 0 AS VLR_TOTAL_ADITIVO                                                                   "
				+ "     , CON1.ID_MOEDA                                                                            "
				+ "  FROM CONTRATO AS CON1                                                                         "
				+ " WHERE CON1.ID_CONTRATO = ?                                                                     "
				+ "UNION ALL                                                                                       "
				+ "SELECT                                                                                          "
				+ "     0 AS VLR_TOTAL_CONV                                                                        "
				+ "   , 0 AS VLR_TOTAL_PRINCIPAL                                                                   "
				+ "   , SUM( ADT1.VLR_TOTAL_ADIT ) AS VLR_TOTAL_ADITIVO                                            "
				+ "   , CON2.ID_MOEDA                                                                              "
				+ "  FROM CONTRATO          AS CON2                                                                "
				+ " INNER JOIN ADITIVADO    AS ADT1                                                                "
				+ "         ON ADT1.ID_CONTRATO = CON2.ID_CONTRATO                                                 "
				+ " WHERE CON2.ID_CONTRATO      = ?                                                                "
				+ "GROUP BY CON2.ID_MOEDA                                                                          "
				+ ") AS VLRS_CONTRATO                                                                              "
				+ "GROUP BY VLRS_CONTRATO.ID_MOEDA                                                                 ";

		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		statemet.setLong ( 2,  idContrato );
		statemet.setLong ( 3,  idContrato );
		statemet.setLong ( 4,  idContrato );
		ResultSet resultado = statemet.executeQuery();
		
		ModalVlrsContrato mdVlrsContrato = new ModalVlrsContrato();
		 
		while (resultado.next()) {
			mdVlrsContrato.setVlr_total_conv     ( resultado.getString ( "VLR_TOTAL_CONV"      ) );
			mdVlrsContrato.setVlr_total_principal( resultado.getString ( "VLR_TOTAL_PRINCIPAL" ) );
			mdVlrsContrato.setVlr_total_aditivo  ( resultado.getString ( "VLR_TOTAL_ADITIVO"   ) );
		}
		
		return mdVlrsContrato;		
	}
	
	
	
	public ModalFaturamentoProjetado consultaContrato( Long idContrato ) throws SQLException {

		String sql = "SELECT                                                                                               "
				+ "      CON.ID_CONTRATO                                                                                   "
				+ "    , CLI.ID_CLIENTE                                                                                    "
				+ "    , CLI.RAZAO_SOCIAL                                                                                  "
				+ "    , CLI.NOME_FANTASIA                                                                                 "
				+ "    , CLI.ALIAS                                                                                         "
				+ "    , CLI.CNPJ                                                                                          "
				+ "    , CLI.DT_CRIACAO                                                                                    "
				+ "    , CON.PEP                                                                                           "
				+ "    , CON.LOGIN_CADASTRO                                                                                "
				+ "    , CON.ID_HUB_SPOT                                                                                   "
				+ "    , (SELECT COUNT(ID_CONTRATO) FROM ADITIVADO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTA_ADITIVOS   "
				+ "    , (SELECT  COUNT(ID_CONTRATO) FROM RECURSO WHERE ID_CONTRATO = CON.ID_CONTRATO) AS TOTAL_INSTANCIAS "
				+ "    , ( SELECT SUM(QTY_USUARIOS.QYT_USER ) AS QYT_USER                                                  "
				+ "         FROM                                                                                           "
				+ "        ( SELECT SUM( CPR.QTY_SERVICO    ) AS QYT_USER                                                  "
				+ "            FROM CONTRATO              AS CON1                                                          "
				+ "       LEFT JOIN CONTRATO_PRODUTO AS CPR                                                                "
				+ "              ON CON1.ID_CONTRATO = CPR.ID_CONTRATO                                                     "
				+ "       LEFT JOIN PRODUTO          AS PRO                                                                "
				+ "              ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                        "
				+ "           WHERE CON1.ID_CONTRATO = CON.ID_CONTRATO                                                     "
				+ "             AND PRO.ID_PRODUTO  = 8                                                                    "
				+ "           GROUP BY CON1.ID_CONTRATO, CON1.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO       "
				+ "           UNION ALL                                                                                    "
				+ "          SELECT SUM( CON2.QTY_USUARIO_CONTRATADA ) AS QYT_USER                                         "
				+ "            FROM CONTRATO AS CON2                                                                       "
				+ "           WHERE CON2.ID_CONTRATO = CON.ID_CONTRATO                                                     "
				+ "           UNION ALL                                                                                    "
				+ "          SELECT SUM( APR.QTY_PRODUTO    ) AS QYT_USER                                                  "
				+ "            FROM CONTRATO AS CON3                                                                       "
				+ "           INNER JOIN  ADITIVADO        AS ADT                                                          "
				+ "                   ON ADT.ID_CONTRATO  = CON3.ID_CONTRATO                                               "
				+ "            LEFT JOIN ADITIVADO_PRODUTO AS APR                                                          "
				+ "                   ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                                               "
				+ "           WHERE APR.ID_PRODUTO  = 8                                                                    "
				+ "             AND CON3.ID_CONTRATO = CON.ID_CONTRATO                                                     "
				+ "           GROUP BY CON3.ID_CONTRATO, CON3.PEP                                                          "
				+ "        )  AS QTY_USUARIOS                                                                              "
				+ "    )  AS QTY_USUARIO_CONTRATADA                                                                        "
				+ "    , CASE                                                                                              "
				+ "        WHEN CON.RENOVACAO IS NULL THEN 'NÃO'                                                           "
				+ "        WHEN CON.RENOVACAO = 1 THEN 'SM'                                                                "
				+ "      END AS 'RENOVACAO'                                                                                "
				+ "    , CASE                                                                                              "
				+ "        WHEN CON.RENOVACAO IS NULL THEN ' - '                                                           "
				+ "       ELSE CON.RENOVACAO_CONTRATO_ORIGEM                                                               "
				+ "      END AS CONTRATO_ANTIGO                                                                            "
				+ "    , NUV.MOME_PARCEIRO AS NUVEM                                                                        "
				+ "    , SIT.NOME AS SITE                                                                                  "
				+ "    , SEC.DESC_SERVICO AS SERVICO_CONTRATADO                                                            "
				+ "    , MOE.MOEDA                                                                                         "
				+ "    , FAC.FASE_CONTRATO                                                                                 "
				+ "    , CIU.DESCRICAO AS CICLO_UPDATE                                                                     "
				+ "  FROM CLIENTE CLI                                                                                      "
				+ "    LEFT JOIN CONTRATO         AS CON                                                                   "
				+ "           ON CLI.ID_CLIENTE = CON.ID_CLIENTE                                                           "
				+ "   LEFT JOIN NUVEM               AS NUV                                                                 "
				+ "          ON NUV.ID_NUVEM              = CON.ID_NUVEM                                                   "
				+ "  LEFT JOIN SITE                AS SIT                                                                  "
				+ "          ON SIT.ID_SITE               = CON.ID_SITE                                                    "
				+ "   LEFT JOIN SERVICO_CONTRATADO  AS SEC                                                                 "
				+ "          ON SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO                                      "
				+ "   LEFT JOIN MOEDA               AS MOE                                                                 "
				+ "          ON MOE.ID_MOEDA              = CON.ID_MOEDA                                                   "
				+ "   LEFT JOIN FASE_CONTRATO       AS FAC                                                                 "
				+ "          ON FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO                                           "
				+ "   LEFT JOIN CICLO_UPDATE        AS CIU                                                                 "
				+ "        ON CIU.ID_CICLO_UPDATE         = CON.ID_CICLO_UPDATE                                            "
				+ "    WHERE CON.ID_STATUS_CONTRATO IN ( 1, 4 )                                                            "
				+ "      AND CON.ID_CONTRATO = ?                                                                           ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		ResultSet resultado = statemet.executeQuery();
		
		ModalFaturamentoProjetado mdFaturamentoProjetado = new ModalFaturamentoProjetado();
		DAOUtil daoUtil = new DAOUtil();
		
		while (resultado.next()) {
			mdFaturamentoProjetado.setId_contrato           ( resultado.getString ( "ID_CONTRATO"                                      ));
			mdFaturamentoProjetado.setId_cliente            ( resultado.getString ( "ID_CLIENTE"                                       ));
			mdFaturamentoProjetado.setRazao_social          ( resultado.getString ( "RAZAO_SOCIAL"                                     ));
			mdFaturamentoProjetado.setNome_fantasia         ( resultado.getString ( "NOME_FANTASIA"                                    ));
			mdFaturamentoProjetado.setAlias                 ( resultado.getString ( "ALIAS"                                            ));
			mdFaturamentoProjetado.setCnpj                  ( resultado.getString ( "CNPJ"                                             ));
			mdFaturamentoProjetado.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ));
			mdFaturamentoProjetado.setPep                   ( resultado.getString ( "PEP"                                              ));
			mdFaturamentoProjetado.setLogin_cadastro        ( resultado.getString ( "LOGIN_CADASTRO"                                   ));
			mdFaturamentoProjetado.setId_hub_spot           ( resultado.getString ( "ID_HUB_SPOT"                                      ));
			mdFaturamentoProjetado.setTota_aditivos         ( resultado.getString ( "TOTA_ADITIVOS"                                    ));
			mdFaturamentoProjetado.setTotal_instancias      ( resultado.getString ( "TOTAL_INSTANCIAS"                                 ));
			mdFaturamentoProjetado.setQty_usuario_contratada( resultado.getString ( "QTY_USUARIO_CONTRATADA"                           ));
			mdFaturamentoProjetado.setRenovacao             ( resultado.getString ( "RENOVACAO"                                        ));
			mdFaturamentoProjetado.setContrato_antigo       ( resultado.getString ( "CONTRATO_ANTIGO"                                  ));
			mdFaturamentoProjetado.setNuvem                 ( resultado.getString ( "NUVEM"                                            ));
			mdFaturamentoProjetado.setSite                  ( resultado.getString ( "SITE"                                             ));
			mdFaturamentoProjetado.setServico_contratado    ( resultado.getString ( "SERVICO_CONTRATADO"                               ));
			mdFaturamentoProjetado.setMoeda                 ( resultado.getString ( "MOEDA"                                            ));
			mdFaturamentoProjetado.setFase_contrato         ( resultado.getString ( "FASE_CONTRATO"                                    ));
			mdFaturamentoProjetado.setCiclo_update          ( resultado.getString ( "CICLO_UPDATE"                                     ));
		}
		
		return mdFaturamentoProjetado;
	}
	
	public List<ModalProdutosContrato> consultaProdutoContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT                                                                                                                      "
				+ "    PRODUTOS.ID_CONTRATO                                                                                                       "
				+ "  , PRODUTOS.ID_PRODUTO                                                                                                        "
				+ "  , PRODUTOS.PRODUTO                                                                                                           "
				+ "  , PRODUTOS.TIPO_CONTRATO                                                                                                     "
				+ "  , PRODUTOS.QTY_SERVICO                                                                                                       "
				+ "  , CASE                                                                                                                       "
				+ "        WHEN ID_MOEDA = 1 THEN FORMAT( PRODUTOS.VALOR_UNITARIO, 'c', 'pt-br')                                                  "
				+ "        WHEN ID_MOEDA = 2 THEN FORMAT( PRODUTOS.VALOR_UNITARIO, 'C', 'en-us')                                                  "
				+ "        WHEN ID_MOEDA = 3 THEN FORMAT( PRODUTOS.VALOR_UNITARIO, 'C', 'de-de')                                                  "
				+ "    END AS VALOR_UNITARIO                                                                                                      "
				+ "  , CASE                                                                                                                       "
				+ "        WHEN ID_MOEDA = 1 THEN FORMAT( PRODUTOS.VALOR, 'c', 'pt-br')                                                           "
				+ "        WHEN ID_MOEDA = 2 THEN FORMAT( PRODUTOS.VALOR, 'C', 'en-us')                                                           "
				+ "        WHEN ID_MOEDA = 3 THEN FORMAT( PRODUTOS.VALOR, 'C', 'de-de')                                                           "
				+ "    END AS VALOR                                                                                                               "
				+ "FROM (                                                                                                                         "
				+ "    SELECT                                                                                                                     "
				+ "      CON.ID_CONTRATO                                                                                                          "
				+ "    , CON.ID_MOEDA                                                                                                             "
				+ "    , '8' AS ID_PRODUTO                                                                                                        "
				+ "    , 'Usuário' AS PRODUTO                                                                                                     "
				+ "    , 'CONTRATO PRINCIPAL'      AS TIPO_CONTRATO                                                                               "
				+ "    , SUM(CON.QTY_USUARIO_CONTRATADA) AS QTY_SERVICO                                                                           "
				+ "    , 0 AS VALOR_UNITARIO                                                                                                      "
				+ "    , 0 AS VALOR                                                                                                               "
				+ "    FROM CONTRATO              AS CON                                                                                          "
				+ "    GROUP BY CON.ID_CONTRATO, CON.ID_MOEDA                                                                                     "
				+ "    UNION ALL                                                                                                                  "
				+ "    SELECT                                                                                                                     "
				+ "      CON.ID_CONTRATO                                                                                                          "
				+ "    , CON.ID_MOEDA                                                                                                             "
				+ "    , PRO.ID_PRODUTO                                                                                                           "
				+ "    , PRO.PRODUTO                                                                                                              "
				+ "    , 'CONTRATO'      AS TIPO_CONTRATO                                                                                         "
				+ "    , SUM( CPR.QTY_SERVICO    ) AS QTY_SERVICO                                                                                 "
				+ "    , CPR.VALOR_UNITARIO                                                                                                       "
				+ "    , SUM( CPR.VALOR ) AS VALOR                                                                                                "
				+ "    FROM CONTRATO              AS CON                                                                                          "
				+ "    LEFT JOIN CONTRATO_PRODUTO AS CPR                                                                                          "
				+ "            ON CON.ID_CONTRATO = CPR.ID_CONTRATO                                                                               "
				+ "    LEFT JOIN PRODUTO          AS PRO                                                                                          "
				+ "            ON CPR.ID_PRODUTO = PRO.ID_PRODUTO                                                                                 "
				+ "    GROUP BY CON.ID_CONTRATO, CON.ID_MOEDA, PRO.ID_PRODUTO , PRO.PRODUTO, VALOR_UNITARIO                                       "
				+ "    UNION ALL                                                                                                                  "
				+ "    SELECT                                                                                                                     "
				+ "      CON.ID_CONTRATO                                                                                                          "
				+ "    , CON.ID_MOEDA                                                                                                             "
				+ "    , PRD.ID_PRODUTO                                                                                                           "
				+ "    , PRD.PRODUTO                                                                                                              "
				+ "    , CONCAT('ADITIVO - ',  APR.ID_ADITIVADO ) AS TIPO_CONTRATO                                                                "
				+ "    , SUM( APR.QTY_PRODUTO ) AS QTY_SERVICO                                                                                    "
				+ "    , APR.VALOR_UNITARIO                                                                                                       "
				+ "    , SUM( APR.VALOR ) AS VALOR                                                                                                "
				+ "    FROM CONTRATO               AS CON                                                                                         "
				+ "    LEFT JOIN ADITIVADO         AS ADI                                                                                         "
				+ "            ON ADI.ID_CONTRATO = CON.ID_CONTRATO                                                                               "
				+ "    LEFT JOIN ADITIVADO_PRODUTO AS APR                                                                                         "
				+ "            ON ADI.ID_ADITIVADO = APR.ID_ADITIVADO                                                                             "
				+ "    LEFT JOIN PRODUTO           AS PRD                                                                                         "
				+ "            ON PRD.ID_PRODUTO = APR.ID_PRODUTO                                                                                 "
				+ "    GROUP BY CON.ID_CONTRATO, CON.ID_MOEDA, PRD.ID_PRODUTO, PRD.PRODUTO, APR.ID_ADITIVADO, APR.QTY_PRODUTO, APR.VALOR_UNITARIO "
				+ ") AS PRODUTOS                                                                                                                  "
				+ "WHERE PRODUTOS.ID_CONTRATO = ?                                                                                                 ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		ResultSet resultado = statemet.executeQuery();
		
		List<ModalProdutosContrato> mdProdutosContratos = new ArrayList<ModalProdutosContrato>();
		
		while (resultado.next()) {
			ModalProdutosContrato mdProdutosContrato = new ModalProdutosContrato();
			
			mdProdutosContrato.setId_contrato   ( resultado.getString ( "ID_CONTRATO"   ));
			mdProdutosContrato.setId_produto    ( resultado.getString ( "ID_PRODUTO"    ));
			mdProdutosContrato.setProduto       ( resultado.getString ( "PRODUTO"       ));
			mdProdutosContrato.setTipo_contrato ( resultado.getString ( "TIPO_CONTRATO" ));
			mdProdutosContrato.setQty_servico   ( resultado.getString ( "QTY_SERVICO"   ));	
			mdProdutosContrato.setValor_unitario( resultado.getString ( "VALOR_UNITARIO"));
			mdProdutosContrato.setValor         ( resultado.getString ( "VALOR"         ));
			
			mdProdutosContratos.add(mdProdutosContrato);
		}
		
		return mdProdutosContratos;
		
	}
	
	public List<ModalRecursosContrato> consultaRecursosContrato( Long idContrato ) throws SQLException {
		
		String sql = "SELECT                                                        "
				+ "    REC.HOSTNAME                                                 "
				+ "  , SIO.SISTEMA_OPERACIONAL                                      "
				+ "  , REC.OBS                                                      "
				+ "  , FAF.FAMILIA AS FAMILIA_FLAVORS                               "
				+ "  , CASE                                                         "
				+ "        WHEN REC.RECURSO_TEMPORARIO = 0 THEN 'NAO'               "
				+ "        ELSE 'SIM'                                               "
				+ "    END AS RECURSO_TEMPORARIO                                    "
				+ "  , REC.PERIODO_UTILIZACAO_BOLHA                                 "
				+ "  , REC.TAMANHO_DISCO                                            "
				+ "  , REC.DT_CADASTRO                                              "
				+ "  , REB.RETENCAO_BACKUP                                          "
				+ "  , TIS.TIPO_SERVICO                                             "
				+ "  , CASE                                                         "
				+ "        WHEN REC.ID_ADITIVADO IS NULL THEN 'RECURSO PRINCIPAL'   "
				+ "        ELSE 'RECURSO ADITIVO'                                   "
				+ "    END AS TIPO_RECURSO                                          "
				+ "  , AMB.AMBIENTE                                                 "
				+ "  , CONVERT(VARCHAR, DT_INICIO, 103)  DT_INICIO_VIGENCIA         "
				+ "  , CONVERT(VARCHAR, DT_FINAL , 103)  DT_FINAL_VIGENCIA          "
				+ "  , DATEDIFF( day , DT_INICIO , DT_FINAL)  AS QTY_DIAS_VIGENCIA  "
				+ "  , TEC.DESC_TEMPO AS VIGENCIA                                   "
				+ "  FROM CONTRATO                  AS CON                          "
				+ "   LEFT JOIN RECURSO             AS REC                          "
				+ "          ON REC.ID_CONTRATO           = CON.ID_CONTRATO         "
				+ "   LEFT JOIN SISTEMA_OPERACIONAL AS SIO                          "
				+ "          ON SIO.ID_SO                 = REC.ID_SO               "
				+ "   LEFT JOIN FAMILIA_FLAVORS     AS FAF                          "
				+ "          ON FAF.ID_FAMILIA_FLAVORS    = REC.ID_FAMILIA_FLAVORS  "
				+ "   LEFT JOIN RETENCAO_BACKUP     AS REB                          "
				+ "          ON REB.ID_RETENCAO_BACKUP    = REC.ID_RETENCAO_BACKUP  "
				+ "   LEFT JOIN TIPO_SERVICO        AS TIS                          "
				+ "          ON TIS.ID_TIPO_SERVICO       = REC.ID_TIPO_SERVICO     "
				+ "   LEFT JOIN AMBIENTE            AS AMB                          "
				+ "          ON AMB.ID_AMBIENTE           = REC.ID_AMBIENTE         "
				+ "   LEFT JOIN VIGENCIA            AS VIG                          "
				+ "          ON VIG.ID_CONTRATO           = CON.ID_CONTRATO         "
				+ "  LEFT JOIN TEMPO_CONTRATO       AS TEC                          "
				+ "          ON TEC.ID_TEMPO_CONTRATO = VIG.ID_TEMPO_CONTRATO       "
				+ "  WHERE CON.ID_STATUS_CONTRATO IN ( 1, 4 )                       "
				+ "  AND CON.ID_CONTRATO = ?                                        ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		ResultSet resultado = statemet.executeQuery();
		
		List<ModalRecursosContrato> mdRecursosContratos = new ArrayList<ModalRecursosContrato>();
		DAOUtil daoUtil = new DAOUtil();
		
		while (resultado.next()) {
			ModalRecursosContrato mdRecursosContrato = new ModalRecursosContrato();
			
			mdRecursosContrato.setHostname                ( resultado.getString ( "HOSTNAME"                                          ));
			mdRecursosContrato.setSistema_operacional     ( resultado.getString ( "SISTEMA_OPERACIONAL"                               ));
			mdRecursosContrato.setObs                     ( resultado.getString ( "OBS"                                               ));
			mdRecursosContrato.setFamilia_flavors         ( resultado.getString ( "FAMILIA_FLAVORS"                                   ));
			mdRecursosContrato.setRecurso_temporario      ( resultado.getString ( "RECURSO_TEMPORARIO"                                ));
			mdRecursosContrato.setPeriodo_utilizacao_bolha( resultado.getString ( "PERIODO_UTILIZACAO_BOLHA"                          ));
			mdRecursosContrato.setTamanho_disco           ( resultado.getString ( "TAMANHO_DISCO"                                     ));
			mdRecursosContrato.setDt_cadastro             ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CADASTRO") ));
			mdRecursosContrato.setRetencao_backup         ( resultado.getString ( "RETENCAO_BACKUP"                                   ));
			mdRecursosContrato.setTipo_servico            ( resultado.getString ( "TIPO_SERVICO"                                      ));
			mdRecursosContrato.setTipo_recurso            ( resultado.getString ( "TIPO_RECURSO"                                      ));
			mdRecursosContrato.setAmbiente                ( resultado.getString ( "AMBIENTE"                                          ));
			mdRecursosContrato.setDt_inicio_vigencia      ( resultado.getString ( "DT_INICIO_VIGENCIA"                                ));
			mdRecursosContrato.setDt_final_vigencia       ( resultado.getString ( "DT_FINAL_VIGENCIA"                                 ));
			mdRecursosContrato.setQty_dias_vigencia       ( resultado.getString ( "QTY_DIAS_VIGENCIA"                                 ));
			mdRecursosContrato.setDesc_tempo_vigencia     ( resultado.getString ( "VIGENCIA"                                          ));
			
			mdRecursosContratos.add(mdRecursosContrato);
		}	
		
		return mdRecursosContratos;
		
	}

}
