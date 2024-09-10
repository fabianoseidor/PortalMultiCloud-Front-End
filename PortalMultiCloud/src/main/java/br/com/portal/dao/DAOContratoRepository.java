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
import br.com.portal.model.ModalDescomissionamento;
import br.com.portal.model.ModelComercial;
import br.com.portal.model.ModelContrato;
import br.com.portal.model.ModelContratoProduto;
import br.com.portal.model.ModelDesativacaoContrato;
import br.com.portal.model.ModelListaRecursoContratoAditivo;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelSuporteB1;
import br.com.portal.model.ModelTempoContrato;
import br.com.portal.model.ModelVigenciaContrato;

public class DAOContratoRepository {
	private Connection connection;
	
	public DAOContratoRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	/*
	 * 
	 * Funcao de atualizacao e insersao de um Contrato.
	 * 
	 * */	
	public ModelContrato gravarContrato( ModelContrato objeto ) throws Exception {
		String sql = null;
		if( !objeto.isNovo() ) {
			if( objeto.getId_status_contrato() == 4 ) {
			    sql = "UPDATE CONTRATO "
					+ "   SET  ID_NUVEM          = ? , ID_FASE_CONTRATO    = ?, ID_STATUS_CONTRATO = ?, ID_CLIENTE       = ?, ID_CICLO_UPDATE = ?, ID_SERVICO_CONTRATADO = ?, QTY_USUARIO_CONTRATADA = ? "
					+ "      , ID_SITE           = ? , VALOR_TOTAL         = ?, PEP                = ?, TERMO_ADMIN      = ?, TERMO_DOWNLOAD  = ?, OBSERVACAO            = ?, ID_HUB_SPOT            = ? "
					+ "      , ID_MOEDA          = ? , VALOR_CONVERTIDO    = ?, COTACAO_MOEDA      = ?, ID_SUPORTE_B1    = ?, ID_COMERCIAL    = ?, SETUP                 = ?, VALOR_SETUP            = ? "
					+ "      , QTY_PARCELA_SETUP = ? , VALOR_PARCELA_SETUP = ? , QTY_MESE_SETUP    = ? , MOTIVO_RASCUNHO = ?, ID_RASCUNHO     = ?                                                        "
					+ " WHERE ID_CONTRATO = ?                                                                                                                                                            ";
			}else {
			    sql = "UPDATE CONTRATO "
					+ "   SET  ID_NUVEM          = ? , ID_FASE_CONTRATO    = ?, ID_STATUS_CONTRATO = ?, ID_CLIENTE      = ?, ID_CICLO_UPDATE = ?, ID_SERVICO_CONTRATADO = ?, QTY_USUARIO_CONTRATADA = ? "
					+ "      , ID_SITE           = ? , VALOR_TOTAL         = ?, PEP                = ?, TERMO_ADMIN     = ?, TERMO_DOWNLOAD  = ?, OBSERVACAO            = ?, ID_HUB_SPOT            = ? "
					+ "      , ID_MOEDA          = ? , VALOR_CONVERTIDO    = ?, COTACAO_MOEDA      = ?, ID_SUPORTE_B1   = ?, ID_COMERCIAL    = ?, SETUP                 = ? , VALOR_SETUP           = ? "
					+ "      , QTY_PARCELA_SETUP = ? , VALOR_PARCELA_SETUP = ? , QTY_MESE_SETUP    = ?                                                                                                  "
					+ " WHERE ID_CONTRATO = ?                                                                                                                                                           ";
				
			}

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_nuvem()              );
			prepareSql.setLong     ( 2, objeto.getId_fase_contrato()      );
			prepareSql.setLong     ( 3, objeto.getId_status_contrato()    );
			prepareSql.setLong     ( 4, objeto.getId_cliente()            );
			prepareSql.setLong     ( 5, objeto.getId_ciclo_update()       );
			prepareSql.setLong     ( 6, objeto.getId_servico_contratado() );
			prepareSql.setString   ( 7, objeto.getQty_usuario_contratada());
			prepareSql.setLong     ( 8, objeto.getId_site()               );
			prepareSql.setString   ( 9, objeto.getValor_total()           );
			prepareSql.setString   (10, objeto.getPep()                   );
			prepareSql.setInt      (11, objeto.getTermo_admin()           );
			prepareSql.setInt      (12, objeto.getTermo_download()        );
			prepareSql.setString   (13, objeto.getObservacao()            );
			prepareSql.setString   (14, objeto.getId_hub_spot()           );
			prepareSql.setLong     (15, objeto.getId_moeda()              );
			prepareSql.setString   (16, objeto.getValor_convertido()      );
			prepareSql.setString   (17, objeto.getCotacao_moeda()         );
			prepareSql.setLong     (18, objeto.getId_suporte_b1()         );
			prepareSql.setLong     (19, objeto.getId_comercial()          );
			prepareSql.setBoolean  (20, objeto.getComissao().equalsIgnoreCase("Sim")? true: false );
			prepareSql.setString   (21, objeto.getValor_setup()           );
			prepareSql.setInt      (22, objeto.getQty_parcela_setup()     );
			prepareSql.setString   (23, objeto.getValor_parcela_setup()   );
			prepareSql.setInt      (24, objeto.getQty_mese_setup()        );
			
			if( objeto.getId_status_contrato() == 4 ) {
			    prepareSql.setString   (25, objeto.getMotivoRascunho()        );
			    prepareSql.setLong     (26, objeto.getId_rascunho()           );
			    prepareSql.setLong     (27, objeto.getId_contrato()           );
			}else {
			    prepareSql.setLong     (25, objeto.getId_contrato()           );				
			}

			prepareSql.executeUpdate();
			connection.commit();
			
			ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato();
			vigenciaContrato.setId_vigencia      ( objeto.getId_vigencia()         );
			vigenciaContrato.setId_contrato      ( objeto.getId_contrato()         );
			vigenciaContrato.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaContrato.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaContrato.setDt_final         ( objeto.getDt_final()            );
			vigenciaContrato.setObservacao       ( objeto.getObservacao_vigencia() );
			
			vigenciaContrato = this.gravarVigenciaContrato( vigenciaContrato );
			objeto.setId_vigencia(vigenciaContrato.getId_vigencia());

			if (objeto.getContratopdf() != null && !objeto.getContratopdf().isEmpty()) {
				sql = "UPDATE CONTRATO set CONTRATOPDF =?, EXTENSAOCONTRATOPDF=?, NOMEAQRPDF=? WHERE ID_CONTRATO =?";
				
				prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString(1, objeto.getContratopdf()         );
				prepareSql.setString(2, objeto.getExtensaocontratopdf() );
				prepareSql.setString(3, objeto.getNomeaqrpdf()           );
				prepareSql.setLong  (4, objeto.getId_contrato()         );
				
				prepareSql.execute();
				
				connection.commit();
			}

		}
		return this.consultaContratoID(objeto.getId_contrato());
	}

	public ModelVigenciaContrato gravarVigenciaContrato( ModelVigenciaContrato objeto ) throws Exception {
		
		DAOUtil daoUtil = new DAOUtil();
		if( objeto.isNovo() ){
			String sql = "INSERT INTO VIGENCIA ( ID_CONTRATO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, OBSERVACAO)"
					+ " VALUES ( ?, ?, ?, ?, ?, ? )";

			PreparedStatement prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong     ( 1, objeto.getId_contrato()                                );
			prepareSql.setLong     ( 2, objeto.getId_tempo_contrato()                          );
			prepareSql.setDate     ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio())        );
			prepareSql.setDate     ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())         );
			prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 6, objeto.getObservacao()                                 );
			
			prepareSql.execute();
			connection.commit();
			
			objeto.setId_vigencia( this.getIdVigencia(objeto) );
			
		}else {
			String sql = "UPDATE VIGENCIA                                                         "
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
		
	public Long getIdVigencia( ModelVigenciaContrato objeto ) throws SQLException {
		DAOUtil daoUtil = new DAOUtil();

		String sql = "SELECT ID_VIGENCIA         "
				+ "  FROM VIGENCIA               "
				+ "  WHERE ID_CONTRATO       = ? "
				+ "    AND ID_TEMPO_CONTRATO = ? "
				+ "    AND DT_INICIO         = ? "
				+ "    AND DT_FINAL          = ? ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, objeto.getId_contrato()                         );
		prepareSql.setLong ( 2, objeto.getId_tempo_contrato()                   );
		prepareSql.setDate ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio()) );
		prepareSql.setDate ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())  );
		ResultSet resutado = prepareSql.executeQuery();

		while (resutado.next())  return  resutado.getLong("ID_VIGENCIA");

		return 0L;		
	}

	public ModelVigenciaContrato getContratoVigencia( Long idContrato ) throws SQLException {
		ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT ID_VIGENCIA, ID_CONTRATO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, DT_DESATIVACAO, OBSERVACAO FROM VIGENCIA WHERE ID_CONTRATO = ? AND DT_DESATIVACAO IS NULL";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1,  idContrato );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			vigenciaContrato.setId_vigencia      ( resutado.getLong("ID_VIGENCIA" )                                             );
			vigenciaContrato.setId_contrato      ( resutado.getLong("ID_CONTRATO" )                                             );
			vigenciaContrato.setId_tempo_contrato(resutado.getLong("ID_TEMPO_CONTRATO")                                         );
			vigenciaContrato.setDt_inicio        ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_INICIO") )         );
			vigenciaContrato.setDt_final         ( daoUtil.FormataDataStringTelaData( resutado.getString("DT_FINAL") )          );
			vigenciaContrato.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") )    );
			vigenciaContrato.setDt_desativacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_DESATIVACAO")) );
			vigenciaContrato.setObservacao       ( resutado.getString("OBSERVACAO")                                             );
		}
		return vigenciaContrato;		
	}
	
	
