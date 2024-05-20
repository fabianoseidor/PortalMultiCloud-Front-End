package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelTipoProduto;

public class DAOProduto {
	
	private Connection connection;
	
	public DAOProduto() {
		connection = SingleConnectionBanco.getConnection();
	}

	public List<ModelProduto> getListaProduto() {
		
		String sql = "SELECT * FROM PRODUTO ORDER BY 1";
		List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelProduto produto = new ModelProduto();
				produto.setId_produto( set.getLong  ("ID_PRODUTO")                                          );
				produto.setProduto   ( set.getString("PRODUTO")                                             );
				produto.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				produto.setObs       ( set.getString("OBSERVACAO")                                          );

				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VALOR") );
				produto.setValor( dinheiro.format(valorReal) );

				modelProdutos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelProdutos;
	}

	public ModelProduto gravarAtualizaRegistros( ModelProduto objeto ) throws Exception {
		
		if( objeto.isNovo() ){
			String sql = "INSERT INTO PRODUTO ( PRODUTO, VALOR, OBSERVACAO ) VALUES ( ?, ?, ? )";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString( 1, objeto.getProduto() );
			prepareSql.setString( 2, objeto.getValor()   );
			prepareSql.setString( 3, objeto.getObs()     );

			prepareSql.execute();
			connection.commit();
			
			return this.getProdutoID( this.getMaxId( ) );
		}else {
			String sql = "UPDATE PRODUTO        "
				 	   + "   SET PRODUTO    = ? "
					   + "     , VALOR      = ? "
					   + "     , OBSERVACAO = ? "
					   + " WHERE ID_PRODUTO = ? ";
		
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setString( 1, objeto.getProduto()    );
			prepareSql.setString( 2, objeto.getValor()      );
			prepareSql.setString( 3, objeto.getObs()        );
			prepareSql.setLong  ( 4, objeto.getId_produto() );

			prepareSql.executeUpdate();
			connection.commit();
			
			return objeto;
		}	
	}

	public Long getMaxId( ) throws SQLException {
		String sql = "SELECT MAX( ID_PRODUTO ) AS MAX_ID FROM PRODUTO";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next())  return resutado.getLong("MAX_ID");

