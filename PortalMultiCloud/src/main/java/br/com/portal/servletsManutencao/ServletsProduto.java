package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import br.com.portal.dao.DAOProduto;
import br.com.portal.model.ModelProduto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOProduto daoProduto = new DAOProduto();       

    public ServletsProduto() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaTelaInicial") ) {

				List<ModelProduto> listProdutos = daoProduto.getListaProduto();
				request.setAttribute("listProdutos", listProdutos);
				request.getRequestDispatcher("manutencao/manutencaoProduto.jsp").forward(request, response);
				
		     }else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id = request.getParameter("id");
					request.setAttribute("msg", "Ambiente em edicao!" );
					ModelProduto listProduto = new ModelProduto();
					    
					listProduto = daoProduto.getProdutoID( Long.parseLong( id ) );
					List<ModelProduto> listProdutos = daoProduto.getListaProduto();

					request.setAttribute("listProduto" , listProduto );
					request.setAttribute("listProdutos", listProdutos);
					request.getRequestDispatcher("manutencao/manutencaoProduto.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id = request.getParameter("id");
					
					if(id != null && !id.isEmpty()) 
				  	   daoProduto.deletarRegistro(id);
					
					List<ModelProduto> listProdutos = daoProduto.getListaProduto();
					request.setAttribute("listProdutos", listProdutos);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoProduto.jsp").forward(request, response);
				}else {
				request.getRequestDispatcher("manutencao/manutencaoProduto.jsp").forward(request, response);
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
			String id_produto = request.getParameter( "id_produto" );
			String dt_criacao = request.getParameter( "dt_criacao" );
			String produto    = request.getParameter( "produto"    );
			String obs        = request.getParameter( "obs"        );
			String valor      = request.getParameter( "valor"      );
		    if(valor != null && !valor.isEmpty()) {
		    	valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }
			
			ModelProduto listProduto = new ModelProduto();

			listProduto.setId_produto( id_produto != null && !id_produto.isEmpty() ? Long.parseLong( id_produto ) : null );
			listProduto.setDt_criacao( dt_criacao != null && !dt_criacao.isEmpty() ? dt_criacao                   : null );
			listProduto.setProduto   ( produto    != null && !produto.isEmpty()    ? produto                      : null );
			listProduto.setObs       ( obs        != null && !obs.isEmpty()        ? obs                          : null );
			listProduto.setValor     ( valor      != null && !valor.isEmpty()      ? valor                        : null );

	    	if(listProduto.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listProduto = daoProduto.gravarAtualizaRegistros( listProduto );

	    	List<ModelProduto> listProdutos = daoProduto.getListaProduto();
		    request.setAttribute("msg"                , msg                );
			request.setAttribute("listProduto" , listProduto );
			request.setAttribute("listProdutos", listProdutos);
			request.getRequestDispatcher("manutencao/manutencaoProduto.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
