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
					<td><strong>Would you like to withdrawal or Deposit?</strong></td>


				</tr>

			</table>
			<p>
				<span id="deposit"><a
					href="http://localhost:8282/OnlineBank/deposit/${acctNumb}">
						Deposit</a></span> <span id="withdrawal"><a
					href="http://localhost:8282/OnlineBank/withdrawal/${acctNumb}">Withdrawal</a></span>
			</p>
		</form>
		<br> <br> <br>
		<table>

			<tr>

				<td><a href="javascript:history.back()">Home</a></td>

			</tr>


		</table>
	</div>
</body>
</html>