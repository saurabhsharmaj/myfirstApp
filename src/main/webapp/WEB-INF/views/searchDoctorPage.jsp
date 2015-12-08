<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--  This page display when patient search for any doctor. -->
<link href="${pageContext.request.contextPath}/resources/css/jplist.bootstrap-demo.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jplist.core.min.js"></script>		
<script src="${pageContext.request.contextPath}/resources/js/jplist.bootstrap-pagination-bundle.min.js"></script>
<script>
$('document').ready(function(){

   $('#demo').jplist({				
      itemsBox: '.list' 
      ,itemPath: '.list-item' 
      ,panelPath: '.jplist-panel'	
   });
   
});
</script>
<!-- demo -->
<div id="demo" style="overflow: auto;">
   <!-- HTML data --> 
   <div class="list">
	   <c:choose>
		   <c:when test="${fn:length(searchResults) > 0}">
		   <!-- panel -->
			   <div class="jplist-panel">			      
			      <!-- bootstrap pagination control -->
			      <ul 
			          class="pagination pull-left jplist-pagination"
			          data-control-type="boot-pagination" 
			          data-control-name="paging" 
			          data-control-action="paging"
			          data-range="4"
			          data-mode="google-like">
			      </ul>
			      
			      <!-- pagination info label -->
			      <div 
			         class="pull-left jplist-pagination-info"
			         data-type="<strong>Page {current} of {pages}</strong><br/> <small>{start} - {end} of {all}</small>" 
			         data-control-type="pagination-info" 
			         data-control-name="paging" 
			         data-control-action="paging"></div>
			         
			      <!-- items per page dropdown -->
			      <div 
			         class="dropdown pull-left jplist-items-per-page"
			         data-control-type="boot-items-per-page-dropdown" 
			         data-control-name="paging" 
			         data-control-action="paging">
			
			         <button 
			            class="btn btn-primary dropdown-toggle" 
			            type="button" 
			            data-toggle="dropdown" 
			            id="dropdown-menu-1"
			            aria-expanded="true">					
			            <span data-type="selected-text">Items per Page</span>
			            <span class="caret"></span>						
			         </button>
			
			         <ul class="dropdown-menu" role="menu" aria-labelledby="dropdown-menu-1">
			
			            <li role="presentation">
			               <a role="menuitem" tabindex="-1" href="#" data-number="3" data-default="true">3 per page</a>
			            </li>
			
			            <li role="presentation">
			               <a role="menuitem" tabindex="-1" href="#" data-number="5" >5 per page</a>
			            </li>
			            
			            <li role="presentation">
			               <a role="menuitem" tabindex="-1" href="#" data-number="10">10 per page</a>
			            </li>
			
			            <li role="presentation" class="divider"></li>
			
			            <li role="presentation">
			               <a role="menuitem" tabindex="-1" href="#" data-number="all">View All</a>
			            </li>
			         </ul>						  
			      </div>
			   </div>	
		   	<c:forEach items="${searchResults}" var="doctor">
		   		<div class="list-item">
			   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">&nbsp;</div>							
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 box-border">
							<div class="col-lg-1 col-md-1 col-sm-12 col-xs-12"
								style="padding: 0px 5px 0px 5px;">
								<a href="${pageContext.request.contextPath}/doctorDetail/${doctor.id }">						
									  	<img src="${pageContext.request.contextPath}/resources/profilepic/${doctor.profilePicUrl}" class="img-rounded text-center" width="50" height="50">
								</a>
								
							</div>
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 text-left" style="padding: 3px;">
							
								<p>${doctor.username}</p>
								<p><i>Contact No.:</i><b>${doctor.contact}</b></p>
								<div class="active-stars-yellow space"></div>
								<p style="font-size: 11px;">${doctor.expirence}</p>
							</div>
							
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center"
								style="padding: 5px 5px;">
								<a href="${pageContext.request.contextPath}/doctorDetail/${doctor.id }">View Doctor</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/saveAppointment/${doctor.id }">Get Appointment</a>
							</div>
						</div>	   			
		   		</div>
		   		
		   	</c:forEach> 
		   	</c:when>
		   	<c:otherwise>
		   			No Result found.
		   	</c:otherwise> 
	   	</c:choose>    
   </div>	
               
</div>