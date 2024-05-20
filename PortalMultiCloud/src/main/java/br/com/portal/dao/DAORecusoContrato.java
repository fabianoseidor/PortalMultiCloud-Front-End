package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale; // Que permitirá a configuração da localidade.
import java.text.NumberFormat;// Permit

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelAditivoModal;
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelListAditivoProduto;
import br.com.portal.model.ModelParticao;
import br.com.portal.model.ModelRecursoContrato;
import br.com.portal.model.ModelRecursoContratoCliente;

public class DAORecusoContrato {
	private Connection connection;
	
	/*
	 * 
	 * 
	 * 
	 * */	
	public DAORecusoContrato() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void insertParticao( ModelParticao objeto ) throws SQLException {
		String sql = "INSERT INTO PARTICAO ( ID_RECURSO, QUATIDADE, TAMANHO, DESCRICAO ) VALUES ( ? , ? , ? , ? )";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, objeto.getId_recurso() );
		prepareSql.setString   ( 2, objeto.getQuantidade() );	
		prepareSql.setString   ( 3, objeto.getTamanho()    );	
		prepareSql.setString   ( 4, objeto.getDescricao()  );	
		
		prepareSql.execute();
		connection.commit();

	}
	
	/*
	 * 
	 * 
	 * 
	 * */	
	public ModelRecursoContrato gravarRecursoContrato( ModelRecursoContrato objeto ) throws Exception {
		
		
		if( objeto.isNovo() ){
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
			
			return this.getRecursoIdContrato( this.getMaxIdRecurso( objeto ), 1 );
		}else {
			String sql = "UPDATE RECURSO               "
					+ "   SET  ID_STATUS_RECURSO    = ?" // 1  - getId_status_recurso
					+ "      , ID_RETENCAO_BACKUP   = ?" // 2  - getId_retencao_backup
					+ "      , ID_TIPO_DISCO        = ?" // 3  - getId_tipo_disco
					+ "      , ID_SO                = ?" // 4  - getId_so
					+ "      , ID_AMBIENTE          = ?" // 5  - getId_ambiente
					+ "      , ID_FAMILIA_FLAVORS   = ?" // 6  - getId_familia_flavors
					+ "      , ID_TIPO_SERVICO      = ?" // 7  - getId_tipo_servico
					+ "      , TAG_VCENTER          = ?" // 8  - getTag_vcenter
					+ "      , HOSTNAME             = ?" // 9  - getHostname
					+ "      , TAMANHO_DISCO        = ?" // 10 - getTamanhoDisco
					+ "      , PRIMARY_IP           = ?" // 11 - getPrimaryIP
					+ "      , OBS                  = ?" // 12 - getObs
					+ "      , VALOR_RECURSO        = ?" // 13 - getValor_recurso
					+ "      , ID_NUVEM             = ?" // 14 - getId_nuvem
					+ "      , SITE                 = ?" // 15 - getId_site
					+ "      , EIP_VCENTER          = ?" // 16 - getEip_vcenter
					+ "      , HOST_VCENTER         = ?" // 17 - getHost_vcenter					
					+ " WHERE  ID_RECURSO           = ?";// 18 - getId_recurso
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_status_recurso()  );
			prepareSql.setLong     ( 2, objeto.getId_retencao_backup() );
			prepareSql.setLong     ( 3, objeto.getId_tipo_disco()      );
			prepareSql.setLong     ( 4, objeto.getId_so()              );
			prepareSql.setLong     ( 5, objeto.getId_ambiente()        );
			prepareSql.setLong     ( 6, objeto.getId_familia_flavors() );
			prepareSql.setLong     ( 7, objeto.getId_tipo_servico()    );
			prepareSql.setString   ( 8, objeto.getTag_vcenter()        );
			prepareSql.setString   ( 9, objeto.getHostname()           );	
			prepareSql.setString   (10, objeto.getTamanhoDisco()       );	
			prepareSql.setString   (11, objeto.getPrimaryIP()          );	
			prepareSql.setString   (12, objeto.getObs()                );	
			prepareSql.setString   (13, objeto.getValor_recurso()      );
			prepareSql.setLong     (14, objeto.getId_nuvem()           );
			prepareSql.setLong     (15, objeto.getId_site()            );
			prepareSql.setString   (16, objeto.getEip_vcenter()        );
			prepareSql.setString   (17, objeto.getHost_vcenter()       );
			prepareSql.setLong     (18, objeto.getId_recurso()         );

			prepareSql.executeUpdate();
			connection.commit();
			objeto.setTipoCrud(2);
			return objeto;

		}
	
	}
	
	public ModelRecursoContrato getRecursoIdRecurso( Long idRecurso) throws Exception {
		String sql = "SELECT * FROM RECURSO WHERE ID_RECURSO = ?";
		
		ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idRecurso );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			 modelRecursoContrato.setId_recurso         ( resutado.getLong  ("ID_RECURSO")                                  );			 
			 modelRecursoContrato.setId_status_recurso  ( resutado.getLong  ("ID_STATUS_RECURSO")                           );
			 modelRecursoContrato.setId_retencao_backup ( resutado.getLong  ("ID_RETENCAO_BACKUP")                          );
			 modelRecursoContrato.setId_tipo_disco      ( resutado.getLong  ("ID_TIPO_DISCO")                               );
			 modelRecursoContrato.setId_so              ( resutado.getLong  ("ID_SO")                                       );
			 modelRecursoContrato.setId_ambiente        ( resutado.getLong  ("ID_AMBIENTE")                                 );
			 modelRecursoContrato.setId_familia_flavors ( resutado.getLong  ("ID_FAMILIA_FLAVORS")                          );
			 modelRecursoContrato.setId_tipo_servico    ( resutado.getLong  ("ID_TIPO_SERVICO")                             );
			 modelRecursoContrato.setDt_cadastro        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ));
			 modelRecursoContrato.setTag_vcenter        ( resutado.getString("TAG_VCENTER")                                 );
			 modelRecursoContrato.setHostname           ( resutado.getString("HOSTNAME")                                    );
			 modelRecursoContrato.setTamanhoDisco       ( resutado.getString("TAMANHO_DISCO")                               );
			 modelRecursoContrato.setPrimaryIP          ( resutado.getString("PRIMARY_IP")                                  );
			 modelRecursoContrato.setObs                ( resutado.getString("OBS")                                         );
			 modelRecursoContrato.setId_contrato        ( resutado.getLong  ("ID_CONTRATO")                                 );
			 modelRecursoContrato.setEip_vcenter        ( resutado.getString("EIP_VCENTER")                                 );
			 modelRecursoContrato.setHost_vcenter       ( resutado.getString("HOST_VCENTER")                                );
			 
			 if( modelRecursoContrato.getId_contrato() != null  ) {
				 ModelRecursoContratoCliente modelRecursoContratoCliente = this.getTelaInical(  modelRecursoContrato.getId_contrato() );
				 if( !modelRecursoContratoCliente.isNovo() ) {
				      modelRecursoContrato.setId_cliente ( modelRecursoContratoCliente.getId_cliente()   );
				      modelRecursoContrato.setNomeCliente( modelRecursoContratoCliente.getRazao_social() );
				      modelRecursoContrato.setId_nuvem   ( modelRecursoContratoCliente.getId_nuvem()     );
				      modelRecursoContrato.setNuvem      ( modelRecursoContratoCliente.getNuvem()        );
				 }
		     }
		}
		return modelRecursoContrato;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	
	
	public ModelRecursoContrato getRecursoIdContrato( Long idRecurso, Integer tipoCrud ) throws Exception {
		String sql = "SELECT * FROM RECURSO WHERE ID_RECURSO = ?";
		
		ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();
		DAOUtil daoUtil = new DAOUtil();
//		Locale localeBR       = new Locale("pt","BR");
//		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
//		Double valorReal      = 0.0;
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idRecurso );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			 modelRecursoContrato.setId_recurso         ( resutado.getLong  ("ID_RECURSO")                                  );			 
			 modelRecursoContrato.setId_status_recurso  ( resutado.getLong  ("ID_STATUS_RECURSO")                           );
			 modelRecursoContrato.setId_retencao_backup ( resutado.getLong  ("ID_RETENCAO_BACKUP")                          );
			 modelRecursoContrato.setId_tipo_disco      ( resutado.getLong  ("ID_TIPO_DISCO")                               );
			 modelRecursoContrato.setId_so              ( resutado.getLong  ("ID_SO")                                       );
			 modelRecursoContrato.setId_ambiente        ( resutado.getLong  ("ID_AMBIENTE")                                 );
			 modelRecursoContrato.setId_familia_flavors ( resutado.getLong  ("ID_FAMILIA_FLAVORS")                          );
			 modelRecursoContrato.setId_tipo_servico    ( resutado.getLong  ("ID_TIPO_SERVICO")                             );
			 modelRecursoContrato.setDt_cadastro        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ));
			 modelRecursoContrato.setTag_vcenter        ( resutado.getString("TAG_VCENTER")                                 );
			 modelRecursoContrato.setHostname           ( resutado.getString("HOSTNAME")                                    );
			 modelRecursoContrato.setTamanhoDisco       ( resutado.getString("TAMANHO_DISCO")                               );
			 modelRecursoContrato.setPrimaryIP          ( resutado.getString("PRIMARY_IP")                                  );
			 modelRecursoContrato.setObs                ( resutado.getString("OBS")                                         );
			 modelRecursoContrato.setId_contrato        ( resutado.getLong  ("ID_CONTRATO")                                 );
