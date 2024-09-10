<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
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
    
  </style>

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
                                    <h5 class="card-title text-primary">Informações de Contrato x Cliente</h5> <br>
	                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletsClientesInfo" method="post" id="formClientesInfo">
											<div class="card-deck">
											  <div class="card">
											     <div class="card-body border-primary">
												     <div class="form-row">
														<div class="form-group form-default form-static-label col-md-4 mb-6" >
														    <label class="font-weight-bold font-italic" id="lbperiodoUtilizacaoMAR" >Data Início</label>
														    <input style="color: #000000" type="text" name="dataInicio" id="dataInicio" class="form-control" required="required" value="${dataInicioView}">								     
														</div>	
														
														<div class="form-group form-default form-static-label col-md-4 mb-6" >
														    <label class="font-weight-bold font-italic" id="lbperiodoUtilizacaoMAR" >Data Final</label>
														    <input style="color: #000000" type="text" name="dataFinal" id="dataFinal" class="form-control" required="required" value="${dataFinalView}">
														</div>
														
														<div class="form-group form-default form-static-label col-md-1 mb-6" style="display: flex;justify-content: center;align-items: center;">
															<div class="form-check">
													        <%   
													           String rdButon = (String) session.getAttribute("rdButon"); 
             												   if( rdButon != null ){
             													   if( rdButon.equalsIgnoreCase("Ativo") ){ %>
             											               <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivo" value="Ativo" checked>
             												<% 		   
             													   }else{ %>		 
             												           <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivo" value="Ativo">  
             												<% 		   
             													   }
             												   }else { %>
             												   <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivo" value="Ativo" checked>
             												<% 
             												   } %>
															  <!-- <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivo" value="Ativo" checked> -->
															  <label class="form-check-label" for="rdAtivo">Ativo</label>
														    </div>
                                                        </div>
                                                        <div class="form-group form-default form-static-label col-md-1 mb-6" style="display: flex;justify-content: center;align-items: center;">
															<div class="form-check">
															<% if( rdButon != null ){ 
																   if( rdButon.equalsIgnoreCase("Desativo") ){%>
															           <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdDesativo" value="Desativo" checked>
															<%     }else { %> 
															         <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdDesativo" value="Desativo" >
															<%     }
															   }else { %>
             												   <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdDesativo" value="Desativo" >
             												<% 
             												   } %>
															  <label class="form-check-label" for="rdDesativo">Desativo</label>
															</div>	
													    </div>
													    <div class="form-group form-default form-static-label col-md-1 mb-6" style="display: flex;justify-content: center;align-items: center;">													
															<div class="form-check">
															<% if( rdButon != null ){ 
																   if( rdButon.equalsIgnoreCase("AtivoDesativo") ){%>
															           <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivoDesativo" value="AtivoDesativo" checked>
															<%     }else { %> 
															           <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivoDesativo" value="AtivoDesativo">
															<%     }
															   }else { %>
             												   <input class="form-check-input" type="radio" name="inlineRadioOptions" id="rdAtivoDesativo" value="AtivoDesativo">
             												<% 
             												   } %>
															  <label class="form-check-label" for="rdAtivoDesativo">Ativo/Desativo</label>
															</div>														
														</div>
												     </div>
												     <div class="form-row">
												        <div class="form-group form-default form-static-label col-md-12 mb-12">
												            <button type="submit" class="btn btn-outline-primary btn-sw btn-block" >Processar </button>
												        </div>
												     </div>
											       </div>
											  </div>
											</div>
											
                                            <!-- Link para criacao do arquivo xls. -->
											<a  class="btn btn-outline-primary" role="button" href="<%= request.getContextPath() %>/dashboard/reportClienteInfo.jsp" class="waves-effect waves-dark">Gerar Aquivo Excel</a>
											<br><br>
											<div class="tab-content" id="myTabContent">
											
												<div class="tab-pane fade show active" id="recursoContrato" role="tabpanel" aria-labelledby="recurso-tab">
                                                  <div style="height: 500px; overflow: scroll;">
													<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
													
													  <thead>
													    <tr class="table-primary">
													      <th scope="col" style="vertical-align: middle;color: white">ID Contrato        </th>
													      <th scope="col" style="vertical-align: middle;color: white">ID Cliente         </th>
													      <th scope="col" style="vertical-align: middle;color: white">Razão Social       </th>
													      <th scope="col" style="vertical-align: middle;color: white">Nome Fantasia      </th>
													      <th scope="col" style="vertical-align: middle;color: white">Alias              </th>
													      <th scope="col" style="vertical-align: middle;color: white">PEP                </th>
													      <th scope="col" style="vertical-align: middle;color: white">CNPJ               </th>
													      <th scope="col" style="vertical-align: middle;color: white">Data Criação       </th>
													      <th scope="col" style="vertical-align: middle;color: white">Login Cadastro     </th>
													      <th scope="col" style="vertical-align: middle;color: white">HUB SPOT           </th>
													      <th scope="col" style="vertical-align: middle;color: white">Total Aditivos     </th>
													      <th scope="col" style="vertical-align: middle;color: white">Total Instâncias   </th>
													      <th scope="col" style="vertical-align: middle;color: white">Histórico Contrato </th>
													      <th scope="col" style="vertical-align: middle;color: white">Contrato Antigo    </th>
													      <th scope="col" style="vertical-align: middle;color: white">Status             </th>
													      <th scope="col" style="vertical-align: middle;color: white">Qty Usuário        </th>
													      <th scope="col" style="vertical-align: middle;color: white">Nuvem              </th>
													      <th scope="col" style="vertical-align: middle;color: white">Site               </th>
													      <th scope="col" style="vertical-align: middle;color: white">Serviço Contratado </th>
													      <th scope="col" style="vertical-align: middle;color: white">Moeda              </th>
													      <th scope="col" style="vertical-align: middle;color: white">Fase Contrato      </th>
													      <th scope="col" style="vertical-align: middle;color: white">Ciclo Update       </th>
													      <th scope="col" style="vertical-align: middle;color: white">Início Vigência    </th>
													      <th scope="col" style="vertical-align: middle;color: white">Final Vigência     </th>
													      <th scope="col" style="vertical-align: middle;color: white">Data Desativação   </th>
													      <th scope="col" style="vertical-align: middle;color: white">Dias Vigência      </th>
													      <th scope="col" style="vertical-align: middle;color: white">Meses Vigência     </th>
													      <th scope="col" style="vertical-align: middle;color: white">Vigência           </th>
													    </tr>
													  </thead>
													  <tbody>
 					                                     <c:forEach items="${listCliInfo}" var="lrc">
		                                                   <tr>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.id_contrato}"           > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.id_cliente}"            > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.razao_social}"                              > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.nome_fantasia}"                             > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.alias}"                                     > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.pep}"                                       > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.cnpj}"                                      > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.dt_criacao}"                                > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.login_cadastro}"        > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.id_hub_spot}"                               > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.tota_aditivos}"         > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.total_instancias}"      > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.historico_contrato}"                        > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.contrato_antigo}"       > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.status}"                                    > </c:out> </td>	                                                          
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.qty_usuario_contratada}"> </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.nuvem}"                                     > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.site}"                                      > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.servico_contratado}"                        > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.moeda}"                                     > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.fase_contrato}"                             > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.ciclo_update}"          > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.dt_inicio_vigencia}"                        > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.dt_final_vigencia}"                         > </c:out> </td>
	                                                          <td style="vertical-align: middle"> <c:out value="${lrc.dt_desativacao}"                            > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.qty_dias_vigencia}"     > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.qty_meses_vigencia}"    > </c:out> </td>
	                                                          <td style="text-align: center; vertical-align: middle"> <c:out value="${lrc.vigencia}"              > </c:out> </td>
		                                                   </tr>
		                                                 </c:forEach>
												      </tbody>
											        </table>                                                            
                                                  </div>
                                                </div>

											</div>											
											
										</form>	
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
    
    <jsp:include page="/principal/javascriptfile.jsp"></jsp:include>
    
    <script type="text/javascript">
	    //////////////////////////////////////////////////////////////////////////////////////////////////	    
/*	    
	    var dataInicio = $("#dataInicio").val();
	    
	    if (dataInicio != null && dataInicio != '') {
	   		var dataInicio = new Date(dataInicio);
	   		$("#dataInicio").val(dateFormat.toLocaleDateString('pt-BR',{timeZone: 'UTC'}));
	    }
*/	
	    $( function() {
	   	  
	   	  $("#dataInicio").datepicker({
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
    
	    //////////////////////////////////////////////////////////////////////////////////////////////////	 
/*	    
	    var dtExecucao = $("#dataFinal").val();
	    
	    if (dtExecucao != null && dtExecucao != '') {
	   		var dataExecucao = new Date(dtExecucao);
	   		$("#dataFinal").val(dateFormat.toLocaleDateString('pt-BR',{timeZone: 'UTC'}));
	    }
*/	
	    $( function() {
	   	  
	   	  $("#dataFinal").datepicker({
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
   
    </script>

</body>

</html>
