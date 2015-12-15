<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h2>Doctor Registration Page</h2>			
			<c:url var="addAction" value="/registerDoctor" ></c:url>
			<form:form action="${addAction}" commandName="user" method="post">
			<table>		
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