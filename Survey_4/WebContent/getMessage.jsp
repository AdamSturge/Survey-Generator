<%@ page import="database.*,java.util.*,java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Connection c=MySQL.conDBS();
java.sql.Statement s =null;
java.sql.ResultSet rs = null;
java.sql.PreparedStatement pst =null;
String SID=request.getParameter("SurveyID");

String sql="select Message from AdminSurveyLookup where SID=" +SID;

try{
s = c.createStatement();
rs = s.executeQuery(sql);
while(rs.next())
{ 
%>
<%=rs.getString("Message")%> 
<%
 }

}
	
catch(Exception e)
 {e.printStackTrace();}

finally{
if(rs!=null) rs.close();
if(s!=null) s.close();
if(c!=null) c.close();
}

%>			


 
