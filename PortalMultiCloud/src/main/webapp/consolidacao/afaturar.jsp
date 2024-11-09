<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
  <html lang="en">
		
  <jsp:include page="/principal/head.jsp"></jsp:include>
		
  <style type="text/css">
		    
		#customFile .custom-file-control:lang(en)::after {
		  content: "Selecione um arquivo...";
		}
		
		#customFile .custom-file-control:lang(en)::before {
		  content: "Click aqui";
		}
		
		/*when a value is selected, this class removes the content */
		.custom-file-control.selected:lang(en)::after {
		  content: "" !importante;
		}
		
		.custom-file {
		  overflow: hidden;
		}
		.custom-file-control {
		  white-space: nowrap;
		}  
       
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

  <head>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

     <!-- Data Table CSS -->
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css">
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/3.1.2/css/buttons.dataTables.css">
     
  </head>
  
  
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
	                                    <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletAFaturar" method="post" id="formAFaturar">
											<div class="card-deck">
											  <div class="card">
											     <div class="card-body border-primary">
												     <h5 class="card-title">Arquivo A Faturar para processamento</h5>

												     <br>  
                                                     <input type="hidden" id="radioSelecionado" name="radioSelecionado" value="1" />
													 <div class="form-check form-check-inline">
												       <input class="form-check-input" checked="checked" type="radio" name="customRadioInline1" id="customRadioInline1" onclick="habilitaArquivo()" value="option1">
												       <label class="form-check-label" for="customRadioInline1">Processar pela View</label>
												     </div>
												
												     <div class="form-check form-check-inline">
												       <input class="form-check-input" type="radio" name="customRadioInline1" id="customRadioInline2" onclick="habilitaArquivo()" value="option2">
												       <label class="form-check-label" for="customRadioInline2">Processar Arquivo</label>
												     </div>

												     <div class="form-row">
												       <div class="form-group form-default form-static-label col-md-12 mb-12">
												         <br>
														 <div class="container">
														    <label class="custom-file" id="customFile">
														        <input type="file" disabled="disabled" class="custom-file-input" id="arqAFaturar" name="arqAFaturar" value="${nomeArq}"  aria-describedby="fileHelp" accept="text/plain">
														        <span class="custom-file-control form-control-file"></span>
														    </label>
														 </div>
												       </div>
												     </div>
												     
												     <br>
												     <div class="form-row">
												        <div class="form-group form-default form-static-label col-md-12 mb-12">
												            <button type="submit" class="btn btn-outline-primary btn-sw btn-block" id="btproc" name="btproc" onclick='informe();'>Processar Arquivo</button>
												        </div>
												     </div>
											       </div>
											  </div>
										      <!-- Card Dois, com o resumo do processamento -->
											  <div class="card">
											    <div class="card-body border-primary">
											      <h5 class="card-title">Resumo Processamento</h5><br>
											   
											      <strong >Total Linhas: </strong> <span id="totalLinhas">${totalLinhas}</span><br><br>
											      <strong >Total ${real} : </strong> <span id="totalValor">${vlrReal}</span><br>
											      <strong >Total ${dolar} : </strong> <span id="totalValor">${vlrdolar}</span><br>
											      <strong >Total ${euro} : </strong> <span id="totalValor">${vlreuro}</span><br><br>
											      <strong >Total Valor Faturado: </strong> <span id="totalValor">${vlrTotal}</span>
											    </div>
											  </div>
											</div>
											
											<br><br>
											<!--  
											<button type="button" class="btn btn-outline-primary waves-effect waves-dark" data-toggle="modal" data-target="#ModalTabelaDW">Informações Completa DW </button>
											-->
											<button type="button" class="btn btn-outline-primary waves-effect waves-dark" onclick=" montaTelaModalDW(  )">Informações Completa DW</button>
											
											
											<br><br><br>
											<div class="alert alert-primary alert-dismissible fade show" role="alert">
	                                           <strong >Status: </strong> <span id="msg">${msg}</span>
	                                           <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                                             <span aria-hidden="true">&times;</span>
											  </button>
											</div>

										</form>	
                                    </div>
                                    
                                    <!-- Page-body end -->
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
  <div id="box_dark" class="box_dark" > 
     <div class="spinner-border text-info box_modal" ></div>
  </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
     
    <!-- Data Table JS -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.1.2/js/dataTables.buttons.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.dataTables.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.1.2/js/buttons.print.min.js"></script>
    
   <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>

   <!-- Modal Add Produto 
   <div class="modal t-modal primary" id="ModalTabelaDW" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
