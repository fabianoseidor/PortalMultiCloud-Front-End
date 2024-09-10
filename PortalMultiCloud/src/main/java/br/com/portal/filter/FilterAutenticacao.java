package br.com.portal.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.portal.connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*","/perfil/*","/manutencao/*","/cadastro/*", "/consolidacao/*", "/relatorios/*", "/dashboard/*"})
public class FilterAutenticacao extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
    private static Connection connection;
 
	public FilterAutenticacao() { }

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String urlPortalLogin = "";
	        InetAddress ia = InetAddress.getLocalHost();
	        String node = ia.getHostName();
	        if(node.equals("PIBASTIANDEV"))urlPortalLogin = "http://localhost:8080/pjLoginUnificado/ServletLogin";
	        else urlPortalLogin = "http://10.154.20.134:8080/loginunificado/ServletLogin";

			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutent = req.getServletPath(); // URL que esta sendo acessada.
			urlPortalLogin = urlPortalLogin + "?urlAplic=" + urlParaAutent + "&aplic=1"; // 1 ==> Defini a Aplicacao como Portal de Contrato
			// Validar se esta logado, se nao redireciona para a tela de login
			if( usuarioLogado == null && !urlParaAutent.equalsIgnoreCase("/principal/ServletLogin") ) {
//				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutent);
//				redireciona.forward(request, response);
				request.setAttribute("msg", "Por favor, realizar o login!");
				
 				HttpServletResponse resp = (HttpServletResponse) response;	
 				resp.sendRedirect(urlPortalLogin);
				return ;// para a execucao e redireciona para o login.
			}else {
	    		chain.doFilter(request, response);
			}
			
			connection.commit(); // commit no BD.
			
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
