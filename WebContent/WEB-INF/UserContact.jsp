<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Contact</title>
 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/contact.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
</head>
<body>
	<div id="menu">
		 <jsp:include page="/WEB-INF/header.jsp" /> <br/><br/><br/><br/>
	</div>
	<p><center>Après la validation, vous allez recevoir un mail contenant toutes les informations nécessaires.</center></p>
	<form method="get" action="contact">
           <fieldset>
                <legend>Contact</legend>
               	<label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="" size="20" maxlength="60" required/>
               	<br />
  				
  				<input type="submit" value="contact" class="sansLabel" />
                <br />
                
            </fieldset>
    </form>
</body>
</html>