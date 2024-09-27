
function atualizaVlrContratoBase( qtyParcelas ){
	let vlrContrato = document.getElementById("valor_total").value; 
	vlrContrato = vlrContrato.replace(".", "").replace(",", ".");
	let vlr = ( parseFloat( vlrContrato ) * qtyParcelas );
	const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
	document.getElementById("vlrContratoBase").value = formatado; 
}


async function formatDataStr( data ) {
    
	var dia = data.substr(0,2);
	var mes = data.substr(3,2);
	var ano = data.substr(6,4);
	var dtFort = ano + "-" + mes + "-" + dia;
	return dtFort;

}

async function calcular(){
	var dt1 = await formatDataStr( document.getElementById("dt_inicio").value ); 
	var dt2 = await formatDataStr( document.getElementById("dt_final" ).value );
	var idSetup      = document.getElementById("idSetup"  ).value; 
	
	if( ( dt1 != null && dt1 != '' && dt1.trim() != '' ) && 
	    ( dt2 != null && dt2 != '' && dt2.trim() != '' ) ){

		  var data1 = new Date(dt1); 
		  var data2 = new Date(new Date(dt2));
		  var total = (data2.getFullYear() - data1.getFullYear())*12 + (data2.getMonth() - data1.getMonth());
		  let vlrContrato = document.getElementById("valor_total").value; 
		  vlrConvet =  parseFloat( vlrContrato.replace(".", "").replace(",", ".") );
		  let vlrParcela = vlrConvet;//  total;
		  document.getElementById("qtyMesesContrato").value = total;			
		  document.getElementById("vlrParcelas"     ).value = vlrParcela.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
		  atualizaVlrContratoBase( total ); // Atualizar valor total base de cauclo na tela.
		  if(idSetup === "1" ){
			 
			if( total > 35 ){
				var vlr = vlrConvet; // total;
				const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
				document.getElementById("idValorSetup").value = formatado;
				document.getElementById("qtyParcSetup").value = "1";
			}else if( total < 36 ){
				var vlr = vlrConvet * 0.027;
				const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
				document.getElementById("idValorSetup").value = formatado;
				document.getElementById("qtyParcSetup").value = "1";				
			}
		  }else if(idSetup === "0" ){
			
			var data1 = new Date(dt1); 
			var data2 = new Date(new Date(dt2));
			var total = (data2.getFullYear() - data1.getFullYear())*12 + (data2.getMonth() - data1.getMonth());
			document.getElementById("qtyMesesContrato").value = total;
			
			let vlrContrato = document.getElementById("valor_total").value; 
			vlrContrato = vlrContrato.replace(".", "").replace(",", ".");

			let vlrConvet = parseFloat( vlrContrato )
			var vlr = (vlrConvet /*/ total*/) * 0.02;
			const formatado = vlr.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
			document.getElementById("idValorSetup").value = formatado;
			document.getElementById("qtyParcSetup").value = total;
		  }
	}
}

// In your Javascript (external .js resource or <script> tag)
$(document).ready(function() {
    $('#pep').select2( {
         theme: 'bootstrap-5'
    } );
    
    var pepEscondido = document.getElementById("pepEscondido").value;
    montaSelectPEP( pepEscondido );
//    montaSelectPEP(  );
    
});

function validaStatusPepProvisorio(){
 
   let idStatus  = select.options[id_status_contrato.selectedIndex ].value;
   let status      = $('#id_status_contrato').find(":selected").text( )       ;
   if( ((status !== 'Rascunho') && parseInt(idStatus) !== 4) || status.trim() === ''  ){
	   // PEP PROVISÓRIO
//	   $("#pep option[value='PEP PROVISÓRIO']").remove();
       const select = document.querySelector('#pep')
       for (let i = 0; i < select.options.length; i++) {
            const value = select.options[i].value;
            if (value === 'PEP PROVISÓRIO' ) {
                    select.remove(i);
                    $('#pep').get(0).selectedIndex = 0;
                    break;
            }
       }	   
   }else{
	  $('#pep').append('<option>PEP PROVISÓRIO</option>');
   }	
	
	
}

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 function montaSelectPEP( pep ){
    var urlAction = document.getElementById("formCadastroContrato").action;
	$.ajax({     			
		method : "get",
		url : urlAction,
		data : 'acao=montaSelectPEP',
		success: function(lista){
 //			var option = '<option disabled selected></option>';
            var option = '';
			var selected = '';
			let acheiPep = 0;
			var json = JSON.parse(lista);  
			
			for(var p = 0; p < json.length; p++){
 	 			if( json[p].pep === pep ){
					selected = 'selected';
					acheiPep = 1;
				}
 	 			 
 	 			else selected = '';
				option += '<option value=' + json[p].pep + ' ' + selected + '>' + json[p].pep + '</option>';
			}
			
			$("#pep").html(option).show(); 
			
			if( acheiPep === 0 ) $('#pep').val(null).trigger('change');
			
//			alert("PEP: " + document.getElementById("pep").value);
		}
	}).fail(function( xhr ){
		// alert('Erro ao buscar Cliente: ' + xhr.responseText);
		var iconi = "error";
		var tituloPrincipal = "Erro ao buscar PEP";
		var textoPrincipal = xhr.responseText;
		MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	});    	   
}


