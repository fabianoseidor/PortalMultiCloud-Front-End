package br.com.portal.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.portal.dao.DAOManutencaoUsuarioRepository;
import br.com.portal.model.ModelLogin;
import br.com.portal.util.Md5Criptografia;

@MultipartConfig
public class ServletManutencaoUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAOManutencaoUsuarioRepository daoManutencaoUsuarioRepository = new DAOManutencaoUsuarioRepository();  
	private Integer offsetBegin             = 0;
	private static final Integer OFFSET_END = 10;

   
    public ServletManutencaoUsuarioController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		String acao = request.getParameter("acao");
		
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
			String id = request.getParameter("id_colaboradores");
			daoManutencaoUsuarioRepository.deletarUser(id);
			request.setAttribute("msg", "Registro excluido com sucesso!");

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			String userLogado =  (String) session.getAttribute("usuario");
			String userAdmin =  (String) session.getAttribute("useradmin");
			int userAdminLogado = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;
			//Monta lista de usuario para mostrar no rodape
			List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);
			request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(  OFFSET_END ));
		    request.setAttribute("modelLogins", modelLogins);
			
			request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			 
			 String idUser = request.getParameter("id");
			 
			 daoManutencaoUsuarioRepository.deletarUser(idUser);
			 
			 request.setAttribute("msg", "Excluido com sucesso!");
			 request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
		 }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
			String id = request.getParameter("id_colaboradores");
			daoManutencaoUsuarioRepository.deletarUser(id);
			
			response.getWriter().write("Registro excluido com sucesso!");
			
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax") ) {
			String nomeBusca = request.getParameter("nomeBusca");
			List<ModelLogin> dadosJsonUser = daoManutencaoUsuarioRepository.buscarUsuario(nomeBusca);
			ObjectMapper objectMapper = new ObjectMapper();
		    try {
		         String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dadosJsonUser);
		         //System.out.println(json);
		         response.getWriter().write(json);
		    } catch(Exception e) {
		          e.printStackTrace();
		    }
		} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String id_colaboradores = request.getParameter("id_colaboradores");
				if(id_colaboradores != null && !id_colaboradores.isEmpty()) {
				   // carrega o usuario selecionado nos inputs
				   ModelLogin modelLogin = daoManutencaoUsuarioRepository.consultaUsuarioId( id_colaboradores );
			       request.setAttribute("modelLogin", modelLogin);
				}
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session    = req.getSession();
				String userLogado      = (String) session.getAttribute("usuario");
				String userAdmin       = (String) session.getAttribute("useradmin");
				int userAdminLogado    = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;
				//Monta lista de usuario para mostrar no rodape
				List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);
				request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(  OFFSET_END ));
				request.setAttribute("modelLogins", modelLogins);
			    request.setAttribute("msg", "Usuario em edicao!" );
			    request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
		    
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser") ) {

				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session    = req.getSession();
				String userLogado      =  (String) session.getAttribute("usuario");
				String userAdmin       =  (String) session.getAttribute("useradmin");
				int userAdminLogado    = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;
				
				ModelLogin modelLogin = daoManutencaoUsuarioRepository.consultaUsuario(userLogado);
				
				List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);
				request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(  OFFSET_END ));
			    request.setAttribute("msg", "Usuario Carregados!" );
			    request.setAttribute("modelLogins", modelLogins);
			    request.setAttribute("modelLogin" , modelLogin);
			    request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
			    
		} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar") ) {
			
				offsetBegin = Integer.parseInt( request.getParameter("pag") ) * OFFSET_END;	
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession();
				String userLogado =  (String) session.getAttribute("usuario");
				String userAdmin =  (String) session.getAttribute("useradmin");
				int userAdminLogado = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;
				List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(OFFSET_END));
				request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
		 }else {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			String userLogado =  (String) session.getAttribute("usuario");
			String userAdmin =  (String) session.getAttribute("useradmin");
			
			int userAdminLogado = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;

			List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);

			request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(  OFFSET_END ));
		    request.setAttribute("modelLogins", modelLogins);
			request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
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
			
			String id_colaboradores = request.getParameter("id_colaboradores");
		    String nome             = request.getParameter("nome");
		    String id_cargo         = request.getParameter("id_cargo");
		    String cep              = request.getParameter("cep"); 
		    String endereco         = request.getParameter("endereco");
		    String bairro           = request.getParameter("bairro");
		    String numero           = request.getParameter("numero");
		    String complemento      = request.getParameter("complemento");
		    String cidade           = request.getParameter("cidade");
		    String estado           = request.getParameter("estado");
		    String pais             = request.getParameter("pais");
		    String cpf              = request.getParameter("cpf");
		    String telefone         = request.getParameter("telefone");
		    String celular          = request.getParameter("celular");
		    String email            = request.getParameter("email");
		    String login            = request.getParameter("login");
