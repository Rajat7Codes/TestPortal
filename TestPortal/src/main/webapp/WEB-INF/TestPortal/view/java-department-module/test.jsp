<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

<title>Test List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script> -->
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">
				<div class="element-box">
					<div class="card border-0">
						<span class="font-weight-bold">TEST LIST</span>
					</div>
					<br>
					<table class="table font-weight-bold text-center">
						<tbody>
							<tr class="font-weight-bold">
								<th class="font-weight-bold">Test name</th>
								<th class="font-weight-bold">Time</th>
								<th class="font-weight-bold">date</th>
								<th class="font-weight-bold">Action</th>
							</tr>
							<tr>
								<td>Test_name_01</td>
								<td>05:00 min</td>
								<td>03/14/2020</td>
								<td><a class="btn btn-warning font-weight-bold py-0"
									href="${pageContext.request.contextPath }/java/student/dashboard/start/test">start
										test</a></td>
							</tr>
							<tr>
								<td>Test_name_01</td>
								<td>05:00 min</td>
								<td>03/14/2020</td>
								<td><a class="btn btn-warning font-weight-bold py-0"
									href="#">start test</a></td>
							</tr>
							<tr>
								<td>Test_name_01</td>
								<td>05:00 min</td>
								<td>03/14/2020</td>
								<td><a class="btn btn-warning font-weight-bold py-0"
									href="#">start test</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>