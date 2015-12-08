<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
        <table style="width:600px;">
        <thead>
        	<th>ID</th>
        	<th>Patient Name</th>        	
        	<th>Role</th>
        	<th>Status</th>
        	<th>Action</th>
        </thead>
        <tbody>      
			<c:choose>
			 <c:when test="${fn:length(userList) > 0}">			 	
				<c:forEach items="${userList}" var="user">
			   		<div class="list-item">
				   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>							
							<tr>
								
					        	<td>${user.id }</td>
					        	<td>${user.username}</td>
					        	<td>${user.userRoles.code}</td>
					        	<td>${user.enabled}</td>
					        	<td><a href="#">Edit</a> &nbsp; <a href="#">Delete</a> </td>
					        </tr>	
			   		</div>		   		
		   		</c:forEach> 
			   	</c:when>
			   	<c:otherwise>
			   		<tr><td colspan="4">
			   			No Result found.
			   			</td></tr>
			   	</c:otherwise> 
		   	</c:choose>
	   	 </tbody>
        </table>
		</div>
	</div>
</div>