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
 * Saves the survey that the user is currently working on.
 */
@WebServlet("/SaveUserSurvey")
public class SaveUserSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUserSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		ApplicationObject app = (ApplicationObject)this.getServletContext().getAttribute("Application");
		GUIMediator mediator = new GUIMediator();
		HttpSession session=request.getSession();

		System.out.println("---@SaveUserSurvey---");
		
		String uid=(String) session.getAttribute("userid");
		String pyramidData=request.getParameter("data").replace("___", "#!#");
		String sid=request.getParameter("sid");
		String isCompany=request.getParameter("iscompany");
		String jobFair=request.getParameter("jobfair");
		String groupNumber=request.getParameter("groupnumber");
		Boolean submit= Boolean.parseBoolean(request.getParameter("submit"));
		
		System.out.println("uid: "+uid);
		System.out.println("sid: "+sid);
		System.out.println("isCompany:" + isCompany);
		System.out.println("pyramiddata: "+pyramidData);
		
		if(submit){
			mediator.userSubmit(Long.parseLong(sid.trim()), uid, pyramidData, jobFair, Boolean.parseBoolean(isCompany));
			System.out.println("@SaveUserSurvey: User submitted");
		} else {
			mediator.savePyramid(Long.parseLong(sid.trim()), uid, pyramidData, Boolean.parseBoolean(isCompany),Integer.parseInt(groupNumber));
			System.out.println("@SaveUserSurvey: User saved");
		}
		
		out.print(true);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
