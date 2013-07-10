package applicationLogic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseController;
import database.SQLStatements;
import model.Pyramid;
import model.QuestionList;

public class PyramidFactory {

	private DatabaseController dbController;

	public PyramidFactory(DatabaseController d){
		dbController = d;
	}

	public Pyramid createPyramid(QuestionList questionList) {
		return new Pyramid(questionList);
	}

	public Pyramid readPyramid(String ID, long SID) {
		ArrayList<Object> sqlData = new ArrayList<Object>();
		ResultSet rs;
		sqlData.add(ID);
		sqlData.add(SID);
		try {
			rs = dbController.read(SQLStatements.READ_MAX_COLUMN, sqlData);
			rs.next();
			int colNum = rs.getInt(1); //number of columns
			dbController.closeQuery(rs);
			if(colNum > 0){
				int rowNum = 0; //number of rows in a given column
				int answer = 0;//answer for a given column and row
				String pyramidString = "";
				for(int i = 1; i <= colNum; ++i){
					sqlData.add(i);
					rs = dbController.read(SQLStatements.READ_MAX_ROW, sqlData);
					rs.next();
					rowNum = rs.getInt(1);
					dbController.closeQuery(rs);
					if(rowNum > 0){
						for(int j = 1; j <= rowNum; ++j){
							sqlData.add(j);
							rs = dbController.read(SQLStatements.READ_PYRAMID_ANSWER, sqlData);
							rs.next();
							answer = rs.getInt(1);
							dbController.closeQuery(rs);
							sqlData.remove(sqlData.size()-1); //remove row number
							if(j != rowNum){
								pyramidString += answer + ",";
							}else{
								pyramidString += answer;
							}
						}
						if(i != colNum){
							pyramidString += ModelController.dataSplitter;
						}

					} else{
						pyramidString += "null"+ ModelController.dataSplitter;
					}
					sqlData.remove(sqlData.size()-1); //remove column number
				}
				System.out.println("@PyramidFactory->readPyramid():   pyramidString="+pyramidString);
				return new Pyramid(pyramidString);
			} 
			else{
				return null;
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

		return null;
	}
	
	public Pyramid readJobFairPyramid(String AID, long JFID) {
		ArrayList<Object> sqlData = new ArrayList<Object>();
		ResultSet rs;
		sqlData.add(AID);
		sqlData.add(JFID);
		try {
			rs = dbController.read(SQLStatements.READ_JOBFAIR_MAX_COLUMN, sqlData);
			rs.next();
			int colNum = rs.getInt(1); //number of columns
			dbController.closeQuery(rs);
			if(colNum > 0){
				int rowNum = 0; //number of rows in a given column
				int answer = 0;//answer for a given column and row
				String pyramidString = "";
				for(int i = 1; i <= colNum; ++i){
					sqlData.add(i);
					rs = dbController.read(SQLStatements.READ_JOBFAIR_MAX_ROW, sqlData);
					rs.next();
					rowNum = rs.getInt(1);
					dbController.closeQuery(rs);
					if(rowNum > 0){
						for(int j = 1; j <= rowNum; ++j){
							sqlData.add(j);
							rs = dbController.read(SQLStatements.READ_JOBFAIR_ANSWER, sqlData);
							rs.next();
							answer = rs.getInt(1);
							dbController.closeQuery(rs);
							sqlData.remove(sqlData.size()-1); //remove row number
							if(j != rowNum){
								pyramidString += answer + ",";
							}else{
								pyramidString += answer;
							}
						}
						if(i != colNum){
							pyramidString += ModelController.dataSplitter;
						}

					} else{
						pyramidString += "null"+ ModelController.dataSplitter;
					}
					sqlData.remove(sqlData.size()-1); //remove column number
				}
				System.out.println("@PyramidFactory->readJobFairPyramid():   pyramidString="+pyramidString);
				return new Pyramid(pyramidString);
			} 
			else{
				return null;
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

		return null;
	}


}
