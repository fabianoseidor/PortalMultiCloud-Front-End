package br.com.portal.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lacunasoftware.pkiexpress.AlphaCode;

import br.com.portal.dao.DAOAditivoModal;
import br.com.portal.dao.DAOAditivoModalRecurso;
import br.com.portal.dao.DAOCadastroContrato;
import br.com.portal.dao.DAOClienteRepository;
import br.com.portal.dao.DAOContratoRepository;
import br.com.portal.dao.DAOFamiliaFlavors;
import br.com.portal.dao.DAOProduto;
import br.com.portal.dao.DAORascunho;
import br.com.portal.dao.DAORecursoContratoAditivoRel;
import br.com.portal.dao.DAORecusoContrato;
import br.com.portal.dao.DAOSite;
import br.com.portal.dao.DAOUtil;
import br.com.portal.model.ModalDescomissionamento;
import br.com.portal.model.ModelAditivoModal;
import br.com.portal.model.ModelAitivoRecursoModal;
import br.com.portal.model.ModelCliente;
import br.com.portal.model.ModelContrato;
import br.com.portal.model.ModelContratoProduto;
import br.com.portal.model.ModelContratosDesativados;
import br.com.portal.model.ModelDesativacaoContrato;
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelHistoricoUpgrade;
import br.com.portal.model.ModelHitoricoUpgrade;
import br.com.portal.model.ModelListAditivoProduto;
import br.com.portal.model.ModelListaRecursoContratoAditivo;
import br.com.portal.model.ModelPepSap;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelRecursoContrato;
import br.com.portal.model.ModelRecursoContratoAditivoRel;
import br.com.portal.model.ModelRecursoContratoCliente;
import br.com.portal.model.ModelSite;
import br.com.portal.model.ModelTempoContrato;
import br.com.portal.model.ModelTipoProduto;
import br.com.portal.model.ModelVigenciaContrato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
public class ServletContratoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Integer OFFSET_END    = 20;
	private Integer offsetBegin                = 0;
	private DAOContratoRepository daoContratoRepository = new DAOContratoRepository();
	private DAOClienteRepository   daoClienteRepository = new DAOClienteRepository();
