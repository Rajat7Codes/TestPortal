<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<title>Add Test</title>
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-lg-12">
					<div class="element-wrapper">
						<div class="element-box">
							<form:form
								action="${pageContext.request.contextPath }/admin/add/test/save"
								modelAttribute="addTest" name="addTest" id="addTestForm"
								method="post">

								<form:hidden path="addTestId" />

								<h6 class="element-header mb-5">Add Test</h6>
								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="testName"> Test Name </form:label>
											<form:input path="testName" name="testName" id="testName"
												class="form-control" placeholder="Enter Test Name" />
											<form:errors path="testName"></form:errors>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="date">Date</form:label>
											<form:input path="date" type="date" name="date" id="date"
												class="form-control" />
											<form:errors path="date"></form:errors>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="time">Time</form:label>
											<form:input path="time" name="time" id="time"
												class="form-control" placeholder="Enter Time" />
											<form:errors path="time"></form:errors>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="subject"> Subject </form:label>
											<form:select class="form-control" name="subject"
												path="subject" id="subject" itemLabel="subjectName"
												items="${subjectList}" itemValue="subjectId">
											</form:select>
											<form:errors path="subject" class="errors" />
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="negativeMarking">Negative Marking</form:label>
											<form:select path="negativeMarking" name="negativeMarking"
												id="negativeMarking" class="form-control">
												<form:option value="true">Yes</form:option>
												<form:option value="false">No</form:option>
											</form:select>
											<form:errors path="negativeMarking"></form:errors>
										</div>
									</div>

									<div class="col-sm-3" id="ratioDiv">
										<div class="form-group">
											<form:label path="ratio"> Ratio</form:label>
											<form:input path="ratio" name="ratio" id="ratio"
												class="form-control"
												placeholder="Enter negative marking ratio" />
											<form:errors path="ratio"></form:errors>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="passingPercent"> Passing Percentage </form:label>
											<form:input path="passingPercent" name="passingPercent"
												id="passingPercent" class="form-control" value="50.0" />
											<form:errors path="passingPercent"></form:errors>
										</div>
									</div>
									<div class="col-sm-3">
										<form:label path="instructions">Instructions </form:label>
										<form:input path="instructions" name="instructions"
											id="instructions" class="form-control"
											placeholder="Enter Intructions here.." />
										<form:errors path="instructions"></form:errors>
									</div>

								</div>

								<!-- table -->
								<div class="element-wrapper d-none" id="questions-table">
									<div class="element-box">
										<h6 class="element-header mb-5">Questions List</h6>

										<div class="table-responsive">
											<table id="questionListTable" class="table table-striped">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>Question</th>
														<th>Question Type</th>
														<th class="text-center">Marks</th>
														<th class="text-right">Action</th>
													</tr>
												</thead>
												<tbody id="tableBody">
												</tbody>
											</table>
										</div>
									</div>
									<div class="form-buttons-w">
										<button class="btn btn-secondary" type="submit">Reset</button>
										<button class="btn btn-primary" type="submit">Submit</button>
									</div>
								</div>


							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#negativeMarking").change(function() {
			$("#ratioDiv").toggle();
		});
	});
</script>

<!-- AJAX script for subject -->
<script type="text/javascript">
	$("#subject").change(function() {
		alert("inside function");

		data = {

			"subjectID" : $("#subject").val()

		};

		alert(JSON.stringify(data));

		$

		.ajax({

			type : "POST",

			contentType : "application/json",

			url : "${pageContext.request.contextPath}/add/test/filter/subject",

			data : data,

			dataType : 'json',

			cache : false,

			timeout : 600000,

			success : function(response) {

				alert(JSON.stringify(response));

				var trHTML = '';

				$.each(

				response.questions,

				function(i, item) {

					trHTML += '<tr><td>' + "&nbsp;"

					+ (i + 1)

					+ '</td><td>'

					+ "&nbsp;"

					+ item.question

					+ '</td><td>'

					+ "&nbsp;"

					+ item.questionType

					+ '</td><td>'

					+ "&nbsp;"

					+ item.marks

					+ '</td><td>'

					+ "&nbsp;"

					+ ''

					+ '</td></tr>';

				});

				$('#questionListTable tr td').remove();
				$('#questionListTable').append(trHTML);

			}

		});

		$("#questions-table").css("display", "block !important");
	});
</script>
</html>