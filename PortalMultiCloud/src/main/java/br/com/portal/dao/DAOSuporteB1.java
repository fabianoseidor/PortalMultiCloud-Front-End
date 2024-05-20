package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelSuporteB1;

public class DAOSuporteB1 {
	private Connection connection;
	
	public DAOSuporteB1() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelSuporteB1> getListaSuporteB1() {
		
		String sql = "SELECT * FROM SUPORTE_B1 ORDER BY 1";
		List<ModelSuporteB1> comercias = new ArrayList<ModelSuporteB1>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSuporteB1 comercial = new ModelSuporteB1();
				comercial.setId_suporte_b1 ( set.getLong("ID_SUPORTE_B1")                                         );
				comercial.setNome_suporte  ( set.getString("NOME_SUPORTE")                                        );
				comercial.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CADASTRO")) );
				comercial.setEmail         ( set.getString("EMAIL")                                               );
				comercial.setTelefone      ( set.getString("TELEFONE")                                            );
				comercial.setObs           ( set.getString("OBS")                                                 );
				comercias.add(comercial);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comercias;
	}
	
	public ModelSuporteB1 gravarSuporteB1( ModelSuporteB1 objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO SUPORTE_B1 (DT_CADASTRO, NOME_SUPORTE, TELEFONE, EMAIL, OBS ) VALUES ( ?, ?, ?, ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setTimestamp( 1, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 2, objeto.getNome_suporte()                               );
			prepareSql.setString   ( 3, objeto.getTelefone()                                   );
			prepareSql.setString   ( 4, objeto.getEmail()                                      );
			prepareSql.setString   ( 5, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getSuporteB1ID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE SUPORTE_B1        "
					   + " SET  NOME_SUPORTE   = ? "
					   + "    , TELEFONE       = ? "
					   + "    , EMAIL          = ? "
					   + "    , OBS            = ? "
					   + " WHERE ID_SUPORTE_B1 = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getNome_suporte()  );
			prepareSql.setString( 2, objeto.getTelefone()      );
			prepareSql.setString( 3, objeto.getEmail()         );
			prepareSql.setString( 4, objeto.getObs()           );
			prepareSql.setLong  ( 5, objeto.getId_suporte_b1() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_SUPORTE_B1 ) AS MAX_ID FROM SUPORTE_B1";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelSuporteB1 getSuporteB1ID( Long idSuporteB1 ) throws Exception {
		String sql = "SELECT * FROM SUPORTE_B1 WHERE ID_SUPORTE_B1 = ?";
		
		ModelSuporteB1 suporteB1 = new ModelSuporteB1();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idSuporteB1 );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			suporteB1.setId_suporte_b1 ( resutado.getLong  ("ID_SUPORTE_B1")                                        );			 
			suporteB1.setDt_cadastro   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CADASTRO") ) );
			suporteB1.setNome_suporte  ( resutado.getString ("NOME_SUPORTE")                                       );
			suporteB1.setEmail         ( resutado.getString  ("EMAIL")                                              );
			suporteB1.setTelefone      ( resutado.getString  ("TELEFONE")                                           );
			suporteB1.setObs           ( resutado.getString  ("OBS")                                                );
		}
		return suporteB1;
	}
	
	public void deletarSuporteB1(String id) throws SQLException {
		String sql = "DELETE FROM SUPORTE_B1 WHERE ID_SUPORTE_B1 = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
}
