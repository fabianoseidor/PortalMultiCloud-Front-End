<%@tag import="br.com.portal.model.ModelRecursoAditivo"%>
<%@tag import="br.com.portal.model.ModelRetencaoBackup"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanRetBackup" class="br.com.portal.dao.DAORetencaoBackup" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelRetencaoBackup> retBackups = beanRetBackup.getListaRetencaoBackup();
   ModelRecursoAditivo modelRecursoAditivo = (ModelRecursoAditivo) request.getAttribute("modelRecursoAditivo");
   
//  modelLogin
   
   Long idRetBackupRecup = 0L;
   
   if( modelRecursoAditivo != null ) {
	   if( modelRecursoAditivo.getId_retencao_backup() != null ) idRetBackupRecup = modelRecursoAditivo.getId_retencao_backup();
   }

   for (ModelRetencaoBackup listRetBackup : retBackups) {
	 Long idRetBackup = listRetBackup.getId_retencao_backup();
	 String retencaoBackup  = listRetBackup.getRetencao_backup();
	 if( idRetBackupRecup == idRetBackup )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idRetBackup + " " + selected + ">" + retencaoBackup + "</option>");
}
   

%>
