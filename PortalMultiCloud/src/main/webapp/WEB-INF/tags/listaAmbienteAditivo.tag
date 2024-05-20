<%@tag import="br.com.portal.model.ModelRecursoAditivo"%>
<%@tag import="br.com.portal.model.ModelAmbiente"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOAmbiente"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanAmbiente" class="br.com.portal.dao.DAOAmbiente" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelAmbiente> listaAmbientes = beanAmbiente.getListaAmbiente();
   ModelRecursoAditivo modelRecursoAditivo = (ModelRecursoAditivo) request.getAttribute("modelRecursoAditivo");
   
//  modelLogin
   
   Long idAmbienteRecup = 0L;
   
   if( modelRecursoAditivo != null ) {
	   if( modelRecursoAditivo.getId_ambiente() != null ) idAmbienteRecup = modelRecursoAditivo.getId_ambiente();
   }

   for (ModelAmbiente listaAmbiente : listaAmbientes) {
	 Long idAmbiente = listaAmbiente.getId_ambiente();
	 String ambiente = listaAmbiente.getAmbiente();
	 if( idAmbienteRecup == idAmbiente )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idAmbiente + " " + selected + ">" + ambiente + "</option>");
}
   

%>
