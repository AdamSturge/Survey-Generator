package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import applicationLogic.GUIMediator;
import applicationLogic.LoginSystem;
import applicationLogic.ModelController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/DashBoard" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GUIMediator mediator = new GUIMediator();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usd = request.getParameter("usd");
		String psd = request.getParameter("psd");
		String sid = request.getParameter("sid");
		String title = request.getParameter("title");
		String jobFair = request.getParameter("jobfair");
		String isCompany = request.getParameter("iscompany");
		System.out.println("@LoginServlet\treference-sid:" + sid
				+ "\treference-title:" + title);
		HttpSession session = request.getSession();
		GUIMediator mediator = new GUIMediator();
		String type;

		Boolean valid = mediator.CheckAccount(usd, psd);
		type = mediator.getType(usd); // if type is A, then it is admin. U for
										// user.
		if (valid) {

			if (!sid.equals("null") && !title.equals("null")
					&& !jobFair.equals("null")) {
				System.out
						.println("@LoginServlet\t mc.writeToUserSurveyLookup()");
				boolean written = mediator.writeToUserSurveyLookup(usd,
						Long.parseLong(sid), title, jobFair,
						Boolean.parseBoolean(isCompany));
				if (written == false) {
					// Alert the user that they have already added this survey
					// to their active survey list
					System.out
							.println("@LoginServlet\t mc.writeToUserSurveyLookup() failed");
				}
			}
			if (type.equals("A")) {
				System.out.println("Admin Logged in");
				session.setAttribute("userid", usd);
				session.setAttribute("password", psd);
				session.setAttribute("userType", type);
				request.setAttribute("welcomemsg", "Welcome "
						+ (String) session.getAttribute("userid"));
				System.out.println("isCompany:" + isCompany);
				request.getRequestDispatcher("/AdminDashBoard.jsp").forward(
						request, response);
			} else if (type.equals("P")) {
				System.out.println("Professor Logged in");
				session.setAttribute("userid", usd);
				session.setAttribute("password", psd);
				session.setAttribute("userType", type);
				request.setAttribute("welcomemsg", "Welcome "
						+ (String) session.getAttribute("userid"));
				request.getRequestDispatcher("/ProfDashboard.jsp").forward(
						request, response);

			} else if (type.equals("S")) {
				System.out.println("Student Logged in");
				session.setAttribute("userid", usd);
				session.setAttribute("password", psd);
				session.setAttribute("userType", type);
				request.setAttribute("welcomemsg", "Welcome "
						+ (String) session.getAttribute("userid"));
				request.getRequestDispatcher("/UserDashBoard.jsp").forward(
						request, response);

			} else if (type.equals("P")) {
				System.out.println("Perfessor Logged in");
				session.setAttribute("userid", usd);
				session.setAttribute("password", psd);
				session.setAttribute("userType", type);
				request.setAttribute("welcomemsg", "Welcome "
						+ (String) session.getAttribute("userid"));
				request.getRequestDispatcher("/ProfDashboard.jsp").forward(
						request, response);}
			
			
			
		} else {
			response.sendRedirect("MainPage");
		}

	}

}
