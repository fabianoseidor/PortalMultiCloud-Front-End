<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
        
    
<!DOCTYPE html>
<html lang="en">

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
                                        <h4>Relatórios de Clientes por Contrato</h4>
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card border border-primary ">
													<div class="card-block">
													<!--  
													    <div class="py-md-3 font-weight-bold font-italic"  > <h5 style="color: #0000CD;" >Parâmetros de pesquisa</h5> </div>
                                                    -->
                                            			<form class="form-material" action="<%= request.getContextPath() %>/ServletsRelCliente" method="post" id="formRelClieContrato" >
                                                           <blockquote class="blockquote text-center font-weight-bold font-italic">
                                                              <p class="h4">Parâmetros de pesquisa</p>
                                                           </blockquote>
														   <div class="card-group">
															  <div class="card" style="max-width: 20rem;">
															    <div class="card-body">
															      <h5 class="card-title">Contrato Ativo/Desativado</h5>
															      <div class="card-body mx-5">
																	<div class="form-check">
																	  <input class="form-check-input" type="radio" name="contratoRadios" id="ativoRadios" value="ativo" checked>
																	  <label class="form-check-label" for="ativoRadios"> Ativo </label>
																	</div>
																	<div class="form-check">
																	  <input class="form-check-input" type="radio" name="contratoRadios" id="desativoRadios" value="desativo">
																	  <label class="form-check-label" for="desativoRadios"> Desativado </label>
																	</div>
															      </div>
															    </div>
															  </div>
															  <div class="card">
															    <div class="card-body">
															      <h5 class="card-title">Pesquisa pelo PEP</h5>
															      <br><br>
															      <div class="form-row">
																     <div class="input-group form-default form-static-label col-md-12 mb-6">
																        <input type="text" name="pepContrato" id="pepContrato" style="margin-right: 15px;" class="form-control font-weight-bold font-italic" placeholder="PEP . . ." aria-label="Clinte" aria-describedby="button-addon2" value="BR-MC-ATLAS_PRIV-HOST">
																        <div class="input-group-append">
																		   <!--<button type="button"  class="btn btn-outline-info" id="btPesquisa" onclick="teste( 4 );">Pesquisar Contrato</button>-->	
																		   <button type="button"  class="btn btn-outline-info" id="btPesquisa" onclick="buscarContratoPEP(  );">Pesquisar Contrato</button>	
																		   
																		   														        
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
                                        </div>
                                        
                                        <div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card border border-dark" id="cardResultado">

											    </div>
										    </div>
									    </div>
									    
									    
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
    
    <jsp:include page="javascriptfile.jsp"></jsp:include>
    
    <script type="text/javascript">
        function MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal ) {
    	// icon
        //   "warning"
		//   "error"
		//   "success"
		//   "info"

 			  Swal.fire({
 				    icon  : iconi                             ,
 				    title : tituloPrincipal                   ,
 				    text  : textoPrincipal                    ,
 				    // footer: 'Trybe'
 				  
 				    }
 				);
    			
    	}

	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function buscarContratoPEP(  ){
	    	
	     	var urlAction      = document.getElementById("formRelClieContrato").action;
	     	var pep            = document.getElementById("pepContrato"        ).value;
	     	var statusContrato = 0;
	     	
	     	if (document.getElementById("ativoRadios").checked) statusContrato = 1;
	        else if (document.getElementById("desativoRadios").checked) statusContrato = 2;
	     	
	     	$("#cardResultado").html("").show();

	     	if( pep != null && pep != '' && pep.trim() != '' ){
    			 $.ajax({
    		 			method : "get",
    		 			url : urlAction,
    		 			data : "acao=buscarContratoPEP&pep=" + pep + '&statusContrato=' + statusContrato,
    		 			success: function(lista){
    		 				    
    		                    if( lista === 'NAOEXISTE'){
    		       				   var iconi           = "warning";
    		       	 			   var tituloPrincipal = "Relatório";
    		       	 			   var textStatus      = statusContrato === 1 ? " Ativo ": " Desativo ";
    		       	 			   var textoPrincipal  = "Não existe um Contrato " + textStatus + ", para o PEP pesquisado: " + pep;
    		       	 		       MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );	
    		       				   
    		                     }else{
    		     	  				var json = JSON.parse(lista);
    		     	  				var html = '';
	     	  				    	
    		     	  				
    		     	  				for(var p = 0; p < json.length; p++){
    		     	  					html += montaCardBlock( p, json[p] );
    		     	  				}
		     	  				    $("#cardResultado").html(html).show();
     	  						    
    		                     }
    		 			}
    		 	 }).fail(function( xhr, status, errorThrown ){
    		 			// alert('Erro pesqusa Contrato por ID: ' + xhr.responseText);
    	 				var iconi           = "error";
    	 				var tituloPrincipal = "Erro: Pesquisa Contrado pelo ID.";
    	 				var textoPrincipal  = xhr.responseText;
    	 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  	 		 			
    		 	 }); 

	       
	       }
	     }
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/	    
	    const input = document.getElementById('formRelClieContrato');
	
	    input.addEventListener('keypress', function(event) {
	      if (event.key === 'Enter') {
	        event.preventDefault();
	        buscarContratoPEP(  );
	      }
	    });
	
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function mostraMensagemTela( titulo, texto ) {
	
	        swal({
	            title: titulo,
	            text: texto,
	//            timer: 2000,
	            button: true
	          }).then(
	            function() {},
	            // handling the promise rejection
	            function(dismiss) {
	              if (dismiss === 'timer') {
	                console.log('I was closed by the timer')
	              }
	            }
	          )	
	    	
	    }
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function montaListaAditivoRecurso( json ) {
	    	   
 				var html              = '';
  				var idAditivadoAux    = 0;

//				$('#tabelaResutadoAditivoRecurso > tbody > tr').remove();
  				for(var p = 0; p < json.length; p++){

	  			    if( idAditivadoAux !== json[p].id_aditivado ){
  						if( idAditivadoAux !== 0 ){
  				  			html +='</tbody>'
  		  				      +  '</table>'
  		  				      + ' </td>'
  		  				      + '</tr>';	
  						}
  						html += '<tr> '
	  						  +    '<td> <button onClick="esconderLinha( \'linhaAEsconder' + p + '\')"> + </button> </td>'
	  						  +    '<td>' + json[p].id_aditivado + '</td>'
	  						  +    '<td>' + json[p].dt_criacao   + '</td>'
	  						  +    '<td>' + json[p].valor_total  + '</td>'
  						      + '</tr>'
                              + '<tr id="linhaAEsconder' + p + '" style="display: none">'
							      +    '<td></td>'
							      +    '<td colspan="6">'
							      +       '<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="sub-tabelaResutadoAditivoRecurso">'
							      +          '<thead>'
							      +              '<tr>'
							      +                 '<th scope="col">ID Recurso   </th>'
							      +                 '<th scope="col">Tipo Aditivo </th>'
							      +                 '<th scope="col">Status       </th>'
							      +                 '<th scope="col">Tipo Serviço </th>'
							      +                 '<th scope="col">Ambiente     </th>'
							      +                 '<th scope="col">SO           </th>'
							      +                 '<th scope="col">Tipo Disco   </th>'
							      +                 '<th scope="col">Hostname     </th>'
							      +                 '<th scope="col">Família      </th>'
							      +                 '<th scope="col">Valor Custo  </th>'	  							      
							      +              '</tr>'
							      +          '</thead>'
							      +          '<tbody>'
			  				  +             '<tr> ' 
	  					      +               '<td>' + json[p].id_recurso               + '</td>'
	  					      +               '<td>' + json[p].desc_tipo_ditivo         + '</td>'
	  					      +               '<td>' + json[p].desc_status_recurso      + '</td>'
	  					      +               '<td>' + json[p].desc_tipo_servico        + '</td>'		  					      
	  					      +               '<td>' + json[p].desc_ambiente            + '</td>'
	  					      +               '<td>' + json[p].desc_sistema_operacional + '</td>'
	  					      +               '<td>' + json[p].desc_tipo_disco          + '</td>'
	  					      +               '<td>' + json[p].host_name_modal_recurso  + '</td>'		  					      
	  					      +               '<td>' + json[p].desc_familia             + '</td>'		  					      
	  					      +               '<td>' + json[p].custo_total              + '</td>'		  					      
				              +             '</tr>';

  						idAditivadoAux = json[p].id_aditivado;
  					}else{
	  					 html +=             '<tr> ' 
	  					      +              '<td>' + json[p].id_recurso               + '</td>'
	  					      +              '<td>' + json[p].desc_tipo_ditivo         + '</td>'
	  					      +              '<td>' + json[p].desc_status_recurso      + '</td>'
	  					      +              '<td>' + json[p].desc_tipo_servico        + '</td>'		  					      
	  					      +              '<td>' + json[p].desc_ambiente            + '</td>'
	  					      +              '<td>' + json[p].desc_sistema_operacional + '</td>'
	  					      +              '<td>' + json[p].desc_tipo_disco          + '</td>'
	  					      +              '<td>' + json[p].host_name_modal_recurso  + '</td>'		  					      
	  					      +              '<td>' + json[p].desc_familia             + '</td>'		  					      
	  					      +              '<td>' + json[p].custo_total              + '</td>'		  					      
				              +            '</tr>';
  					}
 
  				}
  				
	  			html +='</tbody>'
  				      +  '</table>'
  				      + ' </td>'
  				      + '</tr>';	      

//			    $("#tabelaResutadoAditivoRecurso tbody").html(html).show();  
			    
			  return html;

		}
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
		function esconderLinha(idDaLinha) {
		  // procura o elemento com o ID passado para a função e coloca o estado para o contrario do estado actual
		  $("#" + idDaLinha).toggle();
		}
		 /******************************************************************/
		 /*                                                                */
		 /*                                                                */
		 /******************************************************************/
	    function montaCardBlock( idcardBlock, vetorContrato ) {
			var html = '<div class="card-block" id="cardBlock' + idcardBlock +'">'
					+ '		<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoContrato' + idcardBlock +'">           '
					+ '		  <thead>                                                                                                                                           '
					+ '		    <tr>                                                                                                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">ID Contrato     </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Nuvem           </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Site            </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Fase Contrato   </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Status Contrato </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Cliente         </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Serviço         </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">pep             </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Moeda           </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Valor Total     </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Valor Convertido</font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Custo Total     </font></th>                                                            '
					+ '		      <th bgcolor="#428bca" scope="col"><font color="white">Cotação Moeda   </font></th>                                                            '
							      
					+ '		    </tr>                                                                                                                                           '
					+ '		  </thead>                                                                                                                                          '
					+ '		  <tbody>                                                                                                                                           ';
			   html += montaTabelaContrato( vetorContrato );
			   html += '		  </tbody>                                                                                                                                      '
					 + '	</table>                                                                                                                                            '
	
					 + '		<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutadoAditivoRecurso' + idcardBlock +'" >'
					 + '		  <thead>                                                                                                                                       '
					 + '		    <tr>                                                                                                                                        '
					 + '		      <th> </th>                                                                                                                                '
					 + '		      <th scope="col">ID Aditivo   </th>                                                                                                        '
					 + '		      <th scope="col">Data Cad.    </th>                                                                                                        '
					 + '		      <th scope="col">Valor Aditivo</th>                                                                                                        '
					 + '		    </tr>                                                                                                                                       '
					 + '		  </thead>                                                                                                                                      '
					 + '		  <tbody>                                                                                                                                       ';
			
               html += montaListaAditivoRecurso( vetorContrato.listaAitivoRecursos  );
               html += montaListaAditivoProduto( vetorContrato.listaAditivoProdutos );
					 
               html += '		  </tbody>                                                                                                                                      '
					+ '		</table>                                                                                                                                            '
					+ '</div>                                                                                                                                                   ';
	    	
			return html;
	    
	    }
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function montaTabelaContrato( vetorContrato ) {
		   
           var custoTotal      = '0.0';
           var valorConvertido = '0.0';
           var cotacaoMoeda    = '0.0';
           var html            = '';
           
           if( vetorContrato.custo_total      !== undefined ) custoTotal      = vetorContrato.tag_vcenter;      else custoTotal      = '0.0';
           if( vetorContrato.valor_convertido !== undefined ) valorConvertido = vetorContrato.valor_convertido; else valorConvertido = '0.0';
           if( vetorContrato.cotacao_moeda    !== undefined ) cotacaoMoeda    = vetorContrato.cotacao_moeda;    else cotacaoMoeda    = '0.0';

           html += '<tr> ' 
                + '<td>' + vetorContrato.id_contrato      + '</td>'
                + '<td>' + vetorContrato.mome_parceiro    + '</td>'
                + '<td>' + vetorContrato.nome_site        + '</td>'
                + '<td>' + vetorContrato.fase_contrato    + '</td>'
                + '<td>' + vetorContrato.status_contrato  + '</td>'
                + '<td>' + vetorContrato.nome_cliente     + '</td>'
                + '<td>' + vetorContrato.desc_servico     + '</td>'
                + '<td>' + vetorContrato.pep              + '</td>'
                + '<td>' + vetorContrato.moeda            + '</td>'
                + '<td>' + vetorContrato.valor_total      + '</td>'
                + '<td>' + valorConvertido          + '</td>'
                + '<td>' + custoTotal               + '</td>'
                + '<td>' + cotacaoMoeda             + '</td>'
                + '</tr>';
           
           return html;
		}
	    /******************************************************************/
	    /*                                                                */
	    /*                                                                */
	    /******************************************************************/
	    function montaListaAditivoProduto( aditivoProdutos ) {
		  var html              = '';
  		  var idAditivadoAux    = 0;
	    	
	      for (var p = 0; p < aditivoProdutos.length; p++) {
  					if( idAditivadoAux != aditivoProdutos[p].id_aditivado ){
 						if( idAditivadoAux != 0 ){
 				  			html +="</tbody>"
 	  		  				      +  "</table>"
 	  		  				      + " </td>"
 	  		  				      + "</tr>";	
 	  						}
 	  						html += "<tr> "
 		  						  +    "<td> <button onClick=\"esconderLinha( 'linhaAEsconderTelaPA" + p + "')\"> + </button> </td>"
 		  						  +    "<td>" + aditivoProdutos[p].id_aditivado   + "</td>"
 		  						  +    "<td>" + aditivoProdutos[p].dt_criacao     + "</td>"
 		  						  +    "<td>" + aditivoProdutos[p].vlr_total_adit + "</td>"
 	  						      + "</tr>"
                                   + "<tr id=\"linhaAEsconderTelaPA" + p + "\" style=\"display: none\">"
   							      +    "<td></td>"
   							      +    "<td colspan=\"6\">"
   							      +       "<table class=\"table table-striped table-hover table-sm table-bordered table-responsive-sm\" id=\"sub-tabelaResutadoAditivoProduto\">"
   							      +          "<thead>"
   							      +              "<tr>"
   							      +                 "<th scope=\"col\">ID Aditivo   </th>"
   							      +                 "<th scope=\"col\">Tipo Aditivo </th>"
   							      +                 "<th scope=\"col\">Status       </th>"
   							      +                 "<th scope=\"col\">Produto      </th>"
   							      +                 "<th scope=\"col\">Quantidade   </th>"
   							      +                 "<th scope=\"col\">Valor Unit.  </th>"
   							      +              "</tr>"
   							      +          "</thead>"
   							      +          "<tbody>"
 				  				  +             "<tr> " 
 		  					      +               " <td>" + aditivoProdutos[p].id_aditivado                 + "</td>"
 		  					      +               " <td>" + aditivoProdutos[p].nome_tipo_aditivo_contratado + "</td>"
 		  					      +               " <td>" + aditivoProdutos[p].dsc_status_aditivo           + "</td>"
 		  					      +               " <td>" + aditivoProdutos[p].nome_prod_contratado         + "</td>"		  					      
 		  					      +               " <td>" + aditivoProdutos[p].qty_produto_contratado       + "</td>"
 		  					      +               " <td>" + aditivoProdutos[p].valor_unitario_contratado    + "</td>"
 					              +             "</tr>";
 	  						idAditivadoAux = aditivoProdutos[p].id_aditivado;
 	  					}else{
 		  					 html +=             "<tr> " 
    		  					      +               " <td>" + aditivoProdutos[p].id_aditivado                 + "</td>"
     		  					      +               " <td>" + aditivoProdutos[p].nome_tipo_aditivo_contratado + "</td>"
     		  					      +               " <td>" + aditivoProdutos[p].dsc_status_aditivo           + "</td>"
     		  					      +               " <td>" + aditivoProdutos[p].nome_prod_contratado         + "</td>"		  					      
     		  					      +               " <td>" + aditivoProdutos[p].qty_produto_contratado       + "</td>"
     		  					      +               " <td>" + aditivoProdutos[p].valor_unitario_contratado    + "</td>"
     					              +             "</tr>";
 	  					}
 		      }		      
 	  				
 		  	  html +="</tbody>"
 	  			   +  "</table>"
 	  			   + " </td>"
 	  			   + "</tr>";	      
          
	      return html;

		}

    </script>
</body>

</html>
