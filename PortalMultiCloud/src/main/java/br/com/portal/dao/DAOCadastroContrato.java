package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelCliente;
import br.com.portal.model.ModelContrato;
import br.com.portal.model.ModelContratoProduto;
import br.com.portal.model.ModelPepSap;
import br.com.portal.model.ModelRecursoContrato;
import br.com.portal.model.ModelVigenciaContrato;

public class DAOCadastroContrato {
	private Connection connection;
	
	public DAOCadastroContrato( ) {
		connection = SingleConnectionBanco.getConnection();
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public String MontagravacaoContrato( ModelContrato dadosContrato, List<ModelRecursoContrato> listRecursos, List<ModelContratoProduto> listProdutoss )  {
		String returnProcessamento = null;
		if( dadosContrato.getId_status_contrato() == 4L ) returnProcessamento = insertContratoRascunho( dadosContrato );
		else  returnProcessamento = insertContrato( dadosContrato );
		
		if( returnProcessamento == null ) {
			returnProcessamento = insertListaRecurso( listRecursos, dadosContrato.getId_contrato(), dadosContrato.getLogin_cadastro() );
			if( returnProcessamento == null ) {
				return insertListaProduto( listProdutoss, dadosContrato.getId_contrato(), dadosContrato.getLogin_cadastro() );
			}
		}
		return null;
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public String insertListaProduto( List<ModelContratoProduto> listProdutoss, Long idContrato, String loginUser ) {
		String returnProcessamento = null;
		if(listProdutoss != null ) {
	        for( int i = 0; i < listProdutoss.size(); i++ ) {
	        	listProdutoss.get(i).setId_contrato(idContrato);
	            returnProcessamento = insertProduto( listProdutoss.get(i), loginUser );
	        	if( returnProcessamento != null ) return returnProcessamento;
	        }
		}
		return returnProcessamento;
	}
	
	public String insertProduto( ModelContratoProduto objeto, String loginUser ) {
		String returnProcessamento = null;
		String sql = "INSERT INTO CONTRATO_PRODUTO ( ID_CONTRATO, ID_PRODUTO, DT_CADASTRO, QTY_SERVICO, VALOR_UNITARIO, VALOR, OBSERVACAO ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
		
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_contrato()                                );
			prepareSql.setLong     ( 2, objeto.getId_produto()                                 );
			prepareSql.setTimestamp( 3, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setInt      ( 4, objeto.getQty_servico()                                ); 
			prepareSql.setString   ( 5, objeto.getValor_unitario()                             );
			prepareSql.setString   ( 6, objeto.getValor()                                      );
			prepareSql.setString   ( 7, objeto.getObservacao()                                 );
	
			prepareSql.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			DAOError daoError = new DAOError();
			String errorContatenado = "ID Produto: " + objeto.getId_contrato() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Cadastro Contrato(Produto)", "73", loginUser, errorContatenado );

		}
		
		return returnProcessamento;
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public String insertListaRecurso( List<ModelRecursoContrato> listRecursos, Long idContrato, String loginUser ) {
		String returnProcessamento = null;
		if( listRecursos != null ) {
	        for( int i = 0; i < listRecursos.size(); i++ ) {
	        	listRecursos.get(i).setId_contrato(idContrato);
	        	returnProcessamento = insertRecurso( listRecursos.get(i), loginUser );
	        	if( returnProcessamento != null ) return returnProcessamento;
	        }
		}
		return returnProcessamento;
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public String insertRecurso( ModelRecursoContrato objeto, String loginUser ) {
		String returnProcessamento = null;
		
		String sql = "INSERT INTO RECURSO ( ID_STATUS_RECURSO               " // 1  - objeto.getId_status_recurso()
				+ "                       , ID_RETENCAO_BACKUP              " // 2  - objeto.getId_retencao_backup()
				+ "                       , ID_TIPO_DISCO                   " // 3  - objeto.getId_tipo_disco()
				+ "                       , ID_SO                           " // 4  - getId_so()
				+ "                       , ID_AMBIENTE                     " // 5  - getId_ambiente()
				+ "                       , ID_FAMILIA_FLAVORS              " // 6  - getId_familia_flavors()
				+ "                       , ID_TIPO_SERVICO                 " // 7  - getId_tipo_servico()
				+ "                       , DT_CADASTRO                     " // 8  - new Date();
				+ "                       , HOSTNAME                        " // 9  - getHostname();
				+ "                       , TAMANHO_DISCO                   " // 10 - getTamanhoDisco();
				+ "                       , PRIMARY_IP                      " // 11 - getPrimaryIP();
				+ "                       , TAG_VCENTER                     " // 12 - getTag_vcenter
				+ "                       , OBS                             " // 13 - getObs
				+ "                       , VALOR_RECURSO                   " // 14 - getValor_recurso
				+ "                       , ID_CONTRATO                     " // 15 - getId_contrato
				+ "                       , ID_NUVEM                        " // 16 - getId_nuvem
				+ "                       , SITE                            " // 17 - getId_site
				+ "                       , EIP_VCENTER                     " // 18 - getEip_vcenter
				+ "                       , HOST_VCENTER                    " // 19 - getHost_vcenter
				+ "                       )                                 " 
				+ "     VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
		try {
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong     ( 1, objeto.getId_status_recurso()                          );
			
			prepareSql.setLong     ( 2, objeto.getId_retencao_backup()                         );
			prepareSql.setLong     ( 3, objeto.getId_tipo_disco()                              );
			prepareSql.setLong     ( 4, objeto.getId_so()                                      );
			prepareSql.setLong     ( 5, objeto.getId_ambiente()                                );
			prepareSql.setLong     ( 6, objeto.getId_familia_flavors()                         );
			prepareSql.setLong     ( 7, objeto.getId_tipo_servico()                            );
			prepareSql.setTimestamp( 8, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 9, objeto.getHostname()                                   );	
			prepareSql.setString   (10, objeto.getTamanhoDisco()                               );	
			prepareSql.setString   (11, objeto.getPrimaryIP()                                  );	
			prepareSql.setString   (12, objeto.getTag_vcenter()                                );	
			prepareSql.setString   (13, objeto.getObs()                                        );	
			prepareSql.setString   (14, objeto.getValor_recurso()                              );	
			prepareSql.setLong     (15, objeto.getId_contrato()                                );
			prepareSql.setLong     (16, objeto.getId_nuvem()                                   );
			prepareSql.setLong     (17, objeto.getId_site()                                    );
			prepareSql.setString   (18, objeto.getEip_vcenter()                                );	
			prepareSql.setString   (19, objeto.getHost_vcenter()                               );	
	
			prepareSql.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			returnProcessamento = "Insert Recurso: " + e.getMessage();
			DAOError daoError = new DAOError();
			String errorContatenado = "Tipo Servico: " + objeto.getId_tipo_servico() + " - Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Cadastro Contrato(Recurso)", "150", loginUser, errorContatenado );
			System.out.println("erro: " + e);
		}
		
		return returnProcessamento;
	}
	
	
	public Long getMaxIdRecurso( ModelRecursoContrato objeto ) throws SQLException {
		String sql = "SELECT MAX( ID_RECURSO ) MAX_ID_RECURSO "
				+ "     FROM RECURSO                          "
				+ "    WHERE ID_CONTRATO     = ?              "
				+ "   AND ID_STATUS_RECURSO  = ?              "
				+ "   AND ID_RETENCAO_BACKUP = ?              "
				+ "   AND ID_TIPO_DISCO      = ?              "
				+ "   AND ID_SO              = ?              "
				+ "   AND ID_AMBIENTE        = ?              "
				+ "   AND ID_FAMILIA_FLAVORS = ?              "
				+ "   AND ID_TIPO_SERVICO    = ?              ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, objeto.getId_contrato()        );
		prepareSql.setLong     ( 2, objeto.getId_status_recurso()  );
		prepareSql.setLong     ( 3, objeto.getId_retencao_backup() );
		prepareSql.setLong     ( 4, objeto.getId_tipo_disco()      );
		prepareSql.setLong     ( 5, objeto.getId_so()              );
		prepareSql.setLong     ( 6, objeto.getId_ambiente()        );
		prepareSql.setLong     ( 7, objeto.getId_familia_flavors() );
		prepareSql.setLong     ( 8, objeto.getId_tipo_servico()    );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_RECURSO");

		return null;
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/	
	public String insertContrato( ModelContrato objeto ) {
		String returnProcessamento = null;
		String sql = "INSERT INTO CONTRATO ( ID_NUVEM              , ID_FASE_CONTRATO, ID_STATUS_CONTRATO, ID_CLIENTE   , ID_CICLO_UPDATE, ID_SERVICO_CONTRATADO, DT_CRIACAO,    "
				+ "                          QTY_USUARIO_CONTRATADA, ID_SITE         , VALOR_TOTAL       , PEP          , TERMO_ADMIN    , TERMO_DOWNLOAD       , OBSERVACAO,    "
				+ "                          ID_HUB_SPOT           , ID_MOEDA        , VALOR_CONVERTIDO  , COTACAO_MOEDA, LOGIN_CADASTRO , ID_SUPORTE_B1        , ID_COMERCIAL ) "
				+ "     VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                                       ";

		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_nuvem()                                   );
			prepareSql.setLong     ( 2, objeto.getId_fase_contrato()                           );
			prepareSql.setLong     ( 3, objeto.getId_status_contrato()                         );
			prepareSql.setLong     ( 4, objeto.getId_cliente()                                 );
			prepareSql.setLong     ( 5, objeto.getId_ciclo_update()                            );
			prepareSql.setLong     ( 6, objeto.getId_servico_contratado()                      );
			prepareSql.setTimestamp( 7, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 8, objeto.getQty_usuario_contratada()                     );
			prepareSql.setLong     ( 9, objeto.getId_site()                                    );
			prepareSql.setString   (10, objeto.getValor_total()                                );
			prepareSql.setString   (11, objeto.getPep()                                        );
			prepareSql.setInt      (12, objeto.getTermo_admin()                                );
			prepareSql.setInt      (13, objeto.getTermo_download()                             );
			prepareSql.setString   (14, objeto.getObservacao()                                 );
			prepareSql.setString   (15, objeto.getId_hub_spot()                                );
			prepareSql.setLong     (16, objeto.getId_moeda()                                   );
			prepareSql.setString   (17, objeto.getValor_convertido()                           );
			prepareSql.setString   (18, objeto.getCotacao_moeda()                              );
			prepareSql.setString   (19, objeto.getLogin_cadastro()                             );
			prepareSql.setLong     (20, objeto.getId_suporte_b1()                              );
			prepareSql.setLong     (21, objeto.getId_comercial()                               );

			prepareSql.execute();
			connection.commit();
			
			objeto.setId_contrato( this.getMaxIdContrato( objeto.getId_cliente() ) );
			ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato();
			vigenciaContrato.setId_vigencia      ( objeto.getId_vigencia()         );
			vigenciaContrato.setId_contrato      ( objeto.getId_contrato()         );
			vigenciaContrato.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaContrato.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaContrato.setDt_final         ( objeto.getDt_final()            );
			vigenciaContrato.setObservacao       ( objeto.getObservacao_vigencia() );
			
			vigenciaContrato = this.gravarVigenciaContrato( vigenciaContrato, objeto.getLogin_cadastro() );
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
			
			if(objeto.isRenovacao() ) {
				updateRenovacaoContratoOrigem( objeto.getId_contrato_origem(), objeto.getId_contrato(), objeto.getLogin_cadastro() );	
			    updateRenovacaoContratoNovo  ( objeto.getId_contrato(), objeto.getId_contrato_origem(), objeto.getLogin_cadastro() );
			    updateRenovacaoContrato( objeto.getId_contrato_origem(), objeto.getLogin_cadastro() );
			    updateRenovacaoContrato( objeto.getId_contrato(), objeto.getLogin_cadastro() );
			}

		} catch (SQLException e) {
			DAOError daoError = new DAOError();
			e.printStackTrace();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Cadastro Contrato", "255", objeto.getLogin_cadastro(), e.getMessage() );
			returnProcessamento = "Insert Contrato: " + e.getMessage();
			System.out.println("erro: " + e);
			
		}        
		return returnProcessamento;
	}

	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/	
	public String insertContratoRascunho( ModelContrato objeto ) {
		String returnProcessamento = null;
		String sql = "INSERT INTO CONTRATO ( ID_NUVEM              , ID_FASE_CONTRATO, ID_STATUS_CONTRATO, ID_CLIENTE   , ID_CICLO_UPDATE, ID_SERVICO_CONTRATADO, DT_CRIACAO , "
				+ "                          QTY_USUARIO_CONTRATADA, ID_SITE         , VALOR_TOTAL       , PEP          , TERMO_ADMIN    , TERMO_DOWNLOAD       , OBSERVACAO , "
				+ "                          ID_HUB_SPOT           , ID_MOEDA        , VALOR_CONVERTIDO  , COTACAO_MOEDA, LOGIN_CADASTRO , MOTIVO_RASCUNHO      , ID_RASCUNHO, "
				+ "                          ID_SUPORTE_B1         , ID_COMERCIAL                                                                                            ) "
				+ "     VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                               ";

		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_nuvem()                                   );
			prepareSql.setLong     ( 2, objeto.getId_fase_contrato()                           );
			prepareSql.setLong     ( 3, objeto.getId_status_contrato()                         );
			prepareSql.setLong     ( 4, objeto.getId_cliente()                                 );
			prepareSql.setLong     ( 5, objeto.getId_ciclo_update()                            );
			prepareSql.setLong     ( 6, objeto.getId_servico_contratado()                      );
			prepareSql.setTimestamp( 7, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 8, objeto.getQty_usuario_contratada()                     );
			prepareSql.setLong     ( 9, objeto.getId_site()                                    );
			prepareSql.setString   (10, objeto.getValor_total()                                );
			prepareSql.setString   (11, objeto.getPep()                                        );
			prepareSql.setInt      (12, objeto.getTermo_admin()                                );
			prepareSql.setInt      (13, objeto.getTermo_download()                             );
			prepareSql.setString   (14, objeto.getObservacao()                                 );
			prepareSql.setString   (15, objeto.getId_hub_spot()                                );
			prepareSql.setLong     (16, objeto.getId_moeda()                                   );
			prepareSql.setString   (17, objeto.getValor_convertido()                           );
			prepareSql.setString   (18, objeto.getCotacao_moeda()                              );
			prepareSql.setString   (19, objeto.getLogin_cadastro()                             );
			prepareSql.setString   (20, objeto.getMotivoRascunho()                             );
			prepareSql.setLong     (21, objeto.getId_rascunho()                                );
			prepareSql.setLong     (22, objeto.getId_suporte_b1()                              );
			prepareSql.setLong     (23, objeto.getId_comercial()                               );

			prepareSql.execute();
			connection.commit();
			
			objeto.setId_contrato( this.getMaxIdContrato( objeto.getId_cliente() ) );
			ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato();
			vigenciaContrato.setId_vigencia      ( objeto.getId_vigencia()         );
			vigenciaContrato.setId_contrato      ( objeto.getId_contrato()         );
			vigenciaContrato.setId_tempo_contrato( objeto.getId_tempo_contrato()   );
			vigenciaContrato.setDt_inicio        ( objeto.getDt_inicio()           );
			vigenciaContrato.setDt_final         ( objeto.getDt_final()            );
			vigenciaContrato.setObservacao       ( objeto.getObservacao_vigencia() );
			
			vigenciaContrato = this.gravarVigenciaContrato( vigenciaContrato, objeto.getLogin_cadastro() );
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
			
			if(objeto.isRenovacao() ) {
				updateRenovacaoContratoOrigem( objeto.getId_contrato_origem(), objeto.getId_contrato(), objeto.getLogin_cadastro() );	
			    updateRenovacaoContratoNovo  ( objeto.getId_contrato(), objeto.getId_contrato_origem(), objeto.getLogin_cadastro() );
			    updateRenovacaoContrato( objeto.getId_contrato_origem(), objeto.getLogin_cadastro() );
			    updateRenovacaoContrato( objeto.getId_contrato(), objeto.getLogin_cadastro() );
			}
			
		} catch (SQLException e) {
			DAOError daoError = new DAOError();
			e.printStackTrace();

			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Cadastro Contrato(Rascunho)", "305", objeto.getLogin_cadastro(), e.getMessage() );
			returnProcessamento = "Insert Contrato: " + e.getMessage();
			System.out.println("erro: " + e);
		}        
		return returnProcessamento;
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public Long getMaxIdContrato( Long idCliente ) throws SQLException {
		String sql = "SELECT max( ID_CONTRATO ) AS MAX_ID_CONTRATO FROM CONTRATO WHERE ID_CLIENTE = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong ( 1, idCliente );
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next())return resutado.getLong("MAX_ID_CONTRATO");
		return null;
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public ModelVigenciaContrato gravarVigenciaContrato( ModelVigenciaContrato objeto, String loginUser ) {
		DAOUtil daoUtil = new DAOUtil();
		String sql = "INSERT INTO VIGENCIA ( ID_CONTRATO, ID_TEMPO_CONTRATO, DT_INICIO, DT_FINAL, DT_CRIACAO, OBSERVACAO)"
				+ " VALUES ( ?, ?, ?, ?, ?, ? )";

		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong     ( 1, objeto.getId_contrato()                                );
			prepareSql.setLong     ( 2, objeto.getId_tempo_contrato()                          );
			prepareSql.setDate     ( 3, daoUtil.FormtGravaDataBD(objeto.getDt_inicio())        );
			prepareSql.setDate     ( 4, daoUtil.FormtGravaDataBD(objeto.getDt_final())         );
			prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 6, objeto.getObservacao()                                 );
			
