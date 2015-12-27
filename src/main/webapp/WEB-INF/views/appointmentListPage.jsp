<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
        <table style="width:600px;">
        <thead>
        	<th>ID</th>
        	<th>Doctor Name</th>
        	<th>Date</th>
        	<th>Status</th>
        </thead>
        <tbody>      
			<c:choose>
			 <c:when test="${fn:length(appointmentList) > 0}">			 	
				<c:forEach items="${appointmentList}" var="appointment">
			   		<div class="list-item">
				   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>							
							<tr>
					        	<td>${appointment.id }</td>
					        	<td>Dr. ${appointment.usersByDoctorId.username}</td>
					        	<td><fmt:formatDate value="${appointment.datetimeStart}" pattern="dd/MM/yyyy hh:mm a" /></td>
					        	<td>
					        		
					        		<c:if test="${appointment.bookingStatus.id==1 }">
					        			<span class="label label-success">${appointment.bookingStatus.name }</span>
					        		</c:if>
					        		
					        		<c:if test="${appointment.bookingStatus.id==2}">
					        			<span class="label label-danger">${appointment.bookingStatus.name }</span>
					        		</c:if>
					        	</td>
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