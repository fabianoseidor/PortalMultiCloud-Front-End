package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelTipoRascunho;

public class DAOTipoRascunho {
	private Connection connection;
	
	public DAOTipoRascunho() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelTipoRascunho> getListaTipoRascunho() {
		
		String sql = "SELECT * FROM TIPO_RASCUNHO ORDER BY 1";
		List<ModelTipoRascunho> tipoRascunhos = new ArrayList<ModelTipoRascunho>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoRascunho tipoRascunho = new ModelTipoRascunho();
				tipoRascunho.setId_tipo_rascunho( set.getLong("ID_RASCUNHO")                                          );
				tipoRascunho.setRascunho        ( set.getString("RASCUNHO")                                           );
				tipoRascunho.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO")));
				tipoRascunho.setObs             ( set.getString("OBS")                                                );
				tipoRascunhos.add(tipoRascunho);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoRascunhos;
	}

	public ModelTipoRascunho gravarTipoRascunho( ModelTipoRascunho objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO TIPO_RASCUNHO (DT_CRIACAO, RASCUNHO, OBS ) VALUES ( ?, ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setTimestamp( 1, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 2, objeto.getRascunho()                                   );
			prepareSql.setString   ( 3, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getTipoRascunhoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE TIPO_RASCUNHO   "
					   + " SET  RASCUNHO     = ? "
					   + "    , OBS          = ? "
					   + " WHERE ID_RASCUNHO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getRascunho()         );
			prepareSql.setString( 2, objeto.getObs()              );
			prepareSql.setLong  ( 3, objeto.getId_tipo_rascunho() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_RASCUNHO ) AS MAX_ID FROM TIPO_RASCUNHO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelTipoRascunho getTipoRascunhoID( Long idTipoRascunho ) throws Exception {
		String sql = "SELECT * FROM TIPO_RASCUNHO WHERE ID_RASCUNHO = ?";
		
		ModelTipoRascunho tipoRascunho = new ModelTipoRascunho();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idTipoRascunho );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			
			tipoRascunho.setId_tipo_rascunho( resutado.getLong("ID_RASCUNHO")                                          );
			tipoRascunho.setRascunho        ( resutado.getString("RASCUNHO")                                           );
			tipoRascunho.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO")));
			tipoRascunho.setObs             ( resutado.getString("OBS")                                                );
		}
		return tipoRascunho;
	}
	
	public void deletarTipoRascunho(String id) throws SQLException {
		String sql = "DELETE FROM TIPO_RASCUNHO WHERE ID_RASCUNHO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
}