//			 valorReal = Double.valueOf                 ( resutado.getString("VALOR_RECURSO")                               );
//			 modelRecursoContrato.setValor_recurso      ( dinheiro.format(valorReal)                                        );
			 modelRecursoContrato.setTipoCrud(tipoCrud);

			 if( modelRecursoContrato.getId_contrato() != null  ) {
				 ModelRecursoContratoCliente modelRecursoContratoCliente = this.getTelaInical(  modelRecursoContrato.getId_contrato() );
				 if( !modelRecursoContratoCliente.isNovo() ) {
				      modelRecursoContrato.setId_cliente ( modelRecursoContratoCliente.getId_cliente()   );
				      modelRecursoContrato.setNomeCliente( modelRecursoContratoCliente.getRazao_social() );
				      modelRecursoContrato.setId_nuvem   ( modelRecursoContratoCliente.getId_nuvem()     );
				      modelRecursoContrato.setNuvem      ( modelRecursoContratoCliente.getNuvem()        );
				 }
//				 System.out.println(modelRecursoContrato);
		     }
		}
		return modelRecursoContrato;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
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
		
		while (resutado.next()) {
			return resutado.getLong("MAX_ID_RECURSO");
			
		}
		return null;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public List<ModelFamiliaFlavors> listaFamiliaSelect(Long idFamilia){
		
		String sql = "SELECT ID_FAMILIA_FLAVORS, ID_NUVEM, FAMILIA, CPU, RAM, VALOR FROM FAMILIA_FLAVORS WHERE ID_NUVEM = " + idFamilia + " ORDER BY ID_FAMILIA_FLAVORS";
		List<ModelFamiliaFlavors> modelFamiliaFlavors = new ArrayList<ModelFamiliaFlavors>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelFamiliaFlavors modelFamiliaFlavor = new ModelFamiliaFlavors();
				
				modelFamiliaFlavor.setId_familia_flavors( set.getLong("ID_FAMILIA_FLAVORS") );
				modelFamiliaFlavor.setId_nuvem( set.getLong("ID_NUVEM") );
				
				modelFamiliaFlavor.setFamilia( set.getString("FAMILIA") );
				modelFamiliaFlavor.setCpu(set.getString("CPU"));
				modelFamiliaFlavor.setRam(set.getString("RAM"));
				
				 Locale localeBR = new Locale("pt","BR");
				 NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				 Double valorReal = Double.valueOf( set.getString("VALOR") ) ;
				 String vlr = dinheiro.format(valorReal);
//				 System.out.println( vlr );
				 modelFamiliaFlavor.setValor( vlr );
				
				 modelFamiliaFlavors.add(modelFamiliaFlavor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelFamiliaFlavors;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public ModelFamiliaFlavors getFamiliaFlavors(Long idFamiliaFlavors){
		
		String sql = "SELECT ID_FAMILIA_FLAVORS, ID_NUVEM, FAMILIA, CPU, RAM, VALOR FROM FAMILIA_FLAVORS WHERE ID_FAMILIA_FLAVORS = " + idFamiliaFlavors ;
		ModelFamiliaFlavors modelFamiliaFlavor = new ModelFamiliaFlavors();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				modelFamiliaFlavor.setId_familia_flavors( set.getLong("ID_FAMILIA_FLAVORS") );
				modelFamiliaFlavor.setId_nuvem( set.getLong("ID_NUVEM") );
				
				modelFamiliaFlavor.setFamilia( set.getString("FAMILIA") );
				modelFamiliaFlavor.setCpu(set.getString("CPU"));
				modelFamiliaFlavor.setRam(set.getString("RAM"));
				
				Locale localeBR = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal = Double.valueOf( set.getString("VALOR") ) ;
				String vlr = dinheiro.format(valorReal);
//				 System.out.println( vlr );
				 modelFamiliaFlavor.setValor( vlr );

	//			System.out.println(modelFamiliaFlavor);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelFamiliaFlavor;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public ModelRecursoContratoCliente getTelaInical(Long idContrato){
		
		String sql = "SELECT                                 "
				+ "    CONT.ID_CONTRATO                      "
				+ "  , CONT.ID_NUVEM                         "
				+ "  , NUV.MOME_PARCEIRO                     "
				+ "  , CLIE.ID_CLIENTE                       "
				+ "  , CLIE.RAZAO_SOCIAL                     "
				+ "  , CLIE.NOME_FANTASIA                    "
				+ "  FROM                                    "
				+ "     CONTRATO AS CONT                     "
				+ "   , CLIENTE  AS CLIE                     "
				+ "   , NUVEM    AS NUV                      "
				+ " WHERE CONT.ID_CONTRATO = ?               "
				+ "   AND CLIE.ID_CLIENTE  = CONT.ID_CLIENTE "
				+ "   AND NUV.ID_NUVEM     = CONT.ID_NUVEM   " ;
		ModelRecursoContratoCliente modelRecursoContratoCliente = new ModelRecursoContratoCliente();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				modelRecursoContratoCliente.setId_contrato  ( set.getLong  ("ID_CONTRATO"  ) );
				modelRecursoContratoCliente.setId_cliente   ( set.getLong  ("ID_CLIENTE"   ) );
				modelRecursoContratoCliente.setId_nuvem     ( set.getLong  ("ID_NUVEM"     ) );
				modelRecursoContratoCliente.setNuvem        ( set.getString("MOME_PARCEIRO") );
				modelRecursoContratoCliente.setNome_fantasia( set.getString("NOME_FANTASIA") );
				modelRecursoContratoCliente.setRazao_social ( set.getString("RAZAO_SOCIAL" ) );
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelRecursoContratoCliente;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public Long getMaxIdContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT MAX( ID_RECURSO ) AS MAX_ID_RECURSO FROM RECURSO  WHERE ID_CONTRATO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("MAX_ID_RECURSO");
			
		}
		return null;
	}	

	public List<ModelParticao> listaParticao( Long idRecurso){
		
		String sql = "SELECT ID_PARTICAO, ID_RECURSO, QUATIDADE, TAMANHO, DESCRICAO FROM PARTICAO WHERE ID_RECURSO = ? ORDER BY ID_PARTICAO";
		List<ModelParticao> modelParticoes = new ArrayList<ModelParticao>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idRecurso );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelParticao modelParticao = new ModelParticao();
				
				modelParticao.setId_particao( set.getLong("ID_PARTICAO") );
				modelParticao.setId_recurso( set.getLong("ID_RECURSO") );
				modelParticao.setQuantidade( set.getString("QUATIDADE") );
				modelParticao.setTamanho( set.getString("TAMANHO") );
				modelParticao.setDescricao( set.getString("DESCRICAO") );
				modelParticoes.add(modelParticao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelParticoes;
	}

	/*
	 * 
	 * 
	 * */
	public void deleteParticao( Long idParticao ) throws SQLException {
		String sql = "DELETE FROM PARTICAO WHERE ID_PARTICAO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong  ( 1, idParticao );
		
		prepareSql.executeUpdate();
		connection.commit();
	}

	public Long getIdContrato( Long idRecuso ) throws SQLException {
		String sql = "SELECT ID_CONTRATO FROM RECURSO WHERE ID_RECURSO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idRecuso );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("ID_CONTRATO");
			
		}
		return null;
	}	

	public ModelListAditivoProduto getAditivoProdutoContratados( ModelAditivoModal obj ) throws SQLException {
		
		String sql = "SELECT                                                       "
				+ "     ADT.ID_ADITIVADO                                           "
				+ "   , ADT.DT_CRIACAO                                             "
				+ "   , ADT.ID_STATUS_ADITIVO                                      "
				+ "   , ADT.ID_CONTRATO                                            "
				+ "   , ADT.VLR_TOTAL_ADIT                                         "
				+ "   , ADT.OBSERVACAO                                             "
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
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong ( 1, obj.getId_aditivado()    );
			ps.setLong ( 2, obj.getId_produto ()     );
			ps.setLong ( 3, obj.getId_tipo_aditivo() );
			
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {

				ListAditivoProduto.setId_aditivado                ( set.getLong  ("ID_ADITIVADO")                                                    );
				ListAditivoProduto.setDt_criacao                  ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO"))              );
				ListAditivoProduto.setObservacao                  ( set.getString("OBSERVACAO")                                                      );
				
				valorReal      = Double.valueOf( set.getString("VLR_TOTAL_ADIT") );
				ListAditivoProduto.setVlr_total_adit              ( dinheiro.format(valorReal)                                                       );


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListAditivoProduto;
	}

	
	public String getNuvemSiteIdContrato( Long idContrato ) throws SQLException {
		String sql = "SELECT ID_NUVEM, ID_SITE FROM CONTRATO WHERE ID_CONTRATO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idContrato );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getString("ID_NUVEM") + ";" + resutado.getString("ID_SITE");
			
		}
		return null;
	}	


}
