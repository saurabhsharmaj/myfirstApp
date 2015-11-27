<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<jsp:include page="header.jsp"/>
<body>

<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
	<h2>Register User</h2>
<c:url var="addAction" value="/user/signup" ></c:url>

<form:form action="${addAction}" commandName="user">
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
			<form:select path="role" id = "role" cssClass="form-control" required="true">
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
			<form:input path="fullname" cssClass="form-control" placeholder="Full Name"/>
		</td> 
	</tr>
	<tr id="specialty">
		<td>
			<form:label path="specialty">
				<spring:message text="specialty"/>
			</form:label>
		</td>
		<td>
			<form:input path="specialty" cssClass="form-control" placeholder="Speciality" />
		</td>
	</tr>
	
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" id="age" cssClass="form-control" placeholder="Age" />
		</td>
	</tr>
	
	<tr id="expirence">
		<td>
			<form:label path="expirence">
				<spring:message text="expirence"/>
			</form:label>
		</td>
		<td>
			<form:input path="expirence" cssClass="form-control" placeholder="Expirence" />
		</td>
	</tr>
	
	
	<tr>
		<td>
			<form:label path="email">
				<spring:message text="email"/>
			</form:label>
		</td>
		<td>
			<form:input path="email" cssClass="form-control" placeholder="Email address" />
		</td>
	</tr>
	
	<tr>
		<td>
			<form:label path="contact">
				<spring:message text="contact"/>
			</form:label>
		</td>
		<td>
			<form:input path="contact" cssClass="form-control" placeholder="Contact Number" />
		</td>
	</tr>
	
	
	<tr>
		<td>
			<form:label path="username">
				<spring:message text="username"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" cssClass="form-control" placeholder="Username" required="true"/>
		</td>
	</tr>
	
	
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="password"/>
			</form:label>
		</td>
		<td>
			<form:password path="password" cssClass="form-control" placeholder="password" required="true"/>
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
				<input type="submit" class="btn btn-lg btn-primary btn-block"
					value="<spring:message text="Save User"/>" />
			
		</td>
	</tr>
</table>	
</form:form>
</div>
</div>
</div>
</body>
</html>
