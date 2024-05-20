
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagsContrato"%>        

  <!-- Modal Add Recurso -->
   <div class="modal t-modal primary" id="ModalRecurso" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Adicionando Recurso</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      <div class="modal-body">
  	      
			<div class="row">
				<div class="col-sm-12">
				<form class="form-material">	
					<div class="card">
						<div class="card-block">
							<div class="form-row">
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<label class="font-weight-bold font-italic">ID Recurso</label>
									<input type="text" name="id_recurso" id="id_recurso" class="form-control" readonly="readonly" value="${modelRecursoContrato.id_recurso}">
								</div>
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<label class="font-weight-bold font-italic">Data Cadastro</label>
									<input type="text" name="dt_cadastro" id="dt_cadastro" class="form-control" readonly="readonly" value="${modelRecursoContrato.dt_cadastro}">
								</div>
<!--  								
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<label class="font-weight-bold font-italic">Valor Recurso</label>
									<input type="text" name="vlrRecurso" id="vlrRecurso" class="form-control"  placeholder="Valor Recurso" value="${modelRecursoContrato.valor_recurso}">
								</div>
-->								
							</div>
						</div>
					</div>
				</form>	
				</div>
			</div>
 
			<div class="row">
				<div class="col-sm-12">
				<form class="form-material">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

                           <!-- Inicio da implementacao -->
								<div class="form-row">
									<!-- Campo Status -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Status</label>
										<select name=id_status_recurso id="id_status_recurso" class="form-control" required="required">
											<option value="" disabled selected>[-Selecione-]</option>
											        <tagsContrato:listaStatusRecurso />
										</select> 
									</div>
	
									<!-- Campo Retenção Backup -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
									
										<label class="font-weight-bold font-italic">Retenção Backup</label>
										<select name="id_retencao_backup" id="id_retencao_backup" class="form-control" required="required">
											<option value="" disabled selected>[-Selecione-]</option>
											        <tagsContrato:listaRetencaoBackup />    
										</select> 
									</div>
								    <!-- Campo Tipo Disco -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Tipo Disco</label>
										<select name="id_tipo_disco" id="id_tipo_disco" class="form-control" required="required" >
											<option value="" disabled selected>[-Selecione-]</option>
											        <tagsContrato:listaTipoDisco />
										</select> 
									</div>
	
								</div>
	
								<hr>  
								<br>
	
								<div class="form-row">
	
									<!-- Campo Sistema Operacional -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Sistema Operacional</label>
										<select name="id_so" id="id_so" class="form-control" required="required">
											<option value="" disabled selected>[-Selecione-]</option>
											      <tagsContrato:listaSistemaOperacional /> 
										</select> 
									</div>
	
									<!-- Campo Ambiente -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Ambiente</label>
										<select name="id_ambiente" id="id_ambiente" class="form-control" required="required">
											<option value="" disabled selected>[-Selecione-]</option>
											       <tagsContrato:listaAmbiente /> 
										</select> 
									</div>
	
									<!-- Campo Tipo Serviçoo -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Tipo Serviço</label>
										<select name="id_tipo_servico" id="id_tipo_servico" class="form-control" required="required">
											<option value="" disabled selected>[-Selecione-]</option>
											        <tagsContrato:listaTipoServico />
										</select> 
									</div>
								</div>
								<hr>  
								<br>
	
								<div class="form-row">
									<!-- Campo Hostname -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Hostname</label>
										<input type="text" name="hostnameModalRecurso" id="hostnameModalRecurso" class="form-control" placeholder="Hostname" > 
									</div>
	
									<!-- Campo Tamanho Disco -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Tamanho Disco</label>
										<input type="text" name="tamanhoDiscoModalRecurso" id="tamanhoDiscoModalRecurso" class="form-control only-number" placeholder="Tamanho Disco" > 
									</div>
	
									<!-- Campo Ambiente -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Primary IP</label>
										<input type="text" name="primaryIPModalRecurso" id="primaryIPModalRecurso" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="Primary IP" >  
									</div>
									
									<!-- Campo Host Vcenter -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">Host Vcenter</label>
										<input type="text" name="hostVcenterModalRecurso" id="hostVcenterModalRecurso" class="form-control" placeholder="Host Vcenter" > 
									</div>
	
									<!-- Campo EIP Vcenter -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">EIP Vcenter</label>
										<input type="text" name="eipVcenterModalRecurso" id="eipVcenterModalRecurso" onkeypress="return ValidaNumeroPonto( this , event ) ;" class="form-control" placeholder="EIP Vcenter" > 
									</div>
									
									<!-- Campo TAG Vcenter -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
                                         <label class="font-weight-bold font-italic">TAG Vcenter</label>
                                         <input type="text" name="tag_vcenter" id="tag_vcenter" class="form-control"  placeholder="TAG da Instancia no Vcenter" >
									</div>
								</div>
								<hr>  
								<br>

								<div class="form-row">
									<!-- Campo ID Contrato -->
									<div class="form-group form-default form-static-label col-md-6 mb-6">
										<label class="font-weight-bold font-italic">Nuvem</label>
										<input type="text" name="nuvem" id="nuvem" style="background: #000080; color: white" class="form-control" readonly="readonly" >
									</div>
	
									<!-- Campo Família Flavors -->
									<div class="form-group form-default form-static-label col-md-6 mb-6">
										<label class="font-weight-bold font-italic">Família Flavors</label>
										<select name="selectFamiliaFlavors" id="selectFamiliaFlavors" class="form-control" required="required">
										   <!--
											<option value="" disabled selected>[-Selecione-]</option>
											        <tagsContrato:listaFamiliaFlavores/>
										-->	        
										</select> 
									</div>
	
								</div>
								<br>
	
								<div class="form-row">
	                                <!-- Campo CPU --> 
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
										 <label class="font-weight-bold font-italic">CPU</label>
										 <input type="text" name="cpu" id="cpu" class="form-control" readonly="readonly" placeholder="CPU" > 
	                                </div>
	                                               
	                                <!-- Campo RAM --> 
	                                <div class="form-group form-default form-static-label col-md-4 mb-6">
										<label class="font-weight-bold font-italic">RAM</label>
										 <input type="text" name="ram" id="ram" class="form-control" readonly="readonly" placeholder="RAM" > 
	                                </div>
	                                
									<!-- Campo Valor Família Flavors -->
									<div class="form-group form-default form-static-label col-md-4 mb-6">
                                         <label class="font-weight-bold font-italic">Valor Família Flavors</label>
                                         <input type="text" name="valor" id="valor" class="form-control" placeholder="Valor" >
									</div>
								</div>

								<hr>  
								<br>
	
							    <div class="form-group form-default form-static-label mb-6">
									<label class="font-weight-bold font-italic">Observações</label>
									<textarea class="form-control" id="obs" name="obs" placeholder="Observações" rows="5" ></textarea>
								</div>
	
                            <!-- Fim Inicio da implementacao -->

						</div>
					</div>
				</form>	
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div style="height: 300px; overflow: scroll;">
								<table class="table table-striped table-hover table-sm table-bordered table-responsive-sm" id="tabelaResutRecurso">
									  <thead>
									    <tr>
									      <th scope="col">ID Recurso     </th>
									      <th scope="col">Cliente        </th>
									      <th scope="col">Hotname        </th>
									      <th scope="col">Status Recurso </th>
									      <th scope="col">Ambiente       </th>
									      <th scope="col">Familia        </th>
									      <th scope="col">Serviço        </th>
									      <th scope="col">IP             </th>
									      <th scope="col">Nuvem          </th>
									      <th scope="col">Site           </th>
									      <th scope="col">Editar         </th>
									    </tr>
									  </thead>
									<tbody id="TbodyProduto">

									</tbody>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
			
	      </div>
	      <div class="modal-footer">
	      <!--  
 	        <button type="button" class="btn btn-primary" onclick="LimparRecursoModal();" >Novo</button> 
 	        <button type="button" class="btn btn-primary" onclick="AddRecursoModal();" >Salvar</button> 
 	      -->
 	        <button type="button" class="btn waves-effect waves-light btn-outline-primary   float-left  pequeno" name="btNovoMD"   id="btNovoMD"   onclick="LimparRecursoModal(); habilitarComponentesModalRecurso(false);">Novo</button>
			<button type="button" class="btn waves-effect waves-light btn-outline-success   float-right pequeno" name="btSalvarMD" id="btSalvarMD" onclick="AddRecursoModal();">Salvar</button>
	        <button type="button" class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" name="btFecharMD" id="btFecharMD" data-dismiss="modal">Fechar</button>
	      	        
	      </div>
	    </div>
	  </div>
 </div>



 <script type="text/javascript">

 function ValidaNumeroPonto( obj , e )
 {
     var tecla = ( window.event ) ? e.keyCode : e.which;
     if ( tecla == 8 || tecla == 0 )
         return true;
     if ( tecla != 46 && tecla < 48 || tecla > 57 )
         return false;
 }


 function AddRecursoModal() {
		
	 var urlAction           = document.getElementById("formCadContrato").action;
	 var valueStatusRecurso  = $("#id_status_recurso option:selected"             ).val(); // 1
	 var valueRetencaoBackup = $("#id_retencao_backup option:selected"            ).val(); // 2
	 var valueTipoDisco      = $("#id_tipo_disco option:selected"                 ).val(); // 3
	 var valueSo             = $("#id_so option:selected"                         ).val(); // 4
	 var valueAmbiente       = $("#id_ambiente option:selected"                   ).val(); // 5
	 var valueTipoServico    = $("#id_tipo_servico option:selected"               ).val(); // 6
	 var valueFamiliaFlavors = $("#selectFamiliaFlavors option:selected"          ).val(); // 7
	 var id_contrato         = document.getElementById("id_contrato"              ).value; // 8
	 var id_cliente          = document.getElementById("id_cliente"               ).value; // 9
	 var nomeCliente         = document.getElementById("nomeCliente"              ).value; // 10
	 var tag_vcenter         = document.getElementById("tag_vcenter"              ).value; // 11
	 var cpu                 = document.getElementById("cpu"                      ).value; // 12
	 var ram                 = document.getElementById("ram"                      ).value; // 13
	 var valor               = document.getElementById("valor"                    ).value; // 14
	 var obs                 = document.getElementById("obs"                      ).value; // 15
	 var idRecurso           = document.getElementById("id_recurso"               ).value; // 16
	 var dtCadastro          = document.getElementById("dt_cadastro"              ).value; // 17
//	 var vlrRecurso          = document.getElementById(("vlrRecurso"               ).value; // 17
	 var hostname            = document.getElementById("hostnameModalRecurso"     ).value; // 19
	 var tamanhoDisco        = document.getElementById("tamanhoDiscoModalRecurso" ).value; // 20
	 var primaryIP           = document.getElementById("primaryIPModalRecurso"    ).value; // 21
	 var eipVcenter          = document.getElementById("eipVcenterModalRecurso"   ).value; // 22
	 var hostVcenter         = document.getElementById("hostVcenterModalRecurso"  ).value; // 23

	 var dados = 'acao=AddRecursoModal' +
	 		     '&statusRecurso='      + valueStatusRecurso +
			     '&retencaoBackup='     + valueRetencaoBackup+
			     '&tipoDisco='          + valueTipoDisco     +
			     '&so='                 + valueSo            +
			     '&ambiente='           + valueAmbiente      +
			     '&tipoServico='        + valueTipoServico   +
			     '&familiaFlavors='     + valueFamiliaFlavors+
			     '&idContrato='         + id_contrato        +
			     '&idCliente='          + id_cliente         +
			     '&nomeCliente='        + nomeCliente        +
			     '&tagVcenter='         + tag_vcenter        +
			     '&cpu='                + cpu                +
			     '&ram='                + ram                +
			     '&valor='              + valor              +
			     '&obs='                + obs                +
			     '&idRecurso='          + idRecurso          +
			     '&dtCadastro='         + dtCadastro         +
//                 '&vlrRecurso='         + vlrRecurso         +    // Retirado a pedido de Eugenio             
                 '&hostname='           + hostname           +
                 '&tamanhoDisco='       + tamanhoDisco       +
                 '&primaryIP='          + primaryIP          +
                 '&eipVcenter='         + eipVcenter         +
                 '&hostVcenter='        + hostVcenter        ;
	

	 if( validaCampoVazio() ){	
		 $.ajax({
				method : "get",
				url    : urlAction,
				data   : dados,
				success: function(lista){

	  				var json            = JSON.parse(lista);
	  				var tituloPrincipal = '';
	  				var textoPrincipal  = '';
	  				var nomeModal       = 'ModalRecurso';
	  				var iconi           = 'success';
	  				
	  				$("#id_recurso" ).val( json.id_recurso  );
	  				$("#dt_cadastro").val( json.dt_cadastro );
					
//					MontatabelaResutadoRecurso();
					// alert('Recurso adicionado com sucesso!');
					
					if(json.tipoCrud === 1 ){
		  			   tituloPrincipal = 'Adição de Recurso';
		  			   textoPrincipal  = 'O Recurso de ID ' + json.id_recurso + ' do cliemte ' + nomeCliente + ' - Contrato ' + id_contrato + ", foi 'Inserido' com sucesso!";
					}else if(json.tipoCrud === 2 ){
			  			   tituloPrincipal = 'Atualização de Recurso';
			  			   textoPrincipal  = 'O Recurso de ID ' + json.id_recurso + ' do hostname ' + hostname + ' - Contrato ' + id_contrato + ", foi 'Atuallizado' com sucesso!";
					}
					
					MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );
				}
		 }).fail(function( xhr, status, errorThrown ){
				// alert('Erro na Adição/Atualização de Recurso: ' + xhr.responseText);
				MensagemConfimacaoModal( "error", 'Erro na Adição/Atualização de Recurso', xhr.responseText, 'ModalRecurso' );
		 }); 
//		 MontatabelaResutadoRecurso();
	 }
}

  function MontatabelaResutadoRecurso() {

		var idContrato = document.getElementById("id_contrato").value;
		var urlAction = document.getElementById("formCadContrato").action;
			 
		$.ajax({
		
		method : "get",
		   url : urlAction,
		  data : "idContrato=" + idContrato + '&acao=MontatabelaResutadoRecurso',
		success: function(response){
					var json = JSON.parse(response);
					var tagVcenter = '';
					$('#tabelaResutRecurso > tbody > tr').remove();
					for(var p = 0; p < json.length; p++){
						// if( json[p].tag_vcenter !== undefined ) tagVcenter = json[p].tag_vcenter; else tagVcenter = '';
			  			$('#tabelaResutRecurso > tbody').append('<tr> <td>' + json[p].id_recurso     + '</td> <td>'
												                            + json[p].razao_social   + '</td> <td>'
												                            + json[p].hostname       + '</td> <td>'
												                            + json[p].status_recurso + '</td> <td>'
												                            + json[p].ambiente       + '</td> <td>'
												                            + json[p].familia        + '</td> <td>'
												                            + json[p].tipo_servico   + '</td> <td>'
												                            + json[p].primary_ip     + '</td> <td>'
												                            + json[p].nuvem          + '</td> <td>'
												                            + json[p].site           + '</td> <td>'
			  			                                                    + '<button onclick="verRecursoContrato(' + json[p].id_recurso + ');" type="button" class="btn btn-info">Selecionar</button></td></tr>');					                                                                            
				}
		}
		
		}).fail(function( xhr, status, errorThrown ){
			// alert('Erro ao monta lista de recurso: ' + xhr.responseText);
			MensagemConfimacaoModal( "error", 'Erro ao Montar Tabela de Resutado Recurso', xhr.responseText, 'ModalRecurso' );
		});		 
		 
	}

 /*================================================================*/
 /*=                                                              =*/
 /*=             Inicio Validacao do ADD Recurso                  =*/
 /*=                                                              =*/
 /*================================================================*/

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 $('#ModalRecurso').on('show.bs.modal', function(e){
 	
// 	var parametros = "?acao=montaRecursoInicial&idcontrato=" + idContrato;
 	
 	 var urlAction  = document.getElementById("formCadContrato").action;
 	 var idContrato = document.getElementById("id_contrato" ).value;
// 	 alert( 'idContrato: ' + idContrato );
 	 
 	 $.ajax({
  			
  			method : "get",
  			url : urlAction,
  			data : 'acao=montaRecursoInicial&idcontrato='+idContrato,
  			
  			success: function(lista){
   				var json = JSON.parse(lista);
   				$("#tag_vcenter").val(json.tag_vcenter);
   				$("#nuvem"      ).val(json.nuvem);
   				$("#cpu"        ).val(json.cpu);
   				$("#ram"        ).val(json.ram);
   				$("#valor"      ).val(json.valor);
//   				$("#vlrRecurso" ).val(json.valor);
  			}
 	        
  	 }).fail(function( xhr, status, errorThrown ){
  			// alert('Erro carregar select Produto: ' + xhr.responseText);
  			MensagemConfimacaoModal( "error", 'Erro ao montar Recurso Inicial', xhr.responseText, 'ModalRecurso' );
  	 }); 
 	 montaSelectFamiliaModal( 0 );
// 	 MontatabelaResutadoRecurso();
 });
 
 function montaSelectFamiliaModal( idFamilia ) {

		var idNuvem = $("#id_nuvem option:selected").val();	
		var idFamiliaFlavors = idFamilia;
		var urlAction = document.getElementById("formCadContrato").action;
		 $.ajax({
				method : "get",
				url : urlAction,
				data : 'acao=montaSelectFamiliaModal&idNuvem=' + idNuvem,

				success: function(lista){
					    var option = '<option value="" disabled selected>[-Selecione-]</option>';
						var selected = '';
						var json = JSON.parse(lista);
						for(var p = 0; p < json.length; p++){
							if( json[p].id_familia_flavors == idFamiliaFlavors  )
						  	     selected = 'selected';
							 else selected = '';
							option += '<option value=' + json[p].id_familia_flavors + ' ' + selected + '>' + json[p].familia + '</option>';
						}
						$("#selectFamiliaFlavors").html(option).show();  
				}
		 }).fail(function( xhr, status, errorThrown ){
				// alert('Erro carregar select Produto: ' + xhr.responseText);
				MensagemConfimacaoModal( "error", 'Erro ao montar Select Familia!', xhr.responseText, 'ModalRecurso' );
		 }); 
		 
	}

 $('#ModalRecurso').on('hidden.bs.modal', function (e) {
		
		$('#id_status_recurso'       ).get(0).selectedIndex = 0;
		$('#id_retencao_backup'      ).get(0).selectedIndex = 0;
		$('#id_tipo_disco'           ).get(0).selectedIndex = 0;
		$('#id_so'                   ).get(0).selectedIndex = 0;
		$('#id_ambiente'             ).get(0).selectedIndex = 0;
		$('#id_tipo_servico'         ).get(0).selectedIndex = 0;
		$('#selectFamiliaFlavors'    ).get(0).selectedIndex = 0;
		$("#cpu"                     ).val("");
		$("#ram"                     ).val("");
		$("#valor"                   ).val("");
//		$("#vlrRecurso"              ).val("");
		$("#obs"                     ).val("");		
		$("#hostnameModalRecurso"    ).val("");
		$("#tamanhoDiscoModalRecurso").val("");
		$("#primaryIPModalRecurso"   ).val("");
		
		goRecursoAditivo( document.getElementById("id_cliente").value );
		
	})
	
	