//		
	public Boolean isExistProduto( Long idContrato, Long idProduto ) throws Exception {

		String sql = "SELECT COUNT(1) AS TOTAL  FROM CONTRATO_PRODUTO WHERE ID_CONTRATO = ? AND ID_PRODUTO  = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idContrato );
		prepareSql.setLong     ( 2, idProduto  );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return ( resutado.getInt("TOTAL")> 0 ? true: false  );

		return false;
   }

	public List<ModelContratoProduto> getListaContratoProduto( Long idContrato ) throws Exception {

		List<ModelContratoProduto> listaContratoProdutos = new ArrayList<ModelContratoProduto>();
		DAOUtil daoUtil = new DAOUtil();
		String sql = "SELECT CP.ID_CONTRATO, CP.ID_PRODUTO, PRO.PRODUTO, CP.DT_CADASTRO, CP.QTY_SERVICO, CP.VALOR_UNITARIO, CP.VALOR, CP.OBSERVACAO "
				   + "FROM                       "
			       + "   CONTRATO_PRODUTO AS CP  "
				   + " , PRODUTO          AS PRO "
				   + "WHERE ID_CONTRATO = ?      "
				   + "  AND PRO.ID_PRODUTO = CP.ID_PRODUTO";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idContrato );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  {

			ModelContratoProduto listaContratoProduto = new ModelContratoProduto();
			listaContratoProduto.setId_contrato   ( resutado.getLong  ("ID_CONTRATO"));
			listaContratoProduto.setId_produto    ( resutado.getLong  ("ID_PRODUTO" ));
			listaContratoProduto.setProduto       ( resutado.getString("PRODUTO"    ));
			listaContratoProduto.setQty_servico   ( resutado.getInt   ("QTY_SERVICO"));
			listaContratoProduto.setObservacao    ( resutado.getString("OBSERVACAO" ));
//			listaContratoProduto.setValor_unitario( resutado.getString("VALOR_UNITARIO"));
//			listaContratoProduto.setValor         ( resutado.getString("VALOR"         ));
			listaContratoProduto.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ));

			Locale localeBR = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorRealUni = Double.valueOf( resutado.getString("VALOR_UNITARIO") ) ;
			Double valorReal    = Double.valueOf( resutado.getString("VALOR"         ) ) ;
			
			listaContratoProduto.setValor_unitario( dinheiro.format(valorRealUni));
			listaContratoProduto.setValor         ( dinheiro.format(valorReal   ));

			listaContratoProdutos.add             ( listaContratoProduto );
		}

		return listaContratoProdutos;
   }

	
	public List<ModelContratoProduto> InsertProduto( ModelContratoProduto objeto ) throws Exception {

		String sql = "INSERT INTO CONTRATO_PRODUTO ( ID_CONTRATO, ID_PRODUTO, DT_CADASTRO, QTY_SERVICO, VALOR_UNITARIO, VALOR ) VALUES ( ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		prepareSql.setLong     ( 1, objeto.getId_contrato()                                );
		prepareSql.setLong     ( 2, objeto.getId_produto()                                 );
		prepareSql.setTimestamp( 3, new java.sql.Timestamp(new java.util.Date().getTime()) );
		prepareSql.setInt      ( 4, objeto.getQty_servico()                                ); 
		prepareSql.setString   ( 5, objeto.getValor_unitario()                             );
		prepareSql.setString   ( 6, objeto.getValor()                                      );

		prepareSql.execute();
		connection.commit();

		return this.getListaContratoProduto( objeto.getId_contrato() );
		
	}

	public String updateProduto( ModelContratoProduto objeto ) {

		String sql = "UPDATE CONTRATO_PRODUTO SET QTY_SERVICO = ?, VALOR_UNITARIO = ?, VALOR = ?, OBSERVACAO = ?"
				   + " WHERE ID_CONTRATO = ? AND ID_PRODUTO  = ?                                                ";
		
		PreparedStatement prepareSql;
		String ERRO = null;
		try {
			prepareSql = connection.prepareStatement(sql);

			prepareSql.setInt      ( 1, objeto.getQty_servico()    ); 
			prepareSql.setString   ( 2, objeto.getValor_unitario() );
			prepareSql.setString   ( 3, objeto.getValor()          );
			prepareSql.setString   ( 4, objeto.getObservacao()     );
			prepareSql.setLong     ( 5, objeto.getId_contrato()    );
			prepareSql.setLong     ( 6, objeto.getId_produto()     );
	
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			ERRO = e.getMessage();
			e.printStackTrace();
			return ERRO;
		}

		return ERRO;
	}

	
	/*
	 * 
	 * Consulta um Contrato pelo ID. 
	 * 
	 * */	
	public ModelContrato consultaContratoID( Long idContrato ) throws SQLException {
		
		ModelContrato modelContrato = new ModelContrato();
		ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato(); 
		DAOUtil daoUtil = new DAOUtil();
		
		String sql = "SELECT * FROM CONTRATO WHERE ID_CONTRATO = "+ idContrato ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
				
		while (resultado.next()) {
			
			modelContrato.setId_contrato           ( resultado.getLong  ("ID_CONTRATO")                                         );
			modelContrato.setId_nuvem              ( resultado.getLong  ("ID_NUVEM")                                            );
			modelContrato.setId_servico_contratado ( resultado.getLong  ("ID_SERVICO_CONTRATADO")                               );
			modelContrato.setId_status_contrato    ( resultado.getLong  ("ID_STATUS_CONTRATO")                                  );
			modelContrato.setId_cliente            ( resultado.getLong  ("ID_CLIENTE")                                          );
			modelContrato.setNomeCliente           ( this.buscaCliente( resultado.getLong("ID_CLIENTE") )                       );			
			modelContrato.setId_ciclo_update       ( resultado.getLong  ("ID_CICLO_UPDATE")                                     );
			modelContrato.setId_fase_contrato      ( resultado.getLong  ("ID_FASE_CONTRATO")                                    );
			modelContrato.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
			modelContrato.setQty_usuario_contratada( resultado.getString("QTY_USUARIO_CONTRATADA")                              );
			modelContrato.setId_site               ( resultado.getLong  ("ID_SITE")                                             );
			modelContrato.setTermo_admin           ( resultado.getInt   ("TERMO_ADMIN")                                         );
			modelContrato.setTermo_download        ( resultado.getInt   ("TERMO_DOWNLOAD")                                      );
			modelContrato.setContratopdf           ( resultado.getString("CONTRATOPDF")                                         );
			modelContrato.setExtensaocontratopdf   ( resultado.getString("EXTENSAOCONTRATOPDF")                                 );
			modelContrato.setNomeaqrpdf            ( resultado.getString("NOMEAQRPDF")                                          );
			modelContrato.setId_rascunho           ( resultado.getLong  ("ID_RASCUNHO")                                         );
			modelContrato.setMotivoRascunho        ( resultado.getString("MOTIVO_RASCUNHO")                                     );
			modelContrato.setId_comercial          ( resultado.getLong  ("ID_COMERCIAL")                                        );
			modelContrato.setId_suporte_b1         ( resultado.getLong  ("ID_SUPORTE_B1")                                       );
			modelContrato.setComissao              ( resultado.getBoolean("SETUP") == true ? "Sim":"Não"                        );
			
			Locale localeBR = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = 0.0;
					
		    String valorSetup = resultado.getString("VALOR_SETUP");				
		    if(valorSetup != null && !valorSetup.isEmpty()) {
		    	valorReal      = Double.valueOf( valorSetup );
			   modelContrato.setValor_setup( dinheiro.format(valorReal) );
	   	    }
			modelContrato.setQty_parcela_setup( resultado.getInt("QTY_PARCELA_SETUP") );
			modelContrato.setQty_mese_setup   ( resultado.getInt("QTY_MESE_SETUP"   ) );
			
			valorSetup = resultado.getString("VALOR_PARCELA_SETUP");				
			if(valorSetup != null && !valorSetup.isEmpty()) {
				valorReal      = Double.valueOf( valorSetup );
				modelContrato.setValor_parcela_setup( dinheiro.format(valorReal) );
			}

			/*********************************************************/
			/* Configura o atributo Valor para demostrar como Real.  */
			/*********************************************************/
		    valorReal = Double.valueOf( resultado.getDouble("VALOR_TOTAL") ) ;
			String vlr = dinheiro.format(valorReal);
			modelContrato.setValor_total( vlr );
			/*********************************************************/
			valorReal = Double.valueOf( resultado.getDouble("VALOR_CONVERTIDO") ) ;
			modelContrato.setValor_convertido(dinheiro.format(valorReal));

			valorReal = Double.valueOf( resultado.getDouble("COTACAO_MOEDA") ) ;
			modelContrato.setCotacao_moeda(dinheiro.format(valorReal));

			modelContrato.setPep                ( resultado.getString("PEP")            );
			modelContrato.setId_hub_spot        ( resultado.getString("ID_HUB_SPOT")    );
			modelContrato.setLogin_cadastro     ( resultado.getString("LOGIN_CADASTRO") );
			modelContrato.setObservacao         ( resultado.getString("OBSERVACAO")     );
			modelContrato.setId_moeda           ( resultado.getLong  ("ID_MOEDA")       );

			vigenciaContrato = this.getContratoVigencia( modelContrato.getId_contrato() );	
			modelContrato.setId_vigencia        (vigenciaContrato.getId_vigencia()       );
			modelContrato.setId_tempo_contrato  (vigenciaContrato.getId_tempo_contrato() );
			modelContrato.setDt_inicio          ( vigenciaContrato.getDt_inicio()        );
			modelContrato.setDt_final           ( vigenciaContrato.getDt_final()         );
			modelContrato.setDt_criacao_vigencia( vigenciaContrato.getDt_criacao()       );
			modelContrato.setObservacao_vigencia( vigenciaContrato.getObservacao()       );
			
		}		
		return modelContrato;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public String buscaCliente( Long idCliente ) throws SQLException {
		
		String sql = "SELECT RAZAO_SOCIAL FROM CLIENTE WHERE ID_CLIENTE = " + idCliente;
		PreparedStatement statemet = connection.prepareStatement(sql);
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next())  return resutado.getString("RAZAO_SOCIAL");
		
		return "";
	}	
