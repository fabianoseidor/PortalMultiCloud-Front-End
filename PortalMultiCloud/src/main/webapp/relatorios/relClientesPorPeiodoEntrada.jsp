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
                                    <h5 class="card-title text-primary">Relatório de Clientes Entrantes</h5> <br>
	                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletsClientesPorPeiodoEntrada" method="get" id="formClientesPorPeiodoEntrada">
											<div class="card-deck">
											  <div class="card">
											     <div class="card-body border-primary">
												     
												     <input type="hidden" id="acaoRelatorioImprimir" name="acao" value="ImprimirTela">
												     
												     <div class="form-row">
                                                            <div class="form-group form-default form-static-label col-md-3 mb-6">
														        <span class="font-weight-bold font-italic" style="color: #708090">Data Início </span>
														        <input style="color: #000000" type="date" name="dt_inicio" id="dt_inicio" maxlength="10" class="form-control" required="required" placeholder="Informe o Ano" onkeypress="return somenteNumeros(event)" value="${dtInicio}"> 
														    </div>
														    
                                                            <div class="form-group form-default form-static-label col-md-3 mb-6">
														        <span class="font-weight-bold font-italic" style="color: #708090">Data Fim</span>
														        <input style="color: #000000" type="date" name="dt_final" id="dt_final" maxlength="10" class="form-control" required="required" placeholder="Informe o Mês" onkeypress="return somenteNumeros(event);" value="${dtFinal}"> 
														    </div>
												     </div>
												     <div class="form-row">
												        <div class="form-group form-default form-static-label col-md-12 mb-12">
												            <button type="button" onclick="ImprimirDadosTela();" class="btn btn-outline-primary btn-sw btn-block" >Processar </button>
												        </div>
												     </div>
											       </div>
											  </div>
										     
											</div>
											
  
											<button type="button" onclick="ImprimirDadosPDF();" class="btn btn-outline-primary waves-effect waves-dark" >Imprimir PDF </button>
											
											
											<br><br>
											<div class="tab-content" id="myTabContent">
											
												<div class="tab-pane fade show active" id="recursoContrato" role="tabpanel" aria-labelledby="recurso-tab">
                                                  <div style="height: 600px; overflow: scroll;">
													<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
													  <thead>
													    <tr>
													      <th scope="col">Tipo Contrato      </th>
													      <th scope="col">PEP                </th>
													      <th scope="col">Razão Social       </th>
													      <th scope="col">Login Cadastro     </th>
													      <th scope="col">Suporte B1         </th>
													      <th scope="col">Comercial          </th>
													      <th scope="col">Dt Criacao Contrato</th>
													      <th scope="col">Moeda              </th>
													      <th scope="col">Vlr Total          </th>
													      <th scope="col">Vlr Convertido     </th>
													      <th scope="col">Custo Total        </th>
													      <th scope="col">Cotacao Moeda      </th>
													      <th scope="col">Dt Criacao Vigência</th>
													      <th scope="col">Dt Início Vigência </th>
													      <th scope="col">Dt Final Vigência  </th>
													      <th scope="col">Qty Dias vigência  </th>
													    </tr>
													  </thead>
													  <tbody>
 					                                     <c:forEach items="${listCliPeiodoEntrada}" var="lrc">
		                                                   <tr>
	                                                          <td> <c:out value="${lrc.tipo_contrato}">      </c:out> </td>
	                                                          <td> <c:out value="${lrc.pep}">                </c:out> </td>
	                                                          <td> <c:out value="${lrc.razao_social}">       </c:out> </td>
	                                                          <td> <c:out value="${lrc.login_cadastro}">     </c:out> </td>
	                                                          <td> <c:out value="${lrc.suporte_b1}">         </c:out> </td>
	                                                          <td> <c:out value="${lrc.comercial}">          </c:out> </td>
	                                                          <td> <c:out value="${lrc.dt_criacao_contrato}"></c:out> </td>
	                                                          <td> <c:out value="${lrc.moeda}">              </c:out> </td>
	                                                          <td> <c:out value="${lrc.vlr_total}">          </c:out> </td>
	                                                          <td> <c:out value="${lrc.valor_convertido}">   </c:out> </td>
	                                                          <td> <c:out value="${lrc.custo_total}">        </c:out> </td>
	                                                          <td> <c:out value="${lrc.cotacao_moeda}">      </c:out> </td>
	                                                          <td> <c:out value="${lrc.dt_criacao_vigencia}"></c:out> </td>
	                                                          <td> <c:out value="${lrc.dt_inicio_vigencia}"> </c:out> </td>
	                                                          <td> <c:out value="${lrc.dt_final_vigencia}">  </c:out> </td>
	                                                          <td> <c:out value="${lrc.qty_dias_vigencia}">  </c:out> </td>
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
    
<script>

$("#desvioPadrao").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });


function ImprimirDadosTela() {
	document.getElementById('acaoRelatorioImprimir').value = 'ImprimirTela';
	$("#formClientesPorPeiodoEntrada").submit();

}

function ImprimirDadosPDF() {
	document.getElementById('acaoRelatorioImprimir').value = 'ImprimirPDF';
	$("#formClientesPorPeiodoEntrada").submit();

}

</script>
    
</body>

</html>
