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


/**
 * Author: Ji He
 * Servlet implementation class getAdminSurveyList
 * 
 * This servlet handles ajax requests for admin created list of surveys.
 */
@WebServlet("/getAdminSurveyList")
public class GetAdminSurveyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAdminSurveyList() {
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
		if( session.getAttribute("userid") == null || ((String)session.getAttribute("userid")).equals("null")){
			response.sendRedirect("MainPage");
		} else {
			//String password = (String) session.getAttribute("password");
			//String surveyid = (String) session.getAttribute("surveyid");
			
			String surveylist = mediator.adminSurveyLookup(userid);
			System.out.println("@GetAdminSurveyList\tuserid: "+ userid +"\t"+surveylist);
			
			out.print(surveylist);
			return;
		}
		out.print(false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
