package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.EmailController;
import applicationLogic.GUIMediator;

/**
 * Servlet implementation class SetOnline
 */
@WebServlet("/SetOnline")
public class SetOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetOnline() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String SID = request.getParameter("sid");
		String adminID = (String) request.getSession().getAttribute("userid");
		GUIMediator mediator = new GUIMediator();
		mediator.setOnlineStatus(Long.parseLong(SID), true);
		EmailController ec = new EmailController();
		ec.sendPublishEmail(adminID, Long.parseLong(SID), "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
