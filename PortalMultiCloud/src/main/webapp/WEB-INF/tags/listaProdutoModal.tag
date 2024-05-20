<%@tag import="br.com.portal.model.ModelProduto"%>
<%@tag import="br.com.portal.model.ModelListAditivoProduto"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanProduto" class="br.com.portal.dao.DAOProduto" ></jsp:useBean>

<%
   String selected = "";
   List<ModelProduto> modelProdutos = beanProduto.getListaProdutoDR();
   ModelListAditivoProduto listAditivoProduto = (ModelListAditivoProduto) request.getAttribute("modelListAditivoProduto");
   Long idTipoProdutoRecuperado = 0L;
   
   if( listAditivoProduto != null ) {
	   if(listAditivoProduto.getId_produto_contratado() != null )
		   idTipoProdutoRecuperado = listAditivoProduto.getId_produto_contratado();
   }

   for (ModelProduto modelProduto : modelProdutos) {
	 Long id_produto     = modelProduto.getId_produto();
	 String desc_produto = modelProduto.getProduto();

	 if( idTipoProdutoRecuperado == id_produto )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_produto + " " + selected + ">" + desc_produto + "</option>");
}
   

%>
