<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html class="js">
<head>
<title>Kafeneio</title>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>
<link rel="stylesheet" href="static/css/bootstrap.css"></link>
 <link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>

<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link href = "static/css/bootstrap-datetimepicker-4.17.37.min.css" rel = "stylesheet">
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 

<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>

<script src="static/JSlib/reports.js"></script>
</head>
<form action="">

<input type="hidden" name="dateTimeFormatCalendar" id="dateTimeFormatCalendar" value="${dateTimeFormatCalendar}"/> 
<input type="hidden" name="toDateTime" id="toDateTime" value="${toDateTime}"/> 
<input type="hidden" name="fromDateTime" id="fromDateTime" value="${fromDateTime}"/> 
	<div class="container">
		<%@include file="menu.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="widget-content">
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
							<button class="btn btn-default" type="button" onclick="searchOrders()">Search Orders</button>
							<button class="btn btn-default" type="button" onclick="searchExpenses()">Search Expenses</button>
						</div>
					</div>

				</div>

				<table id="orderReportGrid"></table>
				 <div id="pager" style="height: 50;"></div>
				<br>
				
				<table id="expenseReportGrid"></table>
				 <div id="pager" style="height: 50;"></div>
				<br>
				
				<!-- <table id="application-list"></table>
				 <div id="application-list-pager" style="height: 50;"></div>
				 <br> -->
				<div style="text-align: left;">
					<button class="btn btn-default" type="button"  onclick="saveExpenses()">Export to Excel</button>
					<button class="btn btn-default" type="button" onclick="">Generate PDF</button>
				</div>

			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
</form>