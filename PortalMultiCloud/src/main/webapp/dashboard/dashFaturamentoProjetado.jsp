<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html>
  <html lang="en">
		
  <jsp:include page="/principal/head.jsp"></jsp:include>
  <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
		
  <style type="text/css">
  /* 
     div{
       background-color: #ccc;
       border: 1px solid #000; 
       min-height: 100px;
     }
 */    
     #rowCabecalho{
        min-height: 400px;
     }
     
     .flex-row{
        flex: 1 0 auto;
     }
     
     .negrito {
        font-weight: bold;
     }
     
  </style>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="/principal/theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          <jsp:include page="/util/navbarheader.jsp"></jsp:include>
          <div class="pcoded-main-container" style="background: #dddee0;"> 
             <jsp:include page="/principal/navbarmainmenu.jsp"></jsp:include>
             <form class="form-material" action="<%= request.getContextPath() %>/ServletsFaturamentoProjetado" method="post" id="formDashFaturamentoProjetado">
                <div class="container-fluid">
                
                  <div class="row" id="rowCabecalho">
                     <div class="col-10 d-flex">
                        <div class="d-flex flex-column w-100">
                           <!-- Linha 1 -->
                           <div class="row align-items-center">
	                          <div class="col-3">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark font-italic" style="text-align: center;">Faturamento Projetado</span>
									   <br><br><br>
									   <p class="h2 font-weight-bold" id="idFaturamentoProj">-</p>
			                        </div>
			                     </div>
	                          </div>

	                          <div class="col-2">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Total de Instâncias</span>
									   <br><br>
									   <p class="h3 font-weight-bold" id="idTotalInstancias">-</p>
			                        </div>
			                     </div>
	                          </div>
	                                                
	                          <div class="col-2">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Nuvem</span>
									   <br><br>
									   <p class="h3 font-weight-bold" id="idNuvem">-</p>
			                        </div>
			                     </div>
	                          </div>
	                                                
	                          <div class="col-2">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Resp Lançamento</span>
									   <br><br>
									   <p class="h5 font-weight-bold" id="idRespLancamento">-</p>
			                        </div>
			                     </div>
	                          </div>
	                                                
	                          <div class="col-3">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Razão Social</span>
									   <br><br>
									   <p class="h6 font-weight-bold" id="idRazaoSocial">-</p>
			                        </div>
			                     </div>
	                          </div>
	                                                	                                                
	                       </div>
                          
                           <!-- Linha 2 -->
                           <div class="row align-items-center">
	                          <div class="col-3">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic">Valor Contrato Principal</span>
									   <br><br>
									   <p class="h4 font-weight-bold" id="idValorPrincipal">-</p>
			                        </div>
			                     </div>
	                          </div>

                              <div class="col-2">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Total de Aditivos</span>
			                           <br><br>
			                           <p class="h3 font-weight-bold" id="idTotalAditivos">-</p>
			                        </div>
			                     </div>
	                          </div>

	                          <div class="col-2">	                                             
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Site</span>
									   <br><br>
									   <p class="h3 font-weight-bold" id="idSite">-</p>
			                        </div>
			                     </div>
	                          </div>
 	                          
 	                          <div class="col-2">
	                             <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Fase</span>
			                           <br><br>
			                           <p class="h5 font-weight-bold" id="idFase">-</p>
			                        </div>
			                     </div>
	                          </div>
                          
 	                          <div class="col-3">
	                             <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">PEP ->> CNPJ</span>
			                           <br>
										<select class="custom-select w-100" size="5" style="text-align: center; min-height: 100px; background-color: #dddee0" name="pepCnpj" id="pepCnpj">
										</select>
			                        </div>
			                     </div>
	                          </div>
                          
                           </div>
                           <!-- Linha 3 -->
                           <div class="row align-items-center">
                              
                              <div class="col-3">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark font-italic">Valor Aditivo</span>
			                           <br><br>
			                           <p class="h4 font-weight-bold" id="idValorAditivo">-</p>
			                        </div>
			                     </div>
	                          </div>
	                          
                              <div class="col-2">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic" style="text-align: center;">Total de Usuário</span>
			                           <br><br>
			                           <p class="h3 font-weight-bold" id="idTotalUser">-</p>
			                        </div>
			                     </div>
	                          </div>

                               <div class="col-4">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic">Serviço Contratado</span>
			                           <br><br>
                                       <div style="height: 150px; overflow: scroll;">
										  <table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
										    <thead >
										      <tr class="table-primary">
										        <th scope="col" style="vertical-align: middle;color: white">ID           </th>
										        <th scope="col" style="vertical-align: middle;color: white">Produto      </th>
										        <th scope="col" style="vertical-align: middle;color: white">Tipo Contrato</th>
										        <th scope="col" style="vertical-align: middle;color: white">Quantidade   </th>
										        <th scope="col" style="vertical-align: middle;color: white">Vlr Unitário </th>
										        <th scope="col" style="vertical-align: middle;color: white">Vlr Total    </th>
										      </tr>
										    </thead>
										    <tbody id="TbodyShowServicoContratado">
										  
										    </tbody>
								          </table>
								       </div>
			                        </div>
	                             </div>
                               </div>
                               
                               <div class="col-3">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark  font-italic">Histórico Contrato</span>
			                           <br><br>
                                       <div style="height: 150px; overflow: scroll;">
										  <table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
										    <thead >
										      <tr class="table-primary">
										        <th scope="col" style="vertical-align: middle;color: white">ID              </th>
										        <th scope="col" style="vertical-align: middle;color: white">Tipo Contrato   </th>
										        <th scope="col" style="vertical-align: middle;color: white">ID Origem       </th>
										        <th scope="col" style="vertical-align: middle;color: white">Data Inicio     </th>
										        <th scope="col" style="vertical-align: middle;color: white">Data Fim        </th>
										        <th scope="col" style="vertical-align: middle;color: white">Meses Contratado</th>
										      </tr>
										    </thead>
										    <tbody id="TbodyShowHistoricoContrato">
										    </tbody>
								          </table>
								       </div>
			                        </div>
	                             </div>
                               </div>
                               
                            </div>
                            <div class="row align-items-center">
                                <div class="col-12">
			                     <div class="row">
			                        <div class="form-group form-default form-static-label col" style="text-align: center;">
			                           <span class="text-dark font-italic">Instâncias</span>
			                           <br><br>
                                       <div style="height: 150px; overflow: scroll;">
										  <table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-sm" style="line-height:0px;">
										    <thead >
										      <tr class="table-primary">
													<th scope="col" style="vertical-align: middle;color: white">Hostname           </th>
													<th scope="col" style="vertical-align: middle;color: white">Familia Flavors    </th>
													<th scope="col" style="vertical-align: middle;color: white">Ambiente           </th>
													<th scope="col" style="vertical-align: middle;color: white">Sistema Operacional</th>
													<th scope="col" style="vertical-align: middle;color: white">Tamanho Disco      </th>
													<th scope="col" style="vertical-align: middle;color: white">Tipo Serviço       </th>
													<th scope="col" style="vertical-align: middle;color: white">Retenção Backup    </th>
													<th scope="col" style="vertical-align: middle;color: white">Recurso Temporário </th>
													<th scope="col" style="vertical-align: middle;color: white">Data Cadastro      </th>
													<th scope="col" style="vertical-align: middle;color: white">Tipo Recurso       </th>
													<th scope="col" style="vertical-align: middle;color: white">Início Vigência    </th>
													<th scope="col" style="vertical-align: middle;color: white">Final Vigência     </th>
													<th scope="col" style="vertical-align: middle;color: white">Dias Vigência      </th>
													<th scope="col" style="vertical-align: middle;color: white">Tempo Vigência     </th>
										      </tr>
										    </thead>
										    <tbody id="TbodyShowInstancias">
										  
										    </tbody>
								          </table>
								       </div>
			                        </div>
	                             </div>
                               </div>
                            
                            </div>
                            
                        </div>                     
                     </div>
                     <div class="col-2 d-flex">
                         <div class="d-flex flex-column w-100">
                            <div class="flex-row">
						       <label for="inRazaoSocial" class="p-3 mb-2 w-100 bg-primary text-white font-weight-bold font-italic">Pesquisa por Razão Social</label>
						       <div class="col-sm-10">
						          <input type="text" name="inRazaoSocial" id="inRazaoSocial" class="form-control text-primary" style="font-weight: bold; font-style: italic;"  placeholder="Digite a Razão Social" onkeyup="carregaRazaoSocial( this.value );" autocomplete="off" required="required" value="">
						          <span id="resultado-pesquisa-raza"></span>
						       </div>
                            </div>
                            <div class="flex-row">
						       <label for="inAlias" class="p-3 mb-2 w-100 bg-primary text-white font-weight-bold font-italic">Pesquisa por Alias</label>
						       <div class="col-sm-10">
						          <input type="text" name="inAlias" id="inAlias" class="form-control text-primary" style="font-weight: bold; font-style: italic;" placeholder="Digite o Alias" onkeyup="carregaAlias( this.value );" autocomplete="off" required="required" value="">
						          <span id="resultado-pesquisa-alias"></span>
						       </div>
                            </div>
                            
                            <div class="flex-row">
						       <label for="inPEP" class="p-3 mb-2 w-100 bg-primary text-white font-weight-bold font-italic">Pesquisa por PEP</label>
						       <div class="col-sm-10">
						          <input type="text" name="inPEP" id="inPEP" class="form-control text-primary" style="font-weight: bold; font-style: italic;"  placeholder="Digite o PEP" onkeyup="carregaPep( this.value );" autocomplete="off" required="required" value="">
						          <span id="resultado-pesquisa-pep"></span>
						       </div>
                            </div>
                         </div>
                     </div>
                  </div>
                  
                </div>
			 </form>	
          </div>
      </div>
  </div>
    
  <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>
  <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    
