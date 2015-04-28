<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<script src="js/form_validation.js"></script>
<head>

<meta charset= "utf-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>Create a New Account</title>
</head>
<body>
	<h2>Hello, please create a new account:</h2>
	<br>
	<br>
	<form name="edit" action="edit" 
		onSubmit="return validation()">
		<p >
			<strong>Modify Your First Name: </strong>
		</p>
		<input type="text" name="first name" value= <%= request.getAttribute("first name") %> >
		<p >
			<strong>Modify Your Last Name: </strong>
		</p>
		<input type="text" name="last name" value = <%= request.getAttribute("last name") %> >
		<p >
			<strong>Modify Home Address: </strong>
		</p>
		<input type="text" name="home address" value = <%= request.getAttribute("home address") %>>
		<p>
			<strong>Modify Your City: </strong>
		</p>
		<input type="text" name="City" value = <%= request.getAttribute("City") %>>
		<p >
			<strong>Modify Your State: </strong>
		</p>
		<input type="text" name="State" value = <%= request.getAttribute("State") %>>
		<p >
			<strong>Modify Your Zip Code: </strong>
		</p>
		<input maxlength="5" type="text" name="Zip Code" value = <%= request.getAttribute("Zip Code") %>>
		<p>
			<strong>Modify Your Email Address: </strong>
		</p>
		<input type="email" name="email address" value = <%= request.getAttribute("email address") %>>
		<p >
			<strong>Modify Your Username: </strong>
		</p>
		<input type="text" name="username" value = <%= request.getAttribute("username") %>>
		<p>
		<p>
			<strong>Modify Your Password: </strong>
		</p>
		<input type="password" name="password" value = <%= request.getAttribute("password") %>>
		<p>
			<strong>Enter Your Current Balance: </strong>
		</p>
		<input type="text" name="balance" >
		  
		<input type="hidden" name="balanceHid"/>  
		
		<input type="hidden" name="nameHid"/>
		<p>
			<input type="submit" value="Submit"> <input type="reset"
				value="Reset"> 
	</form>
</body>
</html>