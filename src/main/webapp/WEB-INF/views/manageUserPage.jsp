<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
tr {
    height: 32px;
}
</style>
<script>


	function deleteUser(ref,id){
	 
		
			
	    $('#confirm').modal({ backdrop: 'static', keyboard: true })
	        .one('click', '#delete', function(e) {	 
	        	e.preventDefault();
	        	$.ajax({
	                url: "/MyFirstApp/admin/deleteUser/"+id+"/"
	            }).then(function(data) {
	            	$('#message').html(data);
	            	ref.closest('tr').remove();
	            	$('#confirm').modal('hide');
	            });
	        }); 
	        
	}	

</script>

<!-- Modal -->
<div id="confirm" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Confirm</h4>
      </div>
      <div class="modal-body">
        Are you sure?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="delete">Delete</button>
      </div>
    </div>
  </div>
</div>
			
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4 col-sm-1">
       
       
       	<div id="message"></div>
        <table style="width:600px;">
        <thead>
        	<th>ID</th>
        	<th>Patient Name</th>        	
        	<th>Role</th>
        	<th>Status</th>
        	<th>Action</th>
        </thead>
        <tbody>      
			<c:choose>
			 <c:when test="${fn:length(userList) > 0}">			 	
				<c:forEach items="${userList}" var="user">
			   		<div class="list-item">
				   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>							
							<tr>
								
					        	<td>${user.id }</td>
					        	<td>${user.username}</td>
					        	<td>
					        		<c:forEach items="${user.userRoles}" var="userRole">
										${userRole.code}
									</c:forEach>
					        	</td>
					        	<td>${user.enabled}</td>
					        	<td><a href="/MyFirstApp/admin/editUser/${user.id }" class="btn btn-primary btn-sm"  >Edit</a> &nbsp; 
					        	<a href="#" class="btn btn-primary btn-sm" onclick="deleteUser(this,'${user.id }')">Delete</a>
					        	</td>
					        </tr>	
			   		</div>		   		
		   		</c:forEach> 
			   	</c:when>
			   	<c:otherwise>
			   		<tr><td colspan="4">
			   			No Result found.
			   			</td></tr>
			   	</c:otherwise> 
		   	</c:choose>
	   	 </tbody>
        </table>       	

			
		</div>
	</div>
</div>