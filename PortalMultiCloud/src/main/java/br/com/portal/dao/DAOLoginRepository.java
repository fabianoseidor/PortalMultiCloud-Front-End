package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelLogin;
import br.com.portal.modelPerfil.ModelPerfilLogado;

public class DAOLoginRepository {

	private Connection conexao;
	
	
	public DAOLoginRepository() {
		this.conexao = SingleConnectionBanco.getConnection();
	}
	
	public void atualizaSenhaPrimeiroAcesso( String novaSenha, String Login ) throws SQLException {
		String sqlUp = "UPDATE COLABORADORES SET SENHA = ?, PRIMEIRO_ACESSO = 1 WHERE LOGIN = ?" ;
	
		PreparedStatement prepareSql = conexao.prepareStatement( sqlUp );
		
		prepareSql.setString( 1, novaSenha ); // ID_FAMILIA_FLAVORS
		prepareSql.setString( 2, Login     ); // TAMANHO_DISCO
	
		prepareSql.executeUpdate();
		conexao.commit();

	}
	
	public void atualizaSenha( String novaSenha, String Login ) throws SQLException {
		String sqlUp = "UPDATE COLABORADORES SET SENHA = ? WHERE LOGIN = ?" ;
	
		PreparedStatement prepareSql = conexao.prepareStatement( sqlUp );
		
		prepareSql.setString( 1, novaSenha ); // ID_FAMILIA_FLAVORS
		prepareSql.setString( 2, Login     ); // TAMANHO_DISCO
	
		prepareSql.executeUpdate();
		conexao.commit();

	}
	
