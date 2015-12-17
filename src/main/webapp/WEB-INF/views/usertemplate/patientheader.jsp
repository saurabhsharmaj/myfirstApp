<div class="blog-masthead">
   <div class="container">
     <nav class="blog-nav">
     	<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
			<a href="${pageContext.request.contextPath}/user/">
				<img
				src="${pageContext.request.contextPath}/resources/images/getDoc.png"
				alt="logo" class="logo" height="40" width="140">
			</a>
		</div>
       <a class="blog-nav-item" href="${pageContext.request.contextPath}/user/appointmentlist">My Appointments</a>      
       <a class="blog-nav-item" href="${pageContext.request.contextPath}/user/myprofile">my profile</a>
       <ul class="nav navbar-nav navbar-right">
       	<img
		src="${pageContext.request.contextPath}/resources/profilepic/${userprofile.profilePicUrl}"
		class="profilePicImage img-circle" alt="profile"
		style="border: 1px solid grey;" width="50" height="50" id="profilePicImage">
        ${userprofile.username},<a class="blog-nav-item" href="${pageContext.request.contextPath}/logout">logout</a>
       </ul>
     </nav>
   </div>
 </div>