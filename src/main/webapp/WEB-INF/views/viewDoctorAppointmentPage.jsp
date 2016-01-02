<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Dr.  ${doctor.username}</h3>
			<img src="<spring:message code="s3bucket.url" />${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${doctor.fullname}</div>
			<div class="col-2">specialty: ${doctor.specialization}</div>
			<div class="col-2">age: ${doctor.age}</div>
			<div class="col-2">expirence: ${doctor.expirence}</div>
			<div class="col-2">contact: ${doctor.contact}</div>			
			You have choose: ${selectSlot.datetimeStart}
			<p><i>Available Appointment Slots :</i>
			<div class="appointment">
					<ul>
					<c:forEach items="${doctor.allBooking}" var="booking">
						<li class=".col-md-4">
							<c:choose>															
							    <c:when test="${booking.bookingStatus.id == 1}">
							       <span class="btn btn-sm btn-info disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>
							    <c:when test="${booking.bookingStatus.id == 2}">
							       <span class="btn btn-sm btn-danger disabled" title="${booking.bookingStatus.name}"><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></span> 
							    </c:when>
							   
							    <c:otherwise>
							     <a type="button" class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/patient/getAppointment/${doctor.id }/${booking.datetimeStartInLong}" ><fmt:formatDate value="${booking.datetimeStart}" pattern="hh:mm a" /></a>
							    </c:otherwise>
							</c:choose>	
						<%-- [${booking.datetimeStart} - ${booking.datetimeEnd}] ${booking.bookingStatus.name } --%>
						</li>
					</c:forEach>
					</ul>
				</div>
			
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/patient/saveAppointment/${doctor.id }/${selectSlot.datetimeStartInLong}">Save Selected Appointment</a>
			&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
				
		</div>
	</div>
</div>