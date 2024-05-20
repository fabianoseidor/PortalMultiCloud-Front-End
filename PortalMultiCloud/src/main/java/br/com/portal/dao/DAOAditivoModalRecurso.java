package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelAitivoRecursoModal;
import br.com.portal.model.ModelHitoricoUpgrade;
import br.com.portal.model.ModelMoeda;
import br.com.portal.model.ModelMonitoramento;
import br.com.portal.model.ModelSite;
import br.com.portal.model.ModelSuporte;
import br.com.portal.model.ModelTempoLigado;
import br.com.portal.model.ModelTipoAditivo;
import br.com.portal.model.ModelVigenciaAditivo;

public class DAOAditivoModalRecurso {
	public static final int INSERT = 1;
	public static final int UPDATE = 2;	
	
	private Connection connection;
	
	public DAOAditivoModalRecurso() {
		connection = SingleConnectionBanco.getConnection();
	}

	
	public ModelAitivoRecursoModal gravarAditivoRecursoModal( ModelAitivoRecursoModal objeto ) {

		if( objeto.isNovo() ){
			String sql = null;
		    if( objeto.getId_status() == 4 ) {
			    sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   , "
			        + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
		            + "                        ID_HUB_SPOT      , ID_RASCUNHO   , MOTIVO_RASCUNHO                           )"
			        + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                       ";
		    }else {
			    sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO       , "
				        + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
			            + "                        ID_HUB_SPOT                                                                  )"
				        + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                           ";
		    }

			
			try {
				PreparedStatement prepareSql;
				prepareSql = connection.prepareStatement(sql);

				prepareSql.setLong     ( 1 , objeto.getId_status()                                  ); // ID_STATUS_ADITIVO
				prepareSql.setString   ( 2 , objeto.getValor_total()                                ); // VLR_TORAL_ADIT
				prepareSql.setString   ( 3 , objeto.getObservacao_aditivo()                         ); // OBSERVACAO
				prepareSql.setTimestamp( 4 , new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
				prepareSql.setLong     ( 5 , objeto.getId_contrato()                                ); // ID_CONTRATO
				prepareSql.setString   ( 6 , objeto.getValor_convertido()                           ); // VALOR_CONVERTIDO
				prepareSql.setString   ( 7 , objeto.getCusto_total()                                ); // CUSTO_TOTAL
				prepareSql.setString   ( 8 , objeto.getCotacao_moeda()                              ); // COTACAO_MOEDA
				prepareSql.setLong     ( 9 , objeto.getId_moeda()                                   ); // ID_MOEDA
				prepareSql.setString   ( 10, objeto.getLogin_cadastro()                             ); // LOGIN_CADASTRO
				prepareSql.setString   ( 11, objeto.getHubspot_aditivo()                            ); // ID_HUB_SPOT
				if( objeto.getId_status() == 4 ) {
				    prepareSql.setLong     ( 12, objeto.getId_rascunho()                                ); // ID_RASCUNHO
				    prepareSql.setString   ( 13, objeto.getMotivoRascunho()                             ); // MOTIVO_RASCUNHO
				}

				prepareSql.execute();
				connection.commit();
				
				objeto.setId_aditivado(this.getMaxIdAditivo( objeto ));
				
				ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
				vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()        );
				vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
				vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()           );
				vigenciaAditivo.setDt_final         ( objeto.getDt_final()            );
				vigenciaAditivo.setObservacao       ( objeto.getObservacao_aditivo()  );
				
				try {
					vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
				    objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
				    this.gravarAditivadoRecurso( objeto, INSERT );
				} catch (Exception e) {
					// e.printStackTrace();
					DAOError daoError = new DAOError();
					System.out.println( e.getMessage() );
					String errorContatenado = "ID Produto: " + objeto.getId_contrato() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
					daoError.insertError("DAOAditivoModalRecurso", "contrato.jsp", "Cadastro Vigencia Aditivo(Recurso)", "79", objeto.getLogin_cadastro(), errorContatenado );
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println( e.getMessage() );
				DAOError daoError = new DAOError();
				String errorContatenado = "ID Produto: " + objeto.getId_contrato() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
				daoError.insertError("DAOAditivoModalRecurso", "contrato.jsp", "Cadastro Aditivo(Recurso)", "64", objeto.getLogin_cadastro(), errorContatenado );
			}
		}else {
			String sql = null;
			if( objeto.getId_status() == 4 ) {
			    sql = "UPDATE ADITIVADO              " 
					+ "   SET ID_STATUS_ADITIVO = ?, "
					+ "       VLR_TOTAL_ADIT    = ?, "
					+ "       VALOR_CONVERTIDO  = ?, "
					+ "       CUSTO_TOTAL       = ?, "
					+ "       COTACAO_MOEDA     = ?, "
					+ "       ID_MOEDA          = ?, "
					+ "       ID_HUB_SPOT       = ?, "
					+ "       OBSERVACAO        = ?, "
					+ "       ID_RASCUNHO       = ?, "
					+ "       MOTIVO_RASCUNHO   = ?  "
					+ " WHERE ID_ADITIVADO      = ?  " ;
			}else {
			    sql = "UPDATE ADITIVADO              " 
					+ "   SET ID_STATUS_ADITIVO = ?, "
					+ "       VLR_TOTAL_ADIT    = ?, "
					+ "       VALOR_CONVERTIDO  = ?, "
					+ "       CUSTO_TOTAL       = ?, "
					+ "       COTACAO_MOEDA     = ?, "
					+ "       ID_MOEDA          = ?, "
					+ "       ID_HUB_SPOT       = ?, "
					+ "       OBSERVACAO        = ?  "
					+ " WHERE ID_ADITIVADO      = ?  " ;
			}

			try {
				PreparedStatement prepareSql;
				prepareSql = connection.prepareStatement(sql);

				prepareSql.setLong     ( 1 , objeto.getId_status()         ); // ID_STATUS_ADITIVO
				prepareSql.setString   ( 2 , objeto.getValor_total()       ); // VLR_TORAL_ADIT
				prepareSql.setString   ( 3 , objeto.getValor_convertido()  ); // VALOR_CONVERTIDO
				prepareSql.setString   ( 4 , objeto.getCusto_total()       ); // CUSTO_TOTAL
				prepareSql.setString   ( 5 , objeto.getCotacao_moeda()     ); // COTACAO_MOEDA
				prepareSql.setLong     ( 6 , objeto.getId_moeda()          ); // ID_MOEDA
				prepareSql.setString   ( 7 , objeto.getHubspot_aditivo()   ); // ID_HUB_SPOT
				prepareSql.setString   ( 8 , objeto.getObservacao_aditivo()); // OBSERVACAO
				
				if( objeto.getId_status() == 4 ) {
				    prepareSql.setLong     ( 9 , objeto.getId_rascunho()       ); // ID_RASCUNHO
				    prepareSql.setString   ( 10, objeto.getMotivoRascunho()    ); // MOTIVO_RASCUNHO
				    prepareSql.setLong     ( 11, objeto.getId_aditivado()      ); // ID_ADITIVADO
				}else {
					prepareSql.setLong     ( 9, objeto.getId_aditivado()      ); // ID_ADITIVADO
				}
	
				prepareSql.executeUpdate();
				connection.commit();
	
				ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
	
				vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()            );
				vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()       );
				vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()               );
				vigenciaAditivo.setDt_final         ( objeto.getDt_final()                );
				vigenciaAditivo.setObservacao       ( objeto.getObservacao_vigencia()     );
				vigenciaAditivo.setId_vigencia      ( this.getIdVigencia(vigenciaAditivo) );
				
