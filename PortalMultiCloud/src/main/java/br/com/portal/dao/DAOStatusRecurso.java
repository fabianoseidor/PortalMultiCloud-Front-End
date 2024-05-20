package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelStatusRecurso;

public class DAOStatusRecurso {
	
	private Connection connection;
	
	public DAOStatusRecurso() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelStatusRecurso> getListaStatus() {
		
		String sql = "SELECT * FROM STATUS_RECURSO ORDER BY 1";
		List<ModelStatusRecurso> modelStatusRecursos = new ArrayList<ModelStatusRecurso>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelStatusRecurso status = new ModelStatusRecurso();
				status.setId_status_recurso( set.getLong("ID_STATUS_RECURSO")                                     );
				status.setStatus_recurso   ( set.getString("STATUS_RECURSO")                                      );
				status.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				status.setObservacao       ( set.getString("OBSERVACAO")                                          );
				modelStatusRecursos.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelStatusRecursos;
	}

	public ModelStatusRecurso gravarAtualizaRegistros( ModelStatusRecurso objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO STATUS_RECURSO ( STATUS_RECURSO, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getStatus_recurso()  );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getStatusRecursoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE STATUS_RECURSO        "
				 	   + "   SET STATUS_RECURSO    = ? "
					   + "     , OBSERVACAO        = ? "
					   + " WHERE ID_STATUS_RECURSO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getStatus_recurso()    );
			prepareSql.setString( 2, objeto.getObservacao()        );
			prepareSql.setLong  ( 3, objeto.getId_status_recurso() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_STATUS_RECURSO ) AS MAX_ID FROM STATUS_RECURSO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelStatusRecurso getStatusRecursoID( Long id ) throws Exception {
		String sql = "SELECT * FROM STATUS_RECURSO WHERE ID_STATUS_RECURSO = ?";
		
		ModelStatusRecurso statusRecurso = new ModelStatusRecurso();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			statusRecurso.setId_status_recurso( resutado.getLong   ("ID_STATUS_RECURSO")                                  );			 
			statusRecurso.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			statusRecurso.setStatus_recurso   ( resutado.getString ("STATUS_RECURSO")                                     );
			statusRecurso.setObservacao       ( resutado.getString ("OBSERVACAO")                                         );
		}
		return statusRecurso;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM STATUS_RECURSO WHERE ID_STATUS_RECURSO = ? AND ID_STATUS NOT IN (1, 2, 3)";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