function LimparRecursoModal() {

		$('#id_status_recurso'       ).get(0).selectedIndex = 0;
		$('#id_retencao_backup'      ).get(0).selectedIndex = 0;
		$('#id_tipo_disco'           ).get(0).selectedIndex = 0;
		$('#id_so'                   ).get(0).selectedIndex = 0;
		$('#id_ambiente'             ).get(0).selectedIndex = 0;
		$('#id_tipo_servico'         ).get(0).selectedIndex = 0;
		$('#selectFamiliaFlavors'    ).get(0).selectedIndex = 0;
		$("#cpu"                     ).val("");
		$("#ram"                     ).val("");
		$("#valor"                   ).val("");
//		$("#vlrRecurso"              ).val("");
		$("#obs"                     ).val("");		
		$("#hostnameModalRecurso"    ).val("");
		$("#tamanhoDiscoModalRecurso").val("");
		$("#primaryIPModalRecurso"   ).val("");
		$("#id_recurso"              ).val("");
		$("#dt_cadastro"             ).val("");
		$("#hostVcenterModalRecurso" ).val("");
		$("#eipVcenterModalRecurso"  ).val("");
		
		var urlAction = document.getElementById("formCadContrato").action;
		
	 	 $.ajax({
	  			
	  			method : "get",
	  			url : urlAction,
	  			data : 'acao=generateCodeTagVcenter',
	  			
	  			success: function(lista){
	   				var json = JSON.parse(lista);
	   				$("#tag_vcenter").val( json );
	  			}
	 	        
	  	 }).fail(function( xhr, status, errorThrown ){
	  			// alert('Erro ao gerar o codigo TagVcenter: ' + xhr.responseText);
	  			MensagemConfimacaoModal( "error", 'Erro ao gerar o codigo Tag Vcenter!', xhr.responseText, 'ModalRecurso' );
	  	}); 
}	

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function habilitarComponentesModalRecurso(habilitar) {
	 document.getElementById('id_status_recurso'       ).disabled = habilitar;
	 document.getElementById('id_retencao_backup'      ).disabled = habilitar;
	 document.getElementById('id_tipo_disco'           ).disabled = habilitar;
	 document.getElementById('id_so'                   ).disabled = habilitar;
	 document.getElementById('id_ambiente'             ).disabled = habilitar;
	 document.getElementById('id_tipo_servico'         ).disabled = habilitar;
	 document.getElementById('hostnameModalRecurso'    ).disabled = habilitar;
	 document.getElementById('tamanhoDiscoModalRecurso').disabled = habilitar;
	 document.getElementById('primaryIPModalRecurso'   ).disabled = habilitar;
	 document.getElementById('hostVcenterModalRecurso' ).disabled = habilitar;
	 document.getElementById('eipVcenterModalRecurso'  ).disabled = habilitar;
	 document.getElementById('tag_vcenter'             ).disabled = habilitar;
	 document.getElementById('selectFamiliaFlavors'    ).disabled = habilitar;
	 document.getElementById('cpu'                     ).disabled = habilitar;
	 document.getElementById('ram'                     ).disabled = habilitar;
	 document.getElementById('valor'                   ).disabled = habilitar;
	 document.getElementById('obs'                     ).disabled = habilitar;
	 document.getElementById('btSalvarMD'              ).disabled = habilitar;
	 
 }
 
 
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function verRecursoContrato( idRecurso ) {	 
	
	var urlAction = document.getElementById("formCadContrato").action;
	
    $.ajax({
			method : "get",
			url    : urlAction,
			data   : "idRecurso=" + idRecurso + '&acao=MontaRecursoEdit',
			success: function(lista){
				 var json = JSON.parse(lista);
				 var idFamiliaFlavors = json.id_familia_flavors;
				 $("#id_recurso"               ).val( json.id_recurso         );
				 $("#dt_cadastro"              ).val( json.dt_cadastro        );
				 $('#id_contrato'              ).val( json.id_contrato        );
				 $("#id_cliente"               ).val( json.id_cliente         );
				 $("#nomeCliente"              ).val( json.nomeCliente        );
				 $("#id_status_recurso"        ).val( json.id_status_recurso  );
				 $("#id_retencao_backup"       ).val( json.id_retencao_backup );
				 $("#id_tipo_disco"            ).val( json.id_tipo_disco      );
				 $("#id_so"                    ).val( json.id_so              );
				 $("#id_ambiente"              ).val( json.id_ambiente        );
				 $("#id_tipo_servico"          ).val( json.id_tipo_servico    );
				 $("#selectFamiliaFlavors"     ).val( json.id_familia_flavors );
				 $("#tag_vcenter"              ).val( json.tag_vcenter        );				 
				 $("#hostVcenterModalRecurso"  ).val( json.host_vcenter       );
				 $("#eipVcenterModalRecurso"   ).val( json.eip_vcenter        );				 
				 $("#nuvem"                    ).val( json.nuvem              );
				 $("#cpu"                      ).val( json.cpu                );
				 $("#ram"                      ).val( json.ram                );
				 $("#valor"                    ).val( json.valor              );
//				 $("#vlrRecurso"               ).val( json.valor              );				 
				 $("#hostnameModalRecurso"     ).val( json.hostname           );
				 $("#tamanhoDiscoModalRecurso" ).val( json.tamanhoDisco       );
				 $("#primaryIPModalRecurso"    ).val( json.primaryIP          );				 
				 $("#obs"                      ).val( json.obs                );
				 
				 if(document.getElementById('tipoUserAdmin').value.trim() !== "1" )
					 habilitarComponentesModalRecurso(true);
				 else habilitarComponentesModalRecurso(false);

			}
	}).fail(function( xhr, status, errorThrown ){
			// alert('Erro carregar select Produto: ' + xhr.responseText);
			MensagemConfimacaoModal( "error", 'Erro ao Montar Recurso para Editar!', xhr.responseText, 'ModalRecurso' );
	}); 
    montaSelectFamiliaModal( idFamiliaFlavors );
}

