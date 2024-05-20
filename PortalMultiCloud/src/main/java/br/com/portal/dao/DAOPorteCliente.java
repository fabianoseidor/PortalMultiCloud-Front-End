package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelPorteCliente;

public class DAOPorteCliente {

	private Connection connection;
	public DAOPorteCliente() {
		connection = SingleConnectionBanco.getConnection();
	}
	

	public List<ModelPorteCliente> getListaPorteEmpresaCliente() {
		
		String sql = "SELECT * FROM PORTE_EMPRESA_CLIENTE ORDER BY ID_PORTE";
		List<ModelPorteCliente> porteEmpresas = new ArrayList<ModelPorteCliente>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelPorteCliente porteEmpresa = new ModelPorteCliente();
				porteEmpresa.setId_porte     ( set.getLong("ID_PORTE")                                              );
				porteEmpresa.setPorte_empresa( set.getString("PORTE_EMPRESA")                                       );
				porteEmpresa.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				porteEmpresa.setObservacao   ( set.getString("OBS")                                                 );
				porteEmpresas.add(porteEmpresa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return porteEmpresas;
	}

	public ModelPorteCliente gravarAtualizaRegistros( ModelPorteCliente objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO PORTE_EMPRESA_CLIENTE ( PORTE_EMPRESA, OBS ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getPorte_empresa()  );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getPorteEmpresaID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE PORTE_EMPRESA_CLIENTE "
				 	   + "   SET PORTE_EMPRESA = ?     "
					   + "     , OBS           = ?     "
					   + " WHERE ID_PORTE      = ?     ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getPorte_empresa() );
			prepareSql.setString( 2, objeto.getObservacao()    );
			prepareSql.setLong  ( 3, objeto.getId_porte()      );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_PORTE ) AS MAX_ID FROM PORTE_EMPRESA_CLIENTE";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	public ModelPorteCliente getPorteEmpresaID( Long id ) throws Exception {
		String sql = "SELECT * FROM PORTE_EMPRESA_CLIENTE WHERE ID_PORTE = ?";
		
		ModelPorteCliente porteEmpresa = new ModelPorteCliente();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			porteEmpresa.setId_porte     ( resutado.getLong   ("ID_PORTE")                                           );			 
			porteEmpresa.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			porteEmpresa.setPorte_empresa( resutado.getString ("PORTE_EMPRESA")                                      );
			porteEmpresa.setObservacao   ( resutado.getString ("OBS")                                                );
		}
		return porteEmpresa;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM PORTE_EMPRESA_CLIENTE WHERE ID_PORTE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
