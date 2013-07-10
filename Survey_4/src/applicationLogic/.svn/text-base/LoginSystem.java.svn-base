package applicationLogic;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import database.DatabaseController;
import database.SQLStatements;

public class LoginSystem {

	
	private DatabaseController dc= new DatabaseController();	
	private String type;

	public boolean CheckAccount(String usd, String psd) 

	{
		boolean valid=false;
		String password = null;
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(usd);
		ResultSet rs;
		try {
			rs = dc.read(SQLStatements.ACCO, sqlData);
			if (rs.next())
			{
				password=rs.getString(1);
				type=rs.getString(2);}

			if(password!=null)
			{	
				if(password.equals(psd))
					valid=true;
			}
			dc.closeQuery(rs);
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
		
		return valid;

	}


	public String getType(String ID)
	{
		ArrayList<Object> sqlData = new ArrayList<Object>();
		sqlData.add(ID);
		try {
			ResultSet rs = dc.read(SQLStatements.READ_TYPE, sqlData);
			rs.next();
			return rs.getString(1);
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

	public void newAccount(ArrayList<Object> userInfo, ArrayList<Object> contactInfo)
	{
		
		try {
			dc.write(SQLStatements.CREATE_ACCO, userInfo);
			dc.write(SQLStatements.WRITE_ACCOINFO, contactInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}





}