function carregaPEPSap( valor ) {
	
	 if( valor.length > 1 ){
         var urlAction      = document.getElementById("formCadastroContrato" ).action;
		 $.ajax({
	 			
	 			method : "get",
	 			url : urlAction,
	 			data : "pepSap=" + valor + '&acao=ListaPepSap',
	 			success: function(lista){
	 				var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		       var nomePEPlAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	
	 		        	    if( json[p].pep !== undefined  ){
	 		        	    	nomePEPlAux = json[p].pep;
	 		        	    } else nomePEPlAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosUser('" +  json[p].pep + "','" +  json[p].nome_cliente + "','" +  json[p].cnpj +"')\">" + nomePEPlAux + "</li>";
	 		               // alert(conteudoHTML);
	 		        	}
	 		        	
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do PHP
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhum PEP encontrado!</li>";
	 		        }
	 		        
	 		        // Fechar a lista de produtos 
	 		        conteudoHTML += "</ul>";

	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa').innerHTML = conteudoHTML;

	  			}
	 	 }).fail(function( xhr ){
 				var iconi = "error";
 				var tituloPrincipal = "Erro ao pesquisar PEP do SAP";
 				var textoPrincipal = xhr.responseText;
 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	 	 }); 
	 }else {
	        // Fechar a lista de produtos ou o erro
	        document.getElementById("resultado-pesquisa").innerHTML = "";
	 }
}


/*
//In your Javascript (external .js resource or <script> tag)
 $(document).ready(function() {
     $('.select2').select2();
 });

function carregaPEPSap( valor ) {
	
	 if( valor.length > 1 ){
         var urlAction      = document.getElementById("formCadastroContrato" ).action;
		 $.ajax({
	 			
	 			method : "get",
	 			url : urlAction,
	 			data : "pepSap=" + valor + '&acao=ListaPepSap',
	 			success: function(lista){
	 				var json = JSON.parse(lista);
	 			    // Abrir a lista de produtos
	 		        var conteudoHTML = "<ul class='list-group position-fixed'>";
	 		       var nomePEPlAux = '';
	 		        if( json !== 'VAZIO' ){
	 		        	for(var p = 0; p < json.length; p++){
	 		        	
	 		        	    if( json[p].pep !== undefined  ){
	 		        	    	nomePEPlAux = json[p].pep;
	 		        	    } else nomePEPlAux = '';
	 		                conteudoHTML += "<li class=\"list-group-item list-group-itemaction\" style=\"cursor: pointer;\" onclick=\"getDadosUser('" +  json[p].pep + "','" +  json[p].nome_cliente + "','" +  json[p].cnpj +"')\">" + nomePEPlAux + "</li>";
	 		               // alert(conteudoHTML);
	 		        	}
	 		        	
	 		        }else {
	 		            // Criar o item da lista com o erro retornado do PHP
	 		            conteudoHTML += "<li class='list-group-item disabled'>Erro: nenhum PEP encontrado!</li>";
	 		        }
	 		        
	 		        // Fechar a lista de produtos 
	 		        conteudoHTML += "</ul>";

	 		        // Enviar para o HTML a lista de produtos
	 		        document.getElementById('resultado-pesquisa').innerHTML = conteudoHTML;

	  			}
	 	 }).fail(function( xhr ){
 				var iconi = "error";
 				var tituloPrincipal = "Erro ao pesquisar PEP do SAP";
 				var textoPrincipal = xhr.responseText;
 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
	 	 }); 
	 }else {
	        // Fechar a lista de produtos ou o erro
	        document.getElementById("resultado-pesquisa").innerHTML = "";
	 }
}
 
 function getDadosUser( pep, nome_cliente, cnpj ) {
	    document.getElementById("pep").value = pep;

	    // Fechar a lista de pep ou o erro
	    document.getElementById("resultado-pesquisa").innerHTML = "";
 }   

*/

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$("#valor_total"     ).maskMoney({ showSymbol:true, symbol:""   , decimal:",", thousands:"." });
$("#valor_convertido").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#valor_Cotacao"   ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#vlrProduto"      ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#vlrUnit"         ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#vltTotal"        ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });
$("#idValorSetup"    ).maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"." });

  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
  function cauculoConversao() {

	 var valorTotal      = document.getElementById("valor_total"  ).value;
	 var valorCotacao    = document.getElementById("valor_Cotacao").value;
	 var valorConvertido = '';
	
	 if( ( valorTotal   != null && valorTotal   != '' && valorTotal.trim() != '' ) && 
		 ( valorCotacao != null && valorCotacao != '' && valorCotacao.trim() != '' ) ){
		 valorTotal = valorTotal.replace(/[^\d]+/g,'');
		 valorTotal = valorTotal /100;
		 
		 valorCotacao = valorCotacao.replace(/[^\d]+/g,'');
		 valorCotacao = valorCotacao /100;
		 
		 valorConvertido = valorTotal * valorCotacao;
		 const valorCalculado = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(valorConvertido);
		 $("#valor_convertido").val(valorCalculado ); 
	 }
  }  
  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/
