 <%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create survey</title>

<link rel='stylesheet' href='WebResources/AdminStyle2.css' type='text/css' />

</head>
<body>
<%int np= Integer.parseInt(request.getParameter("NumOfQues"));  
  int ng = Integer.parseInt(request.getParameter("NumOfGroup"));%>
<h2>total <%=np %> questions enter place is ready to be used</h2>
<h3 > note: each question maximum 200 words</h3>
<form  method="post"  action="transSurvey">
<table border="1">

<% 
int i,j;
String[] question=new String [np];
String[] answer=new String[np];
  for( i=1;i<=ng;i++) { %>
  
<tr>
<th> please enter question for group <%=i%></th>
</tr>
  <tr>
  <td>
  <%for(j=1;j<=(np/ng);j++) { 
    String Q="question"+i+j;
    String A="answer"+i+j;
    %>
<%=j %> <textarea rows="1" cols="70" name=<%=Q %>></textarea><br>
<input type="radio" name=<%=A %> value="1"> Strongly like&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name=<%=A %> value="2"> Like&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name=<%=A %> value="3"> Medium&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name=<%=A %> value="4"> Dislike&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name=<%=A %> value="5"> Strongly Dislike<br><br>
<%} }%>
</td>
</tr>
</table>
<input type="hidden" name="NumOfQues" value="<%=np%>">
<input type="hidden" name="NumOfGroup" value="<%=ng%>">
<button name="button" value="save">save</button>&nbsp;&nbsp;
<button name="button" value="preview" onClick="openNewWindow()">preview</button>&nbsp;&nbsp;
<button onClick="this.form.reset(); return false;">reset</button>&nbsp;&nbsp;
<button name="button" value="publish">publish</button>&nbsp;&nbsp;
</form>

</body>
</html>