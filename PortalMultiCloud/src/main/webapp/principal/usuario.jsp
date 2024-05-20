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
                                                        <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
	                                                         
	                                                             <input type="hidden" name="acao" id="acao" value="">
	                                                             <div class="form-group form-default input-group mb-4">
                                                                  <div class="input-group-prepend">
                                                                    <c:if test="${modelLogin.fotouser != '' && modelLogin.fotouser != null}">
                                                                       <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id_colaboradores}">
	                                                                     <img alt="Imagem User" id="fotoembase64" src="${modelLogin.fotouser}" width="70px">
	                                                                    </a>
                                                                    </c:if>
                                                                    
                                                                    <c:if test="${modelLogin.fotouser == '' || modelLogin.fotouser == null}">
                                                                       <img alt="Imagem User" id="fotoembase64"  src="<%= request.getContextPath() %>/imagens/perfil_avatar.jpg" width="70px">
                                                        			</c:if>
                                                        			
                                                                  </div>
                                                                  <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
                                                             </div>
															 <br>

	                                                         <div class="form-row">

		                                                         <!-- Campo Id Colaborador --> 
		                                                         <div class="form-group form-default form-static-label col-md-6 mb-3">
		                                                             <input type="text" name="id_colaboradores" id="id_colaboradores" class="form-control" required="required" readonly="readonly" value="${modelLogin.id_colaboradores}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">ID</label>
		                                                         </div>
		                                                         <!-- Campo Nome Colaborador -->
		                                                         <div class="form-group form-default form-static-label col-md-6 mb-3">
		                                                             <input type="text" name="nome" id="nome" class="form-control" placeholder="Informe o nome" required="required" value="${modelLogin.nome}">
		                                                             <span class="form-bar"></span>
		                                                             <label class="float-label">Nome do Colaborador</label>
		                                                         </div>

	                                                         </div>
	                                                         <br>
	                                                         <div class="form-row container align-self-center">

																  <div class="form-group row col-md-3 mb-3 ">
																    <div class="col-md-10">
																      <div class="form-check">
																        <input class="form-check-input" type="checkbox" name="admin" id="admin" value="1" <% 
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
																    </div>
																  </div>


		                                                         <div class="form-group form-default form-static-label col-md-3 mb-3">
		                                                             <input type="text" name="login" id="login" class="form-control" placeholder="Informe o Login" required="required" value="${modelLogin.login}">
		                                                             <label class="float-label">Login</label>
		                                                         </div>

		                                                         
		                                                         <!-- Campo Senha Colaborador -->
	                                                             <div class="form-group form-default form-static-label col-md-3 mb-3">
	                                                                 <input class="hidden" type="password" style="display: none!important; visibility: hidden!important;" ></input>
		                                                             <input type="password" name="senha" id="senha" class="form-control" placeholder="Informe a Senha" required="required" autocomplete="off" >
		                                                             <label class="float-label">Senha</label>                                                                    
	                                                             </div>

		                                                         <!-- Campo Senha Colaborador -->
	                                                             <div class="form-group form-default form-static-label col-md-3 mb-3">
	                                                                 <input class="hidden" type="password" style="display: none!important; visibility: hidden!important;" ></input>
		                                                             <input type="password" name="confirm_password" id="confirm_password" class="form-control" placeholder="Confirme Senha" required="required" autocomplete="off" >
		                                                             <label class="float-label">Confirme Senha</label>                                                                    
	                                                             </div>

	                                                         </div>
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
												             <button type="button" class="btn btn-primary waves-effect waves-light mesmo-tamanho-botao" name="novo" onclick="limparForm();">Novo</button>
												             <button type="submit" class="btn btn-success waves-effect waves-light mesmo-tamanho-botao" name="salvar">Salvar</button>
                                                        </form>	
			                                        </div>			                        
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

    
    function limparForm() {
		var elementos = document.getElementById("formUser").elements;
		
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

  var password = document.getElementById("senha")
    , confirm_password = document.getElementById("confirm_password");

  function validatePassword(){
    if(password.value != confirm_password.value) {
      confirm_password.setCustomValidity("Senhas diferentes!");
    } else {
      confirm_password.setCustomValidity('');
    }
  }

  password.onchange        = validatePassword;
  confirm_password.onkeyup = validatePassword;
  
     
 </script>
</body>

</html>
