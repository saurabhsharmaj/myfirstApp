<div class="container container-table">
	<div class="row vertical-center-row">
		<div class="text-center col-md-4 col-md-offset-4 col-sm-1">
			<h3>View your Doctor</h3>
			<img src="${pageContext.request.contextPath}/resources/profilepic/${profile.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
			<div class="col-2">FullName: ${profile.fullname}</div>
			<div class="col-2">specialty: ${profile.specializationId}</div>
			<div class="col-2">age: ${profile.age}</div>
			<div class="col-2">experience: ${profile.expirence}</div>
			<div class="col-2">contact: ${profile.contact}</div>			
			
			<a href="${pageContext.request.contextPath}/patient/saveAppointment/${profile.id }" class="btn btn-default">Get Appointment</a>&nbsp;<a class="btn btn-default" href="#" onclick="window.history.go(-1); return false;">back</a>
		</div>
	</div>
</div>