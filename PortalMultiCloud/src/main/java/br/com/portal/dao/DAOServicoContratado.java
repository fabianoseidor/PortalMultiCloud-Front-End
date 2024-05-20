package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelServicoContratado;

public class DAOServicoContratado {
	
	private Connection connection;
	
	public DAOServicoContratado() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelServicoContratado> listaServicoContratadoSelect(){
		
		String sql = "SELECT * FROM SERVICO_CONTRATADO ORDER BY ID_SERVICO_CONTRATADO";
		List<ModelServicoContratado> modelServicoContratados = new ArrayList<ModelServicoContratado>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelServicoContratado modelServicoContratado = new ModelServicoContratado();
				modelServicoContratado.setId_servico_contratado( set.getLong  ("ID_SERVICO_CONTRATADO")                               );
				modelServicoContratado.setDesc_servico         ( set.getString("DESC_SERVICO")                                        );
				modelServicoContratado.setDt_criacao           ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelServicoContratado.setObs                  ( set.getString("OBS")                                                 );


				modelServicoContratados.add(modelServicoContratado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelServicoContratados;
	}
	
	public ModelServicoContratado gravarAtualizaRegistros( ModelServicoContratado objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO SERVICO_CONTRATADO ( DESC_SERVICO, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getDesc_servico() );
			prepareSql.setString( 2, objeto.getObs()          );

			prepareSql.execute();
			connection.commit();
			
			return this.getServicoContratadoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE SERVICO_CONTRATADO        "
				 	   + "   SET DESC_SERVICO          = ? "
					   + "     , OBS                   = ? "
					   + " WHERE ID_SERVICO_CONTRATADO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getDesc_servico()          );
			prepareSql.setString( 2, objeto.getObs()                   );
			prepareSql.setLong  ( 3, objeto.getId_servico_contratado() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_SERVICO_CONTRATADO ) AS MAX_ID FROM SERVICO_CONTRATADO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelServicoContratado getServicoContratadoID( Long id ) throws Exception {
		String sql = "SELECT * FROM SERVICO_CONTRATADO WHERE ID_SERVICO_CONTRATADO = ?";
		
		ModelServicoContratado servicoContratado = new ModelServicoContratado();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			servicoContratado.setId_servico_contratado( resutado.getLong   ("id_servico_contratado")                              );			 
			servicoContratado.setDt_criacao           ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			servicoContratado.setDesc_servico         ( resutado.getString ("desc_servico")                                       );
			servicoContratado.setObs                  ( resutado.getString ("OBS")                                                );
		}
		return servicoContratado;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM SERVICO_CONTRATADO WHERE ID_SERVICO_CONTRATADO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
