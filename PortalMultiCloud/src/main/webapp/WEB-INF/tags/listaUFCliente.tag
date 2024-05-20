<%@tag import="br.com.portal.model.ModelCliente"%>
<%@tag body-content="empty" %>
<jsp:useBean id="beanUF" class="br.com.portal.dao.DAOUnidadeFederativaRepository" ></jsp:useBean>

<% 
   String selected = "";
   java.util.List<br.com.portal.model.ModelUnidadeFederativa> unidadeFederativas = beanUF.getListaUnidadeFederativa();
   ModelCliente modelUFCli = (ModelCliente) request.getAttribute("modelClienteManu");
   String uf = "";

   if( modelUFCli != null ) {
	   if( modelUFCli.getEstado() != null )
	   uf = modelUFCli.getEstado();
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