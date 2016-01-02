<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Doctor(Wl)</h3>
			<img src="<spring:message code="s3bucket.url" />${profile.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${profile.fullname}</div>
			<div class="col-2">specialty: ${profile.specialization}</div>
			<div class="col-2">age: ${profile.age}</div>
			<div class="col-2">experience: ${profile.expirence}</div>
			<div class="col-2">contact: ${profile.contact}</div>	
			<div class="col-2">summary: ${profile.summary}</div>		
			<p><i>Appointment Slots :</i>
			<div class="appointment">
					<ul>
					<c:forEach items="${profile.allBooking}" var="booking">
						<li class=".col-md-4">
							<c:choose>
								
							    <c:when test="${booking.bookingStatus.id == 1}">
							       <span class="btn btn-sm btn-info disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>
							    <c:when test="${booking.bookingStatus.id == 2}">
							       <span class="btn btn-sm btn-danger disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>							    
							    
							    <c:otherwise>
							     <a type="button" class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/getAppointment/${profile.id }/${booking.datetimeStartInLong}" ><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></a>
							    </c:otherwise>
							</c:choose>	
						<%-- [${booking.datetimeStart} - ${booking.datetimeEnd}] ${booking.bookingStatus.name } --%>
						</li>
					</c:forEach>
					</ul>
				</div>
			
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirm_${profile.id}" data-whatever="@getAppointment/${profile.id }/${user.id}">Get Appointment</button>
			&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
		
		
						<!-- Popup -->
								
			<div id="confirm_${profile.id}" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">Book an Appointment</h4>
			        <h5>Click a time below to book an appointment.</h5>
			      </div>
			      <div class="modal-body">
			       	<div class="container-fluid">
			       	<div class="row">
					  <div class="col-sm-6">
					  		<div class="row">
							  <div class="col-*-1">
							  	<img src="<spring:message code="s3bucket.url" />${profile.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
							  </div>
							  <div class="col-*-1">Dr.${profile.username}</div>
							</div>
							
							<div class="row">
							  <div class="col-*-1">
								<p><i>Contact No.:</i><b>${profile.contact}</b></p>
							  </div>
							  <div class="col-*-1">
								<p><i>Summary :</i><b>${profile.summary}</b></p>
							  </div>
						    </div>
			
					  		<div class="row">
							  <div class="col-*-1">
			       				<p>(${profile.clinicName},${profile.address})</p>
			       			  </div>
			       		   </div>
					  </div>
					  <div class="col-sm-2">
					  		<ul class="list-group row">
							<c:forEach items="${profile.allBooking}" var="booking">
								<li class="list-group-item col-xs-12 borderless">
									<c:choose>
								
							    <c:when test="${booking.bookingStatus.id == 1}">
							       <span class="btn btn-sm btn-info disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>
							    <c:when test="${booking.bookingStatus.id == 2}">
							       <span class="btn btn-sm btn-danger disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>							    
							    
							    <c:otherwise>
							     <a type="button" class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/getAppointment/${profile.id }/${booking.datetimeStartInLong}" ><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></a>
							    </c:otherwise>
							</c:choose>													
								</li>
							</c:forEach>
							</ul>
					  </div>
					</div>							
			      </div><!-- End container-fluid -->
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			    </div>
			  </div>
							<!-- End popup  -->
		</div>
	</div>
</div>