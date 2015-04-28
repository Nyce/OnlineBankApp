<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html>
<div id="wrapperHeader">
	<div id="header">
		<img src="http://home.bvt13.ingdedev.com/images/brand-logo-2x.png"
			width="80%" height="100px" alt="logo" />
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

.customerInfo {
	position: relative;
}

#footer {
	position: fixed;
	height: 30px;
	background-color: #AA4047;
	bottom: 0px;
	left: 0px;
	right: 0px;
	margin-bottom: 0px;
}

.exception {
	color: red;
}
</style>
<script type="text/javascript">


	function validate() {
		//These validations are for the Register Form
	
		//first name
		var fn = document.forms["register"]["firstname"].value;
		var regex = /^[a-zA-Z]+$/;
		if (fn == '' || fn == null) {
			alert("You must type in a first name");
			return false;
		}
		else if (!fn.match(regex))
	    {
	        alert("Firstname must have letters only");
	        return false;
	    }
		
		//last name
		var ln = document.forms["register"]["lastname"].value;
		var regex = /^[a-zA-Z]+$/;
		if (ln == '' || ln == null) {
			alert("You must type in a last name");
			return false;
		}
		
		else if (!ln.match(regex))
	    {
	        alert("Lastname must have letters only");
	        return false;
	    }

		//Home Address
		var address = document.forms["register"]["address"].value;
		if (address == '' || address == null) {
			alert("You must type in a home address");
			return false;
		}

		//City
		var city = document.forms["register"]["city"].value;
		var regex = /^[a-zA-Z]+$/;
		if (city == '' || city == null) {
			alert("You must type in a city");
			return false;
		}
		else if (!city.match(regex))
	    {
	        alert("City must have letters only");
	        return false;
	    }

		//State
		var state = document.forms["register"]["state"].value;
		if (state == '' || state == null) {
			alert("You must choose a state");
			return false;
		}

		//Zip Code Validation
		var zipcode_regex = /^\d{5}[0-9]$/;
		var zip = document.forms["register"]["zip"].value;
		if (zip == '' || zip == null) {
			alert("You must add your zip code");
			return false;
		}
		
		else if((isNaN(zip))){
			alert("Zipcode can only have numbers");
			return false;
			
		}
		//Email Validation
		var email = document.forms["register"]["email"].value;
		if (email == "" || email == null) {
			alert("You must type in a vaild email address");
			return false;
		}

		//username validation
		var un = document.forms["register"]["username"].value;
		if (un == "") {
			alert("You must type in a user name");
			return false;
		}

		var pw = document.forms["register"]["password"].value;
		if (pw == "") {
			alert("You must type in a password");
			return false;
		}

		//dob Validation
		var dob = document.forms["register"]["dob"].value;
		document.getElementByName('dob').valueAsDate = new Date();
		if (dob == "" || dob == null) {
			alert("You must type in a vaild dob");
			return false;
		}
		
		
		
		//balance Validation
		var balance = document.forms["register"]["accountInfo.balance"].value;
		if (balance == "" || balance == null) {
			alert("You must enter a vaild balance");
			return false;
		}
		
	}
	
</script>
<head>

<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Create a New Account</title>
</head>

