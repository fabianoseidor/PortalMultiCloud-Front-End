package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelRscunho;
import br.com.portal.model.ModelTipoRascunho;

public class DAORascunho {
	private Connection connection;
	
	public DAORascunho() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public  List<ModelRscunho> getRascunho(  ) throws SQLException {
		List<ModelRscunho> listaRascunhos = new ArrayList<ModelRscunho>();
		String sql = "SELECT                                                                                                                        "
				   + "        ID_CONTRATO                                                                                                           "
				   + "      , ID_ADITIVADO                                                                                                          "
				   + "      , ID_CLIENTE                                                                                                            "
				   + "      , STATUS_CONTRATO                                                                                                       "
				   + "      , QTY_TEMPO_RASCUNHO                                                                                                    "
				   + "      , DT_UPDATE_STATUS                                                                                                      "
			 	   + "      , MOTIVO_RASCUNHO                                                                                                       "
				   + "      , RAZAO_SOCIAL                                                                                                          "
				   + "      , QTY_USUARIO_CONTRATADA                                                                                                "
				   + "      , VL_TOTAL                                                                                                              "
				   + "      , TIPO_RASCUNHO                                                                                                         "
				   + "      , VIGENCIA                                                                                                              "
				+ " FROM ( SELECT                                                                                                                   "
			       + "        CON.ID_CONTRATO                                                                                                       "
			       + "      , 0 AS ID_ADITIVADO                                                                                                     "
			       + "      , CON.ID_CLIENTE                                                                                                        "
			       + "      , STA.STATUS_CONTRATO                                                                                                   "
			       + "      , DATEDIFF( day , CON.DT_UPDATE_STATUS ,GETDATE() )  AS QTY_TEMPO_RASCUNHO                                              "
			       + "      , CONVERT(VARCHAR,CON.DT_UPDATE_STATUS, 103) + ' ' + CONVERT(VARCHAR(8), CON.DT_UPDATE_STATUS, 108) AS DT_UPDATE_STATUS "
			       + "      , CON.MOTIVO_RASCUNHO                                                                                                   "
			       + "      , CLI.RAZAO_SOCIAL                                                                                                      "
			       + "      , CON.QTY_USUARIO_CONTRATADA                                                                                            "
			       + "      , FORMAT(CON.VALOR_TOTAL, 'C', 'pt-br') AS VL_TOTAL                                                                     "
			       + "      , TIR.RASCUNHO AS TIPO_RASCUNHO                                                                                         "
			       + "      , CONVERT(VARCHAR, VIG.DT_INICIO, 103) + ' A ' + CONVERT(VARCHAR, VIG.DT_FINAL, 103) AS VIGENCIA                        "
			       + "  FROM                                                                                                                        "
			       + "     CONTRATO        AS CON                                                                                                   "
			       + "   , CLIENTE         AS CLI                                                                                                   "
			       + "   , STATUS_CONTRATO AS STA                                                                                                   "
			       + "   , TIPO_RASCUNHO   AS TIR                                                                                                   "
			       + "   , VIGENCIA        AS VIG                                                                                                   "
			       + "  WHERE CON.ID_STATUS_CONTRATO = 4                                                                                            "
			       + "   AND CLI.ID_CLIENTE          = CON.ID_CLIENTE                                                                               "
			       + "   AND STA.ID_STATUS_CONTRATO  = CON.ID_STATUS_CONTRATO                                                                       "
			       + "   AND TIR.ID_RASCUNHO         = CON.ID_RASCUNHO                                                                              "
			       + "   AND VIG.ID_CONTRATO         = CON.ID_CONTRATO                                                                              "
			       + "UNION ALL                                                                                                                     "
			       + "SELECT ADT.ID_CONTRATO                                                                                                        "
			       + "      , ADT.ID_ADITIVADO                                                                                                      "
			       + "      , CON.ID_CLIENTE                                                                                                        "
			       + "      , STA.STATUS_CONTRATO                                                                                                   "
			       + "      , DATEDIFF( day , ADT.DT_UPDATE_STATUS ,GETDATE() )  AS QTY_TEMPO_RASCUNHO                                              "
			       + "      , CONVERT(VARCHAR,ADT.DT_UPDATE_STATUS, 103) + ' ' + CONVERT(VARCHAR(8), ADT.DT_UPDATE_STATUS, 108) AS DT_UPDATE_STATUS "
			       + "      , ADT.MOTIVO_RASCUNHO                                                                                                   "
			       + "      , CLI.RAZAO_SOCIAL                                                                                                      "
			       + "      , CON.QTY_USUARIO_CONTRATADA                                                                                            "
			       + "      ,FORMAT(ADT.VLR_TOTAL_ADIT, 'C', 'pt-br') AS VL_TOTAL                                                                   "
			       + "      ,TIR.RASCUNHO AS TIPO_RASCUNHO                                                                                          "
			       + "      , CONVERT(VARCHAR, VIG.DT_INICIO, 103) + ' A ' + CONVERT(VARCHAR, VIG.DT_FINAL, 103) AS VIGENCIA                        "
			       + "  FROM                                                                                                                        "
			       + "     ADITIVADO       AS ADT                                                                                                   "
			       + "   , CONTRATO        AS CON                                                                                                   "
			       + "   , CLIENTE         AS CLI                                                                                                   "
			       + "   , STATUS_CONTRATO AS STA                                                                                                   "
			       + "   , TIPO_RASCUNHO   AS TIR                                                                                                   "
			       + "   , VIGENCIA        AS VIG                                                                                                   "
			       + "WHERE ADT.ID_CONTRATO        = CON.ID_CONTRATO                                                                                "
			       + "  AND ADT.ID_STATUS_ADITIVO  = 4                                                                                              "
			       + "  AND CLI.ID_CLIENTE         = CON.ID_CLIENTE                                                                                 "
			       + "  AND STA.ID_STATUS_CONTRATO = ADT.ID_STATUS_ADITIVO                                                                          "
			       + "  AND TIR.ID_RASCUNHO        = ADT.ID_RASCUNHO                                                                                "
			       + "  AND VIG.ID_CONTRATO        = ADT.ID_CONTRATO                                                                                "
			       + ") RESULT_SELECT ORDER BY  TIPO_RASCUNHO DESC, ID_CONTRATO                                                                     ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next()) {
			  ModelRscunho listaRascunho = new ModelRscunho();
			  listaRascunho.setId_contrato           ( resutado.getLong  ("ID_CONTRATO"           ));
			  listaRascunho.setId_aditivo            ( resutado.getLong  ("ID_ADITIVADO"          ));
			  listaRascunho.setId_cliente            ( resutado.getLong  ("ID_CLIENTE"            ));
			  listaRascunho.setStatus_contrato       ( resutado.getString("STATUS_CONTRATO"       ));
			  listaRascunho.setQty_tempo_rascunho    ( resutado.getString("QTY_TEMPO_RASCUNHO"    ));
			  listaRascunho.setDt_update_status      ( resutado.getString("DT_UPDATE_STATUS"      ));
			  listaRascunho.setMotivo_rascunho       ( resutado.getString("MOTIVO_RASCUNHO"       ));
			  listaRascunho.setTipo_rascunho         ( resutado.getString("TIPO_RASCUNHO"         ));
			  listaRascunho.setRazao_social          ( resutado.getString("RAZAO_SOCIAL"          ));
			  listaRascunho.setQty_usuario_contratada( resutado.getString("QTY_USUARIO_CONTRATADA"));
			  listaRascunho.setVl_total              ( resutado.getString("VL_TOTAL"              ));
			  listaRascunho.setVigencia              ( resutado.getString("VIGENCIA"              ));
			  
			  listaRascunhos.add(listaRascunho);
		}

