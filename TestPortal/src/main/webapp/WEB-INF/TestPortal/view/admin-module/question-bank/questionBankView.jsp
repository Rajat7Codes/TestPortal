<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">
				<h6 class="element-header">Question Bank View</h6>
				<div class="element-box">
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
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="questionBank" items="${questionBankList }"
									varStatus="ind">
									<tr>
										<td>${ind.index+1}</td>
										<td>${questionBank.getQuestionType().type }</td>
										<td>${questionBank.question }</td>
										<td>${questionBank.subject }</td>
										<td>${questionBank.marks }</td>
										<td class="valigntop"><div class="btn-group">
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
</body>
</html>