<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QUACKer: Quacker's profile</title>

<link rel="stylesheet" href="<c:url value="/css/Profile.css" />">
<script src="<c:url value="/scripts/editProfile.js" />"></script>
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

</head>

<body onload="onLoadBody()">
<c:forEach var="usersList1" items="${user}">	
<div class="header">
	<img src="<c:url value="/images/quacker.svg" />" class="logo" height="90" width="90"/>
	<img src="<c:url value="/images/logoText.png" />" width="500" height="100"/>
		<div class="header-right">
		
			<c:url value="/home/${usersList1.email}" var="homeUrl"/><a href="${homeUrl}"><img src="<c:url value="/images/home.png" />" height="45" width="45"/> </a>
			<c:url value="/profile/${usersList1.id}" var="profileUrl"/><a href="${profileUrl}"><img src="<c:url value="/images/user.svg" />" height="40" width="40"/></a>
			<c:url value="/scores/${usersList1.id}" var="scoresUrl"/><a href="${scoresUrl}"><img src="<c:url value="/images/scores.svg" />" height="40" width="40"/> </a>
			<c:url value="/logout" var="logoutUrl"/><a href="${logoutUrl}"><img src="<c:url value="/images/logout.png" />" height="45" width="45"/></a>	
		</div>
</div>	
	
<center>
	<section class="container">
		<div class="bg">
		<c:url value="/saveUsers" var="saveUsersUrl" />
			<form:form modelAttribute="users" method="post" action="${saveUsersUrl}" id="form1">
					<h1>QUACKer Profile</h1>
					<i>(You cannot edit email, likes, and dislikes!)</i><br><br>

					First Name: <form:input path="fname" type="text" value="${usersList1.fname}" /><br />	
					Last Name: <form:input path="Lname" type="text" value="${usersList1.lname}" /><br />
					Password: <form:input path="password" type="text" value="${usersList1.password}"/><br />
					Email: <form:input path="email" type="text" value="${usersList1.email}"/><br />
					
					
					Up Votes Given <form:input path="uLikes" id="likes" type="text" value="${usersList1.ULikes}"/><br />
					Down Votes Given: <form:input path="uDislikes" id="dislikes" type="text" value="${usersList1.UDislikes}"/><br />
		
					
					
					<form:hidden path="id" />
					<input type="submit" value="Save User" name="btnUpdateUser"/>		
			</form:form>
		</div>	
	</section>
</center>
</c:forEach>		

<br><br><br>
<div class="footer"><p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p></div>
	
</body>
</html>