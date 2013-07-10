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
 * Servlet implementation class GetCurrentGroupNumber
 */
@WebServlet("/GetCurrentGroupNumber")
public class GetCurrentGroupNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentGroupNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GUIMediator mediator = new GUIMediator();
		String uid=(String) request.getSession().getAttribute("userid");
		String SID = request.getParameter("sid");
		int groupNumber = mediator.fetchCurrentGroup(uid, Long.parseLong(SID));
		PrintWriter out = response.getWriter();
		out.print(groupNumber);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
