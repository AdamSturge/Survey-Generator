package applicationLogic;

import java.util.ArrayList;
import java.util.HashMap;

import model.Pyramid;
import model.PyramidSquare;
import model.Question;
import model.QuestionList;
import database.*;

import java.io.IOException;
import java.sql.*;

public class ModelController {

	private QuestionListFactory questionlistFactory;
	private PyramidFactory pyramidFactory;
	private DatabaseController dbController;
	public final static  String dataSplitter = "#!#";
	public final static String guiSplitter = "_split_";
	public final static String criticalSplitter = "CR!T";
	private SIDGenerator sIDGenerator;
	private ProfileMatcher profileMatcher;

	public ModelController() {

		dbController = new DatabaseController();
		pyramidFactory = new PyramidFactory(dbController);
		questionlistFactory = new QuestionListFactory(dbController);
		sIDGenerator = new SIDGenerator();
		profileMatcher = new ProfileMatcher(dbController);
	}


	public String fetchSurvey(long SID, String UID, boolean isAdminGeneric)
	{
		//System.out.println("@ModelController->fetchSurvey():  sid="+SID+"\tuid="+UID);
		QuestionList questionList = questionlistFactory.readQuestionList(SID);
		Pyramid pyramid = null;
		if(!isAdminGeneric){
			pyramid = pyramidFactory.readPyramid(UID,SID);
		}else{
			pyramid = pyramidFactory.readJobFairPyramid(UID,SID);
		}
		if(pyramid != null){
			//user has already started this survey
			ArrayList<Integer> crits = new ArrayList<Integer>();
			crits = dbController.fetchCritValues(UID, SID);
			if(crits.isEmpty()){
				return createSurveyString(pyramid,questionList);
			}
			else{
				return createSurveyStringCrits(pyramid, questionList, crits);
			}
		}else{
			pyramid = pyramidFactory.createPyramid(questionList); 
			writePyramid(pyramid,UID,SID,false); //make a copy for the user to fill out
			return createSurveyString(pyramid,questionList);
		}
	}

