<%@tag import="br.com.portal.model.ModelNuvemSite"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanNuvemSite" class="br.com.portal.dao.DAONuvemSite" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelNuvemSite> modelNuvemSites = beanNuvemSite.listaNuvemSelect();

   for (ModelNuvemSite NuvemSite : modelNuvemSites) {
	 Long idNuvem = NuvemSite.getId_nuvem();
	 String Nuvem = NuvemSite.getMome_parceiro();
	 
     out.println("<option value=" + idNuvem + " " + selected + ">" + Nuvem + "</option>");
}
   

%>
