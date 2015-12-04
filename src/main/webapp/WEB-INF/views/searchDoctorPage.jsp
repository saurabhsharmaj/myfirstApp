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
<div id="demo">
   
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
               <a role="menuitem" tabindex="-1" href="#" data-number="3">3 per page</a>
            </li>

            <li role="presentation">
               <a role="menuitem" tabindex="-1" href="#" data-number="5" data-default="true">5 per page</a>
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
   
   <!-- HTML data -->   
   <div class="list">
      
      <!-- item 1 -->
      <div class="list-item">	
         1
      </div>
      
      <!-- item 2 -->
      <div class="list-item">	
        2
      </div>
      
      <!-- item 2 -->
      <div class="list-item">	
        3
      </div>
      
      <!-- item 2 -->
      <div class="list-item">	
        4
      </div>
      
      
      <!-- item 2 -->
      <div class="list-item">	
        5
      </div>
      
      <!-- item 2 -->
      <div class="list-item">	
        6
      </div>      
     
      
   </div>	
               
</div>