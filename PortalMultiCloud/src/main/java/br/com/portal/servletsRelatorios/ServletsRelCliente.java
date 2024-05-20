package br.com.portal.servletsRelatorios;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import br.com.portal.dao.relatorio.DAORelClienteContrato;
import br.com.portal.modelRelatorio.ModelRelClienteContrato;

public class ServletsRelCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ServletsRelCliente() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	try {
	   		String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarContratoPEP") ) {
				String pep            = request.getParameter("pep");
				String statusContrato = request.getParameter("statusContrato");
				if( pep != null && !pep.isEmpty() ) {
					Gson gson    = new Gson();
					String lista = null;
					
					DAORelClienteContrato daoRelClienteContrato = new DAORelClienteContrato();
					List<ModelRelClienteContrato> listaRelClienteContrato = null;
					if(Integer.valueOf(statusContrato) == 1)
					   listaRelClienteContrato = daoRelClienteContrato.getListaInfoContratoAtivos( pep );
					else listaRelClienteContrato = daoRelClienteContrato.getListaInfoContratoDesativos( pep );
					request.setAttribute( "listaRelClienteContrato", listaRelClienteContrato );
				    
					if( listaRelClienteContrato != null ) lista = gson.toJson(listaRelClienteContrato);
					else lista = "NAOEXISTE";
					response.getWriter().write(lista);
				} 
		    }else{
				  request.getRequestDispatcher("principal/relCliente.jsp").forward(request, response);
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
