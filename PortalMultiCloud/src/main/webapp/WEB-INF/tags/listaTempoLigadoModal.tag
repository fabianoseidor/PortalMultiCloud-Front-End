<%@tag import="br.com.portal.model.ModelTempoLigado"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanRecusoAditivo" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   String selected = "";
   List<ModelTempoLigado> modelTempoLigados = beanRecusoAditivo.listaTempoLigado();
   ModelAditivo modelAditivo = (ModelAditivo) request.getAttribute("modelAditivoModal");
   Long IdTempoLigadoRecuperado = 0L;
   
   if( modelAditivo != null ) {
	   if(modelAditivo.getId_tempo_ligado() != null )
		   IdTempoLigadoRecuperado = modelAditivo.getId_tempo_ligado();
   }

   for (ModelTempoLigado modelTL : modelTempoLigados) {
	 Long IdTempoLigado     = modelTL.getId_tempo_ligado();
	 String descTempoLigado = modelTL.getDesc_tempo_ligado();

	 if( IdTempoLigadoRecuperado == IdTempoLigado )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + IdTempoLigado + " " + selected + ">" + descTempoLigado + "</option>");
}
   

%>
