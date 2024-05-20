<%@page import="br.com.portal.model.ModelRecursoContrato"%>
<%@page import="br.com.portal.model.ModelRecursoContratoAditivoRel"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
        
<%@ taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

   <style>
       .mesmo-tamanho-botao {
           width: 22%;
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

                                      <!-- ===================================================================== -->
                                       <!-- ===================================================================== -->
                                       <!-- ===================================================================== -->
                                       <!-- Usar para construir uma nova PG apartir da PG padrao,
                                            da DIV <div class="row"> ate a <div class="card-block"> -->
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<!-- Aqui eh onde comeca a montar todos os elementos pequenos da pagina do corpo. -->
														<h4 class="sub-title">Recursos Contratados</h4>
														<form class="form-material" action="<%= request.getContextPath() %>/ServletRecursoContrato" method="post" id="formRetencaoContrato">
																<hr>  
																<br>
	
																<div class="form-row">
																	<!-- Campo ID Contrato -->
																	<div class="form-group form-default form-static-label col-md-6 mb-6">
																		<input type="text" name="id_recurso" id="id_recurso" class="form-control" readonly="readonly" value="${modelRecursoContrato.id_recurso}">
																		<span class="form-bar"></span> 
																		<label class="float-label">ID Recurso</label>
																	</div>
																	<!-- Data Cadastro -->
																	<div class="form-group form-default form-static-label col-md-6 mb-6">
																		<input type="text" name="dt_cadastro" id="dt_cadastro" class="form-control" readonly="readonly" value="${modelRecursoContrato.dt_cadastro}">
																		<span class="form-bar">
																		</span> <label class="float-label">Data Cadastro</label>
																	</div>
																																	
																</div>
	
																<hr>  
																<br>

																<div class="form-row">
																	<!-- Campo ID Contrato -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
																		<input type="text" name="id_contrato" id="id_contrato" style="background: #000080; color: white" class="form-control" readonly="readonly" value="${modelRecursoContrato.id_contrato}">
																		<span class="form-bar"></span> 
																		<label class="float-label">ID Contrato</label>
																	</div>

																	<!-- Campo ID Cliente -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
																		<input type="text" name="id_cliente" id="id_cliente" style="background: #000080; color: white" class="form-control" readonly="readonly" value="${modelRecursoContrato.id_cliente}">
																		<span class="form-bar"></span> 
																		<label class="float-label">ID Cliente</label>
																	</div>
	
																	<!-- Data Cliente -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
																		<input type="text" name="nomeCliente" id="nomeCliente" style="background: #000080; color: white" class="form-control" readonly="readonly" value="${modelRecursoContrato.nomeCliente}">
																		<span class="form-bar">
																		</span> <label class="float-label">Cliente</label>
																	</div>

																</div>
															    <hr>  
																<br>

																<div class="form-row">
																	<!-- Campo Status -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
																		<select name=id_status_recurso id="id_status_recurso" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Status</option>
																			        <tagsContrato:listaStatusRecurso />
																		</select> <label class="float-label">Status</label>
																	</div>
	
																	<!-- Campo Retenção Backup -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
																		<select name="id_retencao_backup" id="id_retencao_backup" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Retenção</option>
																			        <tagsContrato:listaRetencaoBackup />    
																		</select> <label class="float-label">Retenção Backup</label>
																	</div>

																	<!-- Campo TAG Vcenter -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                             <input type="text" name="tag_vcenter" id="tag_vcenter" class="form-control" required="required" placeholder="TAG da Instancia no Vcenter" value="${modelRecursoContrato.tag_vcenter}">
			                                                             <span class="form-bar"></span>
			                                                             <label class="float-label">TAG da Instancia no Vcenter</label>
																	</div>

																</div>
	
																<hr>  
																<br>
	
																<div class="form-row">
																	<!-- Campo Nuvem -->
																	<div class="form-group form-default form-static-label col-md-3 mb-4">
																		<select name="id_tipo_disco" id="id_tipo_disco" class="form-control" required="required" >
																			<option value="" disabled selected>Selecione Tipo Disco</option>
																			        <tagsContrato:listaTipoDisco />
																		</select> <label class="float-label">Tipo Disco</label>
																	</div>
	
																	<!-- Campo Site -->
																	<div class="form-group form-default form-static-label col-md-3 mb-4">
																		<select name="id_so" id="id_so" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Sistema Operacional</option>
																			      <tagsContrato:listaSistemaOperacional /> 
																		</select> <label class="float-label">Sistema Operacional</label>
																	</div>
	
																	<!-- Campo Ambiente -->
																	<div class="form-group form-default form-static-label col-md-3 mb-4">
																		<select name="id_ambiente" id="id_ambiente" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Ambiente</option>
																			       <tagsContrato:listaAmbiente /> 
																		</select> <label class="float-label">Ambiente</label>
																	</div>
	
																	<!-- Campo Tipo Serviçoo -->
																	<div class="form-group form-default form-static-label col-md-3 mb-4">
																		<select name="id_tipo_servico" id="id_tipo_servico" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Tipo Serviço</option>
																			        <tagsContrato:listaTipoServico />
																		</select> <label class="float-label">Tipo Serviço</label>
																	</div>
																</div>
																<hr>  
																<br>
	
																<div class="form-row">
																	<!-- Campo ID Contrato -->
																	<div class="form-group form-default form-static-label col-md-6 mb-6">
																		<input type="text" name="id_nuvem" id="id_nuvem" style="background: #000080; color: white" class="form-control" readonly="readonly" value="${modelRecursoContrato.nuvem}">
																		<span class="form-bar"></span> 
																		<label class="float-label">Nuvem</label>
																	</div>

																	<!-- Campo Família Flavors -->
																	<div class="form-group form-default form-static-label col-md-6 mb-4">
																		<select name="id_familia_flavors" id="id_familia_flavors" class="form-control" required="required">
																			<option value="" disabled selected>Selecione Família Flavors</option>
																			        <tagsContrato:listaFamiliaFlavores/>
																		</select> <label class="float-label">Família Flavors</label>
																	</div>
	
																</div>
																<br>

																<div class="form-row">
				                                                    <!-- Campo CPU --> 
				                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
																		<input type="text" name="cpu" id="cpu" class="form-control" required="required" placeholder="CPU" value="${mdFamilias.cpu}"> 
																		<span class="form-bar"></span> 
																		<label class="float-label">CPU</label>
				                                                    </div>
				                                                    <!-- Campo RAM --> 
				                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
																		<input type="text" name="ram" id="ram" class="form-control" readonly="readonly" placeholder="RAM" value="${mdFamilias.ram}"> 
																		<span class="form-bar"></span> 
																		<label class="float-label">RAM</label>
				                                                    </div>
																
																	<!-- Campo Valor Família Flavors -->
																	<div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                             <input type="text" name="valor" id="valor" class="form-control" readonly="readonly" placeholder="Valor" value="${mdFamilias.valor}">
			                                                             <span class="form-bar"></span>
			                                                             <label class="float-label">Valor Família Flavors</label>
																	</div>
	
																</div>

	
																<hr>  
																<br>
	
															    <div class="form-group form-default form-static-label mb-6">
																	<label for="observacao">Observações</label>
																	<textarea class="form-control" id="obs" name="obs" placeholder="Observações" rows="100" >${modelRecursoContrato.obs}</textarea>
																</div>
	
																<hr>  
																<br>
																
															<!-- Declaracoes dos Botoes -->	
															
															<button type="button" class="btn btn-primary mesmo-tamanho-botao" name="btnovo"  id="btnovo" onclick="limparForm();">Novo</button>	
													        <button type="submit" class="btn btn-success mesmo-tamanho-botao" name="btsalvar" id="btsalvar"onClick="return vazio()">Salvar</button>
													        <a href="<%= request.getContextPath() %>/ServletContratoController?acao=buscarContratoCliente&idContratoCliente=${modelRecursoContrato.id_cliente}" class="btn btn-light float-right mesmo-tamanho-botao" id="btvoltarcontrato" >Voltar para o Contrato</a>

															</form>
													</div>
												</div>
											</div>
										</div>
										
										
										<!-- Campo destinado a mensagem na Tela -->
										<div class="alert alert-primary alert-dismissible fade show" role="alert">
                                           <strong>Resultado: </strong> ${msg}
                                           <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                             <span aria-hidden="true">&times;</span>
										  </button>
										</div>

										
                                       <hr>
	                                    <div class="row">
	                                       <div class="col-sm-12">
	                                           <!-- Basic Form Inputs card start -->
	                                           <div class="card">
	                                                <div class="card-block">
	                                                	 <h4 class="h4stilo">Recursos Contratados</h4>
													     <br>
					                                    <div style="height: 300px; overflow: scroll;">
															<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoRecurso" class="table table-striped">
															  <thead>
															    <tr>
															      <th scope="col">ID Recurso</th>
															      <th scope="col">Cliente</th>
															      <th scope="col">Status</th>
															      <th scope="col">Retenção Backup</th>
															      <th scope="col">Tipo Disco</th>
															      <th scope="col">SO</th>
															      <th scope="col">Ambiente</th>
															      <th scope="col">Família</th>
															      <th scope="col">Tipo Serviço</th>
															    </tr>
															  </thead>
															  <tbody>
															      
					                                              <c:forEach items="${modelRecContRels}" var="tl">
					                                                   <tr>
					                                                     <td> <c:out value="${tl.id_recurso}">        </c:out> </td>
					                                                     <td> <c:out value="${tl.razao_social}">        </c:out> </td>
					                                                     <td> <c:out value="${tl.status_recurso}">      </c:out> </td>
					                                                     <td> <c:out value="${tl.retencao_backup}">     </c:out> </td>
					                                                     <td> <c:out value="${tl.tipo_disco}">          </c:out> </td>
					                                                     <td> <c:out value="${tl.sistema_operacional}"> </c:out> </td>
					                                                     <td> <c:out value="${tl.ambiente}">            </c:out> </td>
					                                                     <td> <c:out value="${tl.familia}">             </c:out> </td>
					                                                     <td> <c:out value="${tl.tipo_servico}">        </c:out> </td>

					                                                     <td><a type="button" class="btn btn-info" href="<%= request.getContextPath() %>/ServletRecursoContrato?acao=buscarEditar&idContrato=${tl.id_contrato}&idRecurso=${tl.id_recurso}">Editar</a></td>
					                                                   </tr>
					                                              </c:forEach>
					                                              
															  </tbody>
															</table>
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
    
    <jsp:include page="javascriptfile.jsp"></jsp:include>

<script type="text/javascript" >
/*
 $("#valor").maskMoney({showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."});

const formatter = new Intl.NumberFormat('pt-BR', {
    currency : 'BRL',
    minimumFractionDigits : 2
});

*/

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function limparForm() {
	var elementos = document.getElementById("formRetencaoContrato").elements;
	
	for(p = 0; p < elementos.length; p++){
		if( elementos[p].id != "id_cliente" && elementos[p].id != "nomeCliente" && elementos[p].id != "id_contrato" && elementos[p].id != "id_nuvem" ){
		    elementos[p].value = '';
		}
	}
}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
/*
jQuery(function($) {
	  $(document).on('keypress', 'input.only-number', function(e) {
		  
		  alert("Ola");
	    var $this           = $(this);
	    var key             = (window.event)?event.keyCode:e.which;
	    var dataAcceptDot   = $this.data('accept-dot');
	    var dataAcceptComma = $this.data('accept-comma');
	    var acceptDot       = (typeof dataAcceptDot   !== 'undefined' && (dataAcceptDot   == true || dataAcceptDot   == 1)?true:false);
	    var acceptComma     = (typeof dataAcceptComma !== 'undefined' && (dataAcceptComma == true || dataAcceptComma == 1)?true:false);

		  if((key > 47 && key < 58)
	      || (key == 46 && acceptDot)
	      || (key == 44 && acceptComma)) {
	    	return true;
	  	} else {
	 		return (key == 8 || key == 0)?true:false;
	 	}
	  });
	});
*/
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$(document).ready(function () {

    $('#id_familia_flavors').change(function () {

        var es        = document.getElementById('id_familia_flavors');
        idFamiliaFl   = es.options[es.selectedIndex].value;
        var urlAction = document.getElementById("formRetencaoContrato").action;
        
  		$.ajax({
  			method : "get",
  			url    : urlAction,
  			data   : "idFamiliaFl=" + idFamiliaFl + '&acao=montaCamosFamiliaFl',
  			success: function(lista){
  				var json = JSON.parse(lista);

  				var cpu = json.cpu;
  				var ram = json.ram;
  				var vlr = json.valor;

  				$("#cpu").val(cpu);
  				$("#ram").val(ram);
  				$("#valor").val(vlr);

  //				$("#id_familia_flavors").html(option).show();  
  			}
  		}).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
  		}); 
    });
});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
/*
function montaSelectSite( idSiteSelecionado ){
    var idNuvem = document.getElementById("id_nuvem").value;	
    
    document.getElementById('cpu').value  =''; // Limpa o campo
    document.getElementById('ram').value  =''; // Limpa o campo
    document.getElementById('valor').value=''; // Limpa o campo
    
   if( idNuvem != null && idNuvem != '' && idNuvem.trim() != '' ){
	   var urlAction = document.getElementById("formRetencaoContrato").action;

  		$.ajax({
  			
  			method : "get",
  			url : urlAction,
  			data : "idNuvem=" + idNuvem + '&acao=montaSelectSite',
  			success: function(lista){
  				var option = '<option value="" disabled selected>Selecione Família Flavors</option>';
  				var selected = '';
  				var json = JSON.parse(lista);
  				
  				for(var p = 0; p < json.length; p++){
  					
  					if( idSiteSelecionado == json[p].id_familia_flavors )
  				  	     selected = 'selected';
  					 else selected = '';

  					option += '<option value=' + json[p].id_familia_flavors + ' ' + selected + '>' + json[p].familia + '</option>';
  				}
  				$("#id_familia_flavors").html(option).show();  
  			}
  		}).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao buscar Família Flavors: ' + xhr.responseText);
  		});    	   
   }
}
*/

</script>

    
</body>

</html>
