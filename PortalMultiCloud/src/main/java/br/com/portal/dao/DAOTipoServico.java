package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelTipoServico;

public class DAOTipoServico {
	
	private Connection connection;
	
	public DAOTipoServico() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelTipoServico> getListaTipoServicos() {
		
		String sql = "SELECT * FROM TIPO_SERVICO ORDER BY 1";
		List<ModelTipoServico> tipoServicos = new ArrayList<ModelTipoServico>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoServico tipoServico = new ModelTipoServico();
				
				tipoServico.setId_tipo_servico( set.getLong("ID_TIPO_SERVICO")                                       );
				tipoServico.setDt_criacao     ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				tipoServico.setTipo_servico   ( set.getString("TIPO_SERVICO")                                        );
				tipoServico.setObs            ( set.getString("OBS")                                                 );
				tipoServicos.add(tipoServico);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoServicos;
	}

	public ModelTipoServico gravarAtualizaRegistros( ModelTipoServico objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO TIPO_SERVICO ( TIPO_SERVICO, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getTipo_servico() );
			prepareSql.setString( 2, objeto.getObs()          );

			prepareSql.execute();
			connection.commit();
			
			return this.getTipoServicoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE TIPO_SERVICO        "
				 	   + "   SET TIPO_SERVICO    = ? "
					   + "     , OBS             = ? "
					   + " WHERE ID_TIPO_SERVICO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getTipo_servico()    );
			prepareSql.setString( 2, objeto.getObs()             );
			prepareSql.setLong  ( 3, objeto.getId_tipo_servico() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_TIPO_SERVICO ) AS MAX_ID FROM TIPO_SERVICO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelTipoServico getTipoServicoID( Long id ) throws Exception {
		String sql = "SELECT * FROM TIPO_SERVICO WHERE ID_TIPO_SERVICO = ?";
		
		ModelTipoServico tipoServico = new ModelTipoServico();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			tipoServico.setId_tipo_servico( resutado.getLong   ("ID_TIPO_SERVICO")                                    );			 
			tipoServico.setDt_criacao     ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			tipoServico.setTipo_servico   ( resutado.getString ("TIPO_SERVICO")                                       );
			tipoServico.setObs            ( resutado.getString ("OBS")                                                );
		}
		return tipoServico;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM TIPO_SERVICO WHERE ID_TIPO_SERVICO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
