<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!-- Author: Ji He -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>New Web Project</title>
<link href="WebResources/Site.css" type="text/css" rel="stylesheet">
<link href="WebResources/DashboardLayout.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="WebResources/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="WebResources/jquery-ui-1.9.2.custom.min.js"></script>
</head>
<body>
	<div id="monitor" class="screenElements">
		<div id="lcdBorder" class="screenElements">
			<div id="lcd" style="background-image:url('WebResources/mainpagebackground.png');">
				<div id="registration">
					<p>Rapid registration for new user:</p>
					<br>
					<div style="visibility: visible" id="registerInfo">
						<form id="regiForm" action="Register" method="post">
							<span>Username:</span> <input type="text" name="usd"
								class="logininputR username"> <span id="ucheck"
								class="Warn WarnUserName"></span><br> <span>Password:</span> <input
								type="password" name="psd" class="logininputR"> <br/>
							<span>Confirm:</span> <input type="password" class="logininputR"> <br>
							<span>Name:</span> <input id="fname" type="text" name="name"
								class="logininputR"><br> <span>Address:</span> <input
								type="text" name="address" class="logininputR"> <br>
							<span>Email:</span> <input type="text" name="email" class="logininputR"> <br/>
							<span>Phone:</span> <input type="text" name="phone" class="logininputR">
							<br> <span>Type:</span><br/><input type="radio"
								name="type" value="A">Admin &nbsp;&nbsp;<input
								type="radio" name="type" value="P">Professor
							&nbsp;&nbsp;<input type="radio" name="type" value="S"
								checked="checked">Student
							<button id="rsub" title="submit" type="submit" class="button"></button>
							<div id="Warn" style="visibility: hidden" class="Warn">*
								Complete all fields</div>
						</form>
						
						<div id="togglelogin" class="button">Already registered?</div>
					</div>
				</div>

				<div id="login">
					<p>Existing user please log in:</p>
					<br /> <br />
					<div id="loginform">
						<form action="DashBoard" method="post">
							Username: <input type="text" name="usd" class="logininput"><br />
							<br /> Password: &nbsp;<input type="password" name="psd"
								class="logininput"><br> <br> <input
								type="text" name="sid" style="display: none"
								value=<%=request.getParameter("sid")%>><br> <br>
							<input type="text" name="title" style="display: none"
								value="<%=request.getParameter("title")%>"><br> <br>
							<input type="text" name="jobfair" style="display: none"
								value="<%=request.getParameter("jobfair")%>"><br> <br>
							<input type="text" name="iscompany" style="display: none"
								value=<%=request.getParameter("iscompany")%>><br>
							<div id="toggleregister" class="button">Not registered?</div>
							<br>
							<button style="display: none" type="submit"></button>
						</form>



					</div>
				</div>

			</div>
			<div id="UserControlPanel">
				<img src="WebResources/loginButton.png"
					title="Login and start your application" id="loginButton"
					class="UserControlPanelButton" /> <img
					src="WebResources/registerButton.png"
					title="Register for an account with Memorial CS graduate survey system"
					id="registerButton" class="UserControlPanelButton" />
					<img src="WebResources/mainmunlogo.gif" id="munlogo"/>
			</div>
		</div>
	</div>

	<script>
		$("#loginButton").click(function() {
			$('#loginform button').trigger('click');
		});

		$("#registerButton").click(function() {
			$('#rsub').trigger('click');
		});

		$("#toggleregister").click(function() {
			$('#login').animate({
				left : "2000px"
			}, 1000);
			$("#loginButton").fadeOut("slow");
			$('#registration').animate({
				left : "480px"
			}, 1000);
			$("#registerButton").fadeIn("slow");
		});
		
		$("#togglelogin").click(function() {
			$('#login').animate({
				left : "480px"
			}, 1000);
			$("#loginButton").fadeIn("slow");
			$('#registration').animate({
				left : "2000px"
			}, 1000);
			$("#registerButton").fadeOut("slow");
		});

		$('.username').keyup(function() {
			var usename = $('.username').val();
			$.ajax({

				url : "UseName.jsp",
				type : 'POST',
				data : ({
					"username" : usename
				}),
				success : function(results) {
					//alert(results);
					$("#ucheck").html(results);

				}
			});
		});

		function GetXmlHttpObject() {
			var xmlHttp = null;

			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return xmlHttp;
		}
	</script>
</body>
</html>
