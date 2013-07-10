
function SurveyWizardBindings(){

	$('#lcd *').unbind();

//	-----------SurveyWizardButtons Step 1 [Give title for Survey and choose survey type]--------
	$('#lcd #newSurveyWizardPhaseOne #nextButton').live("click", function(){
		var surveytype = $('input:radio[name=surveyType]:checked').val();
		Global_surveyTitle = $("#surveyTitle").val();
		//check if user has selected input, and filled in a title
		if( (document.title=="Professor Dashboard" || surveytype) && Global_surveyTitle.length>0){
			if(surveytype == "JobFair"){
				Global_isJobFair = true;
				$("#userSelectedSurveyType").text("Job Fair Survey");
			} else {
				Global_isJobFair = false;
				$("#userSelectedSurveyType").text("Custom Survey");
			}

			//$("#newSurveyWizardPhaseOne").fadeOut("slow", function(){$("#newSurveyWizardPhaseTwo").fadeIn("slow");$("#newSurveyWizardPhaseTwo").children("*").fadeIn("slow");});
			var title = $("#surveyTitle").val();
			$.ajax({
				url:"GetTitle?title=" + title,
				dataType: 'text'
			}).done(function(titleAlreadyUsed){
				if(titleAlreadyUsed == "true" && !Global_editMode){
					alert("You have already used this title, select a new title");
				}else{
					slidePhasePanelsLeft();
				}
			});


		}
	});
	//-----------SurveyWizardButtons Step 2 [Add msg for survey participants]--------
	$('#lcd #newSurveyWizardPhaseTwo #backButton').live("click", function(){
		//$("#newSurveyWizardPhaseTwo").fadeOut("slow", function(){$("#newSurveyWizardPhaseOne").fadeIn("slow");$("#newSurveyWizardPhaseOne").children("*").fadeIn("slow");});
		slidePhasePanelsRight();
	});
	$('#lcd #newSurveyWizardPhaseTwo #nextButton').live("click", function(){
		Global_surveyMessage = $("#surveyMessage").val();
		//$("#newSurveyWizardPhaseTwo").fadeOut("slow", function(){$("#newSurveyWizardPhaseThree").fadeIn("slow");$("#newSurveyWizardPhaseThree").children("*").fadeIn("slow");});
		slidePhasePanelsLeft();
	});
	//-----------SurveyWizardButtons Step 3 [Add questions to survey]--------
	$('#lcd #newSurveyWizardPhaseThree #backButton').live("click", function(){
		$("#surveyQuestionCreationBox").unbind();
		//$("#newSurveyWizardPhaseThree").fadeOut("slow", function(){$("#newSurveyWizardPhaseTwo").fadeIn("slow");$("#newSurveyWizardPhaseTwo").children("*").fadeIn("slow");});
		slidePhasePanelsRight();
	});

	$('#lcd #newSurveyWizardPhaseThree #previewButton').live('click', function() {
		questionData = "";
		var groupcount = 1;
		$("#workArea").children(".surveyGroup").each(function(){
			$(this).children(".surveyGroup_Qn").each(function(){
				if($(this).children(".criticalValueBox").attr("checked") == "checked"){
					checked = "true";
				}else{
					checked = "false";
				}
				if(!($(this).children(".qnTextBox").val() == "" || $(this).children(".answerTextBox").val() == "")){
					questionData += $(this).children(".qnTextBox").val() + Global_guiSplitter + $(this).children(".answerTextBox").val() + Global_guiSplitter + groupcount + Global_guiSplitter + checked +  Global_guiSplitter;
				}
			});
			groupcount++;
		});
		//alert("preview clicked");
		$.ajax({
			url: "PreviewSurvey?questiondata="+questionData,
			type: "GET",
			success: function(results) {
				window.open(results);
			}
		});
	});

	$('#addGroupButton').live("click", function(){
		AddGroup( $(this) );
	});
	$('#deleteGroup').live("click", function(){
		$(this).parent().detach();
	});
	$("#closeWorkAreaButton").live("click", function(){
		$(this).parent().fadeOut("slow");
	});
	$("#workOnSurveyButton").live("click", function(){
		if(Global_isJobFair){
			$("#jobfairQuestionCreationBox").fadeIn("slow");
		} else {
			$("#surveyQuestionCreationBox").fadeIn("slow");
		}
	});
	$("#customizePyramidShapeButton").live("click", function(){
		$("#jobfairAddQnZone").hide();
		$("#jobfairShapeSurveyZone").fadeIn("fast");
		JobFairPyramidBuilder();
	});
	$("#addJobFairQnButton").live("click", function(){
		$("#jobfairShapeSurveyZone").hide();
		$("#jobfairAddQnZone").fadeIn("fast");
	});
	$("#addQn").live("click", function(){
		AddQuestion( $(this) );
	});
	$(".removeQuestionButton").live("click", function(){
		$(this).parent().detach();
		var qncount = 0;
		var $qnGroupContainer = $(this).parent().parent().parent();
		$qnGroupContainer.children(".surveyGroup").each(function(){
			$(this).children(".surveyGroup_Qn").each(function(){
				if($(this).children(".qnTextBox").val().length > 0){
					qncount ++ ;
				}
			});
		});
		Global_jobfairQnCount = qncount;
		$("#qnCount").text(Global_jobfairQnCount);
	});
	$(document).on("blur", ".qnTextBox",  function(){
		var qncount = 0;
		var $qnGroupContainer = $(this).parent().parent().parent();
		$qnGroupContainer.children(".surveyGroup").each(function(){
			$(this).children(".surveyGroup_Qn").each(function(){
				if($(this).children(".qnTextBox").val().length > 0){
					qncount ++ ;
				}
			});
		});
		Global_jobfairQnCount = qncount;
		$("#qnCount").text(Global_jobfairQnCount);
	});

	//Apply highlight on column when mouseover a block
	$(document).on("mouseover", ".pyramidBlock", function(){
		var id = $(this).attr("id");
		var row = Math.floor(id/Global_PyramidBuilderColumns);
		var col = id%Global_PyramidBuilderColumns;
		for(var i=0; i<Global_PyramidBuilderRows; i++){
			Global_$PyramidBuilderBlockArray[col][i].css("border", "3px solid orange");
		}
	});
	//Remove highlight when mouseout a block
	$(document).on("mouseout", ".pyramidBlock", function(){
		var id = $(this).attr("id");
		var row = Math.floor(id/Global_PyramidBuilderColumns);
		var col = id%Global_PyramidBuilderColumns;
		for(var i=0; i<Global_PyramidBuilderRows; i++){
			Global_$PyramidBuilderBlockArray[col][i].css("border", "3px solid rgba(51,51,51,0.8)");
		}
	});

	//Selects OR Deselects a Pyramid block
	$(document).on("click", ".pyramidBlock", function(){
		//calculate the handle to this block in the PyramidBlockArray
		var id = $(this).attr("id");
		var row = Math.floor(id/Global_PyramidBuilderColumns);
		var col = id%Global_PyramidBuilderColumns;

		if(Global_jobfairPyramidBlockCount < Global_jobfairQnCount){
			if( $(this).hasClass("selectedBlock") ){
				$(this).removeClass("selectedBlock");
				$(this).css("background", Global_PyramidBuilderBlockUnselectedBackGround);
				BubbleDownBlocksInPyramidBuilder(col);
				//Ajust QnCount
				Global_jobfairPyramidBlockCount--;
				$("#qnCount").text(Global_jobfairQnCount - Global_jobfairPyramidBlockCount);
			} else {
				for(var i=0; i<row; i++){
					if( !Global_$PyramidBuilderBlockArray[col][i].hasClass("selectedBlock") ){
						Global_$PyramidBuilderBlockArray[col][i].css("background", Global_PyramidBuilderBlockSelectedBackGround);
						Global_$PyramidBuilderBlockArray[col][i].addClass("selectedBlock");
						//Ajust QnCount
						Global_jobfairPyramidBlockCount++;
						$("#qnCount").text(Global_jobfairQnCount - Global_jobfairPyramidBlockCount);
						break;
					}
				}

			}
		}
	});

	$('#saveSurvey').live("click", function(){
		SaveSurvey(false);
		//After saving the survey on the server, creation wizard cache should be cleared and the user should be taken to the survey management
		ClearSurveyWizardCache();

		Global_currentTitle = "Manage your created surveys";
		$("#lcd").html("");
		$("#lcd").html( $("#manageSurveyPanel").html() );
		$("#manageSurveyButton").trigger('click');
		//DisplayAdminSurveyList();
	});
	$('#publishSurvey').live("click", function(){
		SaveSurvey(true);
		//After saving the survey on the server, creation wizard cache should be cleared and the user should be taken to the survey management
		ClearSurveyWizardCache();

		Global_currentTitle = "Manage your created surveys";
		$("#lcd").html("");
		$("#lcd").html( $("#manageSurveyPanel").html() );
		//DisplayAdminSurveyList();
	});
}

