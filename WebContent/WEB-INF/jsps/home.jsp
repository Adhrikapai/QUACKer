<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/css/Home.css" />">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
<script src="<c:url value="/scripts/editProfile.js" />"></script>
<meta charset="ISO-8859-1">
<title>QUACKer: Browsing & Blogging eggs</title>
</head>

<body>
	<c:forEach var="user" items="${userName}">
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
		<h1>Hi, ${user.fname}</h1>	
		
		<c:url value="/search/${user.id}" var="searchUrl"/>
		 <form method="get" action="${searchUrl}">
		 	<input type="text" name="searchHeading" placeholder="Search Keywords">
		 	<input type="submit" name="Search" value="Search"></input>
		 </form>
	
		<div class="link">
			<hr> <br /><br />
			<c:url value="/addPost/${user.fname}" var="postUrl"/><a href="${postUrl}">Add an egg &nbsp;<img src="<c:url value="/images/egg.svg" />" height="30" width="30" 	/></a><br /><br /><br />
			<hr>
			<h3>Categories</h3>
			<c:url value="/musicSearch/${user.id}" var="mmUrl"/><a href="${mmUrl}">Music & Movies</a><br /><br />
			<c:url value="/techSearch/${user.id}" var="tUrl"/><a href="${tUrl}">Technology</a><br /><br />
			<c:url value="/hebeSearch/${user.id}" var="hbUrl"/><a href="${hbUrl}">Health & Beauty</a><br /><br />
			<c:url value="/polSearch/${user.id}" var="pUrl"/><a href="${pUrl}">Politics</a><br /><br />
			<c:url value="/eduSearch/${user.id}" var="eUrl"/><a href="${eUrl}">Education</a><br /><br />
			<c:url value="/relSearch/${user.id}" var="rUrl"/><a href="${rUrl}">Relationships</a><br /><br />			
		</div>
	</div>
	
	<div class="two">
	<h1>Recent Posts</h1>	
				
				<c:forEach var="posts" items="${posts}">
					<div class="three">
						<h1>${posts.heading}</h1> <h4>By: ${posts.userName}</h4>		
					
								<h3>Quack <br></h3>
								${posts.quack}<br> <br>
								Category: #${posts.category}<br><br>
		
							<c:url value="/voteLike/${posts.postId}/${user.id}" var="likeUrl"/>${posts.PLikes}
							<a href="${likeUrl}">  <img src="<c:url value="/images/upvote.png" />" height="30" width="30"/></a>
							
							&nbsp;&nbsp;&nbsp;
							
							<c:url value="/voteDislike/${posts.postId}/${user.id}" var="dislikeUrl"/>${posts.PDislikes}
							<a href="${dislikeUrl}">  <img src="<c:url value="/images/downvote.png" />" height="30" width="30"/></a>
										
							&nbsp;&nbsp;&nbsp;
							<c:url value="/addComment/${posts.postId}/${user.id}/${user.fname}" var="commentUrl"/>
							<a href="${commentUrl}"><img src="<c:url value="/images/comment.svg" />" height="29" width="29"/></a> 
					</div>
						<br>
				</c:forEach>
				
					
	</div>
</section>
</c:forEach> 

	<br><br><br>
	<div class="footer"><p>QUACKer by GOC | Adhrika Pai, Michaela Icmat, Samir Nasser & Sneh Singh | Winter 2019</p></div>
	
</body>
</html>