<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<title>Lowell Civil War Database</title>

	<link rel="stylesheet" href="lcwdb.css" type="text/css" />
	<link rel="stylesheet" href="css/custom-theme/jquery-ui-1.10.3.custom.css" />
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.js"></script>
</head>
<body>
	<div id="mainBody">
		<%@include file="header.jsp" %>
		
		<div class="error">
			<h2>A server error has occurred.</h2>
			<p>We apologize for the inconvenience; our administrators have been notified.</p>
			<a href="./" onClick="history.back()">Go back</a>
		</div>
	
		<%@include file="footer.jsp" %>

	</div>

</body>
</html>
