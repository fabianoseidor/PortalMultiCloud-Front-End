package br.com.portal.servlets.dashboard;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.dashboard.DAOFaturamentoProjetado;
import br.com.portal.model.dashboard.ModalFaturamentoProjetado;
import br.com.portal.model.dashboard.ModalPesquisa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsFaturamentoProjetado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOFaturamentoProjetado daoFaturamentoProjetado = new DAOFaturamentoProjetado();
       
     public ServletsFaturamentoProjetado() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaRazaoSocial") ) {
				String razaSocial = request.getParameter("razaSocial");
				List<ModalPesquisa> mdPesquisa = daoFaturamentoProjetado.getListaPesquisaRazaSocial(razaSocial);
				Gson gson = new Gson();
				String lista = "";
				if( mdPesquisa.size() > 0 ) {
  			        lista = gson.toJson(mdPesquisa);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaAlias") ) {
				String alias = request.getParameter("alias");
				List<ModalPesquisa> mdPesquisa = daoFaturamentoProjetado.getListaPesquisaAlias(alias);
				Gson gson = new Gson();
				String lista = "";
				if( mdPesquisa.size() > 0 ) {
  			        lista = gson.toJson(mdPesquisa);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaPEP") ) {
				String pep = request.getParameter("pep");
				List<ModalPesquisa> mdPesquisa = daoFaturamentoProjetado.getListaPesquisaPep(pep);
				Gson gson = new Gson();
				String lista = "";
				if( mdPesquisa.size() > 0 ) {
  			        lista = gson.toJson(mdPesquisa);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTela") ) {
				
				String idContrato = request.getParameter("idContrato");

				ModalFaturamentoProjetado mdFaturamentoProjetado = daoFaturamentoProjetado.montaContrato( Long.parseLong(idContrato) );

				Gson gson = new Gson();
				String lista = "";
				if( mdFaturamentoProjetado != null ) {
  			        lista = gson.toJson(mdFaturamentoProjetado);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
			}else{
				request.getRequestDispatcher("dashboard/dashFaturamentoProjetado.jsp").forward(request, response);
		    }
			
		}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