	public ModelLogin ValidaLogin(String login, String senha)throws SQLException {
		
		ModelLogin modellogin = null;
		
		String sql = "SELECT                                              "
				   + "        id_colaboradores                            "
				   + "      , nome                                        "
				   + "      , id_cargo                                    "
				   + "      , cep                                         "
				   + "      , endereco                                    "
				   + "      , bairro                                      "
				   + "      , numero                                      "
				   + "      , complemento                                 "
				   + "      , cidade                                      "
				   + "      , estado                                      "
				   + "      , pais                                        "
				   + "      , cpf                                         "
				   + "      , telefone                                    "
				   + "      , celular                                     "
				   + "      , email                                       "
				   + "      , login                                       "
				   + "      , senha                                       "
				   + "      , fotouser                                    "
				   + "      , useradmin                                   "
				   + "      , primeiro_acesso                             "
				   + "FROM dbo.COLABORADORES WHERE LOGIN = ? AND SENHA = ?" ;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
        
		statement.setString(1, login );
        statement.setString(2, senha );

		ResultSet resut = statement.executeQuery();
	//	modelLogin.setLogin(login);
	//	modelLogin.setSenha(senha);
		if(resut.next()) {
			modellogin = new ModelLogin();
			modellogin.setId_colaboradores( resut.getLong  ("id_colaboradores") );
			modellogin.setNome            ( resut.getString("nome"            ) );
			modellogin.setId_cargo        ( resut.getLong  ("id_cargo"        ) );
			modellogin.setCep             ( resut.getString("cep"             ) );
			modellogin.setEndereco        ( resut.getString("endereco"        ) );
			modellogin.setBairro          ( resut.getString("bairro"          ) );
			modellogin.setNumero          ( resut.getString("numero"          ) );
			modellogin.setComplemento     ( resut.getString("complemento"     ) );
			modellogin.setCidade          ( resut.getString("cidade"          ) );
			modellogin.setEstado          ( resut.getString("estado"          ) );
			modellogin.setPais            ( resut.getString("pais"            ) );
			modellogin.setCpf             ( resut.getString("cpf"             ) );
			modellogin.setTelefone        ( resut.getString("telefone"        ) );
			modellogin.setCelular         ( resut.getString("celular"         ) );
			modellogin.setEmail           ( resut.getString("email"           ) );
			modellogin.setLogin           ( login                               );
			modellogin.setSenha           ( senha                               );
			modellogin.setUseradmin       ( resut.getString("useradmin"       ) );
			modellogin.setFotouser        ( resut.getString("fotouser"        ) );
			modellogin.setPrimeiro_acesso ( resut.getInt   ("primeiro_acesso" ) );
		}
	    
		return modellogin;
	}
	
	
	public List<ModelPerfilLogado> getPerfilUserLogado( Long idUser )throws SQLException {
		
		List<ModelPerfilLogado> perfilLogados = null;
		
		String sql = "SELECT  COL.ID_COLABORADORES                    "
			       + "      , COL.ID_PERFIL                           "
			       + "      , PSE.NOME_PAG_EXTENSAO                   "
			       + "      , SEC.NOME_SECAO                          "
			       + "      , COL.USERADMIN                           "
			       + "      , COL.PRIMEIRO_ACESSO                     "
			       + "      , PER.NOME_PERFIL                         "
			       + "      , PER.OBS                                 "
			       + "  FROM                                          "
			       + "    COLABORADORES     AS COL                    "
			       + "  , PERFIL            AS PER                    "
			       + "  , ITEM_PERFIL_SECAO AS IPC                    "
			       + "  , PAGINA_SECAO      AS PSE                    "
			       + "  , SECAO             AS SEC                    "
			       + " WHERE COL.ID_COLABORADORES = ?                 "
			       + "   AND PER.ID_PERFIL        = COL.ID_PERFIL     "
			       + "   AND IPC.ID_PERFIL        = PER.ID_PERFIL     "
			       + "   AND PSE.ID_SECAO         = IPC.ID_SECAO      "
			       + "   AND PSE.ID_PAG_SECAO     = IPC.ID_PAG        "
			       + "   AND SEC.ID_SECAO         = PSE.ID_SECAO      "
			       + " ORDER BY SEC.NOME_SECAO, PSE.NOME_PAG_EXTENSAO " ;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setLong(1, idUser );
		ResultSet resut = statement.executeQuery();
		while(resut.next()) {
			if( perfilLogados == null ) perfilLogados = new ArrayList<ModelPerfilLogado>();
			ModelPerfilLogado perfilLogado = new ModelPerfilLogado();
			perfilLogado.setId_colaboradores( resut.getLong  ( "ID_COLABORADORES" ) );
			perfilLogado.setId_perfil       ( resut.getLong  ( "ID_PERFIL"        ) );
			perfilLogado.setNome_secao      ( resut.getString( "NOME_SECAO"       ) );
			perfilLogado.setDesc_pagina     ( resut.getString( "NOME_PAG_EXTENSAO") );
			perfilLogado.setUseradmin       ( resut.getInt   ( "USERADMIN"        ) );
			perfilLogado.setPrimeiro_acesso ( resut.getInt   ( "PRIMEIRO_ACESSO"  ) );
			perfilLogado.setNome_perfil     ( resut.getString( "NOME_PERFIL"      ) );
			perfilLogado.setObs             ( resut.getString( "OBS"              ) );
			perfilLogado.setIsVisivel       ( true                                  );
			perfilLogados.add(perfilLogado);
		}
	    
		return perfilLogados;
	}

	
	public List<ModelPerfilLogado> getPerfilUserLogadoPorLogin( String login )throws SQLException {
		
		List<ModelPerfilLogado> perfilLogados = null;
/*		
		String sql = "SELECT  COL.ID_COLABORADORES                    "
			       + "      , COL.ID_PERFIL                           "
			       + "      , PSE.NOME_PAG_EXTENSAO                   "
			       + "      , SEC.NOME_SECAO                          "
			       + "      , COL.USERADMIN                           "
			       + "      , COL.PRIMEIRO_ACESSO                     "
			       + "      , PER.NOME_PERFIL                         "
			       + "      , PER.OBS                                 "
			       + "  FROM                                          "
			       + "    COLABORADORES     AS COL                    "
			       + "  , PERFIL            AS PER                    "
			       + "  , ITEM_PERFIL_SECAO AS IPC                    "
			       + "  , PAGINA_SECAO      AS PSE                    "
			       + "  , SECAO             AS SEC                    "
			       + " WHERE COL.LOGIN            = ?                 "
			       + "   AND PER.ID_PERFIL        = COL.ID_PERFIL     "
			       + "   AND IPC.ID_PERFIL        = PER.ID_PERFIL     "
			       + "   AND PSE.ID_SECAO         = IPC.ID_SECAO      "
			       + "   AND PSE.ID_PAG_SECAO     = IPC.ID_PAG        "
			       + "   AND SEC.ID_SECAO         = PSE.ID_SECAO      "
			       + " ORDER BY SEC.NOME_SECAO, PSE.NOME_PAG_EXTENSAO " ;
*/
		String sql = "select                                              "
				+ "    us.id_users                                        "
				+ "  , ua.id_perfil                                       "
				+ "  , PSE.NOME_PAG_EXTENSAO                              "
				+ "  , SEC.NOME_SECAO                                     "
				+ "  , ua.admin                                           "
				+ "  , pe.primeiro_acesso                                 "
				+ "  , PER.NOME_PERFIL                                    "
				+ "  , PER.OBS                                            "
				+ "  from                                                 "
				+ "    [LOGINUNIFICADO_PRD].[dbo].[users]           as us "
				+ "  , [LOGINUNIFICADO_PRD].[dbo].[user_aplicativo] as ua "
				+ "  , [LOGINUNIFICADO_PRD].[dbo].[pessoa]          as pe "
				+ "  , PERFIL            AS PER                           "
				+ "  , ITEM_PERFIL_SECAO AS IPC                           "
				+ "  , PAGINA_SECAO      AS PSE                           "
				+ "  , SECAO             AS SEC                           "
				+ "  where us.login     = ?                               "
				+ "    and ua.id_users     = us.id_users                  "
				+ "    and ua.id_aplicacao = 1                            "
				+ "    and pe.id_pessoa    = us.id_pessoa                 "
				+ "    AND PER.ID_PERFIL        = ua.id_perfil            "
				+ "   AND IPC.ID_PERFIL        = PER.ID_PERFIL            "
				+ "   AND PSE.ID_SECAO         = IPC.ID_SECAO             "
				+ "   AND PSE.ID_PAG_SECAO     = IPC.ID_PAG               "
				+ "   AND SEC.ID_SECAO         = PSE.ID_SECAO             "
				+ " ORDER BY SEC.NOME_SECAO, PSE.NOME_PAG_EXTENSAO        ";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, login );
		ResultSet resut = statement.executeQuery();
		while(resut.next()) {
			if( perfilLogados == null ) perfilLogados = new ArrayList<ModelPerfilLogado>();
			ModelPerfilLogado perfilLogado = new ModelPerfilLogado();
			perfilLogado.setId_colaboradores( resut.getLong  ( "id_users"         ) );
			perfilLogado.setId_perfil       ( resut.getLong  ( "id_perfil"        ) );
			perfilLogado.setNome_secao      ( resut.getString( "NOME_SECAO"       ) );
			perfilLogado.setDesc_pagina     ( resut.getString( "NOME_PAG_EXTENSAO") );
			perfilLogado.setUseradmin       ( resut.getInt   ( "admin"            ) );
			perfilLogado.setPrimeiro_acesso ( resut.getInt   ( "primeiro_acesso"  ) );
			perfilLogado.setNome_perfil     ( resut.getString( "NOME_PERFIL"      ) );
			perfilLogado.setObs             ( resut.getString( "OBS"              ) );
			perfilLogado.setIsVisivel       ( true                                  );
			perfilLogados.add(perfilLogado);
		}
	    
		return perfilLogados;
	}
	
	
	
	
	public String getNomeDataBase( )  {
		try {	
			   String sql = "SELECT DB_NAME() AS NOME_DATABESE";
			   PreparedStatement prepareSql = conexao.prepareStatement(sql);
			   ResultSet resultado = prepareSql.executeQuery();
				while (resultado.next()) return " - [" + resultado.getString("NOME_DATABESE") + "]";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return null;
		
		}
	

	public List<ModelLogin> getSelectLogin( )throws SQLException {
		
		List<ModelLogin> modellogins = new ArrayList<ModelLogin>();
		
		String sql = "SELECT ID_COLABORADORES, LOGIN  FROM COLABORADORES" ;
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet resut = statement.executeQuery();
		while(resut.next()) {
			ModelLogin modellogin = new ModelLogin();
			modellogin.setId_colaboradores( resut.getLong  ( "ID_COLABORADORES" ) );
			modellogin.setLogin           ( resut.getString( "LOGIN"            ) );
			
			modellogins.add(modellogin);
		}
	    
		return modellogins;
	}

	
	
	
	
}
