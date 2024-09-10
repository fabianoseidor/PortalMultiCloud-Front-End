package br.com.portal.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.portal.model.loginunificado.Users;
import br.com.portal.dao.DAOLoginRepository;
import br.com.portal.model.ModelLogin;
import br.com.portal.modelPerfil.ModelPerfilLogado;
import br.com.portal.servise.UserService;
import br.com.portal.util.Md5Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import br.com.portal.util.EnviarEmail;

@WebServlet( urlPatterns =  {"/principal/ServletLogin"})/*Mapeamento de URL que vem da Tela*/
public class ServletLogin extends ServletGeniricUtil {
	private static final long serialVersionUID = 1L;
    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();  

    public ServletLogin() { }
    
    /**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao    = request.getParameter("acao");
	        InetAddress ia = InetAddress.getLocalHost();
	        String node    = ia.getHostName();
			
	        if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
		        
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}else 
			  if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaSecaoInicial") ) {
				String url            = request.getParameter("url"  );
				String login          = request.getParameter("login");
				String admin          = request.getParameter("admin");
				String loginUnificado = request.getParameter("loginUnificado");

				String urlAPI = "";
				String urlAPIInicioPortalLogin = "";
				String urlAPI_PortalMudanca = "";
				
		        if(node.equals("PIBASTIANDEV")) urlAPI_PortalMudanca = "http://localhost:8090/PortalMudanca/";
		        else urlAPI_PortalMudanca = "http://10.154.20.134:8090/PortalMudanca/";

				
		        if(node.equals("PIBASTIANDEV")) {
		        	urlAPI = "http://localhost:8091/loginUnificado/";
		        	urlAPIInicioPortalLogin = "http://localhost:8080/pjLoginUnificado/principal/PagPrincipal.jsp";
		        }
		        else {
		        	urlAPI = "http://10.154.20.134:8091/loginUnificado/";
		        	urlAPIInicioPortalLogin = "http://10.154.20.134:8080/loginunificado/principal/PagPrincipal.jsp";
		        }

				String urlBuscaLogin = urlAPI + "buscarByLogin/" + login;
				Users user = UserService.buscaUserLogin( urlBuscaLogin); 
				
				if( user != null ) {
					
					List<ModelPerfilLogado> perfilUserLogados = daoLoginRepository.getPerfilUserLogadoPorLogin( login );
					request.getSession().setAttribute( "usuario"                , user.getLogin()                             );
					request.getSession().setAttribute( "usuarioEmail"           , user.getPessoa().getEmail()                 );
					request.getSession().setAttribute( "nomeUsuario"            , user.getPessoa().getNome_pessoa()           );
					request.getSession().setAttribute( "fotoUsuario"            , user.getPessoa().getFotouser()              );
					request.getSession().setAttribute( "useradmin"              , admin                                       );
					request.getSession().setAttribute( "perfilUserLogados"      , perfilUserLogados                           );
					request.getSession().setAttribute( "loginUnificado"         , loginUnificado != null ? loginUnificado : 0 );
					request.getSession().setAttribute( "urlAPIInicioPortalLogin", urlAPIInicioPortalLogin                     );
					request.getSession().setAttribute( "urlAPI_PortalMudanca"   , urlAPI_PortalMudanca                     );
					
					
					String nomeDatabase = daoLoginRepository.getNomeDataBase();

					String last = nomeDatabase.substring(nomeDatabase.length()-4, nomeDatabase.length()-1);
					if(last.equals("PRD")) nomeDatabase = "";
					
					request.getSession().setAttribute( "nomeDatabase", nomeDatabase);
					

				    if(url == null || url.equalsIgnoreCase("null")) {
					   url = "principal/PagPrincipal.jsp";
				    }
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
					requestDispatcher.forward(request, response);
				}else {
					request.setAttribute("msg", "Login ou Senha incorretos!");
					String urlLonginUnificado = "";
			        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
			        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
			        
	 				HttpServletResponse resp = (HttpServletResponse) response;	
	 				resp.sendRedirect(urlLonginUnificado);
					return ;// para a execucao e redireciona para o login.
/*
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
*/					
				}
			}else {
				request.setAttribute("msg", "Login ou Senha incorretos!");
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
		        
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
/*				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe Login e Senha corretamente!");
				requestDispatcher.forward(request, response);
*/				
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
					request.setAttribute("msg", "Login ou Senha incorretos!");
					requestDispatcher.forward(request, response);
				}
			}else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe Login e Senha corretos!");
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
