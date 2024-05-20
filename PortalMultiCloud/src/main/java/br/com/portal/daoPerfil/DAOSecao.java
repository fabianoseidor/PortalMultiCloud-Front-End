package br.com.portal.daoPerfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.modelPerfil.ModelSecao;

public class DAOSecao {
	private Connection connection;
	
	public DAOSecao() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelSecao gravacao( ModelSecao objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO SECAO( NOME_SECAO, DT_CRIACAO, USER_CADASTRO, OBS ) VALUES( ?, ?, ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString   ( 1, objeto.getNome_secao()                                 );
			prepareSql.setTimestamp( 2, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 3, objeto.getUser_cadastro()                              );
			prepareSql.setString   ( 4, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getSecaoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE SECAO           "
					+ "   SET  NOME_SECAO    = ? "
					+ "      , USER_CADASTRO = ? "
					+ "      , OBS           = ? "
					+ " WHERE ID_SECAO = ?       ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getNome_secao()    );
			prepareSql.setString( 2, objeto.getUser_cadastro() );
			prepareSql.setString( 3, objeto.getObs()           );
			prepareSql.setLong  ( 4, objeto.getId_secao()      );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}

	public ModelSecao getSecaoID( Long idSecao ) throws Exception {
		String sql = "SELECT * FROM SECAO WHERE ID_SECAO = ?";
		
		ModelSecao modelSecao = new ModelSecao();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idSecao );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelSecao.setId_secao     ( resutado.getLong    ("ID_SECAO")                                          );			 
			modelSecao.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modelSecao.setNome_secao   ( resutado.getString  ("NOME_SECAO")                                        );
			modelSecao.setUser_cadastro( resutado.getString  ("USER_CADASTRO")                                     );			
			modelSecao.setObs          ( resutado.getString  ("OBS")                                               );
		}
		return modelSecao;
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_SECAO ) AS MAX_ID FROM SECAO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public List<ModelSecao> getListaSecao() {
		
		String sql = "SELECT * FROM SECAO ORDER BY 1";
		List<ModelSecao> secaos = new ArrayList<ModelSecao>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSecao secao = new ModelSecao();
				secao.setId_secao     ( set.getLong    ("ID_SECAO")                                          );			 
				secao.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				secao.setNome_secao   ( set.getString  ("NOME_SECAO")                                        );
				secao.setUser_cadastro( set.getString  ("USER_CADASTRO")                                     );
				secao.setObs          ( set.getString  ("OBS")                                               );
				secaos.add(secao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return secaos;
	}
	
	public void deletarSecao(String id) throws SQLException {
		String sql = "DELETE FROM SECAO WHERE ID_SECAO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
