package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOAmbiente;
import br.com.portal.model.ModelAmbiente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOAmbiente daoAmbiente  = new DAOAmbiente();
       

    public ServletsAmbiente() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaAmbienteInicial") ) {

				List<ModelAmbiente> listAmbientes = daoAmbiente.getListaAmbiente();
				request.setAttribute("listAmbientes", listAmbientes);
				request.getRequestDispatcher("manutencao/manutencaoAmbiente.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String idAmbiente = request.getParameter("idAmbiente");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelAmbiente listAmbiente = new ModelAmbiente();
					    
					listAmbiente = daoAmbiente.getAmbienteID( Long.parseLong( idAmbiente ) );
					List<ModelAmbiente> listAmbientes = daoAmbiente.getListaAmbiente();

					request.setAttribute("listAmbiente" , listAmbiente );
					request.setAttribute("listAmbientes", listAmbientes);
					request.getRequestDispatcher("manutencao/manutencaoAmbiente.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
					String id = request.getParameter("idAmbiente");
					
					if(id != null && !id.isEmpty()) 
				  	   daoAmbiente.deletarAmbiente(id);


					List<ModelAmbiente> listAmbientes = daoAmbiente.getListaAmbiente();
					request.setAttribute("listAmbientes", listAmbientes);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoAmbiente.jsp").forward(request, response);

				}else {
				request.getRequestDispatcher("manutencao/manutencaoAmbiente.jsp").forward(request, response);
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
			String id_aditivado = request.getParameter( "id_ambiente" );
			String dt_criacao   = request.getParameter( "dt_criacao"  );
			String ambiente     = request.getParameter( "ambiente"    );
			String observacao   = request.getParameter( "observacao"  );
						
			ModelAmbiente listAmbiente = new ModelAmbiente();

			listAmbiente.setId_ambiente( id_aditivado != null && !id_aditivado.isEmpty() ? Long.parseLong( id_aditivado ) : null );
			listAmbiente.setDt_criacao ( dt_criacao   != null && !dt_criacao.isEmpty()   ? dt_criacao                     : null );
			listAmbiente.setAmbiente   ( ambiente     != null && !ambiente.isEmpty()     ? ambiente                       : null );
			listAmbiente.setObs        ( observacao   != null && !observacao.isEmpty()   ? observacao                     : null );

	    	if(listAmbiente.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listAmbiente = daoAmbiente.gravarAmbiente( listAmbiente );

	    	List<ModelAmbiente> listAmbientes = daoAmbiente.getListaAmbiente();
		    request.setAttribute("msg", msg );
			request.setAttribute("listAmbiente" , listAmbiente );
			request.setAttribute("listAmbientes", listAmbientes);
			request.getRequestDispatcher("manutencao/manutencaoAmbiente.jsp").forward(request, response);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
