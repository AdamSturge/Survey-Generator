package applicationLogic;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Pyramid;
import model.Question;
import model.QuestionList;
import database.DatabaseController;
import database.SQLStatements;

public class ProfileMatcher {

	private DatabaseController dbController;
	private QuestionListFactory questionlistFactory;
	private PyramidFactory pyramidFactory;

	public ProfileMatcher(DatabaseController d){
		dbController = d;
		questionlistFactory = new QuestionListFactory(dbController);
		pyramidFactory= new PyramidFactory(dbController);
	}

	public int computeMatch(String UID,long SID){
		QuestionList questionList = questionlistFactory.readQuestionList(SID);
		Pyramid userPyramid = pyramidFactory.readPyramid(UID,SID);
		int adminAnswer;
		int numOfColumns = userPyramid.getColumns();
		int numOfRows;
		int questionNumber;
		Question question;
		int match = 0;
		outerloop:
			for(int i = 1; i <= numOfColumns; ++i){
				numOfRows = userPyramid.getRows(i);
				for(int j = 1; j <= numOfRows; ++j ){
					questionNumber = userPyramid.get(i, j).getAnswer();
					question = questionList.getQuestion(questionNumber);
					if(question.isCriticalValue()){
						if(i < (numOfColumns/2)+1){
							//did not place critical value above neutral
							match = -2;
							break outerloop;
						}
					}
					adminAnswer = question.getAnswer();
					match += Math.abs(i- adminAnswer);
				}
			}

		return match;
	}
	public ArrayList<Integer> computeJobFairScores(String UID,long JFID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(JFID);
		ResultSet AIDs;
		int adminAnswer;
		Pyramid userPyramid = pyramidFactory.readPyramid(UID, JFID);
		int numOfColumns = userPyramid.getColumns();
		int numOfRows;
		int questionNumber;
		ResultSet criticalValueSet;
		int criticalValue;
		String AID;
		Pyramid adminPyramid;
		int match = 0;
		ArrayList<Integer> scores = new ArrayList<Integer>();
		try {
			AIDs = dbController.read(SQLStatements.READ_ALL_AIDS, sqlData);
			while(AIDs.next()){
				match = 0;
				AID = AIDs.getString(1);
				sqlData.add(AID);
				criticalValueSet = dbController.read(SQLStatements.READ_CRITICALS, sqlData);
				adminPyramid = pyramidFactory.readJobFairPyramid(AID,JFID);
				outerloop:
				for(int i = 1; i <= numOfColumns; ++i){
					numOfRows = userPyramid.getRows(i);
					for(int j = 1; j <= numOfRows; ++j ){
						questionNumber = userPyramid.get(i, j).getAnswer();
						while(criticalValueSet.next()){
							criticalValue = criticalValueSet.getInt(1);
							if(criticalValue == questionNumber){
								System.out.println("detected that question " + questionNumber + " is a critical value during matching");
								if(i < (numOfColumns/2)+1){
									//did not place critical value above neutral
									match = -2;
									break outerloop;
								}
							}
						}
						criticalValueSet.beforeFirst();
						adminAnswer = adminPyramid.getColumn(questionNumber);
						match += Math.abs(i- adminAnswer);
					}
				}
				scores.add(match);
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
		return scores;
	}


	public ArrayList<String> computeJobFairMatches(String UID,long JFID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(JFID);
		ResultSet AIDs;
		int adminAnswer;
		Pyramid userPyramid = pyramidFactory.readPyramid(UID, JFID);
		int numOfColumns = userPyramid.getColumns();
		int numOfRows;
		int questionNumber;
		String AID;
		ResultSet criticalValueSet;
		Pyramid adminPyramid;
		int match = 0;
		int criticalValue;
		ResultSet thresholds;
		ArrayList<String> matches = new ArrayList<String>();
		try {
			AIDs = dbController.read(SQLStatements.READ_AIDS, sqlData);
		while(AIDs.next()){
				match = 0;
				AID = AIDs.getString(1);
				sqlData.add(AID);
				criticalValueSet = dbController.read(SQLStatements.READ_CRITICALS, sqlData);
				thresholds = dbController.read(SQLStatements.READ_THRESHOLDS, sqlData);
				adminPyramid = pyramidFactory.readJobFairPyramid(AID,JFID);
				outerloop:
				for(int i = 1; i <= numOfColumns; ++i){
					numOfRows = userPyramid.getRows(i);
					for(int j = 1; j <= numOfRows; ++j ){
						questionNumber = userPyramid.get(i, j).getAnswer();
						while(criticalValueSet.next()){
							criticalValue = criticalValueSet.getInt(1);
							if(criticalValue == questionNumber){
								System.out.println("detected that question " + questionNumber + " is a critical value during matching");
								if(i < (numOfColumns/2)+1){
									//did not place critical value above neutral
									match = -2;
									break outerloop;
								}
							}
						}
						adminAnswer = adminPyramid.getColumn(questionNumber);
						match += Math.abs(i- adminAnswer);
					}
				}
				while(thresholds.next()){
					if(thresholds.getInt(1) >= match){
						matches.add(AID);
					}
				}
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
		return matches;
	}


}
