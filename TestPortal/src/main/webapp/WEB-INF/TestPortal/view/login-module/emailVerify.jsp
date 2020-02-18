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


<link href="${pageContext.request.contextPath}/static/css/main5739.css"
	rel="stylesheet" type="text/css">

<style>
.logo-w {
	padding: 40px 100px !important
}

.reg-form {
	padding-bottom: 30px !important
}

.auth-box-w {
	margin: auto !important
}
</style>

</head>


<body class="auth-wrapper">
	<div class="all-wrapper menu-side with-pattern">
		<div class="auth-box-w">
			<div class="logo-w">
				<a href="index-2.html"><img width="200px" alt=""
					src="${pageContext.request.contextPath}/static/img/Iceico_logo.png"></a>
			</div>
			<h4 class="auth-header">Verify Email</h4>

			<!-- Verification Form -->
			<form action="${pageContext.request.contextPath}/verify/email"
				method="post">
				<div class="form-group">
					<label for="">OTP</label> 

					<input id="fname" value="${ firstName }" name="fname" type="hidden">
					<input id="lname" value="${ lastName }"  name="lname" type="hidden">
					<input id="emailId" value="${ email }"  name="emailId" type="hidden">
					<input id="mobile" value="${ mobile }"  name="mobile" type="hidden">
					
					<%-- <input id="department" value="${ user.department }"  name="department" type="hidden">
					<input id="position" value="${ user.position }"  name="position" type="hidden">
					<input id="img" value="${ user.image }"  name="image" type="hidden"> --%>
					<input id="password" value="${ password }" name="password" type="hidden">

					<input class="form-control" id="otp" name="otp" placeholder="Enter OTP">
				</div>
				<div class="buttons-w">
					<button type="button" onclick="register()" class="btn btn-primary">Register</button>
				</div>
			</form>
		</div>
	</div>
</body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function register() {

		var otp = document.getElementById('otp').value;
		
		if(otp==${otp}) {
			var fName = $('#fname').val();
			var lName = $('#lname').val();
			var email = $('#emailId').val();
			var mobile = $('#mobile').val();
			/* var department = $("#department").val();
			var position = $('#position').val();
			var image = $('#img').val(); */
			var password = $('#password').val();

			
			var registerDetails = {
				'fname' : fName,
				'lname' : lName,
				'email' : email,
				'pass' : password,
				'mob' : mobile
				/* 'department' : department,
				'position' : position,
				'image' : image */
			};
			

			
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/register/user",
				data : registerDetails,
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(response) {
					alert(response)
					window.location.href = "${pageContext.request.contextPath}/login";
				}
			});
		} else {
			alert("OTP is invalid")
		}
	}
</script>

</html>
<!-- end document-->




