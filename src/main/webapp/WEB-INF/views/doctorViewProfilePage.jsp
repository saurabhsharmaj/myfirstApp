<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="addAction" value="/updateProfile" ></c:url>
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
				src="<spring:message code="s3bucket.url" />${userprofile.profilePicUrl}"
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
			<form:hidden name="userRoles" path="userRole.id" value="2"/>
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
						${userproflie.fullname}
					</td> 
				</tr>	
		
				<tr>
					<td>
						<form:label path="specialization">
							<spring:message text="specialty"/>
						</form:label>
					</td>
					<td>
						<form:select path="specialization" cssClass="form-control" items="${specializationList}" itemValue="id" itemLabel="name" disabled="true"/>
					</td>
				</tr>	
	
				<tr>
					<td>
						<form:label path="age">
							<spring:message text="age"/>
						</form:label>
					</td>
					<td>
						${userproflie.age}
					</td>
				</tr>	
	
				<tr id="expirence">
					<td>
						<form:label path="expirence">
							<spring:message text="expirence"/>
						</form:label>
					</td>
					<td>
						${userproflie.expirence}
					</td>
				</tr>
	
				<tr>
					<td>
						<form:label path="email">
							<spring:message text="email"/>
						</form:label>
					</td>
					<td>
						${userproflie.email}
					</td>
				</tr>
	
				<tr>
					<td>
						<form:label path="contact">
							<spring:message text="contact"/>
						</form:label>
					</td>
					<td>
						${userproflie.contact}
					</td>
				</tr>
	
	
				<tr>
					<td>
						<form:label path="username">
							<spring:message text="username"/>
						</form:label>
					</td>
					<td>
						${userproflie.username}
					</td>
				</tr>
	
	
					
		
				<tr>
					<td>
						<form:label path="summary">
							<spring:message text="summary"/>
						</form:label>
					</td>
					<td>
						${userproflie.summary}
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="appointmentSchedule">
							<spring:message text="Appointment Schedule"/>
						</form:label>
					</td>
					<td>						 
						 <div class="row">
						  <div class="col-*-*">Working Days: ${userproflie.appointmentSchedule.workingDays}</div>
						</div>
						<div class="row">
						  <div class="col-*-*">Slot Size: ${userproflie.appointmentSchedule.slotSize}</div>  
						</div>
						
						 <div class="row">
						  <div class="col-*-*">Start Time: <fmt:formatDate value="${userproflie.appointmentSchedule.startTime}" pattern="hh:mm a" /></div>
						</div>
						<div class="row">
						  <div class="col-*-*">End Time:<fmt:formatDate value="${userproflie.appointmentSchedule.endTime}" pattern="hh:mm a" /></div>  
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						
						
							<a href="${pageContext.request.contextPath}/doctor/editViewProfile">Edit Profile</a>
						
					</td>
				</tr>
</table>	
</form:form>
</div>
</div>
</div>