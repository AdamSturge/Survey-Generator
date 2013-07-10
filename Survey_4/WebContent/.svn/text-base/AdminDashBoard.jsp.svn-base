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
	<link href="WebResources/DashboardViewGenericSurveyScore.css" type="text/css" rel="stylesheet">
	<link href="WebResources/jquery.jscrollpane.css" type="text/css" rel="stylesheet">
	<title>Admin Dashboard</title>
</head>
<body>
			
			
			
			
		<div id="monitor" class="screenElements">
			<div id="lcdBorder" class="screenElements">
				<div id="lcd">
				</div>
			</div>
			<div id="UserControlPanel">
					<img src="WebResources/manageSurveyButton.png" title="Manage your surveys" id="manageSurveyButton"  class="UserControlPanelButton"/>
					<img src="WebResources/manageUserSurveyButton.png" title="Lookup surveys you are answering" id="manageUserSurveyButton"  class="UserControlPanelButton"/>
					<img src="WebResources/createNewSurveyButton.png" title="Create a New Survey" id="createNewSurveyButton" class="UserControlPanelButton"/>
					<img src="WebResources/relateSurveyButton.png" title="Relate Jobfair to your surveys" id="relateSurveyButton" class="UserControlPanelButton"/>
					<img src="WebResources/genericSurveyScoreButton.png" title="View Generic Survey Scores" id="viewGenericScoreButton" class="UserControlPanelButton"/>
					<img src="WebResources/memorialButton.png" title="Visit Memorial University of Newfoundland" id="memorialButton" class="UserControlPanelButton"/>
					<img src="WebResources/logoutButton.png" title="Log out" id="logoutButton" class="UserControlPanelButton"/>
					<div id="titlePanel"><h4 id="titleText"></h4></div>
					<p id="systemMsg"><%= request.getAttribute("welcomemsg") %></p>
			</div>
		</div>
			
			
			<div id="manageSurveyPanel">
				<br />
				<h3 id="manageSurveyTitle">Surveys created by you</h3>
				<br />
				<table border="0" id="surveyListTable">
					<tr>
						<th class= "adminSurveyListTitle">Survey Title</th>
						<th class="surveyListAction">Publish</th>
						<th class="surveyListAction">Edit</th>
						<th class="surveyListAction">Delete</th>
					</tr>

				</table>
			</div>
			<div id="manageUserSurveyPanel">
				<br />
				<h3 id="manageUserSurveyTitle">Surveys you are currently doing</h3>
				<br />
				<table border="0" id="userSurveyListTable">
					<tr>
						<th class="userSurveyListTitle">Survey Title</th>
						<th class="Author">Author</th>
						<th class="userJobfairTitle">Generic Survey Title</th>
					</tr>

				</table>
			</div>
			<div id="createNewSurveyPanel">
				<div id="newSurveyWizardPhaseOne">
					
					<p>You are about to create a new Survey, our survey wizard will guide you through the survey setup process. First, what <b>type</b> of survey are you creating?</p>
					<br />
					<br />
					<form id="surveyType">
						<input type="radio" name="surveyType" value="CustomSurvey"><label>Custom Survey</label><br>
						<input type="radio" name="surveyType" value="JobFair"><label>Generic Survey</label>
						<br />
						<br />
						<label>Survey Title</label>&nbsp;<input id="surveyTitle" type="text" />
					</form>
					
					<img id="nextButton" src="WebResources/wizardNextButton.png"  title="Next"/>
				</div>
				<div id="newSurveyWizardPhaseTwo">
					<p>You are about to create a <label id="userSelectedSurveyType"></label>. Please briefly describe your survey in the following text area. You may leave it blank(highly unrecommended) if you have nothing to inform your survey participants before they begin the survey.</p>
					<br />
					<textarea id="surveyMessage" rows="5" cols="66"></textarea>

					<img id="backButton" src="WebResources/wizardPreviousButton.png"  title="Back"/>
					<img id="nextButton" src="WebResources/wizardNextButton.png"  title="Next"/>
				</div>
				<div id="newSurveyWizardPhaseThree">
					<p id="readyToCreateSurveyMsg">You are ready to create your survey, click on the button below to start</p>

					<img id="workOnSurveyButton" src="WebResources/wizardCreateSurveyButton.png"  title="Click me to work on your survey"/>
					
					<img id="backButton" src="WebResources/wizardPreviousButton.png"  title="Back"/>
					<img id="previewButton" src="WebResources/wizardPreviewButton.png"  title="Preview"/>
					<img id="saveSurvey" src="WebResources/wizardSaveButton.png"  title="Save"/>
					<img id="publishSurvey" src="WebResources/wizardPublishButton.png"  title="Publish"/>
				</div>
				<div id="newSurveyWizardPhaseFour">
					<p>Congratulations, your survey is created successfully.</p>
				</div>
			</div>
			
			<div id="relateSurveyPanel">
				<br />
				<h3 id="relateSurveyTitle">Relate generic survey to your custom surveys</h3>
				<br />
				<p></p>
				<br />
				<table border="0" id="relateSurveyTable">
					<tr>
						<th class="relateSurveyJobfairTitle">Generic Survey List</th>
						<th class="relateSurveyJobfairRelatedSurveyTitle">Related Surveys</th>
						<th class="relateSurveyJobfairCanBeRelatedSurveyTitle">Relatable Surveys</th>
					</tr>
					<tr>
						<td id="relateSurveyJobfairList"><div class="listContainer"></div></td>
						<td id="relateSurveyJobfairRelatedSurveyList"><div class="listContainer"></div></td>
						<td id="relateSurveyJobfairCanBeRelatedSurveyList"><div class="listContainer"></div></td>
					</tr>
				</table>
			</div>
		
			<div id="viewGenericSurveyScorePanel">
				<br />
				<h3 id="viewGenericScoreTitle">View how prospect students do in the Generic Survey</h3>
				<br />
				<br />
				<table border="0" id="genericSurveyListTable">
					<tr>
						<th class="genericSurveyTitle">Survey Title</th>
					</tr>

				</table>
			</div>
		
		
		<!-- This is the screen cover box -->
		<div id="surveyQuestionCreationBox">
				<!-- this div is used in newSurveyWizardPhaseThree -->
				<div id="workArea">
					<br/>
					<p>Grouping enforces order in answering survey questions. Once a group of questions has been answered, changes cannot be made to the questions in the completed group(s).</p>
					<br/>
					<br/>
					<div class="button" id="addGroupButton">Add a group</div>
					<br/>
					<!-- Survey content is dymanically inserted using javascript. -->
				</div>
				<img id="closeWorkAreaButton" src="WebResources/closeWorkAreaButton.png" />
		</div>
		<!-- This is the screen cover box -->
		<div id="jobfairQuestionCreationBox">
				<!-- this div is used in newSurveyWizardPhaseThree -->
				<div id="workArea">
					
					<br />
					<p>First add questions to your survey, then shape your survey.</p>
					<br />
					<div class="button" id="addJobFairQnButton">Add Questions</div>
					<div class="button" id="customizePyramidShapeButton">Shape your survey</div>
					<span id="qnCounter"><label>Question Count:</label><label id="qnCount"></label></span>
					<br />
					<div id="jobfairAddQnZone">
						<p>Grouping enforces order in answering survey questions. Once a group of questions has been answered, changes cannot be made to the questions in the completed group(s).</p>
						<br/>
						<br/>
						<div class="button" id="addGroupButton">Add a group</div>
						<br/>
					</div>
					<div id="jobfairShapeSurveyZone">
						<div id="jobfairPyramidBuilder">
						</div>
					</div>
					
					
					
					<!-- Survey content is dymanically inserted using javascript. -->
				</div>
				<img id="closeWorkAreaButton" src="WebResources/closeWorkAreaButton.png" />
		</div>
		<!-- This is the screen cover box -->
		<div id="matchResultBox">
				<!-- this div appears when user clicks on a survey in the Admin Survey Management Table -->
				<div id="workArea">
					<br /><br />
					<p>The following is the tabulated result for the participants of this survey</p>
					<br /><br />
					<!-- Survey content is dynamically inserted using javascript. -->
					<div id="matchResultTable">
						<div class="titleItem">
							<div class="match_uid">User ID</div><div class="match_score">User Score</div><div class="match_name">Name</div><div class="match_email">E-mail</div><div class="match_phone">Phone Number</div>
						</div>
					</div>
				</div>
				<img id="closeWorkAreaButton" src="WebResources/closeWorkAreaButton.png" />
		</div>
</body>

		<script type="text/javascript" src="WebResources/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="WebResources/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="WebResources/Dashboard.js"></script>
		<script type="text/javascript" src="WebResources/loadingProcessDisplayer.js"></script>
		
		<script type="text/javascript" src="WebResources/DashboardManageCreated.js"></script>
		<script type="text/javascript" src="WebResources/DashboardCurrentlyAnswering.js"></script>
		<script type="text/javascript" src="WebResources/DashboardSurveyWizard.js"></script>
		<script type="text/javascript" src="WebResources/DashboardRelateSurvey.js"></script>
		<script type="text/javascript" src="WebResources/DashboardViewGenericSurveyScore.js"></script>
		
		<script type="text/javascript" src="WebResources/DashboardLayout.js"></script>
		
		
		<script type="text/javascript" src="WebResources/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" src="WebResources/jquery.mousewheel.js"></script>
</html>