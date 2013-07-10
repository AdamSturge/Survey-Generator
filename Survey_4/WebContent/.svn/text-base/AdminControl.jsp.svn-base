<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administer page</title>
<link rel='stylesheet' href='WebResources/AdminStyle2.css' type='text/css' />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript" >

$(document).ready(function(){
    $("button:eq(0)").on("click",function(){
        $("#create").show();
       $("#edit").hide();
       $("#delete").hide();
       $("#b2").hide();
       $("#b3").hide();
    });
   
   $("button:eq(1)").on("click",function(){
      $("#create").hide();
      $("#edit").show();
      $("#delete").hide();
      $("#b1").hide();
      $("#b3").hide();
   });
   
   $("button:eq(2)").on("click",function(){
       $("#create").hide();
      $("#edit").hide();
      $("#delete").show();
     
      $("#b1").hide();
      $("#b2").hide();
      
   });
});
</script>
	


<style type="text/css">
#create{
  display:none;   
}
#edit{
  display:none;   
}
#delete{
  display:none;   
}



</style>

</head>
<body>
<h1> Welcome <%="Team 4" %></h1>

<button   id="b1" value="create"> create a new survey</button>
<button  id="b2" value="edit"> edit a survey</button>
<button  id="b3" value="delete"> delete a survey</button>
<br>
<br>
<div id="create">
<form action="Acontrol" method="post">
Number of question: <input type="text" name="NumOfQues">
Number of group: <input type="text" name="NumOfGroup">
<br><br>
<button name="button" value="create">confirm</button>
</form>
</div>

<div id="edit">
<form action="Acontrol" method="post">
select one of the survey to edit:
 <select name="SID">
  <option value="testID">testID</option>
  <option value="testID2">testID2</option>
  <option value="testID3">testID3</option>
  <option value="testID4">testID4</option>
</select>
<br>
<br>
<button name="button" value="edit">confirm</button>
</form>
</div>

<div id="delete">
<form action="Acontrol" method="post">
Select one of the survey to delete: 
<select name="SID">
 <option value="testID">testID</option>
  <option value="testID2">testID2</option>
  <option value="testID3">testID3</option>
  <option value="testID4">testID4</option>
</select>
<br><br>
<button name="button" value="delete">confirm</button>
</form>
</div>


</body>
</html>