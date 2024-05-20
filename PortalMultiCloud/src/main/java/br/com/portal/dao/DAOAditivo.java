package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelAditivo;
import br.com.portal.model.ModelAditivoProduto;
import br.com.portal.model.ModelListaAditivo;
import br.com.portal.model.ModelListaRecursoContratoAditivo;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelTempoContrato;
import br.com.portal.model.ModelTipoAditivo;
import br.com.portal.model.ModelVigenciaAditivo;

public class DAOAditivo {
	
	private Connection connection;
	
	public DAOAditivo() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	/*
	 * 
	 * Funcao de atualizacao e insersao de um Contrato.
	 * 
	 * */	
	public ModelAditivo gravarAditivo( ModelAditivo objeto ) throws Exception {

		if( objeto.isNovo() ){
			String sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO , ID_FASE_CONTRATO, ID_CICLO_UPDATE           , ID_TIPO_ADITIVO, ID_CONTRATO   , DESC_SERV_CONTRATADO, "
					   + "                        RECURSO_TEMPORARIO, ADTI_SEM_RECEITA, APROVACAO_ADIT_SEM_RECEITA, DT_CRIACAO     , VLR_TOTAL_ADIT, OBSERVACAO )"
					   + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                                  ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_status_aditivo()                          ); // ID_STATUS_ADITIVO
			prepareSql.setLong     ( 2, objeto.getId_fase_contrato()                           ); // ID_FASE_CONTRATO
			prepareSql.setLong     ( 3, objeto.getId_ciclo_update()                            ); // ID_CICLO_UPDATE
			prepareSql.setLong     ( 4, objeto.getId_tipo_aditivo()                            ); // ID_TIPO_ADITIVO
			prepareSql.setLong     ( 5, objeto.getId_contrato()                                ); // ID_CONTRATO
			prepareSql.setString   ( 6, objeto.getDesc_serv_contratado()                       ); // DESC_SERV_CONTRATADO
			prepareSql.setInt      ( 7, objeto.getRecurso_temporario()                         ); // RECURSO_TEMPORARIO
			prepareSql.setInt      ( 8, objeto.getAdti_sem_receita()                           ); // ADTI_SEM_RECEITA
			prepareSql.setInt      ( 9, objeto.getAprovacao_adit_sem_receita()                 ); // APROVACAO_ADIT_SEM_RECEITA
			prepareSql.setTimestamp(10, new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setString   (12, objeto.getVlr_toral_adit()                             ); // VLR_TORAL_ADIT
			prepareSql.setString   (13, objeto.getObservacao()                                 ); // OBSERVACAO

			prepareSql.execute();
			connection.commit();
			
			objeto.setId_aditivado( this.getMaxIdAditivo( objeto ));
			
			ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
			vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()        );
			vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaAditivo.setDt_final         ( objeto.getDt_final()            );
			vigenciaAditivo.setObservacao       ( objeto.getObservacao_vigencia() );
			
			vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
			objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
			
		}
		
		else {
			String sql = "UPDATE ADITIVADO                                                                                                                             "
					   + "   SET ID_STATUS_ADITIVO  = ?, ID_FASE_CONTRATO = ?, ID_CICLO_UPDATE            = ?, ID_TIPO_ADITIVO        = ?, DESC_SERV_CONTRATADO   = ?, "
					   + "       RECURSO_TEMPORARIO = ?, ADTI_SEM_RECEITA = ?, APROVACAO_ADIT_SEM_RECEITA = ?, VLR_TOTAL_ADIT         = ?,                             "
					   + "       OBSERVACAO         = ?                                                                                                                "
					   + " WHERE ID_ADITIVADO = ?                                                                                                                      " ;

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_status_aditivo()          ); // ID_STATUS_ADITIVO
			prepareSql.setLong     ( 2, objeto.getId_fase_contrato()           ); // ID_STATUS_ADITIVO 
			prepareSql.setLong     ( 3, objeto.getId_ciclo_update()            ); // ID_CICLO_UPDATE
			prepareSql.setLong     ( 4, objeto.getId_tipo_aditivo()            ); // ID_TIPO_ADITIVO
			prepareSql.setString   ( 5, objeto.getDesc_serv_contratado()       ); // DESC_SERV_CONTRATADO
			prepareSql.setInt      ( 6, objeto.getRecurso_temporario()         ); // RECURSO_TEMPORARIO
			prepareSql.setInt      ( 7, objeto.getAdti_sem_receita()           ); // ADTI_SEM_RECEITA
			prepareSql.setInt      ( 8, objeto.getAprovacao_adit_sem_receita() ); // APROVACAO_ADIT_SEM_RECEITA
			prepareSql.setString   ( 9, objeto.getVlr_toral_adit()             ); // VLR_TORAL_ADIT
			prepareSql.setString   (10, objeto.getObservacao()                 ); // OBSERVACAO
			prepareSql.setLong     (11, objeto.getId_aditivado()               ); // ID_ADITIVADO

