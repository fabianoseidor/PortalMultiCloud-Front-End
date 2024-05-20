<%@page import="br.com.portal.model.ModelContrato"%>
<%@page import="br.com.portal.model.ModelAitivoRecursoModal"%>
<%@page import="br.com.portal.model.ModelListAditivoProduto"%>
<%@page import="br.com.portal.model.ModelHistoricoUpgrade"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

    
<!DOCTYPE html>
<html lang="en"><!-- ico font -->
<link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/select2/select2.min.css" type="text/css" media="all"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/select2/select2-bootstrap-5-theme.min.css" type="text/css" media="all"/>

<jsp:include page="head.jsp"></jsp:include>
   <style>
       .mesmo-tamanho-botao {
           width: 20%;
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
            background-color: #708090;
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

   </style>

  <body>

  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <!-- Chamada para o Menu de Barras -->
          <jsp:include page="navbarheader.jsp"></jsp:include>
          
          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <!-- Chamada para o Navbar Maim Menu -->
                  <jsp:include page="navbarmainmenu.jsp"></jsp:include>
                  
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                        <jsp:include page="pageheader.jsp"></jsp:include>
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
														<h4 class="sub-title">Manutenção de Contrato</h4>
														<form class="form-material" action="<%= request.getContextPath() %>/ServletContratoController" method="post" id="formContrato" enctype="multipart/form-data">

															<div class="form-row">
															    <input type="hidden"  name="tipoUserAdmin" id="tipoUserAdmin" readonly="readonly" value="${useradmin}">
																<!-- Campo ID Contrato -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																	<span class="text-info font-weight-bold font-italic">ID Contrato</span>
																	<input class="form-control text-info font-italic" type="text" name="id_contrato" id="id_contrato" readonly="readonly" value="${modelContrato.id_contrato}">
																	<!--  <label class="float-label">ID Contrato</label> -->
																</div>

																<!-- Data Cadastro -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
																	<input class="form-control text-info font-italic" type="text" name="dt_criacao" id="dt_criacao" readonly="readonly" value="${modelContrato.dt_criacao}">
																	<!-- <label class="float-label">Data Cadastro</label> -->
																</div>
																
																<!-- Login Cadastro -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Login Cadastro</span>
																	<input class="form-control text-info font-italic" type="text" name="login_cadastro" id="login_cadastro" readonly="readonly" value="${modelContrato.login_cadastro}">
																	<!-- <label class="float-label">Data Cadastro</label> -->
																</div>
																																
															</div>
															<!-- Campo sera utilizado para pesquisa -->
															<div class="form-row">															
																<div class="form-group form-default form-static-label col-md-12 mb-6">
																    <input style="color: #000000" type="text" name="pequisaContrato" id="pequisaContrato" class="form-control" placeholder="Digite o ID do Contrato a ser pesquisado" >
																</div>
															</div>
<!-- 															
															<div class="form-row">
																
																<div class="form-group form-default form-static-label col-md-6 mb-6">
                                                                     <button type="button" class="btn btn-outline-primary btn-lg btn-block" onclick="pesquisaContradoID(  );">Pesquisa por ID</button>
																</div>

																<div class="input-group form-default form-static-label col-md-6 mb-6">
                                                                     <button type="button" class="btn btn-outline-success btn-lg btn-block" data-toggle="modal" data-target="#modalPesqContratoPEP">Pesquisa por PEP</button>
																</div>
															</div>
 -->
																
														    <hr>  
															<br>
															
															<div class="form-row">
																<!-- Campo ID Cliente -->
																<div class="form-group form-default form-static-label col-md-6 mb-6">
																	<input type="text" name="id_cliente" id="id_cliente" style="color:#000000; font-weight: bold;" class="form-control font-weight-bolder font-italic" required="required" placeholder="ID Cliente"readonly="readonly" value="${modelContrato.id_cliente}">
																</div>

																<div class="input-group form-default form-static-label col-md-6 mb-6">
																     <input type="text" name="nomeCliente" id="nomeCliente" required="required" style="color: #000000; margin-right: 15px; font-weight: bold;" readonly  class="form-control font-weight-bolder font-italic" placeholder="Cliente" aria-label="Clinte" aria-describedby="button-addon2" value="${modelContrato.nomeCliente}">
																     <div class="input-group-append">
																		<!-- Botao para acionar modal -->
																		<button type="button" class="btn btn-outline-info" data-toggle="modal" id="id_pesquisa_cliente" data-target="#modalClienteContrato">Pesquisar Contrato</button>															        
																     </div>
																</div>
															</div>
														    
														    <hr>  
															<br>

															<div class="form-row">
																<!-- Campo Moeda -->
																<div class="form-group form-default form-static-label col-md-3 mb-4">
																	<span  class="font-weight-bold font-italic" style="color: #708090">Moeda</span>
																	<select style="color: #000000" name="id_moeda" id="id_moeda" class="form-control" onchange="habilitaCotacao();" required="required" >
																		<option value="" disabled selected>Selecione Moeda</option>
																		   <tagsContrato:listaMoeda />
																	</select> 
																</div>

																<!-- Campo Site -->
																<div class="form-group form-default form-static-label col-md-3 mb-4">
																    <span  class="font-weight-bold font-italic" style="color: #708090" >Valor Corrente</span>
																	<input style="color: #000000" type="text" name="valor_total" id="valor_total" onblur="cauculoConversao();" class="form-control" required="required" placeholder="Valor do contrato" value="${modelContrato.valor_total}">
																</div>
																

																<!-- Campo Fase do Contrato -->
																<div class="form-group form-default form-static-label col-md-3 mb-4">
																	<span class="font-weight-bold font-italic" style="color: #708090">Valor Convertido</span>
																	<input style="color: #000000" type="text" name="valor_convertido" id="valor_convertido" disabled="disabled" class="form-control" placeholder="Valor do contrato" value="${modelContrato.valor_convertido}">
																</div>

																<!-- Campo Fase do Contrato -->
																<div class="form-group form-default form-static-label col-md-3 mb-4">
																	<span class="font-weight-bold font-italic" style="color: #708090">Cotação</span>
																	<input style="color: #000000" type="text" name="valor_Cotacao" id="valor_Cotacao" disabled="disabled" onblur="cauculoConversao();" class="form-control" placeholder="Valor do contrato" value="${modelContrato.cotacao_moeda}">
																</div>
															</div>
														    
														    <hr>  
															<br>

															<div class="form-row">
																<!-- Campo Nuvem -->
																<div class="form-group form-default form-static-label col-md-2 mb-4">
																	<span  class="font-weight-bold font-italic" style="color: #708090">Nuvem</span>
																	<select style="color: #000000" name="id_nuvem" id="id_nuvem" class="form-control" required="required" onchange="montaSelectSite();">
																		<option value="" disabled selected>Selecione Nuvem</option>
																		   <tagsContrato:listaNuvemSite />
																	</select> <!-- <label class="float-label">Nuvem</label> -->
																</div>

																<!-- Campo Site -->
																<div class="form-group form-default form-static-label col-md-2 mb-4">
																    <span  class="font-weight-bold font-italic" style="color: #708090" >Site</span>
																	<select style="color: #000000" name="id_site" id="id_site" class="form-control" required="required">
																		<option value="" disabled selected>Selecione Site</option>																		    
																	</select> 
																</div>

																<!-- Campo Fase do Contrato -->
																<div class="form-group form-default form-static-label col-md-2 mb-4">
																	<span class="font-weight-bold font-italic" style="color: #708090">Fase Contrato</span>
																	<select style="color: #000000" name="id_fase_contrato" id="id_fase_contrato" class="form-control" required="required">
																		<option value="" disabled selected>Selecione Fase Contrato</option>
																		   <tagsContrato:listaFaseContrato />
																	</select> <!-- <label class="float-label">Fase Contrato</label> -->
																</div>

																<!-- Campo Comercial -->
																<div class="form-group form-default form-static-label col-md-3 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Comercial</span>
															        <select style="color: #000000" name="id_comercial" id="id_comercial" class="form-control" required="required">
																        <option value="" disabled selected>[-Selecione-]</option>
																           <tagsContrato:listaComercial />
																	</select>
																</div>
																     
																<!-- Campo Suporte B1 -->
																<div class="form-group form-default form-static-label col-md-3 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Suporte B1</span>
															        <select style="color: #000000" name="id_suporte_b1" id="id_suporte_b1" class="form-control" required="required">
															            <option value="" disabled selected>[-Selecione-]</option>
																           <tagsContrato:listaSuporteB1 />
																    </select>
																</div>

															</div>

														    <hr>  
															<br>

															<div class="form-row">
																<!-- Campo Status do Contrato -->
																<div class="form-group form-default form-static-label col-md-2 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Status Contrato</span>
																	<select style="color: #000000" name="id_status_contrato" id="id_status_contrato" onchange="habilitaStatusMotivoRascunho(); validaStatusPepProvisorio();" class="form-control" required="required">
																		<option value="" disabled selected>Selecione Status Contrato</option>
																		    <tagsContrato:listaStatusContrato/>
																	</select> <!-- <label class="float-label">Status Contrato</label> -->
																</div>
																
	                                                            <div class="form-group form-default form-static-label col-md-3 mb-6">
															        <span class="font-weight-bold font-italic" style="color: #708090">Motivo Rascunho</span>
															        <input style="color: #000000" type="text" name="motivoRascunho" id="motivoRascunho" disabled="disabled" maxlength="500" class="form-control"  placeholder="Informe o Motivo do Rascunho" value="${modelContrato.motivoRascunho}"> 
															    </div>

														        <div class="form-group form-default form-static-label col-md-2 mb-3">
															        <span class="font-weight-bold font-italic" style="color: #708090">Tipo Rascunho</span>
															        <select style="color: #000000" name="id_rascunho" id="id_rascunho" disabled="disabled" class="form-control" >
															                <option value="" disabled selected>[-Selecione-]</option>
															                <tagsContrato:listaTipoRascunho />
															        </select> 
														        </div>
																
																
																<!-- Campo Porte Cliente -->
																<div class="form-group form-default form-static-label col-md-2 mb-6">
																    <span class="font-weight-bold font-italic" style="color: #708090">Ciclo Updadate</span>
																	<select style="color: #000000" name="id_ciclo_updadate" id="id_ciclo_updadate" class="form-control" required="required">
																		<option value="" disabled selected>Selecione Ciclo Updadate</option>
																		    <tagsContrato:listaCicloUpdate />
																	</select> <!-- <label class="float-label">Ciclo Updadate</label> -->
																</div>
                                                           
																<!-- Campo Porte Cliente -->
																<div class="form-group form-default form-static-label col-md-3 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Serviço Contratado</span>
																	<select style="color: #000000" name="id_servico_contratado" id="id_servico_contratado" class="form-control" required="required">
																		<option value="" disabled selected>Selecione Serviço Contratado</option>
																		    <tagsContrato:listaServicoContratado />
																	</select> <!-- <label class="float-label">ServiÃƒÂ§o Contratado</label>  -->
																</div>

															</div>
														    
														    <hr>  
															<br>
															<div class="form-row">
																<!-- Campo Termo Admin -->
																<div class="form-group form-default form-static-label col-md-6 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Termo Administrador</span>
																	<select style="color: #000000" name="termo_admin" id="termo_admin" class="form-control" required="required">
																	  <option value="" disabled selected>Selecione uma Opção</option>
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
																	</select> <!-- <label class="float-label">Termo Administrador</label> -->
																</div>
																
																<!-- Campo Termo Download -->
																<div class="form-group form-default form-static-label col-md-6 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Termo Download</span>
																	<select style="color: #000000" name="termo_download" id="termo_download" class="form-control" required="required">
																	  <option value="" disabled selected>Selecione uma Opção</option>
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
																	</select> <!-- <label class="float-label">Termo Download</label> --> 
																</div>
															</div>
														    
														    <hr>  
															<br>
															
                                                            <span class="font-weight-bold font-italic" style="color: #708090">Contrato PDF</span>
															<div class="form-row">
																 <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<div class="card-block icon-btn">															 
																		<div class="row">
																		  <div class="col-sm">
																		      <% 
													       		                  modelContrato           = (ModelContrato) request.getAttribute("modelContrato");
																		          String btInput          = "<input type=\"file\" name=\"arqContratoPDF\" id=\"arqContratoPDF\" accept=\"application/pdf\">";
																		          String msgIsNotContrato = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Upload do contrato!\"";
																		          String LabelIcoInicio   = "<label for=\"arqContratoPDF\" class=\"btn waves-effect waves-dark btn-success btn-outline-success btn-icon\" ";
																		          String LabelIcoFinal    = "> <i class=\"icofont icofont-upload-alt\"> </i></label>";
																		          String writeLabel       = "";
																		          if ( modelContrato != null ) {
																			          String msgIsContrato = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Arquivo anexado '" + modelContrato.getNomeaqrpdf() + "'!\"" ;
																					  if( modelContrato.getContratopdf() == null ){
																						  writeLabel = LabelIcoInicio + msgIsNotContrato + LabelIcoFinal;
																					  }else{
																						  writeLabel = LabelIcoInicio + msgIsContrato + LabelIcoFinal;
																					  }
																		          }	else{
																		        	  writeLabel = LabelIcoInicio + msgIsNotContrato + LabelIcoFinal;
																		          }
																		          out.println( btInput    );
																	        	  out.println( writeLabel );
																		          // System.out.println( writeLabel    );
																		      %>
																		      <!--   
																		      <input type="file" name="arqContratoPDF" id="arqContratoPDF" accept="application/pdf">  
																		      <label for="arqContratoPDF" class="btn waves-effect waves-dark btn-success btn-outline-success btn-icon" data-toggle="tooltip" data-placement="top" title="Upload do contrato"> <i class="icofont icofont-upload-alt"> </i></label> 
																		      -->
																		  </div>
																		  <div class="col-sm">
																		
																		      <% 
													       		                  modelContrato              = (ModelContrato) request.getAttribute("modelContrato");
																		    	  String disabledBT          = " disabled ";	  
																		          String msgDesContrato      = " data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Não existe contrato anexado para ser visualizado!\" ";
																		          String msgAbiContrato      = " data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Visualizar Contrato!\" ";
																		          String BtViewContratoI     = "<button type=\"button\" name=\"VisualizarContrato\" id=\"VisualizarContrato\" class=\"btn waves-effect waves-dark btn-primary btn-outline-primary btn-icon\" ";
																		          String BtViewContratoF     = "";
																		          String writeBtViewContrato = "";
																		          
																		          if ( modelContrato != null ) {
																		        	  String BtViewContratoJS = " onclick=\"mostrarContratoPdf( '" + modelContrato.getNomeaqrpdf() + "' );\" ";
																		        	  BtViewContratoF         = "value=\"" + modelContrato.getContratopdf() +"\"><i class=\"icofont icofont-eye-alt\"></i></button>";
																					  if( modelContrato.getContratopdf() == null ){
																						  writeBtViewContrato = BtViewContratoI + msgDesContrato + disabledBT + BtViewContratoF;
																					  }else{
																						  writeBtViewContrato = BtViewContratoI + BtViewContratoJS + msgAbiContrato + BtViewContratoF;
																					  }
																		          }	else{
																		        	  BtViewContratoF         = "><i class=\"icofont icofont-eye-alt\"></i></button>";
																		        	  writeBtViewContrato = BtViewContratoI + msgDesContrato + disabledBT + BtViewContratoF;
																		          }
																		          out.println( writeBtViewContrato );
																		          // System.out.println( writeBtViewContrato    );
																		      %>
																		       <!--    
																		       <button type="button" name="VisualizarContrato" id="VisualizarContrato" class="btn waves-effect waves-dark btn-primary btn-outline-primary btn-icon" onclick="mostrarContratoPdf( '${modelContrato.nomeaqrpdf}' );" data-toggle="tooltip" data-placement="bottom" title="Visualizar Contrato" value="${modelContrato.contratopdf}"><i class="icofont icofont-eye-alt"></i></button> 
                                                                               -->
																		  </div>
																		  <div class="col-sm">
																		  
																		      <% 
													       		                  modelContrato                  = (ModelContrato) request.getAttribute("modelContrato");
																		    	  disabledBT                     = " disabled ";	  
																		          String msgDownloadDesContrato  = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Não existe contrato anexado para realizar o Download!\" ";
																		          String msgDownloadAbiContrato  = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Download Contrato PDF\" ";
																		          String contexPath              = request.getContextPath();
																		          String BtLinkIDownloadI        = "";
																		          String BtLinkIDownloadF        = " </a> ";
																		          String BtDownloadContratoI     = " <button type=\"button\" name=\"downloadContrato\" id=\"downloadContrato\" class=\"btn waves-effect waves-dark btn-danger btn-outline-danger btn-icon\" ";
																		          String BtDownloadContratoF     = " ><i class=\"icofont icofont-download-alt\" ></i></button> ";
																		          String writeBtDownloadContrato = "";
																		          
																		          if ( modelContrato != null ) {
																		              
																		        	  BtLinkIDownloadI = "<a href=\"" + contexPath + "/ServletContratoController?acao=downloadContratoPdf&id=" + modelContrato.getId_contrato() + "\">";
																		              
																		        	  if( modelContrato.getContratopdf() == null ){
																		        		  writeBtDownloadContrato =  BtDownloadContratoI + msgDownloadDesContrato + disabledBT + BtDownloadContratoF;
																					  }else{
																						  writeBtDownloadContrato = BtLinkIDownloadI + BtDownloadContratoI + msgDownloadAbiContrato + BtDownloadContratoF + BtLinkIDownloadF;
																					  }
																		          }	else{
																		        	  writeBtDownloadContrato =  BtDownloadContratoI + msgDownloadDesContrato + disabledBT + BtDownloadContratoF;
																		          }
																		           out.println( writeBtDownloadContrato );
																		          // System.out.println( writeBtDownloadContrato    );
																		      %>
																		       <!--  
																		       <a href="< request.getContextPath() >/ServletContratoController?acao=downloadContratoPdf&id=${modelContrato.id_contrato}">
																		           <button type="button" class="btn waves-effect waves-dark btn-danger btn-outline-danger btn-icon" data-toggle="tooltip" data-placement="top" title="Download Contrato PDF" ><i class="icofont icofont-download-alt" ></i></button>
																		       </a>
																		       -->
																		  </div>
																		</div>
																	</div>
																</div>
			                                                    <!-- Campo PEP  
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">PEP</span>
																	<input style="color: #000000" type="text" name="pep" id="pep" maxlength="30" class="form-control" required="required" placeholder="Informe o PEP do cliente" value="${modelContrato.pep}"> 
			                                                    </div>
			                                                    -->
			                                                    <input type="hidden"  name="pepEscondido" id="pepEscondido" readonly="readonly" value="${modelContrato.pep}">
		                                                        <div class="form-group form-default form-static-label col-md-4 mb-6">
															         <label for="pep" class="col-md-2 col-form-label font-weight-bold font-italic" style="color: #708090">PEP</label>
																     <select style="color: #000000" name="pep" id="pep" class="form-control" >
																	       <!--    <option value="" disabled selected>[-Selecione-]</option> -->
																     </select> 
															    </div>	 
			                                                    
			                                                    
			                                                    <!-- Campo ID HubSpot -->
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">ID HubSpot</span>
																	<input style="color: #000000" type="text" name="id_hub_spot" id="id_hub_spot" maxlength="20" class="form-control" placeholder="Informe o ID HubSpot do cliente" value="${modelContrato.id_hub_spot}"> 
																	<!-- <label class="float-label">PEP</label> -->
			                                                    </div>
																<!-- Campo Nuvem -->
																<div class="form-group form-default form-static-label col-md-2 mb-6">
																     <span class="font-weight-bold font-italic" style="color: #708090">Quantdade Usuário Contratada</span>
		                                                             <input style="color: #000000" type="number" name="qty_usuario_contratada" id="qty_usuario_contratada" required="required" class="form-control"  placeholder="Informe a quantdade" value="${modelContrato.qty_usuario_contratada}">
																</div>
																<!-- Campo Fase do Contrato -->
																<!--  
																<div class="form-group form-default form-static-label col-md-3 mb-6">
																     <span class="font-weight-bold font-italic" style="color: #708090">Valor do Contrato</span>
		                                                             <input style="color: #708090" type="text" name="valor_total" id="valor_total" class="form-control" required="required" placeholder="Valor do contrato" value="${modelContrato.valor_total}">
																</div>
																-->
															</div>
														    <div class="nomeArquivo"></div>
														    <hr>  
															<br>

														    <div class="form-group form-default form-static-label mb-6">
																<!-- <label for="observacao">Observação</label> -->
																<span class="font-weight-bold font-italic" style="color: #708090">Observação</span>
																<textarea style="color: #000000" class="form-control" id="observacao" name="observacao" placeholder="Observação" rows="100" >${modelContrato.observacao}</textarea>
															</div>
														    
                                                            <!-- 
                                                            *   
                                                            *  Inicio da DIV com as informacoes de Vigencia.
                                                            *
                                                             -->
															<div class="row">
																<div class="col-sm-12">
																	<!-- Basic Form Inputs card start -->
																	<div class="card">
																		<div class="card-block">

																			<div class="form-row">
																				<!-- Campo ID Vigência-->
																				<div class="form-group form-default form-static-label col-md-6 mb-6">
																				    <span class="text-info font-weight-bold font-italic">ID Vigência</span>
																					<input class=" form-control text-info font-italic" type="text" name="id_vigencia" id="id_vigencia" readonly="readonly" value="${modelContrato.id_vigencia}">
																				</div>
				
																				<!-- Data Cadastro Vigência -->
																				<div class="form-group form-default form-static-label col-md-6 mb-6">
																				    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
																					<input class=" form-control text-info font-italic" type="text" name="dt_criacao_vigencia" id="dt_criacao_vigencia" readonly="readonly" value="${modelContrato.dt_criacao_vigencia}">
																				</div>
																			</div>
																			<hr>  
																			<br>
																			<!-- Informacoes do Tempo de Contrato -->
																			<div class="form-row">
																			
																				<!-- Campo Tempo Contrato -->
																				<div class="form-group form-default form-static-label col-md-4 mb-3">
																				    <span class="font-weight-bold font-italic" style="color: #708090">Tempo Contrato</span>
																					<select style="color: #000000" name="selectTempoContrato" id="selectTempoContrato" onchange="calculaDataFinalVigencia();" class="form-control" required="required">
																						<option value="" disabled selected>Selecione Tempo Contrato</option>
																						    <tagsContrato:listaTempoContrato/>
																					</select> <!--  <label class="float-label">Tempo Contrato</label> -->
																				</div>
							                                                    <!-- Campo Data Início --> 
							                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
							                                                        <span class="font-weight-bold font-italic" style="color: #708090">Data Início</span>
																					<input style="color: #000000" type="text" name="dt_inicio" id="dt_inicio" class="form-control" onchange="calculaDataFinalVigencia();" required="required" placeholder="Data início do contrato" value="${modelContrato.dt_inicio}"> 
																					<!-- <label class="float-label">Data Início</label> -->
							                                                    </div>
																			
																				<!-- Campo Data Final -->
																				<div class="form-group form-default form-static-label col-md-4 mb-6">
																				     <span class="font-weight-bold font-italic" style="color: #708090">Data Final</span>
						                                                             <input style="color: #000000" type="text" name="dt_final" id="dt_final" required="required" class="form-control"  placeholder="Data final do contrato" value="${modelContrato.dt_final}">
						                                                             <!-- <span class="form-bar"></span>
						                                                             <!--  <label class="float-label">Data Final</label> -->
																				</div>
																			</div>
																		    <hr>  
																			<br>
																		    <div class="form-group form-default form-static-label mb-6">
																				<span class="font-weight-bold font-italic" style="color: #708090">Observaçãoo Vigência</span>
																				<textarea style="color: #000000" class="form-control" id="observacaoVigencia" name="observacaoVigencia" placeholder="Observação" rows="100" >${modelContrato.observacao_vigencia}</textarea>
																			</div>
																		    <hr>  
																			<br>
																		</div>
																	</div>
																</div>
															</div>


															<!-- Declaracoes dos Botoes 
 											                <button type="button" class="btn btn-outline-primary waves-effect waves-light mesmo-tamanho-botao" name="novo" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-outline-success waves-effect waves-light mesmo-tamanho-botao" name="salvar" id="btSalvar">Salvar</button>
												            -->
													       <% 
													            String useradmin = (String) session.getAttribute("useradmin");
													            if(useradmin != null){
													               if( useradmin.trim().equals("1") ){
													       %>
													            	 <button type="submit" class="btn btn-outline-success waves-effect waves-light mesmo-tamanho-botao" name="salvar" id="btSalvar">Salvar</button>
													       <%      }else{ %>
													                 <button type="submit" class="btn btn-outline-success waves-effect waves-light mesmo-tamanho-botao" data-toggle="tooltip" data-placement="top" title="Esta funcionalidade esta habilitada somente para usário ADMIN!"disabled name="salvar" id="btSalvar">Salvar</button>
													       <%      }
													            } else{ %>
													               <button type="submit" class="btn btn-outline-success waves-effect waves-light mesmo-tamanho-botao" data-toggle="tooltip" data-placement="top" title="Esta funcionalidade esta habilitada somente para usário ADMIN!"disabled name="salvar" id="btSalvar">Salvar</button>
													       <% 
													            }
													       		modelContrato = (ModelContrato) request.getAttribute("modelContrato");
												                
													            String msgAddAditProdDesa  = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Só poderá Adicionar um Produto Via 'Aditivo', se estiver um Contrato em modo de 'Ediçãoo'!\" ";
													            String msgAddAditRecuDesa  = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Só poderá Adicionar um Resurso Via 'Aditivo', se estiver um Contrato em modo de 'Edição'!\" ";
													            disabledBT                 = " disabled ";

													            String InicioTipoBT    = "<button type=\"button\" ";
													            String classeBT        = " class=\"btn waves-effect waves-light mesmo-tamanho-botao";
													            String modalBTAditProd = " data-toggle=\"modal\" data-target=\"#ModalAditivoProduto\" " ;
													            String modalBTAditRecu = " data-toggle=\"modal\" data-target=\"#ModalAditivoRecurso\" " ;
													            String FinalBT         = " </button>";

													            String disabled        = "";
													            String idContrato      = null;
													            String idCliente       = null;
													            String nomeCliente     = null;
																if ( modelContrato != null ) {
																	if( !modelContrato.isNovo() ){ 
																		disabledBT         = ""; 
																		msgAddAditProdDesa = "";
																		msgAddAditRecuDesa = "";
																		idContrato         = String.valueOf( modelContrato.getId_contrato() );
																		idCliente          = String.valueOf( modelContrato.getId_cliente()  );
																		nomeCliente        = modelContrato.getNomeCliente();
																		
																		
																	}
																}
																String BtAddAditProd = InicioTipoBT + classeBT + " btn-outline-info \""    + msgAddAditProdDesa + disabledBT + modalBTAditProd + "  style=\"color:black; \">Aditivo Produto" + FinalBT;
																String BtAddAditivo  = InicioTipoBT + classeBT + " btn-outline-primary \"" + msgAddAditRecuDesa + disabledBT + modalBTAditRecu + ">Aditivo Recurso" + FinalBT;
																
																out.println( BtAddAditivo );
																out.println( BtAddAditProd );
																
//																System.out.println(BtAddAditivo);
															%>

														</form>
													</div>
												</div>
											</div>
										</div>
										
										<!-- Campo destinado a mensagem na Tela -->
										<div class="alert alert-primary alert-dismissible fade show" role="alert">
                                           <strong >Resultado: </strong> <span id="msg">${msg}</span>
                                           <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                             <span aria-hidden="true">&times;</span>
										  </button>
										</div>


										<!-- Campo destinado listar os Cliente no pe da tela -->
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">

														
														<ul class="nav nav-pills nav-justified mb-3" id="pills-tab" role="tablist">
														  
															<li class="nav-item"> <a class="nav-link active" id="recurso-tab"         data-toggle="tab" href="#recursoContrato"      role="tab" aria-controls="recursoContrato"      aria-selected="true" >Recurso Contrato</a></li>
															<li class="nav-item"> <a class="nav-link"        id="produto-tab"         data-toggle="tab" href="#tabelaProduto"        role="tab" aria-controls="tabelaProduto"        aria-selected="false">Produto Contrato</a> </li>
															<li class="nav-item"> <a class="nav-link"        id="recurso-aditivo-tab" data-toggle="tab" href="#tabelaRecursoAditivo" role="tab" aria-controls="tabelaRecursoAditivo" aria-selected="false">Recurso Aditivo </a> </li>
															<li class="nav-item"> <a class="nav-link"        id="produto-aditivo-tab" data-toggle="tab" href="#tabelaProdutoAditivo" role="tab" aria-controls="tabelaProdutoAditivo" aria-selected="false">Produto Aditivo </a> </li>
														</ul>

														<div class="tab-content" id="myTabContent">
														
															<div class="tab-pane fade show active" id="recursoContrato" role="tabpanel" aria-labelledby="recurso-tab">
                                                              <div style="height: 300px; overflow: scroll;">
																<table class="table table-striped table-hover table-sm table-bordered table-responsive-xl">
																  <thead>
																    <tr>
																      <th scope="col">ID Recurso     </th>
																      <th scope="col">DT Cadastro    </th>
																      <th scope="col">Cliente        </th>
																      <th scope="col">Hotname        </th>
																      <th scope="col">Status Contrato</th>
																      <th scope="col">Status Recurso </th>
																      <th scope="col">Ambiente       </th>
																      <th scope="col">Familia        </th>
																      <th scope="col">Serviço        </th>
																      <th scope="col">IP             </th>
																      <th scope="col">Nuvem          </th>
																      <th scope="col">Site           </th>
																      <th scope="col">Editar         </th>
																    </tr>
																  </thead>
																  <tbody id="TbodyRecusos">
<!--  																  
    					                                              <c:forEach items="${listaRecursoContratos}" var="lrc">
					                                                   <tr>
					                                                     <td> <c:out value="${lrc.id_recurso}"     > </c:out> </td>
					                                                     <td> <c:out value="${lrc.dt_cadastro}"    > </c:out> </td>
					                                                     <td> <c:out value="${lrc.razao_social}"   > </c:out> </td>
					                                                     <td> <c:out value="${lrc.hostname}"       > </c:out> </td>
					                                                     <td> <c:out value="${lrc.status_contrato}"> </c:out> </td>
					                                                     <td> <c:out value="${lrc.status_recurso}" > </c:out> </td>
					                                                     <td> <c:out value="${lrc.ambiente}"       > </c:out> </td>
					                                                     <td> <c:out value="${lrc.familia}"        > </c:out> </td>
					                                                     <td> <c:out value="${lrc.tipo_servico}"   > </c:out> </td>
					                                                     <td> <c:out value="${lrc.primary_ip}"     > </c:out> </td>
					                                                     <td> <c:out value="${lrc.mome_parceiro}"  > </c:out> </td>
					                                                     <td> <c:out value="${lrc.nome_site}"      > </c:out> </td>
					                                                   </tr>
					                                                  </c:forEach>
-->					                                                  
 														          </tbody>
														        </table>                                                            
                                                              </div>
                                                            </div>
                                                            
															<div class="tab-pane fade" id=tabelaProduto role="tabpanel" aria-labelledby="produto-tab">
                                                              <div style="height: 300px; overflow: scroll;">
																<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
																  <thead>
																    <tr>
																      <th scope="col">ID Contrato   </th>
																      <th scope="col">ID Produto    </th>
																      <th scope="col">Produto       </th>
																      <th scope="col">Data Cadastro </th>
																      <th scope="col">Quantidade    </th>
																      <th scope="col">Valor Unitário</th>
																      <th scope="col">Valor Total   </th>
																      <th scope="col">Editar        </th>
																    </tr>
																  </thead>
																  <tbody id="TbodyProdutos">
																      <!--  
    					                                              <c:forEach items="${listaContratoProdutos}" var="lcp">
					                                                   <tr>
					                                                     <td> <c:out value="${lcp.id_contrato}"   > </c:out> </td>
					                                                     <td> <c:out value="${lcp.id_produto}"    > </c:out> </td>
					                                                     <td> <c:out value="${lcp.produto}"       > </c:out> </td>
					                                                     <td> <c:out value="${lcp.dt_cadastro}"   > </c:out> </td>
					                                                     <td> <c:out value="${lcp.qty_servico}"   > </c:out> </td>
					                                                     <td> <c:out value="${lcp.valor_unitario}"> </c:out> </td>
					                                                     <td> <c:out value="${lcp.valor}"         > </c:out> </td>
					                                                   </tr>
					                                                  </c:forEach>
					                                                  -->
 														          </tbody>
														        </table>                                                            
                                                              </div>
															</div>

															<div class="tab-pane fade" id=tabelaRecursoAditivo role="tabpanel" aria-labelledby="recurso-aditivo-tab">
                                                              <div style="height: 300px; overflow: scroll;">

																		<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaListaAditivoCadastrado" >
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
                                                                         <% 
                                                                            List<ModelAitivoRecursoModal> aitivoRecursos = new ArrayList<ModelAitivoRecursoModal>();
                                                                            aitivoRecursos = (List<ModelAitivoRecursoModal>) request.getAttribute("modelAitivoRecursoModals");
                                                        	  				String html            = "";
                                                        	  				String vHubspotAditivo = "";
                                                        	  				int idAditivadoAux  = 0;
                                                                            if( aitivoRecursos != null ){
                                                                  		      for (int i = 0; i < aitivoRecursos.size(); i++) {
                                                             	  					if( idAditivadoAux != aitivoRecursos.get(i).getId_aditivado() ){
                                                            	  						if( idAditivadoAux != 0 ){
                                                            	  				  			html +="</tbody>"
                                                                  	  		  				      +  "</table>"
                                                                  	  		  				      + " </td>"
                                                                  	  		  				      + "</tr>";	
                                                                  	  						}
                                                            	  						    if( aitivoRecursos.get(i).getHubspot_aditivo() != null ) vHubspotAditivo = aitivoRecursos.get(i).getHubspot_aditivo();
                                                            	  						    else vHubspotAditivo = " - ";
                                                                  	  						html += "<tr> "
                                                                  		  						  +    "<td align=\"center\"> <button onClick=\"esconderLinha( 'linhaAEsconderTela" + i + "')\"> + </button> </td>"
                                                                  		  						  +    "<td>" + aitivoRecursos.get(i).getId_aditivado()    + "</td>"
                                                                  		  						  +    "<td>" + vHubspotAditivo                            + "</td>"
                                                                  		  						  +    "<td>" + aitivoRecursos.get(i).getDt_criacao()      + "</td>"
                                                                  		  						  +    "<td>" + aitivoRecursos.get(i).getValor_total()     + "</td>"
                                                                  	  						      + "</tr>"
                                                                                                    + "<tr id=\"linhaAEsconderTela" + i + "\" style=\"display: none\">"
                                                                    							      +    "<td></td>"
                                                                    							      +    "<td colspan=\"6\">"
                                                                    							      +       "<table class=\"table table-striped table-hover table-sm table-bordered table-responsive-sm\" id=\"sub-tabelaResutadoAditivoRecurso\">"
                                                                    							      +          "<thead>"
                                                                    							      +              "<tr>"
                                                                    							      +                 "<th scope=\"col\">ID Recurso   </th>"
                                                                    							      +                 "<th scope=\"col\">Tipo Aditivo </th>"
                                                                    							      +                 "<th scope=\"col\">Status       </th>"
                                                                    							      +                 "<th scope=\"col\">Tipo Serviço </th>"
                                                                    							      +                 "<th scope=\"col\">Ambiente     </th>"
                                                                    							      +                 "<th scope=\"col\">SO           </th>"
                                                                    							      +                 "<th scope=\"col\">Tipo Disco   </th>"
                                                                    							      +                 "<th scope=\"col\">Hostname     </th>"
                                                                    							      +                 "<th scope=\"col\">Família      </th>"
                                                                            					      +                 "<th scope=\"col\">Valor Recurso</th>"
                                                                    							      +              "</tr>"
                                                                    							      +          "</thead>"
                                                                    							      +          "<tbody>"
                                                                  				  				  +             "<tr> " 
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getId_recurso()               + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_ditivo()         + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_status_recurso()      + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_servico()        + "</td>"		  					      
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_ambiente()            + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_sistema_operacional() + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_disco()          + "</td>"
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getHost_name_modal_recurso()  + "</td>"		  					      
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_familia()             + "</td>"		  					      
                                                                  		  					      +               " <td>" + aitivoRecursos.get(i).getValor_recurso()            + "</td>"		  					      
                                                                  					              +             "</tr>";

                                                                  	  						idAditivadoAux = aitivoRecursos.get(i).getId_aditivado().intValue();;
                                                                  	  					}else{
                                                                  		  					 html +=             "<tr> " 
                                                                     		  					      +               " <td>" + aitivoRecursos.get(i).getId_recurso()               + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_ditivo()         + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_status_recurso()      + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_servico()        + "</td>"		  					      
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_ambiente()            + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_sistema_operacional() + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_tipo_disco()          + "</td>"
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getHost_name_modal_recurso()  + "</td>"		  					      
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getDesc_familia()             + "</td>"		  					      
                                                                      		  					      +               " <td>" + aitivoRecursos.get(i).getValor_recurso()            + "</td>"		  					      
                                                                      					              +             "</tr>";
                                                                  	  					}
                                                                  		      }		      
                                                                  	  				
                                                                  		  			html +="</tbody>"
                                                                  	  				      +  "</table>"
                                                                  	  				      + " </td>"
                                                                  	  				      + "</tr>";	      
                                                                		      
                                                                  		      
                                                                            }
                                                            			    out.println( html );
                                                            			    /**************************************************************************************************/
                                                            			    /*                                                                                                */
                                                            			    /*      Monta a tabela com as informacoes do Historico de troca de Familia de um Recurso          */  
                                                            			    /*                                                                                                */
                                                            			    /**************************************************************************************************/
                                                                          %>
                                                                            </tbody>
                                                                          </table>
                                                                          <br>
                                                                          <!--  <strong><h4 style="color: blue;">Histórico Troca de Família</h4></strong>  -->
                                                                          <strong class="alert-primary"><h4>Histórico Troca de Família</h4></strong> 
																		  <table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaListaHistoricoFamilia" >
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
								                                      <%    
								                                          List<ModelHistoricoUpgrade> listaHistoricoUpgradeTabela = new ArrayList<ModelHistoricoUpgrade>();
								                                          listaHistoricoUpgradeTabela = (List<ModelHistoricoUpgrade>) request.getAttribute("listaHistoricoUpgrade");
								                      	  				        html              = "";
								                      	  				        int idRecursoAux  = 0;
								                                          if( listaHistoricoUpgradeTabela != null ){
								                                		      for (int i = 0; i < listaHistoricoUpgradeTabela.size(); i++) {
								                           	  					  if( idRecursoAux != listaHistoricoUpgradeTabela.get(i).getId_recurso() ){
								                          	  						  if( idRecursoAux != 0 ){
								                          	  				  		  	html +="</tbody>"
								                                	  		  				      +  "</table>"
								                                	  		  				      + " </td>"
								                                	  		  				      + "</tr>";	
								                                	  						}
								                                	  						html += "<tr> "
								                                		  						  +    "<td align=\"center\"> <button onClick=\"esconderLinha( 'linhaAEsconderTelaHist" + i + "')\"> + </button> </td>"
								                                		  						  +    "<td>" + listaHistoricoUpgradeTabela.get(i).getId_recurso()         + "</td>"
								                                		  						  +    "<td>" + listaHistoricoUpgradeTabela.get(i).getHostname()           + "</td>"
								                                		  						  +    "<td>" + listaHistoricoUpgradeTabela.get(i).getDt_criacao_recurso() + "</td>"
								                                		  						  +    "<td>" + listaHistoricoUpgradeTabela.get(i).getFamilia_atual()      + "</td>"
								                                	  						      + "</tr>"
								                                                                  + "<tr id=\"linhaAEsconderTelaHist" + i + "\" style=\"display: none\">"
								                                  							      +    "<td></td>"
								                                  							      +    "<td colspan=\"6\">"
								                                  							      +       "<table class=\"table table-striped table-hover table-sm table-bordered table-responsive-sm\" id=\"sub-tabelaResutadoHistoricoFamilia\">"
								                                  							      +          "<thead>"
								                                  							      +              "<tr>"
								                                  							      +                 "<th scope=\"col\">ID Contrato     </th>"
								                                  							      +                 "<th scope=\"col\">Data Alteração  </th>"
										                                  						  +                 "<th scope=\"col\">IP              </th>"
								                                  							      +                 "<th scope=\"col\">Família Antiga  </th>"
								                                  							      +                 "<th scope=\"col\">Família Nova    </th>"
								                                  							      +                 "<th scope=\"col\">Tam. Disco Atigo</th>"
								                                  							      +                 "<th scope=\"col\">Tam. Disco Novo </th>"
								                                  							      +                 "<th scope=\"col\">Valor Recurso   </th>"
								                                  							      +                 "<th scope=\"col\">Valor Ajustado  </th>"
								                                  							      +                 "<th scope=\"col\">Nuvem           </th>"
								                                  							      +              "</tr>"
								                                  							      +          "</thead>"
								                                  							      +          "<tbody>"
								                                				  				  +             "<tr> " 
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getId_contrato()         + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getDt_cadastro()         + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getIp()                  + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getFamilia_antiga()      + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getFamilia_novo()        + "</td>"		  					      
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getTamanho_disco_velho() + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getTamanho_disco_novo()  + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getValor_velho()         + "</td>"
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getValor_novo()          + "</td>"		  					      
								                                		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getNuvem()               + "</td>"		  					      
								                                					              +             "</tr>";
								
								                                	  						idRecursoAux = listaHistoricoUpgradeTabela.get(i).getId_recurso().intValue();;
								                                	  					}else{
								                                		  					 html +=             "<tr> " 
								                                   		  					      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getId_contrato()         + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getDt_cadastro()         + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getIp()                  + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getFamilia_antiga()      + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getFamilia_novo()        + "</td>"		  					      
								                                    		  				      +               " <td>" + listaHistoricoUpgradeTabela.get(i).getTamanho_disco_velho() + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getTamanho_disco_novo()  + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getValor_velho()         + "</td>"
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getValor_novo()          + "</td>"		  					      
								                                    		  					  +               " <td>" + listaHistoricoUpgradeTabela.get(i).getNuvem()               + "</td>"		  					      
								                                    					          +             "</tr>";
								                                	  					}
								                                		      }		      
								                                	  				
								                                		  			html +="</tbody>"
								                                	  				      +  "</table>"
								                                	  				      + " </td>"
								                                	  				      + "</tr>";	      

								                                          }
								                                          out.println( html );
								                                          %>
																		    </tbody>
																		  </table>    
                                                              </div>
															</div>
                                                            <div class="tab-pane fade" id=tabelaProdutoAditivo role="tabpanel" aria-labelledby="produto-aditivo-tab">
                                                              <div style="height: 300px; overflow: scroll;">
																<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaListaProdutoAditivo" >
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
                                                                       <% 
                                                                          /* 
                                                                          List<ModelAitivoRecursoModal> aitivoRecursos = new ArrayList<ModelAitivoRecursoModal>();
                                                                          aitivoRecursos = (List<ModelAitivoRecursoModal>) request.getAttribute("modelAitivoRecursoModals");
                                                                          */
                                                                          List<ModelListAditivoProduto> aditivoProdutos = (List<ModelListAditivoProduto>) request.getAttribute("listAditivoProdutos");
                                                      	  				html            = "";
                                                      	  				idAditivadoAux  = 0;
                                                                          if( aditivoProdutos != null ){
                                                                		      for (int i = 0; i < aditivoProdutos.size(); i++) {

                                                           	  					if( idAditivadoAux != aditivoProdutos.get(i).getId_aditivado() ){
                                                          	  						if( idAditivadoAux != 0 ){
                                                          	  				  			html +="</tbody>"
                                                                	  		  				      +  "</table>"
                                                                	  		  				      + " </td>"
                                                                	  		  				      + "</tr>";	
                                                                	  						}
                                                    	  						            if( aditivoProdutos.get(i).getHubspot_aditivo() != null ) vHubspotAditivo = aditivoProdutos.get(i).getHubspot_aditivo();
                                                    	  						            else vHubspotAditivo = " - ";
                                                          	  						
                                                                	  						html += "<tr> "
                                                                		  						  +    "<td align=\"center\"> <button onClick=\"esconderLinha( 'linhaAEsconderTelaPA" + i + "')\"> + </button> </td>"
                                                                		  						  +    "<td>" + aditivoProdutos.get(i).getId_aditivado()   + "</td>"
                                                                		  						  +    "<td>" + vHubspotAditivo                            + "</td>"
                                                                		  						  +    "<td>" + aditivoProdutos.get(i).getDt_criacao()     + "</td>"
                                                                		  						  +    "<td>" + aditivoProdutos.get(i).getVlr_total_adit() + "</td>"
                                                                	  						      + "</tr>"
                                                                                                  + "<tr id=\"linhaAEsconderTelaPA" + i + "\" style=\"display: none\">"
                                                                  							      +    "<td></td>"
                                                                  							      +    "<td colspan=\"6\">"
                                                                  							      +       "<table class=\"table table-striped table-hover table-sm table-bordered table-responsive-sm\" id=\"sub-tabelaResutadoAditivoProduto\">"
                                                                  							      +          "<thead>"
                                                                  							      +              "<tr>"
                                                                  							      +                 "<th scope=\"col\">ID Aditivo   </th>"
                                                                  							      +                 "<th scope=\"col\">Tipo Aditivo </th>"
                                                                  							      +                 "<th scope=\"col\">Status       </th>"
                                                                  							      +                 "<th scope=\"col\">Produto      </th>"
                                                                  							      +                 "<th scope=\"col\">Quantidade   </th>"
                                                                  							      +                 "<th scope=\"col\">Valor Unit.  </th>"
                                                                  							      +              "</tr>"
                                                                  							      +          "</thead>"
                                                                  							      +          "<tbody>"
                                                                				  				  +             "<tr> " 
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getId_aditivado()                 + "</td>"
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getNome_tipo_aditivo_contratado() + "</td>"
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getDsc_status_aditivo()           + "</td>"
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getNome_prod_contratado()         + "</td>"		  					      
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getQty_produto_contratado()       + "</td>"
                                                                		  					      +               " <td>" + aditivoProdutos.get(i).getValor_unitario_contratado()    + "</td>"
                                                                					              +             "</tr>";
                                                                	  						idAditivadoAux = aditivoProdutos.get(i).getId_aditivado().intValue();;
                                                                	  					}else{
                                                                		  					 html +=             "<tr> " 
                                                                   		  					      +               " <td>" + aditivoProdutos.get(i).getId_aditivado()                 + "</td>"
                                                                    		  					      +               " <td>" + aditivoProdutos.get(i).getNome_tipo_aditivo_contratado() + "</td>"
                                                                    		  					      +               " <td>" + aditivoProdutos.get(i).getDsc_status_aditivo()           + "</td>"
                                                                    		  					      +               " <td>" + aditivoProdutos.get(i).getNome_prod_contratado()         + "</td>"		  					      
                                                                    		  					      +               " <td>" + aditivoProdutos.get(i).getQty_produto_contratado()       + "</td>"
                                                                    		  					      +               " <td>" + aditivoProdutos.get(i).getValor_unitario_contratado()    + "</td>"
                                                                    					              +             "</tr>";
                                                                	  					}
                                                                		      }		      
                                                                	  				
                                                                		  			html +="</tbody>"
                                                                	  				      +  "</table>"
                                                                	  				      + " </td>"
                                                                	  				      + "</tr>";	      
                                                                          }
                                                          			      out.println( html );
                                                                        %>																		  																	   
																  </tbody>
																</table>    
                                                              </div>
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
                          <p class="text-primary h6 font-weight-normal font-italic text-center">Pesquisa por: *Nome Cliente *Alias * CNPJ * PEP.</p> 

						</div>
					</div>
				</div>
			</div>



			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div class="input-group mb-3">
								<input type="text" id="nomeBuscaCliente" class="form-control" placeholder="Digite aqui..." aria-label="Nome Cliente" aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn btn-outline-info" type="button" onclick="buscarCliene();">Buscar</button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

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

<!-- 
******************************************************************************************************
*
*                                  Modal Buscar Cliente        
*
******************************************************************************************************
-->
	<div class="modal t-modal primary" id="modalPesqContratoPEP" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisar Contrato pelo PEP</h5>
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

							<div class="input-group mb-3">
								<input type="text" id="buscaContratoPEP" class="form-control" placeholder="Digite parte ou o PEP completo!" aria-label="PEP" aria-describedby="basic-addon2">
								<div class="input-group-append">
									<button class="btn btn-success" type="button" onclick="buscarContratoPEP();">Buscar</button>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div style="height: 300px; overflow: scroll;">
								<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoPEP">
									<thead>
										<tr>
											<th scope="col">#           </th>
											<th scope="col">ID Cliente  </th>
											<th scope="col">Nome Cliente</th>
											<th scope="col">PEP         </th>											
											<th scope="col">Selecionar  </th>
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

		  </div>
	      <samp id="totalResutadosPEP"></samp>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	    </div>
	  </div>
	</div>

<!-- 

   Modal que ira mostrar um contrato assinado e anexo em PDF.
 -->
   <div class="modal t-modal primary" id="ModalShowPDF" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-dialog-centered modal-lg"  style="max-width: 80% !important;" role="document">
		<div class="modal-content">
			<!-- Modal Header -->
	        <div class="modal-header">
	          <h5 class="modal-title" id="TituloModalCentralizado">Visualiza Contrato PDF</h5>
	          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	            <span aria-hidden="true">&times;</span>
	          </button>
	        </div>
			<!-- Modal body -->
			<div class="modal-body">
				<div class="ModalnomeArquivo"></div>
				<iframe src="" width="100%" height="500px" id="framePDF"></iframe>  
			</div>
			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
			</div>
		
		</div>
	</div>
</div>

    <jsp:include page="javascriptfile.jsp"></jsp:include>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/select2/select2.min.js"></script>

    <jsp:include page="ModalProdutoEdit.jsp"   ></jsp:include>
    <jsp:include page="ModalRecursoEdit.jsp"   ></jsp:include>
    <jsp:include page="ModalAditivo.jsp"       ></jsp:include>
    <jsp:include page="ModalAditivoRecurso.jsp"></jsp:include>


 <script type="text/javascript">
 
 
//In your Javascript (external .js resource or <script> tag)
 $(document).ready(function() {
     $('#pep').select2( {
          theme: 'bootstrap-5'
     } );
     
     var pepEscondido = document.getElementById("pepEscondido").value;
     montaSelectPEP( pepEscondido );
     
 });

 function validaStatusPepProvisorio(){
//    let idStatus  = select.options[id_status_contrato.selectedIndex ].value;
    var idStatus  = id_status_contrato.options[id_status_contrato.selectedIndex ].value;
    let status      = $('#id_status_contrato').find(":selected").text( )       ;
    if( ((status !== 'Rascunho') && parseInt(idStatus) !== 4) || status.trim() === ''  ){
 	   // PEP PROVISÓRIO
// 	   $("#pep option[value='PEP PROVISÓRIO']").remove();
        const select = document.querySelector('#pep')
        for (let i = 0; i < select.options.length; i++) {
             const value = select.options[i].value;
             if (value === 'PEP PROVISÓRIO' ) {
                     select.remove(i);
                     $('#pep').get(0).selectedIndex = 0;
                     break;
             }
        }	   
    }else{
 	  $('#pep').append('<option>PEP PROVISÓRIO</option>');
    }	
 }

  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
  function montaSelectPEP( pep ){
     var urlAction = document.getElementById("formContrato").action;
     var pepSelect = $('#pep');
 	$.ajax({     			
 		method : "get",
 		url : urlAction,
 		data : 'acao=montaSelectPEP',
 		success: function(lista){
 //			var option = '<option disabled selected></option>';
            var option = '';
 			var selected = '';
 			var json = JSON.parse(lista);  
 			for(var p = 0; p < json.length; p++){
 	 			if( json[p].pep === pep ) selected = 'selected';
 	 			else selected = '';
 				
// 				option += '<option value=' + json[p].idContrato + ' ' + selected + '>' + json[p].pep + '</option>';
 				option += '<option value=' + json[p].pep + ' ' + selected + '>' + json[p].pep + '</option>';
 			}
 			$("#pep").html(option).show();  
 /*			
 			for(var p = 0; p < json.length; p++){
 		      // create the option and append to Select2
 		      
 		         var option = new Option( json[p].pep, json[p].idContrato, true, true );
 		      
 		     <option value="16" selected="" data-select2-id="select2-data-17-t5sq">BR-MC-BRIG_PUB-HOST</option>		     
 		     
 		     console.log(option);
 		      pepSelect.append(option).trigger('change');
 			}

  			$('#pep').val(null).trigger('change');
*/ 			
 		}
 	}).fail(function( xhr ){
 		// alert('Erro ao buscar Cliente: ' + xhr.responseText);
 		var iconi = "error";
 		var tituloPrincipal = "Erro ao buscar PEP";
 		var textoPrincipal = xhr.responseText;
 		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
 	}); 

 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function montaTabelaProdutos(){
    var urlAction         = document.getElementById("formContrato").action;
    var idContratoCliente = document.getElementById("id_contrato" ).value; 
    
	$.ajax({     			
		method : "get",
		url  : urlAction,
		data : 'acao=montaTabelaProdutos&idContratoCliente=' + idContratoCliente,
		success: function(lista){
			var listProduto = JSON.parse(lista); 
			let tbody = document.getElementById('TbodyProdutos');
			tbody.innerText = '';
			
			for(let i = 0; i < listProduto.length; i++){
				// Cria as linhas
		         let tr = tbody.insertRow();
		         
		         // Crias as celulas
		         let td_id_contrato    = tr.insertCell();
		         let td_id_produto     = tr.insertCell();
		         let td_produto        = tr.insertCell();
		         let td_dt_cadastro    = tr.insertCell();
		         let td_qty_servico    = tr.insertCell();
		         let td_valor_unitario = tr.insertCell();
		         let td_valor          = tr.insertCell();
		         let td_editar         = tr.insertCell();
		         
		         // Inseri os valores do objeto nas celulas
		         td_id_contrato.innerText    = listProduto[i].id_contrato   ;
		         td_id_produto.innerText     = listProduto[i].id_produto    ;
		         td_produto.innerText        = listProduto[i].produto       ;
		         td_dt_cadastro.innerText    = listProduto[i].dt_cadastro   ;
		         td_qty_servico.innerText    = listProduto[i].qty_servico   ;
		         td_valor_unitario.innerText = listProduto[i].valor_unitario;
		         td_valor.innerText          = listProduto[i].valor         ;
		         
		         let imgEdit = document.createElement('img');
		         imgEdit.src = getContextPath() +'/imagens/edit-40.png';
		         
		         let obs = "";
		         if( listProduto[i].observacao !== undefined ) obs = listProduto[i].observacao;
		         
		         var parametros = listProduto[i].id_produto  + ", '" + listProduto[i].valor_unitario + "', '" + obs                  + "', " + 
		                          listProduto[i].qty_servico + ", '" + listProduto[i].valor_unitario + "', '" + listProduto[i].valor + "'"; 
		        

//		         alert( 'abriModalProdutoEditar( ' + parametros + ' )' );

		         imgEdit.setAttribute('onclick','abriModalProdutoEditar( ' + parametros + ' )' );
	         
		         td_editar.appendChild(imgEdit);
			}
		}
	}).fail(function( xhr, status, errorThrown ){
		// alert('Erro ao buscar Cliente: ' + xhr.responseText);
	var iconi = "error";
	var tituloPrincipal = "Erro ao buscar Cliente";
	var textoPrincipal = xhr.responseText;
	MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	}); 		
 }

 function abriModalProdutoEditar( v_Prod, v_vlrProd, v_obsProd, v_QtyProd, v_vlrUnitProd, v_vltTotalProd ) {
	 let vAdmin = document.getElementById('tipoUserAdmin').value.trim();
	 
	 if( vAdmin === "1" ){
		 abreModalProduto();
		 verProdutoContrato( v_Prod, v_vlrProd, v_obsProd, v_QtyProd, v_vlrUnitProd, v_vltTotalProd );
     }else if( vAdmin === "0" ){
    		var iconi = "warning";
    		var tituloPrincipal = "Update Prduto";
    		var textoPrincipal = "Você precisar ser Admin para realizar este procedimento!";
    		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
     }else if( vAdmin === null || vAdmin.trim() === '' || vAdmin.trim() === '' ){
 		var iconi = "warning";
		var tituloPrincipal = "Update Prduto";
		var textoPrincipal = "Favor efeturar Login!";
		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
     }
 }

 function abreModalProduto() {	 
	 $("#ModalProdutoEdit").modal({
	      show: true
	    });
 } 
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function montaTabelaRecursos(){
    var urlAction         = document.getElementById("formContrato").action;
    var idContratoCliente = document.getElementById("id_cliente"    ).value; 
    
	$.ajax({     			
		method : "get",
		url  : urlAction,
		data : 'acao=montaListaRecursos&idContratoCliente=' + idContratoCliente,
		success: function(lista){
			var listRecursos = JSON.parse(lista); 
			let tbody = document.getElementById('TbodyRecusos');
			tbody.innerText = '';
			
			for(let i = 0; i < listRecursos.length; i++){
				// Cria as linhas
		         let tr = tbody.insertRow();
		         
		         // Crias as celulas
		         let td_id_recurso      = tr.insertCell();
		         let td_dt_cadastro     = tr.insertCell();
		         let td_razao_social    = tr.insertCell();
		         let td_hostname        = tr.insertCell();
		         let td_status_contrato = tr.insertCell();
		         let td_status_recurso  = tr.insertCell();
		         let td_ambiente        = tr.insertCell();
		         let td_familia         = tr.insertCell();
		         let td_tipo_servico    = tr.insertCell();
		         let td_primary_ip      = tr.insertCell();
		         let td_nuvem           = tr.insertCell();
		         let td_site            = tr.insertCell();
		         let td_editar          = tr.insertCell();

		         // Inseri os valores do objeto nas celulas
		         td_id_recurso.innerText      = listRecursos[i].id_recurso;
		         td_dt_cadastro.innerText     = listRecursos[i].dt_cadastro;
		         td_razao_social.innerText    = listRecursos[i].razao_social;
		         td_hostname.innerText        = listRecursos[i].hostname;
		         td_status_contrato.innerText = listRecursos[i].status_contrato;
		         td_status_recurso.innerText  = listRecursos[i].status_recurso;
		         td_ambiente.innerText        = listRecursos[i].ambiente;
		         td_familia.innerText         = listRecursos[i].familia;
		         td_tipo_servico.innerText    = listRecursos[i].tipo_servico;
		         td_primary_ip.innerText      = listRecursos[i].primary_ip;
		         td_nuvem.innerText           = listRecursos[i].mome_parceiro;
		         td_site.innerText            = listRecursos[i].nome_site;

		         let imgEdit = document.createElement('img');
		         imgEdit.src = getContextPath() +'/imagens/edit-40.png';
		         imgEdit.setAttribute('onclick','abriModalRecursoEditar( ' + listRecursos[i].id_recurso + ' )');
		         td_editar.appendChild(imgEdit);
			}
		}
	}).fail(function( xhr, status, errorThrown ){
		// alert('Erro ao buscar Cliente: ' + xhr.responseText);
	var iconi = "error";
	var tituloPrincipal = "Erro ao buscar Cliente";
	var textoPrincipal = xhr.responseText;
	MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	}); 		
 }

 function abriModalRecursoEditar( idRec ) {
	 let vAdmin = document.getElementById('tipoUserAdmin').value.trim();
	 
	 if( vAdmin === "1" ){
		 abreModalRecurso();
		 verRecursoContrato( idRec );
     }else if( vAdmin === "0" ){
    		var iconi = "warning";
    		var tituloPrincipal = "Update Recursos";
    		var textoPrincipal = "Você precisar ser Admin para realizar este procedimento!";
    		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
     }else if( vAdmin === null || vAdmin.trim() === '' || vAdmin.trim() === '' ){
 		var iconi = "warning";
		var tituloPrincipal = "Update Recursos";
		var textoPrincipal = "Favor efeturar Login!";
		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
     }

	 
	 
	 
 }

 
 
 function abreModalRecurso() {
	 
	 $("#ModalRecursoEdit").modal({
	      show: true
	    });
 } 
 
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function mostrarContratoPdf( nomeArqPDF ) {
	    var fileNamePdf = document.getElementById("VisualizarContrato").value;
		var src = $('#framePDF').attr('src'); 
        $('.ModalnomeArquivo').html( '<span class="font-weight-bold font-italic" style="color: #000000">Arquivo Selecionado: ' + nomeArqPDF + '</span>' );
		$('.modal-body #framePDF').attr('src', fileNamePdf);   //sets src value in  in modal iframe
        // Abri o Modal com a visualizacao do PDF.
	    $("#ModalShowPDF").modal({
		    show: true
		});

}
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
  $(function () {
      $('#arqContratoPDF').change(function() {
             $('.nomeArquivo').html('<span class="font-weight-bold font-italic" style="color: #000000">Arquivo Selecionado: ' + $(this).val() + '</span>' ) ;
      });
  }); 
 
  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
/*  
  function esconderLinhaTabela(idDaLinha) {
    $("#" + idDaLinha).toggle();
  }
*/ 

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function goRecurso( idContrato ) {
	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServletRecursoContrato"; // + "?acao=montaRecursoInicial&idContrato=" + idContrato + "&idAditivo=" + idAditivo;
	 var parametros = "?acao=montaRecursoInicial&idcontrato=" + idContrato;
	 var url = urlAction + parametros;
	 window.location.href = url;
 }
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function calculaDataFinalVigencia() {
	 
	 var idTempoContrato = selectTempoContrato.options[selectTempoContrato.selectedIndex].value;
	 var dtInicio        = document.getElementById("dt_inicio"   ).value;
	 var dtFinal         = document.getElementById("dt_final"    ).value;
	 var urlAction       = document.getElementById("formContrato").action;
 
	 $.ajax({ 			
  			method : "get",
  			url : urlAction,
  			data : 'acao=CalculaVigencia&idTempoContrato=' + idTempoContrato + '&dtInicio=' + dtInicio + '&dtFinal=' + dtFinal,
  			success: function(lista){
  				var json = JSON.parse(lista);
  				$("#dt_criacao_vigencia").val(json.dt_criacao);
  				$("#dt_inicio").val(json.dt_inicio);
  				$("#dt_final").val(json.dt_final);

   			}
  	 }).fail(function( xhr, status, errorThrown ){
 // 			alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "ERRO";
 			var textoPrincipal  = "Erro: Calcula Vigencia: " + xhr.responseText;
 			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  

  	 }); 
}
 
 
 


// $("#vlrProduto").maskMoney({showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."});


 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 
 // $("#valor_total").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

  $("#valor_total"     ).maskMoney({ showSymbol:true, symbol:""   , decimal:",", thousands:"." });
  $("#valor_convertido").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
  $("#valor_Cotacao"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
  
  
  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
  function cauculoConversao() {

	 var valorTotal      = document.getElementById("valor_total"  ).value;
	 var valorCotacao    = document.getElementById("valor_Cotacao").value;
	 var valorConvertido = '';
	
	 if( ( valorTotal   != null && valorTotal   != '' && valorTotal.trim() != '' ) && 
		 ( valorCotacao != null && valorCotacao != '' && valorCotacao.trim() != '' ) ){
		 valorTotal = valorTotal.replace(/[^\d]+/g,'');
		 valorTotal = valorTotal /100;
		 
		 valorCotacao = valorCotacao.replace(/[^\d]+/g,'');
		 valorCotacao = valorCotacao /100;
		 
		 valorConvertido = valorTotal * valorCotacao;
		 const valorCalculado = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(valorConvertido);
		 $("#valor_convertido").val(valorCalculado ); 
	 }
  }  
  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/ 
  function habilitaCotacao() {
	
	var idMoeda = document.getElementById("id_moeda"  ).value;
	var inputValorConvertido = document.querySelector("#valor_convertido");
	var inputValorCotacao = document.querySelector("#valor_Cotacao");
	
	if(idMoeda === "1"){
		$("#valor_convertido").val('');
		$("#valor_Cotacao"   ).val('');
		inputValorConvertido.disabled = true;
		inputValorCotacao.disabled = true;
	}else{
		inputValorConvertido.disabled = false;
		inputValorCotacao.disabled = false;
	}	
  }

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function buscarCliene() {

	 var nomeBuscaCliente = document.getElementById("nomeBuscaCliente").value;
	 
	 if( nomeBuscaCliente != null && nomeBuscaCliente != '' && nomeBuscaCliente.trim() != '' ){
		 var urlAction = document.getElementById("formContrato").action;
		 
   		$.ajax({
  			
  			method : "get",
  			   url : urlAction,
  			  data : "nomeBuscaCliente=" + nomeBuscaCliente + '&acao=buscarClienteAjax',
  			success: function(response){
  			
  				var json = JSON.parse(response);
  				
  				$('#tabelaResutado > tbody > tr').remove();
  				
  				for(var p = 0; p < json.length; p++){
  					$('#tabelaResutado > tbody').append('<tr> <td>' + (p+1) + '</td> <td>'+ json[p].id_cliente + '</td> <td>'+json[p].razao_social+'</td> <td><button onclick="verClienteSelecionado('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-info">Selecionar</button></td></tr>');
  																																								   
  				}
  				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
  			}
  			
  		}).fail(function( xhr, status, errorThrown ){
  			//alert('Erro ao buscar Cliente: ' + xhr.responseText);
 			var iconi           = "error";
 			var tituloPrincipal = "Erro ao buscar Cliente";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'modalClienteContrato'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, xhr.responseText, nomeModal );	
  			
  		});		 
	 }
 }
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
  function buscarContratoPEP() {
	 var buscaContratoPEP = document.getElementById("buscaContratoPEP").value;

 	 if( buscaContratoPEP != null && buscaContratoPEP != '' && buscaContratoPEP.trim() != '' ){
 		 var urlAction = document.getElementById("formContrato").action;
		 
    		$.ajax({
   			
   			method : "get",
   			   url : urlAction,
   			  data : "ContratoPEP=" + buscaContratoPEP + '&acao=buscarContratoPorPEP',
   			success: function(response){
   				 			
   				var json = JSON.parse(response);
   				var html = '';
   				$('#tabelaResutadoPEP > tbody > tr').remove();
   				
   				for(var p = 0; p < json.length; p++){
   					html += '<tr> '
   					     + '   <td>' + (p+1)                  + '</td> '
   					     + '   <td>' + json[p].id_cliente     + '</td> ' 
   					     + '   <td>' + json[p].razao_social   + '</td> '
   					     + '   <td>' + json[p].pep_pesquisado + '</td> ' 
   					     + '   <td><button onclick="verClienteSelecionado('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-info">Selecionar</button></td>'
   					     + ' </tr>';
   						$("#tabelaResutadoPEP tbody").html(html).show(); 
   				}
   				document.getElementById("totalResutadosPEP").textContent = 'Resutado: ' + json.length + ' Contratos(s) encontrado(s)';
   			}
   		}).fail(function( xhr, status, errorThrown ){
   			// alert('Erro ao buscar Cliente: ' + xhr.responseText);
 			var iconi           = "error";
 			var tituloPrincipal = "Erro ao buscar Cliente";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'modalPesqContratoPEP'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, xhr.responseText, nomeModal );	
   			
   		});		
 	 }
  }
 
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function verClienteSelecionado( idCliente, razaoSocial ) {	 
 	var urlAction = document.getElementById("formContrato").action;
	window.location.href = urlAction + '?acao=buscarContratoCliente&idContratoCliente='+idCliente;	 
	setTimeout(function() {
		
	}, 1000);
}


 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function habilitarComponentes(habilitar) {
	 document.getElementById('id_moeda'              ).disabled = habilitar;
	 document.getElementById('valor_total'           ).disabled = habilitar;
	 document.getElementById('id_nuvem'              ).disabled = habilitar;
	 document.getElementById('id_site'               ).disabled = habilitar;
	 document.getElementById('id_fase_contrato'      ).disabled = habilitar;
	 document.getElementById('id_status_contrato'    ).disabled = habilitar;
	 document.getElementById('id_ciclo_updadate'     ).disabled = habilitar;
	 document.getElementById('id_servico_contratado' ).disabled = habilitar;
	 document.getElementById('termo_admin'           ).disabled = habilitar;
	 document.getElementById('termo_download'        ).disabled = habilitar;
	 document.getElementById('arqContratoPDF'        ).disabled = habilitar;
//	 document.getElementById('VisualizarContrato'    ).disabled = habilitar;
//	 document.getElementById('downloadContrato'      ).disabled = habilitar;
	 document.getElementById('pep'                   ).disabled = habilitar;
	 document.getElementById('id_hub_spot'           ).disabled = habilitar;
	 document.getElementById('qty_usuario_contratada').disabled = habilitar;
	 document.getElementById('observacao'            ).disabled = habilitar;
	 document.getElementById('selectTempoContrato'   ).disabled = habilitar;
	 document.getElementById('dt_inicio'             ).disabled = habilitar;
	 document.getElementById('dt_final'              ).disabled = habilitar;
	 document.getElementById('observacaoVigencia'    ).disabled = habilitar;
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 window.onload = function () { 
	 montaSelectSite(  );
	 habilitaCotacao( );
	 montaTabelaRecursos();
	 montaTabelaProdutos();
	 habilitaStatusMotivoRascunho();
	 if(document.getElementById('tipoUserAdmin').value.trim() !== "1" )
		 habilitarComponentes(true);
	 else habilitarComponentes(false);

 } 

 function getContextPath() {
	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function limparForm() {
	 
	 window.location.href = getContextPath() + "/principal/contrato.jsp";
	 /*
		var elementos = document.getElementById("formContrato").elements;
		
		for(p = 0; p < elementos.length; p++){
			elementos[p].value = '';
		}
		*/
	}
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 document.forms[0].onsubmit = function(){
	   if(!document.getElementById('nomeCliente').value.trim()){
	      // alert("Favor associar um Cliente ao contrato!");
		  var iconi = "warning";
		  var tituloPrincipal = "Cadastro de Contrato";
		  var textoPrincipal  = "Favor associar um Cliente ao contrato!";
		  MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	      
	      return false;
	   }
 }
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function montaSelectSite( idSiteSelecionado ){
	   var idNuvem       = document.getElementById("id_nuvem").value;
	   var idContCliente = document.getElementById("id_cliente").value; 

       if( idNuvem != null && idNuvem != '' && idNuvem.trim() != '' ){
    	   var urlAction = document.getElementById("formContrato").action;

      		$.ajax({
      			
      			method : "get",
      			url : urlAction,
      			data : "idNuvem=" + idNuvem + '&acao=montaSelectSite&idContCliente='+idContCliente,
      			success: function(lista){
      				var option = '<option value="" disabled selected>Selecione Site</option>';
      				var selected = '';
      				var json = JSON.parse(lista);
      				
      				for(var p = 0; p < json.length; p++){

      					if( json[p].selecionado == 1  )
      				  	     selected = 'selected';
      					 else selected = '';

      					option += '<option value=' + json[p].id_site + ' ' + selected + '>' + json[p].nome + '</option>';
      				}
      				$("#id_site").html(option).show();  
      			}
      		}).fail(function( xhr, status, errorThrown ){
      			// alert('Erro ao buscar Cliente: ' + xhr.responseText);
 				var iconi = "error";
 				var tituloPrincipal = "Erro ao buscar Cliente";
 				var textoPrincipal = xhr.responseText;
 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
      			
      			
      		});    	   
       }
}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
jQuery(function($) {
	  $(document).on('keypress', 'input.only-number', function(e) {
	    var $this = $(this);
	    var key = (window.event)?event.keyCode:e.which;
	    var dataAcceptDot = $this.data('accept-dot');
	    var dataAcceptComma = $this.data('accept-comma');
	    var acceptDot = (typeof dataAcceptDot !== 'undefined' && (dataAcceptDot == true || dataAcceptDot == 1)?true:false);
	    var acceptComma = (typeof dataAcceptComma !== 'undefined' && (dataAcceptComma == true || dataAcceptComma == 1)?true:false);

			if((key > 47 && key < 58)
	      || (key == 46 && acceptDot)
	      || (key == 44 && acceptComma)) {
	    	return true;
	  	} else {
	 			return (key == 8 || key == 0)?true:false;
	 		}
	  });
	});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$( function() {
	  
	  $("#dt_inicio").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'PrÃ³ximo',
		    prevText: 'Anterior'
		});
} );    
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$( function() {
	  
	  $("#dt_final").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'PrÃ³ximo',
		    prevText: 'Anterior'
		});
} );


