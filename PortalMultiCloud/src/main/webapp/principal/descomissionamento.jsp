<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="tagsDesligamento"%>  
    
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

<jsp:include page="head.jsp"></jsp:include>
  <style>
	   .box_dark{
		  position: fixed;
		  left: 0;
		  top: 0;
		  width: 100vw;
		  height: 100vw;
		  background: rgba(0,0,0,.7);
		  display: none;
	   }
	   .box_modal{
		   background: #FFF;
		   padding: 15px;
		   position: relative;
		   top: 60ex;
		   left: 50%;
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
											    <div class="card">
								                   <h4 class="card-header border-danger">Contratos em status de "Descomissionamento / Distrato"</h4>
								                   <div class="card-body">
									                   <form class="form-material" action="<%= request.getContextPath() %>/ServletDescomissionamento" method="post" id="formDesligamento">
									                   
				                                            <!-- URL da base da API Base TST ou PRD  -->
				                                            <input type="hidden"  name="urlAPI" id="urlAPI" value="<%=session.getAttribute("urlAPI_PortalMudanca")%>">
				                                            <input type="hidden"  name="userLogado" id="userLogado" value="<%=session.getAttribute("usuario")%>">
				                                            <input type="hidden"  name="usuarioEmail" id="usuarioEmail" value="<%=session.getAttribute("usuarioEmail")%>">
									                   
													        <h5 class="card-title">Lista de Contratos em Descomissionamento / Distrato</h5><hr>
	                                                        <div style="height: 500px; overflow: scroll;">
																<!--  <table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-lg" style="line-height:0px;"> -->
																<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaDesligamento">
																  <thead >
																    <tr class="table-primary">
																      <th scope="col" style="vertical-align: middle;color: white">Contrato Revertido           </th>
																      <th scope="col" style="vertical-align: middle;color: white">Solicitar Deslig. Ambiente   </th>
																      <th scope="col" style="vertical-align: middle;color: white">ID do Contrato               </th>
																      <th scope="col" style="vertical-align: middle;color: white">Cliente                      </th>
																      <th scope="col" style="vertical-align: middle;color: white">Alias                        </th>
																      <th scope="col" style="vertical-align: middle;color: white">Status                       </th>
																      <th scope="col" style="vertical-align: middle;color: white">Data Solicitação             </th>
																      <th scope="col" style="vertical-align: middle;color: white">Data Alteração               </th>
																      <th scope="col" style="vertical-align: middle;color: white">Motivo                       </th>
																      <th scope="col" style="vertical-align: middle;color: white">Descrição Reveção            </th>
																      <th scope="col" style="vertical-align: middle;color: white">Data Solicitação Deslig.     </th>
																      <th scope="col" style="vertical-align: middle;color: white">Obs. Solicitação Deslig.     </th>
																      <th scope="col" style="vertical-align: middle;color: white">E-mail de Solicitação Enviado</th>
																      <th scope="col" style="vertical-align: middle;color: white">Ambiente Desligado           </th>
																      <th scope="col" style="vertical-align: middle;color: white">Data Ambiente Desligado      </th>
																      <th scope="col" style="vertical-align: middle;color: white">Usuario Desligamento         </th>
																    </tr>
																  </thead>
																  <tbody id="TbodyDesligamento">

																  </tbody>
																</table>
														    </div>
												       </form>
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
        </div>
    </div>


	<!-- 
	   Modal Solicitar Desligamento e abriri GMUD automatica
	 -->
	   <div class="modal t-modal primary" id=ModalSolicitarDesligamento tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"  style="max-width: 50% !important;" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
		        <div class="modal-header">
		          <h5 class="modal-title" id="TituloModalCentralizado">Solicitar Desligamento</h5>
		          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
		            <span aria-hidden="true">&times;</span>
		          </button>
		        </div>
				<!-- Modal body -->
				<div class="modal-body">
				   <div class="row">
					  <div class="col-sm-12">
						 <div class="card">
						    <div class="card-block">
                               <form class="form-material">	
                               			
                                  <div class="form-row">
                                     <input type="hidden"  name="idDescomiSoli" id="idDescomiSoli">
                                     <input type="hidden"  name="idContratoSoli" id="idContratoSoli">
                                     <input type="hidden"  name="idClienteSoli" id="idClienteSoli">
                                     
 
                                        <div class="form-group form-default form-static-label col-md-3 mb-3">
                                           <label class="font-weight-bold font-italic">Cliente</label>
                                           <input style="color: #000000" type="text" name="mdClienteSoli" class="form-control" name="mdClienteSoli" id="mdClienteSoli" readonly="readonly">
                                        </div>
										
										<div class="form-group form-default form-static-label col-md-3 mb-3" id="app">
                                           <label class="font-weight-bold font-italic">Data Desligamento</label>
                                           <input style="color: #000000" type="datetime-local" name="dtDesligamentoSoli" id="dtDesligamentoSoli" class="form-control" @input="close()">								     
										</div>	
                                        
                                        <div class="form-group form-default form-static-label col-md-3 mb-3" >
                                           <label class="font-weight-bold font-italic">Status Contrato</label>
                                           <select style="color: #000000" name="idStatusContratoDescomisSoli" id="idStatusContratoDescomisSoli" class="form-control">
                                              <option value="-1" disabled selected>[-Selecione-]</option>
                                              <tagsDesligamento:listaStatusContratoDescomissionamento/>
                                           </select> 
                                        </div>
                                        
                                        <div class="form-group form-default form-static-label col-md-3 mb-3" >
                                           <label class="font-weight-bold font-italic">Categoria Padrão</label>
                                           <select style="color: #000000" name="seletCategoriaPadrao" id="seletCategoriaPadrao" class="form-control">
                                              <option value="-1" disabled selected>[-Selecione-]</option>                                              
                                           </select> 
                                        </div>

                                  </div>
                                  
                                  <hr>  
                                  <br>
                                  
                                  <div class="form-row">
                                     <div class="form-group form-default form-static-label col-md-12 mb-12">
                                          <label class="font-weight-bold font-italic">Obs Desligamento</label>
                                          <textarea style="color: #000000" class="form-control" id="obsDesligamentoSoli" name="obsDesligamentoSoli" placeholder="Obs Desligamento" rows="15" ></textarea>
                                     </div>
                                  </div>
                               </form>																
                            </div>
                         </div>
                      </div>
                   </div>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
				    <button type="button" class="btn btn-outline-success" onclick="processarSolicitaDesligamento()">Atualizar</button>
					<button type="button" class="btn btn-outline-success" data-dismiss="modal">Fechar</button>
				</div>
			
			</div>
		</div>
		
        <div id="box_dark" class="box_dark" > 
             <div class="spinner-border text-info box_modal" ></div>
        </div>
		
	</div>

	<!-- 
	   Modal Descomissionamento / Distrato
	 -->
	   <div class="modal t-modal primary" id=ModalReversaoDistrato tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-dialog-centered modal-lg"  style="max-width: 50% !important;" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
		        <div class="modal-header">
		          <h5 class="modal-title" id="TituloModalCentralizado">Reversão Distrato</h5>
		          <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
		            <span aria-hidden="true">&times;</span>
		          </button>
		        </div>
				<!-- Modal body -->
				<div class="modal-body">
				
				   <div class="row">
					  <div class="col-sm-12">
						 <div class="card">
						    <div class="card-block">
                               <form class="form-material">	
                                  <div class="form-row">
					                 <input type="hidden"  name="idDescomi" id="idDescomi">
						                <div class="form-group form-default form-static-label col-md-4 mb-6">
							               <label class="font-weight-bold font-italic">ID Contrato</label>
							               <input style="color: #000000" class="form-control" type="text" name="mdIdContrato" id="mdIdContrato" readonly="readonly">
						                </div>
						                
						                <div class="form-group form-default form-static-label col-md-4 mb-6">
						                   <label class="font-weight-bold font-italic">Cliente</label>
							               <input style="color: #000000" class="form-control" type="text" name="mdCliente" id="mdCliente" readonly="readonly">
						                </div>
                                  </div>
                                  <hr>  
                                  <br>
                                  <div class="form-row">
                                     <div class="form-group form-default form-static-label col-md-12 mb-12">
                                        <label class="font-weight-bold font-italic">Descrição da mudança</label>
                                        <textarea style="color: #000000" class="form-control" id="obsDesligamentoSoli" name="obsDesligamentoSoli" placeholder="Obs Desligamento" rows="15" ></textarea>
                                     </div>
                                  </div>
                               </form>																
                            </div>
                         </div>
                      </div>
                   </div>

				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
				    <button type="button" class="btn btn-outline-success" onclick="atualizaContratoReversao(  )">Atualizar</button>
					<button type="button" class="btn btn-outline-success" data-dismiss="modal">Fechar</button>
				</div>
			
			</div>
		</div>
	</div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script src="https://unpkg.com/vue@2"></script>
    <jsp:include page="javascriptfile.jsp"></jsp:include>
    
    <script type="text/javascript">

   listaCategoriaPadroesDesativacao( );
   
  /******************************************************************/
  /*                                                                */ 
  /*                                                                */
  /******************************************************************/    

  async function listaCategoriaPadroesDesativacao( ){
    try {
	    showLoading();

   	    let urlBase = document.getElementById('urlAPI').value;
	    let urlAPI  = urlBase + 'listaCategoriaPadroesDesativacao';	 
	    
	    const l_CatPadroesDes = await fetch(urlAPI, { method: 'GET', 		                                    
              headers: { 'Content-Type': 'application/json'}
             }).then(response => response.text())
              .then(body => { return body; })
               .catch(function(err){
		                     var iconi           = "error";
		                     var tituloPrincipal = "ERRO";
		                     var textoPrincipal  = "Erro: " + xhr.responseText;
		                     MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
               });

        if( l_CatPadroesDes !== null ){
        	let v_l_CatPadroesDes   = JSON.parse( l_CatPadroesDes );
        	
		    var option = '<option value="" disabled selected>[-Selecione-]</option>';
			for(var p = 0; p < v_l_CatPadroesDes.length; p++) 
				option += '<option value=' + v_l_CatPadroesDes[p].id_categoria_padrao + '>' + v_l_CatPadroesDes[p].categoria_padrao + '</option>';
			$("#seletCategoriaPadrao").html(option).show();  			    
        }else
        	MensagemConfimacao( "info", "Lista Categoria Padrão para Desativação", "Não foi encontrado Categoria Padrão para Desativação!" );
        hideLoading();
   } catch (error) {
     hideLoading();
     MensagemConfimacao( "error", "Tarefas", "Erro ao Inicializar lista Categoria Padrão para Desativação: " + error );
    }
  }
    
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
    function showLoading(){
    	$("#box_dark").css('display','flex');
    	$("#box_dark_form").css('display','flex');
     }

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
     function hideLoading(){
    	$("#box_dark").css('display','none');
    	$("#box_dark_form").css('display','none');
     }
    
    /******************************************************************/
    /*  Fechar o Input data ao cliclar                                */
    /*                                                                */
    /******************************************************************/ 
    new Vue({
        el: '#app', //Tells Vue to render in HTML element with id "app"
        methods: {
          close() {
            let el = document.getElementById("dtDesligamentoSoli");
            if (el) {
              el.blur();
            }
          },
        }
    });
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
	// Inicia a tabela ao carregar a pagina!
	window.onload = function () { 
	  montaTabelaDescomissionamento( );
	}

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    async function validacaoSolicitaDesligamento(){

		if( document.getElementById("idStatusContratoDescomisSoli").value === '-1'){
			 return "Favor selecionar o 'Status' para o Descomissionamento!";
		}
        
		var dtDesli = document.getElementById("dtDesligamentoSoli").value;
		if( dtDesli.trim() === '') {
			return "Favor selecionar uma data para 'Desligamento' do Ambiente!";
		}
				
		var obs = document.getElementById("obsDesligamentoSoli").value;
		if( obs.trim() === '') {
			return "Favor preencher o campo 'Obs Desligamento'!";
		}

		return 'OK';
    
	}
    
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    async function conversion(mins) {
        // getting the hours.
        let hrs = Math.floor(mins / 60); 
        // getting the minutes.
        let min = mins % 60; 
        // formatting the hours.
        hrs = hrs < 10 ? '0' + hrs : hrs; 
        // formatting the minutes.
        min = min < 10 ? '0' + min : min;
        
        let result = hrs + ':' + min;
        
        // returning them as a string.
      return result; 
//      return `${hrs}:${min}`; 
    }
    
    async function FormataStringData(data) {
        var dia  = data.split("/")[0];
        var mes  = data.split("/")[1];
        var ano  = data.split("/")[2];

       return ano + '-' + ("0"+mes).slice(-2) + '-' + ("0"+dia).slice(-2);
      // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
    }
    
    async function SomaNDiasData(data, dias) {

    	// var time = new Date('2014-03-14T23:54:00');
    	var time = new Date(data);
    	var outraData = new Date();
    	outraData.setDate(time.getDate() + dias); // Adiciona N dias    	

       return outraData;
      // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
    }	
    
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    async function getTarefas( listaItemCatPadrao, dataTarefa ){
		let list_Tarefas = [];
		for(let i = 0; i < listaItemCatPadrao.length; i++){
			
			var dr = await conversion( listaItemCatPadrao[i].duracao );
			
			let l_Tarefas = {
	            titulo_atividade_mudanca: listaItemCatPadrao[i].tituloCatPadrao ,
				atividade_mudanca       : listaItemCatPadrao[i].descCatPadrao   ,
	            dt_tarefa               : dataTarefa                            ,
				duracao                 : dr                                    ,
//				enviar_email            : listaItemCatPadrao.enviarEmail                                 , // teste
				enviar_email            : 1                                     ,
	            responsavelAtividade    :{id_responsavel_atividade:'17' },
	            statusAtividade         :{id_status_atividade     :'2' }
			};
			list_Tarefas.push(l_Tarefas);
			
		}
		
		return list_Tarefas;
    }
    
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
	async function getDadosApi( listaItemCatPadrao, clientesAfetado, login, emailSolicitante, dtSugeridaDeslgue, dtFimGMUD, descricaoMudanca, justificativaMudanca, idDescomissionamento, idGmudPadrao ){

    	let dtInicio = dtSugeridaDeslgue.substr(0,10);
		let dtFim    = dtFimGMUD.substr(0,10);
		let hrInicio = dtSugeridaDeslgue.substr(11,8);
		let hrFim    = dtFimGMUD.substr(11,8);
    
    	////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		let list_ClientesAfetados = await getCategoriaPadrao( clientesAfetado );
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		let list_Tarefas = await getTarefas( listaItemCatPadrao, dtInicio );
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		
		let dadostMudanca = {
		    titulo_mudanca        : 'GMUD DE DESCOMISSIONAMENTO PARA O CLIENTE "' + clientesAfetado.nome_cliente + '" !',
 	   	    login_user            : login                                         ,
 	   	    email_solicitante     : emailSolicitante                              ,
 	   	    
 	   	    criticidade           : {id_criticidade: '2'                          ,
 	   	                                criticidade: 'MEDIA'}                     ,
 	   	                                
 	   	    impactoMudanca        : {id_impacto_mudanca: '2'                      ,
 	   	                                impacto_mudanca: 'MEDIA'}                 ,
 	   	                                
 	   	    tipoMudanca           : {id_tipo_mudanca: '3'                         ,
 	   	                                tipo_mudanca: 'PADRÃO'         }          ,
 	   	                                
 	   	    categoriaPadrao        : {id_categoria_padrao: idGmudPadrao }         ,
 	   	                                 
 	   	    mudancaClientesAfetados: list_ClientesAfetados                        ,
 	   	    
 	   	    atividadesMudanca      : list_Tarefas                                 ,
 	   	    
 	   	    dadosMudanca           : {dt_inicio            : dtInicio             ,
 	   	                              hr_inicio            : hrInicio             ,
 	   	                              dt_final             : dtFim                ,
 	   	                              hr_final             : hrFim                ,
 	   	                              dsc_mudanca          : descricaoMudanca     ,
 	   	                              justificativa_mudanca: justificativaMudanca ,
 	   	                              id_descomissionamento: idDescomissionamento 
 	   	                             }
 	   	                                 ,
 	   	    acaoPosAtividade        : {plano_teste: '', plano_rollback:''}                
 	   	};  


 	   	return dadostMudanca;     

	}

    
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    async function getCategoriaPadrao( clientesAfetado ) {
    	
		let list_ClientesAfetados = [];
		let l_ClientesAfetados = { clientesAfetados: {id_clientes_af: clientesAfetado.id_clientes_af,
		                                                nome_cliente: clientesAfetado.nome_cliente }
		                             };
            list_ClientesAfetados.push(l_ClientesAfetados);
		
       return  list_ClientesAfetados;	
    	
    }

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    async function solicitaDesligamento( idDescomissionamento, idContrato, idStatus, status, obsDesligamento, dtSugeridaDeslgue, dtFimGMUD, idCliente, idGmudPadrao ) {
	    try {

	      showLoading();
   	      // valida se os dados foram preenchidos corretamente.
    	  const validacao = await validacaoSolicitaDesligamento();
          if( validacao !== 'OK'  ) throw validacao; 
	      
	      let urlBase = document.getElementById('urlAPI').value;
	      let idCatPadrao = 7; // Categoria serah criada pelo time de suporte.
	      let urlAPIItemCategoria  = urlBase + 'listaItemCategoriaPadrao/' + idCatPadrao;
		  const listaTarefasPadrao = await fetch(urlAPIItemCategoria).then(response => response.json());
		  if( listaTarefasPadrao === null )throw listaTarefasPadrao;
		  
		  let urlAPIClienteAf  = urlBase + 'obterByIdClientesAfetadosPortal/' + idCliente;	
		  const clieAfetado = await fetch(urlAPIClienteAf).then(response => response.json());
		  if( clieAfetado.error !== undefined )throw clieAfetado.error;
		  
		  var userLogado           = document.getElementById('userLogado'  ).value;
		  var usuarioEmail         = document.getElementById('usuarioEmail').value;
		  var descricaoMudanca     = "GMUD criada automaticamente para o desligamento de Ambiente.";
		  var justificativaMudanca = "GMUD criada devido ao 'Descomissionamento / Distrato' do cliente, " + clieAfetado.nome_cliente + ". O Contrato ID( " + idContrato + " ) foi ajustado para o Status '" + status + "'.";
		  let dadosGMUD = await getDadosApi( listaTarefasPadrao, clieAfetado, userLogado, usuarioEmail, dtSugeridaDeslgue, dtFimGMUD, descricaoMudanca, justificativaMudanca, idDescomissionamento, idGmudPadrao );

	      let urlAPIGMUD  = urlBase + 'salvarMudancaPadrao';
	      
	      const ResultGMUD = await fetch(urlAPIGMUD, {
	        	  method : 'post',
	        	  headers: { 'Content-Type': 'application/json; charset=utf-8' },
	        	  body   : JSON.stringify(dadosGMUD) 
	        	}).then(response => response.text())
	        	  .then(body => { return body; });

	      const ResultGMUDJson = JSON.parse(ResultGMUD);
	      
          if( !isNumber( ResultGMUDJson.id_mudanca ) ){
               let error = ResultGMUDJson.error + ' - Message: ' + ResultGMUDJson.message + ' - API: ' + ResultGMUDJson.path;
             throw error;
          }    
		  
          let obsDesli = obsDesligamento + ' - GMUD ID ( ' + ResultGMUDJson.id_mudanca + ' ), Aberta para solicitação de desligamento.';
          
 		  var urlAction = document.getElementById( "formDesligamento" ).action;
 		  var dados = "?acao=atualizaSolicitacaoDesligue&idDescomissionamento=" + idDescomissionamento + 
                                                       '&idContrato='           + idContrato           + 
                                                       '&idStatus='             + idStatus             + 
                                                       '&obsDesligamento='      + obsDesli             + 
                                                       '&dtSugeridaDeslgue='    + dtSugeridaDeslgue    +
                                                       '&idGmudPadrao='         + idGmudPadrao;

	 	  const updateDescomi = await fetch(urlAction + dados, { method: 'GET', headers: { 'Content-Type': 'application/json'} })
	 	                           .then(response => response.text())
                                   .then(body => { return body; })
                                   .catch(function(err){
			                                      var iconi           = "error";
			                                      var tituloPrincipal = "ERRO";
			                                      var textoPrincipal  = "Erro: " + xhr.responseText;
			                                      MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, "ModalSolicitarDesligamento" );
                                   });
	 	  
	 	  var json = JSON.parse(updateDescomi);

          if( json === 'SUCESSO' ){
             const msgOK = "Processo de Descomissionamento executado com sucesso! Mudança de ID (" + ResultGMUDJson.id_mudanca + ") para desligamento do ambiente!";
//        	 MensagemConfimacaoModal( "success", "Solicitação de Descomissionamento",  msgOK, "ModalSolicitarDesligamento" );
        	 
			   Swal.fire({
					   title: "Solicitação de Descomissionamento",
					   text : msgOK,
					   icon : "success",
					   target           : document.getElementById( "ModalSolicitarDesligamento" ),
					   showCancelButton: false,
					   confirmButtonColor: "#3085d6",
					   cancelButtonColor: "#d33",
					   confirmButtonText: "OK"
					 }).then((result) => {
					   if (result.isConfirmed) {
						  $('#ModalSolicitarDesligamento').modal('hide');
						  LimparModalSolicitarDesligamento(  );
						  montaTabelaDescomissionamento( );
					   }
					 });				 

			   
          }else{
             MensagemConfimacaoModal( "error", "Solicitação de Descomissionamento", json, "ModalSolicitarDesligamento" );
          }
                   
          hideLoading();   
		} catch (error) {
			 hideLoading();
			 MensagemConfimacaoModal( "error", "Solicitação de Descomissionamento",  error, "ModalSolicitarDesligamento" );
	     }
		
	}
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
  	function LimparModalSolicitarDesligamento(  ) {
   		$("#mdClienteSoli"               ).val( '' );
   		$("#idDescomiSoli"               ).val( '' );
   		$("#idContratoSoli"              ).val( '' );
   		$("#idClienteSoli"               ).val( '' );
   		$("#idStatusContratoDescomisSoli").get(0).selectedIndex = 0;
   		$("#obsDesligamentoSoli"         ).val( '' );
   		$("#dtDesligamentoSoli"          ).val( '' );
	}

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    function isNumber(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
   	function abrirModalSolicitarDesligamento( id_contrato, Cliente, idDescomi, id_cliente ) {
   		$("#mdClienteSoli"               ).val( Cliente     );
   		$("#idDescomiSoli"               ).val( idDescomi   );
   		$("#idContratoSoli"              ).val( id_contrato );
   		$("#idClienteSoli"               ).val( id_cliente  );
   		$("#idStatusContratoDescomisSoli").get(0).selectedIndex = 0;
   		$("#obsDesligamentoSoli"         ).val( '');
   		$("#dtDesligamentoSoli"          ).val( '');

   		$('#ModalSolicitarDesligamento').modal('show');
	}

   	async function processarSolicitaDesligamento() {

 	 	  var idDescomissionamento = document.getElementById( "idDescomiSoli"                ).value;
	 	  var idContrato           = document.getElementById( "idContratoSoli"               ).value;
	 	  var idStatus             = document.getElementById( "idStatusContratoDescomisSoli" ).value;
	 	  var status               = $('#idStatusContratoDescomisSoli').find ( ":selected").text( ) ;
	 	  var obsDesligamento      = document.getElementById( "obsDesligamentoSoli"          ).value;
	 	  var dtSugeridaDeslgue    = document.getElementById( "dtDesligamentoSoli"           ).value;
	 	  var idCliente            = document.getElementById( "idClienteSoli"                ).value;
	 	  var idGmudPadrao         = document.getElementById( "seletCategoriaPadrao"         ).value;
	 	  var dtFimGMUD            = await SomaNDiasData(dtSugeridaDeslgue, 5);
	 	  
	 	  var dtFimGMUDAjust    = dtFimGMUD.toISOString().slice(0, 19).replace("T", " ");
	 	  var dtSugeridaDeslgue = dtSugeridaDeslgue.replace("T", " ");

 	 	  solicitaDesligamento(idDescomissionamento, idContrato, idStatus, status, obsDesligamento, dtSugeridaDeslgue, dtFimGMUDAjust, idCliente, idGmudPadrao );       
		
	}
   	
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
	function montaTabelaDescomissionamento( ){
	        var urlAction = document.getElementById("formDesligamento").action;
	        
	    	$.ajax({     			
	    		method : "get",
	    		url  : urlAction,
	    		data : 'acao=montaDescomissionamentoDistrato',
	    		success: function(lista){
	    			
	    			var lista = JSON.parse(lista);

					let tbody = document.getElementById('TbodyDesligamento');
					tbody.innerText = '';
					for(let i = 0; i < lista.length; i++){
						// Cria as linhas
						let tr = tbody.insertRow();
						
						
			            // Crias as celulas
			            let td_bt_revecao            = tr.insertCell();
			            let td_solicitar_deslig      = tr.insertCell();
			            let td_id_contrato           = tr.insertCell();
			            let td_cliente               = tr.insertCell();
			            let td_alias                 = tr.insertCell();
			            let td_status_contrato       = tr.insertCell();
			            let td_dt_solicitacao        = tr.insertCell();
			            let td_dt_alteracao          = tr.insertCell();
			            let td_motivo_solicitacao    = tr.insertCell();
			            let td_revecao               = tr.insertCell();
			            let td_dt_solicit_deslig     = tr.insertCell();
			            let td_obs_solict_desligue   = tr.insertCell();			            
			            let td_chk_email_inviado     = tr.insertCell();
			            let td_ambiente_desligado    = tr.insertCell();
			            let td_dt_ambiente_desligado = tr.insertCell();
			            let td_user_desligamento     = tr.insertCell();
			            
			            // Inseri os valores do objeto nas celulas
			            td_id_contrato.innerText           = ( lista[i].id_descomissionamento              !== undefined ? lista[i].id_descomissionamento              : ' - ' );
			            td_cliente.innerText               = ( lista[i].cliente                            !== undefined ? lista[i].cliente                            : ' - ' );
			            td_alias.innerText                 = ( lista[i].alias                              !== undefined ? lista[i].alias                              : ' - ' );
			            td_status_contrato.innerText       = ( lista[i].status_contrato                    !== undefined ? lista[i].status_contrato                    : ' - ' );
			            td_dt_solicitacao.innerText        = ( lista[i].dt_criacao                         !== undefined ? lista[i].dt_criacao                         : ' - ' );
			            td_dt_alteracao.innerText          = ( lista[i].dt_alteracao                       !== undefined ? lista[i].dt_alteracao                       : ' - ' );
			            td_motivo_solicitacao.innerText    = ( lista[i].motivo_descomissionamento          !== undefined ? lista[i].motivo_descomissionamento          : ' - ' );
			            td_revecao.innerText               = ( lista[i].motivo_reversao_descomissionamento !== undefined ? lista[i].motivo_reversao_descomissionamento : ' - ' );
			            td_ambiente_desligado.innerText    = ( lista[i].desligamento_ambiente              === true      ? 'Sim'                                       : 'Não' );
			            td_dt_solicit_deslig.innerText     = ( lista[i].dt_solicitacao_desligamento        !== undefined ? lista[i].dt_solicitacao_desligamento        : ' - ' );
			            td_chk_email_inviado.innerText     = ( lista[i].dt_solicitacao_desligamento        === undefined ? 'Não'                                       : 'Sim' );
			            td_dt_ambiente_desligado.innerText = ( lista[i].dt_desligamento_ambiente           !== undefined ? lista[i].dt_desligamento_ambiente           : ' - ' );
			            td_user_desligamento.innerText     = ( lista[i].user_desligamento                  !== undefined ? lista[i].user_desligamento                  : ' - ' );
			            td_obs_solict_desligue.innerText   = ( lista[i].obsSolictcaoDesligue               !== undefined ? lista[i].obsSolictcaoDesligue               : ' - ' );

			            // Botao Atualizar
			            let imgliberar = document.createElement('img');
			            
			            let funcLib = "abrirModalSolicitarDesligamento( " + lista[i].id_contrato + ", '" + lista[i].cliente + "', " + lista[i].id_descomissionamento + ", " + lista[i].id_cliente +")";
			            td_solicitar_deslig.appendChild(imgliberar);
			            
			            // Botao Editar abrirModalReversao( idContrato, Cliente )
				        let imgEdit = document.createElement('img');				        
				        let funcEdit = "abrirModalReversao( " + lista[i].id_contrato + ", '" + lista[i].cliente + "', " + lista[i].id_descomissionamento  + " )";
				        td_bt_revecao.appendChild(imgEdit);

			            if( lista[i].dt_solicitacao_desligamento === undefined ){
				            // Botao Atualizar
				            imgliberar.src = getContextPath() +'/imagens/Solicitar-Desliga-20.png';
	                        imgliberar.setAttribute('onclick', funcLib );
	                        imgliberar.setAttribute('data-toggle', 'tooltip' );
	                        imgliberar.setAttribute('data-placement', 'top' );
	                        imgliberar.setAttribute('title', 'Solicitar Desligue do Ambiente!' );                        
	                        
				            // Botao Editar abrirModalReversao( idContrato, Cliente )
				            imgEdit.src = getContextPath() +'/imagens/Reversao-20.png';
				            imgEdit.setAttribute('onclick', funcEdit);
				            imgEdit.setAttribute('data-toggle', 'tooltip' );
				            imgEdit.setAttribute('data-placement', 'top' );
				            imgEdit.setAttribute('title', 'Voltar Status para "Ativo"!' );
				            
				            
			            }else{
                            var msnCliente = "Já foi solicitado o desligamento deste ambiente! " + lista[i].obsSolictcaoDesligue.substr(lista[i].obsSolictcaoDesligue.indexOf("-") + 1, lista[i].obsSolictcaoDesligue.length);
                            imgliberar.setAttribute('onclick', "mensagemNaoResponsavel('" + msnCliente + "')" );
                            imgliberar.src = getContextPath() +'/imagens/Solicitar-Desliga-desabilidado-20.png';

			            	imgliberar.setAttribute('data-toggle', 'tooltip' );
			            	imgliberar.setAttribute('data-placement', 'top' );
			            	imgliberar.setAttribute('title', 'Já foi solicitado desligamento deste ambiente!' );                        

				            // Botao Editar abrirModalReversao( idContrato, Cliente )
                            var msnReversao = "Este cliente não poderá ser revertido, já foi solicitado o desligamento!";
                            imgEdit.setAttribute('onclick', "mensagemNaoResponsavel('" + msnReversao + "')" );
				            imgEdit.src = getContextPath() +'/imagens/ok-desabilidado-20.png';
				            imgEdit.setAttribute('data-toggle', 'tooltip' );
				            imgEdit.setAttribute('data-placement', 'top' );
				            imgEdit.setAttribute('title', 'Voltar Status para "Ativo"!' );
			            	
			            }
			            
			            // Botao Desligado
/*			            
			            let imgDelete = document.createElement('img');
			            imgDelete.src = getContextPath() +'/imagens/Desliga-20.png';
			            let funcdelete = "deleteRascunho( " + lista[i].id_contrato + " )";
			            imgDelete.setAttribute('onclick', funcdelete );
			            imgDelete.setAttribute('data-toggle', 'tooltip' );
			            imgDelete.setAttribute('data-placement', 'top' );
			            imgDelete.setAttribute('title', 'Informar Desligue do Ambiente!' );
			            td_ambiente_desligado.appendChild(imgDelete);
			            td_ambiente_desligado.classList.add('center');
*/
						///////////////////////////////////////////////////////////////////////
						
						td_id_contrato.setAttribute          ('style', 'vertical-align: middle; text-align:center' );
						td_cliente.setAttribute              ('style', 'vertical-align: middle' );
						td_alias.setAttribute                ('style', 'vertical-align: middle' );
						td_status_contrato.setAttribute      ('style', 'vertical-align: middle' );
						td_dt_solicitacao.setAttribute       ('style', 'vertical-align: middle' );
						td_dt_alteracao.setAttribute         ('style', 'vertical-align: middle' );
						
						td_revecao.setAttribute              ('style', 'vertical-align: middle' );
						td_bt_revecao.setAttribute           ('style', 'vertical-align: middle; text-align:center' );
						td_solicitar_deslig.setAttribute     ('style', 'vertical-align: middle; text-align:center' );
						td_dt_solicit_deslig.setAttribute    ('style', 'vertical-align: middle' );
						td_chk_email_inviado.setAttribute    ('style', 'vertical-align: middle; text-align:center' );
						td_ambiente_desligado.setAttribute   ('style', 'vertical-align: middle' );
						td_dt_ambiente_desligado.setAttribute('style', 'vertical-align: middle' );
						td_user_desligamento.setAttribute    ('style', 'vertical-align: middle' );
						///////////////////////////////////////////////////////////////////////

                        
                        if( lista[i].motivo_descomissionamento          === undefined ) td_motivo_solicitacao.setAttribute   ('style', 'vertical-align: middle; text-align:center' ); else td_motivo_solicitacao.setAttribute   ('style', 'vertical-align: middle' ); 
                        if( lista[i].motivo_reversao_descomissionamento === undefined ) td_revecao.setAttribute              ('style', 'vertical-align: middle; text-align:center' ); else td_revecao.setAttribute              ('style', 'vertical-align: middle' );
                        if( lista[i].dt_solicitacao_desligamento        === undefined ) td_dt_solicit_deslig.setAttribute    ('style', 'vertical-align: middle; text-align:center' ); else td_dt_solicit_deslig.setAttribute    ('style', 'vertical-align: middle' );
                        if( lista[i].dt_desligamento_ambiente           === undefined ) td_dt_ambiente_desligado.setAttribute('style', 'vertical-align: middle; text-align:center' ); else td_dt_ambiente_desligado.setAttribute('style', 'vertical-align: middle' );
                        if( lista[i].user_desligamento                  === undefined ) td_user_desligamento.setAttribute    ('style', 'vertical-align: middle; text-align:center' ); else td_user_desligamento.setAttribute    ('style', 'vertical-align: middle' );
                        if( lista[i].obsSolictcaoDesligue               === undefined ) td_obs_solict_desligue.setAttribute  ('style', 'vertical-align: middle; text-align:center' ); else td_obs_solict_desligue.setAttribute  ('style', 'vertical-align: middle' );
                        
     				}
					
	    		}	
	        }).fail(function( xhr, status, errorThrown ){
	    		var iconi = "error";
	    		var tituloPrincipal = "Erro ao buscar Lista de Contratos";
	    		var textoPrincipal = xhr.responseText;
	    		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	    	}); 
		}

	    function mensagemNaoResponsavel( msnTxt ) {
		    MensagemConfimacao( "info", "Solicitação de Descomissionamento", msnTxt );
	    }
    
    
		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function getContextPath() {
	 	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	    }

	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	   	function abrirModalReversao( idContrato, Cliente, idDescomi ) {

	   		$("#mdIdContrato"  ).val( idContrato );
	   		$("#mdCliente"     ).val( Cliente    );
	   		$("#txDescReversao").val( ''         );
	   		$("#idDescomi"     ).val( idDescomi );
	   		
	   		$('#ModalReversaoDistrato').modal('show');
		}

	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function atualizaContratoReversao(  ) {
	    	idContrato           = document.getElementById("mdIdContrato"  ).value;
            idDescomissionamento = document.getElementById("idDescomi"     ).value; 
            txDescReversao       = document.getElementById("txDescReversao").value; 
            cliente              = document.getElementById("mdCliente"     ).value;	    	
	    	atualizaStatusContrato( idContrato, idDescomissionamento, txDescReversao, cliente )
	    }

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function atualizaStatusContrato( idContrato, idDescomissionamento, txDescReversao, cliente ) {

	        var urlAction = document.getElementById("formDesligamento").action;
	        
			  Swal.fire({
				  icon             : "warning" ,
				  title            : "Status Contrato",
				  text             : 'Deseja realmente alterar o Status para "Ativo"?',
				  target           : document.getElementById( "ModalReversaoDistrato" ),
				  showDenyButton   : true,
		//		  showCancelButton : true,
				  confirmButtonText: "Sim",
				  denyButtonText   : "Cancelar"
				}).then((result) => {
				  if (result.isConfirmed) {
				     //return true;
                     $.ajax({
                         method : "get",
                         url    : urlAction,
                         data   : "acao=atualizaStatusContrato&idContrato=" + idContrato + '&idDescomissionamento='+ idDescomissionamento + '&txDescReversao='+ txDescReversao,
                         success: function(lista){
                                       var json = JSON.parse(lista);
                        			   if( json === "SUCESSO" ){
                                           var iconi           = "success";
                         			       var tituloPrincipal = 'Atualuzação de Status Contrato';
                         			       var textoPrincipal  = 'Status do Cliente "' + cliente + '", Ajustado para "Ativo" com Sucesso!"';
                         				   Swal.fire({
                         						   title: tituloPrincipal,
                         						   text : textoPrincipal,
                         						   icon : iconi,
                         						   target           : document.getElementById( "ModalReversaoDistrato" ),
                         						   showCancelButton: false,
                         						   confirmButtonColor: "#3085d6",
                         						   cancelButtonColor: "#d33",
                         						   confirmButtonText: "OK"
                         						 }).then((result) => {
                         						   if (result.isConfirmed) {
                         							  $('#ModalReversaoDistrato').modal('hide');
                         							 montaTabelaDescomissionamento( );
                         						   }
                         						 });				 
                         			       
                        			   }else{
                                           var iconi           = "error";
                                           var tituloPrincipal = 'Atualuzação de Status Contrato';
                                           var textoPrincipal  = 'Erro ao atualizar o Status do cliente "' + cliente + '" , para "Ativo" - ERRO: ' + json;
                                           Swal.fire({
                     						   title: tituloPrincipal,
                     						   text : textoPrincipal,
                     						   icon : iconi,
                     						   target           : document.getElementById( "ModalReversaoDistrato" ),
                     						   showCancelButton: false,
                     						   confirmButtonColor: "#3085d6",
                     						   cancelButtonColor: "#d33",
                     						   confirmButtonText: "OK"
                     						 }).then((result) => {
                     						   if (result.isConfirmed) {
                     							  $('#ModalReversaoDistrato').modal('hide');
                     							  montaTabelaDescomissionamento( );
                     						   }
                     						 });				 

                        			   }
                                  }
                         }).fail(function( xhr, status, errorThrown ){
                                 //alert('Erro Cancela Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText);
                                   var iconi           = "error";
                                   var tituloPrincipal = "Erro Cancelar Contrato";
                                   var textoPrincipal  = 'Erro ao Cancela o Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText;
                                   MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
                                 }); 
                  } else if (result.isDenied) {
                             return false;
                  }
                });	  
	    }
		
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal ) {
	   		  Swal.fire({
	   			    icon  : iconi                             ,
	   			    title : tituloPrincipal                   ,
	   			    text  : textoPrincipal                    ,
	   			    target: document.getElementById(nomeModal),
	   			    }
	   			);	    		
	    }
	    
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal ) {
	    	// icon
//	    	"warning"
//	    	"error"
//	    	"success"
//	    	"info"

  			  Swal.fire({
  				    icon  : iconi                             ,
  				    title : tituloPrincipal                   ,
  				    text  : textoPrincipal                    ,
  				    }
  				);
	    }

	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function AlerataMensagensModal( tituloPrincipal, textoPrincipal,  nomeModal ) {
	        Swal.fire({
    			  title            : tituloPrincipal,
    			  text             : textoPrincipal,
    			  target           : document.getElementById( nomeModal ),
    			  showDenyButton   : true,
//	    			  showCancelButton : true,
    			  confirmButtonText: "Sim",
    			  denyButtonText   : "Cancelar"
    			}).then((result) => {
    			  if (result.isConfirmed) {
    			     return true;
    			  } else if (result.isDenied) {
    			    return false;
    			  }
    			});	  
	    }
	    
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function efetivaCancelamentoContratoAfins( idContrato ){
	     	 var urlAction  = document.getElementById("formContrato").action;
	     	 var idStatusContrato = document.getElementById("id_status_contrato").value;
	    	 $.ajax({
	     			method : "get",
	     			url : urlAction,
	     			data : "acao=efetivaCancelamentoContratoAfins" + '&idContrato=' + idContrato + '&idStatusContrato=' + idStatusContrato,
	     			success: function(lista){
	     				var json = JSON.parse(lista);
	     				var iconi = "success";
	     				let tituloPrincipal = "Cancelamento de Contrato";
	     				let textoPrincipal = json;
	     				if( json === 'STATUS4' ){
	     					tituloPrincipal = "Atualização de Contrato";
	     					textoPrincipal  = "Contrato atualizado com sucesso!";
	     				}
	     				
	     				var nomeModal = "";
	     				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
	      	}
	     	}).fail(function( xhr, status, errorThrown ){
	     			// alert('Erro pesqusa Contrato por ID: ' + xhr.responseText);
	    			var iconi           = "error";
	     			var tituloPrincipal = "ERRO";
	     			var textoPrincipal  = "Erro: Efetiva Cancelamento Contrato Afins: " + xhr.responseText;
	     			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  

	     	}); 

	     }
	    
    </script>
    
</body>

</html>
