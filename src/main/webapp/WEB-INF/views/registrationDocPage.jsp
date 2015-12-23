<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getUserValidation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/zxcvbn.js"></script>
<script>
$(document).ready(function(){
	$('#confirmPasswordMsg').hide();
	$('#passwordStrength').hide();
	$('#password').keyup(function() {
		  var textValue = $(this).val();
				
				$('#passwordStrength').hide();
			if(textValue.length >= 0){			
			  var result = zxcvbn(textValue);
			  console.log(result);
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
</script>

<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h2>Doctor Registration Page</h2>			
			<c:url var="addAction" value="/registerDoctor" ></c:url>
			<form:form action="${addAction}" commandName="user" method="post" onsubmit="return validateForm();">			
			<form:hidden name="enabled" path="enabled" value="1"/>
			<form:hidden name="profilePicUrl" path="profilePicUrl" value="doctorProfilePic.jpg"/>
			<form:hidden name="userRole" path="userRole.id" value="2"/>
			<table>		
				<tr>
					<td>
						<form:label path="email">
							<spring:message text="email"/>
						</form:label>
					</td>
					<td>      
						<form:input path="email" cssClass="form-control" placeholder="Email address" required="true"   onblur="validEmail(this.value);"/>
						
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
					<div class="row">
			        <div class="col-lg-12 control-label">
									<form:password path="password" cssClass="form-control" placeholder="password" id="password" required="true"/>
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
	</div>
</div>