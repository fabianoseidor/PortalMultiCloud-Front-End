
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>        

    <!-- Modal Add Produto -->
   <div class="modal t-modal primary" id="ModalAditivoProduto" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Contratação de Produtos via Aditivo</h5>
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
						    <h5 class="card-title">Info. Aditivo</h5><hr>	
							<div class="form-row">
								<!-- Campo ID Aditivo -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">ID Aditivo</label>
									<input type="text" name="idAditivoModalAditivo" id="idAditivoModalAditivo" class="form-control" readonly="readonly" value="${modelListAditivoProduto.id_aditivado}">
								</div>

								<!-- Data Cadastro -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">Data Cadastro</label>
									<input type="text" name="dtCriacaoModalAditivo" id="dtCriacaoModalAditivo" class="form-control" readonly="readonly" value="${modelListAditivoProduto.dt_criacao}">
								</div>

								<!-- Login Cadastro --> 
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<label class="float-label">Login Cadastro</label>
									<input type="text" name="loginCadastroAditivo" id="loginCadastroAditivo" class="form-control" readonly="readonly" value="${modelListAditivoProduto.login_cadastro}">
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
	        <hr>  
		    <br>
			<div class="row">
				<div class="col-sm-12">
					<form class="form-material">
						<div class="card">
							<div class="card-block">
							    <h5 class="card-title">Info. Produto</h5><hr>
							    <div class="form-row">
		                            <!-- Campo Tipo Aditivo -->
									<div class="form-group form-default form-static-label col-md-12 mb-6">
									    <label class="font-weight-bold font-italic">Tipo Aditivo</label>
										<select name="idTipoAditivoModalAditivo" id="idTipoAditivoModalAditivo" class="form-control" required="required" onchange="MontaDivVisibleHidden();">
											<option value="" disabled selected>Selecione Tipo Aditivo</option>
											    <tagsContrato:listaTipoAditivo />
										</select> 
									</div>
								</div>
								
								<div class="form-row">
									<!-- Campo Status do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
										<label class="font-weight-bold font-italic">Status Aditivo</label>
										<select name="idStatusModalAditivo" id="idStatusModalAditivo" class="form-control" required="required" onchange="habilitaStatusMotivoRascunhoAditivo();">
											<option value="" disabled selected>Selecione Status Aditivo</option>
											    <tagsContrato:listaStatusAditivo/>
										</select> 
									</div>

                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
								        <label class="font-weight-bold font-italic">Motivo Rascunho</label>
								        <input style="color: #000000" type="text" name="motivoRascunhoAditivo" id="motivoRascunhoAditivo" disabled="disabled" maxlength="500" class="form-control"  placeholder="Informe o Motivo do Rascunho" value=""> 
								    </div>

							        <div class="form-group form-default form-static-label col-md-3 mb-3">
								        <label class="font-weight-bold font-italic">Tipo Rascunho</label>>
								        <select name="id_rascunhoAditivo" id="id_rascunhoAditivo" disabled="disabled" class="form-control" >
								            <option value="" disabled selected>[-Selecione-]</option>
								                <tagsContrato:listaTipoRascunho />
								        </select> 
							        </div>

									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic">ID HubSpot Aditivo</label>
	                                     <input type="text" name="idHubSpotAditivoPROD" id="idHubSpotAditivoPROD" class="form-control" placeholder="HubSpot Aditivo">
									</div>								
									
								</div>
								
								<div class="form-row">
									<!-- Campo Moeda -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Moeda</label>
										<select name="id_moedaModalAditivo" id="id_moedaModalAditivo" class="form-control" onchange="habilitaCotacaoModalAdit();" required="required" >
											<option value="" disabled selected>Selecione Moeda</option>
											   <tagsContrato:listaMoedaModalAdit />
										</select> 
									</div>

									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic">Valor do Aditivo</label>
	                                     <input type="text" name="vlrTotalModalAditivo" id="vlrTotalModalAditivo" class="form-control" onblur="cauculoConversaoModalAdit();" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">
									</div>								

									<!-- Campo Fase do Contrato -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Valor Convertido</label>
										<input type="text" name="valor_convertidoModalAditivo" id="valor_convertidoModalAditivo" disabled="disabled" class="form-control" placeholder="Valor do contrato" value="${modelListAditivoProduto.valor_convertido}">
									</div>

									<!-- Campo Fase do Contrato -->
									<div class="form-group form-default form-static-label col-md-3 mb-4">
										<label class="font-weight-bold font-italic">Cotação</label>
										<input type="text" name="valor_CotacaoModalAditivo" id="valor_CotacaoModalAditivo" disabled="disabled" onblur="cauculoConversaoModalAdit();" class="form-control" placeholder="Valor do contrato" value="${modelListAditivoProduto.cotacao_moeda}">
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- DIV configurada para Incremento de Usuário.                                                   -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divIncrementoUsuario" hidden="hidden">
				<div class="col-sm-12">
					<form class="form-material">
						<div class="card">
							<div class="card-block">
								<div class="form-row">
								  	<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic" >Usuário</label>
									     <select name="produtoUserModalAditivo" id="produtoUserModalAditivo"  onchange="getValorProdutoID( 'produtoUserModalAditivo', 'vlrUnitUserMModalAditivo' );" class="form-control" required="required">
									        <option value="" disabled selected>Selecione Usuário</option>
									        <tagsContrato:listaProdutoUserModal/>
									     </select>								     
									</div>
								  
	                                <!-- Campo Quantidade --> 
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
								        <label class="font-weight-bold font-italic">Incremento de Usuário</label>
<!--  								        <input type="text" name="qtyUserModalAditivo" id="qtyUserModalAditivo"  onchange="CalculoVlrTotal('qtyUserModalAditivo', 'vlrUnitUserMModalAditivo' );" class="form-control only-number" placeholder="Incremento de Usuário" value="${modelListAditivoProduto.qty_usuario_contratada}"> --> 
								        <input type="text" name="qtyUserModalAditivo" id="qtyUserModalAditivo" class="form-control only-number" placeholder="Incremento de Usuário" value="${modelListAditivoProduto.qty_usuario_contratada}"> 
	                                </div>
	                              
									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic">Valor Unitário</label>
<!--  	                                     <input type="text" name="vlrUnitUserMModalAditivo" id="vlrUnitUserMModalAditivo" onchange="CalculoVlrTotal('qtyUserModalAditivo', 'vlrUnitUserMModalAditivo' );" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}"> -->
	                                     <input type="text" name="vlrUnitUserMModalAditivo" id="vlrUnitUserMModalAditivo" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">
									</div>								
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- DIV configurada para Incremento de Serviços.                                                  -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divIncrementoServicos" hidden="hidden">
				<div class="col-sm-12">
					<form class="form-material">
						<div class="card">
							<div class="card-block">
								<div class="form-row">
									<!-- Campo Porte Cliente -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic" >Serviço Contratado</label>
									     <select name="servicoContratadoModalAditivo" id="servicoContratadoModalAditivo" onchange="getValorServicoID();" class="form-control" required="required">
									        <option value="" disabled selected>Selecione Serviço Contratado</option>
									        <tagsContrato:listaServicoContratadoModal />
									     </select>								     
									</div>
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
								         <label class="font-weight-bold font-italic">Quantidade de Serviço</label>
<!--  								         <input type="text" name="qtyServicoModalAditivo" id="qtyServicoModalAditivo" onchange="CalculoVlrTotal('qtyServicoModalAditivo', 'vlrUnitservicoModalAditivo' );"  class="form-control only-number" placeholder="Quantidade de Serviço" value="${modelListAditivoProduto.qty_servicos}">--> 
								         <input type="text" name="qtyServicoModalAditivo" id="qtyServicoModalAditivo" class="form-control only-number" placeholder="Quantidade de Serviço" value="${modelListAditivoProduto.qty_servicos}"> 
	                                </div>
									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									     <label class="font-weight-bold font-italic">Valor Unitário</label>
<!--  	                                     <input type="text" name="vlrUnitservicoModalAditivo" id="vlrUnitservicoModalAditivo" onchange="CalculoVlrTotal('qtyServicoModalAditivo', 'vlrUnitservicoModalAditivo' );" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}"> -->
	                                     <input type="text" name="vlrUnitservicoModalAditivo" id="vlrUnitservicoModalAditivo" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">
									</div>								
									
							        <hr>  
								    <br>
								    								
									<div class="form-group form-default form-static-label col-md-12 mb-6">
										<label class="font-weight-bold font-italic">Descrição Serviço Contratado</label>
										<textarea class="form-control" id="descServContratadoModalAditivo" name="descServContratadoModalAditivo" placeholder="Descrição Sobre o Serviço Contratado" rows="10" >${modelListAditivoProduto.desc_serv_contratado}</textarea>
									</div>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- DIV configurada para Contratação do DR.                                                       -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divContratacaoDR" hidden="hidden">
			
				<div class="col-sm-12">
					<form class="form-material">
						<!-- Basic Form Inputs card start -->
						<div class="card">
							<div class="card-block">
								<div class="form-row">
								
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >DR</label>
									     <select name="produtoDRModalAditivo" id="produtoDRModalAditivo" onchange="getValorProdutoID( 'produtoDRModalAditivo', 'vlrUnitDRModalAditivo' ); " class="form-control" required="required">
									        <option value="" disabled selected>Selecione DR</option>
									        <tagsContrato:listaProdutoModal />
									     </select>								     
									</div>
	
									<!-- Campo Porte Cliente -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >Tipo DR</label>
									     <select name="tpDRModalAditivo" id="tpDRModalAditivo" class="form-control" required="required">
									        <option value="" disabled selected>Selecione Tipo DR</option>
									        <tagsContrato:listaTipoProdutoModalDR />
									     </select>								     
									</div>
									
	                                <div class="form-group form-default form-static-label col-md-3 mb-6">
								         <label class="font-weight-bold font-italic">Quantidade DR</label>
