<%@tag import="br.com.portal.model.ModelNuvemSite"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanNuvemSite" class="br.com.portal.dao.DAONuvemSite" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelNuvemSite> modelNuvemSites = beanNuvemSite.listaNuvemSelect();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idNuvemRecuperado = 0L;
   
   if( modelContrato != null ){
	   if(modelContrato.getId_nuvem() != null) 
		   idNuvemRecuperado = modelContrato.getId_nuvem();
   }
	   

   for (ModelNuvemSite NuvemSite : modelNuvemSites) {
	 Long idNuvem = NuvemSite.getId_nuvem();
	 String Nuvem = NuvemSite.getMome_parceiro();
//	 String Site  = (NuvemSite.getNome() != null && !NuvemSite.getNome().isEmpty() ? NuvemSite.getNome(): " - ");		 
//	 String Site  = NuvemSite.getNome();
	 if( idNuvemRecuperado == idNuvem )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idNuvem + " " + selected + ">" + Nuvem + "</option>");
}
   

%>
