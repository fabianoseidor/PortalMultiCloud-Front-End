package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelCargo;

public class DAOCargo {

	private Connection connection;
	public DAOCargo() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelCargo> getListaCargo() {
		
		String sql = "SELECT ID_CARGO, CARGO FROM CARGOS ORDER BY 1";
		List<ModelCargo> cargos = new ArrayList<ModelCargo>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelCargo cargo = new ModelCargo();
				cargo.setId_cargo(set.getLong("id_cargo"));
				cargo.setCargo(set.getString("cargo"));
				cargos.add(cargo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cargos;
	}
}
