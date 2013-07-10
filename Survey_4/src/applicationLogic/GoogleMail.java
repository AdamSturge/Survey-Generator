package applicationLogic;
import com.sun.mail.smtp.SMTPTransport;

import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author doraemon
 * http://stackoverflow.com/questions/3649014/send-email-using-java
 */
public class GoogleMail {
   
	private String registrationString = "Hello FIRSTNAME, \nThank you for registering at Vandelay Industries.";
	private String completeSurveyString = "Hello FIRSTNAME, \nJust letting you know that you completed the following survey: TITLE";
	private String publishSurveyString = "Hello FIRSTNAME, \nYou have put the following survey online: TITLE. \nPlease place the following" +
			"URL(s) on your website SURVEYURL";
	private String publishJobFairString = "Hello FIRSTNAME, \nYou have put the following Job Fair online: TITLE. \nPlease place the following" +
			"URLs on your website SURVEYURL1 \nSURVEYURL2 \nThe first URL is the URL job seekers will follow to enter the job fair. The second is the URL companies" 
			+ "must follow to join the job fair."; 
	
	public GoogleMail() {
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void Send(String recipientEmail, String title, String message) throws AddressException, MessagingException {
        Send(recipientEmail, "", title, message);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void Send(String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress("VandelayIndustries@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message);
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", "vandelayindustries2000@gmail.com ", "ImportExport");
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }
    
    public void sendRegistrationEmail(String fName,String email) throws AddressException, MessagingException{
    	String s = registrationString.replaceAll("FIRSTNAME", fName);
    	Send(email, "Registration", s);
    }
    
    public void sendCompletionEmail(String fName, String email, String title) throws AddressException, MessagingException{
    	String s = completeSurveyString.replaceAll("FIRSTNAME", fName);
    	s = s.replaceAll("TITLE", title);
    	Send(email, "Completed Survey: " + title, s);
    }
    
    //used to send a url where people can do the specified survey to the survey creator
    public void sendPublishEmail(String fName, String email,String title, String URL) throws AddressException, MessagingException{
    	String s = publishSurveyString.replaceAll("FIRSTNAME", fName);
    	s= s.replaceAll("TITLE", title);
    	s= s.replaceAll("SURVEYURL",URL);
    	Send(email, "Published Survey:" + title, s);
    }
    
    //used to send 2 URLs, one for companies to join a job fair and one for users to join a job fair
    public void sendPublishEmail(String fName, String email,String title, String URL1, String URL2) throws AddressException, MessagingException{
    	String s = publishJobFairString.replaceAll("FIRSTNAME", fName);
    	s= s.replaceAll("TITLE", title);
    	s= s.replaceAll("SURVEYURL1",URL1);
    	s = s.replaceAll("SURVEYURL2", URL2);
    	Send(email, "Published Survey:" + title, s);
    }
}
