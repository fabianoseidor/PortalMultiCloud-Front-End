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
	
	public DAOPerfilUser() {
		connection = SingleConnectionBanco.getConnection();
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
	
	public List<ModelPerfilUser> getListaUsuario( String nome ) {
		
		String sql = "SELECT TOP (200)                              "
			       + "        COL.ID_COLABORADORES                  "
			       + "      , COL.DT_CRIACAO                        "
			       + "      , COL.LOGIN_CADASTRO                    "
			       + "      , COL.ID_PERFIL                         "
			       + "      , PER.NOME_PERFIL                       "
			       + "      , COL.NOME                              "
			       + "      , COL.LOGIN                             "
			       + "      , COL.USERADMIN                         "
			       + "   FROM       COLABORADORES AS COL            "
			       + "  LEFT JOIN PERFIL        AS PER              "
			       + "       ON   PER.ID_PERFIL = COL.ID_PERFIL     "
			       + " WHERE UPPER( COL.NOME ) LIKE UPPER('%" + nome + "%')"
			       + "ORDER BY 1                                    ";
		
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

	public ModelPerfilUser getListaUsuario( Long idUser ) {
		
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

		DAOUtil daoUtil = new DAOUtil();
		ModelPerfilUser perfilUser = new ModelPerfilUser();
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
				    perfilUser.setUseradmin       ( "SIM" );
				else perfilUser.setUseradmin       ( "NÃO" );
				perfilUser.setDt_criacao      ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perfilUser;
	}

	public ModelPerfilUser gravaAtualizaPerfil( ModelPerfilUser obj ) throws Exception {
		
			String sql = "UPDATE COLABORADORES SET ID_PERFIL = ? WHERE ID_COLABORADORES = ?";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong( 1, obj.getId_perfil()        );
			prepareSql.setLong( 2, obj.getId_colaboradores() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return getListaUsuario( obj.getId_colaboradores() );
	}

	
}
