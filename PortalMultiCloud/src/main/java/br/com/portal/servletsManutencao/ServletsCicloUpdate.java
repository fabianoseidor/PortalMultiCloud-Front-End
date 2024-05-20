package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOCicloUpdate;
import br.com.portal.model.ModelCicloUpdate;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsCicloUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOCicloUpdate daoCicloUpdate = new DAOCicloUpdate();
       

    public ServletsCicloUpdate() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelCicloUpdate> listCicloUpdates = daoCicloUpdate.listaCicloUpdate();
				request.setAttribute("listCicloUpdates", listCicloUpdates);
				request.getRequestDispatcher("manutencao/manutencaoCicloUpdate.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelCicloUpdate listCicloUpdate = new ModelCicloUpdate();
					    
					listCicloUpdate = daoCicloUpdate.getCicloUpdateID( Long.parseLong( id ) );
					List<ModelCicloUpdate> listCicloUpdates = daoCicloUpdate.listaCicloUpdate();

					request.setAttribute("listCicloUpdate" , listCicloUpdate );
					request.setAttribute("listCicloUpdates", listCicloUpdates);
					request.getRequestDispatcher("manutencao/manutencaoCicloUpdate.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoCicloUpdate.deletarRegistro(id);
					
					List<ModelCicloUpdate> listCicloUpdates = daoCicloUpdate.listaCicloUpdate();
					request.setAttribute("listCicloUpdates", listCicloUpdates);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoCicloUpdate.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoCicloUpdate.jsp").forward(request, response);
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
			String id_ciclo_update = request.getParameter( "id_ciclo_update" );
			String dt_criacao      = request.getParameter( "dt_criacao"      );
			String descricao       = request.getParameter( "descricao"       );
			String observacao      = request.getParameter( "observacao"      );
						
			ModelCicloUpdate listCicloUpdate = new ModelCicloUpdate();

			listCicloUpdate.setId_ciclo_update( id_ciclo_update != null && !id_ciclo_update.isEmpty() ? Long.parseLong( id_ciclo_update ) : null );
			listCicloUpdate.setDt_criacao     ( dt_criacao      != null && !dt_criacao.isEmpty()      ? dt_criacao                        : null );
			listCicloUpdate.setDescricao      ( descricao       != null && !descricao.isEmpty()       ? descricao                         : null );
			listCicloUpdate.setObservacao     ( observacao      != null && !observacao.isEmpty()      ? observacao                        : null );

	    	if(listCicloUpdate.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listCicloUpdate = daoCicloUpdate.gravarAtualizaRegistros( listCicloUpdate );

	    	List<ModelCicloUpdate> listCicloUpdates = daoCicloUpdate.listaCicloUpdate();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listCicloUpdate" , listCicloUpdate );
			request.setAttribute("listCicloUpdates", listCicloUpdates);
			request.getRequestDispatcher("manutencao/manutencaoCicloUpdate.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
