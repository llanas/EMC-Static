<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMC-Static Portail</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<div id="cadre">
		<c:import url="/inc/header.jsp"/>
		<div id="espace_acces">
			<h2>CODE D'ACCES</h2>
				<form method="post" action="authentification" class="identification" id="cadre_acces">
					<fieldset id="identification">
						<!-- Formulaire de connexion à l'espace d'étude -->
						<label for="password_user">Accès aux espaces de stockage</label>
						<input type="password" id="password_user" name="password_user" value=""  maxlength="20" /><br/>
						<c:out value="${form.resultat }"/><br />
						<input type="submit" value="Authentification" />
						<span class="erreur"></span>
					</fieldset>
				</form>
				
<!-- 				<form method="post" action="AuthentificationUpload"	class="acces_espace" id="cadre_acces"> -->
<!-- 					<fieldset> -->
<!-- 						Formulaire de connexion à l'espace d'upload -->
<!-- 						<label for="code_upload">Déposer un fichier</label> <input type="password" -->
<!-- 							id="code_upload" name="code_upload" value=""  -->
<!-- 							maxlength="20" /> -->
<!-- 						<input type="submit" value="AuthentificationUpload" /> -->
<!-- 						<span class="erreur"></span> -->
<!-- 					</fieldset> -->
<!-- 				</form> -->
		</div>
		<c:import url="/inc/footer.jsp"/>
	</div>
</body>
</html>

