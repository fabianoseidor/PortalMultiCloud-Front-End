<%@tag import="br.com.portal.model.ModelFamiliaFlavors"%>
<%@tag import="br.com.portal.model.ModelRecursoAditivo"%>
<%@tag import="java.util.List"%>
<%@tag import="java.util.ArrayList"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanFamilia" class="br.com.portal.dao.DAOFamiliaFlavors" ></jsp:useBean>

<%
   String selected = "";
   ModelRecursoAditivo modelRecursoAditivo = (ModelRecursoAditivo) request.getAttribute("modelRecursoAditivo");
   List<ModelFamiliaFlavors> familiaFlavorses = new ArrayList<ModelFamiliaFlavors>();
   
   Long idNuvemFamilia = 0L;
   Long idFamiliaRec   = 0L;

   if( modelRecursoAditivo != null ){
	   
	   if( modelRecursoAditivo.getId_nuvem() != null ){ 
		      idNuvemFamilia   =  modelRecursoAditivo.getId_nuvem(); 
		      familiaFlavorses = beanFamilia.listaFamiliaFlavors( idNuvemFamilia );
		      if ( modelRecursoAditivo.getId_familia_flavors() != null )
		           idFamiliaRec = modelRecursoAditivo.getId_familia_flavors();
	   }
   }

   for (ModelFamiliaFlavors familiaFlavors : familiaFlavorses) {
	 Long idfamilia = familiaFlavors.getId_familia_flavors();
	 String familia = familiaFlavors.getFamilia();
	 if( idFamiliaRec == idfamilia )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idfamilia + " " + selected + ">" + familia + "</option>");
}
   

%>
