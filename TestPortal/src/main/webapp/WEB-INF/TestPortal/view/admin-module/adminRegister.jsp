<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#dataTable1_wrapper .row {
	width: 100%
}

#dataTable1_length label {
	align-self: flex-end;
}

#dataTable1_filter label {
	text-align: right !important;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div class="card">
				<div class="card-body">
					<div class="content-box">
						<div class="element-wrapper">
							<div class="element-box">
								<form id="registerForm" class="reg-form"
									action="${pageContext.request.contextPath }/admin/save"
									data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
									data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
									data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
									method="POST" enctype="multipart/form-data">

									<div class="form-desc mb-0">
										<h6 class="">New Admin</h6>
									</div>

									<div class="row">
										<div class="col-sm-6">

											<div class="row">
												<div class="col-sm-6">
													<div class="form-group">
														<label> First Name </label> <input id="fname" name="fname"
															type="text" class="form-control"
															placeholder="Enter first name" data-bv-regexp="true"
															data-bv-regexp-regexp="^[a-zA-Z]*$"
															data-bv-regexp-message="Alphabets without spaces only">
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label> Last Name </label> <input class="form-control"
															placeholder="Enter last name" id="lname" name="lname"
															data-bv-regexp="true" data-bv-regexp-regexp="^[a-zA-Z]*$"
															data-bv-regexp-message="Alphabets without spaces only"
															type="text">
													</div>
												</div>
											</div>
											<!-- ^[a-z\s]+$ -->
											<div class="form-group">
												<label class="w-100"> Username </label> <input
													class="form-control" placeholder="Enter username"
													id="ssoId" name="ssoId" type="text" data-bv-regexp="true"
													data-bv-regexp-regexp="^[a-zA-Z]*$"
													data-bv-regexp-message="Alphabets without spaces only" />

											</div>
											<div class="form-group">
												<label class="w-100"> Email address </label> <input
													class="form-control" placeholder="Enter email" id="emailId"
													name="emailId" type="email">

											</div>
											<div class="form-group">
												<label> Phone Number</label> <input class="form-control"
													name="mobile" id="mobile" placeholder="Enter mobile number"
													type="number">
											</div>
										</div>
										<div class="col-sm-6">

											<div class="form-group">
												<label> Gender</label> <select class="form-control"
													id="gender" name="gender">
													<option value="Female">Female</option>
													<option value="Male">Male</option>
													<option value="Transgender">Transgender</option>
												</select>
											</div>
											<div class="form-group">
												<label> Department</label> <select class="form-control"
													id="department" name="department">
													<c:forEach var="department" items="${departmentList}">
														<option value="${department.departmentId}">${department.departmentName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label>Position</label> <input class="form-control"
													placeholder="Enter position" id="position" name="position"
													type="text" data-bv-regexp="true"
													data-bv-regexp-regexp="^[a-zA-Z ]*$">
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="form-group">
														<label> Password</label> <input class="form-control"
															placeholder="Password" id="password" name="password"
															type="password" data-bv-identical="true"
															data-bv-identical-field="confirmPassword"
															data-bv-identical-message=" ">
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label>Confirm Password</label> <input
															id="confirmPassword" name="confirmPassword"
															class="form-control" placeholder="Password"
															type="password" data-bv-identical="true"
															data-bv-identical-field="password"
															data-bv-identical-message="Password mismatch">
													</div>
												</div>
											</div>
										</div>
										<input id="data" name="data" type="hidden">
										<div class="mt-3 text-center">
											<button id="reg" type="submit"
												class="btn btn-primary form-control">
												<b>Register</b>
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="card">
				<div class="card-body">
					<div class="content-box">
						<div class="element-wrapper">
							<div class="element-box">

								<div class="form-desc mb-0">
									<h6 class="">Admin List</h6>
								</div>
								<div class="table-responsive">
									<table id="dataTable1"
										class="table table-striped table-lightfont">
										<thead>

										</thead>
										<tbody id="tableBody">											

										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>