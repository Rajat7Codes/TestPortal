<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Subject</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">Subject</h5>

				<div class="row">
					<div class="col-lg-4">
						<div class="element-wrapper">
							<div class="element-box">
								<form:form
									action="${pageContext.request.contextPath }/java/admin/questionType/save"
									modelAttribute="questionType" name="questionType"
									id="questionTypeForm" method="post">

									<form:hidden path="questionTypeId" />

									<h5 class="form-header">Add Question Type</h5>

									<div class="form-group">
										<form:label class="col-form-label" path="subjectName"> Subject Name</form:label>
										<form:input class="form-control"
											placeholder="Enter Subject Name" path="subjectName"
											name="subjectName" id="subjectName" type="text" />
									</div>

									<div class="form-group">
										<form:label class="col-form-label" path="status"> Status</form:label>
										<form:select path="status" name="status" id="status"
											class="form-control">
											<form:option value="true">Active</form:option>
											<form:option value="false">Inactive</form:option>
										</form:select>
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
								<h5 class="form-header">Question Type List</h5>
								<div class="table-responsive">
									<table class="table table-striped table-lightfont">
										<thead>
											<tr>
												<th>Id</th>
												<th class="subject_">subject Name</th>
												<th class="status_">Status</th>
												<th class="action_">Action</th>
											</tr>
										</thead>
										<tbody id="tableBody">
											<c:forEach var="subject" items="${subjectList}"
												varStatus="ind">
												<tr>
													<td class="id">${subject.subjectId}</td>
													<td class="subject_">${subject.subjectName}</td>
													<c:if test="${subject.status == true}">
														<td class="status"><c:out value="Active" /></td>
													</c:if>
													<c:if test="${subject.status == false}">
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
																	href="<c:url value='/java/admin/subject/delete/${subject.subjectId }' />"><i
																		class="fa fa-trash"></i>Delete</a></li>
																<li><a title="Edit"
																	href="<c:url value='/java/admin/subject/edit/${subject.subjectId }' />"><i
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


 --%>


























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
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">Question Type</h5>

				<div class="row">
					<div class="col-lg-4">
						<div class="element-wrapper">
							<div class="element-box">
								<form:form
									action="${pageContext.request.contextPath }/java/admin/questionType/save"
									modelAttribute="questionType" name="questionType"
									id="questionTypeForm" method="post">

									<form:hidden path="questionTypeId" />
									<h5 class="form-header">Add Question Type</h5>

									<div class=" form-group">
										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="type"> Question Type</form:label>
											<form:input path="type" name="type" id="type"
												class="form-control" placeholder="Enter Question type" />
											<form:errors path="type"></form:errors>
										</div>
									</div>
									<div class=" form-group">
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
									<div class=" form-group">
										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="programType">Program Status</form:label>
											<form:select path="programType" name="programType"
												id="programType" class="form-control">
												<form:option value="false">Inactive</form:option>
												<form:option value="true">Active</form:option>
											</form:select>
											<form:errors path="programType"></form:errors>
										</div>
									</div>
									<div class=" form-group">
										<div class="col-md-12 col-sm-12 col-lg-12 col-12">
											<form:label path="imageType">Image Status</form:label>
											<form:select path="imageType" name="imageType" id="imageType"
												class="form-control">
												<form:option value="false">Inactive</form:option>
												<form:option value="true">Active</form:option>
											</form:select>
											<form:errors path="imageType"></form:errors>
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
								<h5 class="form-header">Question Type List</h5>
								<div class="table-responsive">
									<table class="table table-striped table-lightfont">
										<thead>
											<tr>
												<th>Sr. No.</th>
												<th class="type">Question Type</th>
												<th class="status">Status</th>
												<th class="status">Program Status</th>
												<th class="status">Image Status</th>
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

													<c:if test="${questionType.programType == true}">
														<td class="status"><c:out value="Active" /></td>
													</c:if>
													<c:if test="${questionType.programType == false}">
														<td class="status"><c:out value="Inactive" /></td>
													</c:if>

													<c:if test="${questionType.imageType == true}">
														<td class="status"><c:out value="Active" /></td>
													</c:if>
													<c:if test="${questionType.imageType == false}">
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
																	href="<c:url value='/java/admin/questionType/delete/${questionType.questionTypeId }' />"><i
																		class="fa fa-trash"></i>Delete</a></li>
																<li><a title="Edit"
																	href="<c:url value='/java/admin/questionType/edit/${questionType.questionTypeId }' />"><i
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