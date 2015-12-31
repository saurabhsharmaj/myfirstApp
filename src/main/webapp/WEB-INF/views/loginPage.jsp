<title>${title}</title>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
		  <form class="form-signin" action ="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		    <h2 class="form-signin-heading">Please sign in</h2>	
		    <h3 style="color: red;">${error_message}</h3>	    
		    <label for="username" class="sr-only">Username</label>
		    <input type="input" id="username" name="username" class="form-control" placeholder="username" required autofocus>
		    <label for="inputPassword" class="sr-only">Password</label>
		    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		    <div class="checkbox">
		      <label>
		        <a href="forgotpassword">forgot password</a>
		      </label>
		      <label>
		        <input type="checkbox" value="remember-me"> Remember me
		      </label>
		    </div>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		  </form>
 		</div>
 	</div>
 </div>
 		
