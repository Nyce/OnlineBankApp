<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Transfer Money</title>
<style type="text/css">
</style>
</head>

<body>
	
	<br>
	<br>
	<div
		style="font-family: verdana; padding: 10px; border-radius: 10px; font-size: 12px;">
		<form name="deposit" action="updatedBalanced" onSubmit="return validation()" method="POST">
			<table>

				<tr>
					<td><strong>Your balance is :</strong></td>
					<td><input name="balance" value= "${bal}" readonly></td>
				
				</tr>
				<tr>
					<td><strong>Enter amount you want to transfer:</strong> <input type="text" name="amount"></td>
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
				<td><a href="javascript:history.back()">Home</a></td>
				
			</tr>


		</table>
	</div>
</body>
</html>