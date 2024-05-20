package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.util.List;

import br.com.portal.dao.DAOTipoServico;
import br.com.portal.model.ModelTipoServico;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsTipoServico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOTipoServico daoTipoServico  = new DAOTipoServico();
       

    public ServletsTipoServico() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTipoServicoInicial") ) {

				List<ModelTipoServico> listTipoServicos = daoTipoServico.getListaTipoServicos();
				request.setAttribute("listTipoServicos", listTipoServicos);
				request.getRequestDispatcher("manutencao/manutencaoTipoServico.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelTipoServico listTipoServico = new ModelTipoServico();
					    
					listTipoServico = daoTipoServico.getTipoServicoID( Long.parseLong( id ) );
					List<ModelTipoServico> listTipoServicos = daoTipoServico.getListaTipoServicos();

					request.setAttribute("listTipoServico" , listTipoServico );
					request.setAttribute("listTipoServicos", listTipoServicos);
					request.getRequestDispatcher("manutencao/manutencaoTipoServico.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoTipoServico.deletarRegistro(id);
					
					List<ModelTipoServico> listTipoServicos = daoTipoServico.getListaTipoServicos();
					request.setAttribute("listTipoServicos", listTipoServicos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoTipoServico.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoTipoServico.jsp").forward(request, response);
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
			String id_tipo_servico = request.getParameter( "id_tipo_servico" );
			String dt_criacao      = request.getParameter( "dt_criacao"      );
			String tipo_servico    = request.getParameter( "tipo_servico"    );
			String observacao      = request.getParameter( "observacao"      );
						
			ModelTipoServico listTipoServico = new ModelTipoServico();

			listTipoServico.setId_tipo_servico( id_tipo_servico != null && !id_tipo_servico.isEmpty() ? Long.parseLong( id_tipo_servico ) : null );
			listTipoServico.setDt_criacao     ( dt_criacao      != null && !dt_criacao.isEmpty()      ? dt_criacao                        : null );
			listTipoServico.setTipo_servico   ( tipo_servico    != null && !tipo_servico.isEmpty()    ? tipo_servico                      : null );
			listTipoServico.setObs            ( observacao      != null && !observacao.isEmpty()      ? observacao                        : null );

	    	if(listTipoServico.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listTipoServico = daoTipoServico.gravarAtualizaRegistros( listTipoServico );

	    	List<ModelTipoServico> listTipoServicos = daoTipoServico.getListaTipoServicos();
		    request.setAttribute("msg"             , msg             );
			request.setAttribute("listTipoServico" , listTipoServico );
			request.setAttribute("listTipoServicos", listTipoServicos);
			request.getRequestDispatcher("manutencao/manutencaoTipoServico.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
