<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QUACKer: New egg being dropped!</title>
<link rel="stylesheet" href="<c:url value="/css/Post.css" />">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
</head>
<body>

	<div class="header">
		<img src="<c:url value="/images/quacker.svg" />" class="logo" height="90" width="90" /> 
		<img src="<c:url value="/images/logoText.png" />" width="500" height="100" />
		
		<c:forEach var="user" items="${users}">
			<div class="header-right">
				<c:url value="/home/${user.email}" var="homeUrl" />
				<a href="${homeUrl}"><img src="<c:url value="/images/home.png" />" height="45" width="45" /> </a>
				
				<c:url value="/profile/${user.id}" var="profileUrl" />
				<a href="${profileUrl}"><img src="<c:url value="/images/user.svg" />" height="40" width="40" /></a>
			
				<c:url value="/scores/${user.id}" var="scoresUrl" />
				<a href="${scoresUrl}"><img src="<c:url value="/images/scores.svg" />" height="40" width="40" />
			</a>
			
			<c:url value="/logout" var="logoutUrl" />
			<a href="${logoutUrl}"><img
				src="<c:url value="/images/logout.png" />" height="45" width="45" /></a>
				
		</div>
		</c:forEach>
	</div>


		<section class="container"><c:url value="/savePost/${userName}" var="url" />
			<form:form modelAttribute="posts" method="post" action="${url}">
				<div class="bg">
					
					Heading<form:input path="heading" />${heading}<br /> 
					
					Quack <br> <form:textarea path="quack" />${quack}<br /> <br>
					
					Categories<form:select path="category"><form:option value="NONE" label="Select" /><form:options items="${categoryList}" /></form:select>

					Author<p>${userName}</p>

					<form:hidden path="postId" />

					<input type="submit" value="Add Post!" />
				</div>
			</form:form>
			<br />
		</section>

	<br><br><br>
	<div class="footer">
		<p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p>
	</div>
</body>
</html>