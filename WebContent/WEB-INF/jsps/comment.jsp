<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>QUACKer: Quacking an egg</title>
<link rel="stylesheet" href="<c:url value="/css/Profile.css" />">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
</head>
<body>
	
	<c:forEach var="user" items="${usersList}">
	
		<div class="header">
		<img src="<c:url value="/images/quacker.svg" />" class="logo" height="90" width="90"/>
		<img src="<c:url value="/images/logoText.png" />" width="500" height="100"/>
			<div class="header-right">
			<c:url value="/home/${user.email}" var="homeUrl"/><a href="${homeUrl}"><img src="<c:url value="/images/home.png" />" height="45" width="45"/> </a>
				<c:url value="/profile/${user.id}" var="profileUrl"/><a href="${profileUrl}"><img src="<c:url value="/images/user.svg" />" height="40" width="40"/></a>
				<c:url value="/scores/${user.id}" var="scoresUrl"/><a href="${scoresUrl}"><img src="<c:url value="/images/scores.svg" />" height="40" width="40"/> </a>
				<c:url value="/logout" var="logoutUrl"/><a href="${logoutUrl}"><img src="<c:url value="/images/logout.png" />" height="45" width="45"/></a>	
			</div>
	</div>	

		<section class="container">
			<c:forEach var="posts" items="${posts}">
				<div class="bg">
					<h1>${posts.heading}</h1>
				
					
						<h3>Quack <br></h3>
						${posts.quack}<br> <br />
						<h5>
							${posts.PLikes} &nbsp;<img src="<c:url value="/images/upvote.png" />" height="30" width="30"/>&nbsp;&nbsp;&nbsp;
							${posts.PDislikes}	&nbsp;<img src="<c:url value="/images/downvote.png" />" height="30" width="30"/><br><br>
						 	<i>	By: ${posts.userName} &nbsp;&nbsp; Category: #${posts.category}</i>
						</h5>
					<h3>Comments: <br></h3>
				
				
						<table>
							<c:forEach var="comments" items="${posts.commentsList}">
								<tr>
								<td class="four"><h4>Quacked by ${comments.userName}:</h4> &nbsp; ${comments.comment} </td>
								</tr>
		     				</c:forEach>
		     			</table>	
	     		
	     		
				<c:url value="/insertComments/${posts.postId}/${user.id}/${userName}" var="url" />
				
				<form:form modelAttribute="com" method="post" action="${url}">
					<form:input path="comment" placeholder="Enter your quack"/><div style="color:red">${comment}</div>
					Quacker<p>${userName}</p>
					<form:hidden path="commentId"/>
					<input type="submit" value="Add Comment!" />
				</form:form>
			</div>
		</c:forEach>
		
	</section>
</c:forEach>
	
<br><br><br>
<div class="footer"><p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p></div>
	
</body>
</html>