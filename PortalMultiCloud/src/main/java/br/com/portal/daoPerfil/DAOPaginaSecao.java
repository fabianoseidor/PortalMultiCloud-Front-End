package br.com.portal.daoPerfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.modelPerfil.ModalPaginaPerfil;

public class DAOPaginaSecao {
	private Connection connection;
	
	public DAOPaginaSecao() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModalPaginaPerfil gravacao( ModalPaginaPerfil objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO PAGINA_SECAO( ID_SECAO, DESC_PAGINA, NOME_PAG_EXTENSAO, EXTENSAO, DT_CRIACAO, USER_CADASTRO, OBS ) VALUES( ?, ?, ?, ?, ?, ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong     ( 1, objeto.getId_secao()                                   );
			prepareSql.setString   ( 2, objeto.getDesc_pagina()                                );
			prepareSql.setString   ( 3, objeto.getNome_pag_extensao()                          );
			prepareSql.setString   ( 4, objeto.getExtensao()                                   );
			prepareSql.setTimestamp( 5, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 6, objeto.getUser_cadastro()                              );
			prepareSql.setString   ( 7, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getPagSecaoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE PAGINA_SECAO        "
					+ "   SET  ID_SECAO          = ? "
					+ "      , DESC_PAGINA       = ? "
					+ "      , NOME_PAG_EXTENSAO = ? "
					+ "      , EXTENSAO          = ? "
					+ "      , OBS               = ? "
					+ " WHERE ID_PAG_SECAO = ?       ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong  ( 1, objeto.getId_secao()          );
			prepareSql.setString( 2, objeto.getDesc_pagina()       );
			prepareSql.setString( 3, objeto.getNome_pag_extensao() );
			prepareSql.setString( 4, objeto.getExtensao()          );
			prepareSql.setString( 5, objeto.getObs()               );
			prepareSql.setLong  ( 6, objeto.getId_secao()          );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_PAG_SECAO ) AS MAX_ID FROM PAGINA_SECAO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	
	public List<ModalPaginaPerfil> getListaPagSecao() {
		
		String sql = "SELECT                                 "
			       + "        PS.ID_PAG_SECAO                "
			       + "      , PS.ID_SECAO                    "
			       + "      , PS.DESC_PAGINA                 "
			       + "      , PS.NOME_PAG_EXTENSAO           "
			       + "      , PS.EXTENSAO                    "
			       + "      , PS.DT_CRIACAO                  "
			       + "      , PS.USER_CADASTRO               "
			       + "      , PS.OBS                         "
			       + "      , SE.NOME_SECAO                  "
			       + "FROM                                   "
			       + "   PAGINA_SECAO AS PS                  "
			       + " , SECAO        AS SE                  "
			       + " WHERE SE.ID_SECAO = PS.ID_SECAO       "
			       + "ORDER BY NOME_SECAO, NOME_PAG_EXTENSAO ";
		List<ModalPaginaPerfil> pagSecaos = new ArrayList<ModalPaginaPerfil>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModalPaginaPerfil pagSecao = new ModalPaginaPerfil();
				pagSecao.setId_pag_secao     ( set.getLong    ("ID_PAG_SECAO")                                      );			 
				pagSecao.setId_secao         ( set.getLong    ("ID_SECAO")                                          );					
				pagSecao.setDesc_pagina      ( set.getString  ("DESC_PAGINA")                                       );
				pagSecao.setNome_pag_extensao( set.getString  ("NOME_PAG_EXTENSAO")                                 );				
				pagSecao.setExtensao         ( set.getString  ("EXTENSAO")                                          );
				pagSecao.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				pagSecao.setUser_cadastro    ( set.getString  ("USER_CADASTRO")                                     );
				pagSecao.setObs              ( set.getString  ("OBS")                                               );
				pagSecao.setDec_secao        ( set.getString  ("NOME_SECAO")                                        );
				
				pagSecaos.add(pagSecao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pagSecaos;
	}

	public ModalPaginaPerfil getPagSecaoID( Long idPagSecao ) throws Exception {
		String sql = "SELECT * FROM PAGINA_SECAO WHERE ID_PAG_SECAO = ?";
		
		ModalPaginaPerfil modelPagSecao = new ModalPaginaPerfil();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idPagSecao );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modelPagSecao.setId_pag_secao     ( resutado.getLong    ("ID_PAG_SECAO")                                      );
			modelPagSecao.setId_secao         ( resutado.getLong    ("ID_SECAO")                                          );			 
			modelPagSecao.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modelPagSecao.setDesc_pagina      ( resutado.getString  ("DESC_PAGINA")                                       );
			modelPagSecao.setNome_pag_extensao( resutado.getString  ("NOME_PAG_EXTENSAO")                                 );
			modelPagSecao.setExtensao         ( resutado.getString  ("EXTENSAO")                                          );
			modelPagSecao.setUser_cadastro    ( resutado.getString  ("USER_CADASTRO")                                     );			
			modelPagSecao.setObs              ( resutado.getString  ("OBS")                                               );
		}
		return modelPagSecao;
	}
	
	public void deletarPagSecao(String id) throws SQLException {
		String sql = "DELETE FROM PAGINA_SECAO WHERE ID_PAG_SECAO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

}
