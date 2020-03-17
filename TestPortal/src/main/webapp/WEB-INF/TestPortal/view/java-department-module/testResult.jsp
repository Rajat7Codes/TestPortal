<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test Result</title>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="menu-position-side menu-side-left full-screen"
	onload="myToggle();">
	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-lg-12">
					<div class="element-wrapper">
						<div class="element-box">
							<form:form
								action="${pageContext.request.contextPath }/admin/question/bank/save"
								modelAttribute="testResult" method="post"
								enctype="multipart/form-data">

								<form:hidden path="testResultId" />
								<h6 class="element-header mb-5">Test Result</h6>
								<div class="row">
									<c:if test="${edit == false }">
										<div class="col-sm-6">
											<div class="form-group">
												<form:label path="testName">Test Name</form:label>
												<form:input path="testName" class="form-control"
													placeholder="Test Name" type="text" />
												<form:errors path="testName"></form:errors>
											</div>
										</div>
									</c:if>
									<%-- <c:if test="${edit == true }">
										<div class="col-sm-6">
											<div class="form-group">
												<label>Question Type</label>
												<form:select class="w-75" name="questionType"
													onchange="myToggle();" path="questionType"
													id="questionType" itemLabel="type" multiple="single"
													items="${questionTypeList }" itemValue="questionTypeId" />
												<form:errors path="questionType" />
											</div>
										</div>
									</c:if>

									<div class="col-sm-6">
										<div class="form-group">
											<form:label path="marks"> Marks</form:label>
											<form:input path="marks" class="form-control"
												placeholder="Enter Marks" type="text" />
											<form:errors path="marks"></form:errors>
										</div>
									</div>
								</div>
								<div class="form-group" id="questionDiv">
									<form:label path="question" for=""> Question</form:label>
									<form:input path="question" class="form-control"
										placeholder="Enter a Question" type="text" />
									<form:errors path="question"></form:errors>
								</div>
								<c:if test="${imageTypeStatus == false }">
									<div class="row">
										<div class="col-sm-6" id="subDiv">
											<div class="form-group">
												<form:label path="subject">Subject</form:label>
												<form:input path="subject" class="form-control"
													placeholder="Enter subject" type="text" />
												<form:errors path="subject"></form:errors>
											</div>
										</div>
										
									</div>
								</c:if>
								<c:if test="${imageTypeStatus == true }">
								<div class="row">
									<div class="col-sm-6" id="subDiv">
										<div class="form-group">
											<form:label path="subject">Subject</form:label>
											<form:input path="subject" class="form-control"
												placeholder="Enter subject" type="text" />
											<form:errors path="subject"></form:errors>
										</div>
										<div class="form-group">
											<form:label path="subject">Subject</form:label>
											<form:select class="form-control" name="questionType"
												onchange="myToggle();" path="subject" id="subject"
												itemLabel="subjectName" multiple="single"
												items="${subjectList }" itemValue="subjectId" />
											<form:errors path="subject" />
										</div>
									</div>
									<div class="col-sm-6" id="imageToggle">
										<div class="form-group">
											<label>Image Upload</label> <input
												class="form-control file-upload" type="file" id="imageName"
												name="imageName" />
											<form:errors path="imageName"></form:errors>
										</div>
									</div>
								</div>
								</c:if>
								<div class="form-group">
									<form:label path="description"> Description</form:label>
									<form:textarea path="description" class="form-control" rows="3"></form:textarea>
									<form:errors path="description"></form:errors>
								</div>

								<!-- Sample Input Output -->
								<div id="codeDiv" style="display: none">
									<div class="col-12 row">
										<div class="form-group w-75">
											<label>Sample Input</label>
											<form:textarea class="form-control" rows="3"
												path="sampleInput"></form:textarea>
											<form:errors path="sampleInput" />
										</div>
										<div class="form-group w-25 m-auto text-center">
											<button class="btn btn-dark rounded-circle"
												data-target="#onboardingTextModal" data-toggle="modal"
												type="button">
												<i data-toggle="tooltip" title="Example Input"
													class="fa fa-question"></i>
											</button>
											<div aria-hidden="true"
												class="onboarding-modal modal fade animated"
												id="onboardingTextModal" role="dialog" tabindex="-1">
												<div class="modal-dialog modal-centered" role="document">
													<div class="modal-content text-center">
														<button aria-label="Close" class="close"
															data-dismiss="modal" type="button">
															<span class="os-icon os-icon-close"></span>
														</button>
														<div class="onboarding-content with-gradient">
															<h4 class="onboarding-title">Example of Sample
																Input!</h4>
															<div class="onboarding-text">
																3<br>This<br>Is<br>Example
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-12 row">
										<div class="form-group w-75">
											<label>Sample Output</label>
											<form:textarea class="form-control" rows="3"
												path="sampleOutput"></form:textarea>
											<form:errors path="sampleOutput" />
										</div>
										<div class="form-group w-25 m-auto text-center">
											<button class="btn btn-dark rounded-circle"
												data-target="#onboardingTextModal1" data-toggle="modal"
												type="button">
												<i data-toggle="tooltip" title="Example Output"
													class="fa fa-question"></i>
											</button>
											<div aria-hidden="true"
												class="onboarding-modal modal fade animated"
												id="onboardingTextModal1" role="dialog" tabindex="-1">
												<div class="modal-dialog modal-centered" role="document">
													<div class="modal-content text-center">
														<button aria-label="Close" class="close"
															data-dismiss="modal" type="button">
															<span class="os-icon os-icon-close"></span>
														</button>
														<div class="onboarding-content with-gradient">
															<h4 class="onboarding-title">Example of Sample
																Output!</h4>
															<div class="onboarding-text">Example</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<!-- Hidden Input Output -->
									<div class="col-12 p-0 row">
										<div class="form-group col-6">
											<label>Hidden Input</label>
											<form:textarea class="form-control" rows="3"
												path="hiddenInput"></form:textarea>
											<form:errors path="hiddenInput" />
										</div>
										<div class="form-group col-6 p-0">
											<label>Hidden Output</label>
											<form:textarea class="form-control" rows="3"
												path="hiddenOutput"></form:textarea>
											<form:errors path="hiddenOutput" />
										</div>
									</div>
								</div>


								<c:if test="${edit == false }">
									<div id="optionsDiv">
										<h6 class="element-header mt-5">Options</h6>
										<div class="form-inline mt-5">
											<label> Options :</label><input
												class="form-control mb-2 mr-sm-2 mb-sm-0 col-sm-6 ml-5"
												placeholder="Option Name" id="optionName"><label
												class="form-check-label text-center col-sm-3 mb-2"><input
												class="form-check-input" type="checkbox" id="correct">
												Correct</label>
											<button class="btn btn-primary" id="add" type="button">Add</button>
										</div>


										<div class="mt-5">
											<div class="table-responsive mb-5">

												<table id="allDetailsTableBody"
													class="table table-striped table-lightfont">
													<thead>
														<tr>
															<th>Options</th>
															<th>Answer</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody id="optionsBody">

													</tbody>
												</table>
											</div>
										</div>
									</div>

								</c:if>
								<c:if test="${edit == true }">
									<div id="optionsDiv">
										<h6 class="element-header mt-5">Options</h6>
										<div class="mb-5">
											<div class="table-responsive mb-5">

												<table id="optTableUpdate"
													class="table table-striped table-lightfont">
													<thead>
														<tr>
															<!-- <td>Question Bank Id</td> -->
															<td>Options Id</td>
															<td>Options</td>
															<td>Answer</td>

														</tr>
														<tr style="display: none;">
															<!-- <th>questionBankId</th> -->
															<th>optionsId</th>
															<th>optionName</th>
															<th>correctAnswer</th>

														</tr>
													</thead>
													<tbody id="optionsBodyForUpdate">
														<c:forEach var="options"
															items="${questionBank.getOptions() }" varStatus="ind">
															<tr>
																<td><input class="form-control"
																	value="${options.getQuestionBank().questionBankId}"
																	disabled="disabled"></td>
																<td><input class="form-control"
																	value="${options.optionsId}" disabled="disabled"></td>
																<td><input class="form-control"
																	value="${options.optionName}"></td>
																<td><input class="form-control"
																	value="${options.correctAnswer }"></td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>
										</div>
									</div>
								</c:if>
								<div class="form-buttons-w text-center pt-5">
									<input type="text" id="data" name="data" style="display: none;">
									<!-- <i class="btn" id="jsonBtn" onclick="addToUpdateJson1();">Add</i> -->
									<!-- <input type="button" onclick="newPageJson();">Cheack -->
									<c:if test="${edit == true }">
										<button class="btn btn-primary" type="submit"
											onclick="addToUpdateJson1();">Update</button>
									</c:if> --%>
									<%-- <c:if test="${edit == false }">
										<button class="btn btn-primary" type="submit"
											onclick="newPageJson();">Submit</button>
									</c:if> --%>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>