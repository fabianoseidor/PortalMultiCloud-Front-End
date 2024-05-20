package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelTipoDisco;

public class DAOTipoDisco {
	private Connection connection;
	
	public DAOTipoDisco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelTipoDisco> getListaTipoDisco() {
		
		String sql = "SELECT * FROM TIPO_DISCO ORDER BY 1";
		List<ModelTipoDisco> tipoDiscos = new ArrayList<ModelTipoDisco>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoDisco tipoDisco = new ModelTipoDisco();
				tipoDisco.setId_tipo_disco(set.getLong("ID_TIPO_DISCO"));
				tipoDisco.setTipo_disco   (set.getString("TIPO_DISCO"));
				tipoDisco.setDt_criacao   (daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ));
				tipoDisco.setObs          (set.getString("OBS"));
				tipoDiscos.add(tipoDisco);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoDiscos;
	}


	public ModelTipoDisco gravarTipoDisco( ModelTipoDisco objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO TIPO_DISCO ( TIPO_DISCO, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getTipo_disco() );
			prepareSql.setString( 2, objeto.getObs()        );

			prepareSql.execute();
			connection.commit();
			
			return this.getTipoDiscoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE TIPO_DISCO        "
				 	   + "   SET TIPO_DISCO    = ? "
					   + "     , OBS           = ? "
					   + " WHERE ID_TIPO_DISCO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getTipo_disco()    );
			prepareSql.setString( 2, objeto.getObs()           );
			prepareSql.setLong  ( 3, objeto.getId_tipo_disco() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_TIPO_DISCO ) AS MAX_ID FROM TIPO_DISCO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	public ModelTipoDisco getTipoDiscoID( Long idTipoDisco ) throws Exception {
		String sql = "SELECT * FROM TIPO_DISCO WHERE ID_TIPO_DISCO = ?";
		
		ModelTipoDisco modelTipoDisco = new ModelTipoDisco();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idTipoDisco );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelTipoDisco.setId_tipo_disco( resutado.getLong    ("ID_TIPO_DISCO")                                     );			 
			modelTipoDisco.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modelTipoDisco.setTipo_disco   ( resutado.getString  ("TIPO_DISCO")                                        );
			modelTipoDisco.setObs          ( resutado.getString ("OBS")                                                );
		}
		return modelTipoDisco;
	}

	public void deletarRegistro(String id) throws SQLException {
		String sql = "DELETE FROM TIPO_DISCO WHERE ID_TIPO_DISCO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
