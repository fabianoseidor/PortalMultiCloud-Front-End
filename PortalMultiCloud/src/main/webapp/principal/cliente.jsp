<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsCliente"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>
   <style>
       .mesmo-tamanho-botao {
           width: 15%;
           white-space: normal;
       }
   </style>


<style type="text/css">
/* Style the form */
#formCliente {
	background-color: #ffffff;
	margin: 1px auto;
	padding: 20px;
	width: 85%;
	min-width: 300px;
}

.h4stilo {
	/*	background-color: #ffffff; */
	margin: 1px auto;
	padding: 20px;
	width: 90%;
	/*	min-width: 300px;*/
}

/* Style the input fields */
input {
	padding: 10px;
	width: 100%;
	font-size: 17px;
	font-family: Raleway;
	border: 1px solid #aaaaaa;
}

/* Mark input boxes that gets an error on validation: */
input.invalid {
	background-color: #ffdddd;
}

/* Hide all steps by default: */
.tab {
	display: none;
}

/* Make circles that indicate the steps of the form: */
.step {
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbbbbb;
	border: none;
	border-radius: 50%;
	display: inline-block;
	opacity: 0.5;
}

/* Mark the active step: */
.step.active {
	opacity: 1;
}

/* Mark the steps that are finished and valid: */
.step.finish {
	background-color: #04AA6D;
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
										<div class="row">

											<!--  -->
											<!-- Inicio da implementacao do formulario -->
											<!--  -->

											<h4 class="h4stilo">Cadastro de Cliente</h4>

											<form class="form-material" action="<%= request.getContextPath() %>/ServletManutencaoCliente" method="post" id="formCliente">

												<!-- Uma "tab" para cada etapa do formulário:: -->
												
												<!-- Etapa 01 -->
												<div class="tab">
													<h5 class="sub-title" style="color: SteelBlue; font-weight: bold; font-style: italic; font:">Dados Cliente</h5>
                                                    
													<div class="form-row">
	                                                    <!-- Campo Id Colaborador --> 
	                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
	                                                        <input type="text" name="id_cliente" id="id_cliente" class="form-control" readonly="readonly" value="${modelCliente.id_cliente}">
	                                                        <span class="form-bar"></span>
	                                                        <label class="float-label">ID Cliente</label>
	                                                    </div>

	                                                    <!-- Data Cadastro --> 
	                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
	                                                        <input type="text" name="dt_criacao" id="dt_criacao" class="form-control" readonly="readonly" value="${modelCliente.dt_criacao}">
	                                                        <span class="form-bar"></span>
	                                                        <label class="float-label">Data Cadastro</label>
	                                                    </div>
	                                                    
	                                                    <!-- Login Cadastro --> 
	                                                    <div class="form-group form-default form-static-label col-md-4 mb-6">
	                                                        <input type="text" name="login_cadastro" id="login_cadastro" class="form-control" readonly="readonly" value="${modelCliente.login_cadastro}">
	                                                        <span class="form-bar"></span>
	                                                        <label class="float-label">Login Cadastro</label>
	                                                    </div>

													</div>  
													                                                  
													<!-- Campo Nome Colaborador -->
													<div class="form-group form-default form-static-label">
														<input type="text" name="razao_social" id="razao_social" class="form-control" placeholder="Informe o Razao Social" required="required" value="${modelCliente.razao_social}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Razao Social</label>
													</div>
													
													<!-- Campo Nome Fantasia -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="nome_fantasia" id="nome_fantasia" class="form-control" placeholder="Informe o nome fantasia" value="${modelCliente.nome_fantasia}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Nome Fantasia</label>
													</div>	
																	
													<!-- Campo Alias Apelido da Empresa -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="alias" id="alias" class="form-control" placeholder="Informe o nome Alias" value="${modelCliente.alias}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Alias</label>
													</div>					

													<!-- Campo Nome Colaborador -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="site" id="site" class="form-control" placeholder="Informe o site" required="required" value="${modelCliente.site}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Site</label>
													</div>
													
													<!-- Campo Nome Colaborador -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="cnpj" maxlength="18" onkeyup="handleCpfCnpj(event)" id="cnpj" class="form-control" placeholder="Informe o CNPJ" value="${modelCliente.cnpj}"> 
														<span class="form-bar"></span> 
														<label class="float-label">CNPJ</label>
													</div>
													
													<!-- Campo Nome Colaborador -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="inscricao_estadual" id="inscricao_estadual" class="form-control" placeholder="Informe inscrição estadual" value="${modelCliente.inscricao_estadual}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Inscrição Estadual</label>
													</div>
													
													<!-- Campo Nome Colaborador -->
													<div class="form-group form-default form-static-label ">
														<input type="text" name="inscricao_municipal" id="inscricao_municipal" class="form-control" placeholder="Informe inscrição municipal" value="${modelCliente.inscricao_municipal}"> 
														<span class="form-bar"></span> 
														<label class="float-label">Inscrição Municipal</label>
													</div>
													
													<div class="form-row">
														<!-- Campo Porte Cliente -->
														<div class="form-group form-default form-static-label col-md-6 mb-6">
															<select name="id_porte_emp" id="id_porte_emp" class="form-control">
																<option value="" disabled selected>Selecione Porte Empresa</option>
																<tagsCliente:listaPorteCliente />
															</select> 
															<label class="float-label">Porte Empresa Cliente</label>
														</div>
													
														<!-- Campo Porte Cliente -->
														<div class="form-group form-default form-static-label col-md-6 mb-6">
															<select name="id_status_emp" id="id_status_emp" class="form-control">
																<option value="" disabled selected>Selecione Status Cliente</option>
																<tagsCliente:listaStatusCliente />
															</select> 
															<label class="float-label">Status Cliente</label>
														</div>
													</div>
												</div>
												
											<!-- Etapa 02 -->
												<div class="tab">
													<h5 class="sub-title" style="color: SteelBlue; font-weight: bold; font-style: italic">Endereço</h5>

													<div class="form-group form-default form-static-label ">
														<label for="cep">CEP</label> 
														<input type="text" class="form-control" onblur="pesquisa();" required="required" id="cep" name="cep" placeholder="CEP" onkeyup="handleZipCode(event)" oninput="this.className = ''" value="${modelCliente.cep}"> 
													</div>

 													<div class="form-group">
														<label for="endereco">Endereço</label> 
														<input type="text" class="form-control" id="endereco" name="endereco" placeholder="Rua Pinheiros" value="${modelCliente.endereco}">
													</div>

													<div class="form-row">
														<div class="form-group col-md-4">
															<label for="numero">Número</label> 
															<input type="number" name="numero" class="form-control" id="numero" placeholder="Número" value="${modelCliente.numero}">
														</div>
														
														<div class="form-group col-md-4">
															<label for="complemento">Complemento</label> 
															<input type="text" class="form-control" id="complemento" name="complemento" placeholder="Complemento" value="${modelCliente.complemento}">
														</div>
														
														<div class="form-group col-md-4">
															<label for="bairro">Bairro</label> 
															<input type="text" class="form-control" id="bairro" name="bairro" placeholder="Bairro" value="${modelCliente.bairro}">
														</div>
													</div>
													 
                                                    <div class="form-group">
	                                                     <select name="estado" id="estado" class="form-control" >
	                                                          <option value="" disabled selected>Estado</option>
	                                                          <tagsCliente:listaUFCliente/>
	                                                     </select>
	                                                     <label class="float-label">Estado</label>
	                                                </div>                                                            
                                                    
													<div class="form-row">
														<div class="form-group col-md-6">
															<label for="cidade">Cidade</label> 
															<input type="text" name="cidade" class="form-control" id="cidade" placeholder="Cidade" value="${modelCliente.cidade}">
														</div>

														<div class="form-group col-md-6">
															<label for="pais">País</label> 
															<input type="text" class="form-control" id="pais" name="pais" placeholder="País" value="${modelCliente.pais}">
														</div>
													</div>
												</div>

												<!-- Etapa 03 -->
												<div class="tab">
													<h5 class="sub-title" style="color: SteelBlue; font-weight: bold; font-style: italic ">Observação</h5>
													
												    <div class="form-group form-default form-static-label ">
														<label for="observacao">Observações sobre o Cliente</label>
														<textarea class="form-control" id="observacao" name="observacao" rows="3" >${modelCliente.observacao}</textarea>
													</div>
												</div>

												<div style="overflow: auto;">
													<div style="float: right;">
														<button type="button" class="btn btn-primary waves-effect waves-light" id="prevBtn" onclick="nextPrev(-1)">Voltar</button>
														<button type="button" class="btn btn-success waves-effect waves-light" id="nextBtn" onclick="nextPrev(1)">Próximo</button>
														<button type="submit" class="btn btn-info waves-effect waves-light" id="js-submit-form" name="js-submit-form" >Salvar</button>
														
         												<c:if test="${modelCliente.id_cliente!=null}">
														     <button type="button" class="btn btn-danger waves-effect waves-light">Add Contrato</button>
														    <!--   <a href="<%= request.getContextPath() %>/ServletTelefone?idcliente=${modelLogin.id_cliente}" class="btn btn-info" >Telefone(s)</a> -->
														</c:if>

													</div>
												</div>

												<!-- Circles which indicates the steps of the form: -->
												<!-- Indica a quantidade de Etapas(Aqui sao 7) -->
												<div style="text-align: center; margin-top: 40px;">
													<span class="step"></span> <!-- Etapa 01 -->
													<span class="step"></span> <!-- Etapa 02 --> 
													<span class="step"></span> <!-- Etapa 03 -->
												</div>

											</form>
											<!--  -->
											<!-- Fim da implementacao do formulario -->
											<!--  -->
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
	<script>

    function pesquisa() {
		var cep = $("#cep").val();
        //Consulta o webservice viacep.com.br/
        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

            if (!("erro" in dados)) {
                //Atualiza os campos com os valores da consulta.
                $("#cep"     ).val( dados.cep        );
                $("#endereco").val( dados.logradouro );
                $("#bairro"  ).val( dados.bairro     );
                $("#estado"  ).val( dados.uf         );
                $("#cidade"  ).val( dados.localidade );
                $("#pais"    ).val( 'Brasil'         );
                
            } //end if.
            else {
                //CEP pesquisado não foi encontrado.
                alert("CEP não encontrado.");
            }
        });
	}
	
	
		// Exemplo de JavaScript inicial para desativar envios de formulário, se houver campos inválidos.
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Pega todos os formulários que nós queremos aplicar estilos de validação Bootstrap personalizados.
						var forms = document.getElementsByClassName('needs-validation');
						// Faz um loop neles e evita o envio
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();

		//
		//
		//

		var currentTab = 0; // Current tab is set to be the first tab (0)
		showTab(currentTab); // Display the current tab

		function showTab(n) {
			// This function will display the specified tab of the form ...
			var x = document.getElementsByClassName("tab");
			
			x[n].style.display = "block";
			// ... and fix the Previous/Next buttons:
			if (n == 0) {
				document.getElementById("prevBtn").style.display = "none";
			} else {
				document.getElementById("prevBtn").style.display = "inline";
			}
			if (n == (x.length - 1)) {
				//document.getElementById("nextBtn").innerHTML = "Salvar";
				document.getElementById("nextBtn").style.display = "none";
				document.getElementById("js-submit-form").style.display = "inline";
				document.getElementById("js-submit-form").disabled = false;
				
			} else {
				document.getElementById("nextBtn").style.display = "inline";
				document.getElementById("js-submit-form").style.display = "none";
	//		    document.getElementById("nextBtn").innerHTML = "Próximo";
				document.getElementById("js-submit-form").disabled = true;
			}
			// ... and run a function that displays the correct step indicator:
			fixStepIndicator(n)
		}

		function nextPrev(n) {
			// This function will figure out which tab to display
			var x = document.getElementsByClassName("tab");
			// Exit the function if any field in the current tab is invalid:
				
			if (n == 1 && !validateForm()){

				return false;
		    }
			
			// Hide the current tab:
			x[currentTab].style.display = "none";
			// Increase or decrease the current tab by 1:
			currentTab = currentTab + n;
			// if you have reached the end of the form... :
			if (currentTab >= x.length) {
				//...the form gets submitted:

				document.getElementById("formCliente").submit();
				return false;
			}
			// Otherwise, display the correct tab:
			showTab(currentTab);
		}
	
		function validateForm() {
			// This function deals with validation of the form fields
			var x, y, i, valid = true;
			x = document.getElementsByClassName("tab");
			y = x[currentTab].getElementsByTagName("input");
			z = x[currentTab].getElementsByTagName("select");
			// A loop that checks every input field in the current tab:
			for (i = 0; i < y.length; i++) {
				// If a field is empty...

				
				if (y[i].value == ""                    && y[i].id != "id_cliente"     && y[i].id != "dt_criacao"         && 
					y[i].id    != "complemento"         && y[i].id != "nome_fantasia"  && y[i].id != "inscricao_estadual" && 
					y[i].id    != "inscricao_municipal" && y[i].id != "login_cadastro" && y[i].id != "observacao"           ) {
					// add an "invalid" class to the field:
			//		y[i].className += " invalid";
					y[i].style.background = "#ffdddd";
					// and set the current valid status to false:
					
					valid = false;

				}
			}

			// A loop that checks every input field in the current tab:
			for (i = 0; i < z.length; i++) {
				// If a field is empty...
//				alert('id: ' + z[i].id + ' - Value: ' + z[i].value);
				if (z[i].value == "") {
					// add an "invalid" class to the field:
			//		y[i].className += " invalid";
					z[i].style.background = "#ffdddd";
					// and set the current valid status to false:

					valid = false;
				}
			}			
		
			// If the valid status is true, mark the step as finished and valid:
			if (valid) {
				document.getElementsByClassName("step")[currentTab].className += " finish";
			}

			return valid; // return the valid status
		}

		function fixStepIndicator(n) {
			// This function removes the "active" class of all steps...
			var i, x = document.getElementsByClassName("step");
			for (i = 0; i < x.length; i++) {
				x[i].className = x[i].className.replace(" active", "");
			}
			//... and adds the "active" class to the current step:
			x[n].className += " active";
		}
		
	    function validateEmail(email) {
	    	  var re = /\S+@\S+\.\S+/;
	    	  return re.test(email);
	    }
/*	    
	    teste();
	    function teste() {
	    	var splt = window.location.hostname.split('.')
	    	console.log(window.location);
	    	alert(window.location.pathname);	
		}
*/	    
	</script>
</body>

</html>
