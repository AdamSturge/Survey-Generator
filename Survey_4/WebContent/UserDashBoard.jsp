<!-- 
	Author: Ji He
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="WebResources/Site.css" type="text/css" rel="stylesheet">
	<link href="WebResources/DashboardLayout.css" type="text/css" rel="stylesheet">
	<link href="WebResources/DashboardManageCreated.css" type="text/css" rel="stylesheet">
	<link href="WebResources/DashboardCurrentlyAnswering.css" type="text/css" rel="stylesheet">
	<link href="WebResources/DashboardSurveyWizard.css" type="text/css" rel="stylesheet">
	<link href="WebResources/DashboardRelateSurvey.css" type="text/css" rel="stylesheet">
	<link href="WebResources/jquery.jscrollpane.css" type="text/css" rel="stylesheet">
	<title>Student Dashboard</title>

</head>
<body>
			
			
			
			
		<div id="monitor" class="screenElements">
			<div id="lcdBorder" class="screenElements">
				<div id="lcd">
				</div>
				<div id="UserControlPanel">
					<img src="WebResources/manageUserSurveyButton.png" title="Lookup surveys you are answering" id="manageUserSurveyButton"  class="UserControlPanelButton"/>
					<img src="WebResources/memorialButton.png" title="Visit Memorial University of Newfoundland" id="memorialButton" class="UserControlPanelButton"/>
					<img src="WebResources/logoutButton.png" title="Log out" id="logoutButton" class="UserControlPanelButton"/>
					<div id="titlePanel"><h4 id="titleText"></h4></div>
					<p id="systemMsg"><%= request.getAttribute("welcomemsg") %></p>
				</div>
			</div>
		</div>
			
			
			
			
			
			
			
			
			
			<div id="manageUserSurveyPanel">
				<br />
				<h3 id="manageUserSurveyTitle">Surveys you are currently doing</h3>
				<br />
				<table border="0" id="userSurveyListTable">
					<tr>
						<th class="userSurveyListTitle">Survey Title</th>
							<th class="Author">Author</th>
						<th class="userJobfairTitle">Jobfair Title</th>
						
					</tr>

				</table>
			</div>
			
			
			
					<!-- This is the screen cover box -->
		</body>

		<script type="text/javascript" src="WebResources/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="WebResources/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="WebResources/Dashboard.js"></script>
		
		<script type="text/javascript" src="WebResources/loadingProcessDisplayer.js"></script>
		
		<script type="text/javascript" src="WebResources/DashboardManageCreated.js"></script>
		<script type="text/javascript" src="WebResources/DashboardCurrentlyAnswering.js"></script>
		<script type="text/javascript" src="WebResources/DashboardSurveyWizard.js"></script>
		<script type="text/javascript" src="WebResources/DashboardRelateSurvey.js"></script>
		
		<script type="text/javascript" src="WebResources/DashboardLayout.js"></script>
		
		
		<script type="text/javascript" src="WebResources/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="WebResources/jquery.mousewheel.js"></script>
		
		
</html>