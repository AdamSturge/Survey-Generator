package servlet;
/***
 * Author: Ji He
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet produces the Main page or the Index page for this web application
 * survey forwarding queries needs to be handled in this servlet
 */
@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession(true);
//		session.setAttribute("userid", "123456");
//		session.setAttribute("pass", "654321");
//		session.setAttribute("surveyid", "50");
//		request.setAttribute("session.user", session.getAttribute("userid") );
//		request.setAttribute("session.pass", session.getAttribute("pass") );
//		request.setAttribute("session.surveyid", session.getAttribute("surveyid") );
		request.getRequestDispatcher("/MainPage.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