			prepareSql.execute();
			connection.commit();
			
			objeto.setId_vigencia( this.getIdVigencia(objeto) );
		} catch (SQLException e) {
			e.printStackTrace();
			DAOError daoError = new DAOError();
			String errorContatenado = "Contrato: " + objeto.getId_contrato() + " - Error: " + e.getMessage();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Cadastro Contrato(Vigencia)", "391", loginUser, errorContatenado );

		}
		return objeto;
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
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

	
	public List<ModelCliente> buscarListaClienteNomeCadastro(String dadoCliente) throws SQLException {
		DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
		DAOUtil daoUtil = new DAOUtil();
	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
		String sql = "SELECT *                                                               "
				   + "  FROM CLIENTE       AS CLI                                            "
				   + "  LEFT OUTER JOIN CONTRATO AS CON                                      "
				   + "    ON CON.ID_CLIENTE = CLI.ID_CLIENTE                                 "
				   + " WHERE (UPPER( CLI.RAZAO_SOCIAL ) LIKE UPPER('%"  + dadoCliente + "%') "
				   + "    OR UPPER( CLI.ALIAS        ) LIKE UPPER('%"  + dadoCliente + "%')  "
				   + "    OR CLI.CNPJ                  LIKE '%"        + dadoCliente + "%')  "
				   + "  AND( CON.ID_STATUS_CONTRATO NOT IN (1, 4) OR CON.ID_STATUS_CONTRATO IS NULL )";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
			cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
			cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
			cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
			cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
			cliente.setSite               ( resultado.getString               ("site")                                 );
			cliente.setCep                ( resultado.getString                ("cep")                                 );
			cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
			cliente.setBairro             ( resultado.getString             ("bairro")                                 );
			cliente.setNumero             ( resultado.getString             ("numero")                                 );
			cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
			cliente.setCidade             ( resultado.getString             ("cidade")                                 );
			cliente.setEstado             ( resultado.getString             ("estado")                                 );
			cliente.setPais               ( resultado.getString               ("pais")                                 );
			cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
			cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
			cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
			cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
			cliente.setObservacao         ( resultado.getString("observacao")                                          );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
			cliente.setAlias              ( resultado.getString("alias")                                               );
			Clientes.add(cliente);
			
		}
		
		return Clientes;
	}

	public List<ModelCliente> buscarListaClienteCadastro( Integer offsetBegin, Integer offsetEnd ) throws SQLException {
		DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
		DAOUtil daoUtil = new DAOUtil();
	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
	    Integer totalPag = getTotalPagCadastro( offsetEnd );
		
		String sql = "SELECT * FROM CLIENTE AS CLI                                             "
				   + "WHERE  CLI.ID_CLIENTE  NOT IN  (SELECT CON.ID_CLIENTE                    "
				   + "                                  FROM CONTRATO AS CON                   "
				   + "                                 WHERE CON.ID_STATUS_CONTRATO IN (1, 4)) "
				   + " ORDER BY ID_CLIENTE OFFSET                                              "  
		           + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY                 ";
		
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
			cliente.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
			cliente.setObservacao(resultado.getString("observacao")                                           );
			cliente.setStatus_emp( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro( resultado.getString("login_cadastro")                                  );
			cliente.setAlias(resultado.getString("alias")                                                     );
			cliente.setTotalPagCli(totalPag);
			Clientes.add(cliente);
		}
		
		return Clientes;
	}

	public Integer getTotalPagCadastro( Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(ID_CLIENTE) AS COUNT_ID_CLIENTE                                  "
				   + "  FROM CLIENTE AS CLI                                                         "
				   + " WHERE  CLI.ID_CLIENTE  NOT IN (SELECT CON.ID_CLIENTE                         "
				   + "                                  FROM CONTRATO AS CON                        "
				   + "                                 WHERE CON.ID_STATUS_CONTRATO IN( 1, 4 ) )";
		Double totalClientes = 0.0;
		Double pagina      = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) {
			// return resutado.getInt("COUNT_ID_CLIENTE");
			totalClientes = resutado.getDouble("COUNT_ID_CLIENTE");
		}
		pagina = totalClientes / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}

	public List<ModelCliente> buscarListaClienteRenovacao( Integer offsetBegin, Integer offsetEnd ) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
	    DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
	    DAOUtil daoUtil = new DAOUtil();
	    Integer totalPag = getTotalPagRenovacao( offsetEnd );
		
		String sql = "SELECT * FROM CLIENTE AS CLI                                            "
				   + "WHERE  CLI.ID_CLIENTE   IN  (SELECT CON.ID_CLIENTE                      "
				   + "                                  FROM CONTRATO AS CON                  "
				   + "                                 WHERE CON.ID_STATUS_CONTRATO IN( 1,4)) "
				   + " ORDER BY ID_CLIENTE OFFSET                                             " 
		           + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY                ";
		
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
			cliente.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
			cliente.setObservacao(resultado.getString("observacao")                                           );
			cliente.setStatus_emp( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro( resultado.getString("login_cadastro")                                  );
			cliente.setAlias(resultado.getString("alias")                                                     );
			cliente.setTotalPagCli(totalPag);
			Clientes.add(cliente);
		}
		
		return Clientes;
	}

	public Integer getTotalPagRenovacao( Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(ID_CLIENTE) AS COUNT_ID_CLIENTE                 "
				   + "  FROM CLIENTE AS CLI                                        "
				   + " WHERE  CLI.ID_CLIENTE IN (SELECT CON.ID_CLIENTE             "
				   + "                             FROM CONTRATO AS CON            "
				   + "                            WHERE CON.ID_STATUS_CONTRATO IN( 1, 4))";
		Double totalClientes = 0.0;
		Double pagina      = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) {
			// return resutado.getInt("COUNT_ID_CLIENTE");
			totalClientes = resutado.getDouble("COUNT_ID_CLIENTE");
		}
		pagina = totalClientes / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}
	
	public List<ModelCliente> buscarListaClienteNomeRenovacao(String dadoCliente) throws SQLException {

	    DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
	    DAOUtil daoUtil = new DAOUtil();
	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
		String sql = "SELECT *                                                               "
				   + "  FROM CLIENTE       AS CLI                                            "
				   + "  LEFT JOIN CONTRATO AS CON                                            "
				   + "    ON CON.ID_CLIENTE = CLI.ID_CLIENTE                                 "
				   + " WHERE (UPPER( CLI.RAZAO_SOCIAL ) LIKE UPPER('%"  + dadoCliente + "%') "
				   + "    OR UPPER( CLI.ALIAS        ) LIKE UPPER('%"  + dadoCliente + "%')  "
				   + "    OR CLI.CNPJ                  LIKE '%"        + dadoCliente + "%')  "
				   + " AND CON.ID_STATUS_CONTRATO IN( 1, 4 )                                 ";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
			cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
			cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
			cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
			cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
			cliente.setSite               ( resultado.getString               ("site")                                 );
			cliente.setCep                ( resultado.getString                ("cep")                                 );
			cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
			cliente.setBairro             ( resultado.getString             ("bairro")                                 );
			cliente.setNumero             ( resultado.getString             ("numero")                                 );
			cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
			cliente.setCidade             ( resultado.getString             ("cidade")                                 );
			cliente.setEstado             ( resultado.getString             ("estado")                                 );
			cliente.setPais               ( resultado.getString               ("pais")                                 );
			cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
			cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
			cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
			cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
			cliente.setObservacao         ( resultado.getString("observacao")                                          );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
			cliente.setAlias              ( resultado.getString("alias")                                               );
			Clientes.add(cliente);
			
		}
		
		return Clientes;
    }

    public String updateRenovacaoContrato( Long id_contrato, String login ) {
    	String returnProcessamento = null;
    	String sql = "UPDATE CONTRATO SET RENOVACAO = 1 WHERE ID_CONTRATO = ? ";
	
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong  ( 1, id_contrato );

			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			DAOError daoError = new DAOError();
			e.printStackTrace();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Update Renovacao Contrato", "683", login, e.getMessage() );
			returnProcessamento = "Insert Contrato: " + e.getMessage();
			System.out.println("erro: " + e);
		}

		return returnProcessamento;
	}
    
    public String updateRenovacaoContratoOrigem( Long id_contrato_origem, Long id_contrato, String login ) {
    	String returnProcessamento = null;
    	String sql = "UPDATE CONTRATO SET RENOVACAO_CONTRATO_ORIGEM = ? WHERE ID_CONTRATO = ? ";
	
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong  ( 1, id_contrato_origem );
			prepareSql.setLong  ( 2, id_contrato        );

			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			DAOError daoError = new DAOError();
			e.printStackTrace();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Update Renovacao Contrato Origem", "705", login, e.getMessage() );
			returnProcessamento = "Insert Contrato: " + e.getMessage();
			System.out.println("erro: " + e);
		}

		return returnProcessamento;
	}
    public String updateRenovacaoContratoNovo( Long id_contrato_novo, Long id_contrato, String login ) {
    	String returnProcessamento = null;
    	String sql = "UPDATE CONTRATO SET RENOVACAO_CONTRATO_NOVO = ? WHERE ID_CONTRATO = ? ";
	
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.setLong  ( 1, id_contrato_novo );
			prepareSql.setLong  ( 2, id_contrato        );

			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			DAOError daoError = new DAOError();
			e.printStackTrace();
			daoError.insertError("DAOCadastroContrato", "cadastroContrato.jsp", "Update Renovacao Contrato Origem", "705", login, e.getMessage() );
			returnProcessamento = "Insert Contrato: " + e.getMessage();
			System.out.println("erro: " + e);
		}

		return returnProcessamento;
	}
    
	public List<ModelPepSap> getListaPepSap( String pepSap ) {
		
		String sql = "SELECT TOP (200)                                   "
				   + "       PEP                                         "
				   + "     , NOME_CLIENTE                                "
				   + "     , CNPJ                                        "
				   + "  FROM PEP_SAP                                     "
				   + "  WHERE UPPER( PEP ) LIKE UPPER('%" + pepSap + "%')"
			       + "ORDER BY 1                                         ";
		
		List<ModelPepSap> listaPepSap = new ArrayList<ModelPepSap>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
		
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelPepSap mpepSap = new ModelPepSap();
				mpepSap.setPep         ( set.getString( "PEP"          ));
				mpepSap.setNome_cliente( set.getString( "NOME_CLIENTE" ));
				mpepSap.setCnpj        ( set.getString( "CNPJ"         ));
				listaPepSap.add(mpepSap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPepSap;
	}

	public List<ModelPepSap> getListaPepSapSelect2( ) {
		
		String sql = "SELECT DISTINCT PEP FROM PEP_SAP  ORDER BY 1";
		
		List<ModelPepSap> listaPepSap = new ArrayList<ModelPepSap>();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
		
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelPepSap mpepSap = new ModelPepSap();
				mpepSap.setPep         ( set.getString( "PEP"      ));
				// mpepSap.setIdContrato(set.getLong( "ID_CONTRATO"  ));
				listaPepSap.add(mpepSap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPepSap;
	}


}
