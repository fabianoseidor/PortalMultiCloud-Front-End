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
import br.com.portal.model.ModelAditivoModal;
import br.com.portal.model.ModelAditivoProduto;
import br.com.portal.model.ModelHistoricoUpgrade;
import br.com.portal.model.ModelListAditivoProduto;
import br.com.portal.model.ModelMoeda;
import br.com.portal.model.ModelServicoAditivado;
import br.com.portal.model.ModelVigenciaAditivo;

public class DAOAditivoModal {
	
	private Connection connection;
	
	public DAOAditivoModal() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	/*
	 * 
	 * Funcao de atualizacao e insersao de um Contrato.
	 * 
	 * */	
	public ModelListAditivoProduto gravarAditivo( ModelAditivoModal objeto, String msnErro ) throws Exception {
		
		   switch ( objeto.getId_tipo_aditivo().intValue() ) {
		     case 3:
		     case 4:
		    	 return this.gravarAtualizaContratacaoProduto( objeto, msnErro );
//		       break;
		     case 6:
		    	 return this.gravarAtualizaIncrementoServico( objeto, msnErro  );
//			       break;
		     case 8:// gravar Atualiza Incremento Usuario
		    	 return this.gravarAtualizaIncrementoUsuario( objeto, msnErro );
//		       break;
		   }
		 

		return null;
	}

	public ModelListAditivoProduto gravarAtualizaIncrementoUsuario( ModelAditivoModal objeto, String msnErro ) throws Exception {
		String sql = null;
		
		if( objeto.isNovo() ){
			if( objeto.getId_rascunho() != null ) {
                sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   , "
				    + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
				    + "                        ID_HUB_SPOT      , ID_RASCUNHO      , MOTIVO_RASCUNHO                        )"
				    + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                       ";
			}else {
                sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   , "
				    + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
				    + "                        ID_HUB_SPOT  )                                                                "
				    + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                             ";
				
			}
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo()                          ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()                             ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getObservacao()                                 ); // OBSERVACAO
			prepareSql.setTimestamp( 4 , new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setLong     ( 5 , objeto.getId_contrato()                                ); // ID_CONTRATO
			prepareSql.setString   ( 6 , objeto.getValor_convertido()                           ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 7 , objeto.getCusto_total()                                ); // CUSTO_TOTAL
			prepareSql.setString   ( 8 , objeto.getCotacao_moeda()                              ); // COTACAO_MOEDA
			prepareSql.setLong     ( 9 , objeto.getId_moeda()                                   ); // ID_MOEDA
			prepareSql.setString   ( 10, objeto.getLogin_cadastro()                             ); // LOGIN_CADASTRO
			prepareSql.setString   ( 11, objeto.getHubspot_aditivo()                            ); // ID_HUB_SPOT
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 12, objeto.getId_rascunho()                                ); // ID_RASCUNHO
			    prepareSql.setString   ( 13, objeto.getMotivoRascunho()                             ); // MOTIVO_RASCUNHO
			}
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
			