//	private DAOAditivo                      daoAditivo  = new DAOAditivo();            

    public ServletContratoController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	try {
	   		String acao = request.getParameter("acao");

	   		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("validaPEP") ) {

		   		String pepNew         = request.getParameter("pep");
		   		String pepBase        = "";
		   		String idContrato     = request.getParameter("idContrato");
		   		String RazaoSocial    = "";
		   		String idContratobase = "";
		   		String mensagem       = ""; 
		   		
		   		if( pepNew != null && !pepNew.isEmpty()  ) {

		   			String resultado = daoContratoRepository.verificaPep(pepNew);
		   			if( !resultado.equalsIgnoreCase("OK") ) {
						String textoSplit [] = resultado.split(";");
						pepBase        = textoSplit[0];
						idContratobase = textoSplit[1];
						RazaoSocial    = textoSplit[2];
						
						mensagem = "Este PEP " + pepBase + " já existe na base e esta associando ao Cliente " + RazaoSocial + ".";
		   			}
					if( pepBase.equalsIgnoreCase(pepNew) && !idContratobase.equalsIgnoreCase(idContrato) ) {
					    Gson gson = new Gson();
					    String lista = gson.toJson(mensagem);
					    response.getWriter().write(lista);						
					}else {
						response.getWriter().write("OK");
					}
		   		}
		   		
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectPEP") ) {
				
		    	 DAOCadastroContrato daoCadastroContrato = new DAOCadastroContrato();
						
				 List<ModelPepSap> listPepSap  = daoCadastroContrato.getListaPepSapSelect2( );
				 Gson gson = new Gson();
				 String lista = gson.toJson( listPepSap );
				 response.getWriter().write(lista);
					
			 } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("CalculaVigencia") ) {

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
					
					vigenciaContrato.setId_tempo_contrato( Long.parseLong(idTempoContrato)                                                );
			   		vigenciaContrato.setDt_criacao       ( LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) );
			   		vigenciaContrato.setDt_inicio        ( dataInicial.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") )                );
			   		vigenciaContrato.setDt_final         (datafinal.format( DateTimeFormatter.ofPattern("dd/MM/yyyy") )                   );
			   		
				    Gson gson = new Gson();
				    String lista = gson.toJson(vigenciaContrato);
				    response.getWriter().write(lista);
		   		}
		   		
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaListaRecursos") ) {
				String idContrato = request.getParameter("idContrato");  
				if( (idContrato != null && !idContrato.isEmpty()) ) {
				     List<ModelListaRecursoContratoAditivo> listaRecursoContratos = daoContratoRepository.getListaRecursoContrato( Long.parseLong(idContrato.trim()) );

				     Gson gson = new Gson();
				     String lista = gson.toJson(listaRecursoContratos);
				     response.getWriter().write(lista);
			    }
			}  else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("cadProduto") ) {
		   		String idContrato = request.getParameter("idContrato");
		   		String idProduto  = request.getParameter("idProduto");
		   		String QtyProduto = request.getParameter("QtyProduto");
		   		String vlrUnit    = request.getParameter("vlrUnit");
		   		String vltTotal   = request.getParameter("vltTotal");
	   		
		   		if( (idContrato != null && !idContrato.isEmpty()) && (idProduto != null && !idProduto.isEmpty()) && 
		   			(QtyProduto != null && !QtyProduto.isEmpty()) && (vlrUnit != null && !vlrUnit.isEmpty()) &&
		   			(vltTotal != null && !vltTotal.isEmpty()) ) {
		   			
		   			if( !daoContratoRepository.isExistProduto( Long.parseLong( idContrato ), Long.parseLong( idProduto) ) ) {
			   			ModelContratoProduto modelContratoProduto = new ModelContratoProduto();
			   			modelContratoProduto.setId_contrato( Long.parseLong(idContrato) );
			   			modelContratoProduto.setId_produto( Long.parseLong(idProduto) );
			   			modelContratoProduto.setQty_servico( Integer.parseInt(QtyProduto) );
			   			modelContratoProduto.setValor_unitario( vlrUnit );
			   			modelContratoProduto.setValor( vltTotal );
			   			List<ModelContratoProduto> listaContratoProdutos =  daoContratoRepository.InsertProduto( modelContratoProduto );
			   			
	//		   			System.out.println(listaContratoProdutos);
			   			
					    Gson gson = new Gson();
					    String lista = gson.toJson(listaContratoProdutos);
					    response.getWriter().write(lista);
		   			}else {
		   				response.getWriter().write("EXISTE");
		   			}
		   		}
		   }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTabelaProdutos") ) {
				String idContratoCliente = request.getParameter("idContratoCliente");  
				if( (idContratoCliente != null && !idContratoCliente.isEmpty()) ) {

				     List<ModelContratoProduto> listaContratoProdutos = daoContratoRepository.getListaContratoProduto( Long.parseLong(idContratoCliente.trim()) );

				     Gson gson = new Gson();
				     String lista = gson.toJson(listaContratoProdutos);
				     response.getWriter().write(lista);
			    }
			}  else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editProduto") ) {
		   		String idContrato = request.getParameter("idContrato");
		   		String idProduto  = request.getParameter("idProduto" );
		   		String QtyProduto = request.getParameter("QtyProduto");
		   		String vlrUnit    = request.getParameter("vlrUnit"   );
		   		String vltTotal   = request.getParameter("vltTotal"  );
		   		String obsProduto = request.getParameter("obsProduto");		
		   		
		   		HttpServletRequest req = (HttpServletRequest) request;
		   		HttpSession session    = req.getSession();
		   		String usuarioLogado   = (String) session.getAttribute("usuario");
		   		String urlParaAutent   = req.getServletPath(); // URL que esta sendo acessada.						
		   		// Validar se esta logado, se nao redireciona para a tela de login
		   		if( usuarioLogado == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
		   		    RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
		   		    request.setAttribute("msg", "Por favor, realizar o login!");
		   		    redireciona.forward(request, response);
		   		    return ;// para a execucao e redireciona para o login.
		   		}					    

	   		
		   		if( (idContrato != null && !idContrato.isEmpty()) && (idProduto != null && !idProduto.isEmpty()) && 
		   			(QtyProduto != null && !QtyProduto.isEmpty()) && (vlrUnit != null && !vlrUnit.isEmpty()) &&
		   			(vltTotal != null && !vltTotal.isEmpty()) ) {
		   			
			   			ModelContratoProduto modelContratoProduto = new ModelContratoProduto();
			   			modelContratoProduto.setId_contrato   ( Long.parseLong(idContrato)   );
			   			modelContratoProduto.setId_produto    ( Long.parseLong(idProduto)    );
			   			modelContratoProduto.setQty_servico   ( Integer.parseInt(QtyProduto) );
			   			modelContratoProduto.setValor_unitario( vlrUnit                      );
			   			modelContratoProduto.setValor         ( vltTotal                     );
			   			modelContratoProduto.setObservacao    ( obsProduto                   );
			   			String ERRO = daoContratoRepository.updateProduto( modelContratoProduto );
			   			if(ERRO == null)ERRO = "SUCESSO";
					    Gson gson = new Gson();
					    String lista = gson.toJson(ERRO);
					    response.getWriter().write(lista);
		   		}
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MostraValoresSelectProdudo") ) {
	   			String idProduto = request.getParameter("idProduto");
	   			if( idProduto != null && !idProduto.isEmpty() ) {
				    ModelProduto modelProduto = daoContratoRepository.getValoresProduto( Long.parseLong(idProduto) );
				    Gson gson = new Gson();
				    String lista = gson.toJson(modelProduto);
				    response.getWriter().write(lista);
	   			}
		   } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectProdudo") ) {
					List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
					modelProdutos = daoContratoRepository.getListaProduto();
					request.setAttribute("modelProdutos", modelProdutos);
					Gson gson = new Gson();
					String lista = gson.toJson(modelProdutos);
					response.getWriter().write(lista);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectSite") ) {
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
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteAjax") ) {
				
				String nomeBusca = request.getParameter("nomeBuscaCliente");
				List<ModelCliente> dadosJsonUser = daoClienteRepository.buscarListaClienteNome(nomeBusca);
				
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
		    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteAjaxDesativados") ) {
				
				String nomeBusca = request.getParameter("nomeBuscaCliente");
				List<ModelCliente> dadosJsonUser = daoClienteRepository.buscarListaClienteNomeDesativados(nomeBusca);
				
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
		    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteDesativadosAjax") ) {
				
				String idCliente = request.getParameter("idCliente");
				List<ModelContratosDesativados> contratosDesativados = daoClienteRepository.buscarDadosContratoDesativados( Long.parseLong( idCliente ) );
				
				ObjectMapper objectMapper = new ObjectMapper();
				
		        try {
		          String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contratosDesativados);
		          //System.out.println(json);
		          response.getWriter().write(json);
		        } catch(Exception e) {
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
					request.setAttribute("msg", e.getMessage());
					requestDispatcher.forward(request, response);
		       }
		    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarClienteCadAjax") ) {
				
				String nomeBusca = request.getParameter("nomeBuscaCliente");
				List<ModelCliente> dadosJsonUser = daoClienteRepository.buscarListaClienteNome(nomeBusca);
				
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
		    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarCadContratoCliente") ) {
				String                 idContratoCliente      = request.getParameter("idContratoCliente");
				ModelContrato          modelContrato          = new ModelContrato();
				DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
				DAOAditivoModal        daoAditivoModal        = new DAOAditivoModal();
				
				if( idContratoCliente != null && !idContratoCliente.isEmpty() ) {
					if(daoContratoRepository.isExstContratoAtivo( Long.parseLong(idContratoCliente) ) ) {
					    
						modelContrato = daoContratoRepository.getContratoAtivoCliente( Long.parseLong(idContratoCliente)  );
						List<ModelListaRecursoContratoAditivo> listaRecursoContratos    = daoContratoRepository.getListaRecursoContrato        ( Long.parseLong(idContratoCliente.trim()) );
						List<ModelContratoProduto>             listaContratoProdutos    = daoContratoRepository.getListaContratoProduto        ( modelContrato.getId_contrato()           );
						List<ModelListAditivoProduto>          listAditivoProdutos      = daoAditivoModal.buscarListaAditivoProdutosContratados( modelContrato.getId_contrato()           );
    					List<ModelAitivoRecursoModal>          modelAitivoRecursoModals = daoAditivoModalRecurso.getListaAditivoRecursoID      ( modelContrato.getId_contrato()           );
    					List<ModelHistoricoUpgrade>            listaHistoricoUpgrade    = daoAditivoModal.getListaHistoricoUpgrade             ( modelContrato.getId_contrato()           );

					    request.setAttribute("msg", "Contrto do Cliente em Edição" );

					    request.setAttribute("modelContrato"           , modelContrato            );
					    request.setAttribute("listaContratoProdutos"   , listaContratoProdutos    );
					    request.setAttribute("listaRecursoContratos"   , listaRecursoContratos    );
	   					request.setAttribute("modelAitivoRecursoModals", modelAitivoRecursoModals );
	   					request.setAttribute("listaHistoricoUpgrade"   , listaHistoricoUpgrade    );

					    request.setAttribute("listAditivoProdutos"     , listAditivoProdutos      ); // trabalhando aqui 09/01/2024
					    
					    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
						
					}else {
						ModelCliente dadosCliente = daoClienteRepository.getClienteID( Long.parseLong(idContratoCliente.trim()) );
						modelContrato.setId_cliente(dadosCliente.getId_cliente());
						modelContrato.setNomeCliente( dadosCliente.getRazao_social() );
											
					    request.setAttribute("msg", "O Cliente não possui contrato Ativo" );
					    request.setAttribute("modelContrato", modelContrato);
					    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
	
					}
				} 
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarContratoClienteDesativado") ) {
				String                 idContratoCliente      = request.getParameter("idContrato");
				ModelContrato          modelContrato          = new ModelContrato();
				DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
				DAOAditivoModal        daoAditivoModal        = new DAOAditivoModal();
				
				if( idContratoCliente != null && !idContratoCliente.isEmpty() ) {
					modelContrato = daoContratoRepository.getContratoDesativoCliente( Long.parseLong(idContratoCliente)  );
					List<ModelListaRecursoContratoAditivo> listaRecursoContratos    = daoContratoRepository.getListaRecursoContratoDesativos ( Long.parseLong(idContratoCliente.trim()) );
					List<ModelContratoProduto>             listaContratoProdutos    = daoContratoRepository.getListaContratoProduto          ( modelContrato.getId_contrato()           );
					List<ModelListAditivoProduto>          listAditivoProdutos      = daoAditivoModal.buscarListaAditivoProdutosContratados  ( modelContrato.getId_contrato()           );
    				List<ModelAitivoRecursoModal>          modelAitivoRecursoModals = daoAditivoModalRecurso.getListaAditivoRecursoID        ( modelContrato.getId_contrato()           );
    				List<ModelHistoricoUpgrade>            listaHistoricoUpgrade    = daoAditivoModal.getListaHistoricoUpgrade               ( modelContrato.getId_contrato()           );
 
					request.setAttribute("msg", "Contrto do Cliente em Edição" );

				    request.setAttribute("modelContrato"           , modelContrato            );
				    request.setAttribute("listaContratoProdutos"   , listaContratoProdutos    );
				    request.setAttribute("listaRecursoContratos"   , listaRecursoContratos    );
   					request.setAttribute("modelAitivoRecursoModals", modelAitivoRecursoModals );
				    request.setAttribute("listAditivoProdutos"     , listAditivoProdutos      ); 
				    request.setAttribute("listaHistoricoUpgrade"   , listaHistoricoUpgrade    );
				    
				    request.getRequestDispatcher("principal/contratosOld.jsp").forward(request, response);
						
				} 
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarContratoCliente") ) {
				String                 idContratoCliente      = request.getParameter("idContratoCliente");
				ModelContrato          modelContrato          = new ModelContrato();
				DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
				DAOAditivoModal        daoAditivoModal        = new DAOAditivoModal();
				
				if( idContratoCliente != null && !idContratoCliente.isEmpty() ) {
					if(daoContratoRepository.isExstContratoAtivo( Long.parseLong(idContratoCliente) ) ) {
					    
						modelContrato = daoContratoRepository.getContratoAtivoCliente( Long.parseLong(idContratoCliente)  );
						List<ModelListaRecursoContratoAditivo> listaRecursoContratos    = daoContratoRepository.getListaRecursoContrato        ( Long.parseLong(idContratoCliente.trim()) );
						List<ModelContratoProduto>             listaContratoProdutos    = daoContratoRepository.getListaContratoProduto        ( modelContrato.getId_contrato()           );
						List<ModelListAditivoProduto>          listAditivoProdutos      = daoAditivoModal.buscarListaAditivoProdutosContratados( modelContrato.getId_contrato()           );
    					List<ModelAitivoRecursoModal>          modelAitivoRecursoModals = daoAditivoModalRecurso.getListaAditivoRecursoID      ( modelContrato.getId_contrato()           );
    					List<ModelHistoricoUpgrade>            listaHistoricoUpgrade    = daoAditivoModal.getListaHistoricoUpgrade             ( modelContrato.getId_contrato()           );
 
					    request.setAttribute("msg", "Contrto do Cliente em Edição" );

					    request.setAttribute("modelContrato"           , modelContrato            );
					    request.setAttribute("listaContratoProdutos"   , listaContratoProdutos    );
					    request.setAttribute("listaRecursoContratos"   , listaRecursoContratos    );
	   					request.setAttribute("modelAitivoRecursoModals", modelAitivoRecursoModals );
					    request.setAttribute("listAditivoProdutos"     , listAditivoProdutos      ); 
					    request.setAttribute("listaHistoricoUpgrade"   , listaHistoricoUpgrade    );
					    
					    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
						
					}else {
						ModelCliente dadosCliente = daoClienteRepository.getClienteID( Long.parseLong(idContratoCliente.trim()) );
						modelContrato.setId_cliente(dadosCliente.getId_cliente());
						modelContrato.setNomeCliente( dadosCliente.getRazao_social() );
						
						//idContrato       != null && !idContrato.isEmpty()
						if(modelContrato.getComissao() == null || modelContrato.getComissao().isEmpty() ) modelContrato.setComissao("Não");
											
					    request.setAttribute("msg", "O Cliente não possui contrato Ativo" );
					    request.setAttribute("modelContrato", modelContrato);
					    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
	
					}
				} 
			/********************************************************************************************/
			/*                                                                                          */
			/*            Inicio da regra de Negocio para cadastro de Recurso                           */
			/*                                                                                          */
			/********************************************************************************************/
		    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaRecursoInicial") ) {
				String idcontrato = request.getParameter("idcontrato");
			    ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();

			    if( idcontrato != null && !idcontrato.isEmpty() ) {
				    ModelRecursoContratoCliente modelRecursoContratoCliente = this.montaRecursoContratoCliente(Long.parseLong(idcontrato.trim()), request); //daoRecusoContrato.getTelaInical( Long.parseLong(idcontrato) );
				    if( !modelRecursoContratoCliente.isNovo() ) {
				    	modelRecursoContrato.setId_contrato( modelRecursoContratoCliente.getId_contrato()  );
				    	modelRecursoContrato.setId_cliente ( modelRecursoContratoCliente.getId_cliente()   );
				    	modelRecursoContrato.setNomeCliente( modelRecursoContratoCliente.getRazao_social() );
				    	modelRecursoContrato.setId_nuvem   ( modelRecursoContratoCliente.getId_nuvem()     );
				    	modelRecursoContrato.setNuvem      ( modelRecursoContratoCliente.getNuvem()        );
				    	modelRecursoContrato.setTag_vcenter( this.generateVerificationCode( 6 )            );
				    }
			    }

			    request.setAttribute("modelRecursoContrato", modelRecursoContrato);
			    
			    Gson gson = new Gson();
			    String lista = gson.toJson(modelRecursoContrato);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("generateCodeTagVcenter") ) {
				
				String CodeTagVcenter = this.generateVerificationCode( 6 );
			    Gson gson = new Gson();
			    String lista = gson.toJson(CodeTagVcenter);
			    response.getWriter().write(lista);
			    
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectFamiliaModal") ) {
				   String idNuvem = request.getParameter("idNuvem");
				   if( idNuvem != null && !idNuvem.isEmpty() ){
					   DAOFamiliaFlavors beanFamilia = new DAOFamiliaFlavors();
					   List<ModelFamiliaFlavors> familiaFlavorses = beanFamilia.listaFamiliaFlavors( Long.parseLong( idNuvem.trim() ) );
					   Gson gson = new Gson();
					   String lista = gson.toJson(familiaFlavorses);
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
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontatabelaResutadoRecurso") ) {
				
				String idContrato = request.getParameter("idContrato");
				
				if( idContrato != null && !idContrato.isEmpty() ) {

					DAORecursoContratoAditivoRel    daoRecursoContratoRel = new DAORecursoContratoAditivoRel();
					List<ModelRecursoContratoAditivoRel> modelRecContRels = daoRecursoContratoRel.getListaRecursoIdContrato( Long.parseLong(idContrato.trim()) );
					request.setAttribute("modelRecContRels", modelRecContRels);
					
				//	System.out.println(mdFamilias);
					Gson gson = new Gson();
					String lista = gson.toJson(modelRecContRels);
					response.getWriter().write(lista);
				}
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaRecursoEdit") ) {
				
//				String idContrato = request.getParameter("idContrato");
	   			String id_Recurso = request.getParameter("idRecurso");
	   			ModelRecursoContrato modelRecursoContrato      = this.montaRecursoContrato( Long.parseLong(id_Recurso), request);             // Monta Estrutura de Recurso para Montar na tela
				ModelFamiliaFlavors mdFamilias                 = this.montaFamilia( modelRecursoContrato.getId_familia_flavors() , request ); // Monta Estrutura de Familia para Montar na tela
//				this.montaRecursoContratoRel( Long.parseLong(idContrato), request );         // Monta Lista de Estrutura Recursos para Montar na tela
				
				modelRecursoContrato.setCpu  ( mdFamilias.getCpu().trim()   );
				modelRecursoContrato.setRam  ( mdFamilias.getRam().trim()   );
				modelRecursoContrato.setValor( mdFamilias.getValor().trim() );
				
				Gson gson = new Gson();
				String lista = gson.toJson(modelRecursoContrato);
				response.getWriter().write(lista);
				

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("AddRecursoModal") ) {
				DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();	
				String msg = "Operacao realizada com sucesso!";

		        String statusRecurso  = request.getParameter("statusRecurso"  ); // 1
		        String retencaoBackup = request.getParameter("retencaoBackup" ); // 2
		        String tipoDisco      = request.getParameter("tipoDisco"      ); // 3
		        String so             = request.getParameter("so"             ); // 4
		        String ambiente       = request.getParameter("ambiente"       ); // 5
		        String tipoServico    = request.getParameter("tipoServico"    ); // 6
		        String familiaFlavors = request.getParameter("familiaFlavors" ); // 7
		        String idContrato     = request.getParameter("idContrato"     ); // 8
		        String idCliente      = request.getParameter("idCliente"      ); // 9
		        String nomeCliente    = request.getParameter("nomeCliente"    ); // 10
		        String tagVcenter     = request.getParameter("tagVcenter"     ); // 11
		        String cpu            = request.getParameter("cpu"            ); // 12
		        String ram            = request.getParameter("ram"            ); // 13
		        String valor          = request.getParameter("valor"          ); // 14
		        String vlrRecurso     = request.getParameter("vlrRecurso"     ); // 15
		        String obs            = request.getParameter("obs"            ); // 16
		        String idRecurso      = request.getParameter("idRecurso"      ); // 17
		        String dtCadastro     = request.getParameter("dtCadastro"     ); // 18
		        String hostname       = request.getParameter("hostname"       ); // 19
		        String tamanhoDisco   = request.getParameter("tamanhoDisco"   ); // 20
		        String primaryIP      = request.getParameter("primaryIP"      ); // 21
		        String eipVcenter     = request.getParameter("eipVcenter"     ); // 22
		        String hostVcenter    = request.getParameter("hostVcenter"    ); // 23
		        String nuvemSite      = daoRecusoContrato.getNuvemSiteIdContrato( Long.parseLong(idContrato) );
				String textoSplit []  = nuvemSite.split(";");
				String idNuvem        = textoSplit[0];
				String idSite         = textoSplit[1];

			    if(valor != null && !valor.isEmpty()) {
				   valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			       valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			    }
			    if(vlrRecurso != null && !vlrRecurso.isEmpty()) {
			    	vlrRecurso = Normalizer.normalize(vlrRecurso, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
			    	vlrRecurso = vlrRecurso.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			    }
			    
			    HttpServletRequest req              = (HttpServletRequest) request;
			    HttpSession session                 = req.getSession();
			    String usuarioLogado                = (String) session.getAttribute("usuario");
			    String urlParaAutent                = req.getServletPath(); // URL que esta sendo acessada.						
			    // Validar se esta logado, se nao redireciona para a tela de login
			    if( usuarioLogado == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
			        RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
			        request.setAttribute("msg", "Por favor, realizar o login!");
			        redireciona.forward(request, response);
			        return ;// para a execucao e redireciona para o login.
			    }		

				ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();
				
				modelRecursoContrato.setId_recurso        ( idRecurso      != null && !idRecurso.isEmpty()      ? Long.parseLong(idRecurso.trim())     : null );
				modelRecursoContrato.setDt_cadastro       ( dtCadastro     != null && !dtCadastro.isEmpty()     ? dtCadastro.trim()                    : null );
				modelRecursoContrato.setId_cliente        ( idCliente      != null && !idCliente.isEmpty()      ? Long.parseLong(idCliente.trim())     : null );
				modelRecursoContrato.setId_contrato       ( idContrato     != null && !idContrato.isEmpty()     ? Long.parseLong(idContrato.trim())    : null );
				modelRecursoContrato.setId_status_recurso ( statusRecurso  != null && !statusRecurso.isEmpty()  ? Long.parseLong(statusRecurso.trim()) : null );
				modelRecursoContrato.setId_retencao_backup( retencaoBackup != null && !retencaoBackup.isEmpty() ? Long.parseLong(retencaoBackup.trim()): null );
				modelRecursoContrato.setId_tipo_disco     ( tipoDisco      != null && !tipoDisco.isEmpty()      ? Long.parseLong(tipoDisco.trim())     : null );
				modelRecursoContrato.setId_so             ( so             != null && !so.isEmpty()             ? Long.parseLong(so.trim())            : null );
				modelRecursoContrato.setId_ambiente       ( ambiente       != null && !ambiente.isEmpty()       ? Long.parseLong(ambiente.trim())      : null );
				modelRecursoContrato.setId_tipo_servico   ( tipoServico    != null && !tipoServico.isEmpty()    ? Long.parseLong(tipoServico.trim())   : null );
				modelRecursoContrato.setTag_vcenter       ( tagVcenter     != null && !tagVcenter.isEmpty()     ? tagVcenter.trim()                    : null );
				modelRecursoContrato.setId_familia_flavors( familiaFlavors != null && !familiaFlavors.isEmpty() ? Long.parseLong(familiaFlavors.trim()): null );
				modelRecursoContrato.setCpu               ( cpu            != null && !cpu.isEmpty()            ? cpu.trim()                           : null );
				modelRecursoContrato.setRam               ( ram            != null && !ram.isEmpty()            ? ram.trim()                           : null );
				modelRecursoContrato.setValor             ( valor          != null && !valor.isEmpty()          ? valor.trim()                         : null );
				modelRecursoContrato.setValor_recurso     ( vlrRecurso     != null && !vlrRecurso.isEmpty()     ? vlrRecurso.trim()                    : null );				
				modelRecursoContrato.setObs               ( obs            != null && !obs.isEmpty()            ? obs.trim()                           : null );
				modelRecursoContrato.setNomeCliente       ( nomeCliente    != null && !nomeCliente.isEmpty()    ? nomeCliente.trim()                   : null );
				modelRecursoContrato.setHostname          ( hostname       != null && !hostname.isEmpty()       ? hostname.trim()                      : null );
				modelRecursoContrato.setTamanhoDisco      ( tamanhoDisco   != null && !tamanhoDisco.isEmpty()   ? tamanhoDisco.trim()                  : null );
				modelRecursoContrato.setPrimaryIP         ( primaryIP      != null && !primaryIP.isEmpty()      ? primaryIP.trim()                     : null );
				modelRecursoContrato.setId_nuvem          ( idNuvem        != null && !idNuvem.isEmpty()        ? Long.parseLong(idNuvem.trim())       : null );
				modelRecursoContrato.setId_site           ( idSite         != null && !idSite.isEmpty()         ? Long.parseLong(idSite.trim())        : null );
				modelRecursoContrato.setEip_vcenter       ( eipVcenter     != null && !eipVcenter.isEmpty()     ? eipVcenter.trim()                    : null );
				modelRecursoContrato.setHost_vcenter      ( hostVcenter    != null && !hostVcenter.isEmpty()    ? hostVcenter.trim()                   : null );

				if(modelRecursoContrato.isNovo()) {
		    		msg = "Registro gravado com sucesso!";
		    	}else msg = "Registro atualizado com sucesso!";
	            
		    	// Grava ou Inseri as informacoes na Base.
				modelRecursoContrato = daoRecusoContrato.gravarRecursoContrato(modelRecursoContrato);

				if( modelRecursoContrato != null ) {
					ModelFamiliaFlavors mdFamilias = this.montaFamilia( modelRecursoContrato.getId_familia_flavors() , request );
					modelRecursoContrato.setCpu  ( mdFamilias.getCpu()   );
					modelRecursoContrato.setRam  ( mdFamilias.getRam()   );
					modelRecursoContrato.setValor( mdFamilias.getValor() );
//					this.montaRecursoContratoRel ( modelRecursoContrato.getId_contrato(), request );
				}
			    request.setAttribute("msg", msg );
				Gson gson = new Gson();
				String lista = gson.toJson(modelRecursoContrato);
				response.getWriter().write(lista);

//			    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
			} else 
				//******************************************************************************************//
				//                                                                                          //
				//            Aqui sera tratada as informacoes do ADD Aditivo.                              //
				//                                                                                          //
				//******************************************************************************************//
				if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("AddAditivoModal") ) {
					try {
						// informaoes para todos!
						String dtInicio         = request.getParameter( "dtInicio"            ); // dt_inicio
			            String dtFinal          = request.getParameter( "dtFinal"             ); // dt_final
			            String idTipoAditivo    = request.getParameter( "idTipoAditivo"       ); // id_tipo_aditivo
			            String idStatus         = request.getParameter( "idStatus"            ); // id_status_aditivo
			            String idAditivo        = request.getParameter( "idAditivo"           ); // id_aditivado
			            String dtCriacao        = request.getParameter( "dtCriacao"           ); // Dt_cadastro
			            String observacao       = request.getParameter( "observacao"          ); // observacao
			            String vlrTotal         = request.getParameter( "vlrTotal"            ); // vlr_total_adit
						// Incremento de Serviços
			            String servicoCont      = request.getParameter( "servicoCont"         ); // servico_aditivado
			            String qtyServico       = request.getParameter( "qtyServico"          ); // qty_serv_contratado
			            String vlrUnitServ      = request.getParameter( "vlrUnitServ"         ); // valor_serv_contratado
			            String descServCont     = request.getParameter( "descServCont"        ); // desc_serv_contratado
						// Contratação do DR
			            String produtoDR        = request.getParameter( "produtoDR"           ); // qty_Produto
			            String tpDR             = request.getParameter( "tpDR"                ); // qty_Produto
			            String qtyDR            = request.getParameter( "qtyDR"               ); // qty_Produto
			            String vlrUnitDR        = request.getParameter( "vlrUnitDR"           ); // qty_Produto
			            // Incremento de Usuário
			            String qtyUser          = request.getParameter( "qtyUser"             ); // qty_Produto
			            String vlrUnitUser      = request.getParameter( "vlrUnitUser"         ); // qty_Produto
			            String produtoUser      = request.getParameter( "produtoUser"         ); // qty_Produto
			 			// Contratação de VPN
			            String produtoVPN       = request.getParameter( "produtoVPN"          ); // qty_Produto
			            String tpVPN            = request.getParameter( "tpVPN"               ); // qty_Produto
			            String qtyVPN           = request.getParameter( "qtyVPN"              ); // qty_Produto
			            String vlrUnitVPN       = request.getParameter( "vlrUnitVPN"          ); // qty_Produto
			            // Formulario principal
			            String idContrato       = request.getParameter( "idContrato"          ); // id_contrato
			            // Informacao do tipo de Moeda Contratada para o Atidivo.
			            String idMoedaMA        = request.getParameter( "idMoedaMA"           ); // id_contrato
			            String vlrConvertMA     = request.getParameter( "vlrConvertMA"        ); // id_contrato
			            String vlrCotacaoMA     = request.getParameter( "vlrCotacaoMA"        ); // id_contrato
			            String idHubSpotAdi     = request.getParameter( "idHubSpotAdi"        ); // id_contrato
			            String idRascunho       = request.getParameter( "idRascunho"          ); // id_rascunho
			            String mRascunho        = request.getParameter( "mRascunho"           ); // motivoRascunho
			            String TempoContratoMA  = request.getParameter( "TempoContratoMA"     ); // id_rascunho
			            String obsVigenciaMA    = request.getParameter( "observacaoVigenciaMA"); // motivoRascunho
			            
			            String comissaoMA         = request.getParameter( "comissaoMA"         ); // id_rascunho
			            String idValorSetupMA     = request.getParameter( "idValorSetupMA"     ); // motivoRascunho
			            String qtyMesesContratoMA = request.getParameter( "qtyMesesContratoMA" ); // id_rascunho
			            String vlrParcelasMA      = request.getParameter( "vlrParcelasMA"      ); // motivoRascunho
			            String qtyParcSetupMA     = request.getParameter( "qtyParcSetupMA"     ); // id_rascunho

						if(vlrParcelasMA != null && !vlrParcelasMA.isEmpty()) {
							vlrParcelasMA = Normalizer.normalize(vlrParcelasMA, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrParcelasMA.indexOf(" ") >= 0 )
						    	vlrParcelasMA = vlrParcelasMA.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								vlrParcelasMA = vlrParcelasMA.replaceAll("\\.", "").replaceAll("\\,", ".");
						}

						if(idValorSetupMA != null && !idValorSetupMA.isEmpty()) {
							idValorSetupMA = Normalizer.normalize(idValorSetupMA, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(idValorSetupMA.indexOf(" ") >= 0 )
						    	idValorSetupMA = idValorSetupMA.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								idValorSetupMA = idValorSetupMA.replaceAll("\\.", "").replaceAll("\\,", ".");
						}

						if(vlrTotal != null && !vlrTotal.isEmpty()) {
							vlrTotal = Normalizer.normalize(vlrTotal, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrTotal.indexOf(" ") >= 0 )
						       vlrTotal = vlrTotal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
							   vlrTotal = vlrTotal.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
						
						if(vlrConvertMA != null && !vlrConvertMA.isEmpty()) {
							vlrConvertMA = Normalizer.normalize(vlrConvertMA, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrConvertMA.indexOf(" ") >= 0 )
						    	vlrConvertMA = vlrConvertMA.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								vlrConvertMA = vlrConvertMA.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
						
						if(vlrCotacaoMA != null && !vlrCotacaoMA.isEmpty()) {
							vlrCotacaoMA = Normalizer.normalize(vlrCotacaoMA, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrCotacaoMA.indexOf(" ") >= 0 )
						    	vlrCotacaoMA = vlrCotacaoMA.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								vlrCotacaoMA = vlrCotacaoMA.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
					    
					    if(vlrUnitServ != null && !vlrUnitServ.isEmpty()) {
					       vlrUnitServ = Normalizer.normalize(vlrUnitServ, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
					       vlrUnitServ = vlrUnitServ.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
						}
					    if(vlrUnitDR != null && !vlrUnitDR.isEmpty()) {
					       vlrUnitDR = Normalizer.normalize(vlrUnitDR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
					       vlrUnitDR = vlrUnitDR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
						}
					    
					    if(vlrUnitUser != null && !vlrUnitUser.isEmpty()) {
					       vlrUnitUser = Normalizer.normalize(vlrUnitUser, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
					       vlrUnitUser = vlrUnitUser.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
						}
					    if(vlrUnitVPN != null && !vlrUnitVPN.isEmpty()) {
					       vlrUnitVPN = Normalizer.normalize(vlrUnitVPN, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
					       vlrUnitVPN = vlrUnitVPN.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
						}

					    ModelAditivoModal modelAditivoModal = new ModelAditivoModal();
					    
					    HttpServletRequest req              = (HttpServletRequest) request;
						HttpSession session                 = req.getSession();
					    String usuarioLogado                = (String) session.getAttribute("usuario");
						String urlParaAutent                = req.getServletPath(); // URL que esta sendo acessada.						
						// Validar se esta logado, se nao redireciona para a tela de login
						if( usuarioLogado == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
							RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
							request.setAttribute("msg", "Por favor, realizar o login!");
							redireciona.forward(request, response);
							return ;// para a execucao e redireciona para o login.
						}					    

					    modelAditivoModal.setId_aditivado         ( idAditivo       != null && !idAditivo.isEmpty()       ? Long.parseLong( idAditivo.trim() )     : null  );
					    modelAditivoModal.setDt_cadastro          ( dtCriacao       != null && !dtCriacao.isEmpty()       ? dtCriacao.trim()                       : null  );
					    modelAditivoModal.setDt_inicio            ( dtInicio        != null && !dtInicio.isEmpty()        ? dtInicio.trim()                        : null  );
					    modelAditivoModal.setDt_final             ( dtFinal         != null && !dtFinal.isEmpty()         ? dtFinal.trim()                         : null  );
					    modelAditivoModal.setId_tipo_aditivo      ( idTipoAditivo   != null && !idTipoAditivo.isEmpty()   ? Long.parseLong( idTipoAditivo.trim() ) : null  );
					    modelAditivoModal.setId_status_aditivo    ( idStatus        != null && !idStatus.isEmpty()        ? Long.parseLong( idStatus.trim() )      : null  );
					    modelAditivoModal.setObservacao           ( observacao      != null && !observacao.isEmpty()      ? observacao.trim()                      : null  );
					    modelAditivoModal.setVlr_total_adit       ( vlrTotal        != null && !vlrTotal.isEmpty()        ? vlrTotal.trim()                        : null  );
					    modelAditivoModal.setId_contrato          ( idContrato      != null && !idContrato.isEmpty()      ? Long.parseLong( idContrato.trim() )    : null  );
					    modelAditivoModal.setHubspot_aditivo      ( idHubSpotAdi    != null && !idHubSpotAdi.isEmpty()    ?  idHubSpotAdi.trim()                   : null  );
					    modelAditivoModal.setId_rascunho          ( idRascunho      != null && !idRascunho.isEmpty()      ? Long.parseLong( idRascunho.trim() )    : null  );
					    modelAditivoModal.setMotivoRascunho       ( mRascunho       != null && !mRascunho.isEmpty()       ? mRascunho.trim()                       : null  );
					    // Referencia a Comissao
					    modelAditivoModal.setComissao_adit( (comissaoMA != null && !comissaoMA.isEmpty()? Integer.parseInt(comissaoMA)== 1 : false) ? true                        : false);
					    modelAditivoModal.setValor_setup_adit        ( idValorSetupMA     != null && !idValorSetupMA.isEmpty()     ? idValorSetupMA.trim()                        : null );
					    modelAditivoModal.setQty_mese_setup_adit     ( qtyMesesContratoMA != null && !qtyMesesContratoMA.isEmpty() ? Integer.valueOf( qtyMesesContratoMA.trim() ) : 0    );
					    modelAditivoModal.setValor_parcela_setup_adit( vlrParcelasMA      != null && !vlrParcelasMA.isEmpty()      ? vlrParcelasMA.trim()                         : null );
					    modelAditivoModal.setQty_parcela_setup_adit  ( qtyParcSetupMA     != null && !qtyParcSetupMA.isEmpty()     ? Integer.valueOf( qtyParcSetupMA.trim() )     : 0    );
					    
					    // Referencia a servico_aditivado
					    modelAditivoModal.setId_servico_contratado( servicoCont     != null && !servicoCont.isEmpty()     ? Long.parseLong( servicoCont.trim() )   : 0L    );
					    modelAditivoModal.setQty_serv_contratado  ( qtyServico      != null && !qtyServico.isEmpty()      ? qtyServico.trim()                      : null  );
					    modelAditivoModal.setValor_serv_contratado( vlrTotal        != null && !vlrTotal.isEmpty()        ? vlrTotal.trim()                        : null  );
					    modelAditivoModal.setValor_unit_serv_cont ( vlrUnitServ     != null && !vlrUnitServ.isEmpty()     ? vlrUnitServ.trim()                     : null  );
					    modelAditivoModal.setDesc_serv_contratado ( descServCont    != null && !descServCont.isEmpty()    ? descServCont.trim()                    : null  );
					    modelAditivoModal.setValor_convertido     ( vlrConvertMA    != null && !vlrConvertMA.isEmpty()    ? vlrConvertMA.trim()                    : null  );
					    modelAditivoModal.setCotacao_moeda        ( vlrCotacaoMA    != null && !vlrCotacaoMA.isEmpty()    ? vlrCotacaoMA.trim()                    : null  );
					    modelAditivoModal.setId_moeda             ( idMoedaMA       != null && !idMoedaMA.isEmpty()       ? Long.parseLong( idMoedaMA.trim() )     : 0L    );
					    modelAditivoModal.setLogin_cadastro       ( usuarioLogado   != null && !usuarioLogado.isEmpty()   ? usuarioLogado.trim()                   : null  );			    
					    modelAditivoModal.setId_tempo_contrato    ( TempoContratoMA != null && !TempoContratoMA.isEmpty() ? Long.parseLong(TempoContratoMA.trim()) : 1L    );
					    modelAditivoModal.setObservacao_vigencia  ( obsVigenciaMA   != null && !obsVigenciaMA.isEmpty()   ? obsVigenciaMA.trim()                   : null  );
					    
					    Double vlrCustoTotal = 0.0;					    
					    switch ( modelAditivoModal.getId_tipo_aditivo().intValue() ){
							case 3: // Contratação de VPN
								modelAditivoModal.setId_produto       ( produtoVPN != null && !produtoVPN.isEmpty() ? Long.parseLong( produtoVPN.trim() ) : null );
								modelAditivoModal.setId_tipo_produto  ( tpVPN      != null && !tpVPN.isEmpty()      ? Long.parseLong( tpVPN.trim()      ) : null );
								modelAditivoModal.setQty_produto      ( qtyVPN     != null && !qtyVPN.isEmpty()     ? qtyVPN.trim()                       : null );
								modelAditivoModal.setValor_produto    ( vlrUnitVPN != null && !vlrUnitVPN.isEmpty() ? vlrUnitVPN.trim()                   : null );
								modelAditivoModal.setValor_unitario   ( vlrUnitVPN != null && !vlrUnitVPN.isEmpty() ? vlrUnitVPN.trim()                   : null );
								modelAditivoModal.setVlr_total_produto( vlrTotal   != null && !vlrTotal.isEmpty()   ? vlrTotal.trim()                     : null );
								vlrCustoTotal = Double.valueOf(qtyVPN) * Double.valueOf(vlrUnitVPN) ;
								modelAditivoModal.setCusto_total(Double.toString(vlrCustoTotal));								
								break;
							case 4: // Contratação do DR
								modelAditivoModal.setId_produto        ( produtoDR != null && !produtoDR.isEmpty() ? Long.parseLong( produtoDR.trim() ) : null );
								modelAditivoModal.setId_tipo_produto   ( tpDR      != null && !tpDR.isEmpty()      ? Long.parseLong( tpDR.trim()      ) : null );
								modelAditivoModal.setQty_produto       ( qtyDR     != null && !qtyDR.isEmpty()     ? qtyDR.trim()                       : null );
								modelAditivoModal.setValor_produto     ( vlrUnitDR != null && !vlrUnitDR.isEmpty() ? vlrUnitDR.trim()                   : null );
								modelAditivoModal.setValor_unitario    ( vlrUnitDR != null && !vlrUnitDR.isEmpty() ? vlrUnitDR.trim()                   : null );
								modelAditivoModal.setVlr_total_produto ( vlrTotal  != null && !vlrTotal.isEmpty()  ? vlrTotal.trim()                    : null ); 
								vlrCustoTotal = Double.valueOf(qtyDR) * Double.valueOf(vlrUnitDR) ;
								modelAditivoModal.setCusto_total(Double.toString(vlrCustoTotal));								
								break;
							case 8: // Incremento de Usuário
								modelAditivoModal.setId_produto       ( produtoUser != null && !produtoUser.isEmpty() ? Long.parseLong( produtoUser.trim() ) : null );
								modelAditivoModal.setId_tipo_produto  ( null );
								modelAditivoModal.setQty_produto      ( qtyUser     != null && !qtyUser.isEmpty()     ? qtyUser.trim()                       : null );
								modelAditivoModal.setValor_produto    ( vlrUnitUser != null && !vlrUnitUser.isEmpty() ? vlrUnitUser.trim()                   : null );
								modelAditivoModal.setVlr_total_produto( vlrTotal    != null && !vlrTotal.isEmpty()    ? vlrTotal.trim()                      : null ); 
								vlrCustoTotal = Double.valueOf(qtyUser) * Double.valueOf(vlrUnitUser) ;
								modelAditivoModal.setCusto_total(Double.toString(vlrCustoTotal));								
								break;
						
						}

						DAOAditivoModal daoAditivoModal = new DAOAditivoModal();
						String msnErro = "INSERT";

						if (modelAditivoModal.getId_produto() != null && modelAditivoModal.getId_aditivado() != null ) {
						   if ( daoAditivoModal.isExistProduto( modelAditivoModal.getId_produto(), modelAditivoModal.getId_aditivado()) != 0 )
							    msnErro = "UPDATE";
						}else 
						   if (modelAditivoModal.getId_servico_contratado() != null && modelAditivoModal.getId_aditivado() != null ) {
							   if ( daoAditivoModal.isExistServico( modelAditivoModal.getId_servico_contratado(), modelAditivoModal.getId_aditivado() ) != 0 )  
								    msnErro = "UPDATE";
						}

				    	// Grava ou Inseri as informacoes na Base.
						ModelListAditivoProduto modelListAditivoProduto = daoAditivoModal.gravarAditivo( modelAditivoModal, msnErro );

						List<ModelListAditivoProduto> modelListAditivoProdutos = daoAditivoModal.buscarListaAditivoProdutosContratados( modelAditivoModal.getId_contrato() );
						
						request.setAttribute("modelListAditivoProduto" , modelListAditivoProduto  );
						request.setAttribute("modelListAditivoProdutos", modelListAditivoProdutos );
	
					    Gson gson = new Gson();
						String lista = gson.toJson(modelListAditivoProdutos);
						response.getWriter().write(lista);						
			    	}catch(Exception e){
						e.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
						request.setAttribute("msg", e.getMessage());
						requestDispatcher.forward(request, response);			
					}
					

				} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listaAditivosInicial") ) {
					String idContrato    = request.getParameter( "idContrato" ); // id_contrato
					DAOAditivoModal daoAditivoModal = new DAOAditivoModal();
					List<ModelListAditivoProduto> modelListAditivoProdutos = daoAditivoModal.buscarListaAditivoProdutosContratados( Long.parseLong( idContrato.trim() ) );
					
					request.setAttribute("modelListAditivoProdutos", modelListAditivoProdutos );

				    Gson gson = new Gson();
					String lista = gson.toJson(modelListAditivoProdutos);
					response.getWriter().write(lista);
					
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectProdudoModal") ) {
					
					String idproduto    = request.getParameter( "idproduto" ); // id_contrato
					
					DAOProduto daoProduto = new DAOProduto();
					List<ModelTipoProduto> modelTipoProdutos = new ArrayList<ModelTipoProduto>();
					modelTipoProdutos = daoProduto.getListaTipoProdutoID( Long.parseLong( idproduto ) );

					Gson gson = new Gson();
					String lista = gson.toJson(modelTipoProdutos);
					response.getWriter().write(lista);
				
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("getValorProdutoID") ) {
					
					String idproduto    = request.getParameter( "idproduto" ); // id_contrato

					DAOProduto daoProduto = new DAOProduto();
					String vlrUnit = daoProduto.getValorProdutoId(Long.parseLong( idproduto ));

					Gson gson = new Gson();
					String lista = gson.toJson(vlrUnit);
					response.getWriter().write(lista);
				
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("getValorServicoID") ) {
					
					String idServico    = request.getParameter( "idServico" ); // id_contrato

					DAOProduto daoProduto = new DAOProduto();
					String vlrUnit = daoProduto.getValorServicoId(Long.parseLong( idServico ));

					Gson gson = new Gson();
					String lista = gson.toJson(vlrUnit);
					response.getWriter().write(lista);
				
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("CalculoVlrTotal") ) {
					
					String vlrUnitario = request.getParameter( "vlrUnitario" ); // id_contrato
					String qtyUnitario = request.getParameter( "qtyUnitario" ); // id_contrato
					
				    if(vlrUnitario != null && !vlrUnitario.isEmpty()) {
				    	vlrUnitario = Normalizer.normalize(vlrUnitario, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
				    	vlrUnitario = vlrUnitario.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
					}
				    Double vlrCalculado = Double.valueOf( vlrUnitario ) * Double.valueOf( qtyUnitario );

					Locale localeBR       = new Locale("pt","BR");
					NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

					Gson gson = new Gson();
					String lista = gson.toJson(dinheiro.format(vlrCalculado));
					response.getWriter().write(lista);
				
				} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditarAditivo") ) {
					String idAditivo                = request.getParameter("idAditivo");
					String idTipoAditivo            = request.getParameter("idTipoAditivo");
					String idProduto                = request.getParameter("idProduto");
	
					ModelAditivoModal modelAditivoModal  = new ModelAditivoModal();
					ModelListAditivoProduto modelListAditivoProduto = new ModelListAditivoProduto();
					DAOAditivoModal daoAditivoModal = new DAOAditivoModal();

					modelAditivoModal.setId_aditivado         ( idAditivo     != null && !idAditivo.isEmpty()     ? Long.parseLong( idAditivo.trim()     ) : null );
					modelAditivoModal.setId_tipo_aditivo      ( idTipoAditivo != null && !idTipoAditivo.isEmpty() ? Long.parseLong( idTipoAditivo.trim() ) : null );
					modelAditivoModal.setId_produto           ( idProduto     != null && !idProduto.isEmpty()     ? Long.parseLong( idProduto.trim()     ) : null );
					modelAditivoModal.setId_servico_contratado( idProduto     != null && !idProduto.isEmpty()     ? Long.parseLong( idProduto.trim()     ) : null );

					modelListAditivoProduto = daoAditivoModal.consultaAdtivoPorProdutoID( modelAditivoModal );
	
					request.setAttribute("modelListAditivoProduto" , modelListAditivoProduto );
					Gson gson    = new Gson();
					String lista = gson.toJson( modelListAditivoProduto );
					response.getWriter().write(lista);
	
				}
			    //******************************************************************************************//
			    //                                                                                          //
			    //            Aqui sera tratada as informacoes do ADD Aditivo Recurso.                      //
			    //                                                                                          //
			    //******************************************************************************************//
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTelaInicialAditivoRecurso") ) {
					
					String tagVcenter = "";
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					do {
						tagVcenter = this.generateVerificationCode( 6 );
					}while( daoAditivoModalRecurso.isTagVcenter(tagVcenter) );
					Gson gson    = new Gson();
					String lista = gson.toJson( tagVcenter );
					response.getWriter().write(lista);
				
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listaHistoricoTrocaFamilia") ) {
					String idContrato                  = request.getParameter("idContrato");
					if (idContrato != null && !idContrato.isEmpty() ) {
					    DAOAditivoModal daoAditivoModal  = new DAOAditivoModal();
					    List<ModelHistoricoUpgrade> listaHistoricoUpgrade = daoAditivoModal.getListaHistoricoUpgrade( Long.parseLong( idContrato.trim() ) );
					    request.setAttribute("listaHistoricoUpgrade", listaHistoricoUpgrade );
					    Gson gson    = new Gson();
					    String lista = gson.toJson( listaHistoricoUpgrade );
					    response.getWriter().write(lista);
					}
				
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("AddAditivoRecursoModal") ) {
//					try {
						String idAditivoMAR                = request.getParameter("idAditivoMAR"               ); // 1
						String dtCriacaoMAR                = request.getParameter("dtCriacaoMAR"               ); // 2
						String idTipoAditivoMAR            = request.getParameter("idTipoAditivoMAR"           ); // 3
						String idStatusMAR                 = request.getParameter("idStatusMAR"                ); // 4
						String vlrTotalMAR                 = request.getParameter("vlrTotalMAR"                ); // 5
						String idTipoContratacaoMAR        = request.getParameter("idTipoContratacaoMAR"       ); // 6
						String idSuporteMAR                = request.getParameter("idSuporteMAR"               ); // 7
						String idMonitoramentoMAR          = request.getParameter("idMonitoramentoMAR"         ); // 8
						String idBackupMAR                 = request.getParameter("idBackupMAR"                ); // 9
						String idRetencaoBackupMAR         = request.getParameter("idRetencaoBackupMAR"        ); // 10
						String idStatusRecursoMAR          = request.getParameter("idStatusRecursoMAR"         ); // 11
						String tagVcenterMAR               = request.getParameter("tagVcenterMAR"              ); // 12
						String idTipoDiscoMAR              = request.getParameter("idTipoDiscoMAR"             ); // 13
						String idSoMAR                     = request.getParameter("idSoMAR"                    ); // 14
						String idAmbienteMAR               = request.getParameter("idAmbienteMAR"              ); // 15
						String idTipoServicoMAR            = request.getParameter("idTipoServicoMAR"           ); // 16
						String hostNameModalRecursoMAR     = request.getParameter("hostnameModalRecursoMAR"    ); // 17
						String tamanhoDiscoModalRecursoMAR = request.getParameter("tamanhoDiscoModalRecursoMAR"); // 18
						String primaryIPModalRecursoMAR    = request.getParameter("primaryIPModalRecursoMAR"   ); // 19
						String idNuvemMAR                  = request.getParameter("idNuvemMAR"                 ); // 20
						String idSiteMAR                   = request.getParameter("idSiteMAR"                  ); // 21
						String idFamiliaFlavorsMAR         = request.getParameter("idFamiliaFlavorsMAR"        ); // 22
						String ObservacoesRecursoMAR       = request.getParameter("ObservacoesRecursoMAR"      ); // 23
						String observacaoAditivoMAR        = request.getParameter("observacaoAditivoMAR"       ); // 24
						String dtInicioMAR                 = request.getParameter("dtInicioMAR"                ); // 25
						String dtFinalMAR                  = request.getParameter("dtFinalMAR"                 ); // 26
						String idContrato                  = request.getParameter("idContratoMAR"              ); // 27
						String recursoTemporarioMAR        = request.getParameter("recursoTemporarioMAR"       ); // 28
						String nomeAprovadorMAR            = request.getParameter("nomeAprovadorMAR"           ); // 29
						String temReceitaMAR               = request.getParameter("temReceitaMAR"              ); // 30
						String periodoUtilizacaoMAR        = request.getParameter("periodoUtilizacaoMAR"       ); // 31
						String idTipoAditivoUGR            = request.getParameter("idTipoAditivoUGR"           ); // 32
						String idRecurso_HostnameUGR       = request.getParameter("idRecurso_HostnameUGR"      ); // 33
						String tamanhoDiscoUGR             = request.getParameter("tamanhoDiscoUGR"            ); // 34
						String idFamiliaFlavorsUGR         = request.getParameter("idFamiliaFlavorsUGR"        ); // 35
						String cpuUGR                      = request.getParameter("cpuUGR"                     ); // 36
						String ramUGR                      = request.getParameter("ramUGR"                     ); // 37
//						String valorUGR                    = request.getParameter("valorUGR"                   ); // 38
						String vlrTotalUGR                 = request.getParameter("vlrTotalUGR"                ); // 39
						String id_moedaMAR                 = request.getParameter("id_moedaMAR"                ); // 40
						String valor_convertidoMAR         = request.getParameter("valor_convertidoMAR"        ); // 41
						String valor_CotacaoMAR            = request.getParameter("valor_CotacaoMAR"           ); // 42
						String valorMAR                    = request.getParameter("valorMAR"                   ); // 43
						String eipVcenterMAR               = request.getParameter("eipVcenterModalRecursoMAR"  ); // 44
						String hostVcenterMAR              = request.getParameter("hostVcenterModalRecursoMAR" ); // 45
						String idRecursoMAR                = request.getParameter("idRecursoMAR"               ); // 46
						String idHubSpotAditivoMAR         = request.getParameter("idHubSpotAditivoMAR"        ); // 47
						String id_rascunhoMAR              = request.getParameter("id_rascunhoMAR"             ); // 48
						String motivoRascunhoMAR           = request.getParameter("motivoRascunhoMAR"          ); // 49
						String comissaoMAR                 = request.getParameter("comissaoMAR"                ); // 50
						String idValorSetupMAR             = request.getParameter("idValorSetupMAR"            ); // 51
						String qtyMesesContratoMAR         = request.getParameter("qtyMesesContratoMAR"        ); // 51
						String vlrParcelasMAR              = request.getParameter("vlrParcelasMAR"             ); // 51
						String qtyParcSetupMAR             = request.getParameter("qtyParcSetupMAR"            ); // 51
						String id_tempo_contrato           = request.getParameter("selectTempoContratoMAR"     ); // 52
						String observacaoVigenciaMAR       = request.getParameter("observacaoVigenciaMAR"      ); // 53
						
						if(vlrParcelasMAR != null && !vlrParcelasMAR.isEmpty()) {
							vlrParcelasMAR = Normalizer.normalize(vlrParcelasMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrParcelasMAR.indexOf(" ") >= 0 )
						       vlrParcelasMAR = vlrParcelasMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
							   vlrParcelasMAR = vlrParcelasMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}

						if(idValorSetupMAR != null && !idValorSetupMAR.isEmpty()) {
							idValorSetupMAR = Normalizer.normalize(idValorSetupMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(idValorSetupMAR.indexOf(" ") >= 0 )
						    	idValorSetupMAR = idValorSetupMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								idValorSetupMAR = idValorSetupMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}

						if(valorMAR != null && !valorMAR.isEmpty()) {
							valorMAR = Normalizer.normalize(valorMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(valorMAR.indexOf(" ") >= 0 )
						    	valorMAR = valorMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								valorMAR = valorMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
						
						if(valor_convertidoMAR != null && !valor_convertidoMAR.isEmpty()) {
							valor_convertidoMAR = Normalizer.normalize(valor_convertidoMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(valor_convertidoMAR.indexOf(" ") >= 0 )
						    	valor_convertidoMAR = valor_convertidoMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								valor_convertidoMAR = valor_convertidoMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
						
						if(valor_CotacaoMAR != null && !valor_CotacaoMAR.isEmpty()) {
							valor_CotacaoMAR = Normalizer.normalize(valor_CotacaoMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(valor_CotacaoMAR.indexOf(" ") >= 0 )
						    	valor_CotacaoMAR = valor_CotacaoMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								valor_CotacaoMAR = valor_CotacaoMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}

					    if(vlrTotalMAR != null && !vlrTotalMAR.isEmpty()) {
					    	vlrTotalMAR = Normalizer.normalize(vlrTotalMAR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
						    if(vlrTotalMAR.indexOf(" ") >= 0 )
						    	vlrTotalMAR = vlrTotalMAR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
							else
								vlrTotalMAR = vlrTotalMAR.replaceAll("\\.", "").replaceAll("\\,", ".");
						}
											
						if(idTipoAditivoMAR != null && idTipoAditivoMAR.isEmpty()) 
						   idTipoAditivoMAR = idTipoAditivoUGR;

						ModelAitivoRecursoModal modelAitivoRecursoModal = new ModelAitivoRecursoModal();
						
					    HttpServletRequest req                          = (HttpServletRequest) request;
						HttpSession session                             = req.getSession();
					    String usuarioLogado                            = (String) session.getAttribute("usuario");
					    String urlParaAutent                = req.getServletPath(); // URL que esta sendo acessada.						
					    // Validar se esta logado, se nao redireciona para a tela de login
					    if( usuarioLogado == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
					        RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
					        request.setAttribute("msg", "Por favor, realizar o login!");
					        redireciona.forward(request, response);
					        return ;// para a execucao e redireciona para o login.
					    }					    
 

						modelAitivoRecursoModal.setId_aditivado               ( idAditivoMAR                != null && !idAditivoMAR.isEmpty()                ? Long.parseLong( idAditivoMAR )         : null );
						modelAitivoRecursoModal.setId_contrato                ( idContrato                  != null && !idContrato.isEmpty()                  ? Long.parseLong( idContrato )           : null );
						modelAitivoRecursoModal.setDt_criacao                 ( dtCriacaoMAR                != null && !dtCriacaoMAR.isEmpty()                ? dtCriacaoMAR                           : null );
						modelAitivoRecursoModal.setId_tipo_aditivo            ( idTipoAditivoMAR            != null && !idTipoAditivoMAR.isEmpty()            ? Long.parseLong( idTipoAditivoMAR )     : null );
						modelAitivoRecursoModal.setId_status                  ( idStatusMAR                 != null && !idStatusMAR.isEmpty()                 ? Long.parseLong( idStatusMAR )          : null );
						
						// 
						modelAitivoRecursoModal.setId_recurso                 ( idRecursoMAR                != null && !idRecursoMAR.isEmpty()                ? Long.parseLong( idRecursoMAR.trim() )         : null);
						modelAitivoRecursoModal.setValor_total                ( vlrTotalMAR                 != null && !vlrTotalMAR.isEmpty()                 ? vlrTotalMAR.trim()                            : null );
					    modelAitivoRecursoModal.setId_tipo_contratacao        ( idTipoContratacaoMAR        != null && !idTipoContratacaoMAR.isEmpty()        ? Long.parseLong( idTipoContratacaoMAR.trim() ) : null );
					    modelAitivoRecursoModal.setId_suporte                 ( idSuporteMAR                != null && !idSuporteMAR.isEmpty()                ? Long.parseLong( idSuporteMAR.trim() )         : null );
					    modelAitivoRecursoModal.setId_monitoramento           ( idMonitoramentoMAR          != null && !idMonitoramentoMAR.isEmpty()          ? Long.parseLong( idMonitoramentoMAR.trim() )   : null );
					    modelAitivoRecursoModal.setId_backup                  ( idBackupMAR                 != null && !idBackupMAR.isEmpty()                 ? Integer.valueOf( idBackupMAR.trim() )         : 0    );
					    modelAitivoRecursoModal.setId_retencao_backup         ( idRetencaoBackupMAR         != null && !idRetencaoBackupMAR.isEmpty()         ? Long.parseLong( idRetencaoBackupMAR.trim() )  : 1L   );
					    modelAitivoRecursoModal.setId_status_recurso          ( idStatusRecursoMAR          != null && !idStatusRecursoMAR.isEmpty()          ? Long.parseLong( idStatusRecursoMAR.trim() )   : null );
					    modelAitivoRecursoModal.setTag_vcenter                ( tagVcenterMAR               != null && !tagVcenterMAR.isEmpty()               ? tagVcenterMAR.trim()                          : null );
					    modelAitivoRecursoModal.setId_tipo_disco              ( idTipoDiscoMAR              != null && !idTipoDiscoMAR.isEmpty()              ? Long.parseLong( idTipoDiscoMAR.trim() )       : null );
					    modelAitivoRecursoModal.setId_so                      ( idSoMAR                     != null && !idSoMAR.isEmpty()                     ? Long.parseLong( idSoMAR.trim() )              : null );
					    modelAitivoRecursoModal.setId_ambiente                ( idAmbienteMAR               != null && !idAmbienteMAR.isEmpty()               ? Long.parseLong( idAmbienteMAR.trim() )        : null );
					    modelAitivoRecursoModal.setId_tipo_servico            ( idTipoServicoMAR            != null && !idTipoServicoMAR.isEmpty()            ? Long.parseLong( idTipoServicoMAR.trim() )     : null );
					    modelAitivoRecursoModal.setHost_name_modal_recurso    ( hostNameModalRecursoMAR     != null && !hostNameModalRecursoMAR.isEmpty()     ? hostNameModalRecursoMAR.trim()                : null );
					    modelAitivoRecursoModal.setTamanho_disco_modal_recurso( tamanhoDiscoModalRecursoMAR != null && !tamanhoDiscoModalRecursoMAR.isEmpty() ? tamanhoDiscoModalRecursoMAR.trim()            : null );
					    modelAitivoRecursoModal.setPrimary_ip_modalrecurso    ( primaryIPModalRecursoMAR    != null && !primaryIPModalRecursoMAR.isEmpty()    ? primaryIPModalRecursoMAR.trim()               : null );					    
					    modelAitivoRecursoModal.setEip_Vcenter                ( eipVcenterMAR               != null && !eipVcenterMAR.isEmpty()               ? eipVcenterMAR.trim()                          : null );
					    modelAitivoRecursoModal.setHost_Vcenter               ( hostVcenterMAR              != null && !hostVcenterMAR.isEmpty()              ? hostVcenterMAR.trim()                         : null );
					    modelAitivoRecursoModal.setId_nuvem                   ( idNuvemMAR                  != null && !idNuvemMAR.isEmpty()                  ? Long.parseLong( idNuvemMAR.trim() )           : null );
					    modelAitivoRecursoModal.setId_site                    ( idSiteMAR                   != null && !idSiteMAR.isEmpty()                   ? Long.parseLong( idSiteMAR.trim() )            : null );
					    modelAitivoRecursoModal.setId_familia_flavors         ( idFamiliaFlavorsMAR         != null && !idFamiliaFlavorsMAR.isEmpty()         ? Long.parseLong( idFamiliaFlavorsMAR.trim() )  : null );
					    modelAitivoRecursoModal.setObservacoes_recurso        ( ObservacoesRecursoMAR       != null && !ObservacoesRecursoMAR.isEmpty()       ? ObservacoesRecursoMAR.trim()                  : null );
					    modelAitivoRecursoModal.setObservacao_aditivo         ( observacaoAditivoMAR        != null && !observacaoAditivoMAR.isEmpty()        ? observacaoAditivoMAR.trim()                   : null );
					    modelAitivoRecursoModal.setRecurso_temporario         ( recursoTemporarioMAR        != null && !recursoTemporarioMAR.isEmpty()        ? Integer.valueOf( recursoTemporarioMAR.trim() ): 0    );
					    modelAitivoRecursoModal.setAprovador_adit_sem_receita ( nomeAprovadorMAR            != null && !nomeAprovadorMAR.isEmpty()            ? nomeAprovadorMAR.trim()                       : null );
					    modelAitivoRecursoModal.setAdti_sem_receita           ( temReceitaMAR               != null && !temReceitaMAR.isEmpty()               ? Integer.valueOf( temReceitaMAR.trim() )       : 0    );
					    modelAitivoRecursoModal.setPeriodo_utilizacao_bolha   ( periodoUtilizacaoMAR        != null && !periodoUtilizacaoMAR.isEmpty()        ? periodoUtilizacaoMAR.trim()                   : null );
					    modelAitivoRecursoModal.setId_tempo_contrato          ( id_tempo_contrato           != null && !id_tempo_contrato.isEmpty()           ? Long.parseLong(id_tempo_contrato.trim())      : null );
					    modelAitivoRecursoModal.setObservacao_vigencia        ( observacaoVigenciaMAR       != null && !observacaoVigenciaMAR.isEmpty()       ? observacaoVigenciaMAR.trim()                  : null );

					    modelAitivoRecursoModal.setDt_inicio                  ( dtInicioMAR                 != null && !dtInicioMAR.isEmpty()                 ? dtInicioMAR.trim()                            : null );
					    modelAitivoRecursoModal.setDt_final                   ( dtFinalMAR                  != null && !dtFinalMAR.isEmpty()                  ? dtFinalMAR.trim()                             : null );
					    modelAitivoRecursoModal.setHubspot_aditivo            ( idHubSpotAditivoMAR         != null && !idHubSpotAditivoMAR.isEmpty()         ? idHubSpotAditivoMAR.trim()                    : null );
					    modelAitivoRecursoModal.setId_rascunho                ( id_rascunhoMAR              != null && !id_rascunhoMAR.isEmpty()              ? Long.parseLong( id_rascunhoMAR.trim() )       : null );
					    modelAitivoRecursoModal.setMotivoRascunho             ( motivoRascunhoMAR           != null && !motivoRascunhoMAR.isEmpty()           ? motivoRascunhoMAR.trim()                      : null );
					    modelAitivoRecursoModal.setComissao_adit              ( (comissaoMAR != null && !comissaoMAR.isEmpty()? Integer.parseInt(comissaoMAR)== 1 : false) ? true                             : false);
					    modelAitivoRecursoModal.setValor_setup_adit           ( idValorSetupMAR             != null && !idValorSetupMAR.isEmpty()             ? idValorSetupMAR.trim()                        : null );
					    modelAitivoRecursoModal.setQty_mese_setup_adit        ( qtyMesesContratoMAR         != null && !qtyMesesContratoMAR.isEmpty()         ? Integer.valueOf( qtyMesesContratoMAR.trim() ) : 0    );
					    modelAitivoRecursoModal.setValor_parcela_setup_adit   ( vlrParcelasMAR              != null && !vlrParcelasMAR.isEmpty()              ? vlrParcelasMAR.trim()                         : null );
					    modelAitivoRecursoModal.setQty_parcela_setup_adit     ( qtyParcSetupMAR             != null && !qtyParcSetupMAR.isEmpty()             ? Integer.valueOf( qtyParcSetupMAR.trim() )     : 0    );

						// Informacao do tipo de Moeda Contratada para o Atidivo.
						modelAitivoRecursoModal.setValor_convertido           ( valor_convertidoMAR         != null && !valor_convertidoMAR.isEmpty()         ? valor_convertidoMAR.trim()                    : null );
						modelAitivoRecursoModal.setCusto_total                ( valorMAR                    != null && !valorMAR.isEmpty()                    ? valorMAR.trim()                               : null );
						modelAitivoRecursoModal.setCotacao_moeda              ( valor_CotacaoMAR            != null && !valor_CotacaoMAR.isEmpty()            ? valor_CotacaoMAR.trim()                       : null );
						modelAitivoRecursoModal.setId_moeda                   ( id_moedaMAR                 != null && !id_moedaMAR.isEmpty()                 ? Long.parseLong( id_moedaMAR.trim() )          : null );
						modelAitivoRecursoModal.setLogin_cadastro             ( usuarioLogado               != null && !usuarioLogado.isEmpty()               ? usuarioLogado.trim()                          : null );
//					    System.out.println( modelAitivoRecursoModal );
						
					    DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					    DAOAditivoModal        daoAditivoModal        = new DAOAditivoModal();
                        try {
						    switch ( modelAitivoRecursoModal.getId_tipo_aditivo().intValue() ){
							case 1: // Ambiente Bolha
							case 2: // Contatação do Ambiente DEV
							case 5: // Contratação do QAS
							case 7: // Incremento de Servidores	
								modelAitivoRecursoModal = daoAditivoModalRecurso.gravarAditivoRecursoModal( modelAitivoRecursoModal );
								break;
							case 9: // Upgrade de Recurso
							    ModelHitoricoUpgrade modelHitoricoUpgrade = new ModelHitoricoUpgrade();
							    modelHitoricoUpgrade.setLogin                   ( usuarioLogado         != null && !usuarioLogado.isEmpty()         ? usuarioLogado.trim()                           : null );
							    modelHitoricoUpgrade.setId_recurso              ( idRecurso_HostnameUGR != null && !idRecurso_HostnameUGR.isEmpty() ? Long.parseLong( idRecurso_HostnameUGR.trim() ) : null );
							    modelHitoricoUpgrade.setTamanho_disco_novo      ( tamanhoDiscoUGR       != null && !tamanhoDiscoUGR.isEmpty()       ? tamanhoDiscoUGR.trim()                         : null );
							    modelHitoricoUpgrade.setId_familia_flavors_novo ( idFamiliaFlavorsUGR   != null && !idFamiliaFlavorsUGR.isEmpty()   ? Long.parseLong( idFamiliaFlavorsUGR.trim() )   : null );
							    modelHitoricoUpgrade.setCpu_novo                ( cpuUGR                != null && !cpuUGR.isEmpty()                ? cpuUGR.trim()                                  : null );
							    modelHitoricoUpgrade.setRam_novo                ( ramUGR                != null && !ramUGR.isEmpty()                ? ramUGR.trim()                                  : null );
							    
							    if(vlrTotalUGR != null && !vlrTotalUGR.isEmpty()) {
							    	vlrTotalUGR = Normalizer.normalize(vlrTotalUGR, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
							    	vlrTotalUGR = vlrTotalUGR.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
								}
							    modelHitoricoUpgrade.setValor_novo              ( vlrTotalUGR          != null && !vlrTotalUGR.isEmpty()          ? vlrTotalUGR.trim()                            : null );
								modelHitoricoUpgrade = daoAditivoModalRecurso.upgradeRecurso( modelHitoricoUpgrade );
								break;
							}
						    request.setAttribute("modelAitivoRecursoModal" , modelAitivoRecursoModal );
                        } catch( SQLException er ) {
                        	er.printStackTrace();
    						RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
    						request.setAttribute("msg", er.getMessage());
    						requestDispatcher.forward(request, response);			
                        }
                        
    					List<ModelAitivoRecursoModal> modelAitivoRecursoModals = daoAditivoModalRecurso.getListaAditivoRecursoID( Long.parseLong( idContrato ) );
    					List<ModelHistoricoUpgrade> listaHistoricoUpgrade      = daoAditivoModal.getListaHistoricoUpgrade( Long.parseLong( idContrato.trim() ) );
    					
    					request.setAttribute("listaHistoricoUpgrade"   , listaHistoricoUpgrade    );
    					request.setAttribute("modelAitivoRecursoModals", modelAitivoRecursoModals );

						Gson gson    = new Gson();
						String lista = gson.toJson( modelAitivoRecursoModals );
						response.getWriter().write(lista);
/*
			    	}catch(Exception e){
						e.printStackTrace();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
						request.setAttribute("msg", e.getMessage());
						requestDispatcher.forward(request, response);			
					}
*/					
				} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectSiteRecusto") ) {
					String idNuvem = request.getParameter("idNuvem");
					if( idNuvem != null && !idNuvem.isEmpty() ) {
						List<ModelSite> modelSites = new ArrayList<ModelSite>();
						DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();

						modelSites = daoAditivoModalRecurso.listaSiteSelectRecurso( Long.parseLong(idNuvem) );
						Gson gson = new Gson();
						String lista = gson.toJson(modelSites);
						response.getWriter().write(lista);
					}
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listaAditivosRecursoInicial") ) {
					String idContrato    = request.getParameter( "idContrato" ); // id_contrato
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					List<ModelAitivoRecursoModal> modelAitivoRecursoModal = daoAditivoModalRecurso.getListaAditivoRecursoID( Long.parseLong( idContrato.trim() ) );
					request.setAttribute("modelAitivoRecursoModal", modelAitivoRecursoModal );

				    Gson gson = new Gson();
					String lista = gson.toJson(modelAitivoRecursoModal);
					response.getWriter().write(lista);
					
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditarAditivoRecurso") ) {

					String idRecurso  = request.getParameter("idRecurso" );
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					ModelAitivoRecursoModal modelAitivoRecursoModal = new ModelAitivoRecursoModal();
					modelAitivoRecursoModal.setId_recurso( Long.parseLong( idRecurso ) );
							
					modelAitivoRecursoModal = daoAditivoModalRecurso.getAditivoRecursoID( modelAitivoRecursoModal );
	
					request.setAttribute("modelAitivoRecursoModal" , modelAitivoRecursoModal );
					Gson gson    = new Gson();
					String lista = gson.toJson( modelAitivoRecursoModal );
					response.getWriter().write(lista);
	
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectHostname") ) {
					String idContrato = request.getParameter("idContrato");
	
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					List<ModelAitivoRecursoModal> modelAitivoRecursoModal = daoAditivoModalRecurso.listaHostnameSelectRecurso( Long.parseLong( idContrato.trim() ) );

					request.setAttribute("modelAitivoRecursoModal" , modelAitivoRecursoModal );
					Gson gson    = new Gson();
					String lista = gson.toJson( modelAitivoRecursoModal );
					response.getWriter().write(lista);
					
//					String login = request.getParameter("login");
	
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaDadosFamiliaUpdade") ) {
					String idRecurso = request.getParameter("idRecurso");
	
					DAOAditivoModalRecurso daoAditivoModalRecurso = new DAOAditivoModalRecurso();
					
					ModelHitoricoUpgrade modelHitoricoUpgrade = daoAditivoModalRecurso.cargaUpdateHistorico( Long.parseLong( idRecurso.trim() ) );

					request.setAttribute("modelHitoricoUpgrade" , modelHitoricoUpgrade );
					Gson gson    = new Gson();
					String lista = gson.toJson( modelHitoricoUpgrade );
					response.getWriter().write(lista);
					
//					String login = request.getParameter("login");
	
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("pesquisaContradoID") ) {
					String idContrato = request.getParameter("idContrato");
					Gson gson    = new Gson();
					String resultado = daoContratoRepository.getContratoID( Long.parseLong( idContrato ) );
					if( !resultado.equals("NAOEXISTE")) {
						String textoSplit [] = resultado.split(";");
						String vetorID[]     = {textoSplit[0], textoSplit[1]};
						response.getWriter().write(gson.toJson( vetorID ));
					}else  response.getWriter().write(gson.toJson( "NAOEXISTE" ));
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadContratoPdf")) {
					 String idContrato = request.getParameter("id");
					 ModelContrato modelContrato= daoContratoRepository.consultaContratoID( Long.parseLong( idContrato ) );
					 if ( modelContrato.getContratopdf() != null && !modelContrato.getContratopdf().isEmpty()) {
						 response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelContrato.getExtensaocontratopdf());
						 response.getOutputStream().write(Base64.decodeBase64( modelContrato.getContratopdf().split("\\,")[1]) );
					 }
			    }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("cancelaContrato")) {
					 String idContrato       = request.getParameter("idContrato"      );
					 String idStatusContrato = request.getParameter("idStatusContrato");
					 
					 String retornoStatus = daoContratoRepository.getStatusContrato( Long.parseLong( idContrato ) );
					 String textoSplit [] = retornoStatus.split(";");
					 String vetorMensagemRetorno[] = {"",""};
					 String tituloPrincipal, textoPrincipal = "";
					 Gson gson    = new Gson();
					 if( !textoSplit[0].trim().equals(idStatusContrato) ) {
				    	 if( textoSplit[0].trim().equals("1") && idStatusContrato.trim().equals("2") ) {
				    		 String retornoRec = daoContratoRepository.getQtyRecurso( Long.parseLong( idContrato ) );
				    		 String retornoRecSplit [] = retornoRec.split(";");
				    		 tituloPrincipal = "Deseja alterar o status do contrato para 'Descomissionamento'?"; 
				    		 
				    		 if( Integer.valueOf(retornoRecSplit[0]) > 0 )
				    		     textoPrincipal += "O contrato possui " + retornoRecSplit[0] + " recuros associado a ele";
				    		 
				    		 if( Integer.valueOf(retornoRecSplit[1]) > 0 )
				    			 textoPrincipal += " e " + retornoRecSplit[1] + " recuros associado a 1 ou mais Aditivo\n";
				    		 
				    		 textoPrincipal += "!\nTodos terão seus status alterados para 'Inativo' juntamente com o contrato.";
				    		 
				    		 vetorMensagemRetorno[0] = tituloPrincipal;
				    		 vetorMensagemRetorno[1] = textoPrincipal;
				    		 response.getWriter().write(gson.toJson( vetorMensagemRetorno ));
				    		 
				    	 }else if( textoSplit[0].trim().equals("1") && idStatusContrato.trim().equals("3") ) {
				    		 String retornoRec = daoContratoRepository.getQtyRecurso( Long.parseLong( idContrato ) );
				    		 String retornoRecSplit [] = retornoRec.split(";");
				    		 
				    		 tituloPrincipal = "Deseja alterar o status do contrato para 'Distrato'?"; 
				    		 
				    		 if( Integer.valueOf(retornoRecSplit[0]) > 0 )
				    		     textoPrincipal += "O contrato possui " + retornoRecSplit[0] + " recuros associado a ele";
				    		 
				    		 if( Integer.valueOf(retornoRecSplit[1]) > 0 )
				    			 textoPrincipal += " e " + retornoRecSplit[1] + " recuros associado a 1 ou mais Aditivo\n";
				    		 
				    		 textoPrincipal += "!\nTodos terão seus status alterados para 'Desativado' juntamente com o contrato.";
				    		 
				    		 vetorMensagemRetorno[0] = tituloPrincipal;
				    		 vetorMensagemRetorno[1] = textoPrincipal;
				    		 response.getWriter().write(gson.toJson( vetorMensagemRetorno ));
				    	 }else if( textoSplit[0].trim().equals("4") && idStatusContrato.trim().equals("1") ) {
				    		 textoPrincipal += "Deseja alterar o Status de \"Rascunho\" para \"Ativo\"?";
					    	 tituloPrincipal = "Alteração de Status"; 
					    	 vetorMensagemRetorno[0] = tituloPrincipal;
				    		 vetorMensagemRetorno[1] = textoPrincipal;
					    	 response.getWriter().write(gson.toJson( vetorMensagemRetorno ));
				    	 
				    	 }else {
					    	 vetorMensagemRetorno[0] = "STATUSNAOALTERADO";
					    	 response.getWriter().write(gson.toJson( vetorMensagemRetorno ));
					     }
				     }else {
				    	 vetorMensagemRetorno[0] = "STATUSNAOALTERADO";
				    	 response.getWriter().write(gson.toJson( vetorMensagemRetorno ));
				     }
				 }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("efetivaCancelamentoContratoAfins")) {
					 ModelDesativacaoContrato DesativacaoContrato = new ModelDesativacaoContrato();
					 String msnRetorno       = null;
					 String idContrato       = request.getParameter( "idContrato" );
					 String idStatusContrato = request.getParameter( "idStatusContrato" );
					 DAORascunho daoRascunho = new DAORascunho();
				   	 String retornoInfoRenovacao = daoRascunho.getInfoRenovacao(Long.parseLong( idContrato ));
				   	 String textoSplit [] = retornoInfoRenovacao.split(";");
                     
				   	 Boolean renovacao = ( Integer.valueOf(textoSplit[0].trim()) == 0 ? false : true );
				   	 Long statusAtual  = ( textoSplit[3] != null && !textoSplit[3].isEmpty() ? Long.parseLong(textoSplit[3].trim()) : null );
				   	 if( renovacao && statusAtual == 4) {
				   			DAOContratoRepository daoContratoRepository = new DAOContratoRepository();	
				   		    Long renovacaoContratoOrigem = ( textoSplit[1] != null && !textoSplit[1].trim().isEmpty()      ? Long.parseLong(textoSplit[1].trim())    : null );
							HttpServletRequest req = (HttpServletRequest) request;
						    HttpSession session    = req.getSession();
			                String userDesativacao = (String) session.getAttribute("usuario");
							// Cancelamento de contrato em caso de renovacao.							
							DesativacaoContrato.setUser_desativacao( userDesativacao );
							DesativacaoContrato.setId_contrato     ( renovacaoContratoOrigem      );
							String retorno = daoContratoRepository.CancelaContrato( DesativacaoContrato, 1 );
							if( retorno == null ) retorno = "O Contrato: " + idContrato + " e todo(s) os produtos e recusso(s) a ele vinculados(caso exista), foram cancelados com sucesso";
							daoRascunho.atualizaStatusRascunhoContrato( Long.parseLong( idContrato ), Long.parseLong( idStatusContrato ) );
							Gson gson         = new Gson();
							response.getWriter().write(gson.toJson( retorno ));

				   	 }if( statusAtual == 4) {
				   		
				   		daoRascunho.atualizaStatusRascunhoContrato( Long.parseLong( idContrato ), Long.parseLong( idStatusContrato ) );
						Gson gson         = new Gson();
						response.getWriter().write(gson.toJson( "STATUS4" ));
				   	 }else {
						 HttpServletRequest req = (HttpServletRequest) request;
					     HttpSession session    = req.getSession();
						 
						 DesativacaoContrato.setUser_desativacao( (String) session.getAttribute("usuario") );
						 DesativacaoContrato.setId_contrato     ( Long.parseLong( idContrato )             );
	
						 String retorno = daoContratoRepository.CancelaContrato( DesativacaoContrato, 0 );
						 if( retorno == null )
						     msnRetorno = "O Contrato: " + idContrato + " e todo(s) os produtos e recusso(s) a ele vinculados(caso exista), foram cancelados com sucesso";
						 else msnRetorno = retorno;
						 
						 Gson gson         = new Gson();
						 response.getWriter().write(gson.toJson( msnRetorno ));
				   	 }
				 } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginarPesquisaCliente") ) {	
				   		String pag = request.getParameter("pag");
				   		offsetBegin = Integer.parseInt( pag ) * OFFSET_END;
				   		
						List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente( offsetBegin, OFFSET_END );
						request.setAttribute("totalPagina", daoClienteRepository.getTotalPag( OFFSET_END ) );
					    Gson gson = new Gson();
					    String lista = gson.toJson(modelClientes);
					    response.getWriter().write(lista);
					
				} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginarPesquisaClienteDesativado") ) {	
			   		String pag = request.getParameter("pag");
			   		offsetBegin = Integer.parseInt( pag ) * OFFSET_END;
			   		
					List<ModelCliente> modelClientes = daoClienteRepository.buscarListaClienteDesativado( offsetBegin, OFFSET_END );
					request.setAttribute("totalPagina", daoClienteRepository.getTotalPag( OFFSET_END ) );
				    Gson gson = new Gson();
				    String lista = gson.toJson(modelClientes);
				    response.getWriter().write(lista);
				
			    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizaStatusContratoDescomissionamento") ) {	
			   		String idStatusContrato = request.getParameter("idStatusContrato"   );
			   		String idContrato       = request.getParameter("idContrato"         );
			   		String idCliente        = request.getParameter("idCliente"          );
			   		String motivoDescomiss  = request.getParameter("mDescomissionamento");

			   		ModalDescomissionamento mDescomissionamento = new ModalDescomissionamento();
			   		mDescomissionamento.setId_contrato              ( idContrato       != null && !idContrato.isEmpty()       ? Long.parseLong(idContrato.trim())       : null );
			   		mDescomissionamento.setId_cliente               ( idCliente        != null && !idCliente.isEmpty()        ? Long.parseLong(idCliente.trim())        : null );
			   		mDescomissionamento.setId_status_contrato       ( idStatusContrato != null && !idStatusContrato.isEmpty() ? Long.parseLong(idStatusContrato.trim()) : null );
			   		mDescomissionamento.setMotivo_descomissionamento( motivoDescomiss  != null && !motivoDescomiss.isEmpty()  ? motivoDescomiss                         : null );
			   		
			   		DAOContratoRepository daoContratoRepository = new DAOContratoRepository();	
			   		String result = daoContratoRepository.atualizaStatusDescomissionamento(mDescomissionamento);

				    response.getWriter().write(result);
				
			    }else{
					  request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);
			    }
    	}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String msg = "Operacao realizada com sucesso!";
			// Long
			String id_contrato            = request.getParameter( "id_contrato"           );
			String id_nuvem               = request.getParameter( "id_nuvem"              ); 
			String id_fase_contrato       = request.getParameter( "id_fase_contrato"      );
			String id_status_contrato     = request.getParameter( "id_status_contrato"    );
			String id_cliente             = request.getParameter( "id_cliente"            );
			String id_ciclo_update        = request.getParameter( "id_ciclo_updadate"     );
			String id_servico_contratado  = request.getParameter( "id_servico_contratado" );
			String id_site                = request.getParameter( "id_site"               );
		    String termo_admin            = request.getParameter( "termo_admin"           );
		    String termo_download         = request.getParameter( "termo_download"        );
		    String nomeCliente            = request.getParameter( "nomeCliente"           );
		    String dt_criacao             = request.getParameter( "dt_criacao"            );
		    String qty_usuario_contratada = request.getParameter( "qty_usuario_contratada"); 
		    String valor_total            = request.getParameter( "valor_total"           );
		    String id_moeda               = request.getParameter( "id_moeda"              );
		    String valor_convertido       = request.getParameter( "valor_convertido"      );
		    String valor_Cotacao          = request.getParameter( "valor_Cotacao"         );
			String id_rascunho            = request.getParameter( "id_rascunho"           );
			String motivo_rascunho        = request.getParameter( "motivoRascunho"        );
			String id_suporte_b1          = request.getParameter( "id_suporte_b1"         );
			String id_comercial           = request.getParameter( "id_comercial"          );
			
			String comissao               = ( request.getParameter( "comissao" ).equals("1") ? "Sim": "Não" ) ;
			String valor_setup            = request.getParameter( "idValorSetup"          );
			String valor_parcela_setup    = request.getParameter( "vlrParcelas"           );
			String qty_parcela_setup      = request.getParameter( "qtyParcSetup"          );
			String qty_mese_setup         = request.getParameter( "qtyMesesContrato"      );
			
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
		       if(valor_total.indexOf(" ") >= 0 ) {
		    	  if(id_moeda.equals("1")) 
		             valor_total = valor_total.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    	  else  valor_total = valor_total.replaceAll("\\$", "").replaceAll("\\.", "").replaceAll("\\,", ".");
		       }else 
		    	 if(id_moeda.equals("1")) 
		            valor_total = valor_total.replaceAll("\\.", "").replaceAll("\\,", ".");
		    	 else valor_total = valor_total.replaceAll("\\$", "").replaceAll("\\€", "").replaceAll("\\,", "");
		    }
			valor_total = valor_total.replaceAll("\\ ", "");
			
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
		    
		    String pep                    = request.getParameter( "pep"                 );
		    String id_hub_spot            = request.getParameter( "id_hub_spot"         );
		    String observacao             = request.getParameter( "observacao"          );
			String id_vigencia            = request.getParameter( "id_vigencia"         );
			String id_tempo_contrato      = request.getParameter( "selectTempoContrato" ); 
			String dt_inicio              = request.getParameter( "dt_inicio"           );
			String dt_final               = request.getParameter( "dt_final"            );
			String observacao_vigencia    = request.getParameter( "observacaoVigencia"  );		    
		    HttpServletRequest req        = (HttpServletRequest) request;
			HttpSession session           = req.getSession();
		    String usuarioLogado          = (String) session.getAttribute("usuario");
			 
		    ModelContrato modelContrato = new ModelContrato();
		    
		    modelContrato.setId_contrato           ( id_contrato             != null && !id_contrato.isEmpty()            ? Long.parseLong(id_contrato.trim())          : null );
		    modelContrato.setId_nuvem              ( id_nuvem                != null && !id_nuvem.isEmpty()               ? Long.parseLong(id_nuvem.trim())             : null );
		    modelContrato.setId_fase_contrato      ( id_fase_contrato        != null && !id_fase_contrato.isEmpty()       ? Long.parseLong(id_fase_contrato.trim())     : null );
		    modelContrato.setId_status_contrato    ( id_status_contrato      != null && !id_status_contrato.isEmpty()     ? Long.parseLong(id_status_contrato.trim())   : null );
		    modelContrato.setId_rascunho           ( id_rascunho             != null && !id_rascunho.isEmpty()            ? Long.parseLong(id_rascunho.trim())          : null );
		    modelContrato.setMotivoRascunho        ( motivo_rascunho         != null && !motivo_rascunho.isEmpty()        ? motivo_rascunho.trim()                      : null );
		    modelContrato.setId_cliente            ( id_cliente              != null && !id_cliente.isEmpty()             ? Long.parseLong(id_cliente.trim())           : null );
		    modelContrato.setId_ciclo_update       ( id_ciclo_update         != null && !id_ciclo_update.isEmpty()        ? Long.parseLong(id_ciclo_update.trim())      : null );
		    modelContrato.setId_servico_contratado ( id_servico_contratado   != null && !id_servico_contratado.isEmpty()  ? Long.parseLong(id_servico_contratado.trim()): null );
		    modelContrato.setDt_criacao            ( dt_criacao              != null && !dt_criacao.isEmpty()             ? dt_criacao.trim()                           : null );
		    modelContrato.setNomeCliente           ( nomeCliente             != null && !nomeCliente.isEmpty()            ? nomeCliente.trim()                          : null );
		    modelContrato.setQty_usuario_contratada( qty_usuario_contratada  != null && !qty_usuario_contratada.isEmpty() ? qty_usuario_contratada.trim()               : null );
		    modelContrato.setId_site               ( id_site                 != null && !id_site.isEmpty()                ? Long.parseLong(id_site.trim())              : null );
		    modelContrato.setValor_total           ( valor_total             != null && !valor_total.isEmpty()            ? valor_total.trim()                          : null );
		    modelContrato.setPep                   ( pep                     != null && !pep.isEmpty()                    ? pep.trim()                                  : null );
		    modelContrato.setId_hub_spot           ( id_hub_spot             != null && !id_hub_spot.isEmpty()            ? id_hub_spot.trim()                          : null );
		    modelContrato.setObservacao            ( observacao              != null && !observacao.isEmpty()             ? observacao.trim()                           : null );
		    modelContrato.setTermo_admin           ( termo_admin             != null && !termo_admin.isEmpty()            ? Integer.valueOf(termo_admin.trim())         : 1    );
		    modelContrato.setTermo_download        ( termo_download          != null && !termo_download.isEmpty()         ? Integer.valueOf(termo_download.trim())      : 1    );
		    modelContrato.setId_moeda              ( id_moeda                != null && !id_moeda.isEmpty()               ? Long.parseLong(id_moeda.trim())             : null );
		    modelContrato.setValor_convertido      ( valor_convertido        != null && !valor_convertido.isEmpty()       ? valor_convertido.trim()                     : null );
		    modelContrato.setCotacao_moeda         ( valor_Cotacao           != null && !valor_Cotacao.isEmpty()          ? valor_Cotacao.trim()                        : null );
		    modelContrato.setId_suporte_b1         ( id_suporte_b1           != null && !id_suporte_b1.isEmpty()          ? Long.parseLong(id_suporte_b1.trim())        : null );
		    modelContrato.setId_comercial          ( id_comercial            != null && !id_comercial.isEmpty()           ? Long.parseLong(id_comercial.trim())         : null );
//		    modelContrato.setSetup                 ( Integer.parseInt(Setup)       == 1                                   ? true                                        : false );
		    modelContrato.setValor_setup           ( valor_setup             != null && !valor_setup.isEmpty()            ? valor_setup.trim()                          : null );
		    modelContrato.setComissao              ( comissao                != null && !comissao.isEmpty()               ? comissao.trim()                             : null );

		    modelContrato.setQty_parcela_setup     ( qty_parcela_setup       != null && !qty_parcela_setup.isEmpty()      ? Integer.valueOf(qty_parcela_setup.trim())   : 1    );
		    modelContrato.setQty_mese_setup        ( qty_mese_setup          != null && !qty_mese_setup.isEmpty()         ? Integer.valueOf(qty_mese_setup.trim())      : 1    );
		    modelContrato.setValor_parcela_setup   ( valor_parcela_setup     != null && !valor_parcela_setup.isEmpty()    ? valor_parcela_setup.trim()                  : null );
	/*	    
			String valor_parcela_setup    = request.getParameter( "vlrParcelas"               );
			String qty_parcela_setup      = request.getParameter( "qtyParcSetup"       );
			String qty_mese_setup         = request.getParameter( "qtyMesesContrato"               );
*/
		    // informacoes sobre a vigencia do contrato.
		    modelContrato.setId_vigencia           ( id_vigencia             != null && !id_vigencia.isEmpty()            ? Long.parseLong(id_vigencia.trim())          : null );
		    modelContrato.setId_tempo_contrato     ( id_tempo_contrato       != null && !id_tempo_contrato.isEmpty()      ? Long.parseLong(id_tempo_contrato.trim())    : null );
		    modelContrato.setDt_inicio             ( dt_inicio               != null && !dt_inicio.isEmpty()              ? dt_inicio.trim()                            : null );
		    modelContrato.setDt_final              ( dt_final                != null && !dt_final.isEmpty()               ? dt_final.trim()                             : null );
		    modelContrato.setObservacao_vigencia   ( observacao_vigencia     != null && !observacao_vigencia.isEmpty()    ? observacao_vigencia.trim()                  : null );
		    modelContrato.setLogin_cadastro        ( usuarioLogado           != null && !usuarioLogado.isEmpty()          ? usuarioLogado.trim()                        : null );
		   
		    if (request.getPart("arqContratoPDF") != null) {
			    Part part = request.getPart("arqContratoPDF"); 
			    try {
			    	if (part.getSize() > 0) {
				        byte[] contratoPdf = IOUtils.toByteArray(part.getInputStream()); 
						new org.apache.tomcat.util.codec.binary.Base64();
						String contratoPdfBase64 = "data:" + part.getContentType() + ";base64," + Base64.encodeBase64String(contratoPdf);
						modelContrato.setContratopdf(contratoPdfBase64);
						modelContrato.setExtensaocontratopdf(part.getContentType().split("\\/")[1]);
						modelContrato.setNomeaqrpdf( part.getSubmittedFileName() );
			    	}
			      } catch (Exception e) {
			        e.printStackTrace();
			      }		    
		    }

		    // System.out.println(modelContrato);

	    	if(modelContrato.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
	    	
		    modelContrato = daoContratoRepository.gravarContrato(modelContrato);

		    request.setAttribute("msg", msg );
		    request.setAttribute("modelContrato", modelContrato);
		    request.getRequestDispatcher("principal/contrato.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}

	private ModelFamiliaFlavors montaFamilia( Long idFamilia, HttpServletRequest request ){
		ModelFamiliaFlavors mdFamilias = new ModelFamiliaFlavors();
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		mdFamilias = daoRecusoContrato.getFamiliaFlavors( idFamilia );
		request.setAttribute("mdFamilias", mdFamilias);
		return mdFamilias;
	}
/*	
	private void montaRecursoContratoRel( Long idContrato, HttpServletRequest request ){
		DAORecursoContratoAditivoRel    daoRecursoContratoRel = new DAORecursoContratoAditivoRel();
		List<ModelRecursoContratoAditivoRel> modelRecContRels = daoRecursoContratoRel.getListaRecursoIdContrato( idContrato );
		request.setAttribute("modelRecContRels", modelRecContRels);
	}
*/	
	private ModelRecursoContrato montaRecursoContrato( Long id_Recurso, HttpServletRequest request ) throws Exception{
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		ModelRecursoContrato modelRecursoContrato = daoRecusoContrato.getRecursoIdRecurso( id_Recurso );
		request.setAttribute("modelRecursoContrato", modelRecursoContrato);
		return modelRecursoContrato;
	}
	
	private ModelRecursoContratoCliente montaRecursoContratoCliente( Long idContrato, HttpServletRequest request ) throws Exception{
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		ModelRecursoContratoCliente modelRecursoContratoCliente = daoRecusoContrato.getTelaInical( idContrato );
		return modelRecursoContratoCliente;
	}	

	public String generateVerificationCode(int qtyCaracteres) {
		   return AlphaCode.generate(qtyCaracteres);
	}

}
