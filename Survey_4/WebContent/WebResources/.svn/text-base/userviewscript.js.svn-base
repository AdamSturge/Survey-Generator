/*******************************************************************************
 * Author: Ji He
 */

$(document).ready(function() {
	// Check if admin preview mode is enabled. If yes, enable previewmode
	var currentmode = getParameterByName("mode");
	surveyID = getParameterByName("sid");
	Global_jobfairTitle = getParameterByName("jobfair");
	Global_isCompany = getParameterByName("iscompany");


	if (currentmode == "preview") {
		previewmode = true;
		window.onbeforeunload = function() {
			$.ajax({
				url : "DeleteSurvey?sid=" + surveyID,
			});
		};

	}
	if (surveyID) {
		surveyLoader();
		loadMessage(surveyID);

	} else {
		alert("bad reference string");
		$("#dialog").html("hello");
		$('#dialog').bPopup();

	}
});

var previewmode = false;
var serverSplitter = "#!#";
var columnSplitter = ",";
var guiSplitter = "_split_"; // reason for a guiSplitter is because # is not
// allowed in querystring unless html encoded
var ElementOnDrag = null; // the current element that is being dragged (the
//only draggable class of element would be "qn"
var cursorAtX = 0;
var cursorAtY = 0;
var PyramidData = new Array(); // Information on constructing pyramid; Length =
//width of pyramid, Index 'I' of array
//correspond to the (I+1)th column of pyramid;
//Element at index 'I' corresponds height of
//(I+1)th column.
var ColumnData = new Array(); // Information on the content of each grid in a
// particular column in the pyramid. Each
// element in ColumnData corresponds to a 1d
// array of numbers, and each number represents
// the QnTag that is placed inside that grid. If
// Qn5 has been placed inside the grid, then the
// value of the grid should be 5. a value of -1
// indicates an empty grid.
var QuestionList = new Array(); // Stores the entire list of question in
//sequence 0 .... N, !!!! however QnTags will
//be named from 1 ... N+1
var GroupList = new Array(); // contains all the questions in a given group
var criticalValueList = new Array(); //contains the list of question numbers that are marked critical.
var CurrentGroupNumber = 0; // indicates the currently displayed group, shifted
// by -1. ie) 0 indicates group 1 is being displayed
var CurrentQuestionNumber = 1; // indicates the question number of the last
// question added to the display
var QuestionTagOriginalOffset = new Array(); // stores original offset
//position of each QnTag in the
//questionlist.
var GridArray = null; // A 2D-Array that will hold references to each grid
//element in the pyramid; Imagine the pyramid is
//plotted inside a 2dArray, each Grid is an element in
//the 2dArray. GridArray allows fast and logical access
//to each grid in the pyramid.
//Accessed using GridArray[Rows][Columns] !! not to be confused with ColumnData
//and PyramidData

var surveyID = "";
var clientID = ""; // survey creator's id
var userID = ""; // survey participant's id
var Global_isCompany;
var Global_jobfairTitle;

/*******************************************************************************
 * 1. Ajax call to server to request for QuestionList and Pyramid Shape Data 2.
 * Prompt user to wait will data retrieval is being performed. 3. When the data
 * are successfully retrieved, present the data in the correct containers 4.
 * Apply listeners on interactable elements by calling "performBindings()". 5.
 * Apply scrolling UI to question box
 * 
 * [Note]: The protocol for retrieving data from server is as follows
 * [pyramidstart]col1_height, col2_height, ... ,
 * col2_height[pyramidend][QnStart] ... [QnEnd][QnStart] ... [QnEnd][QnStart]
 * ... [QnEnd]....
 */

function loadMessage(surveyID) {
	$.ajax({

		url : "getMessage.jsp",
		type : 'POST',
		data : ({
			"SurveyID" : surveyID
		}),
		success : function(results) {
			// alert(results);

			results = results.trim();
			// alert(results.length);
			if (results.length != 0) {
				$("#dialog").html(results);
				$("#dialog").bPopup();

			}
		}
	});

}

