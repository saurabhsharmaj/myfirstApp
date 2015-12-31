<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
function setAction(appId, status){
	console.log(appId +" - "+status);
	var url = '/MyFirstApp/doctor/approved/'+appId+"/"+status;
	$('#changeStatus-'+appId).attr("href", url);	
}
</script>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
        hhhh
        <table style="width:600px;">
        <thead>
        	<th>ID</th>
        	<th>Patient Name</th>
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
					        	<td>${appointment.usersByPatientId.username}</td>
					        	<td>
					        		<fmt:formatDate value="${appointment.datetimeStart}" pattern="dd/mm/yyyy hh:mm a" />
					        	</td>
					        	<td title="${appointment.bookingStatus.name}">${appointment.bookingStatus.id}-${appointment.bookingStatus.name}
					        		<form:select path="bookingStatus" cssClass="form-control" items="${bookingStatus}" itemValue="id" itemLabel="name" onchange="setAction('${appointment.id }',this.value);">\
										<form:option value="0"> --SELECT--</form:option>
									</form:select>
							
					        		<a id="changeStatus-${appointment.id }" href="#" class="btn btn-primary" style="float:right;">Save Status</a>
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