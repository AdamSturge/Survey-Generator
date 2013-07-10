package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import database.DatabaseController;
import database.SQLStatements;

import applicationLogic.LoginSystem;

public class LoginSystemTest {

	private LoginSystem loginSystem;
	private DatabaseController databaseController;
	
	@Before
	public void setUp(){
		loginSystem = new LoginSystem();
		databaseController = new DatabaseController();
	}
	
	@Test
	public void testNewAccount() {
		ArrayList<Object> data1 = new ArrayList<Object>();
		data1.add("JUnit");
		data1.add("JUnit");
		data1.add("A");
		ArrayList<Object> data2 = new ArrayList<Object>();
		data2.add("JUnit");
		data2.add("JUnit@JUnit.ca");
		data2.add("JUnit lane");
		data2.add("111-1111");
		data2.add("JUnit");
		
		loginSystem.newAccount(data1, data2);
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add("JUnit");
		try {
			ResultSet rs = databaseController.read(SQLStatements.ACCO, sqlData);
			if(rs.next()){
				assertEquals("JUnit", rs.getString(1)); //checks to see if the password matches
				assertEquals("A", rs.getString(2)); //checks to see if the user is Admin 
			}
		} catch (SQLException e) {
			fail("SQLException");
			e.printStackTrace();
		} catch (IOException e) {
			fail("IOException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Test
	public void testCheckAccount() {
		boolean valid = loginSystem.CheckAccount("JUnit", "JUnit");
		assertTrue(valid);
	}

	

}