function surveyLoader() {
	// Reinitialize the arrays incase of survey reloading
	PyramidData = new Array();
	ColumnData = new Array();
	QuestionList = new Array();
	GroupList = new Array();
	CurrentQuestionNumber = 1;
	QuestionTagOriginalOffset = new Array();
	$("#diagramContainer").html('');
	$('#questionContainer').html('');
	$('*').unbind();
	ClearMsgDisplayer();
	if(previewMode = false){
		$.ajax({
			url : "GetCurrentGroupNumber?sid=" + surveyID,
			dataType : 'text'
		}).done(function(groupNumber) {
			CurrentGroupNumber = parseInt(groupNumber);
		});	
	}
	

	var ajaxURL = "";
	ajaxURL += "getSurvey?sid=" + surveyID + "&iscompany=" + Global_isCompany;
	// Request survey information from server
	$.ajax({
		url : ajaxURL,
		dataType : 'json'
	}).done(function(responsedata) {
		console.log(responsedata);
		// Spools through each key-value pair in JSON result
		$.each(responsedata.survey, function(key, value) {
			if (key == 'pyramid') {
				// PyramidData = value.split(serverSplitter);
				//alert("pyramid data:" + value);
				var dummyarray = value.split(serverSplitter); // each element
				// in dummyarray
				// contains grid
				// info for
				// respective
				// columns
				// for each column, extract the column height and push into
				// PyramidData array
				for ( var i = 0; i < dummyarray.length; i++) {
					// alert("traversing dummyarray, total loop count
					// ="+dummyarray.length+" iterator = "+i);
					if (dummyarray[i] == 'null') {
						// alert("pushing 0");
						PyramidData.push(0);
						ColumnData.push(null);
					} else {
						var columnarray = dummyarray[i].split(columnSplitter);
						// alert("pushing "+columnarray);
						ColumnData.push(columnarray);
						PyramidData.push(columnarray.length);
					}
				}
			} else if(key == "crit"){
				//alert('crit data = '+ value);
				var critList = value.split(',');
				for(var i=0;i<critList.length; i++){
					criticalValueList.push(critList[i]);
				}
			} else {
				var queslist = value.questions.split(serverSplitter); // put
				// each
				// question
				// into
				// arrays.
				for ( var i = 0; i < queslist.length; i++) {
					if (queslist[i].length > 1) {
						GroupList.push(queslist[i]);
					}
				}
				QuestionList.push(GroupList); // put the current group of
				// questions into the
				// QuestionList
				GroupList = new Array(); // re-initialize the group
				// container.
			}
		});

		PopulatePyramid();

		for ( var i = 0; i <= CurrentGroupNumber; ++i) {
			PopulateQuestions(i);
			RestoreSavedState();
			if (i < CurrentGroupNumber) {
				ClearQuestions();
			}

			performBindings();

		}

		ApplyCriticalValues();	//ticks all the checkbox of the critical questions

		ReviseInterface(); // check if some buttons should show up or not.

		PerformCompletionCheck(); // check if the submit button should show
		// up.

		// re-size revert zone
		$('#revertZone').css('height', $('html').height() + 'px');
		// $('#revertZone').css('width', '800px');
		$('#revertZone').css('width', $('html').width() + 'px');

		// calculate with of degreebar
		$("#degreeBar").width(PyramidData.length * (80 + 3)); // where 80 is
		// width of
		// placeholder,
		// and 3 is the
		// margin
		// between
		// placeholders
		$('#questionContainer').jScrollPane();
	});
}

/*******************************************************************************
 * Place event listeners on interactable elements.
 */
