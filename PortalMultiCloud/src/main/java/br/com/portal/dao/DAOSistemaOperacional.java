package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModeliSistemaOperacional;

public class DAOSistemaOperacional {

	private Connection connection;
	public DAOSistemaOperacional() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModeliSistemaOperacional> getListaSistemaOperacional() {
		
		String sql = "SELECT * FROM SISTEMA_OPERACIONAL ORDER BY 1";
		List<ModeliSistemaOperacional> tipoSOs = new ArrayList<ModeliSistemaOperacional>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModeliSistemaOperacional tipoSO = new ModeliSistemaOperacional();
				tipoSO.setId_so              ( set.getLong("ID_SO")                                                 );
				tipoSO.setSistema_operacional( set.getString("SISTEMA_OPERACIONAL")                                 );
				tipoSO.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				tipoSO.setObs                ( set.getString("OBS")                                                 );
				tipoSOs.add(tipoSO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoSOs;
	}

	public ModeliSistemaOperacional gravarSistemaOperacional( ModeliSistemaOperacional objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO SISTEMA_OPERACIONAL ( SISTEMA_OPERACIONAL, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getSistema_operacional() );
			prepareSql.setString( 2, objeto.getObs()                 );

			prepareSql.execute();
			connection.commit();
			
			return this.getSistemaOperacionalID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE SISTEMA_OPERACIONAL     "
				 	   + "   SET SISTEMA_OPERACIONAL = ? "
					   + "     , OBS                 = ? "
					   + " WHERE ID_SO               = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getSistema_operacional() );
			prepareSql.setString( 2, objeto.getObs()                 );
			prepareSql.setLong  ( 3, objeto.getId_so()               );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_SO ) AS MAX_ID FROM SISTEMA_OPERACIONAL";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModeliSistemaOperacional getSistemaOperacionalID( Long id ) throws Exception {
		String sql = "SELECT * FROM SISTEMA_OPERACIONAL WHERE ID_SO = ?";
		
		ModeliSistemaOperacional tipoSO = new ModeliSistemaOperacional();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			tipoSO.setId_so              ( resutado.getLong   ("ID_SO")                                              );			 
			tipoSO.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			tipoSO.setSistema_operacional( resutado.getString ("SISTEMA_OPERACIONAL")                                );
			tipoSO.setObs                ( resutado.getString ("OBS")                                                );
		}
		return tipoSO;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM SISTEMA_OPERACIONAL WHERE ID_SO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
}
