<%@tag import="br.com.portal.model.ModelRecursoAditivo"%>
<%@tag import="br.com.portal.model.ModeliSistemaOperacional"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanSO" class="br.com.portal.dao.DAOSistemaOperacional" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModeliSistemaOperacional> listaSOs = beanSO.getListaSistemaOperacional();
   ModelRecursoAditivo modelRecursoAditivo = (ModelRecursoAditivo) request.getAttribute("modelRecursoAditivo");
   
//  modelLogin
   
   Long idSOsRecup = 0L;
   
   if( modelRecursoAditivo != null ) {
	   if( modelRecursoAditivo.getId_so() != null ) idSOsRecup = modelRecursoAditivo.getId_so();
   }

   for (ModeliSistemaOperacional listaSO : listaSOs) {
	 Long idSO   = listaSO.getId_so();
	 String sistemaOperacional  = listaSO.getSistema_operacional();
	 if( idSOsRecup == idSO )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idSO + " " + selected + ">" + sistemaOperacional + "</option>");
}
   

%>
