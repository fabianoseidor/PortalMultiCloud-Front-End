<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="head.jsp"></jsp:include>

<style type="text/css"></style>


<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
	rel="stylesheet" />
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
											<div class="container ">
												<div class="alert alert-primary alert-dismissible fade show"
													role="alert">
													<!-- Colicar o fundo da div branca -->
													<form class="form-material" action="<%=request.getContextPath()%>/ServletTrocaSenha" method="post" id="formAtualizaSenha">

														<div class="row">

															<div class="col-4">
																<div class="form-group">
																	<div class="col-md-8 offset-md-2">
																		<label class="form-label" style="color: black">Login</label>
																		<input class="form-control" name="nomeLogin" id="nomeLogin" type="text" readonly="readonly" autocomplete="off" value="<%=session.getAttribute("usuario")%>">
																	</div>
																</div>

																<div class="form-group">
																	<div class="col-md-8 offset-md-2">
																		<label class="form-label" style="color: black">Escolha logon</label> 
																		<select name="escolhaLogon" id="escolhaLogon" class="form-control js-example-basic-single" onchange="atualizaLogin();"
																		
																		<%  String useradmin = (String) session.getAttribute("useradmin"); 
                                                                           if ( !useradmin.equals("1")  ) {
						                                                        out.print(" disabled ");
		                                                                   }
														                %>
																		>
																			<option value="" disabled selected>Selecione logon</option>

																		</select>
																		
																		<%  
                                                                           if ( !useradmin.equals("1")  ) {
						                                                        out.print( " <div class=\"form-group\"> "
																						// + "   <div class=\"col-md-8 offset-md-2\">"
																						 + "       <label style=\"color: red\" class=\"float-label\">* Esta opção esta habilitada somente para usuário \"ADMIN\"</label>"
																						// + "   </div> "
																					     + " </div>" );
		                                                                   }
														                %>
																		<!--  -->


																	</div>
																</div>

															</div>

															<div class="col-8">
																<div class="form-group">
																	<div class="col-md-6 offset-md-1">
																		<label class="form-label" style="color: black">Digite a Nova Senha</label> 
																		<input class="form-control" name="senha1" id="senha1" type="password" required="required" autocomplete="off">
																	</div>
																</div>

																<div class="form-group">
																	<div class="col-md-6 offset-md-1">
																		<label class="form-label" style="color: black">Confirme a nova Senha</label> 
																		<input class="form-control" name="senha2" id="senha2" type="password" required="required" autocomplete="off"> 
																		<span id="resultado-pesquisa" style="color: red"></span>
																	</div>
																</div>

																<div class="form-group">
																	<div class="col-md-6 offset-md-1">
																		<button type="button" class="btn btn-outline-secondary btn-sm" onclick="atualizaSenha();">Alterar Senha</button>
																	</div>
																</div>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>

									<!-- Campo destinado a mensagem na Tela -->
									<div class="alert alert-primary alert-dismissible fade show"
										role="alert">
										<strong id="msg">Resultado: </strong> ${msg}
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
										</button>
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

	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	<script type="text/javascript">
	

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
		function atualizaSenha() {

			if (validaSenhaIguais()) {
				var urlAction = document.getElementById("formAtualizaSenha").action;
				var senha1 = document.getElementById("senha1").value;
				var login = document.getElementById("nomeLogin").value;
				var dados = 'acao=updateSenha' +
	                        '&password='       + senha1+ 
	                        '&login='          + login;
				
				$.ajax({

					method  : "get"    ,
					url     : urlAction,
					data    :  dados   ,
					success : function(lista){
						$("#senha1").val( '' );
						$("#senha2").val( '' );
						document.getElementById("msg").textContent = "Resultado: Senha atualizada com sucesso!";
						// document.getElementById("msg").textContent = json;
					}

				}).fail(function(xhr, status, errorThrown) {
					alert('Erro ao atualizar a senha: ' + xhr.responseText);
				});
			} else {
				document.getElementById("msg").textContent = "Resultado: ";
			}
		}

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
		function validaSenhaIguais() {
			var senha1 = document.getElementById("senha1").value;
			var senha2 = document.getElementById("senha2").value;

			if (senha1 !== senha2) {
				document.getElementById("resultado-pesquisa").innerHTML = "Senhas não converem!";
				return false;
			} else {
				document.getElementById("resultado-pesquisa").innerHTML = "";
				return true;
			}
		}

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
		$('document').ready(function() {
			$("#escolhaLogon").select2({ theme : "classic" });
		});

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
		function montaSelectLogin() {
            var urlAction = document.getElementById("formAtualizaSenha").action;
            $.ajax({
					method : "get",
					url     : urlAction,
					data    : 'acao=montaSelectLogin',
					success : function(lista) {
						var option = '<option value="" disabled selected>Selecione logon</option>';
						var json = JSON.parse(lista);
						for (var p = 0; p < json.length; p++) {
							option += '<option value=' + json[p].id_colaboradores + '>' + json[p].login + '</option>';
						}
						$("#escolhaLogon").html(option).show();
					}
				}).fail(
				function(xhr, status, errorThrown) {
					alert('Erro ao montar select do login: ' + xhr.responseText);
				});
		}

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
		window.onload = function () { 
			 montaSelectLogin( );
		} 	    
		
		function atualizaLogin() {
			var select = document.getElementById('escolhaLogon');
			var value = select.options[select.selectedIndex].text;
			
			$("#nomeLogin").val( value );
		} 
	</script>

</body>

</html>
