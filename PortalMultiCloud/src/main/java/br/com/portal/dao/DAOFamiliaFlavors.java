package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.*;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelFamiliaFlavors;

public class DAOFamiliaFlavors {
	private Connection connection;
	
	public DAOFamiliaFlavors() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelFamiliaFlavors> listaFamiliaFlavors( Long idNuvem ){
		
		String sql = "SELECT * FROM FAMILIA_FLAVORS WHERE ID_NUVEM = ?";
		List<ModelFamiliaFlavors> modelFamiliaFlavorses = new ArrayList<ModelFamiliaFlavors>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idNuvem );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelFamiliaFlavors modelFamiliaFlavors = new ModelFamiliaFlavors();

				modelFamiliaFlavors.setId_familia_flavors( set.getLong   ("ID_FAMILIA_FLAVORS"));			 
				modelFamiliaFlavors.setId_nuvem          ( set.getLong   ("ID_NUVEM")                                           );	
				modelFamiliaFlavors.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelFamiliaFlavors.setFamilia           ( set.getString ("FAMILIA"));
				modelFamiliaFlavors.setCpu               ( set.getString ("CPU"));
				modelFamiliaFlavors.setRam               ( set.getString ("RAM"));
				modelFamiliaFlavors.setObservacao        ( set.getString ("OBSERVACAO"));	

				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString ("VALOR") );
				modelFamiliaFlavors.setValor(dinheiro.format(valorReal));
				
				modelFamiliaFlavorses.add(modelFamiliaFlavors);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelFamiliaFlavorses;
	}
	
	public ModelFamiliaFlavors gravarAtualizaRegistros( ModelFamiliaFlavors objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO FAMILIA_FLAVORS ( ID_NUVEM, FAMILIA, CPU, RAM, VALOR, OBSERVACAO ) VALUES ( ?, ?, ?, ?, ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong  ( 1, objeto.getId_nuvem()   );
			prepareSql.setString( 2, objeto.getFamilia()    );
			prepareSql.setString( 3, objeto.getCpu()        );
			prepareSql.setString( 4, objeto.getRam()        );
			prepareSql.setString( 5, objeto.getValor()      );
			prepareSql.setString( 6, objeto.getObservacao() );

			prepareSql.execute();
			connection.commit();
			
			return this.getFamiliaFlavorsID( this.getMaxId( objeto.getId_nuvem() ) );
		}else {
			String sql = "UPDATE FAMILIA_FLAVORS        "
				 	   + "   SET ID_NUVEM           = ? "
					   + "     , FAMILIA            = ? "
					   + "     , CPU                = ? "
					   + "     , RAM                = ? "
					   + "     , VALOR              = ? "
					   + "     , OBSERVACAO         = ? "
					   + " WHERE ID_FAMILIA_FLAVORS = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong  ( 1, objeto.getId_nuvem()           );
			prepareSql.setString( 2, objeto.getFamilia()            );
			prepareSql.setString( 3, objeto.getCpu()                );
			prepareSql.setString( 4, objeto.getRam()                );
			prepareSql.setString( 5, objeto.getValor()              );
			prepareSql.setString( 6, objeto.getObservacao()         );
			prepareSql.setLong  ( 7, objeto.getId_familia_flavors() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}
	
	public Long getMaxId( Long idNuvem ) throws SQLException {
		String sql = "SELECT MAX( ID_FAMILIA_FLAVORS ) AS MAX_ID FROM FAMILIA_FLAVORS WHERE ID_NUVEM = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong  ( 1, idNuvem );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelFamiliaFlavors getFamiliaFlavorsID( Long id ) throws Exception {
		String sql = "SELECT * FROM FAMILIA_FLAVORS WHERE ID_FAMILIA_FLAVORS = ?";
		
		ModelFamiliaFlavors familiaFlavors = new ModelFamiliaFlavors();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			familiaFlavors.setId_familia_flavors( resutado.getLong   ("ID_FAMILIA_FLAVORS"));			 
			familiaFlavors.setId_nuvem          ( resutado.getLong   ("ID_NUVEM")                                           );	
			familiaFlavors.setDt_criacao        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			familiaFlavors.setFamilia           ( resutado.getString ("FAMILIA"));
			familiaFlavors.setCpu               ( resutado.getString ("CPU"));
			familiaFlavors.setRam               ( resutado.getString ("RAM"));
			familiaFlavors.setObservacao        ( resutado.getString ("OBSERVACAO"));	
			
			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resutado.getString ("VALOR") );
			familiaFlavors.setValor(dinheiro.format(valorReal));

		}
		return familiaFlavors;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM FAMILIA_FLAVORS WHERE ID_FAMILIA_FLAVORS = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