function performBindings() {
	$('html *').unbind();

	$('.UserControlPanelButton').hover(function() {
		var curposition = $(this).offset();
		$(this).offset({
			top : curposition.top - 3,
			left : curposition.left - 3
		});
		$(this).css("border", "3px solid #e4e277");
		$(this).css("z-index", "10");
		$(this).width(66);
		$(this).height(66);
	}, function() {
		var curposition = $(this).offset();
		$(this).offset({
			top : curposition.top + 3,
			left : curposition.left + 3
		});
		$(this).width(60);
		$(this).height(60);
		$(this).css("border", "0");
		$(this).css("z-index", "0");
	});
	if (!previewmode) {
		$('#saveButton').click(function() {
			SaveState(false);
		});

		$('#submitButton').live("click", function() {
			SaveState(true);
		});

		$('#loadButton').click(function() {
			surveyLoader();
		});

		$('#discardButton').click(function() {
			DiscardAnswers();
		});
	}
	$('#nextGroupButton').click(function() {
		$('#nextGroupButton').fadeOut('fast');
		CurrentGroupNumber++;
		ClearQuestions();
		PopulateQuestions(CurrentGroupNumber);
		performBindings();
		LockPyramidSquares();
		SaveState(false);
		
	});

	$("#homeButton").click(function() {
		history.back();
	});

	// Adding draggable listeners to QnTag class elements.
	$('.QnTag')
	.draggable(
			{
				opacity : 0.7,
				helper : 'clone',
				appendTo : 'body', // this did wonders to help allowing
				// drag motion stay on top(visible)
				// of drop zone
				containment : 'body',
				revert : "invalid",
				// revert: function(){ $(this).animate({top: '150px',
				// left: '100px'} , "slow"); },
				cursorAt : {
					top : 21,
					left : 21
				},
				start : function() {
					$(this).data("origPosition", $(this).position());
				},
				drag : function() {
					$(this)
					.css("-moz-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(90,210,255,0.5)"); // Alter
					// Tag
					// CSS
					// to
					// indicate
					// in
					// drag
					$(this)
					.css("-webkit-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(90,210,255,0.5)"); // Alter
					// Tag
					// CSS
					// to
					// indicate
					// in
					// drag
					$(this)
					.css("box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(90,210,255,0.5)"); // Alter
					// Tag
					// CSS
					// to
					// indicate
					// in
					// drag
				},
				stop : function(event, ui) {
					$(this)
					.css("-moz-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(255, 100, 48, 0.5)"); // restore
					// Tag
					// CSS
					$(this)
					.css("-webkit-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(255, 100, 48, 0.5)"); // restore
					// Tag
					// CSS
					$(this)
					.css("box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 6px 4px rgba(255, 100, 48, 0.5)"); // restore
					// Tag
					// CSS
					// Check for survey completion
					PerformCompletionCheck();
				}
			});

	// Droppable Listeners for NON qnPlaceHolder elements; Dropping into these
	// elements causes the qnTag to be sent back to its original location in
	// QnList
	$('#diagramContainer').droppable(
			{
				drop : function(event, ui) {
					var tagOwner = $('#questionContainer').find(
							'#' + ui.draggable.attr('id'));
					$(tagOwner).find('.qnPlaceholder').append(
							ui.draggable.detach());

				}
			});

	$('#revertZone').droppable(
			{
				drop : function(event, ui) {
					var tagOwner = $('#questionContainer').find(
							'#' + ui.draggable.attr('id'));
					$(tagOwner).find('.qnPlaceholder').append(
							ui.draggable.detach());

				}
			});

	// Droppable listeners for Pyramid Grids and Question List Tag holders
	$('.qnPlaceholder')
	.droppable(
			{
				drop : function(event, ui) {
					// first perform a check for the placeholder type:
					// Is the placeholder in QuestionList or in Pyramid
					// If placeholder is in QuestionList, and container
					// ID is same as draggable ID, allow drop, else
					// refuse drop
					if (($(this).parent().attr("class") == "QnItem" && $(
							this).attr("id") == ui.draggable.attr("id"))
							|| $(this).parent().attr("id") == "diagramContainer") {
						ElementOnDrag = ui.draggable;
						// check if the placeholder is empty, if empty,
						// append the question, else, put the question
						// back to where it was before appending new
						// question
						if ($(this).find(".QnTag").length <= 0) {
							$(this).append($(ElementOnDrag).detach());
						} else {
							var tagOwner = $('#questionContainer')
							.find(
									'#'
									+ $(this).find(
									".QnTag")
									.attr('id'));
							$(tagOwner).find('.qnPlaceholder').append(
									$(this).find(".QnTag").detach());
							$(this).append($(ElementOnDrag).detach());
						}
						$(ElementOnDrag).css({
							'top' : 'auto',
							'left' : 'auto'
						});
						ElementOnDrag = null;
						$(this)
						.css("-moz-box-shadow",
						"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
						// Grid
						// CSS
						$(this)
						.css("-webkit-box-shadow",
						"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
						// Grid
						// CSS
						$(this)
						.css("box-shadow",
						"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
						// Grid
						// CSS

					} else {
						ui.draggable.animate(
								ui.draggable.data().origPosition,
								"slow");
						return;
					}
				},
				over : function(event, ui) {
					$(this)
					.css("-moz-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 4px 4px rgba(41, 253, 0, 0.5)"); // Alter
					// Grid
					// CSS
					// to
					// indicate
					// droppable
					// Zone
					$(this)
					.css("-webkit-box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 4px 4px rgba(41, 253, 0, 0.5)"); // Alter
					// Grid
					// CSS
					// to
					// indicate
					// droppable
					// Zone
					$(this)
					.css("box-shadow",
					"0 0 1px 1px rgba(255,255,255,0.4) inset, 0 0 4px 4px rgba(41, 253, 0, 0.5)"); // Alter
					// Grid
					// CSS
					// to
					// indicate
					// droppable
					// Zone
				},
				out : function(event, ui) {
					$(this).css("-moz-box-shadow",
					"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
					// Grid
					// CSS
					$(this).css("-webkit-box-shadow",
					"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
					// Grid
					// CSS
					$(this).css("box-shadow",
					"0px 0px 2px 2px rgba(47, 47, 47, 0.5)"); // restore
					// Grid
					// CSS
				},
				activeClass : "ui-state-hover",
				hoverClass : "ui-state-active"
			});

}

/*******************************************************************************
 * Create a 2D array using max height and width of Pyramid, and fit the pyramid
 * into this 2D array such that one grid in pyramid is a grid in 2D array.
 */
function PopulatePyramid() {
	var rows = Math.max.apply(null, PyramidData);
	var cols = PyramidData.length;
	// alert(rows+" "+cols);
	GridArray = new Array(rows);
	for ( var i = 0; i < rows; i++) {
		GridArray[i] = new Array(cols);
	}
	// Populate 2D array of Grids, storing reference to each Grid, and position
	// the grids.
	for ( var j = 0; j < rows; j++) {
		for ( var k = 0; k < cols; k++) {
			$("#diagramContainer").append('<div class="tableGrid"></div>');
			GridArray[j][k] = $("#diagramContainer .tableGrid").last();
			var height = $(GridArray[j][k]).height();
			var width = $(GridArray[j][k]).width();
			var distancebetweengrids = 3;
			$(GridArray[j][k]).css("position", "absolute");
			$(GridArray[j][k])
			.css(
					"top",
					(distancebetweengrids + j
							* (height + distancebetweengrids))
							+ "px");
			$(GridArray[j][k]).css(
					"left",
					(distancebetweengrids + k * (width + distancebetweengrids))
					+ "px");
		}
	}
	// Draw apply qnPlaceHolderClass to interactable Grids
	for ( var i = 0; i < cols; i++) {
		for ( var j = 0; j < PyramidData[i]; j++) {
			$(GridArray[j][i]).removeClass().addClass("qnPlaceholder");
			// $(GridArray[j][i]).attr("id",""); //will need an ID to disable
			// dropping into them after a question group is completed
		}
	}

}

function PopulateQuestions(GroupNumber) {
	for ( var i = 0; i < QuestionList[GroupNumber].length; i++) {
		var qnItem = '<div class="QnItem" id="' + CurrentQuestionNumber + '">';
		qnItem += '<div class="qnPlaceholder" id="' + CurrentQuestionNumber
		+ '"><h1>' + CurrentQuestionNumber + '</h1>';
		qnItem += '<div class="QnTag" title="Drag me" id="'
			+ CurrentQuestionNumber + '"><h1>' + CurrentQuestionNumber
			+ '</div>';
		qnItem += '</div>';
		// Checks if it is a professsor filling up a Generic Survey
		if (getParameterByName("iscompany") == "true") {
			qnItem += '<div class="QnCriticalBox" title="Check this box if this Question is critical" id="'
				+ CurrentQuestionNumber
				+ '"><label>Critical Value</label><br/><input id="criticalcheck" type="checkbox" /></div>';
		}
		qnItem += '<div class="QnBox" id="' + CurrentQuestionNumber + '"><p>';
		qnItem += QuestionList[GroupNumber][i];
		qnItem += '</p></div></div>';

		$('#questionContainer').append(qnItem);
		CurrentQuestionNumber++;
	}

}

function ClearQuestions() {
	$('#questionContainer').html("");
}

function LockPyramidSquares() {
	var cols = PyramidData.length;
	for ( var i = 0; i < cols; i++) {
		for ( var j = 0; j < PyramidData[i]; j++) {
			if ($(GridArray[j][i]).find(".QnTag").length > 0) {
				$(GridArray[j][i]).droppable('option', 'disabled', true);

			}

		}
	}

}
//resize QnTags and qnPlaceHolders: currently qnPlaceHolderWidth is 80, max
//number of qnplaceholder allowed is 7
//to allow for more. dynamic resize is required.
function ResizeGrids() {
	var measurement = 0;
	if (PyramidData.length > 7) {
		var distancebetweengrids = 3;
		measurement = Math
		.floor(($('#diagramContainer').width() - distancebetweengrids
				* PyramidData.length)
				/ PyramidData.length);
	} else {
		measurement = 80;
	}
	$('.tableGrid').css('min-width', measurement + 'px');
	$('.tableGrid').css('min-height', measurement + 'px');
	$('.qnPlaceholder').css('min-width', measurement + 'px');
	$('.qnPlaceholder').css('min-height', measurement + 'px');
	$('.QnTag').css('min-width', (measurement - 4) + 'px');
	$('.QnTag').css('min-height', (measurement - 4) + 'px');
	//alert(measurement + 'px');
}

/*******************************************************************************
 * Save the current state of survey, this includes layout of grids inside the
 * pyramid, as well as the completion of survey questions
 */
function SaveState(DoSubmit) {
	// Display the Saving msg
	var doSubmitQueryString = "";
	if (DoSubmit) {
		doSubmitQueryString = "&submit=true";
	}

	var rows = GridArray.length;
	var cols = GridArray[0].length;
	var pyramidString = "";
	for ( var i = 0; i < cols; i++) {
		for ( var j = 0; j < rows; j++) {
			// Check if current grid is being used as a qnPlaceHolder
			// if($(GridArray[j][i]).find('.qnPlaceholder').length>0){
			if ($(GridArray[j][i]).hasClass('qnPlaceholder')) {
				if ($(GridArray[j][i]).find('h1').length > 0) {
					pyramidString += $(GridArray[j][i]).find('h1').text() + ','; // case
					// of
					// filled
					// qnPlaceholder
				} else {
					pyramidString += '-1,'; // case of unfilled qnPlaceholder
				}
			} else if (j <= 0) {
				pyramidString += 'null'; // case of empty column
			}

		}
		// remove the last grid spliiter
		if (pyramidString.charAt(pyramidString.length - 1) == ',') {
			pyramidString = pyramidString
			.substring(0, pyramidString.length - 1);
		}
		pyramidString += '___';
	}
	// remove the last column splitter
	if (pyramidString.charAt(pyramidString.length - 1) == "_") {
		pyramidString = pyramidString.substring(0, pyramidString.length - 3);
	}

	// Append critical values
	if (Global_isCompany == "true" && $('.QnCriticalBox').find('#criticalcheck').attr('checked').length>0) {
		pyramidString += "CR!T";
		$('.QnCriticalBox').each(function() {
			//alert("a critical box found");
			if ($(this).find('#criticalcheck').attr('checked')) {
				pyramidString += $(this).attr('id') + ',';
			}
		});
	}
	
	// remove the last critical value splitter
	if (pyramidString.charAt(pyramidString.length - 1) == ",") {
		pyramidString = pyramidString.substring(0, pyramidString.length - 1);
	}

	// alert(pyramidString);

	// ---- pyramidString now contains the querystring to be sent to server-----
	var ajaxURL = "SaveUserSurvey?sid=" + surveyID + "&data=" + pyramidString
	+ doSubmitQueryString + "&jobfair=" + Global_jobfairTitle
	+ "&iscompany=" + Global_isCompany + "&groupnumber="
	+ CurrentGroupNumber;
	//alert(ajaxURL);
	$.ajax({
		url : ajaxURL
	}).done(function() {
//		if (submit) {
//			DisplaySubmitMsg();
//		} else {
//			DisplaySavedMsg();
//		}
		//go back to dashboard
		if(DoSubmit){
			history.back();
		}
	});

}

/*******************************************************************************
 * Determine if there is a previously started state to be restored, if yes,
 * restore the survey to the previously saved state.
 */
function RestoreSavedState() {
	// check if there is a state for restoration
	var restorationNeeded = false;
	for ( var i = 0; i < ColumnData.length; i++) {
		if (ColumnData[i] != null) {
			restorationNeeded = true;
			break;
		}
	}

	// Proceed with state restoration
	if (restorationNeeded) {
		for ( var cols = 0; cols < ColumnData.length; cols++) {
			// for each column inspect column height so that each grid in the
			// column can be accessed
			if (ColumnData[cols] != null) {
				var columnheight = ColumnData[cols].length;
				// for each grid in a column
				for ( var row = 0; row < columnheight; row++) {
					// check for the content, if it is bigger than 0 (QnTag
					// starts from 1)
					if (ColumnData[cols][row] > 0) {
						var qnNumber = ColumnData[cols][row];
						// search through the populated QnItem(s) to find a
						// QnTag that matches the qnNumber, move
						// the QnTag in that QnItem to the corresponding (i,j)
						// grid
						$('.QnItem').each(
								function() {
									// alert($(this).html());
									if ($(this).children('.qnPlaceholder')
											.children('.QnTag').children('h1')
											.text() == qnNumber) {
										var qntag = $(this).children(
										'.qnPlaceholder').children(
										'.QnTag').detach();
										$(GridArray[row][cols]).append(qntag);
									}
								});
					} else {

					}
				}
			}
		}
	}
}

/*******************************************************************************
 * MsgDisplayer related functions
 */
function ClearMsgDisplayer() {
	$('.messageDisplayer').html('');
}

function DisplaySavingMsg() {
	ClearMsgDisplayer();
	$('.messageDisplayer').html(
	'<img src="WebResources\loading.gif"/>Saving ... ');
}

function DisplaySavedMsg() {
	$('.messageDisplayer').html('Saved');
	setTimeout(function() {
		ClearMsgDisplayer();
	}, 3000);
}

function DisplaySubmitMsg() {
	$('.messageDisplayer').html('Submitted');
	setTimeout(function() {
		ClearMsgDisplayer();
	}, 3000);
}

/*******************************************************************************
 * Puts all the answers in pyramid back to question list
 */
function DiscardAnswers() {
	var rows = GridArray.length;
	var cols = GridArray[0].length;
	for ( var i = 0; i < cols; i++) {
		for ( var j = 0; j < rows; j++) {
			if ($(GridArray[j][i]).find('.QnTag').length > 0) {
				var qnNumber = $(GridArray[j][i]).find('.QnTag').children('h1')
				.text();
				// alert(qnNumber);
				$('.QnItem').each(
						function() {
							// alert($(this).html());
							if ($(this).children('.qnPlaceholder').find('h1')
									.text() == qnNumber) {
								var qntag = $(GridArray[j][i]).find('.QnTag')
								.detach();
								$(this).children('.qnPlaceholder')
								.append(qntag);
							}
						});
			}
		}
	}

}

/*******************************************************************************
 * Check if some buttons should show up in the controller panel.
 */
function ReviseInterface() {
	// alert(QuestionList.length);
	//var numberOfGroupsOfQuestions = QuestionList.length;
	//if (numberOfGroupsOfQuestions > 1) {
	//	$('#nextGroupButton').show();
	//}
}

/*******************************************************************************
 * Check if user has completed survey If survey is complete, show the submit
 * button
 */
function PerformCompletionCheck() {
	// Checks if user has completed survey - filled up all the qnPlaceHolders
	// with QnTags
	var surveyComplete = true;
	$('#diagramContainer .qnPlaceHolder').each(function() {
		if ($(this).find('.QnTag').length == 0) {
			surveyComplete = false;
		}
	});
	if (surveyComplete) {
		$('#submitButton').fadeIn('fast');
	} else {
		$('#submitButton').fadeOut('fast');
	}

	if($("#questionContainer").find(".QnTag").length == 0 && CurrentGroupNumber < QuestionList.length-1){
		$('#nextGroupButton').fadeIn('fast');
	}else{
		$('#nextGroupButton').fadeOut('fast');
	}

}

/***********
 * Applies critical values to the check boxes.
 */
function ApplyCriticalValues(){
	//alert("@ApplyCriticalValues: length="+criticalValueList.length);
	for(var i=0;i<criticalValueList.length;i++){
		$('#questionContainer').find('.QnCriticalBox').each(function(){
			if( $(this).attr('id') == criticalValueList[i] ){
				$(this).find('#criticalcheck').prop('checked', true);
			}
		});
	}
}

/*******************************************************************************
 * This method is directly copied from stackoverflow. URL:
 * http://stackoverflow.com/a/5158301
 * 
 * @param name:
 *            querystring parameter name
 * @returns value of the requested querystring parameter
 */
function getParameterByName(name) {
	var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
	return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}