SurveyWizardBindings();



/******
 * Add a Group container for survey questions.
 * Note: questions can only be added to a Group container.
 */
function AddGroup($button){
	//$("#surveyQuestionCreationBox").find(".jspContainer").detach();
	var groupContainerCode = '<div class="surveyGroup"><img src="WebResources/deleteGroupButton.png" id="deleteGroup" title="Delete this group" /><img src="WebResources/addQnButton.png" title="Add a Question" id="addQn"/></div>';
	$button.parent().append(groupContainerCode);
}


/******
 * Add a Group container for survey questions.
 * Note: questions can only be added to a Group container.
 */
function AddQuestion($button){
	//var groupContainerCode = '<div class="surveyGroup_Qn"><img class="removeQuestionButton" src="WebResources/removeQnButton.png" /><label>Question: </label><input type="text" class="qnTextBox" /><label>Answer: </label><input type="text" class="answerTextBox" /> </div>';

	var groupContainerCode;
	if(Global_isJobFair){
		groupContainerCode = '<div class="surveyGroup_Qn"><img class="removeQuestionButton" src="WebResources/removeQnButton.png" /><label>Question: </label><input type="text" class="qnTextBox" /></div>';
	} else {
		groupContainerCode = '<div class="surveyGroup_Qn"><img class="removeQuestionButton" src="WebResources/removeQnButton.png" /><label>Critical Value: </label><input type = "checkbox" class = "criticalValueBox" /><label>Question: </label><input type="text" class="qnTextBox" /><label>Answer: </label><input type="text" class="answerTextBox" /> </div>';
	}
	$button.parent().append(groupContainerCode);

}

