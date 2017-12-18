<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html class="js">
<head>
<title>Kafeneio</title>

<link rel="stylesheet" href="static/css/bootstrap.css"></link>
<link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>
<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link href = "static/css/bootstrap-datetimepicker-4.17.37.min.css" rel = "stylesheet">
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 

<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>
<!-- <script src="static/JSlib/bootstrap-modal.2.0.4.js"></script>
 -->
<script src="static/JSlib/reports.js"></script>
</head>
<body>
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

								<!-- <div class="col-md-12"> -->
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="inlineCheckbox1" value="option1">
										<label for="inlineCheckbox1"> Inline One </label>
									</div>
									
								<!-- </div> -->
							</div>
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


 <div class="container">
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Expense</h4>
        </div>
        <div class="modal-body">
        <fieldset>
        				<input type="hidden" id="expenseRowId" name="expenseRowId"/>
							 <div class="form-group">
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Code</label> <input type="text" name="foodItemCode1" placeholder=""
										class="form-control" id="foodItemCode1" required>
								</div>

								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Description</label> <input type="text" name="fodItemDesc"
										placeholder="" class="form-control" id="foodItemDesc" required>
								</div>

							<div class="col-md-6">
									<label for="normal-field" class="control-label">Amount
										</label> <input type="text" name="amount" placeholder=""
										class="form-control" id="amount" required>
								</div>
	
							</div>


							<div class="form-group">
								<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="date">Date</label>
									<div class='input-group date' id='datetimepicker3'>
										<input type='text' class="form-control" id="expenseDateTime"
											required /> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span> </span>
									</div>
								</div>
							</div>
							
							
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Remarks
										</label> <input type="text" name="remarks" placeholder=""
										class="form-control" id="remarks" required>
								</div>
	
							
						</fieldset>
		 
		 		 
		  
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id= "updateExpensesButton" onclick="updateExpense()">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelExpensesButton" >Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>