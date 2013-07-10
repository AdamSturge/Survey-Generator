package database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import applicationLogic.ModelController;

import model.Pyramid;
import model.QuestionList;


public class DatabaseController {

	private static Connection c;
	private HashMap<ResultSet,PreparedStatement> StatementMap;
	private final String dataSplitter = ModelController.dataSplitter;

	public DatabaseController() {
		c=MySQL.conDBS();
		StatementMap = new HashMap<ResultSet,PreparedStatement>();
	}

	public boolean isOnline(){
		return true;	//currently implemented to test connection with GUI //Ji
	}


	public void write(String SQLStatement, ArrayList<Object> sqlData) throws SQLException {
		PreparedStatement pstmt = c.prepareStatement(SQLStatement);
		String className = "";
		for(int i = 0; i < sqlData.size(); ++i) {
			className = sqlData.get(i).getClass().getName();
			//add in cases to the switch statement as different sqlData types become required
			if(className.equals("java.lang.String")){
				pstmt.setString(i+1, (String)sqlData.get(i));
			}else if(className.equals("java.lang.Integer")){
				pstmt.setInt(i+1, (Integer)sqlData.get(i));
			}
			else if(className.equals("java.lang.Long")){
				pstmt.setLong(i+1,(Long)sqlData.get(i));
			} 
			else if(className.equals("java.lang.Boolean")){
				pstmt.setBoolean(i+1,(Boolean) sqlData.get(i));
			}
			else{
				pstmt.setObject(i+1,sqlData.get(i));
			}
		}

		pstmt.executeUpdate();
		pstmt.close();
	}


	public ResultSet read(String SQLStatement,ArrayList<Object> sqlData) throws SQLException, IOException, ClassNotFoundException{
		PreparedStatement pstmt = c.prepareStatement(SQLStatement);
		String className = "";
		for(int i = 0; i < sqlData.size(); ++i) {
			className = sqlData.get(i).getClass().getName();
			//add in cases to the switch statement as different sqlData types become required
			if(className.equals("java.lang.String")){
				pstmt.setString(i+1, (String)sqlData.get(i));
			}else if(className.equals("model.QuestionList")) {
				pstmt.setObject(i+1,sqlData.get(i));
			}else if(className.equals("model.Pyramid")) {
				pstmt.setObject(i+1, sqlData.get(i));
			}else if(className.equals("java.lang.Integer")){
				pstmt.setInt(i+1, (Integer) sqlData.get(i));
			}else if(className.equals("java.lang.Long")) {
				pstmt.setLong(i+1, (Long) sqlData.get(i));
			}else if(className.equals("java.lang.Boolean")){
				pstmt.setBoolean(i+1,(Boolean) sqlData.get(i));
			}
			else{
				pstmt.setObject(i+1,sqlData.get(i));
			}
		}
		ResultSet rs = pstmt.executeQuery();
		StatementMap.put(rs, pstmt);
		return rs;

	}

	public void delete(String SQLStatement,ArrayList<Object> sqlData) throws SQLException, IOException, ClassNotFoundException{
		PreparedStatement pstmt = c.prepareStatement(SQLStatement);
		String className = "";
		for(int i = 0; i < sqlData.size(); ++i) {
			className = sqlData.get(i).getClass().getName();
			//add in cases to the switch statement as different sqlData types become required
			if(className.equals("java.lang.String")){
				pstmt.setString(i+1, (String)sqlData.get(i));
			}else if(className.equals("java.lang.Integer")){
				pstmt.setInt(i+1, (Integer)sqlData.get(i));
			}
			else if(className.equals("java.lang.Long")){
				pstmt.setLong(i+1, (Long)sqlData.get(i));
			}
		}

		pstmt.executeUpdate();
		pstmt.close();
	}

