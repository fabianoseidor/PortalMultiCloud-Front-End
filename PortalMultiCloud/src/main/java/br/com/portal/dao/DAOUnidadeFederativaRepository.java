package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelUnidadeFederativa;

public class DAOUnidadeFederativaRepository {

	private Connection connection;
	public DAOUnidadeFederativaRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	

	public List<ModelUnidadeFederativa> getListaUnidadeFederativa() {
		
		String sql = "SELECT SIGLA, UNIDADE_FEDERATIVA FROM ESTADO ORDER BY SIGLA";
		List<ModelUnidadeFederativa> unidadeFederativas = new ArrayList<ModelUnidadeFederativa>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelUnidadeFederativa federativa = new ModelUnidadeFederativa();
				federativa.setSigla(set.getString("SIGLA"));
				federativa.setUnidade_federativa(set.getString("UNIDADE_FEDERATIVA"));
				unidadeFederativas.add(federativa);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unidadeFederativas;
	}
}
