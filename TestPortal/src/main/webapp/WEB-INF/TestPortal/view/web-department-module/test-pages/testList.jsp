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
	<div class="content-i">
		<div class="content-box">
			<div class="pipelines-w">
				<div class="row">
					<div class="col-lg-12 col-xxl-3">
						<div class="pipeline white lined-primary">
							<div class="pipeline-header">
								<h5 class="pipeline-name">Tests</h5>

								<div class="pipeline-settings">
									<div class="pipeline-count  os-dropdown-trigger">Total
										Tests ${testListShown.size()}</div>
								</div>
							</div>
							<div class="row">
								<c:forEach var="test" items="${testListShown}" varStatus="ind">
									<c:if test="${ test.isDeleted!= true }">
										<c:if test="${ test.status == true }">
											<div class="col-lg-4 col-md-4 col-lg-4 col-xxl-3">
												<div class="pipeline-body">
													<div class="pipeline-item">
														<div class="pi-controls">
															<c:if test="${ ind.index==0 }">
																<div class="status status-green" data-placement="top"
																	data-toggle="tooltip" title="Active Status"></div>
															</c:if>
															<c:if test="${ ind.index>0 }">
																<div class="status status-red" data-placement="top"
																	data-toggle="tooltip" title="Active Status"></div>
															</c:if>
														</div>
														<div class="pi-body">
															<div class="pi-info">
																<div class="h6 pi-name mb-2">${test.testName}</div>
																<div class="pi-sub">${test.date}</div>
															</div>
														</div>
														<div class="pi-foot">
															<div class="tags">
																<a class="tag"
																	href="${pageContext.request.contextPath }/java/student/start/test/${test.addTestId}">Start
																	Test</a>
															</div>
															<a class="extra-info" href="#"><i
																class="os-icon os-icon-watch"></i><span>${test.time}
																	mins</span></a>
														</div>
													</div>
												</div>
											</div>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>