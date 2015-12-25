<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:url var="addAction" value="updateProfile" ></c:url>
<script type="text/javascript">
$( document ).ready(function() {
	 	  
	init(); 
});

function init(){
	
	 $('input[type=file]').change(function() { 
		    // select the form and submit
		    $('#profilePicForm').submit(); 
		}); 
		
	 $(".profilePicImage").click(function () {		 
	    $("#profilePic").trigger('click');
	});
		 
}
	
</script>

<div class="container container-table" onload="init();">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
        <!-- form for Profile Picture -->
		<form name="profilePicForm" id="profilePicForm" action="${pageContext.request.contextPath}/saveProfilePic" method="post" enctype="multipart/form-data">
			
				<img
				src="${pageContext.request.contextPath}/resources/profilepic/${userprofile.profilePicUrl}"
				class="profilePicImage img-circle" alt="profile"
				style="border: 1px solid grey;" width="130" height="130" id="profilePicImage">
			
		  
			<div class="image-upload">											
				<input type="file" name="profilePic" id="profilePic" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Your label here." style="display: none;">
				<!-- <input type="submit" value="upload Pic"/> -->
			</div>
		
		</form>

		<form:form action="${addAction}" commandName="userproflie">
		<form:hidden name="enabled" path="enabled" value="1"/>	
		<%-- <c:forEach items="${userproflie.userRoles}" var="userRole">
			<form:hidden name="userRoles" path="userRoles" value="${userRole.id}"/>			
		</c:forEach> --%>
			<table>
				<c:if test="${!empty userproflie.id}">
				<tr>
					<td>
						<form:label path="id">
							<spring:message text="ID"/>
						</form:label>
					</td>
					<td>			
						<form:input path="id" readonly="true" size="8"  disabled="true" />
						<form:hidden path="id" />
					</td> 
				</tr>
				</c:if>				
	
				<tr>
					<td>
						<form:label path="fullname">
							<spring:message text="Full Name"/>
						</form:label>
					</td>
					<td>
						<form:input path="fullname" />
					</td> 
				</tr>	
		
					
	
				<tr>
					<td>
						<form:label path="age">
							<spring:message text="age"/>
						</form:label>
					</td>
					<td>
						<form:input path="age" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</td>
				</tr>	
	
				
	
				<tr>
					<td>
						<form:label path="email">
							<spring:message text="email"/>
						</form:label>
					</td>
					<td>
						<form:input path="email" />
					</td>
				</tr>
	
				<tr>
					<td>
						<form:label path="contact">
							<spring:message text="contact"/>
						</form:label>
					</td>
					<td>
						<form:input path="contact" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					</td>
				</tr>
	
	
				<tr>
					<td>
						<form:label path="username">
							<spring:message text="username"/>
						</form:label>
					</td>
					<td>
						<form:input path="username" />
					</td>
				</tr>
	
	
				<tr>
					<td>
						<form:label path="password">
							<spring:message text="password"/>
						</form:label>
					</td>
					<td>
						<form:password path="password" />
					</td>
				</tr>	
		
				<tr>
					<td>
						<form:label path="summary">
							<spring:message text="summary"/>
						</form:label>
					</td>
					<td>
						<form:textarea path="summary" />
					</td>
				</tr>
	
				<tr>
					<td colspan="2">
						
						
							<input type="submit" class="btn btn-primary btn-sm"
								value="<spring:message text="Save User"/>" />
						
					</td>
				</tr>
</table>	
</form:form>
</div>
</div>
</div>