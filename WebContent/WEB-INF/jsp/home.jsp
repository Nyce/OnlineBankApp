<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<p>&copy; Capital One 360 | � 2015 Capital One. All rights
			reserved.</p>
	</div>
</div>

<style>
body {
	/*background-image: url('http://crunchify.com/bg.png'); */
	
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

<head>
<title>Online Banking</title>
</head>
<body>
	<br>
	<h2>Welcome, ${username} ${exception}</h2>
	<br>
	<br>
	<p></p>
	<!--<div style="text-align: center">-->
	<form name="acctDetails" action="transfer">
		<table>
			<tr>
				<td>${balMessage}</td>
				<td>${bal}</td>
			</tr>
			<tr>
				<td>${acctTypeMessage}</td>
				<td>${acctType}</td>

			</tr>
			<tr>
				<td>${acctNumberMessage}</td>
				<td>${acctNumb}</td>

			</tr>

		</table>
		<br> <br> <a href="LogOut/">Log Out</a>

		<!--  	<form name="acctDetails" action="withdrawal" >
			
					<input type="hidden"  name="customerid" value= "${acctNumb}" readonly>
					<input type="submit" value="withdrawal" name="Withdrawal" />
					</form>
					<br>
					<input type="submit" value="Transfer"
			name="transfer" /> 
					
			<!--<a href="transfer/" >Transfer Funds</a> -->
	</form>
	<!--</div>-->

</body>
</html>