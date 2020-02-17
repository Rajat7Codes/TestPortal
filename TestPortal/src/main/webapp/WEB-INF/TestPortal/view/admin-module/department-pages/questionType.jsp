<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<title>Question_Types</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-4">
			<div class="card">
				<div class="card-body">
					<div class="element-wrapper">
						<div class="element-box">
							<form:form
								action="${pageContext.request.contextPath }/admin/questionType/save"
								modelAttribute="questionType" name="questionType"
								id="questionTypeForm" method="post">

								<form:hidden path="questionTypeId" />

								<div class="form-desc">
									<h4>ADD QUESTION TYPE</h4>
									<hr>
								</div>

								<div class="row form-group">
									<div class="col-md-12 col-sm-12 col-lg-12 col-12">
										<form:label path="type"> Question Type</form:label>
										<form:input path="type" name="type" id="type"
											class="form-control" placeholder="Enter Question type" />
										<form:errors path="type"></form:errors>
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
								</div>
								<br>
								<div class="form-buttons-w">
									<button class="btn btn-secondary" type="submit">Reset</button>
									<button class="btn btn-primary" type="submit">Submit</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class=" col-lg-8">
			<div class="card">
				<div class="card-body">
					<div class="content-box">
						<div class="element-wrapper">
							<div class="element-box">
								<div class="form-desc">
									<h4 class="form-header">QUESTION TYPE LIST</h4>
									<hr>
								</div>
								<div class="table-responsive">
									<table id="dataTable1"
										class="table table-striped table-lightfont">
										<thead>
											<tr>

												<th>Sr. No.</th>
												<th class="type">Question Type</th>
												<th class="status">status</th>
												<th class="action">Action</th>
											</tr>
										</thead>
										<tbody id="tableBody">
											<c:forEach var="questionType" items="${questionTypeList}"
												varStatus="ind">

												<tr>
													<td>${ind.index+1}</td>
													<td class="questionType">${questionType.type}</td>
													<c:if test="${questionType.status == true}">
														<td class="status"><c:out value="Active" /></td>
													</c:if>
													<c:if test="${questionType.status == false}">
														<td class="status"><c:out value="Inactive" /></td>
													</c:if>

													<td class="valigntop"><div class="btn-group">
															<button
																class="btn btn-xs btn-success dropdown-toggle no-margin"
																type="button" data-toggle="dropdown"
																aria-expanded="false">
																Actions <i class="fa fa-angle-down"></i>
															</button>
															<ul class="dropdown-menu pull-left" role="menu">
																<li><a title="Delete"
																	href="<c:url value='/admin/questionType/delete/${questionType.questionTypeId }' />"><i
																		class="fa fa-trash"></i>Delete</a></li>
																<li><a title="Edit"
																	href="<c:url value='/admin/questionType/edit/${questionType.questionTypeId }' />"><i
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

<script type="text/javascript">
	$(document).ready(function() {
		$('#dataTable1').DataTable();
	});
</script>
</html>