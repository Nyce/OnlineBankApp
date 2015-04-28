<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Customer Details</title>
<style type="text/css">
</style>
</head>
<body>


	<h2>${customer1.firstname}${message}</h2>
	<br>
	<br>
	<table>
		<tr>
			<td>Customer Firstname</td>
			<td>${customer1.firstname}</td>
		</tr>
		<tr>
			<td>Customer Lastname:</td>
			<td>${customer1.lastname}</td>

		</tr>
		<tr>
			<td>Customer Address:</td>
			<td>${customer1.address}</td>

		</tr>
		<tr>
			<td>Customer City</td>
			<td>${customer1.city}</td>
		</tr>
		<tr>
			<td>Customer State:</td>
			<td>${customer1.state}</td>

		</tr>
		<tr>
			<td>Customer Zip Code:</td>
			<td>${customer1.zip}</td>

		</tr>
		<tr>
			<td>Customer Email:</td>
			<td>${customer1.email}</td>

		</tr>
		<tr>
			<td>Customer Birthdate</td>
			<td>${customer1.dob}</td>
		</tr>
		
		<tr>
			<td>Customer Account #:</td>
			<td>${customer1.accountInfo.accountid}</td>

		</tr>

<tr>
			<td>My Balance:</td>
			<td>${customer1.accountInfo.balance}</td>

		</tr>

	</table>

</body>

</html>