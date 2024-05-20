<%@page import="br.com.portal.model.ModelCliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsCliente"%>
    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>
<head>
<style type="text/css">
	hr{
	 border-color: #191970; 
	 size: 70px;
	 font-weight: bolder;
	}
    .mesmo-tamanho-botao {
        width: 15%;
        white-space: normal;
    }

</style>

</head>
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
														<h4 class="sub-title">Manutenção de Cliente</h4>
														<form class="form-material" action="<%=request.getContextPath()%>/ServletManutencaoCliente" method="post" id="formManutencaoCliente">
														    <!-- Linha 01 -->
															<div class="form-row">
			                                                    <!-- Campo Id Cliente --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <input type="text" name="id_cliente" id="id_cliente" class="form-control" required="required" readonly="readonly" value="${modelClienteManu.id_cliente}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">ID Cliente</label>
			                                                    </div>
		
			                                                    <!-- Data Cadastro --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <input type="text" name="dt_criacao" id="dt_criacao" class="form-control" required="required" readonly="readonly" value="${modelClienteManu.dt_criacao}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">Data Cadastro</label>
			                                                    </div>
			                                                    
			                                                    <!-- Login Cadastro --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
			                                                        <input type="text" name="login_cadastro" id="login_cadastro" class="form-control" required="required" readonly="readonly" value="${modelClienteManu.login_cadastro}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">Login Cadastro</label>
			                                                    </div>
			                                                    
															</div>
															<hr>  
															<br>
														    <!-- Linha 02 -->
															<div class="form-row">
			                                                    <!-- Campo Razao Social --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
																	<input type="text" name="razao_social" id="razao_social" class="form-control" placeholder="Informe o Razao Social" required="required" value="${modelClienteManu.razao_social}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Razao Social</label>
			                                                    </div>
		
			                                                    <!-- Campo Nome Fantasia --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
																	<input type="text" name="nome_fantasia" id="nome_fantasia" class="form-control" placeholder="Informe o nome fantasia" required="required"  value="${modelClienteManu.nome_fantasia}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Nome Fantasia</label>
			                                                    </div>

			                                                    <!-- Campo Alias Apelido da Empresa --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
																	<input type="text" name="alias" id="alias" class="form-control" placeholder="Informe o nome Alias" required="required"  value="${modelClienteManu.alias}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Alias</label>
			                                                    </div>
			                                                    
															</div>  
														    <hr>
														    <br>
														    <!-- Linha 03 -->
															<div class="form-row">
			                                                    <!-- Campo CNPJ --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-4">
																	<input type="text" name="cnpj" maxlength="18" onkeyup="handleCpfCnpj(event)" id="cnpj" class="form-control" placeholder="Informe o CNPJ" value="${modelClienteManu.cnpj}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">CNPJ</label>
			                                                    </div>
		
			                                                    <!-- Campo Inscrição Estadual --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-4">
																	<input type="text" name="inscricao_estadual" id="inscricao_estadual" class="form-control" placeholder="Informe inscrição estadual" value="${modelClienteManu.inscricao_estadual}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Inscrição Estadual</label>
			                                                    </div>

			                                                    <!-- Campo Inscrição Estadual --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-4">
																	<input type="text" name="inscricao_municipal" id="inscricao_municipal" class="form-control" placeholder="Informe inscrição municipal" value="${modelClienteManu.inscricao_municipal}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Inscrição Municipal</label>
			                                                    </div>
															</div>  
														    <hr>
														    <br>
														    <!-- Linha 04 -->
															<div class="form-row">
			                                                    <!-- Campo Site --> 
			                                                    <div class="form-group form-default form-static-label col-md-4 mb-4">
																	<input type="text" name="site" id="site" class="form-control" placeholder="Informe o site" required="required" value="${modelClienteManu.site}"> 
																	<span class="form-bar"></span> 
																	<label class="float-label">Site</label>
			                                                    </div>

																<!-- Campo Porte Cliente -->
																<div class="form-group form-default form-static-label col-md-4 mb-4">
																	<select name="id_porte_emp" id="id_porte_emp" class="form-control">
																		<option value="" disabled selected>Selecione Porte Empresa</option>
																		<tagsCliente:listaPorteCliente />
																	</select> 
																	<label class="float-label">Porte Empresa Cliente</label>
																</div>
															
																<!-- Campo Status Cliente -->
																<div class="form-group form-default form-static-label col-md-4 mb-4">
																	<select name="id_status_emp" id="id_status_emp" class="form-control">
																		<option value="" disabled selected>Selecione Status Cliente</option>
																		<tagsCliente:listaStatusCliente />
																	</select> 
																	<label class="float-label">Status Cliente</label>
																</div>
															</div> 
													        <hr>
													        <br>
														    <!-- Linha 05 -->	
															<div class="form-row">
																<!-- Campo CEP -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="cep">CEP</label> 
																	<input type="text" class="form-control" onblur="pesquisa();" required="required" id="cep" name="cep" placeholder="CEP" onkeyup="handleZipCode(event)" oninput="this.className = ''" value="${modelClienteManu.cep}"> 
																</div>
																<!-- Campo Endereço -->
			 													<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="endereco">Endereço</label> 
																	<input type="text" class="form-control" id="endereco" name="endereco" placeholder="Rua Pinheiros" value="${modelClienteManu.endereco}">
																</div>
																<!-- Campo Número -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="numero">Número</label> 
																	<input type="number" name="numero" class="form-control" id="numero" placeholder="Número" value="${modelClienteManu.numero}">
																</div>
																<!-- Campo Complemento -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="complemento">Complemento</label> 
																	<input type="text" class="form-control" id="complemento" name="complemento" placeholder="Complemento" value="${modelClienteManu.complemento}">
																</div>
															</div>
														    <hr>
														    <br>
														    <!-- Linha 06 -->	
															<div class="form-row">
																<!-- Campo Bairro -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="bairro">Bairro</label> 
																	<input type="text" class="form-control" id="bairro" name="bairro" placeholder="Bairro" value="${modelClienteManu.bairro}">
																</div>
																<!-- Campo Estado -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
				                                                    <select name="estado" id="estado" class="form-control" >
				                                                        <option value="" disabled selected>Estado</option>
				                                                        <tagsCliente:listaUFCliente/>
				                                                    </select>
				                                                    <label class="float-label">Estado</label>
																</div>

																<!-- Campo Cidade -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="cidade">Cidade</label> 
																	<input type="text" name="cidade" class="form-control" id="cidade" placeholder="Cidade" value="${modelClienteManu.cidade}">
																</div>
																
																<!-- Campo País -->
																<div class="form-group form-default form-static-label col-md-3 mb-2">
																	<label for="pais">País</label> 
																	<input type="text" class="form-control" id="pais" name="pais" placeholder="País" value="${modelClienteManu.pais}">
																</div>
															</div>
														    <hr>
														    <br>
														    <!-- Linha 07 -->
														    <div class="form-group form-default form-static-label ">
																<label for="observacao">Observações sobre o Cliente</label>
																<textarea class="form-control" id="observacao" name="observacao" rows="3" >${modelClienteManu.observacao}</textarea>
															</div>
												            
												            <!-- Delcaracao de botoes -->
												            <button type="submit" class="btn btn-primary waves-effect waves-light mesmo-tamanho-botao" name="salvar">Salvar</button>
												             <!-- Button trigger modal -->
															 <button type="button" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" data-toggle="modal" data-target="#modalCliente">Pesq. Cliente</button>
														       <% 
														            ModelCliente modelCliente= (ModelCliente) request.getAttribute("modelClienteManu");
														            
														            String disabled = "";
														            String idTelCli = null;
																	if ( modelCliente != null ) {
																		if(modelCliente.getId_cliente() != null ){ 
																			disabled = "";
																			idTelCli = String.valueOf( modelCliente.getId_cliente() );
																		}else{
																			disabled = "style=\"" + "pointer-events: none" + "\"";
																		}
																	}else disabled = "style=\"" + "pointer-events: none" + "\"";;
																	String url = request.getContextPath() + "/ServletTelefone?idcliente=" + idTelCli;
																	out.println("<a href=\""+url+"\" class=\"btn btn-info waves-effect waves-light mesmo-tamanho-botao\" "+ disabled +">Telefone(s)</a>");
																%>
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
										
										<!-- Campo destinado listar os Cliente no pe da tela -->
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
													   <div style="height: 300px; overflow: scroll;">
													   		<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoView" class="table table-striped">
															  <thead>
															    <tr>
															      <th scope="col">ID Clie.</th>
															      <th scope="col">Data Cad.</th>
															      <th scope="col">Razão Social</th>
															      <th scope="col">Nome Fantasia</th>
															      <th scope="col">CNPJ</th>
															      <th scope="col">Satus</th>
															      
															    </tr>
															  </thead>
															  <tbody>
					                                              <c:forEach items="${modelClientes}" var="cl">
					                                                   <tr>
					                                                     <td> <c:out value="${cl.id_cliente}"></c:out> </td>
					                                                     <td> <c:out value="${cl.dt_criacao}"></c:out> </td>
					                                                     <td> <c:out value="${cl.razao_social}"></c:out> </td>
					                                                     <td><c:out value="${cl.nome_fantasia}"></c:out></td>
					                                                     <td><c:out value="${cl.cnpj}"></c:out></td>
					                                                     <td><c:out value="${cl.status_emp}"></c:out></td>
					                                                     <td><a type="button" class="btn btn-info" href="<%= request.getContextPath() %>/ServletManutencaoCliente?acao=buscarEditar&id_cliente=${cl.id_cliente}">Editar</a></td>
					                                                   </tr>
					                                              </c:forEach>
															  </tbody>
															</table>
													   </div>

														<nav aria-label="Navegação de página exemplo">
															<ul class="pagination">
															    <%
															       int totalPagina = (int) request.getAttribute("totalPagina");
															       for(int p = 0; p < totalPagina; p++){
															    	   String urlPag = request.getContextPath() + "/ServletManutencaoCliente?acao=paginar&pag=" + p;
															    	   out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+urlPag+"\">"+(p+1)+"</a></li>");
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
	<div class="modal fade" id="modalCliente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Cliente</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">


	        <div class="d-flex align-items-center justify-content-center ">
				<div class="form-check form-check-inline">
				  <label class="form-check-label" for="inlineRadio1">
				  <input class="form-check-input" type="radio" name="radioBusca" id="radioNome" checked value="1">
				  Nome</label>
				</div>
				<div class="form-check form-check-inline">
				  <label class="form-check-label" for="inlineRadio2">
				  <input class="form-check-input" type="radio" name="radioBusca" id="radioAlias" value="2">
				  Alias</label>
				</div>
				<div class="form-check form-check-inline">
				  <label class="form-check-label" for="inlineRadio3">
				  <input class="form-check-input" type="radio" name="radioBusca" id="radioCNPJ" value="3">
				  CNPJ</label>
				</div>     
		    </div>

	        <div class="input-group mb-3">
			  <input type="text" id="nomeBusca" class="form-control" placeholder="Digite..." aria-label="nome cliente" aria-describedby="basic-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-success" type="button" onclick="buscarCliente();">Buscar</button>
			  </div>
			</div>
			
            <div style="height: 300px; overflow: scroll;">
				<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutado">
				  <thead>
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">ID</th>
				      <th scope="col">Cliente</th>
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

