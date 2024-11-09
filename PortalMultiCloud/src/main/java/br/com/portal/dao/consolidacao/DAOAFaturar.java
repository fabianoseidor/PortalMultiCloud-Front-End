package br.com.portal.dao.consolidacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.InformacoesDW;
import br.com.portal.model.consolidacao.ModelAFaturar;

public class DAOAFaturar {
	private Connection connection;
	private Connection connectionMySql;
	
	public DAOAFaturar() {
		connection      = SingleConnectionBanco.getConnection();
		connectionMySql = SingleConnectionBanco.getConexaoMySQL();
	}
	
    //******************************************************************************************//
    //                                                                                          //
    //                                                                                          //
    //                                                                                          //
    //******************************************************************************************//
	public int gravarAFaturar( List<ModelAFaturar> listaAFatura ) throws Exception {
	    
	    if(istruncateTable()) truncateTable( );
		int i = 0;
		for (ModelAFaturar listaAFaturar : listaAFatura) {
		    i++;
			String sql = "INSERT INTO AFATURA (PEP, NOME_EMISSOR, DT_FATURAMENTO, ANO, MES, VALOR, MOEDA, VL_FATURAMENTO)"
					+ "                VALUES (?, ?, ?, ?, ?, ?, ?, ?)                                                   ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			String valor          = null;
			String vl_faturamento = null;
	
			prepareSql.setString( 1, listaAFaturar.getPep()            ); // PEP
			prepareSql.setString( 2, listaAFaturar.getNome_emissor()   ); // NOME_EMISSOR
			prepareSql.setString( 3, listaAFaturar.getDt_faturamento() ); // DT_FATURAMENTO
			prepareSql.setString( 4, listaAFaturar.getAno()            ); // ANO
			prepareSql.setString( 5, listaAFaturar.getMes()            ); // MES
	
			valor = listaAFaturar.getValor();
			if(valor != null && !valor.isEmpty()) {
				valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			    if(valor.indexOf(" ") >= 0 )
			    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
				else
					valor = valor.replaceAll("\\.", "").replaceAll("\\,", ".");
			}
	
			prepareSql.setString( 6, valor                    ); // VALOR
			prepareSql.setString( 7, listaAFaturar.getMoeda() ); // MOEDA
			
			vl_faturamento = listaAFaturar.getVl_faturamento();		
			if(vl_faturamento != null && !vl_faturamento.isEmpty()) {
				vl_faturamento = Normalizer.normalize(vl_faturamento, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			    if(vl_faturamento.indexOf(" ") >= 0 )
			    	vl_faturamento = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
				else
					vl_faturamento = vl_faturamento.replaceAll("\\.", "").replaceAll("\\,", ".");
			}
			prepareSql.setString ( 8, vl_faturamento ); // VL_FATURAMENTO
			
			prepareSql.execute();
			connection.commit();
//			if (i % 1000 == 0){ System.out.println("Quantidade de linhas inseridos: " + i); }
		}
		
		// verifica se exite PEP para ser atualizado, vindo do SAP.
		atualizaPepSap();
//		System.out.println("Quantidade de linhas inseridos: " + i);
		return i;
    }
	
	public void truncateTable( ) {
		String sql = "TRUNCATE TABLE AFATURA";
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean istruncateTable() throws SQLException {
		String sql = "SELECT count(1) as existe FROM AFATURA";
		PreparedStatement statemet = connection.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		while (resutado.next()) return resutado.getBoolean("existe");
		return false;
	}

	public String[] vlrsFaturamento() throws SQLException {
		String resutadoReturns[] = null;
		String sql = "SELECT                                                                "
				+ "    MOEDA                                                                "
				+ "  , CASE                                                                 "
				+ "       WHEN MOEDA = 'BRL' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'pt-br') "
				+ "       WHEN MOEDA = 'USD' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'en-us') "
				+ "       WHEN MOEDA = 'EUR' THEN FORMAT(sum(VL_FATURAMENTO), 'C', 'fr-FR') "
				+ "    END AS VL_FATURAMENTO                                                "
				+ "  FROM AFATURA                                                           "
				+ "  group by MOEDA  ";
		PreparedStatement statemet = connection.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		int rows = getTotalLinhas( );		
		int i = 0;
		resutadoReturns = new String[rows];
		while (resutado.next())  {
			resutadoReturns[i] = resutado.getString("MOEDA") + ";" + resutado.getString("VL_FATURAMENTO");
		    i++;
		}
		return resutadoReturns;
	}
	
	public int getTotalLinhas( ) throws SQLException {
		String sql = "select count(MOEDA) AS TOTAL from ( SELECT MOEDA,sum(VL_FATURAMENTO)AS VL_FATURAMENTO FROM AFATURA  group by MOEDA )as t";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getInt("TOTAL");
		
		return 0;
	}
	
	public String getVlrTotal( ) throws SQLException {
		String sql = "SELECT FORMAT(sum(VL_FATURAMENTO), 'C', 'pt-br') AS VL_FATURAMENTO FROM AFATURA";
		PreparedStatement resultadoSql = connection.prepareStatement(sql);

		ResultSet resutado = resultadoSql.executeQuery();
		
		while (resutado.next()) return resutado.getString("VL_FATURAMENTO");
		
		return "R$ 0,00";
	}
	
    public void atualizaPepSap() {
    	
		String sql = "INSERT INTO PEP_SAP ( PEP, NOME_CLIENTE )                                                       "
				   + "             SELECT DISTINCT PEP, NOME_EMISSOR                                                  "
				   + "               FROM AFATURA AS AF                                                               "
			 	   + "              WHERE NOT EXISTS ( SELECT *                                                       "
				   + "                                  FROM PEP_SAP PS                                               "
				   + "                                 WHERE PS.PEP = AF.PEP AND PS.NOME_CLIENTE = AF.NOME_EMISSOR )  ";
		
		PreparedStatement prepareSql;
		try {
			prepareSql = connection.prepareStatement(sql);
			prepareSql.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
	 	
	public List<ModelAFaturar> porcessaBaseMySql() throws SQLException {
		
		List<ModelAFaturar> listaAFaturar = null;
		
		String sql = "SELECT                               "
				+ "    vw.pep                              "
				+ "  , vw.NomeParceiro                     "
				+ "  , vw.DataVencimentoOriginal           "
				+ "  , Month(vw.DataVencimentoOriginal) mes"
				+ "  , vw.AnoFaturamento                   "
				+ "  , vw.CodigoMoeda                      "
				+ "  , vw.ValorBruto                       "
				+ "FROM prd.vw_fluxo_receita as vw         ";
		PreparedStatement statemet = connectionMySql.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next())  {
			ModelAFaturar aFaturar = new ModelAFaturar();
	    	aFaturar.setPep           ( resutado.getString("pep"                   ).trim() != null && !resutado.getString("pep"                   ).trim().isEmpty() ? resutado.getString("pep"                   ).trim() : null );   // - 0  razaoSocial 	                        
	    	aFaturar.setNome_emissor  ( resutado.getString("NomeParceiro"          ).trim() != null && !resutado.getString("NomeParceiro"          ).trim().isEmpty() ? resutado.getString("NomeParceiro"          ).trim() : null );   // - 1  pep                                   
	    	aFaturar.setDt_faturamento( resutado.getString("DataVencimentoOriginal").trim() != null && !resutado.getString("DataVencimentoOriginal").trim().isEmpty() ? resutado.getString("DataVencimentoOriginal").trim() : null );   // - 2  alias 	                              
	    	aFaturar.setMes           ( resutado.getString("mes"                   ).trim() != null && !resutado.getString("mes"                   ).trim().isEmpty() ? resutado.getString("mes"                   ).trim() : null );   // - 3  status	                              
	    	aFaturar.setAno           ( resutado.getString("AnoFaturamento"        ).trim() != null && !resutado.getString("AnoFaturamento"        ).trim().isEmpty() ? resutado.getString("AnoFaturamento"        ).trim() : null );   // - 4  comercial	                            
	    	aFaturar.setValor         ( resutado.getString("ValorBruto"            ).trim() != null && !resutado.getString("ValorBruto"            ).trim().isEmpty() ? resutado.getString("ValorBruto"            ).trim() : null );   // - 5  emailCriseSeidor                      
	    	aFaturar.setMoeda         ( resutado.getString("CodigoMoeda"           ).trim() != null && !resutado.getString("CodigoMoeda"           ).trim().isEmpty() ? resutado.getString("CodigoMoeda"           ).trim() : null );   // - 6  obsCriseSeidor 	                      
	    	aFaturar.setVl_faturamento( resutado.getString("ValorBruto"            ).trim() != null && !resutado.getString("ValorBruto"            ).trim().isEmpty() ? resutado.getString("ValorBruto"            ).trim() : null );   // - 7  Vlr faturamento 
	    	
	    	if( listaAFaturar == null ) listaAFaturar = new ArrayList<ModelAFaturar>();
	    	listaAFaturar.add(aFaturar);
		}
		
	    try {
	    	this.gravarAFaturarMySql(listaAFaturar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaAFaturar;
	}
	
    //******************************************************************************************//
    //                                                                                          //
    //                                                                                          //
    //                                                                                          //
    //******************************************************************************************//
	public int gravarAFaturarMySql( List<ModelAFaturar> listaAFatura ) throws Exception {
	    
	    if(istruncateTable()) truncateTable( );
		int i = 0;
		for (ModelAFaturar listaAFaturar : listaAFatura) {
		    i++;
			String sql = "INSERT INTO AFATURA (PEP, NOME_EMISSOR, DT_FATURAMENTO, ANO, MES, VALOR, MOEDA, VL_FATURAMENTO)"
					+ "                VALUES (?, ?, ?, ?, ?, ?, ?, ?)                                                   ";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
	
			prepareSql.setString( 1, listaAFaturar.getPep()            ); // PEP
			prepareSql.setString( 2, listaAFaturar.getNome_emissor()   ); // NOME_EMISSOR
			prepareSql.setString( 3, listaAFaturar.getDt_faturamento() ); // DT_FATURAMENTO
			prepareSql.setString( 4, listaAFaturar.getAno()            ); // ANO
			prepareSql.setString( 5, listaAFaturar.getMes()            ); // MES
			prepareSql.setString( 6, listaAFaturar.getValor()          ); // VALOR
			prepareSql.setString( 7, listaAFaturar.getMoeda()          ); // MOEDA
			prepareSql.setString( 8, listaAFaturar.getVl_faturamento() ); // VL_FATURAMENTO
			
			prepareSql.execute();
			connection.commit();
//			if (i % 1000 == 0){ System.out.println("Quantidade de linhas inseridos: " + i); }
		}
		
		// verifica se exite PEP para ser atualizado, vindo do SAP.
		atualizaPepSap();
//		System.out.println("Quantidade de linhas inseridos: " + i);
		return i;
    }

	
	public List<InformacoesDW> getInfoDW() throws SQLException {
		
		List<InformacoesDW> listInformacoesDW = null;
		
		String sql = "SELECT * FROM prd.vw_fluxo_receita as vw         ";
		PreparedStatement statemet = connectionMySql.prepareStatement(sql);
		ResultSet resutado = statemet.executeQuery();
		
		while (resutado.next())  {
			InformacoesDW infDW = new InformacoesDW();
			infDW.setEmpresa(resutado.getString("Empresa")) ;                                            
			infDW.setDocumentoVenda(resutado.getString("DocumentoVenda")) ;                              
			infDW.setItemDocumentoVenda(resutado.getString("ItemDocumentoVenda")) ;                      
			infDW.setReferencia(resutado.getString("Referencia")) ;                                      
			infDW.setParceiro( resutado.getString("Parceiro")) ;                                         
			infDW.setNomeParceiro( resutado.getString("NomeParceiro")) ;                                 
			infDW.setOrganizacaoVenda( resutado.getString("OrganizacaoVenda")) ;                         
			infDW.setPEP( resutado.getString("PEP")) ;                                                   
			infDW.setMaterial( resutado.getString("Material")) ;                                         
			infDW.setSetorAtividade( resutado.getString("SetorAtividade")) ;                             
			infDW.setDocumentoFaturamento( resutado.getString("DocumentoFaturamento")) ;                 
			infDW.setDataLancamento( resutado.getString("DataLancamento")) ;                             
			infDW.setCondicaoPagamento( resutado.getString("CondicaoPagamento")) ;                       
			infDW.setDataVencimentoOriginal( resutado.getString("DataVencimentoOriginal")) ;             
			infDW.setDataVencimentoAtual( resutado.getString("DataVencimentoAtual")) ;                   
			infDW.setValorBruto( resutado.getString("ValorBruto")) ;                                     
			infDW.setValorLiquido( resutado.getString("ValorLiquido")) ;                                 
			infDW.setEquipeVendas( resutado.getString("EquipeVendas")) ;                                 
			infDW.setDenominacao( resutado.getString("Denominacao")) ;                                   
			infDW.setNumeroDocumento( resutado.getString("NumeroDocumento")) ;                           
			infDW.setAtribuicao( resutado.getString("Atribuicao")) ;                                     
			infDW.setGrupoAdminTesouraria( resutado.getString("GrupoAdminTesouraria")) ;                 
			infDW.setDataAtualizacao( resutado.getString("DataAtualizacao")) ;                           
			infDW.setDataCompensacao( resutado.getString("DataCompensacao")) ;                           
			infDW.setIdProjeto( resutado.getString("IdProjeto")) ;                                       
			infDW.setCentro( resutado.getString("Centro")) ;                                             
			infDW.setId( resutado.getString("Id")) ;                                                     
			infDW.setNumeroPedido( resutado.getString("NumeroPedido")) ;                                                       
			infDW.setDescricaoVendasMaterial( resutado.getString("DescricaoVendasMaterial")) ;           
			infDW.setSuplementoFormaPagamento( resutado.getString("SuplementoFormaPagamento")) ;         
			infDW.setNumContrato( resutado.getString("NumContrato")) ;                                   
			infDW.setStatusContrato( resutado.getString("StatusContrato")) ;                             
			infDW.setStatusLinhaContrato( resutado.getString("StatusLinhaContrato")) ;                   
			infDW.setTpDocVendas( resutado.getString("TpDocVendas")) ;                                   
			infDW.setCodigoMoeda( resutado.getString("CodigoMoeda")) ;                                   
			infDW.setPreVendas( resutado.getString("PreVendas")) ;                                       
			infDW.setDescrPrevendas( resutado.getString("DescrPrevendas")) ;                             
			infDW.setDtDivisaoRemessa( resutado.getString("DtDivisaoRemessa")) ;                         
			infDW.setNovoModelo( resutado.getString("NovoModelo")) ;                                     
			infDW.setTaxaCambio( resutado.getString("TaxaCambio")) ;                                     
			infDW.setNomeSet( resutado.getString("NomeSet")) ;                                           
			infDW.setNomeSubSet( resutado.getString("NomeSubSet")) ;                                     
			infDW.setAliquotaISS( resutado.getString("AliquotaISS")) ;                                   
			infDW.setCategoria( resutado.getString("Categoria")) ;                                       
			infDW.setUnidade( resutado.getString("Unidade")) ;                                           
			infDW.setFAT_AFAT( resutado.getString("FAT_AFAT")) ;                                         
			infDW.setAnoFaturamento( resutado.getString("AnoFaturamento")) ;                             
			infDW.setDiasAtraso( resutado.getString("DiasAtraso")) ;                                     
			infDW.setAtrasado( resutado.getString("Atrasado")) ;                                         
			infDW.setFaixaInadimplencia( resutado.getString("FaixaInadimplencia")) ;                                           
			                                                                                             
                 
	    	
	    	if( listInformacoesDW == null ) listInformacoesDW = new ArrayList<InformacoesDW>();
	    	listInformacoesDW.add(infDW);
		}
		
		return listInformacoesDW;
	}

}