		return listaRascunhos;		
	}
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM SITE WHERE ID_SITE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
	public String atualizaStatusRascunhoContrato( Long idContrato ) {
		
		String Error = null;
		String sqlUp = "UPDATE CONTRATO               "
				     + "   SET ID_STATUS_CONTRATO = 1 "
				     + "WHERE ID_CONTRATO = ?         " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setLong( 1, idContrato ); // ID_FAMILIA_FLAVORS
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	public String atualizaStatusRascunhoContrato( Long idContrato, Long idStatus ) {
		
		String Error = null;
		String sqlUp = "UPDATE CONTRATO               "
				     + "   SET ID_STATUS_CONTRATO = ? "
				     + "WHERE ID_CONTRATO = ?         " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setLong( 1, idStatus   ); // ID_FAMILIA_FLAVORS
			prepareSql.setLong( 2, idContrato ); // ID_FAMILIA_FLAVORS
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	
	public String atualizaStatusRascunhoAditivo( Long idContrato, Long idAditivado ) {
		String Error = null;
		String sqlUp = "UPDATE ADITIVADO             "
				     + "   SET ID_STATUS_ADITIVO = 1 "
				     + "WHERE ID_ADITIVADO = ?       "
				     + "  AND ID_CONTRATO  = ?       " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setLong( 1, idAditivado ); // 
			prepareSql.setLong( 2, idContrato  ); // 
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	
	
	public String deleteContrato( Long idContrato ) throws SQLException {
		
		String retornoErro     = null;
		String sqlRecurso      = "DELETE FROM RECURSO           WHERE ID_CONTRATO  = ?";
		String sqlContrato     = "DELETE FROM CONTRATO          WHERE ID_CONTRATO  = ?";
		String sqlAditivo      = "DELETE FROM ADITIVADO         WHERE ID_CONTRATO  = ?";
		String sqlProduto      = "DELETE FROM ADITIVADO_PRODUTO WHERE ID_ADITIVADO IN ( SELECT ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ? ) ";
		String sqlVigencia     = "DELETE FROM VIGENCIA          WHERE ID_CONTRATO  = ?";
		String sqlVigenciaAdt  = "DELETE FROM VIGENCIA_ADITIVO  WHERE ID_ADITIVADO IN ( SELECT ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ? ) ";

		
		
		
	    try (
	        PreparedStatement deleteContrato    = connection.prepareStatement( sqlContrato    );
	    	PreparedStatement deleteRecurso     = connection.prepareStatement( sqlRecurso     );
			PreparedStatement deleteAditivo     = connection.prepareStatement( sqlAditivo     );
	        PreparedStatement deleteProduto     = connection.prepareStatement( sqlProduto     );
			PreparedStatement deleteVigencia    = connection.prepareStatement( sqlVigencia    );
	        PreparedStatement deleteVigenciaAdt = connection.prepareStatement( sqlVigenciaAdt );){
	    	
	    	
	    	connection.setAutoCommit(false);
	    	deleteVigenciaAdt.setLong  ( 1, idContrato );
	    	deleteVigenciaAdt.executeUpdate();

	    	deleteVigencia.setLong     ( 1, idContrato );
	    	deleteVigencia.executeUpdate();
	    	
	    	deleteProduto.setLong      ( 1, idContrato );
	    	deleteProduto.executeUpdate();

	    	deleteRecurso.setLong      ( 1, idContrato );
	    	deleteRecurso.executeUpdate();
	    	
	    	deleteAditivo.setLong      ( 1, idContrato );
		    deleteAditivo.executeUpdate();

		    deleteContrato.setLong     ( 1, idContrato );
		    deleteContrato.executeUpdate();
		        
		    connection.commit();
		    
		    return retornoErro;
		}catch (SQLException e) {
			retornoErro = "Erro: Dodigo: " + e.getErrorCode() + e.getMessage();
			e.printStackTrace() ;
	      if (connection != null) {
	        try {
	          System.err.print("Transaction is being rolled back");
	          connection.rollback();
	        } catch (SQLException excep) {
	        	excep.printStackTrace();
	        }
	      }
	   }	    
	    return retornoErro;
	} 

	public String deleteAditivo( Long idAditivo ) throws SQLException {
		
		String retornoErro     = null;
		String sqlRecurso      = "DELETE FROM RECURSO           WHERE ID_ADITIVADO = ?";
		String sqlAditivo      = "DELETE FROM ADITIVADO         WHERE ID_ADITIVADO = ?";
		String sqlVigenciaAdt  = "DELETE FROM VIGENCIA_ADITIVO  WHERE ID_ADITIVADO = ?";
		String sqlProduto      = "DELETE FROM ADITIVADO_PRODUTO WHERE ID_ADITIVADO = ?";

	    try (
	    	PreparedStatement deleteRecurso     = connection.prepareStatement( sqlRecurso     );
			PreparedStatement deleteAditivo     = connection.prepareStatement( sqlAditivo     );
	    	PreparedStatement deleteProduto     = connection.prepareStatement( sqlProduto     );
	        PreparedStatement deleteVigenciaAdt = connection.prepareStatement( sqlVigenciaAdt );){
	    	
	    	
	    	connection.setAutoCommit(false);
	    	deleteVigenciaAdt.setLong  ( 1, idAditivo );
	    	deleteVigenciaAdt.executeUpdate();

	    	deleteRecurso.setLong      ( 1, idAditivo );
	    	deleteRecurso.executeUpdate();
	    	
	    	deleteProduto.setLong      ( 1, idAditivo );
	    	deleteProduto.executeUpdate();

		    deleteAditivo.setLong      ( 1, idAditivo );
		    deleteAditivo.executeUpdate();

		        
		    connection.commit();
		    
		    return retornoErro;
		}catch (SQLException e) {
			retornoErro = "Erro: Dodigo: " + e.getErrorCode() + e.getMessage();
			e.printStackTrace() ;
	      if (connection != null) {
	        try {
	          System.err.print("Transaction is being rolled back");
	          connection.rollback();
	        } catch (SQLException excep) {
	        	excep.printStackTrace();
	        }
	      }
	   }
	    return retornoErro;
	} 
	
	public List<ModelTipoRascunho> listaTipoRascunho(){
		
		String sql = "SELECT ID_RASCUNHO, RASCUNHO, DT_CRIACAO, OBS  FROM TIPO_RASCUNHO ORDER BY RASCUNHO";
		List<ModelTipoRascunho> modelTipoRascunhos = new ArrayList<ModelTipoRascunho>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoRascunho modelTipoRascunho = new ModelTipoRascunho();
				modelTipoRascunho.setId_tipo_rascunho(set.getLong("ID_RASCUNHO"));
				modelTipoRascunho.setRascunho(set.getString("RASCUNHO"));
				modelTipoRascunhos.add(modelTipoRascunho);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTipoRascunhos;
	}
	
	public String getInfoRenovacao( Long idContrato ) throws SQLException {
		String sql = "SELECT  RENOVACAO, RENOVACAO_CONTRATO_ORIGEM, RENOVACAO_CONTRATO_NOVO, ID_STATUS_CONTRATO FROM CONTRATO WHERE ID_CONTRATO = ?" ;
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) 
			return resutado.getString("RENOVACAO") + ";" + resutado.getString("RENOVACAO_CONTRATO_ORIGEM") + ";" + resutado.getString("RENOVACAO_CONTRATO_NOVO") + ";" + resutado.getString("ID_STATUS_CONTRATO");
			
		return null;
	}	

}
