<%@page import="br.com.portal.model.ModelPagPrincipal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
    
<!DOCTYPE html>
<html lang="en">

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
                                        
  <!--                                         <form  action="<%= request.getContextPath() %>/ServletPagPrincipal" method="post" id="formPrincipal">       -->                                      
                                            <!-- Aqui eh onde comeca a montar todos os elementos pequenos da pagina do corpo. -->
                                           <!-- task, page, download counter  start -->
                                            <div class="col-xl-3 col-md-6">
                                                <div class="card">
                                                    <div class="card-block">
                                                    
                                                        <div class="row align-items-center">
                                                            <div class="col-8">
                                                                <h4 class="text-c-purple" id="vlContAtivos">${vlContAtivos}</h4>
                                                                <h6 class="text-muted m-b-0">Valores Contratos</h6>
                                                            </div>
                                                            <div class="col-4 text-c-purple text-right">
                                                                <i class="fa fa-money f-28"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer bg-c-purple">
                                                        <div class="row align-items-center">
                                                            <div class="col-9">
                                                                <p class="text-white m-b-0">Contratos Principais</p>
                                                            </div>
                                                            <div class="col-3 text-right">
                                                                <i class="fa fa-line-chart text-white f-16"></i>
                                                            </div>
                                                        </div>
            
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-3 col-md-6">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <div class="row align-items-center">
                                                            <div class="col-8">
                                                                <h4 class="text-c-green" id="ContAtivos">${ContAtivos}</h4>
                                                                <h6 class="text-muted m-b-0">Contratos Ativos</h6>
                                                            </div>
                                                            <div class="col-4 text-c-green text-right">
                                                                <i class="fa fa-handshake-o f-28"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer bg-c-green">
                                                        <div class="row align-items-center">
                                                            <div class="col-9">
                                                                <p class="text-white m-b-0" id="PercContAtivos">${PercContAtivos}</p>
                                                            </div>
                                                            <div class="col-3 text-right">
                                                                <i class="fa fa-line-chart text-white f-16"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-3 col-md-6">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <div class="row align-items-center">
                                                            <div class="col-8">
                                                                <h4 class="text-c-red" id="ContDesativo" >${ContDesativo}   </h4>
                                                                <h6 class="text-muted m-b-0">Contratos Desativados</h6>
                                                            </div>
                                                            <div class="col-4 text-c-red text-right" >
                                                                <i class="fa fa-frown-o f-28"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer bg-c-red">
                                                        <div class="row align-items-center">
                                                            <div class="col-9">
                                                                <p class="text-white m-b-0" id="PercContDestivos" >${PercContDestivos}</p>
                                                            </div>
                                                            <div class="col-3 text-right">
                                                                <i class="fa fa-line-chart text-white f-16"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-3 col-md-6">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <div class="row align-items-center">
                                                            <div class="col-8">
                                                                <h4 class="text-warning" id="ContVenc3meses">${ContVenc3meses}</h4>
                                                                <h6 class="text-muted m-b-0">Vencimento prox. 3 meses</h6>
                                                            </div>
                                                            <div class="col-4 text-warning text-right">
                                                                <i class="fa fa-warning f-28"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card-footer bg-warning">
                                                        <div class="row align-items-center">
                                                            <div class="col-9">
                                                                <p class="text-white m-b-0" id="PercContVenc3meses">${PercContVenc3meses}</p>
                                                            </div>
                                                            <div class="col-3 text-right ">
                                                                <i class="fa fa-line-chart text-white f-16"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- task, page, download counter  end -->
    
                                            <!--  sale analytics start -->
                                            <div class="col-xl-8 col-md-12">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Análise de vendas</h5>
                                                        <span class="text-muted">Analise de contratos ativados nos últimos 12 meses.</span>
                                                        <div class="card-header-right">
                                                            <ul class="list-unstyled card-option">
                                                                <li><i class="fa fa fa-wrench open-card-option"></i></li>
                                                                <li><i class="fa fa-window-maximize full-card"></i></li>
                                                                <li><i class="fa fa-minus minimize-card"></i></li>
                                                                <li><i class="fa fa-refresh reload-card"></i></li>
                                                                <li><i class="fa fa-trash close-card"></i></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="card-block">
                                                        <!--    <div id="sales-analytics" style="height: 450px;"></div> -->
														<div style="height: 410px;">
															<canvas id="myChart"></canvas>
														</div>
													</div>
                                                </div>
                                            </div>

                                            <div class="col-xl-4 col-md-12">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <div class="row">
                                                            <div class="col">
                                                                <h4 id="vlrMesAtual">${vlrMesAtual}</h4>
                                                                <p class="text-muted">Este Mês</p>
                                                            </div>
                                                            <div class="col-auto">
                                                                <label class="label label-success">+5%</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    
                                                  <div class="card-block">
                                                        <div class="row">
                                                            <div class="col">
                                                                <h4 id="vlrMesAnterior">${vlrMesAnterior} </h4>
                                                                <p class="text-muted">Último Mês</p>
                                                            </div>
                                                            <div class="col-auto">
                                                                <label class="label label-success">+10%</label>
                                                            </div>
                                                        </div>
                                                   
                                                    </div>
                                                    
                                                </div>
                                                <div class="card quater-card">
                                                    <div class="card-block">
                                                        <h6 class="text-muted m-b-15">Este trimestre</h6>
                                                        <h4 id="totalTrimestre">${totalTrimestre}</h4>
                                                        <p class="text-muted" id="vlrContTrimestri">${vlrContTrimestri}</p>
                                                        <h5 class="m-t-15"id="qtyContratoTrimestri">${qtyContratoTrimestri}</h5>
                                                        <p class="text-muted">Contrato Principal<span class="f-right"id="perContratoTrimestri">${perContratoTrimestri}</span></p>
                                                        <div class="progress" id="divPerContratoTri"></div>
                                                        <p class="text-muted" id="vlrAditTrimestri">${vlrAditTrimestri}</p>
                                                        <h5 class="m-t-15" id="qtyAditivoTrimestri">${qtyAditivoTrimestri}</h5>
                                                        <p class="text-muted">Contrato Aditivo<span class="f-right" id="perAditivoTrimestri">${perAditivoTrimestri}</span></p>
                                                        <div class="progress" id="divPerAtivoTri"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--  sale analytics end -->
                                                
                                            
                                            
