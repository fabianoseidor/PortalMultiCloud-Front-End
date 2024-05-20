package br.com.portal.servlets;

import java.io.IOException;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import br.com.portal.dao.DAOUsuarioRepository;
import br.com.portal.model.ModelLogin;
import br.com.portal.util.Md5Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@MultipartConfig
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();  

    public ServletUsuarioController() {
     }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
		    if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId_colaboradores() == null) {
		    	msg = "Ja existe usuário com o este Login, informe outro Login!";
			    request.setAttribute("modelLogin" , modelLogin);
			    request.setAttribute("msg", msg );
			    request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		    }else {
		    	if(modelLogin.isNovo()) {
		    		msg = "Registro gravado com sucesso!";
		    		modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
				    request.setAttribute("modelLogin" , modelLogin);
				    request.setAttribute("msg", msg );
				//    request.getRequestDispatcher("principal/manutencaoUsuario.jsp?acao=buscarEditar&id_colaboradores=" + modelLogin.getId_colaboradores() ).forward(request, response);
				    String url= request.getContextPath() + "/ServletManutencaoUsuarioController?acao=buscarEditar&id_colaboradores="+ modelLogin.getId_colaboradores();
				//	System.out.println(url);
					response.sendRedirect( url ); 
		    	}
		    }

		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}

}
