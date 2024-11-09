package br.com.portal.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.portal.dao.DAOClienteRepository;
import br.com.portal.dao.DAOTelefoneRepository;
import br.com.portal.model.ModelTelefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletTelefone extends HttpServlet{
	private static final long serialVersionUID = 1L;
	DAOClienteRepository  daoClienteRepository  = new DAOClienteRepository();
	DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
	private Integer offsetBegin             = 0;  // controle do inicio da conulta da paginacao
	private static final Integer OFFSET_END = 10; // Quantidade máxima de informacao por pagina
	
	public ServletTelefone() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String idCliente = request.getParameter("idcliente");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir") ) {
				String idFelefone = request.getParameter("id_telefone");
				String idcli = request.getParameter("idcli");
				// Delete telefone
				daoTelefoneRepository.deleteFone(Long.parseLong(idFelefone));
				request.setAttribute("msg", "Contato excluido com sucesso!" );
			    ModelTelefone modelTelefone = daoTelefoneRepository.getClineteID(Long.parseLong(idcli));
			    request.setAttribute("modelTelefone", modelTelefone);

			    if( modelTelefone != null ) {
	                List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone(  modelTelefone.getCliente().getId_cliente(), offsetBegin, OFFSET_END );			    
			        request.setAttribute("modelTelefones", modelTelefones);
			        request.setAttribute("totalPagina", daoTelefoneRepository.getTotalPag( modelTelefone.getCliente().getId_cliente(), OFFSET_END ) );
			    }
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {

				String idFelefone = request.getParameter("id_telefone");
	            ModelTelefone modelTelefone = daoTelefoneRepository.getFoneID(Long.parseLong(idFelefone));
			    request.setAttribute("modelTelefone", modelTelefone);				

	            List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone(  modelTelefone.getCliente().getId_cliente(), offsetBegin, OFFSET_END );
			    request.setAttribute("modelTelefones", modelTelefones);
			    request.setAttribute("totalPagina", daoTelefoneRepository.getTotalPag( modelTelefone.getCliente().getId_cliente(), OFFSET_END ) );
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
			}else if(idCliente != null && !idCliente.isEmpty()) {
				        
				     ModelTelefone modelTelefone = new ModelTelefone();
				     List<ModelTelefone> modelTelefones = new ArrayList<ModelTelefone>();
				     if( daoTelefoneRepository.isTelExist( Long.parseLong(idCliente))) {
						 modelTelefone = daoTelefoneRepository.getClineteID(Long.parseLong(idCliente));
				     }else {
				    	 modelTelefone.setCliente( daoClienteRepository.getClienteID(Long.parseLong(idCliente) ));
				     }

				     modelTelefones = daoTelefoneRepository.listaFone( modelTelefone.getCliente().getId_cliente(), offsetBegin, OFFSET_END );
				     request.setAttribute("modelTelefone", modelTelefone);
					 request.setAttribute("modelTelefones", modelTelefones);
					 request.setAttribute("totalPagina", daoTelefoneRepository.getTotalPag( modelTelefone.getCliente().getId_cliente(), OFFSET_END ));
					 request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		
				}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar") ) {
						offsetBegin = Integer.parseInt( request.getParameter("pag") ) * OFFSET_END;	
						String idCliePag = request.getParameter("idCliePag");
					    ModelTelefone modelTelefone        = daoTelefoneRepository.getClineteID(Long.parseLong(idCliePag));
					    List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone( modelTelefone.getCliente().getId_cliente(), offsetBegin, OFFSET_END );
					    request.setAttribute("modelTelefone", modelTelefone);
						request.setAttribute("modelTelefones", modelTelefones);
						request.setAttribute("totalPagina", daoTelefoneRepository.getTotalPag( modelTelefone.getCliente().getId_cliente(), OFFSET_END ) );
						request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				 }else {
				    request.getRequestDispatcher("principal/cliente.jsp").forward(request, response);    
				}
			
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String msg = "Operacao realizada com sucesso!";
        try {
		    String id_telefone = request.getParameter("id_telefone");
		    String id_cliente  = request.getParameter("id_cliente");
		    String id_funcao   = request.getParameter("funcaoContato");
		    String nomeContato = request.getParameter("nomeContato");
		    String telefone    = request.getParameter("telefone");
		    String email       = request.getParameter("email");
		    String obsTelefone = request.getParameter("obsTelefone");
			
		    ModelTelefone modelTelefone = new ModelTelefone();
		    modelTelefone.setId_telefone( id_telefone != null && !id_telefone.isEmpty() ? Long.parseLong(id_telefone): null);
		    modelTelefone.setEmail(email);
			modelTelefone.setCliente( daoClienteRepository.getClienteID(Long.parseLong(id_cliente) ));
			modelTelefone.setId_funcao( Long.parseLong( id_funcao));
			modelTelefone.setTelefone(telefone);
            modelTelefone.setNome_contato(nomeContato);
            modelTelefone.setObs(obsTelefone);             

	    	if(modelTelefone.isNovo()) {
	    		msg = "Registro gravado com sucesso!";
	    	}else msg = "Registro atualizado com sucesso!";
	    	
	    	modelTelefone = daoTelefoneRepository.gravaTelefone(modelTelefone);

            List<ModelTelefone> modelTelefones = daoTelefoneRepository.listaFone( Long.parseLong( id_cliente ), offsetBegin, OFFSET_END );

		    request.setAttribute("modelTelefone", modelTelefone);			
		    request.setAttribute("msg", msg );
		    request.setAttribute("modelTelefones", modelTelefones);
		    request.setAttribute("totalPagina", daoTelefoneRepository.getTotalPag( modelTelefone.getCliente().getId_cliente(), OFFSET_END ) );
		    request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);    

            
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}
	}
	
	

}
