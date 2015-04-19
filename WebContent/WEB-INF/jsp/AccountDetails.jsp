<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Account Details</title>
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
		</table>