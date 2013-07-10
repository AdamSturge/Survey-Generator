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
 * Servlet implementation class GetUserSurveyList
 */
@WebServlet("/GetUserSurveyList")
public class GetUserSurveyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserSurveyList() {
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
		System.out.println("@GetUserSurveyList queryString "+ request.getQueryString());
		if( session.getAttribute("userid") == null || ((String)session.getAttribute("userid")).equals("null")){
			response.sendRedirect("MainPage");
		} else {
			//System.out.println("user id: "+userid );
			//String password = (String) session.getAttribute("password");
			//String surveyid = (String) session.getAttribute("surveyid");
			System.out.println("@GetUserSurveyList: before userSurveyLookup");
			//System.out.println("@GetUserSurveyList: "+ request.getQueryString());
			String surveylist = mediator.userSurveyLookup(userid);
			System.out.println("@GetUserSurveyList   "+ surveylist);
			
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
