<script>
$('#passwordStrength').hide();
$('#confirmPasswordMsg').hide();

var isPasswordMisMatch = false;

function matchPassword(){
	
	$('#passwordStrength').hide();
	$('#confirmPasswordMsg').hide();
	if($('#password').val()== $('#confirm_password').val()){
		isPasswordMisMatch= true;
	} else { 
		$('#confirmPasswordMsg').html('Password is not match').show();
		isPasswordMisMatch= false;
	}
	return isPasswordMisMatch;
	
}

function validatePassword(){
	if(matchPassword()){
		return true;
	} else {
		return false;
	}	
}
</script>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
		  <form class="form-signin" action ="${pageContext.request.contextPath}/saveNewPassword" method="post"  onsubmit="return validatePassword();">
		    <h3 class="form-signin-heading">Get your password</h3>	
		    <h3 style="color: red;">${error_message}</h3>	    
		    <label for="email" class="sr-only">Email Id</label>
		    id:<input type="hidden" name="id" value="${userProfile.id}">
		    <tr>
					<td>
						New Password :
					</td>
					<td>
					<div class="row">
			        <div class="col-lg-12 control-label">
									<input type="password" name="password" class="form-control" placeholder="password" id="password" required/>
			        </div>
			        <div class="col-lg-12 control-label">
									<div id="passwordStrength" class="alert alert-warning">  </div>            
			        </div>
    				</div>
					</td>
				</tr>
				
				<tr>
					<td>
						Confirm Password
					</td>
					<td>
					
			        <input type="password" class="form-control" placeholder="confirm password" id="confirm_password" required onblur="matchPassword();">
			        <div id="confirmPasswordMsg" class="alert alert-warning">  </div> 
					</td>
				</tr>	    
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>	    
		   
		  </form>
 		</div>
 	</div>
 </div>