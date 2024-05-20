<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Optional"%>

<%@page import="br.com.portal.modelPerfil.ModelPerfilLogado"%>

<nav class="pcoded-navbar">
    
    <div class="pcoded-inner-navbar main-menu">
        <div class="">
            <div class="main-menu-header">
                <!--  
                <img class="img-80 img-radius" src="<%= request.getContextPath() %>/imagens/FotoCapa.jpg" alt="User-Profile-Image">
                -->
                 
                <img class="img-80 img-radius" src=<% 
                
                	String fotoUser = (String) session.getAttribute("fotoUsuario"); 
                    if ( fotoUser != null ) {
						  out.print("\"" + fotoUser + "\"");
		            }else{
		            	String avatar = request.getContextPath() + "/imagens/perfil_avatar.jpg";
		            	// out.print("\"./imagens/perfil_avatar.jpg\"");
		            	out.print( avatar );
		            }
                %> alt="User-Profile-Image">
				 <!--  
                 <img class="img-80 img-radius" src="<%= session.getAttribute("fotoUsuario") %>" alt="User-Profile-Image">
                 -->
                 <div class="user-details">
                    <span id="more-details"><%= session.getAttribute("nomeUsuario") %><i class="fa fa-caret-down"></i></span>
                </div>
            </div>

            <div class="main-menu-content">
                <ul>
                    <li class="more-details">
                       <!--   <a href="user-profile.html"><i class="ti-user"></i>View Profile</a> -->
                       <!--   <a href="#!"><i class="ti-settings"></i>Settings</a>                -->
                        <a href="<%= request.getContextPath() %>/ServletLogin?acao=logout"><i class="ti-layout-sidebar-left"></i>Sair</a>
                    </li>
                </ul>
            </div>
        </div>
        

        <% 
         List<ModelPerfilLogado> perfilUserLogados = (List<ModelPerfilLogado>) session.getAttribute("perfilUserLogados"); 
         if( perfilUserLogados != null ) {
            Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Configuração".equals(x.getNome_secao()) ).findFirst();
            if( perfilLogado.isPresent() ) { %>	
		        <div class="pcoded-navigation-label" data-i18n="nav.category.forms">Configuração</div>
		        <ul class="pcoded-item pcoded-left-item">
		            <li class="pcoded-hasmenu">
		            
		                <a href="javascript:void(0)" class="waves-effect waves-dark">
		                    <span class="pcoded-micon"><i class="ti-layout-grid2-alt"></i></span>
		                    <span class="pcoded-mtext"  data-i18n="nav.basic-components.main">Ambiente</span>
		                    <span class="pcoded-mcaret"></span>
		                </a>
	        
		                <ul class="pcoded-submenu">
			            <% 
			           // List<ModelPerfilLogado> perfilUserLogados = (List<ModelPerfilLogado>) session.getAttribute("perfilUserLogados"); 
			               perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoAmbiente".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                    <li class=" "> <!-- manutencaoAmbiente -->
			                        <a href="<%= request.getContextPath() %>/ServletsAmbiente?acao=MontaAmbienteInicial" class="waves-effect waves-dark">
			                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                            <span class="pcoded-mtext" data-i18n="nav.basic-components.alert">Ambiente</span>
			                            <span class="pcoded-mcaret"></span>
			                        </a>
			                    </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoTipoDisco".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                    <li class=" "> <!-- manutencaoTipoDisco -->
			                        <a href="<%= request.getContextPath() %>/ServletsTipoDisco?acao=MontaTipoDiscoInicial" class="waves-effect waves-dark">
			                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                            <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Tipo Disco</span>
			                            <span class="pcoded-mcaret"></span>
			                        </a>
			                    </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoSistemaOperacional".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>    
			                    <li class=" "> <!-- manutencaoSistemaOperacional -->
			                        <a href="<%= request.getContextPath() %>/ServletsSistemaOperacional?acao=MontaSistemaOperacionalInicial" class="waves-effect waves-dark">
			                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                            <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Sistema Operacional</span>
			                            <span class="pcoded-mcaret"></span>
			                        </a>
			                    </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoTipoServico".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>   
			                    <li class=" "> <!-- manutencaoTipoServico -->
			                        <a href="<%= request.getContextPath() %>/ServletsTipoServico?acao=MontaTipoServicoInicial" class="waves-effect waves-dark">
			                            <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                            <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Tipo Servico</span>
			                            <span class="pcoded-mcaret"></span>
			                        </a>
			                    </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoServicoContratado".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>  
			                   <li class=" "> <!-- manutencaoServicoContratado -->
			                       <a href="<%= request.getContextPath() %>/ServletsServicoContratado?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Serviço Contratado</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoCicloUpdate".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoCicloUpdate -->
			                       <a href="<%= request.getContextPath() %>/ServletsCicloUpdate?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Ciclo Update</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoTipoAditivo".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoTipoAditivo -->
			                       <a href="<%= request.getContextPath() %>/ServletsTipoAditivo?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Tipo Aditivo</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoFaseContrato".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoFaseContrato -->
			                       <a href="<%= request.getContextPath() %>/ServletsFaseContrato?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Fase Contrato</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoRetencaoBackup".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoRetencaoBackup -->
			                       <a href="<%= request.getContextPath() %>/ServletsRetencaoBackup?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Retenção Backup</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoPorteEmpresa".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
	  		                   <li class=" "> <!-- manutencaoPorteEmpresa -->
			                       <a href="<%= request.getContextPath() %>/ServletsPorteEmpresa?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Porte Empresa</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoStatusRecurso".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoStatusRecurso -->
			                       <a href="<%= request.getContextPath() %>/ServletsStatusRecurso?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Status Recurso</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoStatusContrato".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoStatusContrato -->
			                       <a href="<%= request.getContextPath() %>/ServletsStatusContrato?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Status Contrato</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoStatusCliente".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoStatusCliente -->
			                       <a href="<%= request.getContextPath() %>/ServletsStatusCliente?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Status Cliente</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoProduto".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoProduto -->
			                       <a href="<%= request.getContextPath() %>/ServletsProduto?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Produto</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoNuvem".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoNuvem -->
			                       <a href="<%= request.getContextPath() %>/ServletsNuvem?acao=MontaTelaInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Nuvem</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoSite".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoSite -->
			                       <a href="<%= request.getContextPath() %>/ServletsSite" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Site</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
		                   perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoFamiliaFlavors".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
			                   <li class=" "> <!-- manutencaoFamiliaFlavors -->
			                       <a href="<%= request.getContextPath() %>/ServletsFamiliaFlavors" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Familia Flavors</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
                           perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoComercial".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
		                	   <li class=" "> <!-- manutencaoNuvem -->
			                       <a href="<%= request.getContextPath() %>/ServletsComercial?acao=MontaComercialInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Comercial</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
                           perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoSuporteB1".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
		                	   <li class=" "> <!-- manutencaoNuvem -->
			                       <a href="<%= request.getContextPath() %>/ServletsSuporteB1?acao=MontaSuporteB1Inicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Suporte B1</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% }
			               perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoTipoRascunho".equals(x.getDesc_pagina() ) ).findFirst();
			               if( perfilLogado.isPresent() ) { %>
		                	   <li class=" "> <!-- manutencaoNuvem -->
			                       <a href="<%= request.getContextPath() %>/ServletsTipoRascunho?acao=MontaTipoRascunhoInicial" class="waves-effect waves-dark">
			                           <span class="pcoded-micon"><i class="ti-angle-right"></i></span>
			                           <span class="pcoded-mtext" data-i18n="nav.basic-components.breadcrumbs">Tipo Rascunho</span>
			                           <span class="pcoded-mcaret"></span>
			                       </a>
			                   </li>
		                <% } %>
		                
		                </ul>
		            </li>
		
		        </ul>
        
         <% } 
		} %> <!-- Fim da Secao "Cadastro" -->
        
    </div>
</nav>