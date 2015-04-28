<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<div id="wrapperHeader">
	<div id="header">
		<img src="http://home.bvt13.ingdedev.com/images/brand-logo-2x.png"
			width="80%" height="150px" alt="logo" />
	</div>
</div>

<div class="wrapper">
	<div class="content">
		<p></p>
	</div>
	<div id="footer">
		<p>&copy; Capital One 360 | © 2015 Capital One. All rights
			reserved.</p>
	</div>
</div>

<style>
body {
	background-image: url('http://crunchify.com/bg.png');
}

.wrapper {
	min-height: 100%;
	position: relative;
}

.content {
	/* padding the footer adds 40 to footer height */
	padding-bottom: 140px;
}

#footer {
	position: fixed;
	height: 50px;
	background-color: #AA4047;
	bottom: 0px;
	left: 0px;
	right: 0px;
	margin-bottom: 0px;
}
</style>
<script type="text/javascript">
	function validation() {
		//These validations are for the login form 

		//Username Validation
		//checks value of username in login form
		var un = document.forms["login"]["username"].value;
		if (un == "" || un == null) {
			alert("You must type in a name");
			//increment invalidation counter
			return false;
		}

		//Password Validation

		//var pw_regex=  /^[A-Za-z]\w{7,14}$/;
		//check if password is empty
		var pw = document.forms["login"]["password"].value;
		if (pw == "" || pw == null) {
			alert("Password cannot be empty");
			return false;
		}
	}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>Login Page</title>
</head>
<body>
	<h2>Hello, please log in:</h2>

	<br>
	<br>
	<form name="login" action="loginSuccess" method="post"
		onSubmit="return validation()">
		<table>
			<tr>
				<td><strong>User Name: </strong></td>
				<td><input type="text" name="username"></td>

			</tr>
			<tr>

				<td><strong>Password: </strong></td>
				<td><input type="password" name="password"></td>
			<tr>
		</table>

		<p>
			<input type="submit" value="Submit"> <input type="reset"
				value="Reset"> <input type="hidden" name="testName" /> <input
				type="hidden" name="hiddenBalance" />
		</p>
	</form>
	<br>
	<p>
		<b>Don't have an account yet? </b><a href="register">Register here</a>
	</p>
</body>
</html>