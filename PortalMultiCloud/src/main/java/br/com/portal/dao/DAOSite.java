package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelSite;

public class DAOSite {

	private Connection connection;
	DAOContratoRepository daoContratoRepository = new DAOContratoRepository();
	
	public DAOSite() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelSite> listaSiteSelect( Long idNuvem , Long idCliente ) throws SQLException{
		
		String sql = "SELECT * FROM SITE WHERE ID_NUVEM = " + idNuvem + " ORDER BY ID_SITE";
		List<ModelSite> modelSites = new ArrayList<ModelSite>();
		
		Long idSiteClie = daoContratoRepository.getSiteCliente( idCliente );
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSite modelSite = new ModelSite();
				modelSite.setId_site(set.getLong("ID_SITE"));
				modelSite.setId_nuvem(set.getLong("ID_NUVEM"));
				modelSite.setNome(set.getString("NOME"));
				if( idSiteClie == set.getLong("ID_SITE") ) 
					modelSite.setSelecionado(1);
				else 
					modelSite.setSelecionado(0);
					
				modelSites.add(modelSite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelSites;
	}

	public List<ModelSite> listaSiteSelectManutncao( Long idNuvem ) throws SQLException{
		
		String sql = "SELECT * FROM SITE WHERE ID_NUVEM = " + idNuvem + " ORDER BY ID_SITE";
		List<ModelSite> modelSites = new ArrayList<ModelSite>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelSite modelSite = new ModelSite();
				modelSite.setId_site   ( set.getLong("ID_SITE")                                               );
				modelSite.setId_nuvem  ( set.getLong("ID_NUVEM")                                              );
				modelSite.setNome      ( set.getString("NOME")                                                );
				modelSite.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				modelSite.setObservacao( set.getString("OBSERVACAO")                                          );
				modelSites.add(modelSite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelSites;
	}

	public ModelSite gravarAtualizaRegistros( ModelSite objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO SITE ( ID_NUVEM, NOME, OBSERVACAO ) VALUES ( ?, ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong  ( 1, objeto.getId_nuvem()   );
			prepareSql.setString( 2, objeto.getNome()       );
			prepareSql.setString( 3, objeto.getObservacao() );

			prepareSql.execute();
			connection.commit();
			
			return this.getSiteID( this.getMaxId( objeto.getId_nuvem() ) );
		}else {
			String sql = "UPDATE SITE           "
				 	   + "   SET ID_NUVEM   = ? "
					   + "     , NOME       = ? "
					   + "     , OBSERVACAO = ? "
					   + " WHERE ID_SITE    = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong  ( 1, objeto.getId_nuvem()   );
			prepareSql.setString( 2, objeto.getNome()       );
			prepareSql.setString( 3, objeto.getObservacao() );
			prepareSql.setLong  ( 4, objeto.getId_site()    );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( Long idNuvem ) throws SQLException {
		String sql = "SELECT MAX( ID_SITE ) AS MAX_ID FROM SITE WHERE ID_NUVEM = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong  ( 1, idNuvem );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModelSite getSiteID( Long id ) throws Exception {
		String sql = "SELECT * FROM SITE WHERE ID_SITE = ?";
		
		ModelSite site = new ModelSite();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			site.setId_site      ( resutado.getLong   ("ID_SITE")                                            );			 
			site.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			site.setNome         ( resutado.getString ("NOME")                                               );
			site.setObservacao   ( resutado.getString ("OBSERVACAO")                                         );
			site.setId_nuvem     ( resutado.getLong   ("ID_NUVEM")                                           );	

		}
		return site;
	}

	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM SITE WHERE ID_SITE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
