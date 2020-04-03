<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

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
								<input id="questionsJson" name="questionsJson" type="hidden">

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
											<form:label path="subject">Subject</form:label>
											<form:select class="form-control" name="subject"
												path="subject" onchange="filterBySubject()" id="subject">
												<form:option value="" label="--- Select Subject ---" />
												<form:options items="${subjectList}" itemValue="subjectId"
													itemLabel="subjectName" id="subject" multiple="single" />
											</form:select>
											<form:errors path="subject" />
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-sm-3">
										<div class="form-group">
											<form:label path="negativeMarking">Negative Marking</form:label>
											<form:select path="negativeMarking" name="negativeMarking"
												id="negativeMarking" class="form-control">
												<form:option selected="true" value="true">Yes</form:option>
												<form:option value="false">No</form:option>
											</form:select>
											<form:errors path="negativeMarking"></form:errors>
										</div>
									</div>

									<c:if test="${ addTest.negativeMarking==true }">
										<div class="col-sm-3" id="ratioDiv">
											<div class="form-group">
												<form:label path="ratio"> Ratio</form:label>
												<form:input path="ratio" name="ratio" id="ratio"
													class="form-control"
													placeholder="Enter negative marking ratio" />
												<form:errors path="ratio"></form:errors>
											</div>
										</div>
									</c:if>

									<c:if test="${ addTest.negativeMarking==null }">
										<div class="col-sm-3" id="ratioDiv">
											<div class="form-group">
												<form:label path="ratio"> Ratio</form:label>
												<form:input path="ratio" name="ratio" id="ratio"
													class="form-control"
													placeholder="Enter negative marking ratio" />
												<form:errors path="ratio"></form:errors>
											</div>
										</div>
									</c:if>


									<c:if test="${ addTest.negativeMarking==false }">
										<div class="col-sm-3" style="display: none" id="ratioDiv">
											<div class="form-group">
												<form:label path="ratio"> Ratio</form:label>
												<form:input path="ratio" name="ratio" id="ratio"
													class="form-control"
													placeholder="Enter negative marking ratio" />
												<form:errors path="ratio"></form:errors>
											</div>
										</div>
									</c:if>

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
								<div class="element-wrapper">
									<div class="element-box" style="display: none;"
										id="questions-table">
										<h6 class="element-header mb-5">Questions List</h6>

										<div class="table-responsive">
											<table id="questionListTable"
												class="table table-striped text-center">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>Question</th>
														<th>Question Type</th>
														<th class="text-center">Marks</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody id="tableBody">
												</tbody>
											</table>
										</div>
									</div>
									<div class="form-buttons-w">
										<button class="btn btn-secondary" onclick="addCheckbtn()"
											type="button">Reset</button>
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


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<c:if test="${edit==false}">

<!-- Script triggered on change of subject -->
<script type="text/javascript">
	function filterBySubject() {
		$('#tableBody tr').remove();
		data = {
			"subjectID" : $("#subject").val()
		};
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/add/test/filter/subject",
			data : data,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(response) {
				var trHTML = '';
				$.each(response, function(i, item) {
					trHTML += '<tr><td>' + item.questionId + '</td><td>'
							+ item.question + '</td><td>' + item.questionType
							+ '</td><td>' + item.marks + '</td><td>'
							+ '<button type="button" id="addbtn'
							+ (item.questionId)
							+ '" class="btn btn-primary" onclick="add('
							+ (item.questionId)
							+ ');"> Add </button> </td> </tr>';
				});
				$('#tableBody tr td').remove();
				$('#tableBody').append(trHTML);
			}
		});
		$("#questions-table").css("display", "block");
	}
</script>
</c:if>
<!-- Script for toggling ratioDiv on negative marking change -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#negativeMarking").change(function() {
			$("#ratioDiv").toggle();
		});
	});
</script>

<!-- Script for changing add btn to added -->
<script type="text/javascript">
	function add(a) {
		var addBtn = document.getElementById('addbtn' + a);
		addBtn.innerText = "Added";
		addBtn.classList.remove("btn-primary");
		addBtn.classList.add("btn-success");
	}
</script>

<!-- Script for sending json to hidden fields -->
<script type="text/javascript">
	document.getElementById("addTestForm").onsubmit = function() {
		var jsonArr = [];
		var tableBody = document.getElementById("tableBody");
		var rowCount = tableBody.rows.length;
		for (var i = 0; i < rowCount; i++) {
			var addedCheck = tableBody.rows[i].cells[4].children[0].innerHTML;
			if (addedCheck == "Added") {
				var jsonobj = {
					"questionId" : tableBody.rows[i].cells[0].innerHTML
				}
				jsonArr.push(jsonobj);
			}
		}
		var data = document.getElementById('questionsJson');
		data.value = JSON.stringify(jsonArr);
	};
</script>
</html>
