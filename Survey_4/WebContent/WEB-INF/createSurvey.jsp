 <%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create survey</title>
</head>
<body>
<h2>please enter 25 questions</h2>
<h3> note: each question maximum 200 words</h3>
<% int i;
String[] question=new String [25];
String[] answer=new String[25];
  for( i=1;i<=25;i++) { %>
<%=i %> <textarea rows="2" cols="70"></textarea><br>
<input type="radio" name="{$answer[i]}" value="SL"> Strongly like&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name="{$answer[i]}" value="L"> Like&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name="{$answer[i]}" value="M"> Medium&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name="{$answer[i]}" value="D"> Dislike&nbsp;&nbsp;&nbsp;&nbsp;
 <input type="radio" name="{$answer[i]}" value="SD"> Strongly Dislike<br><br>
<%} %>
<form>
<button>save</button>&nbsp;&nbsp;
<button>preview</button>&nbsp;&nbsp;
<button>reset</button>&nbsp;&nbsp;
<button>publish</button>&nbsp;&nbsp;
</form>
</body>
</html>