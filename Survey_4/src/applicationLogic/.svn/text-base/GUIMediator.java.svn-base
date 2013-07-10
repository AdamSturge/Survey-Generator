package applicationLogic;

import java.util.ArrayList;

import model.Pyramid;
import model.QuestionList;
import database.DatabaseController;

public class GUIMediator {
	private ModelController modelController;
	private DatabaseController databaseController;
	private LoginSystem loginSystem;
	
	public GUIMediator(){
		modelController = new ModelController();
		databaseController = new DatabaseController();
		loginSystem = new LoginSystem();
	}
	
	public String fetchSurvey(long SID,String UID, boolean isAdminGeneric){
		return modelController.fetchSurvey(SID, UID, isAdminGeneric);
	}
	
	public void writeToAdminSurveyLookup(String AID, long SID, String title, String Message, boolean isJobFair){
		databaseController.writeToAdminSurveyLookup(AID, SID, title, Message, isJobFair);
	}
	
	public String adminSurveyLookup(String AID){
		return databaseController.adminSurveyLookup(AID);
	}
	
	public String adminJobFairParticipationLookup(String AID){
		return databaseController.adminJobFairParticipationLookup(AID);
	}
	
	public boolean writeToUserSurveyLookup(String UID, long SID, String title, String jobFairTitle, boolean isCompany){
		return databaseController.writeToUserSurveyLookup(UID, SID, title, jobFairTitle, isCompany);
	}
	
	public String userSurveyLookup(String UID){
		return databaseController.userSurveyLookup(UID);
	}
	
	public void writePyramid(Pyramid pyramid, String ID, long SID, boolean isAdminGeneric){
		modelController.writePyramid(pyramid, ID, SID, isAdminGeneric);
	}
	
	public long createSurvey(String AID, String title, String message, String questionData, boolean isJobFair){
		return modelController.createSurvey(AID, title, message, questionData, isJobFair);
	}
	
	public long createSurvey(String AID, String title, String message, String questionData, String pyramidShape, boolean isJobFair){
		return modelController.createSurvey(AID, title, message, questionData, pyramidShape, isJobFair);
	}
	
	public void savePyramid(long SID, String ID, String pyramidData, boolean isAdminGeneric, int groupNumber){
		modelController.savePyramid(SID, ID, pyramidData, isAdminGeneric, groupNumber);
	}
	
	public void saveQuestionList(long SID, String questionData){
		modelController.saveQuestionList(SID, questionData);
	}
	
	public long addPreview(String AID,String questionData){
		return modelController.addPreview(AID, questionData);
	}
	
	public void deleteSurvey(long SID){
		modelController.deleteSurvey(SID);
	}
	
	public String fetchMatches(long SID){
		return databaseController.fetchMatches(SID);
	}
	
	public String fetchJobFairMatches(long SID){
		return databaseController.fetchJobFairMatches(SID);
	}
	
	public void savePairedSuveys(long JFID,String AID, long SID,String title, int threshold ){
		databaseController.savePairedSuveys(JFID, AID, SID, title, threshold);
	}
	
	public void setOnlineStatus(long SID,boolean online){
		databaseController.setOnlineStatus(SID, online);
	}
	
	public void userSubmit(long SID,String ID, String pyramidData, String jobFairTitle, boolean isCompany){
		modelController.userSubmit(SID, ID, pyramidData, jobFairTitle, isCompany);
	}
	
	public boolean CheckAccount(String usd, String psd) {
		return loginSystem.CheckAccount(usd, psd);
	}
	
	public String getType(String ID){
		return loginSystem.getType(ID);
	}
	
	public void newAccount(ArrayList<Object> userInfo, ArrayList<Object> contactInfo){
		loginSystem.newAccount(userInfo, contactInfo);
	}
	
	public String readPairedSurveys(String AID, String JFID){
		return databaseController.readPairedSurveys(AID, JFID);
	}
	
	public void unpairSurvey(long SID, long JFID){
		databaseController.unpairSurvey(SID, JFID);
	}

	public String jobFairLookup(String AID){
		return databaseController.jobFairLookup(AID);
	}
	
	public String fetchIncompleteSurvey(long SID, boolean isJobFair){
		return databaseController.fetchIncompleteSurvey(SID, isJobFair);
	}
	
	public boolean getOnlineStatus(long SID){
		return databaseController.getOnlineStatus(SID);
	}
	
	public boolean titleInUse(String AID, String title){
		return databaseController.titleInUse(AID, title);
	}
	
	public boolean isJobFair(long SID){
		return databaseController.isJobFair(SID);
	}
	
	public int fetchCurrentGroup(String UID, long SID){
		return databaseController.fetchCurrentGroup(UID, SID);
	}
	
	public void writeCurrentGroup(String UID, long SID, int groupNum){
		databaseController.writeCurrentGroup(UID, SID, groupNum);
	}
	
}
