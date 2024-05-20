package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.connection.SingleConnectionBanco;
import br.com.portal.model.ModelRecursoContratoAditivoRel;

public class DAORecursoContratoAditivoRel {
	private Connection connection;
	
	public DAORecursoContratoAditivoRel() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelRecursoContratoAditivoRel>  getListaRecursoIdContrato(Long idContrato  ){
		
		String sql = "SELECT  REC.ID_RECURSO                             "
				+ "      , REC.ID_STATUS_RECURSO                         "
				+ "      , SR.STATUS_RECURSO                             "
				+ "      , REC.ID_RETENCAO_BACKUP                        "
				+ "      , RB.RETENCAO_BACKUP                            "
				+ "      , REC.ID_TIPO_DISCO                             "
				+ "      , TD.TIPO_DISCO                                 "
				+ "      , REC.ID_SO                                     "
				+ "      , SO.SISTEMA_OPERACIONAL                        "
				+ "      , REC.ID_AMBIENTE                               "
				+ "      , AM.AMBIENTE                                   "
				+ "      , REC.ID_FAMILIA_FLAVORS                        "
				+ "      , FF.FAMILIA                                    "
				+ "      , REC.ID_TIPO_SERVICO                           "
				+ "      , TSER.TIPO_SERVICO                             "
				+ "      , REC.HOSTNAME                                  "
				+ "      , REC.PRIMARY_IP                                "
				+ "      , REC.TAG_VCENTER                               "
				+ "      , REC.ID_CONTRATO                               "
				+ "      , CLI.RAZAO_SOCIAL                              "
				+ "      , NU.MOME_PARCEIRO                              "
				+ "      , SI.NOME                                       "
				+ "  FROM                                                "
				+ "     RECURSO             AS REC                       "
				+ "   , CONTRATO            AS CON                       "
				+ "   , CLIENTE             AS CLI                       "
				+ "   , TIPO_SERVICO        AS TSER                      "
				+ "   , FAMILIA_FLAVORS     AS FF                        "
				+ "   , AMBIENTE            AS AM                        "
				+ "   , SISTEMA_OPERACIONAL AS SO                        "
				+ "   , TIPO_DISCO          AS TD                        "
				+ "   , RETENCAO_BACKUP     AS RB                        "
				+ "   , STATUS_RECURSO      AS SR                        "
				+ "   , NUVEM               AS NU                        "
				+ "   , SITE                AS SI                        "
				+ " WHERE REC.ID_CONTRATO       = ?                      "
				+ "   AND REC.ID_ADITIVADO      IS NULL                  "
				+ "   AND CON.ID_CONTRATO       = REC.ID_CONTRATO        "
				+ "   AND CLI.ID_CLIENTE        = CON.ID_CLIENTE         "
				+ "   AND TSER.ID_TIPO_SERVICO  = REC.ID_TIPO_SERVICO    "
				+ "   AND FF.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
				+ "   AND AM.ID_AMBIENTE        = REC.ID_AMBIENTE        "
				+ "   AND SO.ID_SO              = REC.ID_SO              "
				+ "   AND TD.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
				+ "   AND RB.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "
				+ "   AND SR.ID_STATUS_RECURSO  = REC.ID_STATUS_RECURSO  "
				+ "   AND NU.ID_NUVEM           = REC.ID_NUVEM           "
				+ "   AND SI.ID_SITE            = REC.SITE               ";
		
		
		List<ModelRecursoContratoAditivoRel> modelRecursoContratoRels = new ArrayList<ModelRecursoContratoAditivoRel>();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idContrato );
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelRecursoContratoAditivoRel modelRecursoContratoRel = new ModelRecursoContratoAditivoRel();
				
