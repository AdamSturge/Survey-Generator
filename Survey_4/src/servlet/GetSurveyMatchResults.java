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
 * Allows the Admin to fetch the match results for the surveys they have created. Takes the surveyID as parameter.
 */
@WebServlet("/GetSurveyMatchResults")
public class GetSurveyMatchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSurveyMatchResults() {
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
		String sid = request.getParameter("sid");
		if( session.getAttribute("userid") == null || ((String)session.getAttribute("userid")).equals("null")){
			response.sendRedirect("MainPage");
		} else {
			System.out.println("@GetSurveyMatchResults\t sid="+ sid);
			String matcheResults = mediator.fetchMatches(Long.parseLong(sid.trim()));
			System.out.println("@GetSurveyMatchResults   "+ matcheResults);
			
			out.print(matcheResults);
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
