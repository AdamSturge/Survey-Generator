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
 * Servlet implementation class GetTitle
 */
@WebServlet("/GetTitle")
public class GetTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTitle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminID = (String) request.getSession().getAttribute("userid");
		System.out.println("@GetTitle the userID is " + adminID);
		String title = request.getParameter("title");
		System.out.println("@GetTitle the title is " + title);
		GUIMediator mediator = new GUIMediator();
		PrintWriter out = response.getWriter();
		boolean titleCheck = mediator.titleInUse(adminID, title);
		System.out.println("@GetTitle the response for check title is " + titleCheck);
		out.print(titleCheck);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
