<%@tag import="br.com.portal.model.ModelCicloUpdate"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanCicloUpdate" class="br.com.portal.dao.DAOCicloUpdate" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelCicloUpdate> modelCicloUpdates = beanCicloUpdate.listaCicloUpdate();
   ModelAditivo modelAditivo = (ModelAditivo) request.getAttribute("modelAditivo");
   Long idCicloUpdateRecuperado = 0L;
   
   if( modelAditivo != null ) {
	   if( modelAditivo.getId_ciclo_update() != null )
	       idCicloUpdateRecuperado = modelAditivo.getId_ciclo_update();
   }

   for (ModelCicloUpdate cicloUpdate : modelCicloUpdates) {
	 Long idCicloUpdate = cicloUpdate.getId_ciclo_update();
	 String descricao   = cicloUpdate.getDescricao();

	 if( idCicloUpdateRecuperado == idCicloUpdate )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idCicloUpdate + " " + selected + ">" + descricao + "</option>");
}
   

%>
