/****************
 * Note:
 * 
 * Write the initializing functions for this panel in this file.
 * Do not worry about length of function names. Make it intuitive and recognizable.
 * 
 * Remember to call the initializing function in DashboardLayout.js
 */

function DisplayGenericSurveys(){
	$.ajax({
		url:"GetGenericSurveys",
		dataType: 'json'
	}).done(function(responseData){
		var surveyList = responseData.surveys.split(Global_serverSplitter);
		var title;
		var SID;
		var surveyData;
		var theSurveyList;
		for(var i = 0; i < surveyList.length; ++i){
			surveyData = surveyList[i].split(",");
			title = surveyData[1];
			SID = surveyData[0];
			theSurveyList += '<tr><td class="genericSurveyListTitle" title="' +title+ '"id="' + SID + '">'+title+'</td></tr>';
		}
		
		$("#genericSurveyListTable").append(theSurveyList);
		
	});
}

$('.genericSurveyListTitle').live("click", function(){
	var ajaxURL = "GetGenericSurveyMatchResults?sid=" + $(this).attr("id");
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