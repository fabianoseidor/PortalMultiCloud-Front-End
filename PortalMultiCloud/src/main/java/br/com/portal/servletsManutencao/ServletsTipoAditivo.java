package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOTipoAditivo;
import br.com.portal.model.ModelTipoAditivo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsTipoAditivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOTipoAditivo daoTipoAditivo = new DAOTipoAditivo();
       

    public ServletsTipoAditivo() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelTipoAditivo> listTipoAditivos = daoTipoAditivo.listaTipoAditivo();
				request.setAttribute("listTipoAditivos", listTipoAditivos);
				request.getRequestDispatcher("manutencao/manutencaoTipoAditivo.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelTipoAditivo listTipoAditivo = new ModelTipoAditivo();
					    
					listTipoAditivo = daoTipoAditivo.getTipoAditivoID( Long.parseLong( id ) );
					List<ModelTipoAditivo> listTipoAditivos = daoTipoAditivo.listaTipoAditivo();

					request.setAttribute("listTipoAditivo" , listTipoAditivo );
					request.setAttribute("listTipoAditivos", listTipoAditivos);
					request.getRequestDispatcher("manutencao/manutencaoTipoAditivo.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoTipoAditivo.deletarRegistro(id);
					
					List<ModelTipoAditivo> listTipoAditivos = daoTipoAditivo.listaTipoAditivo();
					request.setAttribute("listTipoAditivos", listTipoAditivos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoTipoAditivo.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoTipoAditivo.jsp").forward(request, response);
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
			String id_tipo_aditivo  = request.getParameter( "id_tipo_aditivo" );
			String dt_criacao       = request.getParameter( "dt_criacao"      );
			String desc_tipo_ditivo = request.getParameter( "desc_tipo_ditivo"       );
			String observacao       = request.getParameter( "observacao"      );
						
			ModelTipoAditivo listTipoAditivo = new ModelTipoAditivo();

			listTipoAditivo.setId_tipo_aditivo ( id_tipo_aditivo != null && !id_tipo_aditivo.isEmpty() ? Long.parseLong( id_tipo_aditivo ) : null );
			listTipoAditivo.setDt_criacao      ( dt_criacao      != null && !dt_criacao.isEmpty()      ? dt_criacao                        : null );
			listTipoAditivo.setDesc_tipo_ditivo( desc_tipo_ditivo!= null && !desc_tipo_ditivo.isEmpty()? desc_tipo_ditivo                  : null );
			listTipoAditivo.setObservacao      ( observacao      != null && !observacao.isEmpty()      ? observacao                        : null );

	    	if(listTipoAditivo.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listTipoAditivo = daoTipoAditivo.gravarAtualizaRegistros( listTipoAditivo );

	    	List<ModelTipoAditivo> listTipoAditivos = daoTipoAditivo.listaTipoAditivo();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listTipoAditivo" , listTipoAditivo );
			request.setAttribute("listTipoAditivos", listTipoAditivos);
			request.getRequestDispatcher("manutencao/manutencaoTipoAditivo.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