	//closes the connection between the passed result set to the database
	public void closeQuery(ResultSet rs){
		try {
			StatementMap.get(rs).close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeToAdminSurveyLookup(String AID, long SID, String title, String Message, boolean isJobFair){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		sqlData.add(SID);
		sqlData.add(title);
		sqlData.add(Message);
		sqlData.add(isJobFair);
		try {
			write(SQLStatements.WRITE_ADMINSURVEYLOOKUP, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String adminSurveyLookup(String AID){
		String jsonResult = "{ \"surveys\": \"";
		ResultSet rs;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		try {
			rs = read(SQLStatements.READ_ADMINSURVEYLOOKUP, sqlData);
			while(rs.next()){
				jsonResult += rs.getInt(1);
				jsonResult += ",";
				jsonResult += rs.getString(2);
				jsonResult += dataSplitter;
			}
			jsonResult += "\" }";
			closeQuery(rs);
			return jsonResult;
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

	//lookup all jobFairs an admin is registered for
	public String adminJobFairParticipationLookup(String AID){
		String jsonResult = "{ \"surveys\": \"";
		ResultSet rs;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		try {
			rs = read(SQLStatements.READ_ADMIN_PARTICIPARED_JOBFAIR, sqlData);
			while(rs.next()){
				jsonResult += rs.getInt(1);
				jsonResult += ",";
				jsonResult += rs.getString(2);
				jsonResult += dataSplitter;
			}

			jsonResult = jsonResult.substring(0,jsonResult.length()-dataSplitter.length());
			jsonResult += "\" }";
			closeQuery(rs);
			return jsonResult;
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

	public boolean writeToUserSurveyLookup(String UID, long SID, String title, String jobFairTitle, boolean isCompany){

		try {
			ArrayList<Object> sqlData = new ArrayList<Object>();
			sqlData.add(UID);
			sqlData.add(SID);
			ResultSet rs = read(SQLStatements.READ_USERTITLE, sqlData);
			//System.out.println("@ModelController->writeToUserSurveyLookup()\t resultSetIsNull?"+rs.getString(1));
			if(!rs.first()){
				sqlData.add(title);
				sqlData.add(jobFairTitle);
				sqlData.add(isCompany);
				System.out.println("@ModelController->writeToUserSurveyLookup()\t sid="+SID+"\t title="+title);
				write(SQLStatements.WRITE_USERSURVEYLOOKUP, sqlData);
				return true;
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
		return false;
	}

	//fetch active surveys for the given user
	public String userSurveyLookup(String UID){
		String jsonResult = "{ \"surveys\": \"";
		ResultSet rs;
		ResultSet UserInfoRS;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		boolean isCompany;
		sqlData.add(UID);
		long SID;
		String title;
		String jobFairTitle;
		String AID;
		String name;
		int score;
		try {
			rs = read(SQLStatements.READ_USERSURVEYLOOKUP, sqlData);
			while(rs.next()){
				score = rs.getInt(4);
				if(score == -1){
					SID = rs.getInt(1);
					sqlData.clear();
					sqlData.add(SID);
					UserInfoRS = read(SQLStatements.READ_ADMIN_AID, sqlData);
					UserInfoRS.next();
					AID = UserInfoRS.getString(1);
					sqlData.clear();
					sqlData.add(AID);
					UserInfoRS = read(SQLStatements.READ_USER_NAME, sqlData);
					UserInfoRS.next();
					name = UserInfoRS.getString(1);
					title = rs.getString(2);
					jobFairTitle = rs.getString(3);
					isCompany = rs.getBoolean(5);
					jsonResult += SID;
					jsonResult += ",";
					jsonResult += title;
					jsonResult += ",";
					jsonResult += jobFairTitle;
					jsonResult += ",";
					jsonResult += isCompany;
					jsonResult += ",";
					jsonResult += name;
					jsonResult += dataSplitter;	
				}

			}
			jsonResult += "\" }";
			closeQuery(rs);
			return jsonResult;
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

	public String fetchMatches(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		ResultSet usernameRS;
		ResultSet scoreRS;
		ResultSet infoRS;
		String name, email, phone;
		String UID;
		int score;
		String JSON = "{ \"Scores\": \" ";
		try {
			usernameRS = read(SQLStatements.READ_UID, sqlData);
			sqlData.clear();
			while(usernameRS.next()){
				UID = usernameRS.getString(1);
				sqlData.add(UID);
				infoRS = read(SQLStatements.READ_ACCOUNT_INFO, sqlData);
				infoRS.next();
				name = infoRS.getString(1);
				email = infoRS.getString(2);
				phone = infoRS.getString(3);
				sqlData.add(SID);
				scoreRS = read(SQLStatements.READ_SCORE, sqlData);
				scoreRS.next();
				score = scoreRS.getInt(1);
				sqlData.clear();
				if(score != -1 && score != -2){
					//-1 indicates that the user has not submitted their survey yet
					JSON += UID + ","+ score + ","+ name + ","+ email+  ","+ phone + dataSplitter;
				}

			}

			JSON += "\" }";

			return JSON;
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

	public String fetchJobFairMatches(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		ResultSet usernameRS;
		ResultSet scoreRS;
		ResultSet infoRS;
		String name, email, phone;
		String UID;
		int score;
		String JSON = "{ \"Scores\": \" ";
		try {
			usernameRS = read(SQLStatements.READ_UID, sqlData);
			while(usernameRS.next()){
				sqlData.clear();
				UID = usernameRS.getString(1);
				sqlData.add(UID);
				infoRS = read(SQLStatements.READ_ACCOUNT_INFO, sqlData);
				infoRS.next();
				name = infoRS.getString(1);
				email = infoRS.getString(2);
				phone = infoRS.getString(3);
				sqlData.add(SID);
				scoreRS = read(SQLStatements.READ_JOBFAIR_SCORES, sqlData);
				if(scoreRS.next()){
					score = scoreRS.getInt(1);
					sqlData.clear();
					if(score != -1 && score != -2){
						//-1 indicates that the user has not submitted their survey yet
						JSON += UID + ","+ score + ","+ name + ","+ email+  ","+ phone + dataSplitter;
					}

				}

			}

			JSON += "\" }";

			return JSON;
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





	public void savePairedSuveys(long JFID,String AID, long SID,String title, int threshold ){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(JFID);
		sqlData.add(AID);
		sqlData.add(SID);
		sqlData.add(title);
		sqlData.add(threshold);
		try {
			write(SQLStatements.PAIR_SURVEY, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unpairSurvey(long SID, long JFID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		sqlData.add(JFID);
		try {
			delete(SQLStatements.UNPAIR_SURVEY, sqlData);
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

	public void setOnlineStatus(long SID,boolean online){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(online);
		sqlData.add(SID);
		try {
			write(SQLStatements.UPDATE_ONLINE, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String jobFairLookup(String AID){
		String jsonResult = "{ \"JobFairs\": \" ";
		ResultSet rs;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		try {
			rs = read(SQLStatements.READ_JOBFAIRS, sqlData);
			while(rs.next()){
				jsonResult += rs.getInt(1);
				jsonResult += ",";
				jsonResult += rs.getString(2);
				jsonResult += dataSplitter;
			}
			jsonResult += "\" }";
			closeQuery(rs);
			return jsonResult;
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



	public String readPairedSurveys(String AID, String JFID){
		String jsonResult = "{ \"Surveys\": { \"pairedSurveys\":\"";
		ResultSet allSurveys;
		ResultSet pairedSurveys;
		long sid;
		String title;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		try {
			allSurveys = read(SQLStatements.READ_ADMINSURVEYLOOKUP_JOBLESS, sqlData);
			sqlData.add(0, JFID);
			pairedSurveys = read(SQLStatements.READ_PAIRED_SURVEY, sqlData);

			HashMap<Long,String> allSurveysMap = new HashMap<Long,String>();
			while(allSurveys.next()){
				allSurveysMap.put(allSurveys.getLong(1), allSurveys.getString(2));
			}
			ArrayList<Long> pairedSurveySids = new ArrayList<Long>();
			while(pairedSurveys.next()){
				pairedSurveySids.add(pairedSurveys.getLong(1));
			}

			pairedSurveys.beforeFirst();
			for(Long SID: pairedSurveySids){
				if(allSurveysMap.containsKey(SID)){
					allSurveysMap.remove(SID);
				}
			}
			while(pairedSurveys.next()){
				sid = pairedSurveys.getLong(1);
				title = pairedSurveys.getString(2);
				jsonResult += sid;
				jsonResult += ",";
				jsonResult += title;
				jsonResult += dataSplitter;
			}

			jsonResult += "\" , \"unpairedSurveys\":\"";
			for(Long SID: allSurveysMap.keySet()){
				jsonResult += SID;
				jsonResult += ",";
				jsonResult += allSurveysMap.get(SID);
				jsonResult += dataSplitter;
			}
			jsonResult += "\" }}";
			closeQuery(pairedSurveys);
			closeQuery(allSurveys);
			return jsonResult;
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


	public String fetchIncompleteSurvey(long SID, boolean isJobFair){
		String jsonResult = "{ \"Survey\": {";
		String title = "";
		String message = "";
		int questionGroup = -1;
		ResultSet questionNumbers = null;
		ResultSet groupNumbers = null;
		String questionString = "";
		int questionAnswer = -1;
		boolean criticalValue;

		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		try {
			ResultSet rs = read(SQLStatements.READ_ADMINTITLEANDMESSAGE, sqlData);
			rs.next();
			title = rs.getString(1);
			message = rs.getString(2);
			closeQuery(rs);

			jsonResult += "\"Title\":\"" + title + "\",";
			jsonResult += "\"Message\":\"" + message + "\",";
			jsonResult += "\"QuestionData\":\"";

			questionNumbers = read(SQLStatements.READ_QUESTIONLIST_QUESTIONNUMBER, sqlData);
			groupNumbers = read(SQLStatements.READ_QUESTIONLIST_GROUPS, sqlData);
			while(questionNumbers.next() && groupNumbers.next()){
				sqlData.add(questionNumbers.getInt(1));
				rs = read(SQLStatements.READ_QUESTIONLIST_STRING, sqlData);
				rs.next();
				questionString = rs.getString(1);
				closeQuery(rs);

				questionGroup = groupNumbers.getInt(1);

				if(!isJobFair){
					rs = read(SQLStatements.READ_QUESTIONLIST_ANSWER, sqlData);
					rs.next();
					questionAnswer = rs.getInt(1);
					closeQuery(rs);

					rs = read(SQLStatements.READ_QUESTIONLIST_CRIT,sqlData);
					rs.next();
					criticalValue = rs.getBoolean(1);
					closeQuery(rs);

					jsonResult += questionString + "," + questionGroup + "," + questionAnswer + "," + criticalValue + ",";
				}else{
					jsonResult += questionString + "," + questionGroup + ",";
				}

				sqlData.remove(1);

			}

			jsonResult = jsonResult.substring(0,jsonResult.length()-1);
			jsonResult += "\"}}";

			return jsonResult;

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


	public boolean getOnlineStatus(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		boolean online;
		try {
			ResultSet rs = read(SQLStatements.READ_ONLINE,sqlData);
			rs.next();
			online = rs.getBoolean(1);
			closeQuery(rs);
			return online;
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

		return false;
	}

	public boolean titleInUse(String AID, String title){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(AID);
		try {
			ResultSet rs = read(SQLStatements.READ_TITLE, sqlData);
			while(rs.next()){
				String existingTitle = rs.getString(1);
				if(title.equals(existingTitle)){
					return true;
				}
			}

			closeQuery(rs);

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
		return false;
	}

	//writes jobfair scores for users filling out generic survey



	public boolean isJobFair(long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		ResultSet rs;
		try {
			rs = read(SQLStatements.READ_ISJOBFAIR, sqlData);
			rs.next();
			boolean isJobFair = rs.getBoolean(1);
			closeQuery(rs);
			return isJobFair;
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
		System.out.println("isJobFair method failed");
		return false;

	}


	public void writeJobFairScores(String UID, long JFID, ArrayList<Integer> scores){
		ResultSet AIDs;
		String AID;
		int i = 0;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(JFID);
		try {
			AIDs = read(SQLStatements.READ_ALL_AIDS, sqlData);
			sqlData.clear();
			while(AIDs.next()){
				AID = AIDs.getString(1);
				sqlData.add(AID);
				sqlData.add(JFID);
				sqlData.add(UID);
				sqlData.add(scores.get(i));
				i++;
				write(SQLStatements.WRITE_JOBFAIR_SCORES, sqlData);
				sqlData.clear();
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

	public void removeSurveyFromList(String UID, long SID){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		System.out.println("SURVEY REMOVED FROM LIST");
		sqlData.add(-2);
		sqlData.add(UID);
		sqlData.add(SID);
		try {
			write(SQLStatements.UPDATE_SCORE, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int fetchCurrentGroup(String UID, long SID){
		ResultSet rs;
		int groupNum;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(UID);
		sqlData.add(SID);
		try {
			rs = read(SQLStatements.READ_CURRENTGROUP, sqlData);
			rs.next();
			groupNum = rs.getInt(1);
			return groupNum;
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
		return -1;
	}
	
	public void writeCurrentGroup(String UID, long SID, int groupNum){
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(UID);
		sqlData.add(SID);
		sqlData.add(groupNum);
		try {
			write(SQLStatements.WRITE_CURRENTGROUP, sqlData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> fetchCritValues(String UID, long SID){
		ArrayList<Integer> crits = new ArrayList<Integer>();
		ResultSet rs;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(SID);
		sqlData.add(UID);
		try {
			rs = read(SQLStatements.READ_CRITICALS, sqlData);
			while(rs.next()){
				crits.add(rs.getInt(1));
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
		return crits;
		
	}


}




