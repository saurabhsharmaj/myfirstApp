<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:url var="addAction" value="/updateProfile" ></c:url>
<form:form action="${addAction}" commandName="userproflie">
<table>
	<c:if test="${!empty user.fullname}">
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
			<form:label path="role">
				<spring:message text="role"/>
			</form:label>
		</td>
		<td>
			<form:select path="role" cssClass="form-control" required="true" onchange="displayField();">
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
			<form:input path="password" />
		</td>
	</tr>
	
	<tr>
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
	<tr>
		<td colspan="2">
			
			
				<input type="submit"
					value="<spring:message text="Save User"/>" />
			
		</td>
	</tr>
</table>	
</form:form>