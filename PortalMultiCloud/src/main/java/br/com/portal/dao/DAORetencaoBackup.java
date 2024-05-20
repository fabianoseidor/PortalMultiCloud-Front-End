package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelRetencaoBackup;

public class DAORetencaoBackup {
	private Connection connection;
	
	public DAORetencaoBackup() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelRetencaoBackup> getListaRetencaoBackup() {
		
		String sql = "SELECT * FROM RETENCAO_BACKUP ORDER BY 1";
		List<ModelRetencaoBackup> retencaoBackups = new ArrayList<ModelRetencaoBackup>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelRetencaoBackup retencaoBackup = new ModelRetencaoBackup();
				retencaoBackup.setId_retencao_backup( set.getLong("ID_RETENCAO_BACKUP")                                    );
				retencaoBackup.setRetencao_backup   ( set.getString("RETENCAO_BACKUP")                                     );
				retencaoBackup.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				retencaoBackup.setObservacao        ( set.getString("OBS")                                                 );

				retencaoBackups.add(retencaoBackup);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retencaoBackups;
	}

	public ModelRetencaoBackup gravarAtualizaRegistros( ModelRetencaoBackup objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO RETENCAO_BACKUP ( RETENCAO_BACKUP, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getRetencao_backup()  );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getRetencaoBackupID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE RETENCAO_BACKUP        "
				 	   + "   SET RETENCAO_BACKUP    = ? "
					   + "     , OBS       = ? "
					   + " WHERE ID_RETENCAO_BACKUP = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getRetencao_backup()    );
			prepareSql.setString( 2, objeto.getObservacao()         );
			prepareSql.setLong  ( 3, objeto.getId_retencao_backup() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_RETENCAO_BACKUP ) AS MAX_ID FROM RETENCAO_BACKUP";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelRetencaoBackup getRetencaoBackupID( Long id ) throws Exception {
		String sql = "SELECT * FROM RETENCAO_BACKUP WHERE ID_RETENCAO_BACKUP = ?";
		
		ModelRetencaoBackup retencaoBackup = new ModelRetencaoBackup();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			retencaoBackup.setId_retencao_backup( resutado.getLong   ("ID_RETENCAO_BACKUP")                                 );			 
			retencaoBackup.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			retencaoBackup.setRetencao_backup   ( resutado.getString ("RETENCAO_BACKUP")                                    );
			retencaoBackup.setObservacao        ( resutado.getString ("OBS")                                                );
		}
		return retencaoBackup;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM RETENCAO_BACKUP WHERE ID_RETENCAO_BACKUP = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
}
