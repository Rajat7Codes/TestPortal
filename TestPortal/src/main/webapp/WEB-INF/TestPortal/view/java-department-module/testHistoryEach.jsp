<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Test List</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">

				<div class="element-box">
					<h5 class="element-header mb-4">Test History</h5>
					<div class="element-content">
						<div class="row">
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Test Name</div>
									<div class="font-weight-bold">${result.testName}</div>
								</span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Date</div>
									<div class="font-weight-bold">${result.date}</div>
								</span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Subject</div>
									<div class="font-weight-bold">${test.subject.subjectName}</div>
								</span>
							</div>
							<div class="col-sm-3">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Time</div>
									<div class="font-weight-bold">${test.time}</div>
								</span>
							</div>
						</div>
						<c:if test="${test.negativeMarking}">
							<div class="row">
								<div class="col-sm-3 ">
									<span class="element-box el-tablo bg-light">
										<div class="label text-dark">Negative Marking</div>
										<div class="font-weight-bold">Yes</div>
									</span>
								</div>
								<div class="col-sm-3 ">
									<span class="element-box el-tablo bg-light">
										<div class="label text-dark">Negative Ratio</div>
										<div class="font-weight-bold">${test.ratio}</div>
									</span>
								</div>
								<div class="col-sm-3 ">
									<span class="element-box el-tablo bg-light">
										<div class="label text-dark">Negative Marks</div>
										<div class="font-weight-bold">${result.negativeMarks}</div>
									</span>
								</div>
								<div class="col-sm-3">
									<span class="element-box el-tablo bg-light">
										<div class="label text-dark">Total Questions</div>
										<div class="font-weight-bold">${questionCount}</div>
									</span>
								</div>
							</div>
						</c:if>
						<div class="row">
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Total Marks</div>
									<div class="font-weight-bold">${result.totalMarks}</div>
								</span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Passing Percent</div>
									<div class="font-weight-bold">${test.passingPercent}</div>
								</span>
							</div>
							<div class="col-sm-3 ">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Marks Obtained</div>
									<div class="font-weight-bold">${result.obtainedMarks}</div>
								</span>
							</div>
							<div class="col-sm-3">
								<span class="element-box el-tablo bg-light">
									<div class="label text-dark">Result</div>
									<div class="font-weight-bold">${result.resultStatus}</div>
								</span>
							</div>
						</div>
					</div>
					<br><br>
					<table class="table text-center">
						<thead>
							<tr>
								<th>Sr No.</th>
								<th>Question</th>
								<th>Marks</th>
								<th>Correct Answer</th>
								<th>Your Answer</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="question" items="${questionList}" varStatus="ind">
								<tr>
									<td>${ind.index+1}</td>
									<td>${question.question }
									<c:if test="${question.questionType.programType==true}">
									<br>Input => ${question.hiddenInput}
									</c:if>
									</td>
									<td>${question.marks }</td>
									<c:if test="${question.questionType.programType!=true}">
										<c:forEach var="option" items="${question.options }">
											<c:if test="${option.correctAnswer==true}">
												<td>${option.optionName}</td>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${question.questionType.programType==true}">
										<td>${question.hiddenOutput}</td>
									</c:if>
									<td> ${answerList[ind.index]} </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>