package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import applicationLogic.QuestionListFactory;
import database.DatabaseController;

public class DatabaseControllerTest {
 
	private DatabaseController db;

	public DatabaseControllerTest(){
		  db= new DatabaseController();
	}
	


	@Test
	public void testRead() throws SQLException, IOException, ClassNotFoundException
	{
		final String SQLStatement = "SELECT * FROM User_Acco Where USN=?";
		ArrayList<Object> sqlData = new ArrayList<Object> ();
		sqlData.add("1");
		//sqlData.add("10");
		//sqlData.add("2");
		//sqlData.add("3");
		ResultSet rs=db.read(SQLStatement, sqlData);
//		System.out.println(rs.getString(1));
//		System.out.println(rs.getString(2));
//		System.out.println(rs.getString(3));
		 rs.next();
		assertEquals("1",rs.getString(1));
		assertEquals("1",rs.getString(2));
		assertEquals("A",rs.getString(3));
		
	}


	
	@Test
	public void testWrite() throws SQLException {
	
		
		final String SQLStatement = "INSERT INTO User_Acco (USN,PSD,type) VALUES (?,?,?)";
		ArrayList<Object> sqlData = new ArrayList<Object> ();
		sqlData.add("33");
		sqlData.add("4");
		sqlData.add("U");
		//sqlData.add("3");
		db.write(SQLStatement, sqlData);

	}
	
	

	@Test
	public void testDelete() throws SQLException, IOException, ClassNotFoundException {
		final String SQLStatement = "DELETE FROM User_Acco WHERE USN = ?";
		ArrayList<Object> sqlData = new ArrayList<Object> ();
		sqlData.add("33");
		db.delete(SQLStatement, sqlData);
		
		
	}
	
	@Test
	public void testFetchIncompleteSurvey(){
		ArrayList<Object> sqlData = new ArrayList<Object> ();
		sqlData.add(2965);
		String s = db.fetchIncompleteSurvey(2965, false);
		System.out.println(s);
	}


}
