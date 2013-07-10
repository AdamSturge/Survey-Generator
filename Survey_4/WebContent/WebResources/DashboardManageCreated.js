


$('.adminSurveyListTitle').live("click", function(){
	var ajaxURL = "GetSurveyMatchResults?sid=" + $(this).attr("id");
	//alert("click: "+$(this).attr("id")+" "+$(this).text());
	$.ajax({
		url : ajaxURL,
		dataType : 'json'
	}).done(function(responseResult) {
		//remove old data
		$("#matchResultBox").find(".matchItem").each(function(){
			$(this).detach();
		});
		//insert new data
		var resultpairs = responseResult.Scores.split(Global_serverSplitter);
		var theScoreList = "";
		for(var i=0; i<resultpairs.length; i++){
			if(resultpairs[i].length > 3){
				var user_id = resultpairs[i].split(",")[0];
				var user_score = resultpairs[i].split(",")[1];
				var user_name = resultpairs[i].split(",")[2];
				var user_email = resultpairs[i].split(",")[3];
				var user_phone = resultpairs[i].split(",")[4];
				theScoreList += '<div class="matchItem"><div class="match_uid">'+ user_id + '</div><div class="match_score">'+user_score+'</div><div class="match_name">'+user_name+'</div><div class="match_email">'+user_email+'</div><div class="match_phone">'+user_phone+'</div></div>';
			}
		}
		$("#matchResultTable").append(theScoreList);
		//show the matchResultBox
		$("#matchResultBox").fadeIn("slow");
		$("#matchResultBox").children("*").fadeIn("slow");
	});
});	


/******
 * Gets the list of surveys created by the admin/current user.
 */
function DisplayAdminSurveyList(){
	var ajaxURL = "getAdminSurveyList";
	//Request survey information from server
	$.ajax({
		dataType: "json",
		url: ajaxURL,
		beforeSend: function(){
			//alert("before sending ");
			//$("#lcd").html('<img src="WebResources/loading.gif" class="loadingGraphics">');
			LoaderGraphic.load();
		},
		success: function(responsedata){

			$("#lcd").html("");
			$("#lcd").html( $("#manageSurveyPanel").html() );
			var surveyList = null;
			//alert("list content: " + responsedata.surveys);
			//if(responsedata.surveys.length > 3){
			surveyList = responsedata.surveys.split(Global_serverSplitter);
			//alert(surveyList.length);
			var theSurveyList = "";
			for(var i=0; i<surveyList.length; i++){
				if(surveyList[i]){
					var surveyID = surveyList[i].split(",")[0];
					var Global_surveyTitle = surveyList[i].split(",")[1];
					var Global_jobFairTitle = surveyList[i].split(",")[2];
					theSurveyList += '<tr><td class="adminSurveyListTitle" title="' +Global_surveyTitle+ '"id="' + surveyID + '">'+Global_surveyTitle+'</td><td class="surveyListAction"><input class = "Publish" type= "button" value = "Publish" sid ="'+ surveyID + '"/></td><td class="surveyListAction"><input class = "Edit" type= "button" value = "Edit" sid ="'+ surveyID + '"/></td><td class="surveyListAction"><input class = "Delete" type= "button" value = "Delete" sid ="'+ surveyID + '"/></td></tr>';
				}
			}
			$("#lcd #surveyListTable").append(theSurveyList);
			//}
			LoaderGraphic.unload();
		}
	});
}

$('.surveyListAction input.Publish').live('click', function(){
	var SID = $(this).attr("sid");
	$.ajax ({
		url:"SetOnline?sid=" + SID
	});

	alert("Survey has been published");
});

$('.surveyListAction input.Edit').live('click', function(){
	var SID = $(this).attr("sid");
	Global_editMode = true;
	Global_oldSID = SID;
	var isJobFair = false;
	
	$.ajax({
		url:"GetOnline?sid=" + SID,
		beforeSend: function(){},
		dataType : 'text',
		success: function(){}
	}).done(function(online){
		//alert(SID + " " + online + " " + isJobFair);
		EditSurvey(SID,online,isJobFair);
	});

});

