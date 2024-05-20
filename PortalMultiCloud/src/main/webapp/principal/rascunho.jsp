<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<jsp:include page="head.jsp"></jsp:include>

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
								                   <h4 class="card-header border-danger">Contratos em status de "Rascunho"</h4>
								                   <div class="card-body">
									                   <form class="form-material" action="<%= request.getContextPath() %>/ServletRascunho" method="post" id="formRascunho">
													        <h5 class="card-title">Lista de Contratos em Rascunho</h5><hr>
	                                                        <div style="height: 500px; overflow: scroll;">
																<table class="table table-borderless table-striped table-hover table-sm table-bordered table-responsive-lg" style="line-height:0px;">
																  <thead >
																    <tr >
																      <th class="font-weight-bold font-italic " style="color: #708090">ID do contrato          </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Status                  </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Motivo do Rascunho      </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Tempo em Rascunho (Dias)</th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Data da Assinatura      </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Cliente                 </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Tipo                    </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">vigência                </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Quantidade de Usuarios  </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Valor                   </th>
																      
																      <th class="font-weight-bold font-italic " style="color: #708090">Liberar                 </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Editar                  </th>
																      <th class="font-weight-bold font-italic " style="color: #708090">Deletar                 </th>

																    </tr>
																  </thead>
																  <tbody id="TbodyRascunho">
																  
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
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    
    <jsp:include page="javascriptfile.jsp"></jsp:include>
    
    <script type="text/javascript">
    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
    // window.onload = function () { 
	function montaTabelaRecurso( ){
	        var urlAction = document.getElementById("formRascunho").action;
	        
	    	$.ajax({     			
	    		method : "get",
	    		url  : urlAction,
	    		data : 'acao=montaListaRascunho',
	    		success: function(lista){
	    			
	    			var listRascunho = JSON.parse(lista);

					let tbody = document.getElementById('TbodyRascunho');
					tbody.innerText = '';
					for(let i = 0; i < listRascunho.length; i++){
						// Cria as linhas

						let tr = tbody.insertRow();
			            // Crias as celulas
			            let td_id_contrato        = tr.insertCell();
			            let td_status_contrato    = tr.insertCell();
			            
			            let td_motivo_rascunho    = tr.insertCell();
			            
			            let td_qty_tempo_rascunho = tr.insertCell();
			            let td_dt_update_status   = tr.insertCell();
			            let td_razao_social       = tr.insertCell();
			            let td_tipo_rascunho      = tr.insertCell();
			            let td_vigencia           = tr.insertCell();
			            let td_qty_usuario        = tr.insertCell();
			            let td_vl_total           = tr.insertCell();
			            
			            let td_liberar            = tr.insertCell();
			            let td_editar             = tr.insertCell();
			            let td_delete             = tr.insertCell();
			            // Inseri os valores do objeto nas celulas
			            td_id_contrato.innerText       = listRascunho[i].id_contrato;
			            td_status_contrato.innerText   = listRascunho[i].status_contrato;			            
			            td_motivo_rascunho.innerText   = listRascunho[i].motivo_rascunho;
			            td_qty_tempo_rascunho.innerText = listRascunho[i].qty_tempo_rascunho;
			            td_dt_update_status.innerText  = listRascunho[i].dt_update_status;
			            td_razao_social.innerText      = listRascunho[i].razao_social;
			            td_tipo_rascunho.innerText     = listRascunho[i].tipo_rascunho;
			            td_vigencia.innerText          = listRascunho[i].vigencia;
			            td_qty_usuario.innerText       = listRascunho[i].qty_usuario_contratada;
			            td_vl_total.innerText          = listRascunho[i].vl_total;
		
			            let imgliberar = document.createElement('img');
			            imgliberar.src = getContextPath() +'/imagens/atualizar-20.png';
			            let funcLib = "atualizaStatus( " + listRascunho[i].id_contrato   + ", " 
                                                         + listRascunho[i].id_aditivo    + ", '" 
                                                         + listRascunho[i].tipo_rascunho + "', '"
                                                         + listRascunho[i].razao_social  +"' )";
                        imgliberar.setAttribute('onclick', funcLib );
                        imgliberar.setAttribute('data-toggle', 'tooltip' );
                        imgliberar.setAttribute('data-placement', 'top' );
                        imgliberar.setAttribute('title', 'Atualizar Status para "Ativo"!' );                        
			            td_liberar.appendChild(imgliberar);
			            td_liberar.classList.add('center');
		
			            let imgEdit = document.createElement('img');
			            imgEdit.src = getContextPath() +'/imagens/edit-20.png';
			            let funcEdit = "goContrato( " + listRascunho[i].id_cliente + ")";
			            imgEdit.setAttribute('onclick', funcEdit);
			            imgEdit.setAttribute('data-toggle', 'tooltip' );
			            imgEdit.setAttribute('data-placement', 'top' );
			            imgEdit.setAttribute('title', 'Ir para o modulo "Edição!"' );
			            td_editar.appendChild(imgEdit);
			            td_editar.classList.add('center');
			            let imgDelete = document.createElement('img');
			            imgDelete.src = getContextPath() +'/imagens/delete-20.png';
			            let funcdelete = "deleteRascunho( " + listRascunho[i].id_contrato   + ", " 
                                                            + listRascunho[i].id_aditivo    + ", '" 
                                                            + listRascunho[i].razao_social  +"' )";
			            imgDelete.setAttribute('onclick', funcdelete );
			            imgDelete.setAttribute('data-toggle', 'tooltip' );
			            imgDelete.setAttribute('data-placement', 'top' );
			            imgDelete.setAttribute('title', 'Apagar Contrato!' );
			            td_delete.appendChild(imgDelete);
			            td_delete.classList.add('center');
			            
			            td_id_contrato.classList.add       ('center');
			            td_qty_tempo_rascunho.classList.add('center');
			            td_qty_usuario.classList.add       ('center');
					}
					
	    		}	
	        }).fail(function( xhr, status, errorThrown ){
	    		var iconi = "error";
	    		var tituloPrincipal = "Erro ao buscar Cliente";
	    		var textoPrincipal = xhr.responseText;
	    		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	    	}); 
		}
	    // Inicia a tabela ao carregar a pagina!
	    window.onload = function () { 
	         montaTabelaRecurso( );
	    }
	    
		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function deleteRascunho( idContrato, idAditivo, cliente  ) {
             // tipoRascunho ==> Contrato = 1, Aditivo = 2, Renovacao = 3
             // Se for um Aditivo / 
            if( idAditivo === 0  ){
            	deleteContrato( idContrato, cliente );
            }else{
            	deleteAditivo( idAditivo, cliente );
            }
	    }
		
		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function deleteAditivo( idAditivo, cliente  ){
	        var urlAction = document.getElementById("formRascunho").action;
	        
			  Swal.fire({
				  icon             : "warning" ,
				  title            : "Apagar Aditivo",
				  text             : 'Deseja realmente APARAGAR o Aditivo do Cliente "' + cliente + '"?',
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
                     data   : "acao=deleteAditivo&idAditivo=" + idAditivo,
                     success: function(lista){
                                   var json = JSON.parse(lista);
                    			   if( json === "SUCESSO" ){
                                       var iconi           = "success";
                     			       var tituloPrincipal = "Apagar Aditivo";
                     			       var textoPrincipal  = 'O Aditivo ' + idAditivo + ', do Cliente "' + cliente + '", foi apagado com sucesso!"';
                     			       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
                     			       montaTabelaRecurso( );
                    			   }else{
                                       var iconi           = "error";
                                       var tituloPrincipal = 'Status "Aditivo"';
                                       var textoPrincipal  = 'Erro ao apagar o Contrato ' + idAditivo + ', do cliente "' + cliente + '" - ERRO: ' + json;
                                       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
                                       montaTabelaRecurso( );
                    			   }
                              }
                     }).fail(function( xhr, status, errorThrown ){
                             //alert('Erro Cancela Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText);
                               var iconi           = "error";
                               var tituloPrincipal = 'Erro Apagar "Aditivo"';
                               var textoPrincipal  = 'Erro ao Apagar o Aditivo: ' + idAditivo + ' - ERRO: ' + xhr.responseText;
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
	    function deleteContrato( idContrato, cliente  ){
	        var urlAction = document.getElementById("formRascunho").action;
	        
			  Swal.fire({
				  icon             : "warning" ,
				  title            : "Apagar Contrato",
				  text             : 'Deseja realmente APARAGAR o Contrato do Cliente "' + cliente + '"?',
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
                     data   : "acao=deleteContrato&idContrato=" + idContrato,
                     success: function(lista){
                                   var json = JSON.parse(lista);
                    			   if( json === "SUCESSO" ){
                                       var iconi           = "success";
                     			       var tituloPrincipal = "Apagar Contrato";
                     			       var textoPrincipal  = 'O Contrato ' + idContrato + ', do Cliente "' + cliente + '", foi apagado com sucesso!"';
                     			       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
                     			       montaTabelaRecurso( );
                    			   }else{
                                       var iconi           = "error";
                                       var tituloPrincipal = 'Status "Contrato"';
                                       var textoPrincipal  = 'Erro ao apagar o Contrato ' + idContrato + ', do cliente "' + cliente + '" - ERRO: ' + json;
                                       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
                                       montaTabelaRecurso( );
                    			   }
                              }
                     }).fail(function( xhr, status, errorThrown ){
                             //alert('Erro Cancela Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText);
                               var iconi           = "error";
                               var tituloPrincipal = 'Erro Apagar "Contrato"';
                               var textoPrincipal  = 'Erro ao Apagar o Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText;
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
	    function getContextPath() {
	 	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	    }
	    
		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function atualizaStatus( idContrato, idAditivo ,tipoRascunho, cliente  ) {
             // tipoRascunho ==> Contrato = 1, Aditivo = 2, Renovacao = 3
             // Se for um Aditivo / 
             if( idAditivo === 0  ){
            	 atualizaStatusContrato( idContrato, idAditivo ,tipoRascunho, cliente );
             }else{
            	 atualizaStatusAditivo( idContrato, idAditivo ,tipoRascunho, cliente );
             }
	    }

		/******************************************************************/
		/*                                                                */
		/*                                                                */
		/******************************************************************/
	    function atualizaStatusAditivo( idContrato, idAditivo ,tipoRascunho, cliente ) {
	          var urlAction = document.getElementById("formRascunho").action;
	        
			  Swal.fire({
				  icon             : "warning" ,
				  title            : "Status Contrato",
				  text             : 'Deseja realmente alterar o Status para "Ativo"?',
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
                       data   : "acao=atualizaStatusAditivo&idContrato=" + idContrato + '&idAditivo='+ idAditivo + '&tipoRascunho='+ tipoRascunho,
                       success: function(lista){
                                     var json = JSON.parse(lista);
                      			   if( json === "SUCESSO" ){
                                         var iconi           = "success";
                       			       var tituloPrincipal = "Status Contrato";
                       			       var textoPrincipal  = 'Status do Aditivo para Cliente "' + cliente + '", Ajustado para "Ativo" com Sucesso!"';
                       			       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
                       			       montaTabelaRecurso( );
                      			   }else{
                                         var iconi           = "error";
                                         var tituloPrincipal = 'Status "Contrato"';
                                         var textoPrincipal  = 'Erro ao atualizar o Status do Aditivo para cliente "' + cliente + '" , para "Ativo" - ERRO: ' + json;
                                         MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
                                         montaTabelaRecurso( );
                      			   }
                                }
                       }).fail(function( xhr, status, errorThrown ){
                               //alert('Erro Cancela Contrato: ' + idContrato + ' - ERRO: ' + xhr.responseText);
                                 var iconi           = "error";
                                 var tituloPrincipal = 'Erro Status "Contrato"';
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
	    function atualizaStatusContrato( idContrato, idAditivo ,tipoRascunho, cliente ) {

	        var urlAction = document.getElementById("formRascunho").action;
	        
			  Swal.fire({
				  icon             : "warning" ,
				  title            : "Status Contrato",
				  text             : 'Deseja realmente alterar o Status para "Ativo"?',
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
                         data   : "acao=atualizaStatusContrato&idContrato=" + idContrato + '&idAditivo='+ idAditivo + '&tipoRascunho='+ tipoRascunho,
                         success: function(lista){
                                       var json = JSON.parse(lista);
                        			   if( json === "SUCESSO" ){
                                           var iconi           = "success";
                         			       var tituloPrincipal = 'Status "Aditivo"';
                         			       var textoPrincipal  = 'Status do Cliente "' + cliente + '", Ajustado para "Ativo" com Sucesso!"';
                         			       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );
                         			       montaTabelaRecurso( );
                        			   }else{
                                           var iconi           = "error";
                                           var tituloPrincipal = 'Erro Status "Aditivo"';
                                           var textoPrincipal  = 'Erro ao atualizar o Status do cliente "' + cliente + '" , para "Ativo" - ERRO: ' + json;
                                           MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
                                           montaTabelaRecurso( );
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
	    function goContrato( idCliente ) {
	   	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServletContratoController"; 
	   	 var parametros = "?acao=buscarContratoCliente&idContratoCliente=" + idCliente;
	   	 var url = urlAction + parametros;
	   	 window.location.href = url;
	   	 
	    }	    
	    
    </script>
    
</body>

</html>
