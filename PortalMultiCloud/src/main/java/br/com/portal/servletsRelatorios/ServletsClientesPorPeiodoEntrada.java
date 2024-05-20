package br.com.portal.servletsRelatorios;

import java.io.IOException;
import java.util.List;
// import java.util.HashMap;
// import java.io.File;

import br.com.portal.dao.DAOUtil;
import br.com.portal.dao.relatorio.DAOClientesPorPeiodoEntrada;
import br.com.portal.modelRelatorio.ModelClientesPorPeiodoEntrada;
import br.com.portal.util.ReportUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public class ServletsClientesPorPeiodoEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletsClientesPorPeiodoEntrada() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	DAOUtil daoUtil = new DAOUtil();
        	String acao = request.getParameter("acao");
        	
        	if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ImprimirTela") ) {
        	
	            String dtInicio = daoUtil.FormataDataStringTelaData(request.getParameter("dt_inicio")); // 1
	            String dtFinal  = daoUtil.FormataDataStringTelaData(request.getParameter("dt_final" )); // 2
	        
	            DAOClientesPorPeiodoEntrada daoClientesPorPeiodoEntrada = new DAOClientesPorPeiodoEntrada();
	        
				List<ModelClientesPorPeiodoEntrada> listCliPeiodoEntrada = daoClientesPorPeiodoEntrada.getListaClientesPorPeiodoEntrada(dtInicio, dtFinal);
				
			    request.setAttribute("dtInicio"            , request.getParameter("dt_inicio"));
			    request.setAttribute("dtFinal"             , request.getParameter("dt_final" ));
			    request.setAttribute("listCliPeiodoEntrada", listCliPeiodoEntrada             );
			    request.getRequestDispatcher("relatorios/relClientesPorPeiodoEntrada.jsp").forward(request, response);
			    
        	}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("ImprimirPDF") ) {	
	            String dtInicio = daoUtil.FormataDataStringTelaData(request.getParameter("dt_inicio")); // 1
	            String dtFinal  = daoUtil.FormataDataStringTelaData(request.getParameter("dt_final" )); // 2
	        
	            DAOClientesPorPeiodoEntrada daoClientesPorPeiodoEntrada = new DAOClientesPorPeiodoEntrada();
	        
				List<ModelClientesPorPeiodoEntrada> listCliPeiodoEntrada = daoClientesPorPeiodoEntrada.getListaClientesPorPeiodoEntrada(dtInicio, dtFinal);
				
/*				
				 HashMap<String, Object> params = new HashMap<String, Object>();
				 params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
				 
				 byte[] relatorio = new ReportUtil().geraReltorioPDF(listCliPeiodoEntrada, "rel-clientes-por-peiodo-entrada-jsp", params ,request.getServletContext());
*/				 
				 byte[] relatorio = new ReportUtil().gerarRelatorioPDF(listCliPeiodoEntrada, "rel-clientes-por-peiodo-entrada-jsp", request.getServletContext());
				 
				 response.setHeader("Content-Disposition", "attachment;filename=relClientesPorPeiodoEntrada.pdf");
				 response.getOutputStream().write(relatorio);       		   		
			
		}else{
			  request.getRequestDispatcher("relatorios/relClientesPorPeiodoEntrada.jsp").forward(request, response);
	    }

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}

}
