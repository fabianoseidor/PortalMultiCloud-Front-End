package br.com.portal.servlets.consolidacao;

import java.io.IOException;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.portal.dao.consolidacao.DAOVlrFaturado;
import br.com.portal.model.consolidacao.ModelVlrFaturado;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletvlrFaturadovlrAFatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletvlrFaturadovlrAFatura() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String desvioPadrao = request.getParameter( "desvioPadrao" );
			String ano          = request.getParameter( "ano"          );
			String mes          = request.getParameter( "mes"          );
			String desvioPadraoView  =desvioPadrao;
			request.setAttribute("desvioPadraoView" , desvioPadraoView );
			request.setAttribute("ano" , ano );
			request.setAttribute("mes" , mes );
	
		    if(desvioPadrao != null && !desvioPadrao.isEmpty()) {
		    	desvioPadrao = Normalizer.normalize(desvioPadrao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", " ");
		    	desvioPadrao = desvioPadrao.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		    }
		    
		    DAOVlrFaturado daoVlrFaturado = new DAOVlrFaturado();
		    
		    List<ModelVlrFaturado> listVlrFaturados = daoVlrFaturado.getvlrAFaturar( Integer.parseInt(mes), Integer.parseInt(ano), Double.parseDouble(desvioPadrao) );

		    int    qtyOK         = 0;
		    int    qtyNOK        = 0;
		    Double vlrAFatura    = 0.0;
		    Double vlrFaturado   = 0.00;
		    int    qtyNPepSAP    = 0;
		    int    qtyNPepPortal = 0;
		    Locale localeBR = new Locale( "pt", "BR" );  
		    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);  
		    
		    for( ModelVlrFaturado lista : listVlrFaturados ) {
		    	if( lista.getStatus().trim().equals("OK") ) {
		    		qtyOK++;
		    	}else qtyNOK++;
		    	
		    	if( lista.getVlrAFaturar() != null ) {
		    		vlrAFatura  += lista.getVlrAFaturar();
		    		lista.setVlrAFaturarView(dinheiroBR.format(lista.getVlrAFaturar()));
		    	}
		    	if( lista.getVlrFaturado() != null ) {
		    		vlrFaturado += lista.getVlrFaturado();
		    		lista.setVlrFaturadoView( dinheiroBR.format(lista.getVlrFaturado()) );
		    	}
		    	if( lista.getVlrDesvioDiff() == null ) lista.setVlrDesvioDiff(0.00);
		    	
		    	lista.setVlrDesvioDiffView(dinheiroBR.format(lista.getVlrDesvioDiff()));
		    	// NOT_EXIST_AFATURA_NAO_TEM_NO_FATURADO
		    	// NOT_EXIST_FATURADO_NAO_TEM_NO_AFATURA
		    	if( lista.getStatus().trim().equals("NOT_EXIST_AFATURA_NAO_TEM_NO_FATURADO") )  qtyNPepPortal++;
		    	if( lista.getStatus().trim().equals("NOT_EXIST_FATURADO_NAO_TEM_NO_AFATURA") )  qtyNPepSAP++;
		    }
		    String SvlrAFatura  =  dinheiroBR.format(vlrAFatura);
		    String SvlrFaturado =  dinheiroBR.format(vlrFaturado);


		    listVlrFaturados.sort((a, b) -> a.getStatus().compareTo(b.getStatus( ) ) );
		    listVlrFaturados.sort((a, b) -> a.getVlrDesvioDiff().compareTo(b.getVlrDesvioDiff() ) );
		    
		    request.setAttribute("qtyOK"        , qtyOK         );
		    request.setAttribute("qtyNOK"       , qtyNOK        );
		    request.setAttribute("SvlrAFatura"  , SvlrAFatura   );
		    request.setAttribute("SvlrFaturado" , SvlrFaturado  );
		    request.setAttribute("qtyNPepSAP"   , qtyNPepSAP    );
		    request.setAttribute("qtyNPepPortal", qtyNPepPortal );

	//	    request.setAttribute("listVlrFaturados" , listVlrFaturados );
		    request.getSession().setAttribute( "listVlrFaturados", listVlrFaturados         );
		    request.getRequestDispatcher("consolidacao/vlrFaturado-vlrAFatura.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	}

}
