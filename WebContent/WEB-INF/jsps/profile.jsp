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
<script src="<c:url value="/scripts/Profile.js" />"></script>
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

</head>

<body onload="onLoadBody()">
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

	<div class="one">
		<c:url value="/profile/${user.id}" var="profileUrl"/>
		<form:form modelAttribute="usersList" method="get" action="profileUrl" id="form1">
					<h1>QUACKer Profile</h1>
					First Name <input type="text" value="${user.fname}" id="fname">
					Last Name <input type="text" value="${user.lname}" id="lname">
					Email <input type="text" value="${user.email}" id="email">			
					Number of Up Votes Given <input type="text" value="${user.ULikes}" id="likes">
					Number of Down Votes Given <input type="text" value="${user.UDislikes}" id="dislikes">
					<c:forEach var="posts" items="${user.posts}">
					Number of Up Votes Received <input type="text" value="${posts.PLikes}">
					Number of Down Votes Received <input type="text" value="${posts.PDislikes}">
					</c:forEach>
		</form:form>
		
		<c:url value="/editUser/${user.id}" var="editUrl"/>
			<a href="${editUrl}"><input type="submit" value="Edit User" name="btnEditUser"/></a>
		<c:url value="/deleteUser/${user.id}" var="deleteUrl"/>
			<a href="${deleteUrl}"><input type="submit" value="Delete User" name="btnDeleteUser" onSubmit="window.location.reload()"/></a>				
	</div>
			
	<div class="two">
		<h1>Badges Received</h1>
		<c:set var = "likes" value ="${user.ULikes}"/>
		<c:set var = "dislikes" value ="${user.UDislikes}"/>	
		<c:forEach var="posts" items="${user.posts}">
			<c:set var = "ulikes" value ="${posts.PLikes}"/>
			<c:set var = "udislikes" value ="${posts.PDislikes}"/>	
		</c:forEach>
		<a>
			<img src="<c:url value="/images/duck.svg" />" height="40" width="40" alt="account created">
			<span>Account Successfully Created!</span>
		</a>
		
		<c:if test="${likes == '0'}"> 
		<a>
			<img src="<c:url value="/images/ghostLogo.svg" />" height="40" width="40">
			<span>Looking around!</span>
		</a>
		</c:if>
		
		<c:if test="${ulikes >= '1'}"> 
		<a>
			<img src="<c:url value="/images/likesReceived.svg" />" height="40" width="40">
			<span>Likes Received!</span>
		</a>
		</c:if>
		
		<c:if test="${udislikes >= '1'}"> 
		<a>
			<img src="<c:url value="/images/dislikesReceived.svg" />" height="40" width="40">
			<span>Dislikes Received!</span>
		</a>
		</c:if>
		
		<c:if test="${likes >= '1'}"> 
		<a>
			<img src="<c:url value="/images/firstLike.svg" />" height="40" width="40">
			<span>First Like Given!</span>
		</a>
		</c:if>
		
		<c:if test="${likes >= '10'}">
		<a>
			 <img src="<c:url value="/images/likeOne.svg" />" height="40" width="40">
			 <span>Liker of 10 posts!!</span>
		</a>
		</c:if>
		
		<c:if test="${likes >= '50'}">
		<a>
			 <img src="<c:url value="/images/likeTwo.svg" />" height="40" width="40">
			 <span>Liker of 50 posts!</span>
		</a>
		</c:if>
		
		<c:if test="${likes >= '100'}">
		<a>
			<img src="<c:url value="/images/likeThree.svg" />" height="40" width="40">
			<span>Liker of 100 posts!</span>
		</a>
		</c:if>
			    
		<c:if test="${dislikes >= '10'}">
		<a>
			<img src="<c:url value="/images/dislikeOne.svg" />" height="40" width="40">
			<span>Disliker of 10 posts!</span>
		</a>
		</c:if>
		
		<c:if test="${dislikes >= '50'}">
		<a>
			<img src="<c:url value="/images/dislikeTwo.svg" />" height="40" width="40">
			<span>Disliker of 50 posts!</span>
		</a>
	
		</c:if>
		
		<c:if test="${dislikes >= '100'}">
		<a>
			<img src="<c:url value="/images/dislikeThree.svg" />" height="40" width="40">
			<span>Disliker of 100 posts!</span>
		</a>
		</c:if>
		
	</div>	
	
</section>	

</c:forEach>	
	
<br><br><br>
<div class="footer"><p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p></div>
	
</body>
</html>