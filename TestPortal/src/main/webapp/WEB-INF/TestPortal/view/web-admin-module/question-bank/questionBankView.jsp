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
							<h6 class="">Question Bank View</h6>
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
								class="table table-striped table-lightfont">
								<thead>
									<tr>
										<th>Sr. No</th>
										<th>Type</th>
										<th>Question</th>
										<th>subject</th>
										<th>Marks</th>
										<!-- <th>Action</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="questionBank" items="${questionBankList }"
										varStatus="ind">
										<c:if
											test="${questionBank.departmentName==user.department.departmentName}">
											<tr>
												<td>${ind.index+1}</td>
												<td>${questionBank.getQuestionType().type }</td>
												<td>${questionBank.question }</td>
												<td>${questionBank.getSubject().subjectName }</td>
												<td>${questionBank.marks }</td>
												<%-- <td class="valigntop"><div class="btn-group">
													<button
														class="btn btn-xs btn-success dropdown-toggle no-margin"
														type="button" data-toggle="dropdown" aria-expanded="false">
														Actions <i class="fa fa-angle-down"></i>
													</button>
													<ul class="dropdown-menu pull-left" role="menu">
														<li><a title="Delete"
															href="<c:url value='/admin/question/bank/edit/${questionBank.questionBankId}' />"><i
																class="fa fa-trash"></i>Edit</a></li>
														<li><a title="Edit"
															href="<c:url value='/admin/question/bank/delete/${questionBank.questionBankId}' />"><i
																class="fa fa-edit"></i>Delete</a></li>
													</ul>
												</div></td> --%>
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