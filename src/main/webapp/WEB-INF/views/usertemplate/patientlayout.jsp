<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"> 
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>	
	<div class="wrapper row1">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div class="wrapper row2">
		<div id="container" class="clear">
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
	<div class="wrapper row3">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
