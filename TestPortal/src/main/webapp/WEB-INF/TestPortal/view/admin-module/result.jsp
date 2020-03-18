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
	<div class="content-i ">
		<div class="content-box ">
			<div class="element-wrapper ">
				<div class="element-box bg-light">
					<h6 class="element-header mb-4">Test Result</h6>

					<div class="element-content ">
						<div class="row">
							<div class="col-sm-2 ">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Date</div>
									<div class="font-weight-bold">${dateValue}</div>
								</span>
							</div>
							<div class="col-sm-2 ">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Marks</div>
									<div class="font-weight-bold">m</div>
								</span>
							</div>

							<div class="col-sm-2 ">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">isNegative</div>
									<div class="font-weight-bold">

										<c:if test="${test.negativeMarking==true}">
											<div class="">Yes</div>
										</c:if>
										<c:if test="${test.negativeMarking==false}">
											<div class="">No</div>
										</c:if>
										</td>
									</div>
								</span>
							</div>
							<div class="col-sm-2">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Attempted</div>
									<div class="font-weight-bold">5</div>
								</span>
							</div>
							<div class="col-sm-2">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Passed</div>
									<div class="font-weight-bold">2</div>
								</span>
							</div>

							<div class="col-sm-2">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Failed</div>
									<div class="font-weight-bold">3</div>
								</span>
							</div>
						</div>
					</div>
					<h6 class="element-header mb-2 mt-2">Result Details</h6>
					<div>
						<table class="table text-center">
							<thead>
								<tr>
									<th>Id</th>
									<th>Student Name</th>
									<th>Result</th>
									<th>Date</th>
									<th>Attempts</th>
								</tr>
							</thead>
							<tbody>
								<td>1</td>
								<td>Anonymous</td>
								<td>Pass</td>
								<td>3-18-2020</td>
								<td>2</td>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>