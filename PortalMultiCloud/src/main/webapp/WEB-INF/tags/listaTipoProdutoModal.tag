<%@tag import="br.com.portal.model.ModelTipoProduto"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanProduto" class="br.com.portal.dao.DAOProduto" ></jsp:useBean>

<%
   String selected = "";
   List<ModelTipoProduto> modelTipoProdutos = beanProduto.getListaTipoProdutoDR();
   ModelAditivo modelAditivo = (ModelAditivo) request.getAttribute("modelAditivoModal");
   Long idTipoProdutoRecuperado = 0L;
   
   if( modelAditivo != null ) {
	   if(modelAditivo.getId_tipo_produto() != null )
		   idTipoProdutoRecuperado = modelAditivo.getId_tipo_produto();
   }

   for (ModelTipoProduto modelTipoProduto : modelTipoProdutos) {
	 Long id_tipo_produto      = modelTipoProduto.getId_tipo_produto();
	 String desc_tipo_produto = modelTipoProduto.getDesc_tipo_produto();

	 if( idTipoProdutoRecuperado == id_tipo_produto )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_tipo_produto + " " + selected + ">" + desc_tipo_produto + "</option>");
}
   

%>
