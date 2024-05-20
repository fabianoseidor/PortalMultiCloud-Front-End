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
	                                    <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletAFaturar" method="post" id="formAFaturar">
											<div class="card-deck">
											  <div class="card">
											     <div class="card-body border-primary">
												     <h5 class="card-title">Arquivo A Faturar para processamento</h5>
												     <div class="form-row">
												       <div class="form-group form-default form-static-label col-md-12 mb-12">
												         <br>
														 <div class="container">
														    <label class="custom-file" id="customFile">
														        <input type="file" class="custom-file-input" id="arqAFaturar" value="${nomeArq}" name="arqAFaturar" aria-describedby="fileHelp" accept="text/plain">
														        <span class="custom-file-control form-control-file"></span>
														    </label>
														 </div>
												       </div>
												     </div>
												     <div class="form-row">
												        <div class="form-group form-default form-static-label col-md-12 mb-12">
												            <button type="submit" class="btn btn-outline-primary btn-sw btn-block" onclick='informe();'>Processar Arquivo</button>
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
  $('.custom-file-input').on('change',function(){
     var fileName = document.getElementById("arqAFaturar").files[0].name;
     $(this).next('.form-control-file').addClass("selected").html(fileName);
  })
  
  function informe(){ 
	  var fileName = document.getElementById("arqAFaturar").files[0].name;
	  document.getElementById("msg").textContent = 'Processando o arquivo ==> ' + fileName;
  } 
</script>
    
</body>

</html>
