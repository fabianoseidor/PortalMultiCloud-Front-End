package br.com.portal.servlets;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOContratoRepository;
import br.com.portal.dao.DAORascunho;
import br.com.portal.model.ModelDesativacaoContrato;
import br.com.portal.model.ModelRscunho;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletRascunho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAORascunho daoRascunho = new DAORascunho();
       
    public ServletRascunho() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	try {
	   		String acao = request.getParameter("acao");
	   		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaListaRascunho") ) {
	   			
	   			List<ModelRscunho> listaRascunhos = daoRascunho.getRascunho();			    
			    Gson gson = new Gson();
			    String lista = gson.toJson(listaRascunhos);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizaStatusContrato") ) {
				
		   		String idContrato = request.getParameter( "idContrato" );
		   		String retornoInfoRenovacao = daoRascunho.getInfoRenovacao(Long.parseLong( idContrato ));
		   		String textoSplit [] = retornoInfoRenovacao.split(";");
		   		
		   		Boolean renovacao = ( Integer.valueOf(textoSplit[0].trim()) == 0 ? false : true );
		   		if( renovacao ) {
		   			DAOContratoRepository daoContratoRepository = new DAOContratoRepository();	
		   		    Long renovacaoContratoOrigem = ( textoSplit[1] != null && !textoSplit[1].trim().isEmpty() ? Long.parseLong(textoSplit[1].trim()) : null );
					HttpServletRequest req = (HttpServletRequest) request;
				    HttpSession session    = req.getSession();
	                String userDesativacao = (String) session.getAttribute("usuario");
					// Cancelamento de contrato em caso de renovacao.
					ModelDesativacaoContrato DesativacaoContrato = new ModelDesativacaoContrato();
					DesativacaoContrato.setUser_desativacao( userDesativacao );
					DesativacaoContrato.setId_contrato     ( renovacaoContratoOrigem      );
					String retorno = daoContratoRepository.CancelaContrato( DesativacaoContrato, 1 );
					if( retorno == null )
					retorno = "O Contrato: " + idContrato + " e todo(s) os produtos e recusso(s) a ele vinculados(caso exista), foram cancelados com sucesso";
		   		}
		   		String Error = daoRascunho.atualizaStatusRascunhoContrato( Long.parseLong( idContrato ) );
		   		
		   		if( Error == null ) Error = "SUCESSO";
		    
			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizaStatusAditivo") ) {
				
		   		String idContrato = request.getParameter( "idContrato" );
		   		String idAditivo  = request.getParameter( "idAditivo"  );

		   		String Error = daoRascunho.atualizaStatusRascunhoAditivo( Long.parseLong( idContrato ), Long.parseLong( idAditivo ) );
		   		
		   		if( Error == null ) Error = "SUCESSO";
		    
			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deleteContrato") ) {
				
		   		String idContrato = request.getParameter( "idContrato" );

		   		String Error = daoRascunho.deleteContrato( Long.parseLong( idContrato ) );
		   		
		   		if( Error == null ) Error = "SUCESSO";
		    
			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deleteAditivo") ) {
				
		   		String idAditivo = request.getParameter( "idAditivo" );

		   		String Error = daoRascunho.deleteAditivo( Long.parseLong( idAditivo ) );
		   		
		   		if( Error == null ) Error = "SUCESSO";
		    
			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else{
				  request.getRequestDispatcher("principal/rascunho.jsp").forward(request, response);
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
