package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOStatusContrato;
import br.com.portal.model.ModelStatusContrato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsStatusContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOStatusContrato daoStatusContrato  = new DAOStatusContrato();       

    public ServletsStatusContrato() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelStatusContrato> listStatusContratos = daoStatusContrato.listaStatusContrato();
				request.setAttribute("listStatusContratos", listStatusContratos);
				request.getRequestDispatcher("manutencao/manutencaoStatusContrato.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelStatusContrato listStatusContrato = new ModelStatusContrato();
					    
					listStatusContrato = daoStatusContrato.getStatusContratoID( Long.parseLong( id ) );
					List<ModelStatusContrato> listStatusContratos = daoStatusContrato.listaStatusContrato();

					request.setAttribute("listStatusContrato" , listStatusContrato );
					request.setAttribute("listStatusContratos", listStatusContratos);
					request.getRequestDispatcher("manutencao/manutencaoStatusContrato.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoStatusContrato.deletarRegistro(id);
					
					List<ModelStatusContrato> listStatusContratos = daoStatusContrato.listaStatusContrato();
					request.setAttribute("listStatusContratos", listStatusContratos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoStatusContrato.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoStatusContrato.jsp").forward(request, response);
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
			String id_status_contrato = request.getParameter( "id_status_contrato" );
			String dt_criacao         = request.getParameter( "dt_criacao"         );
			String status_contrato    = request.getParameter( "status_contrato"    );
			String observacao         = request.getParameter( "observacao"         );
						
			ModelStatusContrato listStatusContrato = new ModelStatusContrato();

			listStatusContrato.setId_status_contrato( id_status_contrato != null && !id_status_contrato.isEmpty() ? Long.parseLong( id_status_contrato ) : null );
			listStatusContrato.setDt_criacao        ( dt_criacao         != null && !dt_criacao.isEmpty()         ? dt_criacao                           : null );
			listStatusContrato.setStatus_contrato   ( status_contrato    != null && !status_contrato.isEmpty()    ? status_contrato                      : null );
			listStatusContrato.setObservacao        ( observacao         != null && !observacao.isEmpty()         ? observacao                           : null );

	    	if(listStatusContrato.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listStatusContrato = daoStatusContrato.gravarAtualizaRegistros( listStatusContrato );

	    	List<ModelStatusContrato> listStatusContratos = daoStatusContrato.listaStatusContrato();
		    request.setAttribute("msg"                , msg                );
			request.setAttribute("listStatusContrato" , listStatusContrato );
			request.setAttribute("listStatusContratos", listStatusContratos);
			request.getRequestDispatcher("manutencao/manutencaoStatusContrato.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
