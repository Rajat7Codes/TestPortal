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
	<div class="row">
		<div class="col-lg-12">
			<div class="element-wrapper">
				<h5 class="element-header">Question Bank List</h5>
				<div class="element-wrapper">
					<div class="element-box">

						<div class="table-responsive">
							<table class="table table-striped table-lightfont text-center">
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

												<%-- <td class="row-actions"><a title="Edit"
												href="<c:url value='/admin/question/bank/edit/${questionBank.questionBankId}' />"><i
													class="os-icon os-icon-ui-49"></i></a> <a class="danger"
												title="Delete"
												href="<c:url value='/java/admin/questionType/delete/${questionType.questionTypeId }' />"><i
													class="os-icon os-icon-ui-15"></i></a></td> --%>
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