
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- start chat sidebar -->
	<div class="chat-sidebar-container" data-close-on-body-click="false">
		<div class="chat-sidebar">
			<ul class="nav nav-tabs">

				<li class="nav-item"><a href="#quick_sidebar_tab_3"
					class="nav-link tab-icon" data-toggle="tab"> <i
						class="material-icons">settings</i> Settings
				</a></li>
			</ul>

			<!-- End Doctor Chat -->
			<!-- Start Setting Panel -->
			<div class="tab-pane chat-sidebar-settings animated slideInUp"
				role="tabpanel" id="quick_sidebar_tab_3">
				<div class="chat-sidebar-settings-list slimscroll-style">
					<div class="chat-header">
						<h5 class="list-heading">Layout Settings</h5>
					</div>
					<div class="chatpane inner-content ">
						<div class="settings-list">
							<div class="setting-item">
								<div class="setting-text">Sidebar Position</div>
								<div class="setting-set">
									<select
										class="sidebar-pos-option form-control input-inline input-sm input-small ">
										<option value="left" selected="selected">Left</option>
										<option value="right">Right</option>
									</select>
								</div>
							</div>
							<div class="setting-item">
								<div class="setting-text">Header</div>
								<div class="setting-set">
									<select
										class="page-header-option form-control input-inline input-sm input-small ">
										<option value="fixed" selected="selected">Fixed</option>
										<option value="default">Default</option>
									</select>
								</div>
							</div>
							<div class="setting-item">
								<div class="setting-text">Sidebar Menu</div>
								<div class="setting-set">
									<select
										class="sidebar-menu-option form-control input-inline input-sm input-small ">
										<option value="accordion" selected="selected">Accordion</option>
										<option value="hover">Hover</option>
									</select>
								</div>
							</div>
							<div class="setting-item">
								<div class="setting-text">Footer</div>
								<div class="setting-set">
									<select
										class="page-footer-option form-control input-inline input-sm input-small ">
										<option value="fixed">Fixed</option>
										<option value="default" selected="selected">Default</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>