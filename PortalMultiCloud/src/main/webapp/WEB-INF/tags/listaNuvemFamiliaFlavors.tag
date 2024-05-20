<%@tag import="br.com.portal.model.ModelNuvem"%>
<%@tag import="br.com.portal.model.ModelFamiliaFlavors"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanNuvem" class="br.com.portal.dao.DAONuvem" ></jsp:useBean>

<%
   String selected = "";
   List<ModelNuvem> modelNuvemSites = beanNuvem.getListaNuvem();
   ModelFamiliaFlavors modelFF = (ModelFamiliaFlavors) request.getAttribute("listFamiliaFlavors");

   Long idNuvemCorrente    = 0L;
   
   if( modelFF != null ){
	   if(modelFF.getId_nuvem() != null) 
		   idNuvemCorrente = modelFF.getId_nuvem();
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
