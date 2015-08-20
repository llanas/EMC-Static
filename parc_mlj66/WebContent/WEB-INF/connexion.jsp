<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="connexion">
		<fieldset>
			<legend>Connexion</legend>
			<p>Merci de vous connecter via ce formulaire:</p>
			
			<label for="login">Nom d'utilisateur</label>
			<input type="text" id="login" name="login" value="" size="20" maxlength="20" />
			
			<label for="password">Mot de passe <span class="requis">*</span></label>
			<input type="password" id="password" name="password" value="" size="20" maxlength="20" />
			
			<br />
			<input type="submit" value="Valide" class="sansLabel" /> <br />
		</fieldset>
	</form>
</body>
</html>