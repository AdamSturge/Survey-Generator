package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.GUIMediator;
import applicationLogic.LoginSystem;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({"/RegisterServlet","/Register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usd=request.getParameter("usd");
		String psd=request.getParameter("psd");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String type=request.getParameter("type");

		GUIMediator mediator = new GUIMediator();
		ArrayList<Object> contactInfo =new ArrayList<Object>();
		contactInfo.add(name);
	    contactInfo.add(email);
		contactInfo.add(address);
		contactInfo.add(phone);
		contactInfo.add(usd);
		
	
		
		ArrayList<Object> userInfo = new ArrayList<Object>();
		userInfo.add(usd);
		userInfo.add(psd);
		userInfo.add(type);

		mediator.newAccount(userInfo, contactInfo);
		response.sendRedirect("MainPage");
	}

}
