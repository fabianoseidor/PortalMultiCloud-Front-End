package br.com.portal.model;
/* **************************************************************************************************** */
/*                                                                                                      */
/* Tabela: Aditivado             | Modal: ModelAitivoRecursoModal      | Tela: Atribudo campor tela     */
/* ----------------------------- | ------------------------------------| --------------------------     */
/* Campo Tabela                  | Atributo Modal                      | Tela de Cad. Adit. Recur.      */
/* _____________________________ | ___________________________________ | __________________________     */
/*  * ID_ADITIVADO               | * id_aditivo                        | * idAditivoMAR                 */
/*  * ID_STATUS_ADITIVO          | * id_status                         | * idStatusMAR                  */
/*  * ID_CONTRATO                | * id_contrato                       | * id_contrato                  */
/*  * DT_CRIACAO                 | * dt_criacao                        | * dtCriacaoMAR                 */
/*  * VLR_TOTAL_ADIT             | * valor_total                       | * vlrTotalMAR                  */
/*  * OBSERVACAO                 | * observacao_aditivo                | * observacaoAditivoMAR         */
/*  * LOGIN_CADASTRO             | * login_cadastro                    | * login_cadastro               */
/* _____________________________ | ___________________________________ | __________________________     */
/*  * ID_RECURSO                 | * id_recurso                        | * idRecursoMAR                 */
/*  * ID_STATUS_RECURSO          | * id_status_recurso                 | * idStatusRecursoMAR           */
/*  * BACKUP                     | * id_backup                         | * idBackupMAR                  */
/*  * ID_RETENCAO_BACKUP         | * id_retencao_backup                | * idRetencaoBackupMAR          */
/*  * ID_TIPO_DISCO              | * id_tipo_disco                     | * idTipoDiscoMAR               */
/*  * ID_SO                      | * id_so                             | * idSoMAR                      */
/*  * ID_AMBIENTE                | * id_ambiente                       | * idAmbienteMAR                */
/*  * ID_FAMILIA_FLAVORS         | * id_familia_flavors                | * idFamiliaFlavorsMAR          */
/*  * ID_TIPO_SERVICO            | * id_tipo_servico                   | * idTipoServicoMAR             */
/*  * DT_CADASTRO                | * dt_criacao_recurso                | * dtCriacaoRecursoMAR          */
/*  * HOSTNAME                   | * host_name_modal_recurso           | * hostnameModalRecursoMAR      */
/*  * TAMANHO_DISCO              | * tamanho_disco_modal_recurso       | * tamanhoDiscoModalRecursoMAR  */
/*  * PRIMARY_IP                 | * primary_ip_modalrecurso           | * primaryIPModalRecursoMAR     */
/*  * TAG_VCENTER                | * tag_vcenter                       | * tagVcenterMAR                */
/*  * OBS                        | * observacoes_recurso               | * ObservacoesRecursoMAR        */
/*  * ID_CONTRATO                | * id_contrato                       | * id_contrato                  */
/*  * ID_ADITIVADO               | * id_aditivo                        | * idAditivoMAR                 */
/*  * ID_SUPORTE                 | * id_suporte                        | * idSuporteMAR                 */
/*  * ID_MONITORAMENTO           | * id_monitoramento                  | * idMonitoramentoMAR           */
/*  * ID_TEMPO_LIGADO            | * id_tipo_contratacao               | * idTipoContratacaoMAR         */
/*  * ID_TIPO_ADITIVO            | * id_tipo_aditivo                   | * idTipoAditivoMAR             */
/*  * RECURSO_TEMPORARIO         | * recurso_temporario                | * recursoTemporarioMAR         */
/*  * ADTI_SEM_RECEITA           | * adti_sem_receita                  | * temReceitaMAR                */
/*  * APROVADOR_ADIT_SEM_RECEITA | * aprovador_adit_sem_receita        | * nomeAprovadorMAR             */
/*  * PERIODO_UTILIZACAO_BOLHA   | * periodo_utilizacao_bolha          | * periodoUtilizacaoMARMAR      */
/*  * ID_NUVEM                   | * id_nuvem                          | * idNuvemMAR                   */
/*  * SITE                       | * id_site                           | * idSiteMAR                    */
/*  * EIP_VCENTER                | * eip_Vcenter                       | * eipVcenterModalRecursoMAR    */
/*  * HOST_VCENTER               | * host_Vcenter                      | * hostVcenterModalRecursoMAR   */
/* _____________________________ | ___________________________________ | __________________________     */
/*  * ID_VIGENCIA                | * id_vigencia                       | *                              */
/*  * ID_ADITIVADO               | * id_aditivo_recurso                | *                              */
/*  * ID_TEMPO_CONTRATO          | * id_tempo_contrato                 | *                              */
/*  * DT_INICIO                  | * dt_inicio                         | * dtInicioMAR                  */
/*  * DT_FINAL                   | * dt_final                          | * dtFinalMAR                   */
/*  * DT_CRIACAO                 | * dt_criacao_vigencia               | *                              */
/*  * DT_DESATIVACAO             | *                                   | *                              */
/*  * OBSERVACAO                 | * observacao_vigencia               | * observacaoAditivoMAR         */
/*                                                                                                      */
/* **************************************************************************************************** */


