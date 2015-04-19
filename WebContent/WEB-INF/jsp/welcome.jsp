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
<title>Account Created</title>
</head>
<body>
	<h2>${username}${message}</h2>
	<br>
	<br>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 18px;">

		<table>

			<!-- <tr>
				<td>Your balance is :</td>
				<td>${customer1.accountInfo.balance}</td>
			</tr> -->
		</table>
		<br> <br> <br>
		<table>

			<tr>
				<td><a href="login">Login</a></td>
				
			</tr>


		</table>
	</div>
</body>
</html>