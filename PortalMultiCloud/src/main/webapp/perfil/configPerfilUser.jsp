<%@page import="br.com.portal.modelPerfil.ModalPerfil"%>
<%@page import="br.com.portal.model.ModelContrato"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/principal/head.jsp"></jsp:include>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

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
														<form class="form-material" action="<%= request.getContextPath() %>/ServletsPerfilUser" method="post" id="formPerfilUser">
                                                           
															<div class="form-row">
																<!-- Campo ID Contrato -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																	<span class="text-info font-weight-bold font-italic">ID Usuário</span>
																	<input class="form-control text-info font-italic" type="text" name="id_usuario" id="id_usuario" readonly="readonly" value="${modelPerfilUser.id_colaboradores}">
																</div>
																<!-- Data Cadastro -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Data Cadastro</span>
																	<input class="form-control text-info font-italic" type="text" name="dt_criacao"    id="dt_criacao"    readonly="readonly" value="${modelPerfilUser.dt_criacao}">
																</div>
																<!-- Login Cadastro --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <span  class="text-info font-weight-bold font-italic">Login Cadastro</span>
			                                                        <input class="form-control text-info font-italic" type="text" name="user_cadastro" id="user_cadastro" readonly="readonly" value="${modelPerfilUser.login_cadastro}">			                                                        
			                                                    </div>
																
															</div>
															
															<div class="form-row">
																<!-- Campo ID Contrato -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																	<span class="text-info font-weight-bold font-italic">Login</span>
																	<input class="form-control text-info font-italic" type="text" name="login" id="login" readonly="readonly" value="${modelPerfilUser.login}">
																</div>
																<!-- Data Cadastro -->
																<div class="form-group form-default form-static-label col-md-4 mb-6">
																    <span  class="text-info font-weight-bold font-italic">Perfil Cad</span>
																	<input class="form-control text-info font-italic" type="text" name="perfil"    id="perfil"    readonly="readonly" value="${modelPerfilUser.nome_perfil}">
																</div>
																<!-- Login Cadastro --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <span  class="text-info font-weight-bold font-italic">Usuário Admin</span>
			                                                        <input class="form-control text-info font-italic" type="text" name="userAdmin" id="userAdmin" readonly="readonly" value="${modelPerfilUser.useradmin}">			                                                        
			                                                    </div>
																
															</div>
															
														    <hr>  
															<br>
																														
															<div class="form-row">
															   
															   <div class="row mb-3 col-md-12">
															       <label for="userperfil" class="col-md-2 col-form-label">Usuário</label>
															       <div class="col-sm-10">
															          <input type="text" name="userperfil" id="userperfil" class="form-control" placeholder="Digite o nome do Usuário" onkeyup="carregaNomeUser( this.value );" autocomplete="off" required="required" value="">
															          <span id="resultado-pesquisa"></span>
															       </div>
															   </div>
															</div>

														    <hr>  
															<br>

															<div class="form-row">
																<!-- Campo Seção -->
																<div class="form-group form-default form-static-label col-md-12 mb-3">
																    <span class="font-weight-bold font-italic" style="color: #708090">Perfil</span>
																	<select style="color: #B0C4DE" name="Selectperfil" id="Selectperfil" class="form-control" onchange="ListaConteudoPerfil(  );" required="required">
																		<option value="" disabled selected>[-Selecione-]</option>
																		    <tagsContrato:listaPerfil/>
																	</select> 
																</div>
															</div>

														    <hr>  
															<br>

															<!-- Declaracoes dos Botoes -->

												            <button type="submit" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" name="btsalvar" id="btSalvar">Salvar</button>

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
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>


 <script type="text/javascript">


//In your Javascript (external .js resource or <script> tag)
 $(document).ready(function() {
     $('.select2').select2();
 });
 
function carregaNomeUser( valor ) {
	
	 if( valor.length > 2 ){
         var urlAction      = document.getElementById("formPerfilUser" ).action;
		 $.ajax({
	 			method : "get",
	 			url : urlAction,
	 			data : "nome=" + valor + '&acao=ListaUser',
	 			success: function(lista){
	 			    var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		        var nomePerfilAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	
	 		        	    if( json[p].nome_perfil !== undefined  ){
	 		        	    	nomePerfilAux = json[p].nome_perfil;
	 		        	    } else nomePerfilAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosUser(" +  json[p].id_colaboradores + ",'" +  json[p].nome + "','" +  json[p].dt_criacao + "','" +  json[p].login_cadastro + "','" +  nomePerfilAux + "','" + json[p].login + "','" + json[p].useradmin +"')\">" + json[p].nome + "</li>";
	 		        	}
	 		        	
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do PHP
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhum Usuário encontrado!</li>";
	 		        }
	 		        
	 		        // Fechar a lista de produtos 
	 		        conteudoHTML += "</ul>";

	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa').innerHTML = conteudoHTML;

	  			}
	 	 }).fail(function( xhr, status, errorThrown ){
	 			alert('Erro carregar Usuários: ' + xhr.responseText);
	 	 }); 
		
		
		
	 }else {
	        // Fechar a lista de produtos ou o erro
	        document.getElementById("resultado-pesquisa").innerHTML = "";
	 }
}
 
 function getDadosUser( idColaboradores, nome, dtCriacao, userCadastro, perfil, login, userAdmin ) {
	    document.getElementById("id_usuario"   ).value = idColaboradores;
	    document.getElementById("userperfil"   ).value = nome;
	    document.getElementById("dt_criacao"   ).value = dtCriacao;
	    document.getElementById("user_cadastro").value = userCadastro;
	    
	    document.getElementById("perfil"       ).value = perfil;
	    document.getElementById("login"        ).value = login;
	    document.getElementById("userAdmin"    ).value = userAdmin;

	    // Fechar a lista de produtos ou o erro
	    document.getElementById("resultado-pesquisa").innerHTML = "";
 }   

 
 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function ListaConteudoPerfil(  ){
	 var urlAction = document.getElementById("formPerfilUser"  ).action;
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
  	  						
  	    			html += '</tr>';
   				}
  				$("#ItemPerflPagina tbody").html(html).show();  
   			}
  	 }).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao Listar Conteudo Perfil: ' + xhr.responseText);
  	 }); 
 };



</script>

</body>

</html>


