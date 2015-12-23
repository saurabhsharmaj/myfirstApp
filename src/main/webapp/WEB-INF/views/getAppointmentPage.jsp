<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getUserValidation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/zxcvbn.js"></script>
<script>

$( document ).ready(function() {
	$('#confirmPasswordMsg').hide();
	$('#signin').hide();
	$('#registration').hide();
	$('#passwordStrength').hide();
	$('#password').keyup(function() {
		  var textValue = $(this).val();				
			$('#passwordStrength').hide();
			if(textValue.length >= 0){			  
				var result = zxcvbn(textValue);
				if(result.score <= 1){
				 isWeekPassword=true;
				 $('#passwordStrength').html("Week").show();	  
				} else if(result.score <= 3){
				 isWeekPassword=false;
				 $('#passwordStrength').html("Medium").show();			  
				} else if(result.score <= 4){
				 isWeekPassword=false;
				 $('#passwordStrength').html("Strong").show();			  
				}
		}
	});
	
});
	

function displayLoginForm(){
	$('#signin').show();
	$('#registration').hide();
	
}

function displayRegistrationForm(){
	$('#signin').hide();
	$('#registration').show();
	
}
</script>

<h3>Book Your Appointment</h3>
<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
		<h4>Have you used ZocDoc before? We'll use the
			information from your last visit </h4>
					
			
		        <input type="radio" name="type" onclick="displayRegistrationForm();"/>I'm new to GetDoc.
				<input type="radio" name="type" onclick="displayLoginForm();"/>I've used GetDoc before.
		     
		     <div id="registration">
		     	<h2>Registration Page</h2>
				<c:url var="addAction" value=" ${pageContext.request.contextPath}/newRegistration/${doctor.id}" ></c:url>
			<form:form action="${addAction}" commandName="user" method="post" onsubmit="return validateForm();">
			<form:hidden name="enabled" path="enabled" value="1"/>
			<form:hidden name="profilePicUrl" path="profilePicUrl" value="profilePic.jpg"/>
			<form:hidden name="userRole" path="userRole.id" value="3"/>
			<table>		
				<tr>
					<td>
						<form:label path="email">
							<spring:message text="email"/>
						</form:label>
					</td>
					<td>
						<form:input path="email" cssClass="form-control" placeholder="Email address" required="true"  onblur="validEmail(this.value);"/>
						<span id="emailMsg" style="color: red;"></span>
					</td>
				</tr>	
				
				<tr>
					<td>
						<form:label path="username">
							<spring:message text="username"/>
						</form:label>
					</td>
					<td>
						<form:input path="username" cssClass="form-control" placeholder="Username" required="true" onblur="checkUsername(this.value);"/>
						<span id="usernameMsg" style="color: red;"></span>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<form:label path="password">
							<spring:message text="password"/>
						</form:label>
					</td>
					<td>
						<form:password path="password" cssClass="form-control" placeholder="password" id="password" required="true"/>
						<div id="passwordStrength" class="alert alert-warning">  </div>
					</td>
				</tr>	
				
				<tr>
					<td>
						Confirm Password
					</td>
					<td>
					
			        <input type="password" cssClass="form-control" placeholder="confirm password" id="confirm_password" required="true" onblur="matchPassword();">
			        <div id="confirmPasswordMsg" class="alert alert-warning">  </div> 
					</td>
				</tr>
				
				<tr>
					<td colspan="2">			
							<input type="submit" class="btn btn-lg btn-primary btn-block"
								value="<spring:message text="Register"/>" />
						
					</td>
				</tr>
			</table>	
			</form:form>
		     </div>
		     <div id="signin">
		   <form class="form-signin" action ="${pageContext.request.contextPath}/signin/${doctor.id}" method="post">
		    <h2 class="form-signin-heading">Please sign in</h2>	
		    <h3 style="color: red;">${error_message}</h3>	    
		    <label for="username" class="sr-only">Username</label>
		    <input type="input" id="username" name="username" class="form-control" placeholder="username" required autofocus>
		    <label for="inputPassword" class="sr-only">Password</label>
		    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		    <div class="checkbox">
		      <label>
		        <input type="checkbox" value="remember-me"> Remember me
		      </label>
		    </div>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		  </form>
		     </div>
		</div>
	</div>
</div>