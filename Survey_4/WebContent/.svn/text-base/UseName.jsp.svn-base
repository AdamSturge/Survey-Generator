<%@ page import="database.*,java.util.*,java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%

Connection c=MySQL.conDBS();
java.sql.Statement s =null;
java.sql.ResultSet rs = null;
java.sql.PreparedStatement pst =null;
String usn=request.getParameter("username");

String sql="select USN from User_Acco  ";
boolean check=true;
try{
s = c.createStatement();
rs = s.executeQuery(sql);

while( rs.next() ){ 
	if(usn.equals(rs.getString("USN")))
	{check=false;
	break;
	}
}		
		
	if(check) {
			
%>
 <span style="color:green"> this username is available </span>
 <span id="checkUSN" style="visibility: hidden">true</span>
<% 

}
	
	else {  %>
	
*this username is already in use
<span id="checkUSN" style="visibility: hidden">false</span>
<% }
}	

catch(Exception e)
{e.printStackTrace();}

finally{
if(rs!=null) rs.close();
if(s!=null) s.close();
if(c!=null) c.close();
}

%>


