<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        <table style="width:600px;">
        <thead>
        	<th>ID</th>
        	<th>Patient Name</th>
        	<th>Date</th>
        	<th>Status</th>
        </thead>
        <tbody>      
			<c:choose>
			 <c:when test="${fn:length(patientList) > 0}">			 	
				<c:forEach items="${patientList}" var="patient">
			   		<div class="list-item">
				   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>							
							<tr>
					        	<td>${patient.id }</td>
					        	<td>${patient.usersByPatientId.username}</td>
					        	<td><fmt:formatDate value="${patient.datetimeStart}" pattern="dd/MM/yyyy hh:mm a" /></td>
					        	<td title="${patient.bookingStatus.name}">
					        		<form:select path="bookingStatus" cssClass="form-control" style="display:inline-block;" items="${bookingStatus}" itemValue="id" itemLabel="name" onchange="setAction('${patient.id }',this.value);"/>
					        		<a id="changeStatus-${patient.id }" href="#" class="btn btn-primary" style="float:right;">Save Status</a>
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