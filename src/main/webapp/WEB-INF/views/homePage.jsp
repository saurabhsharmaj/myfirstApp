<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My First APP</title>
 
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="blog-masthead">
   <div class="container">
     <nav class="blog-nav">         
       <a class="blog-nav-item" href="login">Sign In</a>
       <a class="blog-nav-item" href="register">Register</a>
        <a class="blog-nav-item" href="addUser">Add User</a>
     </nav>
   </div>
 </div>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
       	 	<div class="input-group">
  				<input type="text" class="form-control input-lg" placeholder="Search your Doctor" aria-describedby="basic-addon2">
  				<span class="input-group-addon" id="basic-addon2"><a href="#">Search</a></span>
			</div>
		</div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>