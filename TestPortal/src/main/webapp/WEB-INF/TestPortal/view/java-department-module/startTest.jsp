<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>

<head>

<title>Start Test</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link href="${pageContext.request.contextPath }/static/css/compiler.css">

<script
	src="${pageContext.request.contextPath }/static/countdown_timer/LIB/jquery-2.0.3.js"
	type="text/javascript" charset="utf-8"></script>


<script
	src="${pageContext.request.contextPath }/static/countdown_timer/jquery.countdownTimer.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/static/countdown_timer/jquery.countdownTimer.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath }/static/countdown_timer/CSS/jquery.countdownTimer.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.7/ace.js"
	type="text/javascript" charset="utf-8"></script>

</head>
<body>

	<div class="content-i">
		<div class="content-box">
			<div class="row">
				<div class="col-sm-4">
					<div class="element-wrapper">
						<div class="element-box">
							<div class="card text-center">
								<h1>
									<div class="countdown"></div>
								</h1>
							</div>

							<!-- <div id="result"></div> -->
							<!-- TRIAL INTERVAL -->
							<!-- 	<a href="#" onclick="SaveTime()">Save Current Time</a> | <a
								href="#" onclick="retrieveTime()">Retrieve Saved Time</a>
 -->


							<br>
							<div class="card text-center form-group">

								<button class="btn btn-warning form-control font-weight-bold"
									type="submit">SUBMIT TEST</button>
							</div>
							<hr>
							<div class="card text-center">
								<div class="card-header font-weight-bold">${ addTest.testName }</div>

								<div class="card-body">
									<div class="form-group">
										<div class="row">
											<c:forEach varStatus="ind" var="question"
												items="${ addTest.testQuestions }">
												<div class="col-2 p-0">
													<button class="btn btn-dark border border rounded-circle"
														id="q${question.questionId}"
														onclick="getQuestion(${question.questionId})">${ind.index+1}</button>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="element-wrapper">
						<c:forEach var="testQuestion" varStatus="ind"
							items="${ testQuestions }">
							<div class="element-box questionsDiv mt-0"
								id="questionsDiv${testQuestion.questionBankId}"
								style="display: none">
								<div class="row">
									<div class="col-sm-6">
										<span class="badge badge-pill badge-dark px-3 py-2">Question
											${ind.index+1}</span>
									</div>
									<div class="col-sm-6 text-right">
										<span class="badge badge-pill badge-dark px-3 py-2 ">${testQuestion.marks}
											marks </span>
									</div>
								</div>
								<hr>
								<div class="form-group p-2 text-justify font-weight-normal">
									<p>${testQuestion.question}</p>
								</div>

								<c:if test="${testQuestion.questionType.imageType}">
									<div class="mx-auto w-100 text-center my-0">
										<img class="w-50 "
											src="${pageContext.request.contextPath }/static/img/javaImage.jpg"
											alt="Your image here..">
									</div>
								</c:if>

								<c:if test="${testQuestion.questionType.programType}">
									<div class="row">
										<div class="col-sm-6">
											<span class="badge badge-pill badge-dark px-3 py-2">Sample
												Input </span> <br>
											<div class="form-group p-2 m-0">
												<p>${ testQuestion.sampleInput }</p>
											</div>
										</div>
										<div class="col-sm-6 text-right">
											<span class="badge badge-pill badge-dark px-3 py-2">
												Sample Output</span> <br>
											<div class="form-group p-2 m-0">
												<p>${ testQuestion.sampleOutput }</p>

											</div>
										</div>
									</div>


									<!--Compiler-->
									<div class="row container p-0 m-0">
										<div class="col-sm-12 p-0">
											<%-- <form id="editorForm"
												action="${pageContext.request.contextPath }/java/student/start/test/compiler"
												method="post"> --%>
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
													<button id="run" onclick="xm()" type="button">
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
											<%-- </form> --%>
										</div>
										<div class="col-sm-12">
											<p>${ output1 }</p>

										</div>
									</div>
								</c:if>
								<c:if test="${!testQuestion.questionType.programType}">
									<div class="form-group p-2">
										<c:forEach var="option" varStatus="opt"
											items="${ testQuestion.options }">
											<div class="form-check">
												<label class="form-check-label"> <input type="radio"
													class="form-check-input" name="optradio">${option.optionName}
												</label>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<hr>
								<div class="form-group text-right">
									<a class="btn btn-dark font-weight-bold px-4" href="#">next</a>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<!-- Script for Question Changing -->
<script type="text/javascript">
	function getQuestion( questionId) {
		let questionsToBeShown = document.getElementById("questionsDiv"+questionId);
		let questionsNotToBeShown = document.getElementsByClassName("questionsDiv");
		for(let i=0; i<questionsNotToBeShown.length; i++) {
			questionsNotToBeShown[i].style.display = "none";
			
		}
			
		questionsToBeShown.style.display = "block";
	}
</script>

<script type="text/javascript">
function xm() {
	var code = editor.session.getValue();
	var language = document.getElementById("prolang").value;


  var dataJ = {
  	language: language,
    code: code
  };

  $.ajax({
    type: "GET",
    url: "${pageContext.request.contextPath}/java/student/start/test/compiler",
    contentType : "application/json",
	data : dataJ,
	cache : false,
	timeout : 600000,
    success: function(e) {
      console.log(e);
    },
    error: function(e) {
      console.log(e.statusText);
    }
  });
}
</script>

<!--TRIAL INTERVAL  -->
<!-- <script type="text/javascript">
function SaveTime(){
	var date = new Date();
	var timeObj = { sec:date.getSeconds(), min:date.getMinutes(), hr:date.getHours() };
	localStorage.setItem("timeObj", JSON.stringify(timeObj));
	$('#result').append(JSON.stringify(timeObj)+' -- > Saved<br />' );
	}

	function retrieveTime(){
	var timeObj = JSON.parse(localStorage.getItem("timeObj"));
	//You have the time with you now
	//You have the time with you now
	$('#result').append(timeObj.hr+':'+timeObj.min+':'+timeObj.sec+' Retrieved<br />');
	}

</script>
 -->

<!-- <script>
// Check browser support
if (typeof(Storage) !== "undefined") {
  // Store
  localStorage.setItem("date", new Date());
  // Retrieve
  document.getElementById("result").innerHTML = localStorage.getItem("date");
} else {
  document.getElementById("result").innerHTML = "Sorry, your browser does not support Web Storage...";
}
</script>
 -->

<!-- 
<script>


window.onbeforeunload = function() {
	
	alert("checking....");
}
/* function myFunction(){ */
	
/* } */

</script> -->





<!-- Script for Countdown -->
<script>
	var timer2 = ${ addTest.time }+":00";
	var interval = setInterval(function() {

		var timer = timer2.split(':');
		//by parsing integer, I avoid all extra string processing
		var minutes = parseInt(timer[0], 10);
		var seconds = parseInt(timer[1], 10);
		--seconds;
		minutes = (seconds < 0) ? --minutes : minutes;
		if (minutes < 0)
			clearInterval(interval);
		seconds = (seconds < 0) ? 59 : seconds;
		seconds = (seconds < 10) ? '0' + seconds : seconds;
		//minutes = (minutes < 10) ?  minutes : minutes;
		$('.countdown').html(minutes + ':' + seconds);
		timer2 = minutes + ':' + seconds;
		/* --- */
		 localStorage.setItem("timerr", timer2);
		 document.getElementById("result").innerHTML = localStorage.getItem("timerr");
		                                        
	}, 1000);
</script>
</html>