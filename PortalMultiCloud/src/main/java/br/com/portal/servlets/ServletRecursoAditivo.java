package br.com.portal.servlets;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import java.text.Normalizer;

import br.com.portal.dao.DAORecursoContratoAditivoRel;
import br.com.portal.dao.DAORecusoAditivo;
import br.com.portal.dao.DAORecusoContrato;
import br.com.portal.model.ModelFamiliaFlavors;
import br.com.portal.model.ModelRecursoAditivo;
import br.com.portal.model.ModelRecursoContratoCliente;
import br.com.portal.model.ModelRecursoContratoAditivoRel;

public class ServletRecursoAditivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	DAORecusoContrato daoRecusoContrato = new DAORecusoContrato();
       
    public ServletRecursoAditivo() {
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   	try {
	   		String acao = request.getParameter("acao");

	   	    if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				String idContrato = request.getParameter("idContrato");
	   			String idRecurso  = request.getParameter("idRecurso");
	   			String idAditivo  = request.getParameter("idAditivo");
	   			
	   			ModelRecursoAditivo modelRecursoAditivo = this.montaRecursoAditivo( Long.parseLong( idRecurso ), request);            // Monta Estrutura de Recurso para Montar na tela
				ModelFamiliaFlavors mdFamilias          = this.montaFamilia( modelRecursoAditivo.getId_familia_flavors() , request ); // Monta Estrutura de Familia para Montar na tela
				this.montaRecursoAditivoRel( Long.parseLong(idContrato), Long.parseLong(idAditivo),request );                         // Monta Lista de Estrutura Recursos para Montar na tela
				
				modelRecursoAditivo.setCpu  ( mdFamilias.getCpu()   );
				modelRecursoAditivo.setRam  ( mdFamilias.getRam()   );
				modelRecursoAditivo.setValor( mdFamilias.getValor() );
				
			    request.setAttribute("msg", "Recurso em modo de Edição" );
				request.getRequestDispatcher("principal/recursoAditivo.jsp").forward(request, response);
			
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaRecursoInicial") ) {

				String idContrato  = request.getParameter("idContrato" );
				String idAditivo   = request.getParameter("idAditivo"  );
				
				ModelRecursoAditivo modelRecursoAditivo = new ModelRecursoAditivo();

			    if( ( idContrato != null && !idContrato.isEmpty() ) && ( idAditivo != null && !idAditivo.isEmpty() )) {
				    ModelRecursoContratoCliente modelRecursoContratoCliente = this.montaRecursoContratoCliente(Long.parseLong(idContrato), request); 
				    if( !modelRecursoContratoCliente.isNovo() ) {
				    	modelRecursoAditivo.setId_contrato ( modelRecursoContratoCliente.getId_contrato()  );
				    	modelRecursoAditivo.setId_aditivado(Long.parseLong(idAditivo)                      );
				    	modelRecursoAditivo.setId_cliente  ( modelRecursoContratoCliente.getId_cliente()   );
				    	modelRecursoAditivo.setNomeCliente ( modelRecursoContratoCliente.getRazao_social() );
				    	modelRecursoAditivo.setId_nuvem    ( modelRecursoContratoCliente.getId_nuvem()     );
				    	modelRecursoAditivo.setNuvem       ( modelRecursoContratoCliente.getNuvem()        );
				    	
				    }
		            this.montaRecursoAditivoRel( Long.parseLong(idContrato), Long.parseLong(idAditivo), request );//daoRecursoContratoRel.getListaRecursoIdContrato( Long.parseLong(idcontrato) );

			    }
			    request.setAttribute("modelRecursoAditivo", modelRecursoAditivo);
			    request.getRequestDispatcher("principal/recursoAditivo.jsp").forward(request, response);
			    
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaCamosFamiliaFl") ) {
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
			}
			
			 else {
				request.getRequestDispatcher("principal/recursoAditivo.jsp").forward(request, response);
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
			DAORecusoAditivo daoRecusoAditivo = new DAORecusoAditivo();

			// Long
			String id_recurso         = request.getParameter("id_recurso"        );
			String id_aditivado        = request.getParameter("id_aditivado"     );
			String id_contrato        = request.getParameter("id_contrato"       );
			String dt_cadastro        = request.getParameter("dt_cadastro"       );       
			String id_cliente         = request.getParameter("id_cliente"        );        
			String nomeCliente        = request.getParameter("nomeCliente"       );       
			String id_status_recurso  = request.getParameter("id_status_recurso" ); 
			String id_retencao_backup = request.getParameter("id_retencao_backup");
			String id_tipo_disco      = request.getParameter("id_tipo_disco"     );     
			String id_so              = request.getParameter("id_so"             );             
			String id_ambiente        = request.getParameter("id_ambiente"       );       
			String id_tipo_servico    = request.getParameter("id_tipo_servico"   );   
			String tag_vcenter        = request.getParameter("tag_vcenter"       );       
//			String id_nuvem           = request.getParameter("id_nuvem"          );          
			String id_familia_flavors = request.getParameter("id_familia_flavors");
			String cpu                = request.getParameter("cpu"               );               
			String ram                = request.getParameter("ram"               );               
		    String valor              = request.getParameter("valor"             );
		    if(valor != null && !valor.isEmpty()) {
			    valor = Normalizer.normalize(valor, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	valor = valor.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }

			String obs                = request.getParameter("obs");
			
			ModelRecursoAditivo modelRecursoAditivo = new ModelRecursoAditivo();
			
			modelRecursoAditivo.setId_recurso        ( id_recurso         != null && !id_recurso.isEmpty()         ? Long.parseLong(id_recurso)        : null );
			modelRecursoAditivo.setId_contrato       ( id_contrato        != null && !id_contrato.isEmpty()        ? Long.parseLong(id_contrato)       : null );
			modelRecursoAditivo.setId_aditivado      ( id_aditivado       != null && !id_aditivado.isEmpty()       ? Long.parseLong(id_aditivado)      : null );
			modelRecursoAditivo.setDt_cadastro       ( dt_cadastro        != null && !dt_cadastro.isEmpty()        ? dt_cadastro                       : null );
			modelRecursoAditivo.setId_cliente        ( id_cliente         != null && !id_cliente.isEmpty()         ? Long.parseLong(id_cliente)        : null );
			modelRecursoAditivo.setId_status_recurso ( id_status_recurso  != null && !id_status_recurso.isEmpty()  ? Long.parseLong(id_status_recurso) : null );
			modelRecursoAditivo.setId_retencao_backup( id_retencao_backup != null && !id_retencao_backup.isEmpty() ? Long.parseLong(id_retencao_backup): null );
			modelRecursoAditivo.setId_tipo_disco     ( id_tipo_disco      != null && !id_tipo_disco.isEmpty()      ? Long.parseLong(id_tipo_disco)     : null );
			modelRecursoAditivo.setId_so             ( id_so              != null && !id_so.isEmpty()              ? Long.parseLong(id_so)             : null );
			modelRecursoAditivo.setId_ambiente       ( id_ambiente        != null && !id_ambiente.isEmpty()        ? Long.parseLong(id_ambiente)       : null );
			modelRecursoAditivo.setId_tipo_servico   ( id_tipo_servico    != null && !id_tipo_servico.isEmpty()    ? Long.parseLong(id_tipo_servico)   : null );
			modelRecursoAditivo.setTag_vcenter       ( tag_vcenter        != null && !tag_vcenter.isEmpty()        ? tag_vcenter                       : null );
//			modelRecursoAditivo.setId_nuvem          ( id_nuvem           != null && !id_nuvem.isEmpty()           ? Long.parseLong(id_nuvem)          : null );
			modelRecursoAditivo.setId_familia_flavors( id_familia_flavors != null && !id_familia_flavors.isEmpty() ? Long.parseLong(id_familia_flavors): null );
			modelRecursoAditivo.setCpu               ( cpu                != null && !cpu.isEmpty()                ? cpu                               : null );
			modelRecursoAditivo.setRam               ( ram                != null && !ram.isEmpty()                ? ram                               : null );
			modelRecursoAditivo.setValor             ( valor              != null && !valor.isEmpty()              ? valor                             : null );
			modelRecursoAditivo.setObs               ( obs                != null && !obs.isEmpty()                ? obs                               : null );
			modelRecursoAditivo.setNomeCliente       ( nomeCliente        != null && !nomeCliente.isEmpty()        ? nomeCliente                       : null );
			
	    	if(modelRecursoAditivo.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
            
	    	// Grava ou Inseri as informacoes na Base.
	    	modelRecursoAditivo = daoRecusoAditivo.gravarContrato(modelRecursoAditivo);

			if( modelRecursoAditivo != null ) {
				ModelFamiliaFlavors mdFamilias = this.montaFamilia( modelRecursoAditivo.getId_familia_flavors() , request );
				modelRecursoAditivo.setCpu  ( mdFamilias.getCpu()   );
				modelRecursoAditivo.setRam  ( mdFamilias.getRam()   );
				modelRecursoAditivo.setValor( mdFamilias.getValor() );
				this.montaRecursoAditivoRel ( modelRecursoAditivo.getId_contrato(), modelRecursoAditivo.getId_aditivado(), request );
			}

		    request.setAttribute("msg", msg );
		    request.setAttribute("modelRecursoAditivo", modelRecursoAditivo);
		    request.getRequestDispatcher("principal/recursoAditivo.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	
	private ModelFamiliaFlavors montaFamilia( Long idFamilia, HttpServletRequest request ){
		ModelFamiliaFlavors mdFamilias = new ModelFamiliaFlavors();
		DAORecusoAditivo daoRecusoAditivo = new DAORecusoAditivo();
		mdFamilias = daoRecusoAditivo.getFamiliaFlavors( idFamilia );
		request.setAttribute("mdFamilias", mdFamilias);
		return mdFamilias;
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	
	private void montaRecursoAditivoRel( Long idContrato, Long idAditivo, HttpServletRequest request ){
		DAORecursoContratoAditivoRel    daoRecursoContratoRel = new DAORecursoContratoAditivoRel();
		List<ModelRecursoContratoAditivoRel> modelRecContRels = daoRecursoContratoRel.getListaRecursoIdContratoIdAditivo( idContrato, idAditivo );
		request.setAttribute("modelRecContRels", modelRecContRels);
	}
	/*
	 * 
	 * 
	 * 
	 * */	
	
	private ModelRecursoAditivo montaRecursoAditivo( Long id_Recurso, HttpServletRequest request ) throws Exception{
		DAORecusoAditivo daoRecusoAditivo = new DAORecusoAditivo();
		ModelRecursoAditivo modelRecursoAditivo = daoRecusoAditivo.getAditivoIdRecurso( id_Recurso );
		request.setAttribute("modelRecursoAditivo", modelRecursoAditivo);
		return modelRecursoAditivo;
	}
	/*
	 * 
	 * 
	 * 
	 * */	

	private ModelRecursoContratoCliente montaRecursoContratoCliente( Long idContrato, HttpServletRequest request ) throws Exception{
		DAORecusoAditivo daoRecusoAditivo = new DAORecusoAditivo();
		ModelRecursoContratoCliente modelRecursoContratoCliente = daoRecusoAditivo.getTelaInical( idContrato );
		return modelRecursoContratoCliente;
	}	
}
