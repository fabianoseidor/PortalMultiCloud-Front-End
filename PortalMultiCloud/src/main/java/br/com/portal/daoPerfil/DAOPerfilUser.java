package br.com.portal.daoPerfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.modelPerfil.ModelPerfilUser;

public class DAOPerfilUser {
	private Connection connection;
	private Connection connectionGMUD;
	
	public DAOPerfilUser() {
		connection = SingleConnectionBanco.getConnection();
		connectionGMUD = SingleConnectionBanco.getConnectionGMUD();
	}
	
	public List<ModelPerfilUser> getListaUsuario() {
		
		String sql = "SELECT                             "
				+ "        COL.ID_COLABORADORES          "
				+ "      , COL.DT_CRIACAO                "
				+ "      , COL.LOGIN_CADASTRO            "
				+ "      , COL.ID_PERFIL                 "
				+ "      , PER.NOME_PERFIL               "
				+ "      , COL.NOME                      "
				+ "      , COL.LOGIN                     "
				+ "      , COL.USERADMIN                 "
				+ "  FROM                                "
				+ "     COLABORADORES AS COL             "
				+ "   , PERFIL        AS PER             "
				+ "  WHERE PER.ID_PERFIL = COL.ID_PERFIL "
				+ " ORDER BY 1                           ";
		List<ModelPerfilUser> perfilUsers = new ArrayList<ModelPerfilUser>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelPerfilUser perfilUser = new ModelPerfilUser();
				perfilUser.setId_colaboradores( set.getLong  ( "ID_COLABORADORES" ) );	
				perfilUser.setId_perfil       ( set.getLong  ( "ID_PERFIL"        ) );
				perfilUser.setNome_perfil     ( set.getString( "NOME_PERFIL"      ) );
				perfilUser.setNome            ( set.getString( "NOME"             ) );
				perfilUser.setLogin           ( set.getString( "LOGIN"            ) );
				perfilUser.setLogin_cadastro  ( set.getString( "LOGIN_CADASTRO"   ) );
				if( set.getInt   ( "USERADMIN" ) == 1 )
				    perfilUser.setUseradmin       ( "SIM" );
				else perfilUser.setUseradmin       ( "NÃO" );
				perfilUser.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				perfilUsers.add(perfilUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perfilUsers;
	}
	
	
	public String getNomePerfil( Long id ) {
		try {
			String sql = "SELECT pe.NOME_PERFIL FROM PERFIL AS pe WHERE pe.ID_PERFIL = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, id);  
			ResultSet set = ps.executeQuery();
			
			while(set.next()) return set.getString( "NOME_PERFIL" );

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public List<ModelPerfilUser> getListaUsuario( String nome, int loginUnificado ) {
		List<ModelPerfilUser> perfilUsers = new ArrayList<ModelPerfilUser>();
		DAOUtil daoUtil = new DAOUtil();
		
		if( loginUnificado == 0 ) {
			String sql = "SELECT TOP (200)                              "
				       + "        COL.ID_COLABORADORES                  "
				       + "      , COL.DT_CRIACAO                        "
				       + "      , COL.LOGIN_CADASTRO                    "
				       + "      , COL.ID_PERFIL                         "
				       + "      , PER.NOME_PERFIL                       "
				       + "      , COL.NOME                              "
				       + "      , COL.LOGIN                             "
				       + "      , COL.USERADMIN                         "
				       + "   FROM     COLABORADORES AS COL              "
				       + "  LEFT JOIN PERFIL        AS PER              "
				       + "       ON   PER.ID_PERFIL = COL.ID_PERFIL     "
				       + " WHERE UPPER( COL.NOME ) LIKE UPPER('%" + nome + "%')"
				       + "ORDER BY 1                                    ";
			
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
			
				ResultSet set = ps.executeQuery();
				while(set.next()) {
					ModelPerfilUser perfilUser = new ModelPerfilUser();
					perfilUser.setId_colaboradores( set.getLong  ( "ID_COLABORADORES" ) );	
					perfilUser.setId_perfil       ( set.getLong  ( "ID_PERFIL"        ) );
					perfilUser.setNome_perfil     ( set.getString( "NOME_PERFIL"      ) );
					perfilUser.setNome            ( set.getString( "NOME"             ) );
					perfilUser.setLogin           ( set.getString( "LOGIN"            ) );
					perfilUser.setLogin_cadastro  ( set.getString( "LOGIN_CADASTRO"   ) );
					if( set.getInt   ( "USERADMIN" ) == 1 )
					    perfilUser.setUseradmin       ( "SIM" );
					else perfilUser.setUseradmin       ( "NÃO" );
					perfilUser.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
					perfilUsers.add(perfilUser);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}else 
			if( loginUnificado == 1 ) {
				String sql = " SELECT TOP (200)                                                "
						+ "       u.id_users                                                   "
						+ "     , u.dt_criacao                                                 "
						+ "     , u.login_cadastro                                             "
						+ "     , ua.id_perfil                                                 "
						+ "     , p.nome_pessoa                                                "
						+ "     , u.login                                                      "
						+ "     , ua.admin                                                     "
						+ "    FROM                                                            "
						+ "               pessoa          AS p                                 "
						+ "    INNER JOIN users           AS u  ON u.id_pessoa  = p.id_pessoa  "
						+ "    INNER JOIN user_aplicativo AS ua ON ua.id_users  = u.id_users   "
						+ "   WHERE p.nome_pessoa LIKE UPPER('%" + nome + "%')                 "
						+ "     AND ua.id_aplicacao = 1                                        "
					    + "   ORDER BY 1                                                       ";
				
				try {
					PreparedStatement ps = connectionGMUD.prepareStatement(sql);
				
					ResultSet set = ps.executeQuery();
					
					while(set.next()) {
						ModelPerfilUser perfilUser = new ModelPerfilUser();
						String perfil = getNomePerfil( set.getLong  ( "ID_PERFIL" ) );
						
						perfilUser.setId_colaboradores( set.getLong  ( "id_users"       ) );	
						perfilUser.setId_perfil       ( set.getLong  ( "id_perfil"      ) );
						perfilUser.setNome_perfil     ( perfil                            );
						perfilUser.setNome            ( set.getString( "nome_pessoa"    ) );
						perfilUser.setLogin           ( set.getString( "login"          ) );
						perfilUser.setLogin_cadastro  ( set.getString( "login_cadastro" ) );
						if( set.getInt   ( "admin" ) == 1 )
						    perfilUser.setUseradmin       ( "SIM" );
						else perfilUser.setUseradmin       ( "NÃO" );
						perfilUser.setDt_criacao      ( set.getString("DT_CRIACAO"));
						perfilUsers.add(perfilUser);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
		}
		return perfilUsers;
	}

	public ModelPerfilUser getListaUsuario( Long idUser, int loginUnificado ) {
		DAOUtil daoUtil = new DAOUtil();
		ModelPerfilUser perfilUser = new ModelPerfilUser();
		
		if( loginUnificado == 0 ) {
			String sql = "SELECT TOP (200)                            "
				       + "        COL.ID_COLABORADORES                "
				       + "      , COL.DT_CRIACAO                      "
				       + "      , COL.LOGIN_CADASTRO                  "
				       + "      , COL.ID_PERFIL                       "
				       + "      , PER.NOME_PERFIL                     "
				       + "      , COL.NOME                            "
				       + "      , COL.LOGIN                           "
				       + "      , COL.USERADMIN                       "
				       + "  FROM                                      "
				       + "     COLABORADORES AS COL                   "
				       + "   , PERFIL        AS PER                   "
				       + "   WHERE ID_COLABORADORES = ?               "
				       + "    AND PER.ID_PERFIL = COL.ID_PERFIL       "
				       + " ORDER BY 1                                 ";
	
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setLong(1, idUser);  
				ResultSet set = ps.executeQuery();
				
				while(set.next()) {
					
					perfilUser.setId_colaboradores( set.getLong  ( "ID_COLABORADORES" ) );	
					perfilUser.setId_perfil       ( set.getLong  ( "ID_PERFIL"        ) );
					perfilUser.setNome_perfil     ( set.getString( "NOME_PERFIL"      ) );
					perfilUser.setNome            ( set.getString( "NOME"             ) );
					perfilUser.setLogin           ( set.getString( "LOGIN"            ) );
					perfilUser.setLogin_cadastro  ( set.getString( "LOGIN_CADASTRO"   ) );
					if( set.getInt   ( "USERADMIN" ) == 1 )
					    perfilUser.setUseradmin( "SIM" );
					else perfilUser.setUseradmin( "NÃO" );
					perfilUser.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else { 
			if( loginUnificado == 1 ) {
				String sql = " SELECT TOP (200)                                                "
						+ "       u.id_users                                                   "
						+ "     , u.dt_criacao                                                 "
						+ "     , u.login_cadastro                                             "
						+ "     , ua.id_perfil                                                 "
						+ "     , p.nome_pessoa                                                "
						+ "     , u.login                                                      "
						+ "     , ua.admin                                                     "
						+ "    FROM                                                            "
						+ "               pessoa          AS p                                 "
						+ "    INNER JOIN users           AS u  ON u.id_pessoa  = p.id_pessoa  "
						+ "    INNER JOIN user_aplicativo AS ua ON ua.id_users  = u.id_users   "
						+ "   WHERE u.id_users      = ?                                        "
						+ "     AND ua.id_aplicacao = 1                                        "
					    + "   ORDER BY 1                                                       ";
				
				try {
					PreparedStatement ps = connectionGMUD.prepareStatement(sql);
					ps.setLong(1, idUser);  
					ResultSet set = ps.executeQuery();
					
					while(set.next()) {
						String perfil = getNomePerfil( set.getLong  ( "ID_PERFIL" ) );
						perfilUser.setId_colaboradores( set.getLong  ( "id_users"       ) );	
						perfilUser.setId_perfil       ( set.getLong  ( "id_perfil"      ) );
						perfilUser.setNome_perfil     ( perfil                            );
						perfilUser.setNome            ( set.getString( "nome_pessoa"    ) );
						perfilUser.setLogin           ( set.getString( "login"          ) );
						perfilUser.setLogin_cadastro  ( set.getString( "login_cadastro" ) );
						if( set.getInt   ( "admin" ) == 1 )
						    perfilUser.setUseradmin( "SIM" );
						else perfilUser.setUseradmin( "NÃO" );
						perfilUser.setDt_criacao( set.getString("DT_CRIACAO")            );
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		    }
		}
		return perfilUser;
	}

	public ModelPerfilUser gravaAtualizaPerfil( ModelPerfilUser obj, int loginUnificado ) throws Exception {
		String sql = "";
		if( loginUnificado == 0 ) {
			sql = "UPDATE COLABORADORES SET ID_PERFIL = ? WHERE ID_COLABORADORES = ?";
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong( 1, obj.getId_perfil()        );
			prepareSql.setLong( 2, obj.getId_colaboradores() );

			prepareSql.executeUpdate();
			connection.commit();			
			
		}
		else{ 
			 if( loginUnificado == 1 ) { 
				 sql = "UPDATE user_aplicativo  SET ID_PERFIL = ? where id_users = ? and id_aplicacao = 1";
		
			     PreparedStatement prepareSql = connectionGMUD.prepareStatement(sql);

			     prepareSql.setLong( 1, obj.getId_perfil()        );
			     prepareSql.setLong( 2, obj.getId_colaboradores() );

			     prepareSql.executeUpdate();
			     connectionGMUD.commit();
			}
		}
		
		return getListaUsuario( obj.getId_colaboradores(), loginUnificado );
	}

	
}
