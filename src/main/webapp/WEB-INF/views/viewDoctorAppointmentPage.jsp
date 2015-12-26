<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Dr.  ${doctor.username}</h3>
			<img src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${doctor.fullname}</div>
			<div class="col-2">specialty: ${doctor.specialization}</div>
			<div class="col-2">age: ${doctor.age}</div>
			<div class="col-2">expirence: ${doctor.expirence}</div>
			<div class="col-2">contact: ${doctor.contact}</div>			
			
			<p><i>Appointment Slots :</i>
			<div class="appointment">
					<ul>
					<c:forEach items="${doctor.allBooking}" var="booking">
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
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirm_${doctor.id}" data-whatever="@getAppointment/${doctor.id }">Get Appointment</button>
			&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
			<!-- Popup -->
								<div id="confirm_${doctor.id}" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								        <h4 class="modal-title" id="myModalLabel">Book an Appointment</h4>
								        <h5>Click a time below to book an appointment.</h5>
								      </div>
								      <div class="modal-body">
								       	<img src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
								       	Dr.${doctor.username}
								       	${doctor.clinicName}
								       		<div class="appointment">
								       		<p>Appointment Schedule:</p>
												<ul>
												<c:forEach items="${doctor.allBooking}" var="booking">
													<li class=".col-md-4">
														<c:if test="${booking.bookingStatus.code==1}">
														<a type="button" class="btn btn-sm btn-success" href="getAppointment/${doctor.id }">${booking.datetimeStart}</a>						
														</c:if>
													<%-- [${booking.datetimeStart} - ${booking.datetimeEnd}] ${booking.bookingStatus.name } --%>
													</li>
												</c:forEach>
												</ul>
											</div>
																	
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