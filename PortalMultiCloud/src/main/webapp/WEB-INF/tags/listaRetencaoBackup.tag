<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="br.com.portal.model.ModelRetencaoBackup"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanRetBackup" class="br.com.portal.dao.DAORetencaoBackup" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelRetencaoBackup> retBackups = beanRetBackup.getListaRetencaoBackup();
   ModelRecursoContrato modelRecursoContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");
   
//  modelLogin
   
   Long idRetBackupRecup = 0L;
   
   if( modelRecursoContrato != null ) {
	   if( modelRecursoContrato.getId_retencao_backup() != null ) idRetBackupRecup = modelRecursoContrato.getId_retencao_backup();
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