<!--  								         <input type="text" name="qtyDRModalAditivo" id="qtyDRModalAditivo" onchange="CalculoVlrTotal('qtyDRModalAditivo', 'vlrUnitDRModalAditivo' );" class="form-control only-number" placeholder="Quantidade DR" value="${modelListAditivoProduto.qty_servicos}"> -->
								         <input type="text" name="qtyDRModalAditivo" id="qtyDRModalAditivo" class="form-control only-number" placeholder="Quantidade DR" value="${modelListAditivoProduto.qty_servicos}">	                                   
	                                </div>

									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic">Valor Unitário</label>
<!--  	                                     <input type="text" name="vlrUnitDRModalAditivo" id="vlrUnitDRModalAditivo" onchange="CalculoVlrTotal('qtyDRModalAditivo', 'vlrUnitDRModalAditivo' );" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}"> -->
	                                     <input type="text" name="vlrUnitDRModalAditivo" id="vlrUnitDRModalAditivo" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">
									</div>								

								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- DIV configurada para Contratação de VPN.                                                      -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			<div class="row" id="divContratacaoVPN" hidden="hidden">
			
				<div class="col-sm-12">
					<form class="form-material">
						<!-- Basic Form Inputs card start -->
						<div class="card">
							<div class="card-block">
								<div class="form-row">
								
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >VPN</label>
									     <select name="produtoVPNodalAditivo" id="produtoVPNodalAditivo" onchange="getValorProdutoID( 'produtoVPNodalAditivo', 'vlrUnitVPNModalAditivo' );" class="form-control" required="required">
									        <option value="" disabled selected>Selecione VPN</option>
									        <tagsContrato:listaProdutoVPNModal/>
									     </select>								     
									</div>
	
									<!-- Campo Porte Cliente -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic" >Tipo VPN</label>
									     <select name="tpVPNodalAditivo" id="tpVPNodalAditivo" class="form-control" required="required">
									        <option value="" disabled selected>Selecione Tipo VPN</option>
									        <tagsContrato:listaTipoProdutoVPNModal/>
									     </select>								     
									</div>
									
	                                <div class="form-group form-default form-static-label col-md-3 mb-6">
								         <label class="font-weight-bold font-italic">Quantidade VPN</label>
<!--  								         <input type="text" name="qtyVPNodalAditivo" id="qtyVPNodalAditivo" onchange="CalculoVlrTotal('qtyVPNodalAditivo', 'vlrUnitVPNModalAditivo' );"class="form-control only-number" placeholder="Quantidade VPN" value="${modelListAditivoProduto.qty_servicos}">-->	                                   
  								         <input type="text" name="qtyVPNodalAditivo" id="qtyVPNodalAditivo" class="form-control only-number" placeholder="Quantidade VPN" value="${modelListAditivoProduto.qty_servicos}">	                                   
	                                </div>

									<!-- Campo Valor do Aditivo -->
									<div class="form-group form-default form-static-label col-md-3 mb-6">
									     <label class="font-weight-bold font-italic">Valor Unitário</label>
