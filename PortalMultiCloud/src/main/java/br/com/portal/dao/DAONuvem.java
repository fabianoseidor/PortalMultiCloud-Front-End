package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelNuvem;

public class DAONuvem {

	private Connection connection;
	
	public DAONuvem() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelNuvem> getListaNuvem() {
		
		String sql = "SELECT * FROM NUVEM ORDER BY 1";
		List<ModelNuvem> modelNuvens = new ArrayList<ModelNuvem>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelNuvem modelNuvem = new ModelNuvem();
				modelNuvem.setId_nuvem     ( set.getLong  ("ID_NUVEM")                                            );
				modelNuvem.setMome_parceiro( set.getString("MOME_PARCEIRO")                                       );
				modelNuvem.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelNuvem.setObservacao   ( set.getString("OBSERVACAO")                                          );

				modelNuvens.add(modelNuvem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelNuvens;
	}
	
	public ModelNuvem gravarAtualizaRegistros( ModelNuvem objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO NUVEM ( MOME_PARCEIRO, OBSERVACAO ) VALUES ( ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getMome_parceiro() );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getNuvemID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE NUVEM             "
				 	   + "   SET MOME_PARCEIRO = ? "
					   + "     , OBSERVACAO    = ? "
					   + " WHERE ID_NUVEM      = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getMome_parceiro() );
			prepareSql.setString( 2, objeto.getObservacao()    );
			prepareSql.setLong  ( 3, objeto.getId_nuvem()      );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_NUVEM ) AS MAX_ID FROM NUVEM";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelNuvem getNuvemID( Long id ) throws Exception {
		String sql = "SELECT * FROM NUVEM WHERE ID_NUVEM = ?";
		
		ModelNuvem nuvem = new ModelNuvem();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			nuvem.setId_nuvem     ( resutado.getLong   ("ID_NUVEM")                                           );			 
			nuvem.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			nuvem.setMome_parceiro( resutado.getString ("MOME_PARCEIRO")                                      );
			nuvem.setObservacao   ( resutado.getString ("OBSERVACAO")                                         );

		}
		return nuvem;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM NUVEM WHERE ID_NUVEM = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
	
}
