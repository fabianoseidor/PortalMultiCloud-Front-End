package br.com.portal.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.portal.dao.DAOLoginRepository;
import br.com.portal.model.ModelLogin;
import br.com.portal.modelPerfil.ModelPerfilLogado;
import br.com.portal.modelPerfil.ModelSecao;
import br.com.portal.util.Md5Criptografia;

// import br.com.portal.util.EnviarEmail;

@WebServlet( urlPatterns =  {"/principal/ServletLogin"})/*Mapeamento de URL que vem da Tela*/
public class ServletLogin extends ServletGeniricUtil {
	private static final long serialVersionUID = 1L;
    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();  

    public ServletLogin() {
    }
    
    /**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSecaoInicial") ) {	


			
			
			
			}else {
		    	 RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				 request.setAttribute("msg", "Erro ao efetuar Login! Favor tentar novamente");
			     requestDispatcher.forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
//		String senha = null;
		
//		EnviarEmail.sendEmail( "Teste de envio de E-mail", "Voce esta recebendo um e-mail do Portal MultiCloud!", "fabiano.bolacha@gmail.com,amaralanalysissoftware@gmail.com,fabiano.amaral@seidor.com" ); 
		try {
			senha = Md5Criptografia.convertPasswordToMD5( request.getParameter("senha") );
//			System.out.println("senha: " + senha);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage() + " <br> " + e.toString() + " <br> " + e.getLocalizedMessage() + " <br> " + e.getCause() + " <br> " + e.initCause(e) );
			requestDispatcher.forward(request, response);
		}
		String url = request.getParameter("url");
		
		try {
			
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = daoLoginRepository.ValidaLogin(login, senha);
				if( modelLogin != null ) {
					
					List<ModelPerfilLogado> perfilUserLogados = daoLoginRepository.getPerfilUserLogado(modelLogin.getId_colaboradores());
					request.getSession().setAttribute( "usuario"          , modelLogin.getLogin()     );
					request.getSession().setAttribute( "nomeUsuario"      , modelLogin.getNome()      );
					request.getSession().setAttribute( "fotoUsuario"      , modelLogin.getFotouser()  );
					request.getSession().setAttribute( "useradmin"        , modelLogin.getUseradmin() );
					request.getSession().setAttribute( "perfilUserLogados", perfilUserLogados         );
					
					String nomeDatabase = daoLoginRepository.getNomeDataBase();

					String last = nomeDatabase.substring(nomeDatabase.length()-4, nomeDatabase.length()-1);
					if(last.equals("PRD")) nomeDatabase = "";
					
					request.getSession().setAttribute( "nomeDatabase", nomeDatabase);
					
					if( modelLogin.getPrimeiro_acesso() == 0 ) {
						url = "principal/trocaSenhaPrimeiroAcesso.jsp";
					}else 
					   if(url == null || url.equalsIgnoreCase("null")) {
						   url = "principal/PagPrincipal.jsp";
						   /* Alterado para o Novo Menu.
						   if( modelLogin.getUseradmin().equals("1") )
						     url = "principal/principal.jsp";
						   else 
							   url = "principal/principalNaoAdmin.jsp";
						   */	   
					   }
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
					requestDispatcher.forward(request, response);
				}else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Login ou Senha incorretamentos!");
					requestDispatcher.forward(request, response);
				}
			}else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe Login e Senha corretamente!");
				requestDispatcher.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage() + " <br> " + e.toString() + " <br> " + e.getLocalizedMessage() + " <br> " + e.getCause() + " <br> " + e.initCause(e) );
			requestDispatcher.forward(request, response);
		}
	}	
}
