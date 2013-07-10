package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationLogic.ApplicationObject;
import applicationLogic.GUIMediator;
import applicationLogic.ModelController;
import database.DatabaseController;

/**
 * Author: Ji He
 * Servlet implementation class LoginDashBoard
 * 
 * This servlet presents the interface after a user successfully logs into the survey system.
 * Depending on the type of user logged in, different interface will be presented
 */
@WebServlet("/LoginDashBoard")
public class LoginDashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDashBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApplicationObject app = (ApplicationObject)this.getServletContext().getAttribute("Application");
		GUIMediator mediator = new GUIMediator();
		
		
		HttpSession session = request.getSession();
		
		System.out.println("@LoginDashBoard\tuserid:" + (String)session.getAttribute("userid"));
		
		if( session.getAttribute("userid") == null || ((String)session.getAttribute("userid")).equals("null")){
			System.out.println("@LoginDashBoard\t Redirecting to MainPage");
			response.sendRedirect("MainPage");
		} else {
			request.setAttribute("welcomemsg", "Welcome "+(String)session.getAttribute("userid") );
			
			if(  ((String)session.getAttribute("userType")).equalsIgnoreCase("A") ){
				System.out.println("@LoginDashBoard\t Redirecting to AdminDashBoard");
				request.getRequestDispatcher("/AdminDashBoard.jsp").forward(request,response);
			} else {
				System.out.println("@LoginDashBoard\t Redirecting to UserDashBoard");
				request.getRequestDispatcher("/UserDashBoard.jsp").forward(request,response);
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