$(document).ready( function(){
    //Esconde todos os passos e exibe o primeiro ao carregar a página 
    $('.step').hide();
    $('.step').first().show();
	
    //Exibe no topo em qual passo estamos pela ordem da div que esta visivel
    var passoexibido = function(){
        var index = parseInt($(".step:visible").index());
//        alert("index: " + index + " - Total: " + $(".step").length );
        let legendaPasso = '';
        if(index == 1){
            //Se for o primeiro passo desabilita o botão de voltar
            $("#prev").prop('disabled',true);
        }else if(index == (parseInt($(".step").length))){
            //Se for o ultimo passo desabilita o botão de avançar
            $("#next").prop('disabled',true);
        }else{
            //Em outras situações os dois serão habilitados
            $("#next").prop('disabled',false);            
            $("#prev").prop('disabled',false);
        }
        
        if( index === 1 ){
			legendaPasso = index + ' ( Informações do Contrato )';
		}else if( index === 2 ){
			legendaPasso = index + ' ( Informações da Vigência e Comissão )';
		}else if( index === 3 ){
			legendaPasso = index + ' ( Informações do Recurso )';
		}else if( index === 4 ){
			legendaPasso = index + ' ( Informações de Produto )';
		}else if( index === 5 ){
			legendaPasso = index + ' ( Revisão )';
		}
        
        $("#passo").html( legendaPasso );

    };
   
    //Executa a função ao carregar a página
    passoexibido();

    //avança para o próximo passo
    $("#next").click(function(){
		
		var index = parseInt($(".step:visible").index());
		
        if( index === 1 ){
			if(validaStep('.step_1_validar')){
			   contrato.salvarContrato();
               $(".step:visible").hide().next().show();
               passoexibido();
            }
		}else if( index === 2 ){
			if(validaStep('.step_2_validar')){
				contrato.salvarVigencia();
               $(".step:visible").hide().next().show();
               passoexibido();
            }
		}else if( index === 3 ){
			let validaRecurso = contrato.validaStepRecursoRenovacao();
			if(validaRecurso){
			   MensagemConfimacao( "warning", "Cadastro Recurso Renovação", validaRecurso );
			}
			
			else{
               $(".step:visible").hide().next().show();
               passoexibido();			
			}
			   
		}else{
           
		   if( index === 4 ) contrato.mostraResumoTela();	
           $(".step:visible").hide().next().show();
           passoexibido();			
		}
		
    });

    //retrocede para o passo anterior
    $("#prev").click(function(){
        $(".step:visible").hide().prev().show();
        passoexibido();
    });
	
} );


 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
  $(function () {
      $('#arqContratoPDF').change(function() {		
             $('.nomeArquivo').html('<span class="font-weight-bold font-italic" style="color: #708090">Arquivo Selecionado: ' + $(this).val() + '</span>' ) ;
      });
  }); 

  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/ 
  function habilitaCotacao() {
	
	var idMoeda              = document.getElementById("selectIdMoeda").value;
	var inputValorConvertido = document.querySelector("#valor_convertido");
	var inputValorCotacao    = document.querySelector("#valor_Cotacao");
	
	if(idMoeda === "1"){
		$("#valor_convertido").val('');
		$("#valor_Cotacao"   ).val('');
		inputValorConvertido.disabled = true;
		inputValorCotacao.disabled = true;
	}else{
		inputValorConvertido.disabled = false;
		inputValorCotacao.disabled = false;
	}	
  }
  
  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/ 
  function habilitaSetup() {

  	var idSetup      = document.getElementById("idSetup"  ).value;
  	var idValorSetup = document.querySelector("#idValorSetup");
  		
  	if(idSetup === "0"){
  		$("#idValorSetup").val('R$ 0,00');
  		idValorSetup.disabled = true;
  	}else idValorSetup.disabled = false;
  		
  }

  /******************************************************************/
  /*                                                                */
  /*                                                                */
  /******************************************************************/ 
  function habilitaStatusMotivoRascunho() {
	
	var idStatus            = document.getElementById("id_status_contrato"  ).value;
	var inputid_rascunho    = document.querySelector("#id_rascunho");
	var inputmotivoRascunho = document.querySelector("#motivoRascunho");
	if(idStatus === "4"){
		inputid_rascunho.disabled    = false;
		inputmotivoRascunho.disabled = false;
	}else{
		inputid_rascunho.disabled    = true;
		inputmotivoRascunho.disabled = true;
		$("#id_rascunho"   ).val('');
		$("#motivoRascunho").val('');
	}	
  }
 
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/ 
$('#modalClienteContrato').on('shown.bs.modal', function () {
	
	  let isRenovacao = document.getElementById("renovacaoContratoControle").value;
	  if( !isRenovacao  ){
		   PaginacaoInicialCliente( 0 );
	  }if( parseInt(isRenovacao) === 0 ){
		   PaginacaoInicialCliente( 0 );
	  }if( parseInt(isRenovacao) === 1 ){
	       PaginacaoClienteRenovacao( 0 );
	       let checkBox = document.getElementById("cbRenovacaoContrato");
           checkBox.checked = true;
	  }
	 
}) 

