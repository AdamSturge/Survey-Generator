package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import applicationLogic.GUIMediator;


/**
 * Servlet implementation class adminControl
 */
@WebServlet({"/adminControl", "/Acontrol"})
public class adminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GUIMediator mediator =new GUIMediator();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminControl() {
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
		 PrintWriter writer=response.getWriter();
		String action=request.getParameter("button");
		//System.out.println(action);
	    if(action.equals("create"))
	      {RequestDispatcher dp=request.getRequestDispatcher("createSurvey.jsp");
		   dp.forward(request, response);
	      }
	    else if(action.equals("edit"))
	    {
	    	
	    	
	    	
	    }
	    else if(action.equals("delete"))
	    {
	    	String UID="MUN";
	    	String SID = request.getParameter("SID");
	    	System.out.println(SID);
	    	//String GuiString=SID+"_split_"+UID;
	    	//md.deleteSurvey(SID,UID);
	    	writer.println("<head>");
	    	writer.println("<link rel='stylesheet' href='WebResources/AdminStyle2.css' type='text/css' />");
	    	writer.println("</head>");
	    	writer.println("<body>");
	    	writer.println("you has successfully deleted survey "+SID);
	    	writer.println("<br><br>");
	    	writer.println("<button>go to main page</button>&nbsp;&nbsp;");
	    	writer.println("<button value='logOut'> Log out </button>");
	    	writer.println("</body>");
	    			
	    	
	    	
	    }
	}

}
