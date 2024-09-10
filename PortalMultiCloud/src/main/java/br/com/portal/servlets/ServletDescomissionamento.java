package br.com.portal.servlets;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAODescomissionamento;
import br.com.portal.model.ModalDescomissionamento;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletDescomissionamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAODescomissionamento daoDescomissionamento = new DAODescomissionamento();
       
    public ServletDescomissionamento() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
            if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaDescomissionamentoDistrato") ) {
	   			
	   			List<ModalDescomissionamento> mdDescomissionamento = daoDescomissionamento.getDescomissionamentoDistrato();		    
			    Gson gson = new Gson();
			    String lista = gson.toJson(mdDescomissionamento);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizaStatusContrato") ) {

                String idContrato           = request.getParameter( "idContrato"           );
		   		String idDescomissionamento = request.getParameter( "idDescomissionamento" );
		   		String txDescReversao       = request.getParameter( "txDescReversao"       );

		   		String Error = null;
		   		
		   		Error = daoDescomissionamento.updateReversao( Long.parseLong( idDescomissionamento ), txDescReversao );
		   		if( Error.equals("SUCESSO") ) Error = daoDescomissionamento.atualizaStatusRascunhoContrato( Long.parseLong( idContrato ) );

			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizaSolicitacaoDesligue") ) {
		   		String idDescomissionamento = request.getParameter( "idDescomissionamento" );
		   		String idContrato           = request.getParameter( "idContrato"           );
		   		String idStatus             = request.getParameter( "idStatus"             );
		   		String obsDesligamento      = request.getParameter( "obsDesligamento"      );
		   		String dtSugeridaDeslgue    = request.getParameter( "dtSugeridaDeslgue"    );
		   		String idGmudPadrao         = request.getParameter( "idGmudPadrao"         );
		   		Long  idGmudPadraoParse     = 0L;
		   		
		   		if (idGmudPadrao != null && !idGmudPadrao.isEmpty() && idGmudPadrao.equalsIgnoreCase("atualizaSolicitacaoDesligue") ) idGmudPadraoParse = Long.parseLong(idGmudPadrao);
		   		
		   		String Error = null;
		   		
		   		Error = daoDescomissionamento.upSolicitarDesligue( Long.parseLong(idDescomissionamento), obsDesligamento, dtSugeridaDeslgue, idGmudPadraoParse );
		   		if( Error.equals("SUCESSO") ) Error = daoDescomissionamento.atualizaStatus(Long.parseLong(idContrato), Long.parseLong(idStatus) );

			    Gson gson = new Gson();
			    String lista = gson.toJson(Error);
			    response.getWriter().write(lista);
			}else{
				  request.getRequestDispatcher("principal/descomissionamento.jsp").forward(request, response);
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
