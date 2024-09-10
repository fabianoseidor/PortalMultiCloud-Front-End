class MudancaPadrao{
	constructor(){
		// Informacoes da Categoria Padrao
		this.dadosCatPadrao      = {}; 
		this.clienteAfetado      = {}; 
		// Informacoes dos Itens que conterao na Categoria Padrao
		this.listaItemCatPadrao  = []
	}


	 async getDataMudanca() {
		const dataBase = new Date();
		const dias = 5;
		const dataCalc = new Date();
		
		function addDays(dataCalc, days) {
			dataCalc.setDate(dataCalc.getDate() + days);
			  return dataCalc;
		}
				
		const novaData = addDays(dataCalc, dias);
		
		let dtBase     = dataBase.toISOString();
		let dtNovaData = novaData.toISOString();
		
		let dtMudanca = {};
		dtMudanca.dtInicio  = dtBase.substr(0,10);
		dtMudanca.hrInicio  = dtBase.slsubstrice(11, 8);
		dtMudanca.hrFim     = dtBase.substr(11, 8);
		dtMudanca.dtFim     = dtNovaData.substr(0,10);;

		return dtMudanca;
	  
	 }

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/	
	async getDadosApi( urlBase, idCliebte, login, emailSolicitante, idContrato ){
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////		
		await this.getItemCategoriaPadrao( urlBase );		

		const dtMudanca = await this.getDataMudanca();

		let list_ClientesAfetados = [];
		for(let i = 0; i < this.clienteAfetado.length; i++){
		    let l_ClientesAfetados = { clientesAfetados: {id_clientes_af: this.clienteAfetado[i].id_clientes_af,
		                                                    nome_cliente: this.clienteAfetado[i].nome_cliente }
		                             };
            list_ClientesAfetados.push(l_ClientesAfetados);
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		let list_Tarefas = [];
		for(let i = 0; i < this.listaItemCatPadrao.length; i++){
			let l_Tarefas = {
	                  titulo_atividade_mudanca: this.listaItemCatPadrao[i].tituloCatPadrao                                ,
				      atividade_mudanca       : this.listaItemCatPadrao[i].descCatPadrao                                  ,
	                  dt_tarefa               : this.listaItemCatPadrao[i].dataTarefa                                     ,
				      duracao                 : this.listaItemCatPadrao[i].duracaoTarefa                                  ,
				      prioridade              : this.listaItemCatPadrao[i].prioridade                                     ,
				      enviar_email            : this.listaItemCatPadrao[i].enviar_email                                   ,
	                  responsavelAtividade    :{id_responsavel_atividade: this.listaItemCatPadrao[i].idResponsavelTarefa },
	                  statusAtividade         :{id_status_atividade     : '2'                                            }
			};
			list_Tarefas.push(l_Tarefas);
			
		}
		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		let list_ArquivosMudanca = [];
		
		var descricaoMudanca     = "GMUD criada automaticamente para a criação de novo Ambiente.";
		var justificativaMudanca = "GMUD criada devido ao 'Comissionamento' do cliente, " + list_ClientesAfetados[0].nome_cliente + ". O Contrato ID( " + idContrato + " ) foi criado.";

		////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		let dadostMudanca = {
			
		    titulo_mudanca         : 'GMUD DE COMISSIONAMENTO PARA NOVO CLIENTE "' + list_ClientesAfetados[0].nome_cliente + '" !', 
 	   	    login_user             : login                                                                                        ,
 	   	    email_solicitante      : emailSolicitante                                                                             ,
			
 	   	    criticidade            : {id_criticidade    : 2        ,
 	   	                                 criticidade    : 'MEDIA'} ,
										 
 	   	    impactoMudanca         : {id_impacto_mudanca: 2        ,
 	   	                                 impacto_mudanca: 'MEDIA' },
										 
 	   	    tipoMudanca            : {id_tipo_mudanca   : 3        ,
 	   	                                 tipo_mudanca   : 'PADRÃO'},
										 
 	   	    categoriaPadrao        : {id_categoria_padrao: this.dadosCatPadrao.id_categoria_padrao,
 	   	                                 categoria_padrao: this.dadosCatPadrao.categoria_padrao}  ,
										 
 	   	    mudancaClientesAfetados: list_ClientesAfetados ,
			
 	   	    arquivosMudanca        : list_ArquivosMudanca  ,
			
 	   	    atividadesMudanca      : list_Tarefas          ,
			
 	   	    dadosMudanca           : {dt_inicio            : dtMudanca.dtInicio  ,
 	   	                              hr_inicio            : dtMudanca.hrInicio  ,
 	   	                              dt_final             : dtMudanca.dtFim     ,
 	   	                              hr_final             : dtMudanca.hrFim     ,
 	   	                              dsc_mudanca          : descricaoMudanca    ,
 	   	                              justificativa_mudanca: justificativaMudanca
 	   	                             }                     ,
									 
 	   	    acaoPosAtividade        : {plano_teste: '', plano_rollback:''}                
 	   	};  


 	   	return dadostMudanca;     

	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/	
     async salvarGMUDPadrao( urlBase ){
		try {	
			let dados              = this.getDadosApi();
	    	let urlAPI             = urlBase + 'salvarMudancaPadrao';
			
			const postMudanca = await fetch(urlAPI, {
							        	        method : 'post',
							        	        headers: { 'Content-Type': 'application/json; charset=utf-8' },
							        	        body   : JSON.stringify( dados )
							        	   }).then(response => response.text())
							        	     .then(body => { return body; });
			
			if( postMudanca !== null )
				return "GMUD Padrao( "+ postMudanca.titulo_mudanca +" )cadastrada com sucesso!";
		} catch (error) {
			if (error.status == 500){
	            var answer   = error.responseText; //adiciona o que foi carregado a uma variável
	            var patients = JSON.parse(answer); //converte o que foi carregado para um objeto javascript
				let msnErro = 'Codego Erro: ' + patients.status + ' / Mensagem erro: ' + patients.message;				
				Swal.fire({
							icon  : "error"        ,
							title : patients.error ,
							text  : msnErro        ,
						  });
		    }else{
				Swal.fire({
				    icon  : "error"                             ,
				    title : "Salvar GMUD"                       ,
				    text  : "Erro ao salvar GMUD: " + error.responseText ,
				    }
				);
		    }
			MensagemConfimacao( "error", "Tarefas", "Erro ao Inicialização de Tarefas: " + error.responseText ) ;
		}
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	async getClientesAfetados( urlBase, idCliebte ){
	    let urlAPI  = urlBase + 'obterByIdClientesAfetadosPortal/' + idCliebte;
	    const l_ClienteRespons = await fetch(urlAPI).then(response => response.json());
		
		if(l_ClienteRespons.length ){
			this.clienteAfetado = this.salvarClienteAfet( l_ClienteRespons );
		}
	    return l_ResponsavelCliente;
	}
	
	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	salvarClienteAfet( l_ClienteRespons ){
		r_clienteAfetado      = {}; 
		r_clienteAfetado.id_clientes_af    = l_ClienteRespons.id_clientes_af;
		r_clienteAfetado.id_cliente_portal = l_ClienteRespons.id_cliente_portal;
		r_clienteAfetado.nome_cliente      = l_ClienteRespons.nome_cliente;
		
		return r_clienteAfetado;
	}

	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	async getItemCategoriaPadrao( urlBase ){

        let urlAPI  = urlBase + 'listaItemCategoriaPadrao/11';
			
		this.limpaClasse();
			
		$.ajax({
			method : "GET",
			url : urlAPI,
			contentType : "application/json; charset=utf-8",
			success : function(json) {
				for(var p = 0; p < json.length; p++){
					salvarItemCatPadrao( urlBase, json[p].id_itens_cat_padrao, json[p].tituloCatPadrao, json[p].descCatPadrao, json[p].prioridade, json[p].duracao );
				}
				this.dadosCatPadrao = lerDadosCategoriaPadrao( json[0].categoriaPadrao.id_categoria_padrao, json[0].categoriaPadrao.categoria_padrao );
			}
		}).fail(function(xhr ) {
			// alert("Erro ao Listar informacoes da Categoria Padrão: " + xhr.responseText);
			MensagemConfimacao( "error", "Listar informacoes da Categoria Padrão", "Erro ao Listar informacoes da Categoria Padrão: " + xhr.responseText) ;
		});		
	}
	
	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	limpaClasse(){
		this.dadosCatPadrao      = {}; 
		this.clienteAfetado      = {}; 
		// Informacoes dos Itens que conterao na Categoria Padrao
		this.listaItemCatPadrao.length = 0;		
	}
	 
	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	lerDadosCategoriaPadrao( idCategoriaPadrao, categoriaPadrao ){
		let dadosCategoriaPadrao = {};
		/* Dados de Identificação da Mudança */
		dadosCategoriaPadrao.id_categoria_padrao = idCategoriaPadrao;
	    dadosCategoriaPadrao.categoria_padrao    = categoriaPadrao
		
	    return dadosCategoriaPadrao;
	}

	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	salvarItemCatPadrao( urlBase, idItemCat, tituloCatPadrao, descCatPadrao, prioridade, duracao ){
		let itemCatPadrao = this.lerDadosItemCatPadrao( urlBase, idItemCat, tituloCatPadrao, descCatPadrao, prioridade, duracao );
		this.adicionarItemCatPadrao(itemCatPadrao);
	}

	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	adicionarItemCatPadrao(itemCatPadrao){
		this.listaItemCatPadrao.push(itemCatPadrao);
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	async getResponsavelTarefa( urlBase, idCliente ){
	    let urlAPI  = urlBase + 'obterByIdClientesAfetadosPortal/' + idCliente;
	    const l_ClienteRespons = await fetch(urlAPI).then(response => response.json());
		let r_Resp_Trarefa = {};
		if(l_ClienteRespons.length ){
		   
		   r_Resp_Trarefa.id_responsavel_atividade    = l_ClienteRespons.id_responsavel_atividade;
		   r_Resp_Trarefa.responsavel_atividade       = l_ClienteRespons.responsavel_atividade;
		   r_Resp_Trarefa.login_responsavel_atividade = l_ClienteRespons.login_responsavel_atividade;
		   r_Resp_Trarefa.email_responsavel_atividade = l_ClienteRespons.email_responsavel_atividade;
		   
		}
	    return r_Resp_Trarefa;
	}
	/******************************************************************/
	/*                                                                */
	/******************************************************************/
	async lerDadosItemCatPadrao( urlBase, idItemCat, tituloCatPadrao, descCatPadrao, prioridade, duracao ){
		let itemCatPadrao = {};
		itemCatPadrao.idItemCatPadrao = idItemCat      ;
		itemCatPadrao.tituloCatPadrao = tituloCatPadrao;
		itemCatPadrao.descCatPadrao   = descCatPadrao  ;
		itemCatPadrao.prioridade      = prioridade     ;		
		itemCatPadrao.duracao         = duracao        ;
		
		let r_Resp_Trarefa = await getResponsavelTarefa( urlBase, 7 );

		itemCatPadrao.idResponsavelTarefa   = r_Resp_Trarefa.id_responsavel_atividade   ;
		itemCatPadrao.responsavel_atividade = r_Resp_Trarefa.responsavel_atividade      ;
		itemCatPadrao.enviar_email          = r_Resp_Trarefa.email_responsavel_atividade;
		
		
		return itemCatPadrao;
	}
	
}	
	
	
	
	
	