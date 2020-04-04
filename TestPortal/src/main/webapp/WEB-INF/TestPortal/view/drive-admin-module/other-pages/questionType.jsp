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
									action="${pageContext.request.contextPath }/drive/admin/questionType/save"
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
										<button class="btn btn-secondary" type="reset">Reset</button>
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
									<table class="table table-striped table-lightfont text-center">
										<thead>
											<tr>
												<th>Sr. No.</th>
												<th>Question Type</th>
												<th>Status</th>
												<th>Program Status</th>
												<th>Image Status</th>
												<th >Action</th>
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

													<td class="row-actions"><a title="Edit"
														href="<c:url value='/drive/admin/questionType/edit/${questionType.questionTypeId }' />"><i
															class="os-icon os-icon-ui-49"></i></a> <a class="danger"
														title="Delete"
														href="<c:url value='/drive/admin/questionType/delete/${questionType.questionTypeId }' />"><i
															class="os-icon os-icon-ui-15"></i></a></td>
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