			prepareSql.executeUpdate();
			connection.commit();
		}
		return this.consultaAdtivoID( objeto.getId_aditivado() );
	}
	/*
	 * 
	 * Busca o ID Aditivo apos cadastro do Aditivo
	 * 
	 * */	
	public Long getMaxIdAditivo( ModelAditivo objeto ) throws SQLException {
		String sql = "SELECT MAX( ID_ADITIVADO ) MAX_ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ? AND ID_STATUS_ADITIVO = ? AND ID_FASE_CONTRATO = ? AND ID_CICLO_UPDATE = ? AND ID_TIPO_ADITIVO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1, objeto.getId_contrato()       );
		statemet.setLong ( 2, objeto.getId_status_aditivo() );
		statemet.setLong ( 3, objeto.getId_fase_contrato()  );
		statemet.setLong ( 4, objeto.getId_ciclo_update()   );
		statemet.setLong ( 5, objeto.getId_tipo_aditivo()   );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_ADITIVADO");

		return null;
	}

	public ModelVigenciaAditivo gravarVigenciaAditivo( ModelVigenciaAditivo objeto ) throws Exception {
		
		String sql = "INSERT INTO VIGENCIA_ADITIVO ( ID_ADITIVADO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, OBSERVACAO)"
		 		  + " VALUES ( ?, ?, ?, ?, ?, ? ) ";
		
		DAOUtil daoUtil = new DAOUtil();

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, objeto.getId_aditivado()                               );
		prepareSql.setLong     ( 2, objeto.getId_tempo_contrato()                          );
		prepareSql.setDate     ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio())        );
		prepareSql.setDate     ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())         );
		prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) );
		prepareSql.setString   ( 6, objeto.getObservacao()                                 );
		
		prepareSql.execute();
		connection.commit();
		
		objeto.setId_vigencia( this.getIdVigencia(objeto) );
				
		return objeto;
	}
	
	public Long getIdVigencia( ModelVigenciaAditivo objeto ) throws SQLException {
		DAOUtil daoUtil = new DAOUtil();

		String sql = "SELECT MAX(ID_VIGENCIA) AS MAX_ID_VIGENCIA "
				+ "  FROM VIGENCIA_ADITIVO                       "
				+ "  WHERE ID_ADITIVADO      = ?                 "
				+ "    AND ID_TEMPO_CONTRATO = ?                 "
				+ "    AND DT_INICIO         = ?                 "
				+ "    AND DT_FINAL          = ?                 ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, objeto.getId_aditivado()                        );
		prepareSql.setLong ( 2, objeto.getId_tempo_contrato()                   );
		prepareSql.setDate ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio()) );
		prepareSql.setDate ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())  );
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next())  return  resutado.getLong("MAX_ID_VIGENCIA");

		return 0L;		
	}

	/*
	 * 
	 * Consulta um Contrato pelo ID. 
	 * 
	 * */	
	public ModelAditivo consultaAdtivoID( Long idAditivo ) throws SQLException {
		
		ModelAditivo            modelAditivo = new ModelAditivo();
		ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT ID_ADITIVADO      , ID_STATUS_ADITIVO, ID_FASE_CONTRATO          , ID_CICLO_UPDATE, ID_TIPO_ADITIVO       , ID_CONTRATO   , DESC_SERV_CONTRATADO, "
				   + "       RECURSO_TEMPORARIO, ADTI_SEM_RECEITA , APROVACAO_ADIT_SEM_RECEITA, DT_CRIACAO     , VLR_TOTAL_ADIT, OBSERVACAO                                    "
				   + " FROM ADITIVADO WHERE ID_ADITIVADO = "+ idAditivo ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
				
		while (resultado.next()) {
			
			modelAditivo.setId_aditivado              ( resultado.getLong  ("ID_ADITIVADO"                                       ) ); // ID_ADITIVADO
			modelAditivo.setId_status_aditivo         ( resultado.getLong  ("ID_STATUS_ADITIVO"                                  ) ); // ID_STATUS_ADITIVO
			modelAditivo.setId_fase_contrato          ( resultado.getLong  ("ID_FASE_CONTRATO"                                   ) ); // ID_FASE_CONTRATO
			modelAditivo.setId_ciclo_update           ( resultado.getLong  ("ID_CICLO_UPDATE"                                    ) ); // ID_CICLO_UPDATE
			modelAditivo.setId_tipo_aditivo           ( resultado.getLong  ("ID_TIPO_ADITIVO"                                    ) ); // ID_TIPO_ADITIVO
			modelAditivo.setId_contrato               ( resultado.getLong  ("ID_CONTRATO"                                        ) ); // ID_CONTRATO
			modelAditivo.setDesc_serv_contratado      ( resultado.getString("DESC_SERV_CONTRATADO"                               ) ); // DESC_SERV_CONTRATADO			
			modelAditivo.setRecurso_temporario        ( resultado.getInt   ("RECURSO_TEMPORARIO"                                 ) ); // RECURSO_TEMPORARIO 
			modelAditivo.setAdti_sem_receita          ( resultado.getInt   ("ADTI_SEM_RECEITA"                                   ) ); // ADTI_SEM_RECEITA
			modelAditivo.setAprovacao_adit_sem_receita( resultado.getInt   ("APROVACAO_ADIT_SEM_RECEITA"                         ) ); // APROVACAO_ADIT_SEM_RECEITA
			modelAditivo.setDt_criacao                ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) ); // DT_CRIACAO
