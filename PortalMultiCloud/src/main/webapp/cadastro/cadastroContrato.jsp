<%@page import="br.com.portal.model.ModelContrato"%>
<%@page import="br.com.portal.model.ModelAitivoRecursoModal"%>
<%@page import="br.com.portal.model.ModelListAditivoProduto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html>
<html lang="en">

<!-- ico font 
<link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css">
-->

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
  
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!--
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/select2/select2.min.css" type="text/css" media="all"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/select2/select2-bootstrap-5-theme.min.css" type="text/css" media="all"/>

<jsp:include page="/principal/head.jsp"></jsp:include>

   <style>
       .mesmo-tamanho-botao {
           width: 15%;
           white-space: normal;
       }
       
       .t-modal .modal-content{
           border-top-left-radius: 10px;
           border-top-right-radius: 10px;
           
           border-bottom-left-radius: 10px;
           border-bottom-right-radius: 10px;
           
           border-bottom: 3px solid transparent;
       }
       
       
       .t-modal .modal-header{
          padding: 30px 15px;
       }
       
        .t-modal .modal-title{
          text-transform: uppercase;
          font-size: 20px;
       }
       
       .t-modal .modal-title,
       .t-modal .close{
          color: #fff;
       }
       
       .t-modal.primary .modal-content{
          border-color: #4a69bd; 
       }
       .t-modal.primary .modal-header{
          background-color: #4a69bd;
       }

        hr {
            height: 3px;
            background-color: #B0C4DE;
            border: none;
        }
        
		.tooltip-inner {
		  background-color: #5A72FE !important;
		  /*!important is not necessary if you place custom.css at the end of your css calls. For the purpose of this demo, it seems to be required in SO snippet*/
		  color: #fff;
		}
		
		.tooltip.top .tooltip-arrow {
		  border-top-color: #00acd6;
		}
		
		.tooltip.right .tooltip-arrow {
		  border-right-color: #00acd6;
		}
		
		.tooltip.bottom .tooltip-arrow {
		  border-bottom-color: #00acd6;
		}
		
		.tooltip.left .tooltip-arrow {
		  border-left-color: #00acd6;
		}
		
		input[type="file"]{
			display: none;
		}

       .pequeno {
           width: 20%;
       }

       .medio {
           width: 30%;
       }
       .error-text{
         font-size:12px;
         color: red;
       }
       
       .center{
          text-align: center;
       }

   </style>


  <body>
  <!-- Pre-loader start -->
  <jsp:include page="/principal/theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <!-- Chamada para o Menu de Barras -->
          <jsp:include page="/principal/navbarheader.jsp"></jsp:include>
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <!-- Chamada para o Navbar Maim Menu -->
                  <jsp:include page="/principal/navbarmainmenu.jsp"></jsp:include>
                  
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                        <jsp:include page="/principal/pageheader.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                        <div class="row">
                                            
                                            <div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<!-- Aqui eh onde comeca a montar todos os elementos pequenos da pagina do corpo. -->
														<h2 class="sub-title">Cadastro de Contrato - Passo <samp id="passo"></samp></h2>
														<!-- <h3>Passo <samp id="passo"></samp></h3> -->
														<form class="form-material formularioCadContrato" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletCadastroContrato" method="post" id="formCadastroContrato">
                                                           
                                                           <!-- Utilizado para quardar a informacao do usuario admin -->
                                                           <input type="hidden"  name="tipoUserAdmin" id="tipoUserAdmin" readonly="readonly" value="${useradmin}">
                                                           
                                                           
                                                           <!-- ##################################################################################### -->
                                                           <!--          Etapa para realizar o cadastro das informacoes relativa ao contrato!         -->
                                                           <!-- ##################################################################################### -->
                                                           <div id="step_1" class="step">
                                                                <input type="hidden"  name="renovacaoContratoControle" id="renovacaoContratoControle" readonly="readonly" value=""> 
                                                                <input type="hidden" name="id_contrato" id="id_contrato" readonly="readonly" value="">
                                                                <div class="form-row">
																     <!-- Campo ID Cliente -->
																     <div class="form-group form-default form-static-label col-md-2 mb-6">
																          <span style="display:none">ID cliente</span>
																	      <input style="color: #000000" type="text" name="id_cliente" id="id_cliente" class="form-control font-weight-bold font-italic text-center step_1_validar"  placeholder="ID Cliente"readonly="readonly" value="">
																     </div>

																     <div class="input-group form-default form-static-label col-md-10 mb-6">
																          <span class="font-weight-bold font-italic" style="color: #708090; display:none">Nome Cliente</span>
																          <input style="color: #000000" type="text" name="nomeCliente" id="nomeCliente"  style="margin-right: 15px;" readonly class="form-control font-weight-bold font-italic step_1_validar" placeholder="Cliente" aria-label="Clinte" aria-describedby="button-addon2" value="">
																          <div class="input-group-append">
																	           <!-- Botao para acionar modal -->
																	           <button type="button" class="btn btn-outline-info" data-toggle="modal" id="id_pesquisa_cliente" data-target="#modalClienteContrato">Pesquisar Cliente</button>															        
																          </div>
																    </div>
															    </div>
															    <hr>  
															    <br>

															    <div class="form-row">
																    <!-- Campo Moeda -->
															  	    <div class="form-group form-default form-static-label col-md-3 mb-4">
																	    <span  class="font-weight-bold font-italic" style="color: #708090">Moeda</span>
																	    <select style="color: #000000" name="id_moeda" id="id_moeda" class="form-control step_1_validar" onchange="habilitaCotacao();"  >
																		    <option value="" disabled selected>[-Selecione-]</option>
																		    <tagsContrato:listaMoeda />
																	    </select> 
																    </div>

																    <div class="form-group form-default form-static-label col-md-3 mb-4">
																        <span  class="font-weight-bold font-italic" style="color: #708090" >Valor Corrente</span>
																	    <input style="color: #000000" type="text" name="valor_total" id="valor_total" onblur="cauculoConversao();" class="form-control step_1_validar"  placeholder="Valor do contrato" value="">
																    </div>

																    <div class="form-group form-default form-static-label col-md-3 mb-4">
																	    <span class="font-weight-bold font-italic" style="color: #708090">Cotação</span>
																	    <input style="color: #000000" type="text" name="valor_Cotacao" id="valor_Cotacao" disabled="disabled" onblur="cauculoConversao();" class="form-control" placeholder="Valor do contrato" value="">
																    </div>

																    <div class="form-group form-default form-static-label col-md-3 mb-4">
																	    <span class="font-weight-bold font-italic" style="color: #708090">Valor Convertido</span>
																	    <input style="color: #000000" type="text" name="valor_convertido" id="valor_convertido" disabled="disabled" class="form-control" placeholder="Valor do contrato" value="">
																    </div>

															    </div>
															
															    <hr>  
															    <br>

															    <div class="form-row">
															         <div class="form-group form-default form-static-label col-md-2 mb-10">
																          <span class="font-weight-bold font-italic" style="color: #708090">Status Contrato</span>
																	      <select style="color: #000000" name="id_status_contrato" id="id_status_contrato" onchange="habilitaStatusMotivoRascunho(); validaStatusPepProvisorio();" class="form-control step_1_validar" >
																                  <option value="" disabled selected>[-Selecione-]</option>
																                  <tagsContrato:listaStatusContrato/>
												                          </select>
																     </div>
																     
			                                                         <div class="form-group form-default form-static-label col-md-2 mb-6">
																	      <span class="font-weight-bold font-italic" style="color: #708090">Motivo Rascunho</span>
																	      <input style="color: #000000" type="text" name="motivoRascunho" id="motivoRascunho" disabled="disabled" maxlength="500" class="form-control"  placeholder="Informe o Motivo do Rascunho" value=""> 
																	 </div>

																     <div class="form-group form-default form-static-label col-md-2 mb-3">
																	      <span class="font-weight-bold font-italic" style="color: #708090">Tipo Rascunho</span>
																	      <select style="color: #000000" name="id_rascunho" id="id_rascunho" disabled="disabled" class="form-control" >
																	              <option value="" disabled selected>[-Selecione-]</option>
																	              <tagsContrato:listaTipoRascunho />
																	      </select> 
																     </div>
																     
																     <div class="form-group form-default form-static-label col-md-2 mb-3">
																	      <span class="font-weight-bold font-italic" style="color: #708090">Nuvem</span>
																	      <select style="color: #000000" name="id_nuvem" id="id_nuvem" class="form-control step_1_validar"  onchange="montaSelectSite( ); setnuvemRecurso();">
																	              <option value="" disabled selected>[-Selecione-]</option>
																	              <tagsContrato:listaNuvemSite />
																	      </select> 
																     </div>

																     <!-- Campo Site -->
																     <div class="form-group form-default form-static-label col-md-2 mb-3">
																          <span class="font-weight-bold font-italic" style="color: #708090" >Site</span>
																	      <select style="color: #000000" name="id_site" id="id_site" class="form-control step_1_validar" >
																	          <option value="" disabled selected>[-Selecione-]</option>																		    
																	      </select> 
																     </div>
																     
																     <!-- Campo Fase do Contrato -->
																     <div class="form-group form-default form-static-label col-md-2 mb-3">
																          <span class="font-weight-bold font-italic" style="color: #708090">Fase Contrato</span>
															              <select style="color: #000000" name="id_fase_contrato" id="id_fase_contrato" class="form-control step_1_validar" >
																                  <option value="" disabled selected>[-Selecione-]</option>
																                  <tagsContrato:listaFaseContrato />
																	      </select> <!-- <label class="float-label">Fase Contrato</label> -->
																     </div>

															    </div>
														        <hr>  
															    <br>
															
															    <div class="form-row">
															    
																    <!-- Campo Comercial -->
																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Comercial</span>
															             <select style="color: #000000" name="id_comercial" id="id_comercial" class="form-control step_1_validar" >
																               <option value="" disabled selected>[-Selecione-]</option>
																               <tagsContrato:listaComercial />
																	     </select> <!-- <label class="float-label">Fase Contrato</label> -->
																    </div>
																     
																    <!-- Campo Suporte B1 -->
																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Suporte B1</span>
															             <select style="color: #000000" name="id_suporte_b1" id="id_suporte_b1" class="form-control step_1_validar" >
															                   <option value="" disabled selected>[-Selecione-]</option>
																               <tagsContrato:listaSuporteB1 />
																         </select> <!-- <label class="float-label">Fase Contrato</label> -->
																    </div>

																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Ciclo Updadate</span>
																	     <select style="color: #000000" name="id_ciclo_updadate" id="id_ciclo_updadate" class="form-control step_1_validar" >
																		       <option value="" disabled selected>[-Selecione-]</option>
																		       <tagsContrato:listaCicloUpdate />
																	     </select> <!-- <label class="float-label">Ciclo Updadate</label> -->
																    </div>

																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Serviço Contratado</span>
																	     <select style="color: #000000" name="id_servico_contratado" id="id_servico_contratado" class="form-control step_1_validar" >
																		         <option value="" disabled selected>[-Selecione-]</option>
																		         <tagsContrato:listaServicoContratado />
																	     </select> 
																    </div>

                                                                    <!-- Campo Termo Admin -->
																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Termo Administrador</span>
																	     <select style="color: #000000" name="termo_admin" id="termo_admin" class="form-control" >
																	             <option value="" disabled selected>[-Selecione-]</option>
																	             <option value="1"<% 
																			             ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
																	                     if ( modelContrato != null ) {
																				             if( modelContrato.getTermo_admin() == 1  ){
																					             out.print(" ");
																					             out.print("selected=\"selected\"");
																					             out.print(" ");
																				             }
																	                     }
																	             %>>Sim</option>
																	             <option value="2"<% 
																			             modelContrato = (ModelContrato) request.getAttribute("modelContrato");
																	                     if ( modelContrato != null ) {
																				              if( modelContrato.getTermo_admin() == 2 ){
																					              out.print(" ");
																					              out.print("selected=\"selected\"");
																					              out.print(" ");
																				              }
																	                     }
																	             %>>Não</option>
																	     </select> 
																    </div>
																
																    <!-- Campo Termo Download -->
																    <div class="form-group form-default form-static-label col-md-2 mb-3">
																         <span class="font-weight-bold font-italic" style="color: #708090">Termo Download</span>
																	     <select style="color: #000000" name="termo_download" id="termo_download" class="form-control" >
																	             <option value="" disabled selected>[-Selecione-]</option>
																	             <option value="1"<% 
																			             modelContrato = (ModelContrato) request.getAttribute("modelContrato");
																	                     if ( modelContrato != null ) {
																				              if( modelContrato.getTermo_download() == 1 ){
																					              out.print(" ");
																					              out.print("selected=\"selected\"");
																					              out.print(" ");
																				              }
																	                     }
																	             %>>Sim</option>
																	             <option value="2"<% 
																			             modelContrato = (ModelContrato) request.getAttribute("modelContrato");
																	                     if ( modelContrato != null ) {
																				              if( modelContrato.getTermo_download() == 2 ){
																					              out.print(" ");
																					              out.print("selected=\"selected\"");
																					              out.print(" ");
																				              }
																	                     }
																	             %>>Não</option>
																	     </select>
																    </div>
															    </div>
															    <hr>  
															    <br>
															    
															    <div class="form-row">
															         <div class="form-group form-default form-static-label col-md-1 mb-12">
															              <div class="card-block icon-btn ce">															 
																		       <div class="row d-flex justify-content-center">
																		            <div class="col-sm">
																		                 <input type="file" name="arqContratoPDF" id="arqContratoPDF" accept="application/pdf" >
                                                                                         <label for="arqContratoPDF" class="btn waves-effect waves-dark btn-success btn-outline-success btn-icon"  data-toggle="tooltip" data-placement="top" title="Upload do contrato!">
                                                                                               <i class="fa fa-upload" aria-hidden="true" ></i>
                                                                                         </label>  
                                                                                    </div>
                                                                               </div>
                                                                          </div>
															         </div>
															         
															         <!-- Campo PEP 
			                                                         <div class="form-group form-default form-static-label col-md-4 mb-6">
																	      <span class="font-weight-bold font-italic" style="color: #708090">PEP</span>
																	      <input style="color: #000000" type="text" name="pep" id="pep" maxlength="30" class="form-control step_1_validar"  placeholder="Informe o PEP do cliente" value=""> 
																	 </div>  
																	 
			                                                         <div class="form-group form-default form-static-label col-md-4 mb-6">
																	      
																	      <label for="pep" class="col-md-2 col-form-label font-weight-bold font-italic" style="color: #708090">PEP</label>
																	      <div class="col-sm-10">
																	          <input type="text" name="pep" id="pep" class="form-control step_1_validar" placeholder="Digite o PEP do cliente" onkeyup="carregaPEPSap( this.value );" autocomplete="off" value="">
																	          <span id="resultado-pesquisa"></span>
																	      </div>
																     </div>	 
																	 -->
																	 <input type="hidden"  name="pepEscondido" id="pepEscondido" readonly="readonly" value="${modelContrato.pep}">
			                                                         <div class="form-group form-default form-static-label col-md-4 mb-6">

																          <label for="pep" class="col-md-2 col-form-label font-weight-bold font-italic" style="color: #708090">PEP</label>
																	     <select style="color: #000000" name="pep" id="pep" class="form-control step_1_validar" >
																		         <option value="" disabled selected>[-Selecione-]</option>
																	     </select> 
																     </div>	 

			                                                         <!-- Campo ID HubSpot -->
			                                                         <div class="form-group form-default form-static-label col-md-4 mb-6">
																	      <span class="font-weight-bold font-italic" style="color: #708090">ID HubSpot</span>
																	      <input style="color: #000000" type="text" name="id_hub_spot" id="id_hub_spot" maxlength="20" class="form-control" placeholder="Informe o ID HubSpot do cliente" value=""> 
																	 </div>
																     <!-- Campo Nuvem -->
																     <div class="form-group form-default form-static-label col-md-3 mb-6">
																          <span class="font-weight-bold font-italic" style="color: #708090">Quantidade Usuário</span>
		                                                                  <input style="color: #000000" type="number" name="qty_usuario_contratada" id="qty_usuario_contratada"  class="form-control step_1_validar"  placeholder="Informe a quantdade" value="">
																     </div>
															    </div>
															    <div class="nomeArquivo"></div>
															    <hr>  
															    <br>
                                                                <div class="form-group form-default form-static-label mb-6">
                                                                     <span class="font-weight-bold font-italic" style="color: #708090">Observação</span>
																     <textarea style="color: #000000" class="form-control" id="observacao" name="observacao" placeholder="Observação" rows="100" >${modelContrato.observacao}</textarea>
															    </div>
                                                           </div> <!-- Fim etapa 1 -->
                                                           
                                                           <!-- ##################################################################################### -->
                                                           <!--     Etapa para realizar o cadastro das informacoes relativa a vigencia do contrato!   --> 
                                                           <!-- ##################################################################################### -->
                                                           <div id="step_2" class="step">
                                                                <div class="row">
																     <div class="col-sm-12">
																	      <div class="card">
																		       <div class="card-block">
																		            <!-- Informacoes do Tempo de Contrato -->
																					<div class="form-row">
																						<!-- Campo Tempo Contrato -->
																						<div class="form-group form-default form-static-label col-md-4 mb-3">
																						    <span class="font-weight-bold font-italic" style="color: #708090">Tempo Contrato</span>
																							<select style="color: #000000" name="selectTempoContrato" id="selectTempoContrato" onchange="calculaDataFinalVigencia();" class="form-control step_2_validar" >
																								<option value="" disabled selected>[-Selecione-]</option>
																								    <tagsContrato:listaTempoContrato/>
																							</select> <!--  <label class="float-label">Tempo Contrato</label> -->
																						</div>
									                                                    <!-- Campo Data Início --> 
									                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
									                                                        <span class="font-weight-bold font-italic" style="color: #708090">Data Início</span>
																							<input style="color: #000000" type="text" name="dt_inicio" id="dt_inicio" class="form-control step_2_validar" onchange="calculaDataFinalVigencia();"  placeholder="Data início do contrato" value=""> 
																							<!-- <label class="float-label">Data Início</label> -->
									                                                    </div>
																					
																						<!-- Campo Data Final -->
																						<div class="form-group form-default form-static-label col-md-4 mb-6">
																						     <span class="font-weight-bold font-italic" style="color: #708090">Data Final</span>
								                                                             <input style="color: #000000" type="text" name="dt_final" id="dt_final"  class="form-control step_2_validar"  placeholder="Data final do contrato" value="">
																						</div>
																					</div>
																				    <hr>  
																					<br>
																					<div class="form-row">
																					     <div class="form-group form-default form-static-label col-md-12 mb-12">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Observação Vigência</span>
																							  <textarea style="color: #000000" class="form-control" id="observacaoVigencia" name="observacaoVigencia" placeholder="Observação" rows="100" >${modelContrato.observacao_vigencia}</textarea>
																						 </div>
																					</div>
												                               </div>
												                          </div>
												                     </div>
												                </div>
                                                           
                                                           </div> <!-- Fim etapa 2 -->
                                                           
                                                           <!-- ##################################################################################### -->
                                                           <!--                       Etapa para realizar cadastro de recursos                        -->
                                                           <!-- ##################################################################################### --> 
                                                           <div id="step_3" class="step">
                                                                
                                                                <div class="row">
																     <div class="col-sm-12">
																	      <div class="card">
																		       <div class="card-block">
																		            <!-- Informacoes do Tempo de Contrato -->
																					<div class="form-row">
																					     
																					     <!-- Campo Status -->
									                                                     <div class="form-group form-default form-static-label col-md-3 mb-6">
										                                                      <span class="font-weight-bold font-italic" style="color: #708090">Status</span>
										                                                      <select style="color: #000000" name=id_status_recurso id="id_status_recurso" class="form-control step_3_validar" >
											                                                          <option value="" disabled selected>[-Selecione-]</option>
											                                                          <tagsContrato:listaStatusRecurso />
										                                                       </select> 
									                                                     </div>
	
									                                                     <!-- Campo Retenção Backup -->
									                                                     <div class="form-group form-default form-static-label col-md-3 mb-6">
										                                                      <span class="font-weight-bold font-italic" style="color: #708090">Retenção Backup</span>
										                                                      <select style="color: #000000" name="id_retencao_backup" id="id_retencao_backup" class="form-control step_3_validar" >
											                                                          <option value="" disabled selected>[-Selecione-]</option>
											                                                          <tagsContrato:listaRetencaoBackup />    
										                                                       </select> 
									                                                     </div>
								                                                         <!-- Campo Tipo Disco -->
									                                                     <div class="form-group form-default form-static-label col-md-3 mb-6">
										                                                      <span class="font-weight-bold font-italic" style="color: #708090">Tipo Disco</span>
																							  <select style="color: #000000" name="id_tipo_disco" id="id_tipo_disco" class="form-control step_3_validar"  >
											                                                          <option value="" disabled selected>[-Selecione-]</option>
											                                                          <tagsContrato:listaTipoDisco />
										                                                      </select> 
									                                                     </div>
																					     <!-- Campo Sistema Operacional -->
																						 <div class="form-group form-default form-static-label col-md-3 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Sistema Operacional</span>
																							  <select style="color: #000000" name="id_so" id="id_so" class="form-control step_3_validar" >
																								      <option value="" disabled selected>[-Selecione-]</option>
																								      <tagsContrato:listaSistemaOperacional /> 
																							  </select> 
																						 </div>
																				    </div>
																					<hr>  
								                                                    <br>
																					     
																					<div class="form-row">
																				         <!-- Campo Ambiente -->
																						 <div class="form-group form-default form-static-label col-md-2 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Ambiente</span>
																							  <select style="color: #000000" name="id_ambiente" id="id_ambiente" class="form-control step_3_validar" >
																							          <option value="" disabled selected>[-Selecione-]</option>
																								      <tagsContrato:listaAmbiente /> 
																							  </select> 
																						 </div>
														
																						 <!-- Campo Tipo Serviçoo -->
																						 <div class="form-group form-default form-static-label col-md-2 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Tipo Serviço</span>
																							  <select style="color: #000000" name="id_tipo_servico" id="id_tipo_servico" class="form-control step_3_validar" >
																								      <option value="" disabled selected>[-Selecione-]</option>
																								      <tagsContrato:listaTipoServico />
																							  </select> 
																						 </div>
																				          
																				         <div class="form-group form-default form-static-label col-md-3 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Hostname</span>
																							  <input style="color: #000000" type="text" name="hostnameModalRecurso" id="hostnameModalRecurso" class="form-control" placeholder="Hostname" > 
																						 </div>
																				         <!-- Campo Ambiente -->
																						 <div class="form-group form-default form-static-label col-md-3 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Primary IP</span>
																							  <input style="color: #000000" type="text" name="primaryIPModalRecurso" id="primaryIPModalRecurso" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="Primary IP" >  
																						 </div>
														
																						 <!-- Campo Tamanho Disco -->
																						 <div class="form-group form-default form-static-label col-md-2 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Tamanho Disco</span>
																							  <input style="color: #000000" type="text" name="tamanhoDiscoModalRecurso" id="tamanhoDiscoModalRecurso" class="form-control only-number" placeholder="Tamanho Disco" > 
																						 </div>
																					</div>
																				    <hr>  
																					<br>
																					     
																				    <div class="form-row">
																				         <!-- Campo Host Vcenter -->
																						 <div class="form-group form-default form-static-label col-md-4 mb-6">
																					  	      <span class="font-weight-bold font-italic" style="color: #708090">Host Vcenter</span>
																							  <input style="color: #000000" type="text" name="hostVcenterModalRecurso" id="hostVcenterModalRecurso" class="form-control" placeholder="Host Vcenter" > 
																						 </div>
														
																						 <!-- Campo EIP Vcenter -->
																						 <div class="form-group form-default form-static-label col-md-4 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">EIP Vcenter</span>
																							  <input style="color: #000000" type="text" name="eipVcenterModalRecurso" id="eipVcenterModalRecurso" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="EIP Vcenter" > 
																						 </div>
																						
																						 <!-- Campo TAG Vcenter -->
																						 <div class="form-group form-default form-static-label col-md-4 mb-6">
													                                          <span class="font-weight-bold font-italic" style="color: #708090">TAG Vcenter</span>
													                                          <input style="color: #000000" type="text" name="tag_vcenter" id="tag_vcenter" class="form-control"  placeholder="TAG da Instancia no Vcenter" >
																						 </div>
																						  
																						 <!-- Campo ID Contrato -->
																						 <div class="form-group form-default form-static-label col-md-6 mb-6">
																					 	      <span class="font-weight-bold font-italic" style="color: #708090">Nuvem</span>
																							  <input style="color: #000000" type="text" name="nuvemRecurso" id="nuvemRecurso" style="background: #000080; color: white" class="form-control step_3_validar" readonly="readonly" >
																						 </div>
														
																						 <!-- Campo Família Flavors -->
																						 <div class="form-group form-default form-static-label col-md-6 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">Família Flavors</span>
																							  <select style="color: #000000" name="selectFamiliaFlavors" id="selectFamiliaFlavors" class="form-control step_3_validar" >
																							  </select> 
																						 </div>
																				    </div>
																				    <hr>  
																					<br>
																					     
																					<div class="form-row">
														                                 <!-- Campo CPU --> 
														                                 <div class="form-group form-default form-static-label col-md-4 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">CPU</span>
																							  <input style="color: #000000" type="text" name="cpu" id="cpu" class="form-control" readonly="readonly" placeholder="CPU" > 
														                                 </div>
														                                               
														                                 <!-- Campo RAM --> 
														                                 <div class="form-group form-default form-static-label col-md-4 mb-6">
																							  <span class="font-weight-bold font-italic" style="color: #708090">RAM</span>
																							  <input style="color: #000000" type="text" name="ram" id="ram" class="form-control" readonly="readonly" placeholder="RAM" > 
														                                 </div>
														                                
																						 <!-- Campo Valor Família Flavors -->
																						 <div class="form-group form-default form-static-label col-md-4 mb-6">
													                                          <span class="font-weight-bold font-italic" style="color: #708090">Valor Família Flavors</span>
													                                          <input style="color: #000000" type="text" name="valor" id="valor" class="form-control" placeholder="Valor" >
																						 </div>
																					</div>
														
																				    <hr>  
																					<br>
															                        <div class="form-row">
																					     <div class="form-group form-default form-static-label col-md-12 mb-12">
																						 	  <span class="font-weight-bold font-italic" style="color: #708090">Observações</span>
																							  <textarea style="color: #000000" class="form-control" id="obs" name="obs" placeholder="Observações" rows="5" ></textarea>
																						 </div>
																					</div>
																					<hr>  
																					<br>
																					<button type="button" class="btn waves-effect waves-light btn-outline-success float-right pequeno" name="btAddRecurso" id="btAddRecurso" onclick="contrato.salvarRecuso();">Add</button>
																			   </div>
																		  </div>
																	 </div>
																</div> 
																
																<!-- Tabela com as informacoes do Recursos a serem cadastrasdos -->                       
																<div class="row">
																	<div class="col-sm-12">
																		<!-- Basic Form Inputs card start -->
																		<div class="card">
																			<div class="card-block">
													
																				<div style="height: 300px; overflow: scroll;">
																					<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutRecurso">
																						  <thead>
																						    <tr>
																						      <th scope="col">Serviço   </th>
																						      <th scope="col">Status    </th>
																						      <th scope="col">Hotname   </th>
																						      <th scope="col">Primary IP</th>
																						      <th scope="col">Ambiente  </th>
																						      <th scope="col">Familia   </th>
																						      <th scope="col">Nuvem     </th>
																						      <th scope="col">Site      </th>
																						      <th scope="col">Editar    </th>
																						      <th scope="col">Delete    </th>
																						    </tr>
																						  </thead>
																						<tbody id="Tbody">
													
																						</tbody>
																					</table>
																				</div>
													
																			</div>
																		</div>
																	</div>
																</div>

                                                           </div> <!-- Fim etapa 3 -->
                                                        
                                                           <!-- ##################################################################################### -->
                                                           <!--                      Etapa para realizar cadastro de produtos                         -->
                                                           <!-- ##################################################################################### --> 
                                                           <div id="step_4" class="step">
                                                                <div class="row">
																     <div class="col-sm-12">
																	      <div class="card">
																		       <div class="card-block">
																		             <div class="form-row">
																		      		      <!-- Campo ID Produto -->
																					      <div class="form-group form-default form-static-label col-md-12 mb-12">
																						       <select style="color: #000000" name=selectProduto id="selectProduto" class="form-control" >
																							          <option value="" disabled selected>Selecione Produto</option>
																						       </select> 
																					      </div>
																					 </div>
																					 <hr>  
																					 <br>
																					 <div class="form-row">
																						  <!-- Campo Valor Produto -->
																						  <div class="form-group form-default form-static-label col-md-6 mb-6">
																						       <span class="font-weight-bold font-italic" style="color: #708090">Valor Produto</span>
																							   <input style="color: #000000" type="text" name="vlrProduto" id="vlrProduto" class="form-control step_4_validar" value="">
																						  </div>
																						  <!-- Campo observação -->
																						  <div class="form-group form-default form-static-label col-md-6 mb-6">
																						       <span class="font-weight-bold font-italic" style="color: #708090">Observação</span>
																							   <input style="color: #000000" type="text" name="obsProduto" id="obsProduto" class="form-control"  value="">
																						  </div>
																					 </div>
																					 <hr>  
																					 <br>
																					 <div class="form-row">
															                              <!-- Campo Quantidade --> 
															                              <div class="form-group form-default form-static-label col-md-4 mb-6">
																						      <span class="font-weight-bold font-italic" style="color: #708090">Quantidade</span>
																						      <input style="color: #000000" type="text" name="idQty" id="idQty" class="form-control only-number step_4_validar"  placeholder="Quantidade" disabled value=""> 
															                              </div>
															                              <!-- Campo Valor Unitário --> 
															                              <div class="form-group form-default form-static-label col-md-4 mb-6">
																						      <span class="font-weight-bold font-italic" style="color: #708090">Valor Unitário</span>
																						      <input style="color: #000000" type="text" name="vlrUnit" id="vlrUnit" class="form-control step_4_validar" placeholder="Valor Unitário" disabled value=""> 
																						      <span class="form-bar"></span> 
															                              </div>
																					      <!-- Campo Valor Famiia Flavors -->
																					      <div class="form-group form-default form-static-label col-md-4 mb-6">
															                                  <span class="font-weight-bold font-italic" style="color: #708090">Valor Total</span>
															                                  <input style="color: #000000" type="text" name="vltTotal" id="vltTotal" class="form-control step_4_validar" placeholder="Valor Total" disabled value="">
															                                  <span class="form-bar"></span>
																					      </div>																					 
																					 </div>
																					 <hr>  
																					 <br>
																					 <button type="button" class="btn waves-effect waves-light btn-outline-success float-right pequeno" name="btAddProduto" id="btAddProduto" onclick="contrato.salvarProdutos();">Add</button>
																               </div>
																		  </div>
																     </div>
																</div>

																<!-- Tabela com as informacoes dos Produtos a serem cadastrasdos -->                       
																<div class="row">
																	<div class="col-sm-12">
																		<!-- Basic Form Inputs card start -->
																		<div class="card">
																			<div class="card-block">
																				<div style="height: 300px; overflow: scroll;">
																					<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaProduto">
																						  <thead>
																						    <tr>
																						      <th scope="col">Produto       </th>
																						      <th scope="col">Quantidade    </th>
																						      <th scope="col">Valor Unitário</th>
																						      <th scope="col">Valor Total   </th>
																						      <th scope="col">Editar        </th>
																						      <th scope="col">Delete        </th>
																						    </tr>
																						  </thead>
																						<tbody id="TbodyProduto">
													
																						</tbody>
																					</table>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>

                                                           </div> <!-- Fim etapa 4 -->
                                                           
                                                           <!-- ##################################################################################### -->
                                                           <!--              Etapa para realizar Analise das informacoes contratadas                  -->
                                                           <!-- ##################################################################################### --> 
                                                           <div id="step_5" class="step">
                         
																<div class="card">
																  <h5 class="card-header border-primary">Contrato</h5>
																  <div class="card-body ">
																       <h5 class="card-title">Informações do Contrato</h5><hr>
                                                                       <div style="height: 80px; overflow: scroll;">
																			<table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
																			  <thead >
																			    <tr >
																			      <th class="font-weight-bold font-italic " style="color: #708090">ID Cliente         </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Cliente            </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Moeda              </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Valor Contrato     </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Valor Cotação      </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Valor Convertido   </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Status             </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Motivo Rascunho    </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Tipo Rascunho      </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Nuvem              </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Site               </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Fase Contrato      </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Comercial          </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Suporte B1         </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Ciclo Updadate     </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Serviço Contratado </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Termo Administrador</th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Termo Download     </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Contrato(Arq)      </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">PEP                </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">ID HubSpot         </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Quantidade Usuário </th>
																			      <th class="font-weight-bold font-italic " style="color: #708090">Observação         </th>
																			    </tr>
																			  </thead>
																			  <tbody id="TbodyResumoContratoCad">
																			  
	 																		  </tbody>
																			</table>
																	   </div>
 																  </div>
																</div>

																<div class="card">
																  <h5 class="card-header border-success">Vigência</h5>
																  <div class="card-body">
																    <h5 class="card-title">Informações sobre a Vigência do Contrato</h5><hr>
																		<div class="form-row">
																		     
						                                                     <div class="form-group form-default form-static-label col-md-2" style="max-height:0px;">
							                                                      <p class="font-weight-bold font-italic " style="color: #708090">Tempo Contrato</p>
						                                                     </div>
						                                                     <div class="form-group form-default form-static-label col-md-2" style="max-height:0px;">
							                                                      <p class="font-weight-bold font-italic " style="color: #708090">Data Início</p>
						                                                     </div>
																			 <div class="form-group form-default form-static-label col-md-2" style="max-height:0px;">
																				  <p class="font-weight-bold font-italic " style="color: #708090">Data Final</p>
																			 </div>
						                                                     <div class="form-group form-default form-static-label col-md-6" style="max-height:0px;">
						                                                          <p class="font-weight-bold font-italic " style="color: #708090">Observação</p>
							                                                 </div>
							                                                 <!---------------------------------------------->
							                                                 <!-- Demonstrativo as informacoes contratadas -->
							                                                 <!---------------------------------------------->
						                                                     <div class="form-group form-default form-static-label col-md-2" style="max-height:0px;">
							                                                      <p id="tempoContratoShow"></p>
						                                                     </div>
						                                                     <div class="form-group form-default form-static-label col-md-2" style="max-height:20px;">
							                                                      <p id="dataInicioShow" ></p>
						                                                     </div>
																			 <div class="form-group form-default form-static-label col-md-2" style="max-height:20px;">
																				  <p id="dataFinalShow" ></p>
																			 </div>
						                                                     <div class="form-group form-default form-static-label col-md-6" style="max-height:20px;">
							                                                      <p id="observacaoVigenciaShow"></p>
							                                                 </div>
																	    </div>
																  </div>
																</div>

																  <div class="card">
																      <h5 class="card-header border-info">Recursos</h5>
															          <div class="card-body">
														                   <h5 class="card-title">Lista de Recursos contratados</h5><hr>
                                                                            <div style="height: 150px; overflow: scroll;">
																				<table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
																				  <thead >
																				    <tr >
																				      <th class="font-weight-bold font-italic " style="color: #708090">Status         </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Retenção Backup</th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Tipo Disco     </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">SO             </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Ambiente       </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Tipo Serviço   </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Hostname       </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Primary IP     </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Tamanho Disco  </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Host Vcenter   </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Eip Vcenter    </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Tag Vcenter    </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Nuvem          </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Site           </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Familia Flavors</th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">CPU            </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">RAM            </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Valor          </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Obs.           </th>
																				    </tr>
																				  </thead>
																				  <tbody id="TbodyResumoRecurso">
																				  
	  																			  </tbody>
																				</table>
																			</div>
																	  </div>
																  </div>

																  <div class="card">
													                   <h5 class="card-header border-danger">Produtos</h5>
													                   <div class="card-body">
																	        <h5 class="card-title">Lista de Serviços contratados</h5><hr>
                                                                            <div style="height: 150px; overflow: scroll;">
																				<table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
																				  <thead >
																				    <tr >
																				      <th class="font-weight-bold font-italic " style="color: #708090">Produto       </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Qtde.         </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Valor Unitário</th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Valor Total   </th>
																				      <th class="font-weight-bold font-italic " style="color: #708090">Obs.          </th>
																				    </tr>
																				  </thead>
																				  <tbody id="TbodyResumoProduto">
																				  
	  																			  </tbody>
																				</table>
																			</div>
																	   </div>
																  </div>
																  <!--  
																  <button type="button" class="btn waves-effect waves-light btn-outline-success float-right pequeno" name="btSalvarContrato" id="btSalvarContrato" onclick="contrato.gravarContrato();">Salvar</button>
																  -->
																  <button type="submit" class="btn waves-effect waves-light btn-outline-success float-right pequeno" name="btSalvarContrato" id="btSalvarContrato">Salvar</button>
																  
																  <input type="hidden" name="conteudoContrato" id="conteudoContrato" readonly="readonly" value="">
																  <input type="hidden" name="conteudoVigencia" id="conteudoVigencia" readonly="readonly" value="">
																  <input type="hidden" name="conteudoRecurso" id="conteudoRecurso"   readonly="readonly" value="">
																  <input type="hidden" name="conteudoProduto" id="conteudoProduto"   readonly="readonly" value="">
																                           
                                                           </div> <!-- Fim etapa 5 -->
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                         
                                        <div class="row">
                                            <div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<div class="row">
														     <div class="col-lg-6">
														        <button type="button" class="btn btn-block btn-outline-info" id="prev">Anterior</button>
														     </div>
														     <div class="col-lg-6">
														        <button type="button" class="btn btn-block btn-outline-info" id="next">Próximo</button>
														     </div>
														</div>
												    </div>
												</div>
										    </div>
									    </div>
                                        
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
	<!-- 
	******************************************************************************************************
	*
	*                                  Modal Buscar Cliente        
	*
	******************************************************************************************************
	-->
	<div class="modal t-modal primary" id="modalClienteContrato" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisar Cliente</h5>
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
                          <p class="text-primary h6 font-weight-normal font-italic text-center">Pesquisa por: *Nome Cliente *Alias * CNPJ.</p> 

						</div>
					</div>
				</div>
			</div>
			<!-- --------------------------------------------- -->
			<!-- Seleciona se eh um Novo Contrato ou Renovacao -->
			<!-- --------------------------------------------- -->
			<div class="card" >
			   <div class="card-block">
				 <div class="row" >
                   <div class="col-sm-12">
                        <div class="form-group form-check col py-1 px-md-3" >
                             <input type="checkbox" class="form-check-input" id="cbRenovacaoContrato" onchange="atualizaPaginacao();" >
                             <label class="form-check-label" for="cbRenovacaoContrato">Renovação de Contrato</label>
                        </div>
                   </div>

			     </div>
		       </div>
	        </div>
	        
			<!-- --------------------------------------------- -->
			<!--                                               -->
			<!-- --------------------------------------------- -->
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div class="input-group mb-3">
								<input type="text" id="nomeBuscaCliente" class="form-control" placeholder="Digite aqui..." aria-label="Nome Cliente" aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn btn-outline-info" type="button" onclick="tipoPesquisaCadastroRenovacaoNovo();">Buscar</button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			
			<!-- --------------------------------------------- -->
			<!--                                               -->
			<!-- --------------------------------------------- -->
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div style="height: 300px; overflow: scroll;">
								<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutado">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col">ID Cliente</th>
											<th scope="col">Nome Cliente</th>
											<th scope="col">Selecionar</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
			

            <nav id="navegacaoPag" aria-label="Navegação de página">
             <!--  
                <ul class="pagination">
                   <li class="page-item"><a class="page-link" href="#">1</a></li> 
                </ul>
              --> 
            </nav>
			<br>			   
            <samp id="totalResutados"></samp>

		  </div>

	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    
    <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>
<!--      
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
-->    
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/cadContrato.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/cadContratoClass.js"></script>
    
    <script src="<%= request.getContextPath() %>/assets/js/select2/select2.min.js"></script>
 
</body>

</html>