/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function pesquisaContradoID(  ){
 	 var urlAction  = document.getElementById("formContrato").action;
 	 var idContrato = document.getElementById("pequisaContrato").value;

 	if( idContrato != null && idContrato != '' && idContrato.trim() != '' ){
 	   if( isNumber( idContrato.trim() ) ){
			 $.ajax({
		 			method : "get",
		 			url : urlAction,
		 			data : "idContrato=" + idContrato + '&acao=pesquisaContradoID',
		 			success: function(lista){
		 				var json = JSON.parse(lista);
		                if(json !== "NAOEXISTE"){		
		                	verClienteSelecionado( json[1], '' )
		                }else{
		                	mostraMensagemTela( 'Pequisa Contrato ID!', 'O ID ' + idContrato + ', não foi encontrado na base de dados!' );
		                }
		 			}
		 	 }).fail(function( xhr, status, errorThrown ){
		 			// alert('Erro pesqusa Contrato por ID: ' + xhr.responseText);
	 				var iconi           = "error";
	 				var tituloPrincipal = "Erro: Pesquisa Contrado pelo ID.";
	 				var textoPrincipal  = xhr.responseText;
	 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  	 		 			
		 	 }); 
 	   }else{
           mostraMensagemTela( 'Erro: pequisa Contrato ID!', 'Para realizar a pesquisa pelo ID do contrato, digite somente caracteres Numéricos.' );
       }
   
   }
 }
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function mostraMensagemTela( titulo, texto ) {

    swal({
        title: titulo,
        text: texto,
//        timer: 2000,
        button: true
      }).then(
        function() {},
        // handling the promise rejection
        function(dismiss) {
          if (dismiss === 'timer') {
            console.log('I was closed by the timer')
          }
        }
      )	
	
}	

function MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal ) {
// icon
//  	"warning"
//  	"error"
//  	"success"
//  	"info"

	  Swal.fire({
		    icon  : iconi                             ,
		    title : tituloPrincipal                   ,
		    text  : textoPrincipal                    ,
		    target: document.getElementById(nomeModal),
		    }
		);
	
}
function MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal ) {
// icon
//	  	"warning"
//	  	"error"
//	  	"success"
//	  	"info"

		  Swal.fire({
			    icon  : iconi                             ,
			    title : tituloPrincipal                   ,
			    text  : textoPrincipal                    ,
			    }
			);
		
	}

function AlerataMensagensModal( tituloPrincipal, textoPrincipal,  nomeModal ) {
	  Swal.fire({
		  title            : tituloPrincipal,
		  text             : textoPrincipal,
		  target           : document.getElementById( nomeModal ),
		  showDenyButton   : true,
//		  showCancelButton : true,
		  confirmButtonText: "Sim",
		  denyButtonText   : "Cancelar"
		}).then((result) => {
		  if (result.isConfirmed) {
		     return true;
		  } else if (result.isDenied) {
		    return false;
		  }
		});	  
	
}
/******************************************************************/
/*                                                                */
/*  Funcao para realizar as validacoes antes do Submit.           */
/*                                                                */
/******************************************************************/
$( "#formContrato" ).submit(function( event ) {
 	 var urlAction        = document.getElementById("formContrato"      ).action;
 	 var idStatusContrato = document.getElementById("id_status_contrato").value;
 	 var idContrato       = document.getElementById("id_contrato"       ).value;

 	 if( idContrato != null && idContrato != '' && idContrato.trim() != '' ){
			 $.ajax({
		 			method : "get",
		 			url : urlAction,
		 			data : "acao=cancelaContrato&idContrato=" + idContrato + '&idStatusContrato='+ idStatusContrato,
		 			success: function(lista){
		 				var json = JSON.parse(lista);
		 				
		 				if( json[0] !== 'STATUSNAOALTERADO' ){
		 					
		 					  Swal.fire({
		 						      icon             : "info" ,
									  title            : json[0],
									  text             : json[1],
									  showDenyButton   : true,
							//		  showCancelButton : true,
									  confirmButtonText: "Sim",
									  denyButtonText   : "Cancelar"
									}).then((result) => {
									  if (result.isConfirmed) {
									     //return true;
									     efetivaCancelamentoContratoAfins( idContrato );
									  } else if (result.isDenied) {
									    return false;
									  }
									});	  
		 				}else{		 					
		 					document.getElementById("formContrato").submit();	  
		 				}
		 			}
		 	 }).fail(function( xhr, status, errorThrown ){
		 			//alert('Erro Cancela Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText);
	 				var iconi           = "error";
	 				var tituloPrincipal = "Erro Cancelar Contrato";
	 				var textoPrincipal  = 'Erro ao Cancela o Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText;
	 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
		 	 }); 
   }else 
	   document.getElementById("formContrato").submit();
 	
	  event.preventDefault();
});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function efetivaCancelamentoContratoAfins( idContrato ){
 	 var urlAction  = document.getElementById("formContrato").action;
 	 var idStatusContrato = document.getElementById("id_status_contrato").value;
	 $.ajax({
 			method : "get",
 			url : urlAction,
 			data : "acao=efetivaCancelamentoContratoAfins" + '&idContrato=' + idContrato + '&idStatusContrato=' + idStatusContrato,
 			success: function(lista){
 				var json = JSON.parse(lista);
 				var iconi = "success";
 				let tituloPrincipal = "Cancelamento de Contrato";
 				let textoPrincipal = json;
 				if( json === 'STATUS4' ){
 					tituloPrincipal = "Atualização de Contrato";
 					textoPrincipal  = "Contrato atualizado com sucesso!";
 				}
 				
 				var nomeModal = "";
 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
  	}
 	}).fail(function( xhr, status, errorThrown ){
 			// alert('Erro pesqusa Contrato por ID: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "ERRO";
 			var textoPrincipal  = "Erro: Efetiva Cancelamento Contrato Afins: " + xhr.responseText;
 			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  

 	}); 

 }

 
 const input = document.getElementById('pequisaContrato');

 input.addEventListener('keypress', function(event) {
   if (event.key === 'Enter') {
     event.preventDefault();
     pesquisaContradoID(  );
   }
 });


 
 function PaginacaoInicialCliente( pag ) {
	 var urlAction = document.getElementById("formContrato").action;

	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : 'acao=paginarPesquisaCliente&pag=' + pag,
  			success: function(lista){
  				var json = JSON.parse( lista );
  				var totalPag = 0;
  				var urlPag = '';
  				
   				$('#tabelaResutado > tbody > tr').remove();
   				
   				for(var p = 0; p < json.length; p++){
   					$('#tabelaResutado > tbody').append(
   							            '<tr>  <td>' + json[p].id_cliente   + ' </td> '
   							               + ' <td>' + json[p].razao_social + ' </td> '
   							               + ' <td>' + json[p].alias        + ' </td> '
   							               + ' <td>'
   							               +     '<button onclick="verClienteSelecionado('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-outline-success" data-dismiss="modal">Selecionar</button>'
   							               + '</td>'
   							           +'</tr>'); 
   					totalPag = json[p].totalPagCli;
   				}
   				
   				$('#navegacaoPag > ul').remove();
   				var liPaginacao = '<ul class="pagination">';
   				for(var p = 0; p < totalPag; p++){
   					  if( pag === p )
   					      liPaginacao +=  "<li class=\"page-item active\" onclick=\"PaginacaoInicialCliente(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
   					  else  
   						liPaginacao +=  "<li class=\"page-item\" onclick=\"PaginacaoInicialCliente(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
//			    	   urlPag = urlAction + "?acao=paginar&pag=" + p;
//			    	   liPaginacao +=  "<li class=\"page-item\"><a class=\"page-link\" href=\""+urlPag+"\">"+(p+1)+"</a></li>";
   				}
   				
   				liPaginacao += "</ul>";
   				
   				$("#navegacaoPag").html(liPaginacao).show(); 
   				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
   			}
  	 }).fail(function( xhr, status, errorThrown ){
  			alert('Erro buscando quantitade de paginacao de clietes: ' + xhr.responseText);
  	 }); 

}


 $('#modalClienteContrato').on('shown.bs.modal', function (e) {
	 
	   PaginacaoInicialCliente( 0 );
}) 

  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/ 
  function habilitaStatusMotivoRascunho() {
	
	var idStatus            = document.getElementById("id_status_contrato"  ).value;
	var inputid_rascunho    = document.querySelector("#id_rascunho");
	var inputmotivoRascunho = document.querySelector("#motivoRascunho");
	if(idStatus === "4"){
		inputid_rascunho.disabled    = false;
		inputmotivoRascunho.disabled = false;
	}else{
		inputid_rascunho.disabled    = true;
		inputmotivoRascunho.disabled = true;
		$("#id_rascunho"   ).val('');
		$("#motivoRascunho").val('');
	}	
  }

</script>

</body>

</html>
