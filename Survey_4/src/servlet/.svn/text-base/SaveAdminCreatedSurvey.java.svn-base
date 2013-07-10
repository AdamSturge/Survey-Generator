package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationLogic.ApplicationObject;
import applicationLogic.EmailController;
import applicationLogic.GUIMediator;
import applicationLogic.ModelController;

/**
 * Author: Ji He
 * Saves the survey created by the admin.
 * 
 * If querystring contains a publish flag, for the survey, the survey will be saved and published.
 * 
 * 
 */
@WebServlet("/SaveAdminCreateSurvey")
public class SaveAdminCreatedSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAdminCreatedSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		ApplicationObject app = (ApplicationObject)this.getServletContext().getAttribute("Application");
		GUIMediator mediator = new GUIMediator();
		
		System.out.println("-------SaveSurvey: Post-------");
		
		HttpSession session = request.getSession();
		String adminID = (String) session.getAttribute("userid");
		String userpass = (String) session.getAttribute("password");
		boolean isAdmin =  ((String)session.getAttribute("userType")).equalsIgnoreCase("A");
		boolean isProf =  ((String)session.getAttribute("userType")).equalsIgnoreCase("P");
		System.out.println("isAdmin?: "+isAdmin);
		//String guiString = request.getParameter("guistring").replace("___", "#!#");
		System.out.println("HtmlQueryString:  " + request.getQueryString());
		if(isAdmin || isProf){
			
			String surveyTitle = request.getParameter("title");
			String oldSID = request.getParameter("oldsid");
			System.out.println("@SaveAdminSurvey oldSid is " + oldSID);
			String jobfairTitle = "";	//In the case where user is creating a jobfair, jobfairTitle will be same as surveyTitle, else jobfairTitle can be left blank
			String msg = request.getParameter("msg");
			String qndata = request.getParameter("qndata");
			String publishflag = request.getParameter("publish");
			boolean isJobFair = Boolean.parseBoolean(request.getParameter("isjobfair"));
			String pyramidshape = request.getParameter("jobfairpyramidshape");
			
			if(qndata.lastIndexOf("_split_")+"_split_".length() == qndata.length()){
				qndata = qndata.substring(0, qndata.lastIndexOf("_split_"));
			}
			if(pyramidshape.lastIndexOf("_split_")+"_split_".length() == pyramidshape.length()){
				pyramidshape = pyramidshape.substring(0, pyramidshape.lastIndexOf("_split_"));
			}
			qndata = qndata.replace("_split_", "#!#");
			pyramidshape = pyramidshape.replace("_split_", "#!#");
			System.out.println("surveyTitle:"+surveyTitle);
			System.out.println("msg:"+msg);
			System.out.println("Qndata:"+qndata);
			System.out.println("Publish?:"+publishflag);
			System.out.println("Jobfair?:"+isJobFair);
			System.out.println("PyramidShape:"+pyramidshape);
			System.out.println("---------------");
			
			long surveyID = 0;
			if(isJobFair){
				jobfairTitle = surveyTitle;
				surveyID = mediator.createSurvey(adminID, surveyTitle, msg, qndata, pyramidshape, isJobFair);
			}else{
				surveyID = mediator.createSurvey(adminID, surveyTitle, msg, qndata, isJobFair);
				if(!oldSID.equals("0")){
					//0 indicates that there is no old sid
					mediator.deleteSurvey(Long.parseLong(oldSID));
				}
				
			}
			
			if(publishflag.equalsIgnoreCase("true")){
				mediator.setOnlineStatus(surveyID, true);
				EmailController ec = new EmailController();
				jobfairTitle = URLEncoder.encode(jobfairTitle, "UTF-8").replace("+", "%20");
				ec.sendPublishEmail(adminID, surveyID, jobfairTitle);
			}
			out.print(true);
		} else {
			out.print("you do not have any rights to do this.");
		}
	}

}
