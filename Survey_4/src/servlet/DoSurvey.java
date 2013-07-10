package servlet;
/**
 * Author: Ji He
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseController;

import applicationLogic.ApplicationObject;

/**
 *	This servlet produces GUI for survey participants to do the surveys.
 *	Note: Somehow the project is created in such a way that Servlet Mapping is Annotation based (below this comment block)
 *	The Servlet Name shall be the class name, and mapping is quotation in WebServlet( .. )
 */
@WebServlet("/DoSurvey")
public class DoSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSurvey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationObject app = (ApplicationObject)this.getServletContext().getAttribute("Application");
		DatabaseController db = (DatabaseController) app.getApplication("DatabaseController");
		request.setAttribute("path", this.getServletContext().getContextPath() );
		request.setAttribute("dbOnline", db.isOnline() );
		
		HttpSession session = request.getSession();
		request.setAttribute("userid", session.getAttribute("userid") );
		request.setAttribute("surveyid", session.getAttribute("surveyid") );
		request.getRequestDispatcher("/DoSurvey.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
