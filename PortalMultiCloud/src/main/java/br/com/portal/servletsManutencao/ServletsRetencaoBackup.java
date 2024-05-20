package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAORetencaoBackup;
import br.com.portal.model.ModelRetencaoBackup;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsRetencaoBackup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAORetencaoBackup daoRetencaoBackup = new DAORetencaoBackup();
       

    public ServletsRetencaoBackup() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelRetencaoBackup> listRetencaoBackups = daoRetencaoBackup.getListaRetencaoBackup();
				request.setAttribute("listRetencaoBackups", listRetencaoBackups);
				request.getRequestDispatcher("manutencao/manutencaoRetencaoBackup.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelRetencaoBackup listRetencaoBackup = new ModelRetencaoBackup();
					    
					listRetencaoBackup = daoRetencaoBackup.getRetencaoBackupID( Long.parseLong( id ) );
					List<ModelRetencaoBackup> listRetencaoBackups = daoRetencaoBackup.getListaRetencaoBackup();

					request.setAttribute("listRetencaoBackup" , listRetencaoBackup );
					request.setAttribute("listRetencaoBackups", listRetencaoBackups);
					request.getRequestDispatcher("manutencao/manutencaoRetencaoBackup.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoRetencaoBackup.deletarRegistro(id);
					
					List<ModelRetencaoBackup> listRetencaoBackups = daoRetencaoBackup.getListaRetencaoBackup();
					request.setAttribute("listRetencaoBackups", listRetencaoBackups);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoRetencaoBackup.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoRetencaoBackup.jsp").forward(request, response);
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
			// id_ambiente
			String id_retencao_backup = request.getParameter( "id_retencao_backup" );
			String dt_criacao         = request.getParameter( "dt_criacao"         );
			String retencao_backup    = request.getParameter( "retencao_backup"    );
			String observacao         = request.getParameter( "observacao"         );
						
			ModelRetencaoBackup listRetencaoBackup = new ModelRetencaoBackup();

			listRetencaoBackup.setId_retencao_backup( id_retencao_backup != null && !id_retencao_backup.isEmpty() ? Long.parseLong( id_retencao_backup ) : null );
			listRetencaoBackup.setDt_criacao        ( dt_criacao         != null && !dt_criacao.isEmpty()         ? dt_criacao                           : null );
			listRetencaoBackup.setRetencao_backup   ( retencao_backup    != null && !retencao_backup.isEmpty()    ? retencao_backup                      : null );
			listRetencaoBackup.setObservacao        ( observacao         != null && !observacao.isEmpty()         ? observacao                           : null );

	    	if(listRetencaoBackup.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listRetencaoBackup = daoRetencaoBackup.gravarAtualizaRegistros( listRetencaoBackup );

	    	List<ModelRetencaoBackup> listRetencaoBackups = daoRetencaoBackup.getListaRetencaoBackup();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listRetencaoBackup" , listRetencaoBackup );
			request.setAttribute("listRetencaoBackups", listRetencaoBackups);
			request.getRequestDispatcher("manutencao/manutencaoRetencaoBackup.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
