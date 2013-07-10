var DashboardRelateSurveySpace = DashboardRelateSurveySpace || {};

/*****
* This function loads the jobfair list from server and presents the list inside jobfair list table.
*/
DashboardRelateSurveySpace.PopulateJobfairList = function (){
	//alert("@ PopulateJobfairList");
	DashboardRelateSurveySpace.performBinding();
	
	$('td#relateSurveyJobfairList .listContainer').html('');	//clear contents
	
	var ajaxURL = "GetRelateSurveyInfo";
	$.ajax({
		  url: ajaxURL,
		  dataType: "json",
		  success: function(responsedata){
			  var jobfairList = responsedata["JobFairs"];
			  var jobfairItems = jobfairList.split("#!#");
			  for(var i=0;i<jobfairItems.length; i++){
				  if(jobfairItems[i].length > 3){ 
					  var jobfairID = jobfairItems[i].split(',')[0];
					  var jobfairTitle = jobfairItems[i].split(',')[1];
					  $('td#relateSurveyJobfairList .listContainer').append('<div class="relateSurveyJobfairItem" id="' + jobfairID + '">' + jobfairTitle + '</div>');
				  }
			  }
		  }
	});
};


/****
 * This function performs an AJAX call to GEtRelatSurveyInfo servlet. 
 * The Json response is in the following format
 * { "Surveys": { "pairedSurveys":" .... ..... ...." , "unpairedSurveys":" ... ... ..." }
 * The result is then loaded into their respective list containers
 * 
 * @param jobfairid
 */
DashboardRelateSurveySpace.PopulateSurveyLists = function (jobfairid){
	$('td#relateSurveyJobfairRelatedSurveyList .listContainer').html('');	//clear contents
	$('td#relateSurveyJobfairCanBeRelatedSurveyList .listContainer').html('');	//clear contents
	var ajaxURL = "GetRelateSurveyInfo?jobfairid="+jobfairid;
	$.ajax({
		  url: ajaxURL,
		  dataType: "json",
		  success: function(responsedata){
			  DashboardRelateSurveySpace.LoadSurveyLists(responsedata, jobfairid);
		  }
	});
};


DashboardRelateSurveySpace.performBinding = function(){
	$('#relateSurveyTable *').unbind();
	
	$('td#relateSurveyJobfairList .listContainer .relateSurveyJobfairItem').live('click', function(){
		DashboardRelateSurveySpace.PopulateSurveyLists($(this).attr('id'));
	});
	
	//Relates an unrelated survey item to jobfair
	$('td#relateSurveyJobfairCanBeRelatedSurveyList .listContainer .relateSurveyRelatableSurveyItem').live('click', function(){
		DashboardRelateSurveySpace.RelateSurveyItem($(this).attr('jobfairid'), $(this).attr('id'), $(this).text().replace("[Relate]", ""));
	});
	
	//Unrelates an related survey item from jobfair
	$('td#relateSurveyJobfairRelatedSurveyList .listContainer .relateSurveyRelatedSurveyItem').live('click', function(){
		DashboardRelateSurveySpace.UnrelateSurveyItem($(this).attr('jobfairid'), $(this).attr('id'), $(this).text().replace("[Unrelate]", ""));
	});
}

/*****
 * Relates an survey item to a jobfair item
 * @param jobfairid
 * @param surveyid
 */
DashboardRelateSurveySpace.RelateSurveyItem = function(jobfairid, surveyid, surveytitle){
	var ajaxURL = "GetRelateSurveyInfo?action=relate&jobfairid="+jobfairid+"&surveyid="+surveyid+"&surveytitle="+surveytitle;
	$.ajax({
		  url: ajaxURL,
		  dataType: "json",
		  success: function(responsedata){
			  DashboardRelateSurveySpace.LoadSurveyLists(responsedata, jobfairid);
		  }
	});
}


/*****
 * Unrelates an survey item from a jobfair item
 */
DashboardRelateSurveySpace.UnrelateSurveyItem = function(jobfairid, surveyid, surveytitle){
	var ajaxURL = "GetRelateSurveyInfo?action=unrelate&jobfairid="+jobfairid+"&surveyid="+surveyid+"&surveytitle="+surveytitle;
	$.ajax({
		  url: ajaxURL,
		  dataType: "json",
		  success: function(responsedata){
			  DashboardRelateSurveySpace.LoadSurveyLists(responsedata, jobfairid);
		  }
	});
}

/******
 * This method populates the Related and Unrelated survey lists. Takes the JSON response from the respective AJAX functions.
 * @param responsedata
 */
DashboardRelateSurveySpace.LoadSurveyLists = function(responsedata, jobfairid){
	  var pairsurveys = responsedata["Surveys"]["pairedSurveys"];
	  var unpairsurveys = responsedata["Surveys"]["unpairedSurveys"];
	  
	  //clear the containers
	  $('td#relateSurveyJobfairRelatedSurveyList .listContainer').html("");
	  $('td#relateSurveyJobfairCanBeRelatedSurveyList .listContainer').html("");
	  
	  //load in paired surveys
	  var pairedsurveylist = pairsurveys.split("#!#");
	  for(var i=0;i<pairedsurveylist.length; i++){
		  if(pairedsurveylist[i].length > 3){ 
			  var surveyID = pairedsurveylist[i].split(',')[0];
			  var surveyTitle = pairedsurveylist[i].split(',')[1];
			  $('td#relateSurveyJobfairRelatedSurveyList .listContainer').append('<div class="relateSurveyRelatedSurveyItem" id="' + surveyID + '" jobfairid="' + jobfairid + '"> [Unrelate] ' + surveyTitle + '</div>');
		  }
	  }
	  
	 //load in paired surveys
	  var unpairedsurveylist = unpairsurveys.split("#!#");
	  for(var i=0;i<unpairedsurveylist.length; i++){
		  if(unpairedsurveylist[i].length > 3){ 
			  var surveyID = unpairedsurveylist[i].split(',')[0];
			  var surveyTitle = unpairedsurveylist[i].split(',')[1];
			  $('td#relateSurveyJobfairCanBeRelatedSurveyList .listContainer').append('<div class="relateSurveyRelatableSurveyItem" id="' + surveyID + '" jobfairid="' + jobfairid + '"> [Relate] ' + surveyTitle + '</div>');
		  }
	  }
}