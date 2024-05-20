package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOFaseContrato;
import br.com.portal.model.ModelFaseContrato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsFaseContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOFaseContrato daoFaseContrato = new DAOFaseContrato();
       

    public ServletsFaseContrato() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelFaseContrato> listFaseContratos = daoFaseContrato.listaFaseContrato();
				request.setAttribute("listFaseContratos", listFaseContratos);
				request.getRequestDispatcher("manutencao/manutencaoFaseContrato.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelFaseContrato listFaseContrato = new ModelFaseContrato();
					    
					listFaseContrato = daoFaseContrato.getFaseContratoID( Long.parseLong( id ) );
					List<ModelFaseContrato> listFaseContratos = daoFaseContrato.listaFaseContrato();

					request.setAttribute("listFaseContrato" , listFaseContrato );
					request.setAttribute("listFaseContratos", listFaseContratos);
					request.getRequestDispatcher("manutencao/manutencaoFaseContrato.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoFaseContrato.deletarRegistro(id);
					
					List<ModelFaseContrato> listFaseContratos = daoFaseContrato.listaFaseContrato();
					request.setAttribute("listFaseContratos", listFaseContratos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoFaseContrato.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoFaseContrato.jsp").forward(request, response);
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
			String id_fase_contrato = request.getParameter( "id_fase_contrato" );
			String dt_criacao       = request.getParameter( "dt_criacao"       );
			String fase_contrato    = request.getParameter( "fase_contrato"    );
			String observacao       = request.getParameter( "observacao"       );
						
			ModelFaseContrato listFaseContrato = new ModelFaseContrato();

			listFaseContrato.setId_fase_contrato( id_fase_contrato != null && !id_fase_contrato.isEmpty() ? Long.parseLong( id_fase_contrato ) : null );
			listFaseContrato.setDt_criacao      ( dt_criacao       != null && !dt_criacao.isEmpty()       ? dt_criacao                         : null );
			listFaseContrato.setFase_contrato   ( fase_contrato    != null && !fase_contrato.isEmpty()    ? fase_contrato                      : null );
			listFaseContrato.setObservacao      ( observacao       != null && !observacao.isEmpty()       ? observacao                         : null );

	    	if(listFaseContrato.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listFaseContrato = daoFaseContrato.gravarAtualizaRegistros( listFaseContrato );

	    	List<ModelFaseContrato> listFaseContratos = daoFaseContrato.listaFaseContrato();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listFaseContrato" , listFaseContrato );
			request.setAttribute("listFaseContratos", listFaseContratos);
			request.getRequestDispatcher("manutencao/manutencaoFaseContrato.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
