<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<link rel="icon" type="image/png" href="https://s3.amazonaws.com/getdocprofilepic/favicon.png">

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"> 
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
