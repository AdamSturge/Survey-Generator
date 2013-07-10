/*******************************************************************************
 * Author: Ji He
 */

$(document).ready(function() {
	ConfigureDisplayPanels();
	performBindings();
	GetServerIP();

	var scriptloadstart = new Date().getTime();
	while(new Date().getTime() - scriptloadstart < 1200){}	//just to waste time
	
	//trigger the basic event of clicking on manageSurveyButton
	if( $("#manageSurveyButton").length > 0 ){ 
		$("#manageSurveyButton").trigger('click');
	} else {
		$("#manageUserSurveyButton").trigger('click');
	}
	
});

function ConfigureDisplayPanels(){
	var windowHeight = window.innerHeight;
	var windowWidth = window.innerWidth;
	
	
	var contentPanelMinimumHeight = 200;
	var controlPanelReservedHeight = 200;
	var controlPanelTop = windowHeight - controlPanelReservedHeight;
	//var contentPanelTop = $("#lcd").position().top;
	
	/***no longer using js to fix control panel position due to problems with page debugging, position defined in CSS now****/
//	$("#lcd").height( controlPanelTop - contentPanelTop - 100 );
//	$("#UserControlPanel").height(controlPanelReservedHeight/2);
//	//position the control panel
//	$("#UserControlPanel").css("top", controlPanelTop +"px");
	
	/*** Stretching the shadow boxes to full page width and height***/
	//currently done using CSS, fully customized for 1028x768 screen resolution
//	$('#surveyQuestionCreationBox').css('height', window.innerHeight+"px" );
//	$('#surveyQuestionCreationBox').css('width', window.innerWidth+"px" );
//	$('#jobfairQuestionCreationBox').css('height', window.innerHeight+"px" );
//	$('#jobfairQuestionCreationBox').css('width', window.innerWidth+"px" );
//	$('#matchResultBox').css('height', window.innerHeight+"px" );
//	$('#matchResultBox').css('width', window.innerWidth+"px" );
}

/*******************************************************************************
 * Place event listeners on interactable elements.
 */
function performBindings() {
	$('.UserControlPanelButton').hover(
		function(){
			var curposition = $(this).offset();
            $(this).offset({ top: curposition.top - 3, left: curposition.left - 3 });
            $(this).css("border", "3px solid #e4e277");
            $(this).css("z-index", "10");
			$(this).width(66);
			$(this).height(66);
			Global_currentTitle = $("#titleText").text();
			if($(this).attr('id') == "manageSurveyButton"){
				$("#titleText").text("Manage your created surveys");
			} else if ($(this).attr('id') == "manageUserSurveyButton"){
				$("#titleText").text("Surveys that you are answering");
			} else if ($(this).attr('id') == "loginButton"){
				$("#titleText").text("Login");
			} else if ($(this).attr('id') == "registerButton"){
				$("#titleText").text("register a new account");
			} else if ($(this).attr('id') == "createNewSurveyButton"){
				$("#titleText").text("Create a New Survey");
			} else if ($(this).attr('id') == "relateSurveyButton"){
				$("#titleText").text("Relate jobfair to your surveys");
			} else if ($(this).attr('id') == "viewGenericScoreButton"){
				$("#titleText").text("View generic survey scores");
			} else if ($(this).attr('id') == "memorialButton"){
				$("#titleText").text("Visit MUN website");
			} else {
				$("#titleText").text("Log out");
			}
		},
		function(){
			var curposition = $(this).offset();
            $(this).offset({ top: curposition.top + 3, left: curposition.left + 3 });
			$(this).width(60);
			$(this).height(60);
			$(this).css("border", "0");
            $(this).css("z-index", "0");
			$("#titleText").text(Global_currentTitle);
		}
	);
	
	//-----------Manage Admin Created Surveys--------
	$('#manageSurveyButton').live('click', function(){
		Global_currentTitle = "Manage your created surveys";
		$("#titleText").text(Global_currentTitle);
//		$("#lcd").html("");
//		$("#lcd").html( $("#manageSurveyPanel").html() );
		DisplayAdminSurveyList();
		ClearSurveyWizardCache();
		
	});
	
	//-----------Currently working on surveys--------
	$('#manageUserSurveyButton').click(function(){
		Global_currentTitle = "Surveys that you are answering";
		$("#titleText").text(Global_currentTitle);
		$("#lcd").html("");
		$("#lcd").html( $("#manageUserSurveyPanel").html() );
		DisplayUserSurveyList();
		ClearSurveyWizardCache();
	});
	
	//-----------Create Survey/Jobfair--------
	$('#createNewSurveyButton').click(function(){
		Global_currentTitle = "Create a New Survey";
		$("#lcd").html("");
		$("#lcd").html( $("#createNewSurveyPanel").html() );
		$("#newSurveyWizardPhaseOne").fadeIn("slow");
		$("#newSurveyWizardPhaseOne").children("*").fadeIn("slow");	//make sure all elements hidden by default CSS is shown.
		Global_jobfairQnCount = 0;
		Global_jobfairPyramidBlockCount = 0;
		ClearSurveyWizardCache();
	});
	
	//-----------Relate surveys to jobfair--------
	$('#relateSurveyButton').click(function(){
		Global_currentTitle = "Relate jobfair to your surveys";
		$("#lcd").html("");
		$("#lcd").html( $("#relateSurveyPanel").html() );
		DashboardRelateSurveySpace.PopulateJobfairList();
		ClearSurveyWizardCache();
	});
	
	//-----------View Scores for Generic Survey--------
	$('#viewGenericScoreButton').click(function(){
		Global_currentTitle = "View generic survey scores";
		$("#lcd").html("");
		$("#lcd").html( $("#viewGenericSurveyScorePanel").html() );
		DisplayGenericSurveys();
		ClearSurveyWizardCache();
	});
	
	//-----------Visit MUN CS Website--------
	$('#memorialButton').click(function(){
		window.open("http://www.cs.mun.ca");
		ClearSurveyWizardCache();
	});
	
	
	//------------- Generic Listeners -------------
	$('body').keyup(function(e){
		//escape key pressed
		//alert(e.which);
        if(e.which == 27){
        	$("#surveyQuestionCreationBox").fadeOut("slow");
        	$("#matchResultBox").fadeOut("slow");
        	$("#jobfairQuestionCreationBox").fadeOut("slow");
        }
    });
	
	$('#logoutButton').live('click', function(){
		window.location = "LogOut";
	});
}



function GetServerIP(){
	var ajaxURL = "GetServerIP";
	$.ajax({
		  url: ajaxURL,
		  dataType: "text",
		  success: function(responsedata){
			  Global_serverIP = responsedata;
			  //alert("@dashboardlayout - get ip:"+ Global_serverIP);
		  }
		});
}

function ClearSurveyWizardCache(){
	$("#surveyQuestionCreationBox #workArea").find(".surveyGroup").each(function(){$(this).detach();});
	Global_surveyTitle="";
	Global_surveyMessage="";
	Global_isJobFair = false;
	$('input[name = "surveyType"][value = "JobFair"]').attr("checked", "");
	$('input[name = "surveyType"][value = "CustomSurvey"]').attr("checked", "");
}

