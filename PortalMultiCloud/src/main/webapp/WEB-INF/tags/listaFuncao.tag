<%@tag import="br.com.portal.model.ModelFuncao"%>
<%@tag import="br.com.portal.model.ModelTelefone"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanfuncao" class="br.com.portal.dao.DAOFuncao" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelFuncao> modelFuncoes = beanfuncao.getListaFuncaContato();
   ModelTelefone modelTelefone = (ModelTelefone) request.getAttribute("modelTelefone");
   Long idFoneRecuperado = 0L;
   
   if( modelTelefone != null ) {
	   if(  modelTelefone.getId_funcao() != null )
	        idFoneRecuperado = modelTelefone.getId_funcao();
   }

   for (ModelFuncao modelFuncao : modelFuncoes) {
	 Long idFone   = modelFuncao.getId_funcao();
	 String funcao = modelFuncao.getFuncao();

	 if( idFoneRecuperado == idFone )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idFone + " " + selected + ">" + funcao + "</option>");
}
   

%>
