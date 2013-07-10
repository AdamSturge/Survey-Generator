package database;

public abstract class SQLStatements {
	public final static String WRITE_PYRAMID_SQUARES = "INSERT INTO Pyramids2 (ID, SID, COL, ROW, ANS) VALUES (?, ?,?, ?, ?)";
	public final static String READ_PYRAMID_ANSWER = "SELECT ANS FROM Pyramids2 WHERE ID = ? AND SID = ? AND COL = ? AND ROW = ?";
	public final static String READ_MAX_COLUMN = "SELECT MAX(COL) FROM Pyramids2 WHERE ID = ? AND SID = ?";
	public final static String READ_MAX_ROW = "SELECT MAX(ROW) FROM Pyramids2 WHERE ID = ? AND SID = ? AND COL = ?";

	public final static String WRITE_QUESTIONLIST_QUESTIONS = "INSERT INTO QuestionLists2 (SID, QuestionString, QuestionAnswer, GroupNumber, QuestionNumber, CriticalValue) VALUES (?, ?, ?, ?, ?, ?)";
	public final static String READ_QUESTIONLIST_STRING = "SELECT QuestionString FROM QuestionLists2 WHERE SID = ? AND QuestionNumber = ?";
	public final static String READ_QUESTIONLIST_ANSWER = "SELECT QuestionAnswer FROM QuestionLists2 WHERE SID = ? AND QuestionNumber = ?";
	public final static String READ_QUESTIONLIST_GROUPS = "SELECT GroupNumber FROM QuestionLists2 WHERE SID = ? ORDER BY GroupNumber ASC";
	public final static String READ_QUESTIONLIST_QUESTIONNUMBER = "SELECT QuestionNumber FROM QuestionLists2 WHERE SID = ? ORDER BY QuestionNumber ASC";
	public final static String READ_QUESTIONLIST_CRIT = "SELECT CriticalValue FROM QuestionLists2 WHERE SID = ? AND QuestionNumber = ?";
	
	public final static String DELETE_PYRAMID = "DELETE FROM Pyramids2 WHERE SID = ?";
	public final static String DELETE_QUESTIONLIST = "DELETE FROM QuestionLists2 WHERE SID = ?";
	public final static String DELETE_ADMINLOOKUP = "DELETE FROM AdminSurveyLookup WHERE SID = ?";
	public final static String DELETE_USERLOOKUP = "DELETE FROM UserSurveyLookup WHERE SID = ?";
	
	public final static String WRITE_ADMINSURVEYLOOKUP = "INSERT INTO AdminSurveyLookup (AID, SID, Title, Message, isJobFair) VALUES (?, ?, ?, ?, ?)";
	public final static String READ_ADMINSURVEYLOOKUP = "SELECT SID, Title FROM AdminSurveyLookup WHERE AID = ?";
	public final static String READ_SID = "SELECT SID FROM AdminSurveyLookup WHERE AID = ?";
	public final static String READ_TITLE = "SELECT Title FROM AdminSurveyLookup WHERE AID = ?";
	public final static String WRITE_USERSURVEYLOOKUP = "INSERT INTO UserSurveyLookup (UID, SID, Title, JobFairTitle, isCompany) VALUES (?, ?, ?, ?, ?)";
	public final static String READ_USERSURVEYLOOKUP = "SELECT SID, Title, JobFairTitle, Score, isCompany FROM UserSurveyLookup WHERE UID = ?";
	public final static String READ_UID = "SELECT UID FROM UserSurveyLookup WHERE SID = ?";
	public final static String UPDATE_SCORE = "UPDATE UserSurveyLookup SET Score = ? WHERE UID = ? AND SID = ?";
	public final static String READ_SCORE = "SELECT Score FROM UserSurveyLookup Where UID = ? and SID = ?";
	public final static String UPDATE_ONLINE = "UPDATE AdminSurveyLookup SET Online = ? WHERE SID = ? ";
	public final static String READ_ONLINE = "SELECT Online FROM AdminSurveyLookup WHERE SID = ? ";
	public final static String READ_ADMINTITLE = "SELECT Title FROM AdminSurveyLookup WHERE SID = ?";
	public final static String READ_ADMINTITLEANDMESSAGE = "SELECT Title,Message FROM AdminSurveyLookup WHERE SID = ?";
	public final static String READ_USERTITLE = "SELECT Title FROM UserSurveyLookup WHERE UID = ? AND SID = ?";
	public final static String READ_ADMINSURVEYLOOKUP_JOBLESS = "SELECT SID, Title FROM AdminSurveyLookup WHERE AID = ? AND isJobFair = 0";
	public final static String READ_ADMIN_AID = "SELECT AID FROM AdminSurveyLookup WHERE SID = ?";
	public final static String READ_USER_NAME = "SELECT Name FROM User_info WHERE USN = ?";
	public final static String WRITE_CURRENTGROUP = "UPDATE UserSurveyLookup SET CurrentGroup = ? WHERE UID = ? AND SID = ?";
	public final static String READ_CURRENTGROUP = "SELECT CurrentGroup FROM UserSurveyLookup WHERE UID = ? AND SID = ?";
	