/*
 * 
 * 
 * 
 * */	
	public Long getMaxIdContrato( Long idCliente ) throws SQLException {
		String sql = "SELECT max( ID_CONTRATO ) AS MAX_ID_CONTRATO FROM CONTRATO WHERE ID_CLIENTE = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1, idCliente );
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("MAX_ID_CONTRATO");
			
		}
		return null;
	}
	
	/*
	 * 
	 * 
	 * 
	 * */	
		public String verificaPep( String pep ) throws SQLException {
			String sql = "SELECT                               "
					+ "     CON.PEP                            "
					+ "   , CON.ID_CONTRATO                    "
					+ "   , CLI.RAZAO_SOCIAL                   "
					+ "  FROM                                  "
					+ "     CONTRATO AS CON                    "
					+ "   , CLIENTE AS CLI                     "
					+ "   WHERE PEP = ?                        "
					+ "     AND CLI.ID_CLIENTE = CON.ID_CLIENTE";
						
			PreparedStatement statemet = connection.prepareStatement(sql);
			statemet.setString(1, pep); 
			ResultSet resutado = statemet.executeQuery();
			
//			while (resutado.next()) return "Este PEP " + resutado.getString("PEP") + " já existe na base e esta associando ao Cliente " + resutado.getString("RAZAO_SOCIAL") + ".";
			while (resutado.next()) return resutado.getString("PEP") + ";" + resutado.getString("ID_CONTRATO") + ";" +resutado.getString("RAZAO_SOCIAL");
			
			return "OK";
		}
	/*
	 * 
	 * 
	 * 
	 * */	
	public Boolean isExstContratoAtivo( Long idContrato ) throws SQLException {
		String sql = "SELECT COUNT( ID_CONTRATO ) AS EXIST FROM CONTRATO WHERE ID_CLIENTE = ? AND ID_STATUS_CONTRATO IN ( 1, 4, 5 )";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idContrato);
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return ( resutado.getLong("EXIST") > 0 ? true : false);

		return false;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public ModelContrato getContratoAtivoCliente( Long idCliente ) throws SQLException {
		ModelContrato modelContrato = new ModelContrato();
		String nomeClienteBD = "";
		
	    if(this.isExstContratoAtivo(idCliente)) {
		
			ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato(); 
			DAOUtil daoUtil       = new DAOUtil();
			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = 0.0;			
			String sql = "SELECT * FROM CONTRATO WHERE ID_CLIENTE = ? AND ID_STATUS_CONTRATO IN ( 1, 4, 5 )";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong     ( 1, idCliente);
			ResultSet resultado = statement.executeQuery();
					
			while (resultado.next()) {
				
				modelContrato.setId_contrato           ( resultado.getLong  ( "ID_CONTRATO"                               ) );
				modelContrato.setId_nuvem              ( resultado.getLong  ( "ID_NUVEM"                                  ) );
				modelContrato.setId_servico_contratado ( resultado.getLong  ( "ID_SERVICO_CONTRATADO"                     ) );
				modelContrato.setId_status_contrato    ( resultado.getLong  ( "ID_STATUS_CONTRATO"                        ) );
				modelContrato.setId_cliente            ( resultado.getLong  ( "ID_CLIENTE"                                ) );
				
				nomeClienteBD = Normalizer.normalize( this.buscaCliente( resultado.getLong("ID_CLIENTE")), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
				 modelContrato.setNomeCliente           ( nomeClienteBD );
				// modelContrato.setNomeCliente           ( this.buscaCliente  ( resultado.getLong("ID_CLIENTE")             ) );
				
				
				modelContrato.setId_ciclo_update       ( resultado.getLong  ( "ID_CICLO_UPDATE"                           ) );
				modelContrato.setId_fase_contrato      ( resultado.getLong  ( "ID_FASE_CONTRATO"                          ) );
				modelContrato.setDt_criacao            ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
				modelContrato.setQty_usuario_contratada( resultado.getString( "QTY_USUARIO_CONTRATADA"                    ) );
				modelContrato.setId_site               ( resultado.getLong  ( "ID_SITE"                                   ) );
				modelContrato.setTermo_admin           ( resultado.getInt   ("TERMO_ADMIN"                                ) );
				modelContrato.setTermo_download        ( resultado.getInt   ("TERMO_DOWNLOAD"                             ) );
				modelContrato.setContratopdf           ( resultado.getString("CONTRATOPDF")                                 );
				modelContrato.setExtensaocontratopdf   ( resultado.getString("EXTENSAOCONTRATOPDF")                         );
				modelContrato.setNomeaqrpdf            ( resultado.getString("NOMEAQRPDF")                                  );
				
				modelContrato.setId_rascunho           ( resultado.getLong  ("ID_RASCUNHO")                                  );
				modelContrato.setMotivoRascunho        ( resultado.getString("MOTIVO_RASCUNHO")                              );

				modelContrato.setId_comercial          ( resultado.getLong  ("ID_COMERCIAL")                                 );
				modelContrato.setId_suporte_b1         ( resultado.getLong  ("ID_SUPORTE_B1")                                );
				
				/*********************************************************/
				/* Configura o atributo Valor para demostrar como Real.  */
				/*********************************************************/
				valorReal = Double.valueOf( resultado.getDouble("VALOR_TOTAL") ) ;
				modelContrato.setValor_total( dinheiro.format(valorReal) );
				/*********************************************************/
				modelContrato.setPep                   ( resultado.getString( "PEP"                                       ) );
				modelContrato.setId_hub_spot           ( resultado.getString( "ID_HUB_SPOT"                               ) );
				modelContrato.setObservacao            ( resultado.getString( "OBSERVACAO"                                ) );
				modelContrato.setLogin_cadastro        ( resultado.getString("login_cadastro"                             ) );
				modelContrato.setId_moeda              ( resultado.getLong   ( "ID_MOEDA"                                 ) );
				
				valorReal = Double.valueOf( resultado.getDouble("VALOR_CONVERTIDO") ) ;
				modelContrato.setValor_convertido(dinheiro.format(valorReal));
                
				valorReal = Double.valueOf( resultado.getDouble("CUSTO_TOTAL") ) ;
				modelContrato.setCusto_total(dinheiro.format(valorReal));
				
				valorReal = Double.valueOf( resultado.getDouble("COTACAO_MOEDA") ) ;
				modelContrato.setCotacao_moeda(dinheiro.format(valorReal));

				modelContrato.setComissao(resultado.getBoolean("SETUP") == true? "Sim" : "Não");
				
				String valorSetup = resultado.getString("VALOR_SETUP");				
				if(valorSetup != null && !valorSetup.isEmpty()) {
					valorReal      = Double.valueOf( valorSetup );
					modelContrato.setValor_setup( dinheiro.format(valorReal) );
					valorSetup = null;
				}else valorSetup = null;
								
				modelContrato.setQty_parcela_setup( resultado.getInt("QTY_PARCELA_SETUP") );
				modelContrato.setQty_mese_setup   ( resultado.getInt("QTY_MESE_SETUP"   ) );
				
				valorSetup = resultado.getString("VALOR_PARCELA_SETUP");				
				if(valorSetup != null && !valorSetup.isEmpty()) {
					valorReal      = Double.valueOf( valorSetup );
					modelContrato.setValor_parcela_setup( dinheiro.format(valorReal) );
				}

			}
			
			vigenciaContrato = this.getContratoVigencia(modelContrato.getId_contrato());
			
			modelContrato.setId_vigencia(vigenciaContrato.getId_vigencia());
			modelContrato.setId_tempo_contrato  ( vigenciaContrato.getId_tempo_contrato() );
			modelContrato.setDt_inicio          ( vigenciaContrato.getDt_inicio()         );
			modelContrato.setDt_final           ( vigenciaContrato.getDt_final()          );
			modelContrato.setDt_criacao_vigencia( vigenciaContrato.getDt_criacao()        );
			modelContrato.setObservacao_vigencia( vigenciaContrato.getObservacao()        );
	    }
		return modelContrato;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public Long getSiteCliente( Long idCliente ) throws SQLException {
		String sql = "SELECT [ID_SITE] FROM CONTRATO WHERE [ID_CLIENTE] = ? AND [ID_STATUS_CONTRATO] in (1, 4)";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idCliente);
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) return resutado.getLong("ID_SITE");

		return 0L;
	}

	/*
	 * 
	 * 
	 * 
	 * */	
	public Long getNuvemRecuso( Long idRecurso ) throws SQLException {
		String sql = "SELECT REC.ID_RECURSO, FF.ID_FAMILIA_FLAVORS, FF.ID_NUVEM"
  				   + "  FROM                                                   "
				   + "     RECURSO AS REC                                      "
			  	   + "   , FAMILIA_FLAVORS AS FF                               "
				   + "WHERE ID_RECURSO = ?                                     "
				   + "  AND FF.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS     ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idRecurso);

		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("ID_NUVEM");
			
		}
		return 0L;
	}

	/*
	 * 
	 * 
	 * 
	 * */	
	public List<ModelListaRecursoContratoAditivo> getListaRecursoContrato( Long idContrato ) throws SQLException {
		
		List<ModelListaRecursoContratoAditivo> listaRecursoContratos = new ArrayList<ModelListaRecursoContratoAditivo>();
		DAOUtil daoUtil       = new DAOUtil();
//		Locale localeBR       = new Locale("pt","BR");
//		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
//		Double valorReal      = 0.0;
		
		String sql = "SELECT                                             "
				+ "        REC.ID_RECURSO                                "
				+ "      , SR.STATUS_RECURSO                             "
				+ "      , REC.ID_RETENCAO_BACKUP                        "
				+ "      , RB.RETENCAO_BACKUP                            "
				+ "      , REC.ID_TIPO_DISCO                             "
				+ "      , TD.TIPO_DISCO                                 "
				+ "      , REC.ID_STATUS_RECURSO                         "
				+ "      , SO.SISTEMA_OPERACIONAL                        "
				+ "      , REC.ID_AMBIENTE                               "
				+ "      , AMB.AMBIENTE                                  "
				+ "      , FF.FAMILIA                                    "
				+ "      , REC.ID_TIPO_SERVICO                           "
				+ "      , TS.TIPO_SERVICO                               " 
				+ "      , REC.DT_CADASTRO                               " 
				+ "      , REC.HOSTNAME                                  "
				+ "      , REC.TAMANHO_DISCO                             "
				+ "      , REC.PRIMARY_IP                                "
				+ "      , REC.TAG_VCENTER                               "
				+ "      , REC.EIP_VCENTER                               "
				+ "      , REC.HOST_VCENTER                              "
				+ "      , REC.VALOR_RECURSO                             "
				+ "      , REC.OBS                                       "
				+ "      , REC.ID_CONTRATO                               "
				+ "      , REC.ID_NUVEM                                  "
				+ "      , REC.SITE                                      "
				+ "      , REC.ID_FAMILIA_FLAVORS                        "
				+ "      , REC.ID_ADITIVADO                              "
				+ "      , CON.LOGIN_CADASTRO                            "
				+ "      , CASE WHEN REC.ID_ADITIVADO IS NULL THEN 'NÃO' "
				+ "             ELSE 'SIM' END AS ISADITIVO              "
				+ "      , CLI.RAZAO_SOCIAL                              "
				+ "      , STC.STATUS_CONTRATO                           "
				+ "      , NUV.MOME_PARCEIRO                             "
				+ "      , SIT.NOME                                      " 
				+ "  FROM                                                "
				+ "    RECURSO             AS REC                        "
				+ "  , STATUS_RECURSO      AS SR                         "
				+ "  , RETENCAO_BACKUP     AS RB                         "
				+ "  , TIPO_DISCO          AS TD                         "
				+ "  , SISTEMA_OPERACIONAL AS SO                         "
				+ "  , AMBIENTE            AS AMB                        "
				+ "  , FAMILIA_FLAVORS     AS FF                         "
				+ "  , TIPO_SERVICO        AS TS                         "
				+ "  , CONTRATO            AS CON                        "
				+ "  , STATUS_CONTRATO     AS STC                        "
				+ "  , CLIENTE             AS CLI                        "
				+ "  , NUVEM               AS NUV                        "
				+ "  , SITE                AS SIT                        "				
				+ "WHERE CON.ID_CLIENTE         = ?                      "
				+ "  AND CON.ID_STATUS_CONTRATO IN ( 1, 4, 5 )           "
				+ "  AND REC.ID_CONTRATO        = CON.ID_CONTRATO        "				
				+ "  AND SR.ID_STATUS_RECURSO   = REC.ID_STATUS_RECURSO  "
				+ "  AND RB.ID_RETENCAO_BACKUP  = REC.ID_RETENCAO_BACKUP "
				+ "  AND TD.ID_TIPO_DISCO       = REC.ID_TIPO_DISCO      "
				+ "  AND SO.ID_SO               = REC.ID_SO              "
				+ "  AND AMB.ID_AMBIENTE        = REC.ID_AMBIENTE        "
				+ "  AND FF.ID_FAMILIA_FLAVORS  = REC.ID_FAMILIA_FLAVORS "
				+ "  AND TS.ID_TIPO_SERVICO     = REC.ID_TIPO_SERVICO    "
				+ "  AND REC.ID_ADITIVADO       IS NULL                  "
				+ "  AND CLI.ID_CLIENTE         = CON.ID_CLIENTE         "
				+ "  AND STC.ID_STATUS_CONTRATO = CON.ID_STATUS_CONTRATO "
				+ "  AND NUV.ID_NUVEM           = REC.ID_NUVEM           "
				+ "  AND SIT.ID_SITE            = REC.SITE               " ;
		

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
			listaRecursoContrato.setId_aditivado       ( resutado.getLong  ("ID_ADITIVADO"       ) );  // 16
			listaRecursoContrato.setIsaditivo          ( resutado.getString("ISADITIVO"          ) );  // 17
			listaRecursoContrato.setLogin_cadastro     ( resutado.getString("LOGIN_CADASTRO"     ) );
			listaRecursoContrato.setRazao_social       ( resutado.getString("RAZAO_SOCIAL"       ) );
			listaRecursoContrato.setStatus_contrato    ( resutado.getString("STATUS_CONTRATO"    ) );
			listaRecursoContrato.setMome_parceiro      ( resutado.getString("MOME_PARCEIRO"      ) );
			listaRecursoContrato.setNome_site          ( resutado.getString("NOME"               ) );
			listaRecursoContrato.setId_retencao_backup ( resutado.getLong  ("ID_RETENCAO_BACKUP" ) );
			listaRecursoContrato.setId_tipo_disco      ( resutado.getLong  ("ID_TIPO_DISCO"      ) );
			listaRecursoContrato.setId_so              ( resutado.getLong  ("ID_STATUS_RECURSO"  ) );
			listaRecursoContrato.setId_ambiente        ( resutado.getLong  ("ID_AMBIENTE"        ) );
			listaRecursoContrato.setId_tipo_servico    ( resutado.getLong  ("ID_TIPO_SERVICO"    ) );
			listaRecursoContrato.setId_nuvem           ( resutado.getLong  ("ID_NUVEM"           ) );
			listaRecursoContrato.setId_site            ( resutado.getLong  ("SITE"               ) );
			listaRecursoContrato.setId_familia         ( resutado.getLong  ("ID_FAMILIA_FLAVORS" ) );
			listaRecursoContrato.setEip_Vcenter        ( resutado.getString("EIP_VCENTER"        ) );
			listaRecursoContrato.setHost_Vcenter       ( resutado.getString("HOST_VCENTER"       ) );
			
			
			
			
//			 valorReal = Double.valueOf                ( resutado.getString("VALOR_RECURSO"      ) );
//			 listaRecursoContrato.setValor_recurso     ( dinheiro.format(valorReal)                );

//			System.out.println(listaRecursoContrato);
			listaRecursoContratos.add(listaRecursoContrato);
			
		}
		return listaRecursoContratos;
	}

	/*
	 * 
	 * 
	 * 
	 * */	
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

	public String getContratoID( Long id ) throws SQLException {
		
        String resultado = "NAOEXISTE";
		String sql = "SELECT ID_CONTRATO,  CLI.ID_CLIENTE,  CLI.RAZAO_SOCIAL "
				+ "  FROM                                                    "
				+ "     CONTRATO AS CON                                      "
				+ "   , CLIENTE  AS CLI                                      "
				+ "  WHERE ID_CONTRATO = ?                                   "
				+ "  AND CLI.ID_CLIENTE = CON.ID_CLIENTE                     " ;
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong ( 1, id );
		ResultSet         resutado   = prepareSql.executeQuery();
		
		if (resutado.next()) 			
			resultado = resutado.getString  ("ID_CONTRATO") + ";" + resutado.getString  ("ID_CLIENTE"); 
					
		return resultado;
	}

	public String getQtyRecurso( Long idContrato ) throws SQLException {
		String sql = " SELECT                                           "
				   + " CASE WHEN COUNT ( ID_CONTRATO )  IS NULL  THEN 0 "
				   + "            ELSE COUNT (ID_CONTRATO )             "
				   + "        END AS CONTRATO,                          "
				   + " CASE WHEN                                        "
				   + "        COUNT  ( ID_ADITIVADO ) IS NULL  THEN 0   "
				   + "            ELSE COUNT ( ID_ADITIVADO )           "
				   + "        END AS ADITIVO                            "
				   + "  FROM  RECURSO                                   "
				   + "  WHERE ID_CONTRATO = ?                           ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) 
			return resutado.getString("CONTRATO") + ";" + resutado.getString("ADITIVO");
			
		return null;
	}	

	public String getStatusContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT CON.ID_CONTRATO, CON.ID_STATUS_CONTRATO, SCO.STATUS_CONTRATO "
				+ "  FROM                                                                 "
				+ "     CONTRATO        AS CON                                            "
				+ "   , STATUS_CONTRATO AS SCO                                            "
				+ "  WHERE ID_CONTRATO = ?                                                "
				+ "    AND SCO.ID_STATUS_CONTRATO = CON.ID_STATUS_CONTRATO                ";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) 
			return resutado.getString("ID_STATUS_CONTRATO") + ";" + resutado.getString("STATUS_CONTRATO");
			
		return null;
	}	
	
	
	public String atualizaStatusDescomissionamento(ModalDescomissionamento mDescomissionamento ) throws SQLException {
		
			String sql = "INSERT INTO DESCOMISSIONAMENTO                    "
					   + "           ( ID_CONTRATO                          "
					   + "           , ID_CLIENTE                           "
					   + "           , ID_STATUS_CONTRATO                   "
					   + "           , MOTIVO_DESCOMISSIONAMENTO          ) "
					   + " VALUES ( ?, ?, ?, ? )";

			PreparedStatement prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong     ( 1, mDescomissionamento.getId_contrato()               );
			prepareSql.setLong     ( 2, mDescomissionamento.getId_cliente()                );
			prepareSql.setLong     ( 3, mDescomissionamento.getId_status_contrato()        );
			prepareSql.setString   ( 4, mDescomissionamento.getMotivo_descomissionamento() ); 

			prepareSql.execute();
			connection.commit();
		return "Sucesso";
	}
	
	
	
	
	
	
	public String CancelaContrato( ModelDesativacaoContrato modelDesativacaoContrato, int tipoCancelamento ) throws SQLException {
		//fa
		String retornoErro     = null;
		String sqlRecurso      = "UPDATE RECURSO          SET ID_STATUS_RECURSO  = 2, USER_DESATIVACAO = ?                WHERE ID_CONTRATO  = ?";
		String sqlContrato     = "UPDATE CONTRATO         SET ID_STATUS_CONTRATO = 2                                      WHERE ID_CONTRATO  = ?";
		String sqlAditivo      = "UPDATE ADITIVADO        SET ID_STATUS_ADITIVO  = 2                                      WHERE ID_CONTRATO  = ?";
		String sqlVigencia     = "UPDATE VIGENCIA         SET DT_DESATIVACAO     = ?, USER_DESATIVACAO = ?, RENOVACAO = ? WHERE ID_CONTRATO  = ?";
		String sqlVigenciaAdt  = "UPDATE VIGENCIA_ADITIVO SET DT_DESATIVACAO     = ?, USER_DESATIVACAO = ?                WHERE ID_ADITIVADO = ?";

		Timestamp dtDes        = new java.sql.Timestamp(new java.util.Date().getTime());
		String dataDesativacao =  dtDes.toString().substring(0, 19);
		
	    try (
	        PreparedStatement updateContrato    = connection.prepareStatement( sqlContrato    );
	    	PreparedStatement updateRecurso     = connection.prepareStatement( sqlRecurso     );
			PreparedStatement updateAditivo     = connection.prepareStatement( sqlAditivo     );
			PreparedStatement updateVigencia    = connection.prepareStatement( sqlVigencia    );){
	    	
	    	connection.setAutoCommit(false);
		    updateRecurso.setString    ( 1, modelDesativacaoContrato.getUser_desativacao() );
		    updateRecurso.setLong      ( 2, modelDesativacaoContrato.getId_contrato()      );
		    updateRecurso.executeUpdate();

		    updateContrato.setLong     ( 1, modelDesativacaoContrato.getId_contrato()      );
		    updateContrato.executeUpdate();
		        
		    updateAditivo.setLong      ( 1, modelDesativacaoContrato.getId_contrato()      );
		    updateAditivo.executeUpdate();
		      
		    updateVigencia.setString    ( 1, dataDesativacao                                );
		    updateVigencia.setString    ( 2, modelDesativacaoContrato.getUser_desativacao() );
		    updateVigencia.setInt       ( 3, tipoCancelamento                               );
		    updateVigencia.setLong      ( 4, modelDesativacaoContrato.getId_contrato()      );
		    updateVigencia.executeUpdate();
	    	
		    List<ModelDesativacaoContrato> aditivoDesativacoes = listaAditicoCancelar( modelDesativacaoContrato.getId_contrato() );
		    
		    for (ModelDesativacaoContrato aditivoDesativacao : aditivoDesativacoes) {
			    PreparedStatement updateVigenciaAdt = connection.prepareStatement( sqlVigenciaAdt );
			    updateVigenciaAdt.setString( 1, dataDesativacao                                );
			    updateVigenciaAdt.setString( 2, modelDesativacaoContrato.getUser_desativacao() );
			    updateVigenciaAdt.setLong  ( 3, aditivoDesativacao.getId_aditivado()     );
			    updateVigenciaAdt.executeUpdate();
			}
		    
		    connection.commit();
		    return retornoErro;
		}catch (SQLException e) {
			retornoErro = "Erro: Cancemento de contrato: " + e.getErrorCode() + e.getMessage();
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

	
	public List<ModelDesativacaoContrato> listaAditicoCancelar( Long idContrato ) throws SQLException {
		List<ModelDesativacaoContrato> aditivoDesativacoes = new  ArrayList<ModelDesativacaoContrato>();

		
		String sqlExistContrato    = "SELECT ID_ADITIVADO FROM ADITIVADO WHERE ID_CONTRATO = ?";
		PreparedStatement statemet = connection.prepareStatement(sqlExistContrato);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			ModelDesativacaoContrato aditivoDesativacao = new  ModelDesativacaoContrato();
			aditivoDesativacao.setId_aditivado(resutado.getLong( "ID_ADITIVADO" ) );
			aditivoDesativacoes.add(aditivoDesativacao);
		}
		
		return aditivoDesativacoes;
	}
	
	public List<ModelComercial> selecaoComercial(  ) throws SQLException{
		
		String sql = "SELECT * FROM COMERCIAL";

		List<ModelComercial> listaComerciais = new ArrayList<ModelComercial>();
			try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelComercial listaComercial = new ModelComercial();
				
				listaComercial.setId_comercial  ( set.getLong  ("ID_COMERCIAL"  ));
				listaComercial.setNome_comercial( set.getString("NOME_COMERCIAL"));
				listaComerciais.add(listaComercial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaComerciais;
	}

	public List<ModelSuporteB1> selecaoSuporteB1(  ) throws SQLException{
		
		String sql = "SELECT * FROM SUPORTE_B1";

		List<ModelSuporteB1> listSuporteB1s = new ArrayList<ModelSuporteB1>();
			try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSuporteB1 listSuporteB1 = new ModelSuporteB1();
				listSuporteB1.setId_suporte_b1(set.getLong  ("ID_SUPORTE_B1"  ));
				listSuporteB1.setNome_suporte(set.getString("NOME_SUPORTE"));
				listSuporteB1s.add(listSuporteB1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSuporteB1s;
	}


/*
	public List<ModelCliente> buscarListaCliente( Integer offsetBegin, Integer offsetEnd ) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
	    
	    Integer totalPag = getTotalPag( offsetEnd );

//		String sql = "SELECT * FROM CLIENTE ORDER BY ID_CLIENTE OFFSET " + offsetBegin + " ROWS FETCH NEXT " + offsetEnd + " ROWS ONLY";
		String sql = "SELECT CLI.*                                                            "
				+ "    FROM                                                                   "
				+ "       CLIENTE  AS CLI                                                     "
				+ "   ORDER BY CLI.ID_CLIENTE                                                 "
				+ "   OFFSET " + offsetBegin + " ROWS FETCH NEXT " + offsetEnd + "  ROWS ONLY ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente(resultado.getLong           ("id_cliente")                                  );
			cliente.setId_porte_emp(resultado.getLong         ("id_porte_emp")                                );
			cliente.setId_status_emp(resultado.getLong        ("id_status_emp")                               );
			cliente.setRazao_social(resultado.getString       ("razao_social")                                );
			cliente.setNome_fantasia(resultado.getString      ("nome_fantasia")                               );
			cliente.setSite(resultado.getString               ("site")                                        );
			cliente.setCep(resultado.getString                ("cep")                                         );
			cliente.setEndereco(resultado.getString           ("endereco")                                    );
			cliente.setBairro(resultado.getString             ("bairro")                                      );
			cliente.setNumero(resultado.getString             ("numero")                                      );
			cliente.setComplemento(resultado.getString        ("complemento")                                 );
			cliente.setCidade(resultado.getString             ("cidade")                                      );
			cliente.setEstado(resultado.getString             ("estado")                                      );
			cliente.setPais(resultado.getString               ("pais")                                        );
			cliente.setCnpj(resultado.getString               ("cnpj")                                        );
			cliente.setInscricao_estadual(resultado.getString ("inscricao_estadual")                          );
			cliente.setInscricao_municipal(resultado.getString("inscricao_municipal")                         );
			cliente.setNicho_mercado(resultado.getString      ("nicho_mercado")                               );
			cliente.setObservacao(resultado.getString("observacao")                                           );
			cliente.setLogin_cadastro( resultado.getString("login_cadastro")                                  );
			cliente.setAlias(resultado.getString("alias")                                                     );
			cliente.setTotalPagCli(totalPag);
			Clientes.add(cliente);
		}
		
		return Clientes;
	}
	
	public Integer getTotalPag( Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(CLI.ID_CLIENTE) AS TOTAL_CLIENTE FROM CLIENTE AS CLI, CONTRATO AS CON WHERE CON.ID_CLIENTE = CLI.ID_CLIENTE";
		Double totalClientes = 0.0;
		Double pagina        = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) totalClientes = resutado.getDouble("TOTAL_CLIENTE");
		
		pagina = totalClientes / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}
*/
}
