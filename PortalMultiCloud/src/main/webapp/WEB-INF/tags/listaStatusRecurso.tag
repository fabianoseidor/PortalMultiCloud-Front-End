<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="br.com.portal.model.ModelStatusRecurso"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanStatusRec" class="br.com.portal.dao.DAOStatusRecurso" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelStatusRecurso> StatusRecusos = beanStatusRec.getListaStatus();
   ModelRecursoContrato modelRecursoContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");
   
//  modelLogin
   
   Long idStatusRecup = 0L;
   
   if( modelRecursoContrato != null ) {
	   if( modelRecursoContrato.getId_status_recurso() != null ) idStatusRecup = modelRecursoContrato.getId_status_recurso();
   }

   for (br.com.portal.model.ModelStatusRecurso lisStatus : StatusRecusos) {
	 Long idStatusRecurso = lisStatus.getId_status_recurso();
	 String statusRecurso  = lisStatus.getStatus_recurso();
	 if( idStatusRecup == idStatusRecurso )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idStatusRecurso + " " + selected + ">" + statusRecurso + "</option>");
}
   

%>
