<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!--  This page display when patient search for any doctor. -->
	

<link href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>

<script>
$(document).ready(function() {
    $('#example').DataTable({
    		"lengthMenu": [[1, 3, 5, -1], [1, 3, 5, "All"]]	
    		,"iDisplayLength":3
    		,"bFilter": false,
    		"bSearchable":false,
    		"bInfo":false
    		,"bLengthChange":false
    		//,"sPaginationType": "full_numbers"
    });
} );
</script>
<!-- demo -->
	   <c:choose>
		   <c:when test="${fn:length(searchResults) > 0}">
		   <div class="fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"></div>
			<form:form id ="searchDoctorForm" action="searchDoctor" style="padding: 20px;">
	       	 	<div class="input-group">
		  				<input name="keyword" type="text" class="form-control input-lg" placeholder="Search your Doctor" aria-describedby="basic-addon2">
	
						<span class="input-group-addon" id="basic-addon2">
							<a href="#" onclick="document.getElementById('searchDoctorForm').submit();">Search</a>				
						</span>				
						
				</div>
  			</form:form>
  				
		   <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
		   <thead>
		        <tr>
		            <th>List of Doctor</th>            
		        </tr>
		    </thead>
		   <tbody>
		   
		   	<c:forEach items="${searchResults}" var="doctor">
		   		<tr><td>			   						
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"
								style="padding: 0px 5px 0px 5px;">
								<a href="${pageContext.request.contextPath}/doctorDetail/${doctor.id }">						
									  	<img src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
								</a>
								
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 text-left" style="padding: 3px;">
							
								<p>${doctor.username}</p>
								<p><i>Contact No.:</i><b>${doctor.contact}</b></p>
								<p><i>Summary :</i><b>${doctor.summary}</b></p>
								<p><i>Appointment Slots :</i>
								<div class="appointment">
										<ul>
										<c:forEach items="${doctor.allBooking}" var="booking">
											<li class=".col-md-4">
												<c:if test="${booking.bookingStatus.code==1}">
												<a type="button" class="btn btn-sm btn-success" href="getAppointment/${doctor.id }/${user.id}" >${booking.datetimeStart}</a>
												</c:if>
											<%-- [${booking.datetimeStart} - ${booking.datetimeEnd}] ${booking.bookingStatus.name } --%>
											</li>
										</c:forEach>
										</ul>
									</div>
								</p>
								<div class="active-stars-yellow space"></div>
								<p style="font-size: 11px;">${doctor.expirence}</p>
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"
								style="padding: 5px 5px;">								
						          <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirm_${doctor.id}" data-whatever="@getAppointment/${doctor.id }/${user.id}">Book Online</button>
						          &nbsp;
								<a href="${pageContext.request.contextPath}/doctorDetail/${doctor.id }">View Doctor</a>
								
							</div>
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
														<a type="button" class="btn btn-sm btn-success" href="getAppointment/${doctor.id }/${user.id}" >${booking.datetimeStart}</a>						
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
				</td></tr>
		   	</c:forEach> 
		   	</tbody>
		   	</table>
		   	</c:when>
		   	<c:otherwise>
		   			No Result found.
		   	</c:otherwise> 
	   	</c:choose>    
   </div>	
               
</div>