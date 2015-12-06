<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
       	 		<form:form id ="searchForm" action="${pageContext.request.contextPath}/admin/searchUser">
       	 	<div class="input-group">
	  				<input name="keyword" type="text" class="form-control input-lg" placeholder="Search doctors/ patients" aria-describedby="basic-addon2">

					<span class="input-group-addon" id="basic-addon2">
						<a href="#" onclick="document.getElementById('searchForm').submit();">Search</a>				
					</span>				
					
			</div>
  				</form:form>
		</div>
    </div>
</div>