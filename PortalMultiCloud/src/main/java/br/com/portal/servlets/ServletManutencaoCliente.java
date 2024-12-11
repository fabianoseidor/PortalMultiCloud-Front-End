package br.com.portal.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.portal.dao.DAOClienteRepository;
import br.com.portal.dao.DAOStatusCliente;
import br.com.portal.model.ModelCliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletManutencaoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAOStatusCliente daoStatusCliente = new DAOStatusCliente();
	DAOClienteRepository daoClienteRepository = new DAOClienteRepository();
	private Integer offsetBegin = 0;
//	private static final Integer OFFSET_BEGIN = 0;
	private static final Integer OFFSET_END   = 20;
       
    public ServletManutencaoCliente() {}
    /*
     * 
     * 
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idCliente = request.getParameter("idcliente");
			String acao      = request.getParameter("acao");
			
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarCliente") ) {
			    List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente(offsetBegin, OFFSET_END);
			    request.setAttribute("msg", "Clientes Carregados!" );
			    request.setAttribute("modelClientes", modelClientes);
			    request.setAttribute("totalPagina", daoClienteRepository.getTotalPag(OFFSET_END));
			    request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar") ) {
				   String idClienteEditar = request.getParameter("id_cliente");
				   request.setAttribute("msg", "Cliente em edicao!" );
				   
				   ModelCliente modelClienteManu = daoClienteRepository.getClienteID( Long.parseLong( idClienteEditar ) );
				   request.setAttribute("modelClienteManu", modelClienteManu);
					    
				   List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente(offsetBegin, OFFSET_END);
				   request.setAttribute("modelClientes", modelClientes);
				   request.setAttribute("totalPagina", daoClienteRepository.getTotalPag(OFFSET_END));
				   request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);
				    
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax") ) {
					String nomeBusca = request.getParameter("nomeBusca");
					String tipoPesq  = request.getParameter("tipoPesq").trim();
					List<ModelCliente> dadosJsonUser = new ArrayList<ModelCliente>();
					// buscarListaClienteAlias
					//buscarListaClienteCNPJ
					if(tipoPesq.equals("1")) // Pesquisa pelo Nome do Cliente.
                       dadosJsonUser = daoClienteRepository.buscarListaClienteNome(nomeBusca);
					  else if(tipoPesq.equals("2")) // Pesquisa pelo Alias do Cliente.
						  dadosJsonUser = daoClienteRepository.buscarListaClienteAlias(nomeBusca);
					    else if(tipoPesq.equals("3")) // Pesquisa pelo CNPJ do Cliente.
					    	dadosJsonUser = daoClienteRepository.buscarListaClienteCNPJ(nomeBusca);
					 
					ObjectMapper objectMapper = new ObjectMapper();
					
				      try {
				          String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dadosJsonUser);
				          //System.out.println(json);
				          response.getWriter().write(json);
				       } catch(Exception e) {
				          e.printStackTrace();
				       }
			} else if(idCliente != null && !idCliente.isEmpty()) {
				  ModelCliente modelClienteManu = daoClienteRepository.getClienteID( Long.parseLong(idCliente) );
				  request.setAttribute("modelClienteManu", modelClienteManu);
				    
				  List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente( offsetBegin, OFFSET_END );
				  request.setAttribute("modelClientes", modelClientes);
				  request.setAttribute("totalPagina", daoClienteRepository.getTotalPag(OFFSET_END));
				  request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);
				  
				} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar") ) {
						offsetBegin = Integer.parseInt( request.getParameter("pag") ) * OFFSET_END;	
						List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente( offsetBegin, OFFSET_END );
						request.setAttribute("modelClientes", modelClientes);
						request.setAttribute("totalPagina", daoClienteRepository.getTotalPag( OFFSET_END ) );
						request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);
				 } else {
					 
				    List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente( offsetBegin, OFFSET_END );
				    request.setAttribute("modelClientes", modelClientes);
				    request.setAttribute("totalPagina", daoClienteRepository.getTotalPag(OFFSET_END));
				    request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);
				 }
		} catch (NumberFormatException | SQLException e) {
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
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    try {	
	    	String msg = "Operacao realizada com sucesso!";
		    String id_cliente          = request.getParameter("id_cliente");
		    String id_porte_emp        = request.getParameter("id_porte_emp");
		    String id_status_emp       = request.getParameter("id_status_emp");
		    String razao_social        = request.getParameter("razao_social");
		    String nome_fantasia       = request.getParameter("nome_fantasia");
		    String site                = request.getParameter("site");
		    String cep                 = request.getParameter("cep");
		    String endereco            = request.getParameter("endereco");
		    String bairro              = request.getParameter("bairro");
		    String numero              = request.getParameter("numero");
		    String complemento         = request.getParameter("complemento");
		    String cidade              = request.getParameter("cidade");
		    String estado              = request.getParameter("estado");
		    String pais                = request.getParameter("pais");
		    String cnpj                = request.getParameter("cnpj");
		    String inscricao_estadual  = request.getParameter("inscricao_estadual");
		    String inscricao_municipal = request.getParameter("inscricao_municipal");
		    String nicho_mercado       = request.getParameter("nicho_mercado");
		    String dt_criacao          = request.getParameter("dt_criacao");
		    String observacao          = request.getParameter("observacao");
		    String alias               = request.getParameter("alias");

		    ModelCliente       modelClienteManu = new ModelCliente();
		    HttpServletRequest req              = (HttpServletRequest) request;
			HttpSession        session          = req.getSession();
		    String             usuarioLogado    = (String) session.getAttribute("usuario");
		    
		    modelClienteManu.setId_cliente         ( id_cliente          != null && !id_cliente.isEmpty()          ? Long.parseLong(id_cliente   ): null );
		    modelClienteManu.setId_porte_emp       ( id_porte_emp        != null && !id_porte_emp.isEmpty()        ? Long.parseLong(id_porte_emp ): null );
		    modelClienteManu.setId_status_emp      ( id_status_emp       != null && !id_status_emp.isEmpty()       ? Long.parseLong(id_status_emp): null );
		    modelClienteManu.setDt_criacao         ( dt_criacao          != null && !dt_criacao.isEmpty()          ? dt_criacao                   : null );
		    modelClienteManu.setRazao_social       ( razao_social        != null && !razao_social.isEmpty()        ? razao_social                 : null );
		    modelClienteManu.setNome_fantasia      ( nome_fantasia       != null && !nome_fantasia.isEmpty()       ? nome_fantasia                : null );
		    modelClienteManu.setSite               ( site                != null && !site.isEmpty()                ? site                         : null );
		    modelClienteManu.setCep                ( cep                 != null && !cep.isEmpty()                 ? cep                          : null );
		    modelClienteManu.setEndereco           ( endereco            != null && !endereco.isEmpty()            ? endereco                     : null );
		    modelClienteManu.setBairro             ( bairro              != null && !bairro.isEmpty()              ? bairro                       : null );
		    modelClienteManu.setNumero             ( numero              != null && !numero.isEmpty()              ? numero                       : null );
		    modelClienteManu.setComplemento        ( complemento         != null && !complemento.isEmpty()         ? complemento                  : null );
		    modelClienteManu.setCidade             ( cidade              != null && !cidade.isEmpty()              ? cidade                       : null );
		    modelClienteManu.setEstado             ( estado              != null && !estado.isEmpty()              ? estado                       : null );
		    modelClienteManu.setPais               ( pais                != null && !pais.isEmpty()                ? pais                         : null );
		    modelClienteManu.setCnpj               ( cnpj                != null && !cnpj.isEmpty()                ? cnpj                         : null );
		    modelClienteManu.setInscricao_estadual ( inscricao_estadual  != null && !inscricao_estadual.isEmpty()  ? inscricao_estadual           : null );
		    modelClienteManu.setInscricao_municipal( inscricao_municipal != null && !inscricao_municipal.isEmpty() ? inscricao_municipal          : null );
		    modelClienteManu.setNicho_mercado      ( nicho_mercado       != null && !nicho_mercado.isEmpty()       ? nicho_mercado                : null );
		    modelClienteManu.setLogin_cadastro     ( usuarioLogado       != null && !usuarioLogado.isEmpty()       ? usuarioLogado                : null );
		    modelClienteManu.setAlias              ( alias               != null && !alias.isEmpty()               ? alias                        : null );
	
		    modelClienteManu.setStatus_emp(  daoStatusCliente.getNomeStatus( modelClienteManu.getId_status_emp() ) );
		    // Exemplo aula Alex
		    // Date dt = new Date(new SimpleDateFormat("dd/mm/yyyy").parse(dt_criacao).getTime());
		    // import java.text.SimpleDateFormat;
		    // import java.sql.Date;
		    modelClienteManu.setObservacao(observacao);

			if(daoClienteRepository.validarRazaoSocial(modelClienteManu.getRazao_social()) && modelClienteManu.getId_cliente() == null) {
				msg = "Ja existe este Cliente cadastrado na Base de Dados!";
			}else {
				if(modelClienteManu.isNovo()) {
					msg = "Registro gravado com sucesso!";
				}else msg = "Registro atualizado com sucesso!";
				
				modelClienteManu = daoClienteRepository.gravarCliente(modelClienteManu);		
			}

		    // Carrega as informacoes na Tela apos uma acao de Insert ou Update
//	        System.out.println(modelCliente);
			request.setAttribute("msg", msg );
			request.setAttribute("modelClienteManu", modelClienteManu);
			 
			List<ModelCliente> modelClientes = daoClienteRepository.buscarListaCliente(offsetBegin, OFFSET_END);
			request.setAttribute("modelClientes", modelClientes);
			 
			request.setAttribute("totalPagina", daoClienteRepository.getTotalPag(OFFSET_END));
			request.getRequestDispatcher("principal/mautencaoCliente.jsp").forward(request, response);    
		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