/******
 * Saves the user created survey on the server side through ajax
 */
function SaveSurvey(publish){
	var ajaxURL = "SaveAdminCreateSurvey";
	var questionData = "";
	var groupcount = 1;
	var jobfairpyramidshape = "";

	//alert("jobfair? :"+Global_isJobFair);

	if(Global_isJobFair){
		//--Building query string for Jobfair--
		$("#workArea #jobfairAddQnZone").find(".surveyGroup").each(function(){
			//alert("located a survey group");
			$(this).find(".surveyGroup_Qn").each(function(){
				questionData += $(this).children(".qnTextBox").val() + Global_guiSplitter + "-1" + Global_guiSplitter + groupcount + Global_guiSplitter + "false" + Global_guiSplitter;	//since there are no answers for jobfair questions, so set answer to -1.
			});
			groupcount++;
		});
		//generate jobfair pyramid shape
		var pyramidStartColumn = 0, pyramidEndColumn = 0;
		for(var col=0; col< Global_PyramidBuilderColumns; col++){
			for(var row=0; row<Global_PyramidBuilderRows; row++){
				if( Global_$PyramidBuilderBlockArray[col][row].hasClass("selectedBlock") ){
					//alert("start:" + col);
					pyramidStartColumn = col;
					row+= 10000; //for the sake of breaking out of nested loops
					col+= 10000; //for the sake of breaking out of nested loops
				}
			}
		}
		for(var col=Global_PyramidBuilderColumns-1; col>= 0; col--){
			//alert("Finding ending col:  @" + col);
			for(var row=0; row<Global_PyramidBuilderRows; row++){
				if( Global_$PyramidBuilderBlockArray[col][row].hasClass("selectedBlock") ){
					//alert("end:" + col);
					pyramidEndColumn = col;
					row+= 10000; //for the sake of breaking out of nested loops
					col-= 10000; //for the sake of breaking out of nested loops
				}
			}
		}
		//alert("start col:"+pyramidStartColumn + "   end col:" + pyramidEndColumn);
		for(var col=pyramidStartColumn; col<= pyramidEndColumn; col++){
			for(var row=0; row<Global_PyramidBuilderRows; row++){
				if( Global_$PyramidBuilderBlockArray[col][row].hasClass("selectedBlock") ){
					jobfairpyramidshape += "-1,";
				}
			}
			jobfairpyramidshape = jobfairpyramidshape.substring(0, jobfairpyramidshape.length-1);
			jobfairpyramidshape += Global_guiSplitter;
		}

	} else {
		//Building query string for Custom Survey
		var checked;
		$("#workArea").children(".surveyGroup").each(function(){
			$(this).children(".surveyGroup_Qn").each(function(){
				if($(this).children(".criticalValueBox").attr("checked") == "checked"){
					checked = "true";
				}else{
					checked = "false";
				}
				if(!($(this).children(".qnTextBox").val() == "" || $(this).children(".answerTextBox").val() == "")){
					questionData += $(this).children(".qnTextBox").val() + Global_guiSplitter + $(this).children(".answerTextBox").val() + Global_guiSplitter + groupcount + Global_guiSplitter + checked +  Global_guiSplitter;
				}
			});
			groupcount++;
		});
	}


	$.ajax({
		type: "POST",
		url: ajaxURL+"?oldsid="+ Global_oldSID + "&title="+Global_surveyTitle + "&msg="+Global_surveyMessage+"&qndata="+questionData+"&publish="+publish+"&isjobfair="+Global_isJobFair+"&jobfairpyramidshape="+jobfairpyramidshape,
		success: function(responsedata){
			//alert(responsedata);
			//After saving the survey on the server, creation wizard cache should be cleared and the user should be taken to the survey management
			ClearSurveyWizardCache();

			Global_currentTitle = "Manage your created surveys";
			$("#lcd").html("");
			$("#lcd").html( $("#manageSurveyPanel").html() );
			$("#manageSurveyButton").trigger('click');
		}
	});
}



