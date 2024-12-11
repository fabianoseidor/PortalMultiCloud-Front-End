package br.com.portal.servlets;

import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lacunasoftware.pkiexpress.AlphaCode;

import br.com.portal.dao.DAOAditivoModalRecurso;
import br.com.portal.dao.DAOCadastroContrato;
import br.com.portal.dao.DAOContratoRepository;
import br.com.portal.dao.DAOFamiliaFlavors;
import br.com.portal.dao.DAORecusoContrato;
import br.com.portal.dao.DAOSite;
import br.com.portal.dao.DAOUtil;
import br.com.portal.model.ModelAitivoRecursoModal;
import br.com.portal.model.ModelCliente;
import br.com.portal.model.ModelContrato;
import br.com.portal.model.ModelContratoProduto;
import br.com.portal.model.ModelDesativacaoContrato;
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelListaRecursoContratoAditivo;
import br.com.portal.model.ModelPepSap;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelRecursoContrato;
import br.com.portal.model.ModelRenovacaoContrato;
import br.com.portal.model.ModelSite;
import br.com.portal.model.ModelTempoContrato;
import br.com.portal.model.ModelVigenciaContrato;
import br.com.portal.servise.CriarMudancaComissionamento;


@MultipartConfig
public class ServletCadastroContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Integer OFFSET_END    = 20;
	private Integer offsetBegin                = 0;
	private DAOContratoRepository daoContratoRepository = new DAOContratoRepository();	
