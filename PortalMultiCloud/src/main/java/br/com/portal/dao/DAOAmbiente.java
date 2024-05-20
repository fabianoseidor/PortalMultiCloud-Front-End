package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelAmbiente;
public class DAOAmbiente {

	private Connection connection;
	
	public DAOAmbiente() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelAmbiente> getListaAmbiente() {
		
		String sql = "SELECT * FROM AMBIENTE ORDER BY 1";
		List<ModelAmbiente> ambientes = new ArrayList<ModelAmbiente>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelAmbiente ambiente = new ModelAmbiente();
				ambiente.setId_ambiente( set.getLong("ID_AMBIENTE")                                          );
				ambiente.setAmbiente   ( set.getString("AMBIENTE")                                           );
				ambiente.setDt_criacao ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ));
				ambiente.setObs        ( set.getString("OBS")                                                );
				ambientes.add(ambiente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ambientes;
	}
	

	public ModelAmbiente gravarAmbiente( ModelAmbiente objeto ) throws Exception {
				
		if( objeto.isNovo() ){
			String sql = "INSERT INTO AMBIENTE (DT_CRIACAO, AMBIENTE, OBS ) VALUES (?, ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setTimestamp( 1, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 2, objeto.getAmbiente()                                   );
			prepareSql.setString   ( 3, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getAmbienteID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE AMBIENTE        "
					   + " SET  AMBIENTE     = ? "
					   + "    , OBS          = ? "
					   + " WHERE ID_AMBIENTE = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getAmbiente() );
			prepareSql.setString( 2, objeto.getObs()      );
			prepareSql.setLong  ( 3, objeto.getId_ambiente()  );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_AMBIENTE ) AS MAX_ID FROM AMBIENTE";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	public ModelAmbiente getAmbienteID( Long idAmbiente ) throws Exception {
		String sql = "SELECT * FROM AMBIENTE WHERE ID_AMBIENTE = ?";
		
		ModelAmbiente modelAmbiente = new ModelAmbiente();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idAmbiente );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelAmbiente.setId_ambiente( resutado.getLong  ("ID_AMBIENTE")                                         );			 
			modelAmbiente.setDt_criacao ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modelAmbiente.setAmbiente   ( resutado.getString  ("AMBIENTE")                                          );
			modelAmbiente.setObs        ( resutado.getString  ("OBS")                                               );
		}
		return modelAmbiente;
	}
	
	public void deletarAmbiente(String id) throws SQLException {
		String sql = "DELETE FROM AMBIENTE WHERE ID_AMBIENTE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

	
}
