package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModalDescomissionamento;

public class DAODescomissionamento {
	private Connection connection;
	private DAOUtil daoUtil = new DAOUtil();
	
	public DAODescomissionamento() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	
	public  List<ModalDescomissionamento> getDescomissionamentoDistrato(  ) throws SQLException {
		List<ModalDescomissionamento> mfDescomissionamentos = new ArrayList<ModalDescomissionamento>();
		String sql = "SELECT DESCO.*, STC.STATUS_CONTRATO, CLI.ALIAS, CLI.RAZAO_SOCIAL "
				   + "    FROM                                                         "
				   + "       DESCOMISSIONAMENTO AS DESCO                               "
				   + "     , STATUS_CONTRATO    AS STC                                 "
				   + "     , CLIENTE            AS CLI                                 "
				   + "   WHERE DESCO.ID_STATUS_CONTRATO      = STC.ID_STATUS_CONTRATO  "
				   + "     AND CLI.ID_CLIENTE                = DESCO.ID_CLIENTE        "
				   + "     AND DESCO.DESLIGAMENTO_AMBIENTE  != 1                       "
//				   + "     AND DESCO.SOLICITAR_DESLIGAMENTO != 1                       "
				   + "     AND DESCO.STATUS_REVERSAO         = 0                       ";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		ResultSet resutado = prepareSql.executeQuery();
		
		
		while (resutado.next()) {
			ModalDescomissionamento mfDescomissionamento = new ModalDescomissionamento();
			
			
			
			mfDescomissionamento.setId_descomissionamento             ( resutado.getLong   ("ID_DESCOMISSIONAMENTO"              ) );
			mfDescomissionamento.setId_contrato                       ( resutado.getLong   ("ID_CONTRATO"                        ) );
			mfDescomissionamento.setId_cliente                        ( resutado.getLong   ("ID_CLIENTE"                         ) );
			mfDescomissionamento.setId_status_contrato                ( resutado.getLong   ("ID_STATUS_CONTRATO"                 ) );
			mfDescomissionamento.setDt_criacao                        ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_CRIACAO"  ) ));
			mfDescomissionamento.setDt_alteracao                      ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_ALTERACAO") ));
			mfDescomissionamento.setMotivo_descomissionamento         ( resutado.getString ("MOTIVO_DESCOMISSIONAMENTO"          ) );
			mfDescomissionamento.setMotivo_reversao_descomissionamento( resutado.getString ("MOTIVO_REVERSAO_DESCOMISSIONAMENTO" ) );
			mfDescomissionamento.setSolicitar_desligamento            ( resutado.getBoolean("SOLICITAR_DESLIGAMENTO"             ) );
			mfDescomissionamento.setDt_solicitacao_desligamento       ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_SOLICITACAO_DESLIGAMENTO") ) );
			mfDescomissionamento.setDesligamento_ambiente             ( resutado.getBoolean("DESLIGAMENTO_AMBIENTE"              ) );
			mfDescomissionamento.setDt_desligamento_ambiente          ( daoUtil.FormataDataStringTelaDataTime( resutado.getString("DT_DESLIGAMENTO_AMBIENTE") ));
			mfDescomissionamento.setCliente                           ( resutado.getString ("RAZAO_SOCIAL"                       ) );
			mfDescomissionamento.setAlias                             ( resutado.getString ("ALIAS"                              ) );
			mfDescomissionamento.setStatus_contrato                   ( resutado.getString ("STATUS_CONTRATO"                    ) );			
			mfDescomissionamento.setUser_desligamento                 ( resutado.getString ("USER_DESLIGAMENTO"                  ) );
			mfDescomissionamento.setObsSolictcaoDesligue              ( resutado.getString ("OBS_SOLICTCAO_DESLIGUE"             ) );
			mfDescomissionamentos.add(mfDescomissionamento);
		}
		

		return mfDescomissionamentos;		
	}
	
    public String updateReversao( Long idDescomissionamento, String txDescReversao ) {
		String Error = "SUCESSO";
		String sqlUp = "UPDATE DESCOMISSIONAMENTO                                       "
				     + "   SET  DT_ALTERACAO                        = CURRENT_TIMESTAMP "
				     + "      , MOTIVO_REVERSAO_DESCOMISSIONAMENTO  = ?                 "
				     + "      , STATUS_REVERSAO                     = 1                 "
				     + "      , DT_REVERSAO                         = CURRENT_TIMESTAMP "
				     + " WHERE  ID_DESCOMISSIONAMENTO = ?                               " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setString( 1, txDescReversao       );
			prepareSql.setLong  ( 2, idDescomissionamento );
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;	
    }
    
	public String atualizaStatusRascunhoContrato( Long idContrato ) {
		
		String Error = "SUCESSO";
		String sqlUp = "UPDATE CONTRATO               "
				     + "   SET ID_STATUS_CONTRATO = 1 "
				     + "WHERE ID_CONTRATO = ?         " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setLong( 1, idContrato ); 
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	
	public String atualizaStatus( Long idContrato, Long idStatus ) {
		
		String Error = "SUCESSO";
		String sqlUp = "UPDATE CONTRATO               "
				     + "   SET ID_STATUS_CONTRATO = ? "
				     + "WHERE ID_CONTRATO = ?         " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );
		
			prepareSql.setLong( 1, idStatus   ); 
			prepareSql.setLong( 2, idContrato ); 
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	  /*
     * O usuario entra com o campo do banco de dados do tipo Date
     * e a função retorna o a data formatada 
     */
    
    public String MostraDataBD(Date data){
        String data_return = null; //Data que ira retornar
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        data_return = sdf.format(data);
        
        return data_return;
    }
    
    /*
     * O sistema pega a data e formata automaticamente para o formato
     * do banco de dados. E retorna a formatação.
     * O método funciona como campo Date.
     */ 
    public Date DataBD(){
        java.util.Date d1 = new java.util.Date();  
        java.sql.Date d2 = new java.sql.Date(d1.getTime());
        return d2;
    }
    
    /*
     *
     */ 
	public String upSolicitarDesligue( Long idDescomissionamento, String obsSolictcaoDesligue, String dtDeleigamento, Long idGmudPadrao ) {
		
		String Error = "SUCESSO";
		String sqlUp = "UPDATE DESCOMISSIONAMENTO                          "
				+ "   SET  DT_ALTERACAO                = CURRENT_TIMESTAMP "
				+ "      , SOLICITAR_DESLIGAMENTO      = 1                 "
				+ "      , DT_SOLICITACAO_DESLIGAMENTO = CURRENT_TIMESTAMP "
				+ "      , OBS_SOLICTCAO_DESLIGUE      = ?                 "
				+ "      , DT_SUGERIDA_DESLGUE         = ?                 "
				+ "      , ID_GMUD_DESLIGUE            = ?                 "
				+ " WHERE  ID_DESCOMISSIONAMENTO = ?                       " ;
	
		PreparedStatement prepareSql;
		
		try {
			prepareSql = connection.prepareStatement( sqlUp );	
			prepareSql.setString( 1, obsSolictcaoDesligue   );
			prepareSql.setString( 2, dtDeleigamento         ); 
			prepareSql.setLong  ( 3, idGmudPadrao           );
			prepareSql.setLong  ( 4, idDescomissionamento   );
		
			prepareSql.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			Error = e.getMessage();
			e.printStackTrace();
		}
		return Error;
	}
	
	
}
