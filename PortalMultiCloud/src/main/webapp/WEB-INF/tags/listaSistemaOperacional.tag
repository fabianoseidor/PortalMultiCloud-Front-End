<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="br.com.portal.model.ModeliSistemaOperacional"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanSO" class="br.com.portal.dao.DAOSistemaOperacional" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModeliSistemaOperacional> listaSOs = beanSO.getListaSistemaOperacional();
   ModelRecursoContrato modelRecursoContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");
   
//  modelLogin
   
   Long idSOsRecup = 0L;
   
   if( modelRecursoContrato != null ) {
	   if( modelRecursoContrato.getId_so() != null ) idSOsRecup = modelRecursoContrato.getId_so();
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
