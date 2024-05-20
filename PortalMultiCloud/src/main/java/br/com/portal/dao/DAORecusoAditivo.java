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
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelRecursoAditivo;
import br.com.portal.model.ModelRecursoContratoCliente;
public class DAORecusoAditivo {
	private Connection connection;
	
	/*
	 * 
	 * 
	 * 
	 * */	
	public DAORecusoAditivo() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelRecursoAditivo gravarContrato( ModelRecursoAditivo objeto ) throws Exception {
		
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO RECURSO ( ID_STATUS_RECURSO               " // 1  - objeto.getId_status_recurso()
					+ "                       , ID_RETENCAO_BACKUP              " // 2  - objeto.getId_retencao_backup()
					+ "                       , ID_TIPO_DISCO                   " // 3  - objeto.getId_tipo_disco()
					+ "                       , ID_SO                           " // 4  - getId_so()
					+ "                       , ID_AMBIENTE                     " // 5  - getId_ambiente()
					+ "                       , ID_FAMILIA_FLAVORS              " // 6  - getId_familia_flavors()
					+ "                       , ID_TIPO_SERVICO                 " // 7  - getId_tipo_servico()
					+ "                       , DT_CADASTRO                     " // 8  - new Date();
					+ "                       , TAG_VCENTER                     " // 9  - getTag_vcenter
					+ "                       , OBS                             " // 10 - getObs
					+ "                       , ID_CONTRATO                     " // 11 - getId_contrato
					+ "                       , ID_ADITIVADO)                   " // 12 - getId_aditivado
					+ "     VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong     ( 1, objeto.getId_status_recurso()                          );
			prepareSql.setLong     ( 2, objeto.getId_retencao_backup()                         );
			prepareSql.setLong     ( 3, objeto.getId_tipo_disco()                              );
			prepareSql.setLong     ( 4, objeto.getId_so()                                      );
			prepareSql.setLong     ( 5, objeto.getId_ambiente()                                );
			prepareSql.setLong     ( 6, objeto.getId_familia_flavors()                         );
			prepareSql.setLong     ( 7, objeto.getId_tipo_servico()                            );
			prepareSql.setTimestamp( 8, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 9, objeto.getTag_vcenter()                                );	
			prepareSql.setString   (10, objeto.getObs()                                        );
			prepareSql.setLong     (11, objeto.getId_contrato()                                );
			prepareSql.setLong     (12, objeto.getId_aditivado()                               );

			prepareSql.execute();
			connection.commit();
			
			return this.getAditivoIdRecurso( this.getMaxIdRecurso( objeto ) );
		}else {
			String sql = "UPDATE [dbo].[RECURSO]"
					+ "   SET [ID_STATUS_RECURSO]   = ?" // 1  - getId_status_recurso
					+ "      ,[ID_RETENCAO_BACKUP]  = ?" // 2  - getId_retencao_backup
					+ "      ,[ID_TIPO_DISCO]       = ?" // 3  - getId_tipo_disco
					+ "      ,[ID_SO]               = ?" // 4  - getId_so
					+ "      ,[ID_AMBIENTE]         = ?" // 5  - getId_ambiente
					+ "      ,[ID_FAMILIA_FLAVORS]  = ?" // 6  - getId_familia_flavors
					+ "      ,[ID_TIPO_SERVICO]     = ?" // 7  - getId_tipo_servico
					+ "      ,[TAG_VCENTER]         = ?" // 8  - getTag_vcenter
					+ "      ,[OBS]                 = ?" // 9  - getObs
					+ " WHERE [ID_RECURSO]          = ?";// 10 - getId_recurso
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1, objeto.getId_status_recurso()  );
			prepareSql.setLong     ( 2, objeto.getId_retencao_backup() );
			prepareSql.setLong     ( 3, objeto.getId_tipo_disco()      );
			prepareSql.setLong     ( 4, objeto.getId_so()              );
			prepareSql.setLong     ( 5, objeto.getId_ambiente()        );
			prepareSql.setLong     ( 6, objeto.getId_familia_flavors() );
			prepareSql.setLong     ( 7, objeto.getId_tipo_servico()    );
			prepareSql.setString   ( 8, objeto.getTag_vcenter()        );	
			prepareSql.setString   ( 9, objeto.getObs()                );	
			prepareSql.setLong     (10, objeto.getId_recurso()         );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;

		}
	
	}

	public ModelRecursoAditivo getAditivoIdRecurso( Long idRecurso) throws Exception {
		String sql = "SELECT * FROM RECURSO WHERE ID_RECURSO = ?";
		
		ModelRecursoAditivo modelRecursoAditivo = new ModelRecursoAditivo();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idRecurso );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelRecursoAditivo.setId_recurso         ( resutado.getLong  ("ID_RECURSO")                                  );			 
			modelRecursoAditivo.setId_status_recurso  ( resutado.getLong  ("ID_STATUS_RECURSO")                           );
			modelRecursoAditivo.setId_retencao_backup ( resutado.getLong  ("ID_RETENCAO_BACKUP")                          );
			modelRecursoAditivo.setId_tipo_disco      ( resutado.getLong  ("ID_TIPO_DISCO")                               );
			modelRecursoAditivo.setId_so              ( resutado.getLong  ("ID_SO")                                       );
			modelRecursoAditivo.setId_ambiente        ( resutado.getLong  ("ID_AMBIENTE")                                 );
			modelRecursoAditivo.setId_familia_flavors ( resutado.getLong  ("ID_FAMILIA_FLAVORS")                          );
			modelRecursoAditivo.setId_tipo_servico    ( resutado.getLong  ("ID_TIPO_SERVICO")                             );
			modelRecursoAditivo.setDt_cadastro        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ));
			modelRecursoAditivo.setTag_vcenter        ( resutado.getString("TAG_VCENTER")                                 );
			modelRecursoAditivo.setObs                ( resutado.getString("OBS")                                         );
			modelRecursoAditivo.setId_contrato        ( resutado.getLong  ("ID_CONTRATO")                                 );
			modelRecursoAditivo.setId_aditivado       (resutado.getLong   ("ID_ADITIVADO")                                );
			 if( modelRecursoAditivo.getId_aditivado() != null  ) {
				 ModelRecursoContratoCliente modelRecursoContratoCliente = this.getTelaInical(  modelRecursoAditivo.getId_contrato() );
				 if( !modelRecursoContratoCliente.isNovo() ) {
					 modelRecursoAditivo.setId_cliente ( modelRecursoContratoCliente.getId_cliente()   );
					 modelRecursoAditivo.setNomeCliente( modelRecursoContratoCliente.getRazao_social() );
					 modelRecursoAditivo.setId_nuvem   ( modelRecursoContratoCliente.getId_nuvem()     );
					 modelRecursoAditivo.setNuvem      ( modelRecursoContratoCliente.getNuvem()        );
				 }
//				 System.out.println(modelRecursoContrato);
		     }
		}
		return modelRecursoAditivo;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	public Long getMaxIdRecurso( ModelRecursoAditivo objeto ) throws SQLException {
		String sql = "SELECT MAX( ID_RECURSO ) MAX_ID_RECURSO "
				+ "     FROM RECURSO                          "
				+ "    WHERE ID_ADITIVADO       = ?           "
				+ "      AND ID_CONTRATO        = ?           "
				+ "      AND ID_STATUS_RECURSO  = ?           "
				+ "      AND ID_RETENCAO_BACKUP = ?           "
				+ "      AND ID_TIPO_DISCO      = ?           "
				+ "      AND ID_SO              = ?           "
				+ "      AND ID_AMBIENTE        = ?           "
				+ "      AND ID_FAMILIA_FLAVORS = ?           "
				+ "      AND ID_TIPO_SERVICO    = ?           ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, objeto.getId_aditivado()       );
		prepareSql.setLong     ( 2, objeto.getId_contrato()        );
		prepareSql.setLong     ( 3, objeto.getId_status_recurso()  );
		prepareSql.setLong     ( 4, objeto.getId_retencao_backup() );
		prepareSql.setLong     ( 5, objeto.getId_tipo_disco()      );
		prepareSql.setLong     ( 6, objeto.getId_so()              );
		prepareSql.setLong     ( 7, objeto.getId_ambiente()        );
		prepareSql.setLong     ( 8, objeto.getId_familia_flavors() );
		prepareSql.setLong     ( 9, objeto.getId_tipo_servico()    );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID_RECURSO");

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
	public Long getMaxIdContrato( Long idAditivo ) throws SQLException {
		String sql = "SELECT MAX( ID_RECURSO ) AS MAX_ID_RECURSO FROM RECURSO WHERE ID_ADITIVADO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idAditivo );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("MAX_ID_RECURSO");
			
		}
		return null;
	}	

	public Long getIdAditivo( Long idRecuso ) throws SQLException {
		String sql = "SELECT ID_ADITIVADO FROM RECURSO WHERE ID_RECURSO = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		statemet.setLong( 1, idRecuso );
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getLong("ID_ADITIVADO");
			
		}
		return null;
	}	

	
}
