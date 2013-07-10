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
import applicationLogic.IPFetcher;
import applicationLogic.ModelController;
import database.DatabaseController;

/**
 * Servlet implementation class PreviewSurvey
 */
@WebServlet("/PreviewSurvey")
public class PreviewSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		response.setContentType("application/text");
		GUIMediator mediator = new GUIMediator();
		String AID = (String) session.getAttribute("userid");
		String questionData = request.getParameter("questiondata");
		System.out.println("@PreviewSurvey: "+ questionData);
		if(questionData.lastIndexOf("_split_")+"_split_".length() == questionData.length()){
			questionData = questionData.substring(0, questionData.lastIndexOf("_split_"));
		}
		questionData = questionData.replace("_split_", "#!#");
		long SID = mediator.addPreview(AID,questionData);
		String ip = IPFetcher.getIP(request.getRemoteHost());
		String URL = "http://" + ip +":8080/Survey_4/DoSurvey?sid="+Long.toString(SID)+"&jobfair=\"\"&iscompany=false&mode=preview";
		PrintWriter out = response.getWriter();
		out.print(URL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
