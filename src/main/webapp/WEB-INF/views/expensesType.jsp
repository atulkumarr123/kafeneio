<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html class="js">
<head>
<title>Kafeneio</title>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>
<link rel="stylesheet" href="static/css/bootstrap.css"></link>
<link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>

<link href="static/css/app.css" rel="stylesheet"></link>
<link href="./static/css/theme.css" media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<link href = "static/css/ui/1.10.4/jquery-ui.css" rel = "stylesheet">
<link href="static/css/pnotify.custom.css" rel="stylesheet"></link>
<link rel="stylesheet" href="static/css/kafeneio.css"></link>
<script src="static/js/app.js" /></script>
<script src="static/JSlib/jquery/1.12.4/jquery.min.js"></script>
<script src="static/JSlib/jquery.validate.js"></script>
<script src="static/JSlib/twitter-bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="static/JSlib/moment-2.10.6.min.js"></script> 
<script src="static/JSlib/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
<script src="static/JSlib/bootstrap-datetimepicker-4.17.37.min.js"></script>
<script src="static/JSlib/pnotify.custom.js" /></script>
<script src="static/JSlib/twitter-bootstrap/waitingfor.js"></script>

<script src="static/JSlib/expensesType.js"></script>
</head>
 <form:form action="" id = "unitForm" commandName="units">
	<div class="container">
		<%@include file="menu.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-10">
				<div class="page-header-kafeneio" ><label class="control-label"><h3>Expenses Type</h3></label></div>
				<div class="widget-content">
					<fieldset>							
							<div class="form-group">
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Expenses Type
										Code</label> <input type="text" name="expensesTypeCode" 
										placeholder="" class="form-control"
										id="expensesTypeCode" required>
								</div>							
								<div class="col-md-6">
									<label for="normal-field" class="control-label">Expenses Type
										Description</label> <input type="text" name="expensesTypeDesc" 
										placeholder="" class="form-control"
										id="expensesTypeDesc" required>
								</div>							
							</div>
				 		<div class="form-group">
							<div class="col-md-6">
								<label for="prepended-input" class="control-label">Status</label>
						 		<form:select path="id" class="form-control" id="status">
									<option value="1">Active</option>
									<option value="0">Inactive</option>
								</form:select>
						 	</div>
						</div> 					
					</fieldset>
					<div class="form-actions">
						<div>
							<button class="btn btn-default" id= "addExpenseTypeButton" type="button" >Add</button>
							<button class="btn btn-default" id= "searchExpenseTypeButton" type="button" onclick = "searchAndEditExpenseType()" >Search & Edit</button>
						</div>
					</div>
				</div>
				<table id="expenseTypeGrid"></table>
				<table id="editExpenseTypeGrid"></table>
				<div id="pager" style="height: 50;"></div>
				<br>
				<div style="text-align: left;">
					<button class="btn btn-default" type="button" id="saveExpenseTypeButton" onclick="saveExpenseType()">Save</button>
					<button class="btn btn-default" type="button" onclick="reloadExpenseTypeGrid()">Clear</button>
				</div>
			</div>
			<div class="col-lg-1"></div>
		</div>
	</div>
</form:form>

<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="expenseTypeModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit Expense Type</h4>
        </div>
        <div class="modal-body">
        <fieldset>
						<input type="hidden" id="expenseTypeRowId" name="unitRowId" />
						<div class="form-group">
							<div class="col-md-6">
								<label for="normal-field" class="control-label">Expense Type
									Code</label> <input type="text" name="expenseTypeCodeModal" placeholder=""
									class="form-control" id="expenseTypeCodeModal" required>
							</div>
							<div class="col-md-6">
								<label for="normal-field" class="control-label">Expense Type
									Description</label> <input type="text" name="expenseTypeDescriptionModal"
									placeholder="" class="form-control" id="expenseTypeDescriptionModal" required>
							</div>
							<div class="col-md-6">
								<label for="prepended-input" class="control-label">
								Status</label> 
								<select path="id" class="form-control"
									id="statusModal" required="required">
									<option value="1">Active</option>
									<option value="0">Inactive</option>
								</select>
							</div>
						</div>
					</fieldset>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id= "updateExpenseTypeButton" onclick="updateExpenseType()">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelExpenseTypeButton" >Close</button>
        </div>
      </div>
    </div>
  </div>
</div>