
<%@page import="java.util.ArrayList"%>
<%@page import="modele.services.ForumS.Theme"%>
<%@page import="modele.services.ForumS.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administration Theme</title>

<script>

function update(id) {
	console.log("hello");
	
	var url = "http://localhost:8080/EnsemblePourAfrique/deleteApi";
	var params = "idTheme="+ id;
	var http = new XMLHttpRequest();
	http.open("GET", url+"?"+params, true);
	http.send(null);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
	    	document.location.reload(true);
	    }
	}
	}
</script>
</head>
<body style="text-align: center">

	<iframe width="0" height="0" border="0" name="dummyframe"
		id="dummyframe"></iframe>

	

		<%
			HttpSession a = request.getSession();
		Utilisateur mail = (Utilisateur) a.getAttribute("user");
		if (mail != null) {
			ArrayList<Theme> mesThemes = (ArrayList<Theme>) request.getAttribute("theme");

			for (Theme e : mesThemes) {

				out.println("<center><table border=5 width=600 height=300> <tr> <td><img src=\"http://localhost/Images/" + e.getLink()
				+ "\"width=\"600\" height=\"300\"></td></tr><tr><td>" + "<a href=\"/EnsemblePourAfrique/sujet?id="
				+ e.getId() + "\">" + e.getNom() + "</a></td></tr><tr><td><input type=\"button\" value=\"Supprimer\" onclick=\"update( " + e.getId() +")\"><input type=\"button\" value=\"Modifier Sujets\" onclick=\"location.href='http://localhost:8080/EnsemblePourAfrique/editSubject?id="+ e.getId()+"';\"> </td></tr></center></table> <br>");

			}
		}

		else {

			out.println(
			"</div><div class=\"alert\" role=\"alert\"> <strong>Vous n'êtes pas connecté !</strong> <a href=\"/EnsemblePourAfrique/login\" class=\"alert-link\">Cliquez ici pour vous connecter</a></div>");

		}
		%>
<table border=5 width=600 height=300>
		<form method="post" action="refreshCategory"
			enctype="multipart/form-data" target="dummyframe" width=600 height=300>
			<tr>
				<td><textarea id="msg" name="msg" placeholder="Nom du sujet"></textarea></td>
				<td><input type="file" name="img" accept="image/*" /></td>
				<td><input type="submit" value="Envoyer" /></td>
			</tr>


		</form>

</table>
	
</body>
</html>