package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOStatusCliente;
import br.com.portal.model.ModelStatusCliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsStatusCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOStatusCliente daoStatusCliente  = new DAOStatusCliente();       

    public ServletsStatusCliente() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelStatusCliente> listStatusClientes = daoStatusCliente.getListaStatusCliente();
				request.setAttribute("listStatusClientes", listStatusClientes);
				request.getRequestDispatcher("manutencao/manutencaoStatusCliente.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelStatusCliente listStatusCliente = new ModelStatusCliente();
					    
					listStatusCliente = daoStatusCliente.getStatusClienteID( Long.parseLong( id ) );
					List<ModelStatusCliente> listStatusClientes = daoStatusCliente.getListaStatusCliente();

					request.setAttribute("listStatusCliente" , listStatusCliente );
					request.setAttribute("listStatusClientes", listStatusClientes);
					request.getRequestDispatcher("manutencao/manutencaoStatusCliente.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoStatusCliente.deletarRegistro(id);
					
					List<ModelStatusCliente> listStatusClientes = daoStatusCliente.getListaStatusCliente();
					request.setAttribute("listStatusClientes", listStatusClientes);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoStatusCliente.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoStatusCliente.jsp").forward(request, response);
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
			String id_status  = request.getParameter( "id_status"  );
			String dt_criacao = request.getParameter( "dt_criacao" );
			String status     = request.getParameter( "status"     );
			String observacao = request.getParameter( "observacao" );
						
			ModelStatusCliente listStatusCliente = new ModelStatusCliente();

			listStatusCliente.setId_status ( id_status  != null && !id_status.isEmpty()  ? Long.parseLong( id_status ) : null );
			listStatusCliente.setDt_criacao( dt_criacao != null && !dt_criacao.isEmpty() ? dt_criacao                  : null );
			listStatusCliente.setStatus    ( status     != null && !status.isEmpty()     ? status                      : null );
			listStatusCliente.setObservacao( observacao != null && !observacao.isEmpty() ? observacao                  : null );

	    	if(listStatusCliente.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listStatusCliente = daoStatusCliente.gravarAtualizaRegistros( listStatusCliente );

	    	List<ModelStatusCliente> listStatusClientes = daoStatusCliente.getListaStatusCliente();
		    request.setAttribute("msg"                , msg                );
			request.setAttribute("listStatusCliente" , listStatusCliente );
			request.setAttribute("listStatusClientes", listStatusClientes);
			request.getRequestDispatcher("manutencao/manutencaoStatusCliente.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
