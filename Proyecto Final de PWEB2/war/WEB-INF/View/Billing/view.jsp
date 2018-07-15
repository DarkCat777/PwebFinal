<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.entity.*"%>
<%@ page import="com.google.appengine.api.users.*"%>
<%
	Billing billing = (Billing) request.getAttribute("billing");
	UserService use = UserServiceFactory.getUserService();
	User user = use.getCurrentUser();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


<title>ACL - Home</title>

<!-- Bootstrap core CSS -->
<link href="/CSS/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/CSS/cover.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/JS/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
#data {
	align-items: center;
	padding: 30px;
}

#contenido {
	align-text: center;
}

.contenido {
	background-color: white;
	color: black;
}

.lista {
	background-color: black;
	color: white;
	width: 100%;
}
.boton{
	color:black;
}
</style>
</head>

<body>

	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">

				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">
							<b>Access Control List (ACL)</b>
						</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li><a href="/index.html">Inicio</a></li>
								<li><a href="/user">Usuarios</a></li>
								<li><a href="/role">Roles</a></li>
								<li><a href="/access">Accesos</a></li>
								<li><a href="/resource">Recursos</a></li>
								<li class="active"><a href="/billing">Facturas</a></li>
								<%
									if (use.isUserLoggedIn()) {
								%>
								<li class="active"><a href="/user/login"><%=user.getNickname()%></a></li>
								<li><a href="/user/logout">LogOut</a></li>
								<%
									} else {
								%>
								<li><a href="/user/login">Login</a></li>
								<%
									}
								%>
								
							</ul>
						</nav>
					</div>
				</div>
				<div class="inner cover">
					<br>
					<br>
					<br>
					<br>
					<div id="contenido">
						<div class="titulo">
							<h1>Factura</h1>
						</div>
						<table class="lista">
							<tr>
								<td><b>Nombre de la Empresa: </b></td>
								<td><b>Dirección de la Empresa: </b>></td>
								<td><b>Fecha:</b></td>
								<td><b>Producto: </b></td>
								<td><b>Precio Unitario: </b></td>
								<td><b>Cantidad: </b></td>
								<td><b>SUB Total: </b></td>
								<td><b>IGV:(18%)</b></td>
								<td><b>Total:</b></td>
							</tr>
							<tr class="contenido">
								<td><%=billing.getCustomer() %></td>
								<td><%=billing.getAddress()%></td>
								<td><%=billing.getDateIn()%></td>
								<td><%=billing.getDescriptionProduct()%></td>	
								<td><%=billing.getUnitPriceProduct()%></td>
								<td><%=billing.getMountProduct()%></td>							
								<td><%=billing.getTotal()%></td>							
								<td><%=(billing.getTotal() * 0.18)%></td>							
								<td><%=(billing.getTotal() * 1.18)%></td>
							</tr>
						</table>
						<br>
						<form action="/billing/delete" method="get">
							<input type="hidden" value="<%=billing.getId()%>" name="id">
							<input class="boton" type="submit" value="Borrar">
						</form>
						<form action="/billing/edit" method="get">
							<input type="hidden" value="<%=billing.getId()%>" name="id">
							<input class="boton" type="submit" value="Editar">
						</form>
						<div class=link>
							<a href="/billing" title="Añadir Factura">Lista de Facturas</a>
						</div>
					</div>
				</div>

				<!-- Bootstrap core JavaScript
    ================================================== -->
				<!-- Placed at the end of the document so the pages load faster -->
				<script
					src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
				<script>
					window.jQuery
							|| document
									.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
				</script>
				<script src="/JS/bootstrap.min.js"></script>
				<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
				<script src="/JS/ie10-viewport-bug-workaround.js"></script>
</body>
</html>



