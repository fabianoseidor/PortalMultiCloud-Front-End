package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelFaseContrato;

public class DAOFaseContrato {

	private Connection connection;
	
	public DAOFaseContrato() {
		connection = SingleConnectionBanco.getConnection();
	}
    
	public List<ModelFaseContrato> listaFaseContrato(){
		
		String sql = "SELECT * FROM FASE_CONTRATO ORDER BY ID_FASE_CONTRATO";
		List<ModelFaseContrato> modelFaseContratos = new ArrayList<ModelFaseContrato>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelFaseContrato modelFaseContrato = new ModelFaseContrato();
				modelFaseContrato.setId_fase_contrato( set.getLong("ID_FASE_CONTRATO")                                      );
				modelFaseContrato.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelFaseContrato.setFase_contrato   ( set.getString("FASE_CONTRATO")                                       );
				modelFaseContrato.setObservacao      ( set.getString("OBSERVACAO")                                          );

				modelFaseContratos.add(modelFaseContrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelFaseContratos;
	}
	
	public ModelFaseContrato gravarAtualizaRegistros( ModelFaseContrato objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO FASE_CONTRATO ( FASE_CONTRATO, OBSERVACAO ) VALUES ( ?, ? )";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getFase_contrato()  );
			prepareSql.setString( 2, objeto.getObservacao()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getFaseContratoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE FASE_CONTRATO        "
				 	   + "   SET FASE_CONTRATO    = ? "
					   + "     , OBSERVACAO       = ? "
					   + " WHERE ID_FASE_CONTRATO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getFase_contrato()    );
			prepareSql.setString( 2, objeto.getObservacao()       );
			prepareSql.setLong  ( 3, objeto.getId_fase_contrato() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_FASE_CONTRATO ) AS MAX_ID FROM FASE_CONTRATO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	public ModelFaseContrato getFaseContratoID( Long id ) throws Exception {
		String sql = "SELECT * FROM FASE_CONTRATO WHERE ID_FASE_CONTRATO = ?";
		
		ModelFaseContrato modelFaseContrato = new ModelFaseContrato();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelFaseContrato.setId_fase_contrato( resutado.getLong   ("ID_FASE_CONTRATO")                                   );			 
			modelFaseContrato.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modelFaseContrato.setFase_contrato   ( resutado.getString ("FASE_CONTRATO")                                      );
			modelFaseContrato.setObservacao      ( resutado.getString ("OBSERVACAO")                                         );
		}
		return modelFaseContrato;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM FASE_CONTRATO WHERE ID_FASE_CONTRATO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
   
}
