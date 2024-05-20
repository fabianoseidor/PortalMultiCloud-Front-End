package br.com.portal.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelCliente;

public class DAOClienteRepository {

	private Connection connection;
	DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
	DAOUtil daoUtil = new DAOUtil();
	
	public DAOClienteRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	
	public void deletarCliente(String idCliente) throws SQLException {
		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ? ";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, Long.parseLong( idCliente ) );
		prepareSql.executeUpdate();
		
		connection.commit();
	}

	
	
	public boolean validarRazaoSocial(String razaoSocial) throws SQLException {
		
		String sql = "SELECT count(1) as existe FROM CLIENTE WHERE UPPER( RAZAO_SOCIAL ) = UPPER( '" + razaoSocial + "' ) ";
		PreparedStatement statemet = connection.prepareStatement(sql);
		
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next()) {
			return resutado.getBoolean("existe");
		}		
		return false;
	}
	
	public ModelCliente getClienteID(Long idCliente) throws SQLException {
		
		ModelCliente cliente = new ModelCliente();
	    
		String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong( 1, idCliente);	
		ResultSet resultado = prepareSql.executeQuery();

		while (resultado.next()) {
			cliente.setId_cliente         ( resultado.getLong  ("ID_CLIENTE"         )                                 );
			cliente.setId_porte_emp       ( resultado.getLong  ("ID_PORTE_EMP"       )                                 );
			cliente.setId_status_emp      ( resultado.getLong  ("ID_STATUS_EMP"      )                                 );	
			cliente.setRazao_social       ( resultado.getString("RAZAO_SOCIAL"       )                                 );
			cliente.setNome_fantasia      ( resultado.getString("NOME_FANTASIA"      )                                 );
			cliente.setSite               ( resultado.getString("SITE"               )                                 );
			cliente.setCep                ( resultado.getString("CEP"                )                                 );
			cliente.setEndereco           ( resultado.getString("ENDERECO"           )                                 );
			cliente.setBairro             ( resultado.getString("BAIRRO"             )                                 );
			cliente.setNumero             ( resultado.getString("NUMERO"             )                                 );
			cliente.setComplemento        ( resultado.getString("COMPLEMENTO"        )                                 );
			cliente.setCidade             ( resultado.getString("CIDADE"             )                                 );
			cliente.setEstado             ( resultado.getString("ESTADO"             )                                 );
			cliente.setPais               ( resultado.getString("PAIS"               )                                 );
			cliente.setCnpj               ( resultado.getString("CNPJ"               )                                 );
			cliente.setInscricao_estadual ( resultado.getString("INSCRICAO_ESTADUAL" )                                 );
			cliente.setInscricao_municipal( resultado.getString("INSCRICAO_MUNICIPAL")                                 );
			cliente.setNicho_mercado      ( resultado.getString("NICHO_MERCADO"      )                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro"     )                                 );
			cliente.setAlias              ( resultado.getString("ALIAS"              )                                 );
		}
		return cliente;
	}
	
	public ModelCliente gravarCliente(ModelCliente objeto) throws Exception {

		if( objeto.isNovo() ){
			String sql = "INSERT INTO CLIENTE                                                                                                                           "
					   + "        ( ID_PORTE_EMP, ID_STATUS_EMP, RAZAO_SOCIAL, NOME_FANTASIA, SITE , CEP               , ENDERECO           , BAIRRO       , NUMERO     "
					   + "        , COMPLEMENTO  , CIDADE      , ESTADO      , PAIS         , CNPJ , INSCRICAO_ESTADUAL, INSCRICAO_MUNICIPAL, NICHO_MERCADO, DT_CRIACAO "
					   + "        , OBSERVACAO, LOGIN_CADASTRO , ALIAS )                                                                                                "
					   + "  VALUES ( ? ,?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ? )                                                                    ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong     ( 1 , objeto.getId_porte_emp()                               );
			prepareSql.setLong     ( 2 , objeto.getId_status_emp()                              );
			prepareSql.setString   ( 3 , objeto.getRazao_social()                               );
			prepareSql.setString   ( 4 , objeto.getNome_fantasia()                              );
			prepareSql.setString   ( 5 , objeto.getSite()                                       );
			prepareSql.setString   ( 6 , objeto.getCep()                                        );
			prepareSql.setString   ( 7 , objeto.getEndereco()                                   );
			prepareSql.setString   ( 8 , objeto.getBairro()                                     );
			prepareSql.setString   ( 9 , objeto.getNumero()                                     );
			prepareSql.setString   ( 10, objeto.getComplemento()                                );
			prepareSql.setString   ( 11, objeto.getCidade()                                     );
			prepareSql.setString   ( 12, objeto.getEstado()                                     );
			prepareSql.setString   ( 13, objeto.getPais()                                       );
			prepareSql.setString   ( 14, objeto.getCnpj()                                       );
			prepareSql.setString   ( 15, objeto.getInscricao_estadual()                         );
			prepareSql.setString   ( 16, objeto.getInscricao_municipal()                        );
			prepareSql.setString   ( 17, objeto.getNicho_mercado()                              );
			prepareSql.setTimestamp( 18, new java.sql.Timestamp(new java.util.Date().getTime()) );			
			prepareSql.setString   ( 19, objeto.getObservacao()                                 );
			prepareSql.setString   ( 20, objeto.getLogin_cadastro()                             );
			prepareSql.setString   ( 21, objeto.getAlias()                                      );
			

			prepareSql.execute();
			connection.commit();
			objeto.setId_cliente(this.getMaxIdCliente());
			objeto.setStatus_emp( daoStatusCliente.getNomeStatus(objeto.getId_status_emp()) );
		}else {
			String sql = "UPDATE CLIENTE                                                                                                                                "
					   + "   SET   [ID_PORTE_EMP] = ?, [ID_STATUS_EMP] = ?,       [RAZAO_SOCIAL] = ?,       [NOME_FANTASIA] = ?,          [SITE] = ?,       [CEP]  = ?, "
					   + "             [ENDERECO] = ?,        [BAIRRO] = ?,             [NUMERO] = ?,         [COMPLEMENTO] = ?,        [CIDADE] = ?,     [ESTADO] = ?, "
					   + "                 [PAIS] = ?,          [CNPJ] = ?, [INSCRICAO_ESTADUAL] = ?, [INSCRICAO_MUNICIPAL] = ?, [NICHO_MERCADO] = ?, [OBSERVACAO] = ?, "
					   + "                [ALIAS] = ?                                                                                                                   "
					   + " WHERE [ID_CLIENTE] = ?                                                                                                                       ";

			PreparedStatement prepareSql = connection.prepareStatement(sql);

			prepareSql.setLong  ( 1, objeto.getId_porte_emp()       );
			prepareSql.setLong  ( 2, objeto.getId_status_emp()      );
			prepareSql.setString( 3, objeto.getRazao_social()       );
			prepareSql.setString( 4, objeto.getNome_fantasia()      );
			prepareSql.setString( 5, objeto.getSite()               );
			prepareSql.setString( 6, objeto.getCep()                );
			prepareSql.setString( 7, objeto.getEndereco()           );
			prepareSql.setString( 8, objeto.getBairro()             );
			prepareSql.setString( 9, objeto.getNumero()             );
			prepareSql.setString(10, objeto.getComplemento()        );
			prepareSql.setString(11, objeto.getCidade()             );
			prepareSql.setString(12, objeto.getEstado()             );
			prepareSql.setString(13, objeto.getPais()               );
			prepareSql.setString(14, objeto.getCnpj()               );
			prepareSql.setString(15, objeto.getInscricao_estadual() );
			prepareSql.setString(16, objeto.getInscricao_municipal());
			prepareSql.setString(17, objeto.getNicho_mercado()      );
			prepareSql.setString(18, objeto.getObservacao()         );
			prepareSql.setString(19, objeto.getAlias()              );
			prepareSql.setLong  (20, objeto.getId_cliente()         );


			prepareSql.executeUpdate();
			connection.commit();

		}
		return this.getClienteID(objeto.getId_cliente());
	}

	
	
	public List<ModelCliente> buscarListaClienteNome(String dadoCliente) throws SQLException {

		    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
//			String sql = "SELECT * FROM CLIENTE WHERE UPPER( RAZAO_SOCIAL ) LIKE UPPER('%" + nomeCliente + "%') ";			
/*
		    String sql = "SELECT * "
					   + "  FROM CLIENTE "
					   + " WHERE UPPER( RAZAO_SOCIAL ) LIKE UPPER('%" + dadoCliente + "%') "
					   + "    OR UPPER( ALIAS ) LIKE UPPER('%"  + dadoCliente + "%') "
					   + "    OR CNPJ LIKE '%"  + dadoCliente + "%' ";	
			
			String sql = "SELECT *                                                             "
					   + "  FROM CLIENTE       AS CLI                                          "
					   + " WHERE  CLI.ID_CLIENTE  NOT IN  (SELECT CON.ID_CLIENTE               "
					   + "                                   FROM CONTRATO AS CON  )           "
					   + "   AND UPPER( CLI.RAZAO_SOCIAL ) LIKE UPPER('%"  + dadoCliente + "%')"
					   + "    OR UPPER( CLI.ALIAS        ) LIKE UPPER('%"  + dadoCliente + "%')"
					   + "    OR CLI.CNPJ                  LIKE       '%"  + dadoCliente + "%' ";
*/			
			String sql = "SELECT *                                                               "
					   + "  FROM CLIENTE       AS CLI                                            "
					   + " WHERE (UPPER( RTRIM(CLI.RAZAO_SOCIAL) ) LIKE UPPER('%"  + dadoCliente + "%') "
					   + "     OR UPPER( RTRIM(CLI.ALIAS       ) ) LIKE UPPER('%"  + dadoCliente + "%') "
					   + "     OR RTRIM(CLI.CNPJ                 ) LIKE '%"        + dadoCliente + "%') "
					   + " AND CLI.ID_STATUS_EMP IN( 1, 4 )                                             ";
			
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				ModelCliente cliente = new ModelCliente();
				
				cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
				cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
				cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
				cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
				cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
				cliente.setSite               ( resultado.getString               ("site")                                 );
				cliente.setCep                ( resultado.getString                ("cep")                                 );
				cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
				cliente.setBairro             ( resultado.getString             ("bairro")                                 );
				cliente.setNumero             ( resultado.getString             ("numero")                                 );
				cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
				cliente.setCidade             ( resultado.getString             ("cidade")                                 );
				cliente.setEstado             ( resultado.getString             ("estado")                                 );
				cliente.setPais               ( resultado.getString               ("pais")                                 );
				cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
				cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
				cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
				cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
				cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
				cliente.setObservacao         ( resultado.getString("observacao")                                          );
				cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
				cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
				cliente.setAlias              ( resultado.getString("alias")                                               );
				Clientes.add(cliente);
				
			}
			
			return Clientes;
    }
	
	
	public List<ModelCliente> buscarListaClientePorPep(String Pep) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
		String sql = "SELECT CON.PEP,  CLI.*                                    "
				   + "  FROM                                                    "
				   + "     CONTRATO AS CON                                      "
				   + "   , CLIENTE  AS CLI                                      "
				   + "  WHERE  UPPER( CON.PEP ) LIKE UPPER('%" + Pep + "%')         "
				   + "  AND CLI.ID_CLIENTE = CON.ID_CLIENTE                     ";
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
			cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
			cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
			cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
			cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
			cliente.setSite               ( resultado.getString               ("site")                                 );
			cliente.setCep                ( resultado.getString                ("cep")                                 );
			cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
			cliente.setBairro             ( resultado.getString             ("bairro")                                 );
			cliente.setNumero             ( resultado.getString             ("numero")                                 );
			cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
			cliente.setCidade             ( resultado.getString             ("cidade")                                 );
			cliente.setEstado             ( resultado.getString             ("estado")                                 );
			cliente.setPais               ( resultado.getString               ("pais")                                 );
			cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
			cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
			cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
			cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
			cliente.setObservacao         ( resultado.getString("observacao")                                          );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
			cliente.setAlias              ( resultado.getString("alias")                                               );
			cliente.setPep_pesquisado     ( resultado.getString("PEP")                                                 );
			Clientes.add(cliente);
			
		}
		
		return Clientes;
}
	
	
	

	public List<ModelCliente> buscarListaClienteAlias( String nomeAlias ) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
		
		
		String sql = "SELECT * FROM CLIENTE WHERE UPPER( RTRIM(ALIAS) ) LIKE UPPER('%" + nomeAlias + "%') ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
			cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
			cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
			cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
			cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
			cliente.setSite               ( resultado.getString               ("site")                                 );
			cliente.setCep                ( resultado.getString                ("cep")                                 );
			cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
			cliente.setBairro             ( resultado.getString             ("bairro")                                 );
			cliente.setNumero             ( resultado.getString             ("numero")                                 );
			cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
			cliente.setCidade             ( resultado.getString             ("cidade")                                 );
			cliente.setEstado             ( resultado.getString             ("estado")                                 );
			cliente.setPais               ( resultado.getString               ("pais")                                 );
			cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
			cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
			cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
			cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
			cliente.setObservacao         ( resultado.getString("observacao")                                          );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
			cliente.setAlias              ( resultado.getString("alias")                                               );
			Clientes.add(cliente);
			
		}
		
		return Clientes;
	}
	
	public List<ModelCliente> buscarListaClienteCNPJ(String Cnpj) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
		
		
		String sql = "SELECT * FROM CLIENTE WHERE UPPER( RTRIM(CNPJ) ) LIKE UPPER('%" + Cnpj + "%') ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente         ( resultado.getLong           ("id_cliente")                                 );
			cliente.setId_porte_emp       ( resultado.getLong         ("id_porte_emp")                                 );
			cliente.setId_status_emp      ( resultado.getLong        ("id_status_emp")                                 );
			cliente.setRazao_social       ( resultado.getString       ("razao_social")                                 );
			cliente.setNome_fantasia      ( resultado.getString      ("nome_fantasia")                                 );
			cliente.setSite               ( resultado.getString               ("site")                                 );
			cliente.setCep                ( resultado.getString                ("cep")                                 );
			cliente.setEndereco           ( resultado.getString           ("endereco")                                 );
			cliente.setBairro             ( resultado.getString             ("bairro")                                 );
			cliente.setNumero             ( resultado.getString             ("numero")                                 );
			cliente.setComplemento        ( resultado.getString        ("complemento")                                 );
			cliente.setCidade             ( resultado.getString             ("cidade")                                 );
			cliente.setEstado             ( resultado.getString             ("estado")                                 );
			cliente.setPais               ( resultado.getString               ("pais")                                 );
			cliente.setCnpj               ( resultado.getString               ("cnpj")                                 );
			cliente.setInscricao_estadual ( resultado.getString ("inscricao_estadual")                                 );
			cliente.setInscricao_municipal( resultado.getString("inscricao_municipal")                                 );
			cliente.setNicho_mercado      ( resultado.getString      ("nicho_mercado")                                 );
			cliente.setDt_criacao         ( daoUtil.FormataDataStringTelaDataTime( resultado.getString("dt_criacao") ) );
			cliente.setObservacao         ( resultado.getString("observacao")                                          );
			cliente.setStatus_emp         ( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro     ( resultado.getString("login_cadastro")                                      );
			cliente.setAlias              ( resultado.getString("alias")                                               );
			Clientes.add(cliente);
			
		}
		
		return Clientes;
	}
	
	public List<ModelCliente> buscarListaCliente( Integer offsetBegin, Integer offsetEnd ) throws SQLException {

	    List<ModelCliente> Clientes = new ArrayList<ModelCliente>();
	    Integer totalPag = getTotalPag( offsetEnd );
		
		String sql = "SELECT * FROM CLIENTE AS CLI                                       "
				   + "WHERE  CLI.ID_CLIENTE   IN  (SELECT CON.ID_CLIENTE              "
				   + "                                  FROM CONTRATO AS CON             "
				   + "                                 WHERE CON.ID_STATUS_CONTRATO IN( 1,4)) "
				   + " ORDER BY ID_CLIENTE OFFSET                                        " 
		           + offsetBegin + " ROWS FETCH NEXT "+ offsetEnd +" ROWS ONLY           ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelCliente cliente = new ModelCliente();
			
			cliente.setId_cliente(resultado.getLong           ("id_cliente")                                  );
			cliente.setId_porte_emp(resultado.getLong         ("id_porte_emp")                                );
			cliente.setId_status_emp(resultado.getLong        ("id_status_emp")                               );
			cliente.setRazao_social(resultado.getString       ("razao_social")                                );
			cliente.setNome_fantasia(resultado.getString      ("nome_fantasia")                               );
			cliente.setSite(resultado.getString               ("site")                                        );
			cliente.setCep(resultado.getString                ("cep")                                         );
			cliente.setEndereco(resultado.getString           ("endereco")                                    );
			cliente.setBairro(resultado.getString             ("bairro")                                      );
			cliente.setNumero(resultado.getString             ("numero")                                      );
			cliente.setComplemento(resultado.getString        ("complemento")                                 );
			cliente.setCidade(resultado.getString             ("cidade")                                      );
			cliente.setEstado(resultado.getString             ("estado")                                      );
			cliente.setPais(resultado.getString               ("pais")                                        );
			cliente.setCnpj(resultado.getString               ("cnpj")                                        );
			cliente.setInscricao_estadual(resultado.getString ("inscricao_estadual")                          );
			cliente.setInscricao_municipal(resultado.getString("inscricao_municipal")                         );
			cliente.setNicho_mercado(resultado.getString      ("nicho_mercado")                               );
			cliente.setDt_criacao( daoUtil.FormataDataStringTelaDataTime( resultado.getString("DT_CRIACAO") ) );
			cliente.setObservacao(resultado.getString("observacao")                                           );
			cliente.setStatus_emp( daoStatusCliente.getNomeStatus(cliente.getId_status_emp())                 );
			cliente.setLogin_cadastro( resultado.getString("login_cadastro")                                  );
			cliente.setAlias(resultado.getString("alias")                                                     );
			cliente.setTotalPagCli(totalPag);
			Clientes.add(cliente);
		}
		
		return Clientes;
	}

	public Integer getTotalPag( Integer offsetEnd ) throws SQLException {
		String sql = "SELECT COUNT(ID_CLIENTE) AS COUNT_ID_CLIENTE                 "
				   + "  FROM CLIENTE AS CLI                                        "
				   + " WHERE  CLI.ID_CLIENTE IN (SELECT CON.ID_CLIENTE             "
				   + "                             FROM CONTRATO AS CON            "
				   + "                            WHERE CON.ID_STATUS_CONTRATO IN( 1, 4))";
		Double totalClientes = 0.0;
		Double pagina      = 0.0;

		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) {
			// return resutado.getInt("COUNT_ID_CLIENTE");
			totalClientes = resutado.getDouble("COUNT_ID_CLIENTE");
		}
		pagina = totalClientes / offsetEnd;
		Double resto = pagina % 2;
		if(resto > 0) pagina++;
		
		return pagina.intValue();
	}

	public Long getMaxIdCliente( ) throws SQLException {
		String sql = "SELECT MAX(ID_CLIENTE) AS MAX_ID_CLIENTE FROM CLIENTE";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);
		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getLong("MAX_ID_CLIENTE");

		return null;
	}


}
