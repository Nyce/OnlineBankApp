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

.balance {
	position: relative;
	right: 177px;
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
</head>

<body>

	<br>
	<br>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px;">
		<form name="deposit" action="updatedBalance"
			onSubmit="return validation()" method="POST">
			<table>

				<tr>
					<td><strong>Your balance is :</strong></td>
							<td>${bal}</td>

				</tr>
				<tr>
					<td><strong>Enter amount you want to transfer:</strong> <input
						type="text" name="amount"></td>
				</tr>
			</table>
			<p>
				<input type="submit" value="Submit"> <input type="reset"
					value="Reset">
			</p>
		</form>
		<br> <br> <br>
		<table>

			<tr>
				<td><a href="/AccountDetails">Account Info</a></td>
				<td><a href="home">Home</a></td>

			</tr>


		</table>
	</div>
</body>
</html>