<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="modele.services.ForumS.Commentaire"%>
<%@page import="modele.services.ForumS.Utilisateur"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discussion</title>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="assets/css/animate.css" rel="stylesheet">
	    <link href="assets/css/plugins.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="assets/css/commentaire.css" rel="stylesheet">
	
	    <!-- Custom Fonts -->
	    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="assets/css/pe-icons.css" rel="stylesheet">
</head>
<body>
	<div id="menu">
		 <jsp:include page="/WEB-INF/header.jsp" /> <br/><br/><br/><br/>
		</div>
<script>
function update(id) {
	console.log("Hello world!");
	var url = "http://localhost:8080/EnsemblePourAfrique/updateComment";
	var http = new XMLHttpRequest();
	http.open("POST", url+"?"+id + "&msg="+document.getElementById("msg").value, true);
	http.send("msg=" + document.getElementById("msg").value);
	http.onreadystatechange = function()
	{
	    if(http.readyState == 4 && http.status == 200) {
	    	document.location.reload(true);
	    }
	}
	}
	
	
<!--
'use strict';

;( function( $, window, document, undefined )
{
	// feature detection for drag&drop upload

	var isAdvancedUpload = function()
		{
			var div = document.createElement( 'div' );
			return ( ( 'draggable' in div ) || ( 'ondragstart' in div && 'ondrop' in div ) ) && 'FormData' in window && 'FileReader' in window;
		}();


	// applying the effect for every form

	$( '.box' ).each( function()
	{
		var $form		 = $( this ),
			$input		 = $form.find( 'input[type="file"]' ),
			$label		 = $form.find( 'label' ),
			$errorMsg	 = $form.find( '.box-error span' ),
			$restart	 = $form.find( '.box-restart' ),
			droppedFiles = false,
			showFiles	 = function( files )
			{
				$label.text( files.length > 1 ? ( $input.attr( 'data-multiple-caption' ) || '' ).replace( '{count}', files.length ) : files[ 0 ].name );
			};

		// letting the server side to know we are going to make an Ajax request
		$form.append( '<input type="hidden" name="ajax" value="1" />' );

		// automatically submit the form on file select
		$input.on( 'change', function( e )
		{
			showFiles( e.target.files );

			
		});


		// drag&drop files if the feature is available
		if( isAdvancedUpload )
		{
			$form
			.addClass( 'has-advanced-upload' ) // letting the CSS part to know drag&drop is supported by the browser
			.on( 'drag dragstart dragend dragover dragenter dragleave drop', function( e )
			{
				// preventing the unwanted behaviours
				e.preventDefault();
				e.stopPropagation();
			})
			.on( 'dragover dragenter', function() //
			{
				$form.addClass( 'is-dragover' );
			})
			.on( 'dragleave dragend drop', function()
			{
				$form.removeClass( 'is-dragover' );
			})
			.on( 'drop', function( e )
			{
				droppedFiles = e.originalEvent.dataTransfer.files; // the files that were dropped
				showFiles( droppedFiles );

				
			});
		}


		// if the form was submitted

		$form.on( 'submit', function( e )
		{
			// preventing the duplicate submissions if the current one is in progress
			if( $form.hasClass( 'is-uploading' ) ) return false;

			$form.addClass( 'is-uploading' ).removeClass( 'is-error' );

			if( isAdvancedUpload ) // ajax file upload for modern browsers
			{
				e.preventDefault();

				// gathering the form data
				var ajaxData = new FormData( $form.get( 0 ) );
				if( droppedFiles )
				{
					$.each( droppedFiles, function( i, file )
					{
						ajaxData.append( $input.attr( 'name' ), file );
					});
				}

				// ajax request
				$.ajax(
				{
					url: 			$form.attr( 'action' ),
					type:			$form.attr( 'method' ),
					data: 			ajaxData,
					dataType:		'json',
					cache:			false,
					contentType:	false,
					processData:	false,
					complete: function()
					{
						$form.removeClass( 'is-uploading' );
					},
					success: function( data )
					{
						$form.addClass( data.success == true ? 'is-success' : 'is-error' );
						if( !data.success ) $errorMsg.text( data.error );
					},
					error: function()
					{
						alert( 'Error. Please, contact the webmaster!' );
					}
				});
			}
			else // fallback Ajax solution upload for older browsers
			{
				var iframeName	= 'uploadiframe' + new Date().getTime(),
					$iframe		= $( '<iframe name="' + iframeName + '" style="display: none;"></iframe>' );

				$( 'body' ).append( $iframe );
				$form.attr( 'target', iframeName );

				$iframe.one( 'load', function()
				{
					var data = $.parseJSON( $iframe.contents().find( 'body' ).text() );
					$form.removeClass( 'is-uploading' ).addClass( data.success == true ? 'is-success' : 'is-error' ).removeAttr( 'target' );
					if( !data.success ) $errorMsg.text( data.error );
					$iframe.remove();
				});
			}
		});


		// restart the form if has a state of error/success

		$restart.on( 'click', function( e )
		{
			e.preventDefault();
			$form.removeClass( 'is-error is-success' );
			$input.trigger( 'click' );
		});

		// Firefox focus bug fix for file input
		$input
		.on( 'focus', function(){ $input.addClass( 'has-focus' ); })
		.on( 'blur', function(){ $input.removeClass( 'has-focus' ); });
	});

})( jQuery, window, document );

