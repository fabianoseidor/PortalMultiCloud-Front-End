package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelCicloUpdate;
public class DAOCicloUpdate {

	private Connection connection;
	
	public DAOCicloUpdate() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelCicloUpdate> listaCicloUpdate(){
		
		String sql = "SELECT * FROM CICLO_UPDATE ORDER BY ID_CICLO_UPDATE";
		List<ModelCicloUpdate> modelCicloUpdates = new ArrayList<ModelCicloUpdate>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelCicloUpdate modelCicloUpdate = new ModelCicloUpdate();
				modelCicloUpdate.setId_ciclo_update ( set.getLong("ID_CICLO_UPDATE")                                       );
				modelCicloUpdate.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelCicloUpdate.setDescricao       ( set.getString("DESCRICAO")                                           );
				modelCicloUpdate.setObservacao      ( set.getString("OBSERVACAO")                                          );

				modelCicloUpdates.add(modelCicloUpdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelCicloUpdates;
	}
	
	
	public ModelCicloUpdate gravarAtualizaRegistros( ModelCicloUpdate objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO CICLO_UPDATE ( DESCRICAO, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getDescricao()  );
			prepareSql.setString( 2, objeto.getObservacao() );

			prepareSql.execute();
			connection.commit();
			
			return this.getCicloUpdateID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE CICLO_UPDATE        "
				 	   + "   SET DESCRICAO       = ? "
					   + "     , OBSERVACAO      = ? "
					   + " WHERE ID_CICLO_UPDATE = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getDescricao()       );
			prepareSql.setString( 2, objeto.getObservacao()      );
			prepareSql.setLong  ( 3, objeto.getId_ciclo_update() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_CICLO_UPDATE ) AS MAX_ID FROM CICLO_UPDATE";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelCicloUpdate getCicloUpdateID( Long id ) throws Exception {
		String sql = "SELECT * FROM CICLO_UPDATE WHERE ID_CICLO_UPDATE = ?";
		
		ModelCicloUpdate cicloUpdate = new ModelCicloUpdate();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			cicloUpdate.setId_ciclo_update( resutado.getLong   ("ID_CICLO_UPDATE")                                    );			 
			cicloUpdate.setDt_criacao     ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			cicloUpdate.setDescricao      ( resutado.getString ("DESCRICAO")                                          );
			cicloUpdate.setObservacao     (resutado.getString  ("OBSERVACAO")                                         );
		}
		return cicloUpdate;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM CICLO_UPDATE WHERE ID_CICLO_UPDATE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}


}
