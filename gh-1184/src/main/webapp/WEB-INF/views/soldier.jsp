<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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
		
		<div class="soldier">
			<h1 class="soldierName">${soldierRecord.rank} ${soldierRecord.firstName} ${soldierRecord.middleInitial} ${soldierRecord.lastName} ${soldierRecord.suffix}</h1>
			
			<div class="soldierBio">
				<c:if test="${soldierRecord.residenceCity != null || soldierRecord.residenceState != null}">
					<p>Residence ${soldierRecord.residenceCity} ${soldierRecord.residenceState}</p>
				</c:if>
			</div>

			<div class="soldierService">
				<c:if test="${soldierRecord.units != null && not empty soldierRecord.units}">
					<p>Units served in:</p>
					<div class="soldierMultilineResultData">
						<c:forEach var="unit" items="${soldierRecord.units}">
							<p>${unit.regiment} ${unit.company}</p>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${soldierRecord.ships != null && not empty soldierRecord.ships}">
					<p>Ships served on:</p>
					<div class="soldierMultilineResultData">
						<c:forEach var="ship" items="${soldierRecord.ships}">
							<p>${ship.ship}</p>
						</c:forEach>
					</div>
				</c:if>
			</div>

			<div class="soldierBurialInfo">
				<p>Date of Death <c:if test="${soldierRecord.dateOfDeath != null}">${soldierRecord.dateOfDeath}</c:if><c:if test="${soldierRecord.dateOfDeath == null}">Unknown Date</c:if></p>
				<c:if test="${soldierRecord.killedInAction == true}"><p>500 Club</p></c:if>
				<p>Buried in ${soldierRecord.cemeteryName} ${soldierRecord.cemeteryLot}</p>
				<c:if test="${soldierRecord.monumentType != null}"><p>Monument ${soldierRecord.monumentType}</p></c:if>
			</div>
			
			<div class="soldierNotes">
				<p>Additional Notes:</p>
				<div class="soldierMultilineResultData">
					<p>${soldierRecord.notes}</p>
				</div>			
			</div>
			
			<a href="./" onClick="history.back()">Go back</a>
		</div>
	
		<%@include file="footer.jsp" %>

	</div>

</body>
</html>
