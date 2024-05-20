package br.com.portal.servlets;

import java.io.Serializable;
import java.sql.SQLException;

import br.com.portal.dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;

public class ServletGeniricUtil extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;

	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository(); 
	
	public Long getUserLogado(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");		
		return daoUsuarioRepository.consultaUsuario(usuarioLogado).getId_cargo();
	}

}
