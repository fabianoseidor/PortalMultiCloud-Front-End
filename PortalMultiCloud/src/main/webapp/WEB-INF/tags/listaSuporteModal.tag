<%@tag import="br.com.portal.model.ModelSuporte"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanRecusoAditivo" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   String selected = "";
   List<ModelSuporte> modelSuportes = beanRecusoAditivo.listaSuporte();
   ModelAditivo modelAditivo = (ModelAditivo) request.getAttribute("modelAditivoModal");
   Long idSuporteRecuperado = 0L;
   
   if( modelAditivo != null ) {
	   if(modelAditivo.getId_suporte() != null )
		   idSuporteRecuperado = modelAditivo.getId_suporte();
   }

   for (ModelSuporte modelSuporte : modelSuportes) {
	 Long idSuporte     = modelSuporte.getId_suporte();
	 String descSuporte = modelSuporte.getDesc_suporte();

	 if( idSuporteRecuperado == idSuporte )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idSuporte + " " + selected + ">" + descSuporte + "</option>");
}
   

%>
