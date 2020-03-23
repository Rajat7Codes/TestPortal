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
								<span class="element-box el-tablo bg-3white">
									<div class="label text-dark">Marks</div>
									<div class="font-weight-bold">${marks}</div>
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
									<div class="font-weight-bold">${ attempted}</div>
								</span>
							</div>
							<div class="col-sm-2">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Passed</div>
									<div class="font-weight-bold">${passed }</div>
								</span>
							</div>

							<div class="col-sm-2">
								<span class="element-box el-tablo bg-white">
									<div class="label text-dark">Failed</div>
									<div class="font-weight-bold">${failed}</div>
								</span>
							</div>
						</div>
					</div>
					<h6 class="element-header mb-2 mt-2">Result Details</h6>
					<div>
						<table class="table text-center">
							<thead>
								<tr>
									<th>Sr. No.</th>
									<th>Student Name</th>
									<th>Marks</th>
									<th>Result</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${userList}" varStatus="ind">

									<tr>
										<td>${ind.index+1}</td>
										<td>${user[0]}</td>
										<td>${user[3]}</td>
										<td>${user[1]}</td>
										<td>${user[2]}</td>

									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>