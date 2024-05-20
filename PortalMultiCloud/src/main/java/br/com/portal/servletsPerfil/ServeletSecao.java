package br.com.portal.servletsPerfil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import br.com.portal.daoPerfil.DAOSecao;
import br.com.portal.modelPerfil.ModelSecao;

public class ServeletSecao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOSecao daoSecao = new DAOSecao();
       
    public ServeletSecao() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSecaoInicial") ) {	
		    	List<ModelSecao> listSecaos = daoSecao.getListaSecao();
				request.setAttribute( "listSecaos", listSecaos );
				request.getRequestDispatcher("perfil/configSecao.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String idSecao = request.getParameter("idSecao");
					request.setAttribute("msg", "Ambiente em edicao!" );

					ModelSecao modelSecao  = daoSecao.getSecaoID(Long.parseLong( idSecao ));
			    	List<ModelSecao> listSecaos = daoSecao.getListaSecao();

			    	request.setAttribute("modelSecao" , modelSecao );
					request.setAttribute( "listSecaos", listSecaos );
					request.getRequestDispatcher("perfil/configSecao.jsp").forward(request, response);
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
					String id = request.getParameter("idSecao");
					
					if(id != null && !id.isEmpty()) 
						daoSecao.deletarSecao(id);

					List<ModelSecao> listSecaos = daoSecao.getListaSecao();
					request.setAttribute( "listSecaos", listSecaos );
					request.getRequestDispatcher("perfil/configSecao.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("perfil/configSecao.jsp").forward(request, response);
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
			// Informacoes capturadas da tela.
			String id_secao      = request.getParameter( "id_secao"      );
			String dt_criacao    = request.getParameter( "dt_criacao"    );
			String user_cadastro = request.getParameter( "user_cadastro" );
			String nome_secao    = request.getParameter( "nome_secao"    );
			String observacao    = request.getParameter( "observacao"    );

			ModelSecao modelSecao  = new ModelSecao();
			if( user_cadastro == null || user_cadastro.isEmpty() ) {
			    HttpServletRequest req = (HttpServletRequest) request;
			    HttpSession session    = req.getSession();
			    user_cadastro          = (String) session.getAttribute("usuario");
			}

			modelSecao.setId_secao     ( id_secao      != null && !id_secao.isEmpty()      ? Long.parseLong( id_secao ) : null );
			modelSecao.setDt_criacao   ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                 : null );
			modelSecao.setUser_cadastro( user_cadastro != null && !user_cadastro.isEmpty() ? user_cadastro              : null );
			modelSecao.setNome_secao   ( nome_secao    != null && !nome_secao.isEmpty()    ? nome_secao                 : null );
			modelSecao.setObs          ( observacao    != null && !observacao.isEmpty()    ? observacao                 : null );
	    	
			if(modelSecao.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";

			modelSecao = daoSecao.gravacao(modelSecao);

	    	List<ModelSecao> listSecaos = daoSecao.getListaSecao();
		    request.setAttribute( "msg"       , msg        );
			request.setAttribute("modelSecao" , modelSecao );
			request.setAttribute( "listSecaos", listSecaos );
			request.getRequestDispatcher("perfil/configSecao.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	
	}

}
