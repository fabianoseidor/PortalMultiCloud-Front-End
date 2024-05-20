<%@tag import="br.com.portal.model.ModelLogin"%>
<%@tag import="br.com.portal.model.ModelCargo"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOCargo"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanCargo" class="br.com.portal.dao.DAOCargo" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelCargo> cargos = beanCargo.getListaCargo();
   ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
   Long id_recuperado = 0L;
   
   if( modelLogin != null ) {
	   if( modelLogin.getId_cargo() != null ) id_recuperado = modelLogin.getId_cargo();
   }

   for (br.com.portal.model.ModelCargo liscargo : cargos) {
	 Long id_cargo = liscargo.getId_cargo();
	 String cargo  = liscargo.getCargo();
	 if( id_recuperado == id_cargo )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_cargo + " " + selected + ">" + cargo + "</option>");
}
   

%>
