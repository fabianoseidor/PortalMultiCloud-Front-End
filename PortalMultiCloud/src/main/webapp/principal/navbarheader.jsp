<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
   <style>
 
.mylogo  {
    max-width:190px;
    max-height:140px;
    width: auto;
    height: auto;
}
		
   </style>
<nav class="navbar header-navbar pcoded-header">
    <div class="navbar-wrapper">
        <div class="navbar-logo">
             
            <a class="mobile-menu waves-effect waves-light" id="mobile-collapse" href="#!">
                <i class="ti-menu"></i> 
            </a>
           
         
            <div class="mobile-search waves-effect waves-light">
                <div class="header-search">
                    <div class="main-search morphsearch-search">
                        <div class="input-group">
                            <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                            <input type="text" class="form-control" placeholder="Enter Keyword">                            
                        </div>
                    </div>
                </div>
            </div>
            <a href="<%= request.getContextPath() %>/principal/PagPrincipal.jsp">
                <img class="img-fluid mylogo" src="<%= request.getContextPath() %>/imagens/seidor-multicloud2.png" alt="Theme-Logo" />
            </a>
            <a class="mobile-options waves-effect waves-light">
                <i class="ti-more"></i>
            </a>
        </div>
      
        <div class="navbar-container container-fluid">
            <ul class="nav-right">
                <li>
                    <div class="sidebar_toggle"><a href="javascript:void(0)"><i class="ti-menu"></i></a></div>
                </li>
               
                <li class="header-search">
                    <div class="main-search morphsearch-search">
                        <div class="input-group">
                            <span class="input-group-addon search-close"><i class="ti-close"></i></span>
                            <input type="text" class="form-control">                           
                        </div>
                    </div>
                </li>
                <li>
                   <a href="<%= request.getContextPath() %>/principal/PagPrincipal.jsp" class="waves-effect waves-dark" style="margin-top: 10px">               
                      <span class="pcoded-micon"><i class="ti-home"></i></span>
                      <span class="pcoded-mtext" data-i18n="nav.dash.main">Início</span>
                      <span class="pcoded-mcaret"></span>
                   </a>
                </li>

                <li class="user-profile header-notification">
                    <a href="#!" class="waves-effect waves-light">
                    <!--  
                        <img src="<%= request.getContextPath() %>/imagens/FotoCapa.jpg" class="img-radius" alt="User-Profile-Image">
                    -->   
                        <span><%= session.getAttribute("nomeUsuario") %></span>
                        <i class="ti-angle-down"></i>
                    </a>
                    <ul class="show-notification profile-notification">
                        <li class="waves-effect waves-light">
                            <a href="<%= request.getContextPath() %>/ServletLogin?acao=logout">
                                <i class="ti-layout-sidebar-left"></i> Sair
                            </a>
                        </li>
                    </ul>
                </li>
                
            </ul>
        </div>
    </div>
</nav>
