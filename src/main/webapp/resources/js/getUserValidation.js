
var isValidEmail = false;
var isEmailIdUnique = false;
var isUserNameUique = false;

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
	   $('#emailMsg').text('Email Id is not valid.').show();
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
    	if(data){
    		isEmailIdUnique = true;
    		$('#emailMsg').text('Please Choose Another email Id.').show();
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
    	if(data){
    		isUserNameUique = true;
    		$('#usernameMsg').text('Username Already Taken. please choose another username.').show();
    	}  else {
    		isUserNameUique = false;
    	}
    	
      /*  $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content); */
    });
	}
}

function validateForm(){
	
	if(isValidEmail|isEmailIdUnique|isUserNameUique){
		return false;
	} else {
		return true;
	}
}