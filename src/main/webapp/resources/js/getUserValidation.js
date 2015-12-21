
var isValidEmail = false;
var isEmailIdUnique = false;
var isUserNameUique = false;
var isWeekPassword = false;

function validEmail(emailId) {
	if(emailId.length > 0){
	$('#emailMsg').hide();
	isValidEmail = false;
	var r = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    var response = (emailId.match(r) == null) ? false : true;
   if(response){
    isEmailIdExist(emailId);
   } else {
	   isValidEmail = true;
	   $('#emailMsg').html('<div class="alert alert-warning">Email Id is not valid.</div>').show();
   }
	}
  
}

function isEmailIdExist(emailId){
	//send request to rest controller.	
	$('#emailMsg').hide();
	if(emailId.length > 0){
	isEmailIdUnique=false;
	$.ajax({
        url: "/MyFirstApp/isEmailExist/"+emailId+"/"
    }).then(function(data) {
    	if(data.data){
    		isEmailIdUnique = true;
    		$('#emailMsg').html('<div class="alert alert-warning">Email Id already taken, Please choose another email Id.</div>').show();
    	}else {
    		isEmailIdUnique= false;
    	} 
      /*  $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content); */
    });
	}
}

function checkUsername(username){
	isUserNameUique = false;
	$('#usernameMsg').hide();
	if(username.length > 0){
	$.ajax({
        url: "isUserNameExist/"+username+"/"
    }).then(function(data) {
    	if(data.data){
    		isUserNameUique = true;
    		$('#usernameMsg').html('<div class="alert alert-warning">Username Already Taken. please choose another username.</div>').show();
    	}  else {
    		isUserNameUique = false;
    	}
    	
      /*  $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content); */
    });
	}
}

function validateForm(){
	
	if(isValidEmail|isEmailIdUnique|isUserNameUique|isWeekPassword){
		return false;
	} else {
		return true;
	}
}