-->

   <div class="modal" id="ModalTabelaDW"  role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-xl" role="document">


	    <div class="modal-content">
	    
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Tabela Resultado DW</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>

	      <div class="modal-body">
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">	
                          <table id="example" class="display nowrap" cellpadding="0" style="width:100%">
                             <thead>
                                <tr>
									<th>Empresa</th> 
									<th>DocumentoVenda</th> 
									<th>ItemDocumentoVenda</th> 
									<th>Referencia</th> 
									<th>Parceiro</th> 
									<th>NomeParceiro</th> 
									<th>OrganizacaoVenda</th> 
									<th>PEP</th> 
									<th>Material</th> 
									<th>SetorAtividade</th> 
									<th>DocumentoFaturamento</th> 
									<th>DataLancamento</th> 
									<th>CondicaoPagamento</th> 
									<th>DataVencimentoOriginal</th> 
									<th>DataVencimentoAtual</th> 
									<th>ValorBruto</th> 
									<th>ValorLiquido</th> 
									<th>EquipeVendas</th> 
									<th>Denominacao</th> 
									<th>NumeroDocumento</th> 
									<th>Atribuicao</th> 
									<th>GrupoAdminTesouraria</th> 
									<th>DataAtualizacao</th> 
									<th>DataCompensacao</th> 
									<th>IdProjeto</th> 
									<th>Centro</th> 
									<th>Id</th> 
									<th>NumeroPedido</th> 
									<th>DescricaoVendasMaterial</th> 
									<th>SuplementoFormaPagamento</th> 
									<th>NumContrato</th> 
									<th>StatusContrato</th> 
									<th>StatusLinhaContrato</th> 
									<th>TpDocVendas</th> 
									<th>CodigoMoeda</th> 
									<th>PreVendas</th> 
									<th>DescrPrevendas</th> 
									<th>DtDivisaoRemessa</th> 
									<th>NovoModelo</th> 
									<th>TaxaCambio</th> 
									<th>NomeSet</th> 
									<th>NomeSubSet</th> 
									<th>AliquotaISS</th> 
									<th>Categoria</th> 
									<th>Unidade</th> 
									<th>FAT_AFAT</th> 
									<th>AnoFaturamento</th> 
									<th>DiasAtraso</th> 
									<th>Atrasado</th> 
									<th>FaixaInadimplencia</th>            
								</tr>
        					 </thead>
					      </table>
						</div>
					</div>
				</div>
			</div>
	      </div>

	      <div class="modal-footer">
	      <!-- 
	         <button type="button" id="btFecharModal" class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" data-dismiss="modal">Fechar</button>
          -->	         
	         <button type="button" id="btFecharModal" class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" onclick="FecharModal(  )">Fechar</button>
	         
	      </div>
	      
	    </div>
	  </div>
 </div>

