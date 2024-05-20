
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>        
    <!-- Modal Add Produto -->
   <div class="modal t-modal primary" id="ModalAditivoRecurso" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Contratação de Recurso via Aditivo</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>

	      <div class="modal-body">
	      
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">	
							<div class="form-row">
								<!-- Campo ID Aditivo -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">ID Aditivo</label>
									<input type="text" name="idAditivoMAR" id="idAditivoMAR" class="form-control" readonly="readonly" value="">
								</div>

								<!-- Data Cadastro -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">Data Cadastro</label>
									<input type="text" name="dtCriacaoMAR" id="dtCriacaoMAR" class="form-control" readonly="readonly" value="">
								</div>
								
								<!-- Login Cadastro --> 
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">Login Cadastro</label>
									<input type="text" name="loginCadastroMAR" id="loginCadastroMAR" class="form-control" readonly="readonly" value="">
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row" id="divAditivoMR">
				<div class="col-sm-12">
			        <hr>  
				    <br>
					<form class="form-material">
						<div class="card">
							<div class="card-block">
							    <div class="form-row">
		                            <!-- Campo Tipo Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									    <label class="font-weight-bold font-italic">Tipo Aditivo</label>
										<select name="idTipoAditivoMAR" id="idTipoAditivoMAR" class="form-control" required="required" onchange="MontaDivVisibleHiddenMAR( 1, 'idTipoAditivoMAR' );">
											<option value="" disabled selected>[-Selecione-]</option>
											    <tagsContrato:listaTipoAditivoRecurso />
										</select> 
									</div>
									<!-- Campo Status do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
										<label class="font-weight-bold font-italic">Status Aditivo</label>
										<select name="idStatusMAR" id="idStatusMAR" class="form-control" required="required" onchange="habilitaStatusMotivoRascunhoMAR();">
											<option value="" disabled selected>[-Selecione-]</option>
											    <tagsContrato:listaStatusAditivo/>
										</select> 
									</div>

                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
								        <label class="font-weight-bold font-italic">Motivo Rascunho</label>
								        <input style="color: #000000" type="text" name="motivoRascunhoMAR" id="motivoRascunhoMAR" disabled="disabled" maxlength="500" class="form-control"  placeholder="Informe o Motivo do Rascunho" value=""> 
								    </div>

							        <div class="form-group form-default form-static-label col-md-3 mb-3">
								        <label class="font-weight-bold font-italic">Tipo Rascunho</label>>
								        <select name="id_rascunhoMAR" id="id_rascunhoMAR" disabled="disabled" class="form-control" >
								            <option value="" disabled selected>[-Selecione-]</option>
								                <tagsContrato:listaTipoRascunho />
								        </select> 
							        </div>

								</div>

								<div class="form-row">
									<!-- Campo Moeda -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Moeda</label>
										<select name="id_moedaMAR" id="id_moedaMAR" class="form-control" onchange="habilitaCotacaoMAR();" required="required" >
											<option value="" disabled selected>Selecione Moeda</option>
											   <tagsContrato:listaMoedaModalAdit />
										</select> 
									</div>

									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic">Valor Recurso</label>
	                                     <input type="text" name="vlrTotalMAR" id="vlrTotalMAR" onblur="cauculoConversaoMAR();" class="form-control" placeholder="Valor do Aditivo" value="">
									</div>								

									<!-- Campo Fase do Contrato -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Valor Convertido</label>
										<input type="text" name="valor_convertidoMAR" id="valor_convertidoMAR" disabled="disabled" class="form-control" placeholder="Valor do contrato" value="${modelListAditivoProduto.valor_convertido}">
									</div>

									<!-- Campo Fase do Contrato -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Cotação</label>
										<input type="text" name="valor_CotacaoMAR" id="valor_CotacaoMAR" disabled="disabled" onblur="cauculoConversaoMAR();" class="form-control" placeholder="Valor do contrato" value="${modelListAditivoProduto.cotacao_moeda}">
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
            <!-- ********************************************************************************************* -->
            <!--                                                                                               -->
            <!-- DIV configurada para Contatação do Upgrade de Recurso.                                        -->
            <!-- - Upgrade de Recurso                                                                          --> 
            <!--     * Listar a relação de recursos do cliente para informar qual servidor terá o upgrade.     -->
            <!--                                                                                               -->
            <!--     * Qual item será upgrade (Disco, CPU, Memoria)	                                           -->
 			<!--                                                                                               -->
            <!--     * Quantidade do Upgrade.                                                                  -->
			<!--                                                                                               -->
 			<!-- ********************************************************************************************* -->
			<div class="row" id="divUpgradeRecursoMG" hidden="hidden">
				<div class="col-sm-12">
					<hr>  
					<br>
					<form class="form-material">
						<div class="card">
							<div class="card-block">
							
								<div class="form-row">
								    <!-- Campo Tipo Aditivo -->
									<div class="form-group form-default form-static-label col-md-6 mb-6">
									    <label class="font-weight-bold font-italic">Tipo Aditivo</label>
										<select name="idTipoAditivoUGR" id="idTipoAditivoUGR" class="form-control" required="required" onchange="MontaDivVisibleHiddenMAR( 1, 'idTipoAditivoUGR' );">
											<option value="" disabled selected>[-Selecione-]</option>
											    <tagsContrato:listaTipoAditivoRecurso />
										</select> 
									</div>
									
									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-6 mb-6">
									     <label class="font-weight-bold font-italic">Valor Recurso</label>
	                                     <input type="text" name="vlrTotalUGR" id="vlrTotalUGR" class="form-control" placeholder="Valor do Aditivo" value="">
									</div>								
									
								</div>									
								<div class="form-row">
									<!-- Campo Hostname -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									    <label class="font-weight-bold font-italic">Hostname</label>
										<select name="idRecurso_HostnameUGR" id="idRecurso_HostnameUGR" class="form-control" >
											<option value="" disabled selected>[-Selecione-]</option>
											    <!-- <tagsContrato:listaStatusContrato/> -->
										</select> 
									</div>

									<!-- Campo Tamanho Disco -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Tamanho Disco</label>
										<input type="text" name="tamanhoDiscoUGR" id="tamanhoDiscoUGR" class="form-control only-number" placeholder="Tamanho Disco" > 
									</div>

									<!-- Campo Família Flavors -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Família Flavors</label>
										<select name="idFamiliaFlavorsUGR" id="idFamiliaFlavorsUGR" onchange="selecionaCpuRamVlrFamiliaUGR( );" class="form-control">
										     <option value="" disabled selected>[-Selecione-]</option>
										</select> 
									</div>
	
								</div>

								<div class="form-row">
	                                <!-- Campo CPU --> 
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
										 <label class="font-weight-bold font-italic">CPU</label>
										 <input type="text" name="cpuUGR" id="cpuUGR" class="form-control" readonly="readonly" placeholder="CPU" > 
	                                </div>
	                                               
	                                <!-- Campo RAM --> 
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">RAM</label>
										 <input type="text" name="ramUGR" id="ramUGR" class="form-control" readonly="readonly" placeholder="RAM" > 
	                                </div>
	                                
									<!-- Campo Valor Valor Ajustado -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
                                         <label class="font-weight-bold font-italic">Valor Ajustado</label>
                                         <input type="text" name="valorUGR" id="valorUGR" class="form-control" readonly="readonly" placeholder="Valor" >
									</div>
								</div>  
							</div>

						</div>
					</form>
			        <hr>  
			        <br>
				</div>
			</div>	
			<!-- ********************************************************************************************* -->
            <!-- DIV configurada para Contatação do Ambiente Bolha.                                            -->
            <!--                                                                                               -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divAmbienteBolhaMG" hidden="hidden">
				<div class="col-sm-12">
					<form class="form-material">
						<div class="card">
							<div class="card-block">
							
								<div class="form-row">
								
								  	<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >Recurso Temporário</label>
									     <select name="recursoTemporarioMAR" id="recursoTemporarioMAR" class="form-control" onchange="ValidaTemReceitaMAR();">
										  <option value="" disabled selected>[-Selecione-]</option>
										  <option value="1">Sim</option>
										  <option value="2">Não</option>
									     </select>								     
									</div>
								
								    <!-- Campo Quantidade -->
								  	<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >Tem Receita</label>
									     <select name="temReceitaMAR" id="temReceitaMAR" class="form-control" disabled="disabled">
										  <option value="" disabled selected>[-Selecione-]</option>
										  <option value="1">Sim</option>
										  <option value="2">Não</option>
									     </select>								     
									</div>
								  
									<!-- Campo Termo Admin -->
									
									<div class="form-group form-default form-static-label col-md-3 mb-6">
                                        <label class="font-weight-bold font-italic">Nome Aprovador</label>
                                        <input type="text" name="nomeAprovadorMAR" id="nomeAprovadorMAR" disabled="disabled" class="form-control" placeholder="Nome Aprovador" >
									</div>

									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6" >
									    <label class="font-weight-bold font-italic" id="lbperiodoUtilizacaoMAR" >Período de Utilização</label>
									    <input type="text" name="periodoUtilizacaoMAR" id="periodoUtilizacaoMAR" class="form-control" disabled="disabled">								     
									</div>	
								</div>
							</div>

						</div>
					</form>
			        <hr>  
			        <br>
				</div>
			</div>	
			<!-- ********************************************************************************************* -->
            <!-- DIV configurada .                                                                             -->
            <!--                                                                                               -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divAmbienteMG" >
				<div class="col-sm-12">
					<form class="form-material">
						<div class="card">
						
							<div class="card-block">
							
								<div class="form-row">
								
								  	<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic" >Tipo Contratação</label>
									     <select name="idTipoContratacaoMAR" id="idTipoContratacaoMAR" class="form-control" required="required">
									        <option value="" disabled selected>[-Selecione-]</option>
									        <tagsContrato:listaTempoLigadoModal/>
									     </select>								     
									</div>
								  
									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic">Suporte</label>
									     <select name="idSuporteMAR" id="idSuporteMAR" class="form-control" required="required">
									        <option value="" disabled selected>[-Selecione-]</option>
									        <tagsContrato:listaSuporteModal/>
									     </select>								     
									</div>	
									
									<div class="form-group form-default form-static-label col-md-4 mb-6">
                                        <label class="font-weight-bold font-italic">ID HubSpot Aditivo</label>
                                        <input type="text" name="idHubSpotAditivoMAR" id="idHubSpotAditivoMAR" class="form-control" placeholder="HubSpot Aditivo" >
									</div>
																
								</div>
							</div>
							
							<div class="card-block">
							
								<div class="form-row">
								    <!-- Campo Quantidade -->
								  	<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic" >Monitoramento</label>
									     <select name="idMonitoramentoMAR" id="idMonitoramentoMAR" class="form-control" required="required">
									        <option value="" disabled selected>[-Selecione-]</option>
									        <tagsContrato:listaMonitoramentoModal/>
									     </select>								     
									</div>
								  
									<!-- Campo Termo Admin -->
									
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									    <label class="font-weight-bold font-italic">Contratação Backup</label>
										<select  name="idBackupMAR" id="idBackupMAR" class="form-control" onchange="validaRetencaoBackup ( 'idRetencaoBackupMAR', 'lbRetencaoBackupMAR', 'idBackupMAR' ); ">
										  <option value="" disabled selected>[-Selecione-]</option>
										  <option value="1">Sim</option>
										  <option value="2">Não</option>
										</select> 
									</div>

									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-4 mb-6" >
									    <label class="font-weight-bold font-italic" id="lbRetencaoBackupMAR" hidden="hidden">Retenção Backup</label>
									    <select name="idRetencaoBackupMAR" id="idRetencaoBackupMAR" class="form-control" hidden="hidden">
									        <option value="" >[-Selecione-]</option>
									        <tagsContrato:listaRetencaoBackupAditivo /> 
									    </select>								     
									</div>								
								</div>
							</div>

						</div>
					</form>
					<hr>  
					<br>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!--                                                                                               -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divRecursoMR">
				<div class="col-sm-12">
			        <h4 class="h4stilo" style="text-align: center; color:#00008B; font-style: italic;font-weight: bold;text-decoration: underline;">Informações Sobre o Recurso Contratado</h4>
					<hr>  
					<br>
					
					<div class="row">
						<div class="col-sm-12">
							<!-- Basic Form Inputs card start -->
							<div class="card">
								<div class="card-block">	
									<div class="form-row">
										<div class="form-group form-default form-static-label col-md-6 mb-6">
											<label class="float-label">ID Aditivo</label>
											<input type="text" name="idRecursoMAR" id="idRecursoMAR" class="form-control" readonly="readonly" value="">
										</div>
										<!-- Data Cadastro -->
										<div class="form-group form-default form-static-label col-md-6 mb-6">
											<label class="float-label">Data Cadastro Recurso</label>
											<input type="text" name="dtCriacaoRecursoMAR" id="dtCriacaoRecursoMAR" class="form-control" readonly="readonly" value="">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<form class="form-material">
						<!-- Basic Form Inputs card start -->
						<div class="card">
							<div class="card-block">
	
	                           <!-- Inicio da implementacao -->
									<div class="form-row">
										<!-- Campo Status -->
										<div class="form-group form-default form-static-label col-md-12 mb-6">
											<label class="font-weight-bold font-italic">Status</label>
											<select name="idStatusRecursoMAR" id="idStatusRecursoMAR" class="form-control" required="required">
												<option value="" disabled selected>[-Selecione-]</option>
												        <tagsContrato:listaStatusRecurso />
											</select> 
										</div>
	
										<!-- Campo TAG Vcenter -->
										<div class="input-group form-default form-static-label col-md-12 mb-6">
	<!--                                          <label class="font-weight-bold font-italic">TAG da Instancia no Vcenter</label> --> 
	                                         <input type="text" name="tagVcenterMAR" id="tagVcenterMAR" class="form-control" aria-describedby="button-addon2" placeholder="TAG da Instancia no Vcenter" >
										     <div class="input-group-append">
												<button type="button" class="btn btn-secondary" data-toggle="modal" id="id_pesquisa_cliente" onclick=" montaTelaInicialAditivoRecurso( );">Gerar TAG Vcenter</button>															        
										     </div>
										</div>									
									</div>
		
									<hr>  
									<br>
		
									<div class="form-row">
										<!-- Campo Tipo Disco -->
										<div class="form-group form-default form-static-label col-md-3 mb-6">
											<label class="font-weight-bold font-italic">Tipo Disco</label>
											<select name="idTipoDiscoMAR" id="idTipoDiscoMAR" class="form-control" required="required" >
												<option value="" disabled selected>[-Selecione-]</option>
												        <tagsContrato:listaTipoDisco />
											</select> 
										</div>
										<!-- Campo Sistema Operacional -->
										<div class="form-group form-default form-static-label col-md-3 mb-6">
											<label class="font-weight-bold font-italic">Sistema Operacional</label>
											<select name="idSoMAR" id="idSoMAR" class="form-control" required="required">
												<option value="" disabled selected>[-Selecione-]</option>
												      <tagsContrato:listaSistemaOperacional /> 
											</select> 
										</div>
		
										<!-- Campo Ambiente -->
										<div class="form-group form-default form-static-label col-md-3 mb-6">
											<label class="font-weight-bold font-italic">Ambiente</label>
											<select name="idAmbienteMAR" id="idAmbienteMAR" class="form-control" required="required">
												<option value="" disabled selected>[-Selecione-]</option>
												       <tagsContrato:listaAmbiente /> 
											</select> 
										</div>
		
										<!-- Campo Tipo Serviçoo -->
										<div class="form-group form-default form-static-label col-md-3 mb-6">
											<label class="font-weight-bold font-italic">Tipo Serviço</label>
											<select name="idTipoServicoMAR" id="idTipoServicoMAR" class="form-control" required="required">
												<option value="" disabled selected>[-Selecione-]</option>
												        <tagsContrato:listaTipoServico />
											</select> 
										</div>
									</div>
									<hr>  
									<br>
		
									<div class="form-row">
										<!-- Campo Hostname -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">Hostname</label>
											<input type="text" name="hostnameModalRecursoMAR" id="hostnameModalRecursoMAR" class="form-control" placeholder="Hostname" > 
										</div>
		
										<!-- Campo Tamanho Disco -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">Tamanho Disco</label>
											<input type="text" name="tamanhoDiscoModalRecursoMAR" id="tamanhoDiscoModalRecursoMAR" class="form-control only-number" placeholder="Tamanho Disco" > 
										</div>
		
										<!-- Campo Primary IP -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">Primary IP</label>
											<input type="text" name="primaryIPModalRecursoMAR" id="primaryIPModalRecursoMAR" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="Primary IP" >  
										</div>
										
										<!-- Campo Host Vcenter -->
										<div class="form-group form-default form-static-label col-md-6 mb-6">
											<label class="font-weight-bold font-italic">Host Vcenter</label>
											<input type="text" name="hostVcenterModalRecursoMAR" id="hostVcenterModalRecursoMAR" class="form-control" placeholder="Host Vcenter" > 
										</div>
		
										<!-- Campo EIP Vcenter -->
										<div class="form-group form-default form-static-label col-md-6 mb-6">
											<label class="font-weight-bold font-italic">EIP Vcenter</label>
											<input type="text" name="eipVcenterModalRecursoMAR" id="eipVcenterModalRecursoMAR" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="EIP Vcenter" > 
										</div>
										
										
									</div>
	
									<hr>  
									<br>
	
									<div class="form-row">
										<!-- Campo Nuvem -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">Nuvem</label>
											<select name="idNuvemMAR" id="idNuvemMAR" class="form-control" onchange="montaSelectFamiliaMAR( 0 ); montaSelectSiteMAR( 0 );">
												<option value="" disabled selected>[-Selecione-]</option>
												   <tagsContrato:listaNuvemSiteMAD/>
											</select>
										</div>
										
										<!-- Campo Site -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
										    <label class="font-weight-bold font-italic">Site</label>
											<select name="idSiteMAR" id="idSiteMAR" class="form-control" >
												<option value="" disabled selected>[-Selecione-]</option>
												    <!-- <tagsContrato:listaStatusContrato/> -->
											</select> 
										</div>
																			
										<!-- Campo Família Flavors -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">Família Flavors</label>
											<select name="idFamiliaFlavorsMAR" id="idFamiliaFlavorsMAR" onchange="selecionaCpuRamVlrFamilia( 0 );" class="form-control">
											     <option value="" disabled selected>[-Selecione-]</option>
											</select> 
										</div>
		
									</div>
									<br>
		
									<div class="form-row">
		                                <!-- Campo CPU --> 
		                                <div class="form-group form-default form-static-label col-md-4 mb-6">
											 <label class="font-weight-bold font-italic">CPU</label>
											 <input type="text" name="cpuMAR" id="cpuMAR" class="form-control" readonly="readonly" placeholder="CPU" > 
		                                </div>
		                                               
		                                <!-- Campo RAM --> 
		                                <div class="form-group form-default form-static-label col-md-4 mb-6">
											<label class="font-weight-bold font-italic">RAM</label>
											 <input type="text" name="ramMAR" id="ramMAR" class="form-control" readonly="readonly" placeholder="RAM" > 
		                                </div>
		                                
										<!-- Campo Valor Família Flavors -->
										<div class="form-group form-default form-static-label col-md-4 mb-6">
	                                         <label class="font-weight-bold font-italic">Valor Família</label>
	                                         <input type="text" name="valorMAR" id="valorMAR" class="form-control" placeholder="Valor" >
										</div>
									</div>
	
									<hr>  
									<br>
									<!-- Campo Valor Observações Recurso -->
								    <div class="form-group form-default form-static-label mb-6">
										<label class="font-weight-bold font-italic">Observações Sobre o Recurso</label>
										<textarea class="form-control" id="ObservacoesRecursoMAR" name="ObservacoesRecursoMAR" placeholder="Observações" rows="5" ></textarea>
									</div>
		
	                            <!-- Fim Inicio da implementacao -->
	
							</div>
						</div>
					</form>	
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- O Campo Observacao estara visivel para todas as opcoes de Aditivo.                            -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divobservacaoAditivoMR">
				<div class="col-sm-12">
		        <hr>  
			    <br>											
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">
							<div class="form-row">
							  <div class="form-group form-default form-static-label col-md-12 mb-6">
							      <label for="observacao">Observação Aditivo</label>
								  <textarea class="form-control" id="observacaoAditivoMAR" name="observacaoAditivoMAR" placeholder="Observação" rows="10" ></textarea>
							  </div>
							</div>
						</div>
					</div>
			        <hr>  
				    <br>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- A Data Vigencia, sempre ira ser visivel com os valores da data atual(Abetro para alteracao) e -->
            <!-- data final, de acordo com a data final do contrato.                                           -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="DivVigenciaAditivoMR" >
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">
							<div class="form-row">
								<!-- Campo Data Início --> 
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<span class="font-weight-bold font-italic" style="color: #708090">Data Início</span>
									<input style="color: #B0C4DE" type="text" name="dtInicioMAR" id="dtInicioMAR" class="form-control" required="required" placeholder="Data início do Aditivo" value=""> 
									
								</div>
					
								<!-- Campo Data Final -->
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<span class="font-weight-bold font-italic" style="color: #708090">Data Final</span>
									<input style="color: #B0C4DE" type="text" name="dtFinalMAR" id="dtFinalMAR" required="required" class="form-control" readonly="readonly" placeholder="Data final do Aditivo" value="">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
	      </div>
	      
	      <h4 class="h4stilo" style="text-align: center; color:#00008B; font-style: italic;font-weight: bold;text-decoration: underline;">Lista de Aditivo por Contrato</h4>

	      <div class="modal-body">
            <div style="height: 300px; overflow: scroll;   ">
				<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoAditivoRecurso" >
				  <thead>
				    <tr>
				      <th> </th>
				      <th scope="col">ID Aditivo        </th>
				      <th scope="col">ID HubSpot Aditivo</th> 
				      <th scope="col">Data Cad.         </th>
				      <th scope="col">Valor Aditivo     </th>
				    </tr>
				  </thead>
				  <tbody>
				   
				  </tbody>
				  
				</table>
				<br>
                <strong><h5 style="color: blue;">Histórico Troca de Família</h5></strong>
				<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoHistTrocaFmilia" >
				  <thead>
				    <tr>
				      <th> </th>
				      <th scope="col">ID Recurso   </th>
				      <th scope="col">Hostname     </th>
				      <th scope="col">Data Cad.    </th>
					  <th scope="col">Família Atual</th>
				    </tr>
				  </thead>
				  <tbody>
				   
				  </tbody>
				  
				</table>
				
				
				    
			</div>
	      </div>

	      <div class="modal-footer">
	      
	        <button type="button" id="btNovoModal"        class="btn waves-effect waves-light btn-outline-primary   float-left  pequeno" onclick="limparModalAditivoMAR( 2 ); habilitarComponentesAditivoRecurso(false);">Novo</button>	     
	        <button type="button" id="btAddAditivoModal"  class="btn waves-effect waves-light btn-outline-success   float-right pequeno" onclick="AddAditivoModalMAR();" >Salvar</button>
	        <button type="button" id="btNovoRecursoModal" class="btn waves-effect waves-light btn-outline-info      float-right pequeno" onclick="limparModalAditivoMAR( 1 );" >Novo Recurso</button>
	        <button type="button" id="btFecharModal"      class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
 </div>
 
	<div class="modal t-modal primary" id="ModalMenssagemOK_MAR" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Resultado Manutenção</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <strong >Resultado: </strong> <span id="msgResultMAR"></span>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
	      </div>
	    </div>
	  </div>
	</div>
 
 <script type="text/javascript">

 var dataNascimento = $("#periodoUtilizacaoMAR").val();

 if (dataNascimento != null && dataNascimento != '') {

 	var dateFormat = new Date(dataNascimento);
 	
 	$("#periodoUtilizacaoMAR").val(dateFormat.toLocaleDateString('pt-BR',{timeZone: 'UTC'}));

 }


 $( function() {
 	  
 	  $("#periodoUtilizacaoMAR").datepicker({
 		    dateFormat: 'dd/mm/yy',
 		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
 		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
 		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
 		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
 		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
 		    nextText: 'Próximo',
 		    prevText: 'Anterior'
 		});
 } );
    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	 $(document).ready(function () {
	     $('.datepicker').datepicker({
	         format: 'dd/mm/yyyy',                
	         language: 'pt-BR'
	     });
	 });
	 
    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    $('#ModalAditivoRecurso').on('show.bs.modal', function(e){
		var dtFinal = document.getElementById( "dt_final" ).value; // 6

		const horas = new Date();
		$("#dtInicioMAR").val( horas.toLocaleDateString('pt-BR') );
		$("#dtFinalMAR" ).val( dtFinal                           );
		montaTelaInicialAditivoRecurso( );
		listaAditivosRecursoInicial();
	});
    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    $("#valorUGR"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
    $("#vlrTotalUGR").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
    $("#valorMAR"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

//    $("#vlrTotalMAR").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
    $("#vlrTotalMAR"        ).maskMoney({ showSymbol:true, symbol:""   , decimal:",", thousands:"." });
    $("#valor_convertidoMAR").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
    $("#valor_CotacaoMAR"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    $('#ModalAditivoRecurso').on('hidden.bs.modal', function (e) {
    	limparModalAditivoMAR( 2 );
    	goRecursoAditivo( document.getElementById("id_cliente").value );
	})


    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function validaRetencaoBackup( campo1, campo2, campoValor ) {		
		 var vlCampo = document.getElementById( campoValor ).value;		 
		 if(vlCampo === "1"){
			document.getElementById(campo1).removeAttribute("hidden");
	    	document.getElementById(campo2).removeAttribute("hidden");
		 }	
	     else{	
	    	// desabilita todas as outras DIV's.
	    	document.getElementById(campo1).setAttribute("hidden","");
	    	document.getElementById(campo2).setAttribute("hidden","");
	     }	     
	}
    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function montaTelaInicialAditivoRecurso( ) {

			var urlAction = document.getElementById("formContrato").action;
			 $.ajax({
					method : "get",
					url : urlAction,
					data : 'acao=montaTelaInicialAditivoRecurso',

					success: function(lista){
							var json = JSON.parse(lista);
							$("#tagVcenterMAR").val( json ); // 
					}
			 }).fail(function( xhr, status, errorThrown ){
					// alert('Erro carregar select Produto: ' + xhr.responseText);
		  			var nomeModal = 'ModalAditivoRecurso';
		  			var iconiErro = 'error';
					var texto = 'Erro montar Tela Inicial Aditivo Recurso: ';
					MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );

			 }); 
	}

	function montaSelectFamiliaMAR( idFamiliaSelecionada ) {
		var idNuvem = $("#idNuvemMAR option:selected").val();	
		var urlAction = document.getElementById("formContrato").action;

		$.ajax({
			method : "get",
			url    : urlAction,
			data   : 'acao=montaSelectFamiliaModal&idNuvem=' + idNuvem,
	
			success: function(lista){
					    var option = '<option value="" disabled selected>[-Selecione-]</option>';
					    var selected = '';
						var json = JSON.parse(lista);

						for(var p = 0; p < json.length; p++){
	      					if( json[p].id_familia_flavors === idFamiliaSelecionada  )
	      				  	     selected = 'selected';
	      					 else selected = '';
							
							option += '<option value=' + json[p].id_familia_flavors + ' ' + selected + '>' + json[p].familia + '</option>';
						}
						$("#idFamiliaFlavorsMAR").html(option).show();  
			}
		 }).fail(function( xhr, status, errorThrown ){
				// alert('Erro carregar select Produto: ' + xhr.responseText);
		  		var nomeModal = 'ModalAditivoRecurso';
		  		var iconiErro = 'error';
				var texto = 'Erro ao montar Select Familia Modal: ';
				MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
		 }); 
			 
	}

	 /******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/
	 function montaSelectSiteMAR( SiteSelecionado  ){

		   var idNuvem  = document.getElementById("idNuvemMAR").value;

	       if( idNuvem != null && idNuvem != '' && idNuvem.trim() != '' ){
	    	   var urlAction = document.getElementById("formContrato").action;

	      		$.ajax({
	      			
	      			method : "get",
	      			url : urlAction,
	      			data : "idNuvem=" + idNuvem + '&acao=montaSelectSiteRecusto',
	      			success: function(lista){
	      				var option = '<option value="" disabled selected>[-Selecione-]</option>';

	      				var json = JSON.parse(lista);
	      				
	      				for(var p = 0; p < json.length; p++){
	      					if( json[p].id_site === SiteSelecionado  )
	      				  	     selected = 'selected';
	      					 else selected = '';

	      					option += '<option value=' + json[p].id_site + ' ' + selected + '>' + json[p].nome + '</option>';

	  //    					option += '<option value=' + json[p].id_site + '>' + json[p].nome + '</option>';
	      				}
	      				$("#idSiteMAR").html(option).show();  
	      			}
	      		}).fail(function( xhr, status, errorThrown ){
	      			// alert('Erro ao buscar Cliente: ' + xhr.responseText);
		  			var nomeModal = 'ModalAditivoRecurso';
		  			var iconiErro = 'error';
					var texto = 'Erro ao montar Select Site MAR: ';
					MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
	      		});    	   
	       }
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
/*	
	$(document).ready(function () {

	    $('#idFamiliaFlavorsMAR').change(function () {

	        var es        = document.getElementById('idFamiliaFlavorsMAR');
	        idFamiliaFl   = es.options[es.selectedIndex].value;
	        var urlAction = document.getElementById("formContrato").action;
	        
	  		$.ajax({
	  			method : "get",
	  			url    : urlAction,
	  			data   : "idFamiliaFl=" + idFamiliaFl + '&acao=montaCamosFamiliaFl',
	  			success: function(lista){
	  				var json = JSON.parse(lista);

	  				var cpu = json.cpu;
	  				var ram = json.ram;
	  				var vlr = json.valor;

	  				$("#cpuMAR").val(cpu);
	  				$("#ramMAR").val(ram);
	  				$("#valorMAR").val(vlr);
	  			}
	  		}).fail(function( xhr, status, errorThrown ){
	  			alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
	  		}); 
	    });
	});
*/

function selecionaCpuRamVlrFamilia( idFamiliaFlSelect ) {

	 var urlAction    = document.getElementById("formContrato").action;
	 var idFamiliaFl = document.getElementById("idFamiliaFlavorsMAR").value;
	 
	 if( idFamiliaFl == null || idFamiliaFl == '' || idFamiliaFl.trim() == '' )
		 idFamiliaFl = idFamiliaFlSelect; 

		$.ajax({
			method : "get",
			url    : urlAction,
			data   : "idFamiliaFl=" + idFamiliaFl + '&acao=montaCamosFamiliaFl',
			success: function(lista){
				var json = JSON.parse(lista);

				$("#cpuMAR"     ).val( json.cpu   );
				$("#ramMAR"     ).val( json.ram   );
				$("#valorMAR"   ).val( json.valor );

//				$("#id_familia_flavors").html(option).show();  
			}
		}).fail(function( xhr, status, errorThrown ){
			// alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
		  	var nomeModal = 'ModalAditivoRecurso';
		  	var iconiErro = 'error';
			var texto = 'Erro ao buscar valores campo Família Flavors: ';
			MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
		}); 	
}	
	
	
	function limparModalAditivoMAR( tipoLimpeza ) {
		
			$("#idTipoContratacaoMAR"       ).val( '' );
			$("#idSuporteMAR"               ).val( '' );
			$("#idMonitoramentoMAR"         ).val( '' );
			$("#idBackupMAR"                ).val( '' );
			$("#idRetencaoBackupMAR"        ).val( '' );
			$("#idStatusRecursoMAR"         ).val( '' );
			$("#idRecursoMAR"               ).val( '' );
			$("#dtCriacaoRecursoMAR"        ).val( '' );
			$("#recursoTemporarioMAR"       ).val( '' );
			$("#idRetencaoBackupMAR"        ).val( '' );			
			$("#temReceitaMAR"              ).val( '' );
			$("#nomeAprovadorMAR"           ).val( '' );
			$("#periodoUtilizacaoMAR"       ).val( '' );			
			
			// Limpeza para inserir novo item
			if( tipoLimpeza !== 1 ){
				$("#tagVcenterMAR"              ).val( '' );
//				$("#idTipoAditivoMAR"           ).val( '' );
				$("#idStatusMAR"                ).val( '' );
				$("#vlrTotalMAR"                ).val( '' );
				$("#vlrTotalUGR"                ).val( '' );			
				$("#id_moedaMAR"                ).val( '' );
				$("#valor_convertidoMAR"        ).val( '' );
				$("#valor_CotacaoMAR"           ).val( '' );
				$("#observacaoAditivoMAR"       ).val( '' );
				
				$("#id_rascunhoMAR"             ).val( '' );
				$("#motivoRascunhoMAR"          ).val( '' );
			}
            if( tipoLimpeza === 2 ){
				$("#idAditivoMAR"               ).val( '' );
				$("#dtCriacaoMAR"               ).val( '' );
				$("#loginCadastroMAR"           ).val( '' );	
				$("#vlrTotalMAR"                ).val( '' );
				$("#idTipoAditivoMAR"           ).val( '' );					
				montaTelaInicialAditivoRecurso( );
			}
			
			$("#idTipoDiscoMAR"             ).val( '' );
			$("#idSoMAR"                    ).val( '' );
			$("#idAmbienteMAR"              ).val( '' );
			$("#idTipoServicoMAR"           ).val( '' );
			$("#hostnameModalRecursoMAR"    ).val( '' );
			$("#tamanhoDiscoModalRecursoMAR").val( '' );
			$("#primaryIPModalRecursoMAR"   ).val( '' );			
			$("#eipVcenterModalRecursoMAR"  ).val( '' );
			$("#hostVcenterModalRecursoMAR" ).val( '' );			
			$("#idNuvemMAR"                 ).val( '' );
			$("#idSiteMAR"                  ).val( '' );
			$("#idFamiliaFlavorsMAR"        ).val( '' );
			$("#cpuMAR"                     ).val( '' );
			$("#ramMAR"                     ).val( '' );
			$("#valorMAR"                   ).val( '' );
			$("#ObservacoesRecursoMAR"      ).val( '' );
			$("#idRecurso_HostnameUGR"      ).val( '' );
			$("#tamanhoDiscoUGR"            ).val( '' );
			$("#idFamiliaFlavorsUGR"        ).val( '' );
			$("#cpuMAR"                     ).val( '' );
//			$("#idTipoAditivoUGR"           ).val( '' );
			$("#cpuUGR"                     ).val( '' );
			$("#ramUGR"                     ).val( '' );
			$("#valorUGR"                   ).val( '' );
			habilitaStatusMotivoRascunhoMAR();

	}	
	 /******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/
	 function habilitarComponentesAditivoRecurso(habilitar) {
	    document.getElementById('idTipoAditivoMAR'           ).disabled = habilitar;                
	    document.getElementById('idStatusMAR'                ).disabled = habilitar;           
	    document.getElementById('id_moedaMAR'                ).disabled = habilitar;                        
	    document.getElementById('vlrTotalMAR'                ).disabled = habilitar;                
	    document.getElementById('valor_convertidoMAR'        ).disabled = habilitar;                      
	    document.getElementById('valor_CotacaoMAR'           ).disabled = habilitar;                          
	    document.getElementById('idTipoAditivoUGR'           ).disabled = habilitar;                               
	    document.getElementById('vlrTotalUGR'                ).disabled = habilitar;                                   
	    document.getElementById('idRecurso_HostnameUGR'      ).disabled = habilitar;                                
	    document.getElementById('tamanhoDiscoUGR'            ).disabled = habilitar;                         
	    document.getElementById('idFamiliaFlavorsUGR'        ).disabled = habilitar;                            
	    document.getElementById('cpuUGR'                     ).disabled = habilitar;                            
	    document.getElementById('ramUGR'                     ).disabled = habilitar;                                 
	    document.getElementById('valorUGR'                   ).disabled = habilitar;                      
	    document.getElementById('temReceitaMAR'              ).disabled = habilitar;                         
	    document.getElementById('nomeAprovadorMAR'           ).disabled = habilitar;                      
	    document.getElementById('periodoUtilizacaoMAR'       ).disabled = habilitar;                     
	    document.getElementById('recursoTemporarioMAR'       ).disabled = habilitar;                              
	    document.getElementById('idTipoContratacaoMAR'       ).disabled = habilitar;                                
	    document.getElementById('idSuporteMAR'               ).disabled = habilitar;                               
	    document.getElementById('idMonitoramentoMAR'         ).disabled = habilitar;                                   
	    document.getElementById('idBackupMAR'                ).disabled = habilitar;                         
	    document.getElementById('idRetencaoBackupMAR'        ).disabled = habilitar;                            
	    document.getElementById('idRecursoMAR'               ).disabled = habilitar;                            
	    document.getElementById('dtCriacaoRecursoMAR'        ).disabled = habilitar;                
	    document.getElementById('idStatusRecursoMAR'         ).disabled = habilitar;           
	    document.getElementById('tagVcenterMAR'              ).disabled = habilitar;                        
	    document.getElementById('idTipoDiscoMAR'             ).disabled = habilitar;                
	    document.getElementById('idSoMAR'                    ).disabled = habilitar;                      
	    document.getElementById('idAmbienteMAR'              ).disabled = habilitar;                          
	    document.getElementById('idTipoServicoMAR'           ).disabled = habilitar;                               
	    document.getElementById('hostnameModalRecursoMAR'    ).disabled = habilitar;                                   
	    document.getElementById('tamanhoDiscoModalRecursoMAR').disabled = habilitar;                                
	    document.getElementById('primaryIPModalRecursoMAR'   ).disabled = habilitar;                         
	    document.getElementById('hostVcenterModalRecursoMAR' ).disabled = habilitar;                            
	    document.getElementById('eipVcenterModalRecursoMAR'  ).disabled = habilitar;                            
	    document.getElementById('idNuvemMAR'                 ).disabled = habilitar;                                 
	    document.getElementById('idSiteMAR'                  ).disabled = habilitar;                      
	    document.getElementById('idFamiliaFlavorsMAR'        ).disabled = habilitar;                         
	    document.getElementById('cpuMAR'                     ).disabled = habilitar;                      
	    document.getElementById('ramMAR'                     ).disabled = habilitar;                     
	    document.getElementById('valorMAR'                   ).disabled = habilitar;                              
	    document.getElementById('ObservacoesRecursoMAR'      ).disabled = habilitar;                                
	    document.getElementById('observacaoAditivoMAR'       ).disabled = habilitar;                               
	    document.getElementById('dtInicioMAR'                ).disabled = habilitar;                                   
	    document.getElementById('dtFinalMAR'                 ).disabled = habilitar;                                                                                                               
	    document.getElementById('btAddAditivoModal'          ).disabled = habilitar;                                   
	    document.getElementById('btNovoRecursoModal'         ).disabled = habilitar;  
	    document.getElementById('id_rascunhoMAR'             ).disabled = habilitar;                                   
	    document.getElementById('motivoRascunhoMAR'          ).disabled = habilitar;  
	    

	 }

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
//	function buscarEditarAditivoRecurso( idAditivo, idContrato ) {
	function buscarEditarAditivoRecurso( idRecurso ) {
		var urlAction = document.getElementById("formContrato").action;
//		alert('idAditivo: ' + idAditivo + ' - idContrato: ' + idContrato ); 
		
		$.ajax({ 			
	  		method : "get",
	  		url : urlAction,
//	  		data : 'acao=buscarEditarAditivoRecurso&idAditivo=' + idAditivo + '&idContrato=' + idContrato,
	  		data : 'acao=buscarEditarAditivoRecurso&idRecurso=' + idRecurso,
	  		success: function(lista){
	  			var json = JSON.parse(lista);
//	  			alert('recurso_temporario' + json.recurso_temporario);
	  			
	  			
	  			$("#idAditivoMAR"               ).val( json.id_aditivado                ); // 1 
	  			$("#idStatusMAR"                ).val( json.id_status                   ); // 2 
	  			$("#dtCriacaoMAR"               ).val( json.dt_criacao                  ); // 3 
	  			$("#vlrTotalMAR"                ).val( json.valor_total                 ); // 4 
	  			$("#observacaoAditivoMAR"       ).val( json.observacao_aditivo          ); // 5 
	  			$("#idStatusRecursoMAR"         ).val( json.id_status_recurso           ); // 6 
	  			$("#recursoTemporarioMAR"       ).val( json.recurso_temporario          ); // 7
	  			$("#idBackupMAR"                ).val( json.id_backup                   ); // 8 
	  			$("#idRetencaoBackupMAR"        ).val( json.id_retencao_backup          ); // 9 
	  			$("#idTipoDiscoMAR"             ).val( json.id_tipo_disco               ); // 10 
	  			$("#idSoMAR"                    ).val( json.id_so                       ); // 11
	  			$("#idAmbienteMAR"              ).val( json.id_ambiente                 ); // 12
	  			$("#idFamiliaFlavorsMAR"        ).val( json.id_familia_flavors          ); // 13
	  			$("#idTipoServicoMAR"           ).val( json.id_tipo_servico             ); // 14
	  			$("#hostnameModalRecursoMAR"    ).val( json.host_name_modal_recurso     ); // 15
	  			$("#tamanhoDiscoModalRecursoMAR").val( json.tamanho_disco_modal_recurso ); // 16
	  			$("#primaryIPModalRecursoMAR"   ).val( json.primary_ip_modalrecurso     ); // 17
	  			$("#tagVcenterMAR"              ).val( json.tag_vcenter                 ); // 18
	  			$("#ObservacoesRecursoMAR"      ).val( json.observacoes_recurso         ); // 19
	  			$("#idSuporteMAR"               ).val( json.id_suporte                  ); // 20
	  			$("#idMonitoramentoMAR"         ).val( json.id_monitoramento            ); // 21
	  			$("#idTipoContratacaoMAR"       ).val( json.id_tipo_contratacao         ); // 22
	  			$("#idTipoAditivoMAR"           ).val( json.id_tipo_aditivo             ); // 23
	  			$("#temReceitaMAR"              ).val( json.adti_sem_receita            ); // 24
	  			$("#nomeAprovadorMAR"           ).val( json.aprovador_adit_sem_receita  ); // 25
	  			$("#periodoUtilizacaoMAR"       ).val( json.periodo_utilizacao_bolha    ); // 26
	  			$("#idNuvemMAR"                 ).val( json.id_nuvem                    ); // 27
	  			$("#idSiteMAR"                  ).val( json.id_site                     ); // 28
	  			$("#observacaoAditivoMAR"       ).val( json.observacao_vigencia         ); // 29
	  			$("#dtInicioMAR"                ).val( json.dt_inicio                   ); // 30
	  			$("#dtFinalMAR"                 ).val( json.dt_final                    ); // 31
	  			$("#loginCadastroMAR"           ).val( json.login_cadastro              ); // 32	  			
	  			$("#eipVcenterModalRecursoMAR"  ).val( json.eip_Vcenter                 ); // 33
	  			$("#hostVcenterModalRecursoMAR" ).val( json.host_Vcenter                ); // 34
	  			$("#id_moedaMAR"                ).val( json.id_moeda                    ); // 35	  			
	  			$("#idRecursoMAR"               ).val( json.id_recurso                  ); // 36
				$("#dtCriacaoRecursoMAR"        ).val( json.dt_criacao_recurso          ); // 37
				$("#idHubSpotAditivoMAR"        ).val( json.hubspot_aditivo             ); // 38
				$("#id_rascunhoMAR"             ).val( json.id_rascunho                 ); // 39
				$("#motivoRascunhoMAR"          ).val( json.motivoRascunho              ); // 40
				

	  			if( json.id_backup === 1 )
	  			    validaRetencaoBackup ( 'idRetencaoBackupMAR', 'lbRetencaoBackupMAR', 'idBackupMAR' ); 
	  			
	  			montaSelectFamiliaMAR( json.id_familia_flavors ); 
	  			montaSelectSiteMAR( json.id_site );
	  			selecionaCpuRamVlrFamilia( json.id_familia_flavors );
	  			
				 if(document.getElementById('tipoUserAdmin').value.trim() !== "1" )
					 habilitarComponentesAditivoRecurso(true);
				 else habilitarComponentesAditivoRecurso(false);

	  			
	   		}
	  	 }).fail(function( xhr, status, errorThrown ){
	  			// alert('Erro carregar select Produto: ' + xhr.responseText);
	  			var nomeModal = 'ModalAditivoRecurso';
	  			var iconiErro = 'error';
				var texto     = 'Erro ao buscar Editar/Aditivo Recurso: ';
				MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
	  	 }); 
	}
	

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function listaAditivosRecursoInicial() {
		 var urlAction  = document.getElementById( "formContrato" ).action;
		 var idContrato = document.getElementById( "id_contrato"  ).value;

		 $.ajax({
				method : "get",
				url    : urlAction,
				data   : 'acao=listaAditivosRecursoInicial&idContrato=' + idContrato,
				success: function(lista){
	  				var json = JSON.parse(lista);

	  				var html              = '';
	  				var idAditivadoTemp   = '';
	  				var dtCriacaoTemp     = '';
	  				var loginCadastroTemp = '';
	  				var idAditivadoAux    = 0;
	  				$('#tabelaResutadoAditivoRecurso > tbody > tr').remove();
	  				html =  motaTabelaRecursos( json );	
  				    $("#tabelaResutadoAditivoRecurso tbody").html(html).show();  
  				    
  				    getlistaHistoricoTrocaFamilia( );	
				}
			 }).fail(function( xhr, status, errorThrown ){
					// alert('Erro carregar select Produto: ' + xhr.responseText);
		  			var nomeModal = 'ModalAditivoRecurso';
		  			var iconiErro = 'error';
					var texto     = 'Erro ao lista Aditivos Recurso Inicial: ';
					MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
			 }); 
			
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function AddAditivoModalMAR() {

		 var urlAction                   = document.getElementById("formContrato"               ).action;
		 var idAditivoMAR                = document.getElementById("idAditivoMAR"               ).value; //  1
		 var dtCriacaoMAR                = document.getElementById("dtCriacaoMAR"               ).value; //  2
		 var idTipoAditivoMAR            = document.getElementById("idTipoAditivoMAR"           ).value; //  3
		 var idStatusMAR                 = document.getElementById("idStatusMAR"                ).value; //  4
		 var vlrTotalMAR                 = document.getElementById("vlrTotalMAR"                ).value; //  5
		 var idTipoContratacaoMAR        = document.getElementById("idTipoContratacaoMAR"       ).value; //  6
		 var idSuporteMAR                = document.getElementById("idSuporteMAR"               ).value; //  7
		 var idMonitoramentoMAR          = document.getElementById("idMonitoramentoMAR"         ).value; //  8
		 var idBackupMAR                 = document.getElementById("idBackupMAR"                ).value; //  9
		 var idRetencaoBackupMAR         = document.getElementById("idRetencaoBackupMAR"        ).value; //  10      
		 var idStatusRecursoMAR          = document.getElementById("idStatusRecursoMAR"         ).value; //  11       
		 var tagVcenterMAR               = document.getElementById("tagVcenterMAR"              ).value; //  12            
		 var idTipoDiscoMAR              = document.getElementById("idTipoDiscoMAR"             ).value; //  13           
		 var idSoMAR                     = document.getElementById("idSoMAR"                    ).value; //  14                  
		 var idAmbienteMAR               = document.getElementById("idAmbienteMAR"              ).value; //  15            
		 var idTipoServicoMAR            = document.getElementById("idTipoServicoMAR"           ).value; //  16         
		 var hostnameModalRecursoMAR     = document.getElementById("hostnameModalRecursoMAR"    ).value; //  17  
		 var tamanhoDiscoModalRecursoMAR = document.getElementById("tamanhoDiscoModalRecursoMAR").value; //  18
		 var primaryIPModalRecursoMAR    = document.getElementById("primaryIPModalRecursoMAR"   ).value; //  19
		 var idNuvemMAR                  = document.getElementById("idNuvemMAR"                 ).value; //  20 
		 var idSiteMAR                   = document.getElementById("idSiteMAR"                  ).value; //  21 
		 var idFamiliaFlavorsMAR         = document.getElementById("idFamiliaFlavorsMAR"        ).value; //  22      
		 var ObservacoesRecursoMAR       = document.getElementById("ObservacoesRecursoMAR"      ).value; //  23   
		 var observacaoAditivoMAR        = document.getElementById("observacaoAditivoMAR"       ).value; //  24    
		 var dtInicioMAR                 = document.getElementById("dtInicioMAR"                ).value; //  25              
		 var dtFinalMAR                  = document.getElementById("dtFinalMAR"                 ).value; //  26               
		 var idContrato                  = document.getElementById("id_contrato"                ).value; //  27
		 var recursoTemporarioMAR        = document.getElementById("recursoTemporarioMAR"       ).value; //  28
		 var temReceitaMAR               = document.getElementById("temReceitaMAR"              ).value; //  29               
		 var nomeAprovadorMAR            = document.getElementById("nomeAprovadorMAR"           ).value; //  30               
		 var periodoUtilizacaoMAR        = document.getElementById("periodoUtilizacaoMAR"       ).value; //  31		 
		 var idTipoAditivoUGR            = document.getElementById("idTipoAditivoUGR"           ).value; //  32              
		 var idRecurso_HostnameUGR       = document.getElementById("idRecurso_HostnameUGR"      ).value; //  33               
		 var tamanhoDiscoUGR             = document.getElementById("tamanhoDiscoUGR"            ).value; //  34
		 var idFamiliaFlavorsUGR         = document.getElementById("idFamiliaFlavorsUGR"        ).value; //  35
		 var cpuUGR                      = document.getElementById("cpuUGR"                     ).value; //  36               
		 var ramUGR                      = document.getElementById("ramUGR"                     ).value; //  37               
		 var valorUGR                    = document.getElementById("valorUGR"                   ).value; //  38
		 var vlrTotalUGR                 = document.getElementById("vlrTotalUGR"                ).value; //  39
		 var id_moedaMAR                 = document.getElementById("id_moedaMAR"                ).value; //  40               
		 var valor_convertidoMAR         = document.getElementById("valor_convertidoMAR"        ).value; //  41
		 var valor_CotacaoMAR            = document.getElementById("valor_CotacaoMAR"           ).value; //  42
		 var valorMAR                    = document.getElementById("valorMAR"                   ).value; //  43
		 var eipVcenterModalRecursoMAR	 = document.getElementById("eipVcenterModalRecursoMAR"  ).value; //  44
		 var hostVcenterModalRecursoMAR  = document.getElementById("hostVcenterModalRecursoMAR" ).value; //  45
		 var idRecursoMAR                = document.getElementById("idRecursoMAR"               ).value; //  46
		 var v_idHubSpotAditivoMAR       = document.getElementById("idHubSpotAditivoMAR"        ).value; //  47
		 var id_rascunhoMAR              = document.getElementById("id_rascunhoMAR"             ).value; //  48
		 var motivoRascunhoMAR           = document.getElementById("motivoRascunhoMAR"          ).value; //  49

		 var dados = 'acao=AddAditivoRecursoModal'   +
		             '&idAditivoMAR='       	     + idAditivoMAR		           + //  1
		             '&dtCriacaoMAR='      		     + dtCriacaoMAR		           + //  2
		             '&idTipoAditivoMAR='            + idTipoAditivoMAR	           + //  3
		             '&idStatusMAR='     		     + idStatusMAR		           + //  4
		             '&vlrTotalMAR='      	         + vlrTotalMAR		           + //  5
		             '&idTipoContratacaoMAR='      	 + idTipoContratacaoMAR	       + //  6	 
		             '&idSuporteMAR='      	         + idSuporteMAR	               + //  7
		             '&idMonitoramentoMAR='        	 + idMonitoramentoMAR	       + //  8	 
		             '&idBackupMAR='      	         + idBackupMAR	               + //  9
		             '&idRetencaoBackupMAR='      	 + idRetencaoBackupMAR	       + // 10
		             '&idStatusRecursoMAR='      	 + idStatusRecursoMAR		   + // 11
		             '&tagVcenterMAR='      	     + tagVcenterMAR			   + // 12
		             '&idTipoDiscoMAR='      		 + idTipoDiscoMAR			   + // 13
		             '&idSoMAR='      	             + idSoMAR		               + // 14
		             '&idAmbienteMAR='      		 + idAmbienteMAR		       + // 15		             
		             '&idTipoServicoMAR='		     + idTipoServicoMAR	           + // 16
		             '&hostnameModalRecursoMAR='	 + hostnameModalRecursoMAR	   + // 17
		             '&tamanhoDiscoModalRecursoMAR=' + tamanhoDiscoModalRecursoMAR + // 18
		             '&primaryIPModalRecursoMAR='    + primaryIPModalRecursoMAR	   + // 19
		             '&idNuvemMAR='                  + idNuvemMAR	   			   + // 20
		             '&idSiteMAR='                   + idSiteMAR	   			   + // 21
		             '&idFamiliaFlavorsMAR='      	 + idFamiliaFlavorsMAR		   + // 22
		             '&ObservacoesRecursoMAR='       + ObservacoesRecursoMAR	   + // 23
		             '&observacaoAditivoMAR='      	 + observacaoAditivoMAR	       + // 24
		             '&dtInicioMAR='      	         + dtInicioMAR	               + // 25
		             '&dtFinalMAR='      	         + dtFinalMAR                  + // 26
		             '&idContratoMAR='      	     + idContrato                  + // 27
		             '&recursoTemporarioMAR='      	 + recursoTemporarioMAR        + // 28
		             '&temReceitaMAR='      	     + temReceitaMAR               + // 29
		             '&nomeAprovadorMAR='      	     + nomeAprovadorMAR            + // 30
		             '&periodoUtilizacaoMAR='        + periodoUtilizacaoMAR        + // 31		             
		             '&idTipoAditivoUGR='            + idTipoAditivoUGR            + // 32
		             '&idRecurso_HostnameUGR='       + idRecurso_HostnameUGR       + // 33
		             '&tamanhoDiscoUGR='             + tamanhoDiscoUGR             + // 34
		             '&idFamiliaFlavorsUGR='         + idFamiliaFlavorsUGR         + // 35
		             '&cpuUGR='                      + cpuUGR                      + // 36
		             '&ramUGR='                      + ramUGR                      + // 37
		             '&valorUGR='                    + valorUGR                    + // 38
		             '&vlrTotalUGR='                 + vlrTotalUGR                 + // 39
		             '&id_moedaMAR='                 + id_moedaMAR                 + // 40
		             '&valor_convertidoMAR='         + valor_convertidoMAR         + // 41
		             '&valor_CotacaoMAR='            + valor_CotacaoMAR            + // 42
		             '&valorMAR='                    + valorMAR                    + // 43
		             '&eipVcenterModalRecursoMAR='   + eipVcenterModalRecursoMAR   + // 44
		             '&hostVcenterModalRecursoMAR='  + hostVcenterModalRecursoMAR  + // 45
		             '&idRecursoMAR='                + idRecursoMAR                + // 46
		             '&idHubSpotAditivoMAR='         + v_idHubSpotAditivoMAR       + // 47
		             '&id_rascunhoMAR='              + id_rascunhoMAR              + // 48
		             '&motivoRascunhoMAR='           + motivoRascunhoMAR           ; // 49

		    var editCampo = 0;

			if( idAditivoMAR != null && idAditivoMAR != '' && idAditivoMAR.trim() != '' ){
				editCampo = 1;
			}

			if( validaAditivoRecursoModal( idTipoAditivoMAR ) ){
			 $.ajax({
					method : "get",
					url    : urlAction,
					data   : dados,
					success: function(lista){

		  				var json = JSON.parse(lista);

		  				var html                 = '';
		  				var idAditivadoTemp      = '';
		  				var dtCriacaoTemp        = '';
		  				var login_cadastroTemp   = '';
		  				var idRecursoTemp        = '';
		  				var dtCriacaoRecursoTemp = '';
		  				var idAditivadoAux       = 0;
		  				var nomeModal            = 'ModalAditivoRecurso';
		  				var iconi                = 'success';
		  				
		  				for(var p = 0; p < json.length; p++){
				           if(idTipoAditivoMAR === '9'){
					         if( ( tamanhoDiscoUGR == json[p].tamanho_disco_modal_recurso ) && ( idFamiliaFlavorsUGR == json[p].id_familia_flavors ) ){
                                   idAditivadoTemp    = json[p].id_aditivado; 
                                   dtCriacaoTemp      = json[p].dt_criacao ;
                                   login_cadastroTemp = json[p].login_cadastro;
					         }
					       }else{
                              idAditivadoTemp      = json[p].id_aditivado; 
                              dtCriacaoTemp        = json[p].dt_criacao ;
                              login_cadastroTemp   = json[p].login_cadastro;
                              idRecursoTemp        = json[p].id_recurso;
                              dtCriacaoRecursoTemp = json[p].dt_criacao_recurso;
					       }
		  				}

		  				$('#tabelaResutadoAditivoRecurso > tbody > tr').remove();
		  				html =  motaTabelaRecursos( json );	
	  				    $("#tabelaResutadoAditivoRecurso tbody").html(html).show();  
	  				    
	  				    getlistaHistoricoTrocaFamilia( );

                        if( editCampo === 0 ){
			  				$("#idAditivoMAR"       ).val( idAditivadoTemp      );
			  				$("#dtCriacaoMAR"       ).val( dtCriacaoTemp        );
			  				$("#loginCadastroMAR"   ).val( login_cadastroTemp   );	  			  			   
			  				$("#idRecursoMAR"       ).val( idRecursoTemp        );
			  				$("#dtCriacaoRecursoMAR").val( dtCriacaoRecursoTemp );                        
                        }
                        
                        var msnTipoAdit = '';
					    switch ( parseInt( idTipoAditivoMAR ) ){
						case 1: // Ambiente Bolha
							msnTipoAdit = 'Ambiente Bolha';
							break;
						case 2: // Contatação do Ambiente DEV
							msnTipoAdit = 'Contatação do Ambiente DEV';
							break;
						case 5: // Contratação do QAS
							msnTipoAdit = 'Contratação do QAS';
							break;
						case 7: // Incremento de Servidores	
							msnTipoAdit = 'Incremento de Servidores';
							break;
						case 9: // Upgrade de Recurso
							msnTipoAdit = 'Upgrade de Recurso';
							break;
						}
					    var titulo = '';
					    if( idAditivoMAR != null && idAditivoMAR != '' && idAditivoMAR.trim() != '' )
					    	titulo = 'UPDATE - ' + msnTipoAdit;
					    else titulo = 'INSERT - ' + msnTipoAdit;
					    
                        textoPrincipal  = 'Procedimento do cliemte ' + document.getElementById("nomeCliente").value + ' - Contrato ' + idContrato + ", executado com sucesso!";
                        MensagemConfimacaoModal( iconi, titulo, textoPrincipal, nomeModal );
					}
			 }).fail(function( xhr, status, errorThrown ){
					var texto = 'Erro ao Adicionar "Aditivo": ';
				  	var nomeModal = 'ModalAditivoRecurso';
				  	var iconiErro = 'error';
					MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
			 }); 
			 
		}
	 }
	
	 /******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/
	 function motaTabelaHistorico( objetoRecurso ) {
			var html                 = '';
			var idAditivadoAux       = 0;

			for(var p = 0; p < objetoRecurso.length; p++){
  					if( idAditivadoAux !== objetoRecurso[p].id_recurso ){
 						if( idAditivadoAux !== 0 ){
 				  			html +='</tbody>'
 		  				      +  '</table>'
 		  				      + ' </td>'
 		  				      + '</tr>';	
 						}
 						html += '<tr> '
  						  +    '<td align="center"> <button onClick="esconderLinha( \'linhaAEsconderHF' + p + '\')"> + </button> </td>'
  						  +    '<td>' + objetoRecurso[p].id_recurso         + '</td>'
  						  +    '<td>' + objetoRecurso[p].hostname           + '</td>'
  						  +    '<td>' + objetoRecurso[p].dt_criacao_recurso + '</td>'
  						  +    '<td>' + objetoRecurso[p].familia_atual      + '</td>'
                          + '</tr>'
                          + '<tr id="linhaAEsconderHF' + p + '" style="display: none">'
						  +    '<td></td>'
						  +    '<td colspan="6">'
						  +       '<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="sub-tabelaResutadoAditivoRecurso">'
						  +          '<thead>'
						  +              '<tr>'
						  +                 '<th scope="col">ID Contrato     </th>'
						  +                 '<th scope="col">Data Alteração  </th>'
						  +                 '<th scope="col">IP              </th>'
						  +                 '<th scope="col">Família Antiga  </th>'
						  +                 '<th scope="col">Família Nova    </th>'
						  +                 '<th scope="col">Tam. Disco Atigo</th>'
						  +                 '<th scope="col">Tam. Disco Novo </th>'
						  +                 '<th scope="col">Valor Recurso   </th>'
						  +                 '<th scope="col">Valor Ajustado  </th>'
						  +                 '<th scope="col">Nuvem           </th>'	  							      
						  +              '</tr>'
						  +          '</thead>'
						  +          '<tbody>'
		  				  +             '<tr> ' 
  					      +               '<td>' + objetoRecurso[p].id_contrato         + '</td>'
  					      +               '<td>' + objetoRecurso[p].dt_cadastro         + '</td>'
  					      +               '<td>' + objetoRecurso[p].ip                  + '</td>'
  					      +               '<td>' + objetoRecurso[p].familia_antiga      + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].familia_novo        + '</td>'
  					      +               '<td>' + objetoRecurso[p].tamanho_disco_velho + '</td>'
  					      +               '<td>' + objetoRecurso[p].tamanho_disco_novo  + '</td>'
  					      +               '<td>' + objetoRecurso[p].valor_velho         + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].valor_novo          + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].nuvem               + '</td>'		  					      
			              +             '</tr>';
			              
 						idAditivadoAux = objetoRecurso[p].id_recurso;
 						
 					}else{
  					 html +=           '<tr> ' 
 					      +               '<td>' + objetoRecurso[p].id_contrato         + '</td>'
  					      +               '<td>' + objetoRecurso[p].dt_cadastro         + '</td>'
  					      +               '<td>' + objetoRecurso[p].ip                  + '</td>'
  					      +               '<td>' + objetoRecurso[p].familia_antiga      + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].familia_novo        + '</td>'
  					      +               '<td>' + objetoRecurso[p].tamanho_disco_velho + '</td>'
  					      +               '<td>' + objetoRecurso[p].tamanho_disco_novo  + '</td>'
  					      +               '<td>' + objetoRecurso[p].valor_velho         + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].valor_novo          + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].nuvem               + '</td>'		  					      
			              +            '</tr>';
 					}
 			}

  			html +='</tbody>'
 				      +  '</table>'
 				      + ' </td>'
 				      + '</tr>';
	 				      
 	        return html;  

	 }
	 
	 
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	
	function getlistaHistoricoTrocaFamilia() {

		 var urlAction  = document.getElementById( "formContrato" ).action;
		 var idContrato = document.getElementById( "id_contrato"  ).value;
		 $.ajax({
				method : "get",
				url    : urlAction,
				data   : 'acao=listaHistoricoTrocaFamilia&idContrato=' + idContrato,
				success: function(lista){
	  				var json = JSON.parse(lista);
	  				var html = '';
	  				
  				    $('#tabelaResutadoHistTrocaFmilia > tbody > tr').remove();
  				    html = motaTabelaHistorico( json );
    				$("#tabelaResutadoHistTrocaFmilia tbody").html(html).show(); 
				}
			 }).fail(function( xhr, status, errorThrown ){
					// alert('Erro carregar select Produto: ' + xhr.responseText);
		  			var nomeModal = 'ModalAditivoRecurso';
		  			var iconiErro = 'error';
					var texto     = 'Erro ao buscar lista Historico Troca de Familia: ';
					MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );
			 }); 
			
	 }
	 /******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/	 
	 function motaTabelaRecursos( objetoRecurso ) {
			var html            = '';
			var idAditivadoAux  = 0;
			let vHubspotAditivo = '';  
		
			for(var p = 0; p < objetoRecurso.length; p++){
  					if( idAditivadoAux !== objetoRecurso[p].id_aditivado ){
 						if( idAditivadoAux !== 0 ){
 				  			html +='</tbody>'
 		  				      +  '</table>'
 		  				      + ' </td>'
 		  				      + '</tr>';	
 						}
 						if( objetoRecurso[p].hubspot_aditivo != undefined ) vHubspotAditivo = objetoRecurso[p].hubspot_aditivo;
 						else vHubspotAditivo = ' - ';
 						html += '<tr> '
  						  +    '<td align="center"> <button onClick="esconderLinha( \'linhaAEsconderAR' + p + '\')"> + </button> </td>'
  						  +    '<td>' + objetoRecurso[p].id_aditivado + '</td>'
  						  +    '<td>' + vHubspotAditivo               + '</td>'
  						  +    '<td>' + objetoRecurso[p].dt_criacao   + '</td>'
  						  +    '<td>' + objetoRecurso[p].valor_total  + '</td>'
                          + '</tr>'
                          + '<tr id="linhaAEsconderAR' + p + '" style="display: none">'
						  +    '<td></td>'
						  +    '<td colspan="6">'
						  +       '<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="sub-tabelaResutadoAditivoRecurso">'
						  +          '<thead>'
						  +              '<tr>'
						  +                 '<th scope="col">ID Recurso   </th>'
						  +                 '<th scope="col">Tipo Aditivo </th>'
						  +                 '<th scope="col">Status       </th>'
						  +                 '<th scope="col">Tipo Serviço </th>'
						  +                 '<th scope="col">Ambiente     </th>'
						  +                 '<th scope="col">SO           </th>'
						  +                 '<th scope="col">Tipo Disco   </th>'
						  +                 '<th scope="col">Hostname     </th>'
						  +                 '<th scope="col">Família      </th>'
						  +                 '<th scope="col">Valor Custo  </th>'	  							      
						  +                 '<th scope="col">Editar       </th>'
						  +              '</tr>'
						  +          '</thead>'
						  +          '<tbody>'
		  				  +             '<tr> ' 
  					      +               '<td>' + objetoRecurso[p].id_recurso               + '</td>'
  					      +               '<td>' + objetoRecurso[p].desc_tipo_ditivo         + '</td>'
  					      +               '<td>' + objetoRecurso[p].desc_status_recurso      + '</td>'
  					      +               '<td>' + objetoRecurso[p].desc_tipo_servico        + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].desc_ambiente            + '</td>'
  					      +               '<td>' + objetoRecurso[p].desc_sistema_operacional + '</td>'
  					      +               '<td>' + objetoRecurso[p].desc_tipo_disco          + '</td>'
  					      +               '<td>' + objetoRecurso[p].host_name_modal_recurso  + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].desc_familia             + '</td>'		  					      
  					      +               '<td>' + objetoRecurso[p].custo_total              + '</td>'		  					      
 					      +               '<td>' 
 					      +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivoRecurso(' + objetoRecurso[p].id_recurso + ');"> ' 
						  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
						  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
						  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
						  +                 '   </svg> </button> ' 
						  +               '</td>' 
			              +             '</tr>';
 						idAditivadoAux = objetoRecurso[p].id_aditivado;
 					}else{
  					 html +=             '<tr> ' 
  					      +              '<td>' + objetoRecurso[p].id_recurso               + '</td>'
  					      +              '<td>' + objetoRecurso[p].desc_tipo_ditivo         + '</td>'
  					      +              '<td>' + objetoRecurso[p].desc_status_recurso      + '</td>'
  					      +              '<td>' + objetoRecurso[p].desc_tipo_servico        + '</td>'		  					      
  					      +              '<td>' + objetoRecurso[p].desc_ambiente            + '</td>'
  					      +              '<td>' + objetoRecurso[p].desc_sistema_operacional + '</td>'
  					      +              '<td>' + objetoRecurso[p].desc_tipo_disco          + '</td>'
  					      +              '<td>' + objetoRecurso[p].host_name_modal_recurso  + '</td>'		  					      
  					      +              '<td>' + objetoRecurso[p].desc_familia             + '</td>'		  					      
  					      +              '<td>' + objetoRecurso[p].custo_total              + '</td>'		  					      
 					      +              '<td>' 
 					      +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivoRecurso(' + objetoRecurso[p].id_recurso + ');"> ' 
						  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
						  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
						  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
						  +                 '   </svg> </button> ' 
						  +              '</td>' 
			              +            '</tr>';
 					}
 			}

  			html +='</tbody>'
 				      +  '</table>'
 				      + ' </td>'
 				      + '</tr>';
 	        return html;  
	 }

	 /******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/
	 
	 function MontaDivVisibleHiddenMAR( trataTAG, campoValor ) {
		 var idTipoAditivoMAR = document.getElementById( campoValor ).value;
		 
		switch( parseInt( idTipoAditivoMAR ) ) {
		    case 1: // Ambiente Bolha
		    	// desabilita todas as outras DIV's.
	    	    document.getElementById("divUpgradeRecursoMG").setAttribute("hidden","");
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divAmbienteBolhaMG"    ).removeAttribute("hidden");
		    	document.getElementById("divAmbienteMG"         ).removeAttribute("hidden");
		    	document.getElementById("divAditivoMR"          ).removeAttribute("hidden");
		    	document.getElementById("divRecursoMR"          ).removeAttribute("hidden");
		    	document.getElementById("divobservacaoAditivoMR").removeAttribute("hidden");
		    	document.getElementById("DivVigenciaAditivoMR"  ).removeAttribute("hidden");
		        break;
		    case 9: // Upgrade de Recurso
		    	// Desabilita a DIV's.
		    	document.getElementById("divAmbienteBolhaMG"    ).setAttribute("hidden","");
		    	document.getElementById("divAmbienteMG"         ).setAttribute("hidden","");
		    	document.getElementById("divAditivoMR"          ).setAttribute("hidden","");
		    	document.getElementById("divRecursoMR"          ).setAttribute("hidden","");
		    	document.getElementById("divobservacaoAditivoMR").setAttribute("hidden","");
		    	document.getElementById("DivVigenciaAditivoMR"  ).setAttribute("hidden","");
		    	// Habilita a DIV Upgrade Recurso.
		    	document.getElementById("divUpgradeRecursoMG").removeAttribute("hidden");
		    	montaSelectHostname( );
		        break;		    	
		    default: // Contratação do Ambiente DEV - Contratação do QAS - Incremento de Servidores
		    	// desabilita todas as outras DIV's.
//		    	document.getElementById("divAmbienteBolhaMG" ).setAttribute("hidden","");
	    	    document.getElementById("divUpgradeRecursoMG").setAttribute("hidden","");
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divAmbienteBolhaMG"    ).removeAttribute("hidden");
		    	document.getElementById("divAmbienteMG"         ).removeAttribute("hidden");
		    	document.getElementById("divAditivoMR"          ).removeAttribute("hidden");
		    	document.getElementById("divRecursoMR"          ).removeAttribute("hidden");
		    	document.getElementById("divobservacaoAditivoMR").removeAttribute("hidden");
		    	document.getElementById("DivVigenciaAditivoMR"  ).removeAttribute("hidden");
		    	break;
	    }
		
		$("#idTipoAditivoMAR").val( idTipoAditivoMAR );
		$("#idTipoAditivoUGR").val( idTipoAditivoMAR );
		limparModalAditivoMAR( trataTAG );

	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function validaAditivoRecursoModal( idTipoAditivo ){
		 var nomeModal       = 'ModalAditivoRecurso';
		 var iconi           = 'warning';
		 var tituloPrincipal = "Validação de Formulário";

		switch( parseInt( idTipoAditivo ) ) {
			case 1: // Ambiente Bolha
			case 2: // Contatação do Ambiente DEV
			case 5: // Contratação do QAS
			case 7: // Incremento de Servidores	
				dataValidada = document.getElementById("dtInicioModalAditivo").value;
				 // Campo Tipo Aditivo
				 if(document.getElementById("idStatusMAR").value == ""){
			        //alert('Por favor, Selecione o "Status Aditivo"!');
			        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, Selecione o "Status Aditivo"!', nomeModal ); 
			        document.getElementById("idStatusMAR").focus();
			        return false
				 }
				
				 // Campo Status
				 if(document.getElementById("vlrTotalMAR").value == ""){
			        // alert('Por favor, informe o Valor do Aditivo!');
			        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Valor do Aditivo!', nomeModal );
			        document.getElementById("vlrTotalMAR").focus();
			        return false
				 }
				 
				 // Campo Moeda
				 if(document.getElementById("id_moedaMAR").value == ""){
			       // alert('Por favor, informe o Valor a Moeda!');
			        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Valor a Moeda!', nomeModal );
			        document.getElementById("id_moedaMAR").focus();
			        return false
				 }

				 // Campo Data Inicio Vigencia
				 if(document.getElementById("idStatusRecursoMAR").value == ""){
			        // alert('Por favor, informe  a "Status do Recurso""!');
			        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe  a "Status do Recurso""!', nomeModal );
			        document.getElementById("idStatusRecursoMAR").focus();
			        return false
				 }
				 
				 if(document.getElementById("recursoTemporarioMAR").value == ""){
				        // alert('Por favor, informe se é "Recurso Temporário"!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe se é "Recurso Temporário"!', nomeModal );
				        document.getElementById("recursoTemporarioMAR").focus();
				        return false
					 }
				 
				 // Campo Data Fi Vigencia
				 if(document.getElementById("idBackupMAR").value == ""){
			        // alert('Por favor, informe se tem Backup!');
			        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe se tem Backup!', nomeModal );
			        document.getElementById("idBackupMAR").focus();
			        return false
				 }
				 
				 if(document.getElementById("idTipoDiscoMAR").value == ""){
				        // alert('Por favor, informe o Tipo Disco!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Tipo Disco!', nomeModal );
				        document.getElementById("idTipoDiscoMAR").focus();
				        return false
				 }
				 if(document.getElementById("idSoMAR").value == ""){
				        //alert('Por favor, informe o Sistema Operacional!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Sistema Operacional!', nomeModal );
				        document.getElementById("idSoMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("idAmbienteMAR").value == ""){
				        // alert('Por favor, informe o Ambiente!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Ambiente!', nomeModal );
				        document.getElementById("idAmbienteMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("idFamiliaFlavorsMAR").value == ""){
				        // alert('Por favor, informe a Familia Flavors!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe a Familia Flavors!', nomeModal );
				        document.getElementById("idFamiliaFlavorsMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("idTipoServicoMAR").value == ""){
				        // alert('Por favor, informe a Familia Flavors!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe a Familia Flavors!', nomeModal );
				        document.getElementById("idTipoServicoMAR").focus();
				        return false
				 }
/*				 
				 if(document.getElementById("hostnameModalRecursoMAR").value == ""){
				        //alert('Por favor, informe o Hostname!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Hostname!', nomeModal );
				        document.getElementById("hostnameModalRecursoMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("tamanhoDiscoModalRecursoMAR").value == ""){
				        //alert('Por favor, informe o Tamanho do Disco!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Tamanho do Disco!', nomeModal );
				        document.getElementById("tamanhoDiscoModalRecursoMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("primaryIPModalRecursoMAR").value == ""){
				        // alert('Por favor, informe o Primary IP!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Primary IP!', nomeModal );
				        document.getElementById("primaryIPModalRecursoMAR").focus();
				        return false
				 }
*/				 
				 if(document.getElementById("idTipoContratacaoMAR").value == ""){
				        // alert('Por favor, informe o Tipo Contratação!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Tipo Contratação!', nomeModal );
				        document.getElementById("idTipoContratacaoMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("idNuvemMAR").value == ""){
				        // alert('Por favor, informe a Nuvem!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe a Nuvem!', nomeModal );
				        document.getElementById("idNuvemMAR").focus();
				        return false
				 }
				 
				 if(document.getElementById("idSiteMAR").value == ""){
				        // alert('Por favor, informe o Site!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Site!', nomeModal );
				        document.getElementById("idSiteMAR").focus();
				        return false
				 }
				 if(document.getElementById("idMonitoramentoMAR").value == ""){
				        // alert('Por favor, informe se haverá Monitoramento!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe se haverá Monitoramento!', nomeModal );
				        document.getElementById("idMonitoramentoMAR").focus();
				        return false
				 }
				 if(document.getElementById("idSuporteMAR").value == ""){
				        // alert('Por favor, informe se haverá Suporte.!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe se haverá Suporte.!', nomeModal );
				        document.getElementById("idSuporteMAR").focus();
				        return false
				 }

				break;
			case 9: // Incremento de Servidores	
				 if(document.getElementById("vlrTotalUGR").value == ""){
				        // alert('Por favor, informe o Valor do Recurso!');
				        MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, informe o Valor do Recurso!', nomeModal );
				        document.getElementById("vlrTotalUGR").focus();
				        return false
				        break;
				 }	
			/*
			default:
		         
	             MensagemConfimacaoModal( iconi, tituloPrincipal, 'Por favor, Selecione o "Tipo Aditivo"!', nomeModal ); 
	             document.getElementById("idTipoAditivoMAR").focus();
	             return false
				break;
	        */		
		}

       return true;
	}
	 
    /******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function montaSelectHostname( ) {
	  var urlAction  = document.getElementById("formContrato").action;
	  var idContrato = document.getElementById("id_contrato" ).value;

	  $.ajax({
             method : "get",
             url : urlAction,
             data : 'acao=montaSelectHostname&idContrato=' + idContrato,
             success: function(lista){
    				var option = '<option value="" disabled selected>[-Selecione-]</option>';
       				var json = JSON.parse(lista);
       				for(var p = 0; p < json.length; p++){
       					option += '<option value=' + json[p].id_recurso + '>' + json[p].host_name_modal_recurso + '</option>';
       				}
       				$("#idRecurso_HostnameUGR").html(option).show();  
             }
	  }).fail(function( xhr, status, errorThrown ){
          //  alert('Erro carregar select Produto: ' + xhr.responseText);
		  	var nomeModal = 'ModalAditivoRecurso';
		  	var iconiErro = 'error';
			var texto = 'Erro ao montar Select Hostname: ';
			MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );            
      }); 
   }
   /******************************************************************/
   /*                                                                */
   /*                                                                */
   /******************************************************************/
   $(document).ready(function () {

	    $('#idRecurso_HostnameUGR').change(function () {

	        var es        = document.getElementById('idRecurso_HostnameUGR');
	        var idRecurso = es.options[es.selectedIndex].value;
	        var urlAction = document.getElementById("formContrato").action;
	        
	  		$.ajax({
	  			method : "get",
	  			url    : urlAction,
	  			data   : "idRecurso=" + idRecurso + '&acao=montaDadosFamiliaUpdade',
	  			success: function(lista){
	  				var json = JSON.parse(lista);

	  				montaSelectFamiliaUGR( json.id_familia_flavors_velho, json.id_nuvem );
	  				
	  				$("#tamanhoDiscoUGR").val( json.tamanho_disco_velho );
	  				$("#cpuUGR"         ).val( json.cpu_velho           );
	  				$("#ramUGR"         ).val( json.ram_velho           );
	  				$("#valorUGR"       ).val( json.valor_velho         );
	  			} 
	  		}).fail(function( xhr, status, errorThrown ){
	  			//alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
		  		var nomeModal = 'ModalAditivoRecurso';
		  		var iconiErro = 'error';
				var texto = 'Erro ao buscar valores campo Família Flavors: ';
				MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );

	  		}); 	  			  		
	    });
   });
 
	function montaSelectFamiliaUGR( idFamiliaSelecionada, idNuvem ) {
		var urlAction = document.getElementById("formContrato").action;
		
		$.ajax({
			method : "get",
			url    : urlAction,
			data   : 'acao=montaSelectFamiliaModal&idNuvem=' + idNuvem,
	
			success: function(lista){
					    var option = '<option value="" disabled selected>[-Selecione-]</option>';
					    var selected = '';
						var json = JSON.parse(lista);

						for(var p = 0; p < json.length; p++){
	      					if( json[p].id_familia_flavors === idFamiliaSelecionada  )
	      				  	     selected = 'selected';
	      					 else selected = '';
							
							option += '<option value=' + json[p].id_familia_flavors + ' ' + selected + '>' + json[p].familia + '</option>';
						}
						$("#idFamiliaFlavorsUGR").html(option).show();  
			}
		 }).fail(function( xhr, status, errorThrown ){
				//alert('Erro carregar select Produto: ' + xhr.responseText);
		  		var nomeModal = 'ModalAditivoRecurso';
		  		var iconiErro = 'error';
				var texto = 'Erro ao montar Select Familia Modal: ';
				MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );

		 }); 
			 
	}

	function selecionaCpuRamVlrFamiliaUGR( ) {
		
	    var urlAction    = document.getElementById("formContrato").action;	    
		var idFamiliaFl = document.getElementById("idFamiliaFlavorsUGR").value;

		$.ajax({
			method : "get",
			url    : urlAction,
			data   : "idFamiliaFl=" + idFamiliaFl + '&acao=montaCamosFamiliaFl',
			success: function(lista){
				var json = JSON.parse(lista);
				$("#cpuUGR"     ).val(json.cpu);
				$("#ramUGR"     ).val(json.ram);
				$("#valorUGR"   ).val(json.valor);
			}
		}).fail(function( xhr, status, errorThrown ){
			// alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
		  	var nomeModal = 'ModalAditivoRecurso';
		  	var iconiErro = 'error';
			var texto = 'Erro selecionar Cpu Ram Vlr Familia UGR: ';
			MensagemConfimacaoModal( iconiErro, texto, xhr.responseText, nomeModal );

		}); 	
	}	
		

	function ValidaTemReceitaMAR() {
	    var temReceitaMAR = document.getElementById("recursoTemporarioMAR" ).value; 
		if( temReceitaMAR === "2" ){
			 document.getElementById('temReceitaMAR'       ).disabled = true;
			 document.getElementById('nomeAprovadorMAR'    ).disabled = true;
			 document.getElementById('periodoUtilizacaoMAR').disabled = true;
		}else{
			 document.getElementById('temReceitaMAR'       ).disabled = false;
			 document.getElementById('nomeAprovadorMAR'    ).disabled = false;
			 document.getElementById('periodoUtilizacaoMAR').disabled = false;
		}
	}
	
	
	function habilitaCotacaoMAR() {	
		var idMoedaMAR              = document.getElementById( "id_moedaMAR"         ).value;
		var inputValorConvertidoMAR = document.querySelector ( "#valor_convertidoMAR");
		var inputValorCotacaoMAR    = document.querySelector ( "#valor_CotacaoMAR"   );
	
		if(idMoedaMAR === "1"){			
			$("#valor_convertidoMAR").val('');
			$("#valor_CotacaoMAR"   ).val('');
			inputValorConvertidoMAR.disabled = true;
			inputValorCotacaoMAR.disabled    = true;				
		}else{
			inputValorConvertidoMAR.disabled = false;
			inputValorCotacaoMAR.disabled    = false;	
		}
	}

	  
	function cauculoConversaoMAR() {
//		 alert( 'Ola' );
		 var valorTotalMAR      = document.getElementById("vlrTotalMAR"     ).value;
		 var valorCotacaoMAR    = document.getElementById("valor_CotacaoMAR").value;
		 var valorConvertidoMAR = '';
		
		 if( ( valorTotalMAR   != null && valorTotalMAR   != '' && valorTotalMAR.trim()   != '' ) && 
			 ( valorCotacaoMAR != null && valorCotacaoMAR != '' && valorCotacaoMAR.trim() != '' ) ){
			 
			 valorTotalMAR = valorTotalMAR.replace(/[^\d]+/g,'');
			 valorTotalMAR = valorTotalMAR /100;
			 
			 valorCotacaoMAR = valorCotacaoMAR.replace(/[^\d]+/g,'');
			 valorCotacaoMAR = valorCotacaoMAR /100;

//			 alert('valorTotalMA: ' + valorTotalMA + ' - valorCotacaoMA: ' + valorCotacaoMA );
			 
			 valorConvertidoMAR = valorTotalMAR * valorCotacaoMAR;
			 const valorCalculadoMAR = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(valorConvertidoMAR);
			 $("#valor_convertidoMAR").val(valorCalculadoMAR ); 
		 }
	}  
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/ 
    function habilitaStatusMotivoRascunhoMAR() {
	
	  var idStatusMAR            = document.getElementById("idStatusMAR"      ).value;
	  var inputid_rascunhoMAR    = document.querySelector("#id_rascunhoMAR"   );
	  var inputmotivoRascunhoMAR = document.querySelector("#motivoRascunhoMAR");
	  if(idStatusMAR === "4"){
		 inputid_rascunhoMAR.disabled    = false;
		 inputmotivoRascunhoMAR.disabled = false;
	  }else{
		inputid_rascunhoMAR.disabled    = true;
		inputmotivoRascunhoMAR.disabled = true;
		$("#id_rascunhoMAR"   ).val('');
		$("#motivoRascunhoMAR").val('');
	 }	
   }
    
 </script>

