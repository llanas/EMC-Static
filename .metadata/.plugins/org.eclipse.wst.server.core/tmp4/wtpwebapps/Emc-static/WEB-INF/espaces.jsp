<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Espace de téléchargement</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<div id="cadre">
		<c:import url="/inc/header.jsp"/>
		<div id="espace_acces">
			<h2>ESPACE UTILISATEUR</h2>
			<table>
				<tr>
					<th>Identifiant :</th>
					<th>Type de Profil :</th>
					<th>Prénom :</th>
					<th>Nom :</th>
					<th>Téléphone :</th>
					<th>Mobile :</th>
					<th>Mail :</th>
					<th>Fonction :</th>
					<th>Entreprise :</th>
				</tr>
				<tr>
					<td><c:out value="${ sessionUser.id }" /></td>
					<td><c:out value="${ sessionUser.profil.name }" /></td>
					<td><c:out value="${ sessionUser.prenom }" /></td>
					<td><c:out value="${ sessionUser.nom }" /></td>
					<td><c:out value="${ sessionUser.mobile }" /></td>
					<td><c:out value="${ sessionUser.phone }" /></td>
					<td><c:out value="${ sessionUser.mail }" /></td>
					<td><c:out value="${ sessionUser.fonction }" /></td>
					<td><c:out value="${ sessionUser.groupe.name }" /></td>
				</tr>
			</table>
		</div>
		<c:import url="/inc/footer.jsp"/>
	</div>

</body>
</html>