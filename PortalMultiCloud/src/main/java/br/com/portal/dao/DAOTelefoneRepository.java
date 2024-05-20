package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelTelefone;

public class DAOTelefoneRepository {
	
	private Connection connection;
	DAOClienteRepository daoClienteRepository = new DAOClienteRepository();
	DAOFuncao daoFuncao = new DAOFuncao();
	/*
	 * 
	 * 
	 * */
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	/*
	 * 
	 * 
	 * */
	public ModelTelefone gravaTelefone( ModelTelefone modelTelefone ) throws SQLException {
		if(modelTelefone.isNovo()) {
			String sql = "INSERT INTO TELEFONE (ID_CLIENTE, NOME_CONTATO, TELEFONE, EMAIL, ID_FUNCAO, OBS ) VALUES (? ,  ? , ? , ? , ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong  ( 1, modelTelefone.getCliente().getId_cliente() );
			prepareSql.setString( 2, modelTelefone.getNome_contato()            );	
			prepareSql.setString( 3, modelTelefone.getTelefone()                );	
			prepareSql.setString( 4, modelTelefone.getEmail()                   );	
			prepareSql.setLong  ( 5, modelTelefone.getId_funcao()               );
			prepareSql.setString( 6, modelTelefone.getObs()                     );
			
			prepareSql.execute();
			connection.commit();
			
			modelTelefone.setId_telefone(this.getMaxIdFoneCliente(modelTelefone.getCliente().getId_cliente()));

		}else {
			String sql = "UPDATE TELEFONE SET NOME_CONTATO = ?, TELEFONE = ? ,EMAIL = ? ,ID_FUNCAO = ?, OBS = ? WHERE ID_TELEFONE =?";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			prepareSql.setString( 1, modelTelefone.getNome_contato());
			prepareSql.setString( 2, modelTelefone.getTelefone()    );
			prepareSql.setString( 3, modelTelefone.getEmail()       );
			prepareSql.setLong  ( 4, modelTelefone.getId_funcao()   );
			prepareSql.setString( 5, modelTelefone.getObs()         );
			prepareSql.setLong  ( 6, modelTelefone.getId_telefone() );
			
			prepareSql.executeUpdate();
			connection.commit();
		}
		
		return this.getFoneID(modelTelefone.getId_telefone());
	}
	/*
	 * 
	 * 
	 * */
	public void deleteFone( Long idFone ) throws SQLException {
		String sql = "DELETE TELEFONE WHERE ID_TELEFONE = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong  ( 1, idFone );
		
		prepareSql.executeUpdate();
		connection.commit();
	}
	/*
	 * 
	 * 
	 * */
	public List<ModelTelefone> listaFone(Long idCliente, Integer offsetBegin, Integer offsetEnd ) throws SQLException{
		
		List<ModelTelefone> modelTelefones = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM TELEFONE WHERE ID_CLIENTE = ? ORDER BY ID_TELEFONE OFFSET " + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idCliente );
		
		ResultSet resultado = resultadoSql.executeQuery();
		
		while(resultado.next()) {
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId_telefone ( resultado.getLong("ID_TELEFONE")                                   );
			modelTelefone.setNome_contato( resultado.getString("NOME_CONTATO")                                );
			modelTelefone.setTelefone    ( resultado.getString("TELEFONE")                                    );
			modelTelefone.setEmail       ( resultado.getString("EMAIL")                                       );
			modelTelefone.setCliente     ( daoClienteRepository.getClienteID(resultado.getLong("ID_CLIENTE")) );
			modelTelefone.setId_funcao   ( resultado.getLong("ID_FUNCAO")                                     );
			modelTelefone.setNomeFuncao  ( daoFuncao.getNomeFuncaContato(modelTelefone.getId_funcao())        );
			modelTelefone.setObs         ( resultado.getString("OBS")                         );
			modelTelefones.add(modelTelefone);
		}
		
		return modelTelefones;
	}
	/*
	 * 
	 * 
	 * */
	public ModelTelefone getFoneID(Long idFone ) throws SQLException{
		
		ModelTelefone modelTelefone = new ModelTelefone();
		
		String sql = "SELECT * FROM TELEFONE WHERE ID_TELEFONE = ?";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idFone );
		
		ResultSet resultado = resultadoSql.executeQuery();

		while(resultado.next()) {
			modelTelefone.setId_telefone ( resultado.getLong("ID_TELEFONE")                                   );
			modelTelefone.setNome_contato( resultado.getString("NOME_CONTATO")                                );
			modelTelefone.setTelefone    ( resultado.getString("TELEFONE")                                    );
			modelTelefone.setEmail       ( resultado.getString("EMAIL")                                       );
			modelTelefone.setCliente     ( daoClienteRepository.getClienteID(resultado.getLong("ID_CLIENTE")) );
			modelTelefone.setId_funcao   ( resultado.getLong("ID_FUNCAO")                                     );
			modelTelefone.setObs         ( resultado.getString("OBS")                                         );
			modelTelefone.setNomeFuncao  ( daoFuncao.getNomeFuncaContato(modelTelefone.getId_funcao())        );
		}
		
		return modelTelefone;
	}
	/*
	 * 
	 * 
	 * */
	public ModelTelefone getClineteID(Long idCliente ) throws SQLException{
		
		ModelTelefone modelTelefone = new ModelTelefone();
		
		String sql = "SELECT TOP (1) ID_TELEFONE ,ID_CLIENTE ,NOME_CONTATO ,TELEFONE ,EMAIL ,ID_FUNCAO  FROM TELEFONE where ID_CLIENTE = ?";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idCliente );
		
		ResultSet resultado = resultadoSql.executeQuery();

		while(resultado.next()) {
			modelTelefone.setCliente( daoClienteRepository.getClienteID( resultado.getLong("ID_CLIENTE")) );
			return modelTelefone;
		}

		return null;
	}
	/*
	 * 
	 * 
	 * */
	public Boolean isTelExist( Long idCliente) throws SQLException {
		String sql = "SELECT COUNT( 1 ) AS IS_EXIST FROM TELEFONE WHERE ID_CLIENTE = ?";
		int is_exist = 0;
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idCliente );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) is_exist = resutado.getInt("IS_EXIST");
		
    	if(is_exist > 0) return true; else return false;
    	
 	}
	/*
	 * 
	 * 
	 * */
	public Long getMaxIdFoneCliente( Long idCliente) throws SQLException {
		String sql = "SELECT MAX( ID_TELEFONE ) AS MAX_ID_FONE FROM TELEFONE WHERE ID_CLIENTE = ?";
		
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, idCliente );
		
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_FONE");
			
		return null;
	}
	/*
	 * 
	 * 
	 * */
	public Integer getTotalPag(Long id, Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(ID_CLIENTE) AS COUNT_ID_CLIENTE FROM TELEFONE WHERE ID_CLIENTE = ?";
		Double totalId = 0.0;
		Double pagina  = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		resultadoSql.setLong  ( 1, id );

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) totalId = resutado.getDouble("COUNT_ID_CLIENTE");
		
		pagina = totalId / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}

	
		
}
