package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	/*
	 * 
	 * 
	 * */
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	/*
	 * 
	 * 
	 * */
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
	    if(objeto.isNovo()) {
			String sql = "INSERT INTO COLABORADORES ( NOME, LOGIN, SENHA, EMAIL, ID_CARGO, CEP, ENDERECO, BAIRRO, NUMERO  , "
					+ "                              COMPLEMENTO,  CIDADE, ESTADO, PAIS, CPF, TELEFONE, CELULAR, USERADMIN, "
					+ "                              PRIMEIRO_ACESSO )"
					+ "                 VALUES   ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )  ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getNome()        );
			prepareSql.setString( 2, objeto.getLogin()       );
			prepareSql.setString( 3, objeto.getSenha()       );
			prepareSql.setString( 4, objeto.getEmail()       );
			prepareSql.setLong  ( 5, objeto.getId_cargo()    );
			prepareSql.setString( 6, objeto.getCep()         );
			prepareSql.setString( 7, objeto.getEndereco()    );
			prepareSql.setString( 8, objeto.getBairro()      );
			prepareSql.setString( 9, objeto.getNumero()      );
			prepareSql.setString(10, objeto.getComplemento() );
			prepareSql.setString(11, objeto.getCidade()      );
			prepareSql.setString(12, objeto.getEstado()      );
			prepareSql.setString(13, objeto.getPais()        );
			prepareSql.setString(14, objeto.getCpf()         );
			prepareSql.setString(15, objeto.getTelefone()    );
			prepareSql.setString(16, objeto.getCelular()     );
			prepareSql.setString(17, objeto.getUseradmin()   );
			prepareSql.setInt   (18, 0                       );

			prepareSql.execute();
			connection.commit();

			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update COLABORADORES set FOTOUSER =?, EXTENSAOFOTOUSER=? where login =?";
				
				prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString(1, objeto.getFotouser());
				prepareSql.setString(2, objeto.getExtensaofotouser());
				prepareSql.setString(3, objeto.getLogin());
				
				prepareSql.execute();
				
				connection.commit();
			}
	    }
		return this.consultaUsuario(objeto.getLogin());
	}
	/*
	 * 
	 * 
	 * */
	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
//		String sql = "SELECT * FROM COLABORADORES COL WHERE COL.[LOGIN] = '"+ login +"' AND USERADMIN = 0";
		String sql = "SELECT * FROM COLABORADORES COL WHERE COL.[LOGIN] = '"+ login +"'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
				
		while (resultado.next()) {
			modelLogin.setId_colaboradores( resultado.getLong  ("id_colaboradores") );
		    modelLogin.setNome            ( resultado.getString("nome"            ) );
		    modelLogin.setId_cargo        ( resultado.getLong  ("id_cargo"        ) );
		    modelLogin.setCep             ( resultado.getString("cep"             ) );
		    modelLogin.setEndereco        ( resultado.getString("endereco"        ) );
		    modelLogin.setBairro          ( resultado.getString("bairro"          ) );
		    modelLogin.setNumero          ( resultado.getString("numero"          ) );
		    modelLogin.setComplemento     ( resultado.getString("complemento"     ) );
		    modelLogin.setCidade          ( resultado.getString("cidade"          ) );
		    modelLogin.setEstado          ( resultado.getString("estado"          ) );
		    modelLogin.setPais            ( resultado.getString("pais"            ) );
		    modelLogin.setCpf             ( resultado.getString("cpf"             ) );
		    modelLogin.setTelefone        ( resultado.getString("telefone"        ) );
		    modelLogin.setCelular         ( resultado.getString("celular"         ) );
		    modelLogin.setEmail           ( resultado.getString("email"           ) );
		    modelLogin.setLogin           ( resultado.getString("login"           ) );
//		    modelLogin.setSenha           ( resultado.getString("senha"           ) );
		    modelLogin.setFotouser        ( resultado.getString("fotouser"        ) );
		    modelLogin.setExtensaofotouser( resultado.getString("extensaofotouser") );
		    modelLogin.setUseradmin       ( resultado.getString("useradmin"       ) );
		}
		return modelLogin;
	}
	/*
	 * 
	 * 
	 * */

	public boolean validarLogin(String login) throws SQLException {
		String sql = "SELECT count(1) as existe FROM COLABORADORES COL WHERE COL.[LOGIN] = '" + login+ "'";
		PreparedStatement statemet = connection.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		while (resutado.next()) return resutado.getBoolean("existe");

		return false;
	}
		
}
