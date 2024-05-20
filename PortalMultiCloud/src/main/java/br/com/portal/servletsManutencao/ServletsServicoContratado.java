package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOServicoContratado;
import br.com.portal.model.ModelServicoContratado;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsServicoContratado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOServicoContratado daoServicoContratado = new DAOServicoContratado();
       

    public ServletsServicoContratado() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelServicoContratado> listServicoContratados = daoServicoContratado.listaServicoContratadoSelect();
				request.setAttribute("listServicoContratados", listServicoContratados);
				request.getRequestDispatcher("manutencao/manutencaoServicoContratado.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelServicoContratado listServicoContratado = new ModelServicoContratado();
					    
					listServicoContratado = daoServicoContratado.getServicoContratadoID( Long.parseLong( id ) );
					List<ModelServicoContratado> listServicoContratados = daoServicoContratado.listaServicoContratadoSelect();

					request.setAttribute("listServicoContratado" , listServicoContratado );
					request.setAttribute("listServicoContratados", listServicoContratados);
					request.getRequestDispatcher("manutencao/manutencaoServicoContratado.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoServicoContratado.deletarRegistro(id);
					
					List<ModelServicoContratado> listServicoContratados = daoServicoContratado.listaServicoContratadoSelect();
					request.setAttribute("listServicoContratados", listServicoContratados);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoServicoContratado.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoServicoContratado.jsp").forward(request, response);
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
			String id_servico_contratado = request.getParameter( "id_servico_contratado" );
			String dt_criacao            = request.getParameter( "dt_criacao"  );
			String desc_servico          = request.getParameter( "desc_servico"  );
			String observacao            = request.getParameter( "observacao"  );
						
			ModelServicoContratado listServicoContratado = new ModelServicoContratado();

			listServicoContratado.setId_servico_contratado( id_servico_contratado != null && !id_servico_contratado.isEmpty() ? Long.parseLong( id_servico_contratado ) : null );
			listServicoContratado.setDt_criacao           ( dt_criacao            != null && !dt_criacao.isEmpty()            ? dt_criacao                              : null );
			listServicoContratado.setDesc_servico         ( desc_servico          != null && !desc_servico.isEmpty()          ? desc_servico                            : null );
			listServicoContratado.setObs                  ( observacao            != null && !observacao.isEmpty()            ? observacao                              : null );

	    	if(listServicoContratado.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listServicoContratado = daoServicoContratado.gravarAtualizaRegistros( listServicoContratado );

	    	List<ModelServicoContratado> listServicoContratados = daoServicoContratado.listaServicoContratadoSelect();
		    request.setAttribute("msg"           , msg           );
			request.setAttribute("listServicoContratado"  , listServicoContratado );
			request.setAttribute("listServicoContratados", listServicoContratados);
			request.getRequestDispatcher("manutencao/manutencaoServicoContratado.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
