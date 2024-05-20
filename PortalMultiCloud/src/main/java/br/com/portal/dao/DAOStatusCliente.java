package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelStatusCliente;

public class DAOStatusCliente {
	private Connection connection;
	public DAOStatusCliente() {
		connection = SingleConnectionBanco.getConnection();
	}
	

	public List<ModelStatusCliente> getListaStatusCliente() {
		
		String sql = "SELECT * FROM STATUS_CLIENTE ORDER BY ID_STATUS";
		List<ModelStatusCliente> statusClientes = new ArrayList<ModelStatusCliente>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusCliente statusCliente = new ModelStatusCliente();
				statusCliente.setId_status ( set.getLong("ID_STATUS")                                             );
				statusCliente.setStatus    ( set.getString("STATUS")                                              );
				statusCliente.setObservacao( set.getString("OBSERVACAO")                                          );
				statusCliente.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				
				
				statusClientes.add(statusCliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statusClientes;
	}
	
	
	public String getNomeStatus( Long idStatus) throws SQLException {
		String sql = "SELECT ID_STATUS ,STATUS FROM STATUS_CLIENTE WHERE ID_STATUS = ?";
		
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idStatus );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getString("STATUS");

		return null;
	}

	public ModelStatusCliente gravarAtualizaRegistros( ModelStatusCliente objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO STATUS_CLIENTE ( STATUS, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getStatus()     );
			prepareSql.setString( 2, objeto.getObservacao() );

			prepareSql.execute();
			connection.commit();
			
			return this.getStatusClienteID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE STATUS_CLIENTE "
				 	   + "   SET STATUS     = ? "
					   + "     , OBSERVACAO = ? "
					   + " WHERE ID_STATUS  = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getStatus()     );
			prepareSql.setString( 2, objeto.getObservacao() );
			prepareSql.setLong  ( 3, objeto.getId_status()  );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_STATUS ) AS MAX_ID FROM STATUS_CLIENTE";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelStatusCliente getStatusClienteID( Long id ) throws Exception {
		String sql = "SELECT * FROM STATUS_CLIENTE WHERE ID_STATUS = ?";
		
		ModelStatusCliente statusCliente = new ModelStatusCliente();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			statusCliente.setId_status ( resutado.getLong   ("ID_STATUS")                                          );			 
			statusCliente.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			statusCliente.setStatus    ( resutado.getString ("STATUS")                                             );
			statusCliente.setObservacao( resutado.getString ("OBSERVACAO")                                         );
		}
		return statusCliente;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM STATUS_CLIENTE WHERE ID_STATUS = ? AND ID_STATUS NOT IN (1, 2, 3)";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