			this.gravarAditivadoProdutoUser(objeto, "INSERT");
			
		} else {
			if( objeto.getId_rascunho() != null ) {
			    sql = "UPDATE ADITIVADO              " 
					+ "   SET ID_STATUS_ADITIVO = ?, "
					+ "       VLR_TOTAL_ADIT    = ?, "
					+ "       VALOR_CONVERTIDO  = ?, "
					+ "       CUSTO_TOTAL       = ?, "
					+ "       COTACAO_MOEDA     = ?, "
					+ "       ID_MOEDA          = ?, "
					+ "       OBSERVACAO        = ?, "
					+ "       ID_HUB_SPOT       = ?, "
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
					+ "       OBSERVACAO        = ?, "
					+ "       ID_HUB_SPOT       = ?  "
					+ " WHERE ID_ADITIVADO      = ?  " ;				
			}

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo() ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()    ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getValor_convertido()  ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 4 , objeto.getCusto_total()       ); // CUSTO_TOTAL
			prepareSql.setString   ( 5 , objeto.getCotacao_moeda()     ); // COTACAO_MOEDA
			prepareSql.setLong     ( 6 , objeto.getId_moeda()          ); // ID_MOEDA
			prepareSql.setString   ( 7 , objeto.getObservacao()        ); // OBSERVACAO
			prepareSql.setString   ( 8 , objeto.getHubspot_aditivo()   ); // ID_HUB_SPOT
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 9 , objeto.getId_rascunho()       ); // ID_RASCUNHO
			    prepareSql.setString   ( 10 , objeto.getMotivoRascunho()    ); // MOTIVO_RASCUNHO
		 	    prepareSql.setLong     ( 11, objeto.getId_aditivado()      ); // ID_ADITIVADO
			}else prepareSql.setLong     ( 9, objeto.getId_aditivado()      ); // ID_ADITIVADO

			prepareSql.executeUpdate();
			connection.commit();

			ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
			vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()        );
			vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaAditivo.setDt_final         ( objeto.getDt_final()            );
			vigenciaAditivo.setObservacao       ( objeto.getObservacao_vigencia() );
			vigenciaAditivo.setId_vigencia      ( this.getIdVigencia(vigenciaAditivo) );
			
			vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
			objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
			
			this.gravarAditivadoProdutoUser(objeto, msnErro);
			
		}
		
		return this.consultaAdtivoPorProdutoID( objeto );
	}

	public void gravarAditivadoProduto( ModelAditivoModal objeto, String msnErro ) throws Exception {
		
		if( !msnErro.equalsIgnoreCase("UPDATE") ) { 
			String sql = "INSERT INTO ADITIVADO_PRODUTO ( ID_PRODUTO    , ID_ADITIVADO, DT_CADASTRO    , QTY_PRODUTO    ,   "
					   + "                                VALOR_UNITARIO, VALOR       , ID_TIPO_PROTUDO, ID_TIPO_ADITIVO  ) "
					   + "                       VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )                                          ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			prepareSql.setLong     ( 1, objeto.getId_produto()                                 ); // ID_PRODUTO
			prepareSql.setLong     ( 2, objeto.getId_aditivado()                               ); // ID_ADITIVADO
			prepareSql.setTimestamp( 3, new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setString   ( 4, objeto.getQty_produto()                                ); // QTY_SERVICO
			prepareSql.setString   ( 5, objeto.getValor_produto()                              ); // VALOR_UNITARIO
			prepareSql.setString   ( 6, objeto.getVlr_total_produto()                          ); // VALOR
			prepareSql.setLong     ( 7, objeto.getId_tipo_produto()                            ); // ID_TIPO_PROTUDO
			prepareSql.setLong     ( 8, objeto.getId_tipo_aditivo()                            ); // ID_TIPO_ADITIVO
	
			prepareSql.execute();
			connection.commit();
		}else {

			String sql = "UPDATE ADITIVADO_PRODUTO                                                      " 
					   + "   SET QTY_PRODUTO = ?, VALOR_UNITARIO  = ?,  VALOR = ?,  ID_TIPO_PROTUDO = ? "
					   + " WHERE ID_PRODUTO   = ?                                                       "
					   + "   AND ID_ADITIVADO = ?                                                       " ;

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getQty_produto()       ); // QTY_PRODUTO
			prepareSql.setString( 2, objeto.getValor_unitario()    ); // VALOR_UNITARIO
			prepareSql.setString( 3, objeto.getVlr_total_produto() ); // VALOR
			prepareSql.setLong  ( 4, objeto.getId_tipo_produto()   ); // ID_TIPO_PROTUDO
			prepareSql.setLong  ( 5, objeto.getId_produto()        ); // ID_PRODUTO
			prepareSql.setLong  ( 6, objeto.getId_aditivado()    ); // ID_ADITIVADO

			prepareSql.executeUpdate();
			connection.commit();
			
		}
		
//		this.atualiaVlrTotalProdutoAditivo( objeto.getId_aditivado() );
	}


	public void gravarAditivadoProdutoUser( ModelAditivoModal objeto, String msnErro ) throws Exception {
		if( !msnErro.equalsIgnoreCase("UPDATE") ) { 
			String sql = "INSERT INTO ADITIVADO_PRODUTO ( ID_PRODUTO, ID_ADITIVADO, DT_CADASTRO, QTY_PRODUTO, VALOR_UNITARIO, VALOR, ID_TIPO_ADITIVO ) "
					   + "                       VALUES ( ?, ?, ?, ?, ?, ?, ? )                                                                           ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			prepareSql.setLong     ( 1, objeto.getId_produto()                                 ); // ID_PRODUTO
			prepareSql.setLong     ( 2, objeto.getId_aditivado()                               ); // ID_ADITIVADO
			prepareSql.setTimestamp( 3, new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setString   ( 4, objeto.getQty_produto()                                ); // QTY_SERVICO
			prepareSql.setString   ( 5, objeto.getValor_produto()                              ); // VALOR_UNITARIO
			prepareSql.setString   ( 6, objeto.getVlr_total_produto()                          ); // VALOR
			prepareSql.setLong     ( 7, objeto.getId_tipo_aditivo()                            ); // ID_TIPO_ADITIVO
	
			prepareSql.execute();
			connection.commit();
		}else {
			if ( objeto.getId_produto() != 8 ) {
				String sql = "UPDATE ADITIVADO_PRODUTO                                                      " 
						   + "   SET QTY_PRODUTO = ?, VALOR_UNITARIO  = ?,  VALOR = ?,  ID_TIPO_PROTUDO = ? "
						   + " WHERE ID_PRODUTO   = ?                                                       "
						   + "   AND ID_ADITIVADO = ?                                                       " ;
	
				PreparedStatement prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString( 1, objeto.getQty_produto()       ); // QTY_PRODUTO
				prepareSql.setString( 2, objeto.getValor_produto()     ); // VALOR_UNITARIO
				prepareSql.setString( 3, objeto.getVlr_total_produto() ); // VALOR
				prepareSql.setLong  ( 4, objeto.getId_tipo_produto()   ); // ID_TIPO_PROTUDO			
				prepareSql.setLong  ( 5, objeto.getId_produto()        ); // ID_PRODUTO
				prepareSql.setLong  ( 6, objeto.getId_aditivado()      ); // ID_ADITIVADO
				prepareSql.executeUpdate();
				connection.commit();
			}else {
				String sql = "UPDATE ADITIVADO_PRODUTO                                " 
						   + "   SET QTY_PRODUTO = ?, VALOR_UNITARIO  = ?,  VALOR = ? "
						   + " WHERE ID_PRODUTO   = ?                                 "
						   + "   AND ID_ADITIVADO = ?                                 " ;
	
				PreparedStatement prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString( 1, objeto.getQty_produto()       ); // QTY_PRODUTO
				prepareSql.setString( 2, objeto.getValor_produto()    ); // VALOR_UNITARIO
				prepareSql.setString( 3, objeto.getVlr_total_produto() ); // VALOR
				prepareSql.setLong  ( 4, objeto.getId_produto()        ); // ID_PRODUTO
				prepareSql.setLong  ( 5, objeto.getId_aditivado()    ); // ID_ADITIVADO
				prepareSql.executeUpdate();
				connection.commit();
				
			}
			
		}
		
//		this.atualiaVlrTotalProdutoAditivo( objeto.getId_aditivado() );

	}
	
	
	public void gravarServicoAditivado( ModelAditivoModal objeto, String msnErro ) throws Exception {
		if( !msnErro.equalsIgnoreCase("UPDATE") ) { 
			String sql = "INSERT INTO SERVICO_ADITIVADO ( ID_SERVICO_CONTRATADO, ID_ADITIVADO, QTY_SERVICO, DESC_SERV_CONTRATADO, DT_CRIACAO, VALOR, VALOR_UNITARIO, ID_TIPO_ADITIVO  )"
					   + "                       VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )                                                                                                     ";
	
			PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			prepareSql.setLong     ( 1, objeto.getId_servico_contratado()                      ); // ID_SERVICO_CONTRATADO
			prepareSql.setLong     ( 2, objeto.getId_aditivado()                               ); // ID_ADITIVADO
			prepareSql.setString   ( 3, objeto.getQty_serv_contratado()                        ); // QUANTIDADE
			prepareSql.setString   ( 4, objeto.getDesc_serv_contratado()                       ); // DESC_SERV_CONTRATADO
			prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setString   ( 6, objeto.getValor_serv_contratado()                      ); // DESC_SERV_CONTRATADO
			prepareSql.setString   ( 7, objeto.getValor_unit_serv_cont()                       ); // DESC_SERV_CONTRATADO
			prepareSql.setLong     ( 8, objeto.getId_tipo_aditivo()                            ); // DESC_SERV_CONTRATADO
	
			prepareSql.execute();
			connection.commit();
		} else {

			String sql = "UPDATE SERVICO_ADITIVADO                                " 
					   + "   SET QTY_SERVICO = ?, VALOR_UNITARIO  = ?,  VALOR = ? "
					   + " WHERE ID_ADITIVADO            = ?                      "
					   + "   AND ID_SERVICO_CONTRATADO = ?                        " ;

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getQty_serv_contratado()   ); // QTY_SERVICO
			prepareSql.setString( 2, objeto.getValor_unit_serv_cont()  ); // VALOR_UNITARIO
			prepareSql.setString( 3, objeto.getValor_serv_contratado() ); // VALOR
			prepareSql.setLong  ( 4, objeto.getId_aditivado()          ); // ID_PRODUTO
			prepareSql.setLong  ( 5, objeto.getId_servico_contratado() ); // ID_SERVICO_CONTRATADO

			prepareSql.executeUpdate();
			connection.commit();
		}
//		this.atualiaVlrTotalProdutoAditivo( objeto.getId_aditivado() );								
	}

	/*
	 * 
	 * 
	 * */	
	public ModelListAditivoProduto gravarAtualizaIncrementoServico( ModelAditivoModal objeto, String msnErro  ) throws Exception {
		String sql = "";
		if( objeto.isNovo() ){
			if( objeto.getId_rascunho() != null ) {
			       sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   ,   "
					   + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO,   "
					   + "                        ID_HUB_SPOT      ,ID_RASCUNHO    , MOTIVO_RASCUNHO                            ) "
					   + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                         ";
			}else {
			       sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   ,   "
					   + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO,   "
					   + "                        ID_HUB_SPOT )                                                                   "
					   + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                               ";
			}
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo()                          ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()                             ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getObservacao()                                 ); // OBSERVACAO
			prepareSql.setTimestamp( 4 , new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setLong     ( 5 , objeto.getId_contrato()                                ); // ID_CONTRATO
			prepareSql.setString   ( 6 , objeto.getValor_convertido()                           ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 7 , objeto.getCusto_total()                                ); // CUSTO_TOTAL
			prepareSql.setString   ( 8 , objeto.getCotacao_moeda()                              ); // COTACAO_MOEDA
			prepareSql.setLong     ( 9 , objeto.getId_moeda()                                   ); // ID_MOEDA
			prepareSql.setString   ( 10, objeto.getLogin_cadastro()                             ); // LOGIN_CADASTRO
			prepareSql.setString   ( 11, objeto.getHubspot_aditivo()                            ); // ID_HUB_SPOT
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 12, objeto.getId_rascunho()                                ); // ID_RASCUNHO
			    prepareSql.setString   ( 13, objeto.getMotivoRascunho()                             ); // MOTIVO_RASCUNHO
			}

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
			// Grava serivo aditivo
			this.gravarServicoAditivado(objeto, "INSERT");
			
		} 
		else {		
			if( objeto.getId_rascunho() != null ) {
		        sql = "UPDATE ADITIVADO              " 
			 	    + "   SET ID_STATUS_ADITIVO = ?, "
				    + "       VLR_TOTAL_ADIT    = ?, "
				    + "       VALOR_CONVERTIDO  = ?, "
				    + "       CUSTO_TOTAL       = ?, "
				    + "       COTACAO_MOEDA     = ?, "
				    + "       ID_MOEDA          = ?, "
				    + "       OBSERVACAO        = ?, "
				    + "       ID_HUB_SPOT       = ?, "
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
					+ "       OBSERVACAO        = ?, "
					+ "       ID_HUB_SPOT       = ?  "
					+ " WHERE ID_ADITIVADO      = ?  " ;				
			}
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo() ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()    ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getValor_convertido()  ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 4 , objeto.getCusto_total()       ); // CUSTO_TOTAL
			prepareSql.setString   ( 5 , objeto.getCotacao_moeda()     ); // COTACAO_MOEDA
			prepareSql.setLong     ( 6 , objeto.getId_moeda()          ); // ID_MOEDA
			prepareSql.setString   ( 7 , objeto.getObservacao()        ); // OBSERVACAO
			prepareSql.setString   ( 8 , objeto.getHubspot_aditivo()   ); // ID_HUB_SPOT
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 9 , objeto.getId_rascunho()       ); // ID_RASCUNHO
			    prepareSql.setString   ( 10 , objeto.getMotivoRascunho()    ); // MOTIVO_RASCUNHO
		 	    prepareSql.setLong     ( 11, objeto.getId_aditivado()      ); // ID_ADITIVADO
			}else prepareSql.setLong     ( 9, objeto.getId_aditivado()      ); // ID_ADITIVADO
			
			prepareSql.executeUpdate();
			connection.commit();
			
			ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
			vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()        );
			vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaAditivo.setDt_final         ( objeto.getDt_final()            );
			vigenciaAditivo.setObservacao       ( objeto.getObservacao_vigencia() );
			vigenciaAditivo.setId_vigencia      ( this.getIdVigencia(vigenciaAditivo) );
			
			vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
			objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
			// Grava serivo aditivo
			this.gravarServicoAditivado(objeto, msnErro);
			
		}
		
		return this.consultaAdtivoPorProdutoID( objeto );
	}
	
	/*
	 * 
	 * 
	 * */	
	public ModelListAditivoProduto gravarAtualizaContratacaoProduto( ModelAditivoModal objeto, String msnErro ) throws Exception {
		String sql = "";
		if( objeto.isNovo() ){
			if( objeto.getId_rascunho() != null ) {
			    sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   , "
			        + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
			        + "                        ID_HUB_SPOT      , ID_RASCUNHO   , MOTIVO_RASCUNHO)                           "
				    + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                       ";
			}else {
                sql = "INSERT INTO ADITIVADO ( ID_STATUS_ADITIVO, VLR_TOTAL_ADIT, OBSERVACAO   , DT_CRIACAO, ID_CONTRATO   , "
				    + "                        VALOR_CONVERTIDO , CUSTO_TOTAL   , COTACAO_MOEDA, ID_MOEDA  , LOGIN_CADASTRO, "
				    + "                        ID_HUB_SPOT                                                                  )"
				    + "               VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                             ";
				
			}

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo()                          ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()                             ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getObservacao()                                 ); // OBSERVACAO
			prepareSql.setTimestamp( 4 , new java.sql.Timestamp(new java.util.Date().getTime()) ); // DT_CRIACAO
			prepareSql.setLong     ( 5 , objeto.getId_contrato()                                ); // ID_CONTRATO
			prepareSql.setString   ( 6 , objeto.getValor_convertido()                           ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 7 , objeto.getCusto_total()                                ); // CUSTO_TOTAL
			prepareSql.setString   ( 8 , objeto.getCotacao_moeda()                              ); // COTACAO_MOEDA
			prepareSql.setLong     ( 9 , objeto.getId_moeda()                                   ); // ID_MOEDA
			prepareSql.setString   ( 10, objeto.getLogin_cadastro()                             ); // LOGIN_CADASTRO
			prepareSql.setString   ( 11, objeto.getHubspot_aditivo()                            ); // LOGIN_CADASTRO
			
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 12, objeto.getId_rascunho()                            ); // ID_RASCUNHO
			    prepareSql.setString   ( 13, objeto.getMotivoRascunho()                         ); // MOTIVO_RASCUNHO
			}
			
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
			
			this.gravarAditivadoProduto(objeto, "INSERT");
		}else {
			
			if( objeto.getId_rascunho() != null ) {
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
					+ " WHERE ID_ADITIVADO = ?       " ;
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
			

			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong     ( 1 , objeto.getId_status_aditivo() ); // ID_STATUS_ADITIVO
			prepareSql.setString   ( 2 , objeto.getVlr_total_adit()    ); // VLR_TORAL_ADIT
			prepareSql.setString   ( 3 , objeto.getValor_convertido()  ); // VALOR_CONVERTIDO
			prepareSql.setString   ( 4 , objeto.getCusto_total()       ); // CUSTO_TOTAL
			prepareSql.setString   ( 5 , objeto.getCotacao_moeda()     ); // COTACAO_MOEDA
			prepareSql.setLong     ( 6 , objeto.getId_moeda()          ); // ID_MOEDA
			prepareSql.setString   ( 7 , objeto.getHubspot_aditivo()   ); // ID_HUB_SPOT
			prepareSql.setString   ( 8 , objeto.getObservacao()        ); // OBSERVACAO
			if( objeto.getId_rascunho() != null ) {
			    prepareSql.setLong     ( 9 , objeto.getId_rascunho()       ); // ID_RASCUNHO
			    prepareSql.setString   ( 10, objeto.getMotivoRascunho()    ); // MOTIVO_RASCUNHO
		 	    prepareSql.setLong     ( 11, objeto.getId_aditivado()      ); // ID_ADITIVADO
			}else prepareSql.setLong     ( 9, objeto.getId_aditivado()      ); // ID_ADITIVADO

			prepareSql.executeUpdate();
			connection.commit();

			ModelVigenciaAditivo vigenciaAditivo = new ModelVigenciaAditivo();
//			 this.getIdVigencia( objeto );
			vigenciaAditivo.setId_aditivado     ( objeto.getId_aditivado()            );
			vigenciaAditivo.setId_tempo_contrato( objeto.getId_tempo_contrato()       );
			vigenciaAditivo.setDt_inicio        ( objeto.getDt_inicio()               );
			vigenciaAditivo.setDt_final         ( objeto.getDt_final()                );
			vigenciaAditivo.setObservacao       ( objeto.getObservacao_vigencia()     );
			vigenciaAditivo.setId_vigencia      ( this.getIdVigencia(vigenciaAditivo) );
			
			vigenciaAditivo = this.gravarVigenciaAditivo( vigenciaAditivo );
			objeto.setId_vigencia( vigenciaAditivo.getId_vigencia() );
			
			this.gravarAditivadoProduto( objeto, msnErro );
			
		}
		
		return this.consultaAdtivoPorProdutoID( objeto );
	}

	
	/*
	 * 
	 * Busca o ID Aditivo apos cadastro do Aditivo
	 * 
	 * */	
	public Long getMaxIdAditivo( ModelAditivoModal objeto ) throws SQLException {
		String sql = "SELECT MAX( ID_ADITIVADO ) MAX_ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ? AND ID_STATUS_ADITIVO = ? ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1, objeto.getId_contrato()       );
		statemet.setLong ( 2, objeto.getId_status_aditivo() );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_ADITIVADO");

		return null;
	}

	public ModelVigenciaAditivo gravarVigenciaAditivo( ModelVigenciaAditivo objeto ) throws Exception {
		DAOUtil daoUtil = new DAOUtil();
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO VIGENCIA_ADITIVO ( ID_ADITIVADO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, OBSERVACAO)"
			 		  + " VALUES ( ?, ?, ?, ?, ?, ? ) ";
			
	
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
		}else {
			String sql = "UPDATE VIGENCIA_ADITIVO                                                 "
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

		return null;		
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

	
	public ModelAditivoProduto getAditivoProduto( ModelAditivoModal objeto ) throws SQLException {
		ModelAditivoProduto aditivoProduto = new ModelAditivoProduto(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT * FROM ADITIVADO_PRODUTO WHERE ID_PRODUTO = ? AND ID_ADITIVADO = ? AND ID_TIPO_PROTUDO = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1,  objeto.getId_produto() );
		prepareSql.setLong ( 2,  objeto.getId_aditivado() );
		prepareSql.setLong ( 3,  objeto.getId_tipo_produto() );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			aditivoProduto.setId_produto     ( resutado.getLong("ID_PRODUTO"                                        ));
			aditivoProduto.setId_aditivo     ( resutado.getLong("ID_ADITIVADO"                                      ));
			aditivoProduto.setId_tipo_protudo( resutado.getLong("ID_TIPO_ADITIVO"                                   ));
			aditivoProduto.setId_tipo_protudo( resutado.getLong("ID_TIPO_PROTUDO"                                   ));
			aditivoProduto.setDt_cadastro    ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_CADASTRO") ));
			aditivoProduto.setQty_servico    ( resutado.getInt("QTY_PRODUTO"                                        ));
			
			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resutado.getString("VALOR") );
			Double valorRealUnit  = Double.valueOf( resutado.getString("VALOR_UNITARIO") );

			aditivoProduto.setValor         ( dinheiro.format(valorReal    ));
			aditivoProduto.setValor_unitario(dinheiro.format(valorRealUnit ));
		}
		return aditivoProduto;		
	}

	public ModelServicoAditivado getServicoAditivado( ModelAditivoModal objeto ) throws SQLException {
		ModelServicoAditivado servicoAditivado = new ModelServicoAditivado(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT * FROM SERVICO_ADITIVADO WHERE ID_SERVICO_CONTRATADO = ? AND ID_ADITIVADO = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1,  objeto.getId_servico_contratado() );
		prepareSql.setLong ( 2,  objeto.getId_aditivado() );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			servicoAditivado.setId_servico_contratado( resutado.getLong("ID_SERVICO_CONTRATADO" ));
			servicoAditivado.setId_aditivado         ( resutado.getLong("ID_ADITIVADO" )         );
			servicoAditivado.setQuantidade           ( resutado.getInt ("QTY_SERVICO" )           );
			servicoAditivado.setDt_criacao           ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_CRIACAO") ));
			servicoAditivado.setDesc_serv_contratado ( resutado.getString("DESC_SERV_CONTRATADO"));
		}
		return servicoAditivado;		
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
	
	
	public Integer isExistProduto( Long idProduto, Long idAditivo ) throws SQLException {
		String sql = "SELECT COUNT(1) AS IS_PRODUTO_EXIST FROM ADITIVADO_PRODUTO AS AP WHERE AP.ID_ADITIVADO = ? AND AP.ID_PRODUTO = ?";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong( 1, idAditivo );
		resultadoSql.setLong( 2, idProduto );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getInt("IS_PRODUTO_EXIST");
		

		return null;
	}
	
	public Integer isExistServico( Long idServico, Long idAditivo ) throws SQLException {
		String sql = "SELECT COUNT(1) AS IS_SERVICO_EXIST FROM SERVICO_ADITIVADO AS SA WHERE SA.ID_SERVICO_CONTRATADO = ? AND SA.ID_ADITIVADO = ?";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong( 1, idServico );
		resultadoSql.setLong( 2, idAditivo );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getInt("IS_SERVICO_EXIST");
		

		return null;
	}
