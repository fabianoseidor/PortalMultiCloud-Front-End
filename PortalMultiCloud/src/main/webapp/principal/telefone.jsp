<%@page import="br.com.portal.model.ModelTelefone"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    	
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsTelefone"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
													     <h4 class="h4stilo">Cadastro de Telefone</h4>
													     <br>
													     <form class="form-material" action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formTelefone">
													        <input type="hidden" name="id_telefone" id="id_telefone" value="${modelTelefone.id_telefone}">
															<div class="form-row">
			                                                    <!-- Campo ID Cliente(Dono do Telefone) --> 
			                                                    <div class="form-group form-default form-static-label col-md-6 mb-6">
			                                                        <input type="text" name="id_cliente" id="id_cliente" class="form-control" required="required" readonly="readonly" value="${modelTelefone.cliente.getId_cliente()}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">ID Cliente</label>
			                                                    </div>
		
			                                                    <!-- Nome do Cliente --> 
			                                                    <div class="form-group form-default form-static-label col-md-6 mb-6">
			                                                        <input type="text" name="razao_social" id="razao_social" class="form-control" required="required" readonly="readonly" value="${modelTelefone.cliente.getRazao_social()}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">Razao Social</label>
			                                                    </div>
															</div>  


															<div class="form-row">
			                                                    <!-- Campo Nome do Contato --> 
			                                                    <div class="form-group form-default form-static-label col-md-6 mb-6">
			                                                        <input type="text" name="nomeContato" id="nomeContato" class="form-control" required="required" value="${modelTelefone.nome_contato}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">Nome do Contato</label>
			                                                    </div>
		
			                                                    <!-- Data Telefone --> 
			                                                    <div class="form-group form-default form-static-label col-md-6 mb-6">
			                                                        <input type="text" name="telefone" onkeyup="handlePhone(event)"  size="15" id="telefone" class="form-control" required="required" value="${modelTelefone.telefone}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">Telefone</label>
			                                                    </div>
															</div>  

															<div class="form-row">
			                                                    <!-- Campo E-mail --> 
			                                                    <div class="form-group form-default form-static-label col-md-6 mb-6">
			                                                        <input type="email" name="email" id="email" class="form-control" required="required" value="${modelTelefone.email}">
			                                                        <span class="form-bar"></span>
			                                                        <label class="float-label">E-mail</label>
			                                                    </div>
		
																<!-- Campo Função Contato -->
																
																<div class="form-group form-default form-static-label col-md-6 mb-6">
																	<select name="funcaoContato" id="funcaoContato" class="form-control">
																		<option value="" disabled selected>Selecione Função do Contato</option>
																		  <tagsTelefone:listaFuncao />
																	</select> 
																	<label class="float-label">Função Contato</label>
																</div>
															</div>
															
															<div class="form-row">								
																<!-- Campo observação -->
																<div class="form-group form-default form-static-label col-md-12 mb-6">
																	<input type="text" name="obsTelefone" id="obsTelefone" class="form-control"  value="${modelTelefone.obs}">
																	<label class="float-label">Observação</label>
																</div>
															</div>															  

													        <button type="submit" class="btn btn-primary mesmo-tamanho-botao" onClick="return vazio()">Salvar</button>
													        <a href="<%= request.getContextPath() %>/ServletManutencaoCliente?idcliente=${modelTelefone.cliente.getId_cliente()}" class="btn btn-info mesmo-tamanho-botao" >Voltar Cliente</a>
													     
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
	                                                	 <h4 class="h4stilo">Telefone(s) Cadastrados</h4>
													     <br>
					                                    <div style="height: 300px; overflow: scroll;">
															<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoView" class="table table-striped">
															  <thead>
															    <tr>
															      <th scope="col">ID Tel.</th>
															      <th scope="col">Cliente</th>
															      <th scope="col">Nome Contato</th>
															      <th scope="col">Telefone</th>
															      <th scope="col">E-mail</th>
															      <th scope="col">Função</th>
															      <th scope="col">observação</th>
															    </tr>
															  </thead>
															  <tbody>
					                                              <c:forEach items="${modelTelefones}" var="tl">
					                                                   <tr>
					                                                     <td> <c:out value="${tl.id_telefone}">              </c:out> </td>
					                                                     <td> <c:out value="${tl.cliente.getRazao_social()}"></c:out> </td>
					                                                     <td> <c:out value="${tl.nome_contato}">             </c:out> </td>
					                                                     <td><c:out value="${tl.telefone}">                  </c:out> </td>
					                                                     <td><c:out value="${tl.email}">                     </c:out> </td>
					                                                     <td><c:out value="${tl.nomeFuncao}">                </c:out> </td>
					                                                     <td><c:out value="${tl.obs}">                       </c:out> </td>
					                                                     <td><a type="button" class="btn btn-info" href="<%= request.getContextPath() %>/ServletTelefone?acao=buscarEditar&id_telefone=${tl.id_telefone}">Editar</a></td>
					                                                     <td><a type="button" class="btn btn-danger" href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id_telefone=${tl.id_telefone}&idcli=${tl.cliente.getId_cliente()}">Excluir</a></td>
					                                                   </tr>
					                                              </c:forEach>
															  </tbody>
															</table>
														</div>   
	                                                
														<nav aria-label="Navegação de página exemplo">
															<ul class="pagination">
															    <%
															       ModelTelefone modelTelefonePag = (ModelTelefone) request.getAttribute("modelTelefone");
															       if ( modelTelefonePag != null ){
															    	   int totalPagina = (int) request.getAttribute("totalPagina");
																       for(int p = 0; p < totalPagina; p++){
																    	   String urlPag = request.getContextPath() + "/ServletTelefone?acao=paginar&pag=" + p + "&idCliePag=" + modelTelefonePag.getCliente().getId_cliente();
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
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	  function vazio() {
		     var x;
		     x = document.getElementById("funcaoContato").value;
		     if ((x == "")||(x == null)) {
		        alert("Selecione uma Função do Contato");
		        return false;
		     };
	  }
	  
	</script>
	


</body>

</html>