//			modelAditivo.setVlr_toral_adit            ( resultado.getString("VLR_TOTAL_ADIT"                                     ) ); // VLR_TORAL_ADIT
			modelAditivo.setObservacao                ( resultado.getString("OBSERVACAO"                                         ) ); // OBSERVACAO

			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resultado.getString("VLR_TOTAL_ADIT") );
			modelAditivo.setVlr_toral_adit            ( dinheiro.format(valorReal                                                ) ); // VLR_TORAL_ADIT

			String retornoCliente = this.buscaCliente  ( resultado.getLong("ID_CONTRATO") );
			String textoSplit [] = retornoCliente.split(";");
			
			modelAditivo.setId_cliente ( Long.parseLong( textoSplit[0] ) );
			modelAditivo.setNomeCliente( textoSplit[1]                   );

			vigenciaAditivo = this.getAditivoVigencia( modelAditivo.getId_aditivado()   );	
			modelAditivo.setId_vigencia        ( vigenciaAditivo.getId_vigencia()       );
			modelAditivo.setId_tempo_contrato  ( vigenciaAditivo.getId_tempo_contrato() );
			modelAditivo.setDt_inicio          ( vigenciaAditivo.getDt_inicio()         );
			modelAditivo.setDt_final           ( vigenciaAditivo.getDt_final()          );
			modelAditivo.setDt_criacao_vigencia( vigenciaAditivo.getDt_criacao()        );
			modelAditivo.setObservacao_vigencia( vigenciaAditivo.getObservacao()        );
			
		}
		
		return modelAditivo;
	}

	public String buscaCliente( Long idContrato ) throws SQLException {
		
		String sql = "SELECT ID_CONTRATO                   "
				+ "     , CONT.ID_CLIENTE                  "
				+ "     , CLI.ID_CLIENTE                   "
				+ "     , CLI.RAZAO_SOCIAL                 "
				+ "   FROM                                 "
				+ "       CONTRATO AS CONT                 "
				+ "     , CLIENTE  AS CLI                  "
				+ "  WHERE ID_CONTRATO = ?                 "
				+ "    AND CLI.ID_CLIENTE = CONT.ID_CLIENTE";
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1,  idContrato );
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) return resutado.getLong("ID_CLIENTE") + ";" + resutado.getString("RAZAO_SOCIAL");

		return "";
	}

	public ModelVigenciaAditivo getAditivoVigencia( Long idAditivo ) throws SQLException {
		ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT ID_VIGENCIA, ID_ADITIVADO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, DT_DESATIVACAO, OBSERVACAO FROM VIGENCIA_ADITIVO WHERE ID_ADITIVADO = ? AND DT_DESATIVACAO IS NULL";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1,  idAditivo );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			vigenciaAditivo.setId_vigencia      ( resutado.getLong("ID_VIGENCIA"                                             ) );
			vigenciaAditivo.setId_vigencia      ( resutado.getLong("ID_ADITIVADO"                                            ) );
			vigenciaAditivo.setId_tempo_contrato( resutado.getLong("ID_TEMPO_CONTRATO"                                       ) );
			vigenciaAditivo.setDt_inicio        ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_INICIO")         ) );
			vigenciaAditivo.setDt_final         ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_FINAL")          ) );
			vigenciaAditivo.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO")    ) );
			vigenciaAditivo.setDt_desativacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_DESATIVACAO")) );
			vigenciaAditivo.setObservacao       ( resutado.getString("OBSERVACAO"                                            ) );
		}
		return vigenciaAditivo;		
	}
	
	public List<ModelTipoAditivo> listaTipoAditivoSelect(){
		
//		String sql = "SELECT ID_TIPO_ADITIVO, DESC_TIPO_DITIVO FROM TIPO_ADITIVO ORDER BY ID_TIPO_ADITIVO";
		String sql = "SELECT ID_TIPO_ADITIVO, DESC_TIPO_DITIVO FROM TIPO_ADITIVO WHERE ID_TIPO_ADITIVO IN( 3,4,6,8 ) ORDER BY ID_TIPO_ADITIVO";
		
		
		
		List<ModelTipoAditivo> modelTipoAditivos = new ArrayList<ModelTipoAditivo>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoAditivo modelTipoAditivo = new ModelTipoAditivo();
				modelTipoAditivo.setId_tipo_aditivo(set.getLong("ID_TIPO_ADITIVO"));
				modelTipoAditivo.setDesc_tipo_ditivo(set.getString("DESC_TIPO_DITIVO"));

				modelTipoAditivos.add(modelTipoAditivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTipoAditivos;
	}
	
	public List<ModelTempoContrato> getListaTempoContrato( ) throws SQLException {
		
		List<ModelTempoContrato> listaTempoContratos = new ArrayList<ModelTempoContrato>();

		String sql = "SELECT ID_TEMPO_CONTRATO, DESC_TEMPO, TEMPO_DIA, TEMPO_SEMANA, TEMPO_MESES, TEMPO_ANO FROM TEMPO_CONTRATO ORDER BY ID_TEMPO_CONTRATO" ;
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet         resutado   = prepareSql.executeQuery();
		
		while (resutado.next()) {
			ModelTempoContrato listaTempoContrato = new ModelTempoContrato();
			listaTempoContrato.setId_tempo_contrato( resutado.getLong  ("ID_TEMPO_CONTRATO" ));  // 1
			listaTempoContrato.setDesc_tempo       ( resutado.getString("DESC_TEMPO"        ));  // 2
			listaTempoContrato.setTempo_dia        ( resutado.getInt   ("TEMPO_DIA"         ));  // 3
			listaTempoContrato.setTempo_semana     ( resutado.getInt   ("TEMPO_SEMANA"      ));  // 4
			listaTempoContrato.setTempo_meses      ( resutado.getInt   ("TEMPO_MESES"       ));  // 5
			listaTempoContrato.setTempo_ano        ( resutado.getInt   ("TEMPO_ANO"         ));  // 6
			listaTempoContratos.add(listaTempoContrato);
		}
		return listaTempoContratos;
	}

	public ModelTempoContrato getTempoContrato( Long idTempoCont ) throws SQLException {
		
		ModelTempoContrato listaTempoContrato = new ModelTempoContrato();

		String sql = "SELECT ID_TEMPO_CONTRATO, DESC_TEMPO, TEMPO_DIA, TEMPO_SEMANA, TEMPO_MESES, TEMPO_ANO FROM TEMPO_CONTRATO WHERE ID_TEMPO_CONTRATO = ?" ;
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, idTempoCont );
		ResultSet         resutado   = prepareSql.executeQuery();
		
		while (resutado.next()) {
			listaTempoContrato.setId_tempo_contrato( resutado.getLong  ("ID_TEMPO_CONTRATO" ));  // 1
			listaTempoContrato.setDesc_tempo       ( resutado.getString("DESC_TEMPO"        ));  // 2
			listaTempoContrato.setTempo_dia        ( resutado.getInt   ("TEMPO_DIA"         ));  // 3
			listaTempoContrato.setTempo_semana     ( resutado.getInt   ("TEMPO_SEMANA"      ));  // 4
			listaTempoContrato.setTempo_meses      ( resutado.getInt   ("TEMPO_MESES"       ));  // 5
			listaTempoContrato.setTempo_ano        ( resutado.getInt   ("TEMPO_ANO"         ));  // 6
		}
		return listaTempoContrato;
	}
	
	public List<ModelListaAditivo> getListaAditivo( Long idContrato ){
		
		String sql = "SELECT  ADI.ID_ADITIVADO                          "
				+ "         , ADI.ID_STATUS_ADITIVO                     "
				+ "	        , SC.STATUS_CONTRATO                        "
				+ "         , ADI.ID_FASE_CONTRATO                      "
				+ "	        , FC.FASE_CONTRATO                          "
				+ "         , ADI.ID_CICLO_UPDATE                       "
				+ "	        , CU.DESCRICAO                              "
				+ "         , ADI.ID_CONTRATO                           "
				+ "         , ADI.DESC_SERV_CONTRATADO                  "
				+ "         , ADI.RECURSO_TEMPORARIO                    "
				+ "         , ADI.ADTI_SEM_RECEITA                      "
				+ "         , ADI.APROVACAO_ADIT_SEM_RECEITA            "
				+ "         , ADI.DT_CRIACAO                            "
				+ "         , ADI.VLR_TOTAL_ADIT                        "
				+ "         , ADI.OBSERVACAO                            "
				+ "  FROM                                               "
				+ "    ADITIVADO       AS ADI                           "
				+ "  , STATUS_CONTRATO AS SC                            "
				+ "  , FASE_CONTRATO   AS FC                            "
				+ "  , CICLO_UPDATE    AS CU                            "
				+ " WHERE ADI.ID_CONTRATO       = ?                     "
				+ "   AND SC.ID_STATUS_CONTRATO = ADI.ID_STATUS_ADITIVO "
				+ "   AND FC.ID_FASE_CONTRATO   = ADI.ID_FASE_CONTRATO  "
				+ "   AND CU.ID_CICLO_UPDATE    = ADI.ID_CICLO_UPDATE   ";
		
		List<ModelListaAditivo> modelListaAditivos = new ArrayList<ModelListaAditivo>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelListaAditivo modelListaAditivo = new ModelListaAditivo();
				modelListaAditivo.setId_aditivado              ( set.getLong  ("ID_ADITIVADO"              ) );    
				modelListaAditivo.setId_status_aditivo         ( set.getLong  ("ID_STATUS_ADITIVO"         ) );
				modelListaAditivo.setStatus_contrato           ( set.getString("STATUS_CONTRATO"           ) );
				modelListaAditivo.setId_fase_contrato          ( set.getLong  ("ID_FASE_CONTRATO"          ) );
				modelListaAditivo.setFase_contrato             ( set.getString("FASE_CONTRATO"             ) );
				modelListaAditivo.setId_ciclo_update           ( set.getLong  ("ID_CICLO_UPDATE"           ) );
				modelListaAditivo.setDescricao                 ( set.getString("DESCRICAO"                 ) );
				modelListaAditivo.setId_contrato               ( set.getLong  ("ID_CONTRATO"               ) );
				modelListaAditivo.setDesc_serv_contratado      ( set.getString("DESC_SERV_CONTRATADO"      ) );
				modelListaAditivo.setRecurso_temporario        ( set.getString("RECURSO_TEMPORARIO").equals("1")         ? "Sim" : set.getString("RECURSO_TEMPORARIO").equals("2")         ? "Não"  : null );
				modelListaAditivo.setAdti_sem_receita          ( set.getString("ADTI_SEM_RECEITA").equals("1")           ? "Sim" : set.getString("ADTI_SEM_RECEITA").equals("2")           ? "Não"  : null );
				modelListaAditivo.setAprovacao_adit_sem_receita( set.getString("APROVACAO_ADIT_SEM_RECEITA").equals("1") ? "Sim" : set.getString("APROVACAO_ADIT_SEM_RECEITA").equals("2") ? "Não"  : null );
				modelListaAditivo.setDt_criacao(daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")));
				modelListaAditivo.setObservacao                ( set.getString("OBSERVACAO"                ) );
								
				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VLR_TOTAL_ADIT") );
				modelListaAditivo.setVlr_toral_adit( dinheiro.format(valorReal) );

				modelListaAditivos.add(modelListaAditivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelListaAditivos;
	}
	
	public Boolean isExistProduto( Long idAditivo, Long idProduto ) throws Exception {

		String sql = "SELECT COUNT(1) AS TOTAL FROM ADITIVADO_PRODUTO WHERE ID_ADITIVADO = ? AND ID_PRODUTO  = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idAditivo );
		prepareSql.setLong     ( 2, idProduto  );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return ( resutado.getInt("TOTAL")> 0 ? true: false  );

		return false;
   }
	public List<ModelAditivoProduto> InsertProduto( ModelAditivoProduto objeto ) throws Exception {

		String sql = "INSERT INTO ADITIVADO_PRODUTO ( ID_ADITIVADO, ID_PRODUTO, DT_CADASTRO, QTY_PRODUTO, VALOR_UNITARIO, VALOR ) VALUES ( ?, ?, ?, ?, ?, ? )";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		prepareSql.setLong     ( 1, objeto.getId_aditivo()                                 );
		prepareSql.setLong     ( 2, objeto.getId_produto()                                 );
		prepareSql.setTimestamp( 3, new java.sql.Timestamp(new java.util.Date().getTime()) );
		prepareSql.setInt      ( 4, objeto.getQty_servico()                                ); 
		prepareSql.setString   ( 5, objeto.getValor_unitario()                             );
		prepareSql.setString   ( 6, objeto.getValor()                                      );

		prepareSql.execute();
		connection.commit();

		return this.getListaProdutoAditivo( objeto.getId_aditivo() );
		
	}
	
	public List<ModelAditivoProduto> getListaProdutoAditivo( Long idAditivo ) throws Exception {

		List<ModelAditivoProduto> listaAditivoProdutos = new ArrayList<ModelAditivoProduto>();
		DAOUtil daoUtil = new DAOUtil();
		String sql = "SELECT CP.ID_ADITIVADO, CP.ID_PRODUTO, PRO.PRODUTO, CP.DT_CADASTRO, CP.QTY_PRODUTO, CP.VALOR_UNITARIO, CP.VALOR "
				   + "  FROM                                                                                                          "
				   + "    ADITIVADO_PRODUTO AS CP                                                                                     "
				   + "  , PRODUTO           AS PRO                                                                                    "
				   + " WHERE ID_ADITIVADO = ?                                                                                         "
				   + "   AND PRO.ID_PRODUTO = CP.ID_PRODUTO                                                                           ";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idAditivo );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  {

			ModelAditivoProduto modelAditivoProduto = new ModelAditivoProduto();
			modelAditivoProduto.setId_aditivo    (resutado.getLong   ("ID_ADITIVADO"  ));
			modelAditivoProduto.setId_produto    ( resutado.getLong  ("ID_PRODUTO"    ));
			modelAditivoProduto.setProduto       ( resutado.getString("PRODUTO"       ));
			modelAditivoProduto.setQty_servico   ( resutado.getInt   ("QTY_SERVICO"   ));
			modelAditivoProduto.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ));

			Locale localeBR = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorRealUni = Double.valueOf( resutado.getString("VALOR_UNITARIO") ) ;
			Double valorReal    = Double.valueOf( resutado.getString("VALOR"         ) ) ;
			
			modelAditivoProduto.setValor_unitario( dinheiro.format(valorRealUni));
			modelAditivoProduto.setValor         ( dinheiro.format(valorReal   ));
			listaAditivoProdutos.add              ( modelAditivoProduto );
		}
		return listaAditivoProdutos;
   }

	
	public List<ModelListaAditivo> buscarListaAditivo( Long idContrato, Integer offsetBegin, Integer offsetEnd ) throws SQLException {
		
		String sql = "SELECT  ADI.ID_ADITIVADO                          "
				+ "         , ADI.ID_STATUS_ADITIVO                     "
				+ "	        , SC.STATUS_CONTRATO                        "
				+ "         , ADI.ID_FASE_CONTRATO                      "
				+ "	        , FC.FASE_CONTRATO                          "
				+ "         , ADI.ID_CICLO_UPDATE                       "
				+ "	        , CU.DESCRICAO                              "
				+ "         , ADI.ID_TIPO_ADITIVO                       "
				+ "	        , TA.DESC_TIPO_DITIVO                       "
				+ "         , ADI.ID_CONTRATO                           "
				+ "         , ADI.DESC_SERV_CONTRATADO                  "
				+ "         , ADI.RECURSO_TEMPORARIO                    "
				+ "         , ADI.ADTI_SEM_RECEITA                      "
				+ "         , ADI.APROVACAO_ADIT_SEM_RECEITA            "
				+ "         , ADI.DT_CRIACAO                            "
				+ "         , ADI.VLR_TOTAL_ADIT                        "
				+ "         , ADI.OBSERVACAO                            "
				+ "  FROM                                               "
				+ "    ADITIVADO       AS ADI                           "
				+ "  , STATUS_CONTRATO AS SC                            "
				+ "  , FASE_CONTRATO   AS FC                            "
				+ "  , CICLO_UPDATE    AS CU                            "
				+ "  , TIPO_ADITIVO    AS TA                            "
				+ " WHERE ADI.ID_CONTRATO       = ?                     "  
				+ "   AND SC.ID_STATUS_CONTRATO = ADI.ID_STATUS_ADITIVO "
				+ "   AND FC.ID_FASE_CONTRATO   = ADI.ID_FASE_CONTRATO  "
				+ "   AND CU.ID_CICLO_UPDATE    = ADI.ID_CICLO_UPDATE   "
				+ "   AND TA.ID_TIPO_ADITIVO    = ADI.ID_TIPO_ADITIVO   "
				+ " ORDER BY ADI.ID_ADITIVADO OFFSET " + offsetBegin +  " ROWS FETCH NEXT "+ offsetEnd + " ROWS ONLY";


		List<ModelListaAditivo> modelListaAditivos = new ArrayList<ModelListaAditivo>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelListaAditivo modelListaAditivo = new ModelListaAditivo();
				modelListaAditivo.setId_aditivado              ( set.getLong  ("ID_ADITIVADO"              ) );    
				modelListaAditivo.setId_tipo_aditivo           ( set.getLong  ("ID_TIPO_ADITIVO"           ) );
				modelListaAditivo.setId_status_aditivo         ( set.getLong  ("ID_STATUS_ADITIVO"         ) );
				modelListaAditivo.setStatus_contrato           ( set.getString("STATUS_CONTRATO"           ) );
				modelListaAditivo.setId_fase_contrato          ( set.getLong  ("ID_FASE_CONTRATO"          ) );
				modelListaAditivo.setFase_contrato             ( set.getString("FASE_CONTRATO"             ) );
				modelListaAditivo.setId_ciclo_update           ( set.getLong  ("ID_CICLO_UPDATE"           ) );
				modelListaAditivo.setDescricao                 ( set.getString("DESCRICAO"                 ) );
				modelListaAditivo.setId_tipo_aditivo           ( set.getLong  ("ID_TIPO_ADITIVO"           ) );
				modelListaAditivo.setDesc_tipo_ditivo          ( set.getString("DESC_TIPO_DITIVO"          ) );
				modelListaAditivo.setId_contrato               ( set.getLong  ("ID_CONTRATO"               ) );
				modelListaAditivo.setDesc_serv_contratado      ( set.getString("DESC_SERV_CONTRATADO"      ) );
				modelListaAditivo.setRecurso_temporario        ( set.getString("RECURSO_TEMPORARIO").equals("1")         ? "Sim" : set.getString("RECURSO_TEMPORARIO").equals("2")         ? "Não"  : null );
				modelListaAditivo.setAdti_sem_receita          ( set.getString("ADTI_SEM_RECEITA").equals("1")           ? "Sim" : set.getString("ADTI_SEM_RECEITA").equals("2")           ? "Não"  : null );
				modelListaAditivo.setAprovacao_adit_sem_receita( set.getString("APROVACAO_ADIT_SEM_RECEITA").equals("1") ? "Sim" : set.getString("APROVACAO_ADIT_SEM_RECEITA").equals("2") ? "Não"  : null );
				modelListaAditivo.setDt_criacao(daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")));
				modelListaAditivo.setObservacao                ( set.getString("OBSERVACAO"                ) );
								
				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VLR_TOTAL_ADIT") );
				modelListaAditivo.setVlr_toral_adit( dinheiro.format(valorReal) );

				modelListaAditivos.add(modelListaAditivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelListaAditivos;
	}

	public Integer getTotalPag( Integer offsetEnd, Long idContrato ) throws SQLException {
		String sql = "SELECT COUNT( ID_CONTRATO ) AS COUNT_ID_CONTRATO FROM ADITIVADO WHERE ID_CONTRATO = ?";
		Double totalClientes = 0.0;
		Double pagina        = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong( 1, idContrato );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) totalClientes = resutado.getDouble("COUNT_ID_CONTRATO");
		
		pagina = totalClientes / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}

	public List<ModelProduto> getListaProduto( ) throws SQLException {
		
		List<ModelProduto> listaProdutos = new ArrayList<ModelProduto>();

		String sql = "SELECT ID_PRODUTO, PRODUTO, VALOR, OBSERVACAO FROM PRODUTO ORDER BY ID_PRODUTO" ;
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			ModelProduto listaProduto = new ModelProduto();
			listaProduto.setId_produto( resutado.getLong("ID_PRODUTO"  ) );  // 1
			listaProduto.setProduto   ( resutado.getString("PRODUTO"   ) );  // 2
			listaProduto.setValor     ( resutado.getString("VALOR"     ) );  // 3
			listaProduto.setObs       ( resutado.getString("OBSERVACAO") );  // 4
			listaProdutos.add(listaProduto);
		}
		return listaProdutos;
	}

	public ModelProduto getValoresProduto( Long IdProduto ) throws SQLException {
		
		ModelProduto listaProduto = new ModelProduto();

		String sql = "SELECT ID_PRODUTO, PRODUTO, VALOR, OBSERVACAO FROM PRODUTO WHERE ID_PRODUTO = ?" ;
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, IdProduto );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			listaProduto.setId_produto( resutado.getLong("ID_PRODUTO"  ) );  // 1
			listaProduto.setProduto   ( resutado.getString("PRODUTO"   ) );  // 2
