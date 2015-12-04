<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:url var="addAction" value="/updateProfile" ></c:url>
<script type="text/javascript">
$( document ).ready(function() {
	  /* var roleType = $('#role').val();	  
	  if(roleType ==2){
		  $('#specialty').show();
		  $('#expirence').show();
		  
	  } else {
		  $('#specialty').hide();
		  $('#expirence').hide();
	  }
	  
	  if(roleType ==1){
		  $('#enabledRow').show();
	  } else {
		  $('#enabledRow').hide();
	  } */
	  
	 $('input[type=file]').change(function() { 
	    // select the form and submit
	    $('#profilePicForm').submit(); 
	}); 
	
	 $("#profilePicImage").click(function () {
	    $("#profilePic").trigger('click');
	}); 
});
	
</script>

<form name="profilePicForm" id="profilePicForm" action="${pageContext.request.contextPath}/saveProfilePic" method="post" enctype="multipart/form-data">
	
		<img
		src="${pageContext.request.contextPath}/resources/profilepic/${userproflie.id}.jpg"
		class="img-circle" alt="profile"
		style="border: 1px solid grey;" width="130" height="130" id="profilePicImage">
	
  
	<div class="image-upload">											
		<input type="file" name="profilePic" id="profilePic" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Your label here." style="display: none;">
		<!-- <input type="submit" value="upload Pic"/> -->
	</div>

</form>
<form:form action="${addAction}" commandName="userproflie">
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
	
	<tr style="display:none;">
		<td>
			<form:label path="role">
				<spring:message text="role"/>
			</form:label>
		</td>
		<td>
			<form:select id ="role" path="role" cssClass="form-control" required="true" onchange="displayField();">
		        <form:option value="">--Select--</form:option>
		        <form:options items="${userRoles}" itemLabel="code" itemValue="id" />
		    </form:select>
		</td>
	</tr>
	
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
	
		<c:if test="${user.role==2 }">
				<tr id="specialty">
					<td>
						<form:label path="specialty">
							<spring:message text="specialty"/>
						</form:label>
					</td>
					<td>
						<form:input path="specialty" />
					</td>
				</tr>
		</c:if>
		
	
	
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" />
		</td>
	</tr>
	
	<c:if test="${user.role==2 }">
	<tr id="expirence">
		<td>
			<form:label path="expirence">
				<spring:message text="expirence"/>
			</form:label>
		</td>
		<td>
			<form:input path="expirence" />
		</td>
	</tr>
	</c:if>
	
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
			<form:input path="contact" />
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
	<c:if test="${user.role==1 }">
	<tr id="enabledRow">
		<td>
			<form:label path="enabled">
				<spring:message text="enabled"/>
			</form:label>
		</td>
		<td>
			<form:select path="enabled" cssClass="form-control" required="true">
		        <form:option value="">--Select--</form:option>
		        <form:options items="${status}" itemLabel="label" itemValue="code" />
		    </form:select>
		</td>
	</tr>
	</c:if>
	<tr>
		<td colspan="2">
			
			
				<input type="submit"
					value="<spring:message text="Save User"/>" />
			
		</td>
	</tr>
</table>	
</form:form>