$('#modalClienteContrato').on('hidden.bs.modal', function (e) {
   if (document.getElementById("cbRenovacaoContrato").checked){
	   $("#renovacaoContratoControle" ).val( 1 );
   }else $("#renovacaoContratoControle" ).val( 0 );
})
 

function MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal ) {
// icon
//  	"warning"
//  	"error"
//  	"success"
//  	"info"

	  Swal.fire({
		    icon  : iconi                             ,
		    title : tituloPrincipal                   ,
		    text  : textoPrincipal                    ,
		    target: document.getElementById(nomeModal),
		    }
		);
	
}
function MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal ) {
// icon
//	  	"warning"
//	  	"error"
//	  	"success"
//	  	"info"

		  Swal.fire({
			    icon  : iconi                             ,
			    title : tituloPrincipal                   ,
			    text  : textoPrincipal                    ,
			    }
			);
		
	}

function AlerataMensagensModal( tituloPrincipal, textoPrincipal,  nomeModal ) {
	  Swal.fire({
		  title            : tituloPrincipal,
		  text             : textoPrincipal,
		  target           : document.getElementById( nomeModal ),
		  showDenyButton   : true,
//		  showCancelButton : true,
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
 function montaSelectSite(  ){
	   var idNuvem       = document.getElementById("id_nuvem"  ).value;
	   var idContCliente = document.getElementById("id_cliente").value; 

       if( idNuvem != null && idNuvem != '' && idNuvem.trim() != '' ){
    	   var urlAction = document.getElementById("formCadastroContrato").action;
      		$.ajax({     			
      			method : "get",
      			url : urlAction,
      			data : "idNuvem=" + idNuvem + '&acao=montaSelectSite&idContCliente='+idContCliente,
      			success: function(lista){
      				var option = '<option value="" disabled selected>Selecione Site</option>';
      				var selected = '';
      				var json = JSON.parse(lista);  
      				
      				for(var p = 0; p < json.length; p++){
      					option += '<option value=' + json[p].id_site + ' ' + selected + '>' + json[p].nome + '</option>';
      				}
      				
      				$("#id_site").html(option).show();  
      			}
      		}).fail(function( xhr ){
      			// alert('Erro ao buscar Cliente: ' + xhr.responseText);
 				var iconi = "error";
 				var tituloPrincipal = "Erro ao buscar Cliente";
 				var textoPrincipal = xhr.responseText;
 				MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  
      		});    	   
       }
}

function setnuvemRecurso(){
	const nuvemRec = $('#id_nuvem').find(":selected").text();
//	alert(nuvemRec);
	$("#nuvemRecurso" ).val( nuvemRec  );
	montaSelectFamiliaModal(  );
}



 function montaSelectFamiliaModal(  ) {

		var idNuvem = $("#id_nuvem option:selected").val();	
		var urlAction = document.getElementById("formCadastroContrato").action;
		 $.ajax({
				method : "get",
				url : urlAction,
				data : 'acao=montaSelectFamiliaModal&idNuvem=' + idNuvem,

				success: function(lista){
					    var option = '<option value="" disabled selected>[-Selecione-]</option>';
						var json = JSON.parse(lista);
						for(var p = 0; p < json.length; p++){
							option += '<option value=' + json[p].id_familia_flavors + '>' + json[p].familia + '</option>';
						}
						$("#selectFamiliaFlavors").html(option).show();  
				}
		 }).fail(function( xhr ){
				// alert('Erro carregar select Produto: ' + xhr.responseText);
				MensagemConfimacao( "error", 'Erro ao montar Select Familia!', xhr.responseText );
		 }); 		 
}

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 async function calculaDataFinalVigencia() {
	 
	 var idTempoContrato = selectTempoContrato.options[selectTempoContrato.selectedIndex].value;
	 var dtInicio        = document.getElementById("dt_inicio"   ).value;
	 var dtFinal         = document.getElementById("dt_final"    ).value;
	 var urlAction       = document.getElementById("formCadastroContrato").action;
 
	 $.ajax({ 			
  			method : "get",
  			url : urlAction,
			async: true,
  			data : 'acao=CalculaVigencia&idTempoContrato=' + idTempoContrato + '&dtInicio=' + dtInicio + '&dtFinal=' + dtFinal,
  			success: function(lista){
  				var json = JSON.parse(lista);
  				$("#dt_criacao_vigencia").val(json.dt_criacao);
  				$("#dt_inicio").val(json.dt_inicio);
  				$("#dt_final").val(json.dt_final);
				
				idSetup.disabled = false;
				calcular();

   			}
  	 }).fail(function( xhr ){
 // 			alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "ERRO";
 			var textoPrincipal  = "Erro: Calcula Vigencia: " + xhr.responseText;
 			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );  

  	 }); 
}

 /******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function ValidaNumeroPonto( obj , e )
 {
     var tecla = ( window.event ) ? e.keyCode : e.which;

     if ( tecla == 8 || tecla == 0 )
         return true;
     if ( tecla != 46 && tecla < 48 || tecla > 57 )
         return false;
 }


/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
jQuery(function($) {
	  $(document).on('keypress', 'input.only-number', function(e) {
	    var $this = $(this);
	    var key = (window.event)?event.keyCode:e.which;
	    var dataAcceptDot = $this.data('accept-dot');
	    var dataAcceptComma = $this.data('accept-comma');
	    var acceptDot = (typeof dataAcceptDot !== 'undefined' && (dataAcceptDot == true || dataAcceptDot == 1)?true:false);
	    var acceptComma = (typeof dataAcceptComma !== 'undefined' && (dataAcceptComma == true || dataAcceptComma == 1)?true:false);

		if((key > 47 && key < 58)
	      || (key == 46 && acceptDot)
	      || (key == 44 && acceptComma)) {
	    	return true;
	  	} else {
	 			return (key == 8 || key == 0)?true:false;
	 		}
	  });
});
	

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
$(document).ready(function () {

    $('#selectFamiliaFlavors').change(function () {

        var es        = document.getElementById('selectFamiliaFlavors');
        idFamiliaFl   = es.options[es.selectedIndex].value;
        var urlAction = document.getElementById("formCadastroContrato").action;
        
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
  		}).fail(function( xhr ){
  			//alert('Erro ao buscar valores campo Família Flavors: ' + xhr.responseText);
  			MensagemConfimacao( "error", 'Erro ao buscar valores para o campo Família Flavors!', xhr.responseText );
  		}); 
    });
});


 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 var select = document.getElementById('selectProduto');

 select.addEventListener('change', function(){
	
    carregaVlrSelect( select.value );
//    MensagemConfimacao( "info", "TESTE", select.value );	
    if (select.options[select.selectedIndex].value != "" ){
        var inputQty      = document.querySelector("#idQty");
        var inputvlrUnit  = document.querySelector("#vlrUnit");
        var inputvltTotal = document.querySelector("#vltTotal");
        inputQty.disabled      = false;
        inputvlrUnit.disabled  = false;
        inputvltTotal.disabled = false;
    }
 });

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function carregaVlrSelect( idProduto ){
	 var urlAction = document.getElementById("formCadastroContrato").action;

	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : "idProduto=" + idProduto + '&acao=MostraValoresSelectProdudo',
  			success: function(lista){
				
  				var json = JSON.parse(lista);
  				$("#vlrProduto").val(json.valor);
  				$("#obsProduto").val(json.obs  );
  				$("#vlrUnit"   ).val(json.valor);
   			}
  	 }).fail(function( xhr ){
  			// alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "Erro: carregar select Produto";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'ModalProduto'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );  
  	 }); 
  };
  
 const qtyProdutoVlrTotal = document.getElementById('idQty');

 qtyProdutoVlrTotal.addEventListener('blur', function () {

    var vlrUnitCal     = document.getElementById("vlrUnit").value;
    var qty = qtyProdutoVlrTotal.value;
    cauculoVlrToral( qty, vlrUnitCal );
 });

 /******************************************************************/
 /*                                                                */
 /*                                                                */
 /******************************************************************/
 const vlrProdutoInput = document.getElementById('vlrProduto');
 vlrProdutoInput.addEventListener('blur', function () {
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
 function cauculoVlrToral( qtyProduto, vlrUnitario ) {
	 
	 if( qtyProduto != null && qtyProduto != '' && qtyProduto.trim() != '' ){
		 vlrUnitario = vlrUnitario.replace(/[^\d]+/g,'');
		 vlrUnitario = vlrUnitario /100;
		 vlrTotal = qtyProduto * vlrUnitario;
		 const valorFormatado = Intl.NumberFormat('pt-br', {style: 'currency', currency: 'BRL'}).format(vlrTotal);
		 $("#vltTotal").val(valorFormatado );
	 }
 } 
  
 
getProduto(); 
 function getProduto(){
	 var urlAction = document.getElementById("formCadastroContrato").action;
	 	
	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : '&acao=montaSelectProdudo',

  			success: function(lista){
  				var option = '<option value="" disabled selected>Selecione Produto</option>';
  				var json = JSON.parse(lista);
  				
  				for(var p = 0; p < json.length; p++){
  					option += '<option value=' + json[p].id_produto + '>' + json[p].produto + '</option>';
  				}
  				$("#selectProduto").html(option).show();  
  			}
  	 }).fail(function( xhr ){
  			// alert('Erro carregar select Produto: ' + xhr.responseText);
			var iconi           = "error";
 			var tituloPrincipal = "Erro carregar select Produto";
 			var textoPrincipal  = xhr.responseText;  
 			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal );	
  	 }); 	
 }
 
 
 function validaStep( stepValidate ){
	let valid = true;
	let formulario = document.querySelector('.formularioCadContrato');
	
//	let formulario = document.getElementById("formCadastroContrato");

	for( let errorText of formulario.querySelectorAll('.error-text') ){
		 errorText.remove();
	}
	
	for( let campo of formulario.querySelectorAll(stepValidate) ){
		 const label = campo.previousElementSibling.innerText;
		 if(!campo.value){
			criaErro(campo, `O campo "${label}" não pode esta vazio!`);
			valid = false;
		 }
	}
	return valid;
 }
 
 function criaErro(campo, msg){
	const div = document.createElement('div');
	div.innerHTML = msg;
	div.classList.add('error-text');
	campo.insertAdjacentElement("afterend", div);
 }
 
geragenerateCodeTagVcenter(); 
function geragenerateCodeTagVcenter() {

		var urlAction = document.getElementById("formCadastroContrato").action;

	 	 $.ajax({
	  			method : "get",
	  			url : urlAction,
	  			data : 'acao=generateCodeTagVcenter',
	  			
	  			success: function(lista){
	   				var json = JSON.parse(lista);
	   				$("#tag_vcenter").val( json );
	  			}
	 	        
	  	 }).fail(function( xhr ){
	  			// alert('Erro ao gerar o codigo TagVcenter: ' + xhr.responseText);
	  			MensagemConfimacaoModal( "error", 'Erro ao gerar o codigo Tag Vcenter!', xhr.responseText, 'ModalRecurso' );
	  	}); 
	  	
}	
 
 function atualizaPaginacao(){
	if (document.getElementById("cbRenovacaoContrato").checked) PaginacaoClienteRenovacao( 0 );
	else PaginacaoInicialCliente( 0 );
 } 

 function tipoPesquisaCadastroRenovacaoNovo(){
	if (document.getElementById("cbRenovacaoContrato").checked) buscarClieneRenovacao();
	else buscarCliene();
 } 


/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function buscarClieneRenovacao() {

	 var nomeBuscaCliente = document.getElementById("nomeBuscaCliente").value;
	 
	 if( nomeBuscaCliente != null && nomeBuscaCliente != '' && nomeBuscaCliente.trim() != '' ){
		 var urlAction = document.getElementById("formCadastroContrato").action;
		 
   		$.ajax({
  			
  			method : "get",
  			   url : urlAction,
  			  data : "nomeBuscaCliente=" + nomeBuscaCliente + '&acao=buscarClienteAjaxRenovacao',
  			success: function(response){
  			
  				var json = JSON.parse(response);
  				
  				$('#tabelaResutado > tbody > tr').remove();
  				
  				for(var p = 0; p < json.length; p++){
  					$('#tabelaResutado > tbody').append('<tr> <td>' + (p+1) + '</td> <td>'+json[p].id_cliente+'</td> <td>'+json[p].razao_social+'</td> <td><button onclick="verClienteSelecionadoRenovacao('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-info">Selecionar</button></td></tr>');
  																																								   
  				}
  				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
  			}
  			
  		}).fail(function( xhr ){
  			//alert('Erro ao buscar Cliente: ' + xhr.responseText);
 			var iconi           = "error";
 			var tituloPrincipal = "Erro ao buscar Cliente";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'modalClienteContrato'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	
  			
  		});		 
	 }
 }

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
 function buscarCliene() {

	 var nomeBuscaCliente = document.getElementById("nomeBuscaCliente").value;
	 
	 if( nomeBuscaCliente != null && nomeBuscaCliente != '' && nomeBuscaCliente.trim() != '' ){
		 var urlAction = document.getElementById("formCadastroContrato").action;
		 
   		$.ajax({
  			
  			method : "get",
  			   url : urlAction,
  			  data : "nomeBuscaCliente=" + nomeBuscaCliente + '&acao=buscarClienteCadAjax',
  			success: function(response){
  			
  				var json = JSON.parse(response);
  				
  				$('#tabelaResutado > tbody > tr').remove();
  				
  				for(var p = 0; p < json.length; p++){
  					$('#tabelaResutado > tbody').append('<tr> <td>' + (p+1) + '</td> <td>'+json[p].id_cliente+'</td> <td>'+json[p].razao_social+'</td> <td><button onclick="verClienteSelecionado('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-info">Selecionar</button></td></tr>');
  																																								   
  				}
  				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
  			}
  			
  		}).fail(function( xhr ){
  			//alert('Erro ao buscar Cliente: ' + xhr.responseText);
 			var iconi           = "error";
 			var tituloPrincipal = "Erro ao buscar Cliente";
 			var textoPrincipal  = xhr.responseText;  
 			var nomeModal       = 'modalClienteContrato'; 
 			MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal );	
  			
  		});		 
	 }
 }
 
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/ 
 function PaginacaoClienteRenovacao( pag ) {
	 var urlAction = document.getElementById("formCadastroContrato").action;

	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : 'acao=PaginacaoClienteRenovacao&pag=' + pag,
  			success: function(lista){
  				var json = JSON.parse( lista );
  				var totalPag = 0;
  				
   				$('#tabelaResutado > tbody > tr').remove();
   				
   				for(var p = 0; p < json.length; p++){
   					$('#tabelaResutado > tbody').append(
   							            '<tr>  <td>' + json[p].id_cliente   + ' </td> '
   							               + ' <td>' + json[p].razao_social + ' </td> '
   							               + ' <td>' + json[p].alias        + ' </td> '
   							               + ' <td>'
//   							               +     '<button onclick="verClienteSelecionadoRenovacao('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-outline-success" data-dismiss="modal">Selecionar</button>'
   							               +     '<button onclick="verClienteSelecionadoRenovacao('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-outline-success">Selecionar</button>'
   							               + '</td>'
   							           +'</tr>'); 
   					totalPag = json[p].totalPagCli;
   				}
   				
   				$('#navegacaoPag > ul').remove();
   				var liPaginacao = '<ul class="pagination">';
   				for(var p = 0; p < totalPag; p++){
   					  if( pag === p )
   					      liPaginacao +=  "<li class=\"page-item active\" onclick=\"PaginacaoClienteRenovacao(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
   					  else  
   						liPaginacao +=  "<li class=\"page-item\" onclick=\"PaginacaoClienteRenovacao(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
   				}
   				
   				liPaginacao += "</ul>";
   				
   				$("#navegacaoPag").html(liPaginacao).show(); 
   				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
   			}
  	 }).fail(function( xhr ){
  			alert('Erro buscando quantitade de paginacao de clietes: ' + xhr.responseText);
  	 }); 

} 
  
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/ 
 function PaginacaoInicialCliente( pag ) {
	 var urlAction = document.getElementById("formCadastroContrato").action;

	 $.ajax({
  			method : "get",
  			url : urlAction,
  			data : 'acao=paginarPesquisaCliente&pag=' + pag,
  			success: function(lista){
  				var json = JSON.parse( lista );
  				var totalPag = 0;
  				
   				$('#tabelaResutado > tbody > tr').remove();
   				
   				for(var p = 0; p < json.length; p++){
   					$('#tabelaResutado > tbody').append(
   							            '<tr>  <td>' + json[p].id_cliente   + ' </td> '
   							               + ' <td>' + json[p].razao_social + ' </td> '
   							               + ' <td>' + json[p].alias        + ' </td> '
   							               + ' <td>'
   							               +     '<button onclick="verClienteSelecionado('+json[p].id_cliente+',' + '\'' + json[p].razao_social+ '\''+');" type="button" class="btn btn-outline-success" data-dismiss="modal">Selecionar</button>'
   							               + '</td>'
   							           +'</tr>'); 
   					totalPag = json[p].totalPagCli;
   				}
   				
   				$('#navegacaoPag > ul').remove();
   				var liPaginacao = '<ul class="pagination">';
   				for(var p = 0; p < totalPag; p++){
   					  if( pag === p )
   					      liPaginacao +=  "<li class=\"page-item active\" onclick=\"PaginacaoInicialCliente(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
   					  else  
   						liPaginacao +=  "<li class=\"page-item\" onclick=\"PaginacaoInicialCliente(" + p + " ); \"><a class=\"page-link\" href=\"#\">" + ( p + 1 ) + "</a></li>";
   				}
   				
   				liPaginacao += "</ul>";
   				
   				$("#navegacaoPag").html(liPaginacao).show(); 
   				document.getElementById("totalResutados").textContent = 'Resutado: ' + json.length + ' cliente(s) encontrado(s)';
   			}
  	 }).fail(function( xhr ){
  			alert('Erro buscando quantitade de paginacao de clietes: ' + xhr.responseText);
  	 }); 

} 

