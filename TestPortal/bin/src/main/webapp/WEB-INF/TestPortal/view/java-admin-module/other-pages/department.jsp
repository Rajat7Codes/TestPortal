<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Department</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">Department</h5>

				<div class="row">
					<div class="col-lg-4">
						<div class="element-wrapper">
							<div class="element-box">
								<form:form
									action="${pageContext.request.contextPath }/java/admin/department/save"
									modelAttribute="department" name="department"
									id="departmentForm" method="post">

									<form:hidden path="departmentId" />

									<h5 class="form-header">Add Department</h5>

									<div class="row form-group">
										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="departmentName"> Department Name </form:label>
											<form:input path="departmentName" name="departmentName"
												id="departmentName" class="form-control"
												placeholder="Enter Department Name" />
											<form:errors path="departmentName"></form:errors>
										</div>

										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="status">Status</form:label>
											<form:select path="status" name="status" id="status"
												class="form-control">
												<form:option value="true">Active</form:option>
												<form:option value="false">Inactive</form:option>
											</form:select>
											<form:errors path="status"></form:errors>
										</div>

										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="description">Description</form:label>
											<form:textarea path="description" name="description" rows="1"
												id="description" class="form-control"
												placeholder="Enter Description" />
											<form:errors path="description"></form:errors>
										</div>
									</div>
									<div class="form-buttons-w">
										<button class="btn btn-secondary" type="submit">Reset</button>
										<button class="btn btn-primary" type="submit">Submit</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>

					<div class=" col-lg-8">
						<div class="element-wrapper">
							<div class="element-box">
								<h5 class="form-header">Department List</h5>
								<div class="table-responsive">
									<table class="table table-striped table-lightfont">
										<thead>
											<tr>
												<th>Sr.No.</th>
												<th class="department_">Department Name</th>
												<th class="status_">status</th>
												<th class="description_">Description</th>
												<th class="action_">Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="department" items="${departmentList}"
												varStatus="ind">
												<tr>
													<td>${ind.index+1}</td>
													<td class="department_">${department.departmentName}</td>
													<c:if test="${department.status == true}">
														<td class="status"><c:out value="Active" /></td>
													</c:if>
													<c:if test="${department.status == false}">
														<td class="status"><c:out value="Inactive" /></td>
													</c:if>
													<td class="description_">${department.description }</td>
													<td class="valigntop"><div class="btn-group">
															<button
																class="btn btn-xs btn-success dropdown-toggle no-margin"
																type="button" data-toggle="dropdown"
																aria-expanded="false">
																Actions <i class="fa fa-angle-down"></i>
															</button>
															<ul class="dropdown-menu pull-left" role="menu">
																<li><a title="Delete"
																	href="<c:url value='/java/admin/department/delete/${department.departmentId }' />"><i
																		class="fa fa-trash"></i>Delete</a></li>
																<li><a title="Edit"
																	href="<c:url value='/java/admin/department/edit/${department.departmentId }' />"><i
																		class="fa fa-edit"></i>Edit</a></li>
															</ul>
														</div></td>
												</tr>
											</c:forEach>
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