/*****
 * This is the Job Fair pyramid builder
 */

function JobFairPyramidBuilder(){
	$("#jobfairPyramidBuilder").unbind();
	$("#jobfairPyramidBuilder").html("");

	//(Re)Initialize the Global_$PyramidBuilderBlockArray
	Global_$PyramidBuilderBlockArray = new Array();
	for(var i=0; i<Global_PyramidBuilderColumns; i++){
		Global_$PyramidBuilderBlockArray.push(new Array(Global_PyramidBuilderRows));
	}

	var blockcount = 0;
	for(var rows=0; rows< Global_PyramidBuilderRows; rows++){
		for(var cols=0; cols< Global_PyramidBuilderColumns; cols++){
			$("#jobfairPyramidBuilder").append('<div class="pyramidBlock" id="'+ blockcount + '"></div>');
			Global_$PyramidBuilderBlockArray[cols][rows] = $($("#jobfairPyramidBuilder").find("#"+blockcount));
			blockcount++;
		}
	}

	//Global_$PyramidBuilderBlockArray[7][3].css("background", "red");`//test block mapping
}


function BubbleDownBlocksInPyramidBuilder(columnNumber){
	var selectedBlockCount = 0;
	for(var i=0; i<Global_PyramidBuilderRows; i++){ 
		if(Global_$PyramidBuilderBlockArray[columnNumber][i].hasClass("selectedBlock")){
			selectedBlockCount++;
			Global_$PyramidBuilderBlockArray[columnNumber][i].removeClass("selectedBlock");
			Global_$PyramidBuilderBlockArray[columnNumber][i].css("background", Global_PyramidBuilderBlockUnselectedBackGround);
		}
	}
	for(var j=0; j<selectedBlockCount; j++){
		Global_$PyramidBuilderBlockArray[columnNumber][j].addClass("selectedBlock");
		Global_$PyramidBuilderBlockArray[columnNumber][j].css("background", Global_PyramidBuilderBlockSelectedBackGround);
	}
}


/******
 * Clears all the temporary data from the Survey creation process
 */
function ClearSurveyWizardCache(){
	$("#surveyQuestionCreationBox #workArea").find(".surveyGroup").each(function(){$(this).detach();});
	Global_surveyTitle="";
	Global_surveyMessage="";
	Global_isJobFair = false;
	Global_editMode = false;
	Global_oldSID = 0;
}


/*******
 *	To be called when user clicks next button
 *	Slides all the Wizard Panels left by 938px (width of #lcd)
 */
function slidePhasePanelsLeft(){
	var phase1Left = parseInt($('#newSurveyWizardPhaseOne').css('left').replace('px', ''))-938;
	$('#newSurveyWizardPhaseOne').animate({left: phase1Left}, 1000);
	var phase2Left = parseInt($('#newSurveyWizardPhaseTwo').css('left').replace('px', ''))-938;
	$('#newSurveyWizardPhaseTwo').animate({left:phase2Left}, 1000);
	var phase3Left = parseInt($('#newSurveyWizardPhaseThree').css('left').replace('px', ''))-938;
	$('#newSurveyWizardPhaseThree').animate({'left':phase3Left}, 1000);
	var phase4Left = parseInt($('#newSurveyWizardPhaseFour').css('left').replace('px', ''))-938;
	$('#newSurveyWizardPhaseFour').animate({'left':phase4Left}, 1000);
}

/*******
 *	To be called when user clicks back button
 *	Slides all the Wizard Panels right by 938px (width of #lcd)
 */
function slidePhasePanelsRight(){
	var phase1Left = parseInt($('#newSurveyWizardPhaseOne').css('left').replace('px', '')) + 938;
	$('#newSurveyWizardPhaseOne').animate({left: phase1Left}, 1000);
	var phase2Left = parseInt($('#newSurveyWizardPhaseTwo').css('left').replace('px', '')) + 938;
	$('#newSurveyWizardPhaseTwo').animate({left:phase2Left}, 1000);
	var phase3Left = parseInt($('#newSurveyWizardPhaseThree').css('left').replace('px', '')) + 938;
	$('#newSurveyWizardPhaseThree').animate({'left':phase3Left}, 1000);
	var phase4Left = parseInt($('#newSurveyWizardPhaseFour').css('left').replace('px', '')) + 938;
	$('#newSurveyWizardPhaseFour').animate({'left':phase4Left}, 1000);
}