function validaCampoVazio() {
	
	 var valueStatusRecurso  = $("#id_status_recurso option:selected"   ).val(); // 1
	 var valueRetencaoBackup = $("#id_retencao_backup option:selected"  ).val(); // 2
	 var valueFamiliaFlavors = $("#selectFamiliaFlavors option:selected").val(); // 3  
	 var valueTipoServico    = $("#id_tipo_servico option:selected"     ).val(); // 4
	 var valueAmbiente       = $("#id_ambiente option:selected"         ).val(); // 5
	 var valueSo             = $("#id_so option:selected"               ).val(); // 6
	 var valueTipoDisco      = $("#id_tipo_disco option:selected"       ).val(); // 7
	 

	 if( valueStatusRecurso === null || valueStatusRecurso === '' || valueStatusRecurso.trim() === '' ){         
		// alert('Favor informar o Status.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar o Status!', 'ModalRecurso' );
		 document.getElementById("id_status_recurso").focus();
		 return false;
	 }else  if( valueRetencaoBackup === null || valueRetencaoBackup === '' || valueRetencaoBackup.trim() === '' ){
		 // alert('Favor informar a Retenção de Backup.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar a Retenção de Backup!', 'ModalRecurso' );
		 document.getElementById("id_retencao_backup").focus();
		 return false;		 
	 }else  if( valueTipoDisco === null || valueTipoDisco === '' || valueTipoDisco.trim() === '' ){
		// alert('Favor informar o Tipo de Disco.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar o Tipo de Disco!', 'ModalRecurso' );
		 document.getElementById("id_tipo_disco").focus();
		 return false;		 
	 }else  if( valueSo === null || valueSo === '' || valueSo.trim() === '' ){
		// alert('Favor informar o SO.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar o SO!', 'ModalRecurso' );
		 document.getElementById("id_so").focus();
		 return false;
	 }else  if( valueAmbiente === null || valueAmbiente === '' || valueAmbiente.trim() === '' ){
		 // alert('Favor informar o Ambiente.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar o Ambiente!', 'ModalRecurso' );
		 document.getElementById("id_ambiente").focus();
		 return false;
	 }else  if( valueTipoServico === null || valueTipoServico === '' || valueTipoServico.trim() === '' ){
		 // alert('Favor informar o Tipo Servico.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar o Tipo Servico!', 'ModalRecurso' );
		 document.getElementById("id_tipo_servico").focus();
		 return false;
	 }else  if( valueFamiliaFlavors === null || valueFamiliaFlavors === '' || valueFamiliaFlavors.trim() === '' ){
		 // alert('Favor informar a Familia Flavors.');
		 MensagemConfimacaoModal( "info", 'Validação', 'Favor informar a Familia Flavors!', 'ModalRecurso' );
		 document.getElementById("selectFamiliaFlavors").focus();
		 return false;
	 }
	 
	 return true;
}


