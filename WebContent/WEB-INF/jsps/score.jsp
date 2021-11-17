<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QUACKer: Most eggs</title>
<link rel="stylesheet" href="<c:url value="/css/Score.css" />">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px"
	rel="stylesheet">
</head>
<body>
	<c:forEach var="user" items="${userName}">
		<div class="header">
			<img src="<c:url value="/images/quacker.svg" />" class="logo"
				height="90" width="90" /> <img
				src="<c:url value="/images/logoText.png" />" width="500"
				height="100" />
			<div class="header-right">
				<c:url value="/home/${user.email}" var="homeUrl" />
				<a href="${homeUrl}"><img
					src="<c:url value="/images/home.png" />" height="45" width="45" />
				</a>
				<c:url value="/profile/${user.id}" var="profileUrl" />
				<a href="${profileUrl}"><img
					src="<c:url value="/images/user.svg" />" height="40" width="40" /></a>
				<c:url value="/scores/${user.id}" var="scoresUrl" />
				<a href="${scoresUrl}"><img
					src="<c:url value="/images/scores.svg" />" height="40" width="40" />
				</a>
				<c:url value="/logout" var="logoutUrl" />
				<a href="${logoutUrl}"><img
					src="<c:url value="/images/logout.png" />" height="45" width="45" /></a>
			</div>
		</div>

		<section class="container">
		
			<div class="one">
				<h1>Most Up Votes Received Eggs</h1>
				<table class="tblOne">
			
					<tr>
						<th>Heading</th>
						<th>Author</th>
						<th>Up Votes</th>
					</tr>
				
					<c:forEach var="LPosts" items="${postsLikes}">
						<tr>
							<td>${LPosts.heading}</td>
							<td>${LPosts.userName}</td>
							<td>${LPosts.PLikes}</td>
						</tr>
					</c:forEach>
				</table>



				<h1>Most Down Votes Received Eggs</h1>
				<table class="tblOne">
					<tr>
						<th>Heading  </th>
						<th>Author  </th>
						<th>Down Votes  </th>
					</tr>
					<c:forEach var="DPosts" items="${postsDislikes}">
						<tr>
							<td>${DPosts.heading}</td>
							<td>${DPosts.userName}</td>
							<td>${DPosts.PDislikes}</td>
						</tr>
					</c:forEach>
				</table><br><br>

			</div>

			<div class="two">
		
				<h1>Most Up Votes Given</h1>
				<table class="tblTwo">
					<tr>
						<th>User</th>
						<th>Up Votes</th>
					</tr>
					<c:forEach var="LUsers" items="${usersLikes}">
						<tr>
							<td>${LUsers.fname}</td>
							<td>${LUsers.ULikes}</td>
						</tr>
					</c:forEach>
				</table>

				<h1>Most Down Votes Given</h1>
				<table class="tblTwo">
					<tr>
						<th>User</th>
						<th>Down Votes</th>
					</tr>
					<c:forEach var="DUsers" items="${usersDislikes}">
						<tr>
							<td>${DUsers.fname}</td>
							<td>${DUsers.UDislikes}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
	</c:forEach>

</body>
</html>