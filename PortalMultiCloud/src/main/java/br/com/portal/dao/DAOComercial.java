package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelComercial;

public class DAOComercial {
	
	private Connection connection;
	
	public DAOComercial() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelComercial> getListaComercial() {
		
		String sql = "SELECT * FROM COMERCIAL ORDER BY 1";
		List<ModelComercial> comercias = new ArrayList<ModelComercial>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelComercial comercial = new ModelComercial();
				comercial.setId_comercial  ( set.getLong("ID_COMERCIAL")                                          );
				comercial.setNome_comercial( set.getString("NOME_COMERCIAL")                                      );
				comercial.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CADASTRO")) );
				comercial.setEmail         ( set.getString("EMAIL")                                               );
				comercial.setTelefone      ( set.getString("TELEFONE")                                            );
				comercial.setObs           ( set.getString("OBS")                                                 );
				comercias.add(comercial);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comercias;
	}
	
	public ModelComercial gravarComercial( ModelComercial objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO COMERCIAL (DT_CADASTRO, NOME_COMERCIAL, TELEFONE, EMAIL, OBS ) VALUES ( ?, ?, ?, ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setTimestamp( 1, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 2, objeto.getNome_comercial()                             );
			prepareSql.setString   ( 3, objeto.getTelefone()                                   );
			prepareSql.setString   ( 4, objeto.getEmail()                                      );
			prepareSql.setString   ( 5, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getComercialID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE COMERCIAL         "
					   + " SET  NOME_COMERCIAL = ? "
					   + "    , TELEFONE       = ? "
					   + "    , EMAIL          = ? "
					   + "    , OBS            = ? "
					   + " WHERE ID_COMERCIAL  = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getNome_comercial() );
			prepareSql.setString( 2, objeto.getTelefone()       );
			prepareSql.setString( 3, objeto.getEmail()          );
			prepareSql.setString( 4, objeto.getObs()            );
			prepareSql.setLong  ( 5, objeto.getId_comercial()   );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_COMERCIAL ) AS MAX_ID FROM COMERCIAL";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelComercial getComercialID( Long idComercial ) throws Exception {
		String sql = "SELECT * FROM COMERCIAL WHERE ID_COMERCIAL = ?";
		
		ModelComercial modelComercial = new ModelComercial();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idComercial );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelComercial.setId_comercial  ( resutado.getLong  ("ID_COMERCIAL")                                         );			 
			modelComercial.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ) );
			modelComercial.setNome_comercial( resutado.getString  ("NOME_COMERCIAL")                                     );
			modelComercial.setEmail         ( resutado.getString  ("EMAIL")                                              );
			modelComercial.setTelefone      ( resutado.getString  ("TELEFONE")                                           );
			modelComercial.setObs           ( resutado.getString  ("OBS")                                                );
		}
		return modelComercial;
	}
	
	public void deletarComercial(String id) throws SQLException {
		String sql = "DELETE FROM COMERCIAL WHERE ID_COMERCIAL = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}


}