	/****
	 * Gets the survey title by survey ID
	 * @param SID: surveyID
	 * @return survey_title
	 * @author: Ji
	 */
	public String getSurveyTitleById(long SID)
	{
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		try {
			ResultSet rs = this.dbController.read(SQLStatements.READ_ADMINTITLE, sqlData);
			rs.next();
			return rs.getString(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//write a pyramid into the database
	public void writePyramid(Pyramid pyramid, String ID, long SID, boolean isAdminGeneric){
		int cols, rows;
		int ans; //final information to be stored in DB
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(ID);
		sqlData.add(SID);
		PyramidSquare pyramidSquare;
		cols = pyramid.getColumns();
		for(int i = 1; i <= cols; i++){
			rows = pyramid.getRows(i);
			sqlData.add(i);
			for(int j = 1; j <= rows; j++){
				pyramidSquare = pyramid.get(i,j);
				ans = pyramidSquare.getAnswer();
				System.out.println(ans);
				sqlData.add(j);
				sqlData.add(ans);
				try {
					if(!isAdminGeneric){
						dbController.write(SQLStatements.WRITE_PYRAMID_SQUARES, sqlData);
						sqlData.remove(sqlData.size()-1); //remove answer
						sqlData.remove(sqlData.size()-1); //remove row number

					}else{
						dbController.write(SQLStatements.WRITE_JOBFAIR_PYRAMID_SQUARES, sqlData);
						sqlData.remove(sqlData.size()-1); //remove answer
						sqlData.remove(sqlData.size()-1); //remove row number

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			sqlData.remove(sqlData.size()-1); //remove col number
		}
	}

	//write a question list into the database
	private void writeQuestionList(QuestionList questionList, long SID){
		HashMap<Integer,ArrayList<Question>> answerMap;
		ArrayList<Question> questions;
		Question q;
		answerMap = questionList.getAnswerMap();
		for(int i = 1; i <= questionList.getNumColumns(); i++){
			questions = answerMap.get(i);
			if(questions != null){
				for(int j = 0; j < questions.size(); j++ ){
					q = questions.get(j);
					ArrayList<Object> sqlData = new ArrayList<Object>();
					sqlData.add(SID);
					sqlData.add(q.getQuestion());
					sqlData.add(q.getAnswer());
					sqlData.add(q.getGroupNumber());
					sqlData.add(q.getQuestionNumber());
					sqlData.add(q.isCriticalValue());
					try{
						dbController.write(SQLStatements.WRITE_QUESTIONLIST_QUESTIONS, sqlData);
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}




	/**Receives a GUI string, builds a question list, and writes it to the database.
	 * 
	 */
	public long createSurvey(String AID, String title, String message, String questionData, boolean isAdminGeneric){

		QuestionList questionList = questionlistFactory.createQuestionList(questionData);

		long SID = sIDGenerator.generateSID(AID);
		writeQuestionList(questionList, SID);
		dbController.writeToAdminSurveyLookup(AID,SID ,title, message, isAdminGeneric);

		return SID;

	}

	/**Receives a GUI string, builds a question list, and writes it to the database.
	 * Builds a pyramid from the question list and writes it to the database
	 */
	public long createSurvey(String AID, String title, String message, String questionData, String pyramidShape, boolean isAdminGeneric){

		String[] split = questionData.split("#!#");
		String[] colSplit = pyramidShape.split("#!#");
		String[] rowSplit;
		ArrayList<Integer> numberOfRows = new ArrayList<Integer>(); //gives number of rows for the column indexed by i

		for(int i = 0; i < colSplit.length; ++i){
			rowSplit = colSplit[i].split(",");
			numberOfRows.add(rowSplit.length);
		}

		int index = 1;
		for(int j = 0; j < numberOfRows.size(); ++j){
			for(int k = numberOfRows.get(j); index < split.length && k > 0; index+=4, k-- ){
				split[index] = Integer.toString(j+1);
			}
		}

		questionData = "";
		String s = "";
		for(int i = 0; i < split.length; ++i){
			s = split[i];
			if(i != split.length-1){
				questionData += s + "#!#";
			}else{
				questionData += s;
			}

		}

		System.out.println("@createSurvey, modified questionData is " + questionData);

		QuestionList questionList = questionlistFactory.createQuestionList(questionData);

		long SID = sIDGenerator.generateSID(AID);
		writeQuestionList(questionList, SID);
		dbController.writeToAdminSurveyLookup(AID,SID ,title, message, isAdminGeneric);

		return SID;
	}

	//save a currently worked on pyramid
	public void savePyramid(long SID, String ID, String pyramidData, boolean isAdminGeneric, int groupNumber){
		if(isAdminGeneric){
			System.out.println("TEST: " + pyramidData);
			String crit;
			String[] split = pyramidData.split(criticalSplitter);
			for(int i = 0; i < 2; i++){
				System.out.println("TEST2: " + split[i]);
			}
			pyramidData = split[0];
			System.out.println("Testing split: "  +  split[1]);
			crit = split[1];
			ArrayList<Object> sqlData = new ArrayList<Object>();
			sqlData.add(SID);
			sqlData.add(ID);
			split = crit.split(",");
			for(String qnum : split){
				sqlData.add(qnum);
				try {
					dbController.write(SQLStatements.WRITE_CRITICALS, sqlData);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sqlData.remove(qnum);
			}
			sqlData.clear();
		}
		
		Pyramid pyramid = new Pyramid(pyramidData);
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		try {
			dbController.delete(SQLStatements.DELETE_PYRAMID, sqlData); //remove old data
			sqlData.clear();
			sqlData.add(groupNumber);
			sqlData.add(ID);
			sqlData.add(SID);
			dbController.write(SQLStatements.WRITE_CURRENTGROUP, sqlData);
			writePyramid(pyramid, ID, SID, isAdminGeneric);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//save a currently worked on questionList
	public void saveQuestionList(long SID, String questionData){

		//split[0] is the SID and split[1] is the ID
		QuestionList questionList = questionlistFactory.createQuestionList(questionData);
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		try {
			dbController.delete(SQLStatements.DELETE_QUESTIONLIST, sqlData); //remove old data
			writeQuestionList(questionList,SID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**writes the question list into the database but does not add it to the list of admin created surveys
	 *since it is not yet completed
	 **/
	public long addPreview(String AID,String questionData){
		long SID = sIDGenerator.generateSID(AID);
		saveQuestionList(SID,questionData);
		return SID;
	}

	//creates a JSON survey object (JSON pyramid + JSON QuestionList)
	private String createSurveyString(Pyramid pyramid, QuestionList questionList) {

		String result = "{\"survey\": " + pyramid.toString() + "," + questionList.toString() + "}";
		return result;
	}
	
	//creates a JSON survey object (JSON pyramid + JSON QuestionList)
	private String createSurveyStringCrits(Pyramid pyramid, QuestionList questionList, ArrayList<Integer> crits) {
		String critString = "\"crit\": \"";
		for(int crit: crits){
			critString += crit + ",";
		}
		critString = critString.substring(0, critString.length()-1);
		String result = "{\"survey\": " + pyramid.toString() + "," + critString + "\"," + questionList.toString() + "}";
		System.out.println(result);
		return result;
	}

	public void deleteSurvey(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		try {
			dbController.delete(SQLStatements.DELETE_ADMINLOOKUP, sqlData);
			dbController.delete(SQLStatements.DELETE_USERLOOKUP, sqlData);
			dbController.delete(SQLStatements.DELETE_PYRAMID, sqlData);
			dbController.delete(SQLStatements.DELETE_QUESTIONLIST, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Submits the users pyramid and updates score if required
	public void userSubmit(long SID,String ID, String pyramidData, String jobFairTitle, boolean isCompany){
		ArrayList<Object> sqlData = new ArrayList<Object>();		
		if(jobFairTitle == "" || (jobFairTitle != "" && isCompany == false)){
			savePyramid(SID, ID, pyramidData, false, 0);
			int score = profileMatcher.computeMatch(ID, SID);
			sqlData.add(score);
			sqlData.add(ID);
			sqlData.add(SID);
			try {
				dbController.write(SQLStatements.UPDATE_SCORE, sqlData);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(isCompany){
			savePyramid(SID, ID, pyramidData, true, 0);
			dbController.removeSurveyFromList(ID, SID);
		}
		else if(!isCompany){
			savePyramid(SID, ID, pyramidData, false, 0);
			long pairSID;
			String title;
			ArrayList<String> matches = profileMatcher.computeJobFairMatches(ID, SID);
			ArrayList<Integer> scores = profileMatcher.computeJobFairScores(ID,SID);
			dbController.writeJobFairScores(ID, SID, scores);
			dbController.removeSurveyFromList(ID, SID);
			sqlData.add(SID);
			try {
				ResultSet surveyInformation;
				for(String AID: matches){
					sqlData.add(AID);
					System.out.println("@UserSubmit: user has matched up with "+ AID );
					System.out.println("@UserSubmit: JFID is "+ SID );
					surveyInformation = dbController.read(SQLStatements.READ_PAIRED_SURVEY, sqlData);
					surveyInformation.next();
					pairSID = surveyInformation.getLong(1);
					title = surveyInformation.getString(2);
					dbController.writeToUserSurveyLookup(ID, pairSID, title, jobFairTitle, isCompany);
					sqlData.remove(AID);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	//gets a question list based on the SID
	public String fetchQuestionList(long SID){
		return questionlistFactory.readQuestionList(SID).toString();
	}




}
