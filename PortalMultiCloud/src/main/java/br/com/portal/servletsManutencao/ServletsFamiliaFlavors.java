package br.com.portal.servletsManutencao;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOFamiliaFlavors;
import br.com.portal.model.ModelFamiliaFlavors;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsFamiliaFlavors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOFamiliaFlavors daoFamiliaFlavors = new DAOFamiliaFlavors();
       

    public ServletsFamiliaFlavors() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("MontaFamiliaFlavors") ) {
				String idNuvem = request.getParameter("idNuvem");
				
				List<ModelFamiliaFlavors> listFamiliaFlavorss = daoFamiliaFlavors.listaFamiliaFlavors( Long.parseLong(idNuvem) );

			    Gson gson = new Gson();
			    String lista = gson.toJson(listFamiliaFlavorss);
			    response.getWriter().write(lista);
				
		     }
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
					String id      = request.getParameter("id");
					String idNuvem = request.getParameter("idNuvem");
					
					ModelFamiliaFlavors listFamiliaFlavors = new ModelFamiliaFlavors();
					    
					listFamiliaFlavors = daoFamiliaFlavors.getFamiliaFlavorsID( Long.parseLong( id ) );
					List<ModelFamiliaFlavors> listFamiliaFlavorss = daoFamiliaFlavors.listaFamiliaFlavors( Long.parseLong(idNuvem) );

					request.setAttribute("msg"      , "Ambiente em edicao!" );
					request.setAttribute("listFamiliaFlavors" , listFamiliaFlavors              );
					request.setAttribute("listFamiliaFlavorss", listFamiliaFlavorss             );
					request.getRequestDispatcher("manutencao/manutencaoFamiliaFlavors.jsp").forward(request, response);
					    
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar") ) {
					String id      = request.getParameter("id");
					String idNuvem = request.getParameter("idNuvem");
					
					if(id != null && !id.isEmpty()) 
				  	   daoFamiliaFlavors.deletarRegistro(id);
					
					List<ModelFamiliaFlavors> listFamiliaFlavorss = daoFamiliaFlavors.listaFamiliaFlavors( Long.parseLong(idNuvem) );
					request.setAttribute("listFamiliaFlavorss", listFamiliaFlavorss);
					request.setAttribute("msg", "Registro excluido com sucesso!");
					request.getRequestDispatcher("manutencao/manutencaoFamiliaFlavors.jsp").forward(request, response);
				} else {
				request.getRequestDispatcher("manutencao/manutencaoFamiliaFlavors.jsp").forward(request, response);
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
			String id_familia_flavors = request.getParameter( "id_familia_flavors" );
			String id_nuvem           = request.getParameter( "id_nuvem"           );
			String dt_criacao         = request.getParameter( "dt_criacao"         );
			String familia            = request.getParameter( "familia"            );
			String cpu                = request.getParameter( "cpu"                );
			String ram                = request.getParameter( "ram"                );
			String observacao         = request.getParameter( "observacao"         );
			String valor              = request.getParameter( "valor"              );
			
		    if(valor != null && !valor.isEmpty()) {
		    	valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }
						
			ModelFamiliaFlavors listFamiliaFlavors = new ModelFamiliaFlavors();

			listFamiliaFlavors.setId_familia_flavors( id_familia_flavors != null && !id_familia_flavors.isEmpty() ? Long.parseLong( id_familia_flavors ) : null );
			listFamiliaFlavors.setId_nuvem          ( id_nuvem           != null && !id_nuvem.isEmpty()           ? Long.parseLong( id_nuvem )           : null );
			listFamiliaFlavors.setDt_criacao        ( dt_criacao         != null && !dt_criacao.isEmpty()         ? dt_criacao                           : null );
			listFamiliaFlavors.setFamilia           ( familia            != null && !familia.isEmpty()            ? familia                              : null );
			listFamiliaFlavors.setCpu               ( cpu                != null && !cpu.isEmpty()                ? cpu                                  : null );
			listFamiliaFlavors.setRam               ( ram                != null && !ram.isEmpty()                ? ram                                  : null );
			listFamiliaFlavors.setObservacao        ( observacao         != null && !observacao.isEmpty()         ? observacao                           : null );
			listFamiliaFlavors.setValor             ( valor              != null && !valor.isEmpty()              ? valor                                : null );

	    	if(listFamiliaFlavors.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	listFamiliaFlavors = daoFamiliaFlavors.gravarAtualizaRegistros( listFamiliaFlavors );

	    	List<ModelFamiliaFlavors> listFamiliaFlavorss = daoFamiliaFlavors.listaFamiliaFlavors( listFamiliaFlavors.getId_nuvem() );
		    request.setAttribute("msg"                , msg                );
			request.setAttribute("listFamiliaFlavors" , listFamiliaFlavors );
			request.setAttribute("listFamiliaFlavorss", listFamiliaFlavorss);
			request.getRequestDispatcher("manutencao/manutencaoFamiliaFlavors.jsp").forward(request, response);
		    
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

}
