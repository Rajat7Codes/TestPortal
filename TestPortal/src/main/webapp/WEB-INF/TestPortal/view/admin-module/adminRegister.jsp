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
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">New Admin</h5>
				<div class="element-wrapper">
					<div class="element-box">

						<form id="registerForm" class="reg-form"
							action="${pageContext.request.contextPath }/admin/save"
							data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
							data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
							data-bv-feedbackicons-validating="glyphicon glyphicon-refresh"
							method="POST" enctype="multipart/form-data">

							<div class="row">
								<div class="col-sm-6">

									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label> First Name </label> <input id="fname" name="fname"
													type="text" class="form-control"
													<c:if test="${edit==true}"> value="${user.firstName}"</c:if>
													placeholder="Enter first name" data-bv-regexp="true"
													data-bv-regexp-regexp="^[a-zA-Z]*$"
													data-bv-regexp-message="Alphabets without spaces only">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label> Last Name </label> <input class="form-control"
													placeholder="Enter last name" id="lname" name="lname"
													<c:if test="${edit==true}"> value="${user.lastName}"</c:if>
													data-bv-regexp="true" data-bv-regexp-regexp="^[a-zA-Z]*$"
													data-bv-regexp-message="Alphabets without spaces only"
													type="text">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="w-100"> Username </label> <input
											class="form-control" placeholder="Enter username" id="ssoId"
											<c:if test="${edit==true}"> value="${user.ssoId}" readonly="readonly"</c:if>
											 name="ssoId" type="text" data-bv-regexp="true"
											data-bv-regexp-regexp="^[a-zA-Z]*$"
											data-bv-regexp-message="Alphabets without spaces only" />

									</div>
									<div class="form-group">
										<label class="w-100"> Email Address </label> <input
											class="form-control" placeholder="Enter email" id="emailId"
											<c:if test="${edit==true}"> value="${user.email}"</c:if>
											name="emailId" type="email">

									</div>
									<div class="form-group">
										<label> Phone Number</label> <input class="form-control"
											name="mobile" id="mobile" placeholder="Enter mobile number"
											<c:if test="${edit==true}"> value="${user.mobileNumber}"</c:if>
											type="number">
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<label> Gender</label> <select class="form-control" value=""
											id="gender" name="gender">
											<c:if test="${edit==false}">
												<option value="Female">Female</option>
												<option value="Male">Male</option>
												<option value="Transgender">Transgender</option>
											</c:if>
											<c:if test="${edit==true}">
												<option
													<c:if test="${user.gender==\"Female\"}">
											selected
											</c:if>
													value="Female">Female</option>

												<option
													<c:if test="${user.gender==\"Male\"}">
											selected
											</c:if>
													value="Male">Male</option>

												<option
													<c:if test="${user.gender==\"Transgender\"}">
											selected
											</c:if>
													value="Transgender">Transgender</option>
											</c:if>
										</select>
									</div>
									<div class="form-group">
										<label> Department</label> <select class="form-control"
											id="department" name="department">
											<c:if test="${edit==false}">
												<c:forEach var="department" items="${departmentList}">
													<option value="${department.departmentId}">${department.departmentName}</option>
												</c:forEach>
											</c:if>
											<c:if test="${edit==true}">
												<c:forEach var="department" items="${departmentList}">
													<c:if
														test="${user.department.departmentId==department.departmentId}">
														<option selected value="${department.departmentId}">${department.departmentName}</option>
													</c:if>
													<c:if
														test="${user.department.departmentId!=department.departmentId}">
														<option value="${department.departmentId}">${department.departmentName}</option>
													</c:if>

												</c:forEach>
											</c:if>
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
												<label>Confirm Password</label> <input id="confirmPassword"
													name="confirmPassword" class="form-control"
													placeholder="Password" type="password"
													data-bv-identical="true" data-bv-identical-field="password"
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
					<h5 class="element-header mt-5">Admin List</h5>
					<div class="element-box">
						<div class="table-responsive">
							<table class="table table-striped table-lightfont">
								<thead>
									<tr>
										<th>Id</th>
										<th>Name</th>
										<th>Username</th>
										<th>Department</th>
										<th>Email-ID</th>
										<th>Position</th>
										<th>Contact #</th>
										<th class=" text-center">Action</th>
									</tr>
								</thead>
								<tbody id="tableBody">
									<c:forEach var="user" items="${userList }" varStatus="ind">
										<c:if test="${!user.isDeleted}">
											<tr>
												<td>${ind.index+1 }</td>
												<td>${user.firstName }&nbsp;${user.lastName }</td>
												<td>${user.ssoId }</td>
												<td>${user.department.departmentName }</td>
												<td>${user.email }</td>
												<td>${user.position }</td>
												<td>${user.mobileNumber }</td>
												<td class="row-actions"><a title="Edit"
													href="<c:url value='/admin/edit/${user.id }' />"><i
														class="os-icon os-icon-ui-49"></i></a> <a class="danger"
													title="Delete"
													href="<c:url value='/admin/delete/${user.id }' />"><i
														class="os-icon os-icon-ui-15"></i></a></td>
											</tr>
										</c:if>
									</c:forEach>
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