//	private DAOClienteRepository   daoClienteRepository = new DAOClienteRepository();
       
    public ServletCadastroContrato() {}

    /******************************************************************/
    /*                                                                */
    /*                                                                */
    /******************************************************************/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	   		String acao = request.getParameter("acao");
			
	   		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaPepSap") ) {
				String pepSap = request.getParameter("pepSap");
				DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
				List<ModelPepSap> listPepSap = daoCadastroContrato.getListaPepSap(pepSap);
				Gson gson = new Gson();
				String lista = "";
				if( listPepSap.size() > 0 ) {
  			        lista = gson.toJson(listPepSap);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectPEP") ) {
					
		    	 DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
						
				 List<ModelPepSap> listPepSap  = daoCadastroContrato.getListaPepSapSelect2( );
				 Gson gson = new Gson();
				 String lista = gson.toJson( listPepSap );
				 response.getWriter().write(lista);
					
			 }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteCadAjax") ) {
				
				DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
				String nomeBusca = request.getParameter("nomeBuscaCliente");
				List<ModelCliente> dadosJsonUser = daoCadastroContrato.buscarListaClienteNomeCadastro(nomeBusca.trim());
				
				ObjectMapper objectMapper = new ObjectMapper();
				
		        try {
		          String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dadosJsonUser);
		          //System.out.println(json);
		          response.getWriter().write(json);
		        } catch(Exception e) {
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
					request.setAttribute("msg", e.getMessage());
					requestDispatcher.forward(request, response);
		       }
		       
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteAjaxRenovacao") ) {
				
				String nomeBusca = request.getParameter("nomeBuscaCliente");
				DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
				List<ModelCliente> dadosJsonUser = daoCadastroContrato.buscarListaClienteNomeRenovacao( nomeBusca );
				
				ObjectMapper objectMapper = new ObjectMapper();
				
		        try {
		          String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dadosJsonUser);
		          //System.out.println(json);
		          response.getWriter().write(json);
		        } catch(Exception e) {
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
					request.setAttribute("msg", e.getMessage());
					requestDispatcher.forward(request, response);
		       }
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectSite") ) {
				String idNuvem = request.getParameter("idNuvem");
				Long idContCliente = (request.getParameter("idContCliente") != null && !request.getParameter("idContCliente").isEmpty() ? Long.parseLong(request.getParameter("idContCliente")): 0L);
				
				if( idNuvem != null && !idNuvem.isEmpty() ) {

					List<ModelSite> modelSites = new ArrayList<ModelSite>();
					DAOSite daoSite = new DAOSite();
					
					modelSites = daoSite.listaSiteSelect( Long.parseLong(idNuvem),  idContCliente );
					Gson gson = new Gson();
					String lista = gson.toJson(modelSites);
					response.getWriter().write(lista);
				}
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectFamiliaModal") ) {
				   String idNuvem = request.getParameter("idNuvem");
				   if( idNuvem != null && !idNuvem.isEmpty() ){
					   DAOFamiliaFlavors beanFamilia = new DAOFamiliaFlavors();
					   List<ModelFamiliaFlavors> familiaFlavorses = beanFamilia.listaFamiliaFlavors( Long.parseLong( idNuvem.trim() ) );
					   Gson gson = new Gson();
					   String lista = gson.toJson(familiaFlavorses);
					   response.getWriter().write(lista);
				   }
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("CalculaVigencia") ) {

		   		String idTempoContrato = request.getParameter("idTempoContrato");
		   		if( idTempoContrato != null && !idTempoContrato.isEmpty() ) {
		   			
		   			String dtInicio = request.getParameter("dtInicio");
		   			// String dtFinal  = request.getParameter("dtFinal");
		   			LocalDate dataInicial = LocalDate.now();
		   			LocalDate datafinal   = LocalDate.now();
		   			if( dtInicio != null && !dtInicio.isEmpty() ) {
		   				dataInicial = LocalDate.parse(dtInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		   			}
		   			ModelVigenciaContrato vigenciaContrato = new ModelVigenciaContrato();
		   			ModelTempoContrato tempoContrato = daoContratoRepository.getTempoContrato( Long.parseLong(idTempoContrato) );
		   			DAOUtil util = new DAOUtil();

		   			//     public LocalDate getDtFinal( String tipoTime, LocalDate dataBase, int tempo )
					if( tempoContrato.getTempo_ano() > 0 )
						datafinal = util.getDtFinal("A", dataInicial, tempoContrato.getTempo_ano() );
					else if( tempoContrato.getTempo_meses() > 0 )
						datafinal = util.getDtFinal("M", dataInicial, tempoContrato.getTempo_meses() );
					else datafinal = util.getDtFinal("S", dataInicial, tempoContrato.getTempo_semana() );
					
					vigenciaContrato.setId_tempo_contrato( Long.parseLong(idTempoContrato) );
			   		vigenciaContrato.setDt_criacao( LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) );
			   		vigenciaContrato.setDt_inicio( dataInicial.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") ) );
			   		vigenciaContrato.setDt_final(datafinal.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") )  );
			   		
				    Gson gson = new Gson();
				    String lista = gson.toJson(vigenciaContrato);
				    response.getWriter().write(lista);
		   		}
		   		
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaCamosFamiliaFl") ) {
				String idFamiliaFl = request.getParameter("idFamiliaFl");
				
				if( idFamiliaFl != null && !idFamiliaFl.isEmpty() ) {

					ModelFamiliaFlavors mdFamilias = new ModelFamiliaFlavors();
					DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
					mdFamilias = daoRecusoContrato.getFamiliaFlavors( Long.parseLong(idFamiliaFl.trim()) );
				//	System.out.println(mdFamilias);
					Gson gson = new Gson();
					String lista = gson.toJson(mdFamilias);
					response.getWriter().write(lista);
				}
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectProdudo") ) {
				List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
				modelProdutos = daoContratoRepository.getListaProduto();
				request.setAttribute("modelProdutos", modelProdutos);
				Gson gson = new Gson();
				String lista = gson.toJson(modelProdutos);
				response.getWriter().write(lista);
			
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MostraValoresSelectProdudo") ) {
	   			String idProduto = request.getParameter("idProduto");
	   			if( idProduto != null && !idProduto.isEmpty() ) {
				    ModelProduto modelProduto = daoContratoRepository.getValoresProduto( Long.parseLong(idProduto) );
				    Gson gson = new Gson();
				    String lista = gson.toJson(modelProduto);
				    response.getWriter().write(lista);
	   			}
		   }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("generateCodeTagVcenter") ) {
				
				String CodeTagVcenter = this.generateVerificationCode( 6 );
			    Gson gson = new Gson();
			    String lista = gson.toJson(CodeTagVcenter);
			    response.getWriter().write(lista);
			    
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("PaginacaoClienteRenovacao") ) {	
				DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
		   		String pag = request.getParameter("pag");
		   		offsetBegin = Integer.parseInt( pag ) * OFFSET_END;
		   		
				List<ModelCliente> modelClientes = daoCadastroContrato.buscarListaClienteRenovacao( offsetBegin, OFFSET_END );
				request.setAttribute("totalPagina", daoCadastroContrato.getTotalPagRenovacao( OFFSET_END ) );
			    Gson gson = new Gson();
			    String lista = gson.toJson(modelClientes);
			    response.getWriter().write(lista);
			
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginarPesquisaCliente") ) {	
		   		String pag = request.getParameter("pag");
		   		offsetBegin = Integer.parseInt( pag ) * OFFSET_END;
		   		DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
		   		
				List<ModelCliente> modelClientes = daoCadastroContrato.buscarListaClienteCadastro( offsetBegin, OFFSET_END );
				request.setAttribute("totalPagina", daoCadastroContrato.getTotalPagCadastro( OFFSET_END ) );
			    Gson gson = new Gson();
			    String lista = gson.toJson(modelClientes);
			    response.getWriter().write(lista);
			
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarContratoClienteRenovacao") ) {
					String                 idCliente      = request.getParameter("idCliente");
					ModelContrato          modelContrato          = new ModelContrato();
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					if( idCliente != null && !idCliente.isEmpty() ) {
						if(daoContratoRepository.isExstContratoAtivo( Long.parseLong(idCliente) ) ) {
						    
							modelContrato = daoContratoRepository.getContratoAtivoCliente( Long.parseLong(idCliente)  );
							List<ModelListaRecursoContratoAditivo> listaRecursoContratos = daoContratoRepository.getListaRecursoContrato   ( modelContrato.getId_contrato() );
	    					List<ModelAitivoRecursoModal>          listaAitivoRecurso    = daoAditivoModalRecurso.getListaAditivoRecursoID ( modelContrato.getId_contrato() );
	 
	    					List<ModelRenovacaoContrato> listaRenovacaoContratos = new ArrayList<ModelRenovacaoContrato>();
	    					//Informacoes dos recursos
	    					for(int i = 0; i < listaRecursoContratos.size(); i++) {
	    						ModelRenovacaoContrato renovacaoContrato = new ModelRenovacaoContrato();
	    						// Info contrato.
	    						renovacaoContrato.setId_contrato          ( modelContrato.getId_contrato()                       );
	    						renovacaoContrato.setId_fase_contrato     ( modelContrato.getId_fase_contrato()                  );
	    						renovacaoContrato.setId_ciclo_update      ( modelContrato.getId_ciclo_update()                   );
	    						renovacaoContrato.setId_servico_contratado( modelContrato.getId_servico_contratado()             );
	    						renovacaoContrato.setPep                  ( modelContrato.getPep()                               );
	    						renovacaoContrato.setId_hub_spot          ( modelContrato.getId_hub_spot()                       );
	    						renovacaoContrato.setTermo_admin          ( modelContrato.getTermo_admin()                       );
	    						renovacaoContrato.setTermo_download       ( modelContrato.getTermo_download()                    );
	    						renovacaoContrato.setId_cliente           ( modelContrato.getId_cliente()                        );
	    						renovacaoContrato.setNomeCliente          ( modelContrato.getNomeCliente()                       );
	    						renovacaoContrato.setId_nuvem_contr       ( modelContrato.getId_nuvem()                          );
	    						renovacaoContrato.setId_site_contr        ( modelContrato.getId_site()                           );
	    						// Info Recurso.
	    						renovacaoContrato.setId_retencao_backup   ( listaRecursoContratos.get(i).getId_retencao_backup() );
	    						renovacaoContrato.setDesc_retencao_backup ( listaRecursoContratos.get(i).getRetencao_backup()    );
	    						renovacaoContrato.setTag_vcenter          ( listaRecursoContratos.get(i).getTag_vcenter()        );
	    						renovacaoContrato.setId_tipo_disco        ( listaRecursoContratos.get(i).getId_tipo_disco()      );
	    						renovacaoContrato.setDesc_tipo_disco      ( listaRecursoContratos.get(i).getTipo_disco()         );
	    						renovacaoContrato.setId_so                ( listaRecursoContratos.get(i).getId_so()              );
	    						renovacaoContrato.setDesc_so              ( listaRecursoContratos.get(i).getSistema_operacional());
	    						renovacaoContrato.setId_ambiente          ( listaRecursoContratos.get(i).getId_ambiente()        );
	    						renovacaoContrato.setDesc_ambiente        ( listaRecursoContratos.get(i).getAmbiente()           );
	    						renovacaoContrato.setId_tipo_servico      ( listaRecursoContratos.get(i).getId_tipo_servico()    );
	    						renovacaoContrato.setDesc_tipo_servico    ( listaRecursoContratos.get(i).getTipo_servico()       );
	    						renovacaoContrato.setHostname             ( listaRecursoContratos.get(i).getHostname()           );
	    						renovacaoContrato.setTamanho_disco        ( listaRecursoContratos.get(i).getTamanho_disco()      );
	    						renovacaoContrato.setPrimary_ip           ( listaRecursoContratos.get(i).getPrimary_ip()         );
	    						renovacaoContrato.setId_nuvem             ( listaRecursoContratos.get(i).getId_nuvem()           );
	    						renovacaoContrato.setId_site              ( listaRecursoContratos.get(i).getId_site()            );
	    						renovacaoContrato.setId_familia_flavors   ( listaRecursoContratos.get(i).getId_familia()         );
	    						renovacaoContrato.setDesc_familia         ( listaRecursoContratos.get(i).getFamilia()            );
	    						renovacaoContrato.setEip_Vcenter          ( listaRecursoContratos.get(i).getEip_Vcenter()        );
	    						renovacaoContrato.setHost_Vcenter         ( listaRecursoContratos.get(i).getHost_Vcenter()       );
	    						listaRenovacaoContratos.add(renovacaoContrato);
	    					}
						    
	    					for(int i = 0; i < listaAitivoRecurso.size(); i++) {
	    						ModelRenovacaoContrato renovacaoContrato = new ModelRenovacaoContrato();
	    						// Info contrato.
	    						renovacaoContrato.setId_contrato          ( modelContrato.getId_contrato()                             );
	    						renovacaoContrato.setId_fase_contrato     ( modelContrato.getId_fase_contrato()                        );
	    						renovacaoContrato.setId_ciclo_update      ( modelContrato.getId_ciclo_update()                         );
	    						renovacaoContrato.setId_servico_contratado( modelContrato.getId_servico_contratado()                   );
	    						renovacaoContrato.setPep                  ( modelContrato.getPep()                                     );
	    						renovacaoContrato.setId_hub_spot          ( modelContrato.getId_hub_spot()                             );
	    						renovacaoContrato.setTermo_admin          ( modelContrato.getTermo_admin()                             );
	    						renovacaoContrato.setTermo_download       ( modelContrato.getTermo_download()                          );
	    						renovacaoContrato.setId_cliente           ( modelContrato.getId_cliente()                              );
	    						renovacaoContrato.setNomeCliente          ( modelContrato.getNomeCliente()                             );
	    						// Info contrato.
	    						renovacaoContrato.setId_retencao_backup   ( listaAitivoRecurso.get(i).getId_retencao_backup()          );
	    						renovacaoContrato.setTag_vcenter          ( listaAitivoRecurso.get(i).getTag_vcenter()                 );
	    						renovacaoContrato.setId_tipo_disco        ( listaAitivoRecurso.get(i).getId_tipo_disco()               );
	    						renovacaoContrato.setId_so                ( listaAitivoRecurso.get(i).getId_so()                       );
	    						renovacaoContrato.setId_ambiente          ( listaAitivoRecurso.get(i).getId_ambiente()                 );
	    						renovacaoContrato.setDesc_ambiente        ( listaAitivoRecurso.get(i).getDesc_ambiente()               );
	    						renovacaoContrato.setId_tipo_servico      ( listaAitivoRecurso.get(i).getId_tipo_servico()             );
	    						renovacaoContrato.setDesc_tipo_servico    ( listaAitivoRecurso.get(i).getDesc_tipo_servico()           );
	    						renovacaoContrato.setHostname             ( listaAitivoRecurso.get(i).getHost_name_modal_recurso()     );
	    						renovacaoContrato.setTamanho_disco        ( listaAitivoRecurso.get(i).getTamanho_disco_modal_recurso() );
	    						renovacaoContrato.setPrimary_ip           ( listaAitivoRecurso.get(i).getPrimary_ip_modalrecurso()     );
	    						renovacaoContrato.setId_nuvem             ( listaAitivoRecurso.get(i).getId_nuvem()                    );
	    						renovacaoContrato.setId_site              ( listaAitivoRecurso.get(i).getId_site()                     );
	    						renovacaoContrato.setId_familia_flavors   ( listaAitivoRecurso.get(i).getId_familia_flavors()          );
	    						renovacaoContrato.setDesc_familia         ( listaAitivoRecurso.get(i).getDesc_familia()                );
	    						renovacaoContrato.setEip_Vcenter          ( listaAitivoRecurso.get(i).getEip_Vcenter()                 );
	    						renovacaoContrato.setHost_Vcenter         ( listaAitivoRecurso.get(i).getHost_Vcenter()                );

	    						listaRenovacaoContratos.add(renovacaoContrato);
	    					}
	    					if(listaAitivoRecurso.size() == 0 && listaRecursoContratos.size() == 0) {
	    						ModelRenovacaoContrato renovacaoContrato = new ModelRenovacaoContrato();
	    						// Info contrato.
	    						renovacaoContrato.setId_contrato          ( modelContrato.getId_contrato()                             );
	    						renovacaoContrato.setId_fase_contrato     ( modelContrato.getId_fase_contrato()                        );
	    						renovacaoContrato.setId_ciclo_update      ( modelContrato.getId_ciclo_update()                         );
	    						renovacaoContrato.setId_servico_contratado( modelContrato.getId_servico_contratado()                   );
	    						renovacaoContrato.setPep                  ( modelContrato.getPep()                                     );
	    						renovacaoContrato.setId_hub_spot          ( modelContrato.getId_hub_spot()                             );
	    						renovacaoContrato.setTermo_admin          ( modelContrato.getTermo_admin()                             );
	    						renovacaoContrato.setTermo_download       ( modelContrato.getTermo_download()                          );
	    						renovacaoContrato.setId_cliente           ( modelContrato.getId_cliente()                              );
	    						renovacaoContrato.setNomeCliente          ( modelContrato.getNomeCliente()                             );
	    						listaRenovacaoContratos.add(renovacaoContrato);
	    					}
	    					
	    				    Gson gson = new Gson();
	    				    String lista = gson.toJson(listaRenovacaoContratos);
	    				    response.getWriter().write(lista);
	    					
							
						}else {
	    				    Gson gson = new Gson();
	    				    String lista = gson.toJson("NAOTEMCONTRATOATIVO");
	    				    response.getWriter().write(lista);
						}
					} 
			    } else{
				  request.getRequestDispatcher("cadastro/cadastroContrato.jsp").forward(request, response);
		    }	
	   	}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				requestDispatcher.forward(request, response);			
			}
	
	
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String dadosJsonContrato = request.getParameter( "conteudoContrato" );
			String dadosJsonVigencia = request.getParameter( "conteudoVigencia" );
			String listJsonRecursos  = request.getParameter( "conteudoRecurso"  );
			String listJsonProduto   = request.getParameter( "conteudoProduto"   );
	//		String arqContratoPDF   = request.getParameter( "arqContratoPDF"   );
			
			// Transforma a String no formato de Objeto, recebido pelo JavaScript em um Array JSON.
			JSONObject jsJsonContrato  = new JSONObject(dadosJsonContrato);
			JSONObject jsJsonVigencia  = new JSONObject(dadosJsonVigencia);
			JSONArray  jsArrayRecursos = new JSONArray (listJsonRecursos );
			JSONArray  jsArrayProduto  = new JSONArray (listJsonProduto  );
	
			ModelContrato              dadosContrato = trataObjetoContrato ( jsJsonContrato, jsJsonVigencia, request );
			List<ModelRecursoContrato> listRecursos  = lerJsonRecurso      ( jsArrayRecursos, dadosContrato );
			List<ModelContratoProduto> listProdutoss = lerJsonProduto      ( jsArrayProduto );
			
			DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
			
			// Validar se esta logado, se nao redireciona para a tela de login
			HttpServletRequest req = (HttpServletRequest) request;
		    HttpSession session    = req.getSession();
            String userDesativacao = (String) session.getAttribute("usuario");
            String urlParaAutent   = req.getServletPath(); // URL que esta sendo acessada.						
			
			if( dadosContrato.isRenovacao() ) {
                if( userDesativacao == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
                    RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
                    request.setAttribute("msg", "Por favor, realizar o login!");
                    redireciona.forward(request, response);
                    return ;// para a execucao e redireciona para o login.
                }	                
				if( dadosContrato.getId_status_contrato() != 4 ) {
					// Cancelamento de contrato em caso de renovacao.
					ModelDesativacaoContrato DesativacaoContrato = new ModelDesativacaoContrato();
					Long idContrato      = dadosContrato.getId_contrato_origem() ;
					 
					DesativacaoContrato.setUser_desativacao( userDesativacao );
					DesativacaoContrato.setId_contrato     ( idContrato      );
	
					String retorno = daoContratoRepository.CancelaContrato( DesativacaoContrato, 1 );
					if( retorno == null )
						retorno = "O Contrato: " + idContrato + " e todo(s) os produtos e recusso(s) a ele vinculados(caso exista), foram cancelados com sucesso";
				}
			}
					
			String retornoProcessamento = daoCadastroContrato.MontagravacaoContrato(dadosContrato, listRecursos, listProdutoss);
			if(retornoProcessamento != null) {
				@SuppressWarnings("unused")
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", retornoProcessamento);	
			}
			
			if( dadosContrato.getIsGmud()  ) {
				if( !dadosContrato.isRenovacao() ) {
	                // <!-- URL da base da API Base TST ou PRD  -->
					String urlBase           = (String) session.getAttribute("urlAPI_PortalMudanca");
				    String loginUser         = (String) session.getAttribute("usuario");
					String email_solicitante = (String) session.getAttribute("usuarioEmail");		
					String idCliente         = dadosContrato.getId_cliente().toString();
					String idContrato        = dadosContrato.getId_contrato().toString();
					String urlPostMudacao    = urlBase + "salvarMudancaPadrao";  // "http://localhost:8090/PortalMudanca/salvarMudancaPadrao"
					
					CriarMudancaComissionamento criarMudancaComiss = new CriarMudancaComissionamento( urlBase,idCliente, idContrato, loginUser, email_solicitante );
					try {
						String result = criarMudancaComiss.sendPOST( urlPostMudacao );
						
						System.out.println( result );
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
			
		    String url= request.getContextPath() + "/ServletContratoController?acao=buscarContratoCliente&idContratoCliente=" + dadosContrato.getId_cliente();
			response.sendRedirect( url ); 
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public String generateVerificationCode(int qtyCaracteres) {
		   return AlphaCode.generate(qtyCaracteres);
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public List<ModelContratoProduto> lerJsonProduto( JSONArray jsArrayProduto ) {
		List<ModelContratoProduto> listaProduto = null;
		for (int i = 0; i < jsArrayProduto.length(); ++i) {
			  JSONObject objJsonProduto = jsArrayProduto.getJSONObject(i);
			  ModelContratoProduto produto = trataObjetoProduto ( objJsonProduto ) ;
			  if( listaProduto == null ) listaProduto = new ArrayList<ModelContratoProduto>(); 
			  listaProduto.add(produto);
			}
		return listaProduto;
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public ModelContratoProduto trataObjetoProduto( JSONObject objJsonProduto ) {
		ModelContratoProduto produto = new ModelContratoProduto();
		
//   		String idContrato = objJsonProduto.getString("idContrato");
   		String idProduto  = objJsonProduto.getString("idProdutoBD" );
   		String QtyProduto = objJsonProduto.getString("idQty"       );
   		String vlrUnit    = objJsonProduto.getString("vlrUnit"     );
   		String vltTotal   = objJsonProduto.getString("vltTotal"    );
   		String obs        = objJsonProduto.getString("obsProduto"  );
   		
   		   		
	    if(vlrUnit != null && !vlrUnit.isEmpty()) {
	    	vlrUnit = Normalizer.normalize(vlrUnit, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		       if(vlrUnit.indexOf(" ") >= 0 )
		    	   vlrUnit = vlrUnit.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		       else
		    	   vlrUnit = vlrUnit.replaceAll("\\.", "").replaceAll("\\,", ".");
		}
	    
	    if(vltTotal != null && !vltTotal.isEmpty()) {
	    	vltTotal = Normalizer.normalize(vltTotal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		       if(vltTotal.indexOf(" ") >= 0 )
		    	   vltTotal = vltTotal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		       else
		    	   vltTotal = vltTotal.replaceAll("\\.", "").replaceAll("\\,", ".");
		}

//	    produto.setId_contrato   ( Long.parseLong(idContrato)   );
	    produto.setId_produto    ( Long.parseLong(idProduto)    );
	    produto.setQty_servico   ( Integer.parseInt(QtyProduto) );
	    produto.setValor_unitario( vlrUnit                      );
	    produto.setValor         ( vltTotal                     );
	    produto.setObservacao    ( obs                          );
		return produto;
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public ModelContrato trataObjetoContrato ( JSONObject objJsonContrato, JSONObject objJsonVigencia, HttpServletRequest request ) throws IOException, ServletException {
	 	
		String id_cliente             = objJsonContrato.getString ( "id_cliente"             );
		String nomeCliente            = objJsonContrato.getString ( "nomeCliente"            );
		String valor_total            = objJsonContrato.getString ( "valor_total"            );
		String valor_Cotacao          = objJsonContrato.getString ( "valor_Cotacao"          );
		String valor_convertido       = objJsonContrato.getString ( "valor_convertido"       );
//		String extensaocontratopdf    = objJsonContrato.getString ( "arqContratoPDF"         );
		String pep                    = objJsonContrato.getString ( "pep"                    );
		String id_hub_spot            = objJsonContrato.getString ( "id_hub_spot"            );
		String qty_usuario_contratada = objJsonContrato.getString ( "qty_usuario_contratada" );
		String id_moeda               = objJsonContrato.getString ( "id_moeda"               );
		String id_status_contrato     = objJsonContrato.getString ( "id_status_contrato"     );
		String id_rascunho            = objJsonContrato.getString ( "id_rascunho"            );
		String motivo_rascunho        = objJsonContrato.getString ( "motivoRascunho"         );
		String id_nuvem               = objJsonContrato.getString ( "id_nuvem"               );
		String id_site                = objJsonContrato.getString ( "id_site"                );
		String id_fase_contrato       = objJsonContrato.getString ( "id_fase_contrato"       );
		String id_ciclo_update        = objJsonContrato.getString ( "id_ciclo_updadate"      );
		String id_servico_contratado  = objJsonContrato.getString ( "id_servico_contratado"  );
		String termo_admin            = objJsonContrato.getString ( "termo_admin"            );
		String termo_download         = objJsonContrato.getString ( "termo_download"         );
		String observacao             = objJsonContrato.getString ( "observacao"             ); 
		String isRenovacao            = objJsonContrato.getString ( "isRenovacao"            );
		String id_contrato_origem     = objJsonContrato.getString ( "id_contrato"            );		
		String id_suporte_b1          = objJsonContrato.getString ( "id_suporte_b1"          );
		String id_comercial           = objJsonContrato.getString ( "id_comercial"           );		
		String comissao               = objJsonContrato.getString ( "comissao"               );
		String valor_setup            = objJsonContrato.getString ( "valor_setup"            );		
		String qty_parcela_setup      = objJsonContrato.getString ( "qty_parcela_setup"      );
		String valor_parcela_setup    = objJsonContrato.getString ( "valor_parcela_setup"    );		
		String qty_mes_setup          = objJsonContrato.getString ( "qty_mes_setup"          );
		Boolean isGmud                = objJsonContrato.getBoolean( "isGmud"                 );
		
		
	    HttpServletRequest req        = (HttpServletRequest) request;
		HttpSession session           = req.getSession();
	    String usuarioLogado          = (String) session.getAttribute("usuario");

	    if(valor_parcela_setup != null && !valor_parcela_setup.isEmpty()) {
	    	valor_parcela_setup = Normalizer.normalize(valor_parcela_setup, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		       if(valor_parcela_setup.indexOf(" ") >= 0 )
		    	   valor_parcela_setup = valor_parcela_setup.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		       else
		    	   valor_parcela_setup = valor_parcela_setup.replaceAll("\\.", "").replaceAll("\\,", ".");
		}

	    if(valor_setup != null && !valor_setup.isEmpty()) {
	    	valor_setup = Normalizer.normalize(valor_setup, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		       if(valor_setup.indexOf(" ") >= 0 )
		    	   valor_setup = valor_setup.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		       else
		    	   valor_setup = valor_setup.replaceAll("\\.", "").replaceAll("\\,", ".");
		}

	    if(valor_total != null && !valor_total.isEmpty()) {
	       valor_total = Normalizer.normalize(valor_total, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
	       if(valor_total.indexOf(" ") >= 0 )
	          valor_total = valor_total.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
	       else
	         valor_total = valor_total.replaceAll("\\.", "").replaceAll("\\,", ".");
	    }
	    if(valor_convertido != null && !valor_convertido.isEmpty()) {
	    	valor_convertido = Normalizer.normalize(valor_convertido, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    if(valor_convertido.indexOf(" ") >= 0 )
	    	   valor_convertido = valor_convertido.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    else
		       valor_convertido = valor_convertido.replaceAll("\\.", "").replaceAll("\\,", ".");
		}
	    if(valor_Cotacao != null && !valor_Cotacao.isEmpty()) {
	    	valor_Cotacao = Normalizer.normalize(valor_Cotacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
	    	if(valor_Cotacao.indexOf(" ") >= 0 )
	    	   valor_Cotacao = valor_Cotacao.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
	    	else
	    	   valor_Cotacao = valor_Cotacao.replaceAll("\\.", "").replaceAll("\\,", ".");
		}

		String id_tempo_contrato   = objJsonVigencia.getString("selectTempoContrato");
		String dt_inicio           = objJsonVigencia.getString("dt_inicio");
		String dt_final            = objJsonVigencia.getString("dt_final");
		String observacao_vigencia = objJsonVigencia.getString("observacaoVigencia" );	

		ModelContrato contrato = new ModelContrato();

		contrato.setId_contrato_origem    ( id_contrato_origem      != null && !id_contrato_origem.isEmpty()     ? Long.parseLong(id_contrato_origem.trim())   : null );
		contrato.setId_nuvem              ( id_nuvem                != null && !id_nuvem.isEmpty()               ? Long.parseLong(id_nuvem.trim())             : null );
		contrato.setId_fase_contrato      ( id_fase_contrato        != null && !id_fase_contrato.isEmpty()       ? Long.parseLong(id_fase_contrato.trim())     : null );
		contrato.setId_status_contrato    ( id_status_contrato      != null && !id_status_contrato.isEmpty()     ? Long.parseLong(id_status_contrato.trim())   : null );
		contrato.setId_rascunho           ( id_rascunho             != null && !id_rascunho.isEmpty()            ? Long.parseLong(id_rascunho.trim())          : null );
		contrato.setMotivoRascunho        ( motivo_rascunho         != null && !motivo_rascunho.isEmpty()        ? motivo_rascunho.trim()                      : null );
		contrato.setId_cliente            ( id_cliente              != null && !id_cliente.isEmpty()             ? Long.parseLong(id_cliente.trim())           : null );
		contrato.setId_ciclo_update       ( id_ciclo_update         != null && !id_ciclo_update.isEmpty()        ? Long.parseLong(id_ciclo_update.trim())      : null );
		contrato.setId_servico_contratado ( id_servico_contratado   != null && !id_servico_contratado.isEmpty()  ? Long.parseLong(id_servico_contratado.trim()): null );
//		contrato.setDt_criacao            ( dt_criacao              != null && !dt_criacao.isEmpty()             ? dt_criacao.trim()                           : null );
		contrato.setNomeCliente           ( nomeCliente             != null && !nomeCliente.isEmpty()            ? nomeCliente.trim()                          : null );
		contrato.setQty_usuario_contratada( qty_usuario_contratada  != null && !qty_usuario_contratada.isEmpty() ? qty_usuario_contratada.trim()               : null );
		contrato.setId_site               ( id_site                 != null && !id_site.isEmpty()                ? Long.parseLong(id_site.trim())              : null );
		contrato.setValor_total           ( valor_total             != null && !valor_total.isEmpty()            ? valor_total.trim()                          : null );
		contrato.setPep                   ( pep                     != null && !pep.isEmpty()                    ? pep.trim()                                  : null );
		contrato.setId_hub_spot           ( id_hub_spot             != null && !id_hub_spot.isEmpty()            ? id_hub_spot.trim()                          : null );
		contrato.setObservacao            ( observacao              != null && !observacao.isEmpty()             ? observacao.trim()                           : null );
		contrato.setTermo_admin           ( termo_admin             != null && !termo_admin.isEmpty()            ? Integer.valueOf(termo_admin.trim())         : 1    );
		contrato.setTermo_download        ( termo_download          != null && !termo_download.isEmpty()         ? Integer.valueOf(termo_download.trim())      : 1    );
		contrato.setId_moeda              ( id_moeda                != null && !id_moeda.isEmpty()               ? Long.parseLong(id_moeda.trim())             : null );
		contrato.setValor_convertido      ( valor_convertido        != null && !valor_convertido.isEmpty()       ? valor_convertido.trim()                     : null );
		contrato.setCotacao_moeda         ( valor_Cotacao           != null && !valor_Cotacao.isEmpty()          ? valor_Cotacao.trim()                        : null );
		contrato.setId_comercial          ( id_comercial            != null && !id_comercial.isEmpty()           ? Long.parseLong(id_comercial.trim())         : null );
		contrato.setId_suporte_b1         ( id_suporte_b1           != null && !id_suporte_b1.isEmpty()          ? Long.parseLong(id_suporte_b1.trim())        : null );
		contrato.setRenovacao             ( Integer.parseInt(isRenovacao) == 1                                   ? true                                        : false );		
		contrato.setComissao              ( comissao                != null && !comissao.isEmpty()               ? comissao.trim()                             : null );
		contrato.setValor_setup           ( valor_setup             != null && !valor_setup.isEmpty()            ? valor_setup.trim()                          : null );
		contrato.setQty_parcela_setup     ( qty_parcela_setup       != null && !qty_parcela_setup.isEmpty()      ? Integer.valueOf(qty_parcela_setup.trim())   : 0    );
		contrato.setValor_parcela_setup   ( valor_parcela_setup     != null && !valor_parcela_setup.isEmpty()    ? valor_parcela_setup.trim()                  : null );
		contrato.setQty_mese_setup        ( qty_mes_setup           != null && !qty_mes_setup.isEmpty()          ? Integer.valueOf(qty_mes_setup.trim())       : 0    );
		contrato.setIsGmud                (isGmud);
		// 

	    // informacoes sobre a vigencia do contrato.
//		contrato.setId_vigencia           ( id_vigencia             != null && !id_vigencia.isEmpty()            ? Long.parseLong(id_vigencia.trim())          : null );
		contrato.setId_tempo_contrato     ( id_tempo_contrato       != null && !id_tempo_contrato.isEmpty()      ? Long.parseLong(id_tempo_contrato.trim())    : null );
		contrato.setDt_inicio             ( dt_inicio               != null && !dt_inicio.isEmpty()              ? dt_inicio.trim()                            : null );
		contrato.setDt_final              ( dt_final                != null && !dt_final.isEmpty()               ? dt_final.trim()                             : null );
		contrato.setObservacao_vigencia   ( observacao_vigencia     != null && !observacao_vigencia.isEmpty()    ? observacao_vigencia.trim()                  : null );
		contrato.setLogin_cadastro        ( usuarioLogado           != null && !usuarioLogado.isEmpty()          ? usuarioLogado.trim()                        : null );

	    if (request.getPart("arqContratoPDF") != null) {
		    Part part = request.getPart("arqContratoPDF"); 
		    try {
		    	if (part.getSize() > 0) {
			        byte[] contratoPdf = IOUtils.toByteArray(part.getInputStream()); 
					new org.apache.tomcat.util.codec.binary.Base64();
					String contratoPdfBase64 = "data:" + part.getContentType() + ";base64," + Base64.encodeBase64String(contratoPdf);
					contrato.setContratopdf(contratoPdfBase64);
					contrato.setExtensaocontratopdf(part.getContentType().split("\\/")[1]);
					contrato.setNomeaqrpdf( part.getSubmittedFileName() );
		    	}
		      } catch (Exception e) {
		        e.printStackTrace();
		      }		    
	    }

		return contrato;
	}
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public List<ModelRecursoContrato> lerJsonRecurso( JSONArray jsArrayRecursos, ModelContrato dadosContrato ) {
		List<ModelRecursoContrato> listaRecurso = null;
		for (int i = 0; i < jsArrayRecursos.length(); ++i) {
			  JSONObject objJsonRecurso = jsArrayRecursos.getJSONObject(i);
			  ModelRecursoContrato recurso = trataObjetoRecurso ( objJsonRecurso, dadosContrato ) ;
			  if( listaRecurso == null ) listaRecurso = new ArrayList<ModelRecursoContrato>(); 
			  listaRecurso.add(recurso);
			}
		return listaRecurso;
	}

	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	public ModelRecursoContrato trataObjetoRecurso ( JSONObject objJsonRecurso, ModelContrato dadosContrato ) {
		ModelRecursoContrato recurso = new ModelRecursoContrato();
		
//		String	id_recurso         = objJsonRecurso.getString("idRecurso"           );
		String	id_status_recurso  = objJsonRecurso.getString("id_status_recurso"  );
		String	status_rec	       = objJsonRecurso.getString("status_rec"          );
		String	id_retencao_backup = objJsonRecurso.getString("id_retencao_backup"  );
		String	retencao_backup	   = objJsonRecurso.getString("retencao_backup"     );
		String	id_tipo_disco      = objJsonRecurso.getString("id_tipo_disco"       );
		String	tipo_disco	       = objJsonRecurso.getString("tipo_disco"          );
		String	id_so              = objJsonRecurso.getString("id_so"               );
		String	so	               = objJsonRecurso.getString("so"                  );
		String	id_ambiente        = objJsonRecurso.getString("id_ambiente"         );
		String	ambiente	       = objJsonRecurso.getString("ambiente"            );
		String	id_tipo_servico    = objJsonRecurso.getString("id_tipo_servico"     );
		String	tipo_servico	   = objJsonRecurso.getString("tipo_servico"        );
		String	hostname           = objJsonRecurso.getString("hostname"            );
		String	primaryIP          = objJsonRecurso.getString("primaryIP"           );
		String	tamanhoDisco       = objJsonRecurso.getString("tamanhoDisco"        );
		String	host_vcenter       = objJsonRecurso.getString("hostVcenter"         );
		String	eip_vcenter        = objJsonRecurso.getString("eipVcenter"          );
		String	tag_vcenter        = objJsonRecurso.getString("tag_vcenter"         );
		String	id_nuvem           = objJsonRecurso.getString("id_nuvem"            );
		String	nuvem              = objJsonRecurso.getString("nuvemRecurso"        );
		String	id_site            = objJsonRecurso.getString("id_site"             );
		String	site               = objJsonRecurso.getString("site"                );
		String	id_familia_flavors = objJsonRecurso.getString("selectFamiliaFlavors");
		String	familiaFlavors     = objJsonRecurso.getString("familiaFlavors"      );
		String	cpu                = objJsonRecurso.getString("cpu"                 );
		String	ram                = objJsonRecurso.getString("ram"                 );
		String	valor              = objJsonRecurso.getString("valor"               );
		String	obs                = objJsonRecurso.getString("obs"                 );
		String vlrRecurso          = null;

	    if(valor != null && !valor.isEmpty()) {
		   valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
	       valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
	    }

//		recurso.setId_recurso        ( id_recurso         != null && !id_recurso.isEmpty()         ? Long.parseLong(id_recurso.trim())        : null );
		recurso.setId_status_recurso ( id_status_recurso  != null && !id_status_recurso.isEmpty()  ? Long.parseLong(id_status_recurso.trim()) : null );
		recurso.setStatus_rec        ( status_rec         != null && !status_rec.isEmpty()         ? status_rec.trim()                        : null );
		recurso.setId_retencao_backup( id_retencao_backup != null && !id_retencao_backup.isEmpty() ? Long.parseLong(id_retencao_backup.trim()): null );
		recurso.setRetencao_backup   ( retencao_backup    != null && !retencao_backup.isEmpty()    ? retencao_backup.trim()                   : null );
		recurso.setId_tipo_disco     ( id_tipo_disco      != null && !id_tipo_disco.isEmpty()      ? Long.parseLong(id_tipo_disco.trim())     : null );
		recurso.setTipo_disco        ( tipo_disco         != null && !tipo_disco.isEmpty()         ? tipo_disco.trim()                        : null );
		recurso.setId_so             ( id_so              != null && !id_so.isEmpty()              ? Long.parseLong(id_so.trim())             : null );
		recurso.setSo                ( so                 != null && !so.isEmpty()                 ? so.trim()                                : null );
		recurso.setId_ambiente       ( id_ambiente        != null && !id_ambiente.isEmpty()        ? Long.parseLong(id_ambiente.trim())       : null );
		recurso.setAmbiente          ( ambiente           != null && !ambiente.isEmpty()           ? ambiente.trim()                          : null );
		recurso.setId_tipo_servico   ( id_tipo_servico    != null && !id_tipo_servico.isEmpty()    ? Long.parseLong(id_tipo_servico.trim())   : null );
		recurso.setTipo_servico      ( tipo_servico       != null && !tipo_servico.isEmpty()       ? tipo_servico.trim()                      : null );
		recurso.setTag_vcenter       ( tag_vcenter        != null && !tag_vcenter.isEmpty()        ? tag_vcenter.trim()                       : null );
		recurso.setId_familia_flavors( id_familia_flavors != null && !id_familia_flavors.isEmpty() ? Long.parseLong(id_familia_flavors.trim()): null );
		recurso.setFamiliaFlavors    ( familiaFlavors     != null && !familiaFlavors.isEmpty()     ? familiaFlavors.trim()                    : null );
		recurso.setCpu               ( cpu                != null && !cpu.isEmpty()                ? cpu.trim()                               : null );
	    recurso.setRam               ( ram                != null && !ram.isEmpty()                ? ram.trim()                               : null );
		recurso.setValor             ( valor              != null && !valor.isEmpty()              ? valor.trim()                             : null );
		recurso.setValor_recurso     ( vlrRecurso         != null && !vlrRecurso.isEmpty()         ? vlrRecurso.trim()                        : null );				
		recurso.setObs               ( obs                != null && !obs.isEmpty()                ? obs.trim()                               : null );
		recurso.setId_nuvem          ( id_nuvem           != null && !id_nuvem.isEmpty()           ? Long.parseLong(id_nuvem.trim())          : null );
		recurso.setNuvem             ( nuvem              != null && !nuvem.isEmpty()              ? nuvem.trim()                             : null );
		recurso.setId_site           ( id_site            != null && !id_site.isEmpty()            ? Long.parseLong(id_site.trim())           : null );
		recurso.setSite              ( site               != null && !site.isEmpty()               ? site.trim()                              : null );
		recurso.setHostname          ( hostname           != null && !hostname.isEmpty()           ? hostname.trim()                          : null );
		recurso.setTamanhoDisco      ( tamanhoDisco       != null && !tamanhoDisco.isEmpty()       ? tamanhoDisco.trim()                      : null );
		recurso.setPrimaryIP         ( primaryIP          != null && !primaryIP.isEmpty()          ? primaryIP.trim()                         : null );
		recurso.setEip_vcenter       ( eip_vcenter        != null && !eip_vcenter.isEmpty()        ? eip_vcenter.trim()                       : null );
		recurso.setHost_vcenter      ( host_vcenter       != null && !host_vcenter.isEmpty()       ? host_vcenter.trim()                      : null );

		recurso.setId_cliente        ( dadosContrato.getId_cliente()  );
		recurso.setNomeCliente       ( dadosContrato.getNomeCliente() );
		return recurso;
		
	}
	
}
