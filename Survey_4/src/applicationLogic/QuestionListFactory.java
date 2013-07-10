package applicationLogic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseController;
import database.SQLStatements;

import model.Question;
import model.QuestionList;

public class QuestionListFactory {

	private DatabaseController dbController;
	private final String dataSplitter = ModelController.dataSplitter;

	public QuestionListFactory(DatabaseController d){
		dbController = d;
	}

	public QuestionList createQuestionList(ArrayList<String> questionStrings,ArrayList<Integer> answers,
			ArrayList<Integer> groupNumbers,ArrayList<Integer> questionNumbers, ArrayList<Boolean> criticalValues){

		ArrayList<Question> questions = new ArrayList<Question>();
		for(int i = 0; i < questionStrings.size(); ++i){
			questions.add(new Question(questionStrings.get(i), answers.get(i),groupNumbers.get(i), questionNumbers.get(i), criticalValues.get(i)));
		}
		
		return new QuestionList(questions);
	}

	public QuestionList createQuestionList(ArrayList<String> questionStrings,ArrayList<Integer> answers, ArrayList<Integer> groupNumbers,ArrayList<Boolean> criticalValues ) {
		String q;
		int a;
		int g;
		boolean c;
		ArrayList<Question> questions = new ArrayList<Question>();
		for(int i = 0; i < questionStrings.size(); ++i) {
			q = questionStrings.get(i);
			a = answers.get(i);
			g = groupNumbers.get(i);
			c = criticalValues.get(i);

			questions.add(new Question(q,a,g,i+1,c));
		}

		return new QuestionList(questions);

	}

	public QuestionList createQuestionList(String questionData){
		String[] split = questionData.split(dataSplitter);
		String q;
		int a;
		int g;
		ArrayList<Question> questions = new ArrayList<Question>();
		int questionNumber = 0;
		boolean c = false;
		for(int i = 0; i < split.length; i+=4) {
			q = split[i];
			a = Integer.parseInt(split[i+1]);
			g = Integer.parseInt(split[i+2]);
			questionNumber++;
			c = Boolean.parseBoolean(split[i+3]);
			
			questions.add(new Question(q,a,g,questionNumber,c));
		}

		return new QuestionList(questions);
	}

	public QuestionList readQuestionList(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		ResultSet groupSet = null;
		ResultSet questionNumberSet = null;
		ResultSet questionStringSet = null;
		ResultSet questionAnswerSet = null;
		ResultSet criticalValueSet = null;
		try {
			groupSet = dbController.read(SQLStatements.READ_QUESTIONLIST_GROUPS, sqlData);
			questionNumberSet = dbController.read(SQLStatements.READ_QUESTIONLIST_QUESTIONNUMBER, sqlData);

			String questionString; //admin provided question 
			int questionAnswer; //admin provided answer to the current question

			boolean done = false;
			int groupNumber;
			int questionNumber; 
			boolean criticalValue;

			ArrayList<String> questionStrings = new ArrayList<String>();
			ArrayList<Integer> answers = new ArrayList<Integer>();
			ArrayList<Integer> groupNumbers = new ArrayList<Integer>();
			ArrayList<Integer> questionNumbers = new ArrayList<Integer>();
			ArrayList<Boolean> criticalValues = new ArrayList<Boolean>();
			while(!done){
				if(groupSet.next() && questionNumberSet.next()){

					groupNumber = groupSet.getInt(1);
					groupNumbers.add(groupNumber);
					questionNumber = questionNumberSet.getInt(1);
					questionNumbers.add(questionNumber);

					sqlData.add(questionNumber);

					questionStringSet = dbController.read(SQLStatements.READ_QUESTIONLIST_STRING, sqlData);
					questionStringSet.next();
					questionString = questionStringSet.getString(1);
					dbController.closeQuery(questionStringSet);
					questionStrings.add(questionString);

					questionAnswerSet = dbController.read(SQLStatements.READ_QUESTIONLIST_ANSWER, sqlData);
					questionAnswerSet.next();
					questionAnswer= questionAnswerSet.getInt(1);
					dbController.closeQuery(questionAnswerSet);
					answers.add(questionAnswer);
					
					criticalValueSet = dbController.read(SQLStatements.READ_QUESTIONLIST_CRIT, sqlData);
					criticalValueSet.next();
					criticalValue= criticalValueSet.getBoolean(1);
					dbController.closeQuery(criticalValueSet);
					criticalValues.add(criticalValue);
					
					sqlData.remove(sqlData.size()-1);

				}
				else{
					done = true;
				}

			}
			return createQuestionList(questionStrings,answers,groupNumbers,questionNumbers, criticalValues);
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

		return null;
	}
	
}
