package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.GUIMediator;

/**
 * Servlet implementation class EditSurvey
 */
@WebServlet("/EditSurvey")
public class EditSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String SID = request.getParameter("sid");
		String isJobFair = request.getParameter("isjobfair");
		GUIMediator mediator = new GUIMediator();
		String jsonResult = mediator.fetchIncompleteSurvey(Long.parseLong(SID), Boolean.parseBoolean(isJobFair));
		System.out.println("@EditSurvey: jsonResult is " + jsonResult);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
