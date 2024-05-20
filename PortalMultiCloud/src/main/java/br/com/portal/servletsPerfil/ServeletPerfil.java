package br.com.portal.servletsPerfil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.daoPerfil.DAOPerfil;
import br.com.portal.modelPerfil.ModalItemPerfilSecao;
import br.com.portal.modelPerfil.ModalListaPerfilItem;
import br.com.portal.modelPerfil.ModalPaginaPerfil;
import br.com.portal.modelPerfil.ModalPerfil;

public class ServeletPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOPerfil daoPerfil = new DAOPerfil();
       
     public ServeletPerfil() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ListaConteudoPerfil") ) {
				String idPerfil = request.getParameter("idPerfil");
				
				List<ModalListaPerfilItem> listItemPerfilSecao = daoPerfil.getListaPerfiItens( Long.parseLong(idPerfil) );

			    Gson gson = new Gson();
			    String lista = gson.toJson(listItemPerfilSecao);
			    response.getWriter().write(lista);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("InsertItemPerfil") ) {
					String idPagSecao    = request.getParameter( "idPagSecao" );
					String idSecao       = request.getParameter( "idSecao"    );
					String idPerfil      = request.getParameter( "idPerfil"   );
					String decSecao      = request.getParameter( "decSecao"   );
					String descPagina    = request.getParameter( "descPagina" );
					String user_cadastro = request.getParameter( "loginCadastro" );
					String obs           = request.getParameter( "obs"        );
					String compObs       = "Descrição da Secão: " + decSecao + " - Descrição da Página: " + descPagina + "\n" + obs;
							 
					
					ModalItemPerfilSecao itemPerfilSecao = new ModalItemPerfilSecao();
					if( user_cadastro == null || user_cadastro.isEmpty() ) {
					    HttpServletRequest req = (HttpServletRequest) request;
					    HttpSession session    = req.getSession();
					    user_cadastro          = (String) session.getAttribute("usuario");
					}

					itemPerfilSecao.setId_pag       ( idPagSecao    != null && !idPagSecao.isEmpty()    ? Long.parseLong( idPagSecao.trim() ) : null );
					itemPerfilSecao.setId_perfil    ( idPerfil      != null && !idPerfil.isEmpty()      ? Long.parseLong( idPerfil.trim()   ) : null );
					itemPerfilSecao.setId_secao     ( idSecao       != null && !idSecao.isEmpty()       ? Long.parseLong( idSecao.trim()    ) : null );
					itemPerfilSecao.setObs          ( compObs       != null && !compObs.isEmpty()       ? compObs.trim()                      : null );
					itemPerfilSecao.setUser_cadastro( user_cadastro != null && !user_cadastro.isEmpty() ? user_cadastro.trim()                : null );
					itemPerfilSecao.setBt_editar    ( 0 );
					itemPerfilSecao.setBt_escluir   ( 0 );
					itemPerfilSecao.setBt_novo      ( 0 );
					itemPerfilSecao.setBt_pesquisar ( 0 );

					itemPerfilSecao = daoPerfil.gravaItemPerfilSecao( itemPerfilSecao );

					Gson gson = new Gson();
				    String lista = gson.toJson(itemPerfilSecao);
				    response.getWriter().write(lista);
					
			     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ValidaNomePerfilBase") ) {
					String nomePerfil    = request.getParameter("nomePerfil");
					String retornoResult = "";
					Boolean result = daoPerfil.isExistPerfil( nomePerfil );
					if(result) retornoResult="EXISTE";
					else retornoResult="NAOEXISTE";
							
				    Gson gson = new Gson();
				    String lista = gson.toJson(retornoResult);
				    response.getWriter().write(lista);
					
			     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTabelaPaginaSecaoModal") ) {
						String idSecao  = request.getParameter("idSecao");
						String idPerfil = request.getParameter("idPerfilMd");
						
						List<ModalPaginaPerfil> listaPaginaPerfis = daoPerfil.getListaPaginaSecao( Long.parseLong(idSecao), Long.parseLong(idPerfil) );
								
					    Gson gson = new Gson();
					    String lista = gson.toJson(listaPaginaPerfis);
					    response.getWriter().write(lista);
				
		           }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("populaCamposPerfil") ) {
						String idPerfil = request.getParameter("idPerfil");
						
						ModalPerfil modalPerfil = daoPerfil.getPerfilID( Long.parseLong(idPerfil) );
								
					    Gson gson = new Gson();
					    String lista = gson.toJson(modalPerfil);
					    response.getWriter().write(lista);
					
			       }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletaraItemPerfiljax") ) {
						 String idSecao  = request.getParameter("idSecao");
						 String idPerfil = request.getParameter("idPerfil");
						 String idPag    = request.getParameter("idPag");

						if( (idSecao != null && !idSecao.isEmpty()) && (idPerfil != null && !idPerfil.isEmpty()) && (idPag != null && !idPag.isEmpty()) ) {
							daoPerfil.deletarItemPerfil( Long.parseLong(idSecao), Long.parseLong(idPerfil), Long.parseLong(idPag) );
							response.getWriter().write("Registro excluido com sucesso!");
						}

						// request.getRequestDispatcher("perfil/configPerfil.jsp").forward(request, response);
					}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("DeletePerfilAjax") ) {
						
						 String id = request.getParameter("idPerfil");
							
						if(id != null && !id.isEmpty()) 
							daoPerfil.deletarPerfil(id);

						request.getRequestDispatcher("perfil/configPerfil.jsp").forward(request, response);
					}else{
					request.getRequestDispatcher("perfil/configPerfil.jsp").forward(request, response);
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
			ModalPerfil modalPerfil = new ModalPerfil();
			
			String id_perfil     = request.getParameter( "id_perfil"     );
			String nome_perfil   = request.getParameter( "nome_perfil"   );
			String dt_criacao    = request.getParameter( "dt_criacao"    );
			String user_cadastro = request.getParameter( "user_cadastro" );
			String obs           = request.getParameter( "observacao"    );
			
			if( user_cadastro == null || user_cadastro.isEmpty() ) {
			    HttpServletRequest req = (HttpServletRequest) request;
			    HttpSession session    = req.getSession();
			    user_cadastro          = (String) session.getAttribute("usuario");
			}
			          
			modalPerfil.setId_perfil    ( id_perfil     != null && !id_perfil.isEmpty()     ? Long.parseLong( id_perfil ) : null );
			modalPerfil.setNome_perfil  ( nome_perfil   != null && !nome_perfil.isEmpty()   ? nome_perfil                 : null );
			modalPerfil.setDt_criacao   ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                  : null );
			modalPerfil.setUser_cadastro( user_cadastro != null && !user_cadastro.isEmpty() ? user_cadastro               : null );
			modalPerfil.setObs          ( obs           != null && !obs.isEmpty()           ? obs                         : null );
			   
			if(modalPerfil.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";

			modalPerfil = daoPerfil.gravacao(modalPerfil);

		    request.setAttribute( "msg"        , msg         );
			request.setAttribute( "modalPerfil", modalPerfil );
//			request.setAttribute( "listPerfil" , listPerfil  );
			request.getRequestDispatcher("perfil/configPerfil.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}

}
