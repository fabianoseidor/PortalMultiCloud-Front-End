<%@page import="br.com.portal.model.ModelContrato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

    
<!DOCTYPE html>
<html lang="en">

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
														<h4 class="sub-title">Manutenção da Família Flavors</h4>
														<form class="form-material" action="<%= request.getContextPath() %>/ServletsFamiliaFlavors" method="post" id="formFamiliaFlavors">
                                                            
															<div class="form-row">
																<!-- Campo ID Contrato -->
																<div class="form-group form-default form-static-label col-md-6 mb-6">
																	<span class="text-info font-weight-bold font-italic">ID Família Flavors</span>
																	<input class="form-control text-info font-italic" type="text" name="id_familia_flavors" id="id_familia_flavors" readonly="readonly" value="${listFamiliaFlavors.id_familia_flavors}">
																	<!--  <label class="float-label">ID Contrato</label> -->
																</div>

																<!-- Data Cadastro -->
																<div class="form-group form-default form-static-label col-md-6 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
																	<input class="form-control text-info font-italic" type="text" name="dt_criacao" id="dt_criacao" readonly="readonly" value="${listFamiliaFlavors.dt_criacao}">
																	<!-- <label class="float-label">Data Cadastro</label> -->
																</div>
															</div>
															
															<div class="form-row">
																
																<!-- Campo Fase do Aditivo -->
																<div class="form-group form-default form-static-label col-md-12 mb-6">
																	<select name="id_nuvem" id="id_nuvem" class="form-control" required="required" onchange="infoSite();" >
																		<option value="" disabled selected>[-Selecione-]</option>
																		   <tagsContrato:listaNuvemFamiliaFlavors />
																	</select> <label class="float-label">Nuvem</label>
																</div>		
																													
															</div>
															
															
															<div class="form-row">
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">Família</span>
																	<input style="color: #B0C4DE" type="text" name="familia" id="familia" maxlength="250" class="form-control" required="required" placeholder="Informe a Família" value="${listFamiliaFlavors.familia}"> 
																	<!-- <label class="float-label">PEP</label> -->
			                                                    </div>
			                                                    
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">CPU</span>
																	<input style="color: #B0C4DE" type="text" name="cpu" id="cpu" maxlength="10" class="form-control" required="required" placeholder="Informe CPU" value="${listFamiliaFlavors.cpu}"> 
																	<!-- <label class="float-label">PEP</label> -->
			                                                    </div>
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">RAM</span>
																	<input style="color: #B0C4DE" type="text" name="ram" id="ram" maxlength="10" class="form-control" required="required" placeholder="Informe RAM" value="${listFamiliaFlavors.ram}"> 
																	<!-- <label class="float-label">PEP</label> -->
			                                                    </div>
			                                                    <div class="form-group form-default form-static-label col-md-3 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">Valor</span>
																	<input style="color: #B0C4DE" type="text" name="valor" id="valor" class="form-control" required="required" placeholder="Informe Valor" value="${listFamiliaFlavors.valor}"> 
																	<!-- <label class="float-label">PEP</label> -->
			                                                    </div>
			                                                    
															</div>
															

															<div class="form-row">
															    <div class="form-group form-default form-static-label col-md-12 mb-12">
																	<span class="font-weight-bold font-italic" style="color: #708090">Observação</span>
																	<textarea style="color: #B0C4DE" class="form-control" id="observacao" name="observacao" placeholder="Observação" rows="100" >${listFamiliaFlavors.observacao}</textarea>
																</div>
															</div>

														    <hr>  
															<br>

															<!-- Declaracoes dos Botoes -->
 											                <button type="button" class="btn btn-primary waves-effect waves-light mesmo-tamanho-botao" name="novo" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" name="salvar" id="btSalvar">Salvar</button>

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
										<h4 class="sub-title">Lista das Familias Flavors</h4>
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">

														<div class="tab-pane fade show active" id="tabelaNuvem" role="tabpanel" aria-labelledby="recurso-tab">
                                                             <div style="height: 300px; overflow: scroll;">
															<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
															  <thead>
															    <tr>
															      <th scope="col">ID Familia   </th>
															      <th scope="col">Data Criação </th>
															      <th scope="col">Família      </th>
															      <th scope="col">CPU          </th>
															      <th scope="col">RAM          </th>
															      <th scope="col">Valor        </th>
															      <th scope="col">Obs.         </th>
															      <th scope="col">Editar       </th>
															      <th scope="col">Deletar      </th>
															    </tr>
															  </thead>
															  <tbody>
														        <!-- Informacao ser'a preenchida pelo Onchena onchange do Select da Nuvem -->
														          <c:forEach items="${listFamiliaFlavorss}" var="lrc">
				                                                   <tr>
				                                                     <td> <c:out value="${lrc.id_familia_flavors}" > </c:out> </td>
				                                                     <td> <c:out value="${lrc.dt_criacao}"         > </c:out> </td>
				                                                     <td> <c:out value="${lrc.familia}"            > </c:out> </td>
				                                                     <td> <c:out value="${lrc.cpu}"                > </c:out> </td>
				                                                     <td> <c:out value="${lrc.ram}"                > </c:out> </td>
				                                                     <td> <c:out value="${lrc.valor}"              > </c:out> </td>
				                                                     <td> <c:out value="${lrc.observacao}"         > </c:out> </td>
				                                                     <td>
																		<button type="button" class="btn btn-primary" onclick="Editar( ${lrc.id_familia_flavors}, ${lrc.id_nuvem} );">
																		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
																		       <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
																		       <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
																		   </svg> 
																		</button>
																	<td>
																	    <button type="button" class="btn btn-danger" onclick="Delete ( ${lrc.id_familia_flavors}, ${lrc.id_nuvem} )">
																			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
																			  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
																			  <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
																			</svg>
																	    </button>
																	</td>
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
    
    <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>



 <script type="text/javascript">
 
 $("#valor").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

 const formatter = new Intl.NumberFormat('pt-BR', {
     currency : 'BRL',
     minimumFractionDigits : 2
 });
 
 function limparForm() {
		var elementos = document.getElementById("formFamiliaFlavors").elements;
		
		for(p = 0; p < elementos.length; p++){
			elementos[p].value = '';
		}
 }
 
 function limparFormChageNuvem() {
		var elementos = document.getElementById("formFamiliaFlavors").elements;
		
		for(p = 0; p < elementos.length; p++){
			if(elementos[p].id !== 'id_nuvem' )
			   elementos[p].value = '';
		}
}


 function Delete( id, idNuvem  ) {
    if(confirm('Deseja realmente excluir os dados?')) {    	
	   	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServletsFamiliaFlavors"; 
	   	 var parametros = "?acao=deletar&id=" + id + '&idNuvem=' + idNuvem;
	   	 var url = urlAction + parametros;
	   	 window.location.href = url;	    
    }
}

 function Editar( id, idNuvem ) {
   	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServletsFamiliaFlavors"; 
   	 var parametros = "?acao=buscarEditar&id=" + id + '&idNuvem=' + idNuvem;
   	 var url = urlAction + parametros;
   	 window.location.href = url;		    

 }

 
