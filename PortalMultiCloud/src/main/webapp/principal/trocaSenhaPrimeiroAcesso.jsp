<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Portal MultiCloud</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"  >
		
		<style type="text/css">
		   form{
		     position: absolute;
		     top: 40%;
		     left: 70%;
		     right: 10%;
		     font-size: 12px;
		   }
		   h5{
		     position: absolute;
		     top: 30%;
		     left: 70%;
		     font-size: 20px;
		   }
		   .msg{
		     position: absolute;
		     top: 10%;
		     left: 33%;
		     font-size: 10px;
		     color: #664d03;
		     background-color: #fff3cd;
		     border-color: #ffecb5;
		   }
		   
		   body {
		     height: 70vh; 
		     background-color: black;
             background-image: url('./imagens/bg-portal1.jpeg');
             background-position: left left;
             background-repeat: no-repeat;
             background-size: cover;
           }	   		
		   		
		</style>
	</head>
	<body>
		
		<h5 style="color: black">Alterar senha primeiro acesso</h5>
		<form action="<%= request.getContextPath() %>/ServletTrocaSenha" method="post" id="formTrocaSenha" class="row g-3 needs-validation" novalidate>
            
		    <input type="hidden" value="<%= request.getParameter("url") %>" name="url"/>
			<div class="mb-1">
			  <label class="form-label" style="color: black">Digite a Nova Senha</label>
			  <input class="form-control" name="senha1" id="senha1" type="password" required="required" autocomplete="off">
			</div>
			<div class="mb-1">
			  <label class="form-label" style="color: black">Confirme a Senha</label>
			  <input class="form-control" name="senha2" id="senha2" type="password" required="required" onchange="validaSenhaIguais();" autocomplete="off">
			  <span id="resultado-pesquisa" style="color: red"></span>
	        </div>			

    		<input type="submit" value="Alterar Senha" class="btn btn-outline-secondary btn-sm" >


		</form>
			
		<h2 class="msg" id="msg">${msg}</h2>
		
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" ></script>

        <script type="text/javascript">
        
 	        function validaSenhaIguais() {
	       	   var senha1 = document.getElementById("senha1").value;
	    	   var senha2 = document.getElementById("senha2").value;
	    	  
	    	   if( senha1 !== senha2 ) document.getElementById("resultado-pesquisa").innerHTML = "Senhas não converem!"; // document.getElementById("msg").textContent = 'Senhas não converem!';
	    	   else document.getElementById("resultado-pesquisa").innerHTML = "Senhas Converem!"; // document.getElementById("msg").textContent = 'Senhas Converem!';
		    	     
	    	}	        
 	        var password = document.getElementById("senha1")
 	          , confirm_password = document.getElementById("senha2");

 	        function validatePassword(){

 	           if(password.value != confirm_password.value) {
 	              confirm_password.setCustomValidity("Senhas diferentes!");
 	           } else {
 	              confirm_password.setCustomValidity('');
 	           }
 	        }
 	        password.onchange        = validatePassword;
 	        confirm_password.onkeyup = validatePassword;

        </script>
	</body>
</html>