public class ModelAitivoRecursoModal {
	// Tabela Aditivo
	private Long   id_aditivado;                 // idAditivoMAR
	private Long   id_status;                    // idStatusMAR 
	private String desc_status;                  // 
	private Long   id_contrato;                  // idContrato
	private String dt_criacao;                   // dtCriacaoMAR
	private String observacao_aditivo;           // observacaoAditivoMAR
	private String valor_total;                  // vlrTotalMAR
	private String login_cadastro;               //
    private Long   id_rascunho;
	private String motivoRascunho;
	
	
	// Tabela Recurso
	private Long   id_recurso;                   
	private Long   id_tipo_aditivo;              // idTipoAditivoMAR
	private String desc_tipo_ditivo;
	private String dt_criacao_recurso; 
	private Long   id_tipo_contratacao;          // idTipoContratacaoMAR
	private String desc_tempo_ligado;
	private Long   id_suporte;                   // idSuporteMAR
	private String desc_suporte;
	private Long   id_monitoramento;             // idMonitoramentoMAR
	private String desc_monitoramento;
	private int    id_backup;                    // idBackupMAR
	private Long   id_retencao_backup;           // idRetencaoBackupMAR
	private String desc_retencao_backup;
	private Long   id_status_recurso;            // idStatusRecursoMAR
	private String desc_status_recurso;
	private String tag_vcenter;                  // tagVcenterMAR
	private Long   id_tipo_disco;                // idTipoDiscoMAR
	private String desc_tipo_disco;
	private Long   id_so;                        // idSoMAR
	private String desc_sistema_operacional;
	private Long   id_ambiente;                  // idAmbienteMAR
	private String desc_ambiente;
	private Long   id_tipo_servico;              // idTipoServicoMAR
	private String desc_tipo_servico;
	private String host_name_modal_recurso;      // hostnameModalRecursoMAR
	private String tamanho_disco_modal_recurso;  // tamanhoDiscoModalRecursoMAR 
	private String primary_ip_modalrecurso;      // primaryIPModalRecursoMAR
	private Long   id_nuvem;                     // idNuvemMAR
	private Long   id_site;                      // idSiteMAR
	private Long   id_familia_flavors;           // idFamiliaFlavorsMAR
	private String desc_familia;
	private String observacoes_recurso;          // ObservacoesRecursoMAR
	private int    recurso_temporario;           // 
    private int    adti_sem_receita;
    private String aprovador_adit_sem_receita;
    private String periodo_utilizacao_bolha;
    private String valor_recurso;
    private String eip_Vcenter; 
    private String host_Vcenter;
    private String hubspot_aditivo;
	
	// Referencia a tabela Vigencia
	private Long   id_vigencia;
	private Long   id_aditivo_recurso;
	private Long   id_tempo_contrato;
	private String dt_inicio;                    // dtInicioMAR
	private String dt_final;                     // dtFinalMAR
	private String dt_criacao_vigencia;         
	private String observacao_vigencia;
	
	// Informacao do tipo de Moeda Contratada para o Atidivo.
    private String valor_convertido;
    private String custo_total;
    private String cotacao_moeda;
    private Long   id_moeda;    

	public Long getId_rascunho() {
		return id_rascunho;
	}

	public void setId_rascunho(Long id_rascunho) {
		this.id_rascunho = id_rascunho;
	}

	public String getMotivoRascunho() {
		return motivoRascunho;
	}

	public void setMotivoRascunho(String motivoRascunho) {
		this.motivoRascunho = motivoRascunho;
	}

	public boolean isNovo() {
    	if(this.id_aditivado == null) {
    		return true;
    	}else if( this.id_aditivado != null && this.id_aditivado > 0 ) {
    		return false;
    	}
    	return this.id_aditivado == null;
    }
	
	public String getHubspot_aditivo() {
		return hubspot_aditivo;
	}

	public void setHubspot_aditivo(String hubspot_aditivo) {
		this.hubspot_aditivo = hubspot_aditivo;
	}

