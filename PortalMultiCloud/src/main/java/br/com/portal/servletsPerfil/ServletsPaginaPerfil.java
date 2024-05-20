package br.com.portal.servletsPerfil;

import java.io.IOException;
import java.util.List;

import br.com.portal.daoPerfil.DAOPaginaSecao;
import br.com.portal.modelPerfil.ModalPaginaPerfil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ServletsPaginaPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOPaginaSecao daoPaginaSecao = new DAOPaginaSecao();
       
     public ServletsPaginaPerfil() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaPaginaSecaoInicial") ) {	
		    	List<ModalPaginaPerfil> listPaginaPerfis = daoPaginaSecao.getListaPagSecao();
				request.setAttribute( "listPaginaPerfis", listPaginaPerfis );
				request.getRequestDispatcher("perfil/configPaginaSecao.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String idPagSecao = request.getParameter("idPagSecao");
					request.setAttribute("msg", "Ambiente em edicao!" );

					ModalPaginaPerfil modalPaginaPerfil      = daoPaginaSecao.getPagSecaoID(Long.parseLong( idPagSecao ));
					List<ModalPaginaPerfil> listPaginaPerfis = daoPaginaSecao.getListaPagSecao();

			    	request.setAttribute( "modalPaginaPerfil", modalPaginaPerfil );
			    	request.setAttribute( "listPaginaPerfis" , listPaginaPerfis  );
					request.getRequestDispatcher("perfil/configPaginaSecao.jsp").forward(request, response);
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax") ) {
					String id = request.getParameter("idPagSecao");
					
					if(id != null && !id.isEmpty()) 
						daoPaginaSecao.deletarPagSecao(id);

					List<ModalPaginaPerfil> listPaginaPerfis = daoPaginaSecao.getListaPagSecao();
					request.setAttribute( "listPaginaPerfis", listPaginaPerfis );
					request.getRequestDispatcher("perfil/configPaginaSecao.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("perfil/configPaginaSecao.jsp").forward(request, response);
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

			ModalPaginaPerfil modalPaginaPerfil = new ModalPaginaPerfil();
			
			String id_pag_secao      = request.getParameter( "id_pag_secao"      );
			String dt_criacao        = request.getParameter( "dt_criacao"        );
			String user_cadastro     = request.getParameter( "user_cadastro"     );
			String id_secao          = request.getParameter( "id_secao"          );
			String desc_pagina       = request.getParameter( "desc_pagina"       );
			String nome_pag_extensao = request.getParameter( "nome_pag_extensao" );
			String extensao          = request.getParameter( "extensao"          );
			String obs               = request.getParameter( "observacao"        );
			
			if( user_cadastro == null || user_cadastro.isEmpty() ) {
			    HttpServletRequest req = (HttpServletRequest) request;
			    HttpSession session    = req.getSession();
			    user_cadastro          = (String) session.getAttribute("usuario");
			}

			modalPaginaPerfil.setId_pag_secao     ( id_pag_secao      != null && !id_pag_secao.isEmpty()      ? Long.parseLong( id_pag_secao ) : null );
			modalPaginaPerfil.setId_secao         ( id_secao          != null && !id_secao.isEmpty()          ? Long.parseLong( id_secao )     : null );
			modalPaginaPerfil.setDt_criacao       ( dt_criacao        != null && !dt_criacao.isEmpty()        ? dt_criacao                     : null );
			modalPaginaPerfil.setUser_cadastro    ( user_cadastro     != null && !user_cadastro.isEmpty()     ? user_cadastro                  : null );
			modalPaginaPerfil.setDesc_pagina      ( desc_pagina       != null && !desc_pagina.isEmpty()       ? desc_pagina                    : null );
			modalPaginaPerfil.setNome_pag_extensao( nome_pag_extensao != null && !nome_pag_extensao.isEmpty() ? nome_pag_extensao              : null );
			modalPaginaPerfil.setExtensao         ( extensao          != null && !extensao.isEmpty()          ? extensao                       : null );
			modalPaginaPerfil.setObs              ( obs               != null && !obs.isEmpty()               ? obs                            : null );
			
			if(modalPaginaPerfil.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";

			modalPaginaPerfil = daoPaginaSecao.gravacao(modalPaginaPerfil);
	    	List<ModalPaginaPerfil> listPaginaPerfis = daoPaginaSecao.getListaPagSecao();

		    request.setAttribute( "msg"              , msg               );
	    	request.setAttribute( "modalPaginaPerfil", modalPaginaPerfil );
			request.setAttribute( "listPaginaPerfis" , listPaginaPerfis  );
			
			request.getRequestDispatcher("perfil/configPaginaSecao.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	
	}

}
