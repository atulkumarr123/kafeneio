<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<html class="js">
<head>
<title>Sales Analytics</title>

<link rel="stylesheet" href="static/css/bootstrap.css"></link>
<link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>


<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link href = "static/css/bootstrap-datetimepicker-4.17.37.min.css" rel = "stylesheet">
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 
<script src="static/JSlib/jquery.validate.js"></script>
<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>
<script src="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>
<script src="static/JSlib/salesAnalytics.js"></script>
</head>

<form:form action="" id = "foodItemsReport" commandName="foodCategoryReport">
<input type="hidden" name="dateTimeFormatCalendar" id="dateTimeFormatCalendar" value="${dateTimeFormatCalendar}"/> 
<input type="hidden" name="toDateTime" id="toDateTime" value="${toDateTime}"/> 
<input type="hidden" name="fromDateTime" id="fromDateTime" value="${fromDateTime}"/> 
	<div class="container">
		<%@include file="../menu.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
						<div class="page-header-kafeneio" ><label class="control-label"><h3>Sales Analytics</h3></label></div>
			
				<div class="widget-content">
	
						<fieldset>
						<div class="form-group">
							 	<c:forEach items="${modeOfPayments}" var="mode" varStatus = "status">
									<div class="col-md-2">
										<input type="checkbox" id="modeOfPayment${status.index}" name="modeOfPayment" value="${mode.id}" /> 
										<label for="modeOfPayment${status.index}"><span></span>${mode.description}</label>
									</div>
								</c:forEach> 
							</div>
							<br>
								<div class="col-md-6">
								<label for="prepended-input" class="control-label">Category</label>
								<form:select path="id" class="form-control" id="category"
									required="required">
									<option value="0">SELECT</option>
									<form:options items="${categoryList}" itemValue="id" itemLabel="foodDesc" />
								</form:select>
							</div>
							
						<!-- 	<div class="col-md-6">
							<select name="basicOptgroup[]" multiple="multiple" class="2col active">
					            <optgroup label="FoodItems">
					                <option value="HTML">HTML</option>
					                <option value="CSS">CSS</option>
					                <option value="CSS3">CSS3</option>
					                <option value="jQuery">jQuery</option>
					                <option value="JavaScript">JavaScript</option>
					            </optgroup>
					            <optgroup label="High Level Languages">
					                <option value="Java">Java</option>
					                <option value="Python">Python</option>
					            </optgroup>
					            <option value="cpp">C++</option> 
						   </select>
							</div> -->
							
						</fieldset>
					<fieldset>					
						<div class="form-group">
				
						<div class="col-md-6">
						
							<label for="prepended-input" class="control-label">From Date</label>
							<div class='input-group date' id='fromDateTimePicker'>
								<input type='text' id ="fromReportDate"  class="form-control" /> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span> </span>
							</div>
							</div>
							<div class="col-md-6">
							<label for="prepended-input" class="control-label">To Date</label>
							<div class='input-group date' id='toDateTimePicker'>
								<input type='text' id ="toReportDate" class="form-control" /> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span> </span>
							</div>
							</div>
							
						</div>
					</fieldset>
					<div class="form-actions">
						<div>	
							<button class="btn btn-default" type="button" onclick="searchSales()">Search Sales</button>
							</div>
					</div>
				</div>
				<table id="salesAnalyticsGrid"></table>
				 <div id="pager" style="height: 50;"></div>
				 
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
</form:form>
