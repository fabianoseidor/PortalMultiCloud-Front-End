package br.com.portal.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAORecursoContratoAditivoRel;
import br.com.portal.dao.DAORecusoContrato;
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelRecursoContrato;
import br.com.portal.model.ModelRecursoContratoCliente;
import br.com.portal.model.ModelRecursoContratoAditivoRel;

public class ServletRecursoContrato extends HttpServlet{
	private static final long serialVersionUID = 1L;
//	DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
       
    public ServletRecursoContrato() {
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   	try {
	   		String acao = request.getParameter("acao");

	   	 if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {

				String idContrato = request.getParameter("idContrato");
	   			String id_Recurso = request.getParameter("idRecurso");
	   			ModelRecursoContrato modelRecursoContrato      = this.montaRecursoContrato( Long.parseLong(id_Recurso), request);             // Monta Estrutura de Recurso para Montar na tela
				ModelFamiliaFlavors mdFamilias                 = this.montaFamilia( modelRecursoContrato.getId_familia_flavors() , request ); // Monta Estrutura de Familia para Montar na tela
				this.montaRecursoContratoRel( Long.parseLong(idContrato), request );         // Monta Lista de Estrutura Recursos para Montar na tela
				
				modelRecursoContrato.setCpu  ( mdFamilias.getCpu()   );
				modelRecursoContrato.setRam  ( mdFamilias.getRam()   );
				modelRecursoContrato.setValor( mdFamilias.getValor() );
				
			    request.setAttribute("msg", "Recurso em modo de Edição" );
				request.getRequestDispatcher("principal/recursoContrato.jsp").forward(request, response);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaRecursoInicial") ) {
				String idcontrato = request.getParameter("idcontrato");
			    ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();

			    if( idcontrato != null && !idcontrato.isEmpty() ) {
				    ModelRecursoContratoCliente modelRecursoContratoCliente = this.montaRecursoContratoCliente(Long.parseLong(idcontrato), request); //daoRecusoContrato.getTelaInical( Long.parseLong(idcontrato) );
				    if( !modelRecursoContratoCliente.isNovo() ) {
				    	modelRecursoContrato.setId_contrato( modelRecursoContratoCliente.getId_contrato()  );
				    	modelRecursoContrato.setId_cliente ( modelRecursoContratoCliente.getId_cliente()   );
				    	modelRecursoContrato.setNomeCliente( modelRecursoContratoCliente.getRazao_social() );
				    	modelRecursoContrato.setId_nuvem   ( modelRecursoContratoCliente.getId_nuvem()     );
				    	modelRecursoContrato.setNuvem      ( modelRecursoContratoCliente.getNuvem()        );
				    }
		            this.montaRecursoContratoRel( Long.parseLong(idcontrato), request );//daoRecursoContratoRel.getListaRecursoIdContrato( Long.parseLong(idcontrato) );

			    }

			    request.setAttribute("modelRecursoContrato", modelRecursoContrato);
			    request.getRequestDispatcher("principal/recursoContrato.jsp").forward(request, response);
			    
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaCamosFamiliaFl") ) {
				String idFamiliaFl = request.getParameter("idFamiliaFl");
				
				if( idFamiliaFl != null && !idFamiliaFl.isEmpty() ) {

					ModelFamiliaFlavors mdFamilias = new ModelFamiliaFlavors();
					DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
					mdFamilias = daoRecusoContrato.getFamiliaFlavors( Long.parseLong(idFamiliaFl) );
				//	System.out.println(mdFamilias);
					Gson gson = new Gson();
					String lista = gson.toJson(mdFamilias);
					response.getWriter().write(lista);
				}
			}else {
				   request.getRequestDispatcher("principal/recursoContrato.jsp").forward(request, response);
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
	    	DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();

			// Long
			String id_recurso         = request.getParameter("id_recurso");        
			String dt_cadastro        = request.getParameter("dt_cadastro");       
			String id_cliente         = request.getParameter("id_cliente");        
			String nomeCliente        = request.getParameter("nomeCliente");       
			String id_contrato        = request.getParameter("id_contrato");
			String id_status_recurso  = request.getParameter("id_status_recurso"); 
			String id_retencao_backup = request.getParameter("id_retencao_backup");
			String id_tipo_disco      = request.getParameter("id_tipo_disco");     
			String id_so              = request.getParameter("id_so");             
			String id_ambiente        = request.getParameter("id_ambiente");       
			String id_tipo_servico    = request.getParameter("id_tipo_servico");   
			String tag_vcenter        = request.getParameter("tag_vcenter");       
	//		String id_nuvem           = request.getParameter("id_contrato");          
			String id_familia_flavors = request.getParameter("id_familia_flavors");
			String cpu                = request.getParameter("cpu");               
			String ram                = request.getParameter("ram");               
		    String valor              = request.getParameter("valor");
		    if(valor != null && !valor.isEmpty()) {
			    valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }

			String obs                = request.getParameter("obs");
			
			ModelRecursoContrato modelRecursoContrato = new ModelRecursoContrato();
			
			modelRecursoContrato.setId_recurso        ( id_recurso         != null && !id_recurso.isEmpty()         ? Long.parseLong(id_recurso)        : null );
			modelRecursoContrato.setDt_cadastro       ( dt_cadastro        != null && !dt_cadastro.isEmpty()        ? dt_cadastro                       : null );
			modelRecursoContrato.setId_cliente        ( id_cliente         != null && !id_cliente.isEmpty()         ? Long.parseLong(id_cliente)        : null );
			modelRecursoContrato.setId_contrato       ( id_contrato        != null && !id_contrato.isEmpty()        ? Long.parseLong(id_contrato)       : null );
			modelRecursoContrato.setId_status_recurso ( id_status_recurso  != null && !id_status_recurso.isEmpty()  ? Long.parseLong(id_status_recurso) : null );
			modelRecursoContrato.setId_retencao_backup( id_retencao_backup != null && !id_retencao_backup.isEmpty() ? Long.parseLong(id_retencao_backup): null );
			modelRecursoContrato.setId_tipo_disco     ( id_tipo_disco      != null && !id_tipo_disco.isEmpty()      ? Long.parseLong(id_tipo_disco)     : null );
			modelRecursoContrato.setId_so             ( id_so              != null && !id_so.isEmpty()              ? Long.parseLong(id_so)             : null );
			modelRecursoContrato.setId_ambiente       ( id_ambiente        != null && !id_ambiente.isEmpty()        ? Long.parseLong(id_ambiente)       : null );
			modelRecursoContrato.setId_tipo_servico   ( id_tipo_servico    != null && !id_tipo_servico.isEmpty()    ? Long.parseLong(id_tipo_servico)   : null );
			modelRecursoContrato.setTag_vcenter       ( tag_vcenter        != null && !tag_vcenter.isEmpty()        ? tag_vcenter                       : null );
			modelRecursoContrato.setId_familia_flavors( id_familia_flavors != null && !id_familia_flavors.isEmpty() ? Long.parseLong(id_familia_flavors): null );
			modelRecursoContrato.setCpu               ( cpu                != null && !cpu.isEmpty()                ? cpu                               : null );
			modelRecursoContrato.setRam               ( ram                != null && !ram.isEmpty()                ? ram                               : null );
			modelRecursoContrato.setValor             ( valor              != null && !valor.isEmpty()              ? valor                             : null );
			modelRecursoContrato.setObs               ( obs                != null && !obs.isEmpty()                ? obs                               : null );
			modelRecursoContrato.setNomeCliente       ( nomeCliente        != null && !nomeCliente.isEmpty()        ? nomeCliente                       : null );
			
	    	if(modelRecursoContrato.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
			modelRecursoContrato = daoRecusoContrato.gravarRecursoContrato(modelRecursoContrato);

			if( modelRecursoContrato != null ) {
				ModelFamiliaFlavors mdFamilias = this.montaFamilia( modelRecursoContrato.getId_familia_flavors() , request );
				modelRecursoContrato.setCpu  ( mdFamilias.getCpu()   );
				modelRecursoContrato.setRam  ( mdFamilias.getRam()   );
				modelRecursoContrato.setValor( mdFamilias.getValor() );
				this.montaRecursoContratoRel ( modelRecursoContrato.getId_contrato(), request );
			}

		    request.setAttribute("msg", msg );
		    request.setAttribute("modelRecursoContrato", modelRecursoContrato);
		    request.getRequestDispatcher("principal/recursoContrato.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}
	
	private ModelFamiliaFlavors montaFamilia( Long idFamilia, HttpServletRequest request ){
		ModelFamiliaFlavors mdFamilias = new ModelFamiliaFlavors();
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		mdFamilias = daoRecusoContrato.getFamiliaFlavors( idFamilia );
		request.setAttribute("mdFamilias", mdFamilias);
		return mdFamilias;
	}
	
	private void montaRecursoContratoRel( Long idContrato, HttpServletRequest request ){
//	private List<ModelRecursoContratoRel> montaRecursoContratoRel( Long idContrato, HttpServletRequest request ){
		DAORecursoContratoAditivoRel    daoRecursoContratoRel = new DAORecursoContratoAditivoRel();
		List<ModelRecursoContratoAditivoRel> modelRecContRels = daoRecursoContratoRel.getListaRecursoIdContrato( idContrato );
		request.setAttribute("modelRecContRels", modelRecContRels);
//		return modelRecContRels;
	}
	
	private ModelRecursoContrato montaRecursoContrato( Long id_Recurso, HttpServletRequest request ) throws Exception{
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		ModelRecursoContrato modelRecursoContrato = daoRecusoContrato.getRecursoIdRecurso( id_Recurso );
		request.setAttribute("modelRecursoContrato", modelRecursoContrato);
		return modelRecursoContrato;
	}

	private ModelRecursoContratoCliente montaRecursoContratoCliente( Long idContrato, HttpServletRequest request ) throws Exception{
		DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
		ModelRecursoContratoCliente modelRecursoContratoCliente = daoRecusoContrato.getTelaInical( idContrato );
		return modelRecursoContratoCliente;
	}	
}
