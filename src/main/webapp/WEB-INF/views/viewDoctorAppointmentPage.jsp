<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Dr.  ${doctor.username}</h3>
			<img src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${doctor.fullname}</div>
			<div class="col-2">specialty: ${doctor.specialty}</div>
			<div class="col-2">age: ${doctor.age}</div>
			<div class="col-2">expirence: ${doctor.expirence}</div>
			<div class="col-2">contact: ${doctor.contact}</div>			
			
			<a href="${pageContext.request.contextPath}/patient/saveAppointment/${doctor.id }" class="btn btn-default">Get Appointment</a>&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
		</div>
	</div>
</div>