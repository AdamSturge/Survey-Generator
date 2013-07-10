package applicationLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import database.DatabaseController;
import database.SQLStatements;

public class EmailController {
	private GoogleMail gmail;
	private DatabaseController dbController;
	
	public EmailController(){
		dbController = new DatabaseController();
		gmail = new GoogleMail();
	}
	
	public void sendPublishEmail(String AID, long SID, String jobFair) {
		try {
			ArrayList<Object> sqlData = new ArrayList<Object>();
			sqlData.add(AID);
			ResultSet rs;
			rs = dbController.read(SQLStatements.READ_NAMEANDEMAIL, sqlData);
			rs.next();
			String name = rs.getString(1);
			String email = rs.getString(2);
			
			sqlData.remove(0);
			sqlData.add(SID);
			rs = dbController.read(SQLStatements.READ_ADMINTITLE, sqlData);
			rs.next();
			String title = rs.getString(1);
			String encodedTitle = URLEncoder.encode(title, "UTF-8").replace("+", "%20"); 
			
			
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
			                whatismyip.openStream()));

			String ip = in.readLine(); //you get the IP as a String
			
			String URL = "http://" +ip+ ":8080/Survey_4/MainPage?sid=" + SID +"&title=" + encodedTitle + "&jobfair=" + jobFair; 
			
			if(jobFair.equals("")){
				//Survey is not a job Fair
				URL  = URL + "&iscompany=false";
				gmail.sendPublishEmail(name, email, title, URL);
			}else{
				String URL1  = URL + "&iscompany=false";
				String URL2  = URL + "&iscompany=true";
				gmail.sendPublishEmail(name, email, title, URL1, URL2);
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
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendRegistrationEmail(String name, String email){
		try {
			gmail.sendRegistrationEmail(name, email);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendCompletionEmail(String ID, long SID){
		try {
			ArrayList<Object> sqlData = new ArrayList<Object>();
			sqlData.add(ID);
			ResultSet rs;
			rs = dbController.read(SQLStatements.READ_NAMEANDEMAIL, sqlData);
			rs.next();
			String name = rs.getString(1);
			String email = rs.getString(2);
			
			sqlData.remove(0);
			sqlData.add(SID);
			rs = dbController.read(SQLStatements.READ_ADMINTITLE, sqlData);
			rs.next();
			String title = rs.getString(1);
			
			gmail.sendCompletionEmail(name, email, title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
