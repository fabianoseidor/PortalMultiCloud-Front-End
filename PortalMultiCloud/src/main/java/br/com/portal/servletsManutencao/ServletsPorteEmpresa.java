package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOPorteCliente;
import br.com.portal.model.ModelPorteCliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsPorteEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOPorteCliente daoPorteCliente = new DAOPorteCliente();
       

    public ServletsPorteEmpresa() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelPorteCliente> listPorteEmpresas = daoPorteCliente.getListaPorteEmpresaCliente();
				request.setAttribute("listPorteEmpresas", listPorteEmpresas);
				request.getRequestDispatcher("manutencao/manutencaoPorteEmpresa.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelPorteCliente listPorteEmpresa = new ModelPorteCliente();
					    
					listPorteEmpresa = daoPorteCliente.getPorteEmpresaID( Long.parseLong( id ) );
					List<ModelPorteCliente> listPorteEmpresas = daoPorteCliente.getListaPorteEmpresaCliente();

					request.setAttribute("listPorteEmpresa" , listPorteEmpresa );
					request.setAttribute("listPorteEmpresas", listPorteEmpresas);
					request.getRequestDispatcher("manutencao/manutencaoPorteEmpresa.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoPorteCliente.deletarRegistro(id);
					
					List<ModelPorteCliente> listPorteEmpresas = daoPorteCliente.getListaPorteEmpresaCliente();
					request.setAttribute("listPorteEmpresas", listPorteEmpresas);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoPorteEmpresa.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoPorteEmpresa.jsp").forward(request, response);
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
			String id_porte      = request.getParameter( "id_porte"      );
			String dt_criacao    = request.getParameter( "dt_criacao"    );
			String porte_empresa = request.getParameter( "porte_empresa" );
			String observacao    = request.getParameter( "observacao"    );
						
			ModelPorteCliente listPorteEmpresa = new ModelPorteCliente();

			listPorteEmpresa.setId_porte     ( id_porte      != null && !id_porte.isEmpty()      ? Long.parseLong( id_porte ) : null );
			listPorteEmpresa.setDt_criacao   ( dt_criacao    != null && !dt_criacao.isEmpty()    ? dt_criacao                 : null );
			listPorteEmpresa.setPorte_empresa( porte_empresa != null && !porte_empresa.isEmpty() ? porte_empresa              : null );
			listPorteEmpresa.setObservacao   ( observacao    != null && !observacao.isEmpty()    ? observacao                 : null );

	    	if(listPorteEmpresa.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listPorteEmpresa = daoPorteCliente.gravarAtualizaRegistros( listPorteEmpresa );

	    	List<ModelPorteCliente> listPorteEmpresas = daoPorteCliente.getListaPorteEmpresaCliente();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listPorteEmpresa" , listPorteEmpresa );
			request.setAttribute("listPorteEmpresas", listPorteEmpresas);
			request.getRequestDispatcher("manutencao/manutencaoPorteEmpresa.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
