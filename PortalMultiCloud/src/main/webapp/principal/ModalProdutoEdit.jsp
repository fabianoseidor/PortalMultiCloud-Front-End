<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<!-- 
******************************************************************************************************
*
*                                  Modal Add Produto        
*
******************************************************************************************************
-->
   <!-- Modal Add Produto -->
   <div class="modal t-modal primary" id="ModalProdutoEdit" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Atualiza Produto</h5>
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

							<!-- Campo ID Produto -->
							<div class="form-group form-default form-static-label">
								<select name=selectProduto id="selectProduto" class="form-control" required="required">
									<option value="" disabled selected>Selecione Produto</option>
									       
								</select> 
							</div>
							<hr>  
							<br>

							<div class="form-row">
								<!-- Campo Valor Produto -->
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<label class="float-label">Valor Produto</label>
									<input type="text" name="vlrProduto" id="vlrProduto" class="form-control" value="">
									<span class="form-bar"></span> 
								</div>
								<!-- Campo observação -->
								<div class="form-group form-default form-static-label col-md-6 mb-6">
									<label class="float-label">Observação</label>
									<input type="text" name="obsProduto" id="obsProduto" class="form-control"  value="">
									<span class="form-bar"></span> 
								</div>
							</div>
							
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12">
					<!-- Basic Form Inputs card start -->
					<div class="card">
						<div class="card-block">

							<div class="form-row">
                              <!-- Campo Quantidade --> 
                              <div class="form-group form-default form-static-label col-md-4 mb-6">
							      <label class="float-label">Quantidade</label>
							      <input type="text" name="idQty" id="idQty" class="form-control only-number" required="required" placeholder="Quantidade" value=""> 
							      <span class="form-bar"></span> 
                              </div>
                              <!-- Campo Valor Unitário --> 
                              <div class="form-group form-default form-static-label col-md-4 mb-6">
							      <label class="float-label">Valor Unitário</label>
							      <input type="text" name="vlrUnit" id="vlrUnit" class="form-control" placeholder="Valor Unitário" value=""> 
							      <span class="form-bar"></span> 
                              </div>
						      <!-- Campo Valor Famiia Flavors -->
						      <div class="form-group form-default form-static-label col-md-4 mb-6">
                                  <label class="float-label">Valor Total</label>
                                  <input type="text" name="vltTotal" id="vltTotal" class="form-control" placeholder="Valor Total" value="">
                                  <span class="form-bar"></span>
						      </div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn waves-effect waves-light btn-outline-primary   float-left  pequeno" onclick="gravarProduto();" >Salvar</button>
	        <button type="button" class="btn waves-effect waves-light btn-outline-secondary float-right pequeno" data-dismiss="modal">Fechar</button>
	        
	        
	      </div>
	    </div>
	  </div>
 </div>

<script type="text/javascript">
$("#vlrProduto"      ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#vlrUnit"         ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#vltTotal"        ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function gravarProduto() {
	 var idProdutoFunc  = select.options[selectProduto.selectedIndex].value;
	 var idQtyFunc      = document.getElementById("idQty"       ).value;
	 var vlrUnitFunc    = document.getElementById("vlrUnit"     ).value;
	 var vltTotalFunc   = document.getElementById("vltTotal"    ).value;
	 var idContratoFunc = document.getElementById("id_contrato" ).value;
	 var obsProduto     = document.getElementById("obsProduto"  ).value;
	 var urlAction      = document.getElementById("formContrato").action;
	 
	 vlrUnitFunc = vlrUnitFunc.replace(/[^\d]+/g,'');
	 vlrUnitFunc = vlrUnitFunc /100;
	 
	 vltTotalFunc = vltTotalFunc.replace(/[^\d]+/g,'');
	 vltTotalFunc = vltTotalFunc /100;

	 $.ajax({
			method : "get",
 			url : urlAction,
 			data : "acao=editProduto" + 
 			      '&idContrato=' + idContratoFunc + 
 			      '&idProduto='  + idProdutoFunc  + 
 			      '&QtyProduto=' + idQtyFunc      + 
 			      '&vlrUnit='    + vlrUnitFunc    +
 			      '&obsProduto=' + obsProduto     +
 			      '&vltTotal='   + vltTotalFunc,
 			success: function(lista){

 	  				var json = JSON.parse(lista);
 	  				if( json === 'SUCESSO' ){
	 	  				var iconi           = "success";
	 	  				var tituloPrincipal = "Update de Produto";
	 	  				var textoPrincipal  = "Updade do Produto realizado com sucesso";
	 	  				var nomeModal       = 'ModalProdutoEdit';
	 	  				MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	 
	 	  			}else{
		  				var iconi           = "error";
	 	  				var tituloPrincipal = "ERRO";
	 	  				var textoPrincipal  = "Erro: Updade de Produto: " + json;
	 	  				var nomeModal       = 'ModalProdutoEdit';
	 	  				MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	 
 			  		}

               
 			}		 
		 
 	 }).fail(function( xhr, status, errorThrown ){
			var iconi           = "error";
			var tituloPrincipal = "ERRO";
			var textoPrincipal  = "Erro: Cadastro de Produto: " + xhr.responseText;
			var nomeModal       = 'ModalProdutoEdit';
			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	 

	 });

}

