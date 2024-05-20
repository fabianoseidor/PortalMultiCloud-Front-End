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
		
		<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-1 needs-validation" novalidate>

		    <input type="hidden" value="<%= request.getParameter("url") %>" name="url"/>
			<div class="mb-1">
			  <label class="form-label" style="color: black">Login</label>
			  <input class="form-control" name="login" type="text" required="required">
			  <div class="valid-feedback">
                   OK
              </div>
			  <div class="invalid-feedback">
                   Informe Login!
              </div>
			</div>
			<div class="mb-1">
			  <label class="form-label" style="color: black">Senha</label>
			  <input class="form-control" name="senha" type="password" required="required">
			  <div class="valid-feedback">
                   OK
              </div>
			  <div class="invalid-feedback">
                   Informe Senha!
              </div>
	        </div>			

	      
    		  <input type="submit" value="Acessar" class="btn btn-outline-secondary btn-sm" >
  			

		</form>
			
		<h5 class="msg">${msg}</h5>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" ></script>

        <script type="text/javascript">
	        (() => {
	          'use strict'
	
	          // Fetch all the forms we want to apply custom Bootstrap validation styles to
	          const forms = document.querySelectorAll('.needs-validation')
	
	          // Loop over them and prevent submission
	          Array.from(forms).forEach(form => {
	            form.addEventListener('submit', event => {
	              if (!form.checkValidity()) {
	                event.preventDefault()
	                event.stopPropagation()
	              }
	
	              form.classList.add('was-validated')
	            }, false)
	          })
	        })()
        </script>
	</body>
</html>