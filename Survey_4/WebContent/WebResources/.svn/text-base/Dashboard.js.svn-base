/*******************************************************************************
 * Author: Ji He
 */


var Global_currentTitle = "";
var Global_surveyTitle = "";
var Global_isJobFair = false;	//if false, it means survey type is customSurvey, customSurvey and JobFair are mustally exclusive
var Global_surveyMessage = "";
var Global_serverSplitter = "#!#";
var Global_columnSplitter = ",";
var Global_guiSplitter = "_split_";		//reason for a Global_guiSplitter is because # is not allowed in querystring unless html encoded
var Global_serverIP = null;
var Global_$PyramidBuilderBlockArray;	//each element[column][row] of this array maps to a Block in the PyramidBuilder
var Global_PyramidBuilderColumns = 10;
var Global_PyramidBuilderRows = 8;
var Global_PyramidBuilderBlockSelectedBackGround = "rgb(255, 255, 0)";
var Global_PyramidBuilderBlockUnselectedBackGround = "rgba(180,180,180,0.4)";
var Global_jobfairQnCount = 0;
var Global_jobfairPyramidBlockCount = 0;
var Global_editMode = false;
var Global_oldSID = 0; //used to delete old data from the database in the case of the save button 