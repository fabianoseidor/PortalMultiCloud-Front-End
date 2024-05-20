class Contrato{
	constructor(){
		// Variaveis para uso das informcacoes de Recurso
		this.idRecurso     = 1;
		this.listRecursos  = [];
		this.editId        = null;
		// Variaveis para uso das informcacoes de Produto
		this.idProduto     = 1;
		this.listProduto   = [];
		this.editIdProduto = null;
		// Variaveis para uso das informcacoes do Contrato
		this.dadosContrato = {};
		// Variaveis para uso das informcacoes da Vigencia
		this.dadosVigencia = {};

	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	salvarRecuso(){
		 let recurso = this.lerDadosRecuso();
		 if( validaStep( '.step_3_validar' ) )	{
			if( this.editId === null ){
		        this.adicionaRecuso(recurso);
		    }else{
				this.atualizarRecurso( this.editId );
			}
		 }	
		 this.montaTabelaRecurso( this.listRecursos );
		 this.LimparRecurso();
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	adicionaRecuso( recurso ){		
		this.listRecursos.push(recurso);
		this.idRecurso++;		
	} 
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
   // Monta a tablela
   	montaTabelaRecurso(){
		let tbody = document.getElementById('Tbody');
		tbody.innerText = '';
		
		for(let i = 0; i < this.listRecursos.length; i++){
			// Cria as linhas
            let tr = tbody.insertRow();
            // Crias as celulas
            let td_tipo_servico   = tr.insertCell();
            let td_status_rec     = tr.insertCell();
            let td_hostname       = tr.insertCell();
            let td_primaryIP      = tr.insertCell();
            let td_ambiente       = tr.insertCell();
            let td_familiaFlavors = tr.insertCell();
            let td_nuvemRecurso   = tr.insertCell();
            let td_site           = tr.insertCell();
            let td_editar         = tr.insertCell();
            let td_delete         = tr.insertCell();
            let teste;
             // Inseri os valores do objeto nas celulas
            td_tipo_servico.innerText   = ( this.listRecursos[i].tipo_servico   !== undefined ? this.listRecursos[i].tipo_servico   : '' );
            td_status_rec.innerText     = ( this.listRecursos[i].status_rec     !== undefined ? this.listRecursos[i].status_rec     : '' );
            td_hostname.innerText       = ( this.listRecursos[i].hostname       !== undefined ? this.listRecursos[i].hostname       : '' );
            td_primaryIP.innerText      = ( this.listRecursos[i].primaryIP      !== undefined ? this.listRecursos[i].primaryIP      : '' );
            td_ambiente.innerText       = ( this.listRecursos[i].ambiente       !== undefined ? this.listRecursos[i].ambiente       : '' );
            td_familiaFlavors.innerText = ( this.listRecursos[i].familiaFlavors !== undefined ? this.listRecursos[i].familiaFlavors : '' );
            td_nuvemRecurso.innerText   = ( this.listRecursos[i].nuvemRecurso   !== undefined ? this.listRecursos[i].nuvemRecurso   : '' );
            td_site.innerText           = ( this.listRecursos[i].site           !== undefined ? this.listRecursos[i].site           : '' );

            let imgDelete = document.createElement('img');
            imgDelete.src = this.getContextPath() +'/imagens/delete-40.png';
            imgDelete.setAttribute('onclick','contrato.delete( ' + this.listRecursos[i].idRecurso + ' )');
            td_delete.appendChild(imgDelete);
             
            let imgEdit = document.createElement('img');
            imgEdit.src = this.getContextPath() +'/imagens/edit-40.png';
            imgEdit.setAttribute('onclick','contrato.preparaEdit( ' + JSON.stringify(this.listRecursos[i]) + ' )');
            td_editar.appendChild(imgEdit);
		}
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	 
    getContextPath() {
       return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    preparaEdit( dados ){
		this.editId = dados.idRecurso;

		$('#id_status_recurso'       ).get(0).selectedIndex = dados.id_status_recurso;
		$('#id_retencao_backup'      ).get(0).selectedIndex = dados.id_retencao_backup;
		$('#id_tipo_disco'           ).get(0).selectedIndex = dados.id_tipo_disco;
		$('#id_so'                   ).get(0).selectedIndex = dados.id_so;
		$('#id_ambiente'             ).get(0).selectedIndex = dados.id_ambiente;
		$('#id_tipo_servico'         ).get(0).selectedIndex = dados.id_tipo_servico;
		$("#selectFamiliaFlavors"     ).val( dados.selectFamiliaFlavors);
				
		$("#hostnameModalRecurso"    ).val( dados.hostname      );
		$("#tamanhoDiscoModalRecurso").val( dados.tamanhoDisco  );
		$("#primaryIPModalRecurso"   ).val( dados.primaryIP     );
		$("#hostVcenterModalRecurso" ).val( dados.hostVcenter   );
		$("#eipVcenterModalRecurso"  ).val( dados.eipVcenter    );
		$("#tag_vcenter"             ).val( dados.tag_vcenter   );

		$("#cpu"                     ).val( dados.cpu   );
		$("#ram"                     ).val( dados.ram   );
		$("#valor"                   ).val( dados.valor );
		$("#obs"                     ).val( dados.obs   );
		
		document.getElementById('btAddRecurso').innerText = 'Atualizar';
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	atualizarRecurso( id ){
		
	    for(let i = 0; i < this.listRecursos.length; i++){
		    if( this.listRecursos[i].idRecurso === id ){
				this.listRecursos[i].id_status_recurso    = document.getElementById("id_status_recurso"       ).value;
				this.listRecursos[i].status_rec           = $('#id_status_recurso').find(":selected").text( )        ;
			    this.listRecursos[i].id_retencao_backup   = document.getElementById("id_retencao_backup"      ).value;
				this.listRecursos[i].retencao_backup      = $('#id_retencao_backup').find(":selected").text( )       ;
				this.listRecursos[i].id_tipo_disco        = document.getElementById("id_tipo_disco"           ).value;
				this.listRecursos[i].tipo_disco           = $('#id_tipo_disco').find(":selected").text( )            ;
				this.listRecursos[i].id_so                = document.getElementById("id_so"                   ).value;
				this.listRecursos[i].so                   = $('#id_so').find(":selected").text( )                    ;
				this.listRecursos[i].id_ambiente          = document.getElementById("id_ambiente"             ).value;
				this.listRecursos[i].ambiente             = $('#id_ambiente').find(":selected").text( )              ;
				this.listRecursos[i].id_tipo_servico      = document.getElementById("id_tipo_servico"         ).value;
				this.listRecursos[i].tipo_servico         = $('#id_tipo_servico').find(":selected").text( )          ;
				this.listRecursos[i].hostname             = document.getElementById("hostnameModalRecurso"    ).value;
				this.listRecursos[i].primaryIP            = document.getElementById("primaryIPModalRecurso"   ).value;
				this.listRecursos[i].tamanhoDisco         = document.getElementById("tamanhoDiscoModalRecurso").value;
				this.listRecursos[i].hostVcenter          = document.getElementById("hostVcenterModalRecurso" ).value;
				this.listRecursos[i].eipVcenter           = document.getElementById("eipVcenterModalRecurso"  ).value;
				this.listRecursos[i].tag_vcenter          = document.getElementById("tag_vcenter"             ).value;
				this.listRecursos[i].id_nuvem             = document.getElementById("id_nuvem"                ).value;
				this.listRecursos[i].nuvemRecurso         = document.getElementById("nuvemRecurso"            ).value;
				this.listRecursos[i].id_site              = document.getElementById("id_site"                 ).value;
				this.listRecursos[i].site                 = $('#id_site').find(":selected").text( )                  ;
				this.listRecursos[i].selectFamiliaFlavors = document.getElementById("selectFamiliaFlavors"    ).value;
				this.listRecursos[i].familiaFlavors       = $('#selectFamiliaFlavors').find(":selected").text( )     ;
				this.listRecursos[i].cpu                  = document.getElementById("cpu"                     ).value;
				this.listRecursos[i].ram                  = document.getElementById("ram"                     ).value;
				this.listRecursos[i].valor                = document.getElementById("valor"                   ).value;
				this.listRecursos[i].obs                  = document.getElementById("obs"                     ).value;
				
				// Atualiza informcoes
				document.getElementById('btAddRecurso').innerText = 'Add';
				this.editId = null;
			    break;
		    }
	    }		
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    delete ( id ){
		
	    Swal.fire({
		     title: "Deletar",
		     text: "Deseja realmente excluir o Recurso da lista de Cadastro?",
		     icon: "warning",
		     showCancelButton   : true,
		     confirmButtonColor : "#3085d6",
		     cancelButtonColor  : "#d33",
		     cancelButtonText   : "Cancelar",
		     confirmButtonText  : "Exclui"
		}).then((result) => {
		  if (result.isConfirmed) {
			  let tbody = document.getElementById('Tbody');
			  for(let i = 0; i < this.listRecursos.length; i++){
				  if( this.listRecursos[i].idRecurso === id ){
					  this.listRecursos.splice(i, 1);
					  tbody.deleteRow(i);
					  break;
				  }
			  }
		  }
		});
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	lerDadosRecuso( ){
		let recurso = {};
		
		recurso.idRecurso            = this.idRecurso;
		recurso.id_status_recurso    = document.getElementById("id_status_recurso"       ).value;
		recurso.status_rec           = $('#id_status_recurso').find(":selected").text( )        ;
	    recurso.id_retencao_backup   = document.getElementById("id_retencao_backup"      ).value;
		recurso.retencao_backup      = $('#id_retencao_backup').find(":selected").text( )       ;
		recurso.id_tipo_disco        = document.getElementById("id_tipo_disco"           ).value;
		recurso.tipo_disco           = $('#id_tipo_disco').find(":selected").text( )            ;
		recurso.id_so                = document.getElementById("id_so"                   ).value;
		recurso.so                   = $('#id_so').find(":selected").text( )                    ;
		recurso.id_ambiente          = document.getElementById("id_ambiente"             ).value;
		recurso.ambiente             = $('#id_ambiente').find(":selected").text( )              ;
		recurso.id_tipo_servico      = document.getElementById("id_tipo_servico"         ).value;
		recurso.tipo_servico         = $('#id_tipo_servico').find(":selected").text( )          ;
		recurso.hostname             = document.getElementById("hostnameModalRecurso"    ).value;
		recurso.primaryIP            = document.getElementById("primaryIPModalRecurso"   ).value;
		recurso.tamanhoDisco         = document.getElementById("tamanhoDiscoModalRecurso").value;
		recurso.hostVcenter          = document.getElementById("hostVcenterModalRecurso" ).value;
		recurso.eipVcenter           = document.getElementById("eipVcenterModalRecurso"  ).value;
		recurso.tag_vcenter          = document.getElementById("tag_vcenter"             ).value;
		recurso.id_nuvem             = document.getElementById("id_nuvem"                ).value;
		recurso.nuvemRecurso         = document.getElementById("nuvemRecurso"            ).value;
		recurso.id_site              = document.getElementById("id_site"                 ).value;
		recurso.site                 = $('#id_site').find(":selected").text( )                  ;
		recurso.selectFamiliaFlavors = document.getElementById("selectFamiliaFlavors"    ).value;
		recurso.familiaFlavors       = $('#selectFamiliaFlavors').find(":selected").text( )     ;
		recurso.cpu                  = document.getElementById("cpu"                     ).value;
		recurso.ram                  = document.getElementById("ram"                     ).value;
		recurso.valor                = document.getElementById("valor"                   ).value;
		recurso.obs                  = document.getElementById("obs"                     ).value;
		recurso.renovacao            = 0;
		return recurso;
	}
	
	limpaListaRecurso(){
		this.listRecursos = [];
		this.idRecurso = 1;
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	lerDadosRecusoRenovacao( id_retencao_backup, desc_retencao_backup, id_tipo_disco    , desc_tipo_disco  , id_so             , desc_so     , 
                             id_ambiente       , desc_ambiente       , id_tipo_servico  , desc_tipo_servico, hostname          , primary_ip  , 
                             tamanho_disco     , host_Vcenter        , eip_Vcenter      , tag_vcenter      , id_familia_flavors, desc_familia ){
		let recurso = {};
		
		recurso.idRecurso            = this.idRecurso;
	    recurso.id_retencao_backup   = id_retencao_backup;
		recurso.retencao_backup      = desc_retencao_backup;
		recurso.id_tipo_disco        = id_tipo_disco;
		recurso.tipo_disco           = desc_tipo_disco;
		recurso.id_so                = id_so;
		recurso.so                   = desc_so;
		recurso.id_ambiente          = id_ambiente;
		recurso.ambiente             = desc_ambiente;
		recurso.id_tipo_servico      = id_tipo_servico;
		recurso.tipo_servico         = desc_tipo_servico;
		recurso.hostname             = hostname;
		recurso.primaryIP            = primary_ip;
		recurso.tamanhoDisco         = tamanho_disco;
		recurso.hostVcenter          = host_Vcenter;
		recurso.eipVcenter           = eip_Vcenter;
		recurso.tag_vcenter          = tag_vcenter;
		recurso.selectFamiliaFlavors = id_familia_flavors;
		recurso.familiaFlavors       = desc_familia;
		recurso.renovacao            = 1;
		//////////////////////////////////////////////////////
		/*   Adiciona o recurso do contrato em renovacao    */
		//////////////////////////////////////////////////////
        this.adicionaRecuso(recurso);
		this.montaTabelaRecurso( this.listRecursos );
	}
	
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	LimparRecurso() {

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
		$("#obs"                     ).val("");		
		$("#hostnameModalRecurso"    ).val("");
		$("#tamanhoDiscoModalRecurso").val("");
		$("#primaryIPModalRecurso"   ).val("");
		$("#hostVcenterModalRecurso" ).val("");
		$("#eipVcenterModalRecurso"  ).val("");
		
		var urlAction = document.getElementById("formCadastroContrato").action;
		
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
	  			MensagemConfimacao( "error", 'Erro ao gerar o codigo Tag Vcenter!', xhr.responseText );
	  	}); 
    }
    
    
    /**************************************************************************************/
    /*                                                                                    */
    /*        Trata as informacoes referente aos produtos                                 */
    /*                                                                                    */
    /**************************************************************************************/
    
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	salvarProdutos(){
	  let produto = this.lerDadosProdutos();
	    if( validaStep( '.step_4_validar' ) )	{
	        if( this.editIdProduto === null ){
			    this.adicionaProdutos( produto );
		    }else{
			  this.atualizarProdutos( this.editIdProduto );
		    }
	    }
	  	this.montaTabelaProdutos( this.listProduto );
		this.LimparProdutos();
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    adicionaProdutos( recurso ){
		this.listProduto.push(recurso);
		this.idProduto++;	
	}	

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    montaTabelaProdutos(){
		let tbody = document.getElementById('TbodyProduto');
		tbody.innerText = '';
		for(let i = 0; i < this.listProduto.length; i++){
			// Cria as linhas
            let tr = tbody.insertRow();
            
            // Crias as celulas
            let td_produto      = tr.insertCell();
            let td_quantidade   = tr.insertCell();
            let td_vlr_unitario = tr.insertCell();
            let td_vlr_total    = tr.insertCell();
            let td_editar       = tr.insertCell();
            let td_delete       = tr.insertCell();

            // Inseri os valores do objeto nas celulas
            td_produto.innerText      = ( this.listProduto[i].produto  !== undefined ? this.listProduto[i].produto  : '' );
            td_quantidade.innerText   = ( this.listProduto[i].idQty    !== undefined ? this.listProduto[i].idQty    : '' );
            td_vlr_unitario.innerText = ( this.listProduto[i].vlrUnit  !== undefined ? this.listProduto[i].vlrUnit  : '' );
            td_vlr_total.innerText    = ( this.listProduto[i].vltTotal !== undefined ? this.listProduto[i].vltTotal : '' );

 
            let imgDelete = document.createElement('img');
            imgDelete.src = this.getContextPath() +'/imagens/delete-40.png';
            imgDelete.setAttribute('onclick','contrato.deleteProdutos( ' + this.listProduto[i].idProduto + ' )');
            td_delete.appendChild(imgDelete);
             
            let imgEdit = document.createElement('img');
            imgEdit.src = this.getContextPath() +'/imagens/edit-40.png';
            imgEdit.setAttribute('onclick','contrato.preparaEditProdutos( ' + JSON.stringify(this.listProduto[i]) + ' )');
            td_editar.appendChild(imgEdit);
		}
		
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	preparaEditProdutos( dados ){
		this.editIdProduto = dados.idProduto;

		$("#selectProduto").val( dados.idProdutoBD);
		$("#vlrProduto"   ).val( dados.vlrProduto );		
		$("#obsProduto"   ).val( dados.obsProduto );		
		$("#idQty"        ).val( dados.idQty      );
		$("#vlrUnit"      ).val( dados.vlrUnit    );
		$("#vltTotal"     ).val( dados.vltTotal   );
				
		document.getElementById('btAddProduto').innerText = 'Atualizar';	
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	atualizarProdutos( id ){
	    for(let i = 0; i < this.listProduto.length; i++){
		    if( this.listProduto[i].idProduto === id ){
				
		        this.listProduto[i].idProdutoBD = select.options[selectProduto.selectedIndex].value;
		        this.listProduto[i].produto     = $('#selectProduto').find(":selected").text( );
		        this.listProduto[i].vlrProduto  = document.getElementById("vlrProduto").value;
		        this.listProduto[i].obsProduto  = document.getElementById("obsProduto").value;
			    this.listProduto[i].idQty       = document.getElementById("idQty"     ).value;
			    this.listProduto[i].vlrUnit     = document.getElementById("vlrUnit"   ).value;
			    this.listProduto[i].vltTotal    = document.getElementById("vltTotal"  ).value;
				
				// Atualiza informcoes
				document.getElementById('btAddProduto').innerText = 'Add';
				this.editIdProduto = null;
			    break;
		    }
	    }		
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	deleteProdutos ( id ){
	    Swal.fire({
		     title: "Deletar",
		     text: "Deseja realmente excluir o Produto da lista de Cadastro?",
		     icon: "warning",
		     showCancelButton   : true,
		     confirmButtonColor : "#3085d6",
		     cancelButtonColor  : "#d33",
		     cancelButtonText   : "Cancelar",
		     confirmButtonText  : "Exclui"
		}).then((result) => {
		  if (result.isConfirmed) {
			  let tbody = document.getElementById('TbodyProduto');
			  for(let i = 0; i < this.listProduto.length; i++){
				  if( this.listProduto[i].idProduto === id ){
					  this.listProduto.splice(i, 1);
					  tbody.deleteRow(i);
					  break;
				  }
			  }
		  }
		});
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	LimparProdutos() {
		$('#selectProduto').get(0).selectedIndex = 0;
		$("#vlrProduto"   ).val("");
		$("#obsProduto"   ).val("");
		$("#idQty"        ).val("");
		$("#vlrUnit"      ).val("");		
		$("#vltTotal"     ).val("");
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    lerDadosProdutos(){
		let produto = {};
		produto.idProduto   = this.idProduto;
        produto.idProdutoBD = select.options[selectProduto.selectedIndex].value;
        produto.produto     = $('#selectProduto').find(":selected").text( );
        produto.vlrProduto  = document.getElementById("vlrProduto").value;
        produto.obsProduto  = document.getElementById("obsProduto").value;
	    produto.idQty       = document.getElementById("idQty"     ).value;
	    produto.vlrUnit     = document.getElementById("vlrUnit"   ).value;
	    produto.vltTotal    = document.getElementById("vltTotal"  ).value;
	    
	    return produto;
    }
    
    
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    salvarContrato(){
    	this.dadosContrato = this.lerDadosContrato();
    //	console.log( this.dadosContrato );  	
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/	
    lerDadosContrato(){
	    let contrato = {};
	    // input	   
		contrato.id_cliente             = document.getElementById("id_cliente"            ).value; 
		contrato.nomeCliente            = document.getElementById("nomeCliente"           ).value;
		contrato.valor_total            = document.getElementById("valor_total"           ).value;
		contrato.valor_Cotacao          = document.getElementById("valor_Cotacao"         ).value;
		contrato.valor_convertido       = document.getElementById("valor_convertido"      ).value;
		contrato.arqContratoPDF         = document.getElementById("arqContratoPDF"        ).value;
		contrato.pep                    = document.getElementById("pep"                   ).value;
		contrato.id_hub_spot            = document.getElementById("id_hub_spot"           ).value;
		contrato.qty_usuario_contratada = document.getElementById("qty_usuario_contratada").value;
		// select                                   
		contrato.id_moeda               = select.options[id_moeda.selectedIndex           ].value;
		contrato.moeda                  = $('#id_moeda').find(":selected").text( )          ;
		contrato.id_status_contrato     = select.options[id_status_contrato.selectedIndex ].value;
		contrato.status_contrato        = $('#id_status_contrato').find(":selected").text( )       ;
		
		contrato.id_rascunho            = select.options[id_rascunho.selectedIndex        ].value;
		contrato.rascunho               = $('#id_rascunho').find(":selected").text( )       ;
		contrato.motivoRascunho         = document.getElementById("motivoRascunho"        ).value;
		
		contrato.id_nuvem               = select.options[id_nuvem.selectedIndex           ].value;
		contrato.nuvem                  = $('#id_nuvem').find(":selected").text( )                 ;
		contrato.id_site                = select.options[id_site.selectedIndex            ].value;
		contrato.site                   = $('#id_site').find(":selected").text( )                  ;
		contrato.id_fase_contrato       = select.options[id_fase_contrato.selectedIndex   ].value;
		contrato.fase_contrato          = $('#id_fase_contrato').find(":selected").text( );
		contrato.id_ciclo_updadate      = document.getElementById("id_ciclo_updadate").value; 
		contrato.ciclo_updadate         = $('#id_ciclo_updadate').find(":selected").text( );
		
		contrato.id_suporte_b1          = document.getElementById("id_suporte_b1").value; 
		contrato.nome_suporte           = $('#id_suporte_b1').find(":selected").text( );
		contrato.id_comercial           = document.getElementById("id_comercial").value; 
		contrato.nome_comercial         = $('#id_comercial').find(":selected").text( );

		contrato.id_servico_contratado  = document.getElementById("id_servico_contratado").value;		
		contrato.servico_contratado     = $('#id_servico_contratado').find(":selected").text( )    ;
		contrato.termo_admin            = select.options[termo_admin.selectedIndex          ].value;
		contrato.termo_adminShow        = $('#termo_admin').find(":selected").text( )              ;
		contrato.termo_download         = select.options[termo_download.selectedIndex       ].value;
		contrato.termo_downloadShow     = $('#termo_download').find(":selected").text( )            ; 
		// textarea                                    
		contrato.observacao             = document.getElementById("observacao").value;
		contrato.isRenovacao            = document.getElementById("renovacaoContratoControle").value;
		contrato.id_contrato            = document.getElementById("id_contrato").value; 
		
		return contrato;
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
    salvarVigencia(){
    	this.dadosVigencia = this.lerDadosVigencia(); 
    //	console.log( this.dadosVigencia );  	
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	lerDadosVigencia(){
		let vigencia = {};
		
		vigencia.selectTempoContrato = document.getElementById("selectTempoContrato").value;
		vigencia.tempoContrato       = $('#selectTempoContrato').find(":selected").text( ) ; 
		vigencia.dt_inicio           = document.getElementById("dt_inicio"          ).value;
		vigencia.dt_final            = document.getElementById("dt_final"           ).value;
		vigencia.observacaoVigencia  = document.getElementById("observacaoVigencia" ).value;	
		return vigencia;
	}
	
	mostraResumoTela(){

		this.mostrarResumoContrato();
		this.mostrarResumoVigencia();
		this.montaResumoRecurso();
		this.montaResumoProduto();
        // Monta campo Escpndito
	    let v_dadosContrato = JSON.stringify(this.dadosContrato);
	    $("#conteudoContrato").val( v_dadosContrato);
	    
		let v_dadosVigencia = JSON.stringify(this.dadosVigencia);
		$("#conteudoVigencia").val( v_dadosVigencia);
		
		let v_listRecursos  = JSON.stringify(this.listRecursos );
		$("#conteudoRecurso").val( v_listRecursos);
		
		let v_listProduto   = JSON.stringify(this.listProduto  );
		$("#conteudoProduto").val( v_listProduto);
	}
	
	mostrarResumoContrato(){
		
		let tbody = document.getElementById('TbodyResumoContratoCad');
		tbody.innerText = '';
		
		let tr = tbody.insertRow();
		
        let td_idClienteShow         = tr.insertCell();
        let td_nomeClienteShow       = tr.insertCell();
        let td_moedaShow             = tr.insertCell();
        let td_vlrToralShow          = tr.insertCell();
        let td_vlrCotacaoShow        = tr.insertCell();
        let td_vlrTConvertidoShow    = tr.insertCell();
        let td_StatusShow            = tr.insertCell();
        let td_motivoRascunho        = tr.insertCell();
        let td_rascunho              = tr.insertCell();
        let td_nuvemShow             = tr.insertCell();
        let td_siteShow              = tr.insertCell();
        let td_faseContratoShow      = tr.insertCell();
        let td_nomeComercialShow     = tr.insertCell();
        let td_nomeSuporteB1Show     = tr.insertCell();
        let td_cicloUpdadateShow     = tr.insertCell();
        let td_servicoContratadoShow = tr.insertCell();
        let td_termoAdminShow        = tr.insertCell();
        let td_termoDownloadShow     = tr.insertCell();
        let td_arqContratoShow       = tr.insertCell();
        let td_pepShow               = tr.insertCell();
        let td_idHubSpotShow         = tr.insertCell();
        let td_qtyUsuarioShow        = tr.insertCell();
        let td_observacaoShow        = tr.insertCell();

        td_idClienteShow.innerText         = ( this.dadosContrato.id_cliente  !== undefined ? this.dadosContrato.id_cliente  : '' );
        td_nomeClienteShow.innerText       = ( this.dadosContrato.nomeCliente !== undefined ? this.dadosContrato.nomeCliente : '' );         
        td_moedaShow.innerText             = ( this.dadosContrato.moeda       !== undefined ? this.dadosContrato.moeda       : '' );
        td_vlrToralShow.innerText          = ( this.dadosContrato.valor_total !== undefined ? this.dadosContrato.valor_total : '' );
        
        if(this.dadosContrato.valor_Cotacao === ''){
		   td_vlrCotacaoShow.innerText   = ' - ';
		   td_vlrCotacaoShow.classList.add('center');
		} else td_vlrCotacaoShow.innerText = this.dadosContrato.valor_Cotacao;
		if(this.dadosContrato.valor_convertido === '') { 
		   td_vlrTConvertidoShow.innerText   = ' - ';
		   td_vlrTConvertidoShow.classList.add('center');
		} else td_vlrTConvertidoShow.innerText = this.dadosContrato.valor_convertido;	

        td_StatusShow.innerText            = ( this.dadosContrato.status_contrato        !== undefined ? this.dadosContrato.status_contrato        : '' );
        td_nuvemShow.innerText             = ( this.dadosContrato.nuvem                  !== undefined ? this.dadosContrato.nuvem                  : '' );
        td_rascunho.innerText              = ( this.dadosContrato.rascunho               !== undefined && this.dadosContrato.rascunho !== "[-Selecione-]" ? this.dadosContrato.rascunho: '' );
        td_motivoRascunho.innerText        = ( this.dadosContrato.motivoRascunho         !== undefined ? this.dadosContrato.motivoRascunho         : '' );
        td_siteShow.innerText              = ( this.dadosContrato.site                   !== undefined ? this.dadosContrato.site                   : '' );
        
        if(this.dadosContrato.nome_suporte === ''){
		   td_nomeSuporteB1Show.innerText   = ' - ';
		   td_nomeSuporteB1Show.classList.add('center');
		} else td_nomeSuporteB1Show.innerText = this.dadosContrato.nome_suporte;
		
		if(this.dadosContrato.nome_comercial === '') { 
		   td_nomeComercialShow.innerText   = ' - ';
		   td_nomeComercialShow.classList.add('center');
		} else td_nomeComercialShow.innerText = this.dadosContrato.nome_comercial;	
        
        td_faseContratoShow.innerText      = ( this.dadosContrato.fase_contrato          !== undefined ? this.dadosContrato.fase_contrato          : '' );
        td_cicloUpdadateShow.innerText     = ( this.dadosContrato.ciclo_updadate         !== undefined ? this.dadosContrato.ciclo_updadate         : '' );
        td_servicoContratadoShow.innerText = ( this.dadosContrato.servico_contratado     !== undefined ? this.dadosContrato.servico_contratado     : '' );
        td_termoAdminShow.innerText        = ( this.dadosContrato.termo_adminShow        !== undefined ? this.dadosContrato.termo_adminShow        : '' );
        td_termoDownloadShow.innerText     = ( this.dadosContrato.termo_downloadShow     !== undefined ? this.dadosContrato.termo_downloadShow     : '' );
        td_arqContratoShow.innerText       = ( this.dadosContrato.arqContratoPDF         !== undefined ? this.dadosContrato.arqContratoPDF         : '' );
        td_pepShow.innerText               = ( this.dadosContrato.pep                    !== undefined ? this.dadosContrato.pep                    : '' );
        td_idHubSpotShow.innerText         = ( this.dadosContrato.id_hub_spot            !== undefined ? this.dadosContrato.id_hub_spot            : '' );
        td_qtyUsuarioShow.innerText        = ( this.dadosContrato.qty_usuario_contratada !== undefined ? this.dadosContrato.qty_usuario_contratada : '' );
        td_observacaoShow.innerText        = ( this.dadosContrato.observacao             !== undefined ? this.dadosContrato.observacao             : '' );
        
        td_idClienteShow.classList.add('center');
        td_cicloUpdadateShow.classList.add('center');
        
        td_termoAdminShow.classList.add('center');
        td_termoDownloadShow.classList.add('center');
        td_qtyUsuarioShow.classList.add('center');
        
	}
	
	
	mostrarResumoVigencia(){
		document.getElementById("tempoContratoShow"     ).textContent = ( this.dadosVigencia.tempoContrato      !== undefined ? this.dadosVigencia.tempoContrato      : '' );
		document.getElementById("dataInicioShow"        ).textContent = ( this.dadosVigencia.dt_inicio          !== undefined ? this.dadosVigencia.dt_inicio          : '' );
		document.getElementById("dataFinalShow"         ).textContent = ( this.dadosVigencia.dt_final           !== undefined ? this.dadosVigencia.dt_final           : '' );
		document.getElementById("observacaoVigenciaShow").textContent = ( this.dadosVigencia.observacaoVigencia !== undefined ? this.dadosVigencia.observacaoVigencia : '' );
	}

   	montaResumoRecurso(){
		let tbody = document.getElementById('TbodyResumoRecurso');
		tbody.innerText = '';
		for(let i = 0; i < this.listRecursos.length; i++){
			// Cria as linhas
            let tr = tbody.insertRow();
            // Crias as celulas
            let td_status          = tr.insertCell();
            let td_retencao_backup = tr.insertCell();
            let td_tipo_disco      = tr.insertCell();
            let td_so              = tr.insertCell();
            let td_ambiente        = tr.insertCell();
            let td_tipo_servico    = tr.insertCell();
            let td_hostname        = tr.insertCell();
            let td_primaryIP       = tr.insertCell();
            let td_tamanhoDisco    = tr.insertCell();
            let td_hostVcenter     = tr.insertCell();
            let td_eipVcenter      = tr.insertCell();
            let td_tag_vcenter     = tr.insertCell();
            let td_nuvemRecurso    = tr.insertCell();
            let td_site            = tr.insertCell();
            let td_familiaFlavors  = tr.insertCell();
            let td_cpu             = tr.insertCell();
            let td_ram             = tr.insertCell();
            let td_valor           = tr.insertCell();
            let td_obs             = tr.insertCell();

            // Inseri os valores do objeto nas celulas
            td_status.innerText          = ( this.listRecursos[i].status_rec      !== undefined ? this.listRecursos[i].status_rec     : '' );
            td_retencao_backup.innerText = ( this.listRecursos[i].retencao_backup !== undefined ? this.listRecursos[i].retencao_backup: '' );
            td_tipo_disco.innerText      = ( this.listRecursos[i].tipo_disco      !== undefined ? this.listRecursos[i].tipo_disco     : '' );
            td_so.innerText              = ( this.listRecursos[i].so              !== undefined ? this.listRecursos[i].so             : '' );
            td_ambiente.innerText        = ( this.listRecursos[i].ambiente        !== undefined ? this.listRecursos[i].ambiente       : '' );
            td_tipo_servico.innerText    = ( this.listRecursos[i].tipo_servico    !== undefined ? this.listRecursos[i].tipo_servico   : '' );
            td_hostname.innerText        = ( this.listRecursos[i].hostname        !== undefined ? this.listRecursos[i].hostname       : '' );
            td_primaryIP.innerText       = ( this.listRecursos[i].primaryIP       !== undefined ? this.listRecursos[i].primaryIP      : '' );
            td_tamanhoDisco.innerText    = ( this.listRecursos[i].tamanhoDisco    !== undefined ? this.listRecursos[i].tamanhoDisco   : '' );
            td_hostVcenter.innerText     = ( this.listRecursos[i].hostVcenter     !== undefined ? this.listRecursos[i].hostVcenter    : '' );
            td_eipVcenter.innerText      = ( this.listRecursos[i].eipVcenter      !== undefined ? this.listRecursos[i].eipVcenter     : '' );
            td_tag_vcenter.innerText     = ( this.listRecursos[i].tag_vcenter     !== undefined ? this.listRecursos[i].tag_vcenter    : '' );
            td_nuvemRecurso.innerText    = ( this.listRecursos[i].nuvemRecurso    !== undefined ? this.listRecursos[i].nuvemRecurso   : '' );
            td_site.innerText            = ( this.listRecursos[i].site            !== undefined ? this.listRecursos[i].site           : '' );
            td_familiaFlavors.innerText  = ( this.listRecursos[i].familiaFlavors  !== undefined ? this.listRecursos[i].familiaFlavors : '' );
            td_cpu.innerText             = ( this.listRecursos[i].cpu             !== undefined ? this.listRecursos[i].cpu            : '' );
            td_ram.innerText             = ( this.listRecursos[i].ram             !== undefined ? this.listRecursos[i].ram            : '' );
            td_valor.innerText           = ( this.listRecursos[i].valor           !== undefined ? this.listRecursos[i].valor          : '' );
            td_obs.innerText             = ( this.listRecursos[i].obs             !== undefined ? this.listRecursos[i].obs            : '' );
            
            td_retencao_backup.classList.add('center');
            td_tamanhoDisco.classList.add   ('center');
            td_cpu.classList.add            ('center');
            td_ram.classList.add            ('center');

		}
	}

   	montaResumoProduto(){
		let tbody = document.getElementById('TbodyResumoProduto');
		tbody.innerText = '';
		for(let i = 0; i < this.listProduto.length; i++){
			// Cria as linhas
            let tr = tbody.insertRow();
            
            // Crias as celulas
            let td_produto      = tr.insertCell();
            let td_quantidade   = tr.insertCell();
            let td_vlr_unitario = tr.insertCell();
            let td_vlr_total    = tr.insertCell();
            let td_obs          = tr.insertCell();

            // Inseri os valores do objeto nas celulas
            td_produto.innerText      = ( this.listProduto[i].produto    !== undefined ? this.listProduto[i].produto    : '' );
            td_quantidade.innerText   = ( this.listProduto[i].idQty      !== undefined ? this.listProduto[i].idQty      : '' );
            td_vlr_unitario.innerText = ( this.listProduto[i].vlrUnit    !== undefined ? this.listProduto[i].vlrUnit    : '' );
            td_vlr_total.innerText    = ( this.listProduto[i].vltTotal   !== undefined ? this.listProduto[i].vltTotal   : '' );
            td_obs.innerText          = ( this.listProduto[i].obsProduto !== undefined ? this.listProduto[i].obsProduto : '' );
		}
	}
	
	
	/******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
     gravarContrato() {
		 let v_dadosContrato = JSON.stringify(this.dadosContrato);
		 let v_dadosVigencia = JSON.stringify(this.dadosVigencia);
		 let v_listRecursos	 = JSON.stringify(this.listRecursos );
		 let v_listProduto   = JSON.stringify(this.listProduto  );
		 let v_arqContratoPDF = document.getElementById("arqContratoPDF").value
		 let urlAction = document.getElementById("formCadastroContrato").action;
		 alert("v_arqContratoPDF: " + v_arqContratoPDF);

		 var iconi          = 'success';
		 var iconiErro      = "error";
		 var textoPrincipal = "Contrato para o Cliente, '" + document.getElementById("nomeCliente").value + "', realizado com Sucesso!";
         var titulo         = 'Cadastro';
 
  
		 var dados = 'acao=AddContrato' +
		             '&listRecursos='   + v_listRecursos  + // 1
		             '&listProduto='    + v_listProduto   + // 2
		             '&dadosContrato='  + v_dadosContrato + // 3
		             '&dadosVigencia='  + v_dadosVigencia + // 4
		             '&arqContratoPDF=' + v_arqContratoPDF;
   		$.ajax({
  			method : "get",
//  			method : "post",
  			   url : urlAction,
  			  data : dados,
  			success: function(response){
  			    
  				var json = JSON.parse(response);
  				if( json === 'OK' ) MensagemConfimacao( iconi, titulo, textoPrincipal ); 
                else MensagemConfimacao( iconiErro, titulo, json ); 
  			}
  			
  		}).fail(function( xhr ){
  			//alert('Erro ao buscar Cliente: ' + xhr.responseText);
 			var iconi           = "error";
 			var tituloPrincipal = "Erro ao Cadastrar Contrato";
 			var textoPrincipal  = xhr.responseText;  
 			MensagemConfimacao( iconi, tituloPrincipal, textoPrincipal ); 			
  		});		 
	 
    }
    
    validaStepRecursoRenovacao(){
		let camposError = '';
		let isErro      = 0;
		for(let i = 0; i < this.listRecursos.length; i++){
			  if( !this.listRecursos[i].id_status_recurso ){
				camposError += 'Preencher o Campo "Status"!' + "\n";
				isErro = 1;
			  }
			  if( !this.listRecursos[i].id_retencao_backup ){
				camposError += 'Preencher o Campo "Retencao Backup"!' + "\n";
				isErro = 1;
			  }
			  if( !this.listRecursos[i].id_tipo_disco){
				camposError += 'Preencher o Campo "Tipo Disco"!' + "\n";
				isErro = 1;
			  }
			  if( !this.listRecursos[i].id_so ){
				camposError += 'Preencher o Campo "SO"!' + "\n";
				isErro = 1;
			  }
			  if( !this.listRecursos[i].id_ambiente  ){
				camposError += 'Preencher o Campo "Ambiente"!' + "\n";
				isErro = 1;
			  }
			  if( !this.listRecursos[i].id_tipo_servico){
				camposError += 'Preencher o Campo "Tipo Servico"!' + "\n";
				isErro = 1;
			  }
			  /*
			  if( !this.listRecursos[i].id_nuvem ){
				camposError += 'Preencher o Campo "Nuvem"!' + "\n";
				isErro = 1;
			  }*/
			  if( !this.listRecursos[i].selectFamiliaFlavors){
				camposError += 'o Preencher o Campo "Familia Flavors"!' + "\n";
				isErro = 1;
			  }
			if(isErro){
				return 'Favor revisar o Recurso: "' + this.listRecursos[i].hostname + '"' + "\n" + camposError;
			}  
	    }
	   return false; 
    }

}

var contrato = new Contrato();

