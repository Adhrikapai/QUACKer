<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QUACKer: Have fun browsing</title>

<link rel="stylesheet" href="<c:url value="/css/Profile.css" />">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
<script src="<c:url value="/scripts/script.js" />"></script>
</head>
<body onLoad="onLoadBody()">

<div class="header">
	<img src="<c:url value="/images/quacker.svg" />" class="logo" height="90" width="90"/>
	<img src="<c:url value="/images/logoText.png" />" width="500" height="100"/>
		
</div>	
<section class="container">
	<div class="one">	
		<c:url value="/signInUsers" var="signInUrl" />
		<form:form modelAttribute="users" method="post" action="${signInUrl}" name="form" id="myForm">
	

	
			<h1>LOGIN</h1><br />
				Email: <form:input path="email" name="email" id="email"/><br /><br />
				Password: <form:input path="password" name="password" id="password"/><br /><br /><br />
				
				<input type="submit" value="Sign In" name="btnSignIn" id="btnSignIn"/>	
				<form:hidden path="id" name="hiddenId"/><br />
				
				<c:forEach var="error" items="${passwordError}">
					<p class="${error}" >Wrong Email and/ Password. Please try again!</p>
				</c:forEach>
	</form:form>
	</div>
	
	<div class="two">	
		<c:url value="/saveUsers" var="saveUsersUrl" />
			<form:form modelAttribute="users" method="post" action="${saveUsersUrl}" id="myForm1">
			<h1>SIGN-UP</h1>		
				First Name: <form:input path="fname" id="fname"/><br />
				Last Name: <form:input path="lname" id="lname"/><br />
				Email: <form:input path="email" id="email1"/><br />
				Password: <form:input path="password" id="password1"/><br />	
				<input type="submit" value="Save User" name="btnAddUser"/>	
				<form:hidden path="id" />
			</form:form>
	</div>
</section>

<br><br><br>
<div class="footer">
<p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p>
</div>
	

</body>
</html>