package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelLogin;

public class DAOManutencaoUsuarioRepository {
	
	private Connection connection;
	/*
	 * 
	 * 
	 * */
	public DAOManutencaoUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	/*
	 * 
	 * 
	 * */
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
           String sql = "UPDATE COLABORADORES    "
	    			+ "   SET  NOME        = ?    "
	    			+ "      , LOGIN       = ?    "
	    			+ "      , EMAIL       = ?    "
	    			+ "      , ID_CARGO    = ?    "
	    			+ "      , CEP         = ?    "
	    			+ "      , ENDERECO    = ?    "
	    			+ "      , BAIRRO      = ?    "
	    			+ "      , NUMERO      = ?    "
	    			+ "      , COMPLEMENTO = ?    "
	    			+ "      , CIDADE      = ?    "
	    			+ "      , ESTADO      = ?    "
	    			+ "      , PAIS        = ?    "
	    			+ "      , CPF         = ?    "
	    			+ "      , TELEFONE    = ?    "
	    			+ "      , CELULAR     = ?    "
	    			+ "      , USERADMIN   = ?    "
	    			+ " WHERE ID_COLABORADORES = ?";
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getNome()             );
			prepareSql.setString( 2, objeto.getLogin()            );
			prepareSql.setString( 3, objeto.getEmail()            );
			prepareSql.setLong  ( 4, objeto.getId_cargo()         );
			prepareSql.setString( 5, objeto.getCep()              );
			prepareSql.setString( 6, objeto.getEndereco()         );
			prepareSql.setString( 7, objeto.getBairro()           );
			prepareSql.setString( 8, objeto.getNumero()           );
			prepareSql.setString( 9, objeto.getComplemento()      );
			prepareSql.setString(10, objeto.getCidade()           );
			prepareSql.setString(11, objeto.getEstado()           );
			prepareSql.setString(12, objeto.getPais()             );
			prepareSql.setString(13, objeto.getCpf()              );
			prepareSql.setString(14, objeto.getTelefone()         );
			prepareSql.setString(15, objeto.getCelular()          );
			prepareSql.setString(16, objeto.getUseradmin()        );
			prepareSql.setLong  (17, objeto.getId_colaboradores() );
			prepareSql.executeUpdate();
			connection.commit();
			if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
				sql = "update COLABORADORES set FOTOUSER =?, EXTENSAOFOTOUSER=? where id_colaboradores =?";
				
				prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString(1, objeto.getFotouser());
				prepareSql.setString(2, objeto.getExtensaofotouser());
				prepareSql.setLong(3, objeto.getId_colaboradores());
				prepareSql.execute();
				connection.commit();
			}
	    
		return this.consultaUsuario(objeto.getLogin());
	}
	/*
	 * 
	 * 
	 * */
	public List<ModelLogin> consultaUsuarioList( Integer offsetBegin, Integer offsetEnd, int Admin, String login  ) throws SQLException {

		List<ModelLogin> modelLogins = new ArrayList<ModelLogin>();
//		String sql = "SELECT * FROM COLABORADORES COL WHERE USERADMIN = 0 ORDER BY ID_COLABORADORES OFFSET " + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY";
		String sql = "";
		
		if( Admin != -1 && login != null && !login.isEmpty() ) {
			if( Admin == 1 )  
			    sql = "SELECT * FROM COLABORADORES COL ORDER BY ID_COLABORADORES OFFSET " + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY";
			else
			    sql = "SELECT * FROM COLABORADORES COL WHERE LOGIN = '" + login + "' ORDER BY ID_COLABORADORES OFFSET " + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY";
		}	
			
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId_colaboradores ( resultado.getLong  ("id_colaboradores") );
		    modelLogin.setNome             ( resultado.getString("nome"            ) );
		    modelLogin.setId_cargo         ( resultado.getLong  ("id_cargo"        ) );
		    modelLogin.setCep              ( resultado.getString("cep"             ) );
		    modelLogin.setEndereco         ( resultado.getString("endereco"        ) );
		    modelLogin.setBairro           ( resultado.getString("bairro"          ) );
		    modelLogin.setNumero           ( resultado.getString("numero"          ) );
		    modelLogin.setComplemento      ( resultado.getString("complemento"     ) );
		    modelLogin.setCidade           ( resultado.getString("cidade"          ) );
		    modelLogin.setEstado           ( resultado.getString("estado"          ) );
		    modelLogin.setPais             ( resultado.getString("pais"            ) );
		    modelLogin.setCpf              ( resultado.getString("cpf"             ) );
		    modelLogin.setTelefone         ( resultado.getString("telefone"        ) );
		    modelLogin.setCelular          ( resultado.getString("celular"         ) );
		    modelLogin.setEmail            ( resultado.getString("email"           ) );
		    modelLogin.setLogin            ( resultado.getString("login"           ) );
//		    modelLogin.setSenha            ( resultado.getString("senha"           ) );
		    modelLogin.setUseradmin        ( resultado.getString("useradmin"       ) );
		    
		    // System.out.println(modelLogin.getId_colaboradores() + " - " + modelLogin.getNome());
		    modelLogins.add(modelLogin);
		}
		return modelLogins;
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
	public ModelLogin consultaUsuarioId(String id) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "SELECT * FROM COLABORADORES COL WHERE COL.[ID_COLABORADORES] = '"+ id +"'";
//		String sql = "SELECT * FROM COLABORADORES COL WHERE COL.[ID_COLABORADORES] = '"+ id +"' AND USERADMIN = 0";
		
		PreparedStatement statement = connection.prepareStatement(sql);		
		ResultSet resultado = statement.executeQuery();		
		
		while (resultado.next()) {
			modelLogin.setId_colaboradores( resultado.getLong ("id_colaboradores" ) );
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
	/*
	 * 
	 * 
	 * */
	public void deletarUser(String id) throws SQLException {
//		String sql = "DELETE FROM COLABORADORES WHERE [ID_COLABORADORES] = ? AND USERADMIN = 0";
		String sql = "DELETE FROM COLABORADORES WHERE [ID_COLABORADORES] = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
/*
 * 
 * 
 * */
	public List<ModelLogin> buscarUsuario(String nome) throws SQLException {
		
 	    List<ModelLogin> modelLogins = new ArrayList<ModelLogin>();

//		String sql = "SELECT * FROM COLABORADORES WHERE UPPER( NOME ) LIKE UPPER('%" + nome + "%') AND USERADMIN = 0";
		String sql = "SELECT * FROM COLABORADORES WHERE UPPER( NOME ) LIKE UPPER('%" + nome + "%')";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
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
		    modelLogin.setUseradmin       ( resultado.getString("useradmin"       ) );
		    
		    modelLogins.add(modelLogin);
		}
		return modelLogins;
	}
	/*
	 * 
	 * 
	 * */
	public Integer getTotalPag( Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(ID_COLABORADORES) AS COUNT_ID_COLABORADORE FROM COLABORADORES";
		Double totalId = 0.0;
		Double pagina  = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) totalId = resutado.getDouble("COUNT_ID_COLABORADORE");
		
		pagina = totalId / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}
	
}
