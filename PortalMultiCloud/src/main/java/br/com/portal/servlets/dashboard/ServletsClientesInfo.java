package br.com.portal.servlets.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.portal.dao.dashboard.DAOClientesInfo;
import br.com.portal.model.dashboard.ModalClientesInfo;
// import br.com.portal.dao.DAOUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ServletsClientesInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletsClientesInfo() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String dtInicio = request.getParameter( "dataInicio"        );
			String dtFim    = request.getParameter( "dataFinal"         );
			String rdButon  = request.getParameter( "inlineRadioOptions");


			String[] dtInicioVet = dtInicio.split("/");
			String[] dtFimVet    = dtFim.split("/");

			String dtInicioBd = dtInicioVet[2] + "-" + dtInicioVet[1] + "-" + dtInicioVet[0];
			String dtFimBd    = dtFimVet[2] + "-" + dtFimVet[1] + "-" + dtFimVet[0];

			DAOClientesInfo daoClientesInfo = new DAOClientesInfo();
			List<ModalClientesInfo> listCliInfo = new ArrayList<ModalClientesInfo>();
			if(      rdButon != null && !rdButon.isEmpty() && rdButon.equalsIgnoreCase("Ativo"        ) ) listCliInfo = daoClientesInfo.getListaClientesInfo         (dtInicioBd, dtFimBd);
			else if( rdButon != null && !rdButon.isEmpty() && rdButon.equalsIgnoreCase("Desativo"     ) ) listCliInfo = daoClientesInfo.getListaClientesInfoDesativos(dtInicioBd, dtFimBd);
			else if( rdButon != null && !rdButon.isEmpty() && rdButon.equalsIgnoreCase("AtivoDesativo") ) listCliInfo = daoClientesInfo.getListaClientesInfoBoth     (dtInicioBd, dtFimBd);

			request.setAttribute("dataInicioView", dtInicio   );
			request.setAttribute("dataFinalView" , dtFim      );
//			request.setAttribute("rdButon"       , rdButon    );
//			request.setAttribute("listCliInfo"   , listCliInfo);
			request.getSession().setAttribute( "listCliInfo", listCliInfo );
			request.getSession().setAttribute( "rdButon", rdButon );
			request.getRequestDispatcher("dashboard/RelClientesInfo.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			requestDispatcher.forward(request, response);
		}

	
	}

}
