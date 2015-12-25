<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Doctor</h3>
			<img src="${pageContext.request.contextPath}/resources/profilepic/${profile.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${profile.fullname}</div>
			<div class="col-2">specialty: ${profile.specialization}</div>
			<div class="col-2">age: ${profile.age}</div>
			<div class="col-2">experience: ${profile.expirence}</div>
			<div class="col-2">contact: ${profile.contact}</div>			
			<p><i>Appointment Slots :</i>
			<div class="appointment">
					<ul>
					<c:forEach items="${profile.allBooking}" var="booking">
						<li class=".col-md-4">
							<c:if test="${booking.bookingStatus.code==1}">
							<button type="button" class="btn btn-success">${booking.datetimeStart}</button>
							</c:if>
						<%-- [${booking.datetimeStart} - ${booking.datetimeEnd}] ${booking.bookingStatus.name } --%>
						</li>
					</c:forEach>
					</ul>
				</div>
			</p>
			<a href="${pageContext.request.contextPath}/getAppointment//${profile.id }/" class="btn btn-default">Get Appointment</a>&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
		</div>
	</div>
</div>