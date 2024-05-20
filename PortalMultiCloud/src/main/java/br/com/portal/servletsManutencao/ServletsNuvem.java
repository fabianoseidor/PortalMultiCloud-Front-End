package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAONuvem;
import br.com.portal.model.ModelNuvem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsNuvem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAONuvem daoNuvem = new DAONuvem();
       

    public ServletsNuvem() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelNuvem> listNuvems = daoNuvem.getListaNuvem();
				request.setAttribute("listNuvems", listNuvems);
				request.getRequestDispatcher("manutencao/manutencaoNuvem.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelNuvem listNuvem = new ModelNuvem();
					    
					listNuvem = daoNuvem.getNuvemID( Long.parseLong( id ) );
					List<ModelNuvem> listNuvems = daoNuvem.getListaNuvem();

					request.setAttribute("listNuvem" , listNuvem );
					request.setAttribute("listNuvems", listNuvems);
					request.getRequestDispatcher("manutencao/manutencaoNuvem.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoNuvem.deletarRegistro(id);
					
					List<ModelNuvem> listNuvems = daoNuvem.getListaNuvem();
					request.setAttribute("listNuvems", listNuvems);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoNuvem.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoNuvem.jsp").forward(request, response);
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
			String id_nuvem      = request.getParameter( "id_nuvem"      );
			String dt_criacao    = request.getParameter( "dt_criacao"    );
			String mome_parceiro = request.getParameter( "mome_parceiro" );
			String observacao    = request.getParameter( "observacao"    );
						
			ModelNuvem listNuvem = new ModelNuvem();

			listNuvem.setId_nuvem     ( id_nuvem      != null && !id_nuvem.isEmpty()      ? Long.parseLong( id_nuvem ) : null );
			listNuvem.setDt_criacao   ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                 : null );
			listNuvem.setMome_parceiro( mome_parceiro != null && !mome_parceiro.isEmpty() ? mome_parceiro              : null );
			listNuvem.setObservacao   ( observacao    != null && !observacao.isEmpty()    ? observacao                 : null );

	    	if(listNuvem.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listNuvem = daoNuvem.gravarAtualizaRegistros( listNuvem );

	    	List<ModelNuvem> listNuvems = daoNuvem.getListaNuvem();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listNuvem" , listNuvem );
			request.setAttribute("listNuvems", listNuvems);
			request.getRequestDispatcher("manutencao/manutencaoNuvem.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
