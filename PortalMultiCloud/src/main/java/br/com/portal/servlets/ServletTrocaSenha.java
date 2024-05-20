package br.com.portal.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOLoginRepository;
import br.com.portal.model.ModelLogin;
import br.com.portal.util.Md5Criptografia;


public class ServletTrocaSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository(); 
       
    public ServletTrocaSenha() { }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("updateSenha") ) {
				String password      = request.getParameter("password");
				String usuarioLogado = request.getParameter("login"   );
				String msnProc       = "Resultado: Senha atualizada com sucesso!";
				
		   		if( ( password != null && !password.isEmpty() ) ) {
		   				String novasenha = Md5Criptografia.convertPasswordToMD5( password );
					    daoLoginRepository.atualizaSenha(novasenha, usuarioLogado);
					   
					    Gson gson = new Gson();
					    String retornoMsn = gson.toJson(msnProc);
					    response.getWriter().write(retornoMsn);

		   		}

			}if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaSelectLogin") ) {

				List<ModelLogin> modellogins  = daoLoginRepository.getSelectLogin( );

				Gson gson = new Gson();
			    String lista = gson.toJson( modellogins );
			    response.getWriter().write(lista);
			    
			}else {
			   request.getRequestDispatcher("principal/cliente.jsp").forward(request, response);    
			}
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

	   		String senha1 = request.getParameter("senha1");
	   		String senha2 = request.getParameter("senha2");

	   		if( ( senha1 != null && !senha1.isEmpty() ) && ( senha2 != null && !senha2.isEmpty() ) ) {

				if( senha1.equals(senha2) ) {
					
					String novasenha = Md5Criptografia.convertPasswordToMD5( senha1 );
				    HttpServletRequest req = (HttpServletRequest) request;
					HttpSession session    = req.getSession();
				    String usuarioLogado   = (String) session.getAttribute("usuario");
				    
				    daoLoginRepository.atualizaSenhaPrimeiroAcesso(novasenha, usuarioLogado);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("principal/PagPrincipal.jsp");
					requestDispatcher.forward(request, response);
				    
//						    Gson gson = new Gson();
//						    String lista = gson.toJson("Senha Atualizada com sucesso");
//						    response.getWriter().write(lista);	
				    
				}else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("principal/trocaSenhaPrimeiroAcesso.jsp");
					request.setAttribute("msg", "As sehas não são iguais, favor ajustar!");
					requestDispatcher.forward(request, response);
				}
	   		}
				
			}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				requestDispatcher.forward(request, response);			
			}
	}

}
