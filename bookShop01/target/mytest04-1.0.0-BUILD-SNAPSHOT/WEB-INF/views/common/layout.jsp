<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("utf-8");
%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" media="screen">
<link href="${contextPath}/resources/css/basic-jquery-slider.css" rel="stylesheet" type="text/css" media="screen">
<link href="${contextPath}/resources/css/mobile.css" rel="stylesheet" type="text/css">
<script src="${contextPath}/resources/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/stickysidebar.jquery.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/basic-jquery-slider.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/tabs.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/carousel.js" type="text/javascript"></script>
<script>
	// 슬라이드 
	$(document).ready(function() {
		$('#ad_main_banner').bjqs({
			'width' : 775,
			'height' : 145,
			'showMarkers' : true,
			'showControls' : false,
			'centerMarkers' : false
		});
	});
	// 스티키 		
	$(function() {
		$("#sticky").stickySidebar({
			timer : 100,
			easing : "easeInBounce"
		});
	});
</script>
	<title><jsp:include page="${title}" /></title>
	
</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<header>
				   <jsp:include page="header.jsp" />
			</header>
			<div class="clear"></div>
			<aside>
				 <jsp:include page="side.jsp"/>
			</aside>
			<article>
				<jsp:include page="${body}" /> 
			</article> 
			<div class="clear"></div>
			<footer>
        		<jsp:include page="footer.jsp"/>
        	</footer>
		</div>
		 <jsp:include page="quickMenu.jsp"/>
    </div>        	
</body>      
        
        