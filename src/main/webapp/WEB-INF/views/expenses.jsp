<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html class="js">
<head>
<title><spring:message code="kafeneio.main.title"/></title>
<link rel="stylesheet" href="static/css/bootstrap.css"></link>
 <link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>
<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link href = "static/css/bootstrap-datetimepicker-4.17.37.min.css" rel = "stylesheet">
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>
<link href="static/css/bootstrap-tagsinput.css" rel="stylesheet"></link>


<link rel="stylesheet" href="static/css/kafeneio.css"></link>

<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/jquery.validate.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 
<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>
<script src="static/JSlib/bootstrap-tagsinput.js" /></script>
<script src="static/JSlib/twitter-bootstrap/waitingfor.js"></script>





<script src="static/JSlib/expenses.js"></script>
</head>
<form action="" name="expenses" id = "expenses">
 <input type="hidden" name="currentDate" id="currentDate" value="${currentDate}"/>
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
									<label for="normal-field" class="control-label">Item
										Description</label> <input type="text" name="itemDesc" 
										placeholder="" class="form-control"
										id="itemDesc" required>
								</div>
								<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="amount">Amount</label>
									<input type="text" class="form-control" name="amount"
										id="amount" placeholder="" required>
								</div>
							</div>
						<div class="form-group">
						<div class="col-md-6">
							<label for="prepended-input" class="control-label" name="date">Date</label>
							<div class='input-group date' id='datetimepicker3'>
								<input type='text' class="form-control" id="expenseDateTime" required/> <span
									class="input-group-addon"><span
									class="glyphicon glyphicon-calendar"></span> </span>
							</div>
							</div>
							<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="remarks">Remarks</label>
									<textarea class="form-control" name="remarks" id="remarks"></textarea>
								</div>
						</div>
							<!-- <input type="text" value="Amsterdam,Washington,Sydney,Beijing,Cairo" data-role="tagsinput" /> -->
					</fieldset>
					<div class="form-actions">
						<div>
							<button class="btn btn-default" id= "addExpensebutton" type="button" >Add</button>
							<button class="btn btn-default" id= "searchAndEditExpensesbutton" type="button" onclick = "searchAndEditExpenses()" >Search & Edit</button>
						</div>
					</div>
				</div>
				<table id="expensesGrid"></table>
				<table id="searchAndEditExpensesGrid"></table>
				<br>
				<div style="text-align: left;">
					<button class="btn btn-default" type="button"  onclick="saveExpenses()" id="saveExpensesButton">Save</button>
					<button class="btn btn-default" type="button" onclick="clearExpenses()" id= "clearExpensesButton">Clear</button>
				</div>
			</div>
			<div class="col-lg-2"></div>
		</div>
	</div>
</form>

<div class="container">
  <div class="modal fade" id="myModalExpenses" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Expense</h4>
        </div>
        <div class="modal-body">
        <fieldset>
        				<input type="hidden" id="expenseRowId" name="expenseRowId"/>
							 <div class="form-group">
<!-- 								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Code</label> <input type="text" name="foodItemCode1" placeholder=""
										class="form-control" id="foodItemCode1" required>
								</div>
 -->
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Item
										Description</label> <input type="text" name="fodItemDesc"
										placeholder="" class="form-control" id="foodItemDescModal" required>
								</div>

							<div class="col-md-6">
									<label for="normal-field" class="control-label">Amount
										</label> <input type="text" name="amount" placeholder=""
										class="form-control" id="amountModal" required>
								</div>
	
							</div>


							<div class="form-group">
								<div class="col-md-6">
									<label for="prepended-input" class="control-label" name="date">Date</label>
									<div class='input-group date' id='datetimepicker3'>
										<input type='text' class="form-control" id="expenseDateTimeModal"
											required /> <span class="input-group-addon"><span
											class="glyphicon glyphicon-calendar"></span> </span>
									</div>
								</div>
							</div>
							
							
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Remarks
										</label> <input type="text" name="remarks" placeholder=""
										class="form-control" id="remarksModal" required>
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