/*	
  -- Esta funcao era utilizada para atualizar o valor do aditivo,
     mas o Eugenio solicitou que o valor do aditivo fosse informado 
     manualmente independente da quantidade de itens do aditido
	public void atualiaVlrTotalProdutoAditivo( Long idAditivo ) throws SQLException {
		
		String sql = "SELECT SUM(VLR) AS VLR_TOTAL FROM (                                                   "
				   + "     SELECT SUM( VALOR ) AS VLR FROM ADITIVADO_PRODUTO APR WHERE APR.ID_ADITIVADO = ? "
				   + "    UNION ALL                                                                         "
				   + "     SELECT SUM( VALOR ) AS VLR FROM SERVICO_ADITIVADO WHERE ID_ADITIVADO         = ? "
				   + " ) AS TOTAL                                                                           ";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong( 1, idAditivo );
		resultadoSql.setLong( 2, idAditivo );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		String vlrOld = "";
		while (resutado.next()) vlrOld = resutado.getString("VLR_TOTAL");
		
		sql = "UPDATE ADITIVADO          " 
		    + "   SET VLR_TOTAL_ADIT = ? "
		    + " WHERE ID_ADITIVADO = ?   " ;
		
		PreparedStatement pSql = connection.prepareStatement(sql);
		pSql.setString( 1, vlrOld    );
		pSql.setLong  ( 2, idAditivo );
		
		pSql.executeUpdate();
		connection.commit();
	}
*/	
	
	public List<ModelListAditivoProduto> buscarListaAditivoProdutosContratados( Long idContrato ) throws SQLException {
		
		String sql = " SELECT                                                                 "
				+ "     ID_ADITIVADO                                                          "
				+ "   , DT_CRIACAO                                                            "
				+ "   , ID_STATUS_ADITIVO                                                     "
				+ "   , STATUS_CONTRATO                                                       "
				+ "   , ID_CONTRATO                                                           "
				+ "   , VLR_TOTAL_ADIT                                                        "
				+ "   , OBSERVACAO                                                            "
				+ "   , LOGIN_CADASTRO                                                        "
				+ "   , ID_HUB_SPOT                                                           "
				
				+ "   , ID_MOEDA                                                              "
				+ "   , VALOR_CONVERTIDO                                                      "
				+ "   , CUSTO_TOTAL                                                           "
				+ "   , COTACAO_MOEDA                                                         "

				
				+ "   , ID_TIPO_ADITIVO_CONTRATADO                                            "
				+ "   , NOME_TIPO_ADITIVO_CONTRATADO                                          "
				+ "   , ID_PRODUTO_CONTRATADO                                                 "
				+ "   , NOME_PROD_CONTRATADO                                                  "
				+ "   , VLR_PROD_CAD_CONTRATADO                                               "
				+ "   , QTY_PRODUTO_CONTRATADO                                                "
				+ "   , VALOR_UNITARIO_CONTRATADO                                             "
				+ "   , DESC_SERV_PROD_CONTRATADO                                             "
				+ "   , VLR_PRODUTO_CONTRATADO                                                "
				+ "   , DATA_PRODUTO_CONTRATADO                                               "
				+ "   , ID_TIPO_PROTUDO_CONTRATADO                                            "
				+ "   , DESC_TIPO_PRODUTO_CONTRATADO                                          "
				+ "FROM (                                                                     "
				+ "			SELECT                                                            "
				+ "			     ADT.ID_ADITIVADO                                             "
				+ "			   , ADT.DT_CRIACAO                                               "
				+ "			   , ADT.ID_STATUS_ADITIVO                                        "
				+ "            , STC.STATUS_CONTRATO                                          "
				+ "			   , ADT.ID_CONTRATO                                              "
				+ "			   , ADT.VLR_TOTAL_ADIT                                           "
				+ "			   , ADT.OBSERVACAO                                               "
				+ "			   , ADT.LOGIN_CADASTRO                                           "
				+ "			   , ADT.ID_HUB_SPOT                                              "
				
				+ "            , ADT.ID_MOEDA                                                 "
				+ "            , ADT.VALOR_CONVERTIDO                                         "
				+ "            , ADT.CUSTO_TOTAL                                              "
				+ "            , ADT.COTACAO_MOEDA                                            "

				
				+ "			   , SAD.ID_TIPO_ADITIVO        AS ID_TIPO_ADITIVO_CONTRATADO     "
				+ "			   , TAD.DESC_TIPO_DITIVO       AS NOME_TIPO_ADITIVO_CONTRATADO   "
				+ "			   , SAD.ID_SERVICO_CONTRATADO  AS ID_PRODUTO_CONTRATADO          "
				+ "			   , SCO.DESC_SERVICO           AS NOME_PROD_CONTRATADO           "
				+ "			   , SCO.VLR_SERVICO            AS VLR_PROD_CAD_CONTRATADO        "
				+ "			   , SAD.QTY_SERVICO            AS QTY_PRODUTO_CONTRATADO         "
				+ "			   , SAD.VALOR_UNITARIO         AS VALOR_UNITARIO_CONTRATADO      "
				+ "			   , SAD.VALOR                  AS VLR_PRODUTO_CONTRATADO         "
				+ "			   , SAD.DESC_SERV_CONTRATADO   AS DESC_SERV_PROD_CONTRATADO      "
				+ "			   , SAD.DT_CRIACAO             AS DATA_PRODUTO_CONTRATADO        "
				+ "			   , NULL                       AS ID_TIPO_PROTUDO_CONTRATADO     "
				+ "			   , NULL                       AS DESC_TIPO_PRODUTO_CONTRATADO   "
				+ "			  FROM       ADITIVADO          AS ADT                            "
				+ "			  INNER JOIN SERVICO_ADITIVADO  AS SAD                            "
				+ "			         ON SAD.ID_ADITIVADO        = ADT.ID_ADITIVADO            "
				+ "			  LEFT JOIN TIPO_ADITIVO       AS TAD                             "
				+ "		           ON TAD.ID_TIPO_ADITIVO       = SAD.ID_TIPO_ADITIVO         "
				+ "			 INNER JOIN SERVICO_CONTRATADO AS SCO                             "
				+ "			         ON SCO.ID_SERVICO_CONTRATADO = SAD.ID_SERVICO_CONTRATADO "
				+ "          INNER JOIN STATUS_CONTRATO    AS STC                             "
				+ "                  ON STC.ID_STATUS_CONTRATO    = ADT.ID_STATUS_ADITIVO     "
				+ "			 WHERE ADT.ID_CONTRATO = ?                                        "
				+ "	UNION ALL                                                                 "
				+ "			SELECT                                                            "
				+ "			     ADT.ID_ADITIVADO                                             "
				+ "			   , ADT.DT_CRIACAO                                               "
				+ "			   , ADT.ID_STATUS_ADITIVO                                        "
				+ "            , STC.STATUS_CONTRATO                                          "
				+ "			   , ADT.ID_CONTRATO                                              "
				+ "			   , ADT.VLR_TOTAL_ADIT                                           "
				+ "			   , ADT.OBSERVACAO                                               "
				+ "			   , ADT.LOGIN_CADASTRO                                           "
				+ "			   , ADT.ID_HUB_SPOT                                              "

				+ "            , ADT.ID_MOEDA                                                 "
				+ "            , ADT.VALOR_CONVERTIDO                                         "
				+ "            , ADT.CUSTO_TOTAL                                              "
				+ "            , ADT.COTACAO_MOEDA                                            "
				
				
				+ "			   , APR.ID_TIPO_ADITIVO        AS ID_TIPO_ADITIVO_CONTRATADO     "
				+ "			   , TAD.DESC_TIPO_DITIVO       AS NOME_TIPO_ADITIVO_CONTRATADO   "
				+ "			   , APR.ID_PRODUTO             AS ID_PRODUTO_CONTRATADO          "
				+ "			   , PRO.PRODUTO                AS NOME_PROD_CONTRATADO           "
				+ "			   , PRO.VALOR                  AS VLR_PROD_CAD_CONTRATADO        "
				+ "			   , APR.QTY_PRODUTO            AS QTY_PRODUTO_CONTRATADO         "
				+ "			   , APR.VALOR_UNITARIO         AS VALOR_UNITARIO_CONTRATADO      "
				+ "			   , APR.VALOR                  AS VLR_PRODUTO_CONTRATADO         "
				+ "			   , NULL                       AS DESC_SERV_PROD_CONTRATADO      "
				+ "			   , APR.DT_CADASTRO            AS DATA_PRODUTO_CONTRATADO        "
				+ "			   , APR.ID_TIPO_PROTUDO        AS ID_TIPO_PROTUDO_CONTRATADO     "
				+ "			   , TPR.DESC_TIPO_PRODUTO      AS DESC_TIPO_PRODUTO_CONTRATADO   "
				+ "			  FROM      ADITIVADO           AS ADT                            "
				+ "			  INNER JOIN ADITIVADO_PRODUTO AS APR                             "
				+ "			         ON APR.ID_ADITIVADO       = ADT.ID_ADITIVADO             "
				+ "			  LEFT JOIN TIPO_ADITIVO       AS TAD                             "
				+ "			         ON TAD.ID_TIPO_ADITIVO    = APR.ID_TIPO_ADITIVO          "
				+ "			  LEFT JOIN PRODUTO            AS PRO                             "
				+ "			         ON PRO.ID_PRODUTO         = APR.ID_PRODUTO               "
				+ "			  LEFT JOIN TIPO_PRODUTO       AS TPR                             "
				+ "			         ON TPR.ID_TIPO_PRODUTO    = APR.ID_TIPO_PROTUDO          "
				+ "          INNER JOIN STATUS_CONTRATO    AS STC                             "
				+ "                  ON STC.ID_STATUS_CONTRATO = ADT.ID_STATUS_ADITIVO        "
				+ "			WHERE ADT.ID_CONTRATO = ?                                         "
				+ ") ADITIVOS_CONTRATAOS ORDER BY ID_ADITIVADO                                ";


		List<ModelListAditivoProduto> modelListAditivoProdutos = new ArrayList<ModelListAditivoProduto>();
		DAOUtil daoUtil = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			ps.setLong ( 2, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelListAditivoProduto modelListAditivoProduto = new ModelListAditivoProduto();

				modelListAditivoProduto.setId_aditivado                ( set.getLong  ("ID_ADITIVADO")                                                    );
				modelListAditivoProduto.setDt_criacao                  ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO"))              );
				modelListAditivoProduto.setId_status_aditivo           ( set.getLong  ("ID_STATUS_ADITIVO")                                               );
				modelListAditivoProduto.setDsc_status_aditivo          ( set.getString("STATUS_CONTRATO")                                                 );
				modelListAditivoProduto.setId_contrato                 ( set.getLong  ("ID_CONTRATO")                                                     );
				modelListAditivoProduto.setObservacao                  ( set.getString("OBSERVACAO")                                                      );
				modelListAditivoProduto.setLogin_cadastro              ( set.getString("LOGIN_CADASTRO")                                                  );
				modelListAditivoProduto.setHubspot_aditivo             ( set.getString("ID_HUB_SPOT")                                                     );
								
				vlrRecuperado = set.getString("VLR_TOTAL_ADIT");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelListAditivoProduto.setVlr_total_adit              ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;

				modelListAditivoProduto.setId_tipo_aditivo             ( set.getLong  ("ID_TIPO_ADITIVO_CONTRATADO")                                      );
				modelListAditivoProduto.setNome_tipo_aditivo_contratado( set.getString("NOME_TIPO_ADITIVO_CONTRATADO")                                    );

				modelListAditivoProduto.setId_produto_contratado       ( set.getLong  ("ID_PRODUTO_CONTRATADO")                                           );
				modelListAditivoProduto.setNome_prod_contratado        ( set.getString("NOME_PROD_CONTRATADO")                                            );
				
				
				vlrRecuperado = set.getString("VLR_PROD_CAD_CONTRATADO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
					valorReal      = Double.valueOf( vlrRecuperado );
					modelListAditivoProduto.setVlr_prod_cad_contratado     ( dinheiro.format(valorReal)                                                       );
					vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				modelListAditivoProduto.setQty_produto_contratado      ( set.getString("QTY_PRODUTO_CONTRATADO")                                          );

				vlrRecuperado = set.getString("VALOR_UNITARIO_CONTRATADO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
					valorReal      = Double.valueOf( vlrRecuperado );
					modelListAditivoProduto.setValor_unitario_contratado   ( dinheiro.format(valorReal)                                                       );
					vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("VLR_PRODUTO_CONTRATADO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelListAditivoProduto.setVlr_produto_contratado      ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;

				modelListAditivoProduto.setData_produto_contratado     ( daoUtil.FormataDataStringTelaDataTime( set.getString("DATA_PRODUTO_CONTRATADO")) );
				
				modelListAditivoProduto.setDesc_serv_prod_contratado   ( set.getString("DESC_SERV_PROD_CONTRATADO"));
				modelListAditivoProduto.setId_tipo_protudo_contratado  ( set.getLong  ("ID_TIPO_PROTUDO_CONTRATADO")                                      );
				modelListAditivoProduto.setDesc_tipo_produto_contratado( set.getString("DESC_TIPO_PRODUTO_CONTRATADO")                                    );
				
				modelListAditivoProduto.setId_moeda                    ( set.getLong  ("ID_MOEDA")                                                        );

				vlrRecuperado = set.getString("VALOR_CONVERTIDO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelListAditivoProduto.setValor_convertido            ( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("CUSTO_TOTAL");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelListAditivoProduto.setCusto_total                 ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("COTACAO_MOEDA");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   modelListAditivoProduto.setCotacao_moeda               ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				

				modelListAditivoProdutos.add(modelListAditivoProduto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelListAditivoProdutos;
	}
	
	
	public ModelListAditivoProduto consultaAdtivoPorProdutoID( ModelAditivoModal obj ) throws SQLException {
		
		ModelListAditivoProduto listAditivoProduto = new ModelListAditivoProduto();

		
		   switch ( obj.getId_tipo_aditivo().intValue() ) {
		     case 3:
		     case 4:
		     case 8:
                  return this.getAditivoProdutoContratados( obj );

		     case 6:
		    	 return this.getAditivoServicoContratados( obj );
		   }
		
		return listAditivoProduto;
	}
	
	public ModelListAditivoProduto getAditivoProdutoContratados( ModelAditivoModal obj ) throws SQLException {
		
		String sql = "SELECT                                                       "
				+ "     ADT.ID_ADITIVADO                                           "
				+ "   , ADT.DT_CRIACAO                                             "
				+ "   , ADT.ID_STATUS_ADITIVO                                      "
				+ "   , ADT.ID_CONTRATO                                            "
				+ "   , ADT.VLR_TOTAL_ADIT                                         "
				+ "   , ADT.OBSERVACAO                                             "
				+ "   , ADT.ID_HUB_SPOT                                            "
				+ "   , ADT.ID_RASCUNHO                                            "
				+ "   , ADT.MOTIVO_RASCUNHO                                        "
				
				+ "   , ADT.ID_MOEDA                                               "
				+ "   , ADT.VALOR_CONVERTIDO                                       "
				+ "   , ADT.CUSTO_TOTAL                                            "
				+ "   , ADT.COTACAO_MOEDA                                          "
				+ "   , ADT.LOGIN_CADASTRO                                         "

				+ "   , APR.ID_TIPO_ADITIVO        AS ID_TIPO_ADITIVO_CONTRATADO   "
				+ "   , TAD.DESC_TIPO_DITIVO       AS NOME_TIPO_ADITIVO_CONTRATADO "
				+ "   , APR.ID_PRODUTO             AS ID_PRODUTO_CONTRATADO        "
				+ "   , PRO.PRODUTO                AS NOME_PROD_CONTRATADO         "
				+ "   , PRO.VALOR                  AS VLR_PROD_CAD_CONTRATADO      "
				+ "   , APR.QTY_PRODUTO            AS QTY_PRODUTO_CONTRATADO       "
				+ "   , APR.VALOR_UNITARIO         AS VALOR_UNITARIO_CONTRATADO    "
				+ "   , APR.VALOR                  AS VLR_PRODUTO_CONTRATADO       "
				+ "   , APR.DT_CADASTRO            AS DATA_PRODUTO_CONTRATADO      "
				+ "   , APR.ID_TIPO_PROTUDO        AS ID_TIPO_PROTUDO_CONTRATADO   "
				+ "   , TPR.DESC_TIPO_PRODUTO      AS DESC_TIPO_PRODUTO_CONTRATADO "
				+ "  FROM ADITIVADO                AS ADT                          "
				+ "   LEFT JOIN ADITIVADO_PRODUTO  AS APR                          "
				+ "      ON APR.ID_ADITIVADO = ADT.ID_ADITIVADO                    "
				+ "   LEFT JOIN TIPO_ADITIVO       AS TAD                          "
				+ "      ON TAD.ID_TIPO_ADITIVO = APR.ID_TIPO_ADITIVO              "
				+ "   LEFT JOIN PRODUTO            AS PRO                          "
				+ "      ON PRO.ID_PRODUTO = APR.ID_PRODUTO                        "
				+ "   LEFT JOIN TIPO_PRODUTO       AS TPR                          "
				+ "      ON TPR.ID_TIPO_PRODUTO = APR.ID_TIPO_PROTUDO              "
				+ "WHERE ADT.ID_ADITIVADO    = ?                                   "
				+ "  AND APR.ID_PRODUTO      = ?                                   "
				+ "  AND APR.ID_TIPO_ADITIVO = ?                                   ";

		ModelListAditivoProduto ListAditivoProduto = new ModelListAditivoProduto();
		DAOUtil daoUtil = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, obj.getId_aditivado()    );
			ps.setLong ( 2, obj.getId_produto ()     );
			ps.setLong ( 3, obj.getId_tipo_aditivo() );
			
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {

				ListAditivoProduto.setId_aditivado                ( set.getLong  ("ID_ADITIVADO")                                                    );
				ListAditivoProduto.setDt_criacao                  ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO"))              );
				ListAditivoProduto.setId_status_aditivo           ( set.getLong  ("ID_STATUS_ADITIVO")                                               );
				ListAditivoProduto.setId_contrato                 ( set.getLong  ("ID_CONTRATO")                                                     );
				ListAditivoProduto.setObservacao                  ( set.getString("OBSERVACAO")                                                      );
				ListAditivoProduto.setLogin_cadastro              ( set.getString("LOGIN_CADASTRO")                                                  );
				ListAditivoProduto.setHubspot_aditivo             ( set.getString("ID_HUB_SPOT")                                                     );
				ListAditivoProduto.setId_rascunho                 ( set.getLong  ("ID_RASCUNHO")                                                     );
				ListAditivoProduto.setMotivoRascunho              ( set.getString("MOTIVO_RASCUNHO")                                                 );
				
				valorReal      = Double.valueOf( set.getString("VLR_TOTAL_ADIT") );
				ListAditivoProduto.setVlr_total_adit              ( dinheiro.format(valorReal)                                                       );

				ListAditivoProduto.setId_tipo_aditivo             ( set.getLong  ("ID_TIPO_ADITIVO_CONTRATADO")                                      );
				ListAditivoProduto.setNome_tipo_aditivo_contratado( set.getString("NOME_TIPO_ADITIVO_CONTRATADO")                                    );

				ListAditivoProduto.setId_produto_contratado       ( set.getLong  ("ID_PRODUTO_CONTRATADO")                                           );
				ListAditivoProduto.setNome_prod_contratado        ( set.getString("NOME_PROD_CONTRATADO")                                            );
				
				valorReal      = Double.valueOf( set.getString("VLR_PROD_CAD_CONTRATADO") );
				ListAditivoProduto.setVlr_prod_cad_contratado     ( dinheiro.format(valorReal)                                                       );
				
				ListAditivoProduto.setQty_produto_contratado      ( set.getString("QTY_PRODUTO_CONTRATADO")                                          );
				
				valorReal      = Double.valueOf( set.getString("VALOR_UNITARIO_CONTRATADO") );
				ListAditivoProduto.setValor_unitario_contratado   ( dinheiro.format(valorReal)                                                       );
				
				valorReal      = Double.valueOf( set.getString("VLR_PRODUTO_CONTRATADO") );
				ListAditivoProduto.setVlr_produto_contratado      ( dinheiro.format(valorReal)                                                       );
				
				ListAditivoProduto.setData_produto_contratado     ( daoUtil.FormataDataStringTelaDataTime( set.getString("DATA_PRODUTO_CONTRATADO")) );
				
				ListAditivoProduto.setId_tipo_protudo_contratado  ( set.getLong  ("ID_TIPO_PROTUDO_CONTRATADO")                                      );
				ListAditivoProduto.setDesc_tipo_produto_contratado( set.getString("DESC_TIPO_PRODUTO_CONTRATADO")                                    );
				ListAditivoProduto.setId_moeda                    ( set.getLong  ("ID_MOEDA")                                                        );

				vlrRecuperado = set.getString("VALOR_CONVERTIDO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setValor_convertido            ( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("CUSTO_TOTAL");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setCusto_total                 ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("COTACAO_MOEDA");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setCotacao_moeda               ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListAditivoProduto;
	}
	
	public ModelListAditivoProduto getAditivoServicoContratados( ModelAditivoModal obj ) throws SQLException {
		
		String sql = "			SELECT                                                            "
				   + "			     ADT.ID_ADITIVADO                                             "
				   + "			   , ADT.DT_CRIACAO                                               "
				   + "			   , ADT.ID_STATUS_ADITIVO                                        "
				   + "			   , ADT.ID_CONTRATO                                              "
				   + "			   , ADT.VLR_TOTAL_ADIT                                           "
				   + "             , ADT.ID_HUB_SPOT                                              "
				   + "			   , ADT.OBSERVACAO                                               "
				   + "			   , ADT.LOGIN_CADASTRO                                           "
				   + "			   , ADT.ID_RASCUNHO                                              "
				   + "			   , ADT.MOTIVO_RASCUNHO                                          "
				   
				   + "             , ADT.ID_MOEDA                                                 "
				   + "             , ADT.VALOR_CONVERTIDO                                         "
				   + "             , ADT.CUSTO_TOTAL                                              "
				   + "             , ADT.COTACAO_MOEDA                                            "
				   + "			   , SAD.ID_TIPO_ADITIVO        AS ID_TIPO_ADITIVO_CONTRATADO     "
				   + "			   , TAD.DESC_TIPO_DITIVO       AS NOME_TIPO_ADITIVO_CONTRATADO   "
				   + "			   , SAD.ID_SERVICO_CONTRATADO  AS ID_PRODUTO_CONTRATADO          "
				   + "			   , SCO.DESC_SERVICO           AS NOME_PROD_CONTRATADO           "
				   + "			   , SCO.VLR_SERVICO            AS VLR_PROD_CAD_CONTRATADO        "
				   + "			   , SAD.QTY_SERVICO            AS QTY_PRODUTO_CONTRATADO         "
				   + "			   , SAD.VALOR_UNITARIO         AS VALOR_UNITARIO_CONTRATADO      "
				   + "			   , SAD.VALOR                  AS VLR_PRODUTO_CONTRATADO         "
				   + "			   , SAD.DESC_SERV_CONTRATADO   AS DESC_SERV_PROD_CONTRATADO      "
				   + "			   , SAD.DT_CRIACAO             AS DATA_PRODUTO_CONTRATADO        "
				   + "			   , NULL                       AS ID_TIPO_PROTUDO_CONTRATADO     "
				   + "			   , NULL                       AS DESC_TIPO_PRODUTO_CONTRATADO   "
				   + "			  FROM       ADITIVADO          AS ADT                            "
				   + "			  INNER JOIN SERVICO_ADITIVADO  AS SAD                            "
				   + "			         ON SAD.ID_ADITIVADO        = ADT.ID_ADITIVADO            "
				   + "			  LEFT JOIN TIPO_ADITIVO       AS TAD                             "
				   + "		           ON TAD.ID_TIPO_ADITIVO       = SAD.ID_TIPO_ADITIVO         "
				   + "			 INNER JOIN SERVICO_CONTRATADO AS SCO                             "
				   + "			         ON SCO.ID_SERVICO_CONTRATADO = SAD.ID_SERVICO_CONTRATADO "
				   + "			 WHERE ADT.ID_ADITIVADO          = ?                              "
				   + "			   AND SAD.ID_SERVICO_CONTRATADO = ?                              "
				   + "			   AND SAD.ID_TIPO_ADITIVO       = ?                              ";
		
		
		ModelListAditivoProduto ListAditivoProduto = new ModelListAditivoProduto();
		DAOUtil daoUtil = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, obj.getId_aditivado()          );
			ps.setLong ( 2, obj.getId_servico_contratado() );
			ps.setLong ( 3, obj.getId_tipo_aditivo()       );
			
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {

				ListAditivoProduto.setId_aditivado                ( set.getLong  ("ID_ADITIVADO")                                                    );
				ListAditivoProduto.setDt_criacao                  ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO"))              );
				ListAditivoProduto.setId_status_aditivo           ( set.getLong  ("ID_STATUS_ADITIVO")                                               );
				ListAditivoProduto.setId_contrato                 ( set.getLong  ("ID_CONTRATO")                                                     );
				ListAditivoProduto.setObservacao                  ( set.getString("OBSERVACAO")                                                      );
				ListAditivoProduto.setLogin_cadastro              ( set.getString("LOGIN_CADASTRO")                                                  );
				ListAditivoProduto.setHubspot_aditivo             ( set.getString("ID_HUB_SPOT")                                                     );
				ListAditivoProduto.setId_rascunho                 ( set.getLong  ("ID_RASCUNHO")                                                     );
				ListAditivoProduto.setMotivoRascunho              ( set.getString("MOTIVO_RASCUNHO")                                                 );
				
				valorReal      = Double.valueOf( set.getString("VLR_TOTAL_ADIT") );
				ListAditivoProduto.setVlr_total_adit              ( dinheiro.format(valorReal)                                                       );

				ListAditivoProduto.setId_tipo_aditivo             ( set.getLong  ("ID_TIPO_ADITIVO_CONTRATADO")                                      );
				ListAditivoProduto.setNome_tipo_aditivo_contratado( set.getString("NOME_TIPO_ADITIVO_CONTRATADO")                                    );

				ListAditivoProduto.setId_produto_contratado       ( set.getLong  ("ID_PRODUTO_CONTRATADO")                                           );
				ListAditivoProduto.setNome_prod_contratado        ( set.getString("NOME_PROD_CONTRATADO")                                            );
				
				valorReal      = Double.valueOf( set.getString("VLR_PROD_CAD_CONTRATADO") );
				ListAditivoProduto.setVlr_prod_cad_contratado     ( dinheiro.format(valorReal)                                                       );
				
				ListAditivoProduto.setQty_produto_contratado      ( set.getString("QTY_PRODUTO_CONTRATADO")                                          );
				
				valorReal      = Double.valueOf( set.getString("VALOR_UNITARIO_CONTRATADO") );
				ListAditivoProduto.setValor_unitario_contratado   ( dinheiro.format(valorReal)                                                       );
				
				valorReal      = Double.valueOf( set.getString("VLR_PRODUTO_CONTRATADO") );
				ListAditivoProduto.setVlr_produto_contratado      ( dinheiro.format(valorReal)                                                       );
				
				ListAditivoProduto.setData_produto_contratado     ( daoUtil.FormataDataStringTelaDataTime( set.getString("DATA_PRODUTO_CONTRATADO")) );
				
				ListAditivoProduto.setId_tipo_protudo_contratado  ( set.getLong  ("ID_TIPO_PROTUDO_CONTRATADO")                                      );
				ListAditivoProduto.setDesc_tipo_produto_contratado( set.getString("DESC_TIPO_PRODUTO_CONTRATADO")                                    );
				ListAditivoProduto.setId_moeda                    ( set.getLong  ("ID_MOEDA")                                                        );

				vlrRecuperado = set.getString("VALOR_CONVERTIDO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setValor_convertido            ( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("CUSTO_TOTAL");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setCusto_total                 ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;
				
				vlrRecuperado = set.getString("COTACAO_MOEDA");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   ListAditivoProduto.setCotacao_moeda               ( dinheiro.format(valorReal)                                                       );
				   vlrRecuperado = null;
				} else vlrRecuperado = null;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListAditivoProduto;
	}

	public List<ModelMoeda> selecaoMoedaModalAditivo(  ) throws SQLException{
		
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
	
	public List<ModelHistoricoUpgrade> getListaHistoricoUpgrade( Long idContrato ) throws SQLException{
		
		String sql = "SELECT  HIU.ID_RECURSO                                          "
			       + "      , HIU.ID_CLIENTE                                          "
			       + "      , HIU.ID_CONTRATO                                         "
			       + "      , HIU.ID_ADITIVO                                          "
			       + "      , HIU.DT_CADASTRO                                         "
			       + "      , HIU.LOGIN                                               "
			       + "      , HIU.ID_FAMILIA_FLAVORS_NOVO                             "
			       + "      , FNOVO.FAMILIA  AS FAMILIA_NOVO                          "
			       + "      , HIU.ID_FAMILIA_FLAVORS_VELHO                            "
			       + "      , FANTIGO.FAMILIA AS FAMILIA_ANTIGA                       "
			       + "      , HIU.TAMANHO_DISCO_VELHO                                 "
			       + "      , HIU.TAMANHO_DISCO_NOVO                                  "
			       + "      , HIU.CPU_NOVO                                            "
			       + "      , HIU.CPU_VELHO                                           "
			       + "      , HIU.RAM_NOVO                                            "
			       + "      , HIU.RAM_VELHO                                           "
			       + "      , HIU.VALOR_NOVO                                          "
			       + "      , HIU.VALOR_VELHO                                         "
			       + "      , REC.ID_STATUS_RECURSO                                   "
			       + "      , REC.DT_CADASTRO AS DT_CAD_REC                           "
			       + "      , REC.HOSTNAME                                            "
			       + "      , NUV.MOME_PARCEIRO                                       "
			       + "      , REC.ID_TIPO_DISCO                                       "
			       + "      , REC.ID_SO                                               "
			       + "      , REC.PRIMARY_IP                                          "
			       + "      , REC.ID_AMBIENTE                                         "
			       + "      , REC.ID_FAMILIA_FLAVORS                                  "
			       + "      , FATUAL.FAMILIA AS FAMILIA_ATUAL                         "
			       + "      , REC.ID_NUVEM                                            "
			       + "      , REC.SITE                                                "
			       + "      , CON.ID_STATUS_CONTRATO                                  "
			       + "      , CON.DT_CRIACAO                                          "
			       + "      , CON.PEP                                                 "
			       + "  FROM                                                          "
			       + "     HITORICO_UPGRADE AS HIU                                    "
			       + "   , RECURSO          AS REC                                    "
			       + "   , NUVEM            AS NUV                                    "
			       + "   , CONTRATO         AS CON                                    "
			       + "   , FAMILIA_FLAVORS  AS FNOVO                                  "
			       + "   , FAMILIA_FLAVORS  AS FANTIGO                                "
			       + "   , FAMILIA_FLAVORS  AS FATUAL                                 "
			       + " WHERE HIU.ID_CONTRATO            = ?                           "
			       + "   AND REC.ID_RECURSO             = HIU.ID_RECURSO              "
			       + "   AND NUV.ID_NUVEM               = REC.ID_NUVEM                "
			       + "   AND CON.ID_CONTRATO            = HIU.ID_CONTRATO             "
			       + "   AND FNOVO.ID_FAMILIA_FLAVORS   = HIU.ID_FAMILIA_FLAVORS_NOVO "
			       + "   AND FANTIGO.ID_FAMILIA_FLAVORS = HIU.ID_FAMILIA_FLAVORS_VELHO"
			       + "   AND FATUAL.ID_FAMILIA_FLAVORS  = REC.ID_FAMILIA_FLAVORS      "
			       + "   ORDER BY HIU.ID_RECURSO, REC.HOSTNAME, HIU.DT_CADASTRO       ";

		
		List<ModelHistoricoUpgrade> listaHistoricoUpgrades = new ArrayList<ModelHistoricoUpgrade>();
		DAOUtil daoUtil       = new DAOUtil();
		Locale localeBR       = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		Double valorReal      = 0.0;
		String vlrRecuperado  = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelHistoricoUpgrade historicoUpgrade = new ModelHistoricoUpgrade();
				historicoUpgrade.setId_recurso              ( set.getLong  ("ID_RECURSO"              ) );
				historicoUpgrade.setId_cliente              ( set.getLong  ("ID_CLIENTE"              ) );
				historicoUpgrade.setId_contrato             ( set.getLong  ("ID_CONTRATO"             ) );
				historicoUpgrade.setId_aditivo              ( set.getLong  ("ID_ADITIVO"              ) );
				historicoUpgrade.setDt_cadastro             ( daoUtil.FormataDataStringTelaDataTime(  set.getString("DT_CADASTRO") ) );
				historicoUpgrade.setLogin                   ( set.getString("LOGIN"                   ) );
				historicoUpgrade.setId_familia_flavors_novo ( set.getLong  ("ID_FAMILIA_FLAVORS_NOVO" ) );
				historicoUpgrade.setFamilia_novo            ( set.getString("FAMILIA_NOVO"            ) );
				historicoUpgrade.setId_familia_flavors_velho( set.getLong  ("ID_FAMILIA_FLAVORS_VELHO") );
				historicoUpgrade.setFamilia_antiga          ( set.getString("FAMILIA_ANTIGA"          ) );
				historicoUpgrade.setTamanho_disco_velho     ( set.getString("TAMANHO_DISCO_VELHO"     ) );
				historicoUpgrade.setTamanho_disco_novo      ( set.getString("TAMANHO_DISCO_NOVO"      ) );
				historicoUpgrade.setCpu_novo                ( set.getString("CPU_NOVO"                ) );
				historicoUpgrade.setCpu_velho               ( set.getString("CPU_VELHO"               ) );
				historicoUpgrade.setRam_novo                ( set.getString("RAM_NOVO"                ) );
				historicoUpgrade.setRam_velho               ( set.getString("RAM_VELHO"               ) );
				
				vlrRecuperado = set.getString("VALOR_NOVO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   historicoUpgrade.setValor_novo( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else historicoUpgrade.setValor_novo( "R$ 0,00" );
				
				vlrRecuperado = set.getString("VALOR_VELHO");
				if(vlrRecuperado != null && !vlrRecuperado.isEmpty()) {
				   valorReal      = Double.valueOf( vlrRecuperado );
				   historicoUpgrade.setValor_velho( dinheiro.format(valorReal) );
				   vlrRecuperado = null;
				} else historicoUpgrade.setValor_velho( "R$ 0,00" );
				// historicoUpgrade.setValor_novo              ( set.getString("VALOR_NOVO"              ) );
				// historicoUpgrade.setValor_velho             ( set.getString("VALOR_VELHO"             ) );
				
				historicoUpgrade.setId_status_recurso       ( set.getLong  ("ID_STATUS_RECURSO"       ) );
				historicoUpgrade.setDt_criacao_recurso      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CAD_REC")) );
				historicoUpgrade.setHostname                ( set.getString("HOSTNAME"                ) );
				historicoUpgrade.setNuvem                   ( set.getString("MOME_PARCEIRO"           ) );
				historicoUpgrade.setId_tipo_disco           ( set.getLong  ("ID_TIPO_DISCO"           ) );
				historicoUpgrade.setId_so                   ( set.getLong  ("ID_SO"                   ) );
				historicoUpgrade.setIp                      ( set.getString("PRIMARY_IP"              ) );
				historicoUpgrade.setId_ambiente             ( set.getLong  ("ID_AMBIENTE"             ) );
				historicoUpgrade.setId_familia_flavors      ( set.getLong  ("ID_FAMILIA_FLAVORS"      ) );
				historicoUpgrade.setFamilia_atual           ( set.getString("FAMILIA_ATUAL"           ) );
				historicoUpgrade.setId_nuvem                ( set.getLong  ("ID_NUVEM"                ) );
				historicoUpgrade.setSite                    ( set.getLong  ("SITE"                    ) );
				historicoUpgrade.setId_status_contrato      ( set.getLong  ("ID_STATUS_CONTRATO"      ) );
				historicoUpgrade.setDt_criacao              ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")) );
				historicoUpgrade.setPep                     ( set.getString("PEP"                     ) );

					
				listaHistoricoUpgrades.add(historicoUpgrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaHistoricoUpgrades;
	}
	
	

}
