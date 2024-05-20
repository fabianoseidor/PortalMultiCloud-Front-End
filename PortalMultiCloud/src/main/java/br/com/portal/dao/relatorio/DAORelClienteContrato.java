package br.com.portal.dao.relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOAditivoModal;
import br.com.portal.dao.DAOAditivoModalRecurso;
import br.com.portal.dao.DAOContratoRepository;
import br.com.portal.modelRelatorio.ModelRelClienteContrato;

public class DAORelClienteContrato {
	private Connection connection;	

	public DAORelClienteContrato() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelRelClienteContrato> getListaInfoContratoAtivos( String pep ) throws Exception {

		List<ModelRelClienteContrato> listarelClienteContratos = null;
		DAOContratoRepository daoContratoRepository            = new DAOContratoRepository();
		DAOAditivoModalRecurso daoAditivoModalRecurso          = new DAOAditivoModalRecurso();
		DAOAditivoModal        daoAditivoModal                 = new DAOAditivoModal();
		
		String sql = "SELECT  CON.ID_CONTRATO                                     "
			       + "      , CON.ID_NUVEM                                        "
			       + "      , NUV.MOME_PARCEIRO                                   "
			       + "      , CON.ID_FASE_CONTRATO                                "
			       + "      , FAC.FASE_CONTRATO                                   "
			       + "      , CON.ID_STATUS_CONTRATO                              "
			       + "      , STC.STATUS_CONTRATO                                 "
			       + "      , CON.ID_CLIENTE                                      "
			       + "      , CLI.RAZAO_SOCIAL                                    "
			       + "      , CON.ID_SERVICO_CONTRATADO                           "
			       + "      , SEC.DESC_SERVICO                                    "
			       + "      , CON.ID_SITE                                         "
			       + "      , SIT.NOME                                            "
			       + "      , CON.ID_MOEDA                                        "
			       + "      , MOE.MOEDA                                           "
			       + "      , CON.PEP                                             "
			       + "      , CON.LOGIN_CADASTRO                                  "
			       + "      , CON.VALOR_TOTAL                                     "
			       + "      , CON.VALOR_CONVERTIDO                                "
			       + "      , CON.CUSTO_TOTAL                                     "
			       + "      , CON.COTACAO_MOEDA                                   "
			       + "  FROM                                                      "
			       + "     CONTRATO           AS CON                              "
			       + "   , CLIENTE            AS CLI                              "
			       + "   , NUVEM              AS NUV                              "
			       + "   , FASE_CONTRATO      AS FAC                              "
			       + "   , STATUS_CONTRATO    AS STC                              "
			       + "   , SERVICO_CONTRATADO AS SEC                              "
			       + "   , SITE               AS SIT                              "
			       + "   , MOEDA              AS MOE                              "
			       + " WHERE UPPER( CON.PEP )       LIKE UPPER('%" + pep + "%')   "
			       + "   AND CLI.ID_CLIENTE            = CON.ID_CLIENTE           "
			       + "   AND NUV.ID_NUVEM              = CON.ID_NUVEM             "
			       + "   AND FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO     "
			       + "   AND STC.ID_STATUS_CONTRATO    = CON.ID_STATUS_CONTRATO   "
			       + "   AND SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO"
			       + "   AND SIT.ID_SITE               = CON.ID_SITE              "
			       + "   AND MOE.ID_MOEDA              = CON.ID_MOEDA             ";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  {

			ModelRelClienteContrato relClienteContrato = new ModelRelClienteContrato();
			
			relClienteContrato.setId_contrato           ( resutado.getString  ("ID_CONTRATO"          ));
			relClienteContrato.setId_nuvem              ( resutado.getString  ("ID_NUVEM"             ));
			relClienteContrato.setMome_parceiro         ( resutado.getString  ("MOME_PARCEIRO"        ));
			relClienteContrato.setId_fase_contrato      ( resutado.getString  ("ID_FASE_CONTRATO"     ));
			relClienteContrato.setFase_contrato         ( resutado.getString  ("FASE_CONTRATO"        ));
			relClienteContrato.setId_status_contrato    ( resutado.getString  ("ID_STATUS_CONTRATO"   ));
			relClienteContrato.setStatus_contrato       ( resutado.getString  ("STATUS_CONTRATO"      ));
			relClienteContrato.setId_cliente            ( resutado.getString  ("ID_CLIENTE"           ));
			relClienteContrato.setNome_cliente          ( resutado.getString  ("RAZAO_SOCIAL"         ));
			relClienteContrato.setId_servico_contratado ( resutado.getString  ("ID_SERVICO_CONTRATADO"));
			relClienteContrato.setDesc_servico          ( resutado.getString  ("DESC_SERVICO"         ));
			relClienteContrato.setId_site               ( resutado.getString  ("ID_SITE"              ));
			relClienteContrato.setNome_site             ( resutado.getString  ("NOME"                 ));
			relClienteContrato.setId_moeda              ( resutado.getString  ("ID_MOEDA"             ));
			relClienteContrato.setMoeda                 ( resutado.getString  ("MOEDA"                ));
			relClienteContrato.setPep                   ( resutado.getString  ("PEP"                  ));
			relClienteContrato.setLogin_cadastro        ( resutado.getString  ("LOGIN_CADASTRO"       ));
			relClienteContrato.setValor_total           ( resutado.getString  ("VALOR_TOTAL"          ));
			relClienteContrato.setValor_convertido      ( resutado.getString  ("VALOR_CONVERTIDO"     ));
			relClienteContrato.setCusto_total           ( resutado.getString  ("CUSTO_TOTAL"          ));
			relClienteContrato.setCotacao_moeda         ( resutado.getString  ("COTACAO_MOEDA"        ));
			
			relClienteContrato.setListaRecursoContratos(daoContratoRepository.getListaRecursoContrato        ( Long.parseLong( relClienteContrato.getId_cliente()  ) ));
			relClienteContrato.setListaAitivoRecursos  (daoAditivoModalRecurso.getListaAditivoRecursoID      ( Long.parseLong( relClienteContrato.getId_contrato() ) ));
			relClienteContrato.setListaAditivoProdutos (daoAditivoModal.buscarListaAditivoProdutosContratados( Long.parseLong( relClienteContrato.getId_contrato() ) ));
            if( listarelClienteContratos == null ) listarelClienteContratos = new ArrayList<ModelRelClienteContrato>();
			listarelClienteContratos.add             ( relClienteContrato );
		}

		return listarelClienteContratos;
   }
	public List<ModelRelClienteContrato> getListaInfoContratoDesativos( String pep ) throws Exception {

		List<ModelRelClienteContrato> listarelClienteContratos = null;
		DAOContratoRepository daoContratoRepository            = new DAOContratoRepository();
		DAOAditivoModalRecurso daoAditivoModalRecurso          = new DAOAditivoModalRecurso();
		DAOAditivoModal        daoAditivoModal                 = new DAOAditivoModal();
		
		String sql = "SELECT  CON.ID_CONTRATO                                     "
			       + "      , CON.ID_NUVEM                                        "
			       + "      , NUV.MOME_PARCEIRO                                   "
			       + "      , CON.ID_FASE_CONTRATO                                "
			       + "      , FAC.FASE_CONTRATO                                   "
			       + "      , CON.ID_STATUS_CONTRATO                              "
			       + "      , STC.STATUS_CONTRATO                                 "
			       + "      , CON.ID_CLIENTE                                      "
			       + "      , CLI.RAZAO_SOCIAL                                    "
			       + "      , CON.ID_SERVICO_CONTRATADO                           "
			       + "      , SEC.DESC_SERVICO                                    "
			       + "      , CON.ID_SITE                                         "
			       + "      , SIT.NOME                                            "
			       + "      , CON.ID_MOEDA                                        "
			       + "      , MOE.MOEDA                                           "
			       + "      , CON.PEP                                             "
			       + "      , CON.LOGIN_CADASTRO                                  "
			       + "      , CON.VALOR_TOTAL                                     "
			       + "      , CON.VALOR_CONVERTIDO                                "
			       + "      , CON.CUSTO_TOTAL                                     "
			       + "      , CON.COTACAO_MOEDA                                   "
			       + "  FROM                                                      "
			       + "     CONTRATO           AS CON                              "
			       + "   , CLIENTE            AS CLI                              "
			       + "   , NUVEM              AS NUV                              "
			       + "   , FASE_CONTRATO      AS FAC                              "
			       + "   , STATUS_CONTRATO    AS STC                              "
			       + "   , SERVICO_CONTRATADO AS SEC                              "
			       + "   , SITE               AS SIT                              "
			       + "   , MOEDA              AS MOE                              "
			       + " WHERE UPPER( CON.PEP )       LIKE UPPER('%" + pep + "%')   "
			       + "   AND CON.ID_STATUS_CONTRATO   != 1                        "
			       + "   AND CLI.ID_CLIENTE            = CON.ID_CLIENTE           "
			       + "   AND NUV.ID_NUVEM              = CON.ID_NUVEM             "
			       + "   AND FAC.ID_FASE_CONTRATO      = CON.ID_FASE_CONTRATO     "
			       + "   AND STC.ID_STATUS_CONTRATO    = CON.ID_STATUS_CONTRATO   "
			       + "   AND SEC.ID_SERVICO_CONTRATADO = CON.ID_SERVICO_CONTRATADO"
			       + "   AND SIT.ID_SITE               = CON.ID_SITE              "
			       + "   AND MOE.ID_MOEDA              = CON.ID_MOEDA             ";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  {

			ModelRelClienteContrato relClienteContrato = new ModelRelClienteContrato();
			
			relClienteContrato.setId_contrato           ( resutado.getString  ("ID_CONTRATO"          ));
			relClienteContrato.setId_nuvem              ( resutado.getString  ("ID_NUVEM"             ));
			relClienteContrato.setMome_parceiro         ( resutado.getString  ("MOME_PARCEIRO"        ));
			relClienteContrato.setId_fase_contrato      ( resutado.getString  ("ID_FASE_CONTRATO"     ));
			relClienteContrato.setFase_contrato         ( resutado.getString  ("FASE_CONTRATO"        ));
			relClienteContrato.setId_status_contrato    ( resutado.getString  ("ID_STATUS_CONTRATO"   ));
			relClienteContrato.setStatus_contrato       ( resutado.getString  ("STATUS_CONTRATO"      ));
			relClienteContrato.setId_cliente            ( resutado.getString  ("ID_CLIENTE"           ));
			relClienteContrato.setNome_cliente          ( resutado.getString  ("RAZAO_SOCIAL"         ));
			relClienteContrato.setId_servico_contratado ( resutado.getString  ("ID_SERVICO_CONTRATADO"));
			relClienteContrato.setDesc_servico          ( resutado.getString  ("DESC_SERVICO"         ));
			relClienteContrato.setId_site               ( resutado.getString  ("ID_SITE"              ));
			relClienteContrato.setNome_site             ( resutado.getString  ("NOME"                 ));
			relClienteContrato.setId_moeda              ( resutado.getString  ("ID_MOEDA"             ));
			relClienteContrato.setMoeda                 ( resutado.getString  ("MOEDA"                ));
			relClienteContrato.setPep                   ( resutado.getString  ("PEP"                  ));
			relClienteContrato.setLogin_cadastro        ( resutado.getString  ("LOGIN_CADASTRO"       ));
			relClienteContrato.setValor_total           ( resutado.getString  ("VALOR_TOTAL"          ));
			relClienteContrato.setValor_convertido      ( resutado.getString  ("VALOR_CONVERTIDO"     ));
			relClienteContrato.setCusto_total           ( resutado.getString  ("CUSTO_TOTAL"          ));
			relClienteContrato.setCotacao_moeda         ( resutado.getString  ("COTACAO_MOEDA"        ));
			
			relClienteContrato.setListaRecursoContratos(daoContratoRepository.getListaRecursoContrato        ( Long.parseLong( relClienteContrato.getId_cliente()  ) ));
			relClienteContrato.setListaAitivoRecursos  (daoAditivoModalRecurso.getListaAditivoRecursoID      ( Long.parseLong( relClienteContrato.getId_contrato() ) ));
			relClienteContrato.setListaAditivoProdutos (daoAditivoModal.buscarListaAditivoProdutosContratados( Long.parseLong( relClienteContrato.getId_contrato() ) ));
            if( listarelClienteContratos == null ) listarelClienteContratos = new ArrayList<ModelRelClienteContrato>();
			listarelClienteContratos.add             ( relClienteContrato );
		}

		return listarelClienteContratos;
   }
	
}
