<%@tag import="br.com.portal.model.ModelLogin"%>
<%@tag body-content="empty" %>
<jsp:useBean id="beanUF" class="br.com.portal.dao.DAOUnidadeFederativaRepository" ></jsp:useBean>

<% 
   String selected = "";
   java.util.List<br.com.portal.model.ModelUnidadeFederativa> unidadeFederativas = beanUF.getListaUnidadeFederativa();
   ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
   String uf = "";

   if( modelLogin != null ) {
	   if( modelLogin.getEstado() != null )
	   uf = modelLogin.getEstado();
   }
   
   for (br.com.portal.model.ModelUnidadeFederativa federativa : unidadeFederativas) {
	   String Sigla = federativa.getSigla();
	   String Unidade_federativa = federativa.getUnidade_federativa();
	   
	   if( uf.equalsIgnoreCase( Sigla ))
		   selected = "selected";
	   else
		   selected = "";   		   

	   
        out.println("<option value=" + Sigla + " " + selected + ">" + Unidade_federativa + "</option>");
   }
%>