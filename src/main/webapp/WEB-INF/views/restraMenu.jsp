<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html  class="js">
<head>
<title><spring:message code="kafeneio.main.title"/></title>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="static/css/bootstrap.css"></link>
<link href="static/css/app.css" rel="stylesheet"></link>
<link href="static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.14.1/css/ui.jqgrid.min.css">
<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>


<script src="static/js/app.js" /></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/jQuery.print.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>

<script  src="static/JSlib/kafeneioHome.js"></script>
</head>
<body>

<input type="hidden" name="currentDateTime" id="currentDateTime" value="${currentDateTime}"/> 
<input type="hidden" name="orderNumber" id="orderNumber" value=""/> 

	<div class="container">
				<%@include file = "menu.jsp" %>
		</div>
		<div class="container" id="menuChildDiv">
		<div class="row">
		<div class="col-lg-8">
					<div class="row" style="margin-left: 10">
						<div class="top-navbar header b-b">
							<button class=" navbar navbar-toggle" data-toggle="collapse"
								data-target=".navheadercollapse">
								<span class=" icon-bar"></span> <span class=" icon-bar"></span>
								<span class=" icon-bar"></span>
							</button>
							<div>
								<ul class=" nav navbar-nav">
									<li><a href="#" onclick="getItems('KC');">Kafeneio Coolers</a></li>
									<li><a href="#" onclick="getItems('BUR');">Burgers</a></li>
									<li><a href="#" onclick="getItems('SM');">Sandwich Mania</a></li>
									<li><a href="#" onclick="getItems('WR');">Wraps & Rolls</a></li>
									<li><a href="#" onclick="getItems('PST');">Pasta</a></li>
									<li><a href="#" onclick="getItems('CHI');">Chinese</a></li>
									<li><a href="#" onclick="getItems('ATF');">All Time Favourite</a></li>
									<li><a href="#" onclick="getItems('KOC');">Kafeneio Special</a></li>
									<li><a href="#" onclick="getItems('PL');">Protein Loaders</a></li>
									<li><a href="#" onclick="getItems('CL');">Carb Loaders</a></li>
									<li><a href="#" onclick="getItems('AO');">Ad Ons</a></li>
								</ul>
							</div>
						</div>
					</div>
					  <div class="row" style="margin-left: 5" id="itemsRow"></div>
				</div>
				<div class="col-lg-4">
				
				<div id="printGrid" style="width: 253px;text-align: center;">
					<h5>
						<b>The Kafeneio</b>
					</h5>
					<br>
					
					<table id="invoiceGrid"></table>
					<h5> <span class="glyphicon glyphicon-telephone"> 011-49148538 </span></h5>
					<h5>D-1 Central Mkt Surajmal Vihar 110092</h5>
					
				</div>
				
				
					<br>
						<div style="text-align: left;">
							<button class="btn btn-default" type="button" onclick="generateBill();">Print</button>
							<button class="btn btn-default" type="button" onclick="reloadGrid()">Clear</button>
					</div>
				</div>
			</div>

<%-- <c:choose>
    <c:when test="${fileName=='foodCategory'}">
	<jsp:include page="${fileName}.jsp" />  
    </c:when>    
    <c:otherwise>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${fileName=='signUp'}">
	<jsp:include page="${fileName}.jsp" />  
    </c:when>    
    <c:otherwise>
      
    </c:otherwise>
  </c:choose>
<c:choose>
    <c:when test="${fileName=='billingHome'}">
	<jsp:include page="${fileName}.jsp" />  
    </c:when>    
    <c:otherwise>
      
    </c:otherwise>
</c:choose> --%>

    <div ui-view style="margin-top:50px"></div>
		<div class="bottom-nav footer">
			<button class=" navbar navbar-toggle" data-toggle="collapse"
				data-target=".navfootercollapse">
				<span class=" icon-bar"></span> <span class=" icon-bar"></span> <span
					class=" icon-bar"></span>
			</button>
			<div class=" collapse navbar-collapse navfootercollapse">
				<ul class=" nav navbar-nav">
					<li><a href="#">Terms and conditions</a></li>
					<li><a href="#">Privacy Policy</a></li>
					<li><a href="#">Renewable Policy</a></li>
					<li><a href="#">Task Holidays</a></li>
					<li><a href="#">FAQ</a></li>
					<li><a href="#">Contact us</a></li>
				</ul>
			</div>
		</div>		 	
	</div>	
</body>
</html>