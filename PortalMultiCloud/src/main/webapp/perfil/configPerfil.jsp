<%@page import="br.com.portal.modelPerfil.ModalPerfil"%>
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
           width      : 15%;
           white-space: normal;
       }
       
       .t-modal .modal-content{
           border-top-left-radius    : 10px;
           border-top-right-radius   : 10px;
           
           border-bottom-left-radius : 10px;
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
            height          : 3px;
            background-color: #B0C4DE;
            border          : none;
        }
        


#nameError {
  display: none;
  font-size: 0.8em;
}

#nameError.visible {
  display: block;
}

input.invalid {
  border-color: red;
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
														<h4 class="sub-title">Configuração de Perfil</h4>
														<form class="form-material" action="<%= request.getContextPath() %>/ServeletPerfil" method="post" id="formPerfil">
                                                           
															<div class="form-row">
																<!-- Campo ID Contrato -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																	<span class="text-info font-weight-bold font-italic">ID Perfil</span>
																	<input class="form-control text-info font-italic" type="text" name="id_perfil" id="id_perfil" readonly="readonly" value="${modalPerfil.id_perfil}">
																</div>
																<!-- Data Cadastro -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
																	<input class="form-control text-info font-italic" type="text" name="dt_criacao"    id="dt_criacao"    readonly="readonly" value="${modalPerfil.dt_criacao}">
																</div>
																<!-- Login Cadastro --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <span  class="text-info font-weight-bold font-italic">Login Cadastro</span>
			                                                        <input class="form-control text-info font-italic" type="text" name="user_cadastro" id="user_cadastro" readonly="readonly" value="${modalPerfil.user_cadastro}">			                                                        
			                                                    </div>
																
															</div>
															
															<div class="form-row">
			                                                  
			                                                    <div class="form-group form-default form-static-label col-md-12 mb-6">
																	<span class="font-weight-bold font-italic" style="color: #708090">Perfil</span>
																	<input style="color: #B0C4DE" type="text" name="nome_perfil" id="nome_perfil" maxlength="150" class="form-control" required="required" placeholder="Informe o Perfil" value="${modalPerfil.nome_perfil}"> 
												               </div>
												               
												               <span role="alert" id="nameError" class="font-weight-bold font-italic"  style="color: #F23006; font-size:15px" >Este 'Perfil' já Existe na Base de dados!</span>
												                 
															</div>

															<div class="form-row">
															    <div class="form-group form-default form-static-label col-md-12 mb-12">
																	<span class="font-weight-bold font-italic" style="color: #708090">Observação</span>
																	<textarea style="color: #B0C4DE" class="form-control" id="observacao" name="observacao" placeholder="Observação" rows="100" >${modalPerfil.obs}</textarea>
																</div>
															</div>

														    <hr>  
															<br>

															<!-- Declaracoes dos Botoes -->
 											                <button type="button" class="btn btn-primary waves-effect waves-light mesmo-tamanho-botao" name="btnovo" onclick="limparForm();">Novo</button>
												            <button type="submit" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" name="btsalvar" id="btSalvar">Salvar</button>
 											                <button type="button" class="btn btn-danger waves-effect waves-light mesmo-tamanho-botao" data-toggle="tooltip" data-placement="top" title="Selecione um 'Perfil' a ser Deletado!" name="btExcluir" disabled id="btExcluir" onclick="DeletePerfil();">Excluir</button>
												            

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

										<!-- Perfis Cadastrados -->
										<h4 class="sub-title">Perfil Cadastrado</h4>
										
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
													    <div class="form-row">
													    
															<!-- Campo Seção -->
															<div class="form-group form-default form-static-label col-md-6 mb-3">
															    <span class="font-weight-bold font-italic" style="color: #708090">Perfil</span>
																<select style="color: #B0C4DE" name="Selectperfil" id="Selectperfil" class="form-control" onchange="ListaConteudoPerfil(  ); populaCamposPerfil();" required="required">
																	<option value="" disabled selected>[-Selecione-]</option>
																	    <tagsContrato:listaPerfil/>
																</select> 
															</div>
															
															<div class="form-group form-default form-static-label col-md-6 mb-3">
															<div class="text-center">
													            <%
													                ModalPerfil modalPerfil = (ModalPerfil) request.getAttribute("modalPerfil");
													                String InicioTipoBT        = "<button type=\"button\" id=\"btAddItem\" ";
//													                String classeBT            = " class=\"btn waves-effect waves-light mesmo-tamanho-botao";
													                String classeBT            = " class=\"btn btn-lg btn-block waves-effect waves-light ";
													                String msgAddAditRecuDesa  = " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Só poderá Adicionar um Item ao 'Perfil', se estiver um 'Perfil' em modo de 'Edição'!\" ";
													                String disabledBT          = " disabled ";
													               // String modalBTAditRecu     = " data-toggle=\"modal\" data-target=\"#ModalItemPerfil\" " ;
													                String FinalBT             = " </button>";
													                
																	if ( modalPerfil != null ) {
																		if( !modalPerfil.isNovo() ){ 
																			disabledBT         = ""; 
																			msgAddAditRecuDesa = "";
																		}
																	}
													                
//													                String BtAddItem = InicioTipoBT + classeBT + " btn-dark float-right\""   + msgAddAditRecuDesa + disabledBT +   /* modalBTAditRecu + */ ">Add Item" + FinalBT;
													                String BtAddItem = InicioTipoBT + classeBT + " btn-dark \""   + msgAddAditRecuDesa + disabledBT +   /* modalBTAditRecu + */ ">Add Item" + FinalBT;
													                out.println( BtAddItem );
													                
													                 // System.out.println(BtAddItem);
													            %>
															</div>
															</div>
														
														</div>
														
													</div>
												</div>
											</div>											
										</div>

										<!-- Campo destinado listar os Cliente no pe da tela -->
										
										
										<h4 class="sub-title">Seções Cadastradas</h4>
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
													
                                                         <div style="height: 300px; overflow: scroll;">
													   		<table id="ItemPerflPagina" class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
																  <thead>
																    <tr>
																      <th scope="col">Seção           </th>
																      <th scope="col">Perfil          </th>
																      <th scope="col">Página Acesso   </th>
																      <th scope="col">Botão Novo      </th>
																      <th scope="col">Botão Editar    </th>
																      <th scope="col">Botão Excluir   </th>
																      <th scope="col">Botão Pesquisar </th>
															          <th scope="col">Deletar         </th>
																      
																    </tr>
																  </thead>
																  <tbody>
	   					                                              <!-- Conteudo preenchido por Ajax    -->
	   					                                              <!-- Funcao 'ListaConteudoPerfil();' -->
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
    
    <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>

    <!-- Modal Add Produto -->
   <div class="modal t-modal primary" id="ModalItemPerfil" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	    
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Add Item Perfil</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
          <!-- Inicio do corpo do Modal -->
	      <div class="modal-body">
	      
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">
						
							<div class="form-row">
                                <!-- Campo Id Perfil ==> Escondido --> 
                                 <input type="text" name="idPerfilModa" id="idPerfilModa" hidden="hidden">
                                
								<!-- Campo ID Contrato -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
									<span class="text-info font-weight-bold font-italic">Perfil</span>
									<input class="form-control text-info font-italic" type="text" name="nomePerfilModa" id="nomePerfilModa" readonly="readonly" >
								</div>

								<!-- Data Cadastro -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
								    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
									<input class="form-control text-info font-italic" type="text" name="dtCriacaoModa" id="dtCriacaoModa" readonly="readonly" >
								</div>
								
								<!-- Login Cadastro -->
								<div class="form-group form-default form-static-label col-md-4 mb-6">
								    <span  class="text-info font-weight-bold font-italic">Login Cadastro</span>
									<input class="form-control text-info font-italic" type="text" name="loginCadastroModa" id="loginCadastroModa" readonly="readonly" >
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
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">
							 <div class="form-group form-default form-static-label">
								 <label class="font-weight-bold font-italic">Seção</label>
								 <select name="idSecaoModal" id="idSecaoModal" class="form-control" onchange="montaTabelaPaginaSecaoModal(  );">
									<option value="" disabled selected>[-Selecione-]</option>
									   <tagsContrato:listaSecaoModal/>
								</select>
							 </div>
						</div>
					</div>
				</div>
			</div>
		

		    <div class="row">
				<div class="col-sm-12">
				    <div class="card">
					   <div class="card-block">
							<!-- Campo destinado listar os Cliente no pe da tela -->
							<h4 class="sub-title">Itens</h4>
							<div class="row">
								<div class="col-sm-12">
									<div class="card">
										<div class="card-block">
											<div class="tab-pane fade show active" id="tabelaPaginasecao" role="tabpanel" aria-labelledby="recurso-tab">
	                                          <div style="height: 300px; overflow: scroll;">
												<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
												  <thead>
												    <tr>
												      <th scope="col" class="text-center">Selecionar </th>
												      <th scope="col">Seção     </th>
												      <th scope="col">Página    </th>
												      <th scope="col">Obs.      </th>
												    </tr>
												  </thead>
												  <tbody id="lstProdBody">
											        <!-- Informacao inserida de forma dinamica -->
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
				</div>
			</div>
			
	      </div>

	      
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" onclick="addItem();">Add Item</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	      </div>
	      
	    </div>
	  </div>
 </div>




 <script type="text/javascript">

 
 const submit = document.getElementById("btSalvar");

 submit.addEventListener("click", validate);

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function validate(e) {
   e.preventDefault();
   
   const nomePerfilElement = document.getElementById("nome_perfil");
   let valid      = true;
   var urlAction  = document.getElementById("formPerfil"  ).action;
   var nomePerfil = document.getElementById("nome_perfil").value;
 
   
   if (!nomePerfilElement.value) {
	     const nameError = document.getElementById("nameError");
	     document.getElementById("nameError").textContent = 'Informe o nome do perfil';
	     nameError.classList.add("visible");
	     nomePerfilElement.classList.add("invalid");
	     nameError.setAttribute("aria-hidden", false);
	     nameError.setAttribute("aria-invalid", true);
   }else{
	   /**************************************************/
	   $.ajax({ 			
				method : "get",
				url    : urlAction,
				data   : 'acao=ValidaNomePerfilBase&nomePerfil=' + nomePerfil,
				success: function(lista){
					var json = JSON.parse(lista);
					
					if (json === 'EXISTE') {
					    const nameError = document.getElementById("nameError");
					    document.getElementById("nameError").textContent = "Este 'Perfil' já Existe na Base de dados!";
					    nameError.classList.add("visible");
					    nomePerfilElement.classList.add("invalid");
					    nameError.setAttribute("aria-hidden" , false);
					    nameError.setAttribute("aria-invalid", true);
					} else document.getElementById("formPerfil").submit();
				}
		 }).fail(function( xhr, status, errorThrown ){
				alert('Erro ao Popula Campos Perfil: ' + xhr.responseText);
		 }); 
  }
   /**************************************************/

    return valid;
 } 
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function limparForm() {
		var elementos = document.getElementById("formPerfil").elements;
		
		for(p = 0; p < elementos.length; p++){
			elementos[p].value = '';
		}
		$("#Selectperfil"                ).val( '' );
		$('#ItemPerflPagina > tbody > tr').remove();
		document.getElementById("btAddItem").disabled = true;
		
		document.getElementById("btAddItem").textContent = 'Selecione um Perfil para Add um Item';
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function Delete( idSecao, idPerfil, idPag ) {
 	if( confirm('Deseja realmente excluir os dados?') ) {
 		var urlAction = document.getElementById("formPerfil").action;
 		
 		$.ajax({
 			
 			method : "get",
 			url : urlAction,
 			data : "acao=deletaraItemPerfiljax&idSecao=" + idSecao + "&idPerfil=" + idPerfil + "&idPag=" + idPag,
 			success: function(response){

 				ListaConteudoPerfil(  );
 				
 				document.getElementById("msg").textContent = response;
 			}
 			
 		}).fail(function( xhr, status, errorThrown ){
 			alert('Erro ao deletar usuário: ' + xhr.responseText);
 		});
 	}
	}
 
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function DeletePerfil( ) {
    if(confirm('Deseja realmente excluir os dados?')) {  
    	 var idPerfil =  document.getElementById("Selectperfil").value;  	
	   	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServeletPerfil"; 
	   	 var parametros = "?acao=DeletePerfilAjax&idPerfil=" + idPerfil;
	   	 var url = urlAction + parametros;
	   	 window.location.href = url;	    
    }
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function ListaConteudoPerfil(  ){
	 var urlAction = document.getElementById("formPerfil"  ).action;
	 var idPerfil =  document.getElementById("Selectperfil").value;
	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : "idPerfil=" + idPerfil + '&acao=ListaConteudoPerfil',
  			success: function(lista){
  				var json = JSON.parse(lista);
  				var html = '';
  				$('#ItemPerflPagina > tbody > tr').remove();
  				for(var p = 0; p < json.length; p++){
  					html += '<tr> ' 
  					            + '<td>' + json[p].nome_secao   + '</td>'
  					            + '<td>' + json[p].nome_perfil  + '</td>'
  					            + '<td>' + json[p].desc_pagina  + '</td>';  
			  					if( json[p].bt_novo === 1 )
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" checked disabled="disabled" /></th>';
			  					else
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" disabled="disabled" /></th>';
			
			  					if( json[p].bt_editar === 1 )
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" checked disabled="disabled" /></th>';
			  					else
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" disabled="disabled" /></th>';
			
			  	  				if( json[p].bt_escluir === 1 )
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" checked disabled="disabled" /></th>';
			  					else
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" disabled="disabled" /></th>';
			
			  	    			if( json[p].bt_pesquisar === 1 )
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" checked disabled="disabled" /></th>';
			  					else
			  						html +=  '<th scope="col" class="text-center"><input  type="checkbox" disabled="disabled" /></th>';
			  				   html +=  '<td>'  
						            + ' <button type="button" class="btn btn-danger" onclick="Delete (' +  json[p].id_secao  + ',' + json[p].id_perfil + ',' + json[p].id_pag + ');">' 
								    +	' <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16"> ' 
								    +	'   <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/> ' 
								    +	'   <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/> '
								    + '	  </svg> </button> '
						        + '</td>'
  	    			      + '</tr>';
   				}
  				$("#ItemPerflPagina tbody").html(html).show();  
   			}
  	 }).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao Listar Conteudo Perfil: ' + xhr.responseText);
  	 }); 
 };
 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function populaCamposPerfil() {

   	 var urlAction = document.getElementById("formPerfil"  ).action;
	 var idPerfil =  document.getElementById("Selectperfil").value;
	
	 $.ajax({ 			
  			method : "get",
  			url    : urlAction,
  			data   : 'acao=populaCamposPerfil&idPerfil=' + idPerfil,
  			success: function(lista){
  				var json = JSON.parse(lista);
  				$("#id_perfil"    ).val( json.id_perfil     );
  				$("#nome_perfil"  ).val( json.nome_perfil   );
  				$("#dt_criacao"   ).val( json.dt_criacao    );
  				$("#user_cadastro").val( json.user_cadastro );
  				$("#observacao"   ).val( json.obs           );
  				
  				// Altera os atributos 'data-toggle' e 'data-target' para chamar o modal de Adicao de Item
  				var button = document.getElementById('btAddItem');
  				button.setAttribute('data-toggle', "modal");
  				button.setAttribute('data-target', "#ModalItemPerfil");

  				// Trata botoes
  				document.getElementById("btAddItem").disabled = false;
  				$('#btAddItem').tooltip('disable');
  				document.getElementById("btAddItem").textContent = 'Add Item';
  				document.getElementById("btExcluir").disabled = false;
  				$('#btExcluir').tooltip('disable');
  				
   			}
  	 }).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao Popula Campos Perfil: ' + xhr.responseText);
  	 }); 
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 $('#ModalItemPerfil').on('show.bs.modal', function(e){
       var select = document.getElementById("Selectperfil");
       var v_idPerfilModa   = select.options[select.selectedIndex].value;
       var v_nomePerfilModa = select.options[select.selectedIndex].text;
       
	$("#idPerfilModa"   ).val( v_idPerfilModa   );
	$("#nomePerfilModa" ).val( v_nomePerfilModa );
	
	$("#tabelaPaginasecao tbody tr").remove();
	$("#idSecaoModal"              ).val( '' );

 });

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function montaTabelaPaginaSecaoModal(  ) {
	var idSecao    = $("#idSecaoModal option:selected").val();
	var idPerfilMd =  document.getElementById("idPerfilModa").value;
	
	var urlAction = document.getElementById("formPerfil").action;
	$.ajax({
		method : "get",
		url    : urlAction,
		data   : 'acao=montaTabelaPaginaSecaoModal&idSecao=' + idSecao + '&idPerfilMd=' + idPerfilMd,

		success: function(lista){
 				var json = JSON.parse(lista);

 				var html = '';

 				$('#tabelaPaginasecao > tbody > tr').remove();
 				for(var p = 0; p < json.length; p++){
 					
 					html += '<tr> ' // name="id[0]" id="id[0]" 
 						        + "<td class=\"text-center\" \"> <input type=\"checkbox\"  name=\"chck" + p + "\"  id=\"chck" + p + "\" data-id=" + p + " /></td>"
 					            + '<td style="display:none">'   + json[p].id_pag_secao + ' </td>'
 					            + '<td style="display:none"">'  + json[p].id_secao     + ' </td>'
 					            + '<td>' + json[p].dec_secao    + '</td>'
 					            + '<td>' + json[p].desc_pagina  + '</td>'
 					            + '<td>' + json[p].obs          + '</td>'
 					      + '</tr>';
 				}
 				
			$("#tabelaPaginasecao tbody").html(html).show();  
 				document.getElementById("msg").textContent = '';
		}
	 }).fail(function( xhr, status, errorThrown ){
			alert('Erro carregar select Produto: ' + xhr.responseText);
	 }); 
		 
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function InsertItemPerfil( idPagSecao, idSecao, decSecao, descPagina, obs ) {

		var urlAction     = document.getElementById( "formPerfil"   ).action;
		var idPerfil      = document.getElementById( "idPerfilModa" ).value;
		var loginCadastro = document.getElementById( "loginCadastroModa" ).value;

		var dados = 'acao=InsertItemPerfil' +
                    '&idPagSecao='          + idPagSecao   + //  1
                    '&idSecao='             + idSecao	    + //  2
                    '&idPerfil='            + idPerfil	    + //  3
                    '&decSecao='            + decSecao	    + //  4
                    '&descPagina='          + descPagina    + //  5
                    '&loginCadastro='       + loginCadastro + //  6
                    '&obs='                 + obs           ; //  7

		$.ajax({
			method : "get",
			url    : urlAction,
			data   : dados,
			success: function(lista){
						var retornoInsert = JSON.parse(lista);
//						alert(retornoInsert.statusInsert + ' - ' + retornoInsert.user_cadastro + ' - ' + retornoInsert.dt_criacao);
						if( retornoInsert.statusInsert === 'OK' ){
							$("#dtCriacaoModa"    ).val( retornoInsert.dt_criacao   );
							$("#loginCadastroModa").val( retornoInsert.user_cadastro);

						    document.getElementById("msg").textContent = 'Item(ns) inseridos com sucesso!';
						    montaTabelaPaginaSecaoModal(  );
						    ListaConteudoPerfil(  );
						}else 
						   document.getElementById("msg").textContent = 'Erro ao inserir item(ns), favor tentar novamente!';
			}
		 }).fail(function( xhr, status, errorThrown ){
				alert('Erro Cadastro Item Perfil: ' + xhr.responseText);
		 }); 
			 
 }

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function addItem() {
	// Check the status of each data cell in a table
	const table = document.getElementById("tabelaPaginasecao");
	const cells = table.getElementsByTagName("td");
	var retornoString = '';
//	alert( "cells.length: " + cells.length );
	var cont = 0;
	var c = 0;
	var linhas = 0;
	var result = Array(2,6);
	
	
	let dimensao = cells.length / 6; // dimensão da matriz (assumindo que ela é uma matriz quadrada)
	let matriz = Array(dimensao); // array com 3 elementos

	// Inicializa a Matriz.
	for (let i = 0; i < matriz.length; i++) {
	  matriz[i] = Array(6);
	}
    // Faz o loop na tabela
	for (loop = 0; loop < cells.length; loop++) {
	    var item = cells[loop];
	    if( c === 6 ){
            c = 0;
            cont++;
	    }
	    var checkBox = document.getElementById( "chck" + cont );
        // Adiciona na Matriz somente as linhas na tabela que estiverem selecionadas
	    if ( checkBox.type == "checkbox" && checkBox.checked) {
//	         alert( "textContent: " + item.textContent );    
	         matriz[linhas][c] =item.textContent;
	         if( c === 5 ) linhas++;
	    }
	    c++;
    } 
    // Faz o casdasto via Ajax
	let linha, coluna;
	for (linha = 0; linha < linhas; linha++) {
/*        for (coluna = 0; coluna < 6; coluna++) {
		    alert("Linha: " + linha + " - coluna: " + coluna + " - " + matriz[linha][coluna] );
		  } */		  
		  InsertItemPerfil(  matriz[linha][1],  matriz[linha][2],  matriz[linha][3],  matriz[linha][4],  matriz[linha][5] );  
	}
 } 
 


</script>

</body>

</html>


