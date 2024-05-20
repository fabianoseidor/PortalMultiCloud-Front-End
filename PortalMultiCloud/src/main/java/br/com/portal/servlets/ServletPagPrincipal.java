package br.com.portal.servlets;

import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.google.gson.Gson;

import br.com.portal.Beandto.BeanDtoGraficoVlrMes;
import br.com.portal.dao.DAOpagPrincipal;
import br.com.portal.model.ModelPagPrincipal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletPagPrincipal extends ServletGeniricUtil {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat df = new DecimalFormat("0.00");
       
    public ServletPagPrincipal() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

	      
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTelaCards") ) {

			try {
					DAOpagPrincipal daopagPrincipal = new DAOpagPrincipal();
					int qtyContratosCad = daopagPrincipal.QtyContratosCadastrados();
					if( qtyContratosCad > 0 ) {
				 	ModelPagPrincipal modelPagPrincipal = new ModelPagPrincipal();
					df.setRoundingMode(RoundingMode.DOWN);
					Locale localeBR = new Locale("pt","BR");
					NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
				 	/* Valores contratos Principais */
					modelPagPrincipal.setVlrTotalContratos( daopagPrincipal.getVlrTotalContratos() );
					/* Quantidades contratos Principais Ativos */
					String qtyContratosAtivos = daopagPrincipal.getQtyContratoAtivos();
					modelPagPrincipal.setQtyContratosAtivos(qtyContratosAtivos);
					/* Calculo contratos Principais Desativos para realicacao das percentagens ativas e desativas da base*/
					int contratosbase = daopagPrincipal.getQtyContrato(); // Total contratos
					// Calculo percentagem dos contratos Ativos
					Double baseAtivaPer    = 0.0;
					if( Double.valueOf(qtyContratosAtivos) > 0 && contratosbase > 0 ) 
					    baseAtivaPer    = ( Double.valueOf(qtyContratosAtivos) * 100)  / contratosbase;
					
					modelPagPrincipal.setPercentagemContratosAtivos(df.format(baseAtivaPer));
					// Calculo percentagem dos contratos Desativos
					Integer contratoDesativados =  contratosbase - Integer.valueOf(qtyContratosAtivos);
					modelPagPrincipal.setQtyContratosDesativos( Integer.toString(contratoDesativados) );
					/* Quantidades contratos Principais Desativos */
					Double baseDesativaPer = 0.0;
					if( contratoDesativados > 0 && contratosbase > 0 )
					   baseDesativaPer = ((double)  contratoDesativados * 100)  / contratosbase;
					modelPagPrincipal.setPercentagemContratosDesativos(df.format(baseDesativaPer)     );
					/* Quantidades contratos Temporarios Ativos */
					String aditivosTemporarios = daopagPrincipal.getQtyContratoTemporario();
					modelPagPrincipal.setQtyContratosAditivoTemp( aditivosTemporarios );
					// Calculo percentagem dos Ativos Temporariso em referencia ao total da base.
					Double totalAditivosAtivos = daopagPrincipal.getQtyAditivosAtivos();
					Double percentTemporarios = 0.0;
					if( Double.valueOf(aditivosTemporarios) > 0 && totalAditivosAtivos > 0 )
					    percentTemporarios  = ( Double.valueOf(aditivosTemporarios) * 100) / totalAditivosAtivos;
					modelPagPrincipal.setPercentagemContratosAditivoTempAtivos( df.format(percentTemporarios) );
					
					// Valores dos contratos e aditivos do mes atual e anterior
					modelPagPrincipal.setVlrContratosAditivoMesAtual( daopagPrincipal.VlrContratosAditivoMesAtual() );
					modelPagPrincipal.setVlrContratosAditivoMesAnterior( dinheiro.format(daopagPrincipal.VlrAditivoMesAnterior() + daopagPrincipal.VlrContratosMesAnterior()));
					
					// Valores Aditivo Trimestrais
					Double vlrContratoTrimestri = daopagPrincipal.VlrContratoTrimestri();
					Double vlrAditivoTrimestri  = daopagPrincipal.VlrAditivoTrimestri();
	                Double totalTrimestre       = vlrContratoTrimestri + vlrAditivoTrimestri;
	                int qtyAditivoTrimestri     = daopagPrincipal.QtyAditivoTrimestri();
	                int qtyContratoTrimestri    = daopagPrincipal.QtyContratoTrimestri();
	                int totalTrimestri          = ( qtyAditivoTrimestri + qtyContratoTrimestri );
	                int perContratoTrimestri    = 0;
	                if( qtyContratoTrimestri > 0 && totalTrimestri > 0 )
	                    perContratoTrimestri    = ( qtyContratoTrimestri * 100) / totalTrimestri;
	                int perAditivoTrimestri     = 0;
	                if( qtyAditivoTrimestri > 0 && totalTrimestri > 0 )
	                    perAditivoTrimestri     = ( qtyAditivoTrimestri  * 100) / totalTrimestri;
	
	                modelPagPrincipal.setVlrTotalTrimestre    ( dinheiro.format( totalTrimestre       ) );
	                modelPagPrincipal.setVlrAditivoTrimestre  ( dinheiro.format( vlrAditivoTrimestri  ) );
	                modelPagPrincipal.setVlrContratosTrimestre( dinheiro.format( vlrContratoTrimestri ) );
	                
	                modelPagPrincipal.setQtyAditivoTrimestre  ( Integer.toString( qtyAditivoTrimestri  ) );	
	                modelPagPrincipal.setQtyContratosTrimestre( Integer.toString( qtyContratoTrimestri ) );
	                modelPagPrincipal.setPerAditivoTrimestre  ( Integer.toString( perAditivoTrimestri  ) );
	                modelPagPrincipal.setPerContratosTrimestre( Integer.toString( perContratoTrimestri ) );
	                
	                
	               // qtyContratosAtivos
	                int qtyContVencemTesMeses    = daopagPrincipal.QtyContratosVencidosUltimos3Meses();
	                Double perContVencemTesMeses = 0.0;
	                if( qtyContVencemTesMeses > 0 && Integer.parseInt(qtyContratosAtivos) > 0 )
	                    perContVencemTesMeses = ( (double) qtyContVencemTesMeses * 100 ) / Integer.parseInt(qtyContratosAtivos) ;
	                
	                modelPagPrincipal.setQtyContratosVencemTesMeses( Integer.toString( qtyContVencemTesMeses ) );
	                modelPagPrincipal.setPerContratosVencemTesMeses( df.format(perContVencemTesMeses) );
	
				    Gson gson = new Gson();
				    String lista = gson.toJson(modelPagPrincipal);
				    response.getWriter().write(lista);
				}else {
				    Gson gson = new Gson();
				    String lista = gson.toJson("NAOEXISTECONTRATOCAD");
				    response.getWriter().write(lista);
					
				}
 			
				
			} catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				requestDispatcher.forward(request, response);
			}
		}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaGrafico") ) {
			try {
				
			    DAOpagPrincipal daopagPrincipal = new DAOpagPrincipal();
			    BeanDtoGraficoVlrMes beanDtoGraficoVlrMes = daopagPrincipal.montaGrafico();
			    Gson gson = new Gson();
			    String lista = gson.toJson(beanDtoGraficoVlrMes);
			    response.getWriter().write(lista);
			    
			} catch (SQLException e) {
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				requestDispatcher.forward(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
