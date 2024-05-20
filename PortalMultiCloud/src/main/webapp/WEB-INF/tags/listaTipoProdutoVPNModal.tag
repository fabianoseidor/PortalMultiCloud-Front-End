<%@tag import="br.com.portal.model.ModelTipoProduto"%>
<%@tag import="br.com.portal.model.ModelListAditivoProduto"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanProduto" class="br.com.portal.dao.DAOProduto" ></jsp:useBean>

<%
   String selected = "";
   List<ModelTipoProduto> tipoProdutos = beanProduto.getListaTipoProdutoID( 2L );
   ModelListAditivoProduto listAditivoProduto = (ModelListAditivoProduto) request.getAttribute("modelListAditivoProduto");

   for (ModelTipoProduto tipoProduto : tipoProdutos) {
	 Long id_produto     = tipoProduto.getId_tipo_produto();
	 String desc_produto = tipoProduto.getDesc_tipo_produto();
	 
     out.println("<option value=" + id_produto + " " + selected + ">" + desc_produto + "</option>");
}
   

%>
