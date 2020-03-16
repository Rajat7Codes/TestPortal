<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

<title>Start Test</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script> -->

</head>
<body>

	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-sm-4">
					<div class="element-wrapper">
						<div class="element-box">

							<div class="card text-center">

								<h1>${ addTest.time }</h1>

							</div>
							<br>
							<div class="card text-center form-group">

								<button class="btn btn-warning form-control font-weight-bold"
									type="submit">SUBMIT TEST</button>
							</div>
							<hr>
							<div class="card text-center">
								<div class="card-header font-weight-bold">${ addTest.testName }</div>

								<div class="card-body">
									<div class="form-group">
										<div class="row">
											<c:forEach varStatus="ind" var="question" items="testQuestions">
												<div class="col-2 p-0">
													<button class="btn btn-dark border border rounded-circle"
														onclick="getQuestion(${question.testQuestionId})">${ind.index}</button>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<!-- <div class="element-wrapper">
						<div class="element-box">
							<div class="row">
								<div class="col-sm-6">
									<span class="badge badge-pill badge-dark px-3 py-2">Question
										01</span>
								</div>
								<div class="col-sm-6 text-right">
									<span class="badge badge-pill badge-dark px-3 py-2 ">2
										marks </span>
								</div>
							</div>
							<hr>
							<div class="form-group p-2 text-justify font-weight-normal">
								<p>your question here: what's a questions???? your question
									here: what's a questions???? your question here: what's a
									questions???? your question here: what's a questions????</p>
							</div>
							<div class="form-group p-2">

								<div class="form-check">
									<label class="form-check-label"> <input type="radio"
										class="form-check-input" name="optradio">Option 1
									</label>
								</div>
								<div class="form-check">
									<label class="form-check-label"> <input type="radio"
										class="form-check-input" name="optradio">Option 2
									</label>
								</div>
								<div class="form-check">
									<label class="form-check-label"> <input type="radio"
										class="form-check-input" name="optradio">Option 1
									</label>
								</div>
								<div class="form-check">
									<label class="form-check-label"> <input type="radio"
										class="form-check-input border-success" name="optradio">Option
										2
									</label>
								</div>
							</div>
							<hr>
							<div class="form-group text-right">
								<a class="btn btn-dark font-weight-bold px-4" href="#">next</a>
							</div>
						</div>
					</div> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>