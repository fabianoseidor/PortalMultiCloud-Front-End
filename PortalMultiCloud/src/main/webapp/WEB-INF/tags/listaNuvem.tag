<%@tag import="br.com.portal.model.ModelNuvem"%>
<%@tag import="br.com.portal.model.ModelSite"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanNuvem" class="br.com.portal.dao.DAONuvem" ></jsp:useBean>

<%
   String selected = "";
   List<ModelNuvem> modelNuvemSites = beanNuvem.getListaNuvem();
   ModelSite modelSite = (ModelSite) request.getAttribute("listSite");

   Long idNuvemCorrente    = 0L;
   
   if( modelSite != null ){
	   if(modelSite.getId_nuvem() != null) 
		   idNuvemCorrente = modelSite.getId_nuvem();
   }

   for (ModelNuvem nuvem : modelNuvemSites) {
	 Long idNuvem = nuvem.getId_nuvem();
	 String Nuvem = nuvem.getMome_parceiro();

	 if( idNuvemCorrente == idNuvem )
  	     selected = "selected";
	 else selected = "";

     out.println("<option value=" + idNuvem + " " + selected + ">" + Nuvem + "</option>");
   }
   

%>
