<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

<title>Add Test</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link href="${pageContext.request.contextPath }/static/css/compiler.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.7/ace.js"
	type="text/javascript" charset="utf-8"></script>

</head>
<body>
	
	<c:if test="${ output1 }">
		<script type="text/javascript">
			window.alert("$output1")
		</script>
	</c:if>

	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-sm-4">
					<div class="element-wrapper">
						<div class="element-box">

							<div class="card text-center">

								<h1>00:00</h1>

							</div>
							<br>
							<div class="card text-center form-group">

								<button class="btn btn-warning form-control font-weight-bold"
									type="submit">SUBMIT TEST</button>
							</div>
							<hr>
							<div class="card text-center">
								<div class="card-header font-weight-bold">TEST NAME HERE</div>
								<hr>
								<div class="card-body">
									<ul class="list-group">
										<li class="list-group-item font-weight-bold">Question
											01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">4</span>
										</li>
										<li class="list-group-item font-weight-bold">Question
											01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">4</span>
										</li>
										<li class="list-group-item font-weight-bold">Question
											02&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">2</span>
										</li>
										<li class="list-group-item font-weight-bold">Question
											03&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">4</span>
										</li>
										<li class="list-group-item font-weight-bold">Question
											04&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">1</span>
										</li>
										<li class="list-group-item font-weight-bold">Question
											05&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span
											class="badge badge-primary" data-toggle="tooltip"
											title="marks">4</span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="col-sm-8">
					<div class="element-wrapper">
						<div class="element-box">
							<span class="badge badge-pill badge-dark px-3 py-1">Question
								01</span> <br>
							<div class="form-group p-2">
								<p>Program to find loki in the list of string</p>

							</div>
							<div class="row">
								<div class="col-sm-6">
									<span class="badge badge-pill badge-dark px-3 py-1">Sample
										Input </span> <br>
									<div class="form-group p-2 m-0">
										<p>3 Thor Loki IronMan</p>
									</div>
								</div>
								<div class="col-sm-6">
									<span class="badge badge-pill badge-dark px-3 py-1">Sample
										Output</span> <br>
									<div class="form-group p-2 m-0">
										<p>1</p>

									</div>
								</div>
							</div>


							<!--Compiler-->
							<div class="row container p-0 m-0">
								<div class="col-sm-12 p-0">
									<form id="editorForm"
										action="${pageContext.request.contextPath }/java/student/start/test"
										method="post">
										<div id="code-edit" class="row code-div container mx-auto">
											<div id="editor-menu">
												<select name="language" class="options" id="prolang">
													<option value="java">Java</option>
													<!-- <option value="python">Python</option> -->
													<option value="c">C</option>
													<option value="cpp">Cpp</option>
													<option value="javascript">Javascript</option>
													<option value="php">Php</option>
												</select> <select class="options" id="theme">
													<option value="dracula">Dark</option>
													<option value="xcode">Light</option>
												</select> <input type="text" hidden name="code" id="hiddencode">
												<button id="run" type="submit">
													<img id="run-img"
														src="${pageContext.request.contextPath }/static/img/compiler/run.png">
												</button>
											</div>
											<div id="editor"></div>
											<script
												src="${pageContext.request.contextPath }/static/js/compiler.js"
												type="text/javascript">
												
											</script>
										</div>
									</form>
								</div>
								<div class="col-sm-12">
									<p>${ output1 }</p>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var code = `${ code }`;
	var a = false;
	if (code!=(null)&&code!=("")) {
		a = true;
	}
	if (a) {
		var codeOut = code.split('{').join("{\n");
		codeOut = codeOut.split('}').join("\n}");
		codeOut = codeOut.split(';').join(";\n");
		codeOut = codeOut.split('>').join(">\n");

		editor.setValue(codeOut);
	}
</script>
</html>