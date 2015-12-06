<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">

$( document ).ready(function() {

	/* $('#profilePic').onchange = function() {
		debugger
	    $('#profilePicForm').submit();
	}; */
	
	/* $('input[type=file]').change(function() { 
	    // select the form and submit
	    $('#profilePicForm').submit(); 
	}); */
	
	/* $("#profilePicImage").click(function () {
	    $("#profilePic").trigger('click');
	}); */
	
});

</script>

<form name="profilePicForm" id="profilePicForm" action="saveProfilePic" method="post" enctype="multipart/form-data">
	
		<img
		src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}"
		class="img-circle" alt="profile"
		style="border: 1px solid grey;" width="130" height="130" id="profilePicImage">
	
  
	<div class="image-upload">											
		<input type="file" name="profilePic" id="profilePic" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Your label here.">
		<input type="submit" value="upload Pic"/>
	</div>

</form>