/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function verClienteSelecionado( idCliente, razaoSocial ) {	

   $("#id_cliente" ).val(idCliente  );
   $("#nomeCliente").val(razaoSocial);
   	
   $('#modalClienteContrato').modal('hide');	
}


/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/

function showClienteSelecionadoRenovacao( idCliente, nomeCliente, idFaseContrato, idCicloUpdadate, idServicoContratado, pep, idHubSpot, termoAdmin, termoDownload ) {	

   $("#id_cliente"            ).val( idCliente           );
   $("#nomeCliente"           ).val( nomeCliente         );
   
   $("#id_fase_contrato"      ).val( idFaseContrato      );
   $("#id_ciclo_updadate"     ).val( idCicloUpdadate     );
   $("#id_servico_contratado" ).val( idServicoContratado );
//   $("#pep"                   ).val( pep                 );
   $("#pepEscondido"          ).val( pep                 );
   $("#id_hub_spot"           ).val( idHubSpot           );
   $("#termo_admin"           ).val( termoAdmin          );
   $("#termo_download"        ).val( termoDownload       );
   	
//   $('#modalClienteContrato').modal('hide');	
    var pepEscondido = document.getElementById("pepEscondido").value;
    montaSelectPEP( pepEscondido );
}
 
/******************************************************************/
/*                                                                */
/*                                                                */
/******************************************************************/
function verClienteSelecionadoRenovacao( idCliente, razaoSocial ){
 	 var urlAction  = document.getElementById("formCadastroContrato").action;
 	// var idContrato = document.getElementById("pequisaContrato").value;
	let iconi           = "success";
	let tituloPrincipal = "Renovação de Contrato";
	let textoPrincipal  = "Deseja carregar o WORKLOAD atual para o novo contrato?";
	let nomeModal       = "modalClienteContrato";

 	if( idCliente != null && idCliente != '' ){
		
        Swal.fire({
		       icon             : iconi                               ,
		 	   title            : tituloPrincipal                     ,
			   text             : textoPrincipal                      ,
			   target           : document.getElementById( nomeModal ),
			   showDenyButton   : true                                ,
			   confirmButtonText: "Sim"                               ,
			   denyButtonText   : "Não"
	    }).then((result) => {
			 if (result.isConfirmed) {						

		         $.ajax({
	 			         method : "get",
	 			         url    : urlAction,
	 			         data   : "idCliente=" + idCliente + '&acao=buscarContratoClienteRenovacao',
	 			         success: function(lista){
	 				                 var json = JSON.parse(lista);
                                     
	                                 if(json !== "NAOTEMCONTRATOATIVO"){
										showClienteSelecionadoRenovacao( idCliente  , razaoSocial        , json[0].id_fase_contrato, json[0].id_ciclo_update, json[0].id_servico_contratado, 
										                                 json[0].pep, json[0].id_hub_spot, json[0].termo_admin     , json[0].termo_download );
										contrato.limpaListaRecurso();
										$("#id_contrato" ).val( json[0].id_contrato );
						                for(var p = 0; p < json.length; p++){
	                	                  contrato.lerDadosRecusoRenovacao(json[p].id_retencao_backup, json[p].desc_retencao_backup, json[p].id_tipo_disco    , json[p].desc_tipo_disco  , json[p].id_so             , json[p].desc_so     , 
                                                                           json[p].id_ambiente       , json[p].desc_ambiente       , json[p].id_tipo_servico  , json[p].desc_tipo_servico, json[p].hostname          , json[p].primary_ip  , 
                                                                           json[p].tamanho_disco     , json[p].host_Vcenter        , json[p].eip_Vcenter      , json[p].tag_vcenter      , json[p].id_familia_flavors, json[p].desc_familia );
	                	                }
	                                 }else{
	                                     MensagemConfimacaoModal( "info", "Renovação de Contrato", 'O Cliente "'+ razaoSocial + '" não possui contrato Ativo', "modalClienteContrato" ); 
	                                 }
	 			         }
	 			
	 	         }).fail(function( xhr ){
 			             iconi           = "error";
 				         tituloPrincipal = "Erro: Renovação de Contrato";
 				         textoPrincipal  = xhr.responseText;
 				         nomeModal       = "modalClienteContrato";
 				         MensagemConfimacaoModal( iconi, tituloPrincipal, textoPrincipal, nomeModal ); 
	 	         }); 
		     } else 
		         if (result.isDenied) {
					 showClienteSelecionadoRenovacao( idCliente  , razaoSocial, "", "", "", "", "", "", "" );
		             return false;
		         }
		     });	
    }
 }
