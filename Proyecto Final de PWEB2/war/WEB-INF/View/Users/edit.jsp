<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<%@page import="java.util.Date"%>
<%@ page import="com.google.appengine.api.users.*"%>
<%
	Users user = (Users) request.getAttribute("user");
	List<Role> roles = (List<Role>) request.getAttribute("roles");
	UserService use = UserServiceFactory.getUserService();
	User us = use.getCurrentUser();
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

input .dato {
	width: 30%;
	color: black;
}

select {
	color: black
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
								<li class="active"><a href="/user">Usuarios</a></li>
								<li><a href="/role">Roles</a></li>
								<li><a href="/access">Accesos</a></li>
								<li><a href="/resource">Recursos</a></li>
								<li><a href="/billing">Facturas</a></li>
								<%
									if (use.isUserLoggedIn()) {
								%>
								<li class="active"><a href="/user/login"><%=us.getNickname()%></a></li>
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
							<h2>
								Editar Usuario
								<%=user.getEmail()%></h2>
						</div>
						<div class="formulario">
							<form action="/user/edit" method="post">
								<label><b>Email: </b></label> <br> <input type="email"
									name="email" placeholder="Email" value="<%=user.getEmail()%>"
									required> <br> <br> <label><b>Fecha
										de Creación: </b></label> <br> <input type="date" name="create"
									 required> <br> <br>
								<label><b>Genero: </b></label> <select name="gender"
									size="1">
									<%
										if (user.isGender()) {
									%>
									<option value="true" selected>Masculino</option>
									<option value="false">Femenino</option>
									<%
										} else {
									%>
									<option value="true">Masculino</option>
									<option value="false" selected>Femenino</option>
									<%
										}
									%>
								</select> <br><br><label><b>Estado: </b></label> <select name="status"
									size="1">
									<%
										if (user.isStatus()) {
									%>
									<option value="true" selected>Activo</option>
									<option value="false">Desactivado</option>
									<%
										} else {
									%>
									<option value="true">Activo</option>
									<option value="false" selected>Desactivado</option>
									<%
										}
									%>
								</select> <br><br><label><b>Fecha de Cumpleaños: </b></label> <br> <input
									type="date" name="birth" value="<%=user.getBirthJSP() %>" required> <br> <br>
								<label><b>Roles: </b></label> <br>
								<%
									if (roles.isEmpty()) {
								%>
								<a href="/role/add">Crear un Role</a>
								<%
									} else {
								%>
								<select name="idrole" size="1">
									<%
										for (Role role : roles) {
									%>
									<option value="<%=role.getId()%>"><%=role.getName()%></option>
									<%
										}
									%>
								</select>
								<%
									}
								%>
								<br><br>
								<input type="hidden" name="id" value="<%=user.getId()%>">
								<input class="boton" type="submit" value="Editar Usuario">
							</form>
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
									.write('<script src="../../assets/js/vendor/jquery.min.js">_$tag_____')
				</script>
				<script src="/JS/bootstrap.min.js"></script>
				<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
				<script src="/JS/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

