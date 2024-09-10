<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.portal.model.dashboard.ModalClientesInfo"%> 


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table class="table">
	  <thead>
	    <tr>
	      <th scope="col">ID Contrato        </th>
	      <th scope="col">ID Cliente         </th>
	      <th scope="col">Razão Social       </th>
	      <th scope="col">Nome Fantasia      </th>
	      <th scope="col">Alias              </th>
	      <th scope="col">PEP                </th>
	      <th scope="col">CNPJ               </th>
	      <th scope="col">Data Criação       </th>
	      <th scope="col">Login Cadastro     </th>
	      <th scope="col">HUB SPOT           </th>
	      <th scope="col">Total Aditivos     </th>
	      <th scope="col">Total Instâncias   </th>
	      <th scope="col">Histórico Contrato </th>
	      <th scope="col">Contrato Antigo    </th>
	      <th scope="col">Status             </th>
	      <th scope="col">Qty Usuário        </th>
	      <th scope="col">Nuvem              </th>
	      <th scope="col">Site               </th>
	      <th scope="col">Serviço Contratado </th>
	      <th scope="col">Moeda              </th>
	      <th scope="col">Fase Contrato      </th>
	      <th scope="col">Ciclo Update       </th>
	      <th scope="col">Início Vigência    </th>
	      <th scope="col">Final Vigência     </th>
	      <th scope="col">Data Desativação   </th>
	      <th scope="col">Dias Vigência      </th>
	      <th scope="col">Meses Vigência     </th>
	      <th scope="col">Vigência           </th>
	    </tr>
	  </thead>
      <tbody>
         <%
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=relContratoCliente.xls");
            List<ModalClientesInfo> listCliInfo = (List<ModalClientesInfo>) session.getAttribute("listCliInfo"); 
            if( listCliInfo != null ) {
                for( ModalClientesInfo list: listCliInfo ){
          %>          
          <tr>
            <td><%=list.getId_contrato()%>            </td>
            <td><%=list.getId_cliente()%>             </td>
            <td><%=list.getRazao_social()%>           </td>
            <td><%=list.getNome_fantasia()%>          </td>
            <td><%=list.getAlias()%>                  </td>
            <td><%=list.getPep()%>                    </td>
            <td><%=list.getCnpj()%>                   </td>
            <td><%=list.getDt_criacao()%>             </td>
            <td><%=list.getLogin_cadastro()%>         </td>
            <td><%=list.getId_hub_spot()%>            </td>
            <td><%=list.getTota_aditivos()%>          </td>
            <td><%=list.getTotal_instancias()%>       </td>
            <td><%=list.getHistorico_contrato()%>     </td>
            <td><%=list.getContrato_antigo()%>        </td>
            <td><%=list.getStatus()%>                 </td>            
            <td><%=list.getQty_usuario_contratada()%> </td>
            <td><%=list.getNuvem()%>                  </td>
            <td><%=list.getSite()%>                   </td>
            <td><%=list.getServico_contratado()%>     </td>
            <td><%=list.getMoeda()%>                  </td>
            <td><%=list.getFase_contrato()%>          </td>
            <td><%=list.getCiclo_update()%>           </td>
            <td><%=list.getDt_inicio_vigencia()%>     </td>
            <td><%=list.getDt_final_vigencia()%>      </td>
            <td><%=list.getDt_desativacao()%>         </td>
            <td><%=list.getQty_dias_vigencia()%>      </td>
            <td><%=list.getQty_meses_vigencia()%>     </td>
            <td><%=list.getVigencia()%>               </td>
          </tr>
          <%    }
            }%>
      </tbody>
  </table>

</body>
</html>