package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOSite;
import br.com.portal.model.ModelSite;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOSite daoSite = new DAOSite();
       

    public ServletsSite() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSelectNuvem") ) {
				String idNuvem = request.getParameter("idNuvem");
				
				List<ModelSite> listSites = daoSite.listaSiteSelectManutncao( Long.parseLong(idNuvem) );

			    Gson gson = new Gson();
			    String lista = gson.toJson(listSites);
			    response.getWriter().write(lista);
				
		     }
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id      = request.getParameter("id");
					String idNuvem = request.getParameter("idNuvem");
					
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelSite listSite = new ModelSite();
					    
					listSite = daoSite.getSiteID( Long.parseLong( id ) );
					List<ModelSite> listSites = daoSite.listaSiteSelectManutncao( Long.parseLong(idNuvem) );

					request.setAttribute("listSite" , listSite );
					request.setAttribute("listSites", listSites);
					request.getRequestDispatcher("manutencao/manutencaoSite.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id      = request.getParameter("id");
					String idNuvem = request.getParameter("idNuvem");
					
					if(id != null && !id.isEmpty()) 
				  	   daoSite.deletarRegistro(id);
					
					List<ModelSite> listSites = daoSite.listaSiteSelectManutncao( Long.parseLong(idNuvem) );
					request.setAttribute("listSites", listSites);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoSite.jsp").forward(request, response);
				} else {
				request.getRequestDispatcher("manutencao/manutencaoSite.jsp").forward(request, response);
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
			String id_site    = request.getParameter( "id_site"    );
			String dt_criacao = request.getParameter( "dt_criacao" );
			String nome       = request.getParameter( "nome"       );
			String observacao = request.getParameter( "observacao" );
			String id_nuvem   = request.getParameter( "id_nuvem"   );
						
			ModelSite listSite = new ModelSite();

			listSite.setId_site   ( id_site       != null && !id_site.isEmpty()       ? Long.parseLong( id_site )  : null );
			listSite.setDt_criacao( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                 : null );
			listSite.setNome      ( nome          != null && !nome.isEmpty()          ? nome                       : null );
			listSite.setObservacao( observacao    != null && !observacao.isEmpty()    ? observacao                 : null );
			listSite.setId_nuvem  ( id_nuvem      != null && !id_nuvem.isEmpty()      ? Long.parseLong( id_nuvem ) : null );
			
	    	if(listSite.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listSite = daoSite.gravarAtualizaRegistros( listSite );

	    	List<ModelSite> listSites = daoSite.listaSiteSelectManutncao( listSite.getId_nuvem() );
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listSite" , listSite );
			request.setAttribute("listSites", listSites);
			request.getRequestDispatcher("manutencao/manutencaoSite.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
