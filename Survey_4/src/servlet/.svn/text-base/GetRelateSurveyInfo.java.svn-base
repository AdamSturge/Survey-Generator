package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationLogic.ApplicationObject;
import applicationLogic.GUIMediator;
import applicationLogic.ModelController;

/**
 * Author: Ji He
 * 
 * Allows the Admin to pull in jobfair list, and every jobfair item's associated survey item.
 */
@WebServlet("/GetRelateSurveyInfo")
public class GetRelateSurveyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRelateSurveyInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		response.setContentType("application/json");
		ApplicationObject app = (ApplicationObject)this.getServletContext().getAttribute("Application");
		GUIMediator mediator = new GUIMediator();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userid = (String) session.getAttribute("userid");
		
		//implement if this user has the right to use this function

		
		
		
		
		String jobfairid = request.getParameter("jobfairid");
		String surveyid = request.getParameter("surveyid");
		String surveytitle = request.getParameter("surveytitle");
		String action = request.getParameter("action");
//		System.out.println("@GetRelateSurveyInfoServlet  jobfairid resolves to: ["+jobfairid+"]");
//		System.out.println("@GetRelateSurveyInfoServlet  surveyid resolves to: ["+jobfairid+"]");
		System.out.println("@GetRelateSurveyInfoServlet  querystring: " + request.getQueryString());
		if( jobfairid == null || jobfairid.equals("null")){
			String jobfairList = mediator.jobFairLookup(userid);
			out.print(jobfairList);
		} else if(jobfairid != null && action==null){
			jobfairid = jobfairid.trim();
			String associatedsurveyList = mediator.readPairedSurveys(userid, jobfairid);
			//System.out.println("@GetRelateSurveyInfo" + associatedsurveyList);
			out.print(associatedsurveyList);
		} else {
			System.out.println("@GetRelateSurveyInfoServlet - relate");
			jobfairid = jobfairid.trim();
			surveyid = surveyid.trim();
			surveytitle = surveytitle.trim();
			action = action.trim();
			System.out.println("@GetRelateSurveyInfoServlet - relate");
			System.out.println("@GetRelateSurveyInfoServlet  jobfairid resolves to: ["+jobfairid+"]");
			System.out.println("@GetRelateSurveyInfoServlet  surveyid resolves to: ["+surveyid+"]");
			System.out.println("@GetRelateSurveyInfoServlet  surveytitle resolves to: ["+surveytitle+"]");
			
			if(action.equalsIgnoreCase("relate")){
				mediator.savePairedSuveys(Long.parseLong(jobfairid), userid, Long.parseLong(surveyid), surveytitle, 100);
				String associatedsurveyList = mediator.readPairedSurveys(userid, jobfairid);
				out.print(associatedsurveyList);
			} else {
				mediator.unpairSurvey(Long.parseLong(surveyid), Long.parseLong(jobfairid));
				String associatedsurveyList = mediator.readPairedSurveys(userid, jobfairid);
				out.print(associatedsurveyList);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
