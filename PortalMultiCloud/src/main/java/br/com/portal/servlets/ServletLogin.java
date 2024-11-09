package br.com.portal.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import br.com.portal.model.loginunificado.Users;
import br.com.portal.dao.DAOLoginRepository;
import br.com.portal.modelPerfil.ModelPerfilLogado;
import br.com.portal.servise.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// import br.com.portal.util.EnviarEmail;

@WebServlet( urlPatterns =  {"/principal/ServletLogin"})/*Mapeamento de URL que vem da Tela*/
public class ServletLogin extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();  
    public ServletLogin() { }
    
    /**/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao          = request.getParameter("acao");
	        InetAddress ia       = InetAddress.getLocalHost();
	        String node          = ia.getHostName();
			String login         = request.getParameter("login");
	        HttpSession session  = request.getSession();
	        String usuarioLogado = (String) session.getAttribute("usuario");
	        String statusSessao  = (String) session.getAttribute("StatusSessao");
	        
	        if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("manuLoginUnificado")) {
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/principal/PagPrincipal.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/principal/PagPrincipal.jsp";
		        request.getSession().invalidate();
		        HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
		        request.getSession().invalidate();
		        HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}else if( ( usuarioLogado != null && !usuarioLogado.isEmpty() && statusSessao != null && !statusSessao.isEmpty() ) && (statusSessao.equals("PrimeiroAcesso")) ) {
				request.getSession().setAttribute( "StatusSessao", "PrimeiroAcessoOK" );
                String urlPortalMultiCloud = null; 
		        if(node.equals("PIBASTIANDEV")) urlPortalMultiCloud = "http://localhost:8083/PortalMultiCloud/principal/PagPrincipal.jsp";
		        else urlPortalMultiCloud = "http://10.154.20.134:8080/PortalMultiCloud/principal/PagPrincipal.jsp";
		  		HttpServletResponse resp = (HttpServletResponse) response;	
				resp.sendRedirect(urlPortalMultiCloud);
/*
		  		HttpServletResponse resp1 = (HttpServletResponse) response;	
				resp1.sendRedirect("http://localhost:8083/PortalMultiCloud/principal/PagPrincipal.jsp");
*/
			}else  if( (usuarioLogado != null && !usuarioLogado.isEmpty()) && (!usuarioLogado.equals(login)) ) {
				request.setAttribute("msg", "Favor realizar Login com seu usuário!");
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
		        request.getSession().invalidate();
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}else {
				request.setAttribute("msg", "Favor efeturar Login!");
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
		        request.getSession().invalidate();
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}
				
		}catch(Exception e){
			e.printStackTrace();
			request.getSession().invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		        
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String queryString =request.getQueryString();
        String requestURL = request.getRequestURL().toString();
*/

		
		try {
	        InetAddress ia         = InetAddress.getLocalHost();
	        String node            = ia.getHostName();

			String StatusSessao = request.getParameter("StatusSessao");
			
			if( StatusSessao != null && !StatusSessao.isEmpty() && StatusSessao.equalsIgnoreCase("PrimeiroAcesso") ) {

				String login          = request.getParameter( "login"          );
				String admin          = request.getParameter( "admin"          );
				String loginUnificado = request.getParameter( "loginUnificado" );
				String urlDoGet       = request.getParameter( "urlDoGet"       );
				String urlLogin       = request.getParameter( "urlLogin"       );

				if( login != null && !login.isEmpty() ) {
					String urlAPIInicioPortalLogin = "";
					String urlAPI_PortalMudanca = "";
					
			        if(node.equals("PIBASTIANDEV")) {
			        	urlAPIInicioPortalLogin = "http://localhost:8080/pjLoginUnificado/principal/PagPrincipal.jsp";
			        	urlAPI_PortalMudanca = "http://localhost:8090/PortalMudanca/";
			        }
			        else {
			        	urlAPIInicioPortalLogin = "http://10.154.20.134:8080/loginunificado/principal/PagPrincipal.jsp";
			        	urlAPI_PortalMudanca = "http://10.154.20.134:8090/PortalMudanca/";
			        }

					String urlBuscaLogin = urlLogin + "buscarByLogin/" + login;
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
						request.getSession().setAttribute( "urlAPI_PortalMudanca"   , urlAPI_PortalMudanca                        );
						request.getSession().setAttribute( "StatusSessao"           , "PrimeiroAcesso"                            );

						String nomeDatabase = daoLoginRepository.getNomeDataBase();

						String last = nomeDatabase.substring(nomeDatabase.length()-4, nomeDatabase.length()-1);
						if(last.equals("PRD")) nomeDatabase = "";
						
						request.getSession().setAttribute( "nomeDatabase", nomeDatabase);
						HttpServletResponse resp = (HttpServletResponse) response;	
						resp.sendRedirect(urlDoGet);
					}else {
						request.setAttribute("msg", "Favor efetura Login!");
						request.getSession().invalidate();
		 				HttpServletResponse resp = (HttpServletResponse) response;	
		 				resp.sendRedirect(urlLogin + "index.jsp");
						return ;// para a execucao e redireciona para o login.
					}
				}else {
					request.setAttribute("msg", "Favor efetura Login!");
					String urlLonginUnificado = "";
			        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
			        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp";
			        request.getSession().invalidate();
	 				HttpServletResponse resp = (HttpServletResponse) response;	
	 				resp.sendRedirect(urlLonginUnificado);
					return ;// para a execucao e redireciona para o login.
				}
				
			}else {
				request.setAttribute("msg", "Favor efetura Login!");
				String urlLonginUnificado = "";
		        if(node.equals("PIBASTIANDEV")) urlLonginUnificado = "http://localhost:8080/pjLoginUnificado/index.jsp";
		        else urlLonginUnificado       = "http://10.154.20.134:8080/loginunificado/index.jsp?";
		        request.getSession().invalidate();
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlLonginUnificado);
				return ;// para a execucao e redireciona para o login.
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			request.getSession().invalidate();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
		
		
	}	
}
