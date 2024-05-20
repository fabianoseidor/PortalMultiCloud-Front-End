<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="br.com.portal.model.ModelTipoDisco"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanTipoDisco" class="br.com.portal.dao.DAOTipoDisco" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelTipoDisco> tipoDiscos = beanTipoDisco.getListaTipoDisco();
   ModelRecursoContrato modelRecursoContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");
   
//  modelLogin
   
   Long idtipoDiscosRecup = 0L;
   
   if( modelRecursoContrato != null ) {
	   if( modelRecursoContrato.getId_tipo_disco() != null ) idtipoDiscosRecup = modelRecursoContrato.getId_tipo_disco();
   }

   for (ModelTipoDisco listtipoDisco : tipoDiscos) {
	 Long idTipoDisco     = listtipoDisco.getId_tipo_disco();
	 String tipoDisco  = listtipoDisco.getTipo_disco();
	 if( idtipoDiscosRecup == idTipoDisco )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idTipoDisco + " " + selected + ">" + tipoDisco + "</option>");
}
   

%>
