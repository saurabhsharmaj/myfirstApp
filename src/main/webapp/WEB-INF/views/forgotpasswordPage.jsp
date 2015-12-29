
<script>
var validEmailId = true;
var isEmailIdUnique = true;
function validEmail(emailId) {
	
	var r = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    var response = (emailId.match(r) == null) ? false : true;
   if(response){
	   validEmailId = true;	  
   } else {
	   validEmailId = false;	  	 
   }
	return validEmailId;
}

function isEmailIdExist(emailId){		
	$('#emailMsg').hide();
	
	if(validEmail(emailId)){
		
		if(emailId.length > 0){
		isEmailIdUnique=false;
		$.ajax({
	        url: "/MyFirstApp/rest/isEmailExist/"+emailId+"/"
	    }).then(function(data) {
	    	if(data.data){
	    		isEmailIdUnique = false;
	    		$('#emailMsg').hide();
	    	}else {
	    		$('#emailMsg').html('<div class="alert alert-warning">Email Id not exist.</div>').show();
	    		isEmailIdUnique = true;
	    	} 	     
	    });
		}
	} else {
		$('#emailMsg').html('<div class="alert alert-warning">Email Id not Valid.</div>').show();
	}
}

function validateForgotPasswordForm(){
	
	if(isEmailIdUnique | !validEmailId)
		return false;
	else
		return true;
	
}
</script>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
		  <form class="form-signin" action ="${pageContext.request.contextPath}/resetPassword" method="post"  onsubmit="return validateForgotPasswordForm();">
		    <h3 class="form-signin-heading">Get your password</h3>	
		    <h3 style="color: red;">${error_message}</h3>	    
		    <label for="email" class="sr-only">Email Id</label>
		    <input type="text" id="email" name="email" class="form-control" placeholder="email id" required autofocus  onblur="isEmailIdExist(this.value);"/>
			<span id="emailMsg" style="color: red;"></span>		    
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>	    
		   
		  </form>
 		</div>
 	</div>
 </div>