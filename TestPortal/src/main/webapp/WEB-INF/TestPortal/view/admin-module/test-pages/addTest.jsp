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

								<h5 class="form-header">ADD TEST</h5>
								<hr>
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
								<div class="element-wrapper">
									<div class="element-box">
										<h5 class="form-header">QUESTIONS LIST</h5>
										<hr>

										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>Customer Name</th>
														<th>Orders</th>
														<th>Location</th>
														<th class="text-center">Status</th>
														<th class="text-right">Order Total</th>
													</tr>
												</thead>
												<tbody>

													<tr>
														<td>Joe Trulli</td>
														<td>1,256</td>
														<td><img alt="" src="img/flags-icons/es.png"
															width="25px"></td>
														<td class="text-center"><div
																class="status-pill yellow" data-title="Pending"
																data-toggle="tooltip"></div></td>
														<td class="text-right">$1,120</td>
													</tr>
													<tr>
														<td>Fred Kolton</td>
														<td>74</td>
														<td><img alt="" src="img/flags-icons/fr.png"
															width="25px"></td>
														<td class="text-center"><div
																class="status-pill green" data-title="Complete"
																data-toggle="tooltip"></div></td>
														<td class="text-right">$74</td>
													</tr>
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
</html>