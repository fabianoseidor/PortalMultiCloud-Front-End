package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOSuporteB1;
import br.com.portal.model.ModelSuporteB1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletsSuporteB1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAOSuporteB1 daoSuporteB1 = new DAOSuporteB1(); 
    
    public ServletsSuporteB1() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSuporteB1Inicial") ) {
				
				List<ModelSuporteB1> listSuporteB1s = daoSuporteB1.getListaSuporteB1();
				request.setAttribute("listSuporteB1s", listSuporteB1s);
				request.getRequestDispatcher("manutencao/manutencaoSuporte_B1.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String idSuporteB1 = request.getParameter("idSuporteB1");
				request.setAttribute("msg", "Ambiente em edicao!" );
				
				ModelSuporteB1       listSuporteB1 = daoSuporteB1.getSuporteB1ID( Long.parseLong( idSuporteB1 ) );
				List<ModelSuporteB1> listSuporteB1s = daoSuporteB1.getListaSuporteB1();

				request.setAttribute("listSuporteB1" , listSuporteB1 );
				request.setAttribute("listSuporteB1s", listSuporteB1s);
				request.getRequestDispatcher("manutencao/manutencaoSuporte_B1.jsp").forward(request, response);
				    
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
				String id = request.getParameter("idSuporteB1");
				
				if(id != null && !id.isEmpty()) 
					daoSuporteB1.deletarSuporteB1(id);

				List<ModelSuporteB1> listSuporteB1s = daoSuporteB1.getListaSuporteB1();
				request.setAttribute("listSuporteB1s", listSuporteB1s);
				request.setAttribute("msg", "Registro excluido com sucesso!");
				request.getRequestDispatcher("manutencao/manutencaoSuporte_B1.jsp").forward(request, response);

			}else {
			  request.getRequestDispatcher("manutencao/manutencaoSuporte_B1.jsp").forward(request, response);
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
			String id_suporte_b1   = request.getParameter( "id_suporte_b1");
			String dt_criacao     = request.getParameter( "dt_cadastro"  );
			String nome_suporte = request.getParameter( "nome_suporte" );
			String telefone       = request.getParameter( "telefone"     );
			String email          = request.getParameter( "email"        );
			String obs            = request.getParameter( "obs"          );

			ModelSuporteB1 listSuporteB1 = new ModelSuporteB1();

			listSuporteB1.setId_suporte_b1( id_suporte_b1 != null && !id_suporte_b1.isEmpty() ? Long.parseLong( id_suporte_b1 ) : null );
			listSuporteB1.setDt_cadastro  ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                      : null );
			listSuporteB1.setNome_suporte ( nome_suporte  != null && !nome_suporte.isEmpty()  ? nome_suporte                    : null );
			listSuporteB1.setTelefone     ( telefone      != null && !telefone.isEmpty()      ? telefone                        : null );
			listSuporteB1.setEmail        ( email         != null && !email.isEmpty()         ? email                           : null );
			listSuporteB1.setObs          ( obs           != null && !obs.isEmpty()           ? obs                             : null );

	    	if(listSuporteB1.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listSuporteB1 = daoSuporteB1.gravarSuporteB1( listSuporteB1 );
	    	
	    	List<ModelSuporteB1> listSuporteB1s = daoSuporteB1.getListaSuporteB1();
		    request.setAttribute("msg", msg );
			request.setAttribute("listSuporteB1", listSuporteB1 );
			request.setAttribute("listSuporteB1s", listSuporteB1s );
			request.getRequestDispatcher("manutencao/manutencaoSuporte_B1.jsp").forward(request, response);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
