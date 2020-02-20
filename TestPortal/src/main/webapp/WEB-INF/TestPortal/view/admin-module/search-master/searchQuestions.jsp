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
				<h6 class="element-header">Search Data</h6>
				<div class="element-box">
					<form:form action="#" modelAttribute="questionBank" method="get"
						enctype="multipart/form-data">
						<div class="row">
							<div class="text-center p-4">
								<div class="form-check-inline">
									<label class="form-check-label"> <input type="radio"
										id="subjectWiseRadio"
										onclick="if(this.checked){ searchOption(); }"
										name="search-type"><strong>By Subject</strong>
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"> <input type="radio"
										id="typeWiseRadio" onclick="if(this.checked){ searchOption()}"
										name="search-type"> <strong>By Type</strong>
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"> <input type="radio"
										onclick="if(this.checked){ searchOption()}" name="search-type"
										id="marksWiseRadio"> <strong>By Marks</strong>
									</label>
								</div>
								<div class="form-check-inline">
									<label class="form-check-label"> <input type="radio"
										id="allWiseRadio" onclick="if(this.checked){ searchOption()}"
										name="search-type"> <strong>By All</strong>
									</label>
								</div>
							</div>
						</div>
						<div class="row" style="display: none;" id="searchRow">
							<div class="col-sm-3" id="bySubject">
								<div class="form-group">
									<label> Subject</label> <input class="form-control"
										placeholder="Enter subject" type="text" name="subject"
										id="subject">
								</div>
							</div>
							<div class="col-sm-3" id="byType">
								<div class="form-group">
									<form:label path="questionType">Question Type</form:label>
									<form:select class="form-control" name="questionType"
										path="questionType" id="questionType">
										<form:option value="" label="--- Select Category---" />
										<form:options items="${questionTypeList}"
											itemValue="questionTypeId" itemLabel="type"
											id="categoryOption" multiple="single" />
									</form:select>
									<form:errors path="questionType" />
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group" id="byMarks">
									<label> Marks</label> <input class="form-control"
										placeholder="Enter Marks" type="text" id="marks" name="marks">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 text-center mb-5 mt-5">
								<div class="form-group">
									<button class="btn btn-primary" type="button"
										onclick="question_type_submit();" id="btnByType"
										style="display: none;">Submit</button>

									<button class="btn btn-primary" type="button"
										onclick="subject_type_submit()" id="btnBySubject"
										style="display: none;">Submit</button>

									<button class="btn btn-primary" type="button"
										onclick="marks_type_submit();" id="btnByMarks"
										style="display: none;">Submit</button>

									<button class="btn btn-primary" type="button"
										onclick="all_type_submit();" id="btnByAllType"
										style="display: none;">Submit</button>
								</div>
							</div>
						</div>
					</form:form>
				</div>
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
</body>
<script>
	function searchOption() {
		if (document.getElementById('subjectWiseRadio').checked) {
			document.getElementById('searchRow').style.display = 'block';
			document.getElementById('bySubject').style.display = 'block';
			document.getElementById('byType').style.display = 'none';
			document.getElementById('byMarks').style.display = 'none';
			//document.getElementById('byAll').style.display = 'none';

			document.getElementById('btnBySubject').style.display = 'block';

			document.getElementById('btnByType').style.display = 'none';
			document.getElementById('btnByAllType').style.display = 'none';
			document.getElementById('btnByMarks').style.display = 'none';
			//document.getElementById('year').value = "";
		}
		if (document.getElementById('typeWiseRadio').checked) {
			document.getElementById('searchRow').style.display = 'block';
			document.getElementById('byType').style.display = 'block';
			document.getElementById('bySubject').style.display = 'none';
			document.getElementById('byMarks').style.display = 'none';
			//document.getElementById('byAll').style.display = 'none';

			document.getElementById('btnByType').style.display = 'block';

			document.getElementById('btnBySubject').style.display = 'none';
			document.getElementById('btnByAllType').style.display = 'none';
			document.getElementById('btnByMarks').style.display = 'none';
		}
		if (document.getElementById('marksWiseRadio').checked) {
			document.getElementById('searchRow').style.display = 'block';
			document.getElementById('byMarks').style.display = 'block';
			//document.getElementById('byAll').style.display = 'none';
			document.getElementById('bySubject').style.display = 'none';
			document.getElementById('byType').style.display = 'none';

			document.getElementById('btnByMarks').style.display = 'block';

			document.getElementById('btnBySubject').style.display = 'none';
			document.getElementById('btnByType').style.display = 'none';
			document.getElementById('btnByAllType').style.display = 'none';
		}
		if (document.getElementById('allWiseRadio').checked) {
			document.getElementById('searchRow').style.display = 'block';
			document.getElementById('byMarks').style.display = 'block';
			document.getElementById('bySubject').style.display = 'block';
			document.getElementById('byType').style.display = 'block';

			document.getElementById('btnByAllType').style.display = 'block';

			document.getElementById('btnBySubject').style.display = 'none';
			document.getElementById('btnByType').style.display = 'none';
			document.getElementById('btnByMarks').style.display = 'none';
		}

	}
</script>
<script type="text/javascript">
	function all_type_submit() {
		//alert("all_type_submit()");
		/* 
		 alert("questionType ========>> " + $("#questionType").val());
		 alert("marks ========>> " + $("#marks").val());
		 alert("subject ========>> " + $("#subject").val());
		 */
		var mark = $("#marks").val();
		//	alert("marks Before========>> " + $("#marks").val());
		if (mark == "") {
			//alert("inside mark empty==>")
			mark = 0;
			document.getElementById('marks').value = mark;
			//alert("Now marks ========>> " + $("#marks").val());
		}

		var subject = $("#subject").val();
		if (subject == "") {
			//alert("inside subject empty==>")
			subject = null;
			document.getElementById('subject').value = subject;
			//alert("Now subject ========>> " + $("#subject").val());
		}

		data = {
			"type" : $("#questionType").val(),
			"marks" : $("#marks").val(),
			"subject" : $("#subject").val()
		};
		//alert("data ========>> " + data);
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