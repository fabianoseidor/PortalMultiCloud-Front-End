package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOTipoDisco;
import br.com.portal.model.ModelTipoDisco;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsTipoDisco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOTipoDisco daoTipoDisco  = new DAOTipoDisco();
       

    public ServletsTipoDisco() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTipoDiscoInicial") ) {

				List<ModelTipoDisco> listTipoDiscos = daoTipoDisco.getListaTipoDisco();
				request.setAttribute("listTipoDiscos", listTipoDiscos);
				request.getRequestDispatcher("manutencao/manutencaoTipoDisco.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String idTipDisco = request.getParameter("idTipDisco");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelTipoDisco listTipoDisco = new ModelTipoDisco();
					    
					listTipoDisco = daoTipoDisco.getTipoDiscoID( Long.parseLong( idTipDisco ) );
					List<ModelTipoDisco> listTipoDiscos = daoTipoDisco.getListaTipoDisco();

					request.setAttribute("listTipoDisco" , listTipoDisco );
					request.setAttribute("listTipoDiscos", listTipoDiscos);
					request.getRequestDispatcher("manutencao/manutencaoTipoDisco.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("idTipDisco");
					
					if(id != null && !id.isEmpty()) 
				  	   daoTipoDisco.deletarRegistro(id);
					
					List<ModelTipoDisco> listTipoDiscos = daoTipoDisco.getListaTipoDisco();
					request.setAttribute("listTipoDiscos", listTipoDiscos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoTipoDisco.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoTipoDisco.jsp").forward(request, response);
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
			String id_tipo_disco = request.getParameter( "id_tipo_disco" );
			String dt_criacao    = request.getParameter( "dt_criacao"  );
			String tipo_disco    = request.getParameter( "tipo_disco"  );
			String observacao    = request.getParameter( "observacao"  );
						
			ModelTipoDisco listTipoDisco = new ModelTipoDisco();

			listTipoDisco.setId_tipo_disco( id_tipo_disco != null && !id_tipo_disco.isEmpty() ? Long.parseLong( id_tipo_disco ) : null );
			listTipoDisco.setDt_criacao   ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                      : null );
			listTipoDisco.setTipo_disco   ( tipo_disco    != null && !tipo_disco.isEmpty()    ? tipo_disco                      : null );
			listTipoDisco.setObs          ( observacao    != null && !observacao.isEmpty()    ? observacao                      : null );

	    	if(listTipoDisco.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listTipoDisco = daoTipoDisco.gravarTipoDisco( listTipoDisco );

	    	List<ModelTipoDisco> listTipoDiscos = daoTipoDisco.getListaTipoDisco();
		    request.setAttribute("msg"           , msg           );
			request.setAttribute("listTipoDisco"  , listTipoDisco );
			request.setAttribute("listTipoDiscos", listTipoDiscos);
			request.getRequestDispatcher("manutencao/manutencaoTipoDisco.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