<script>
  $('.custom-file-input').on('change',function(){
     var fileName = document.getElementById("arqAFaturar").files[0].name;
     $(this).next('.form-control-file').addClass("selected").html(fileName);
  })
  
  function informe(){ 
	  this.showLoading();
	  var fileName = document.getElementById("arqAFaturar").files[0].name;
	  document.getElementById("msg").textContent = 'Processando o arquivo ==> ' + fileName;
  } 
  
  function habilitaArquivo() {
	  if (document.getElementById("customRadioInline1").checked) {
		  $('#arqAFaturar').attr('disabled', true);
		  document.getElementById("radioSelecionado").value = "1";
//		  $("#arqAFaturar").css({"background-color": "yellow"}); // Alterar cor de fundo
	  } else 
		  if (document.getElementById("customRadioInline2").checked) {
			$('#arqAFaturar').attr('disabled', false);
			document.getElementById("radioSelecionado").value = "2";
//			$("#arqAFaturar").css({"background-color": "yellow"}); // Alterar cor de fundo
	      }	
  }
  
  function FecharModal(  ){
	  $('#ModalTabelaDW').modal('hide');
  }
  
  $(document).on('hidden.bs.modal', function(event){
		if( $('.modal:visible').length ){
			$('body').addClass('modal-open');
		}
});

  async function montaTelaModalDW(  ){
	  try {
		    this.showLoading();
		    var urlAction = document.getElementById("formAFaturar").action;
			const listaDW = await fetch( urlAction + '?acao=montaTelaTAbelaDW', { 
	                                               method : 'GET', 
	                                               headers: { 'Content-Type': 'application/json; charset=utf-8' }
			                                }).then(response => response.text());
			
			if( listaDW !== null ){
//				console.log(JSON.parse(listaDW));  
				await montaTabela( JSON.parse(listaDW) );
				
				var staticBackdrop = document.getElementById('ModalTabelaDW');
				var myModal = new bootstrap.Modal(staticBackdrop);
				myModal.show();
				
				
		    }
			
			this.hideLoading();
		} catch (error) {
			hideLoading();
			alert( "Manutenção Cat. Padrão: " + error );
		}

	 }
  async function montaTabela( listaDW ){
		if (DataTable.isDataTable('#example')) {
			var table = new DataTable('#example');
			table.destroy();
		}
		
			new DataTable('#example', {
			    layout: {
			        topStart: {
			            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
			        }
			    }, 
			    scrollCollapse: true,
			    scrollY: '500px',
			    decimal: '.',
			    thousands: ',',
			 	language: {
			         info: 'Página _PAGE_ de _PAGES_',
			         infoEmpty: 'Não foi encontrado registros',
			         infoFiltered: '(filtered from _MAX_ total records)',
			         lengthMenu: 'Display _MENU_ registros por páginase',
			         zeroRecords: 'Não foi encontrado registros.',	       
			    },
			    data:listaDW,
			    columns: [
			         { data: 'Empresa' },
			         { data: 'DocumentoVenda' },
			         { data: 'ItemDocumentoVenda' },
			         { data: 'Referencia' },
			         { data: 'Parceiro' },
			         { data: 'NomeParceiro' },
			         { data: 'OrganizacaoVenda' },
			         { data: 'PEP' },
			         { data: 'Material' },
			         { data: 'SetorAtividade' },

			         { data: 'DocumentoFaturamento' },
			         { data: 'DataLancamento' },
			         { data: 'CondicaoPagamento' },
			         { data: 'DataVencimentoOriginal' },
			         { data: 'DataVencimentoAtual' },
			         { data: 'ValorBruto' },
			         { data: 'ValorLiquido' },
			         { data: 'EquipeVendas' },
			         { data: 'Denominacao' },
			         { data: 'NumeroDocumento' },

			         { data: 'Atribuicao' },
			         { data: 'GrupoAdminTesouraria' },
			         { data: 'DataAtualizacao' },
			         { data: 'DataCompensacao' },
			         { data: 'IdProjeto' },
			         { data: 'Centro' },
			         { data: 'Id' },
			         { data: 'NumeroPedido' },
			         { data: 'DescricaoVendasMaterial' },
			         { data: 'SuplementoFormaPagamento' },

			         { data: 'NumContrato' },
			         { data: 'StatusContrato' },
			         { data: 'StatusLinhaContrato' },
			         { data: 'TpDocVendas' },
			         { data: 'CodigoMoeda' },
			         { data: 'PreVendas' },
			         { data: 'DescrPrevendas' },
			         { data: 'DtDivisaoRemessa' },
			         { data: 'NovoModelo' },
			         { data: 'TaxaCambio' },

			         { data: 'NomeSet' },
			         { data: 'NomeSubSet' },
			         { data: 'AliquotaISS' },
			         { data: 'Categoria' },
			         { data: 'Unidade' },
			         { data: 'FAT_AFAT' },
			         { data: 'AnoFaturamento' },
			         { data: 'DiasAtraso' },
			         { data: 'Atrasado' },
			         { data: 'FaixaInadimplencia' }
			    ],
			    columnDefs: [{
			               "defaultContent": "-",
			               "targets": "_all"
			     }]
			    
			});  
        
        
	//		console.log("Fim Processamento");  
	  
  }
  
  function showLoading(){
	$("#box_dark").css('display','flex');
  }

  function hideLoading(){
	$("#box_dark").css('display','none');
  }

</script>
    
</body>

</html>
