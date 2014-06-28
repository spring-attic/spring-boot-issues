<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<title>Lowell Civil War Database</title>

	<link rel="stylesheet" href="lcwdb.css" type="text/css" />
	<link rel="stylesheet" href="css/custom-theme/jquery-ui-1.10.3.custom.min.css" />
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.3.custom.min.js"></script>
	
	<script>
	  $(function() {
	    $( "#tabs" ).tabs();
	  });
	</script>
</head>
<body>
	<div id="mainBody">
		<%@include file="header.jsp" %>
		
		<div id="tabs">
			<ul>
				<li><a href="#byName">Name</a></li>
				<li><a href="#byUnit">Unit</a></li>
			</ul>
			<div id="byName">
				<sf:form  action="queryByName" method="POST" modelAttribute="queryByName" id="queryForm">
					<fieldset>
						<p class="queryHelp">Use '%' for wildcard matching</p>
						<label for="firstName">First name:</label>
						<sf:input type="text" id="firstName" path="firstName"/>
						<br />
						<label for="lastName">Last name:</label>
						<sf:input type="text" id="lastName" path="lastName"/>
					</fieldset>
					<br />
					<fieldset>
						<label for="cemeteryNames">Cemetery Name</label>
						<sf:select id="cemeteryNames" path="cemeteryId" items="${cemeteries}" itemLabel="name" itemValue="id"></sf:select>
						<br />
						<label for="fiveHundredClub">500 Club (War Casualty)</label>
						<sf:checkbox id="fiveHundredClub" path="fiveHundredClub"/>
					</fieldset>
					<br />
					<fieldset>
						<p class="queryHelp">Enter dates in YYYY-MM-DD format</p>
						<label for="startDeathDateI">Died after</label>
						<sf:input type="text" id="startDeathDateI" path="startDeathDate"/>
						<sf:errors path="startDeathDate" cssClass="queryError" />
						<br />
						<label for="endDeathDateI">Died before</label>
						<sf:input type="text" id="endDeathDateI" path="endDeathDate"/>
						<sf:errors path="endDeathDate" cssClass="queryError" />
					</fieldset>
					<br />
					<fieldset>
						<label for="sortBy">Sort By</label>
						<sf:select id="sortBy" path="sortBy" items="${sortBy}" itemLabel="sortTypeStr" itemValue="sortTypeStr"></sf:select>
						<br />
					</fieldset>
					<br />
					<input type="submit" value="Submit" />
				</sf:form>
				<div id="results">
					<div id="resultsInfo">
						<c:if test="${queryByNameResults != null}">
							<c:if test="${queryByNameResults.truncated != true}">
								<p>Returned ${fn:length(queryByNameResults.results)} records</p>
							</c:if>
							<c:if test="${queryByNameResults.truncated == true}">
								<p class="warn">Returned too many records. Displaying first ${fn:length(queryByNameResults.results)}. Please restrict your query.</p>
							</c:if>
						</c:if>
					</div>
					<table id="resultsTable">
						<tr>
							<th>Last Name</th>
							<th>First Name</th>
							<th title="Middle Initial">M.</th>
							<th title="Suffix">S.</th>
							<th>Cemetery</th>
							<th>Died</th>
							<th title="War Casualty">500 Club</th>
							<th>Details</th>
						</tr>
						<c:if test="${queryByNameResults != null}">
							<c:forEach var="result" items="${queryByNameResults.results}">
								<tr>
									<td>${result.lastName}</td>
									<td>${result.firstName}</td>
									<td>${result.middleInitial}</td>
									<td>${result.suffix}</td>
									<td>${result.cemeteryName}</td>
									<td>${result.dateOfDeath}</td>
									<td><c:if test="${result.killedInAction == true}">*</c:if></td>
									<td><a href="/lcwdb/soldier?id=${result.id}">Details</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			<div id="byUnit">Lorem ipsum dolor sit amet, consectetuer
				adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet
				dolore magna aliquam erat volutpat.</div>
		</div>
	
		<%@include file="footer.jsp" %>

	</div>

</body>
</html>