<body>
	<h2>${message}</h2>

	<form id="create" name="register" action="welcome" method=post
		onSubmit="return validate()">
		<fieldset class="customerInfo">
			<legend>Customer Info:</legend>
			<p>
				<strong>Please Enter Your First Name: </strong>
			</p>
			<input type="text" name="firstname" maxlength="25"> <span
				class="exception">${fnException}</span>
			<p>
				<strong>Please Enter Your Last Name: </strong>
			</p>
			<input type="text" name="lastname" maxlength="25"> <span
				class="exception">${lnException}</span>
			<p>
				<strong>Please Enter Home Address: </strong>
			</p>
			<input type="text" name="address"> <span class="exception">${addressException}</span>
			<p>
				<strong>Please Enter Your City: </strong>
			</p>
			<input type="text" name="city"> <span class="exception">${cityException}</span>
			<p>
				<strong>Please Choose Your State: </strong>
			</p>
			<select name="state" id="state">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
				<option value="CT">Connecticut</option>
				<option value="DE">Delaware</option>
				<option value="DC">District of Columbia</option>
				<option value="FL">Florida</option>
				<option value="GA">Georgia</option>
				<option value="HI">Hawaii</option>
				<option value="ID">Idaho</option>
				<option value="IL">Illinois</option>
				<option value="IN">Indiana</option>
				<option value="IA">Iowa</option>
				<option value="KS">Kansas</option>
				<option value="KY">Kentucky</option>
				<option value="LA">Louisiana</option>
				<option value="ME">Maine</option>
				<option value="MD">Maryland</option>
				<option value="MA">Massachusetts</option>
				<option value="MI">Michigan</option>
				<option value="MN">Minnesota</option>
				<option value="MS">Mississippi</option>
				<option value="MO">Missouri</option>
				<option value="MT">Montana</option>
				<option value="NE">Nebraska</option>
				<option value="NV">Nevada</option>
				<option value="NH">New Hampshire</option>
				<option value="NJ">New Jersey</option>
				<option value="NM">New Mexico</option>
				<option value="NY">New York</option>
				<option value="NC">North Carolina</option>
				<option value="ND">North Dakota</option>
				<option value="OH">Ohio</option>
				<option value="OK">Oklahoma</option>
				<option value="OR">Oregon</option>
				<option value="PA">Pennsylvania</option>
				<option value="RI">Rhode Island</option>
				<option value="SC">South Carolina</option>
				<option value="SD">South Dakota</option>
				<option value="TN">Tennessee</option>
				<option value="TX">Texas</option>
				<option value="UT">Utah</option>
				<option value="VT">Vermont</option>
				<option value="VA">Virginia</option>
				<option value="WA">Washington</option>
				<option value="WV">West Virginia</option>
				<option value="WI">Wisconsin</option>
				<option value="WY">Wyoming</option>
			</select>${stateException}
			<p>
				<strong>Please Enter Your Zip Code: </strong>
			</p>
			<input maxlength="5" name="zip" minlength="5"> <span
				class="exception">${zipException}</span>
			<p>
				<strong>Please Enter Your Date of Birth (mm/dd/yyyy): </strong>
			</p>
			<input type="date" name="dob" value="2015-04-24" min="1900-01-01"
				max="2015-04-24"> <span class="exception">${dobException}</span>
			<p>
				<strong>Please Enter Your Email Address: </strong>
			</p>
			<input type="email" name="email"> <span class="exception">${emailException}</span>

			<p>
				<strong>Please Choose Your Sex: </strong>
			</p>

			<select name="sex" id="sex">
				<option value="M">M</option>
				<option value="F">F</option>
			</select> <span class="exception">${sexException}</span>
		</fieldset>
		<br>
		<fieldset>
			<legend>User Info:</legend>
			<p>
				<strong>Please Enter Your Username: </strong>
			</p>
			<input type="text" name="username"> <span class="exception">${usernameException}</span>
			<p>
			<p>
				<strong>Please Enter Your Password: </strong>
			</p>
			<input type="password" name="password"
				pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
				title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
			<span class="exception">${passwordException}</span>
		</fieldset>
		<br>
		<fieldset>
			<legend>Account Info:</legend>

			<p>
				<strong>Please Choose your type of Banking Account: </strong>
				${acctTypeException}
			</p>
			<p>
				<select name="accountType" id="accountType">
					<option value="100">Checking</option>
					<option value="200">Savings</option>
				</select>
			</p>
			<input type="submit" value="Submit"> <input type="reset"
				value="Reset">
		</fieldset>
		<p>
			<input type="hidden" name="acct_description"
				value="Checking acct code is 100. Savings acct code is 200">
			<input type="hidden" name="txn_description"
				value="Deposit code is 300. Withdrawal code is 400">
		</p>
		<input type="hidden" name="balance" value="0" /> <input type="hidden"
			name="amount" value="0" /> <input type="hidden" name="txnType"
			value="300" />

	</form>
</body>
</html>