//			listaProduto.setValor     ( resutado.getString("VALOR"     ) );  // 3
			listaProduto.setObs       ( resutado.getString("OBSERVACAO") );  // 4
			
			Locale localeBR = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal = Double.valueOf( resutado.getString("VALOR"     ) ) ;
			String vlr = dinheiro.format(valorReal);
			listaProduto.setValor( vlr );			

		}
		return listaProduto;
	}

	public List<ModelListaRecursoContratoAditivo> getListaRecursoAditivo( Long idContrato ) throws SQLException {
		
		List<ModelListaRecursoContratoAditivo> listaRecursoContratos = new ArrayList<ModelListaRecursoContratoAditivo>();
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT  ID_RECURSO                                "
				+ "      , SR.STATUS_RECURSO                            "
				+ "      , RB.RETENCAO_BACKUP                           "
				+ "      , TD.TIPO_DISCO                                "
				+ "      , SO.SISTEMA_OPERACIONAL                       "
				+ "      , AMB.AMBIENTE                                 "
				+ "      , FF.FAMILIA                                   "
				+ "      , TS.TIPO_SERVICO                              "
				+ "      , REC.DT_CADASTRO                              "
				+ "      , REC.HOSTNAME                                 "
				+ "      , REC.TAMANHO_DISCO                            "
				+ "      , REC.PRIMARY_IP                               "
				+ "      , REC.TAG_VCENTER                              "
				+ "      , REC.OBS                                      "
				+ "      , REC.ID_CONTRATO                              "
				+ "      , REC.ID_ADITIVADO                             "
				+ "  FROM                                               "
				+ "    RECURSO             AS REC                       "
				+ "  , STATUS_RECURSO      AS SR                        "
				+ "  , RETENCAO_BACKUP     AS RB                        "
				+ "  , TIPO_DISCO          AS TD                        "
				+ "  , SISTEMA_OPERACIONAL AS SO                        "
				+ "  , AMBIENTE            AS AMB                       "
				+ "  , FAMILIA_FLAVORS     AS FF                        "
				+ "  , TIPO_SERVICO        AS TS                        "
				+ "  , CONTRATO            AS CON                       "
				+ "WHERE CON.ID_CLIENTE         = ?                     "
				+ "  AND CON.ID_STATUS_CONTRATO = 1                     "
				+ "  AND REC.ID_CONTRATO        = CON.ID_CONTRATO       "				
				+ "  AND REC.ID_ADITIVADO       IS NOT NULL             "
				+ "  AND SR.ID_STATUS_RECURSO  = REC.ID_STATUS_RECURSO  "
				+ "  AND RB.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "				
				+ "  AND TD.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
				+ "  AND SO.ID_SO              = REC.ID_SO              "
				+ "  AND AMB.ID_AMBIENTE       = REC.ID_AMBIENTE        "
				+ "  AND FF.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
				+ "  AND TS.ID_TIPO_SERVICO    = REC.ID_TIPO_SERVICO    " ;
		

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, idContrato );

		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			ModelListaRecursoContratoAditivo listaRecursoContrato = new ModelListaRecursoContratoAditivo();
			listaRecursoContrato.setId_recurso         ( resutado.getLong  ("ID_RECURSO"         ) );  // 1
			listaRecursoContrato.setStatus_recurso     ( resutado.getString("STATUS_RECURSO"     ) );  // 2
			listaRecursoContrato.setRetencao_backup    ( resutado.getString("RETENCAO_BACKUP"    ) );  // 3
			listaRecursoContrato.setTipo_disco         ( resutado.getString("TIPO_DISCO"         ) );  // 4
			listaRecursoContrato.setSistema_operacional( resutado.getString("SISTEMA_OPERACIONAL") );  // 5
			listaRecursoContrato.setAmbiente           ( resutado.getString("AMBIENTE"           ) );  // 6
			listaRecursoContrato.setFamilia            ( resutado.getString("FAMILIA"            ) );  // 7
			listaRecursoContrato.setTipo_servico       ( resutado.getString("TIPO_SERVICO"       ) );  // 8
			listaRecursoContrato.setDt_cadastro        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") )); // 9
			listaRecursoContrato.setHostname           ( resutado.getString("HOSTNAME"           ) );  // 10
			listaRecursoContrato.setTamanho_disco      ( resutado.getString("TAMANHO_DISCO"      ) );  // 11
			listaRecursoContrato.setPrimary_ip         ( resutado.getString("PRIMARY_IP"         ) );  // 12
			listaRecursoContrato.setTag_vcenter        ( resutado.getString("TAG_VCENTER"        ) );  // 13
			listaRecursoContrato.setObs                ( resutado.getString("OBS"                ) );  // 14
			listaRecursoContrato.setId_contrato        ( resutado.getLong  ("ID_CONTRATO"        ) );  // 15
			listaRecursoContrato.setId_aditivado       ( resutado.getLong  ("ID_ADITIVADO"       ) );  // 15

//			System.out.println(listaRecursoContrato);
			listaRecursoContratos.add(listaRecursoContrato);
			
		}
		return listaRecursoContratos;
	}

}