function verEditar( idCliente ) {
	var urlAction = document.getElementById("formManutencaoCliente").action;
	window.location.href = urlAction + '?acao=buscarEditar&id_cliente='+idCliente;
}


function buscarCliente(){
	var nomeBusca = document.getElementById("nomeBusca").value;
	if( nomeBusca != null && nomeBusca && nomeBusca != '' && nomeBusca.trim() != '' ){

  		var urlAction = document.getElementById("formManutencaoCliente").action;
		var tipoPesq  = 0;
		
		if (document.getElementById("radioNome").checked) {
			 tipoPesq = 1;
	    } else if (document.getElementById("radioAlias").checked) {
	    	tipoPesq = 2;
		}else if (document.getElementById("radioCNPJ").checked) {
	    	tipoPesq = 3;
		}

  		$.ajax({
  			
  			method : "get",
  			url : urlAction,
  			data : 'acao=buscarUserAjax' + "&nomeBusca=" + nomeBusca + '&tipoPesq=' + tipoPesq,
  			success: function(response){
  			
  				var json = JSON.parse(response);
  				
  				$('#tabelaResutado > tbody > tr').remove();
  				
  				for(var p = 0; p < json.length; p++){
  					$('#tabelaResutado > tbody').append('<tr> <td>' + (p+1) + '</td> <td>'+json[p].id_cliente+'</td> <td>'+json[p].razao_social+'</td> <td><button onclick="verEditar('+ json[p].id_cliente +');" type="button" class="btn btn-info">Ver</button></td></tr>');
  				}
  				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length ;
  			}
  			
  		}).fail(function( xhr, status, errorThrown ){
  			alert('Erro ao buscar usuário: ' + xhr.responseText);
  		});
	}
}



</script>    
    
    
</body>

</html>
