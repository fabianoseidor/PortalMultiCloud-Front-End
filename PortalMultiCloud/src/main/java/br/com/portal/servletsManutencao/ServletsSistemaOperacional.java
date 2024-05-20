package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOSistemaOperacional;
import br.com.portal.model.ModeliSistemaOperacional;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsSistemaOperacional extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOSistemaOperacional daoSistemaOperacional  = new DAOSistemaOperacional();
       

    public ServletsSistemaOperacional() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSistemaOperacionalInicial") ) {

				List<ModeliSistemaOperacional> listSistemaOperacionals = daoSistemaOperacional.getListaSistemaOperacional();
				request.setAttribute("listSistemaOperacionals", listSistemaOperacionals);
				request.getRequestDispatcher("manutencao/manutencaoSistemaOperacional.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String idSO = request.getParameter("idSO");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModeliSistemaOperacional listSistemaOperacional = new ModeliSistemaOperacional();
					    
					listSistemaOperacional = daoSistemaOperacional.getSistemaOperacionalID( Long.parseLong( idSO ) );
					List<ModeliSistemaOperacional> listSistemaOperacionals = daoSistemaOperacional.getListaSistemaOperacional();

					request.setAttribute("listSistemaOperacional" , listSistemaOperacional );
					request.setAttribute("listSistemaOperacionals", listSistemaOperacionals);
					request.getRequestDispatcher("manutencao/manutencaoSistemaOperacional.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("idSO");
					
					if(id != null && !id.isEmpty()) 
				  	   daoSistemaOperacional.deletarRegistro(id);
					
					List<ModeliSistemaOperacional> listSistemaOperacionals = daoSistemaOperacional.getListaSistemaOperacional();
					request.setAttribute("listSistemaOperacionals", listSistemaOperacionals);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoSistemaOperacional.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoSistemaOperacional.jsp").forward(request, response);
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
			String id_so               = request.getParameter( "id_so"               );
			String dt_criacao          = request.getParameter( "dt_criacao"          );
			String sistema_operacional = request.getParameter( "sistema_operacional" );
			String observacao          = request.getParameter( "observacao"          );
						
			ModeliSistemaOperacional listSistemaOperacional = new ModeliSistemaOperacional();

			listSistemaOperacional.setId_so              ( id_so               != null && !id_so.isEmpty()               ? Long.parseLong( id_so ) : null );
			listSistemaOperacional.setDt_criacao         ( dt_criacao          != null && !dt_criacao.isEmpty()          ? dt_criacao              : null );
			listSistemaOperacional.setSistema_operacional( sistema_operacional != null && !sistema_operacional.isEmpty() ? sistema_operacional     : null );
			listSistemaOperacional.setObs                ( observacao          != null && !observacao.isEmpty()          ? observacao              : null );

	    	if(listSistemaOperacional.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listSistemaOperacional = daoSistemaOperacional.gravarSistemaOperacional( listSistemaOperacional );

	    	List<ModeliSistemaOperacional> listSistemaOperacionals = daoSistemaOperacional.getListaSistemaOperacional();
		    request.setAttribute("msg"                    , msg                    );
			request.setAttribute("listSistemaOperacional" , listSistemaOperacional );
			request.setAttribute("listSistemaOperacionals", listSistemaOperacionals);
			request.getRequestDispatcher("manutencao/manutencaoSistemaOperacional.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
