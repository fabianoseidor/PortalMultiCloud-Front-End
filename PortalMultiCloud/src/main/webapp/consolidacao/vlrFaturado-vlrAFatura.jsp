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
                                    <h5 class="card-title text-primary">Valor Faturado x Valor a Faturar</h5> <br>
	                                    <form class="form-material" action="<%= request.getContextPath() %>/ServletvlrFaturadovlrAFatura" method="post" id="formvlrFaturadovlrAFatura">
											<div class="card-deck">
											  <div class="card">
											     <div class="card-body border-primary">
												     
												     <div class="form-row">
												     
														  <div class="form-group form-default form-static-label col-md-3 mb-4">
														       <span  class="font-weight-bold font-italic" style="color: #708090" >Desvio Padrão</span>
															   <input style="color: #000000" type="text" name="desvioPadrao" id="desvioPadrao" class="form-control" required="required" placeholder="Valor do contrato" value="${desvioPadraoView}">
														  </div>
                                                            <div class="form-group form-default form-static-label col-md-3 mb-6">
														        <span class="font-weight-bold font-italic" style="color: #708090">Ano</span>
														        <input style="color: #000000" type="text" name="ano" id="ano" maxlength="4" class="form-control" required="required" placeholder="Informe o Ano" onkeypress="return somenteNumeros(event)" value="${ano}"> 
														    </div>
														    
                                                            <div class="form-group form-default form-static-label col-md-3 mb-6">
														        <span class="font-weight-bold font-italic" style="color: #708090">Mês</span>
														        <input style="color: #000000" type="text" name="mes" id="mes" maxlength="2" class="form-control" required="required" placeholder="Informe o Mês" onkeypress="return somenteNumeros(event);" value="${mes}"> 
														    </div>
								
												       
												       
												     </div>
												     <div class="form-row">
												        <div class="form-group form-default form-static-label col-md-12 mb-12">
												            <button type="submit" class="btn btn-outline-primary btn-sw btn-block" >Processar </button>
												        </div>
												     </div>
											       </div>
											  </div>
										      <!-- Card Dois, com o resumo do processamento -->
											  <div class="card">
											    <div class="card-body border-primary">
											      <h5 class="card-title">Resumo Processamento</h5><br>
											      <strong >Total PEP OK               : </strong> <span id="idqtyOK"        >${qtyOK}        </span><br>
											      <strong >Total PEP NOK              : </strong> <span id="idqtyNOK"       >${qtyNOK}       </span><br>
											      <strong >Total Valor A SAP          : </strong> <span id="idSvlrAFatura"  >${SvlrAFatura}  </span><br>
											      <strong >Total Valor Portal         : </strong> <span id="idSvlrFaturado" >${SvlrFaturado} </span><br>
											      <strong >Total PEP Não Contém SAP   : </strong> <span id="idqtyNPepSAP"   >${qtyNPepSAP}   </span><br>
											      <strong >Total PEP Não Contém Portal: </strong> <span id="idqtyNPepPortal">${qtyNPepPortal}</span><br>
											    </div>
											  </div>
											</div>
											

											<a  class="btn btn-outline-primary" role="button" href="<%= request.getContextPath() %>/consolidacao/report.jsp" class="waves-effect waves-dark">Gerar Aquivo Excel</a>
											<br><br>
											<div class="tab-content" id="myTabContent">
											
												<div class="tab-pane fade show active" id="recursoContrato" role="tabpanel" aria-labelledby="recurso-tab">
                                                  <div style="height: 300px; overflow: scroll;">
													<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm">
													  <thead>
													    <tr>
													      <th scope="col">PEP SAP         </th>
													      <th scope="col">Valor SAP       </th>
													      <th scope="col">PEP Portal      </th>
													      <th scope="col">Valor Portal    </th>
													      <th scope="col">Diferença Desvio( SAP - Portal )</th>
													      <th scope="col">Status          </th>
													    </tr>
													  </thead>
													  <tbody>
 					                                     <c:forEach items="${listVlrFaturados}" var="lrc">
		                                                   <tr>
		                                                     <c:if test="${lrc.status != 'OK'}" >
		                                                        <td> <c:out value="${lrc.pepAFaturar}">       </c:out> </td>
		                                                        <td> <c:out value="${lrc.vlrAFaturarView}">   </c:out> </td>
		                                                        <td> <c:out value="${lrc.pepFaturado}">       </c:out> </td>
		                                                        <td> <c:out value="${lrc.vlrFaturadoView}">   </c:out> </td>
		                                                        <td> <c:out value="${lrc.vlrDesvioDiffView}"> </c:out> </td>
																<c:choose>
																   <c:when test="${lrc.status == 'DIFF'}">
																     <td> <c:out value="Divergência após aplicar Desvio Padrão"> </c:out> </td>
																   </c:when>
																   <c:when test="${lrc.status == 'NOT_EXIST_FATURADO_NAO_TEM_NO_AFATURA'}">
																     <td> <c:out value="PEP não encontrado no SAP"> </c:out> </td>
																   </c:when>
																   <c:when test="${lrc.status == 'NOT_EXIST_AFATURA_NAO_TEM_NO_FATURADO'}">
																     <td> <c:out value="PEP não encontrado no Portal"> </c:out> </td>
																   </c:when>
																</c:choose>
		                                                     </c:if>
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


function somenteNumeros(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;

    // charCode 8 = backspace   
    // charCode 9 = tab
    if (charCode != 8 && charCode != 9) {
        // charCode 48 equivale a 0   
        // charCode 57 equivale a 9
        if (charCode < 48 || charCode > 57) {
            return false;
        }
    }
}


</script>
    
</body>

</html>
