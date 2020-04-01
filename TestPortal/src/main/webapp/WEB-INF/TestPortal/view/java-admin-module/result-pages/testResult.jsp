<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Test Result</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">Test Results</h5>
				<div class="element-wrapper">
					<div class="element-box">

						<div class="table-responsive">
							<table id="" width="100%"
								class="table table-striped table-lightfont text-center">
								<thead>
									<tr>
										<th>Sr No.</th>
										<th>Test name</th>
										<th>date</th>
										<th>Time</th>
										<th>Passing %</th>
										<th>Subject</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="test" items="${testList}" varStatus="ind">
										<c:if test="${ test.isDeleted !=true }">
											<tr>
												<td>${ind.index+1}</td>
												<td>${test.testName }</td>
												<td>${test.date}</td>
												<td>${test.time }</td>
												<td>${test.passingPercent }</td>
												<td>${test.subject.subjectName }</td>
												<td><a class="row-actions" title="View Result"
													href="<c:url value='/java/admin/test/result/${test.addTestId}' />">
														<i class="os-icon os-icon-window-content"></i>
												</a></td>
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