function infoSite() {
	 var idNuvem   = id_nuvem.options[id_nuvem.selectedIndex].value;
	 var urlAction = document.getElementById("formFamiliaFlavors").action;
	 limparFormChageNuvem();

	 $.ajax({
			method : "get",
  			url    : urlAction,
  			data   : 'acao=MontaFamiliaFlavors&idNuvem=' + idNuvem,
  			success: function(lista) {
	  				var json   = JSON.parse(lista);
	  				var html   = '';
	  				var cpuAux = '';
	  				var ramAux = '';
	  				var vlrAux = '';
	  				var obsAux = '';
	  				$('#tabelaNuvem > tbody > tr').remove();
	  				for(var p = 0; p < json.length; p++){
	  					
	  					if( json[p].cpu        !== undefined ) cpuAux = json[p].cpu; else cpuAux = '';
	  					if( json[p].ram        !== undefined ) ramAux = json[p].cpu; else ramAux = '';
	  					if( json[p].valor      !== undefined ) vlrAux = json[p].cpu; else vlrAux = '';
	  					if( json[p].observacao !== undefined ) obsAux = json[p].cpu; else obsAux = '';
	  					
	  					html += '<tr> ' 
	  					            + '<td>' + json[p].id_familia_flavors + '</td>'
	  					            + '<td>' + json[p].dt_criacao         + '</td>'
	  					            + '<td>' + json[p].familia            + '</td>'
	  					            + '<td>' + cpuAux                     + '</td>'
	  					            + '<td>' + ramAux                     + '</td>'
	  					            + '<td>' + vlrAux                     + '</td>'
	  					            + '<td>' + obsAux                     + '</td>'
	  					            
	  					            + '<td>' 
	  					                + ' <button type="button" class="btn btn-primary" onclick="Editar(' +  json[p].id_familia_flavors + ',' + json[p].id_nuvem + ');"> ' 
									    +   ' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16"> ' 
									    +   '   <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/> ' 
									    +   '   <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/> ' 
									    + '   </svg> </button> ' 
								    + '</td>' 
								    + '<td>'  
							            + ' <button type="button" class="btn btn-danger" onclick="Delete (' +  json[p].id_familia_flavors  + ',' + json[p].id_nuvem + ');">' 
									    +	' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"> ' 
									    +	'   <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/> ' 
									    +	'   <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/> '
									    + '	  </svg> </button> '									    
							    + '</td>'
							    
	  					      + '</tr>';	  					      
	  				}
  				    $("#tabelaNuvem tbody").html(html).show();    				    
  			}		 
		 
  	 }).fail(function( xhr, status, errorThrown ){
			alert('Erro pesquisa Nuvem para Familia Flavors: ' + xhr.responseText);
	 });
   
}

</script>

</body>

</html>
