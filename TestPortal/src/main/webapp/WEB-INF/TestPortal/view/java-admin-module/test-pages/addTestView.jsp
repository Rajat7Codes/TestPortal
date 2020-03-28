<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">
				<!-- <h6 class="element-header">Question Bank View</h6> -->
				<div class="element-box">
					<!-- <h6 class="">Question Bank</h6> -->
					<div class="row">
						<div class="col-sm-12 element-header">
							<h6 class="">Add Test View</h6>
							<%-- <a class="btn btn-primary float-right"
								href="<c:url value="/admin/question/bank/new" />">Question
								Bank New</a> --%>
							<!-- <button class="btn btn-primary float-right" type="button"></button> -->
						</div>

					</div>
					<!-- <h6 class="element-header mb-5">Question Bank</h6>
					<button class="btn btn-primary float-right" type="button">View</button> -->
					<div class="row">
						<div class="table-responsive">
							<table id="" width="100%"
								class="table table-striped table-lightfont text-center">
								<thead>
									<tr>
										<th>Sr. No</th>
										<th>Name</th>
										<th>Date</th>
										<th>Time</th>
										<th>Intructions</th>
										<th>Passing Percent</th>
										<th>Negative Marking</th>
										<th>Ratio</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="test" items="${testList }" varStatus="ind">
										<c:if test="${ test.isDeleted==false }">
											<tr>
												<td>${ind.index+1}</td>
												<td>${test.testName }</td>
												<td>${test.date }</td>
												<td>${test.time }</td>
												<td>${test.instructions }</td>
												<td>${test.passingPercent }</td>
												<td class="text-center"><c:if
														test="${test.negativeMarking==true}">
														<div class="status-pill green"
															data-title="Negative Marking" data-toggle="tooltip"
															data-original-title="" title=""></div>
													</c:if> <c:if test="${test.negativeMarking==false}">
														<div class="status-pill red"
															data-title="No Negative Marking" data-toggle="tooltip"
															data-original-title="" title=""></div>
													</c:if></td>
												<td>${test.ratio }</td>
												<td class="row-actions"><a
													href="<c:url value='/java/admin/add/test/edit/${test.addTestId}' />"><i
														class="os-icon os-icon-ui-49"></i></a> <a class="danger"
													href="<c:url value='/java/admin/add/test/delete/${test.addTestId}' />"><i
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