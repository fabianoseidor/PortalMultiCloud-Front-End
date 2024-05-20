package br.com.portal.servletsPerfil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.daoPerfil.DAOPerfil;
import br.com.portal.daoPerfil.DAOPerfilUser;
import br.com.portal.modelPerfil.ModalListaPerfilItem;
import br.com.portal.modelPerfil.ModelPerfilUser;

public class ServletsPerfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOPerfilUser daoPerfilUser = new DAOPerfilUser();
	DAOPerfil daoPerfil = new DAOPerfil();
       
    public ServletsPerfilUser() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaUser") ) {
				String nome = request.getParameter("nome");
				
				List<ModelPerfilUser> listUsuarios = daoPerfilUser.getListaUsuario(nome);
				Gson gson = new Gson();
				String lista = "";
				if( listUsuarios.size() > 0 ) {
  			        lista = gson.toJson(listUsuarios);
				}else lista = "VAZIO";
			    response.getWriter().write(lista);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaConteudoPerfil") ) {
					String idPerfil = request.getParameter("idPerfil");
					
					List<ModalListaPerfilItem> listItemPerfilSecao = daoPerfil.getListaPerfiItens( Long.parseLong(idPerfil) );

				    Gson gson = new Gson();
				    String lista = gson.toJson(listItemPerfilSecao);
				    response.getWriter().write(lista);
					
			 }else{
					request.getRequestDispatcher("perfil/configPerfilUser.jsp").forward(request, response);
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
			
			ModelPerfilUser modelPerfilUser = new ModelPerfilUser();
			String msg = "Operacao realizada com sucesso!";
			
			String idUsuario     = request.getParameter( "id_usuario"    );
			String dt_criacao    = request.getParameter( "dt_criacao"    );
			String user_cadastro = request.getParameter( "user_cadastro" );
			String login         = request.getParameter( "login"         );
			String idPerfil      = request.getParameter( "Selectperfil"  );
			
			modelPerfilUser.setId_colaboradores ( idUsuario     != null && !idUsuario.isEmpty()     ? Long.parseLong( idUsuario.trim() ) : null );
			modelPerfilUser.setId_perfil        ( idPerfil      != null && !idPerfil.isEmpty()      ? Long.parseLong( idPerfil.trim() )  : null );
			modelPerfilUser.setLogin            ( login         != null && !login.isEmpty()         ? login.trim()                       : null );
			modelPerfilUser.setDt_criacao       ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao.trim()                  : null );
//			modelPerfilUser.setUseradmin        ( user_cadastro != null && !user_cadastro.isEmpty() ? user_cadastro.trim()               : null );
			modelPerfilUser.setLogin_cadastro   ( user_cadastro != null && !user_cadastro.isEmpty() ? user_cadastro.trim()               : null );
			
			modelPerfilUser = daoPerfilUser.gravaAtualizaPerfil( modelPerfilUser );

		    request.setAttribute( "msg"            , msg             );
			request.setAttribute( "modelPerfilUser", modelPerfilUser );
			request.getRequestDispatcher("perfil/configPerfilUser.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}

}