$('.surveyListAction input.Delete').live('click', function(){
	var SID = $(this).attr("sid");
	$.ajax({
		url:"GetOnline?sid=" + SID,
		dataType : 'text'
	}).done(function(online){
		if(online == "false"){
			$.ajax ({
				url:"DeleteSurvey?sid=" + SID
			});
			$("#manageSurveyButton").trigger("click");
			alert("Survey has been deleted");
		}
		else{
			alert("This survey cannot be deleted since it is already online");
		}
	});
});

function EditSurvey(SID,online,isJobFair){
	if(online == "false"){
		$("#lcd").html( $("#createNewSurveyPanel").html() );
		$("#newSurveyWizardPhaseOne").fadeIn("slow");
		$("#newSurveyWizardPhaseOne").children("*").fadeIn("slow");	//make sure all elements hidden by default CSS is shown.

		$.ajax({
			url:"EditSurvey?sid=" + SID +"&isjobfair=" + isJobFair,
			dataType : 'json'
		}).done(function(responsedata) {
			if(isJobFair){
				$('input[name = "surveyType"][value = "JobFair"]').attr("checked", "checked");
			}else{
				$('input[name = "surveyType"][value = "CustomSurvey"]').attr("checked", "checked");
			}
			$.each(responsedata.Survey, function(key, value) {
				if(key == "Title"){
					$('#surveyTitle').attr("value", value);
					
				}
				if(key == "Message"){
					$("#surveyMessage").attr("value", value);
				}
				if(key == "QuestionData"){
					//alert(value);
					if(!isJobFair){
						var split = value.split(",");
						var questionStrings = new Array();
						var questionGroups = new Array();
						var questionAnswers = new Array();
						var criticalValues = new Array();
						var currentGroup = -1;

						var j = 0;

						for(var i = 0; i < split.length; i+=4){
							questionStrings[j] = split[i];
							j++;
						}

						j = 0;

						for(var i = 1; i < split.length; i+=4){
							questionGroups[j] = split[i];
							j++;
						}

						j = 0;

						for(var i = 2; i < split.length; i+=4){
							questionAnswers[j] = split[i];
							j++;
						}

						j = 0;

						for(var i = 3; i < split.length; i+=4){
							criticalValues[j] = split[i];
							j++;
						}

						j = -1;

						for(var i = 0; i < questionGroups.length; ++i){
							if(currentGroup < questionGroups[i]){
								$("#addGroupButton").trigger('click');
								j++;
								currentGroup = questionGroups[i];
							}

							$('img[id = "addQn"]').eq(j).trigger('click');

							if(criticalValues[i] == "true"){
								$(".criticalValueBox").eq(i).attr("checked" ,true);
							}
							else{
								$(".criticalValueBox").eq(i).attr("checked" ,false);
							}

							$(".qnTextBox").eq(i).attr("value" ,questionStrings[i]);
							$(".answerTextBox").eq(i).attr("value" ,questionAnswers[i]);

						}
						
						//alert(currentGroup);

					}else{
						//is a generic survey, no answers or critical values supplied
						Global_isJobFair = true;
						
						var split = value.split(",");
						var questionStrings = new Array();
						var questionGroups = new Array();
						var currentGroup = -1;

						var j = 0;

						for(var i = 0; i < split.length; i+=2){
							questionStrings[j] = split[i];
							j++;
						}

						j = 0;

						for(var i = 1; i < split.length; i+=2){
							questionGroups[j] = split[i];
							j++;
						}

						j = -1;

						for(var i = 0; i < questionGroups.length; ++i){
							if(currentGroup < questionGroups[i]){
								$("#addGroupButton").trigger('click');
								j++;
								currentGroup = questionGroups[i];
							}

							$('img[id = "addQn"]').eq(j).trigger('click');
							$(".qnTextBox").eq(i).attr("value" ,questionStrings[i]);


						}
					}
				}
			});
		});
	}else{
		alert("This survey cannot be edited since it is already online");
	}
}
