<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Test List</title>
</head>
<body>

	<div class="row">
		<div class="col-sm-12">
			<div class="card">
				<div class="card-body">

					<div class="col-sm-12 col-xxxl-9">
						<div class="element-wrapper">
							<h6 class="element-header mb-5">Test History List</h6>
							<div class="element-box">
								<table class="table text-center">
									<thead>
										<tr>
											<th>Sr No.</th>
											<th>Test name</th>
											<th>Result</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="test" items="${resultList}" varStatus="ind">
											<c:if test="${test.userId==user.id}">
												<tr>
													<td>${ind.index+1}</td>
													<td>${test.testName }</td>
													<td>${test.resultStatus }</td>
													<td><a class="text-decoration-none !important"
														href="${pageContext.request.contextPath }/web/student/view/test/result/${test.testResultId}">View</a></td>
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
	</div>

	<%-- 
	<div class="row">
		<div class="col-sm-12">
			<div class="card">
				<div class="card-body">

					<div class="element-wrapper">
						<div class="element-box">
							<h5 class="element-header mb-4">Test History List</h5>
							<table class="table text-center">
								<thead>
									<tr>
										<th>Sr No.</th>
										<th>Test name</th>
										<th>Result</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="test" items="${resultList}" varStatus="ind">
										<tr>
											<td>${ind.index+1}</td>
											<td>${test.testName }</td>
											<td>${test.resultStatus }</td>
											<td><a class="text-decoration-none !important"
												href="${pageContext.request.contextPath }/web/student/view/test/result/${test.testResultId}">View</a></td>
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
	</div> --%>
</body>
</html>