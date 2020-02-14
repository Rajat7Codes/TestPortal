<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<%-- <title><tiles:insertAttribute name="title" /></title> --%>

<!-- Bootstrap CSS-->
<link
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all">


<link href="${pageContext.request.contextPath}/static/css/login.css"
	rel="stylesheet" type="text/css" media="all">
</head>


<body>

	<c:url var="loginUrl" value="/login" />



	<c:if test="${expireDate == true}">
		<div class="alert alert-danger">
			<p>YOUR APPLICATION DATE IS EXPIRED......!</p>
		</div>
	</c:if>



	<c:if test="${param.error != null}">
		<div class="alert alert-danger m-0">
			<p>Invalid Credentials</p>
		</div>
	</c:if>
	<c:if test="${param.logout != null}">
		<div class="alert alert-success m-0">
			<p>Logged Out Successfully</p>
		</div>
	</c:if>

	<div class="row wrapper">
		<div class="wrapper fadeInDown container-fluid">
			<div id="formContent">
				<!-- Tabs Titles -->

				<!-- Icon -->
				<div class="fadeIn first pt-4 pb-4">
					<img
						src="${pageContext.request.contextPath}/static/img/Iceico_logo.png"
						id="icon" alt="User Icon" width="100px" />
				</div>

				<!-- Login Form -->
				<form action="${loginUrl}" method="post">
					<input type="text" id="login" class="form-group fadeIn second"
						name="ssoId" placeholder="Username"> <input
						type="password" id="password" class="form-group fadeIn third"
						name="password" placeholder="Password"> 
					<label style="width:100%" class="text-center pt-4 pb-3 text-secondary"> <input type="checkbox" name="remember-me"
						id="rememberme"> Remember Me
					</label><input
						type="submit" class="form-group fadeIn fourth" value="Log In">
				</form>

				<!-- Remind Passowrd -->
				<%-- <div id="formFooter">
					<a class="underlineHover" href="${pageContext.request.contextPath }/forgot/password">Forgot Password?</a>
				</div> --%>

			</div>
		</div>
	</div>
</body>

</html>
<!-- end document-->