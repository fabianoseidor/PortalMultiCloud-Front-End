package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelFuncao;

public class DAOFuncao {
	private Connection connection;
	
	public DAOFuncao() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelFuncao> getListaFuncaContato() {
		
		String sql = "SELECT ID_FUNCAO, FUNCAO FROM FUNCAO ORDER BY  ID_FUNCAO";
		List<ModelFuncao> funcaos = new ArrayList<ModelFuncao>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelFuncao funcao  = new ModelFuncao();
				funcao.setId_funcao(set.getLong("ID_FUNCAO"));
				funcao.setFuncao(set.getString("FUNCAO"));

				funcaos.add(funcao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcaos;
	}
	
	public String getNomeFuncaContato(Long idFuncao)  {
		try {	
			   String sql = "SELECT ID_FUNCAO, FUNCAO FROM FUNCAO WHERE ID_FUNCAO = ?";
			   PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			   prepareSql.setLong( 1, idFuncao);
			   ResultSet resultado = prepareSql.executeQuery();
			
	
				while (resultado.next()) {
					return resultado.getString("FUNCAO");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return null;
		
		}
	

}
