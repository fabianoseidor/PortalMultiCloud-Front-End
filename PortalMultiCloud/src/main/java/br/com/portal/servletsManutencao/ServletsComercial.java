package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOComercial;
import br.com.portal.model.ModelComercial;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletsComercial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOComercial daoComercial = new DAOComercial();
    public ServletsComercial() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaComercialInicial") ) {
				DAOComercial daoComercial = new DAOComercial();
				
				List<ModelComercial> listComercias = daoComercial.getListaComercial();
				request.setAttribute("listComercias", listComercias);
				request.getRequestDispatcher("manutencao/manutencaoComercial.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String idComercial = request.getParameter("idComercial");
				request.setAttribute("msg", "Ambiente em edicao!" );
				
				ModelComercial       listComercial = daoComercial.getComercialID( Long.parseLong( idComercial ) );
				List<ModelComercial> listComercias = daoComercial.getListaComercial();

				request.setAttribute("listComercial" , listComercial );
				request.setAttribute("listComercias", listComercias);
				request.getRequestDispatcher("manutencao/manutencaoComercial.jsp").forward(request, response);
				    
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
				String id = request.getParameter("idComercial");
				
				if(id != null && !id.isEmpty()) 
			  	   daoComercial.deletarComercial(id);

				List<ModelComercial> listComercias = daoComercial.getListaComercial();
				request.setAttribute("listComercias", listComercias);
				request.setAttribute("msg", "Registro excluido com sucesso!");
				request.getRequestDispatcher("manutencao/manutencaoComercial.jsp").forward(request, response);

			}else {
			  request.getRequestDispatcher("manutencao/manutencaoComercial.jsp").forward(request, response);
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
			String id_comercial   = request.getParameter( "id_comercial"   );
			String dt_criacao     = request.getParameter( "dt_cadastro"    );
			String nome_comercial = request.getParameter( "nome_comercial" );
			String telefone       = request.getParameter( "telefone"       );
			String email          = request.getParameter( "email"          );
			String obs            = request.getParameter( "obs"            );

			ModelComercial listComercial = new ModelComercial();

			listComercial.setId_comercial  ( id_comercial   != null && !id_comercial.isEmpty()   ? Long.parseLong( id_comercial ) : null );
			listComercial.setDt_cadastro   ( dt_criacao     != null && !dt_criacao.isEmpty()     ? dt_criacao                     : null );
			listComercial.setNome_comercial( nome_comercial != null && !nome_comercial.isEmpty() ? nome_comercial                 : null );
			listComercial.setTelefone      ( telefone       != null && !telefone.isEmpty()       ? telefone                       : null );
			listComercial.setEmail         ( email          != null && !email.isEmpty()          ? email                          : null );
			listComercial.setObs           ( obs            != null && !obs.isEmpty()            ? obs                            : null );

	    	if(listComercial.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listComercial = daoComercial.gravarComercial( listComercial );
	    	
	    	List<ModelComercial> listComercias = daoComercial.getListaComercial();
		    request.setAttribute("msg", msg );
			request.setAttribute("listComercial", listComercial );
			request.setAttribute("listComercias", listComercias );
			request.getRequestDispatcher("manutencao/manutencaoComercial.jsp").forward(request, response);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