				modelRecursoContratoRel.setId_recurso         (set.getLong  ("ID_RECURSO")         );
				modelRecursoContratoRel.setId_status_recurso  (set.getLong  ("ID_STATUS_RECURSO")  );
				modelRecursoContratoRel.setStatus_recurso     (set.getString("STATUS_RECURSO")     );
				modelRecursoContratoRel.setId_retencao_backup (set.getLong  ("ID_RETENCAO_BACKUP") );
				modelRecursoContratoRel.setRetencao_backup    (set.getString("RETENCAO_BACKUP")    );
				modelRecursoContratoRel.setId_tipo_disco      (set.getLong  ("ID_TIPO_DISCO")      );
				modelRecursoContratoRel.setTipo_disco         (set.getString("TIPO_DISCO")         );
				modelRecursoContratoRel.setId_so              (set.getLong  ("ID_SO")              );
				modelRecursoContratoRel.setSistema_operacional(set.getString("SISTEMA_OPERACIONAL"));
				modelRecursoContratoRel.setId_ambiente        (set.getLong  ("ID_AMBIENTE")        );
				modelRecursoContratoRel.setAmbiente           (set.getString("AMBIENTE")           );
				modelRecursoContratoRel.setId_familia_flavors (set.getLong  ("ID_FAMILIA_FLAVORS") );
				modelRecursoContratoRel.setFamilia            (set.getString("FAMILIA")            );
				modelRecursoContratoRel.setId_tipo_servico    (set.getLong  ("ID_TIPO_SERVICO")    );
				modelRecursoContratoRel.setTipo_servico       (set.getString("TIPO_SERVICO")       );
				modelRecursoContratoRel.setHostname           (set.getString("HOSTNAME")           );
				modelRecursoContratoRel.setPrimary_ip         (set.getString("PRIMARY_IP")         );
				modelRecursoContratoRel.setTag_vcenter        (set.getString("TAG_VCENTER")        );
				modelRecursoContratoRel.setId_contrato        (set.getLong  ("ID_CONTRATO")        );
				modelRecursoContratoRel.setRazao_social       (set.getString("RAZAO_SOCIAL")       );
				modelRecursoContratoRel.setNuvem              (set.getString("MOME_PARCEIRO")      );
				modelRecursoContratoRel.setSite               (set.getString("NOME")               );
				