$('#ModalProdutoEdit').on('hidden.bs.modal', function (e) {
	
	goRecursoAditivo( document.getElementById("id_cliente").value );
		
})
	
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function verProdutoContrato( v_Prod, v_vlrProd, v_obsProd, v_QtyProd, v_vlrUnitProd, v_vltTotalProd ) {	 
	 $("#selectProduto").val( v_Prod         );
	 $("#vlrProduto"   ).val( v_vlrProd      );
	 $('#obsProduto'   ).val( v_obsProd      );
	 $("#idQty"        ).val( v_QtyProd      );
	 $("#vlrUnit"      ).val( v_vlrUnitProd  );
	 $("#vltTotal"     ).val( v_vltTotalProd );
	 montaSelectProdutoInicial( v_Prod );

}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 
 /*
$('#ModalProdutoEdit').on('show.bs.modal', function(e){
	 
	 
	 var urlAction = document.getElementById("formContrato").action;

	 $.ajax({
 			
 			method : "get",
 			url : urlAction,
 			data : '&acao=montaSelectProdudo',

 			success: function(lista){
 				var option = '<option value="" disabled selected>Selecione Produto</option>';
 				var json = JSON.parse(lista);
 				
 				alert('json.length: ' + json.length);
 				
 				for(var p = 0; p < json.length; p++){
 					option += '<option value=' + json[p].id_produto + '>' + json[p].produto + '</option>';
 				}
 				
 				alert('option: ' + option);
 				
 				
 			}
 	 }).fail(function( xhr, status, errorThrown ){
 			// alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
			var tituloPrincipal = "Erro carregar select Produto";
			var textoPrincipal  = xhr.responseText;  
			var nomeModal       = 'ModalProdutoEdit'; 
			MensagemConfimacaoModal( iconi, tituloPrincipal, xhr.responseText, nomeModal );	
 	 }); 
 }
);
*/
function montaSelectProdutoInicial( v_idProd ){
	 var urlAction = document.getElementById("formContrato").action;
	 

	 $.ajax({
 			
 			method : "get",
 			url : urlAction,
 			data : '&acao=montaSelectProdudo',

 			success: function(lista){
 				var option = '<option value="" disabled selected>Selecione Produto</option>';
 				var json = JSON.parse(lista);

 				for(var p = 0; p < json.length; p++){
 					if( json[p].id_produto == v_idProd  )selected = 'selected'; else selected = '';
 						
 					option += '<option value=' + json[p].id_produto + ' ' + selected + '>' + json[p].produto + '</option>';
 				}
 				
 				$("#selectProduto").html(option).show();  
 				
 				
 			}
 	 }).fail(function( xhr, status, errorThrown ){
 			// alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
			var tituloPrincipal = "Erro carregar select Produto";
			var textoPrincipal  = xhr.responseText;  
			var nomeModal       = 'ModalProdutoEdit'; 
			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	
 	 }); 

}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
var select = document.getElementById('selectProduto');

select.addEventListener('change', function(){
   carregaVlrSelect( select.value );
   if (select.options[select.selectedIndex].value != "" ){
       var inputQty      = document.querySelector("#idQty"   );
       var inputvlrUnit  = document.querySelector("#vlrUnit" );
       var inputvltTotal = document.querySelector("#vltTotal");
       
       inputQty.disabled      = false;
       inputvlrUnit.disabled  = false;
       inputvltTotal.disabled = false;
       
   }
});

const qtyProdutoVlrTotal = document.getElementById('idQty');

qtyProdutoVlrTotal.addEventListener('blur', function (evt) {

   var vlrUnitCal     = document.getElementById("vlrUnit").value;
   var qty = qtyProdutoVlrTotal.value;
   cauculoVlrToral( qty, vlrUnitCal );
});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
const vlrProdutoInput = document.getElementById('vlrProduto');
vlrProdutoInput.addEventListener('blur', function (evt) {
	var vlrUnitAut = vlrProdutoInput.value;
	$("#vlrUnit").val( vlrUnitAut );

	 var qty =  document.getElementById("idQty").value;

	 if( qty != null && qty != '' && qty.trim() != '' ){
		 cauculoVlrToral( qty, vlrUnitAut );
	 }
});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
const vlrProdutoUnitario = document.getElementById('vlrUnit');
vlrProdutoUnitario.addEventListener('blur', function (evt) {
	var vlrUnitAut = vlrProdutoUnitario.value;
	$("#vlrUnit").val( vlrUnitAut );

	 var qty =  document.getElementById("idQty").value;

	 if( qty != null && qty != '' && qty.trim() != '' ){
		 cauculoVlrToral( qty, vlrUnitAut );
	 }
});

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function carregaVlrSelect( idProduto ){
	 var urlAction = document.getElementById("formContrato").action;
	 
	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : "idProduto=" + idProduto + '&acao=MostraValoresSelectProdudo',
  			success: function(lista){
  				var json = JSON.parse(lista);
  				$("#vlrProduto").val(json.valor);
  				$("#obsProduto").val(json.obs);
  				$("#vlrUnit").val(json.valor);
   			}
  	 }).fail(function( xhr, status, errorThrown ){
  			// alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "Erro: carregar select Produto";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'ModalProdutoEdit'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );  
 			
  			
  	 }); 
  };


  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
  function cauculoVlrToral( qtyProduto, vlrUnitario ) {
 	 
 	 if( qtyProduto != null && qtyProduto != '' && qtyProduto.trim() != '' ){
 		 vlrUnitario = vlrUnitario.replace(/[^\d]+/g,'');
 		 vlrUnitario = vlrUnitario /100;
 		 vlrTotal = qtyProduto * vlrUnitario;
 		 const valorFormatado = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(vlrTotal);
 		 $("#vltTotal").val(valorFormatado );
 	 }
  } 



</script>
