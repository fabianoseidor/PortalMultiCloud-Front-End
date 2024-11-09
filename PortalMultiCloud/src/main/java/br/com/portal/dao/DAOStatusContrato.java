package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelStatusContrato;


public class DAOStatusContrato {

	private Connection connection;
	
	public DAOStatusContrato() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelStatusContrato> listaStatusContrato(){
		
		String sql = "SELECT * FROM STATUS_CONTRATO ORDER BY ID_STATUS_CONTRATO";
		List<ModelStatusContrato> modelStatusContratos = new ArrayList<ModelStatusContrato>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusContrato modelStatusContrato = new ModelStatusContrato();
				modelStatusContrato.setId_status_contrato( set.getLong("ID_STATUS_CONTRATO")                                    );
				modelStatusContrato.setStatus_contrato   ( set.getString("STATUS_CONTRATO")                                     );
				modelStatusContrato.setObservacao        ( set.getString("OBSERVACAO")                                          );
				modelStatusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelStatusContratos.add(modelStatusContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelStatusContratos;
	}

	public List<ModelStatusContrato> listaStatusContratoManutencao(){
		
		String sql = "SELECT * FROM STATUS_CONTRATO WHERE ID_STATUS_CONTRATO IN ( 1, 4, 5 ) ORDER BY ID_STATUS_CONTRATO";
		List<ModelStatusContrato> modelStatusContratos = new ArrayList<ModelStatusContrato>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusContrato modelStatusContrato = new ModelStatusContrato();
				modelStatusContrato.setId_status_contrato( set.getLong("ID_STATUS_CONTRATO")                                    );
				modelStatusContrato.setStatus_contrato   ( set.getString("STATUS_CONTRATO")                                     );
				modelStatusContrato.setObservacao        ( set.getString("OBSERVACAO")                                          );
				modelStatusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelStatusContratos.add(modelStatusContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelStatusContratos;
	}

	public List<ModelStatusContrato> listaStatusContratoManutencaoDesativados(){
		
		String sql = "SELECT * FROM STATUS_CONTRATO ORDER BY ID_STATUS_CONTRATO";
		List<ModelStatusContrato> modelStatusContratos = new ArrayList<ModelStatusContrato>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusContrato modelStatusContrato = new ModelStatusContrato();
				modelStatusContrato.setId_status_contrato( set.getLong("ID_STATUS_CONTRATO")                                    );
				modelStatusContrato.setStatus_contrato   ( set.getString("STATUS_CONTRATO")                                     );
				modelStatusContrato.setObservacao        ( set.getString("OBSERVACAO")                                          );
				modelStatusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelStatusContratos.add(modelStatusContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelStatusContratos;
	}
	
	
	
	public List<ModelStatusContrato> listaStatusContratoDescomissionamento(){
		
		String sql = "SELECT * FROM STATUS_CONTRATO WHERE ID_STATUS_CONTRATO IN ( 2, 3 ) ORDER BY ID_STATUS_CONTRATO";
		List<ModelStatusContrato> modelStatusContratos = new ArrayList<ModelStatusContrato>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusContrato modelStatusContrato = new ModelStatusContrato();
				modelStatusContrato.setId_status_contrato( set.getLong("ID_STATUS_CONTRATO")                                    );
				modelStatusContrato.setStatus_contrato   ( set.getString("STATUS_CONTRATO")                                     );
				modelStatusContrato.setObservacao        ( set.getString("OBSERVACAO")                                          );
				modelStatusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelStatusContratos.add(modelStatusContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelStatusContratos;
	}
	
	
	
	public List<ModelStatusContrato> listaStatusContratoCadastro(){
		
		String sql = "SELECT * FROM STATUS_CONTRATO WHERE ID_STATUS_CONTRATO IN( 1, 4 ) ORDER BY ID_STATUS_CONTRATO";
		List<ModelStatusContrato> modelStatusContratos = new ArrayList<ModelStatusContrato>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelStatusContrato modelStatusContrato = new ModelStatusContrato();
				modelStatusContrato.setId_status_contrato( set.getLong("ID_STATUS_CONTRATO")                                    );
				modelStatusContrato.setStatus_contrato   ( set.getString("STATUS_CONTRATO")                                     );
				modelStatusContrato.setObservacao        ( set.getString("OBSERVACAO")                                          );
				modelStatusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelStatusContratos.add(modelStatusContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelStatusContratos;
	}

	public ModelStatusContrato gravarAtualizaRegistros( ModelStatusContrato objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO STATUS_CONTRATO ( STATUS_CONTRATO, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getStatus_contrato()  );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getStatusContratoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE STATUS_CONTRATO        "
				 	   + "   SET STATUS_CONTRATO    = ? "
					   + "     , OBSERVACAO         = ? "
					   + " WHERE ID_STATUS_CONTRATO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getStatus_contrato()    );
			prepareSql.setString( 2, objeto.getObservacao()         );
			prepareSql.setLong  ( 3, objeto.getId_status_contrato() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_STATUS_CONTRATO ) AS MAX_ID FROM STATUS_CONTRATO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelStatusContrato getStatusContratoID( Long id ) throws Exception {
		String sql = "SELECT * FROM STATUS_CONTRATO WHERE ID_STATUS_CONTRATO = ?";
		
		ModelStatusContrato statusContrato = new ModelStatusContrato();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			statusContrato.setId_status_contrato( resutado.getLong   ("ID_STATUS_CONTRATO")                                 );			 
			statusContrato.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			statusContrato.setStatus_contrato   ( resutado.getString ("STATUS_CONTRATO")                                    );
			statusContrato.setObservacao        ( resutado.getString ("OBSERVACAO")                                         );
		}
		return statusContrato;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM STATUS_CONTRATO WHERE ID_STATUS_CONTRATO = ? AND ID_STATUS_CONTRATO NOT IN (1, 2, 3, 4, 5)";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );

//		prepareSql.executeUpdate();
//		connection.commit();
		
	}
	
}