<!--  	                                     <input type="text" name="vlrUnitVPNModalAditivo" id="vlrUnitVPNModalAditivo" onchange="CalculoVlrTotal('qtyVPNodalAditivo', 'vlrUnitVPNModalAditivo' );" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">-->
	                                     <input type="text" name="vlrUnitVPNModalAditivo" id="vlrUnitVPNModalAditivo" class="form-control" required="required" placeholder="Valor do Aditivo" value="${modelListAditivoProduto.vlr_toral_adit}">
									</div>								

								</div>
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
	        <hr>  
		    <br>											
			<div class="row" id="divobservacao">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">
							<div class="form-row">
							  <div class="form-group form-default form-static-label col-md-12 mb-6">
							      <label for="observacao">Observação:</label>
								  <textarea class="form-control" id="observacaoModalAditivo" name="observacaoModalAditivo" placeholder="Observação" rows="10" >${modelListAditivoProduto.observacao}</textarea>
							  </div>
							</div>
						</div>
					</div>
				</div>
			</div>
	        <hr>  
		    <br>
			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- A Data Vigencia, sempre ira ser visivel com os valores da data atual(Abetro para alteracao) e -->
            <!-- data final, de acordo com a data final do contrato.                                           -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
            <div class="row" id="DivVigenciaAditivo" >
               <form class="form-material">
                  <div class="col-sm-12">
                     <div class="card">
                        <div class="card-block">
                           <h5 class="card-title">Vigência Aditivo</h5><hr>
                           <div class="form-row">
                              <div class="form-group form-default form-static-label col-md-6 mb-6">
                                  <label class="font-weight-bold font-italic" >ID Vigência</label>
                                 <input type="text" name="id_vigenciaModalAditivo" id="id_vigenciaModalAditivo" class="form-control" readonly="readonly" disabled="disabled">	
                              </div>
                              <div class="form-group form-default form-static-label col-md-6 mb-6">
                                 <label class="font-weight-bold font-italic" >Data Cadastro</label>
                                 <input type="text" name="dt_criacao_vigenciaModalAditivo" id="dt_criacao_vigenciaModalAditivo" class="form-control" readonly="readonly" disabled="disabled">	
                              </div>
                           </div>
                        
                           <div class="form-row">
                              <div class="form-group form-default form-static-label col-md-4 mb-3">
                                 <label class="font-weight-bold font-italic" >Tempo Contrato</label>
                                 <select style="color: #000000" name="selectTempoContratoModalAditivo" id="selectTempoContratoModalAditivo" onchange="calculaDataFinalVigenciaModalAditivo(); habilitaSetupModalAditivo();" class="form-control">
                                    <option value="" disabled selected>Selecione Tempo Contrato</option>
                                    <tagsContrato:listaTempoContratoAditivo/>
                                 </select>
                              </div>
                              <!-- Campo Data Início --> 
                              <div class="form-group form-default form-static-label col-md-4 mb-6">
                                 <label class="font-weight-bold font-italic" >Data Início</label>
                                 <input type="text" name="dtInicioModalAditivo" id="dtInicioModalAditivo" class="form-control" onchange="calculaDataFinalVigenciaModalAditivo();">	
                              </div>
                              <div class="form-group form-default form-static-label col-md-4 mb-6">
                                 <label class="font-weight-bold font-italic" >Data Final</label>
                                 <input type="text" name="dtFinalModalAditivo" id="dtFinalModalAditivo" class="form-control" >	
                              </div>
                           </div>
                        
                           <div class="form-group form-default form-static-label mb-6">
                              <label class="font-weight-bold font-italic" >Observaçãoo Vigência</label>
                              <textarea style="color: #000000" class="form-control" id="observacaoVigenciaModalAditivo" name="observacaoVigenciaModalAditivo" placeholder="Observação" rows="10" ></textarea>
                           </div>
                        </div>
                     </div>
                  </div>
               </form>
            </div>

            <hr>
            <br>

			<!-- ********************************************************************************************* -->
			<!--                                                                                               -->
            <!-- A Data Vigencia, sempre ira ser visivel com os valores da data atual(Abetro para alteracao) e -->
            <!-- data final, de acordo com a data final do contrato.                                           -->			
			<!--                                                                                               -->
			<!-- ********************************************************************************************* -->
			
            <div class="row" id="DivComissaoAditivoMR" >
               <form class="form-material">
                  <div class="col-sm-12">
                     <div class="card">
                        <div class="card-block">
                           <h5 class="card-title">Comissão</h5><hr>
                           
                           <div class="form-row">  

					        <div class="form-group form-default form-static-label col-md-6 mb-3">
							    <span class="font-weight-bold font-italic" style="color: #708090">Comissão</span>
								<select style="color: #000000" name="comissaoModalAditivo" id="comissaoModalAditivo" class="form-control" disabled="disabled" required="required" onchange="calcularModalAditivo()">
								  <option value="" disabled selected>[-Selecione-]</option>
								  <option value=1>Sim</option>
								  <option value=0>Não</option>
								</select> 
							</div>
															
                           <div class="form-group form-default form-static-label col-md-6 mb-4">
                                 <span class="font-weight-bold font-italic" style="color: #708090">Quantidades Meses Contrato</span>
                                 <input style="color: #000000" type="text" name="qtyMesesContratoModalAditivo" id="qtyMesesContratoModalAditivo" readonly class="form-control" placeholder="Quantidades Meses Contrato">
                              </div>
                           </div>

                           <div class="form-row">  
                              <div class="form-group form-default form-static-label col-md-4 mb-4">
                                 <span class="font-weight-bold font-italic" style="color: #708090">Valor Parcelas</span>
                                 <input style="color: #000000" type="text" name="vlrParcelasModalAditivo" id="vlrParcelasModalAditivo" readonly class="form-control" placeholder="Valor Parcelas">
                              </div>

                              <div class="form-group form-default form-static-label col-md-4 mb-4">
                                 <span class="font-weight-bold font-italic" style="color: #708090">Quantidade Parcelas Setup</span>
                                 <input style="color: #000000" type="text" name="qtyParcSetupModalAditivo" id="qtyParcSetupModalAditivo" readonly class="form-control" placeholder="Quantidade Parcelas Comissão">
                              </div>

                              <div class="form-group form-default form-static-label col-md-4 mb-4">
                                 <span class="font-weight-bold font-italic" style="color: #708090">Valor Setup</span>
                                 <input style="color: #000000" type="text" name="idValorSetupModalAditivo" id="idValorSetupModalAditivo" readonly class="form-control" placeholder="Valor Comissão" >
                              </div>																															

                           </div>


                        </div>
                     </div>
                  </div>
               </form>
            </div>
            <hr>
            <br>


	      </div>
	      
	      <h4 class="h4stilo" style="text-align: center; color:#00008B; font-style: italic;font-weight: bold;text-decoration: underline;">Lista de Aditivo por Contrato</h4>
	      
	      
	      <div class="modal-body">
            <div style="height: 300px; overflow: scroll;   ">
				<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoAditivo" >
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
			</div>
	      </div>

	      <div class="modal-footer">
	      
	        <button type="button" id="btAddAditivoModaSalvarl" class="btn waves-effect waves-light btn-outline-primary float-left pequeno"    onclick="divVisibleHidden( -1, 1, 1 ); habilitarComponentesAditivoProduto(false);" >Novo</button>	     
	        <button type="button" id="btAddAditivoModalSalvar" class="btn waves-effect waves-light btn-outline-success float-right pequeno"   onclick="AddAditivoModal();" >Salvar</button>
	        <button type="button" id="btFecharAditivoModal"    class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
 </div>

	<div class="modal t-modal primary" id="ModalMenssagemOK" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Resultado Manutenção</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <strong >Resultado: </strong> <span id="msgResult"></span>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">OK</button>
	      </div>
	    </div>
	  </div>
	</div>
 
 <script type="text/javascript">

  async function calcularModalAditivo(){
		var dt1     = await formatDataStr( document.getElementById("dtInicioModalAditivo").value ); 
		var dt2     = await formatDataStr( document.getElementById("dtFinalModalAditivo" ).value );
		var idSetup = document.getElementById("comissaoModalAditivo"  ).value; 

		let vlrContrato = document.getElementById("vlrTotalModalAditivo").value;

		if( ( vlrContrato === null || vlrContrato === '' || vlrContrato.trim() === '' ) ){
			  var iconi           = "warning";
			  var tituloPrincipal = "Valor Total Aditivo";
			  var textoPrincipal  = "Para realizar o cálculo da comissão, precisa preencher o valor total do Aditivo!";
			  var nomeModal       = 'ModalAditivoProduto';
		      MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );
		      document.getElementById("vlrTotalModalAditivo").focus();
		      $( '#comissaoModalAditivo' ).get(0).selectedIndex = 0;	
		} else{		
			if( ( dt1 != null && dt1 != '' && dt1.trim() != '' ) && 
			    ( dt2 != null && dt2 != '' && dt2.trim() != '' ) ){
		
				  var data1 = new Date(dt1); 
				  var data2 = new Date(new Date(dt2));
				  var total = (data2.getFullYear() - data1.getFullYear())*12 + (data2.getMonth() - data1.getMonth());
				   
				  vlrConvet =  parseFloat( vlrContrato.replace(".", "").replace(",", ".") );
				  let vlrParcela = vlrConvet / total;
				  document.getElementById("qtyMesesContratoModalAditivo").value = total;			
				  document.getElementById("vlrParcelasModalAditivo"     ).value = vlrParcela.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
		
				  if(idSetup === "1" ){
					 
					if( total > 35 ){
						var vlr = vlrConvet / total;
						const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
						document.getElementById("idValorSetupModalAditivo").value = formatado;
						document.getElementById("qtyParcSetupModalAditivo").value = "1";
					}else if( total < 36 ){
						var vlr = vlrConvet * 0.027;
						const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
						document.getElementById("idValorSetupModalAditivo").value = formatado;
						document.getElementById("qtyParcSetupModalAditivo").value = "1";				
					}
				  }else if(idSetup === "0" ){
					
					var data1 = new Date(dt1); 
					var data2 = new Date(new Date(dt2));
					var total = (data2.getFullYear() - data1.getFullYear())*12 + (data2.getMonth() - data1.getMonth());
					document.getElementById("qtyMesesContratoModalAditivo").value = total;
					
					vlrContrato = vlrContrato.replace(".", "").replace(",", ".");
		
					let vlrConvet = parseFloat( vlrContrato )
					var vlr = (vlrConvet / total) * 0.02;
					const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
					document.getElementById("idValorSetupModalAditivo").value = formatado;
					document.getElementById("qtyParcSetupModalAditivo").value = total;
				  }
			}
		}
	}
	
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/ 
 
 
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
  function calculaDataFinalVigenciaModalAditivo() {
 	 
 	 var idTempoContratoMA    = selectTempoContratoModalAditivo.options[selectTempoContratoModalAditivo.selectedIndex].value;
 	 var dtInicioModalAditivo = document.getElementById("dtInicioModalAditivo" ).value;
 	 var dtFinalModalAditivo  = document.getElementById("dtFinalModalAditivo"  ).value;
 	 var urlAction            = document.getElementById("formContrato"         ).action;
  
 	 $.ajax({ 			
   			method : "get",
   			url : urlAction,
   			data : 'acao=CalculaVigencia&idTempoContrato=' + idTempoContratoMA + '&dtInicio=' + dtInicioModalAditivo + '&dtFinal=' + dtFinalModalAditivo,
   			success: function(lista){
   				var json = JSON.parse(lista);
   				$("#dt_criacao_vigenciaModalAditivo").val( json.dt_criacao );
   				$("#dtInicioModalAditivo"           ).val( json.dt_inicio  );
   				$("#dtFinalModalAditivo"            ).val( json.dt_final   );

    			}
   	 }).fail(function( xhr, status, errorThrown ){
  // 			alert('Erro carregar select Produto: ' + xhr.responseText);
 			var iconi           = "error";
  			var tituloPrincipal = "ERRO";
  			var textoPrincipal  = "Erro: Calcula Vigencia: " + xhr.responseText;
  			var nomeModal       = 'ModalAditivoProduto';
			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal )
   	 }); 
 }
 
 
 
 
 
 
 
 
 
 
 
 //$("#vlrTotalModalAditivo").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
 $("#vlrTotalModalAditivo"        ).maskMoney({ showSymbol:true, symbol:""   , decimal:",", thousands:"." } );
 $("#vlrUnitUserMModalAditivo"    ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#vlrUnitservicoModalAditivo"  ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#valor_convertidoModalAditivo").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#valor_CotacaoModalAditivo"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#vlrUnitDRModalAditivo"       ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#vlrUnitVPNModalAditivo"      ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 $("#idValorSetupModalAditivo"    ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." } );
 function esconderLinha(idDaLinha) {
   // procura o elemento com o ID passado para a função e coloca o estado para o contrario do estado actual
    $("#" + idDaLinha).toggle();
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/ 
 function habilitaSetupModalAditivo() {

	  	var comMAR      = document.getElementById("comissaoModalAditivo"  ).value;
	  	var idValorSetup = document.querySelector("#idValorSetupModalAditivo");
	  		
	  	if(comMAR === "0"){
	  		$("#idValorSetupModalAditivo"    ).val('R$ 0,00');
	  		$("#qtyMesesContratoModalAditivo").val('0'      );
	  		$("#vlrParcelasModalAditivo"     ).val('R$ 0,00');
	  		$("#qtyParcSetupModalAditivo"    ).val('0'      );
	  		idValorSetupModalAditivo.disabled = true;
	  	}else{
	  		idValorSetupModalAditivo.disabled = false;
	  		comissaoModalAditivo.disabled = false;
	  	}
 		
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function MontaDivVisibleHidden() {
	 
	 var idTipoAditivo   = idTipoAditivoModalAditivo.options[idTipoAditivoModalAditivo.selectedIndex].value;
	 divVisibleHidden( idTipoAditivo, 1, 0 );	 
 }
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function divVisibleHidden( idTipoAditivo, editarModal, novoAdit ) {
//		alert( typeof(idTipoAditivo) );
		
		switch( parseInt( idTipoAditivo ) ) {
		    case 3:
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divContratacaoVPN").removeAttribute("hidden");
		    	// desabilita todas as outras DIV's.
		    	document.getElementById("divIncrementoServicos").setAttribute("hidden","");
		    	document.getElementById("divContratacaoDR"     ).setAttribute("hidden","");
		    	document.getElementById("divIncrementoUsuario" ).setAttribute("hidden","");
		        break;
		    case 4:
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divContratacaoDR").removeAttribute("hidden");
		    	// desabilita todas as outras DIV's.
		    	document.getElementById("divIncrementoUsuario" ).setAttribute("hidden","");
		    	document.getElementById("divIncrementoServicos").setAttribute("hidden","");
		    	document.getElementById("divContratacaoVPN"    ).setAttribute("hidden","");
		        break;
		    case 6:
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divIncrementoServicos").removeAttribute("hidden");
		    	// desabilita todas as outras DIV's.
		    	document.getElementById("divIncrementoUsuario" ).setAttribute("hidden","");
		    	document.getElementById("divContratacaoDR"     ).setAttribute("hidden","");
		    	document.getElementById("divContratacaoVPN"    ).setAttribute("hidden","");
		        break;
		    case 8:
		    	// Habilita a DIV Incremento de Usuario.
		    	document.getElementById("divIncrementoUsuario").removeAttribute("hidden");
		    	// desabilita todas as outras DIV's.
		    	document.getElementById("divIncrementoServicos").setAttribute("hidden","");
		    	document.getElementById("divContratacaoDR"     ).setAttribute("hidden","");
		    	document.getElementById("divContratacaoVPN"    ).setAttribute("hidden","");
		        break;
		    default:
		    	document.getElementById("divContratacaoVPN"    ).setAttribute("hidden","");
		    	document.getElementById("divContratacaoDR"     ).setAttribute("hidden","");
		    	document.getElementById("divIncrementoUsuario" ).setAttribute("hidden","");
	    	    document.getElementById("divIncrementoServicos").setAttribute("hidden","");
	    }
		
		limparModalAditivo( editarModal, novoAdit );

	}
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
	$( function() {
		  
		  $("#dtInicioModalAditivo").datepicker({
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

	$( function() {
		  
		  $("#dtFinalModalAditivo").datepicker({
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
	$('#ModalAditivoProduto').on('show.bs.modal', function(e){
/*
		var dtFinal = document.getElementById( "dt_final" ).value; // 6
		const horas = new Date();
		$("#dtInicioModalAditivo").val( horas.toLocaleDateString('pt-BR') );
		$("#dtFinalModalAditivo" ).val( dtFinal                           );
*/		
		listaAditivosInicial();
	});

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	$('#ModalAditivoProduto').on('hidden.bs.modal', function (e) {
		goRecursoAditivo( document.getElementById("id_cliente").value );
		divVisibleHidden( -1, 1, 1 );		
	})

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function validaModal(){
		
		 dataValidada  = document.getElementById("dtInicioModalAditivo").value;
		 var nomeModal = 'ModalAditivoProduto';
		 var iconi     = 'warning';
		
		 // Campo Tipo Aditivo
		 if(document.getElementById("idTipoAditivoModalAditivo").value == ""){
	        // alert('Por favor, Selecione o "Tipo Aditivo"!');
	        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, Selecione o "Tipo Aditivo"!', nomeModal );
	        document.getElementById("idTipoAditivoModalAditivo").focus();
	        return false
		 }
		 // Campo Status
		 if(document.getElementById("idStatusModalAditivo").value == ""){
	        // alert('Por favor, informe um Status para o Aditivo!');
	        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe um Status para o Aditivo!', nomeModal );
	        document.getElementById("idStatusModalAditivo").focus();
	        return false
		 }
		 if(document.getElementById("id_moedaModalAditivo").value == ""){
		        // alert('Por favor, Selecione a "Moeda"!');
		        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, Selecione a "Moeda"!', nomeModal );
		        document.getElementById("id_moedaModalAditivo").focus();
		        return false
		  }

		 // Campo Data Inicio Vigencia
		 if(document.getElementById("dtInicioModalAditivo").value == ""){
	        // alert('Por favor, informe  a "Data Início Vigência""!');
	        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe  a "Data Início Vigência""!', nomeModal );
	        document.getElementById("dtInicioModalAditivo").focus();
	        return false
		 }
		 // Campo Data Fi Vigencia
		 if(document.getElementById("dtFinalModalAditivo").value == ""){
	        // alert('Por favor, informe a "Data Fim Vigência"!');
	        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe a "Data Fim Vigência"!', nomeModal );
	        document.getElementById("dtFinalModalAditivo").focus();
	        return false
		 }
		 
		 var patternData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
		 if(!patternData.test(dataValidada)){
		     // alert("Digite a data no formato Dia/Mês/Ano");
		     MensagemConfimacaoModal( iconi, "Validação de Formulário", "Digite a data no formato Dia/Mês/Ano", nomeModal );
		     return false;
		 }
		// Campo Valor
		 if(document.getElementById("vlrTotalModalAditivo").value == ""){
		        // alert('Por favor, informe um Valor para o Aditivo!');
		        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe um Valor para o Aditivo!', nomeModal );
		        document.getElementById("vlrTotalModalAditivo").focus();
		        return false
		 }
		
		
		 // Info. Vigencia 
		 if(document.getElementById("dtInicioModalAditivo").value == ""){
		        // alert('Por favor, informe um Valor para o Aditivo!');
		        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe a data início da Vigência!', nomeModal );
		        document.getElementById("dtInicioModalAditivo").focus();
		        return false
		 }
		 
		 if(document.getElementById("dtFinalModalAditivo").value == ""){
		        // alert('Por favor, informe um Valor para o Aditivo!');
		        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe a data fim da Vigência!', nomeModal );
		        document.getElementById("dtFinalModalAditivo").focus();
		        return false
		 }
		 if($('#selectTempoContratoModalAditivo').get(0).selectedIndex == 0 ){
		        // alert('Por favor, informe um Valor para o Aditivo!');
		        MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe o periódo da Vigência!', nomeModal );
		        document.getElementById("selectTempoContratoModalAditivo").focus();
		        return false
		 }
		 

	    // Valida por tipo aditivo selecionando.
		var idTipoAditivo = idTipoAditivoModalAditivo.options[idTipoAditivoModalAditivo.selectedIndex].value;

		switch(idTipoAditivo) {
		   case "8":
			   	 if(document.getElementById("qtyUserModalAditivo").value == ""){
				    // alert('Por favor, informe o Número de Usuários!');
				    MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe o Número de Usuários!', nomeModal );
			 	    document.getElementById("qtyUserModalAditivo").focus();
			 	    return false
			 	 }else if(document.getElementById("produtoUserModalAditivo").value == ""){
					    // alert('Por favor, Selecionar o serviço "Usuário"!');
					    MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, Selecionar o serviço "Usuário"!', nomeModal );
				 	    document.getElementById("produtoUserModalAditivo").focus();
				 	    return false
				    }else if(document.getElementById("vlrUnitUserMModalAditivo").value == ""){
						    // alert('Por favor, informe o Valor Unitário!"');
						    MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe o Valor Unitário!"', nomeModal );
					 	    document.getElementById("produtoUserModalAditivo").focus();
					 	    return false
					   }
			    	
		        break;
		   case "6":
			   	 if(document.getElementById("servicoContratadoModalAditivo").value == ""){
					    // alert('Por favor, informe o "Servico Contratado"!');
					    MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe o "Servico Contratado"!', nomeModal );
				 	    document.getElementById("servicoContratadoModalAditivo").focus();
				 	    return false
				 } else if(document.getElementById("qtyServicoModalAditivo").value == ""){
					    // alert('Por favor, informe a "Quantidade"!');
					    MensagemConfimacaoModal( iconi, "Validação de Formulário", 'Por favor, informe a "Quantidade"!', nomeModal );
				 	    document.getElementById("qtyServicoModalAditivo").focus();
				 	    return false
				 } 
			    break;
	   }
       return true;
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function AddAditivoModal() {
		 var nomeModal          = 'ModalAditivoProduto';
		 var iconi              = 'success';

		 var urlAction            = document.getElementById("formContrato").action;
	     // informaoes para todos!
		 var dtInicio             = document.getElementById( "dtInicioModalAditivo"           ).value; //  1
		 var dtFinal              = document.getElementById( "dtFinalModalAditivo"            ).value; //  2
		 var idTipoAditivo        = document.getElementById( "idTipoAditivoModalAditivo"      ).value; //  3
		 var idStatus             = document.getElementById( "idStatusModalAditivo"           ).value; //  4
		 var idAditivo            = document.getElementById( "idAditivoModalAditivo"          ).value; //  5
		 var dtCriacao            = document.getElementById( "dtCriacaoModalAditivo"          ).value; //  6
		 var observacao           = document.getElementById( "observacaoModalAditivo"         ).value; //  7
		 var vlrTotal             = document.getElementById( "vlrTotalModalAditivo"           ).value; //  8
		 var idHubSpotAdi         = document.getElementById( "idHubSpotAditivoPROD"           ).value; //  9
		 // Incremento de Serviços
		 var servicoCont          = document.getElementById( "servicoContratadoModalAditivo"  ).value; // 10
		 var qtyServico           = document.getElementById( "qtyServicoModalAditivo"         ).value; // 11
		 var vlrUnitServ          = document.getElementById( "vlrUnitservicoModalAditivo"     ).value; // 12 
		 var descServCont         = document.getElementById( "descServContratadoModalAditivo" ).value; // 13
		 // Contratação do DR
		 var produtoDR            = document.getElementById( "produtoDRModalAditivo"          ).value; // 14
		 var tpDR                 = document.getElementById( "tpDRModalAditivo"               ).value; // 15
		 var qtyDR                = document.getElementById( "qtyDRModalAditivo"              ).value; // 16
		 var vlrUnitDR            = document.getElementById( "vlrUnitDRModalAditivo"          ).value; // 17
	     // Incremento de Usuário
		 var qtyUser              = document.getElementById( "qtyUserModalAditivo"            ).value; // 18
		 var vlrUnitUser          = document.getElementById( "vlrUnitUserMModalAditivo"       ).value; // 19
		 var produtoUser          = document.getElementById( "produtoUserModalAditivo"        ).value; // 20
		 // Contratação de VPN
		 var produtoVPN           = document.getElementById( "produtoVPNodalAditivo"          ).value; // 21
		 var tpVPN                = document.getElementById( "tpVPNodalAditivo"               ).value; // 22
		 var qtyVPN               = document.getElementById( "qtyVPNodalAditivo"              ).value; // 23
		 var vlrUnitVPN           = document.getElementById( "vlrUnitVPNModalAditivo"         ).value; // 24
	     // Formulario principal
		 var idContrato           = document.getElementById("id_contrato"                     ).value; // 25
		 // Informacao do tipo de Moeda Contratada para o Atidivo.
		 var idMoedaMA            = document.getElementById( "id_moedaModalAditivo"           ).value; // 26
		 var vlrConvertMA         = document.getElementById( "valor_convertidoModalAditivo"   ).value; // 27
		 var vlrCotacaoMA         = document.getElementById( "valor_CotacaoModalAditivo"      ).value; // 28
		 var idRascunho           = document.getElementById( "id_rascunhoAditivo"             ).value; // 29
		 var mRascunho            = document.getElementById( "motivoRascunhoAditivo"          ).value; // 30
		// Informacoes da comissicao de venda sobre o contrato equivalente a equipe de vendas
		 var comissaoMA           = document.getElementById( "comissaoModalAditivo"           ).value; // 31
		 var idValorSetupMA       = document.getElementById( "idValorSetupModalAditivo"       ).value; // 32
		 var qtyMesesContratoMA   = document.getElementById( "qtyMesesContratoModalAditivo"   ).value; // 33
		 var vlrParcelasMA        = document.getElementById( "vlrParcelasModalAditivo"        ).value; // 34
		 var qtyParcSetupMA       = document.getElementById( "qtyParcSetupModalAditivo"       ).value; // 35
		 
		 var TempoContratoMA      = document.getElementById( "selectTempoContratoModalAditivo").value; // 36
		 var observacaoVigenciaMA = document.getElementById( "observacaoVigenciaModalAditivo" ).value; // 37

		 
		 var dados = 'acao=AddAditivoModal'   +
		             '&dtInicio='       	  + dtInicio	 	    + //  1
		             '&dtFinal='      		  + dtFinal		        + //  2
		             '&idTipoAditivo='        + idTipoAditivo	    + //  3
		             '&idStatus='     		  + idStatus		    + //  4
		             '&idAditivo='      	  + idAditivo	 	    + //  5
		             '&dtCriacao='      	  + dtCriacao		    + //  6
		             '&observacao='      	  + observacao	        + //  7	 
		             '&vlrTotal='      		  + vlrTotal		    + //  8
					 // Incremento de Serviços
		             '&servicoCont='      	  + servicoCont	        + //  9
		             '&qtyServico='      	  + qtyServico	        + // 10	 
		             '&vlrUnitServ='      	  + vlrUnitServ	        + // 11
		             '&descServCont='      	  + descServCont	    + // 12
					 // Contratação do DR
		             '&produtoDR='      	  + produtoDR		    + // 13
		             '&tpDR='      			  + tpDR		 	    + // 14
		             '&qtyDR='      		  + qtyDR		  	    + // 15
		             '&vlrUnitDR='      	  + vlrUnitDR		    + // 16
		             // Incremento de Usuário
		             '&qtyUser='      		  + qtyUser		        + // 17
		             '&vlrUnitUser='		  + vlrUnitUser	        + // 18
		             '&produtoUser='		  + produtoUser	        + // 19
		 			 // Contratação de VPN
		             '&produtoVPN='      	  + produtoVPN	        + // 20
		             '&tpVPN='      		  + tpVPN			    + // 21
		             '&qtyVPN='      		  + qtyVPN		        + // 22
		             '&vlrUnitVPN='      	  + vlrUnitVPN	        + // 23
		             // Formulario principal
		             '&idContrato='      	  + idContrato	        + // 24
		             // Informacao do tipo de Moeda Contratada para o Atidivo.
		             '&idMoedaMA='      	  + idMoedaMA	        + // 25
		             '&vlrConvertMA='      	  + vlrConvertMA	    + // 26
 		             '&vlrCotacaoMA='         + vlrCotacaoMA	    + // 27
 		             '&idHubSpotAdi='      	  + idHubSpotAdi	    + // 28
 		             '&idRascunho='      	  + idRascunho	        + // 29
 		             '&mRascunho='      	  + mRascunho    	    + // 30
 		             // Info sombre comissao
 		             '&comissaoMA='      	  + comissaoMA	        + // 31
 		             '&idValorSetupMA='       + idValorSetupMA      + // 32
 		             '&qtyMesesContratoMA='   + qtyMesesContratoMA  + // 33
 		             '&vlrParcelasMA='        + vlrParcelasMA       + // 34
 		             '&qtyParcSetupMA='       + qtyParcSetupMA	    + // 35
                     // Info. Vigencia
 		             '&TempoContratoMA='      + TempoContratoMA	    + // 36
 		             '&observacaoVigenciaMA=' + observacaoVigenciaMA; // 37 		             
 		             
		 var editCampo = 0;
		if( idAditivo != null && idAditivo != '' && idAditivo.trim() != '' ){
			editCampo = 1;
		}

		 if( validaModal() ){

			 $.ajax({
					method : "get",
					url    : urlAction,
					data   : dados,
					success: function(lista){
		  				var json = JSON.parse(lista);
		  				var html               = '';
		  				var idAditivadoTemp    = '';
		  				var dtCriacaoTemp      = '';
		  				var login_cadastroTemp = '';
		  				var idAditivadoAux     = 0;
		  				let vHubspotAditivo    = '';

		  				$('#tabelaResutadoAditivo > tbody > tr').remove();
		  				for(var p = 0; p < json.length; p++){

       	  					if( idAditivadoAux !== json[p].id_aditivado ){
    	  						if( idAditivadoAux !== 0 ){
    	  				  			html +='</tbody>'
    	  		  				      +  '</table>'
    	  		  				      + ' </td>'
    	  		  				      + '</tr>';	
    	  						}
    	 						if( json[p].hubspot_aditivo != undefined ) vHubspotAditivo = json[p].hubspot_aditivo;
    	 						else vHubspotAditivo = ' - ';

    	  						html += '<tr> '
    		  						  +    '<td align=\"center\"> <button onClick="esconderLinha( \'linhaAEsconder' + p + '\')"> + </button> </td>'
    		  						  +    '<td>' + json[p].id_aditivado   + '</td>'
    		  						  +    '<td>' + vHubspotAditivo        + '</td>'
    		  						  +    '<td>' + json[p].dt_criacao     + '</td>'
    		  						  +    '<td>' + json[p].vlr_total_adit + '</td>'
    	  						      + '</tr>'
                                      + '<tr id="linhaAEsconder' + p + '" style="display: none">'
      							      +    '<td></td>'
      							      +    '<td colspan="6">'
      							      +       '<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="sub-tabelaResutadoAditivo">'
      							      +          '<thead>'
      							      +              '<tr>'
      							      +                 '<th scope="col">Tipo Aditivo </th>'
      							      +                 '<th scope="col">Produto      </th>'
      							      +                 '<th scope="col">Quantidade   </th>'
      							      +                 '<th scope="col">Valor Unit.  </th>'
/*      							      +                 '<th scope="col">Valor Total  </th>' */
      							      +                 '<th scope="col">Editar       </th>'
      							      +              '</tr>'
      							      +          '</thead>'
      							      +          '<tbody>'
    				  				  +             '<tr> ' 
    		  					      +               '<td>' + json[p].nome_tipo_aditivo_contratado + '</td>'
    		  					      +               '<td>' + json[p].nome_prod_contratado         + '</td>'
    		  					      +               '<td>' + json[p].qty_produto_contratado       + '</td>'
    		  					      +               '<td>' + json[p].valor_unitario_contratado    + '</td>'
/*    		  					      +               '<td>' + json[p].vlr_produto_contratado       + '</td>'		*/  					      
    	  					          +               '<td>' 
    	  					          +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivo(' +  json[p].id_aditivado + ',' + json[p].id_tipo_aditivo + ',' + json[p].id_produto_contratado + ');"> ' 
    								  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
    								  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
    								  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
    								  +                 '   </svg> </button> ' 
    								  +               '</td>' 
    					              +             '</tr>';

    	  						idAditivadoAux = json[p].id_aditivado;
    	  					}else{
    		  					 html +=             '<tr> ' 
    		  					      +               '<td>' + json[p].nome_tipo_aditivo_contratado + '</td>'
    		  					      +               '<td>' + json[p].nome_prod_contratado         + '</td>'
    		  					      +               '<td>' + json[p].qty_produto_contratado       + '</td>'
    		  					      +               '<td>' + json[p].valor_unitario_contratado    + '</td>'
/*    		  					      +               '<td>' + json[p].vlr_produto_contratado       + '</td>' */
    	  					          +               '<td>' 
    	  					          +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivo(' +  json[p].id_aditivado + ',' + json[p].id_tipo_aditivo + ',' + json[p].id_produto_contratado + ');"> ' 
    								  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
    								  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
    								  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
    								  +                 '   </svg> </button> ' 
    								  +               '</td>' 
    					              +             '</tr>';
    	  					}
		  					      
		  					idAditivadoTemp    = json[p].id_aditivado; 
		  					dtCriacaoTemp      = json[p].dt_criacao ;  
		  					login_cadastroTemp = json[p].login_cadastro ; 
		  				}
			  			html +='</tbody>'
		  				      +  '</table>'
		  				      + ' </td>'
		  				      + '</tr>';	      

	  				    $("#tabelaResutadoAditivo tbody").html(html).show(); 
	  				    
                        if( editCampo === 0 ){
			  				$("#idAditivoModalAditivo").val( idAditivadoTemp    );
			  				$("#dtCriacaoModalAditivo").val( dtCriacaoTemp      );
			  				$("#loginCadastroAditivo" ).val( login_cadastroTemp );
			  				
                        }

                        var msnTipoAdit = '';
					    switch ( parseInt( idTipoAditivo ) ) {
					     case 3:
								msnTipoAdit = 'Contratação de VPN';
								break;
					     case 4:
								msnTipoAdit = 'Contratação do DR';
								break;
					     case 6:
								msnTipoAdit = 'Incremento de Serviços';
								break;
					     case 8:// gravar Atualiza Incremento Usuario
								msnTipoAdit = 'Incremento de Usuário';
								break;
					    }
					    
                        // alert( 'Processo "' +msnTipoAdit + '" Executado com Sucesso!' );
                        tituloPrincipal = msnTipoAdit;
    			        textoPrincipal  = 'Procedimento do cliemte ' + document.getElementById("nomeCliente").value + ' - Contrato ' + idContrato + ", executado com sucesso!";
    			        // Mensagem apos execucao.
                        //MensagemConfimacaoModal( "success", tituloPrincipal, textoPrincipal, 'ModalAditivoProduto' );
                        MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );

					}
			 }).fail(function( xhr, status, errorThrown ){
				//	alert('Erro ao Adicionar "Aditivo": ' + xhr.responseText);
					MensagemConfimacaoModal( "error", 'Erro na Adição/Atualização de "Aditivo": ', xhr.responseText, nomeModal );
					
			 }); 
			
		 }

	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function listaAditivosInicial() {
		 var urlAction  = document.getElementById( "formContrato" ).action;
		 var idContrato = document.getElementById( "id_contrato"  ).value;
		 var nomeModal  = 'ModalAditivoProduto';
		 var iconi      = 'error';

		 $.ajax({
				method : "get",
				url    : urlAction,
				data   : 'acao=listaAditivosInicial&idContrato=' + idContrato,
				success: function(lista){

	  				var json            = JSON.parse(lista);
	  				var html            = '';
	  				var idAditivadoAux  = 0;
	  				let vHubspotAditivo = '';
	  				
	  				$('#tabelaResutadoAditivo > tbody > tr').remove();	  				

	  				for(var p = 0; p < json.length; p++){
	  					
	  					if( idAditivadoAux !== json[p].id_aditivado ){
	  						if( idAditivadoAux !== 0 ){
	  				  			html +='</tbody>'
	  		  				      +  '</table>'
	  		  				      + ' </td>'
	  		  				      + '</tr>';	
	  						}
	 						if( json[p].hubspot_aditivo != undefined ) vHubspotAditivo = json[p].hubspot_aditivo;
	 						else vHubspotAditivo = ' - ';
	  						html += '<tr> '
		  						  +    '<td align=\"center\"> <button onClick="esconderLinha( \'linhaAEsconder' + p + '\')"> + </button> </td>'
		  						  +    '<td>' + json[p].id_aditivado   + '</td>'
		  						  +    '<td>' + vHubspotAditivo        + '</td>'
		  						  +    '<td>' + json[p].dt_criacao     + '</td>'
		  						  +    '<td>' + json[p].vlr_total_adit + '</td>'
	  						      + '</tr>'
                                  + '<tr id="linhaAEsconder' + p + '" style="display: none">'
  							      +    '<td></td>'
  							      +    '<td colspan="6">'
  							      +       '<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="sub-tabelaResutadoAditivo">'
  							      +          '<thead>'
  							      +              '<tr>'
  							      +                 '<th scope="col">Tipo Aditivo </th>'
  							      +                 '<th scope="col">Produto      </th>'
  							      +                 '<th scope="col">Quantidade   </th>'
  							      +                 '<th scope="col">Valor Unit.  </th>'
/*  							      +                 '<th scope="col">Valor Total  </th>' */
  							      +                 '<th scope="col">Editar       </th>'
  							      +              '</tr>'
  							      +          '</thead>'
  							      +          '<tbody>'
				  				  +             '<tr> ' 
		  					      +               '<td>' + json[p].nome_tipo_aditivo_contratado + '</td>'
		  					      +               '<td>' + json[p].nome_prod_contratado         + '</td>'
		  					      +               '<td>' + json[p].qty_produto_contratado       + '</td>'
		  					      +               '<td>' + json[p].valor_unitario_contratado    + '</td>'
/*		  					      +               '<td>' + json[p].vlr_produto_contratado       + '</td>'	*/	  					      
	  					          +               '<td>' 
	  					          +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivo(' +  json[p].id_aditivado + ',' + json[p].id_tipo_aditivo + ',' + json[p].id_produto_contratado + ');"> ' 
								  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
								  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
								  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
								  +                 '   </svg> </button> ' 
								  +               '</td>' 
					              +             '</tr>';

	  						idAditivadoAux = json[p].id_aditivado;
	  					}else{
		  					 html +=             '<tr> ' 
		  					      +               '<td>' + json[p].nome_tipo_aditivo_contratado + '</td>'
		  					      +               '<td>' + json[p].nome_prod_contratado         + '</td>'
		  					      +               '<td>' + json[p].qty_produto_contratado       + '</td>'
		  					      +               '<td>' + json[p].valor_unitario_contratado    + '</td>'
/*		  					      +               '<td>' + json[p].vlr_produto_contratado       + '</td>' */
	  					          +               '<td>' 
	  					          +                 ' <button type="button" class="btn btn-primary" onclick="buscarEditarAditivo(' +  json[p].id_aditivado + ',' + json[p].id_tipo_aditivo + ',' + json[p].id_produto_contratado + ');"> ' 
								  +                 ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
								  +                 '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
								  +                 '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
								  +                 '   </svg> </button> ' 
								  +               '</td>' 
					              +             '</tr>';
	  					}
	  				}
	  				 
		  			html +='</tbody>'
	  				      +  '</table>'
	  				      + ' </td>'
	  				      + '</tr>';	      
		  			$("#tabelaResutadoAditivo tbody").html(html).show();  
				}
			 }).fail(function( xhr, status, errorThrown ){
					// alert('Erro carregar select Produto: ' + xhr.responseText);
					MensagemConfimacaoModal( iconi, 'Erro ao listar Aditivos Inicialmente: ', xhr.responseText, nomeModal ); 
			 }); 
			
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function limparModalAditivo( editarModal, novoAdit ) {
//		alert('editarModal: ' + editarModal)
		
		if( editarModal != 0 ){
	//		document.getElementById("idTipoAditivoModalAditivo"    ).selectedIndex = "0";
			
			if(novoAdit != 0){
				$("#idAditivoModalAditivo"          ).val( '' );
				$("#dtCriacaoModalAditivo"          ).val( '' );
				$("#valor_convertidoModalAditivo"   ).val( '' );
				$("#valor_CotacaoModalAditivo"      ).val( '' );
				document.getElementById("id_moedaModalAditivo"     ).selectedIndex = 0;
				$("#vlrTotalModalAditivo"           ).val( '' );
				document.getElementById("idStatusModalAditivo"     ).selectedIndex = 0;
				$("#observacaoModalAditivo"         ).val( '' );
				$("#loginCadastroAditivo"           ).val( '' );
				document.getElementById("idTipoAditivoModalAditivo").selectedIndex = 0;
				$("#idHubSpotAditivoPROD"           ).val( '' );
                // Limpa dados Vigencia
				$("#id_vigenciaModalAditivo"         ).val( '' );
				$("#dt_criacao_vigenciaModalAditivo" ).val( '' );
				$("#dtInicioModalAditivo"            ).val( '' );
				$("#dtFinalModalAditivo"             ).val( '' );
				$("#observacaoVigenciaModalAditivo"  ).val( '' );
				document.getElementById("selectTempoContratoModalAditivo").selectedIndex = 0;
				
				$("#idValorSetupModalAditivo"        ).val( '' );
				$("#qtyMesesContratoModalAditivo"    ).val( '' );
				$("#vlrParcelasModalAditivo"         ).val( '' );
				$("#qtyParcSetupModalAditivo"        ).val( '' );
				document.getElementById("comissaoModalAditivo").selectedIndex = 0;
				
				
				// document.getElementById("idSetupModalAditivo"      ).selectedIndex = 0;
				// $("#idValorSetupModalAditivo"       ).val( '' );	

		    }
			// Incremento de Serviços
			document.getElementById("servicoContratadoModalAditivo").selectedIndex = "0";
	        $("#qtyServicoModalAditivo"        ).val( '' );
	        $("#vlrUnitservicoModalAditivo"    ).val( '' );
			$("#descServContratadoModalAditivo").val( '' );

			// Contratação do DR
			document.getElementById("produtoDRModalAditivo").selectedIndex = "0";
			document.getElementById("tpDRModalAditivo").selectedIndex      = "0";
			$("#qtyDRModalAditivo"             ).val( '' );
			$("#vlrUnitDRModalAditivo"         ).val( '' );
			
            // Incremento de Usuário
			$("#qtyUserModalAditivo"           ).val( '' );
			$("#vlrUnitUserMModalAditivo"      ).val( '' );
			document.getElementById("produtoUserModalAditivo").selectedIndex = "0";
			
			// Contratação de VPN
			document.getElementById("produtoVPNodalAditivo").selectedIndex = "0";
			document.getElementById("tpVPNodalAditivo").selectedIndex      = "0";
			$("#qtyVPNodalAditivo"             ).val( '' );
			$("#vlrUnitVPNModalAditivo"        ).val( '' );
			$("#id_rascunhoAditivo"            ).val( '' );
			$("#motivoRascunhoAditivo"         ).val( '' );
			habilitaStatusMotivoRascunhoAditivo();
			
	//		$("#dtInicioModalAditivo"          ).val( '' );
	//		$("#dtFinalModalAditivo"           ).val( '' );
		}
	}

	/******************************************************************/
	 /*                                                                */
	 /*                                                                */
	 /******************************************************************/
	 function habilitarComponentesAditivoProduto(habilitar) {
	    document.getElementById('idTipoAditivoModalAditivo'     ).disabled = habilitar;                
	    document.getElementById('idStatusModalAditivo'          ).disabled = habilitar;           
	    document.getElementById('id_moedaModalAditivo'          ).disabled = habilitar;                        
	    document.getElementById('vlrTotalModalAditivo'          ).disabled = habilitar;                
	    document.getElementById('valor_convertidoModalAditivo'  ).disabled = habilitar;                      
	    document.getElementById('valor_CotacaoModalAditivo'     ).disabled = habilitar;                          
	    document.getElementById('produtoUserModalAditivo'       ).disabled = habilitar;                               
	    document.getElementById('qtyUserModalAditivo'           ).disabled = habilitar;                                   
	    document.getElementById('vlrUnitUserMModalAditivo'      ).disabled = habilitar;                                
	    document.getElementById('servicoContratadoModalAditivo' ).disabled = habilitar;                         
	    document.getElementById('qtyServicoModalAditivo'        ).disabled = habilitar;                            
	    document.getElementById('vlrUnitservicoModalAditivo'    ).disabled = habilitar;                            
	    document.getElementById('descServContratadoModalAditivo').disabled = habilitar;                                 
	    document.getElementById('produtoDRModalAditivo'         ).disabled = habilitar;                      
	    document.getElementById('tpDRModalAditivo'              ).disabled = habilitar;                         
	    document.getElementById('qtyDRModalAditivo'             ).disabled = habilitar;                      
	    document.getElementById('vlrUnitDRModalAditivo'         ).disabled = habilitar;                     
	    document.getElementById('produtoVPNodalAditivo'         ).disabled = habilitar;                              
	    document.getElementById('tpVPNodalAditivo'              ).disabled = habilitar;                                
	    document.getElementById('qtyVPNodalAditivo'             ).disabled = habilitar;                               
	    document.getElementById('vlrUnitVPNModalAditivo'        ).disabled = habilitar;                                   
	    document.getElementById('observacaoModalAditivo'        ).disabled = habilitar;                         
	    document.getElementById('btAddAditivoModalSalvar'       ).disabled = habilitar;
	    document.getElementById('id_rascunhoAditivo'            ).disabled = habilitar;
	    document.getElementById('motivoRascunhoAditivo'         ).disabled = habilitar;
//	    document.getElementById('idSetupModalAditivo'           ).disabled = habilitar;
//	    document.getElementById('idValorSetupModalAditivo'      ).disabled = habilitar;

//	    document.getElementById('dtInicioModalAditivo'          ).disabled = habilitar;                            
//	    document.getElementById('dtFinalModalAditivo'           ).disabled = habilitar;
	    
	    
	 }

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function buscarEditarAditivo( idAditivo, idTipoAditivo, idProduto ) {
		var urlAction  = document.getElementById("formContrato").action;
		 var nomeModal = 'ModalAditivoProduto';
		 var iconi     = 'error';
		 
		$.ajax({ 			
	  		method : "get",
	  		url : urlAction,
	  		data : 'acao=buscarEditarAditivo&idAditivo=' + idAditivo + '&idTipoAditivo=' + idTipoAditivo + '&idProduto=' + idProduto,
	  		success: function(lista){
	  			 var json = JSON.parse(lista);

			     $("#idAditivoModalAditivo"          ).val( json.id_aditivado               ); // id_aditivado
				 $("#dtCriacaoModalAditivo"          ).val( json.dt_criacao                 ); // id_tipo_aditivo
				 $('#idTipoAditivoModalAditivo'      ).val( json.id_tipo_aditivo            ); // idServContratado
				 $("#idStatusModalAditivo"           ).val( json.id_status_aditivo          ); // id_status_aditivo
				 $("#vlrTotalModalAditivo"           ).val( json.vlr_produto_contratado     ); // vlr_produto_contratado
				 $("#loginCadastroAditivo"           ).val( json.login_cadastro             ); // login_cadastro
				 $("#idHubSpotAditivoPROD"           ).val( json.hubspot_aditivo            ); // login_cadastro
				 $("#id_rascunhoAditivo"             ).val( json.id_rascunho                ); // id_rascunho
				 $("#motivoRascunhoAditivo"          ).val( json.motivoRascunho             ); // motivoRascunho
				 
				 // Usuário
				 $("#produtoUserModalAditivo"        ).val( json.id_produto_contratado      ); // observacao
				 $("#qtyUserModalAditivo"            ).val( json.qty_produto_contratado     ); // vlr_toral_adit
				 $("#vlrUnitUserMModalAditivo"       ).val( json.valor_unitario_contratado  ); // qty_usuario_contratada
				 // Serviço Contratado
				 $("#servicoContratadoModalAditivo"  ).val( json.id_produto_contratado      ); // qty_servicos
				 $("#qtyServicoModalAditivo"         ).val( json.qty_produto_contratado     ); // desc_serv_contratado
				 $("#vlrUnitservicoModalAditivo"     ).val( json.valor_unitario_contratado  ); // dt_inicio
				 $("#descServContratadoModalAditivo" ).val( json.desc_serv_prod_contratado  ); // dt_final
				 // Servico DRM
				 $("#produtoDRModalAditivo"          ).val( json.id_produto_contratado      ); // id_Produto
				 $("#tpDRModalAditivo"               ).val( json.id_tipo_protudo_contratado ); // id_Produto
				 $("#qtyDRModalAditivo"              ).val( json.qty_produto_contratado     ); // qty_Produto
				 $("#vlrUnitDRModalAditivo"          ).val( json.valor_unitario_contratado  ); // qty_Produto
				 
				 // Servico VPN
				 $("#produtoVPNodalAditivo"          ).val( json.id_produto_contratado      ); // id_Produto
				 $("#tpVPNodalAditivo"               ).val( json.id_tipo_protudo_contratado ); // id_tipo_produto
				 $("#qtyVPNodalAditivo"              ).val( json.qty_produto_contratado     ); // qty_Produto
				 $("#vlrUnitVPNModalAditivo"         ).val( json.valor_unitario_contratado  ); // qty_Produto

				 $("#observacaoModalAditivo"         ).val( json.observacao                 ); // qty_Produto
				 
				 $("#id_moedaModalAditivo"           ).val( json.id_moeda                   ); // id_moeda
				 $("#valor_convertidoModalAditivo"   ).val( json.valor_convertido           ); // valor_convertido
				 $("#valor_CotacaoModalAditivo"      ).val( json.cotacao_moeda              ); // cotacao_moeda
				 
				 // info. vigencia
				 $("#id_vigenciaModalAditivo"        ).val( json.id_vigencia                ); // id_vigencia
				 $("#dt_criacao_vigenciaModalAditivo").val( json.dt_criacao_vigencia        ); // dt_criacao_vigencia
				 $("#selectTempoContratoModalAditivo").val( json.id_tempo_contrato          ); // id_tempo_contrato
				 $("#dtInicioModalAditivo"           ).val( json.dt_inicio                  ); // dt_inicio
				 $("#dtFinalModalAditivo"            ).val( json.dt_final                   ); // dt_final
				 $("#observacaoVigenciaModalAditivo" ).val( json.observacao_vigencia        ); // observacao_vigencia
				 
				 
				 if( json.comissao_adit === true ) $('#comissaoModalAditivo').get(0).selectedIndex = 1;
				 else $('#comissaoModalAditivo').get(0).selectedIndex = 2;
				 $("#idValorSetupModalAditivo"    ).val( json.valor_setup_adit         );
				 $("#qtyMesesContratoModalAditivo").val( json.qty_mese_setup_adit      );
				 $("#vlrParcelasModalAditivo"     ).val( json.valor_parcela_setup_adit );
				 $("#qtyParcSetupModalAditivo"    ).val( json.qty_parcela_setup_adit   );
				 
	 
				 divVisibleHidden( json.id_tipo_aditivo, 0, 0 );
				 
				 if(document.getElementById('tipoUserAdmin').value.trim() !== "1" )
					 habilitarComponentesAditivoProduto(true);
				 else habilitarComponentesAditivoProduto(false);

				 // Fabiano
	   		}
	  	 }).fail(function( xhr, status, errorThrown ){
	  			// alert('Erro carregar select Produto: ' + xhr.responseText);
	  			MensagemConfimacaoModal( iconi, 'Erro ao Buscar Editar Aditivo', xhr.responseText, nomeModal );  
	  	 }); 
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function getValorProdutoID( nameCampo, campoInput ) {
//	   	 var idproduto = produtoUserModalAditivo.options[produtoUserModalAditivo.selectedIndex].value;
	     var urlAction = document.getElementById("formContrato").action;
		 var idproduto = document.getElementById( nameCampo ).value;
		 var nomeModal = 'ModalAditivoProduto';
		 var iconi     = 'error';

		 $.ajax({ 			
	  			method : "get",
	  			url    : urlAction,
	  			data   : 'acao=getValorProdutoID&idproduto=' + idproduto,
	  			success: function(lista){
	  				var json = JSON.parse(lista);
//	  				$("#vlrUnitUserMModalAditivo").val(json);
	  				document.getElementById( campoInput ).value = json;
	   			}
	  	 }).fail(function( xhr, status, errorThrown ){
	  			// alert('Erro carregar select Produto: ' + xhr.responseText);
	  			MensagemConfimacaoModal( iconi, 'Erro ao buscar Valor do Produto por ID', xhr.responseText, nomeModal ); 
	  	 }); 
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	function getValorServicoID() {
	   	 var idServico = servicoContratadoModalAditivo.options[servicoContratadoModalAditivo.selectedIndex].value;
		 var nomeModal = 'ModalAditivoProduto';
		 var iconi     = 'error';
		
	     var urlAction = document.getElementById("formContrato").action;
		 $.ajax({ 			
	  			method : "get",
	  			url    : urlAction,
	  			data   : 'acao=getValorServicoID&idServico=' + idServico,
	  			success: function(lista){
	  				var json = JSON.parse(lista);
	  				$("#vlrUnitservicoModalAditivo").val(json);
	   			}
	  	 }).fail(function( xhr, status, errorThrown ){
	  			// alert('Erro carregar select Produto: ' + xhr.responseText);
	  			MensagemConfimacaoModal( iconi, 'Erro ao buscar Valor de Servico por ID', xhr.responseText, nomeModal );
	  	 }); 
	}
	
	
	/******************************************************************/
	/*                                                                */
	/*  Retirado a pedido do Eugenio, pois o valor do produro sera    */
	/*  apenas informativo                                            */
	/******************************************************************/
/*	
	function CalculoVlrTotal( nameCampoQty, nameCampoVlr ) {
		
	     var urlAction   = document.getElementById("formContrato").action;
		 var vlrUnitario = document.getElementById( nameCampoVlr ).value;
		 var qtyUnitario = document.getElementById( nameCampoQty ).value;
		 
	     if( ( vlrUnitario != null && vlrUnitario != '' && vlrUnitario.trim() != '' ) && 
	    	 ( qtyUnitario != null && qtyUnitario != '' && qtyUnitario.trim() != '' )	 
	      ){
			  $.ajax({ 			
		  			method : "get",
		  			url    : urlAction,
		  			data   : 'acao=CalculoVlrTotal&vlrUnitario=' + vlrUnitario + '&qtyUnitario=' + qtyUnitario,
		  			success: function(lista){
		  				var json = JSON.parse(lista);
		  				$("#vlrTotalModalAditivo").val(json);
		   			}
		  	  }).fail(function( xhr, status, errorThrown ){
		  			alert('Erro realizar calculo Valor total Aditvo: ' + xhr.responseText);
		  	  }); 
	      } 	 
	}
*/	
	function habilitaCotacaoModalAdit() {	
		var idMoedaMA              = document.getElementById( "id_moedaModalAditivo"         ).value;
		var inputValorConvertidoMA = document.querySelector ( "#valor_convertidoModalAditivo");
		var inputValorCotacaoMA    = document.querySelector ( "#valor_CotacaoModalAditivo"   );
	
		if(idMoedaMA === "1"){			
			$("#valor_convertidoModalAditivo").val('');
			$("#valor_CotacaoModalAditivo"   ).val('');
			inputValorConvertidoMA.disabled = true;
			inputValorCotacaoMA.disabled    = true;				
		}else{
			inputValorConvertidoMA.disabled = false;
			inputValorCotacaoMA.disabled    = false;	
		}
	}

	  
	function cauculoConversaoModalAdit() {
//		 alert( 'Ola' );
		 var valorTotalMA      = document.getElementById("vlrTotalModalAditivo"     ).value;
		 var valorCotacaoMA    = document.getElementById("valor_CotacaoModalAditivo").value;
		 var valorConvertidoMA = '';
		
		 if( ( valorTotalMA   != null && valorTotalMA   != '' && valorTotalMA.trim() != '' ) && 
			 ( valorCotacaoMA != null && valorCotacaoMA != '' && valorCotacaoMA.trim() != '' ) ){
			 
			 valorTotalMA = valorTotalMA.replace(/[^\d]+/g,'');
			 valorTotalMA = valorTotalMA /100;
			 
			 valorCotacaoMA = valorCotacaoMA.replace(/[^\d]+/g,'');
			 valorCotacaoMA = valorCotacaoMA /100;

//			 alert('valorTotalMA: ' + valorTotalMA + ' - valorCotacaoMA: ' + valorCotacaoMA );
			 
			 valorConvertidoMA = valorTotalMA * valorCotacaoMA;
			 const valorCalculadoMA = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(valorConvertidoMA);
			 $("#valor_convertidoModalAditivo").val(valorCalculadoMA ); 
		 }
	}  

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/ 
    function habilitaStatusMotivoRascunhoAditivo() {
	
	  var idStatusModalAditivo       = document.getElementById("idStatusModalAditivo" ).value;
	  var inputid_id_rascunhoAditivo = document.querySelector("#id_rascunhoAditivo"   );
	  var inputmotivoRascunhoAditivo = document.querySelector("#motivoRascunhoAditivo");
	  if(idStatusModalAditivo === "4"){
		  inputid_id_rascunhoAditivo.disabled = false;
		  inputmotivoRascunhoAditivo.disabled = false;
	  }else{
		inputid_id_rascunhoAditivo.disabled = true;
		inputmotivoRascunhoAditivo.disabled = true;
		$("#id_rascunhoAditivo"   ).val('');
		$("#motivoRascunhoAditivo").val('');
	 }	
   }

 </script>