	public String getEip_Vcenter() {
		return eip_Vcenter;
	}

	public void setEip_Vcenter(String eip_Vcenter) {
		this.eip_Vcenter = eip_Vcenter;
	}

	public String getHost_Vcenter() {
		return host_Vcenter;
	}

	public void setHost_Vcenter(String host_Vcenter) {
		this.host_Vcenter = host_Vcenter;
	}

	public String getLogin_cadastro() {
		return login_cadastro;
	}

	public void setLogin_cadastro(String login_cadastro) {
		this.login_cadastro = login_cadastro;
	}

	public String getValor_convertido() {
		return valor_convertido;
	}

	public void setValor_convertido(String valor_convertido) {
		this.valor_convertido = valor_convertido;
	}

	public String getCusto_total() {
		return custo_total;
	}

	public void setCusto_total(String custo_total) {
		this.custo_total = custo_total;
	}

	public String getCotacao_moeda() {
		return cotacao_moeda;
	}

	public void setCotacao_moeda(String cotacao_moeda) {
		this.cotacao_moeda = cotacao_moeda;
	}

	public Long getId_moeda() {
		return id_moeda;
	}

	public void setId_moeda(Long id_moeda) {
		this.id_moeda = id_moeda;
	}

	public String getValor_recurso() {
		return valor_recurso;
	}

	public void setValor_recurso(String valor_recurso) {
		this.valor_recurso = valor_recurso;
	}

	public String getPeriodo_utilizacao_bolha() {
		return periodo_utilizacao_bolha;
	}

	public void setPeriodo_utilizacao_bolha(String periodo_utilizacao_bolha) {
		this.periodo_utilizacao_bolha = periodo_utilizacao_bolha;
	}

	public String getDesc_tipo_ditivo() {
		return desc_tipo_ditivo;
	}

	public void setDesc_tipo_ditivo(String desc_tipo_ditivo) {
		this.desc_tipo_ditivo = desc_tipo_ditivo;
	}

	public String getDesc_tempo_ligado() {
		return desc_tempo_ligado;
	}

	public void setDesc_tempo_ligado(String desc_tempo_ligado) {
		this.desc_tempo_ligado = desc_tempo_ligado;
	}

	public String getDesc_monitoramento() {
		return desc_monitoramento;
	}

	public void setDesc_monitoramento(String desc_monitoramento) {
		this.desc_monitoramento = desc_monitoramento;
	}

	public String getDesc_suporte() {
		return desc_suporte;
	}

	public void setDesc_suporte(String desc_suporte) {
		this.desc_suporte = desc_suporte;
	}

	public String getDesc_tipo_servico() {
		return desc_tipo_servico;
	}

	public void setDesc_tipo_servico(String desc_tipo_servico) {
		this.desc_tipo_servico = desc_tipo_servico;
	}

	public String getDesc_familia() {
		return desc_familia;
	}

	public void setDesc_familia(String desc_familia) {
		this.desc_familia = desc_familia;
	}

	public String getDesc_ambiente() {
		return desc_ambiente;
	}

	public void setDesc_ambiente(String desc_ambiente) {
		this.desc_ambiente = desc_ambiente;
	}
	
	public String getDesc_sistema_operacional() {
		return desc_sistema_operacional;
	}

	public void setDesc_sistema_operacional(String desc_sistema_operacional) {
		this.desc_sistema_operacional = desc_sistema_operacional;
	}

	public String getDesc_tipo_disco() {
		return desc_tipo_disco;
	}

	public void setDesc_tipo_disco(String desc_tipo_disco) {
		this.desc_tipo_disco = desc_tipo_disco;
	}

	public String getDesc_retencao_backup() {
		return desc_retencao_backup;
	}

	public void setDesc_retencao_backup(String desc_retencao_backup) {
		this.desc_retencao_backup = desc_retencao_backup;
	}
	
	public String getDesc_status_recurso() {
		return desc_status_recurso;
	}

	public void setDesc_status_recurso(String desc_status_recurso) {
		this.desc_status_recurso = desc_status_recurso;
	}

	public String getDesc_status() {
		return desc_status;
	}

	public void setDesc_status(String desc_status) {
		this.desc_status = desc_status;
	}

	public Long getId_aditivo_recurso() {
		return id_aditivo_recurso;
	}

	public void setId_aditivo_recurso(Long id_aditivo_recurso) {
		this.id_aditivo_recurso = id_aditivo_recurso;
	}

