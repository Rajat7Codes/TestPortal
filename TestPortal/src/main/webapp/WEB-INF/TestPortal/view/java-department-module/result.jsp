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
					<h5 class="element-header mb-4">Test Result</h5>

					<!-- 	<div class="element-wrapper">
						<div class="element-box-tp">

							<div class="row">
								<div class="col-sm-3">Total Marks</div>
								<div class="col-sm-3">Marks Obtained</div>
								<div class="col-sm-3">Attempts</div>
								<div class="col-sm-3">Result</div>
							</div>
							<div class="row">
								<div class="col-sm-3 type='input'">Total Marks</div>
								<div class="col-sm-3">Marks Obtained</div>
								<div class="col-sm-3">Attempts</div>
								<div class="col-sm-3">Result</div>
							</div>

						</div>
					</div> -->


					<div class="element-content">
						<div class="row">
							<div class="col-sm-3 ">
								<span class="element-box el-tablo"><div class="label text-dark">Total
										Marks</div>
									<div class="font-weight-bold">20</div> </span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo"><div class="label text-dark">Marks
										Obtained</div>
									<div class="font-weight-bold">10</div> </span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo"><div class="label text-dark">Attempts</div>
									<div class="font-weight-bold">2</div> </span>
							</div>
							<div class="col-sm-3">
								<span class="element-box el-tablo"><div class="label text-dark">Result</div>
									<div class="font-weight-bold">PASS</div> </span>
							</div>

						</div>
					</div>
					<div>
						<table class="table text-center">
							<thead>
								<tr>
									<th>Question</th>
									<th>Selected Option</th>
									<th>Correct Option</th>

								</tr>
							</thead>
							<tbody>

								<td>question here..</td>
								<td>your option</td>
								<td>correct option</td>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>