	public static final String WRITE_ACCOINFO = "INSERT INTO User_info (Name,email,address,phone, USN) VALUES (?,?,?,?,?)";
	public static final String ACCO = "SELECT PSD, type FROM User_Acco WHERE USN = ? ";
	public static final String CREATE_ACCO = "INSERT INTO User_Acco (USN, PSD,type) VALUES (?, ?,?)";
	public static final String READ_TYPE = "SELECT type FROM User_Acco WHERE USN = ?";
	public static final String READ_NAMEANDEMAIL = "SELECT NAME,EMAIL FROM User_info WHERE USN = ?";
	public static final String READ_ACCOUNT_INFO = "SELECT Name, email,phone FROM User_info WHERE USN = ?";
	
	public static final String WRITE_JOBFAIR_PYRAMID_SQUARES = "INSERT INTO JobFairAdminPyramid (AID,JFID,COL,ROW,ANS) VALUES (?,?,?,?,?)";
	public static final String READ_AIDS = "SELECT AID FROM JobFairPairedSurveys WHERE JFID = ?";
	public static final String READ_ALL_AIDS = "SELECT AID FROM JobFairAdminPyramid WHERE JFID = ? AND  COL = 1 AND ROW = 1";
	public final static String READ_JOBFAIR_MAX_COLUMN = "SELECT MAX(COL) FROM JobFairAdminPyramid WHERE AID = ? AND JFID = ?";
	public final static String READ_JOBFAIR_MAX_ROW = "SELECT MAX(ROW) FROM JobFairAdminPyramid WHERE AID = ? AND JFID = ? AND COL = ?";
	public final static String READ_JOBFAIR_ANSWER = "SELECT ANS FROM JobFairAdminPyramid WHERE AID = ? AND JFID = ? AND COL = ? AND ROW = ?";
	public final static String READ_JOBFAIRS = "SELECT SID, Title FROM AdminSurveyLookup WHERE AID = ? AND isJobFair = 1";
	public final static String READ_PAIRED_SURVEYS = "SELECT SID, Title FROM JobFairPairedSurveys WHERE JFID = ?";
	public final static String READ_ADMIN_PARTICIPARED_JOBFAIR = "SELECT SID, Title FROM UserSurveyLookup WHERE UID = ? AND isCompany = 1";
	
	public final static String READ_ISJOBFAIR = "SELECT isJobFair FROM AdminSurveyLookup WHERE SID = ?";
	public final static String WRITE_JOBFAIR_SCORES = "INSERT INTO JobFairScores (AID, JFID, UID, Score) VALUES (?,?,?,?)";
	public final static String READ_JOBFAIR_SCORES = "SELECT Score FROM JobFairScores WHERE UID = ? AND JFID = ?"; 
	
	public static final String PAIR_SURVEY = "INSERT INTO JobFairPairedSurveys (JFID,AID,SID,Title,Threshold) VALUES (?,?,?,?,?)";
	public static final String READ_THRESHOLDS = "SELECT Threshold FROM JobFairPairedSurveys WHERE JFID = ? AND AID = ?";
	public static final String READ_PAIRED_SURVEY = "SELECT SID,Title FROM JobFairPairedSurveys WHERE JFID = ? AND AID = ?";
	public final static String UNPAIR_SURVEY = "DELETE FROM JobFairPairedSurveys WHERE SID = ? AND JFID = ?";

	public static final String WRITE_CRITICALS = "INSERT INTO GenericCriticalValues (SID,AID,QuestionNumber) VALUES (?,?,?)";
	public static final String READ_CRITICALS = "SELECT QuestionNumber FROM GenericCriticalValues WHERE SID = ? AND AID = ?";
}
