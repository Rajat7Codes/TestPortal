<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Bank</title>
</head>
<body class="menu-position-side menu-side-left full-screen">
	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-lg-12">
					<div class="element-wrapper">
						<!-- <h6 class="element-header">Question Bank</h6> -->
						<div class="element-box">
							<form:form
								action="${pageContext.request.contextPath }/admin/question/bank/save"
								modelAttribute="questionBank">
								<h6 class="element-header mb-5">Question Bank</h6>
								<div class="form-group">
									<label for=""> Question</label><input class="form-control"
										placeholder="Enter a Question" type="text">
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="">Question Type</label>
											<form:select path="questionType" class="form-control"
												onchange="myToggle();" id="type">
												<form:option value="1">---Select Type---</form:option>
												<form:option value="2">Option_Four</form:option>
												<form:option value="3">Option_Two</form:option>
												<form:option value="4">Image_question</form:option>
												<form:option value="5">Write_Program</form:option>
												<%-- <c:forEach var="ty" items="${type }">
													<form:option value="">${ty }</form:option>
												</c:forEach> --%>

												<%-- <form:option value="">Select Type</form:option> --%>
												<%-- <form:options items="${questionType }" /> --%>
												<!-- <option>Option Two</option>
												<option>Image question</option>
												<option>Write Program</option> -->
											</form:select>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label for=""> Marks</label><input class="form-control"
												placeholder="Enter Marks" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="">Subject</label><input class="form-control"
										placeholder="Enter subject" type="text">
								</div>
								<div class="form-group">
									<label for=""> Description</label>
									<textarea class="form-control" rows="3"></textarea>
								</div>



								<!-- <div class="element-wrapper"> -->
								<h6 class="element-header mt-5">Options</h6>
								<!-- <div class="element-box"> -->
								<div class="form-inline mt-5">
									<label class=""> Options :</label><input
										class="form-control mb-2 mr-sm-2 mb-sm-0 col-sm-6 ml-5"
										placeholder="First Name"><label
										class="form-check-label text-center col-sm-3 mb-2"><input
										class="form-check-input" type="checkbox"> Correct</label>
									<button class="btn btn-primary" type="submit">Add</button>
								</div>
								<!-- </div> -->

								<div class="mb-5">
									<div class="table-responsive mb-5">
										<table id="example1" width="100%"
											class="table table-striped table-lightfont">
											<thead>
												<tr>
													<th>Sr. No</th>
													<th>Options</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Tiger Nixon</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- <fieldset class="form-group">
									<legend class="mb-5">
										<span>Section Example</span>
									</legend>
								</fieldset> -->
								<!-- <div class="form-inline">
										<div class="w-100 form-group row">
											<label class="col-sm-2 mb-2" for="">Option</label> <input
												type="text" name="" id=""
												class="col-sm-4 text-center mb-2 form-control"
												placeholder="" aria-describedby="helpId"> <label
												class="form-check-label text-center col-sm-3 mb-2"><input
												class="form-check-input" type="checkbox"> Correct</label>
											<button class="btn btn-primary col-sm-2 mb-2" type="submit">
												Submit</button>
										</div>
									</div> -->


								<!-- <h6 class="element-header mb-5">Data Tables</h6> -->
								<!-- <div class="element-box"> -->
								<!-- <div class="table-responsive">
									<table id="dataTable1" width="100%"
										class="table table-striped table-lightfont">
										<thead>
											<tr>
												<th>Name</th>
												<th>Position</th>
												<th>Office</th>
												<th>Age</th>
												<th>Start date</th>
												<th>Salary</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>Name</th>
												<th>Position</th>
												<th>Office</th>
												<th>Age</th>
												<th>Start date</th>
												<th>Salary</th>
											</tr>
										</tfoot>
										<tbody>
											<tr>
												<td>Tiger Nixon</td>
												<td>System Architect</td>
												<td>Edinburgh</td>
												<td>61</td>
												<td>2011/04/25</td>
												<td>$320,800</td>
											</tr>
											<tr>
												<td>Garrett Winters</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>63</td>
												<td>2011/07/25</td>
												<td>$170,750</td>
											</tr>

										</tbody>
									</table>
								</div> -->
								<!-- <div class="col-sm-12"> -->
								<div class="form-buttons-w text-center">
									<button class="btn btn-primary" type="submit">Submit</button>
								</div>
								<!-- </div> -->
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<!-- <div class="element-wrapper">
				<h6 class="element-header">Inline Form Example</h6>
				<div class="element-box">
					<h5 class="form-header">Inline Form</h5>

					<form class="form-inline">
						<label class="sr-only"> First Name</label><input
							class="form-control mb-2 mr-sm-2 mb-sm-0 col-sm-6"
							placeholder="First Name"><label
							class="form-check-label text-center col-sm-3 mb-2"><input
							class="form-check-input" type="checkbox"> Correct</label>

						<button class="btn btn-primary" type="submit">Add</button>
					</form>
				</div>
			</div> -->











			<!-- <div class="element-wrapper"> -->
			<!-- <h6 class="element-header mb-5">Data Tables</h6>
			<div class="element-box">
				<div class="table-responsive">
					<table id="dataTable1" width="100%"
						class="table table-striped table-lightfont">
						<thead>
							<tr>
								<th>Name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Age</th>
								<th>Start date</th>
								<th>Salary</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Name</th>
								<th>Position</th>
								<th>Office</th>
								<th>Age</th>
								<th>Start date</th>
								<th>Salary</th>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td>Tiger Nixon</td>
								<td>System Architect</td>
								<td>Edinburgh</td>
								<td>61</td>
								<td>2011/04/25</td>
								<td>$320,800</td>
							</tr>
							<tr>
								<td>Garrett Winters</td>
								<td>Accountant</td>
								<td>Tokyo</td>
								<td>63</td>
								<td>2011/07/25</td>
								<td>$170,750</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
 -->
			<!-- 	</div> -->

		</div>
	</div>
</body>
<script type="text/javascript">
	function myToggle() {

		alert("Inside ===>");
		var type = document.getElementById("type").value;
		alert("value ===>" + type);
	}
</script>
</html>