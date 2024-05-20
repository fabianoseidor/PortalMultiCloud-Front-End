<%@tag import="br.com.portal.model.ModelTipoRascunho"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanTipoRasc" class="br.com.portal.dao.DAORascunho" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelTipoRascunho> modeTipoRascunho = beanTipoRasc.listaTipoRascunho();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idtipoRascRecuperado = 0L;

   if( modelContrato != null ) {
	   if( modelContrato.getId_fase_contrato() != null )
		   idtipoRascRecuperado = modelContrato.getId_rascunho();
   }
	   
   for (ModelTipoRascunho tipoRascunho : modeTipoRascunho) {
	 Long idTRascunho = tipoRascunho.getId_tipo_rascunho();
	 String rascunho = tipoRascunho.getRascunho();
	 
	 if( idtipoRascRecuperado == idTRascunho )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idTRascunho + " " + selected + ">" + rascunho + "</option>");
}
   

%>
