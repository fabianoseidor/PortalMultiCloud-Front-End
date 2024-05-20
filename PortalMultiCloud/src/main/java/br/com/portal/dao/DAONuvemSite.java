package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelNuvemSite;
public class DAONuvemSite {
	
	private Connection connection;
	
	public DAONuvemSite() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelNuvemSite> listaNuvemSiteSelect(){
		
		String sql = "SELECT NU.ID_NUVEM, NU.MOME_PARCEIRO, SI.NOME FROM NUVEM AS NU FULL JOIN SITE  AS SI ON SI.ID_NUVEM = NU.ID_NUVEM ORDER BY NU.ID_NUVEM";
		List<ModelNuvemSite> modelNuvemSites = new ArrayList<ModelNuvemSite>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelNuvemSite modelNuvemSite = new ModelNuvemSite();
				modelNuvemSite.setId_nuvem(set.getLong("ID_NUVEM"));
				modelNuvemSite.setMome_parceiro(set.getString("MOME_PARCEIRO"));
				modelNuvemSite.setNome(set.getString("NOME"));
				modelNuvemSites.add(modelNuvemSite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelNuvemSites;
	}

	public List<ModelNuvemSite> listaNuvemSelect(){
		
		String sql = "SELECT NU.ID_NUVEM, NU.MOME_PARCEIRO FROM NUVEM NU ORDER BY NU.ID_NUVEM";
		List<ModelNuvemSite> modelNuvemSites = new ArrayList<ModelNuvemSite>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelNuvemSite modelNuvemSite = new ModelNuvemSite();
				modelNuvemSite.setId_nuvem(set.getLong("ID_NUVEM"));
				modelNuvemSite.setMome_parceiro(set.getString("MOME_PARCEIRO"));
				modelNuvemSites.add(modelNuvemSite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelNuvemSites;
	}
	
	
}
