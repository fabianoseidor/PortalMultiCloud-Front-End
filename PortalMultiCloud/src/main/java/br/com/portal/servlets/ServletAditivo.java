package br.com.portal.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOAditivo;
import br.com.portal.dao.DAOUtil;
import br.com.portal.model.ModelAditivo;
import br.com.portal.model.ModelAditivoProduto;
import br.com.portal.model.ModelListaAditivo;
import br.com.portal.model.ModelListaRecursoContratoAditivo;
import br.com.portal.model.ModelProduto;
import br.com.portal.model.ModelTempoContrato;
import br.com.portal.model.ModelVigenciaContrato;

public class ServletAditivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOAditivo daoAditivo = new DAOAditivo();
	private Integer offsetBegin = 0;
//	private static final Integer OFFSET_BEGIN = 0;
	private static final Integer OFFSET_END   = 10;
       
    public ServletAditivo() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			  if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MostraValoresSelectProdudo") ) {
	   			String idProduto = request.getParameter("idProduto");
	   			if( idProduto != null && !idProduto.isEmpty() ) {
				    ModelProduto modelProduto = daoAditivo.getValoresProduto( Long.parseLong(idProduto) );
				    Gson gson = new Gson();
				    String lista = gson.toJson(modelProduto);
				    response.getWriter().write(lista);
	   			}
		     } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectProdudo") ) {
				List<ModelProduto> modelProdutos = new ArrayList<ModelProduto>();
				modelProdutos = daoAditivo.getListaProduto();
				request.setAttribute("modelProdutos", modelProdutos);
				Gson gson = new Gson();
				String lista = gson.toJson(modelProdutos);
				response.getWriter().write(lista);
			
		    } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String idAditivo = request.getParameter("idAditivo");
				request.setAttribute("msg", "Aditivo em edicao!" );
				ModelAditivo modelAditivo = new ModelAditivo();
				    
				modelAditivo = daoAditivo.consultaAdtivoID( Long.parseLong(idAditivo) );
				request.setAttribute("modelAditivo", modelAditivo);
				
				List<ModelListaRecursoContratoAditivo> listaRecursoAditivos = daoAditivo.getListaRecursoAditivo( modelAditivo.getId_cliente() );
				
				List<ModelListaAditivo>   listaAditivo         = daoAditivo.buscarListaAditivo( modelAditivo.getId_contrato(), offsetBegin, OFFSET_END );
				List<ModelAditivoProduto> listaAditivoProdutos = daoAditivo.getListaProdutoAditivo( modelAditivo.getId_aditivado() );

				request.setAttribute("listaAditivoProdutos", listaAditivoProdutos );
				request.setAttribute("listaAditivo"        , listaAditivo         );
				request.setAttribute("listaRecursoAditivos", listaRecursoAditivos);
				request.setAttribute("totalPagina", daoAditivo.getTotalPag( OFFSET_END, modelAditivo.getId_contrato() ) );
				request.getRequestDispatcher("principal/aditivo.jsp").forward(request, response);
				    
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar") ) {
				
				offsetBegin = Integer.parseInt( request.getParameter("pag") ) * OFFSET_END;
				String idContrato = request.getParameter("idContrato");
				
				List<ModelListaAditivo> listaAditivo = daoAditivo.buscarListaAditivo( Long.parseLong(idContrato), offsetBegin, OFFSET_END );
				request.setAttribute("listaAditivo", listaAditivo);
				request.setAttribute("totalPagina", daoAditivo.getTotalPag( OFFSET_END, Long.parseLong(idContrato) ) );
				request.getRequestDispatcher("principal/aditivo.jsp").forward(request, response);
		 
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("cadProduto") ) {

		   		String idAditivo = request.getParameter("idAditivo");
		   		String idProduto  = request.getParameter("idProduto" );
		   		String QtyProduto = request.getParameter("QtyProduto");
		   		String vlrUnit    = request.getParameter("vlrUnit"   );
		   		String vltTotal   = request.getParameter("vltTotal"  );
		   		
		   		if( (idAditivo  != null && !idAditivo.isEmpty() ) && (idProduto != null && !idProduto.isEmpty()) && 
		   			(QtyProduto != null && !QtyProduto.isEmpty()) && (vlrUnit   != null && !vlrUnit.isEmpty()  ) &&
		   			(vltTotal   != null && !vltTotal.isEmpty()  ) ) {
		   			
		   			if( !daoAditivo.isExistProduto( Long.parseLong( idAditivo ), Long.parseLong( idProduto) ) ) {
		   				ModelAditivoProduto modelAditivoProduto = new ModelAditivoProduto();
		   				modelAditivoProduto.setId_aditivo    ( Long.parseLong(idAditivo)    );
		   				modelAditivoProduto.setId_produto    ( Long.parseLong(idProduto)    );
		   				modelAditivoProduto.setQty_servico   ( Integer.parseInt(QtyProduto) );
		   				modelAditivoProduto.setValor_unitario( vlrUnit                      );
		   				modelAditivoProduto.setValor( vltTotal );
			   			List<ModelAditivoProduto> listaAditivoProdutos = daoAditivo.InsertProduto( modelAditivoProduto );
			   			
//			   			System.out.println(listaAditivoProdutos);
			   			
					    Gson gson = new Gson();
					    String lista = gson.toJson(listaAditivoProdutos);
					    response.getWriter().write(lista);
		   			}else {
		   				response.getWriter().write("EXISTE");
		   			}
		   		}
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
		   			ModelTempoContrato tempoContrato = daoAditivo.getTempoContrato( Long.parseLong(idTempoContrato) );
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
		   		
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaAditivoInicial") ) {
		   		ModelAditivo modelAditivo = new ModelAditivo();
				String idContrato  = request.getParameter("idContrato" );
		   		String idCliente   = request.getParameter("idCliente"  );
		   		String nomeCliente = request.getParameter("nomeCliente");
		   		 
		   		modelAditivo.setId_contrato( Long.parseLong(idContrato) );
		   		modelAditivo.setId_cliente ( Long.parseLong(idCliente)  );
		   		modelAditivo.setNomeCliente( nomeCliente                );

				List<ModelListaAditivo> listaAditivo = daoAditivo.buscarListaAditivo( modelAditivo.getId_contrato(), offsetBegin, OFFSET_END );

				request.setAttribute("msg", "Aditivo em modo de Adição" );
				request.setAttribute("modelAditivo", modelAditivo);
				request.setAttribute("listaAditivo", listaAditivo);
				request.setAttribute("totalPagina" , daoAditivo.getTotalPag( OFFSET_END, modelAditivo.getId_contrato() ) );
				request.getRequestDispatcher("principal/aditivo.jsp").forward(request, response);
				
			 }else {
					request.getRequestDispatcher("principal/aditivo.jsp").forward(request, response);
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
			String id_aditivado               = request.getParameter("id_aditivado"              );
			String dt_criacao                 = request.getParameter("dt_criacao"                );
			String id_contrato                = request.getParameter("id_contrato"               );
			String id_cliente                 = request.getParameter("id_cliente"                );
			String nomeCliente                = request.getParameter("nomeCliente"               );
			String id_status_aditivo          = request.getParameter("id_status_aditivo"         );
			String id_fase_contrato           = request.getParameter("id_fase_contrato"          );
			String id_ciclo_updadate          = request.getParameter("id_ciclo_updadate"         );
			String id_tipo_aditivo            = request.getParameter("id_tipo_aditivo"           );
			String desc_serv_contratado       = request.getParameter("desc_serv_contratado"      );
			String recurso_temporario         = request.getParameter("recurso_temporario"        );
			String adti_sem_receita           = request.getParameter("adti_sem_receita"          ); 
			String aprovacao_adit_sem_receita = request.getParameter("aprovacao_adit_sem_receita");
			String qty_usuario_contratada     = request.getParameter("qty_usuario_contratada"    );
			String observacao                 = request.getParameter("observacao"                );
			String id_vigencia                = request.getParameter("id_vigencia"               );
			String dt_criacao_vigencia        = request.getParameter("dt_criacao_vigencia"       );
			String id_tempo_contrato          = request.getParameter("selectTempoContrato"       );
			String dt_inicio                  = request.getParameter("dt_inicio"                 );
			String dt_final                   = request.getParameter("dt_final"                  );
			String observacaoVigencia         = request.getParameter("observacaoVigencia"        );
			String vlr_toral_adit             = request.getParameter("vlr_toral_adit"            );

		    if(vlr_toral_adit != null && !vlr_toral_adit.isEmpty()) {
		    	vlr_toral_adit = Normalizer.normalize(vlr_toral_adit, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	vlr_toral_adit = vlr_toral_adit.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }
		    
		    ModelAditivo modelAditivo = new ModelAditivo();
		    modelAditivo.setId_aditivado              ( id_aditivado               != null && !id_aditivado.isEmpty()               ? Long.parseLong(id_aditivado                 ) : null );
		    modelAditivo.setDt_criacao                ( dt_criacao                 != null && !dt_criacao.isEmpty()                 ? dt_criacao                                    : null );
		    modelAditivo.setId_contrato               ( id_contrato                != null && !id_contrato.isEmpty()                ? Long.parseLong(id_contrato                  ) : null );
		    modelAditivo.setId_cliente                ( id_cliente                 != null && !id_cliente.isEmpty()                 ? Long.parseLong(id_cliente                   ) : null );
		    modelAditivo.setNomeCliente               ( nomeCliente                != null && !nomeCliente.isEmpty()                ? nomeCliente                                   : null );
		    modelAditivo.setId_status_aditivo         ( id_status_aditivo          != null && !id_status_aditivo.isEmpty()          ? Long.parseLong(id_status_aditivo            ) : null );
		    modelAditivo.setId_fase_contrato          ( id_fase_contrato           != null && !id_fase_contrato.isEmpty()           ? Long.parseLong(id_fase_contrato             ) : null );
		    modelAditivo.setId_ciclo_update           ( id_ciclo_updadate          != null && !id_ciclo_updadate.isEmpty()          ? Long.parseLong(id_ciclo_updadate            ) : null );
		    modelAditivo.setId_tipo_aditivo           ( id_tipo_aditivo            != null && !id_tipo_aditivo.isEmpty()            ? Long.parseLong(id_tipo_aditivo              ) : null );
		    modelAditivo.setDesc_serv_contratado      ( desc_serv_contratado       != null && !desc_serv_contratado.isEmpty()       ? desc_serv_contratado                          : null );
		    modelAditivo.setRecurso_temporario        ( recurso_temporario         != null && !recurso_temporario.isEmpty()         ? Integer.parseInt(recurso_temporario         ) : null );
		    modelAditivo.setAdti_sem_receita          ( adti_sem_receita           != null && !adti_sem_receita.isEmpty()           ? Integer.parseInt(adti_sem_receita           ) : null );
		    modelAditivo.setAprovacao_adit_sem_receita( aprovacao_adit_sem_receita != null && !aprovacao_adit_sem_receita.isEmpty() ? Integer.parseInt(aprovacao_adit_sem_receita ) : null );
		    modelAditivo.setQty_usuario_contratada    ( qty_usuario_contratada     != null && !qty_usuario_contratada.isEmpty()     ? qty_usuario_contratada                        : null );
		    modelAditivo.setObservacao                ( observacao                 != null && !observacao.isEmpty()                 ? observacao                                    : null );
		    modelAditivo.setId_vigencia               ( id_vigencia                != null && !id_vigencia.isEmpty()                ? Long.parseLong(id_vigencia                  ) : null );
		    modelAditivo.setDt_criacao_vigencia       ( dt_criacao_vigencia        != null && !dt_criacao_vigencia.isEmpty()        ? dt_criacao_vigencia                           : null );
		    modelAditivo.setId_tempo_contrato         ( id_tempo_contrato          != null && !id_tempo_contrato.isEmpty()          ? Long.parseLong(id_tempo_contrato            ) : null );
		    modelAditivo.setDt_inicio                 ( dt_inicio                  != null && !dt_inicio.isEmpty()                  ? dt_inicio                                     : null );
		    modelAditivo.setDt_final                  ( dt_final                   != null && !dt_final.isEmpty()                   ? dt_final                                      : null );
		    modelAditivo.setObservacao_vigencia       ( observacaoVigencia         != null && !observacaoVigencia.isEmpty()         ? observacaoVigencia                            : null );
		    modelAditivo.setVlr_toral_adit            ( vlr_toral_adit             != null && !vlr_toral_adit.isEmpty()             ? vlr_toral_adit                                : null );

		    
	    	if(modelAditivo.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	modelAditivo = daoAditivo.gravarAditivo( modelAditivo );
			List<ModelListaAditivo> listaAditivo = daoAditivo.buscarListaAditivo( modelAditivo.getId_contrato(), offsetBegin, OFFSET_END );

		    request.setAttribute("msg", msg );
		    request.setAttribute("modelAditivo", modelAditivo);
			request.setAttribute("listaAditivo", listaAditivo);
			request.setAttribute("totalPagina" , daoAditivo.getTotalPag( OFFSET_END, modelAditivo.getId_contrato() ) );
		    request.getRequestDispatcher("principal/aditivo.jsp").forward(request, response);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
