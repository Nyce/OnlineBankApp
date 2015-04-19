/**
 * Author: Niyi Odumosu
 * File: JS validation form
 * 
 */

//global variables
var invalid = 0;

function validation() {
	//These validations are for the login form 


	//Username Validation
	//checks value of username in login form
	var un = document.forms["login"]["username"].value;
	if (un == "" || un == null){
		alert("You must type in a name");
		//increment invalidation counter
		invalid += 1;
		return false;
	}



	//Password Validation

	//var pw_regex=  /^[A-Za-z]\w{7,14}$/;
	//check if password is empty
	if(document.forms["login"]["password"].value == ""){
		alert("Password cannot be empty");
		return false;
	}


}

function validate() {
	//These validations are for the Register Form
	var username = document.getElementsByTagName("username").value;
	var hidden = document.getElementsByTagName("hidden").value;
	hidden = username;

	//first name
	var fn = document.forms["register"]["first name"].value;
	if(fn == '' || fn == null){
		alert("You must type in a first name");
		return false;
	}

	//last name
	var ln = document.forms["register"]["last name"].value;
	if(ln == '' || ln == null){
		alert("You must type in a last name");
		return false;
	}

	//Home Address
	var address = document.forms["register"]["home address"].value;
	if(address == '' || address == null){
		alert("You must type in a home address");
		return false;
	}

	//City
	var city = document.forms["register"]["City"].value;
	if(city == '' || city == null){
		alert("You must type in a city");
		return false;
	}

	//State
	var state = document.forms["register"]["State"].value;
	if(state == '' || state == null){
		alert("You must choose a state");
		return false;
	}

	//Zip Code Validation
	var zipcode_regex = /^\d{5}[0-9]$/;
	var zip = document.forms["register"]["Zip Code"].value;
	if (zip == '' || zip == null) {
		alert("You must add your zip code");
		return false;
	}
	//
	if (zipcode_regex.test($.trim($('Zip Code').val())) == false){
		alert('invalid zipcode');
		}


	//username validation
	var un = document.forms["register"]["username"].value;
	if (un == ""){
		alert("You must type in a user name");
		return false;
	}

	var pw = document.forms["register"]["username"].value;
	if (pw == ""){
		alert("You must type in a user name");
		return false;
	}
	//Email Validation
	var email = document.forms["register"]["email address"].value;
	if (email == ""|| email == null){
		alert("You must type in a vaild email address");
		return false;
	}





	//checks if all fields pass
	if (invalid != 0){
		return false;
	}
	else {
		return true;
	}
}