/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function goRecursoAditivo( idContrato ) {
	 var urlAction  =  window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)) + "/ServletContratoController"; // + "?acao=montaRecursoInicial&idContrato=" + idContrato + "&idAditivo=" + idAditivo;
	 var parametros = "?acao=buscarContratoCliente&idContratoCliente=" + idContrato;
	 var url = urlAction + parametros;
	 window.location.href = url;
// alert(url);

}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$(document).ready(function () {

    $('#selectFamiliaFlavors').change(function () {

        var es        = document.getElementById('selectFamiliaFlavors');
        idFamiliaFl   = es.options[es.selectedIndex].value;
        var urlAction = document.getElementById("formCadContrato").action;
        
  		$.ajax({
  			method : "get",
  			url    : urlAction,
  			data   : "idFamiliaFl=" + idFamiliaFl + '&acao=montaCamosFamiliaFl',
  			success: function(lista){
  				var json = JSON.parse(lista);

  				$("#cpu"        ).val( json.cpu   );
  				$("#ram"        ).val( json.ram   );
  				$("#valor"      ).val( json.valor );
//  				$("#vlrRecurso" ).val( json.valor );

  //				$("#id_familia_flavors").html(option).show();  
  			}
  		}).fail(function( xhr, status, errorThrown ){
  			//alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
  			MensagemConfimacaoModal( "error", 'Erro ao buscar valores para o campo Família Flavors!', xhr.responseText, 'ModalRecurso' );
  		}); 
    });
});

 </script>

