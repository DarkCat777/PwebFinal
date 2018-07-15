<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<%@page import="java.util.Date"%>
<%@ page import="com.google.appengine.api.users.*"%>
<%
	Billing billing = (Billing) request.getAttribute("billing");
	List<Billing> billings = (List<Billing>) request.getAttribute("billings");
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

.boton {
	color: black;
	width: 150px;
}

input, .dato {
	width: 20%;
	color:black;
}

a {
	color: blue;
}

a:HOVER {
	color: aqua;
}

.link {
	color: white;
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
					<br> <br> <br> <br>
					<div id="contenido">
						<div class="titulo">
							<h2>Editar Factura</h2>
						</div>
						<div class="formulario">
							<form action="/billing/edit" method="post">
								<label><b>Editar el nombre de la empresa: </b></label> <br>
								<input class="dato" type="text" name="customer"
									placeholder="Ingrese el nombre de la emrpresa"
									value="<%=billing.getCustomer()%>" required> <br>
								<br> <label><b>Editar la dirección: </b></label> <br>
								<input class="dato" type="text" name="address"
									placeholder="Ingrese la direccion de la empresa"
									value="<%=billing.getAddress()%>" required> <br> <br>
								<label><b>Editar el producto: </b></label> <br> <input
									type="text" class="dato" name="descriptionproduct"
									placeholder="Ingrese el nombre del producto"
									value="<%=billing.getDescriptionProduct()%>" required>
								<br> <br> <label><b>Editar la unidad de
										producto: </b></label> <br> <input type="text" pattern="^[0-9]+(.[0-9]+)?"
									name="unitpriceproduct" class="number"
									placeholder="Ingrese la unidad de producto"
									value="<%=billing.getUnitPriceProduct()%>" required> <br>
								<br> <label><b>Editar la cantidad de productos:
								</b></label> <br> <input type="text" class="dato" name="mountProduct" pattern="^[0-9]+(.[0-9]+)?"
									placeholder="Ingrese la cantidad de productos"
									value="<%=billing.getMountProduct()%>" required> <br>
								<br> <input type="hidden" name="id"
									value="<%=billing.getId()%>"> <input class="boton"
									type="submit" value="Editar Factura">
							</form>
						</div>
						<div class="link">
							<a id="add" href="/billing" class="link" title="Lista de Facturas">Lista
								de Facturas</a>
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