	public Long getId_recurso() {
		return id_recurso;
	}

	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}

	public String getDt_criacao_recurso() {
		return dt_criacao_recurso;
	}

	public void setDt_criacao_recurso(String dt_criacao_recurso) {
		this.dt_criacao_recurso = dt_criacao_recurso;
	}

	public Long getId_site() {
		return id_site;
	}

	public void setId_site(Long id_site) {
		this.id_site = id_site;
	}

	public int getRecurso_temporario() {
		return recurso_temporario;
	}

	public void setRecurso_temporario(int recurso_temporario) {
		this.recurso_temporario = recurso_temporario;
	}

	public int getAdti_sem_receita() {
		return adti_sem_receita;
	}

	public void setAdti_sem_receita(int adti_sem_receita) {
		this.adti_sem_receita = adti_sem_receita;
	}

	public String getAprovador_adit_sem_receita() {
		return aprovador_adit_sem_receita;
	}

	public void setAprovador_adit_sem_receita(String aprovador_adit_sem_receita) {
		this.aprovador_adit_sem_receita = aprovador_adit_sem_receita;
	}

	public Long getId_retencao_backup() {
		return id_retencao_backup;
	}

	public void setId_retencao_backup(Long i_d_retencao_backup) {
		this.id_retencao_backup = i_d_retencao_backup;
	}

	public Long getId_status_recurso() {
		return id_status_recurso;
	}

	public void setId_status_recurso(Long id_status_recurso) {
		this.id_status_recurso = id_status_recurso;
	}

	public Long getId_vigencia() {
		return id_vigencia;
	}

	public void setId_vigencia(Long id_vigencia) {
		this.id_vigencia = id_vigencia;
	}

	public String getDt_criacao_vigencia() {
		return dt_criacao_vigencia;
	}

	public void setDt_criacao_vigencia(String dt_criacao_vigencia) {
		this.dt_criacao_vigencia = dt_criacao_vigencia;
	}

	public String getObservacao_vigencia() {
		return observacao_vigencia;
	}

	public void setObservacao_vigencia(String observacao_vigencia) {
		this.observacao_vigencia = observacao_vigencia;
	}

	public Long getId_tempo_contrato() {
		return id_tempo_contrato;
	}

	public void setId_tempo_contrato(Long id_tempo_contrato) {
		this.id_tempo_contrato = id_tempo_contrato;
	}

	public Long getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(Long id_contrato) {
		this.id_contrato = id_contrato;
	}

	public Long getId_aditivado() {
		return id_aditivado;
	}

	public void setId_aditivado(Long id_aditivado) {
		this.id_aditivado = id_aditivado;
	}

	public String getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(String dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	public Long getId_tipo_aditivo() {
		return id_tipo_aditivo;
	}
	public void setId_tipo_aditivo(Long id_tipo_aditivo) {
		this.id_tipo_aditivo = id_tipo_aditivo;
	}
	public Long getId_status() {
		return id_status;
	}
	public void setId_status(Long id_status) {
		this.id_status = id_status;
	}
	public String getValor_total() {
		return valor_total;
	}
	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}
	public Long getId_tipo_contratacao() {
		return id_tipo_contratacao;
	}
	public void setId_tipo_contratacao(Long id_tipo_contratacao) {
		this.id_tipo_contratacao = id_tipo_contratacao;
	}
	public Long getId_suporte() {
		return id_suporte;
	}
	public void setId_suporte(Long id_suporte) {
		this.id_suporte = id_suporte;
	}
	public Long getId_monitoramento() {
		return id_monitoramento;
	}
	public void setId_monitoramento(Long id_monitoramento) {
		this.id_monitoramento = id_monitoramento;
	}
	public int getId_backup() {
		return id_backup;
	}
	public void setId_backup(int id_backup) {
		this.id_backup = id_backup;
	}

	public String getTag_vcenter() {
		return tag_vcenter;
	}
	public void setTag_vcenter(String tag_vcenter) {
		this.tag_vcenter = tag_vcenter;
	}
	public Long getId_tipo_disco() {
		return id_tipo_disco;
	}
	public void setId_tipo_disco(Long id_tipo_disco) {
		this.id_tipo_disco = id_tipo_disco;
	}
	public Long getId_so() {
		return id_so;
	}
	public void setId_so(Long id_so) {
		this.id_so = id_so;
	}
	public Long getId_ambiente() {
		return id_ambiente;
	}
	public void setId_ambiente(Long id_ambiente) {
		this.id_ambiente = id_ambiente;
	}
	public Long getId_tipo_servico() {
		return id_tipo_servico;
	}
	public void setId_tipo_servico(Long id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}
	public String getHost_name_modal_recurso() {
		return host_name_modal_recurso;
	}
	public void setHost_name_modal_recurso(String host_name_modal_recurso) {
		this.host_name_modal_recurso = host_name_modal_recurso;
	}
	public String getTamanho_disco_modal_recurso() {
		return tamanho_disco_modal_recurso;
	}
	public void setTamanho_disco_modal_recurso(String tamanho_disco_modal_recurso) {
		this.tamanho_disco_modal_recurso = tamanho_disco_modal_recurso;
	}
	public String getPrimary_ip_modalrecurso() {
		return primary_ip_modalrecurso;
	}
	public void setPrimary_ip_modalrecurso(String primary_ip_modalrecurso) {
		this.primary_ip_modalrecurso = primary_ip_modalrecurso;
	}
	public Long getId_nuvem() {
		return id_nuvem;
	}
	public void setId_nuvem(Long id_nuvem) {
		this.id_nuvem = id_nuvem;
	}
	public Long getId_familia_flavors() {
		return id_familia_flavors;
	}
	public void setId_familia_flavors(Long id_familia_flavors) {
		this.id_familia_flavors = id_familia_flavors;
	}
	public String getObservacoes_recurso() {
		return observacoes_recurso;
	}
	public void setObservacoes_recurso(String observacoes_recurso) {
		this.observacoes_recurso = observacoes_recurso;
	}
	public String getObservacao_aditivo() {
		return observacao_aditivo;
	}
	public void setObservacao_aditivo(String observacao_aditivo) {
		this.observacao_aditivo = observacao_aditivo;
	}
	public String getDt_inicio() {
		return dt_inicio;
	}
	public void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}
	public String getDt_final() {
		return dt_final;
	}
	public void setDt_final(String dt_final) {
		this.dt_final = dt_final;
	}


	public String toString() {
		return "ModelAitivoRecursoModal [id_aditivado=" + id_aditivado + ", id_status=" + id_status + ", desc_status="
				+ desc_status + ", id_contrato=" + id_contrato + ", dt_criacao=" + dt_criacao + ", observacao_aditivo="
				+ observacao_aditivo + ", valor_total=" + valor_total + ", id_recurso=" + id_recurso
				+ ", id_tipo_aditivo=" + id_tipo_aditivo + ", desc_tipo_ditivo=" + desc_tipo_ditivo
				+ ", dt_criacao_recurso=" + dt_criacao_recurso + ", id_tipo_contratacao=" + id_tipo_contratacao
				+ ", desc_tempo_ligado=" + desc_tempo_ligado + ", id_suporte=" + id_suporte + ", desc_suporte="
				+ desc_suporte + ", id_monitoramento=" + id_monitoramento + ", desc_monitoramento=" + desc_monitoramento
				+ ", id_backup=" + id_backup + ", id_retencao_backup=" + id_retencao_backup + ", desc_retencao_backup="
				+ desc_retencao_backup + ", id_status_recurso=" + id_status_recurso + ", desc_status_recurso="
				+ desc_status_recurso + ", tag_vcenter=" + tag_vcenter + ", id_tipo_disco=" + id_tipo_disco
				+ ", desc_tipo_disco=" + desc_tipo_disco + ", id_so=" + id_so + ", desc_sistema_operacional="
				+ desc_sistema_operacional + ", id_ambiente=" + id_ambiente + ", desc_ambiente=" + desc_ambiente
				+ ", id_tipo_servico=" + id_tipo_servico + ", desc_tipo_servico=" + desc_tipo_servico
				+ ", host_name_modal_recurso=" + host_name_modal_recurso + ", tamanho_disco_modal_recurso="
				+ tamanho_disco_modal_recurso + ", primary_ip_modalrecurso=" + primary_ip_modalrecurso + ", id_nuvem="
				+ id_nuvem + ", id_site=" + id_site + ", id_familia_flavors=" + id_familia_flavors + ", desc_familia="
				+ desc_familia + ", observacoes_recurso=" + observacoes_recurso + ", recurso_temporario="
				+ recurso_temporario + ", adti_sem_receita=" + adti_sem_receita + ", aprovador_adit_sem_receita="
				+ aprovador_adit_sem_receita + ", periodo_utilizacao_bolha=" + periodo_utilizacao_bolha
				+ ", id_vigencia=" + id_vigencia + ", id_aditivo_recurso=" + id_aditivo_recurso + ", id_tempo_contrato="
				+ id_tempo_contrato + ", dt_inicio=" + dt_inicio + ", dt_final=" + dt_final + ", dt_criacao_vigencia="
				+ dt_criacao_vigencia + ", observacao_vigencia=" + observacao_vigencia + "]";
	}


    
}