				try {
					vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
					objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
					
					if(objeto.getId_recurso() == null)
					   this.gravarAditivadoRecurso( objeto, INSERT );
					else
					  this.gravarAditivadoRecurso( objeto, UPDATE );
		//			this.atualiaVlrTotalRecursoAditivo( objeto.getId_aditivado() );
				
				} catch (Exception e) {
					e.printStackTrace();
					DAOError daoError = new DAOError();
					String errorContatenado = "ID Produto: " + objeto.getId_contrato() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
					daoError.insertError("DAOAditivoModalRecurso", "contrato.jsp", "Update Vigencia Aditivo(Recurso)", "137", objeto.getLogin_cadastro(), errorContatenado );
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				DAOError daoError = new DAOError();
				String errorContatenado = "ID Produto: " + objeto.getId_contrato() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
				daoError.insertError("DAOAditivoModalRecurso", "contrato.jsp", "Update Aditivo(Recurso)", "123", objeto.getLogin_cadastro(), errorContatenado );
			}
		}

	    return this.getAditivoRecursoID( objeto );

	}
	
	public void gravarAditivadoRecurso( ModelAitivoRecursoModal objeto, int transacao ) throws Exception {
		DAOUtil daoUtil = new DAOUtil();
		if( transacao == INSERT) {
	 		String sql = "INSERT INTO RECURSO                                                                                                                                                                                                         "
					+ "           ( ID_STATUS_RECURSO, ID_BACKUP, ID_RETENCAO_BACKUP, ID_TIPO_DISCO, ID_SO      , ID_AMBIENTE     , ID_FAMILIA_FLAVORS, ID_TIPO_SERVICO, DT_CADASTRO       , HOSTNAME        , TAMANHO_DISCO             , PRIMARY_IP "
					+ "           , TAG_VCENTER      , OBS      , ID_CONTRATO       , ID_ADITIVADO , ID_SUPORTE , ID_MONITORAMENTO, ID_TEMPO_LIGADO   , ID_TIPO_ADITIVO, RECURSO_TEMPORARIO, ADTI_SEM_RECEITA, APROVADOR_ADIT_SEM_RECEITA, ID_NUVEM   "
					+ "           , SITE             , PERIODO_UTILIZACAO_BOLHA     , VALOR_RECURSO, EIP_VCENTER, HOST_VCENTER )                                                                                                                      "
					+ "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                                                                           ";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			Timestamp dtCad = new java.sql.Timestamp(new java.util.Date().getTime());
			String dataCadRecurso =  dtCad.toString().substring(0, 19);
			
			prepareSql.setLong     ( 1 , objeto.getId_status_recurso()                          );          // ID_STATUS_RECURSO
			prepareSql.setInt      ( 2 , objeto.getId_backup()                                  );          // BACKUP
			prepareSql.setLong     ( 3 , objeto.getId_retencao_backup()                         );          // ID_RETENCAO_BACKUP
			prepareSql.setLong     ( 4 , objeto.getId_tipo_disco()                              );          // ID_TIPO_DISCO
			prepareSql.setLong     ( 5 , objeto.getId_so()                                      );          // ID_SO
			prepareSql.setLong     ( 6 , objeto.getId_ambiente()                                );          // ID_AMBIENTE
			prepareSql.setLong     ( 7 , objeto.getId_familia_flavors()                         );          // ID_FAMILIA_FLAVORS
			prepareSql.setLong     ( 8 , objeto.getId_tipo_servico()                            );          // ID_TIPO_SERVICO
//			prepareSql.setTimestamp( 9 , new java.sql.Timestamp(new java.util.Date().getTime()) );          // DT_CADASTRO
			prepareSql.setString   ( 9 , dataCadRecurso                                         );          // DT_CADASTRO
			prepareSql.setString   ( 10, objeto.getHost_name_modal_recurso()                    );          // HOSTNAME
			prepareSql.setString   ( 11, objeto.getTamanho_disco_modal_recurso()                );          // TAMANHO_DISCO
			prepareSql.setString   ( 12, objeto.getPrimary_ip_modalrecurso()                    );          // PRIMARY_IP
			prepareSql.setString   ( 13, objeto.getTag_vcenter()                                );          // TAG_VCENTER
			prepareSql.setString   ( 14, objeto.getObservacoes_recurso()                        );          // OBS
			prepareSql.setLong     ( 15, objeto.getId_contrato()                                );          // ID_CONTRATO
			prepareSql.setLong     ( 16, objeto.getId_aditivado()                               );          // ID_ADITIVADO
			prepareSql.setLong     ( 17, objeto.getId_suporte()                                 );          // ID_SUPORTE
			prepareSql.setLong     ( 18, objeto.getId_monitoramento()                           );          // ID_MONITORAMENTO
			prepareSql.setLong     ( 19, objeto.getId_tipo_contratacao()                        );          // ID_TEMPO_LIGADO 
			prepareSql.setLong     ( 20, objeto.getId_tipo_aditivo()                            );          // ID_TIPO_ADITIVO
			prepareSql.setInt      ( 21, objeto.getRecurso_temporario()                         );          // RECURSO_TEMPORARIO
			prepareSql.setInt      ( 22, objeto.getAdti_sem_receita()                           );          // ADTI_SEM_RECEITA
			prepareSql.setString   ( 23, objeto.getAprovador_adit_sem_receita()                 );          // APROVADOR_ADIT_SEM_RECEITA
			prepareSql.setLong     ( 24, objeto.getId_nuvem()                                   );          // ID_NUVEM
			prepareSql.setLong     ( 25, objeto.getId_site()                                    );          // SITE
			prepareSql.setDate     ( 26, daoUtil.FormtGravaDataBD(objeto.getPeriodo_utilizacao_bolha() ) ); // PERIODO_UTILIZACAO_BOLHA
			prepareSql.setString   ( 27, objeto.getValor_total()                                );          // VALOR_RECURSO
			prepareSql.setString   ( 28, objeto.getEip_Vcenter()                                );          // EIP_VCENTER
			prepareSql.setString   ( 29, objeto.getHost_Vcenter()                               );          // HOST_VCENTER

					
			prepareSql.execute();
			connection.commit();

			objeto.setId_recurso( getIdRecuso( objeto, dataCadRecurso ) );
			
		}else if( transacao == UPDATE ) {
			String sql = "UPDATE RECURSO                                                                                                                                                 "
					+ "      SET  ID_STATUS_RECURSO  = ?, ID_BACKUP          = ?, ID_RETENCAO_BACKUP         = ?, ID_TIPO_DISCO            = ?, ID_SO           = ?, ID_AMBIENTE     = ? "
					+ "         , ID_FAMILIA_FLAVORS = ?, ID_TIPO_SERVICO    = ?, HOSTNAME                   = ?, TAMANHO_DISCO            = ?, PRIMARY_IP      = ?, TAG_VCENTER     = ? "
					+ "         , EIP_VCENTER        = ?, HOST_VCENTER       = ?, ID_SUPORTE                 = ?, ID_MONITORAMENTO         = ?, ID_TEMPO_LIGADO = ?, ID_TIPO_ADITIVO = ? "
					+ "         , RECURSO_TEMPORARIO = ?, ADTI_SEM_RECEITA   = ?, APROVADOR_ADIT_SEM_RECEITA = ?, PERIODO_UTILIZACAO_BOLHA = ?, ID_NUVEM        = ?, SITE            = ? "
					+ "         , VALOR_RECURSO      = ?, OBS                = ?                                                                                                         "
					+ "    WHERE ID_RECURSO = ?                                                                                                                                          " ;

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     (  1, objeto.getId_status_recurso()                                   ); //  ID_STATUS_RECURSO 
			prepareSql.setLong     (  2, objeto.getId_backup()                                           ); //  ID_BACKUP         
			prepareSql.setLong     (  3, objeto.getId_retencao_backup()                                  ); //  ID_RETENCAO_BACKUP
			prepareSql.setLong     (  4, objeto.getId_tipo_disco()                                       ); //  ID_TIPO_DISCO     
			prepareSql.setLong     (  5, objeto.getId_so()                                               ); //  ID_SO             
			prepareSql.setLong     (  6, objeto.getId_ambiente()                                         ); //  ID_AMBIENTE       
			prepareSql.setLong     (  7, objeto.getId_familia_flavors()                                  ); //  ID_FAMILIA_FLAVORS
			prepareSql.setLong     (  8, objeto.getId_tipo_servico()                                     ); //  ID_TIPO_SERVICO   
			prepareSql.setString   (  9, objeto.getHost_name_modal_recurso()                             ); //  HOSTNAME          
			prepareSql.setString   ( 10, objeto.getTamanho_disco_modal_recurso()                         ); //  TAMANHO_DISCO     
			prepareSql.setString   ( 11, objeto.getPrimary_ip_modalrecurso()                             ); //  PRIMARY_IP        
			prepareSql.setString   ( 12, objeto.getTag_vcenter()                                         ); //  TAG_VCENTER       
			prepareSql.setString   ( 13, objeto.getEip_Vcenter()                                         ); //  EIP_VCENTER       
			prepareSql.setString   ( 14, objeto.getHost_Vcenter()                                        ); //  HOST_VCENTER      
			prepareSql.setLong     ( 15, objeto.getId_suporte()                                          ); //  ID_SUPORTE                
			prepareSql.setLong     ( 16, objeto.getId_monitoramento()                                    ); //  ID_MONITORAMENTO          
			prepareSql.setLong     ( 17, objeto.getId_tipo_contratacao()                                 ); //  ID_TEMPO_LIGADO           
			prepareSql.setLong     ( 18, objeto.getId_tipo_aditivo()                                     ); //  ID_TIPO_ADITIVO           
			prepareSql.setLong     ( 19, objeto.getRecurso_temporario()                                  ); //  RECURSO_TEMPORARIO        
			prepareSql.setLong     ( 20, objeto.getAdti_sem_receita()                                    ); //  ADTI_SEM_RECEITA          
			prepareSql.setString   ( 21, objeto.getAprovador_adit_sem_receita()                          ); //  APROVADOR_ADIT_SEM_RECEITA
			prepareSql.setDate     ( 22, daoUtil.FormtGravaDataBD(objeto.getPeriodo_utilizacao_bolha() ) ); //  PERIODO_UTILIZACAO_BOLHA  
			prepareSql.setLong     ( 23, objeto.getId_nuvem()                                            ); //  ID_NUVEM                  
			prepareSql.setLong     ( 24, objeto.getId_site()                                             ); //  SITE                      
			prepareSql.setString   ( 25, objeto.getValor_total()                                         ); //  VALOR_RECURSO             
			prepareSql.setString   ( 26, objeto.getObservacoes_recurso()                                 ); //  OBS                       
			prepareSql.setLong     ( 27, objeto.getId_recurso()                                          ); //  ID_RECURSO

			prepareSql.executeUpdate();
			connection.commit();
		}
	}

	public Long getIdRecuso( ModelAitivoRecursoModal objeto, String dtCadastro ) throws SQLException {

		String sql = "SELECT MAX(ID_RECURSO) AS MAX_ID_RECURSO "
				+ "  FROM RECURSO                              "
				+ "  WHERE ID_CONTRATO        = ?              "
				+ "    AND ID_STATUS_RECURSO  = ?              "
				+ "    AND ID_SO              = ?              "
				+ "    AND ID_AMBIENTE        = ?              "
				+ "    AND ID_FAMILIA_FLAVORS = ?              "
				+ "    AND DT_CADASTRO        = ?              " ;
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong  ( 1, objeto.getId_contrato()        );
		prepareSql.setLong  ( 2, objeto.getId_status_recurso()  );
		prepareSql.setLong  ( 3, objeto.getId_so()              );
		prepareSql.setLong  ( 4, objeto.getId_ambiente()        );
		prepareSql.setLong  ( 5, objeto.getId_familia_flavors() );
		prepareSql.setString( 6, dtCadastro                     );		
		
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next())  return  resutado.getLong("MAX_ID_RECURSO");

		return null;		
	}
	
	
	
	
	
	public ModelVigenciaAditivo gravarVigenciaAditivo( ModelVigenciaAditivo objeto ) throws Exception {
		DAOUtil daoUtil = new DAOUtil();
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO VIGENCIA_ADITIVO ( ID_ADITIVADO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, OBSERVACAO)"
			 		  + " VALUES ( ?, ?, ?, ?, ?, ? ) ";
			
	
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong     ( 1, objeto.getId_aditivado()                                );
			prepareSql.setLong     ( 2, objeto.getId_tempo_contrato()                          );
			prepareSql.setDate     ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio())        );
			prepareSql.setDate     ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())         );
			prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 6, objeto.getObservacao()                                 );
			
			prepareSql.execute();
			connection.commit();
			
			objeto.setId_vigencia( this.getIdVigencia(objeto) );
		}else {
			String sql = "UPDATE VIGENCIA_ADITIVO                                                    "
					   + "   SET  ID_TEMPO_CONTRATO = ?, DT_INICIO = ?, DT_FINAL = ?, OBSERVACAO = ? "
					   + " WHERE ID_VIGENCIA = ?                                                     ";

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong  ( 1, objeto.getId_tempo_contrato()                   );
			prepareSql.setDate  ( 2, daoUtil.FormtGravaDataBD(objeto.getDt_inicio()) );
			prepareSql.setDate  ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_final())  );
			prepareSql.setString( 4, objeto.getObservacao()                          );
			prepareSql.setLong  ( 5, objeto.getId_vigencia()                         );

			prepareSql.executeUpdate();
			connection.commit();
		}
		
		return objeto;
	}
	
	public Long getIdVigencia( ModelVigenciaAditivo objeto ) throws SQLException {
		DAOUtil daoUtil = new DAOUtil();

		String sql = "SELECT MAX(ID_VIGENCIA) AS MAX_ID_VIGENCIA "
				   + "  FROM VIGENCIA_ADITIVO                    "
				   + "  WHERE ID_ADITIVADO      = ?              "
			  	   + "    AND ID_TEMPO_CONTRATO = ?              "
				   + "    AND DT_INICIO         = ?              "
				   + "    AND DT_FINAL          = ?              ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, objeto.getId_aditivado()                          );
		prepareSql.setLong ( 2, objeto.getId_tempo_contrato()                   );
		prepareSql.setDate ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio()) );
		prepareSql.setDate ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())  );
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next())  return  resutado.getLong("MAX_ID_VIGENCIA");

		return null;		
	}
	
	
	/*
	 * 
	 * Busca o ID Aditivo apos cadastro do Aditivo
	 * 
	 * */	
	public Long getMaxIdAditivo( ModelAitivoRecursoModal objeto ) throws SQLException {
		String sql = "SELECT MAX( ID_ADITIVADO ) MAX_ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ? AND ID_STATUS_ADITIVO = ? ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1, objeto.getId_contrato() );
		statemet.setLong ( 2, objeto.getId_status()   );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_ADITIVADO");

		return null;
	}

	public List<ModelTipoAditivo> listaTipoAditivoSelect(){
		
//		String sql = "SELECT ID_TIPO_ADITIVO, DESC_TIPO_DITIVO FROM TIPO_ADITIVO ORDER BY ID_TIPO_ADITIVO";
		String sql = "SELECT ID_TIPO_ADITIVO, DESC_TIPO_DITIVO FROM TIPO_ADITIVO WHERE ID_TIPO_ADITIVO IN( 1, 2, 5, 7, 9  ) ORDER BY ID_TIPO_ADITIVO";

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

	/*
	 * 
	 * 
	 * 
	 * */	
	public List<ModelTempoLigado> listaTempoLigado( ){
		
		String sql = "SELECT * FROM TEMPO_LIGADO ";
		List<ModelTempoLigado> modelTempoLigados = new ArrayList<ModelTempoLigado>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTempoLigado modelTempoLigado = new ModelTempoLigado();
				
				modelTempoLigado.setId_tempo_ligado  ( set.getLong  ("ID_TEMPO_LIGADO"  ) );
				modelTempoLigado.setDesc_tempo_ligado( set.getString("DESC_TEMPO_LIGADO") );
				modelTempoLigado.setObs              ( set.getString("OBS"              ) );
				modelTempoLigado.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );

				 modelTempoLigados.add(modelTempoLigado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTempoLigados;
	}

	/*
	 * 
	 * 
	 * 
	 * */	
	public List<ModelSuporte> listaSuporte( ){
		
		String sql = "SELECT * FROM SUPORTE ";
		List<ModelSuporte> modelSuportes = new ArrayList<ModelSuporte>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSuporte modelSuporte = new ModelSuporte();
				
				modelSuporte.setId_suporte  ( set.getLong  ("ID_SUPORTE"  ) );
				modelSuporte.setDesc_suporte( set.getString("DESC_SUPORTE") );
				modelSuporte.setObs         ( set.getString("OBS"         ) );
				modelSuporte.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );

				modelSuportes.add(modelSuporte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelSuportes;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	
	public List<ModelMonitoramento> listaMonitoramento( ){
		
		String sql = "SELECT * FROM MONITORAMENTO ";
		List<ModelMonitoramento> modelMonitoramentos = new ArrayList<ModelMonitoramento>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelMonitoramento modelMonitoramento = new ModelMonitoramento();
				
				modelMonitoramento.setId_monitoramento  ( set.getLong  ("ID_MONITORAMENTO"   ) );
				modelMonitoramento.setDesc_monitoramento( set.getString("DESC_MONITORAMENTO" ) );
				modelMonitoramento.setObs               ( set.getString("OBS"                ) );
				modelMonitoramento.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );

				modelMonitoramentos.add(modelMonitoramento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelMonitoramentos;
	}
	
	public boolean isTagVcenter( String TagVcenter ){
		
		String sql = "SELECT COUNT(1) AS EXIST_TAG FROM RECURSO WHERE TAG_VCENTER = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString   ( 1, TagVcenter ); // TAG_VCENTER
			ResultSet set = ps.executeQuery();
			while (set.next()) return set.getBoolean("EXIST_TAG");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ModelSite> listaSiteSelectRecurso( Long idNuvem ) throws SQLException{
		
		String sql = "SELECT * FROM SITE WHERE ID_NUVEM = " + idNuvem + " ORDER BY ID_SITE";
		List<ModelSite> modelSites = new ArrayList<ModelSite>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSite modelSite = new ModelSite();
				modelSite.setId_site(set.getLong("ID_SITE"));
				modelSite.setId_nuvem(set.getLong("ID_NUVEM"));
				modelSite.setNome(set.getString("NOME"));
					
				modelSites.add(modelSite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelSites;
	}

	public ModelAitivoRecursoModal getAditivoRecursoID( ModelAitivoRecursoModal obj ) {
		
		String sql = "SELECT                                                      "
			       + "     ADI.ID_ADITIVADO                                       "
			       + "   , ADI.ID_CONTRATO                                        "
			       + "   , ADI.ID_STATUS_ADITIVO                                  "
			       + "   , SCO.STATUS_CONTRATO                                    "
			       + "   , ADI.DT_CRIACAO                                         "
			       + "   , ADI.VLR_TOTAL_ADIT                                     "
			       + "   , ADI.OBSERVACAO                                         "
			       + "   , ADI.LOGIN_CADASTRO                                     "
			       + "   , ADI.ID_HUB_SPOT                                        "
			       			       
			       + "   , ADI.ID_MOEDA                                           "
			       + "   , ADI.VALOR_CONVERTIDO                                   "
			       + "   , ADI.CUSTO_TOTAL                                        "
			       + "   , ADI.COTACAO_MOEDA                                      "
			       + "   , ADI.ID_RASCUNHO                                        "
			       + "   , ADI.MOTIVO_RASCUNHO                                    "

			       + "   , REC.ID_RECURSO                                         "
			       + "   , REC.ID_STATUS_RECURSO                                  "
			       + "   , SREC.STATUS_RECURSO                                    "
			       + "   , REC.ID_BACKUP                                          "
			       + "   , REC.ID_RETENCAO_BACKUP                                 "
			       + "   , RBA.RETENCAO_BACKUP                                    "
			       + "   , REC.ID_TIPO_DISCO                                      "
			       + "   , TDI.TIPO_DISCO                                         "
			       + "   , REC.ID_SO                                              "
			       + "   , SOP.SISTEMA_OPERACIONAL                                "
			       + "   , REC.ID_AMBIENTE                                        "
			       + "   , AMB.AMBIENTE                                           "
			       + "   , REC.ID_FAMILIA_FLAVORS                                 "
			       + "   , FFL.FAMILIA                                            "
			       + "   , REC.ID_TIPO_SERVICO                                    "
			       + "   , TSE.TIPO_SERVICO                                       "
			       + "   , REC.DT_CADASTRO AS DT_CADASTRO_RECURSO                 "
			       + "   , REC.HOSTNAME                                           "
			       + "   , REC.TAMANHO_DISCO                                      "
			       + "   , REC.PRIMARY_IP                                         "
			       + "   , REC.TAG_VCENTER                                        "
			       + "   , REC.EIP_VCENTER                                        "
			       + "   , REC.HOST_VCENTER                                       "
			       + "   , REC.OBS                                                "
			       + "   , REC.VALOR_RECURSO                                      "
			       + "   , REC.ID_SUPORTE                                         "
			       + "   , SUP.DESC_SUPORTE                                       "
			       + "   , REC.ID_MONITORAMENTO                                   "
			       + "   , MON.DESC_MONITORAMENTO                                 "
			       + "   , REC.ID_TEMPO_LIGADO                                    "
			       + "   , TLI.DESC_TEMPO_LIGADO                                  "
			       + "   , REC.ID_TIPO_ADITIVO                                    "
			       + "   , TAD.DESC_TIPO_DITIVO                                   "
			       + "   , REC.RECURSO_TEMPORARIO                                 "
			       + "   , REC.ADTI_SEM_RECEITA                                   "
			       + "   , REC.APROVADOR_ADIT_SEM_RECEITA                         "
			       + "   , REC.PERIODO_UTILIZACAO_BOLHA                           "
			       + "   , REC.ID_NUVEM                                           "
			       + "   , REC.SITE                                               "
			       + "   , VAD.ID_VIGENCIA                                        "
			       + "   , VAD.ID_TEMPO_CONTRATO                                  "
			       + "   , VAD.DT_INICIO                                          "
			       + "   , VAD.DT_FINAL                                           "
			       + "   , VAD.DT_CRIACAO AS DT_CRIACAO_VIGENCIA                  "
			       + "   , VAD.DT_DESATIVACAO                                     "
			       + "   , VAD.OBSERVACAO AS OBS_VIGENCIA                         "
			       + "  FROM ADITIVADO                     AS ADI                 "
			       + "  LEFT JOIN RECURSO                  AS REC                 "
			       + "         ON REC.ID_CONTRATO        = ADI.ID_CONTRATO        "
			       + "  LEFT JOIN VIGENCIA_ADITIVO         AS VAD                 "
			       + "         ON VAD.ID_ADITIVADO       = ADI.ID_ADITIVADO       "
			       + "  LEFT JOIN STATUS_CONTRATO           AS SCO                "
			       + "         ON SCO.ID_STATUS_CONTRATO = ADI.ID_STATUS_ADITIVO  "
			       + "  LEFT JOIN STATUS_RECURSO            AS SREC               "
			       + "         ON SREC.ID_STATUS_RECURSO = REC.ID_STATUS_RECURSO  "
			       + "  LEFT JOIN RETENCAO_BACKUP           AS RBA                "
			       + "         ON RBA.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "
			       + "  LEFT JOIN TIPO_DISCO                AS TDI                "
			       + "         ON TDI.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
			       + "  LEFT JOIN SISTEMA_OPERACIONAL       AS SOP                "
			       + "         ON SOP.ID_SO              = REC.ID_SO              "
			       + "  LEFT JOIN AMBIENTE                  AS AMB                "
			       + "         ON AMB.ID_AMBIENTE        = REC.ID_AMBIENTE        "
			       + "  LEFT JOIN FAMILIA_FLAVORS           AS FFL                "
			       + "         ON FFL.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
			       + "  LEFT JOIN TIPO_SERVICO              AS TSE                "
			       + "         ON TSE.ID_TIPO_SERVICO = REC.ID_TIPO_SERVICO       "
			       + "  LEFT JOIN SUPORTE                   AS SUP                "
			       + "         ON SUP.ID_SUPORTE         = REC.ID_SUPORTE         "
			       + "  LEFT JOIN MONITORAMENTO             AS MON                "
			       + "         ON MON.ID_MONITORAMENTO   = REC.ID_MONITORAMENTO   "
			       + "  LEFT JOIN TEMPO_LIGADO              AS TLI                "
			       + "         ON TLI.ID_TEMPO_LIGADO    =  REC.ID_TEMPO_LIGADO   "
			       + "  LEFT JOIN TIPO_ADITIVO              AS TAD                "
			       + "         ON TAD.ID_TIPO_ADITIVO    = REC.ID_TIPO_ADITIVO    "
			       + "   WHERE REC.ID_RECURSO = ?                                 "
			       + "     AND REC.ID_ADITIVADO = ADI.ID_ADITIVADO                ";

		ModelAitivoRecursoModal modelAitivoRecurso = new ModelAitivoRecursoModal();
		DAOUtil daoUtil = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, obj.getId_recurso() );
			
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				// Info tabela Aditivo
				modelAitivoRecurso.setId_aditivado               ( set.getLong  ("ID_ADITIVADO"              )                                  );
				modelAitivoRecurso.setId_contrato                ( set.getLong  ("ID_CONTRATO"               )                                  );
				modelAitivoRecurso.setId_status                  ( set.getLong  ("ID_STATUS_ADITIVO"         )                                  );
				modelAitivoRecurso.setDesc_status                ( set.getString("STATUS_CONTRATO"           )                                  );
				modelAitivoRecurso.setDt_criacao                 ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO"))          );
				valorReal      = Double.valueOf                  ( set.getString("VLR_TOTAL_ADIT"            )                                  );
				modelAitivoRecurso.setValor_total                ( dinheiro.format(valorReal)                                                   );
				modelAitivoRecurso.setObservacao_aditivo         ( set.getString("OBSERVACAO"                )                                  );
				modelAitivoRecurso.setLogin_cadastro             ( set.getString("LOGIN_CADASTRO"            )                                  );
				modelAitivoRecurso.setHubspot_aditivo            ( set.getString("ID_HUB_SPOT"               )                                  );
				modelAitivoRecurso.setId_rascunho                ( set.getLong  ("ID_RASCUNHO"               )                                  );
				modelAitivoRecurso.setMotivoRascunho             ( set.getString("MOTIVO_RASCUNHO"           )                                  );				
				///////////////////////////
				modelAitivoRecurso.setId_recurso                 ( set.getLong  ("ID_RECURSO"                )                                  );
				modelAitivoRecurso.setId_status_recurso          ( set.getLong  ("ID_STATUS_RECURSO"         )                                  );
				modelAitivoRecurso.setDesc_status_recurso        ( set.getString("STATUS_RECURSO"            )                                  );
				modelAitivoRecurso.setId_backup                  ( set.getInt   ("ID_BACKUP"                 )                                  );
				modelAitivoRecurso.setId_retencao_backup         ( set.getLong  ("ID_RETENCAO_BACKUP"        )                                  );
				modelAitivoRecurso.setDesc_status_recurso        ( set.getString("STATUS_RECURSO"            )                                  );
				modelAitivoRecurso.setDesc_retencao_backup       ( set.getString("RETENCAO_BACKUP"           )                                  );
				modelAitivoRecurso.setId_tipo_disco              ( set.getLong  ("ID_TIPO_DISCO"             )                                  );
				modelAitivoRecurso.setDesc_tipo_disco            ( set.getString("TIPO_DISCO"                )                                  );
				modelAitivoRecurso.setId_so                      ( set.getLong  ("ID_SO"                     )                                  );
				modelAitivoRecurso.setDesc_sistema_operacional   ( set.getString("SISTEMA_OPERACIONAL"       )                                  );
				modelAitivoRecurso.setId_ambiente                ( set.getLong  ("ID_AMBIENTE"               )                                  );
				modelAitivoRecurso.setDesc_ambiente              ( set.getString("AMBIENTE"                  )                                  );
				modelAitivoRecurso.setId_familia_flavors         ( set.getLong  ("ID_FAMILIA_FLAVORS"        )                                  );
				modelAitivoRecurso.setDesc_familia               ( set.getString("FAMILIA"                   )                                  );
				modelAitivoRecurso.setId_tipo_servico            ( set.getLong  ("ID_TIPO_SERVICO"           )                                  );
				modelAitivoRecurso.setDesc_tipo_servico          ( set.getString("TIPO_SERVICO"              )                                  );
				modelAitivoRecurso.setDt_criacao_recurso         ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CADASTRO_RECURSO")) );
				modelAitivoRecurso.setHost_name_modal_recurso    ( set.getString("HOSTNAME"                  )                                  );
				modelAitivoRecurso.setTamanho_disco_modal_recurso( set.getString("TAMANHO_DISCO"             )                                  );
				modelAitivoRecurso.setPrimary_ip_modalrecurso    ( set.getString("PRIMARY_IP"                )                                  );
				modelAitivoRecurso.setEip_Vcenter                ( set.getString("EIP_VCENTER"               )                                  );
				modelAitivoRecurso.setHost_Vcenter               ( set.getString("HOST_VCENTER"              )                                  );
				modelAitivoRecurso.setTag_vcenter                ( set.getString("TAG_VCENTER"               )                                  );
				modelAitivoRecurso.setObservacoes_recurso        ( set.getString("OBS"                       )                                  );
				modelAitivoRecurso.setId_suporte                 ( set.getLong  ("ID_SUPORTE"                )                                  );
				modelAitivoRecurso.setDesc_suporte               ( set.getString("DESC_SUPORTE"              )                                  );				
				modelAitivoRecurso.setId_monitoramento           ( set.getLong  ("ID_MONITORAMENTO"          )                                  );
				modelAitivoRecurso.setDesc_monitoramento         ( set.getString("DESC_MONITORAMENTO"        )                                  );
				modelAitivoRecurso.setId_tipo_contratacao        ( set.getLong  ("ID_TEMPO_LIGADO"           )                                  );
				modelAitivoRecurso.setDesc_tempo_ligado          ( set.getString("DESC_TEMPO_LIGADO"         )                                  );
				modelAitivoRecurso.setId_tipo_aditivo            ( set.getLong  ("ID_TIPO_ADITIVO"           )                                  );
				modelAitivoRecurso.setDesc_tipo_ditivo           ( set.getString("DESC_TIPO_DITIVO"          )                                  );
				modelAitivoRecurso.setRecurso_temporario         ( set.getInt   ("RECURSO_TEMPORARIO"        )                                  );
				modelAitivoRecurso.setAdti_sem_receita           ( set.getInt   ("ADTI_SEM_RECEITA"          )                                  );
				modelAitivoRecurso.setAprovador_adit_sem_receita ( set.getString("APROVADOR_ADIT_SEM_RECEITA")                                  );
				modelAitivoRecurso.setPeriodo_utilizacao_bolha   ( daoUtil.FormataDataStringTelaData( set.getString("PERIODO_UTILIZACAO_BOLHA")));
				modelAitivoRecurso.setId_nuvem                   ( set.getLong  ("ID_NUVEM"                  )                                  );
				modelAitivoRecurso.setId_site                    ( set.getLong  ("SITE"                      )                                  );
				modelAitivoRecurso.setId_vigencia                ( set.getLong  ("ID_VIGENCIA"               )                                  );
				modelAitivoRecurso.setId_tempo_contrato          ( set.getLong  ("ID_TEMPO_CONTRATO"         )                                  );
				modelAitivoRecurso.setDt_inicio                  ( daoUtil.FormataDataStringTelaData( set.getString("DT_INICIO"))               );
				modelAitivoRecurso.setDt_final                   ( daoUtil.FormataDataStringTelaData( set.getString("DT_FINAL"))                );
				modelAitivoRecurso.setDt_criacao_vigencia        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO_VIGENCIA")) );
				modelAitivoRecurso.setObservacao_vigencia        ( set.getString("OBS_VIGENCIA"              )                                  );
				valorReal      = Double.valueOf                  ( set.getString("VALOR_RECURSO"             )                                  );
				modelAitivoRecurso.setValor_recurso              ( dinheiro.format(valorReal)                                                   );
				
				modelAitivoRecurso.setId_moeda                   ( set.getLong  ("ID_MOEDA")                                                    );

				vlrRecuperado = set.getString("VALOR_CONVERTIDO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setValor_convertido            ( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("CUSTO_TOTAL");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setCusto_total                 ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("COTACAO_MOEDA");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setCotacao_moeda               ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
												
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelAitivoRecurso;
	}

	public List<ModelAitivoRecursoModal>  getListaAditivoRecursoID( Long idContrato ) throws SQLException {
		
		String sql = "SELECT                                                      "
			       + "     ADI.ID_ADITIVADO                                       "
			       + "   , ADI.ID_CONTRATO                                        "
			       + "   , ADI.ID_STATUS_ADITIVO                                  "
			       + "   , SCO.STATUS_CONTRATO                                    "
			       + "   , ADI.DT_CRIACAO                                         "
			       + "   , ADI.VLR_TOTAL_ADIT                                     "
			       + "   , ADI.OBSERVACAO                                         "
			       + "   , ADI.LOGIN_CADASTRO                                     "
			       + "   , ADI.ID_HUB_SPOT                                        "
			       
			       + "   , ADI.ID_MOEDA                                           "
			       + "   , ADI.VALOR_CONVERTIDO                                   "
			       + "   , ADI.CUSTO_TOTAL                                        "
			       + "   , ADI.COTACAO_MOEDA                                      "			       
			       
			       + "   , REC.ID_RECURSO                                         "
			       + "   , REC.ID_STATUS_RECURSO                                  "
			       + "   , SREC.STATUS_RECURSO                                    "
			       + "   , REC.ID_BACKUP                                          "
			       + "   , REC.ID_RETENCAO_BACKUP                                 "
			       + "   , RBA.RETENCAO_BACKUP                                    "
			       + "   , REC.ID_TIPO_DISCO                                      "
			       + "   , TDI.TIPO_DISCO                                         "
			       + "   , REC.ID_SO                                              "
			       + "   , SOP.SISTEMA_OPERACIONAL                                "
			       + "   , REC.ID_AMBIENTE                                        "
			       + "   , AMB.AMBIENTE                                           "
			       + "   , REC.ID_FAMILIA_FLAVORS                                 "
			       + "   , FFL.FAMILIA                                            "
			       + "   , REC.ID_TIPO_SERVICO                                    "
			       + "   , TSE.TIPO_SERVICO                                       "
			       + "   , REC.DT_CADASTRO AS DT_CADASTRO_RECURSO                 "
			       + "   , REC.HOSTNAME                                           "
			       + "   , REC.TAMANHO_DISCO                                      "
			       + "   , REC.PRIMARY_IP                                         "
			       + "   , REC.TAG_VCENTER                                        "
			       + "   , REC.OBS                                                "
			       + "   , REC.VALOR_RECURSO                                      "
			       + "   , REC.ID_SUPORTE                                         "
			       + "   , SUP.DESC_SUPORTE                                       "
			       + "   , REC.ID_MONITORAMENTO                                   "
			       + "   , MON.DESC_MONITORAMENTO                                 "
			       + "   , REC.ID_TEMPO_LIGADO                                    "
			       + "   , TLI.DESC_TEMPO_LIGADO                                  "
			       + "   , REC.ID_TIPO_ADITIVO                                    "
			       + "   , TAD.DESC_TIPO_DITIVO                                   "
			       + "   , REC.RECURSO_TEMPORARIO                                 "
			       + "   , REC.ADTI_SEM_RECEITA                                   "
			       + "   , REC.APROVADOR_ADIT_SEM_RECEITA                         "
			       + "   , REC.PERIODO_UTILIZACAO_BOLHA                           "
			       + "   , REC.ID_NUVEM                                           "
			       + "   , REC.SITE                                               "
			       + "   , VAD.ID_VIGENCIA                                        "
			       + "   , VAD.ID_TEMPO_CONTRATO                                  "
			       + "   , VAD.DT_INICIO                                          "
			       + "   , VAD.DT_FINAL                                           "
			       + "   , VAD.DT_CRIACAO AS DT_CRIACAO_VIGENCIA                  "
			       + "   , VAD.DT_DESATIVACAO                                     "
			       + "   , VAD.OBSERVACAO AS OBS_VIGENCIA                         "
			       + "  FROM ADITIVADO                     AS ADI                 "
			       + "  LEFT JOIN RECURSO                  AS REC                 "
			       + "         ON REC.ID_CONTRATO        = ADI.ID_CONTRATO        "
			       + "  LEFT JOIN VIGENCIA_ADITIVO         AS VAD                 "
			       + "         ON VAD.ID_ADITIVADO       = ADI.ID_ADITIVADO       "
			       + " LEFT JOIN STATUS_CONTRATO           AS SCO                 "
			       + "         ON SCO.ID_STATUS_CONTRATO = ADI.ID_STATUS_ADITIVO  "
			       + " LEFT JOIN STATUS_RECURSO            AS SREC                "
			       + "         ON SREC.ID_STATUS_RECURSO = REC.ID_STATUS_RECURSO  "
			       + " LEFT JOIN RETENCAO_BACKUP           AS RBA                 "
			       + "         ON RBA.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "
			       + " LEFT JOIN TIPO_DISCO                AS TDI                 "
			       + "         ON TDI.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
			       + " LEFT JOIN SISTEMA_OPERACIONAL       AS SOP                 "
			       + "         ON SOP.ID_SO              = REC.ID_SO              "
			       + " LEFT JOIN AMBIENTE                  AS AMB                 "
			       + "         ON AMB.ID_AMBIENTE        = REC.ID_AMBIENTE        "
			       + " LEFT JOIN FAMILIA_FLAVORS           AS FFL                 "
			       + "         ON FFL.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
			       + " LEFT JOIN TIPO_SERVICO              AS TSE                 "
			       + "         ON TSE.ID_TIPO_SERVICO = REC.ID_TIPO_SERVICO       "
			       + " LEFT JOIN SUPORTE                   AS SUP                 "
			       + "         ON SUP.ID_SUPORTE         = REC.ID_SUPORTE         "
			       + " LEFT JOIN MONITORAMENTO             AS MON                 "
			       + "         ON MON.ID_MONITORAMENTO   = REC.ID_MONITORAMENTO   "
			       + " LEFT JOIN TEMPO_LIGADO              AS TLI                 "
			       + "         ON TLI.ID_TEMPO_LIGADO    =  REC.ID_TEMPO_LIGADO   "
			       + " LEFT JOIN TIPO_ADITIVO              AS TAD                 "
			       + "         ON TAD.ID_TIPO_ADITIVO    = REC.ID_TIPO_ADITIVO    "
			       + "   WHERE REC.ID_ADITIVADO = ADI.ID_ADITIVADO                "
			       + "     AND ADI.ID_CONTRATO  = ?                               "
			       + "   ORDER BY ADI.ID_ADITIVADO                                ";


		
		List<ModelAitivoRecursoModal> modelAitivoRecursos = new ArrayList<ModelAitivoRecursoModal>();
		DAOUtil daoUtil = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				ModelAitivoRecursoModal modelAitivoRecurso = new ModelAitivoRecursoModal();
				// Info tabela Aditivo
				modelAitivoRecurso.setId_aditivado               ( set.getLong  ("ID_ADITIVADO"              )                                  );
				modelAitivoRecurso.setId_contrato                ( set.getLong  ("ID_CONTRATO"               )                                  );
				modelAitivoRecurso.setId_status                  ( set.getLong  ("ID_STATUS_ADITIVO"         )                                  );
				modelAitivoRecurso.setDesc_status                ( set.getString("STATUS_CONTRATO"           )                                  );
				modelAitivoRecurso.setDt_criacao                 ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")) );
				valorReal      = Double.valueOf                  ( set.getString("VLR_TOTAL_ADIT"            )                                  );
				modelAitivoRecurso.setValor_total                ( dinheiro.format(valorReal)                                                   );
				modelAitivoRecurso.setObservacao_aditivo         ( set.getString("OBSERVACAO"                )                                  );
				modelAitivoRecurso.setLogin_cadastro             ( set.getString("LOGIN_CADASTRO"            )                                  );
				modelAitivoRecurso.setHubspot_aditivo            ( set.getString("ID_HUB_SPOT"               )                                  );
				///////////////////////////
				modelAitivoRecurso.setId_recurso                 ( set.getLong  ("ID_RECURSO"                )                                  );
				modelAitivoRecurso.setId_status_recurso          ( set.getLong  ("ID_STATUS_RECURSO"         )                                  );
				modelAitivoRecurso.setDesc_status_recurso        ( set.getString("STATUS_RECURSO"            )                                  );
				modelAitivoRecurso.setId_backup                  ( set.getInt   ("ID_BACKUP"                 )                                  );
				modelAitivoRecurso.setId_retencao_backup         ( set.getLong  ("ID_RETENCAO_BACKUP"        )                                  );
				modelAitivoRecurso.setDesc_status_recurso        ( set.getString("STATUS_RECURSO"            )                                  );
				modelAitivoRecurso.setDesc_retencao_backup       ( set.getString("RETENCAO_BACKUP"           )                                  );
				modelAitivoRecurso.setId_tipo_disco              ( set.getLong  ("ID_TIPO_DISCO"             )                                  );
				modelAitivoRecurso.setDesc_tipo_disco            ( set.getString("TIPO_DISCO"                )                                  );
				modelAitivoRecurso.setId_so                      ( set.getLong  ("ID_SO"                     )                                  );
				modelAitivoRecurso.setDesc_sistema_operacional   ( set.getString("SISTEMA_OPERACIONAL"       )                                  );
				modelAitivoRecurso.setId_ambiente                ( set.getLong  ("ID_AMBIENTE"               )                                  );
				modelAitivoRecurso.setDesc_ambiente              ( set.getString("AMBIENTE"                  )                                  );
				modelAitivoRecurso.setId_familia_flavors         ( set.getLong  ("ID_FAMILIA_FLAVORS"        )                                  );
				modelAitivoRecurso.setDesc_familia               ( set.getString("FAMILIA"                   )                                  );
				modelAitivoRecurso.setId_tipo_servico            ( set.getLong  ("ID_TIPO_SERVICO"           )                                  );
				modelAitivoRecurso.setDesc_tipo_servico          ( set.getString("TIPO_SERVICO"              )                                  );
				modelAitivoRecurso.setDt_criacao_recurso         ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CADASTRO_RECURSO")) );
				modelAitivoRecurso.setHost_name_modal_recurso    ( set.getString("HOSTNAME"                  )                                  );
				modelAitivoRecurso.setTamanho_disco_modal_recurso( set.getString("TAMANHO_DISCO"             )                                  );
				modelAitivoRecurso.setPrimary_ip_modalrecurso    ( set.getString("PRIMARY_IP"                )                                  );
				modelAitivoRecurso.setTag_vcenter                ( set.getString("TAG_VCENTER"               )                                  );
				modelAitivoRecurso.setObservacoes_recurso        ( set.getString("OBS"                       )                                  );
				modelAitivoRecurso.setId_suporte                 ( set.getLong  ("ID_SUPORTE"                )                                  );
				modelAitivoRecurso.setDesc_suporte               ( set.getString("DESC_SUPORTE"              )                                  );				
				modelAitivoRecurso.setId_monitoramento           ( set.getLong  ("ID_MONITORAMENTO"          )                                  );
				modelAitivoRecurso.setDesc_monitoramento         ( set.getString("DESC_MONITORAMENTO"        )                                  );
				modelAitivoRecurso.setId_tipo_contratacao        ( set.getLong  ("ID_TEMPO_LIGADO"           )                                  );
				modelAitivoRecurso.setDesc_tempo_ligado          ( set.getString("DESC_TEMPO_LIGADO"         )                                  );
				modelAitivoRecurso.setId_tipo_aditivo            ( set.getLong  ("ID_TIPO_ADITIVO"           )                                  );
				modelAitivoRecurso.setDesc_tipo_ditivo           ( set.getString("DESC_TIPO_DITIVO"          )                                  );
				modelAitivoRecurso.setRecurso_temporario         ( set.getInt   ("RECURSO_TEMPORARIO"        )                                  );
				modelAitivoRecurso.setAdti_sem_receita           ( set.getInt   ("ADTI_SEM_RECEITA"          )                                  );
				modelAitivoRecurso.setAprovador_adit_sem_receita ( set.getString("APROVADOR_ADIT_SEM_RECEITA")                                  );
				modelAitivoRecurso.setPeriodo_utilizacao_bolha   ( daoUtil.FormataDataStringTelaData( set.getString("PERIODO_UTILIZACAO_BOLHA")));
				modelAitivoRecurso.setId_nuvem                   ( set.getLong  ("ID_NUVEM"                  )                                  );
				modelAitivoRecurso.setId_site                    ( set.getLong  ("SITE"                      )                                  );
				modelAitivoRecurso.setId_vigencia                ( set.getLong  ("ID_VIGENCIA"               )                                  );
				modelAitivoRecurso.setId_tempo_contrato          ( set.getLong  ("ID_TEMPO_CONTRATO"         )                                  );
				modelAitivoRecurso.setDt_inicio                  ( daoUtil.FormataDataStringTelaData( set.getString("DT_INICIO"))               );
				modelAitivoRecurso.setDt_final                   ( daoUtil.FormataDataStringTelaData( set.getString("DT_FINAL"))                );
				modelAitivoRecurso.setDt_criacao_vigencia        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO_VIGENCIA")) );
				modelAitivoRecurso.setObservacao_vigencia        ( set.getString("OBS_VIGENCIA"              )                                  );				
				valorReal      = Double.valueOf                  ( set.getString("VALOR_RECURSO"            )                                   );
				modelAitivoRecurso.setValor_recurso              ( dinheiro.format(valorReal)                                                   );

				
				modelAitivoRecurso.setId_moeda                    ( set.getLong  ("ID_MOEDA")                                                   );

				vlrRecuperado = set.getString("VALOR_CONVERTIDO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setValor_convertido            ( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("CUSTO_TOTAL");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setCusto_total                 ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("COTACAO_MOEDA");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelAitivoRecurso.setCotacao_moeda               ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;

				modelAitivoRecursos.add(modelAitivoRecurso);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelAitivoRecursos;
	}
	
	
	public List<ModelAitivoRecursoModal> listaHostnameSelectRecurso( Long idContrato ) throws SQLException{
		
		String sql = "SELECT ID_RECURSO, HOSTNAME FROM RECURSO WHERE ID_CONTRATO = ? ORDER BY ID_RECURSO";
		List<ModelAitivoRecursoModal> modelHostnames = new ArrayList<ModelAitivoRecursoModal>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelAitivoRecursoModal modelHostname = new ModelAitivoRecursoModal();
				modelHostname.setId_recurso             ( set.getLong("ID_RECURSO") );
				modelHostname.setHost_name_modal_recurso( set.getString("HOSTNAME") );					
				modelHostnames.add(modelHostname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelHostnames;
	}

	public ModelHitoricoUpgrade cargaUpdateHistorico( Long idReurso ) throws SQLException{
		
		String sql = "SELECT                                            "
				+ "     REC.ID_RECURSO                                  "
				+ "   , CON.ID_CLIENTE                                  "
				+ "   , REC.ID_CONTRATO                                 "
				+ "   , REC.ID_ADITIVADO                                "
				+ "   , REC.ID_NUVEM                                    "
				+ "   , REC.ID_FAMILIA_FLAVORS                          "
				+ "   , CASE                                            "
				+ "       WHEN REC.TAMANHO_DISCO IS NULL THEN 0         "
				+ "       ELSE REC.TAMANHO_DISCO                        "
				+ "     END AS TAMANHO_DISCO                            "
				+ "   , FFL.FAMILIA                                     "
				+ "   , FFL.CPU                                         "
				+ "   , FFL.RAM                                         "
				+ "   , FFL.VALOR                                       "
				+ "  FROM                                               "
				+ "    RECURSO         AS REC                           "
				+ "  , CONTRATO        AS CON                           "
				+ "  , FAMILIA_FLAVORS AS FFL                           "
				+ "WHERE REC.ID_RECURSO = ?                             "
				+ "  AND CON.ID_CONTRATO = REC.ID_CONTRATO              "
				+ "  AND FFL.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS";
		
		ModelHitoricoUpgrade modelHitoricoUpgrade = new ModelHitoricoUpgrade();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idReurso );

			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				
				modelHitoricoUpgrade.setId_recurso              ( set.getLong  ("ID_RECURSO")         );
				modelHitoricoUpgrade.setId_cliente              ( set.getLong  ("ID_CLIENTE")         );
				modelHitoricoUpgrade.setId_contrato             ( set.getLong  ("ID_CONTRATO")        );
				modelHitoricoUpgrade.setId_aditivado            ( set.getLong  ("ID_ADITIVADO")       );
				modelHitoricoUpgrade.setId_familia_flavors_velho( set.getLong  ("ID_FAMILIA_FLAVORS") );
				modelHitoricoUpgrade.setCpu_velho               ( set.getString("CPU")                );
				modelHitoricoUpgrade.setRam_velho               ( set.getString("RAM")                );
				modelHitoricoUpgrade.setId_nuvem                ( set.getLong  ("ID_NUVEM")           );
				modelHitoricoUpgrade.setTamanho_disco_velho     ( set.getString("TAMANHO_DISCO")      );
				
				valorReal      = Double.valueOf                 ( set.getString("VALOR") );
				modelHitoricoUpgrade.setValor_velho             ( dinheiro.format(valorReal)          );

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelHitoricoUpgrade;
	}
	
	public ModelHitoricoUpgrade upgradeRecurso( ModelHitoricoUpgrade obj ) throws SQLException {
		
		ModelHitoricoUpgrade objRetorno = this.cargaUpdateHistorico( obj.getId_recurso() );
		
		obj.setId_cliente              ( objRetorno.getId_cliente()              );
		obj.setId_contrato             ( objRetorno.getId_contrato()             );
		obj.setId_aditivado            ( objRetorno.getId_aditivado()            );
		obj.setId_familia_flavors_velho( objRetorno.getId_familia_flavors_velho());
		obj.setCpu_velho               ( objRetorno.getCpu_velho()               );
		obj.setRam_velho               ( objRetorno.getRam_velho()               );
		obj.setId_nuvem                ( objRetorno.getId_nuvem()                );
		obj.setTamanho_disco_velho     ( objRetorno.getTamanho_disco_velho()     );
        String valor = objRetorno.getValor_velho();

	    if( valor != null && !valor.isEmpty()) {
	        valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
	    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		}
		obj.setValor_velho             ( valor );

		this.executaUpgradeRecurso( obj );
		
		return obj;
	}
	
	public void executaUpgradeRecurso( ModelHitoricoUpgrade obj ) throws SQLException {
		
		String sqlUp = "UPDATE RECURSO                "
			  	     + "   SET ID_FAMILIA_FLAVORS = ? "
				     + "     , TAMANHO_DISCO      = ? "
				     + "     , VALOR_RECURSO      = ? "
				     + " WHERE ID_RECURSO         = ? " ;
		
		PreparedStatement prepareSql = connection.prepareStatement( sqlUp );
		
		prepareSql.setLong     ( 1, obj.getId_familia_flavors_novo() ); // ID_FAMILIA_FLAVORS
		prepareSql.setString   ( 2, obj.getTamanho_disco_novo()      ); // TAMANHO_DISCO
		prepareSql.setString   ( 3, obj.getValor_novo()              ); // VALOR_RECURSO
		prepareSql.setLong     ( 4, obj.getId_recurso()              ); // ID_RECURSO

		prepareSql.executeUpdate();
		connection.commit();

		if( ( obj.getId_familia_flavors_novo() != obj.getId_familia_flavors_velho() ) || ( !obj.getTamanho_disco_velho().equals(obj.getTamanho_disco_novo()) ) ) {
			
			Long familia_novo  = 0L;
			Long familia_velho = 0L;
			String cpu_novo    = "";
			String cpu_velho   = "";
			String ram_novo    = "";
			String ram_velho   = "";
			
			if(obj.getId_familia_flavors_novo() != obj.getId_familia_flavors_velho()) {
				familia_novo  = obj.getId_familia_flavors_novo();
				familia_velho = obj.getId_familia_flavors_velho();
				cpu_novo      = obj.getCpu_novo();
				cpu_velho     = obj.getCpu_velho();
				ram_novo      = obj.getRam_novo();
				ram_velho     = obj.getRam_velho();
			}
			String Tamanho_novo  = "";
			String Tamanho_velho = "";
			
			if( !obj.getTamanho_disco_velho().equals(obj.getTamanho_disco_novo()) ) {
				Tamanho_novo  = obj.getTamanho_disco_novo();
				Tamanho_velho = obj.getTamanho_disco_velho();
			}
	                                                        //  1                    2                   3            4           5            6          7                        8
			String sqlInsert = "INSERT INTO HITORICO_UPGRADE ( ID_RECURSO         , ID_CLIENTE        , ID_CONTRATO, ID_ADITIVO, DT_CADASTRO, LOGIN    , ID_FAMILIA_FLAVORS_NOVO, ID_FAMILIA_FLAVORS_VELHO, "
					         + "                               TAMANHO_DISCO_VELHO, TAMANHO_DISCO_NOVO, CPU_NOVO   , CPU_VELHO , RAM_NOVO   , RAM_VELHO, VALOR_NOVO             , VALOR_VELHO              )"
					         + "                      VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? )                                                                                        ";
	
			PreparedStatement ps = connection.prepareStatement( sqlInsert );
	
			ps.setLong     (  1, obj.getId_recurso()                                    ); // ID_RECURSO
			ps.setLong     (  2, obj.getId_cliente()                                    ); // ID_CLIENTE
			ps.setLong     (  3, obj.getId_contrato()                                   ); // ID_CONTRATO
			ps.setLong     (  4, obj.getId_aditivado()                                  ); // ID_ADITIVO
			ps.setTimestamp(  5, new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CADASTRO
			ps.setString   (  6, obj.getLogin()                                         ); // LOGIN
			ps.setLong     (  7, familia_novo                                           ); // ID_FAMILIA_FLAVORS_NOVO
			ps.setLong     (  8, familia_velho                                          ); // ID_FAMILIA_FLAVORS_VELHO
			ps.setString   (  9, Tamanho_velho                                          ); // TAMANHO_DISCO_VELHO
			ps.setString   ( 10, Tamanho_novo                                           ); // TAMANHO_DISCO_NOVO
			ps.setString   ( 11, cpu_novo                                               ); // CPU_NOVO
			ps.setString   ( 12, cpu_velho                                              ); // CPU_VELHO
			ps.setString   ( 13, ram_novo                                               ); // RAM_NOVO
			ps.setString   ( 14, ram_velho                                              ); // RAM_VELHO
			ps.setString   ( 15, obj.getValor_novo()                                    ); // VALOR_NOVO
			ps.setString   ( 16, obj.getValor_velho()                                   ); // VALOR_VELHO
	
			ps.execute();
			connection.commit();
			
//			this.atualiaVlrTotalRecursoAditivo( obj.getId_aditivado() );
		}

	}
/*
 // Funcao utilizada para atualizar o valor total do aditivo, mas a pedido do Eugenio,
 // foi solicidado que o Valor toal seja informado de forma manual e que os itens 
 // temha, seu valor como valor referencia.
	public void atualiaVlrTotalRecursoAditivo( Long idAditivo ) throws SQLException {
		
		String sql = "SELECT SUM(VALOR_RECURSO) AS VLR "
				   + "  FROM RECURSO                   "
				   + " WHERE ID_ADITIVADO = ?          ";
		
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong( 1, idAditivo );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		String vlrOld = "";
		while (resutado.next()) vlrOld = resutado.getString("VLR");
		
		sql = "UPDATE ADITIVADO          " 
		    + "   SET VLR_TOTAL_ADIT = ? "
		    + " WHERE ID_ADITIVADO   = ? " ;
		
		PreparedStatement pSql = connection.prepareStatement(sql);
		pSql.setString( 1, vlrOld    );
		pSql.setLong  ( 2, idAditivo );
		
		pSql.executeUpdate();
		connection.commit();
	}
*/
	public List<ModelMoeda> selecaoMoeda(  ) throws SQLException{
		
		String sql = "SELECT ID_MOEDA, MOEDA, OBS FROM MOEDA";

		List<ModelMoeda> listaMoedas = new ArrayList<ModelMoeda>();
			try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelMoeda listaMoeda = new ModelMoeda();
				
				listaMoeda.setId_moeda ( set.getLong  ("ID_MOEDA" ) );
				listaMoeda.setMoeda    ( set.getString("MOEDA"    ) );
				listaMoeda.setObs      ( set.getString("OBS"      ) );
				listaMoedas.add(listaMoeda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaMoedas;
	}

}
