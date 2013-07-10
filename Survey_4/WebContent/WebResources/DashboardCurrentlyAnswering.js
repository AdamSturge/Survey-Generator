
/******
 * Gets the list of surveys that the current user is answering or has answered.
 */
function DisplayUserSurveyList(){
	var ajaxURL = "GetUserSurveyList";
	//Request survey information from server
//	$.ajax({
//		  url: "GetServerIP",
//		  success: function(Global_serverIP){
				$.ajax({
					  dataType: "json",
					  url: ajaxURL,
					  beforeSend: function(){LoaderGraphic.load();},
					  success: function(responsedata){
							var surveyList = null;
							//alert("list content: " + responsedata.surveys);
							if(responsedata.surveys.length > 3){
								surveyList = responsedata.surveys.split(Global_serverSplitter);
								//alert(surveyList.length);
								var aSurveyItem = "";
								var URL = "";
								for(var i=0; i<surveyList.length; i++){
									if(surveyList[i]){
										var surveyID = surveyList[i].split(",")[0];
										var surveyTitle = surveyList[i].split(",")[1];
										var jobfairTitle = surveyList[i].split(",")[2];
										var isCompany = surveyList[i].split(",")[3];
										var author = surveyList[i].split(",")[4];
										
										URL = "http://"+Global_serverIP+":8080/Survey_4/DoSurvey?sid="+ surveyID + "&jobfair="+jobfairTitle+"&iscompany="+isCompany;
										aSurveyItem += '<tr><td class="userSurveyListTitle"><a href="'+ URL +'">' +surveyTitle+'</a></td><td class="Author">' + author +'</td><td class="userJobfairTitle">'+ jobfairTitle + '</td></tr>';
									}
								}
								$("#lcd #userSurveyListTable").append(aSurveyItem);
							}
							LoaderGraphic.unload();
					  }
					});
//		  }
//	});
}