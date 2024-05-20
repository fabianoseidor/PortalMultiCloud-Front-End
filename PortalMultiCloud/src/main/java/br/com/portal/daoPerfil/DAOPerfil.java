package br.com.portal.daoPerfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.dao.DAOUtil;
import br.com.portal.modelPerfil.ModalItemPerfilSecao;
import br.com.portal.modelPerfil.ModalListaPerfilItem;
import br.com.portal.modelPerfil.ModalPaginaPerfil;
import br.com.portal.modelPerfil.ModalPerfil;

public class DAOPerfil {
	
	private Connection connection;

	public DAOPerfil() {
		connection = SingleConnectionBanco.getConnection();
	}

	public Boolean isExistPerfil( String NomePerfil ) throws Exception {

		String sql = "SELECT COUNT(1) AS COUNT_NOME FROM PERFIL WHERE NOME_PERFIL = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setString( 1, NomePerfil.trim() );
		
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return ( resutado.getInt("COUNT_NOME")> 0 ? true: false  );

		return false;
   }
	

	
	
	public ModalPerfil gravacao( ModalPerfil objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO PERFIL( NOME_PERFIL, DT_CRIACAO, USER_CADASTRO, OBS ) VALUES( ?, ?, ?, ? )";

			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString   ( 1, objeto.getNome_perfil()                                );
			prepareSql.setTimestamp( 2, new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 3, objeto.getUser_cadastro()                              );
			prepareSql.setString   ( 4, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			
			return this.getPerfilID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE PERFIL            "
					  + "   SET  NOME_PERFIL   = ? "
					  + "      , USER_CADASTRO = ? "
				 	  + "      , OBS           = ? "
					  + " WHERE ID_PERFIL      = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getNome_perfil()   );
			prepareSql.setString( 2, objeto.getUser_cadastro() );
			prepareSql.setString( 3, objeto.getObs()           );
			prepareSql.setLong  ( 4, objeto.getId_perfil()     );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}
	}
	
	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_PERFIL ) AS MAX_ID FROM PERFIL";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}

	public ModalPerfil getPerfilID( Long idSecao ) throws Exception {
		String sql = "SELECT * FROM PERFIL WHERE ID_PERFIL = ?";
		
		ModalPerfil modalPerfil = new ModalPerfil();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idSecao );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			modalPerfil.setId_perfil    ( resutado.getLong    ("ID_PERFIL")                                         );			 
			modalPerfil.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			modalPerfil.setNome_perfil  ( resutado.getString  ("NOME_PERFIL")                                       );
			modalPerfil.setUser_cadastro( resutado.getString  ("USER_CADASTRO")                                     );			
			modalPerfil.setObs          ( resutado.getString  ("OBS")                                               );
		}
		return modalPerfil;
	}

	
	public List<ModalPerfil> getListaPerfil() {
		
		String sql = "SELECT * FROM PERFIL ORDER BY 1";
		List<ModalPerfil> perfis = new ArrayList<ModalPerfil>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModalPerfil perfil = new ModalPerfil();
				perfil.setId_perfil    ( set.getLong  ("ID_PERFIL")                                           );			 
				perfil.setNome_perfil  ( set.getString("NOME_PERFIL")                                         );
				perfil.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				perfil.setUser_cadastro( set.getString("USER_CADASTRO")                                       );
				perfil.setObs          ( set.getString("OBS")                                                 );
				perfis.add(perfil);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perfis;
	}
	
	public List<ModalItemPerfilSecao> getListaItemPerfilSecao( Long idPerfil ) {
		
		String sql = "SELECT   ID_SECAO        "
				   + "       , ID_PERFIL       "
				   + "       , ID_PAG          "
				   + "       , BT_NOVO         "
				   + "       , BT_EDITAR       "
				   + "       , BT_ESCLUIR      "
				   + "       , BT_PESQUISAR    "
				   + "       , DT_CRIACAO      "
				   + "       , USER_CADASTRO   "
				   + "       , OBS             "
				   + "  FROM ITEM_PERFIL_SECAO "
				   + " WHERE ID_PERFIL = ?     "
				   + " ORDER BY ID_SECAO       ";
		List<ModalItemPerfilSecao> itemPerfilSecoes = new ArrayList<ModalItemPerfilSecao>();
		DAOUtil daoUtil = new DAOUtil();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong     ( 1, idPerfil );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModalItemPerfilSecao itemPerfilSecao = new ModalItemPerfilSecao();
				itemPerfilSecao.setId_secao     ( set.getLong  ("ID_SECAO")                                          );			 
				itemPerfilSecao.setId_perfil    ( set.getLong  ("ID_PERFIL")                                         );			 
				itemPerfilSecao.setId_pag       ( set.getLong  ("ID_PAG")                                            );			 
				itemPerfilSecao.setBt_novo      ( set.getInt   ("BT_NOVO")                                           );
				itemPerfilSecao.setBt_editar    ( set.getInt   ("BT_EDITAR")                                         );
				itemPerfilSecao.setBt_escluir   ( set.getInt   ("BT_ESCLUIR")                                        );
				itemPerfilSecao.setBt_pesquisar ( set.getInt   ("BT_PESQUISAR")                                      );				
				itemPerfilSecao.setUser_cadastro( set.getString("USER_CADASTRO")                                     );
				itemPerfilSecao.setObs          ( set.getString("OBS")                                               );
				itemPerfilSecao.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				itemPerfilSecoes.add(itemPerfilSecao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemPerfilSecoes;
	}
	
	public List<ModalListaPerfilItem> getListaPerfiItens( Long idPerfil ) {
		
		String sql = "SELECT  IPS.ID_SECAO                "
				+ "       , SEC.NOME_SECAO                "
				+ "       , IPS.ID_PERFIL                 "
				+ "       , PER.NOME_PERFIL               "
				+ "       , IPS.ID_PAG                    "
				+ "       , PSE.DESC_PAGINA               "
				+ "       , IPS.BT_NOVO                   "
				+ "       , IPS.BT_EDITAR                 "
				+ "       , IPS.BT_ESCLUIR                "
				+ "       , IPS.BT_PESQUISAR              "
				+ "       , IPS.DT_CRIACAO                "
				+ "       , IPS.USER_CADASTRO             "
				+ "       , IPS.OBS                       "
				+ "  FROM                                 "
				+ "     ITEM_PERFIL_SECAO AS IPS          "
				+ "   , SECAO             AS SEC          "
				+ "   , PERFIL            AS PER          "
				+ "   , PAGINA_SECAO      AS PSE          "
				+ " WHERE IPS.ID_PERFIL = ?               "
				+ "   AND SEC.ID_SECAO = IPS.ID_SECAO     "
				+ "   AND PER.ID_PERFIL = IPS.ID_PERFIL   "
				+ "  AND PSE.ID_PAG_SECAO    = IPS.ID_PAG ";
		
		List<ModalListaPerfilItem> listaPerfilIrens = new ArrayList<ModalListaPerfilItem>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong     ( 1, idPerfil );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModalListaPerfilItem lListaPerfilItem = new ModalListaPerfilItem();
				lListaPerfilItem.setId_secao     ( set.getLong  ("ID_SECAO")                                          );			 
				lListaPerfilItem.setNome_secao   ( set.getString("NOME_SECAO")                                        );
				lListaPerfilItem.setId_perfil    ( set.getLong  ("ID_PERFIL")                                         );			 
				lListaPerfilItem.setNome_perfil  ( set.getString("NOME_PERFIL")                                       );
				lListaPerfilItem.setId_pag       ( set.getLong  ("ID_PAG")                                            );			 
				lListaPerfilItem.setDesc_pagina  ( set.getString("DESC_PAGINA")                                       );
				lListaPerfilItem.setBt_novo      ( set.getInt   ("BT_NOVO")                                           );
				lListaPerfilItem.setBt_editar    ( set.getInt   ("BT_EDITAR")                                         );
				lListaPerfilItem.setBt_escluir   ( set.getInt   ("BT_ESCLUIR")                                        );
				lListaPerfilItem.setBt_pesquisar ( set.getInt   ("BT_PESQUISAR")                                      );				
				lListaPerfilItem.setUser_cadastro( set.getString("USER_CADASTRO")                                     );
				lListaPerfilItem.setObs          ( set.getString("OBS")                                               );
				lListaPerfilItem.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				listaPerfilIrens.add(lListaPerfilItem);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPerfilIrens;
	}

	
	public void deletarPerfil(String id) throws SQLException {
		String sql = "DELETE FROM PERFIL WHERE ID_PERFIL = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
	public void deletarItemPerfil( Long idSecao, Long idPerfil, Long idPag ) throws SQLException {
		String sql = "DELETE FROM ITEM_PERFIL_SECAO WHERE ID_SECAO = ? AND ID_PERFIL= ? AND ID_PAG = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, idSecao  );
		prepareSql.setLong( 2, idPerfil );
		prepareSql.setLong( 3, idPag    );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

	
	public List<ModalPaginaPerfil> getListaPaginaSecao( Long idSecao, Long idPerfil ) {
		
		String sql = "SELECT  PS.ID_PAG_SECAO                                                   "
	               + "      , PS.ID_SECAO                                                       "
	               + "      , SE.NOME_SECAO                                                     "
	               + "      , PS.DESC_PAGINA                                                    "
	               + "      , PS.NOME_PAG_EXTENSAO                                              "
	               + "      , PS.EXTENSAO                                                       "
	               + "      , PS.DT_CRIACAO                                                     " 
	               + "      , PS.USER_CADASTRO                                                  "
	               + "      , PS.OBS                                                            "
	               + "   FROM                                                                   "
	               + "      PAGINA_SECAO AS PS                                                  "
	               + "    , SECAO        AS SE                                                  "
	               + "  WHERE PS.ID_SECAO = ?                                                   "
	               + "    AND SE.ID_SECAO = PS.ID_SECAO                                         "
	               + "    AND ( PS.ID_PAG_SECAO ) NOT IN ( SELECT ID_PAG                        "
	               + "                                       FROM ITEM_PERFIL_SECAO AS IPS      "
	               + "                                      WHERE IPS.ID_PAG  = PS.ID_PAG_SECAO "
	               + "                                        AND IPS.ID_SECAO  = PS.ID_SECAO   "
	               + "                                        AND IPS.ID_PERFIL = ? )           ";
		
		List<ModalPaginaPerfil> listaPaginaPerfis = new ArrayList<ModalPaginaPerfil>();
		DAOUtil daoUtil = new DAOUtil();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong     ( 1, idSecao  );
			ps.setLong     ( 2, idPerfil );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModalPaginaPerfil listaPaginaPerfil = new ModalPaginaPerfil();
				listaPaginaPerfil.setId_pag_secao     ( set.getLong  ("ID_PAG_SECAO")                                        );			 
				listaPaginaPerfil.setId_secao         ( set.getLong  ("ID_SECAO")                                            );			 
				listaPaginaPerfil.setDec_secao        ( set.getString("NOME_SECAO")                                          );
				listaPaginaPerfil.setDesc_pagina      ( set.getString("DESC_PAGINA")                                         );			 
				listaPaginaPerfil.setNome_pag_extensao( set.getString("NOME_PAG_EXTENSAO")                                   );
				listaPaginaPerfil.setExtensao         ( set.getString("EXTENSAO")                                            );			 
				listaPaginaPerfil.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				listaPaginaPerfil.setUser_cadastro    ( set.getString("USER_CADASTRO")                                       );
				listaPaginaPerfil.setObs              ( set.getString("OBS")                                                 );
				listaPaginaPerfis.add(listaPaginaPerfil);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPaginaPerfis;
	}

	public ModalItemPerfilSecao gravaItemPerfilSecao( ModalItemPerfilSecao objeto ) throws Exception {

			String sql = "INSERT INTO ITEM_PERFIL_SECAO ( ID_SECAO, ID_PERFIL, ID_PAG, BT_NOVO, BT_EDITAR, BT_ESCLUIR, BT_PESQUISAR, DT_CRIACAO, USER_CADASTRO, OBS ) "
					   + "                       VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )                                                                              ";

			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setLong     ( 1 , objeto.getId_secao()                                   );
			prepareSql.setLong     ( 2 , objeto.getId_perfil()                                  );
			prepareSql.setLong     ( 3 , objeto.getId_pag()                                     );
			prepareSql.setInt      ( 4 , objeto.getBt_novo()                                    );
			prepareSql.setInt      ( 5 , objeto.getBt_editar()                                  );
			prepareSql.setInt      ( 6 , objeto.getBt_escluir()                                 );
			prepareSql.setInt      ( 7 , objeto.getBt_escluir()                                 );
			prepareSql.setTimestamp( 8 , new java.sql.Timestamp(new java.util.Date().getTime()) );
			prepareSql.setString   ( 9 , objeto.getUser_cadastro()                              );
			prepareSql.setString   ( 10, objeto.getObs()                                        );

			prepareSql.execute();
			connection.commit();
			objeto = getItemPerfilSecaoIDs( objeto.getId_secao(), objeto.getId_perfil(), objeto.getId_pag() );
			objeto.setStatusInsert("OK");
			return objeto;
   }
	
	public ModalItemPerfilSecao getItemPerfilSecaoIDs( Long idSecao, Long idPerfil, Long idPag ) throws Exception {
		String sql = "SELECT * FROM ITEM_PERFIL_SECAO WHERE ID_SECAO = ? AND ID_PERFIL = ? AND ID_PAG = ?";
		
		ModalItemPerfilSecao itemPerfilSecao = new ModalItemPerfilSecao();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong     ( 1, idSecao  );
		prepareSql.setLong     ( 2, idPerfil );
		prepareSql.setLong     ( 3, idPag    );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			itemPerfilSecao.setId_secao     ( resutado.getLong    ("ID_SECAO"     )                                     );
			itemPerfilSecao.setId_perfil    ( resutado.getLong    ("ID_PERFIL"    )                                     );			 
			itemPerfilSecao.setId_pag       ( resutado.getLong    ("ID_PAG"       )                                     );
			itemPerfilSecao.setBt_editar    ( resutado.getInt     ("BT_EDITAR"    )                                     );
			itemPerfilSecao.setBt_novo      ( resutado.getInt     ("BT_NOVO"      )                                     );
			itemPerfilSecao.setBt_escluir   ( resutado.getInt     ("BT_ESCLUIR"   )                                     );
			itemPerfilSecao.setBt_pesquisar ( resutado.getInt     ("BT_PESQUISAR" )                                     );
			itemPerfilSecao.setDt_criacao   ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			itemPerfilSecao.setUser_cadastro( resutado.getString  ("USER_CADASTRO")                                     );			
			itemPerfilSecao.setObs          ( resutado.getString  ("OBS"          )                                     );
		}
		return itemPerfilSecao;
	}
	
}	
