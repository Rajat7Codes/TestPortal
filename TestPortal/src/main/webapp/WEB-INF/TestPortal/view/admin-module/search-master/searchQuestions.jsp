<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>
<body>
	<div class="content-i">
		<div class="content-box">
			<div class="element-wrapper">
				<div class="element-box">
					<form:form action="#" modelAttribute="questionBank" method="get"
						enctype="multipart/form-data">
						<h6 class="element-header mb-5">Search Bank</h6>
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group">
									<form:label path="subject">Subject</form:label>
									<form:select class="form-control" name="subject" path="subject"
										id="subject">
										<form:option value="" label="--- Select Subject ---" />
										<form:options items="${subjectList}" itemValue="subjectId"
											itemLabel="subjectName" id="subject" multiple="single" />
									</form:select>
									<form:errors path="subject" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<form:label path="questionType">Question Type</form:label>
									<form:select class="form-control" name="questionType"
										path="questionType" id="questionType">
										<form:option value="" label="--- Select Question Type ---" />
										<form:options items="${questionTypeList}"
											itemValue="questionTypeId" itemLabel="type"
											id="categoryOption" multiple="single" />
									</form:select>
									<form:errors path="questionType" />
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group" id="byMarks">
									<label> Marks</label> <input class="form-control"
										placeholder="Enter Marks" type="text" id="marks" name="marks">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 text-center mb-4 mt-4">
								<div class="form-group">
									<button class="btn btn-primary" type="button"
										onclick="all_type_submit();" id="btnByAllType">Submit</button>
								</div>
							</div>
						</div>
					</form:form>
					<div id="tableToggle" style="display: none;">
						<div class="table-responsive">
							<table id="example1" width="100%"
								class="table table-striped table-lightfont">
								<thead>
									<tr>
										<th>Sr. No</th>
										<th>Question Id</th>
										<th>Subject</th>
										<th>Type</th>
										<th>Question</th>
										<th>Marks</th>
										<!-- <th>Action</th> -->
									</tr>
								</thead>
								<tbody id="wantThatBody">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function all_type_submit() {
		document.getElementById('tableToggle').style.display = 'block';
		var mark = $("#marks").val();
		if (mark == "") {
			mark = 0;
			document.getElementById('marks').value = mark;
		}

		data = {
			"type" : $("#questionType").val(),
			"marks" : $("#marks").val(),
			"subject" : $("#subject").val()
		};
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/question/bank/type/all",
			data : data,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(response) {
				var trHTML = '';
				//alert("response =========>>" + JSON.stringify(response));
				$.each(response, function(i, item) {
					//	alert("inside =====>");
					trHTML += '<tr><td>' + "&nbsp;" + (i + 1) + '</td><td>'
							+ "&nbsp;" + item.questionBankId + '</td><td>'
							+ "&nbsp;" + item.subject + '</td><td>' + "&nbsp;"
							+ item.quetionType + '</td><td>' + "&nbsp;"
							+ item.question + '</td><td>' + "&nbsp;"
							+ item.marks + '</td></tr>';
				});
				$('#wantThatBody tr td').remove();
				$('#wantThatBody').append(trHTML);
			}
		});
	}
</script>
</html>