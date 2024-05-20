package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelTipoAditivo;

public class DAOTipoAditivo {

	private Connection connection;
	
	public DAOTipoAditivo() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelTipoAditivo> listaTipoAditivo(){
		
		String sql = "SELECT * FROM TIPO_ADITIVO ORDER BY ID_TIPO_ADITIVO";
		List<ModelTipoAditivo> modelTipoAditivos = new ArrayList<ModelTipoAditivo>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelTipoAditivo modelTipoAditivo = new ModelTipoAditivo();
				modelTipoAditivo.setId_tipo_aditivo ( set.getLong("ID_TIPO_ADITIVO")                                       );
				modelTipoAditivo.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelTipoAditivo.setDesc_tipo_ditivo( set.getString("DESC_TIPO_DITIVO")                                    );
				modelTipoAditivo.setObservacao      ( set.getString("OBSERVACAO")                                          );

				modelTipoAditivos.add(modelTipoAditivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelTipoAditivos;
	}
	
	
	public ModelTipoAditivo gravarAtualizaRegistros( ModelTipoAditivo objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO TIPO_ADITIVO ( DESC_TIPO_DITIVO, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getDesc_tipo_ditivo()  );
			prepareSql.setString( 2, objeto.getObservacao()        );

			prepareSql.execute();
			connection.commit();
			
			return this.getTipoAditivoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE TIPO_ADITIVO         "
				 	   + "   SET DESC_TIPO_DITIVO = ? "
					   + "     , OBSERVACAO       = ? "
					   + " WHERE ID_TIPO_ADITIVO  = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getDesc_tipo_ditivo());
			prepareSql.setString( 2, objeto.getObservacao()      );
			prepareSql.setLong  ( 3, objeto.getId_tipo_aditivo() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_TIPO_ADITIVO ) AS MAX_ID FROM TIPO_ADITIVO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelTipoAditivo getTipoAditivoID( Long id ) throws Exception {
		String sql = "SELECT * FROM TIPO_ADITIVO WHERE ID_TIPO_ADITIVO = ?";
		
		ModelTipoAditivo tipoAditivo = new ModelTipoAditivo();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			tipoAditivo.setId_tipo_aditivo ( resutado.getLong   ("ID_TIPO_ADITIVO")                                    );			 
			tipoAditivo.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			tipoAditivo.setDesc_tipo_ditivo( resutado.getString ("DESC_TIPO_DITIVO")                                   );
			tipoAditivo.setObservacao      ( resutado.getString ("OBSERVACAO")                                         );
		}
		return tipoAditivo;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM TIPO_ADITIVO WHERE ID_TIPO_ADITIVO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}


}
