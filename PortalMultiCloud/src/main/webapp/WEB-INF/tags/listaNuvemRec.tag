<%@tag import="br.com.portal.model.ModelNuvemSite"%>
<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanNuvemSite" class="br.com.portal.dao.DAONuvemSite" ></jsp:useBean>
<jsp:useBean id="beanContrato" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>


<%
   String selected = "";
   java.util.List<ModelNuvemSite> modelNuvemSites = beanNuvemSite.listaNuvemSelect();
   ModelRecursoContrato modelContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");

   Long idNuvemFamilia    = 0L;

   if( modelContrato != null ){
	   if(modelContrato.getId_familia_flavors() != null) 
	      idNuvemFamilia = beanContrato.getNuvemRecuso( modelContrato.getId_recurso() );
   }

   for (ModelNuvemSite NuvemSite : modelNuvemSites) {
	 Long idNuvem = NuvemSite.getId_nuvem();
	 String Nuvem = NuvemSite.getMome_parceiro();
	 if( idNuvemFamilia == idNuvem )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idNuvem + " " + selected + ">" + Nuvem + "</option>");
}
   

%>