//		    String senha            = request.getParameter("senha");
		    String senha            = Md5Criptografia.convertPasswordToMD5( request.getParameter("senha") );
		    String admin            = request.getParameter("admin");
		    
		    ModelLogin modelLogin = new ModelLogin();
		    modelLogin.setId_colaboradores( id_colaboradores != null && !id_colaboradores.isEmpty() ? Long.parseLong(id_colaboradores): null );
		    modelLogin.setNome            ( nome             != null && !nome.isEmpty()             ? nome                            : null );
		    modelLogin.setId_cargo        ( id_cargo         != null && !id_cargo.isEmpty()         ? Long.parseLong(id_cargo)        : 1    );
		    modelLogin.setCep             ( cep              != null && !cep.isEmpty()              ? cep                             : null );
		    modelLogin.setEndereco        ( endereco         != null && !endereco.isEmpty()         ? endereco                        : null );
		    modelLogin.setBairro          ( bairro           != null && !bairro.isEmpty()           ? bairro                          : null );
		    modelLogin.setNumero          ( numero           != null && !numero.isEmpty()           ? numero                          : null );
		    modelLogin.setComplemento     ( complemento      != null && !complemento.isEmpty()      ? complemento                     : null );
		    modelLogin.setCidade          ( cidade           != null && !cidade.isEmpty()           ? cidade                          : null );
		    modelLogin.setEstado          ( estado           != null && !estado.isEmpty()           ? estado                          : null );
		    modelLogin.setPais            ( pais             != null && !pais.isEmpty()             ? pais                            : null );
		    modelLogin.setCpf             ( cpf              != null && !cpf.isEmpty()              ? cpf                             : null );
		    modelLogin.setTelefone        ( telefone         != null && !telefone.isEmpty()         ? telefone                        : null );
		    modelLogin.setCelular         ( celular          != null && !celular.isEmpty()          ? celular                         : null );
		    modelLogin.setEmail           ( email            != null && !email.isEmpty()            ? email                           : null );
		    modelLogin.setLogin           ( login            != null && !login.isEmpty()            ? login                           : null );
		    modelLogin.setSenha           ( senha            != null && !senha.isEmpty()            ? senha                           : null );
		    modelLogin.setUseradmin       ( admin            != null && !admin.isEmpty()            ? admin                           : "0"  );
		    
			if (request.getPart("fileFoto") != null) {
				Part part = request.getPart("fileFoto"); 
				if (part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); 
//					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new org.apache.tomcat.util.codec.binary.Base64().encodeBase64String(foto);
//					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + Base64.encodeBase64String(foto);
					String imagemBase64 = "data:" + part.getContentType() + ";base64," + Base64.encodeBase64String(foto);
					modelLogin.setFotouser(imagemBase64);                                                   
					modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
				}
			}		    

		    msg = "Registro atualizado com sucesso!";
		    modelLogin = daoManutencaoUsuarioRepository.gravarUsuario(modelLogin);

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session    = req.getSession();
			String userLogado      = (String) session.getAttribute("usuario");
			String userAdmin       = (String) session.getAttribute("useradmin");
			
			int userAdminLogado = userAdmin != null && !userAdmin.isEmpty() ? Integer.valueOf(userAdmin) : -1 ;

			List<ModelLogin> modelLogins = daoManutencaoUsuarioRepository.consultaUsuarioList(offsetBegin, OFFSET_END, userAdminLogado, userLogado);
			request.setAttribute("totalPagina", daoManutencaoUsuarioRepository.getTotalPag(  OFFSET_END ));
		    request.setAttribute("modelLogins", modelLogins);
		    request.setAttribute("modelLogin" , modelLogin);
		    request.setAttribute("msg", msg );
		    request.getRequestDispatcher("principal/manutencaoUsuario.jsp").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}
	

}
