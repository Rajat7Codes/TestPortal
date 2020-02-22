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
				<h6 class="element-header">Data Tables</h6>
				<div class="element-box">
					<!-- <h5 class="form-header">Powerful Datatables</h5> -->
					<!-- <div class="form-desc">
						DataTables is a plug-in for the jQuery Javascript library. It is a
						highly flexible tool, based upon the foundations of progressive
						enhancement, and will add advanced interaction controls to any
						HTML table.. <a href="https://www.datatables.net/" target="_blank">Learn
							More about DataTables</a>
					</div> -->
					<div class="table-responsive">
						<table id="example1" width="100%"
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
														href="<c:url value='/admin/question/bank/edit/${questionBankId}' />"><i
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

</body>
</html>