		return null;
	}
	
	public String getValorProdutoId( Long id ) throws SQLException {
		String sql = "SELECT VALOR FROM PRODUTO WHERE ID_PRODUTO = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id    );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resutado.getString("VALOR") );
			
			return dinheiro.format(valorReal) ;
   
		}

		return null;
	}
	
	public String getValorServicoId( Long id ) throws SQLException {
		String sql = "SELECT VLR_SERVICO FROM SERVICO_CONTRATADO WHERE ID_SERVICO_CONTRATADO = ?";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id    );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resutado.getString("VLR_SERVICO") );
			
			return dinheiro.format(valorReal) ;
   
		}

		return null;
	}

	public ModelProduto getProdutoID( Long id ) throws Exception {
		String sql = "SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?";
		
		ModelProduto produto = new ModelProduto();
		DAOUtil daoUtil = new DAOUtil();
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, id );
		ResultSet resutado = prepareSql.executeQuery();
		
		while (resutado.next()) {
			
			produto.setId_produto( resutado.getLong   ("ID_PRODUTO")                                         );			 
			produto.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO") ) );
			produto.setProduto   ( resutado.getString ("PRODUTO")                                            );
			produto.setObs       ( resutado.getString ("OBSERVACAO")                                         );

			Locale localeBR       = new Locale("pt","BR");
			NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
			Double valorReal      = Double.valueOf( resutado.getString("VALOR") );
			produto.setValor( dinheiro.format(valorReal) );

		}
		return produto;
	}
	
	public void deletarRegistro( String id ) throws SQLException {
		String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ?";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( id ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}
	
	public List<ModelTipoProduto> getListaTipoProdutoDR() {
		
		String sql = "SELECT * FROM TIPO_PRODUTO AS TP WHERE ( TP.ID_PRODUTO = 7 OR UPPER ( TP.DESC_TIPO_PRODUTO ) LIKE '%DR%' ) ORDER BY 1";
		List<ModelTipoProduto> modelTipoProdutos = new ArrayList<ModelTipoProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelTipoProduto tipoProduto = new ModelTipoProduto();
				tipoProduto.setId_tipo_produto  ( set.getLong  ("ID_TIPO_PRODUTO")   );
				tipoProduto.setDesc_tipo_produto( set.getString("DESC_TIPO_PRODUTO") );
				tipoProduto.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				tipoProduto.setObservacao       ( set.getString("DESC_TIPO_PRODUTO")  );
				tipoProduto.setId_produto       (set.getLong  ("ID_PRODUTO")          );

				modelTipoProdutos.add(tipoProduto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelTipoProdutos;
	}

	public List<ModelProduto> getListaProdutoDR(  ) {
		
		String sql = "SELECT * FROM PRODUTO WHERE UPPER(PRODUTO) LIKE '%DR%' ORDER BY 1";
		List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelProduto produto = new ModelProduto();
				produto.setId_produto( set.getLong  ("ID_PRODUTO")                                          );
				produto.setProduto   ( set.getString("PRODUTO")                                             );
				produto.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				produto.setObs       ( set.getString("OBSERVACAO")                                          );

				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VALOR") );
				produto.setValor( dinheiro.format(valorReal) );

				modelProdutos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelProdutos;
	}

	public List<ModelProduto> getListaProdutoVPN(  ) {
		
		String sql = "SELECT * FROM PRODUTO WHERE UPPER(PRODUTO) LIKE '%VPN%' ORDER BY 1";
		List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelProduto produto = new ModelProduto();
				produto.setId_produto( set.getLong  ("ID_PRODUTO")                                          );
				produto.setProduto   ( set.getString("PRODUTO")                                             );
				produto.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				produto.setObs       ( set.getString("OBSERVACAO")                                          );

				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VALOR") );
				produto.setValor( dinheiro.format(valorReal) );

				modelProdutos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelProdutos;
	}

	public List<ModelProduto> getListaProdutoUser(  ) {
		
		String sql = "SELECT * FROM PRODUTO WHERE ( UPPER(PRODUTO) LIKE '%USUÁRIO%' OR  UPPER(PRODUTO) LIKE '%USER%'  )ORDER BY 1";
		List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelProduto produto = new ModelProduto();
				produto.setId_produto( set.getLong  ("ID_PRODUTO")                                          );
				produto.setProduto   ( set.getString("PRODUTO")                                             );
				produto.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				produto.setObs       ( set.getString("OBSERVACAO")                                          );

				Locale localeBR       = new Locale("pt","BR");
				NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				Double valorReal      = Double.valueOf( set.getString("VALOR") );
				produto.setValor( dinheiro.format(valorReal) );

				modelProdutos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelProdutos;
	}


	public List<ModelTipoProduto> getListaTipoProdutoID( Long id ) {
		
		String sql = "SELECT * FROM TIPO_PRODUTO AS TP WHERE TP.ID_PRODUTO = ? ORDER BY 1";
		List<ModelTipoProduto> modelTipoProdutos = new ArrayList<ModelTipoProduto>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, id );
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			while(set.next()) {
				ModelTipoProduto tipoProduto = new ModelTipoProduto();
				tipoProduto.setId_tipo_produto  ( set.getLong  ("ID_TIPO_PRODUTO")   );
				tipoProduto.setDesc_tipo_produto( set.getString("DESC_TIPO_PRODUTO") );
				tipoProduto.setDt_criacao       ( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CRIACAO") ) );
				tipoProduto.setObservacao       ( set.getString("DESC_TIPO_PRODUTO")  );
				tipoProduto.setId_produto       (set.getLong  ("ID_PRODUTO")          );

				modelTipoProdutos.add(tipoProduto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelTipoProdutos;
	}

}
