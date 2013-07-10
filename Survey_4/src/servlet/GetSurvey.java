package servlet;
/****
 * Author: Ji He
 */

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
 * Retrieves a survey from the server for the user to work on. 
 */
@WebServlet("/getSurvey")
public class GetSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSurvey() {
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
		String isCompany = request.getParameter("iscompany");
		System.out.println("@getSurvey is company is " + isCompany);
		if( session.getAttribute("userid") == null || ((String)session.getAttribute("userid")).equals("null")){
			response.sendRedirect("MainPage");
		} else {
			String userID = (String) session.getAttribute("userid");
			//grab the surveyID from querystring
			String surveyID = request.getParameter("sid");
			String survey = mediator.fetchSurvey(Long.parseLong(surveyID.trim()), userID, Boolean.parseBoolean(isCompany));
			
			out.print(survey);
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