				modelRecursoContratoRels.add(modelRecursoContratoRel);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelRecursoContratoRels;
	}
	
	public List<ModelRecursoContratoAditivoRel>  getListaRecursoIdContratoIdAditivo( Long idContrato, Long idAditovo ){
		
		String sql = "SELECT  REC.ID_RECURSO                             "
				+ "      , REC.ID_STATUS_RECURSO                         "
				+ "      , SR.STATUS_RECURSO                             "
				+ "      , REC.ID_RETENCAO_BACKUP                        "
				+ "      , RB.RETENCAO_BACKUP                            "
				+ "      , REC.ID_TIPO_DISCO                             "
				+ "      , TD.TIPO_DISCO                                 "
				+ "      , REC.ID_SO                                     "
				+ "      , SO.SISTEMA_OPERACIONAL                        "
				+ "      , REC.ID_AMBIENTE                               "
				+ "      , AM.AMBIENTE                                   "
				+ "      , REC.ID_FAMILIA_FLAVORS                        "
				+ "      , FF.FAMILIA                                    "
				+ "      , REC.ID_TIPO_SERVICO                           "
				+ "      , TSER.TIPO_SERVICO                             "
				+ "      , REC.HOSTNAME                                  "
				+ "      , REC.PRIMARY_IP                                "
				+ "      , REC.TAG_VCENTER                               "
				+ "      , REC.ID_CONTRATO                               "
				+ "      , REC.ID_ADITIVADO                              "
				+ "      , CLI.RAZAO_SOCIAL                              "
				+ "  FROM                                                "
				+ "     RECURSO             AS REC                       "
				+ "   , CONTRATO            AS CON                       "
				+ "   , CLIENTE             AS CLI                       "
				+ "   , TIPO_SERVICO        AS TSER                      "
				+ "   , FAMILIA_FLAVORS     AS FF                        "
				+ "   , AMBIENTE            AS AM                        "
				+ "   , SISTEMA_OPERACIONAL AS SO                        "
				+ "   , TIPO_DISCO          AS TD                        "
				+ "   , RETENCAO_BACKUP     AS RB                        "
				+ "   , STATUS_RECURSO      AS SR                        "
				+ " WHERE REC.ID_CONTRATO       = ?                      "
				+ "   AND REC.ID_ADITIVADO      = ?                      "
				+ "   AND CON.ID_CONTRATO       = REC.ID_CONTRATO        "
				+ "   AND CLI.ID_CLIENTE        = CON.ID_CLIENTE         "
				+ "   AND TSER.ID_TIPO_SERVICO  = REC.ID_TIPO_DISCO      "
				+ "   AND FF.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
				+ "   AND AM.ID_AMBIENTE        = REC.ID_AMBIENTE        "
				+ "   AND SO.ID_SO              = REC.ID_SO              "
				+ "   AND TD.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
				+ "   AND RB.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "
				+ "   AND SR.ID_STATUS_RECURSO  = REC.ID_STATUS_RECURSO  ";
		
		
		List<ModelRecursoContratoAditivoRel> modelRecursoContratoRels = new ArrayList<ModelRecursoContratoAditivoRel>();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idContrato );
			ps.setLong( 2, idAditovo );
			
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				ModelRecursoContratoAditivoRel modelRecursoContratoRel = new ModelRecursoContratoAditivoRel();
				
				modelRecursoContratoRel.setId_recurso         ( set.getLong  ("ID_RECURSO")         );
				modelRecursoContratoRel.setId_status_recurso  ( set.getLong  ("ID_STATUS_RECURSO")  );
				modelRecursoContratoRel.setStatus_recurso     ( set.getString("STATUS_RECURSO")     );
				modelRecursoContratoRel.setId_retencao_backup ( set.getLong  ("ID_RETENCAO_BACKUP") );
				modelRecursoContratoRel.setRetencao_backup    ( set.getString("RETENCAO_BACKUP")    );
				modelRecursoContratoRel.setId_tipo_disco      ( set.getLong  ("ID_TIPO_DISCO")      );
				modelRecursoContratoRel.setTipo_disco         ( set.getString("TIPO_DISCO")         );
				modelRecursoContratoRel.setId_so              ( set.getLong  ("ID_SO")              );
				modelRecursoContratoRel.setSistema_operacional( set.getString("SISTEMA_OPERACIONAL"));
				modelRecursoContratoRel.setId_ambiente        ( set.getLong  ("ID_AMBIENTE")        );
				modelRecursoContratoRel.setAmbiente           ( set.getString("AMBIENTE")           );
				modelRecursoContratoRel.setId_familia_flavors ( set.getLong  ("ID_FAMILIA_FLAVORS") );
				modelRecursoContratoRel.setFamilia            ( set.getString("FAMILIA")            );
				modelRecursoContratoRel.setId_tipo_servico    ( set.getLong  ("ID_TIPO_SERVICO")    );
				modelRecursoContratoRel.setTipo_servico       ( set.getString("TIPO_SERVICO")       );
				modelRecursoContratoRel.setHostname           ( set.getString("HOSTNAME")           );
				modelRecursoContratoRel.setPrimary_ip         ( set.getString("PRIMARY_IP")         );
				modelRecursoContratoRel.setTag_vcenter        ( set.getString("TAG_VCENTER")        );
				modelRecursoContratoRel.setId_contrato        ( set.getLong  ("ID_CONTRATO")        );
				modelRecursoContratoRel.setId_aditivado       ( set.getLong  ("ID_ADITIVADO")       );
				modelRecursoContratoRel.setRazao_social       ( set.getString("RAZAO_SOCIAL")       );
				
				modelRecursoContratoRels.add(modelRecursoContratoRel);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelRecursoContratoRels;
	}

	
	public ModelRecursoContratoAditivoRel getRecursoIdContrato( Long idRecurso ){
		
		String sql = "SELECT  REC.ID_RECURSO                             "
				+ "      , REC.ID_STATUS_RECURSO                         "
				+ "      , REC.DT_CADASTRO                               "
				+ "      , SR.STATUS_RECURSO                             "
				+ "      , REC.ID_RETENCAO_BACKUP                        "
				+ "      , RB.RETENCAO_BACKUP                            "
				+ "      , REC.ID_TIPO_DISCO                             "
				+ "      , TD.TIPO_DISCO                                 "
				+ "      , REC.ID_SO                                     "
				+ "      , SO.SISTEMA_OPERACIONAL                        "
				+ "      , REC.ID_AMBIENTE                               "
				+ "      , AM.AMBIENTE                                   "
				+ "      , REC.ID_FAMILIA_FLAVORS                        "
				+ "      , FF.FAMILIA                                    "
				+ "      , REC.ID_TIPO_SERVICO                           "
				+ "      , TSER.TIPO_SERVICO                             "
				+ "      , REC.HOSTNAME                                  "
				+ "      , REC.PRIMARY_IP                                "
				+ "      , REC.TAG_VCENTER                               "
				+ "      , REC.ID_CONTRATO                               "
				+ "      , CLI.RAZAO_SOCIAL                              "
				+ "  FROM                                                "
				+ "     RECURSO             AS REC                       "
				+ "   , CONTRATO            AS CON                       "
				+ "   , CLIENTE             AS CLI                       "
				+ "   , TIPO_SERVICO        AS TSER                      "
				+ "   , FAMILIA_FLAVORS     AS FF                        "
				+ "   , AMBIENTE            AS AM                        "
				+ "   , SISTEMA_OPERACIONAL AS SO                        "
				+ "   , TIPO_DISCO          AS TD                        "
				+ "   , RETENCAO_BACKUP     AS RB                        "
				+ "   , STATUS_RECURSO      AS SR                        "
				+ " WHERE REC.ID_RECURSO        = ?                      "
				+ "   AND CON.ID_CONTRATO       = REC.ID_CONTRATO        "
				+ "   AND CLI.ID_CLIENTE        = CON.ID_CLIENTE         "
				+ "   AND TSER.ID_TIPO_SERVICO  = REC.ID_TIPO_DISCO      "
				+ "   AND FF.ID_FAMILIA_FLAVORS = REC.ID_FAMILIA_FLAVORS "
				+ "   AND AM.ID_AMBIENTE        = REC.ID_AMBIENTE        "
				+ "   AND SO.ID_SO              = REC.ID_SO              "
				+ "   AND TD.ID_TIPO_DISCO      = REC.ID_TIPO_DISCO      "
				+ "   AND RB.ID_RETENCAO_BACKUP = REC.ID_RETENCAO_BACKUP "
				+ "   AND SR.ID_STATUS_RECURSO  = REC.ID_STATUS_RECURSO  ";
		
		
		ModelRecursoContratoAditivoRel modelRecursoContratoRel = new ModelRecursoContratoAditivoRel();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong( 1, idRecurso );
			ResultSet set = ps.executeQuery();
			DAOUtil daoUtil = new DAOUtil();
			
			while(set.next()) {
				modelRecursoContratoRel.setId_recurso         (set.getLong("ID_RECURSO")           );
				modelRecursoContratoRel.setDt_cadastro( daoUtil.FormataDataStringTelaDataTime( set.getString("DT_CADASTRO") ));
				
				modelRecursoContratoRel.setId_status_recurso  (set.getLong("ID_STATUS_RECURSO")    );
				modelRecursoContratoRel.setStatus_recurso     (set.getString("STATUS_RECURSO")     );
				modelRecursoContratoRel.setId_retencao_backup (set.getLong("ID_RETENCAO_BACKUP")   );
				modelRecursoContratoRel.setRetencao_backup    (set.getString("RETENCAO_BACKUP")    );
				modelRecursoContratoRel.setId_tipo_disco      (set.getLong("ID_TIPO_DISCO")        );
				modelRecursoContratoRel.setTipo_disco         (set.getString("TIPO_DISCO")         );
				modelRecursoContratoRel.setId_so              (set.getLong("ID_SO")                );
				modelRecursoContratoRel.setSistema_operacional(set.getString("SISTEMA_OPERACIONAL"));
				modelRecursoContratoRel.setId_ambiente        (set.getLong("ID_AMBIENTE")          );
				modelRecursoContratoRel.setAmbiente           (set.getString("AMBIENTE")           );
				modelRecursoContratoRel.setId_familia_flavors (set.getLong("ID_FAMILIA_FLAVORS")   );
				modelRecursoContratoRel.setFamilia            (set.getString("FAMILIA")            );
				modelRecursoContratoRel.setId_tipo_servico    (set.getLong("ID_TIPO_SERVICO")      );
				modelRecursoContratoRel.setTipo_servico       (set.getString("TIPO_SERVICO")       );
				modelRecursoContratoRel.setHostname           (set.getString("HOSTNAME")           );
				modelRecursoContratoRel.setPrimary_ip         (set.getString("PRIMARY_IP")         );
				modelRecursoContratoRel.setTag_vcenter        (set.getString("TAG_VCENTER")        );
				modelRecursoContratoRel.setId_contrato        (set.getLong("ID_CONTRATO")          );
				modelRecursoContratoRel.setRazao_social       (set.getString("RAZAO_SOCIAL")       );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelRecursoContratoRel;
	}
	
	
}
