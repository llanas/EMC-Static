<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification Administrateur</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<div id="cadre">
		<c:import url="/inc/header.jsp"/>
		<div id="espace_acces">
			<h2>ACCES ADMINISTRATEUR</h2>
			<form method="post" action="AuthentificationAdmin" class="authentification" id="cadre_acces">
				<fieldset class="identification">
					<!-- Formulaire de connexion à l'espace administrateur -->
					<label for="login_admin">Identifiant</label>
					<input type="text" id="login_admin" name="login_admin" value="" maxlength="20" />
						<span class="erreur">${form.erreurs['login_admin']}</span>
	
					<label for="password_admin">Mot de passe</label>
					<input type="password" id="password_admin" name="password_admin" value="" maxlength="20" />
						<span class="erreur">${form.erreurs['password_admin']}</span>
					
					<input type="submit" value="AuthentificationAdmin" />
						<span class="erreur"></span>
				</fieldset>
			</form>
		</div>
		<c:import url="/inc/footer.jsp"/>
	</div>
</body>
</html>