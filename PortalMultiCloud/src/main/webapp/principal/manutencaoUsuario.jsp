<%@page import="br.com.portal.model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix = "tagsUsuario" %>   
    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>
   <style>
       .mesmo-tamanho-botao {
           width: 15%;
           white-space: normal;
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
	                                                    <h4 class="sub-title">Cadastro de Usuário</h4>
                                                        <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletManutencaoUsuarioController" method="post" id="formManutencaoUser">
	                                                         
	                                                         <input type="hidden" name="acao" id="acao" value="">
	                                                             <div class="form-group form-default input-group mb-4">
                                                                  <div class="input-group-prepend">
                                                                    <c:if test="${modelLogin.fotouser != '' && modelLogin.fotouser != null}">
                                                                       <a href="<%= request.getContextPath() %>/ServletManutencaoUsuarioController?acao=downloadFoto&id=${modelLogin.id_colaboradores}">
	                                                                     <img alt="Imagem User" id="fotoembase64" src="${modelLogin.fotouser}" width="70px">
	                                                                    </a>
                                                                    </c:if>
                                                                    
                                                                    <c:if test="${modelLogin.fotouser == '' || modelLogin.fotouser == null}">
                                                                       <img alt="Imagem User" id="fotoembase64"  src="<%= request.getContextPath() %>/imagens/perfil_avatar.jpg"  width="70px">
                                                        			</c:if>
                                                        			
                                                                  </div>
                                                                  <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
                                                             </div>
															 <br>

	                                                         <div class="form-row">

		                                                         <!-- Campo Id Colaborador --> 
		                                                         <div class="form-group form-default form-static-label col-md-2 mb-3">
		                                                             <input type="text" name="id_colaboradores" id="id_colaboradores" class="form-control" required="required" readonly="readonly" value="${modelLogin.id_colaboradores}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">ID</label>
		                                                         </div>
		                                                         <!-- Campo Nome Colaborador -->
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-3">
		                                                             <input type="text" name="nome" id="nome" class="form-control" placeholder="Informe o nome" required="required" value="${modelLogin.nome}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Nome do Colaborador</label>
		                                                         </div>
		                                                         
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-3">
		                                                             <input type="text" name="login" id="login" class="form-control" placeholder="Informe o Login" required="required" value="${modelLogin.login}">
		                                                             <label class="float-label">Login</label>
		                                                         </div>
																<center>
																  <div class="form-group form-default form-static-label col-md-4 mb-3">
																        <input class="form-check-input " type="checkbox" name="admin" id="admin" value="1" <% 
																        		ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																	          if ( modelLogin != null ) {
																				  if( Integer.valueOf( modelLogin.getUseradmin() ) == 1  ){
																					  out.print(" ");
																					  out.print("checked");
																					  out.print(" ");
																				  }
																	          }
																	  %>>
																        <label class="form-check-label" for="gridCheck1">Administrador</label>
																  </div>
																</center>
	                                                         </div>
	                                                         <br>
															<br>
	                                                         <div class="form-row">
		                                                         <!-- Campo CPF Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="cpf" id="cpf" onkeyup="handleCpfCnpj(event)" class="form-control" maxlength="14" placeholder="Informe o CPF" value="${modelLogin.cpf}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">CPF</label>
		                                                         </div>
		                                                         <!-- Campo Email Colaborador -->
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="email" name="email" id="email" class="form-control" placeholder="Informe o Email" required="required" autocomplete="off" value="${modelLogin.email}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Email</label>
		                                                         </div>
		
		                                                         <!-- Campo Telefône Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="telefone" id="telefone" class="form-control" maxlength="16" onkeyup="handlePhone(event)"  placeholder="Informe o Telefône" value="${modelLogin.telefone}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Telefône</label>
		                                                         </div>
		                                                          <!-- Campo Celular Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="celular" id="celular" class="form-control" onkeyup="handlePhone(event)" required="required" maxlength="15" placeholder="Informe o Celular" value="${modelLogin.celular}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Celular</label>
		                                                         </div>                                                             
	                                                         
	                                                         </div>
	                                                         <br>
				 											 <div class="form-row">
 	                                                              <!-- Campo Cargo Colaborador -->
	                                                              <div class="form-group form-default form-static-label col-md-12 mb-6">
	                                                                <select name="id_cargo" id="id_cargo" class="form-control" >
	                                                                    <option value="" disabled selected>Selecione o Cargo</option>
                                                                        <tagsUsuario:listaCargo/>
	                                                                </select>
	                                                                <label class="float-label">Cargo</label>
	                                                              </div>
															 </div>
	                                                         <br>

	                                                         <div class="form-row">
		                                                         <!-- Campo CEP Colaborador -->
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input onblur="pesquisa();" type="text" name="cep" id="cep" onkeyup="handleZipCode(event)" class="form-control" maxlength="10" placeholder="Informe o CEP" value="${modelLogin.cep}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">CEP</label>
		                                                         </div>
		                                                          <!-- Campo Endereço Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="endereco" id="endereco" class="form-control" maxlength="150" placeholder="Informe o Endereço" value="${modelLogin.endereco}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Endereço</label>
		                                                         </div>                                                             
		                                                          <!-- Campo Número Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="number" name="numero" id="numero" class="form-control" maxlength="5" placeholder="Informe o Número" value="${modelLogin.numero}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Número</label>
		                                                         </div>                                                             
		                                                          <!-- Campo Complemento Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="complemento" id="complemento" class="form-control" maxlength="45" placeholder="Informe o Complemento" value="${modelLogin.complemento}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Complemento</label>
		                                                         </div>                                                              
	                                                         </div>
	                                                         <br>
	                                                         
	                                                         <div class="form-row">
		                                                          <!-- Campo Bairro Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="bairro" id="bairro" class="form-control" maxlength="80" placeholder="Informe o Bairro" value="${modelLogin.bairro}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Bairro</label>
		                                                         </div>                                                             
		                                                          <!-- Campo Cidade Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="cidade" id="cidade" class="form-control" maxlength="45" placeholder="Informe a Cidade" value="${modelLogin.cidade}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Cidade</label>
		                                                         </div>  
		
		                                                         <!-- Campo Estado Colaborador -->
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                                <select name="estado" name="estado" class="form-control" >
		                                                                    <option value="" disabled selected>Estado</option>
																		    <tagsUsuario:listaUF/>
		                                                                </select>
		                                                                <label class="float-label">Estado</label>
		                                                         </div>                                                            
		                                                          <!-- Campo País Colaborador -->                                                            
		                                                         <div class="form-group form-default form-static-label col-md-3 mb-6">
		                                                             <input type="text" name="pais" id="pais" class="form-control" maxlength="45" placeholder="Informe o País" value="${modelLogin.pais}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">País</label>
		                                                         </div>
	                                                         
	                                                         </div>
	                                                         <br>

												             <button type="submit" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" name="salvar">Salvar</button>
												             <button type="button" class="btn btn-danger  waves-effect waves-light mesmo-tamanho-botao" name="excluir" onclick="criarDeleteComAjax();">Excluir</button>
												             <!-- Button trigger modal -->
															 <button type="button" class="btn btn-secondary waves-effect waves-light mesmo-tamanho-botao" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
												             
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
														<h4 class="h4stilo">Usuário(s) Cadastrados</h4>
														<br>
														<div style="height: 300px; overflow: scroll;">
															<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoView">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																		<th scope="col">Login</th>
																		<th scope="col">Ver</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${modelLogins}" var="ml">
																		<tr>
																			<td><c:out value="${ml.id_colaboradores}"></c:out>
																			</td>
																			<td><c:out value="${ml.nome}"></c:out></td>
																			<td><c:out value="${ml.login}"></c:out></td>
																			<td><a type="button" class="btn btn-info"
																				href="<%= request.getContextPath() %>/ServletManutencaoUsuarioController?acao=buscarEditar&id_colaboradores=${ml.id_colaboradores}">Ver</a></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
														
														<nav aria-label="Navegação de página exemplo">
															<ul class="pagination">
															    <%
															    Integer totalPagina = (Integer) request.getAttribute("totalPagina");
															    if ( totalPagina != null ) {
															       for(int p = 0; p < totalPagina; p++){
															    	   String urlPag = request.getContextPath() + "/ServletManutencaoUsuarioController?acao=paginar&pag=" + p;
															    	   out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+urlPag+"\">"+(p+1)+"</a></li>");
															       }
															    }
															       
															    %>
															</ul>
														</nav>
														
														
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
    
    <!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       
	        <div class="input-group mb-3">
			  <input type="text" id="nomeBusca" class="form-control" placeholder="Digite parte ou nome completo do Usuário" aria-label="nome usuário" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
			  </div>
			</div>
			
            <div style="height: 300px; overflow: scroll;">
				<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutado">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">ID</th>
				      <th scope="col">Nome</th>
				      <th scope="col">Ver</th>
				    </tr>
				  </thead>
				  <tbody>
				    
				  </tbody>
				</table>
			</div>
			
	      </div>
	      <samp id="totalResutados"></samp>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>

	      </div>
	    </div>
	  </div>
	</div>
    
    <script type="text/javascript">
    
    function pesquisa() {
		var cep = $("#cep").val();
        //Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

            if (!("erro" in dados)) {
                //Atualiza os campos com os valores da consulta.
                $("#cep").val(dados.cep);
                $("#endereco").val(dados.logradouro);
                $("#bairro").val(dados.bairro);
                $("#cidade").val(dados.localidade);
                
            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
                alert("CEP não encontrado.");
            }
        });
	}
    
    function visualizarImg(fotoembase64, filefoto) {
        
        var preview = document.getElementById(fotoembase64); // campo IMG html
        var fileUser = document.getElementById(filefoto).files[0];
        var reader = new FileReader();
        
        reader.onloadend = function (){
    	    preview.src = reader.result; /*Carrega a foto na tela*/
        };
        
        if (fileUser) {
    	  reader.readAsDataURL(fileUser); /*Preview da imagem*/
        }else {
    	 preview.src=  '';
        }
        
    }

    
    function verEditar(id_colaboradores ) {
    	var urlAction = document.getElementById("formManutencaoUser").action;
    	var url = urlAction + '?acao=buscarEditar&id_colaboradores='+id_colaboradores;
    	window.location.href = url;
	}
    
    function buscarUsuario(){
    	var nomeBusca = document.getElementById("nomeBusca").value;
    	if( nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '' ){
 
    		
      		var urlAction = document.getElementById("formManutencaoUser").action;
      		
      		$.ajax({
      			
      			method : "get",
      			url : urlAction,
      			data : "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
      			success: function(response){
      			
      				var json = JSON.parse(response);
      				
      				$('#tabelaResutado > tbody > tr').remove();
      				
      				for(var p = 0; p < json.length; p++){
      					$('#tabelaResutado > tbody').append('<tr> <td>' + (p+1) + '</td> <td>'+json[p].id_colaboradores+'</td> <td>'+json[p].nome+'</td> <td><button onclick="verEditar('+ json[p].id_colaboradores +');" type="button" class="btn btn-info">Ver</button></td></tr>');
      				}
      				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length ;
      			}
      			
      		}).fail(function( xhr, status, errorThrown ){
      			alert('Erro ao buscar usuário: ' + xhr.responseText);
      		});
    	}
	}

    function criarDeleteComAjax() {
    	if( confirm('Deseja realmente excluir os dados?') ) {
    		var urlAction = document.getElementById("formManutencaoUser").action;
    		var idUser = document.getElementById("id_colaboradores").value;
    		$.ajax({
    			
    			method : "get",
    			url : urlAction,
    			data : "id_colaboradores=" + idUser + '&acao=deletarajax',
    			success: function(response){
    				limparForm();
    				alert(response);
    				
    				document.getElementById("msg").textContent = response;
    			}
    			
    		}).fail(function( xhr, status, errorThrown ){
    			alert('Erro ao deletar usuário: ' + xhr.responseText);
    		});
    	}
	}
    
    
    function criarDelete() {
    	
    	if( confirm('Deseja realmente excluir os dados?') ) {
	    	document.getElementById("formManutencaoUser").method = 'get';
	    	document.getElementById("acao").value = 'deletar';
	    	document.getElementById("formManutencaoUser").submit();
        }
	}
    function limparForm() {
		var elementos = document.getElementById("formManutencaoUser").elements;
		
		for(p = 0; p < elementos.length; p++){
			elementos[p].value = '';
		}
	}
 
    
    $( function() {
  	  
  	  $("#dataNascimento").datepicker({
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
    
    
    </script>
</body>

</html>
