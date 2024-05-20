<%@tag import="br.com.portal.model.ModelFamiliaFlavors"%>
<%@tag import="br.com.portal.model.ModelRecursoContrato"%>
<%@tag import="java.util.List"%>
<%@tag import="java.util.ArrayList"%>
<%@tag body-content="empty" %>


<jsp:useBean id="beanFamilia" class="br.com.portal.dao.DAOFamiliaFlavors" ></jsp:useBean>
<jsp:useBean id="beanContrato" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>


<%
   String selected = "";
   ModelRecursoContrato modelContrato = (ModelRecursoContrato) request.getAttribute("modelRecursoContrato");
   List<ModelFamiliaFlavors> familiaFlavorses = new ArrayList<ModelFamiliaFlavors>();
   
   Long idNuvemFamilia = 0L;
   Long idFamiliaRec   = 0L;

   if( modelContrato != null ){
	   
	   if( modelContrato.getId_nuvem() != null ){ 
		      idNuvemFamilia   =  modelContrato.getId_nuvem(); //beanContrato.getNuvemRecuso( modelContrato.getId_recurso() );
		      familiaFlavorses = beanFamilia.listaFamiliaFlavors( idNuvemFamilia );
		      if ( modelContrato.getId_familia_flavors() != null )
		           idFamiliaRec = modelContrato.getId_familia_flavors();
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