-->

'use strict';

;( function ( document, window, index )
{
	// feature detection for drag&drop upload
	var isAdvancedUpload = function()
		{
			var div = document.createElement( 'div' );
			return ( ( 'draggable' in div ) || ( 'ondragstart' in div && 'ondrop' in div ) ) && 'FormData' in window && 'FileReader' in window;
		}();


	// applying the effect for every form
	var forms = document.querySelectorAll( '.box' );
	Array.prototype.forEach.call( forms, function( form )
	{
		var input		 = form.querySelector( 'input[type="file"]' ),
			label		 = form.querySelector( 'label' ),
			errorMsg	 = form.querySelector( '.box-error span' ),
			restart		 = form.querySelectorAll( '.box-restart' ),
			droppedFiles = false,
			showFiles	 = function( files )
			{
				label.textContent = files.length > 1 ? ( input.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', files.length ) : files[ 0 ].name;
			},
			triggerFormSubmit = function()
			{
				var event = document.createEvent( 'HTMLEvents' );
				event.initEvent( 'submit', true, false );
				form.dispatchEvent( event );
			};

		// letting the server side to know we are going to make an Ajax request
		var ajaxFlag = document.createElement( 'input' );
		ajaxFlag.setAttribute( 'type', 'hidden' );
		ajaxFlag.setAttribute( 'name', 'ajax' );
		ajaxFlag.setAttribute( 'value', 1 );
		form.appendChild( ajaxFlag );

		// automatically submit the form on file select
		input.addEventListener( 'change', function( e )
		{
			showFiles( e.target.files );

			
		});

		// drag&drop files if the feature is available
		if( isAdvancedUpload )
		{
			form.classList.add( 'has-advanced-upload' ); // letting the CSS part to know drag&drop is supported by the browser

			[ 'drag', 'dragstart', 'dragend', 'dragover', 'dragenter', 'dragleave', 'drop' ].forEach( function( event )
			{
				form.addEventListener( event, function( e )
				{
					// preventing the unwanted behaviours
					e.preventDefault();
					e.stopPropagation();
				});
			});
			[ 'dragover', 'dragenter' ].forEach( function( event )
			{
				form.addEventListener( event, function()
				{
					form.classList.add( 'is-dragover' );
				});
			});
			[ 'dragleave', 'dragend', 'drop' ].forEach( function( event )
			{
				form.addEventListener( event, function()
				{
					form.classList.remove( 'is-dragover' );
				});
			});
			form.addEventListener( 'drop', function( e )
			{
				droppedFiles = e.dataTransfer.files; // the files that were dropped
				showFiles( droppedFiles );

								});
		}


		// if the form was submitted
		form.addEventListener( 'submit', function( e )
		{
			// preventing the duplicate submissions if the current one is in progress
			if( form.classList.contains( 'is-uploading' ) ) return false;

			form.classList.add( 'is-uploading' );
			form.classList.remove( 'is-error' );

			if( isAdvancedUpload ) // ajax file upload for modern browsers
			{
				e.preventDefault();

				// gathering the form data
				var ajaxData = new FormData( form );
				if( droppedFiles )
				{
					Array.prototype.forEach.call( droppedFiles, function( file )
					{
						ajaxData.append( input.getAttribute( 'name' ), file );
					});
				}

				// ajax request
				var ajax = new XMLHttpRequest();
				ajax.open( form.getAttribute( 'method' ), form.getAttribute( 'action' ), true );

				ajax.onload = function()
				{
					form.classList.remove( 'is-uploading' );
					if( ajax.status >= 200 && ajax.status < 400 )
					{
						var data = JSON.parse( ajax.responseText );
						form.classList.add( data.success == true ? 'is-success' : 'is-error' );
						if( !data.success ) errorMsg.textContent = data.error;
					}
					else alert( 'Error. Please, contact the webmaster!' );
				};

				ajax.onerror = function()
				{
					form.classList.remove( 'is-uploading' );
					alert( 'Error. Please, try again!' );
				};

				ajax.send( ajaxData );
			}
			else // fallback Ajax solution upload for older browsers
			{
				var iframeName	= 'uploadiframe' + new Date().getTime(),
					iframe		= document.createElement( 'iframe' );

					$iframe		= $( '<iframe name="' + iframeName + '" style="display: none;"></iframe>' );

				iframe.setAttribute( 'name', iframeName );
				iframe.style.display = 'none';

				document.body.appendChild( iframe );
				form.setAttribute( 'target', iframeName );

				iframe.addEventListener( 'load', function()
				{
					var data = JSON.parse( iframe.contentDocument.body.innerHTML );
					form.classList.remove( 'is-uploading' )
					form.classList.add( data.success == true ? 'is-success' : 'is-error' )
					form.removeAttribute( 'target' );
					if( !data.success ) errorMsg.textContent = data.error;
					iframe.parentNode.removeChild( iframe );
				});
			}
		});


		// restart the form if has a state of error/success
		Array.prototype.forEach.call( restart, function( entry )
		{
			entry.addEventListener( 'click', function( e )
			{
				e.preventDefault();
				form.classList.remove( 'is-error', 'is-success' );
				input.click();
			});
		});

		// Firefox focus bug fix for file input
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });

	});
}( document, window, 0 ));
</script>
<div id="tableau">
<table border=3>
<tr>
<td align=center>Auteur</td>
<td align=center>Commentaire</td>
<td align=center>Date</td>
</tr>
<%

HttpSession a = request.getSession();
Utilisateur mail = (Utilisateur) a.getAttribute("user");
if (mail != null) {
ArrayList<Commentaire>mesCommentaire = (ArrayList<Commentaire>)request.getAttribute("commentaire") ; 
String idSujet = request.getParameter("idSujet") ; 
String idTheme = request.getParameter("idTheme") ; 

for(Commentaire e : mesCommentaire){
	out.println("<tr><td>" + e.getMail()  + "</td>" +"<td>" +e.getCommentaire() + "</td>" + "<td>" + e.getData() + "</td></tr>"); 
}
out.println("<tr><td><strong>" + mail.getMail()  +"</strong></td><td><textarea id=\"msg\" name=\"msg\" placeholder=\"Commentaire\"></textarea></td><td><button onclick=\"update(\'idSujet=" + idSujet + "&idTheme="  +idTheme+"\')\">Repondre</button></td></tr> ") ; 




} else {

	out.println(
	"<div class=\"alert alert-danger\" role=\"alert\"> <strong>You've to be logged !</strong> <a href=\"/EnsemblePourAfrique/login\" class=\"alert-link\">Click here to be logged</a></div>");

}



%>
</table>




</div>




</body>
</html>