<script>
/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function carregaRazaoSocial( valor ) {
	 if( valor.length > 2 ){
        var urlAction = document.getElementById("formDashFaturamentoProjetado" ).action;
		 $.ajax({
	 			method : "get",
	 			url : urlAction,
	 			data : "razaSocial=" + valor + '&acao=ListaRazaoSocial',
	 			success: function(lista){
	 			    var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		        var razaoSocialAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	    if( json[p].razao_social !== undefined  ){
	 		        	    	razaoSocialAux = json[p].razao_social;
	 		        	    } else razaoSocialAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosRazaoSocial(" +  json[p].id_contrato + ",'" +  razaoSocialAux + "','"  +"')\">" + json[p].razao_social + "</li>";
	 		        	}
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do Servlet
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhuma empresa encontrada!</li>";
	 		        }
	 		        // Fechar a lista de Clientes 
	 		        conteudoHTML += "</ul>";
	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa-raza').innerHTML = conteudoHTML;
	  			}
	 	 }).fail(function( xhr, status, errorThrown ){
	 			alert('Erro ao carregar Cliente pelp Razao Social: ' + xhr.responseText);
	 	 }); 
	 }else {
	        // Fechar a lista de produtos ou o erro
	        document.getElementById("resultado-pesquisa-raza").innerHTML = "";
	 }
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function carregaAlias( valor ) {
	 if( valor.length > 1 ){
       var urlAction = document.getElementById("formDashFaturamentoProjetado" ).action;
		 $.ajax({
	 			method : "get",
	 			url : urlAction,
	 			data : "alias=" + valor + '&acao=ListaAlias',
	 			success: function(lista){
	 			    var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		        var aliasAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	    if( json[p].alias !== undefined  ){
	 		        	    	aliasAux = json[p].alias;
	 		        	    } else razaoSocialAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosAlias(" +  json[p].id_contrato + ",'" +  aliasAux + "','"  +"')\">" + json[p].alias + "</li>";
	 		        	}
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do Servlet
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhuma empresa encontrada!</li>";
	 		        }
	 		        // Fechar a lista de Clientes 
	 		        conteudoHTML += "</ul>";
	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa-alias').innerHTML = conteudoHTML;
	  			}
	 	 }).fail(function( xhr, status, errorThrown ){
	 			alert('Erro ao carregar Cliente pelp Alias: ' + xhr.responseText);
	 	 }); 
	 }else {
        // Fechar a lista de produtos ou o erro
        document.getElementById("resultado-pesquisa-alias").innerHTML = "";
	 }
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function carregaPep( valor ) {
	
	 if( valor.length > 1 ){
       var urlAction = document.getElementById("formDashFaturamentoProjetado" ).action;
		 $.ajax({
	 			method : "get",
	 			url : urlAction,
	 			data : "pep=" + valor + '&acao=ListaPEP',
	 			success: function(lista){
	 			    var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		        var pepAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	    if( json[p].pep !== undefined  ){
	 		        	    	pepAux = json[p].pep;
	 		        	    } else razaoSocialAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosPep(" +  json[p].id_contrato + ",'" +  pepAux + "','"  +"')\">" + json[p].pep + "</li>";
	 		        	}
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do Servlet
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhuma empresa encontrada!</li>";
	 		        }
	 		        // Fechar a lista de Clientes 
	 		        conteudoHTML += "</ul>";
	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa-pep').innerHTML = conteudoHTML;
	  			}
	 	 }).fail(function( xhr, status, errorThrown ){
	 			alert('Erro ao carregar Cliente pelp PEP: ' + xhr.responseText);
	 	 }); 
	 }else {
        // Fechar a lista de produtos ou o erro
        document.getElementById("resultado-pesquisa-pep").innerHTML = "";
	 }
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function getDadosPep( idContrato, pepAux ) {
	document.getElementById("inRazaoSocial").value = "";
	document.getElementById("inAlias"      ).value = "";
	document.getElementById("inPEP"        ).value = pepAux;
 
    // Fechar a lista de produtos ou o erro
    document.getElementById("resultado-pesquisa-pep").innerHTML = "";
    consutaInfoTela( idContrato );
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function getDadosAlias( idContrato, aliasAux ) {
	document.getElementById("inRazaoSocial").value = "";
	document.getElementById("inPEP"        ).value = "";
	document.getElementById("inAlias"      ).value = aliasAux;
 
    // Fechar a lista de produtos ou o erro
    document.getElementById("resultado-pesquisa-alias").innerHTML = "";
    consutaInfoTela( idContrato );
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function getDadosRazaoSocial( idContrato, razaoSocialAux ) {
	document.getElementById("inAlias"      ).value = "";
	document.getElementById("inPEP"        ).value = "";
    document.getElementById("inRazaoSocial").value = razaoSocialAux;
 
    // Fechar a lista de produtos ou o erro
    document.getElementById("resultado-pesquisa-raza").innerHTML = "";
    consutaInfoTela( idContrato );
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function consutaInfoTela( idContrato ) {
	
	var urlAction = document.getElementById("formDashFaturamentoProjetado" ).action;
	$.ajax({
		method : "get",
		url : urlAction,
		data : "idContrato=" + idContrato + '&acao=montaTela',
		success: function(lista){
		    var json = JSON.parse(lista);
		    
	        if( json !== 'VAZIO' ) montaTelaJSP( json );
	        
		}
	 }).fail(function( xhr, status, errorThrown ){
			alert('Erro carregar Usuários: ' + xhr.responseText);
	 }); 
	
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function montaTelaJSP( modalContrato ) {
	document.getElementById('idFaturamentoProj').innerHTML = modalContrato.mdVlrsContrato.vlr_total_conv;
	document.getElementById('idValorPrincipal' ).innerHTML = modalContrato.mdVlrsContrato.vlr_total_principal;
	document.getElementById('idValorAditivo'   ).innerHTML = modalContrato.mdVlrsContrato.vlr_total_aditivo;
	document.getElementById('idNuvem'          ).innerHTML = modalContrato.nuvem;
	document.getElementById('idTotalInstancias').innerHTML = modalContrato.total_instancias;
	document.getElementById('idRespLancamento' ).innerHTML = modalContrato.login_cadastro;
	document.getElementById('idRazaoSocial'    ).innerHTML = modalContrato.razao_social;
	document.getElementById('idSite'           ).innerHTML = modalContrato.site;
	document.getElementById('idTotalAditivos'  ).innerHTML = modalContrato.tota_aditivos;
	document.getElementById('idTotalUser'      ).innerHTML = modalContrato.qty_usuario_contratada;
	document.getElementById('idFase'           ).innerHTML = modalContrato.fase_contrato;
	
	montapepCnpj( modalContrato.mdPepCnpjs, modalContrato.cnpj );
	montaProdutosContratos( modalContrato.mdProdutosContratos  );
	montaHistoricoContrato( modalContrato.mdHistoricoContrato  );
	montaRecursosContrato( modalContrato.mdRecursosContrato )
	
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function montapepCnpj( modalPepCnpj, cnpj ) {
	var option   = '';
	var selected = '';
	for(var p = 0; p < modalPepCnpj.length; p++){

// alert( modalPepCnpj[p].cnpj + ' - ' + cnpj);

		if( modalPepCnpj[p].cnpj === cnpj ) selected = 'selected';
		else selected = '';
		option += '<option value=' + modalPepCnpj[p].id_contrato + ' ' + selected + '>' + modalPepCnpj[p].cnpj + '</option>';
	}
	$("#pepCnpj").html(option).show(); 
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function montaProdutosContratos( mdProdutosContratos ){
	let tbody = document.getElementById('TbodyShowServicoContratado');
	tbody.innerText = '';
	
	for(let i = 0; i < mdProdutosContratos.length; i++){
		if(  mdProdutosContratos[i].produto !== undefined ){ 
			// Cria as linhas
		    let tr = tbody.insertRow();
		          
			// Crias as celulas
			let td_idProduto    = tr.insertCell();
			let td_Produto      = tr.insertCell();
			let td_TipoContrato = tr.insertCell();
			let td_Quantidade   = tr.insertCell();
			let td_VlrUnitario  = tr.insertCell();
			let td_VlrTotal     = tr.insertCell();
			
			// Inseri os valores do objeto nas celulas
			td_idProduto.innerText    = ( mdProdutosContratos[i].id_produto     !== undefined ? mdProdutosContratos[i].id_produto     : '' );
			td_Produto.innerText      = ( mdProdutosContratos[i].produto        !== undefined ? mdProdutosContratos[i].produto        : '' );
			td_TipoContrato.innerText = ( mdProdutosContratos[i].tipo_contrato  !== undefined ? mdProdutosContratos[i].tipo_contrato  : '' );
			td_Quantidade.innerText   = ( mdProdutosContratos[i].qty_servico    !== undefined ? mdProdutosContratos[i].qty_servico    : '' );
			td_VlrUnitario.innerText  = ( mdProdutosContratos[i].valor_unitario !== undefined ? mdProdutosContratos[i].valor_unitario : '' );
			td_VlrTotal.innerText     = ( mdProdutosContratos[i].valor          !== undefined ? mdProdutosContratos[i].valor          : '' );
			
			/////////////////////////////////////////////////////////////////
			td_idProduto.setAttribute   ('style', 'vertical-align: middle' );
			td_Produto.setAttribute     ('style', 'vertical-align: middle' );
			td_TipoContrato.setAttribute('style', 'vertical-align: middle' );
			td_Quantidade.setAttribute  ('style', 'vertical-align: middle' );
			td_VlrUnitario.setAttribute ('style', 'vertical-align: middle' );
			td_VlrTotal.setAttribute    ('style', 'vertical-align: middle' );
			/////////////////////////////////////////////////////////////////
		}
	}
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function montaHistoricoContrato( mdHistoricoContrato ){
	let tbody = document.getElementById('TbodyShowHistoricoContrato');
	tbody.innerText = '';
	
	for(let i = 0; i < mdHistoricoContrato.length; i++){
		
		// Cria as linhas
	    let tr = tbody.insertRow();
	          
		// Crias as celulas
		let td_idContrato              = tr.insertCell();
		let td_historicoContrato       = tr.insertCell();
		let td_renovacaoContratoOrigem = tr.insertCell();
		let td_dtInicioVigencia        = tr.insertCell();
		let td_dtFinalVigencia         = tr.insertCell();
		let td_qtyMesesVigencia        = tr.insertCell();
		
		// Inseri os valores do objeto nas celulas
		td_idContrato.innerText              = ( mdHistoricoContrato[i].id_contrato               !== undefined ? mdHistoricoContrato[i].id_contrato               : ' - ' );
		td_historicoContrato.innerText       = ( mdHistoricoContrato[i].historico_contrato        !== undefined ? mdHistoricoContrato[i].historico_contrato        : ' - ' );
		td_renovacaoContratoOrigem.innerText = ( mdHistoricoContrato[i].renovacao_contrato_origem !== undefined ? mdHistoricoContrato[i].renovacao_contrato_origem : ' - ' );
		td_dtInicioVigencia.innerText        = ( mdHistoricoContrato[i].dt_inicio_vigencia        !== undefined ? mdHistoricoContrato[i].dt_inicio_vigencia        : ' - ' );
		td_dtFinalVigencia.innerText         = ( mdHistoricoContrato[i].dt_final_vigencia         !== undefined ? mdHistoricoContrato[i].dt_final_vigencia         : ' - ' );
		td_qtyMesesVigencia.innerText        = ( mdHistoricoContrato[i].qty_meses_vigencia        !== undefined ? mdHistoricoContrato[i].qty_meses_vigencia        : ' - ' );
		
		/////////////////////////////////////////////////////////////////
		td_idContrato.setAttribute             ('style', 'vertical-align: middle' );
		td_historicoContrato.setAttribute      ('style', 'vertical-align: middle' );
		td_renovacaoContratoOrigem.setAttribute('style', 'vertical-align: middle' );
		td_dtInicioVigencia.setAttribute       ('style', 'vertical-align: middle' );
		td_dtFinalVigencia.setAttribute        ('style', 'vertical-align: middle' );
		td_qtyMesesVigencia.setAttribute       ('style', 'vertical-align: middle' );
		/////////////////////////////////////////////////////////////////
		
	}
}

/*********************************************************************************************************/
/*                                                                                                       */
/*                                                                                                       */
/*********************************************************************************************************/
function montaRecursosContrato( mdRecursosContrato ){
	let tbody = document.getElementById('TbodyShowInstancias');
	tbody.innerText = '';
	
	for(let i = 0; i < mdRecursosContrato.length; i++){
		
		// Cria as linhas
	    let tr = tbody.insertRow();
	          
		// Crias as celulas
		let td_hostname           = tr.insertCell();
		let td_familiaFlavors     = tr.insertCell();
		let td_ambiente           = tr.insertCell();
		let td_sistemaOperacional = tr.insertCell();
		let td_tamanhoDisco       = tr.insertCell();
		let td_tipoServico        = tr.insertCell();
		let td_retencaoBackup     = tr.insertCell();
		let td_recursoTemporario  = tr.insertCell();
		let td_dtDadastro         = tr.insertCell();
		let td_tipoRecurso        = tr.insertCell();
		let td_dtInicioVigencia   = tr.insertCell();
		let td_dtFinalVigencia    = tr.insertCell();
		let td_qtyDiasVigencia    = tr.insertCell();
		let td_descTempoVigencia  = tr.insertCell();

		// Inseri os valores do objeto nas celulas
		td_hostname.innerText           = ( mdRecursosContrato[i].hostname            !== undefined ? mdRecursosContrato[i].hostname            : ' - ' );
		td_familiaFlavors.innerText     = ( mdRecursosContrato[i].familia_flavors     !== undefined ? mdRecursosContrato[i].familia_flavors     : ' - ' );
		td_ambiente.innerText           = ( mdRecursosContrato[i].ambiente            !== undefined ? mdRecursosContrato[i].ambiente            : ' - ' );
		td_sistemaOperacional.innerText = ( mdRecursosContrato[i].sistema_operacional !== undefined ? mdRecursosContrato[i].sistema_operacional : ' - ' );
		td_tamanhoDisco.innerText       = ( mdRecursosContrato[i].tamanho_disco       !== undefined ? mdRecursosContrato[i].tamanho_disco       : ' - ' );
		td_tipoServico.innerText        = ( mdRecursosContrato[i].tipo_servico        !== undefined ? mdRecursosContrato[i].tipo_servico        : ' - ' );
		td_retencaoBackup.innerText     = ( mdRecursosContrato[i].retencao_backup     !== undefined ? mdRecursosContrato[i].retencao_backup     : ' - ' );
		td_recursoTemporario.innerText  = ( mdRecursosContrato[i].recurso_temporario  !== undefined ? mdRecursosContrato[i].recurso_temporario  : ' - ' );
		td_dtDadastro.innerText         = ( mdRecursosContrato[i].dt_cadastro         !== undefined ? mdRecursosContrato[i].dt_cadastro         : ' - ' );
		td_tipoRecurso.innerText        = ( mdRecursosContrato[i].tipo_recurso        !== undefined ? mdRecursosContrato[i].tipo_recurso        : ' - ' );
		td_dtInicioVigencia.innerText   = ( mdRecursosContrato[i].dt_inicio_vigencia  !== undefined ? mdRecursosContrato[i].dt_inicio_vigencia  : ' - ' );
		td_dtFinalVigencia.innerText    = ( mdRecursosContrato[i].dt_final_vigencia   !== undefined ? mdRecursosContrato[i].dt_final_vigencia   : ' - ' );
		td_qtyDiasVigencia.innerText    = ( mdRecursosContrato[i].qty_dias_vigencia   !== undefined ? mdRecursosContrato[i].qty_dias_vigencia   : ' - ' );
		td_descTempoVigencia.innerText  = ( mdRecursosContrato[i].desc_tempo_vigencia !== undefined ? mdRecursosContrato[i].desc_tempo_vigencia : ' - ' );
		
		/////////////////////////////////////////////////////////////////
		td_hostname.setAttribute          ('style', 'vertical-align: middle' );
		td_familiaFlavors.setAttribute    ('style', 'vertical-align: middle' );
		td_ambiente.setAttribute          ('style', 'vertical-align: middle' );
		td_sistemaOperacional.setAttribute('style', 'vertical-align: middle' );
		td_tamanhoDisco.setAttribute      ('style', 'vertical-align: middle' );
		td_tipoServico.setAttribute       ('style', 'vertical-align: middle' );
		td_retencaoBackup.setAttribute    ('style', 'vertical-align: middle' );
		td_recursoTemporario.setAttribute ('style', 'vertical-align: middle' );
		td_dtDadastro.setAttribute        ('style', 'vertical-align: middle' );
		td_tipoRecurso.setAttribute       ('style', 'vertical-align: middle' );
		td_dtInicioVigencia.setAttribute  ('style', 'vertical-align: middle' );
		td_dtFinalVigencia.setAttribute   ('style', 'vertical-align: middle' );
		td_qtyDiasVigencia.setAttribute   ('style', 'vertical-align: middle' );
		td_descTempoVigencia.setAttribute ('style', 'vertical-align: middle' );
		/////////////////////////////////////////////////////////////////
		
	}
}

// name="pepCnpj" id="pepCnpj">


</script>
    
</body>

</html>
