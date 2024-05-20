package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOStatusRecurso;
import br.com.portal.model.ModelStatusRecurso;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsStatusRecurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOStatusRecurso daoStatusRecurso = new DAOStatusRecurso();
       

    public ServletsStatusRecurso() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelStatusRecurso> listStatusRecursos = daoStatusRecurso.getListaStatus();
				request.setAttribute("listStatusRecursos", listStatusRecursos);
				request.getRequestDispatcher("manutencao/manutencaoStatusRecurso.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelStatusRecurso listStatusRecurso = new ModelStatusRecurso();
					    
					listStatusRecurso = daoStatusRecurso.getStatusRecursoID( Long.parseLong( id ) );
					List<ModelStatusRecurso> listStatusRecursos = daoStatusRecurso.getListaStatus();

					request.setAttribute("listStatusRecurso" , listStatusRecurso );
					request.setAttribute("listStatusRecursos", listStatusRecursos);
					request.getRequestDispatcher("manutencao/manutencaoStatusRecurso.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoStatusRecurso.deletarRegistro(id);
					
					List<ModelStatusRecurso> listStatusRecursos = daoStatusRecurso.getListaStatus();
					request.setAttribute("listStatusRecursos", listStatusRecursos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoStatusRecurso.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoStatusRecurso.jsp").forward(request, response);
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
			// id_ambiente
			String id_status_recurso = request.getParameter( "id_status_recurso" );
			String dt_criacao        = request.getParameter( "dt_criacao"        );
			String status_recurso    = request.getParameter( "status_recurso"    );
			String observacao        = request.getParameter( "observacao"        );
						
			ModelStatusRecurso listStatusRecurso = new ModelStatusRecurso();

			listStatusRecurso.setId_status_recurso( id_status_recurso != null && !id_status_recurso.isEmpty() ? Long.parseLong( id_status_recurso ) : null );
			listStatusRecurso.setDt_criacao       ( dt_criacao        != null && !dt_criacao.isEmpty()        ? dt_criacao                          : null );
			listStatusRecurso.setStatus_recurso   ( status_recurso    != null && !status_recurso.isEmpty()    ? status_recurso                      : null );
			listStatusRecurso.setObservacao       ( observacao        != null && !observacao.isEmpty()        ? observacao                          : null );

	    	if(listStatusRecurso.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listStatusRecurso = daoStatusRecurso.gravarAtualizaRegistros( listStatusRecurso );

	    	List<ModelStatusRecurso> listStatusRecursos = daoStatusRecurso.getListaStatus();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listStatusRecurso" , listStatusRecurso );
			request.setAttribute("listStatusRecursos", listStatusRecursos);
			request.getRequestDispatcher("manutencao/manutencaoStatusRecurso.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
