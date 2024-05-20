package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOTipoRascunho;
import br.com.portal.model.ModelTipoRascunho;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletsTipoRascunho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOTipoRascunho daoTipoRascunho = new DAOTipoRascunho(); 
	
	
       
    public ServletsTipoRascunho() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTipoRascunhoInicial") ) {
				
				List<ModelTipoRascunho> listTipoRascunhos = daoTipoRascunho.getListaTipoRascunho();
				request.setAttribute("listTipoRascunhos", listTipoRascunhos);
				request.getRequestDispatcher("manutencao/manutencaoTipoRascunho.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String idTipoRascunho = request.getParameter("idTipoRascunho");
				request.setAttribute("msg", "Ambiente em edicao!" );
				
				ModelTipoRascunho       listTipoRascunho  = daoTipoRascunho.getTipoRascunhoID( Long.parseLong( idTipoRascunho ) );
				List<ModelTipoRascunho> listTipoRascunhos = daoTipoRascunho.getListaTipoRascunho();

				request.setAttribute("listTipoRascunho" , listTipoRascunho );
				request.setAttribute("listTipoRascunhos", listTipoRascunhos);
				request.getRequestDispatcher("manutencao/manutencaoTipoRascunho.jsp").forward(request, response);
				    
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
				String id = request.getParameter("idTipoRascunho");
				
				if(id != null && !id.isEmpty()) 
					daoTipoRascunho.deletarTipoRascunho(id);

				List<ModelTipoRascunho> listTipoRascunhos = daoTipoRascunho.getListaTipoRascunho();
				request.setAttribute("listTipoRascunhos", listTipoRascunhos);
				request.setAttribute("msg", "Registro excluido com sucesso!");
				request.getRequestDispatcher("manutencao/manutencaoTipoRascunho.jsp").forward(request, response);

			}else {
			  request.getRequestDispatcher("manutencao/manutencaoTipoRascunho.jsp").forward(request, response);
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
			String id_tipo_rascunho = request.getParameter( "id_tipo_rascunho");
			String rascunho         = request.getParameter( "rascunho"  );
			String dt_criacao       = request.getParameter( "dt_criacao" );
			String obs              = request.getParameter( "obs"          );

			ModelTipoRascunho listTipoRascunho = new ModelTipoRascunho();

			listTipoRascunho.setId_tipo_rascunho( id_tipo_rascunho != null && !id_tipo_rascunho.isEmpty() ? Long.parseLong( id_tipo_rascunho ) : null );
			listTipoRascunho.setDt_criacao      ( dt_criacao       != null && !dt_criacao.isEmpty()       ? dt_criacao                         : null );
			listTipoRascunho.setRascunho        ( rascunho         != null && !rascunho.isEmpty()         ? rascunho                           : null );
			listTipoRascunho.setObs             ( obs              != null && !obs.isEmpty()              ? obs                                : null );

	    	if(listTipoRascunho.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listTipoRascunho = daoTipoRascunho.gravarTipoRascunho( listTipoRascunho );
	    	
	    	List<ModelTipoRascunho> listTipoRascunhos = daoTipoRascunho.getListaTipoRascunho();
		    request.setAttribute("msg", msg );
			request.setAttribute("listTipoRascunho" , listTipoRascunho );
			request.setAttribute("listTipoRascunhos", listTipoRascunhos);
			request.getRequestDispatcher("manutencao/manutencaoTipoRascunho.jsp").forward(request, response);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