<!-- </form>         -->                                   
                                            

                                        </div>
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
    
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <script type="text/javascript">

    
    montaGraficoTela();
    calculaDataFinalVigencia();
    
    function montaGraficoTela(){
    	
    var urlAction = getContextPath() + '/ServletPagPrincipal';
    const ctx = document.getElementById('myChart');
   
    $.ajax({
			method : "get",
			   url : urlAction,
			  data : '&acao=montaGrafico',
			success: function(response){
				var json = JSON.parse(response);
				
			    new Chart(ctx, {
			        type: 'line',
			        data: {
			          labels  : json.meses ,
			          datasets: [{
			            label : 'Valores por Mês',
			            data  : json.valores,
			            borderWidth: 1
			          }
			          ]
			        },
			        options: {
			          scales: {
			            y: {
			              beginAtZero: true
			            }
			          }
			        }
			      });
				
		    }			
		}).fail(function( xhr, status, errorThrown ){
			alert('Erro ao buscar dados para o grafico: ' + xhr.responseText);
		});		 
 
    }
 
    function getContextPath() {
    	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

    function calculaDataFinalVigencia() {
    	 var urlAction   = getContextPath()  + '/ServletPagPrincipal';
	   	 $.ajax({ 			
	     			method : "get",
	     			url : urlAction,
	     			data : 'acao=montaTelaCards',
	     			success: function(lista){
	     				var json = JSON.parse(lista);
	     				if( json !== 'NAOEXISTECONTRATOCAD'){
		     				document.getElementById("vlContAtivos").textContent     = json.vlrTotalContratos;
		     				document.getElementById("ContAtivos").textContent       = json.qtyContratosAtivos;
		     				document.getElementById("PercContAtivos").textContent   = json.percentagemContratosAtivos + '% base';
		     				document.getElementById("ContDesativo").textContent     = json.qtyContratosDesativos;
		     				document.getElementById("PercContDestivos").textContent = json.percentagemContratosDesativos + '% base';
	/*	     				
		     				document.getElementById("ContTemp").textContent         = json.qtyContratosAditivoTemp;
		     				document.getElementById("PercContTemp").textContent     = json.percentagemContratosAditivoTempAtivos + '% base';
	*/	     				
							document.getElementById("ContVenc3meses").textContent         = json.qtyContratosVencemTesMeses;
							document.getElementById("PercContVenc3meses").textContent     = json.perContratosVencemTesMeses + '% base';
	
	
		     				document.getElementById("vlrMesAtual").textContent      = json.vlrContratosAditivoMesAtual;
		     				document.getElementById("vlrMesAnterior").textContent   = json.vlrContratosAditivoMesAnterior;
	
		     				document.getElementById("totalTrimestre").textContent       = json.vlrTotalTrimestre;
		     				document.getElementById("vlrContTrimestri").textContent     = json.vlrContratosTrimestre;
		     				document.getElementById("qtyContratoTrimestri").textContent = json.qtyContratosTrimestre;
		     				document.getElementById("perContratoTrimestri").textContent = json.perContratosTrimestre + '%';
	
		     				document.getElementById("vlrAditTrimestri").textContent     = json.vlrAditivoTrimestre;
		     				document.getElementById("qtyAditivoTrimestri").textContent  = json.qtyAditivoTrimestre;
		     				document.getElementById("perAditivoTrimestri").textContent  = json.perAditivoTrimestre + '%';
		     				
		     				var optionDivPerAtivoTri    = "<div class=\"progress-bar bg-c-green\" style=\"width: " + json.perAditivoTrimestre   +"%\"></div>";
		     				var optionDivPerContratoTri = "<div class=\"progress-bar bg-c-green\" style=\"width: " + json.perContratosTrimestre +"%\"></div>";
		     				$("#divPerAtivoTri"   ).html(optionDivPerAtivoTri   ).show();
		     				$("#divPerContratoTri").html(optionDivPerContratoTri).show();
	     				}
	     			}
	     	 }).fail(function( xhr, status, errorThrown ){
	     			alert('Erro ao selecionar informações de percentagens: ' + xhr.responseText);
	     	 }); 
   }
    
    </script>
     
</body>

</html>
